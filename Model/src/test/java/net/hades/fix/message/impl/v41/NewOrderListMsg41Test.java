/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsg41Test.java
 *
 * $Id: NewOrderListMsg41Test.java,v 1.1 2011-02-02 10:03:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v41.data.NewOrderListMsg41TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 4.1 NewOrderListMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/05/2009, 12:18:17 PM
 */
public class NewOrderListMsg41Test extends MsgTest  {

    public NewOrderListMsg41Test() {
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
        NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_1);
        NewOrderListMsg41TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        NewOrderListMsg dmsg = (NewOrderListMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        NewOrderListMsg41TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode method, of secured message.
     * @throws Exception
     */
    @Test
    public void b4_testEncDecSecureAll() throws Exception {
        System.out.println("-->testEncDecSecureAll");
        setSecuredDataDES();
        try {
            NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_1);
            NewOrderListMsg41TestData.getInstance().populate(msg);
            String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
            System.out.println("encoded-->" + encoded);

            NewOrderListMsg dmsg = (NewOrderListMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
            dmsg.decode();
            NewOrderListMsg41TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetSecuredData();
        }
    }

    /**
     * Test of encode getter method, of class QuoteRequestMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        NewOrderListMsg msg = null;
        try {
            msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_1);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.getRootParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getNoOrders();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.getOrderListGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    /**
     * Test of encode setter method, of class NewOrderListMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        NewOrderListMsg msg = null;
        try {
            msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_1);
        } catch (Exception ex) {
            fail("Error building message");
        }

        try {
            msg.setRootParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearRootParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.setNoOrders(null);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.addOrderListGroup();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.deleteOrderListGroup(0);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
        try {
            msg.clearOrderListGroups();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedException(ex);
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class NewOrderListMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_4_1);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [ListID] [TotNoOrders] [Symbol] [Side] [OrderQty] [OrdType] is missing.", ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [NewOrderListMsg] message version [4.1].", ex.getMessage());
    }
}
