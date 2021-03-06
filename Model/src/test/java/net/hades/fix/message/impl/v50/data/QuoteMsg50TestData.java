/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteMsg50TestData.java
 *
 * $Id: QuoteMsg50TestData.java,v 1.3 2011-10-29 09:42:06 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.data;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50TestData;
import net.hades.fix.message.comp.impl.v50.Instrument50TestData;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50TestData;
import net.hades.fix.message.comp.impl.v50.Parties50TestData;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50TestData;
import net.hades.fix.message.comp.impl.v50.Stipulations50TestData;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50TestData;
import net.hades.fix.message.comp.impl.v50.YieldData50TestData;
import net.hades.fix.message.group.impl.v50.LegQuoteGroup50TestData;
import net.hades.fix.message.type.CommType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.OrdType;
import net.hades.fix.message.type.AccountType;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.CustOrderCapacity;
import net.hades.fix.message.type.ExDestinationIDSource;
import net.hades.fix.message.type.IOIQualifier;
import net.hades.fix.message.type.OrderCapacity;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.QuoteType;
import net.hades.fix.message.type.SettlCurrFxRateCalc;
import net.hades.fix.message.type.SettlType;
import net.hades.fix.message.type.Side;

/**
 * Test utility for QuoteMsg50 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class QuoteMsg50TestData extends MsgTest {

    private static final QuoteMsg50TestData INSTANCE;

    static {
        INSTANCE = new QuoteMsg50TestData();
    }

    public static QuoteMsg50TestData getInstance() {
        return INSTANCE;
    }

    public void populate(quickfix.fix50.Quote msg) throws Exception {
        TestUtils.populateQuickFIX50HeaderAll(msg);
        msg.setString(quickfix.field.QuoteReqID.FIELD, "X162773883");
        msg.setString(quickfix.field.QuoteID.FIELD, "Q63774663");
        msg.setString(quickfix.field.QuoteRespID.FIELD, "A66654443");
        msg.setInt(quickfix.field.QuoteType.FIELD, quickfix.field.QuoteType.INDICATIVE);
        // QuoteQualifierGroup
        msg.setInt(quickfix.field.NoQuoteQualifiers.FIELD, 2);
        quickfix.fix50.Quote.NoQuoteQualifiers grpqq1 = new quickfix.fix50.Quote.NoQuoteQualifiers();
        grpqq1.setChar(quickfix.field.QuoteQualifier.FIELD, 'B');
        msg.addGroup(grpqq1);
        quickfix.fix50.Quote.NoQuoteQualifiers grpqq2 = new quickfix.fix50.Quote.NoQuoteQualifiers();
        grpqq2.setChar(quickfix.field.QuoteQualifier.FIELD, 'C');
        msg.addGroup(grpqq2);

        msg.setInt(quickfix.field.QuoteResponseLevel.FIELD, quickfix.field.QuoteResponseLevel.ACKNOWLEDGE_EACH_QUOTE_MESSAGES);
        // Parties
        quickfix.fix50.component.Parties parties = new quickfix.fix50.component.Parties();
        Parties50TestData.getInstance().populate(parties);
        msg.set(parties);

        msg.setString(quickfix.field.Account.FIELD, "54543464");
        msg.setInt(quickfix.field.AcctIDSource.FIELD, quickfix.field.AcctIDSource.BIC);
        msg.setInt(quickfix.field.AccountType.FIELD, quickfix.field.AccountType.FLOOR_TRADER);
        msg.setString(quickfix.field.TradingSessionID.FIELD, "X637478466");
        msg.setString(quickfix.field.TradingSessionSubID.FIELD, "FCD636744");
        // Instrument
        quickfix.fix50.component.Instrument instr = new quickfix.fix50.component.Instrument();
        Instrument50TestData.getInstance().populate(instr);
        msg.set(instr);
        // FinancingDetails
        quickfix.fix50.component.FinancingDetails findet = new quickfix.fix50.component.FinancingDetails();
        FinancingDetails50TestData.getInstance().populate(findet);
        msg.set(findet);
        // UnderlyingInstrument
        msg.setInt(quickfix.field.NoUnderlyings.FIELD, 2);
        quickfix.fix50.component.UnderlyingInstrument ui1 = new quickfix.fix50.component.UnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate1(ui1);
        quickfix.fix50.QuoteRequest.NoRelatedSym.NoUnderlyings grpu1 = new quickfix.fix50.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu1.set(ui1);
        grpu1.set(ui1.getUnderlyingStipulations());
        grpu1.set(ui1.getUndlyInstrumentParties());
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID unsec11 =
            new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        ui1.getGroup(1, unsec11);
        grpu1.addGroup(unsec11);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID unsec12 =
            new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        ui1.getGroup(2, unsec12);
        grpu1.addGroup(unsec12);
        msg.addGroup(grpu1);
        quickfix.fix50.component.UnderlyingInstrument ui2 = new quickfix.fix50.component.UnderlyingInstrument();
        UnderlyingInstrument50TestData.getInstance().populate2(ui2);
        quickfix.fix50.QuoteRequest.NoRelatedSym.NoUnderlyings grpu2 = new quickfix.fix50.QuoteRequest.NoRelatedSym.NoUnderlyings();
        grpu2.set(ui2);
        grpu2.set(ui2.getUnderlyingStipulations());
        grpu2.set(ui2.getUndlyInstrumentParties());
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID unsec21 =
            new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        ui2.getGroup(1, unsec21);
        grpu2.addGroup(unsec21);
        quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID unsec22 =
            new quickfix.fix50.component.UnderlyingInstrument.NoUnderlyingSecurityAltID();
        ui2.getGroup(2, unsec22);
        grpu2.addGroup(unsec22);
        msg.addGroup(grpu2);

        msg.setChar(quickfix.field.Side.FIELD, quickfix.field.Side.BUY);
        // OrderQtyData
        quickfix.fix50.component.OrderQtyData orderQtyData = new quickfix.fix50.component.OrderQtyData();
        OrderQtyData50TestData.getInstance().populate(orderQtyData);
        msg.set(orderQtyData);
        // Stipulations
        quickfix.fix50.component.Stipulations stips = new quickfix.fix50.component.Stipulations();
        Stipulations50TestData.getInstance().populate(stips);
        msg.set(stips);
        // LegQuoteSymbolGroup
        msg.setInt(quickfix.field.NoLegs.FIELD, 2);
        quickfix.fix50.Quote.NoLegs grpl1 = new quickfix.fix50.Quote.NoLegs();
        LegQuoteGroup50TestData.getInstance().populate1(grpl1);
        msg.addGroup(grpl1);
        quickfix.fix50.Quote.NoLegs grpl2 = new quickfix.fix50.Quote.NoLegs();
        LegQuoteGroup50TestData.getInstance().populate2(grpl2);
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
        msg.setDouble(quickfix.field.BidSwapPoints.FIELD, 3.5);
        msg.setDouble(quickfix.field.OfferSwapPoints.FIELD, 3.6);
        msg.setDouble(quickfix.field.MidPx.FIELD, 114.0);
        msg.setDouble(quickfix.field.BidYield.FIELD, 1.1);
        msg.setDouble(quickfix.field.MidYield.FIELD, 1.05);
        msg.setDouble(quickfix.field.OfferYield.FIELD, 1.15);
        msg.setUtcTimeStamp(quickfix.field.TransactTime.FIELD, new Date());
        msg.setString(quickfix.field.SettlType.FIELD, quickfix.field.SettlType.CASH);
        msg.setUtcDateOnly(quickfix.field.SettlDate.FIELD, new Date());
        msg.setChar(quickfix.field.OrdType.FIELD, quickfix.field.OrdType.FOREX_PREVIOUSLY_QUOTED);
        msg.setUtcDateOnly(quickfix.field.SettlDate2.FIELD, new Date());
        msg.setDouble(quickfix.field.OrderQty2.FIELD, 2.876);
        msg.setDouble(quickfix.field.BidForwardPoints2.FIELD, 2.222);
        msg.setDouble(quickfix.field.OfferForwardPoints2.FIELD, 2.333);
        msg.setString(quickfix.field.Currency.FIELD, "AUD");
        msg.setDouble(quickfix.field.SettlCurrBidFxRate.FIELD, 1.09);
        msg.setDouble(quickfix.field.SettlCurrOfferFxRate.FIELD, 1.11);
        msg.setChar(quickfix.field.SettlCurrFxRateCalc.FIELD, quickfix.field.SettlCurrFxRateCalc.DIVIDE);
        msg.setChar(quickfix.field.CommType.FIELD, quickfix.field.CommType.PERCENTAGE);
        msg.setDouble(quickfix.field.Commission.FIELD, 6.99);
        msg.setInt(quickfix.field.CustOrderCapacity.FIELD, quickfix.field.CustOrderCapacity.MEMBER_TRADING_FOR_ANOTHER_MEMBER);
        msg.setString(quickfix.field.ExDestination.FIELD, "Some Destination");
        msg.setChar(quickfix.field.ExDestinationIDSource.FIELD, quickfix.field.ExDestinationIDSource.GENERALLY_ACCEPTED_MARKET_PARTICIPANT_IDENTIFIER);
        msg.setChar(quickfix.field.OrderCapacity.FIELD, quickfix.field.OrderCapacity.AGENCY);
        msg.setInt(quickfix.field.PriceType.FIELD, quickfix.field.PriceType.DISCOUNT);
        // SpreadOrBenchmarkCurveData
        quickfix.fix50.component.SpreadOrBenchmarkCurveData spread = new quickfix.fix50.component.SpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(spread);
        msg.set(spread);
        // YieldData
        quickfix.fix50.component.YieldData yield = new quickfix.fix50.component.YieldData();
        YieldData50TestData.getInstance().populate(yield);
        msg.set(yield);

        msg.setString(quickfix.field.Text.FIELD, "I want these shares!");
        byte[] textDataExp = new byte[] {(byte) 19, (byte) 34, (byte) 45, (byte) 97,
            (byte) 178, (byte) 200, (byte) 225, (byte) 254};
        msg.setInt(quickfix.field.EncodedTextLen.FIELD, 8);
        msg.setString(quickfix.field.EncodedText.FIELD, new String(textDataExp, DEFAULT_CHARACTER_SET));
    }

    public void populate(QuoteMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate50HeaderAll(msg);
        msg.getHeader().setApplVerID(ApplVerID.FIX50);
        msg.setQuoteReqID("AAA564567");
        msg.setQuoteID("X162773883");
        msg.setQuoteRespID("W256348845");
        msg.setQuoteType(QuoteType.Indicative);
        // QuoteQualifierGroup
        msg.setNoQuoteQualifiers(new Integer(2));
        msg.getQuoteQualifierGroups()[0].setQuoteQualifier(IOIQualifier.AtTheClose);
        msg.getQuoteQualifierGroups()[1].setQuoteQualifier(IOIQualifier.AtTheMarket);

        msg.setQuoteResponseLevel(QuoteResponseLevel.AckOnlyNegativeOrErroneous);
        // Parties
        msg.setParties();
        Parties50TestData.getInstance().populate(msg.getParties());

        msg.setAccount("54543464");
        msg.setAcctIDSource(AcctIDSource.BIC);
        msg.setAccountType(AccountType.HouseTrader);
        msg.setTradingSessionID("X637478466");
        msg.setTradingSessionSubID("FCD636744");
        // Instrument
        Instrument50TestData.getInstance().populate(msg.getInstrument());
        // FinancingDetails
        msg.setFinancingDetails();
        FinancingDetails50TestData.getInstance().populate(msg.getFinancingDetails());
        // UnderlyingInstrument
        msg.setNoUnderlyings(new Integer(2));
        UnderlyingInstrument50TestData.getInstance().populate1(msg.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().populate2(msg.getUnderlyingInstruments()[1]);

        msg.setSide(Side.Buy);
        // OrderQtyData
        msg.setOrderQtyData();
        OrderQtyData50TestData.getInstance().populate(msg.getOrderQtyData());
        // Stipulations
        msg.setStipulations();
        Stipulations50TestData.getInstance().populate(msg.getStipulations());
        // LegQuoteSymbolGroup
        msg.setNoLegs(new Integer(2));
        LegQuoteGroup50TestData.getInstance().populate1(msg.getLegQuoteSymbolGroups()[0]);
        LegQuoteGroup50TestData.getInstance().populate2(msg.getLegQuoteSymbolGroups()[1]);

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
        msg.setBidSwapPoints(new Double(3.8));
        msg.setOfferSwapPoints(new Double(3.9));
        msg.setMidPx(new Double(114.0));
        msg.setBidYield(new Double(1.1));
        msg.setMidYield(new Double(1.05));
        msg.setOfferYield(new Double(1.15));
        msg.setTransactTime(new Date());
        msg.setSettlType(SettlType.Future.getValue());
        msg.setSettlDate(new Date());
        msg.setOrdType(OrdType.ForexMarket);
        msg.setSettlDate2(new Date());
        msg.setOrderQty2(new Double(31.22));
        msg.setBidForwardPoints2(new Double(2.222));
        msg.setOfferForwardPoints2(new Double(2.333));
        msg.setCurrency(Currency.UnitedStatesDollar);
        msg.setSettlCurrBidFxRate(new Double(1.09));
        msg.setSettlCurrOfferFxRate(new Double(1.11));
        msg.setSettlCurrFxRateCalc(SettlCurrFxRateCalc.Multiply);
        msg.setCommType(CommType.CashDiscount);
        msg.setCommission(new Double(6.99));
        msg.setCustOrderCapacity(CustOrderCapacity.MemberTradingForAnotherMember);
        msg.setExDestination("Some Destination");
        msg.setExDestinationIDSource(ExDestinationIDSource.BankIdentificationCode);
        msg.setOrderCapacity(OrderCapacity.Agency);
        msg.setPriceType(PriceType.PerUnit);
        // SpreadOrBenchmarkCurveData
        msg.setSpreadOrBenchmarkCurveData();
        SpreadOrBenchmarkCurveData50TestData.getInstance().populate(msg.getSpreadOrBenchmarkCurveData());
        // YieldData
        msg.setYieldData();
        YieldData50TestData.getInstance().populate(msg.getYieldData());

        msg.setText("Trade in confidence");
        msg.setEncodedTextLen(new Integer(8));
        byte[] encodedText = new byte[] {(byte) 18, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 224, (byte) 253};
        msg.setEncodedText(encodedText);
    }

    public void check(quickfix.fix50.Quote expected, QuoteMsg actual) throws Exception {
        assertEquals(expected.getString(quickfix.field.QuoteID.FIELD), actual.getQuoteID());
        assertEquals(expected.getString(quickfix.field.QuoteReqID.FIELD), actual.getQuoteReqID());
        assertEquals(expected.getString(quickfix.field.QuoteRespID.FIELD), actual.getQuoteRespID());
        assertEquals(expected.getInt(quickfix.field.QuoteType.FIELD), actual.getQuoteType().getValue());
        // QuoteQualifierGroup
        assertEquals(expected.getInt(quickfix.field.NoQuoteQualifiers.FIELD), actual.getNoQuoteQualifiers().intValue());
        quickfix.fix50.Quote.NoQuoteQualifiers grpqq1 = new quickfix.fix50.Quote.NoQuoteQualifiers();
        expected.getGroup(1, grpqq1);
        assertEquals(grpqq1.getChar(quickfix.field.QuoteQualifier.FIELD), actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        quickfix.fix50.Quote.NoQuoteQualifiers grpqq2 = new quickfix.fix50.Quote.NoQuoteQualifiers();
        expected.getGroup(2, grpqq2);
        assertEquals(grpqq2.getChar(quickfix.field.QuoteQualifier.FIELD), actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());

        assertEquals(expected.getInt(quickfix.field.QuoteResponseLevel.FIELD), actual.getQuoteResponseLevel().getValue());
        // Parties check
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getString(quickfix.field.Account.FIELD), actual.getAccount());
        assertEquals(expected.getInt(quickfix.field.AcctIDSource.FIELD), actual.getAcctIDSource().getValue());
        assertEquals(expected.getInt(quickfix.field.AccountType.FIELD), actual.getAccountType().getValue());
        assertEquals(expected.getString(quickfix.field.TradingSessionID.FIELD), actual.getTradingSessionID());
        assertEquals(expected.getString(quickfix.field.TradingSessionSubID.FIELD), actual.getTradingSessionSubID());
        // Instrument check
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getInt(quickfix.field.NoUnderlyings.FIELD), actual.getNoUnderlyings().intValue());
        quickfix.fix50.Quote.NoUnderlyings grpu1 = new quickfix.fix50.Quote.NoUnderlyings();
        expected.getGroup(1, grpu1);
        quickfix.fix50.component.UnderlyingInstrument uinst1 = new quickfix.fix50.component.UnderlyingInstrument();
        grpu1.get(uinst1);
        uinst1.set(grpu1.getUnderlyingStipulations());
        uinst1.set(grpu1.getUndlyInstrumentParties());
        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec11 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
        grpu1.getGroup(1, usec11);
        uinst1.addGroup(usec11);
        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec12 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
        grpu1.getGroup(2, usec12);
        uinst1.addGroup(usec12);
        UnderlyingInstrument50TestData.getInstance().check(uinst1, actual.getUnderlyingInstruments()[0]);
        quickfix.fix50.Quote.NoUnderlyings grpu2 = new quickfix.fix50.Quote.NoUnderlyings();
        expected.getGroup(2, grpu2);
        quickfix.fix50.component.UnderlyingInstrument uinst2 = new quickfix.fix50.component.UnderlyingInstrument();
        grpu2.get(uinst2);
        uinst2.set(grpu2.getUnderlyingStipulations());
        uinst2.set(grpu2.getUndlyInstrumentParties());
        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec21 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
        grpu2.getGroup(1, usec21);
        uinst2.addGroup(usec21);
        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec22 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
        grpu2.getGroup(2, usec22);
        uinst2.addGroup(usec22);
        UnderlyingInstrument50TestData.getInstance().check(uinst2, actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getChar(quickfix.field.Side.FIELD), actual.getSide().getValue());
        // OrderQtyData check
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegQuoteSymbolGroup
        assertEquals(expected.getInt(quickfix.field.NoLegs.FIELD), actual.getNoLegs().intValue());
        quickfix.fix50.Quote.NoLegs grpl1 = new quickfix.fix50.Quote.NoLegs();
        expected.getGroup(1, grpl1);
        LegQuoteGroup50TestData.getInstance().check(grpl1, actual.getLegQuoteSymbolGroups()[0]);
        quickfix.fix50.Quote.NoLegs grpl2 = new quickfix.fix50.Quote.NoLegs();
        expected.getGroup(2, grpl2);
        LegQuoteGroup50TestData.getInstance().check(grpl2, actual.getLegQuoteSymbolGroups()[1]);

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
        assertEquals(expected.getDouble(quickfix.field.BidSwapPoints.FIELD), actual.getBidSwapPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferSwapPoints.FIELD), actual.getOfferSwapPoints().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MidPx.FIELD), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidYield.FIELD), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.MidYield.FIELD), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferYield.FIELD), actual.getOfferYield().doubleValue(), 0.001);
        assertUTCTimestampEquals(expected.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), actual.getTransactTime(), false);
        assertEquals(expected.getString(quickfix.field.SettlType.FIELD), actual.getSettlType());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate.FIELD), actual.getSettlDate());
        assertEquals(expected.getChar(quickfix.field.OrdType.FIELD), actual.getOrdType().getValue());
        assertDateEquals(expected.getUtcDateOnly(quickfix.field.SettlDate2.FIELD), actual.getSettlDate2());
        assertEquals(expected.getDouble(quickfix.field.OrderQty2.FIELD), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.BidForwardPoints2.FIELD), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.OfferForwardPoints2.FIELD), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getString(quickfix.field.Currency.FIELD), actual.getCurrency().getValue());
        assertEquals(expected.getDouble(quickfix.field.SettlCurrBidFxRate.FIELD), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getDouble(quickfix.field.SettlCurrOfferFxRate.FIELD), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getChar(quickfix.field.SettlCurrFxRateCalc.FIELD), actual.getSettlCurrFxRateCalc().getValue());
        assertEquals(expected.getChar(quickfix.field.CommType.FIELD), actual.getCommType().getValue());
        assertEquals(expected.getDouble(quickfix.field.Commission.FIELD), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getInt(quickfix.field.CustOrderCapacity.FIELD), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getString(quickfix.field.ExDestination.FIELD), actual.getExDestination());
        assertEquals(expected.getChar(quickfix.field.ExDestinationIDSource.FIELD), actual.getExDestinationIDSource().getValue());
        assertEquals(expected.getChar(quickfix.field.OrderCapacity.FIELD), actual.getOrderCapacity().getValue());
        assertEquals(expected.getInt(quickfix.field.PriceType.FIELD), actual.getPriceType().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());
        
        assertEquals(expected.getString(quickfix.field.Text.FIELD), actual.getText());
        assertEquals(expected.getInt(quickfix.field.EncodedTextLen.FIELD), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET), actual.getEncodedText());
    }

    public void check(QuoteMsg expected, quickfix.fix50.Quote actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getString(quickfix.field.QuoteReqID.FIELD));
        assertEquals(expected.getQuoteID(), actual.getString(quickfix.field.QuoteID.FIELD));
        assertEquals(expected.getQuoteRespID(), actual.getString(quickfix.field.QuoteRespID.FIELD));
        assertEquals(expected.getQuoteType().getValue(), actual.getInt(quickfix.field.QuoteType.FIELD));
        // QuoteQualifierGroup
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getInt(quickfix.field.NoQuoteQualifiers.FIELD));
        quickfix.fix50.Quote.NoQuoteQualifiers grpqq1 = new quickfix.fix50.Quote.NoQuoteQualifiers();
        actual.getGroup(1, grpqq1);
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(), grpqq1.getChar(quickfix.field.QuoteQualifier.FIELD));
        quickfix.fix50.Quote.NoQuoteQualifiers grpqq2 = new quickfix.fix50.Quote.NoQuoteQualifiers();
        actual.getGroup(2, grpqq2);
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(), grpqq2.getChar(quickfix.field.QuoteQualifier.FIELD));

        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getInt(quickfix.field.QuoteResponseLevel.FIELD));
        // Parties check
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getString(quickfix.field.Account.FIELD));
        assertEquals(expected.getAcctIDSource().getValue(), actual.getInt(quickfix.field.AcctIDSource.FIELD));
        assertEquals(expected.getAccountType().getValue(), actual.getInt(quickfix.field.AccountType.FIELD));
        assertEquals(expected.getTradingSessionID(), actual.getString(quickfix.field.TradingSessionID.FIELD));
        assertEquals(expected.getTradingSessionSubID(), actual.getString(quickfix.field.TradingSessionSubID.FIELD));
        // Instrument check
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getInt(quickfix.field.NoUnderlyings.FIELD));
        quickfix.fix50.Quote.NoUnderlyings grpu1 = new quickfix.fix50.Quote.NoUnderlyings();
        actual.getGroup(1, grpu1);
        quickfix.fix50.component.UnderlyingInstrument uinst1 = new quickfix.fix50.component.UnderlyingInstrument();
        grpu1.get(uinst1);
        uinst1.set(grpu1.getUnderlyingStipulations());
        uinst1.set(grpu1.getUndlyInstrumentParties());
        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec11 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
        grpu1.getGroup(1, usec11);
        uinst1.addGroup(usec11);
//        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec12 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
//        grpu1.getGroup(2, usec12);
//        uinst1.addGroup(usec12);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], uinst1);
        quickfix.fix50.Quote.NoUnderlyings grpu2 = new quickfix.fix50.Quote.NoUnderlyings();
        actual.getGroup(2, grpu2);
        quickfix.fix50.component.UnderlyingInstrument uinst2 = new quickfix.fix50.component.UnderlyingInstrument();
        grpu2.get(uinst2);
        uinst2.set(grpu2.getUndlyInstrumentParties());
        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec21 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
        grpu2.getGroup(1, usec21);
        uinst2.addGroup(usec21);
//        quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID usec22 = new quickfix.fix50.Quote.NoUnderlyings.NoUnderlyingSecurityAltID();
//        grpu2.getGroup(2, usec22);
//        uinst2.addGroup(usec22);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], uinst2);

        assertEquals(expected.getSide().getValue(), actual.getChar(quickfix.field.Side.FIELD));
        // OrderQtyData check
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegQuoteSymbolGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getInt(quickfix.field.NoLegs.FIELD));
        quickfix.fix50.Quote.NoLegs grpl1 = new quickfix.fix50.Quote.NoLegs();
        actual.getGroup(1, grpl1);
        LegQuoteGroup50TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[0], grpl1);
        quickfix.fix50.Quote.NoLegs grpl2 = new quickfix.fix50.Quote.NoLegs();
        actual.getGroup(2, grpl2);
        LegQuoteGroup50TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[1], grpl2);

        assertEquals(expected.getBidPx().doubleValue(), actual.getDouble(quickfix.field.BidPx.FIELD), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getDouble(quickfix.field.OfferPx.FIELD), 0.001);
        assertEquals(expected.getMktBidPx().doubleValue(), actual.getDouble(quickfix.field.MktBidPx.FIELD), 0.001);
        assertEquals(expected.getMktOfferPx().doubleValue(), actual.getDouble(quickfix.field.MktOfferPx.FIELD), 0.001);
        assertEquals(expected.getMinBidSize().doubleValue(), actual.getDouble(quickfix.field.MinBidSize.FIELD), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getDouble(quickfix.field.BidSize.FIELD), 0.001);
        assertEquals(expected.getMinOfferSize().doubleValue(), actual.getDouble(quickfix.field.MinOfferSize.FIELD), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getDouble(quickfix.field.OfferSize.FIELD), 0.001);
        assertTimestampEquals(expected.getValidUntilTime(), actual.getUtcTimeStamp(quickfix.field.ValidUntilTime.FIELD), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getDouble(quickfix.field.BidSpotRate.FIELD), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getDouble(quickfix.field.OfferSpotRate.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints.FIELD), 0.001);
        assertEquals(expected.getBidSwapPoints().doubleValue(), actual.getDouble(quickfix.field.BidSwapPoints.FIELD), 0.001);
        assertEquals(expected.getOfferSwapPoints().doubleValue(), actual.getDouble(quickfix.field.OfferSwapPoints.FIELD), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getDouble(quickfix.field.MidPx.FIELD), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getDouble(quickfix.field.BidYield.FIELD), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getDouble(quickfix.field.MidYield.FIELD), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getDouble(quickfix.field.OfferYield.FIELD), 0.001);
        assertTimestampEquals(expected.getTransactTime(), actual.getUtcTimeStamp(quickfix.field.TransactTime.FIELD), false);
        assertEquals(expected.getSettlType(), actual.getString(quickfix.field.SettlType.FIELD));
        assertDateEquals(expected.getSettlDate(), actual.getUtcDateOnly(quickfix.field.SettlDate.FIELD));
        assertEquals(expected.getOrdType().getValue(), actual.getChar(quickfix.field.OrdType.FIELD));
        assertDateEquals(expected.getSettlDate2(), actual.getUtcDateOnly(quickfix.field.SettlDate2.FIELD));
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getDouble(quickfix.field.OrderQty2.FIELD), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getDouble(quickfix.field.BidForwardPoints2.FIELD), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getDouble(quickfix.field.OfferForwardPoints2.FIELD), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getString(quickfix.field.Currency.FIELD));
        assertEquals(expected.getSettlCurrBidFxRate().doubleValue(), actual.getDouble(quickfix.field.SettlCurrBidFxRate.FIELD), 0.001);
        assertEquals(expected.getSettlCurrOfferFxRate().doubleValue(), actual.getDouble(quickfix.field.SettlCurrOfferFxRate.FIELD), 0.001);
        assertEquals(expected.getSettlCurrFxRateCalc().getValue(), actual.getChar(quickfix.field.SettlCurrFxRateCalc.FIELD), 0.001);
        assertEquals(expected.getCommType().getValue(), actual.getChar(quickfix.field.CommType.FIELD));
        assertEquals(expected.getCommission().doubleValue(), actual.getDouble(quickfix.field.Commission.FIELD), 0.001);
        assertEquals(expected.getCustOrderCapacity().getValue(), actual.getInt(quickfix.field.CustOrderCapacity.FIELD));
        assertEquals(expected.getExDestination(), actual.getString(quickfix.field.ExDestination.FIELD));
        assertEquals(expected.getExDestinationIDSource().getValue(), actual.getChar(quickfix.field.ExDestinationIDSource.FIELD));
        assertEquals(expected.getOrderCapacity().getValue(), actual.getChar(quickfix.field.OrderCapacity.FIELD));
        assertEquals(expected.getPriceType().getValue(), actual.getInt(quickfix.field.PriceType.FIELD));
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getText(), actual.getString(quickfix.field.Text.FIELD));
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getInt(quickfix.field.EncodedTextLen.FIELD));
        assertArrayEquals(expected.getEncodedText(), actual.getString(quickfix.field.EncodedText.FIELD).getBytes(DEFAULT_CHARACTER_SET));
    }

    public void check(QuoteMsg expected, QuoteMsg actual) throws Exception {
        assertEquals(expected.getQuoteReqID(), actual.getQuoteReqID());
        assertEquals(expected.getQuoteID(), actual.getQuoteID());
        assertEquals(expected.getQuoteRespID(), actual.getQuoteRespID());
        assertEquals(expected.getQuoteType().getValue(), actual.getQuoteType().getValue());
        // QuoteQualifierGroup
        assertEquals(expected.getNoQuoteQualifiers().intValue(), actual.getNoQuoteQualifiers().intValue());
        assertEquals(expected.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[0].getQuoteQualifier().getValue());
        assertEquals(expected.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue(),
            actual.getQuoteQualifierGroups()[1].getQuoteQualifier().getValue());

        assertEquals(expected.getQuoteResponseLevel().getValue(), actual.getQuoteResponseLevel().getValue());
        // Parties check
        Parties50TestData.getInstance().check(expected.getParties(), actual.getParties());

        assertEquals(expected.getAccount(), actual.getAccount());
        assertEquals(expected.getAcctIDSource().getValue(), actual.getAcctIDSource().getValue());
        assertEquals(expected.getAccountType().getValue(), actual.getAccountType().getValue());
        assertEquals(expected.getTradingSessionID(), actual.getTradingSessionID());
        assertEquals(expected.getTradingSessionSubID(), actual.getTradingSessionSubID());
        // Instrument check
        Instrument50TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        // FinancingDetails check
        FinancingDetails50TestData.getInstance().check(expected.getFinancingDetails(), actual.getFinancingDetails());
        // UnderlyingInstrument
        assertEquals(expected.getNoUnderlyings().intValue(), actual.getNoUnderlyings().intValue());
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[0], actual.getUnderlyingInstruments()[0]);
        UnderlyingInstrument50TestData.getInstance().check(expected.getUnderlyingInstruments()[1], actual.getUnderlyingInstruments()[1]);

        assertEquals(expected.getSide().getValue(), actual.getSide().getValue());
        // OrderQtyData check
        OrderQtyData50TestData.getInstance().check(expected.getOrderQtyData(), actual.getOrderQtyData());
        // Stipulations check
        Stipulations50TestData.getInstance().check(expected.getStipulations(), actual.getStipulations());
        // LegQuoteSymbolGroup
        assertEquals(expected.getNoLegs().intValue(), actual.getNoLegs().intValue());
        LegQuoteGroup50TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[0], actual.getLegQuoteSymbolGroups()[0]);
        LegQuoteGroup50TestData.getInstance().check(expected.getLegQuoteSymbolGroups()[1], actual.getLegQuoteSymbolGroups()[1]);
        
        assertEquals(expected.getBidPx().doubleValue(), actual.getBidPx().doubleValue(), 0.001);
        assertEquals(expected.getOfferPx().doubleValue(), actual.getOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMktBidPx().doubleValue(), actual.getMktBidPx().doubleValue(), 0.001);
        assertEquals(expected.getMktOfferPx().doubleValue(), actual.getMktOfferPx().doubleValue(), 0.001);
        assertEquals(expected.getMinBidSize().doubleValue(), actual.getMinBidSize().doubleValue(), 0.001);
        assertEquals(expected.getBidSize().doubleValue(), actual.getBidSize().doubleValue(), 0.001);
        assertEquals(expected.getMinOfferSize().doubleValue(), actual.getMinOfferSize().doubleValue(), 0.001);
        assertEquals(expected.getOfferSize().doubleValue(), actual.getOfferSize().doubleValue(), 0.001);
//        assertUTCTimestampEquals(expected.getValidUntilTime(), actual.getValidUntilTime(), false);
        assertEquals(expected.getBidSpotRate().doubleValue(), actual.getBidSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getOfferSpotRate().doubleValue(), actual.getOfferSpotRate().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints().doubleValue(), actual.getBidForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints().doubleValue(), actual.getOfferForwardPoints().doubleValue(), 0.001);
        assertEquals(expected.getBidSwapPoints().doubleValue(), actual.getBidSwapPoints().doubleValue(), 0.001);
        assertEquals(expected.getOfferSwapPoints().doubleValue(), actual.getOfferSwapPoints().doubleValue(), 0.001);
        assertEquals(expected.getMidPx().doubleValue(), actual.getMidPx().doubleValue(), 0.001);
        assertEquals(expected.getBidYield().doubleValue(), actual.getBidYield().doubleValue(), 0.001);
        assertEquals(expected.getMidYield().doubleValue(), actual.getMidYield().doubleValue(), 0.001);
        assertEquals(expected.getOfferYield().doubleValue(), actual.getOfferYield().doubleValue(), 0.001);
//        assertUTCTimestampEquals(expected.getTransactTime(), actual.getTransactTime(), false);
        assertEquals(expected.getSettlType(), actual.getSettlType());
        assertDateEquals(expected.getSettlDate(), actual.getSettlDate());
        assertEquals(expected.getOrdType().getValue(), actual.getOrdType().getValue());
        assertDateEquals(expected.getSettlDate2(), actual.getSettlDate2());
        assertEquals(expected.getOrderQty2().doubleValue(), actual.getOrderQty2().doubleValue(), 0.001);
        assertEquals(expected.getBidForwardPoints2().doubleValue(), actual.getBidForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getOfferForwardPoints2().doubleValue(), actual.getOfferForwardPoints2().doubleValue(), 0.001);
        assertEquals(expected.getCurrency().getValue(), actual.getCurrency().getValue());
        assertEquals(expected.getSettlCurrBidFxRate().doubleValue(), actual.getSettlCurrBidFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrOfferFxRate().doubleValue(), actual.getSettlCurrOfferFxRate().doubleValue(), 0.001);
        assertEquals(expected.getSettlCurrFxRateCalc(), actual.getSettlCurrFxRateCalc());
        assertEquals(expected.getCommType().getValue(), actual.getCommType().getValue());
        assertEquals(expected.getCommission().doubleValue(), actual.getCommission().doubleValue(), 0.001);
        assertEquals(expected.getCustOrderCapacity().getValue(), actual.getCustOrderCapacity().getValue());
        assertEquals(expected.getExDestination(), actual.getExDestination());
        assertEquals(expected.getExDestinationIDSource().getValue(), actual.getExDestinationIDSource().getValue());
        assertEquals(expected.getOrderCapacity().getValue(), actual.getOrderCapacity().getValue());
        assertEquals(expected.getPriceType().getValue(), actual.getPriceType().getValue());
        // SpreadOrBenchmarkCurveData check
        SpreadOrBenchmarkCurveData50TestData.getInstance().check(expected.getSpreadOrBenchmarkCurveData(), actual.getSpreadOrBenchmarkCurveData());
        // YieldData check
        YieldData50TestData.getInstance().check(expected.getYieldData(), actual.getYieldData());

        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen().intValue(), actual.getEncodedTextLen().intValue());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
