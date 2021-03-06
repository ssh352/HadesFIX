/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.util;

import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.config.model.CounterpartyInfo;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.model.CounterpartyAddress;
import net.hades.fix.engine.process.EngineTask;
import net.hades.fix.engine.process.PriorityNamedTask;
import net.hades.fix.engine.process.protocol.timer.EngineTimerTask;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.process.transport.TcpClient;
import net.hades.fix.engine.process.transport.TcpServer;
import net.hades.fix.engine.process.transport.TcpWorker;


/**
 * Utility class used to return different data required for a party.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class PartyUtil {

    private PartyUtil() {
    }

    /**
     * Creates a string in the form compID_subID_locationID used mainly as an ID
     * for Counterparties.
     * @param compID comp ID
     * @param subID sub ID
     * @param locationID location ID
     * @return aggregated string
     */
    public static String getID(String compID, String subID, String locationID) {
        if (compID == null || compID.trim().isEmpty()) {
            throw new IllegalArgumentException("compID cannot be null or empty");
        }
        StringBuilder sb = new StringBuilder(compID);
        if (subID != null && !subID.trim().isEmpty()) {
            sb.append(".").append(subID);
        }
        if (locationID != null && !locationID.trim().isEmpty()) {
            sb.append(".").append(locationID);
        }

        return sb.toString();
    }

    /**
     * Creates a string in the form compID_subID_locationID used mainly as an ID
     * for Counterparties.
     * @param counterpartyInfo counterparty configuration data
     * @return aggregated string
     */
    public static String getID(CounterpartyInfo counterpartyInfo) {
        if (counterpartyInfo.getCompID() == null || counterpartyInfo.getCompID().trim().isEmpty()) {
            throw new IllegalArgumentException("compID cannot be null or empty");
        }
        StringBuilder sb = new StringBuilder(counterpartyInfo.getCompID());
        if (counterpartyInfo.getSubID() != null && !counterpartyInfo.getSubID().trim().isEmpty()) {
            sb.append(".").append(counterpartyInfo.getSubID());
        }
        if (counterpartyInfo.getLocationID() != null && !counterpartyInfo.getLocationID().trim().isEmpty()) {
            sb.append(".").append(counterpartyInfo.getLocationID());
        }

        return sb.toString();
    }

    /**
     * Creates a string in the form compID_subID_locationID used mainly as an ID
     * for Sessions.
     * @param sessionInfo counterparty configuration data
     * @return aggregated string
     */
    public static String getID(SessionInfo sessionInfo) {
        if (sessionInfo.getCompID() == null || sessionInfo.getCompID().trim().isEmpty()) {
            throw new IllegalArgumentException("compID cannot be null or empty");
        }
        StringBuilder sb = new StringBuilder(sessionInfo.getCompID());
        if (sessionInfo.getSubID() != null && !sessionInfo.getSubID().trim().isEmpty()) {
            sb.append(".").append(sessionInfo.getSubID());
        }
        if (sessionInfo.getLocationID() != null && !sessionInfo.getLocationID().trim().isEmpty()) {
            sb.append(".").append(sessionInfo.getLocationID());
        }

        return sb.toString();
    }

    /**
     * Creates a string in the form compID_subID_locationID used mainly as an ID
     * for Counterparties.
     * @param address session address
     * @return aggregated string
     */
    public static String getID(CounterpartyAddress address) {
        if (address.getCompID() == null || address.getCompID().trim().isEmpty()) {
            throw new IllegalArgumentException("compID cannot be null or empty");
        }
        StringBuilder sb = new StringBuilder(address.getCompID());
        if (address.getSubID() != null && !address.getSubID().trim().isEmpty()) {
            sb.append(".").append(address.getSubID());
        }
        if (address.getLocationID() != null && !address.getLocationID().trim().isEmpty()) {
            sb.append(".").append(address.getLocationID());
        }

        return sb.toString();
    }
    
    /**
     * Retrieve the session id for a given component.
     * @param component component
     * @return session ID
     */
    public static String getSessionId(Object component) {
        StringBuilder sb = new StringBuilder();
        if (component instanceof Handler) {
            sb.append(((Handler)component).getId());
        } else if (component instanceof SessionCoordinator) {
            sb.append(((SessionCoordinator)component).getId());
        } else if (component instanceof TcpClient) {
            sb.append(((TcpClient)component).getId());
	} else if (component instanceof TcpServer) {
            sb.append(((TcpServer)component).getId());
        } else if (component instanceof TcpWorker) {
            sb.append(((TcpWorker)component).getId());
        } else if (component instanceof PriorityNamedTask) {
            sb.append(((PriorityNamedTask)component).getName());
        } else {
            sb.append("Undefined");
        }
        
        return sb.toString();
    }
}
