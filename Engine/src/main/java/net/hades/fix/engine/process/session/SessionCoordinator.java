/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.*;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ProtocolException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.model.SessionAddress;
import net.hades.fix.engine.process.Advisable;
import net.hades.fix.engine.process.ManagedTask;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.stream.ConsumerStream;
import net.hades.fix.engine.process.stream.ProducerStream;
import net.hades.fix.message.*;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;

/**
 * Abstract class to be extended by a session coordinator.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class SessionCoordinator implements ManagedTask, Advisable {

    private static final Logger Log = Logger.getLogger(SessionCoordinator.class.getName());
    
    public static final String SESSION_CONTEXT_KEY = "SESSION_CONTEXT_KEY";

    protected HadesInstance hadesInstance;

    protected String id;
    protected CounterpartyInfo cptyConfiguration;
    protected SessionInfo sessionConfiguration;
    protected SessionAddress sessionAddress;
    protected final BlockingQueue<String> commandQueue;

    protected Protocol protocol;

    protected EventProcessor eventProcessor;

    protected ProducerStream producerStream;
    protected ConsumerStream consumerStream;
    protected volatile TaskStatus status;
    protected volatile boolean shutdown;
    protected ConcurrentMap<String, Object> sessionContext;

    public SessionCoordinator(HadesInstance hadesInstance, CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) throws ConfigurationException {
        this.hadesInstance = hadesInstance;
	this.cptyConfiguration = cptyConfiguration;
        this.sessionAddress = sessionAddress;
	commandQueue = new ArrayBlockingQueue<>(1);
	sessionContext = new ConcurrentHashMap<>();
	sessionConfiguration = getSessionConfiguration(cptyConfiguration, sessionAddress);
    }

    protected SessionInfo getConfiguration() {
	return sessionConfiguration;
    }

    public ConcurrentMap<String, Object> getSessionContext() {
	return sessionContext;
    }
    
    /**
     * Runs the stream handlers upon the TCP connection success.
     * @param clientSocket TCP connection
     */
    public abstract void startStreamHandlers(Socket clientSocket);

    public SessionAddress getSessionAddress() {
	return sessionAddress;
    }

    public String getCptyID() {
        return sessionAddress.getRemoteAddress().getID();
    }

    public String getLocalID() {
        return sessionAddress.getLocalAddress().getID();
    }
    
    public ExecutorService getExecutorService() {
	return hadesInstance.getExecutorService();
    }

    public void sendResetSequenceMessage(int newSeqNum) throws ProtocolException, InterruptedException {
        SequenceResetMsg msg;
        try {
            msg = MessageFiller.buildSequenceResetMsg(protocol);
            msg.setGapFillFlag(Boolean.FALSE);
            msg.setNewSeqNo(newSeqNum);
            protocol.setRxSeqNo(newSeqNum - 1);
            msg.setPriority(Message.PRIORITY_HIGH);
            protocol.writeToTransport(msg);
        } catch (InvalidMsgException ex) {
            String errMsg = "Invalid SequenceReset message for session [" + id + "].";

            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });

            eventProcessor.onAlertEvent(new AlertEvent(this,
                    Alert.createAlert(id, this.getClass().getSimpleName(), BaseSeverityType.RECOVERABLE,
                    AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
            throw new ProtocolException(errMsg, ex);
        } catch (TagNotPresentException ex) {
	    Logger.getLogger(SessionCoordinator.class.getName()).log(Level.SEVERE, null, ex);
	} catch (BadFormatMsgException ex) {
	    Logger.getLogger(SessionCoordinator.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Sends a Logon message with ResetSeqNumFlag set to true that will trigger a sequence
     * number reset on both sides.
     * @throws net.hades.fix.engine.exception.ProtocolException
     */
    public void sessionReset() throws ProtocolException {
        if (TaskStatus.Running.equals(status)) {
            try {
                protocol.setTxSeqNo(0);
                LogonMsg logonMessage = MessageFiller.buildResetSeqNumLogonMsg(protocol);
                logonMessage.setPriority(Message.PRIORITY_HIGH);
                protocol.writeToTransport(logonMessage);
            } catch (TagNotPresentException ex) {
		String errMsg = "Invalid Reject message for session [" + id + "].";

                Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(id, this.getClass().getSimpleName(), BaseSeverityType.RECOVERABLE,
                                AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
	    } catch (BadFormatMsgException ex) {
		String errMsg = "Invalid Reject message for session [" + id + "].";

                Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(id, this.getClass().getSimpleName(), BaseSeverityType.RECOVERABLE,
                                AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
	    } catch (InterruptedException ex) {
		String errMsg = "Invalid Reject message for session [" + id + "].";

                Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});

                eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(id, this.getClass().getSimpleName(), BaseSeverityType.RECOVERABLE,
                                AlertCode.MSG_FORMAT_ERROR, errMsg, ex)));
	    } catch (InvalidMsgException ex) {
		Logger.getLogger(SessionCoordinator.class.getName()).log(Level.SEVERE, null, ex);
	    }
        } else {
            throw new ProtocolException("Could not reset session sequence number. The session is not active.");
        }
    }

    @Override
    public void onAlertEvent(AlertEvent message) {
	hadesInstance.getEventProcessor().onAlertEvent(message);
    }

    @Override
    public void onLifeCycleEvent(LifeCycleEvent message) {
	hadesInstance.getEventProcessor().onLifeCycleEvent(message);
    }

    @Override
    public void onMessageEvent(MessageEvent message) {
	hadesInstance.getEventProcessor().onMessageEvent(message);
    }

    private SessionInfo getSessionConfiguration(CounterpartyInfo cptyConfiguration, SessionAddress sessionAddress) {
	for (SessionInfo config : cptyConfiguration.getSessions()) {
	    CounterpartyAddress localAddr = new CounterpartyAddress(config.getCompID(), config.getSubID(), config.getLocationID());
	    if (localAddr.equals(sessionAddress.getLocalAddress().getCompID())) {
		return config;
	    }
	}
	return null;
    }

}
