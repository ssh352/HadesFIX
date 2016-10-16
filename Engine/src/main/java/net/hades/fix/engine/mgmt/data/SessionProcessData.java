/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionData.java
 *
 * $Id: SessionProcessData.java,v 1.7 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

/**
 * Session management data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 28/04/2010
 */
public class SessionProcessData extends ProcessData implements CompositeDataView {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    private static final String[] SESS_DATA_ITEMS;
    private static final String[] SESS_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] SESS_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
	try {
            SESS_DATA_ITEMS = new String[] {"id", "name", "status", "config", "counterparty", "transport", "protocol",
                "consumerStream", "producerStream"};
            SESS_DATA_ITEMS_DESCRIPTION = new String[] {"ID", "Sess Addr", "Stat",
                "Configuration data", "Cpty Addr", "Transport data", "Protocol data", "Consumer stream data", "Producer stream data"};
            SESS_DATA_OPEN_TYPES = new OpenType<?>[] {SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING,
                SimpleType.STRING, TransportProcessData.DataType, ProtocolProcessData.DataType, StreamProcessData.DataType,
                StreamProcessData.DataType};
            DataType = new CompositeType("SessionCompositeType", "Session data composite type", SESS_DATA_ITEMS,
                    SESS_DATA_ITEMS_DESCRIPTION, SESS_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    PrintWriter pout = new PrintWriter(bout);
	    e.printStackTrace(pout);
	    pout.flush();
	    throw new RuntimeException(bout.toString());
	}
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private String counterparty;

    private TransportProcessData transportProcessData;

    private ProtocolProcessData protocolProcessData;

    private StreamProcessData consumerProcessData;

    private StreamProcessData producerProcessData;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SessionProcessData() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public StreamProcessData getConsumerProcessData() {
        return consumerProcessData;
    }

    public void setConsumerProcessData(StreamProcessData consumerProcessData) {
        this.consumerProcessData = consumerProcessData;
    }

    public StreamProcessData getProducerProcessData() {
        return producerProcessData;
    }

    public void setProducerProcessData(StreamProcessData producerProcessData) {
        this.producerProcessData = producerProcessData;
    }

    public ProtocolProcessData getProtocolProcessData() {
        return protocolProcessData;
    }

    public void setProtocolProcessData(ProtocolProcessData protocolProcessData) {
        this.protocolProcessData = protocolProcessData;
    }

    public TransportProcessData getTransportProcessData() {
        return transportProcessData;
    }

    public void setTransportProcessData(TransportProcessData transportProcessData) {
        this.transportProcessData = transportProcessData;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<String>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<String>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<OpenType<?>>();
            for (String item : itemNames) {
                itemDescriptions.add(ct.getDescription(item));
                itemTypes.add(ct.getType(item));
            }

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, SESS_DATA_ITEMS,
                    new Object[] {id, name, status != null ? status.name() : "UNKNOWN", config, counterparty,
                        transportProcessData != null ? transportProcessData.toCompositeData(TransportProcessData.DataType) : null,
                        protocolProcessData != null ? protocolProcessData.toCompositeData(ProtocolProcessData.DataType) : null,
                        consumerProcessData != null ? consumerProcessData.toCompositeData(StreamProcessData.DataType) : null,
                        producerProcessData != null ? producerProcessData.toCompositeData(StreamProcessData.DataType) : null});
            assert ct.isValue(cd);
            
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
