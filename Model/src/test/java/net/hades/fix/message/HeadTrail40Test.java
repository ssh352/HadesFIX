/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeadTrail40Test.java
 *
 * $Id: HeadTrail40Test.java,v 1.6 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import quickfix.FieldNotFound;
import quickfix.field.DeliverToCompID;
import quickfix.field.DeliverToSubID;
import quickfix.field.MsgSeqNum;
import quickfix.field.OnBehalfOfCompID;
import quickfix.field.OnBehalfOfSubID;
import quickfix.field.PossDupFlag;
import quickfix.field.PossResend;
import quickfix.field.SenderCompID;
import quickfix.field.SenderSubID;
import quickfix.field.SendingTime;
import quickfix.field.Signature;
import quickfix.field.SignatureLength;
import quickfix.field.TargetCompID;
import quickfix.field.TargetSubID;
import quickfix.field.TestReqID;

import net.hades.fix.TestUtils;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.BeginString;

/**
 * Test suite for TestRequestMsg class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 26/07/2008, 15:14:31
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadTrail40Test extends MsgTest {

    public HeadTrail40Test() {
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
     * Test of encode method, of a message class Header/Tailer for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a1_testEncodeReqOnly() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
        TestUtils.populateHeaderReq(msg);
        msg.setTestReqID("Test header/trailer 4.0");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.Message qfMsg = new quickfix.Message(encoded);
        checkHeaderReq(qfMsg.getHeader());
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a4_testEncodeAll() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.setTestReqID("Test header/trailer 4.0");
        String encoded = new String(msg.encode(), DEFAULT_CHARACTER_SET);
        System.out.println("encoded-->" + encoded);
        quickfix.Message qfMsg = new quickfix.Message(encoded);
        checkHeaderAll(qfMsg.getHeader());
        checkTrailer(qfMsg.getTrailer());
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.0 with secure data.
     */
    @Test
    public void a6_testEncodeDecodeSecure() {
        try {
            setSecuredDataDES();
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
            TestUtils.populate40HeaderAll(msg);
            msg.setTestReqID("Test header/trailer 4.0");
            byte[] encodedMsg = msg.encode();
            String encoded = new String(encodedMsg, DEFAULT_CHARACTER_SET);
            System.out.println("encoded secured-->" + encoded);
            TestRequestMsg decoded = (TestRequestMsg) FIXMsgBuilder.build(encodedMsg);
//            quickfix.fix40.TestRequest qfMsg = (quickfix.fix40.TestRequest) new quickfix.Message(encoded);
        } catch (Exception ex) {
            fail(ex.toString());
        } finally {
            unsetSecuredData();
        }
    }
    
    /**
     * Test of encode method, of a message class Header/Tailer for FIX 4.0 with custom fields.
     * @throws Exception 
     */
    @Test
    public void a8_testEncodeCustomFields() throws Exception {
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
        TestUtils.populate40HeaderAll(msg);
        msg.addCustomTag("8888", "Test custom tag");
        msg.setTestReqID("Test header/trailer 4.0");
        byte[] encodedMsg = msg.encode();
        String encoded = new String(encodedMsg, DEFAULT_CHARACTER_SET);
        System.out.println("encoded custom-->" + encoded);
        quickfix.Message qfMsg = new quickfix.Message(encoded);
        String customTag = qfMsg.getString(8888);
        assertEquals("Test custom tag", customTag);
    }
    
    /**
     * Test of decode method, of a message class Header/Tailer for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a10_testDecodeReqOnly() throws Exception {
        String strMsg = buildTestRequestReq().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        checkQuickFIXHeaderReq(msg);
    }
    
    /**
     * Test of decode method, of a message class Header/Tailer for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a12_testDecodeAll() throws Exception {
        String strMsg = buildTestRequestAll().toString();
        System.out.println("msg-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        checkQuickFIXHeaderAll(msg);
    }
    
    /**
     * Test of decode method, of a message class Header/Tailer for FIX 4.0.
     * @throws Exception 
     */
    @Test
    public void a14_testDecodeCustom() throws Exception {
        quickfix.fix40.TestRequest qfMsg =  buildTestRequestAll();
        qfMsg.setString(9999, "Custom tag value");
        String strMsg = qfMsg.toString();
        System.out.println("msg custom-->" + strMsg);
        TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(strMsg.getBytes(DEFAULT_CHARACTER_SET));
        msg.decode();
        checkQuickFIXHeaderAll(msg);
        assertEquals("Custom tag value", msg.getCustomTag("9999"));
    }

    // NEGATIVE TEST CASES
    /////////////////////////////////////////
    
    /**
     * Test of encode method, of class Header 4.0 with missing required header data.
     */
    @Test
    public void x1_testEncodeMissingSomeReq() {
        try {
            setValidationRequired();
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
            msg.setTestReqID("Test header/trailer 4.0");
            populateHeaderReqMissing(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [TargetCompID] [MsgSeqNum] is missing.", ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class Header 4.0 with missing required header data.
     */
    @Test
    public void x2_testEncodeMissingManyReq() {
        try {
            setValidationRequired();
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
            msg.setTestReqID("Test header/trailer 4.0");
            populateHeaderAllReqMissing(msg);
            msg.encode();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("Tag value(s) for [SenderCompID] [TargetCompID] [MsgSeqNum] [SendingTime] is missing.", 
                    ex.getMessage());
        }
    }
    
    /**
     * Test of encode method, of class Header 4.0 with unsupported header tag.
     */
    @Test
    public void x4_testGetUnsupportedHeaderTag() {
        try {
            TestRequestMsg msg = (TestRequestMsg) FIXMsgBuilder.build(MsgType.TestRequest.getValue(), BeginString.FIX_4_0);
            msg.getHeader().getSenderLocationID();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            assertEquals("This tag is not supported in release [" + BeginString.FIX_4_0.getValue() + "].",
                    ex.getMessage());
        }
    }
    
    // UTILITY METHODS

    private quickfix.fix40.TestRequest buildTestRequestReq() {
        quickfix.fix40.TestRequest msg = new quickfix.fix40.TestRequest();
        TestUtils.populateQuicFIX40HeaderReq(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.0"));
        
        return msg;
    }
    
    private quickfix.fix40.TestRequest buildTestRequestAll() {
        quickfix.fix40.TestRequest msg = new quickfix.fix40.TestRequest();
        TestUtils.populateQuickFIX40HeaderAll(msg);
        msg.setString(TestReqID.FIELD, ("Test field 4.0"));
        
        return msg;
    }
    
    private static void populateHeaderReqMissing(FIXMsg msg) {
        msg.getHeader().setSenderCompID("Marvisan");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 23);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 45);
        cal.set(Calendar.SECOND, 15);
        cal.set(Calendar.MILLISECOND, 0);
        msg.getHeader().setSendingTime(cal.getTime());
    }
    
    private static void populateHeaderAllReqMissing(FIXMsg msg) {
    }
    
    private void checkQuickFIXHeaderReq(TestRequestMsg msg) {
        assertEquals(BeginString.FIX_4_0, msg.getHeader().getBeginString());
        assertEquals(MsgType.TestRequest.getValue(), msg.getHeader().getMsgType());
        assertEquals(67, msg.getHeader().getBodyLength());
        assertEquals("Teleton", msg.getHeader().getSenderCompID());
        assertEquals("BI", msg.getHeader().getTargetCompID());
        assertEquals(2, msg.getHeader().getMsgSeqNum().intValue());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getSendingTime());
        assertEquals(cal.getTime(), cal1.getTime());
    }
    
    private void checkQuickFIXHeaderAll(TestRequestMsg msg) {
        assertEquals(BeginString.FIX_4_0, msg.getHeader().getBeginString());
        assertEquals(MsgType.TestRequest.getValue(), msg.getHeader().getMsgType());
        assertEquals("Teleton", msg.getHeader().getSenderCompID());
        assertEquals("BI", msg.getHeader().getTargetCompID());
        assertEquals(2, msg.getHeader().getMsgSeqNum().intValue());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 20);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 40);
        cal.set(Calendar.SECOND, 13);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getSendingTime());
        assertEquals(cal.getTime(), cal1.getTime());
        assertEquals("Company on behalf name", msg.getHeader().getOnBehalfOfCompID());
        assertEquals("Deliver to this company", msg.getHeader().getDeliverToCompID());
        assertEquals("Sender sub identifier", msg.getHeader().getSenderSubID());
        assertEquals("Target sub identifier", msg.getHeader().getTargetSubID());
        assertEquals("On behalf of sub identifier", msg.getHeader().getOnBehalfOfSubID());
        assertEquals("Deliver to this company", msg.getHeader().getDeliverToSubID());
        assertTrue(msg.getHeader().getPossDupFlag().booleanValue());
        assertFalse(msg.getHeader().getPossResend().booleanValue());
        cal.add(Calendar.HOUR_OF_DAY, -2);
        cal1 = Calendar.getInstance();
        cal1.setTime(msg.getHeader().getOrigSendingTime());
        assertEquals(cal.getTime(), cal1.getTime());
    }
    
    private void checkHeaderReq(quickfix.Message.Header header) {
        try {
            assertEquals("Marvisan", header.getString(SenderCompID.FIELD));
            assertEquals("IB", header.getString(TargetCompID.FIELD));
            assertEquals(1, header.getInt(MsgSeqNum.FIELD));
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal.set(Calendar.DAY_OF_MONTH, 23);
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.YEAR, 2008);
            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 45);
            cal.set(Calendar.SECOND, 15);
            cal.set(Calendar.MILLISECOND, 0);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal1.setTimeInMillis(header.getUtcTimeStamp(SendingTime.FIELD).getTime());
            assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
        } catch (FieldNotFound ex) {
            fail(ex.toString());
        }
    }
    
    private void checkTrailer(quickfix.Message.Trailer trailer) {
        try {
            assertEquals(8, trailer.getInt(SignatureLength.FIELD));
            String sig = trailer.getString(Signature.FIELD);
            byte[] expected = new byte[] {(byte) 10, (byte) 12, (byte) 14, (byte) 16, 
                (byte) 234, (byte) 236, (byte) 238, (byte) 240};
            assertEquals(new String(expected, DEFAULT_CHARACTER_SET), sig);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
    
    private void checkHeaderAll(quickfix.Message.Header header) {
        try {
            assertEquals("Marvisan", header.getString(SenderCompID.FIELD));
            assertEquals("IB", header.getString(TargetCompID.FIELD));
            assertEquals(1, header.getInt(MsgSeqNum.FIELD));
            assertEquals("Company on behalf name", header.getString(OnBehalfOfCompID.FIELD));
            assertEquals("Deliver to this company", header.getString(DeliverToCompID.FIELD));
            assertEquals("Sender sub identifier", header.getString(SenderSubID.FIELD));
            assertEquals("Target sub identifier", header.getString(TargetSubID.FIELD));
            assertEquals("On behalf of sub identifier", header.getString(OnBehalfOfSubID.FIELD));
            assertEquals("Deliver to this company", header.getString(DeliverToSubID.FIELD));
            assertTrue(header.getBoolean(PossDupFlag.FIELD));
            assertFalse(header.getBoolean(PossResend.FIELD));
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal.set(Calendar.DAY_OF_MONTH, 23);
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.YEAR, 2008);
            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 45);
            cal.set(Calendar.SECOND, 15);
            cal.set(Calendar.MILLISECOND, 0);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal1.setTimeInMillis(header.getUtcTimeStamp(SendingTime.FIELD).getTime());
            assertUTCTimestampEquals(cal.getTime(), cal1.getTime(), false);
        } catch (FieldNotFound ex) {
            fail(ex.toString());
        }
    }
}