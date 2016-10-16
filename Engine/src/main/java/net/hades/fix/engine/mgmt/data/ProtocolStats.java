/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProtocolStats.java
 *
 * $Id: ProtocolStats.java,v 1.4 2011-04-03 08:00:08 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

/**
 * Stats collected by the FIX processes.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/04/2010
 */
public class ProtocolStats extends Stats implements CompositeDataView {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    private static final String[] PROTO_STATS_ITEMS;
    private static final String[] PROTO_STATS_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] PROTO_STATS_OPEN_TYPES;

    public static CompositeType DataType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
	try {
            PROTO_STATS_ITEMS = new String[] {"startTimestamp", "totMsgInCount", "totMsgOutCount", "rejMsgCount"};
            PROTO_STATS_ITEMS_DESCRIPTION = new String[] {"Timestamp when transport started", "Total number of messages received",
                "Total number of messages transmitted", "Number of messages rejected"};
            PROTO_STATS_OPEN_TYPES = new OpenType<?>[] {SimpleType.DATE, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.INTEGER};
            DataType = new CompositeType("ProtocolStatsCompositeType", "FIX protocol statistics composite type", PROTO_STATS_ITEMS,
                    PROTO_STATS_ITEMS_DESCRIPTION, PROTO_STATS_OPEN_TYPES);
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

    private AtomicInteger totMsgInCount;

    private AtomicInteger totMsgOutCount;

    private AtomicInteger rejMsgCount;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ProtocolStats() {
        totMsgInCount = new AtomicInteger(0);
        totMsgOutCount = new AtomicInteger(0);
        rejMsgCount = new AtomicInteger(0);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public int getRejMsgCount() {
        return rejMsgCount.intValue();
    }

    public int incrRejMsgCount() {
        return rejMsgCount.incrementAndGet();
    }

    public int getTotMsgInCount() {
        return totMsgInCount.intValue();
    }

    public int incrTotMsgInCount() {
        return totMsgInCount.incrementAndGet();
    }

    public int getTotMsgOutCount() {
        return totMsgOutCount.intValue();
    }

    public int incrTotMsgOutCount() {
        return totMsgOutCount.incrementAndGet();
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
            CompositeData cd = new CompositeDataSupport(xct, PROTO_STATS_ITEMS,
                    new Object[] {startTimestamp, totMsgInCount.intValue(), totMsgOutCount.intValue(), rejMsgCount.intValue()});
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
