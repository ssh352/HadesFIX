/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ListCancelRequestMsg50Test.java
 *
 * $Id: ListCancelRequestMsg50Test.java,v 1.1 2011-02-06 01:06:13 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListCancelRequestMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0 ListCancelRequestMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class ListCancelRequestMsg50Test extends MsgTest {

    public ListCancelRequestMsg50Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        TestUtils.enableValidation();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b3_testEncodeDecodeAll() throws Exception {
        System.out.println("-->testEncodeDecodeAll");
        ListCancelRequestMsg msg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_5_0);
        TestUtils.populate44HeaderAll(msg);
        Calendar cal = Calendar.getInstance();
        msg.setListID("LST564567");
        msg.setTransactTime(cal.getTime());
        cal.set(2010, 3, 14, 12, 13, 13);
        msg.setTradeOriginationDate(cal.getTime());
        cal.set(2010, 3, 14, 13, 14, 15);
        msg.setTradeDate(cal.getTime());
        msg.setWaveNo("WAVE-635454");
        msg.setText("some text");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] { (byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253 };
        msg.setEncodedText(encodedText);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        ListCancelRequestMsg dmsg = (ListCancelRequestMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        assertEquals(msg.getListID(), dmsg.getListID());
        assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        assertDateEquals(msg.getTradeOriginationDate(), dmsg.getTradeOriginationDate());
        assertDateEquals(msg.getTradeDate(), dmsg.getTradeDate());
        assertEquals(msg.getWaveNo(), dmsg.getWaveNo());
        assertEquals(msg.getText(), dmsg.getText());
        assertEquals(msg.getEncodedTextLen(), dmsg.getEncodedTextLen());
        assertArrayEquals(msg.getEncodedText(), dmsg.getEncodedText());
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        ListCancelRequestMsg msg = null;
        try {
            msg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class ListCancelRequestMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        ListCancelRequestMsg msg = null;
        try {
            msg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class ListCancelRequestMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            ListCancelRequestMsg msg = (ListCancelRequestMsg) FIXMsgBuilder.build(MsgType.ListCancelRequest.getValue(), BeginString.FIX_5_0);
            TestUtils.populate44HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [ListID] [TransactTime] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [ListCancelRequestMsg] message version [5.0].", ex.getMessage());
    }

}
