/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataSnapshotMsg50.java
 *
 * $Id: MarketDataSnapshotMsg50.java,v 1.10 2011-04-14 23:44:40 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.InstrumentLeg50;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDFullGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v50.MDFullGroup50;
import net.hades.fix.message.group.impl.v50.RoutingIDGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0 MarketDataSnapshottMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="MktDataFull")
@XmlType(propOrder = {"header", "instrument", "underlyingInstruments", "instrumentLegs", "mdFullGroups", "routingIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketDataSnapshotMsg50 extends MarketDataSnapshotMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MD_FULL_GROUP_TAGS = new MDFullGroup50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_LEG_COMP_TAGS = new InstrumentLeg50().getFragmentAllTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup50().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MD_FULL_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MD_FULL_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_LEG_COMP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataSnapshotMsg50() {
        super();
    }
    
    public MarketDataSnapshotMsg50(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }

    public MarketDataSnapshotMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }
    
    public MarketDataSnapshotMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }
    
    @Override
    public void copyFixmlData(FIXFragment fragment) {
        MarketDataSnapshotMsg50 fixml = (MarketDataSnapshotMsg50) fragment;
        if (fixml.getTotNumReports() != null) {
            totNumReports = fixml.getTotNumReports();
        }
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getMdReportID() != null) {
            mdReportID = fixml.getMdReportID();
        }
        if (fixml.getClearingBusinessDate() != null) {
            clearingBusinessDate = fixml.getClearingBusinessDate();
        }
        if (fixml.getMdBookType() != null) {
            mdBookType = fixml.getMdBookType();
        }
        if (fixml.getMdSubBookType() != null) {
            mdSubBookType = fixml.getMdSubBookType();
        }
        if (fixml.getMarketDepth() != null) {
            marketDepth = fixml.getMarketDepth();
        }
        if (fixml.getMdFeedType() != null) {
            mdFeedType = fixml.getMdFeedType();
        }
        if (fixml.getRefreshIndicator() != null) {
            refreshIndicator = fixml.getRefreshIndicator();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getMdStreamID() != null) {
            mdStreamID = fixml.getMdStreamID();
        }
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getInstrument() != null) {
            instrument = fixml.getInstrument();
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getInstrumentLegs() != null && fixml.getInstrumentLegs().length > 0) {
            setInstrumentLegs(fixml.getInstrumentLegs());
        }
        if (fixml.getFinancialStatus() != null) {
            financialStatus = fixml.getFinancialStatus();
        }
        if (fixml.getCorporateAction() != null) {
            corporateAction = fixml.getCorporateAction();
        }
        if (fixml.getMdFullGroups() != null && fixml.getMdFullGroups().length > 0) {
            setMdFullGroups(fixml.getMdFullGroups());
        }
        if (fixml.getApplQueueDepth() != null) {
            applQueueDepth = fixml.getApplQueueDepth();
        }
        if (fixml.getApplQueueResolution() != null) {
            applQueueResolution = fixml.getApplQueueResolution();
        }
        if (fixml.getRoutingIDGroups() != null && fixml.getRoutingIDGroups().length > 0) {
            setRoutingIDGroups(fixml.getRoutingIDGroups());
        }
    }
    
    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "TotNumRpts")
    @Override
    public Integer getTotNumReports() {
        return totNumReports;
    }

    @Override
    public void setTotNumReports(Integer totNumReports) {
        this.totNumReports = totNumReports;
    }

    @XmlAttribute(name = "ReqID")
    @Override
    public String getMdReqID() {
        return mdReqID;
    }

    @Override
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    @XmlAttribute(name = "RptID")
    @Override
    public Integer getMdReportID() {
        return mdReportID;
    }

    @Override
    public void setMdReportID(Integer mdReportID) {
        this.mdReportID = mdReportID;
    }

    @XmlAttribute(name = "BizDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getClearingBusinessDate() {
        return clearingBusinessDate;
    }

    @Override
    public void setClearingBusinessDate(Date clearingBusinessDate) {
        this.clearingBusinessDate = clearingBusinessDate;
    }

    @XmlAttribute(name = "MDBkTyp")
    @Override
    public MDBookType getMdBookType() {
        return mdBookType;
    }

    @Override
    public void setMdBookType(MDBookType mdBookType) {
        this.mdBookType = mdBookType;
    }

    @XmlAttribute(name = "MDSubBkTyp")
    @Override
    public Integer getMdSubBookType() {
        return mdSubBookType;
    }

    @Override
    public void setMdSubBookType(Integer mdSubBookType) {
        this.mdSubBookType = mdSubBookType;
    }

    @XmlAttribute(name = "MktDepth")
    @Override
    public Integer getMarketDepth() {
        return marketDepth;
    }

    @Override
    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    @XmlAttribute(name = "MDFeedTyp")
    @Override
    public String getMdFeedType() {
        return mdFeedType;
    }

    @Override
    public void setMdFeedType(String mdFeedType) {
        this.mdFeedType = mdFeedType;
    }

    @XmlAttribute(name = "RefInd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getRefreshIndicator() {
        return refreshIndicator;
    }

    @Override
    public void setRefreshIndicator(Boolean refreshIndicator) {
        this.refreshIndicator = refreshIndicator;
    }

    @XmlAttribute(name = "TrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @XmlAttribute(name = "MDStrmID")
    @Override
    public String getMdStreamID() {
        return mdStreamID;
    }

    @Override
    public void setMdStreamID(String mdStreamID) {
        this.mdStreamID = mdStreamID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        return underlyingInstruments;
    }

    public void setUnderlyingInstruments(UnderlyingInstrument[] underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = underlyingInstruments.length;
        }
    }

    @Override
    public UnderlyingInstrument addUnderlyingInstrument() {

        UnderlyingInstrument group = new UnderlyingInstrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>();
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
        }
        groups.add(group);
        underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
        noUnderlyings = new Integer(underlyingInstruments.length);

        return group;
    }

    @Override
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {

        UnderlyingInstrument result = null;

        if (underlyingInstruments != null && underlyingInstruments.length > 0 && underlyingInstruments.length > index) {
            List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
            result = groups.remove(index);
            underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
            if (underlyingInstruments.length > 0) {
                noUnderlyings = new Integer(underlyingInstruments.length);
            } else {
                underlyingInstruments = null;
                noUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingInstruments() {

        int result = 0;
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            result = underlyingInstruments.length;
            underlyingInstruments = null;
            noUnderlyings = null;
        }

        return result;
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            instrumentLegs = new InstrumentLeg[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < instrumentLegs.length; i++) {
                instrumentLegs[i] = new InstrumentLeg50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public InstrumentLeg[] getInstrumentLegs() {
        return instrumentLegs;
    }

    public void setInstrumentLegs(InstrumentLeg[] instrumentLegs) {
        this.instrumentLegs = instrumentLegs;
        if (instrumentLegs != null) {
            noLegs = instrumentLegs.length;
        }
    }

    @Override
    public InstrumentLeg addInstrumentLeg() {
        InstrumentLeg group = new InstrumentLeg50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<InstrumentLeg> groups = new ArrayList<InstrumentLeg>();
        if (instrumentLegs != null && instrumentLegs.length > 0) {
            groups = new ArrayList<InstrumentLeg>(Arrays.asList(instrumentLegs));
        }
        groups.add(group);
        instrumentLegs = groups.toArray(new InstrumentLeg[groups.size()]);
        noLegs = new Integer(instrumentLegs.length);

        return group;
    }

    @Override
    public InstrumentLeg deleteInstrumentLeg(int index) {
        InstrumentLeg result = null;
        if (instrumentLegs != null && instrumentLegs.length > 0 && instrumentLegs.length > index) {
            List<InstrumentLeg> groups = new ArrayList<InstrumentLeg>(Arrays.asList(instrumentLegs));
            result = groups.remove(index);
            instrumentLegs = groups.toArray(new InstrumentLeg[groups.size()]);
            if (instrumentLegs.length > 0) {
                noLegs = new Integer(instrumentLegs.length);
            } else {
                instrumentLegs = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearInstrumentLegs() {
        int result = 0;
        if (instrumentLegs != null && instrumentLegs.length > 0) {
            result = instrumentLegs.length;
            instrumentLegs = null;
            noLegs = null;
        }

        return result;
    }

    @XmlAttribute(name = "FinclStat")
    @Override
    public String getFinancialStatus() {
        return financialStatus;
    }

    @Override
    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus;
    }

    @XmlAttribute(name = "CorpActn")
    @Override
    public String getCorporateAction() {
        return corporateAction;
    }

    @Override
    public void setCorporateAction(String corporateAction) {
        this.corporateAction = corporateAction;
    }

    @XmlAttribute(name = "NetChgPrevDay")
    @Override
    public Double getNetChgPrevDay() {
        return netChgPrevDay;
    }

    @Override
    public void setNetChgPrevDay(Double netChgPrevDay) {
        this.netChgPrevDay = netChgPrevDay;
    }

    @Override
    public Integer getNoMDEntries() {
        return noMDEntries;
    }

    @Override
    public void setNoMDEntries(Integer noMDEntries) {
        this.noMDEntries = noMDEntries;
        if (noMDEntries != null) {
            mdFullGroups = new MDFullGroup[noMDEntries.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < mdFullGroups.length; i++) {
                mdFullGroups[i] = new MDFullGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public MDFullGroup[] getMdFullGroups() {
        return mdFullGroups;
    }

    public void setMdFullGroups(MDFullGroup[] mdFullGroups) {
        this.mdFullGroups = mdFullGroups;
        if (mdFullGroups != null) {
            noMDEntries = mdFullGroups.length;
        }
    }

    @Override
    public MDFullGroup addMdFullGroup() {
        MDFullGroup group = new MDFullGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MDFullGroup> groups = new ArrayList<MDFullGroup>();
        if (mdFullGroups != null && mdFullGroups.length > 0) {
            groups = new ArrayList<MDFullGroup>(Arrays.asList(mdFullGroups));
        }
        groups.add(group);
        mdFullGroups = groups.toArray(new MDFullGroup[groups.size()]);
        noMDEntries = new Integer(mdFullGroups.length);

        return group;
    }

    @Override
    public MDFullGroup deleteMdFullGroup(int index) {
        MDFullGroup result = null;
        if (mdFullGroups != null && mdFullGroups.length > 0 && mdFullGroups.length > index) {
            List<MDFullGroup> groups = new ArrayList<MDFullGroup>(Arrays.asList(mdFullGroups));
            result = groups.remove(index);
            mdFullGroups = groups.toArray(new MDFullGroup[groups.size()]);
            if (mdFullGroups.length > 0) {
                noMDEntries = new Integer(mdFullGroups.length);
            } else {
                mdFullGroups = null;
                noMDEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearMdFullGroups() {
        int result = 0;
        if (mdFullGroups != null && mdFullGroups.length > 0) {
            result = mdFullGroups.length;
            mdFullGroups = null;
            noMDEntries = null;
        }

        return result;
    }

    @XmlAttribute(name = "ApplQuDepth")
    @Override
    public Integer getApplQueueDepth() {
        return applQueueDepth;
    }

    @Override
    public void setApplQueueDepth(Integer applQueueDepth) {
        this.applQueueDepth = applQueueDepth;
    }

    @XmlAttribute(name = "ApplQuResolution")
    @Override
    public ApplQueueResolution getApplQueueResolution() {
        return applQueueResolution;
    }

    @Override
    public void setApplQueueResolution(ApplQueueResolution applQueueResolution) {
        this.applQueueResolution = applQueueResolution;
    }

    @Override
    public Integer getNoRoutingIDs() {
        return noRoutingIDs;
    }

    @Override
    public void setNoRoutingIDs(Integer noRoutingIDs) {
        this.noRoutingIDs = noRoutingIDs;
        if (noRoutingIDs != null) {
            routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < routingIDGroups.length; i++) {
                routingIDGroups[i] = new RoutingIDGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public RoutingIDGroup[] getRoutingIDGroups() {
        return routingIDGroups;
    }

    public void setRoutingIDGroups(RoutingIDGroup[] routingIDGroups) {
        this.routingIDGroups = routingIDGroups;
        if (routingIDGroups != null) {
            noRoutingIDs = routingIDGroups.length;
        }
    }

    @Override
    public RoutingIDGroup addRoutingIDGroup() {
        RoutingIDGroup group = new RoutingIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>();
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
        }
        groups.add(group);
        routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
        noRoutingIDs = new Integer(routingIDGroups.length);

        return group;

    }

    @Override
    public RoutingIDGroup deleteRoutingIDGroup(int index) {
       RoutingIDGroup result = null;
       if (routingIDGroups != null && routingIDGroups.length > 0 && routingIDGroups.length > index) {
            List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
            result = groups.remove(index);
            routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
            if (routingIDGroups.length > 0) {
                noRoutingIDs = new Integer(routingIDGroups.length);
            } else {
                routingIDGroups = null;
                noRoutingIDs = null;
            }
        }

        return result;

    }

    @Override
    public int clearRoutingIDGroups() {
        int result = 0;
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            result = routingIDGroups.length;
            routingIDGroups = null;
            noRoutingIDs = null;
        }

        return result;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50(context);
            }
            instrument.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument component = new UnderlyingInstrument50(context);
                    component.decode(message);
                    underlyingInstruments[i] = component;
                }
            }
        }
        if (INSTRUMENT_LEG_COMP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                instrumentLegs = new InstrumentLeg[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    InstrumentLeg component = new InstrumentLeg50(context);
                    component.decode(message);
                    instrumentLegs[i] = component;
                }
            }
        }
        if (MD_FULL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMDEntries != null && noMDEntries.intValue() > 0) {
                message.reset();
                mdFullGroups = new MDFullGroup[noMDEntries.intValue()];
                for (int i = 0; i < noMDEntries.intValue(); i++) {
                    MDFullGroup component = new MDFullGroup50(context);
                    component.decode(message);
                    mdFullGroups[i] = component;
                }
            }
        }
        if (ROUTING_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRoutingIDs != null && noRoutingIDs.intValue() > 0) {
                message.reset();
                routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
                for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                    RoutingIDGroup component = new RoutingIDGroup50(context);
                    component.decode(message);
                    routingIDGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataSnapshotMsg] message version [5.0].";
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
