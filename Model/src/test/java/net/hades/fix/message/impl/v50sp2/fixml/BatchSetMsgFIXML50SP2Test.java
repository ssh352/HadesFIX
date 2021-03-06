/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BatchSetMsgFIXML50SP2Test.java
 *
 * $Id: BatchSetMsgFIXML50SP2Test.java,v 1.1 2011-04-27 23:28:24 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.BatchSetMsg;
import net.hades.fix.message.XMLValidationResult;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50sp2.data.BatchSetMsg50SP2TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for BatchSetMsg50SP2 class FIXML implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 20/10/2008, 20:57:03
 */
public class BatchSetMsgFIXML50SP2Test extends MsgTest {

    public BatchSetMsgFIXML50SP2Test() {
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
     * Test of encode method, of class BatchSetMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallOneBatch() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            BatchSetMsg msg = (BatchSetMsg) FIXMsgBuilder.build(MsgType.BatchSet.getValue(), BeginString.FIX_5_0SP2);
            BatchSetMsg50SP2TestData.getInstance().populate1(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP2);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            BatchSetMsg dmsg = (BatchSetMsg) FIXMsgBuilder.build(MsgType.BatchSet.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            BatchSetMsg50SP2TestData.getInstance().check1(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    /**
     * Test of encode method, of class BatchSetMsg for FIXML.
     * @throws Exception
     */
    @Test
    public void testMarshallUnmarshallTwoBatches() throws Exception {
        System.out.println("-->testMarshallUnmarshallFixml");
        setPrintableValidatingFixml();
        try {
            BatchSetMsg msg = (BatchSetMsg) FIXMsgBuilder.build(MsgType.BatchSet.getValue(), BeginString.FIX_5_0SP2);
            BatchSetMsg50SP2TestData.getInstance().populate2(msg);
            String fixml = msg.toFixml();
            System.out.println("fixml-->" + fixml);
            XMLValidationResult result = validateXMLAgainstXSD(fixml, FIXML_SCHEMA_V50SP2);
            if (result.hasErrors()) {
                System.out.println("\nERRORS:\n");
                System.out.println(result.getFatals());
                System.out.println(result.getErrors());
            }
            System.out.println(result.getWarnings());
            assertFalse(result.hasErrors());

            BatchSetMsg dmsg = (BatchSetMsg) FIXMsgBuilder.build(MsgType.BatchSet.getValue(), BeginString.FIX_5_0SP2);
            dmsg.fromFixml(fixml);
            BatchSetMsg50SP2TestData.getInstance().check2(msg, dmsg);
        } finally {
            unsetPrintableFixml();
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    // UTILITY MESSAGES
    /////////////////////////////////////////

}