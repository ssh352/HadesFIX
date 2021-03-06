/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PositionMaintenanceReportMsg50Test.java
 *
 * $Id: PositionMaintenanceReportMsg44Test.java,v 1.1 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.PositionMaintenanceReportMsg;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.impl.v50.data.PositionMaintenanceReportMsg50TestData;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

/**
 * Test suite for FIX 5.0 PositionMaintenanceReportMsg class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/12/2011, 12:18:17 PM
 */
public class PositionMaintenanceReportMsg50Test extends MsgTest {

    public PositionMaintenanceReportMsg50Test() {
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
        PositionMaintenanceReportMsg msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0);
        PositionMaintenanceReportMsg50TestData.getInstance().populate(msg);
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);

        PositionMaintenanceReportMsg dmsg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(encoded.getBytes(DEFAULT_CHARACTER_SET));
        dmsg.decode();
        PositionMaintenanceReportMsg50TestData.getInstance().check(msg, dmsg);
    }

    /**
     * Test of encode getter method, of class PositionMaintenanceReportMsg with unsupported tag.
     */
    @Test
    public void testGetUnsupportedMsgTag() {
        System.out.println("-->testGetUnsupportedMsgTag");
        PositionMaintenanceReportMsg msg = null;
        try {
            msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    /**
     * Test of encode setter method, of class PositionMaintenanceReportMsg with unsupported tag.
     */
    @Test
    public void testSetUnsupportedMsgTag() {
        System.out.println("-->testSetUnsupportedMsgTag");
        PositionMaintenanceReportMsg msg = null;
        try {
            msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0);
        } catch (Exception ex) {
            fail("Error building message");
        }
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////

    /**
     * Test of encode method, of class PositionMaintenanceReportMsg with missing MDIncGroups data.
     */
    @Test
    public void testEncodeMissingRequired() {
        System.out.println("-->testEncodeMissingRequired");
        try {
            PositionMaintenanceReportMsg msg = (PositionMaintenanceReportMsg) FIXMsgBuilder.build(MsgType.PositionMaintenanceReport.getValue(), BeginString.FIX_5_0);
            TestUtils.populate40HeaderAll(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [PosMaintRptID] [PosTransType] [PosMaintAction] [PosMaintStatus] [ClearingBusinessDate] [Symbol] [NoPositions] is missing.", 
                    ex.getMessage());
        }
    }

    // UTILITY MESSAGES
    /////////////////////////////////////////

    private void checkUnsupportedException(Exception ex) {
        assertEquals("This tag is not supported in [PositionMaintenanceReportMsg] message version [5.0].", ex.getMessage());
    }
}
