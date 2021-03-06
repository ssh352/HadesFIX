/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DontKnowTradeMsgFIXML50Test.java
 *
 * $Id: DontKnowTradeMsgFIXML50Test.java,v 1.1 2011-01-16 00:47:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.DontKnowTradeMsg;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.DontKnowTradeMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.DKReason;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.Side;

/**
 * Test suite for DontKnowTradeMsg50 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class DontKnowTradeMsgFIXML50Test extends MsgTest {

    public DontKnowTradeMsgFIXML50Test() {
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
     * Test of encode method, of class DontKnowTradeMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            DontKnowTradeMsg msg = (DontKnowTradeMsg) FIXMsgBuilder.build(MsgType.DontKnowTrade.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            msg.setOrderID("X162773883");
            msg.setDKReason(DKReason.UnknownSymbol);
            msg.setExecID("DDD22242424");
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setSide(Side.Buy);
            msg.setOrderQtyData();
            msg.getOrderQtyData().setOrderQty(22.00d);
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

            DontKnowTradeMsg dmsg = (DontKnowTradeMsg) FIXMsgBuilder.build(MsgType.DontKnowTrade.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getOrderID(), dmsg.getOrderID());
            assertEquals(msg.getDKReason(), dmsg.getDKReason());
            assertEquals(msg.getExecID(), dmsg.getExecID());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
            assertEquals(msg.getSide(), dmsg.getSide());
            assertEquals(msg.getOrderQtyData().getOrderQty(), dmsg.getOrderQtyData().getOrderQty());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class DontKnowTradeMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            DontKnowTradeMsg msg = (DontKnowTradeMsg) FIXMsgBuilder.build(MsgType.DontKnowTrade.getValue(), BeginString.FIX_5_0);
            TestUtils.populate50HeaderAll(msg);
            DontKnowTradeMsg50TestData.getInstance().populate(msg);
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

            DontKnowTradeMsg dmsg = (DontKnowTradeMsg) FIXMsgBuilder.build(MsgType.DontKnowTrade.getValue(), BeginString.FIX_5_0);
            dmsg.fromFixml(fixml);
            DontKnowTradeMsg50TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}