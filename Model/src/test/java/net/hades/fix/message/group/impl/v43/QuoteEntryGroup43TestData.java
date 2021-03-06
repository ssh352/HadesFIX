/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteEntryGroup43TestData.java
 *
 * $Id: QuoteEntryGroup43TestData.java,v 1.2 2011-10-29 09:42:16 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v43.Instrument43TestData;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.TradingSessionID;

/**
 * Test utility for QuoteEntryGroup42 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class QuoteEntryGroup43TestData extends MsgTest {

    private static final QuoteEntryGroup43TestData INSTANCE;

    static {
        INSTANCE = new QuoteEntryGroup43TestData();
    }

    public static QuoteEntryGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries msg) throws Exception {
        msg.setString(quickfix.field.QuoteEntryID.FIELD, "ZZ23433");
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.BidPx.FIELD, 234.55);
        msg.setDouble(quickfix.field.OfferPx.FIELD, 236.55);
        msg.setDouble(quickfix.field.BidSize.FIELD, 100);
        msg.setDouble(quickfix.field.OfferSize.FIELD, 120.0);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setDouble(quickfix.field.BidSpotRate.FIELD, 1.024);
        msg.setDouble(quickfix.field.OfferSpotRate.FIELD, 1.202);
        msg.setDouble(quickfix.field.BidForwardPoints.FIELD, 2.0);
        msg.setDouble(quickfix.field.OfferForwardPoints.FIELD, 3.0);
        msg.setDouble(quickfix.field.MidPx.FIELD, 111.3);
        msg.setDouble(quickfix.field.BidYield.FIELD, 1.4);
        msg.setDouble(quickfix.field.MidYield.FIELD, 1.3);
        msg.setDouble(quickfix.field.OfferYield.FIELD, 2.3);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.AfterHours.getValue());
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_PREVIOUSLY_QUOTED);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.876);
        msg.setDouble(quickfix.field.BidForwardPoints2.FIELD, 2.3);
        msg.setDouble(quickfix.field.OfferForwardPoints2.FIELD, 3.3);
        msg.setString(quickfix.field.Currency.FIELD, Currency.UnitedStatesDollar.getValue());
    }

    public void populate2(quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries msg) throws Exception {
        msg.setString(quickfix.field.QuoteEntryID.FIELD, "AA353553");
        // Instrument
        quickfix.fix43.component.Instrument instr = new quickfix.fix43.component.Instrument();
        Instrument43TestData.getInstance().populate(instr);
        msg.set(instr);

        msg.setDouble(quickfix.field.BidPx.FIELD, 234.77);
        msg.setDouble(quickfix.field.OfferPx.FIELD, 236.77);
        msg.setDouble(quickfix.field.BidSize.FIELD, 44);
        msg.setDouble(quickfix.field.OfferSize.FIELD, 120.77);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setDouble(quickfix.field.BidSpotRate.FIELD, 1.777);
        msg.setDouble(quickfix.field.OfferSpotRate.FIELD, 1.777);
        msg.setDouble(quickfix.field.BidForwardPoints.FIELD, 2.7);
        msg.setDouble(quickfix.field.OfferForwardPoints.FIELD, 3.7);
        msg.setDouble(quickfix.field.MidPx.FIELD, 111.3);
        msg.setDouble(quickfix.field.BidYield.FIELD, 1.4);
        msg.setDouble(quickfix.field.MidYield.FIELD, 1.3);
        msg.setDouble(quickfix.field.OfferYield.FIELD, 2.3);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.TradingSessionID.FIELD, TradingSessionID.Afternoon.getValue());
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_LIMIT);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.777);
        msg.setDouble(quickfix.field.BidForwardPoints2.FIELD, 2.3);
        msg.setDouble(quickfix.field.OfferForwardPoints2.FIELD, 3.3);
        msg.setString(quickfix.field.Currency.FIELD, Currency.AustralianDollar.getValue());
    }

    public void populate1(QuoteEntryGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteEntryID("FFF46466");
        // Instrument
        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setBidPx(new Double(23.44));
        msg.setOfferPx(new Double(23.98));
        msg.setBidSize(new Double(100.0));
        msg.setOfferSize(new Double(150));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.44));
        msg.setOfferSpotRate(new Double(12.54));
        msg.setBidForwardPoints(new Double(2.44));
        msg.setOfferForwardPoints(new Double(3.44));
        msg.setMidPx(new Double(222.32));
        msg.setBidYield(new Double(2.34));
        msg.setMidYield(new Double(1.34));
        msg.setOfferYield(new Double(2.67));
        msg.setTransactTime(new Date());
        msg.setTradingSessionID(TradingSessionID.Afternoon.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Stop);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setBidForwardPoints2(new Double(222.9));
        msg.setOfferForwardPoints2(new Double(222.3));
        msg.setCurrency(Currency.UnitedStatesDollar);
    }

    public void populate2(QuoteEntryGroup msg) throws UnsupportedEncodingException {
        msg.setQuoteEntryID("GGG878w7847");
        // Instrument
        msg.setInstrument();
        Instrument43TestData.getInstance().populate(msg.getInstrument());

        msg.setBidPx(new Double(23.55));
        msg.setOfferPx(new Double(23.55));
        msg.setBidSize(new Double(100.5));
        msg.setOfferSize(new Double(444));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.55));
        msg.setOfferSpotRate(new Double(12.55));
        msg.setBidForwardPoints(new Double(2.55));
        msg.setOfferForwardPoints(new Double(3.55));
        msg.setMidPx(new Double(222.44));
        msg.setBidYield(new Double(2.44));
        msg.setMidYield(new Double(1.44));
        msg.setOfferYield(new Double(2.44));
        msg.setTransactTime(new Date());
        msg.setTradingSessionID(TradingSessionID.AfterHours.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.Market);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.55));
        msg.setBidForwardPoints2(new Double(222.7));
        msg.setOfferForwardPoints2(new Double(222.7));
        msg.setCurrency(Currency.AustralianDollar);
    }

    public void check(QuoteEntryGroup expected, quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries actual) throws Exception {
        assertEquals(expected.getQuoteEntryID(), actual.getString(quickfix.field.QuoteEntryID.FIELD));
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getBidPx().doubleValue(), actual.getDouble(quickfix.field.BidPx.FIELD), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getDouble(quickfix.field.OfferPx.FIELD), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getDouble(quickfix.field.BidSize.FIELD), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getDouble(quickfix.field.OfferSize.FIELD), 0.001);
        assertTimestampEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getDouble(quickfix.field.BidSpotRate.FIELD), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getDouble(quickfix.field.OfferSpotRate.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints.FIELD), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getDouble(quickfix.field.MidPx.FIELD), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getDouble(quickfix.field.BidYield.FIELD), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getDouble(quickfix.field.MidYield.FIELD), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getDouble(quickfix.field.OfferYield.FIELD), 0.001);
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertDateEquals(expected.getSettlDate(), actual.getUtcDateOnly(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertDateEquals(expected.getSettlDate2(), actual.getUtcDateOnly(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints2.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints2.FIELD), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
    }

    public void check(quickfix.fix43.MassQuote.NoQuoteSets.NoQuoteEntries expected, QuoteEntryGroup actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteEntryID.FIELD), actual.getQuoteEntryID());
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getDouble(quickfix.field.BidPx.FIELD), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferPx.FIELD), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidSize.FIELD), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSize.FIELD), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), actual.getValidUntilTime(), false);
        assertEquals(expected.getDouble(quickfix.field.BidSpotRate.FIELD), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSpotRate.FIELD), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints.FIELD), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints.FIELD), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MidPx.FIELD), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidYield.FIELD), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MidYield.FIELD), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferYield.FIELD), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate.FIELD), actual.getSettlDate());
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate2.FIELD), actual.getSettlDate2());
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints2.FIELD), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints2.FIELD), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
    }

    public void check(QuoteEntryGroup expected, QuoteEntryGroup actual) throws Exception {
        assertEquals(expected.getQuoteEntryID(), actual.getQuoteEntryID());
        // Instrument
        Instrument43TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());

        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getOfferSize().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
    }
}
