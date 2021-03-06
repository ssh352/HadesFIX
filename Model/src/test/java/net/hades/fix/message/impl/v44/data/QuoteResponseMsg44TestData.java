/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteResponseMsg44TestData.java
 *
 * $Id: QuoteResponseMsg44TestData.java,v 1.2 2011-10-29 09:42:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteResponseMsg;
import net.hades.fix.message.comp.impl.v44.FinancingDetails44TestData;
import net.hades.fix.message.comp.impl.v44.Instrument44TestData;
import net.hades.fix.message.comp.impl.v44.OrderQtyData44TestData;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44TestData;
import net.hades.fix.message.group.impl.v44.LegQuoteGroup44TestData;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.comp.impl.v44.Parties44TestData;
import net.hades.fix.message.comp.impl.v44.SpreadOrBenchmarkCurveData44TestData;
import net.hades.fix.message.comp.impl.v44.Stipulations44TestData;
import net.hades.fix.message.comp.impl.v44.YieldData44TestData;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuoteRespType;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for QuoteResponseMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteResponseMsg44TestData extends MsgTest {

    private static final QuoteResponseMsg44TestData INSTANCE;

    static {
        INSTANCE = new QuoteResponseMsg44TestData();
    }

    public static QuoteResponseMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix44.QuoteResponse msg) throws Exception {
        TestUtils.populateQuickFIX44HeaderAll(msg);
        msg.setString(quickfix.field.QuoteRespID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "Q63774663");
        msg.setInt(quickfix.field.QuoteRespType.FIELD, quickfix.field.QuoteRespType.COUNTER);
        msg.setString(quickfix.field.ClOrdID.FIELD, "ORD112233");
        msg.setChar(quickfix.field.OrderCapacity.FIELD, quickfix.field.OrderCapacity.AGENCY);
        msg.setString(quickfix.field.IOIID.FIELD, "IOI556677");
        msg.setInt(quickfix.field.QuoteType.FIELD, quickfix.field.QuoteType.INDICATIVE);
        // QuoteQualifierGroup
        msg.setInt(quickfix.field.NoQuoteQualifiers.FIELD, 2);
        quickfix.fix44.QuoteResponse.NoQuoteQualifiers grpqq1 = new quickfix.fix44.QuoteResponse.NoQuoteQualifiers();
        grpqq1.setChar(quickfix.field.QuoteQualifier.FIELD, 'B');
        msg.addGroup(grpqq1);
        quickfix.fix44.QuoteResponse.NoQuoteQualifiers grpqq2 = new quickfix.fix44.QuoteResponse.NoQuoteQualifiers();
        grpqq2.setChar(quickfix.field.QuoteQualifier.FIELD, 'C');
        msg.addGroup(grpqq2);
        // Parties
        quickfix.fix44.component.Parties parties = new quickfix.fix44.component.Parties();
        Parties44TestData.getInstance().populate(parties);
        msg.set(parties);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "FCD636744");

        // Instrument
        quickfix.fix44.component.Instrument instr = new quickfix.fix44.component.Instrument();
        Instrument44TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix44.component.FinancingDetails findet = new quickfix.fix44.component.FinancingDetails();
        FinancingDetails44TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix44.component.UnderlyingInstrument ui1 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate1(ui1);
        quickfix.fix44.QuoteResponse.NoUnderlyings grpu1 = new quickfix.fix44.QuoteResponse.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        msg.addGroup(grpu1);
        quickfix.fix44.component.UnderlyingInstrument ui2 = new quickfix.fix44.component.UnderlyingInstrument();
        UnderlyingInstrument44TestData.getInstance().populate2(ui2);
        quickfix.fix44.QuoteResponse.NoUnderlyings grpu2 = new quickfix.fix44.QuoteResponse.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        msg.addGroup(grpu2);

        msg.setChar(quickfix.field.Side.FIELD, quickfix.field.Side.BUY);
        // OrderQtyData
        quickfix.fix44.component.OrderQtyData orderQtyData = new quickfix.fix44.component.OrderQtyData();
        OrderQtyData44TestData.getInstance().populate(orderQtyData);
        msg.set(orderQtyData);

        msg.setString(quickfix.field.SettlType.FIELD, quickfix.field.SettlType.CASH);
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.876);
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        // Stipulations
        quickfix.fix44.component.Stipulations stips = new quickfix.fix44.component.Stipulations();
        Stipulations44TestData.getInstance().populate(stips);
        msg.set(stips);

        msg.setString(quickfix.field.Account.FIELD, "54543464");
        msg.setInt(quickfix.field.AcctIDSource.FIELD, quickfix.field.AcctIDSource.BIC);
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        // LegQuoteSymbolGroup
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix44.QuoteResponse.NoLegs grpl1 = new quickfix.fix44.QuoteResponse.NoLegs();
        LegQuoteGroup44TestData.getInstance().populate1(grpl1);
        msg.addGroup(grpl1);
        quickfix.fix44.QuoteResponse.NoLegs grpl2 = new quickfix.fix44.QuoteResponse.NoLegs();
        LegQuoteGroup44TestData.getInstance().populate2(grpl2);
        msg.addGroup(grpl2);

        msg.setDouble(quickfix.field.BidPx.FIELD, 234.55);
        msg.setDouble(quickfix.field.OfferPx.FIELD, 236.55);
        msg.setDouble(quickfix.field.MktBidPx.FIELD, 34.55);
        msg.setDouble(quickfix.field.MktOfferPx.FIELD, 37.55);
        msg.setDouble(quickfix.field.MinBidSize.FIELD, 99.0);
        msg.setDouble(quickfix.field.BidSize.FIELD, 100);
        msg.setDouble(quickfix.field.MinOfferSize.FIELD, 30.0);
        msg.setDouble(quickfix.field.OfferSize.FIELD, 120.0);
        msg.setUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD, new Date());
        msg.setDouble(quickfix.field.BidSpotRate.FIELD, 1.024);
        msg.setDouble(quickfix.field.OfferSpotRate.FIELD, 1.202);
        msg.setDouble(quickfix.field.BidForwardPoints.FIELD, 2.0);
        msg.setDouble(quickfix.field.OfferForwardPoints.FIELD, 3.0);
        msg.setDouble(quickfix.field.MidPx.FIELD, 114.0);
        msg.setDouble(quickfix.field.BidYield.FIELD, 1.1);
        msg.setDouble(quickfix.field.MidYield.FIELD, 1.05);
        msg.setDouble(quickfix.field.OfferYield.FIELD, 1.15);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_PREVIOUSLY_QUOTED);
        msg.setDouble(quickfix.field.BidForwardPoints2.FIELD, 2.222);
        msg.setDouble(quickfix.field.OfferForwardPoints2.FIELD, 2.333);
        msg.setDouble(quickfix.field.SettlCurrBidFxRate.FIELD, 1.09);
        msg.setDouble(quickfix.field.SettlCurrOfferFxRate.FIELD, 1.11);
        msg.setChar(quickfix.field.SettlCurrFxRateCalc.FIELD, quickfix.field.SettlCurrFxRateCalc.DIVIDE);
        msg.setChar(quickfix.field.CommType.FIELD, quickfix.field.CommType.PERCENTAGE);
        msg.setDouble(quickfix.field.Commission.FIELD, 6.99);
        msg.setInt(quickfix.field.CustOrderCapacity.FIELD, quickfix.field.CustOrderCapacity.MEMBER_TRADING_FOR_ANOTHER_MEMBER);
        msg.setString(quickfix.field.ExDestination.FIELD, "Some Destination");
        msg.setString(quickfix.field.Text.FIELD, "I want these shares!");
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(textDataExp, DEFAULT_CHARACTER_SET));
        msg.setDouble(quickfix.field.Price.FIELD,23.56);
        msg.setInt(quickfix.field.PriceType.FIELD, quickfix.field.PriceType.DISCOUNT);
        // SpreadOrBenchmarkCurveData
        quickfix.fix44.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix44.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(spread);
        msg.set(spread);
        // YieldData
        quickfix.fix44.component.YieldData yield = new quickfix.fix44.component.YieldData();
        YieldData44TestData.getInstance().populate(yield);
        msg.set(yield);
    }

    public void populate(QuoteResponseMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setQuoteRespID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteRespType(QuoteRespType.Cover);
        msg.setClOrdID("CL_ORD_0887666");
        msg.setOrderCapacity(OrderCapacity.Agency);
        msg.setIoiID("IOI-666775");
        msg.setQuoteType(QuoteType.Indicative);
        // QuoteQualifierGroup
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AtTheClose);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.AtTheMarket);
        // Parties
        msg.setParties();
        Parties44TestData.getInstance().populate(msg.getParties());
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("FCD636744");
        // Instrument
        Instrument44TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails44TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument44TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Buy);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData44TestData.getInstance().populate(msg.getOrderQtyData());
        msg.setSettlType(SettlType.Future.getValue());
        msg.setSettlDate(new Date());
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setCurrency(Currency.UnitedStatesDollar);
        // Stipulations
        msg.setStipulations();
        Stipulations44TestData.getInstance().populate(msg.getStipulations());

        msg.setAccount("54543464");
        msg.setAcctIDSource(AcctIDSource.BIC);
        msg.setAccountType(AccountType.HouseTrader);
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        LegQuoteGroup44TestData.getInstance().populate1(msg.getLegQuoteGroups()[0]);
        LegQuoteGroup44TestData.getInstance().populate2(msg.getLegQuoteGroups()[1]);

        msg.setBidPx(new Double(23.44));
        msg.setOfferPx(new Double(23.98));
        msg.setMktBidPx(new Double(34.55));
        msg.setMktOfferPx(new Double(37.55));
        msg.setMinBidSize(new Double(99.0));
        msg.setBidSize(new Double(100.0));
        msg.setMinOfferSize(new Double(30.0));
        msg.setOfferSize(new Double(150));
        msg.setValidUntilTime(new Date());
        msg.setBidSpotRate(new Double(9.44));
        msg.setOfferSpotRate(new Double(12.54));
        msg.setBidForwardPoints(new Double(2.44));
        msg.setOfferForwardPoints(new Double(3.44));
        msg.setMidPx(new Double(114.0));
        msg.setBidYield(new Double(1.1));
        msg.setMidYield(new Double(1.05));
        msg.setOfferYield(new Double(1.15));
        msg.setTransactTime(new Date());
        msg.setOrdType(OrdType.ForexMarket);
        msg.setBidForwardPoints2(new Double(2.222));
        msg.setOfferForwardPoints2(new Double(2.333));
        msg.setSettlCurrBidFxRate(new Double(1.09));
        msg.setSettlCurrOfferFxRate(new Double(1.11));
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Divide);
        msg.setCommType(CommType.CashDiscount);
        msg.setCommission(new Double(6.99));
        msg.setCustOrderCapacity(CustOrderCapacity.MemberTradingForAnotherMember);
        msg.setExDestination("Some Destination");
        msg.setText("Trade in confidence");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
        msg.setPrice(new Double(23.33));
        msg.setPriceType(PriceType.PerUnit);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData44TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        // YieldData
        msg.setYieldData();
        YieldData44TestData.getInstance().populate(msg.getYieldData());
    }

    public void check(quickfix.fix44.QuoteResponse expected, QuoteResponseMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteRespID.FIELD), actual.getQuoteRespID());
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getInt(quickfix.field.QuoteRespType.FIELD), actual.getQuoteRespType().getValue());
        assertEquals(expected.getString(quickfix.field.ClOrdID.FIELD), actual.getClOrdID());
        assertEquals(expected.getChar(quickfix.field.OrderCapacity.FIELD), actual.getOrderCapacity().getValue());
        assertEquals(expected.getString(quickfix.field.IOIID.FIELD), actual.getIoiID());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        // QuoteQualifierGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteQualifiers.FIELD), actual.getNoQuoteQualifiers().intValue());
        quickfix.fix44.QuoteResponse.NoQuoteQualifiers grpqq1 = new quickfix.fix44.QuoteResponse.NoQuoteQualifiers();
        expected.getGroup(1, grpqq1);
        assertEquals(grpqq1.getChar(quickfix.field.QuoteQualifier.FIELD), actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        quickfix.fix44.QuoteResponse.NoQuoteQualifiers grpqq2 = new quickfix.fix44.QuoteResponse.NoQuoteQualifiers();
        expected.getGroup(2, grpqq2);
        assertEquals(grpqq2.getChar(quickfix.field.QuoteQualifier.FIELD), actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());
        
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getInt(quickfix.field.NoUnderlyings.FIELD), actual.getNoUnderlyings().intValue());
        quickfix.fix44.QuoteResponse.NoUnderlyings grpu1 = new quickfix.fix44.QuoteResponse.NoUnderlyings();
        expected.getGroup(1, grpu1);
        quickfix.fix44.component.UnderlyingInstrument uinst1 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu1.get(uinst1);
        quickfix.fix44.component.UnderlyingStipulations unstip1 = new quickfix.fix44.component.UnderlyingStipulations();
        grpu1.get(unstip1);
        uinst1.set(unstip1);
        UnderlyingInstrument44TestData.getInstance().check(uinst1, actual.getUnderlyingInstruments()[0]);
        quickfix.fix44.QuoteResponse.NoUnderlyings grpu2 = new quickfix.fix44.QuoteResponse.NoUnderlyings();
        expected.getGroup(2, grpu2);
        quickfix.fix44.component.UnderlyingInstrument uinst2 = new quickfix.fix44.component.UnderlyingInstrument();
        grpu2.get(uinst2);
        quickfix.fix44.component.UnderlyingStipulations unstip2 = new quickfix.fix44.component.UnderlyingStipulations();
        grpu2.get(unstip2);
        uinst2.set(unstip2);
        UnderlyingInstrument44TestData.getInstance().check(uinst2, actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        // OrderQtyData check
        OrderQtyData44TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getString(quickfix.field.SettlType.FIELD), actual.getSettlType());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate.FIELD), actual.getSettlDate());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate2.FIELD), actual.getSettlDate2());
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        // Stipulations check
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AcctIDSource.FIELD), actual.getAcctIDSource().getValue());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        // LegQuoteSymbolGroup
        assertEquals(expected.getInt(quickfix.field.NoLegs.FIELD), actual.getNoLegs().intValue());
        quickfix.fix44.QuoteResponse.NoLegs grpl1 = new quickfix.fix44.QuoteResponse.NoLegs();
        expected.getGroup(1, grpl1);
        LegQuoteGroup44TestData.getInstance().check(grpl1, actual.getLegQuoteGroups()[0]);
        quickfix.fix44.QuoteResponse.NoLegs grpl2 = new quickfix.fix44.QuoteResponse.NoLegs();
        expected.getGroup(2, grpl2);
        LegQuoteGroup44TestData.getInstance().check(grpl2, actual.getLegQuoteGroups()[1]);

        assertEquals(expected.getDouble(quickfix.field.BidPx.FIELD), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferPx.FIELD), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MktBidPx.FIELD), actual.getMktBidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MktOfferPx.FIELD), actual.getMktOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MinBidSize.FIELD), actual.getMinBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidSize.FIELD), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MinOfferSize.FIELD), actual.getMinOfferSize().doubleValue(), 0.001);
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
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints2.FIELD), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints2.FIELD), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.SettlCurrBidFxRate.FIELD), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.SettlCurrOfferFxRate.FIELD), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.SettlCurrFxRateCalc.FIELD), actual.getSettlCurrFxRateCalc().getValue());
        assertEquals(expected.getChar(quickfix.field.CommType.FIELD), actual.getCommType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Commission.FIELD), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.CustOrderCapacity.FIELD), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getString(quickfix.field.ExDestination.FIELD), actual.getExDestination());
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
        assertEquals(expected.getDouble(quickfix.field.Price.FIELD), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.PriceType.FIELD), actual.getPriceType().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }

    public void check(QuoteResponseMsg expected, QuoteResponseMsg actual) throws Exception {
        assertEquals(expected.getQuoteRespID(), actual.getQuoteRespID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteRespType().getValue(), actual.getQuoteRespType().getValue());
        assertEquals(expected.getClOrdID(), actual.getClOrdID());
        assertEquals(expected.getOrderCapacity().getValue(), actual.getOrderCapacity().getValue());
        assertEquals(expected.getIoiID(), actual.getIoiID());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        // QuoteQualifierGroup
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getNoQuoteQualifiers().intValue());
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());
        // Parties check
        Parties44TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // Instrument check
        Instrument44TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails44TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument44TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        // OrderQtyData check
        OrderQtyData44TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());

        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        // Stipulations check
        Stipulations44TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        // LegQuoteSymbolGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        LegQuoteGroup44TestData.getInstance().check(expected.getLegQuoteGroups()[0], actual.getLegQuoteGroups()[0]);
        LegQuoteGroup44TestData.getInstance().check(expected.getLegQuoteGroups()[1], actual.getLegQuoteGroups()[1]);
        
        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMktBidPx().doubleValue(), actual.getMktBidPx().doubleValue(), 0.001);
        assertEquals(expected.getMktOfferPx().doubleValue(), actual.getMktOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMinBidSize().doubleValue(), actual.getMinBidSize().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getMinOfferSize().doubleValue(), actual.getMinOfferSize().doubleValue(), 0.001);
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
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrBidFxRate().doubleValue(), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrOfferFxRate().doubleValue(), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getCommType().getValue(), actual.getCommType().getValue());
        assertEquals(expected.getCommission().doubleValue(), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getCustOrderCapacity().getValue(), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
        assertEquals(expected.getPrice().doubleValue(), actual.getPrice().doubleValue(), 0.001);
        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData44TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData44TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
    }
}
