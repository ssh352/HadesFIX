/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityStatusRequestMsg44TestData.java
 *
 * $Id: SecurityStatusRequestMsg44TestData.java,v 1.2 2011-10-29 09:42:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.SecurityStatusRequestMsg;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.type.TradingSessionID;
import net.hades.fix.message.type.TradingSessionSubID;

/**
 * Test utility for SecurityStatusRequestMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class SecurityStatusRequestMsg44TestData extends MsgTest {

    private static final SecurityStatusRequestMsg44TestData INSTANCE;

    static {
        INSTANCE = new SecurityStatusRequestMsg44TestData();
    }

    public static SecurityStatusRequestMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(SecurityStatusRequestMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setSecurityStatusReqID("REQ_11111");

        msg.setInstrument();
        Instrument44TestData.getInstance().populate(msg.getInstrument());

        msg.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(msg.getInstrumentExtension());

        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setNoLegs(2);
        InstrumentLeg44TestData.getInstance().populate1(msg.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().populate2(msg.getInstrumentLegs()[1]);

        msg.setCurrency(Currency.AustralianDollar);
        msg.setSubscriptionRequestType(SubscriptionRequestType.Subscribe);
        msg.setTradingSessionID(TradingSessionID.Day.getValue());
        msg.setTradingSessionSubID(TradingSessionSubID.Intraday.getValue());
    }

    public void check(SecurityStatusRequestMsg expected, SecurityStatusRequestMsg actual) throws Exception {
        assertEquals(expected.getSecurityStatusReqID(), actual.getSecurityStatusReqID());

        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        assertEquals(expected.getNoUnderlyings(), actual.getNoUnderlyings());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[0], actual.getInstrumentLegs()[0]);
        InstrumentLeg44TestData.getInstance().check(expected.getInstrumentLegs()[1], actual.getInstrumentLegs()[1]);

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getSubscriptionRequestType(), actual.getSubscriptionRequestType());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
    }
}
