/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TrdInstrmtLegGroup50SP2.java
 *
 * $Id: TrdInstrmtLegGroup50SP2.java,v 1.2 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.impl.v50sp2.InstrumentLeg50SP2;
import net.hades.fix.message.comp.impl.v50sp2.LegStipulations50SP2;
import net.hades.fix.message.comp.impl.v50sp2.NestedParties50SP2;
import net.hades.fix.message.comp.impl.v50sp2.UnderlyingLegInstrument50SP2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.LegStipulationsGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.comp.UnderlyingLegInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.TrdInstrmtLegGroup;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX 5.0SP2 implementation of TrdInstrmtLegGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/04/2011, 6:46:57 PM
 */
@XmlRootElement(name = "TrdLeg")
@XmlType(propOrder = {"instrumentLeg", "legStipulationsGroups", "nestedPartyIDGroups", "underlyingLegInstruments"})
@XmlAccessorType(XmlAccessType.NONE)
public class TrdInstrmtLegGroup50SP2 extends TrdInstrmtLegGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50SP2().getFragmentAllTags();
    protected static final Set<Integer> LEG_STIPULATIONS_COMP_TAGS = new LegStipulations50SP2().getFragmentAllTags();
    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties50SP2().getFragmentAllTags();
    protected static final Set<Integer> UNDLY_INSTRMT_LEG_COMP_TAGS = new UnderlyingLegInstrument50SP2().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(UNDLY_INSTRMT_LEG_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDLY_INSTRMT_LEG_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TrdInstrmtLegGroup50SP2() {
        super();
    }

    public TrdInstrmtLegGroup50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public InstrumentLeg getInstrumentLeg() {
        return instrumentLeg;
    }

    @Override
    public void setInstrumentLeg() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrumentLeg = new InstrumentLeg50SP2(context);
    }

    @Override
    public void clearInstrumentLeg() {
        instrumentLeg = null;
    }

    public void setInstrumentLeg(InstrumentLeg instrumentLeg) {
        this.instrumentLeg = instrumentLeg;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public Double getLegQty() {
        return legQty;
    }

    @Override
    public void setLegQty(Double legQty) {
        this.legQty = legQty;
    }

    @XmlAttribute(name = "SwapTyp")
    @Override
    public LegSwapType getLegSwapType() {
        return legSwapType;
    }

    @Override
    public void setLegSwapType(LegSwapType legSwapType) {
        this.legSwapType = legSwapType;
    }

    @XmlAttribute(name = "RptID")
    @Override
    public String getLegReportID() {
        return legReportID;
    }

    @Override
    public void setLegReportID(String legReportID) {
        this.legReportID = legReportID;
    }

    @XmlAttribute(name = "LegNo")
    @Override
    public Integer getLegNumber() {
        return legNumber;
    }

    @Override
    public void setLegNumber(Integer legNumber) {
        this.legNumber = legNumber;
    }

    @Override
    public LegStipulations getLegStipulations() {
        return legStipulations;
    }

    @Override
    public void setLegStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.legStipulations = new LegStipulations50SP2(context);
    }

    @Override
    public void clearLegStipulations() {
        this.legStipulations = null;
    }

    @XmlElementRef
    public LegStipulationsGroup[] getLegStipulationsGroups() {
        return legStipulations == null ? null : legStipulations.getLegStipulationsGroups();
    }

    public void setLegStipulationsGroups(LegStipulationsGroup[] legStipulationsGroups) {
        if (legStipulationsGroups != null) {
            if (legStipulations == null) {
                setLegStipulations();
            }
            ((LegStipulations50SP2) legStipulations).setLegStipulationsGroups(legStipulationsGroups);
        }
    }

    @XmlAttribute(name = "PosEfct")
    @Override
    public PositionEffect getLegPositionEffect() {
        return legPositionEffect;
    }

    @Override
    public void setLegPositionEffect(PositionEffect legPositionEffect) {
        this.legPositionEffect = legPositionEffect;
    }

    @XmlAttribute(name = "Cover")
    @Override
    public CoveredOrUncovered getLegCoveredOrUncovered() {
        return legCoveredOrUncovered;
    }

    @Override
    public void setLegCoveredOrUncovered(CoveredOrUncovered legCoveredOrUncovered) {
        this.legCoveredOrUncovered = legCoveredOrUncovered;
    }

    @Override
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.nestedParties = new NestedParties50SP2(context);
    }

    @Override
    public void clearNestedParties() {
        this.nestedParties = null;
    }

    @XmlElementRef
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedParties == null ? null : nestedParties.getNestedPartyIDGroups();
    }

    public void setNestedPartyIDGroups(NestedPartyGroup[] nestedPartyIDGroups) {
        if (nestedPartyIDGroups != null) {
            if (nestedParties == null) {
                setNestedParties();
            }
            ((NestedParties50SP2) nestedParties).setNestedPartyIDGroups(nestedPartyIDGroups);
        }
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getLegRefID() {
        return legRefID;
    }

    @Override
    public void setLegRefID(String legRefID) {
        this.legRefID = legRefID;
    }

    @XmlAttribute(name = "SettlTyp")
    @Override
    public String getLegSettlType() {
        return legSettlType;
    }

    @Override
    public void setLegSettlType(String legSettlType) {
        this.legSettlType = legSettlType;
    }

    @XmlAttribute(name = "SettlDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getLegSettlDate() {
        return legSettlDate;
    }

    @Override
    public void setLegSettlDate(Date legSettlDate) {
        this.legSettlDate = legSettlDate;
    }

    @XmlAttribute(name = "LastPx")
    @Override
    public Double getLegLastPx() {
        return legLastPx;
    }

    @Override
    public void setLegLastPx(Double legLastPx) {
        this.legLastPx = legLastPx;
    }

    @XmlAttribute(name = "SettlCcy")
    @Override
    public Currency getLegSettlCurrency() {
        return legSettlCurrency;
    }

    @Override
    public void setLegSettlCurrency(Currency legSettlCurrency) {
        this.legSettlCurrency = legSettlCurrency;
    }

    @XmlAttribute(name = "LegLastFwdPnts")
    @Override
    public Double getLegLastForwardPoints() {
        return legLastForwardPoints;
    }

    @Override
    public void setLegLastForwardPoints(Double legLastForwardPoints) {
        this.legLastForwardPoints = legLastForwardPoints;
    }

    @XmlAttribute(name = "LegCalcCcyLastQty")
    @Override
    public Double getLegCalculatedCcyLastQty() {
        return legCalculatedCcyLastQty;
    }

    @Override
    public void setLegCalculatedCcyLastQty(Double legCalculatedCcyLastQty) {
        this.legCalculatedCcyLastQty = legCalculatedCcyLastQty;
    }

    @XmlAttribute(name = "LegGrossTrdAmt")
    @Override
    public Double getLegGrossTradeAmt() {
        return legGrossTradeAmt;
    }

    @Override
    public void setLegGrossTradeAmt(Double legGrossTradeAmt) {
        this.legGrossTradeAmt = legGrossTradeAmt;
    }

    @XmlAttribute(name = "LegVolatility")
    @Override
    public Double getLegVolatility() {
        return legVolatility;
    }

    @Override
    public void setLegVolatility(Double legVolatility) {
        this.legVolatility = legVolatility;
    }

    @XmlAttribute(name = "LegDividendYield")
    @Override
    public Double getLegDividendYield() {
        return legDividendYield;
    }

    @Override
    public void setLegDividendYield(Double legDividendYield) {
        this.legDividendYield = legDividendYield;
    }

    @XmlAttribute(name = "LegCurrencyRatio")
    @Override
    public Double getLegCurrencyRatio() {
        return legCurrencyRatio;
    }

    @Override
    public void setLegCurrencyRatio(Double legCurrencyRatio) {
        this.legCurrencyRatio = legCurrencyRatio;
    }

    @XmlAttribute(name = "LegExecInst")
    @Override
    public String getLegExecInst() {
        return legExecInst;
    }

    @Override
    public void setLegExecInst(String legExecInst) {
        this.legExecInst = legExecInst;
    }

    @XmlAttribute(name = "LastQty")
    @Override
    public Double getLegLastQty() {
        return legLastQty;
    }

    @Override
    public void setLegLastQty(Double legLastQty) {
        this.legLastQty = legLastQty;
    }

    @Override
    public Integer getNoOfLegUnderlyings() {
        return noOfLegUnderlyings;
    }

    @Override
    public void setNoOfLegUnderlyings(Integer noOfLegUnderlyings) {
        this.noOfLegUnderlyings = noOfLegUnderlyings;
        if (noOfLegUnderlyings != null) {
            underlyingLegInstruments = new UnderlyingLegInstrument[noOfLegUnderlyings.intValue()];
            for (int i = 0; i < underlyingLegInstruments.length; i++) {
                underlyingLegInstruments[i] = new UnderlyingLegInstrument50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @XmlElementWrapper(name="TradeCapLegUndlyGrp")
    @Override
    public UnderlyingLegInstrument[] getUnderlyingLegInstruments() {
        return underlyingLegInstruments;
    }

    public void setUnderlyingLegInstruments(UnderlyingLegInstrument[] underlyingLegInstruments) {
        this.underlyingLegInstruments = underlyingLegInstruments;
        if (underlyingLegInstruments != null) {
            noOfLegUnderlyings = new Integer(underlyingLegInstruments.length);
        }
    }

    @Override
    public UnderlyingLegInstrument addUnderlyingLegInstrument() {

        UnderlyingLegInstrument group = new UnderlyingLegInstrument50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingLegInstrument> groups = new ArrayList<UnderlyingLegInstrument>();
        if (underlyingLegInstruments != null && underlyingLegInstruments.length > 0) {
            groups = new ArrayList<UnderlyingLegInstrument>(Arrays.asList(underlyingLegInstruments));
        }
        groups.add(group);
        underlyingLegInstruments = groups.toArray(new UnderlyingLegInstrument[groups.size()]);
        noOfLegUnderlyings = new Integer(underlyingLegInstruments.length);

        return group;
    }

    @Override
    public UnderlyingLegInstrument deleteUnderlyingLegInstrument(int index) {

        UnderlyingLegInstrument result = null;

        if (underlyingLegInstruments != null && underlyingLegInstruments.length > 0 && underlyingLegInstruments.length > index) {
            List<UnderlyingLegInstrument> groups = new ArrayList<UnderlyingLegInstrument>(Arrays.asList(underlyingLegInstruments));
            result = groups.remove(index);
            underlyingLegInstruments = groups.toArray(new UnderlyingLegInstrument[groups.size()]);
            if (underlyingLegInstruments.length > 0) {
                noOfLegUnderlyings = new Integer(underlyingLegInstruments.length);
            } else {
                underlyingLegInstruments = null;
                noOfLegUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingLegInstruments() {

        int result = 0;
        if (underlyingLegInstruments != null && underlyingLegInstruments.length > 0) {
            result = underlyingLegInstruments.length;
            underlyingLegInstruments = null;
            noOfLegUnderlyings = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentLeg == null) {
                instrumentLeg = new InstrumentLeg50SP2(context);
            }
            instrumentLeg.decode(tag, message);
        }
        if (LEG_STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (legStipulations == null) {
                legStipulations = new LegStipulations50SP2(context);
            }
            legStipulations.decode(tag, message);
        }
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties50SP2(context);
            }
            nestedParties.decode(tag, message);
        }
        if (noOfLegUnderlyings != null && noOfLegUnderlyings.intValue() > 0) {
            if (UNDLY_INSTRMT_LEG_COMP_TAGS.contains(tag.tagNum)) {
                message.reset();
                underlyingLegInstruments = new UnderlyingLegInstrument[noOfLegUnderlyings.intValue()];
                for (int i = 0; i < noOfLegUnderlyings.intValue(); i++) {
                    UnderlyingLegInstrument group = new UnderlyingLegInstrument50SP2(context);
                    group.decode(message);
                    underlyingLegInstruments[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [TrdInstrmtLegGroup] group version [5.0SP2].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
