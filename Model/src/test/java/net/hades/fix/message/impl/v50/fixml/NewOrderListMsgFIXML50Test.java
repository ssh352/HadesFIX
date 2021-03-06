/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderListMsgFIXML50Test.java
 *
 * $Id: NewOrderListMsgFIXML50Test.java,v 1.1 2011-02-02 10:03:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.NewOrderListMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50.OrderListGroup50TestData;
import net.hades.fix.message.impl.v50.data.NewOrderListMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for NewOrderListMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class NewOrderListMsgFIXML50Test extends MsgTest {

    public NewOrderListMsgFIXML50Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class NewOrderListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setListID("X162773883");
            msg.setBidType(BidType.Disclosed);
            msg.setTotNoOrders(2);
            msg.setNoOrders(1);
            OrderListGroup50TestData.getInstance().populate1(msg.getOrderListGroups()[0]);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            NewOrderListMsg dmsg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getListID(), dmsg.getListID());
            assertEquals(msg.getBidType(), dmsg.getBidType());
            assertEquals(msg.getTotNoOrders(), dmsg.getTotNoOrders());
            assertEquals(msg.getNoOrders(), dmsg.getNoOrders());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class NewOrderListMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            NewOrderListMsg msg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            NewOrderListMsg50TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            NewOrderListMsg dmsg = (NewOrderListMsg) FIXMsgBuilder.build(MsgType.NewOrderList.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            NewOrderListMsg50TestData.getInstance().check(msg, dmsg, true);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}