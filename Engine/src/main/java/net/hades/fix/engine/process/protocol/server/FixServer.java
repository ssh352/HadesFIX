/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.server;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.commons.exception.ExceptionUtil;

import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.engine.config.model.ClientSessionInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.SeqNoPersistenceException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.protocol.*;
import net.hades.fix.engine.process.protocol.client.FixClient;
import net.hades.fix.engine.process.session.ClientSessionCoordinator;
import net.hades.fix.engine.util.PartyUtil;
import net.hades.fix.engine.util.ThreadLocalSessionDataUtil;
import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.exception.InvalidMsgException;

/**
 * Implementation of a FIX server process.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public final class FixServer extends Protocol {

    private static final Logger Log = Logger.getLogger(FixServer.class.getName());

    private static final String COMPONENT_NAME = "FIXCLI";

    private static final int DEFAULT_LOGON_TIMEOUT = 60;
    private static final boolean DO_NOT_RECON_WHEN_SEQ_TOO_LOW = false;
    private static final boolean DEFAULT_CONN_ON_STARTUP = true;
    private static final int DEFAULT_RECONNECT_DELAY = 10000;
    private static final int DEFAULT_MAX_NUM_LOGON_RETRIES = 0;

    private AtomicLong orderSequence;

    public FixServer(ClientSessionCoordinator coordinator, ClientSessionInfo configuration) throws ConfigurationException {
	super(coordinator, configuration);
	setSessionConfigData();
	initialise();
	status = TaskStatus.New;
    }

    @Override
    public ExecutionResult call() throws Exception {
	Log.log(Level.INFO, "Running Fix Client thread [{0}].", id);

	status = TaskStatus.Running;
	protocolState = ProtocolState.INITIALISED;
	ServerSessionMessageProcessor processor = new ServerSessionMessageProcessor(this);

	try {
	    List<FIXMsg> response;
	    while (!shutdown) {
		Message message = rxQueue.peek();
		if (message == null) {
		    Thread.sleep(1);
		    continue;
		}
		response = Collections.emptyList();
		switch (protocolState) {

		    case INITIALISED:
			// accept only counterparty Login message
			if (message instanceof BinaryMessage) {
			    response = processor.processInitStateMessageRcvd((BinaryMessage) rxQueue.take());
			}
			break;

		    case LOGGEDON:
			if (message instanceof BinaryMessage) {
			    response = processor.processLoggedonStateMessageRcvd((BinaryMessage) rxQueue.take());
			} else {
			    FIXMsg msg = (FIXMsg) rxQueue.take();
			    response = processor.processLogoutMessageTrns(msg);
			    writeToTransport(msg);
			}
			break;

		    case LOGGEDOUT:
			// only accept Logout and ResendRequest
			if (message instanceof BinaryMessage) {
			    response = processor.processLoggedoutStateMessageRcvd((BinaryMessage) rxQueue.take());
			}
			break;
		}
		if (!response.isEmpty()) {
		    for (Message send : response) {
			writeToTransport(send);
		    }
		}

	    }
	} catch (InvalidMsgException | InterruptedException | DisconnectSessionException ex) {
	    Log.log(Level.SEVERE, "Unexpected exception raised by Fix Client", ex);
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixClient.class.getSimpleName(),
		    BaseSeverityType.FATAL, AlertCode.SESSION_DESTROYED, ex.getMessage(), ex)));
	    status = TaskStatus.Error;
	    return new ExecutionResult(status);
	}
	status = TaskStatus.Completed;
	return new ExecutionResult(status);
    }

    @Override
    public void write(Message message) {
	try {
	    rxQueue.put(message);
	    applyOrdering(message);
	} catch (InterruptedException ex) {
	    Log.log(Level.SEVERE, "Protocol [{0}] write() interrupted", id);
	}
    }

    @Override
    public boolean tryWrite(Message message, int waitMillis) {
	try {
	    boolean outcome = rxQueue.offer(message, 1, TimeUnit.SECONDS);
	    if (outcome) {
		applyOrdering(message);
	    }
	    return outcome;
	} catch (InterruptedException ex) {
	    Log.log(Level.SEVERE, "Protocol [{0}] tryWrite() interrupted", id);
	}
	return true;
    }

    public void relayMessage(FIXMsg message) {
	nextHandlers.values().iterator().next().write(message);
    }

    @Override
    public int getNextRxSeqNo() {
	int rxSeqNo;
	try {
	    rxSeqNo = seqNoPersister.getNextRxSeqNo();
	} catch (SeqNoPersistenceException ex) {
	    rxSeqNo = seqNoPersister.getRxSeqNo();
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixClient.class.getSimpleName(),
		    BaseSeverityType.WARNING, AlertCode.SEQ_PERSISTENCE_ERROR, ex.toString(), ex)));
	}
	return rxSeqNo;
    }

    @Override
    public void setRxSeqNo(int rxSeqNo) {
	try {
	    seqNoPersister.setRxSeqNo(rxSeqNo);
	} catch (SeqNoPersistenceException ex) {
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixClient.class.getSimpleName(),
		    BaseSeverityType.WARNING, AlertCode.SEQ_PERSISTENCE_ERROR, ex.toString(), ex)));
	}
    }

    @Override
    public int getNextTxSeqNo() {
	int txSeqNo;
	try {
	    txSeqNo = seqNoPersister.getNextTxSeqNo();
	} catch (SeqNoPersistenceException ex) {
	    txSeqNo = seqNoPersister.getTxSeqNo();
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixClient.class.getSimpleName(),
		    BaseSeverityType.WARNING, AlertCode.SEQ_PERSISTENCE_ERROR, ex.toString(), ex)));
	}
	return txSeqNo;
    }

    @Override
    public void setTxSeqNo(int txSeqNo) {
	try {
	    seqNoPersister.setTxSeqNo(txSeqNo);
	} catch (SeqNoPersistenceException ex) {
	    coordinator.onAlertEvent(new AlertEvent(this, Alert.createAlert(id, FixClient.class.getSimpleName(),
		    BaseSeverityType.WARNING, AlertCode.SEQ_PERSISTENCE_ERROR, ex.toString(), ex)));
	}
    }

    @Override
    protected void setSessionConfigData() throws ConfigurationException {
	super.setSessionConfigData();
	try {
	    if (configuration.getLogonTimeout() == null) {
		configuration.setLogonTimeout(DEFAULT_LOGON_TIMEOUT);
	    }
	    if (((ClientSessionInfo) configuration).getConnectOnStartup() == null) {
		((ClientSessionInfo) configuration).setConnectOnStartup(DEFAULT_CONN_ON_STARTUP);
	    }
	    if (((ClientSessionInfo) configuration).getReconnectDelay() == null) {
		((ClientSessionInfo) configuration).setReconnectDelay(DEFAULT_RECONNECT_DELAY);
	    }
	    if (((ClientSessionInfo) configuration).getMaxNumLogonRetries() == null) {
		((ClientSessionInfo) configuration).setMaxNumLogonRetries(DEFAULT_MAX_NUM_LOGON_RETRIES);
	    }
	    if (((ClientSessionInfo) configuration).getDoNotReconnWhenSeqNumTooLow() == null) {
		((ClientSessionInfo) configuration).setConnectOnStartup(DO_NOT_RECON_WHEN_SEQ_TOO_LOW);
	    }
	} catch (Exception ex) {
	    String error = "Error configuring the Fix Client process.";
	    Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{error, ExceptionUtil.getStackTrace(ex)});
	    throw new ConfigurationException(error, ex);
	}
    }

    //---------------------------------------------------------------------------------------------------------------
    
    private void initialise() throws ConfigurationException {
	setSessionProtocolVersion();
	setSupportedMsgTypes();
	createSessionConfigDir();
	createSeqNoPersister();
	ThreadLocalSessionDataUtil.setThreadLocalSessionData(configuration, protocolVersion);
	createTimeoutTimers();
	orderSequence = new AtomicLong(0);
	rxQueue = new PriorityBlockingQueue<>(configuration.getRxBufferSize(), new MessagePriorityComparator());
	id = COMPONENT_NAME + "_" + PartyUtil.getID(targetCompID, targetLocationID, targetSubID);
	transportOut = nextHandlers.values().iterator().next();
    }

    private void applyOrdering(Message message) {
	message.setOrderSequence(orderSequence.getAndIncrement());
	if (message instanceof BinaryMessage) {
	    message.setPriority(Message.PRIORITY_HIGH);
	} else {
	    message.setPriority(Message.PRIORITY_NORMAL);
	}
    }

    @Override
    public Map getStatistics() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shutdown() {
	while (!rxQueue.isEmpty()) {
	    try {
		Thread.sleep(DEFAULT_SLEEP_MILLIS);
	    } catch (InterruptedException ex) {
		Log.log(Level.WARNING, "Thread [{0}] interrupted", id);
		break;
	    }
	    timeout = timeout.minusMillis(DEFAULT_SLEEP_MILLIS);
	    if (timeout.isNegative()) {
		Log.info(String.format("Tcp Client [%s] timedout shutdownImmediate() : [%s]", id, status));
		break;
	    }
	}
	shutdown = true;
    }

    @Override
    public void shutdownImmediate() {
	shutdown = true;
    }

    @Override
    public void setDisabled(boolean disabled) {
	throw new UnsupportedOperationException("Not supported for protocol handler."); 
    }

}
