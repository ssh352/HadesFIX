/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderStatusRequestMsg42TestData.java
 *
 * $Id: OrderStatusRequestMsg42TestData.java,v 1.1 2011-01-24 10:50:02 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.OrderStatusRequestMsg;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SecurityType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for OrderStatusRequestMsg42 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class OrderStatusRequestMsg42TestData extends MsgTest {

    private static final OrderStatusRequestMsg42TestData INSTANCE;

    static {
        INSTANCE = new OrderStatusRequestMsg42TestData();
    }

    public static OrderStatusRequestMsg42TestData getInstance() {
        return INSTANCE;
    }

    public void populate(OrderStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate40HeaderAll(msg);
        msg.setOrderID("ORD33334");
        msg.setClOrdID("AAA564567");
        msg.setClientID("client");
        msg.setExecBroker("broker");
        msg.setAccount("12735534784");
        msg.setSymbol("BHP.AX");
        msg.setSymbolSfx("CDDF");
        msg.setSecurityID("BHP");
        msg.setSecurityIDSource("BHP-src");
        msg.setSecurityType(SecurityType.Cash.getValue());
        msg.setMaturityMonthYear("022010");
        msg.setMaturityDay(2);
        msg.setPutOrCall(PutOrCall.Call);
        msg.setStrikePrice(12.55d);
        msg.setOptAttribute('A');
        msg.setContractMultiplier(34.88d);
        msg.setCouponRate(35.66d);
        msg.setSecurityExchange("ASX");
        msg.setIssuer("issuer");
        msg.setEncodedIssuerLen(new Integer(8));
        byte[] encodedIssuer = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedIssuer(encodedIssuer);
        msg.setSecurityDesc("description");
        msg.setEncodedSecurityDescLen(new Integer(8));
        byte[] encodedSecDesc = new byte[] {(byte) 18, (byte) 34, (byte) 45, (byte) 97,
            (byte) 177, (byte) 200, (byte) 224, (byte) 253};
        msg.setEncodedSecurityDesc(encodedSecDesc);
        msg.setSide(Side.Buy);
    }

    public void check(OrderStatusRequestMsg expected, OrderStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getOrderID(), actual.getOrderID());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getClientID(), actual.getClientID());
        assertEquals(expected.getExecBroker(), actual.getExecBroker());
        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getSymbol(), actual.getSymbol());
        assertEquals(expected.getSymbolSfx(), actual.getSymbolSfx());
        assertEquals(expected.getSecurityID(), actual.getSecurityID());
        assertEquals(expected.getSecurityIDSource(), actual.getSecurityIDSource());
        assertEquals(expected.getSecurityType(), actual.getSecurityType());
        assertEquals(expected.getMaturityMonthYear(), actual.getMaturityMonthYear());
        assertEquals(expected.getMaturityDay(), actual.getMaturityDay());
        assertEquals(expected.getPutOrCall(), actual.getPutOrCall());
        assertEquals(expected.getStrikePrice(), actual.getStrikePrice());
        assertEquals(expected.getOptAttribute(), actual.getOptAttribute());
        assertEquals(expected.getContractMultiplier(), actual.getContractMultiplier());
        assertEquals(expected.getCouponRate(), actual.getCouponRate());
        assertEquals(expected.getSecurityExchange(), actual.getSecurityExchange());
        assertEquals(expected.getIssuer(), actual.getIssuer());
        assertEquals(expected.getEncodedIssuerLen().intValue(), actual.getEncodedIssuerLen().intValue());
        assertArrayEquals(expected.getEncodedIssuer(), actual.getEncodedIssuer());
        assertEquals(expected.getSecurityDesc(), actual.getSecurityDesc());
        assertEquals(expected.getEncodedSecurityDescLen().intValue(), actual.getEncodedSecurityDescLen().intValue());
        assertArrayEquals(expected.getEncodedSecurityDesc(), actual.getEncodedSecurityDesc());
        assertEquals(expected.getSide(), actual.getSide());
    }
}
