/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestRejectMsg43TestData.java
 *
 * $Id: MarketDataRequestRejectMsg44TestData.java,v 1.1 2010-02-03 11:15:20 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MarketDataRequestRejectMsg;
import net.hades.fix.message.MsgTest;

import net.hades.fix.message.type.MDReqRejReason;

/**
 * Test utility for MarketDataRequestRejectMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class MarketDataRequestRejectMsg44TestData extends MsgTest {

    private static final MarketDataRequestRejectMsg44TestData INSTANCE;

    static {
        INSTANCE = new MarketDataRequestRejectMsg44TestData();
    }

    public static MarketDataRequestRejectMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(MarketDataRequestRejectMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setMdReqID("AAA564567");
        // AltMDSourceID
        msg.setNoAltMDSource(2);
        msg.getAltMDSourceGroups()[0].setAltMDSourceID("Source 1");
        msg.getAltMDSourceGroups()[1].setAltMDSourceID("Source 2");

        msg.setMdReqRejReason(MDReqRejReason.UnknownSymbol);
        msg.setText("I want these shares!");
        msg.setEncodedTextLen(new Integer(8));
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setEncodedText(textDataExp);
    }

    public void check(MarketDataRequestRejectMsg expected, MarketDataRequestRejectMsg actual) throws Exception {
        assertEquals(expected.getMdReqID(), actual.getMdReqID());
        // AltMDSourceID
        assertEquals(expected.getNoAltMDSource(), actual.getNoAltMDSource());
        assertEquals(expected.getAltMDSourceGroups()[0].getAltMDSourceID(), actual.getAltMDSourceGroups()[0].getAltMDSourceID());
        assertEquals(expected.getAltMDSourceGroups()[1].getAltMDSourceID(), actual.getAltMDSourceGroups()[1].getAltMDSourceID());
        
        assertEquals(expected.getMdReqRejReason(), actual.getMdReqRejReason());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
