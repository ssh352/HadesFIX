/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrossOrderModificationRequestMsgFIXML50SP1Test.java
 *
 * $Id: CrossOrderModificationRequestMsgFIXML50SP1Test.java,v 1.1 2011-05-17 09:28:25 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.CrossOrderModificationRequestMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.group.impl.v50sp1.SideCrossOrdModGroup50SP1TestData;
import net.hades.fix.message.impl.v50sp1.data.CrossOrderModificationRequestMsg50SP1TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CrossPrioritization;
import net.hades.fix.message.type.CrossType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.OrdType;

/**
 * Test suite for CrossOrderModificationRequestMsg50SP1 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class CrossOrderModificationRequestMsgFIXML50SP1Test extends MsgTest {

    public CrossOrderModificationRequestMsgFIXML50SP1Test() {
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
     * Test of encode method, of class CrossOrderModificationRequestMsg.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            CrossOrderModificationRequestMsg msg = (CrossOrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            msg.setCrossID("X162773883");
            msg.setOrigCrossID("ORIG777883");
            msg.setCrossType(CrossType.CrossAON);
            msg.setCrossPrioritization(CrossPrioritization.BuySidePrioritized);
            msg.setNoSides(1);
            SideCrossOrdModGroup50SP1TestData.getInstance().populate1(msg.getSideCrossOrdModGroups()[0]);
            msg.setInstrument();
            msg.getInstrument().setSymbol("SUN");
            msg.setTransactTime(new Date());
            msg.setOrdType(OrdType.Stop);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP1);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            CrossOrderModificationRequestMsg dmsg = (CrossOrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getCrossID(), dmsg.getCrossID());
            assertEquals(msg.getCrossType(), dmsg.getCrossType());
            assertEquals(msg.getCrossPrioritization(), dmsg.getCrossPrioritization());
            assertEquals(msg.getNoSides(), dmsg.getNoSides());
            assertEquals(msg.getInstrument().getSymbol(), dmsg.getInstrument().getSymbol());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class CrossOrderModificationRequestMsg.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            CrossOrderModificationRequestMsg msg = (CrossOrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            TestUtils.populate50HeaderAll(msg);
            CrossOrderModificationRequestMsg50SP1TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP1);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            CrossOrderModificationRequestMsg dmsg = (CrossOrderModificationRequestMsg) FIXMsgBuilder.build(MsgType.CrossOrderModificationRequest.getValue(), BeginString.FIX_5_0SP1);
            dmsg.fromFixml(fixml);
            CrossOrderModificationRequestMsg50SP1TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}