/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionAckMsgFIXML44Test.java
 *
 * $Id: AllocationInstructionAckMsgFIXML44Test.java,v 1.1 2011-02-17 09:21:28 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.AllocationInstructionAckMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.AllocationInstructionAckMsg44TestData;
import net.hades.fix.message.type.AllocStatus;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for AllocationInstructionAckMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class AllocationInstructionAckMsgFIXML44Test extends MsgTest {

    public AllocationInstructionAckMsgFIXML44Test() {
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
     * Test of encode method, of class AllocationInstructionAckMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            AllocationInstructionAckMsg msg = (AllocationInstructionAckMsg) FIXMsgBuilder.build(MsgType.AllocationAck.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setAllocID("AL33444");
            msg.setTradeDate(new Date());
            msg.setTransactTime(new Date());
            msg.setAllocStatus(AllocStatus.Accepted);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V44);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AllocationInstructionAckMsg dmsg = (AllocationInstructionAckMsg) FIXMsgBuilder.build(MsgType.AllocationAck.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getAllocID(), dmsg.getAllocID());
            assertDateEquals(msg.getTradeDate(), dmsg.getTradeDate());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
            assertEquals(msg.getAllocStatus(), dmsg.getAllocStatus());
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class AllocationInstructionAckMsg for FIXML 4.4.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            AllocationInstructionAckMsg msg = (AllocationInstructionAckMsg) FIXMsgBuilder.build(MsgType.AllocationAck.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            AllocationInstructionAckMsg44TestData.getInstance().populate(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V44);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            AllocationInstructionAckMsg dmsg = (AllocationInstructionAckMsg) FIXMsgBuilder.build(MsgType.AllocationAck.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            AllocationInstructionAckMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}