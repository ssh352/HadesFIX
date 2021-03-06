/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollateralAssignmentMsgFIXML44Test.java
 *
 * $Id: CollateralAssignmentMsgFIXML44Test.java,v 1.1 2011-02-16 11:24:36 vrotaru Exp $
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
import net.hades.fix.message.CollateralAssignmentMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v44.data.CollateralAssignmentMsg44TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.CollAsgnReason;
import net.hades.fix.message.type.CollAsgnTransType;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for CollateralAssignmentMsg44 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 18/12/2011, 20:57:03
 */
public class CollateralAssignmentMsgFIXML44Test extends MsgTest {

    public CollateralAssignmentMsgFIXML44Test() {
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
     * Test of encode method, of class CollateralAssignmentMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixmlReq() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixmlReq");
        setPrintableValidatingFixml();
        try {
            CollateralAssignmentMsg msg = (CollateralAssignmentMsg) FIXMsgBuilder.build(MsgType.CollateralAssignment.getValue(), BeginString.FIX_4_4);
            TestUtils.populate44HeaderAll(msg);
            msg.setCollAsgnID("COLL_ASGN_4444");
            msg.setCollAsgnReason(CollAsgnReason.MarginExcess);
            msg.setCollAsgnTransType(CollAsgnTransType.Release);
            msg.setTransactTime(new Date());
            
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

            CollateralAssignmentMsg dmsg = (CollateralAssignmentMsg) FIXMsgBuilder.build(MsgType.CollateralAssignment.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            assertEquals(msg.getCollAsgnID(), dmsg.getCollAsgnID());
            assertEquals(msg.getCollAsgnReason(), dmsg.getCollAsgnReason());
            assertEquals(msg.getCollAsgnTransType(), dmsg.getCollAsgnTransType());
            assertUTCTimestampEquals(msg.getTransactTime(), dmsg.getTransactTime(), false);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class CollateralAssignmentMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallFixml() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            CollateralAssignmentMsg msg = (CollateralAssignmentMsg) FIXMsgBuilder.build(MsgType.CollateralAssignment.getValue(), BeginString.FIX_4_4);
            CollateralAssignmentMsg44TestData.getInstance().populate(msg);
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

            CollateralAssignmentMsg dmsg = (CollateralAssignmentMsg) FIXMsgBuilder.build(MsgType.CollateralAssignment.getValue(), BeginString.FIX_4_4);
            dmsg.fromFixml(fixml);
            CollateralAssignmentMsg44TestData.getInstance().check(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}