/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument50.java
 *
 * $Id: Instrument50.java,v 1.9 2011-04-14 23:44:50 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.InstrumentParties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.EventGroup;
import net.hades.fix.message.group.InstrumentPartyGroup;
import net.hades.fix.message.group.SecurityAltIDGroup;
import net.hades.fix.message.group.impl.v50.EventGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharacterAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCTimeAdapter;

import java.nio.charset.Charset;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.impl.v50.SecurityAltIDGroup50;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.InstrmtAssignmentMethod;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SecurityStatus;
import net.hades.fix.message.type.TimeUnit;
import net.hades.fix.message.type.UnitOfMeasure;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0 Instrument component data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 30/11/2008, 8:43:40 PM
 */
@XmlRootElement(name="Instrmt")
@XmlType(propOrder = {"securityAltIDGroups", "events", "instrumentPartyGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class Instrument50 extends Instrument {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> SEC_ALT_ID_GROUP_TAGS = new SecurityAltIDGroup50().getFragmentAllTags();
    protected static final Set<Integer> EVENT_GROUP_TAGS = new EventGroup50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_PARTIES_COMP_TAGS = new InstrumentParties50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_ALT_ID_GROUP_TAGS);
        ALL_TAGS.addAll(EVENT_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_ALT_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(EVENT_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Instrument50() {
        super();
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
    }
    
    public Instrument50(FragmentContext context) {
        super(context);
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Sym")
    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @XmlAttribute(name = "Sfx")
    @Override
    public String getSymbolSfx() {
        return symbolSfx;
    }

    @Override
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
    }

    @XmlAttribute(name = "CFI")
    @Override
    public String getCfiCode() {
        return cfiCode;
    }

    @Override
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
    }

    @XmlAttribute(name = "IssuCtry")
    @Override
    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    @Override
    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    @XmlAttribute(name = "CpnPmt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getCouponPaymentDate() {
        return couponPaymentDate;
    }

    @Override
    public void setCouponPaymentDate(Date couponPaymentDate) {
        this.couponPaymentDate = couponPaymentDate;
    }

    @XmlAttribute(name = "CrdRtg")
    @Override
    public String getCreditRating() {
        return creditRating;
    }

    @Override
    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    @XmlAttribute(name = "Fctr")
    @Override
    public Double getFactor() {
        return factor;
    }

    @Override
    public void setFactor(Double factor) {
        this.factor = factor;
    }

    @XmlAttribute(name = "Rgstry")
    @Override
    public String getInstrRegistry() {
        return instrRegistry;
    }

    @Override
    public void setInstrRegistry(String instrRegistry) {
        this.instrRegistry = instrRegistry;
    }

    @XmlAttribute(name = "Issued")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @XmlAttribute(name = "Issr")
    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @XmlAttribute(name = "Lcl")
    @Override
    public String getLocaleOfIssue() {
        return localeOfIssue;
    }

    @Override
    public void setLocaleOfIssue(String localeOfIssue) {
        this.localeOfIssue = localeOfIssue;
    }

    @XmlAttribute(name = "MatDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getMaturityDate() {
        return maturityDate;
    }

    @Override
    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    @XmlAttribute(name = "MMY")
    @Override
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    @Override
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
    }

    @XmlAttribute(name = "MatTm")
    @XmlJavaTypeAdapter(FixUTCTimeAdapter.class)
    @Override
    public Date getMaturityTime() {
        return maturityTime;
    }

    @Override
    public void setMaturityTime(Date maturityTime) {
        this.maturityTime = maturityTime;
    }

    @XmlAttribute(name = "SettlOnOpenFlag")
    @Override
    public String getSettleOnOpenFlag() {
        return settleOnOpenFlag;
    }

    @Override
    public void setSettleOnOpenFlag(String settleOnOpenFlag) {
        this.settleOnOpenFlag = settleOnOpenFlag;
    }

    @XmlAttribute(name = "AsgnMeth")
    @Override
    public InstrmtAssignmentMethod getInstrmtAssignmentMethod() {
        return instrmtAssignmentMethod;
    }

    @Override
    public void setInstrmtAssignmentMethod(InstrmtAssignmentMethod instrmtAssignmentMethod) {
        this.instrmtAssignmentMethod = instrmtAssignmentMethod;
    }

    @XmlAttribute(name = "Status")
    @Override
    public SecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    @Override
    public void setSecurityStatus(SecurityStatus securityStatus) {
        this.securityStatus = securityStatus;
    }

    @XmlAttribute(name = "OptAt")
    @XmlJavaTypeAdapter(FixCharacterAdapter.class)
    @Override
    public Character getOptAttribute() {
        return optAttribute;
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
    }

    @XmlAttribute(name = "Prod")
    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @XmlAttribute(name = "Redeem")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getRedemptionDate() {
        return redemptionDate;
    }

    @Override
    public void setRedemptionDate(Date redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    @XmlAttribute(name = "RepoCollSecTyp")
    @Override
    public String getRepoCollateralSecurityType() {
        return repoCollateralSecurityType;
    }

    @Override
    public void setRepoCollateralSecurityType(String repoCollateralSecurityType) {
        this.repoCollateralSecurityType = repoCollateralSecurityType;
    }

    @XmlAttribute(name = "RepoRt")
    @Override
    public Double getRepurchaseRate() {
        return repurchaseRate;
    }

    @Override
    public void setRepurchaseRate(Double repurchaseRate) {
        this.repurchaseRate = repurchaseRate;
    }

    @XmlAttribute(name = "RepoTrm")
    @Override
    public Integer getRepurchaseTerm() {
        return repurchaseTerm;
    }

    @Override
    public void setRepurchaseTerm(Integer repurchaseTerm) {
        this.repurchaseTerm = repurchaseTerm;
    }

    @Override
    public Integer getNoSecurityAltID() {
        return noSecurityAltID;
    }

    @Override
    public void setNoSecurityAltID(Integer noSecurityAltID) {
        this.noSecurityAltID = noSecurityAltID;
        if (noSecurityAltID != null) {
            securityAltIDGroups = new SecurityAltIDGroup[noSecurityAltID.intValue()];
            for (int i = 0; i < securityAltIDGroups.length; i++) {
                securityAltIDGroups[i] = new SecurityAltIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SecurityAltIDGroup[] getSecurityAltIDGroups() {
        return securityAltIDGroups;
    }

    public void setSecurityAltIDGroups(SecurityAltIDGroup[] securityAltIDGroups) {
        this.securityAltIDGroups = securityAltIDGroups;
        if (securityAltIDGroups != null) {
            noSecurityAltID = new Integer(securityAltIDGroups.length);
        }
    }

    @Override
    public SecurityAltIDGroup addSecurityAltIDGroup() {
        
        SecurityAltIDGroup group = new SecurityAltIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SecurityAltIDGroup> groups = new ArrayList<SecurityAltIDGroup>();
        if (securityAltIDGroups != null && securityAltIDGroups.length > 0) {
            groups = new ArrayList<SecurityAltIDGroup>(Arrays.asList(securityAltIDGroups));
        }
        groups.add(group);
        securityAltIDGroups = groups.toArray(new SecurityAltIDGroup[groups.size()]);
        noSecurityAltID = new Integer(securityAltIDGroups.length);
        
        return group;
    }
    
    @Override
    public SecurityAltIDGroup deleteSecurityAltIDGroup(int index) {
        
        SecurityAltIDGroup result = null;

        if (securityAltIDGroups != null && securityAltIDGroups.length > 0 && securityAltIDGroups.length > index) {
            List<SecurityAltIDGroup> groups = new ArrayList<SecurityAltIDGroup>(Arrays.asList(securityAltIDGroups));
            result = groups.remove(index);
            securityAltIDGroups = groups.toArray(new SecurityAltIDGroup[groups.size()]);
            if (securityAltIDGroups.length > 0) {
                noSecurityAltID = new Integer(securityAltIDGroups.length);
            } else {
                securityAltIDGroups = null;
                noSecurityAltID = null;
            }
        }
        
        return result;
    }

    @Override
    public int clearSecurityAltIDGroups() {
        
        int result = 0;
        if (securityAltIDGroups != null && securityAltIDGroups.length > 0) {
            result = securityAltIDGroups.length;
            securityAltIDGroups = null;
            noSecurityAltID = null;
        }
        
        return result;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getSecurityID() {
        return securityID;
    }

    @Override
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public String getSecurityIDSource() {
        return securityIDSource;
    }

    @Override
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
    }

    @XmlAttribute(name = "SecTyp")
    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @XmlAttribute(name = "SubTyp")
    @Override
    public String getSecuritySubType() {
        return securitySubType;
    }

    @Override
    public void setSecuritySubType(String securitySubType) {
        this.securitySubType = securitySubType;
    }

    @XmlAttribute(name = "StPrv")
    @Override
    public String getStateOrProvinceOfIssue() {
        return stateOrProvinceOfIssue;
    }

    @Override
    public void setStateOrProvinceOfIssue(String stateOrProvinceOfIssue) {
        this.stateOrProvinceOfIssue = stateOrProvinceOfIssue;
    }

    @XmlAttribute(name = "StrkPx")
    @Override
    public Double getStrikePrice() {
        return strikePrice;
    }

    @Override
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    @XmlAttribute(name = "StrkCcy")
    @Override
    public Currency getStrikeCurrency() {
        return strikeCurrency;
    }

    @Override
    public void setStrikeCurrency(Currency strikeCurrency) {
        this.strikeCurrency = strikeCurrency;
    }

    @XmlAttribute(name = "StrkMult")
    @Override
    public Double getStrikeMultiplier() {
        return strikeMultiplier;
    }

    @Override
    public void setStrikeMultiplier(Double strikeMultiplier) {
        this.strikeMultiplier = strikeMultiplier;
    }

    @XmlAttribute(name = "StrkValu")
    @Override
    public Double getStrikeValue() {
        return strikeValue;
    }

    @Override
    public void setStrikeValue(Double strikeValue) {
        this.strikeValue = strikeValue;
    }

    @XmlAttribute(name = "Mult")
    @Override
    public Double getContractMultiplier() {
        return contractMultiplier;
    }

    @Override
    public void setContractMultiplier(Double contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    @XmlAttribute(name = "MinPxIncr")
    @Override
    public Double getMinPriceIncrement() {
        return minPriceIncrement;
    }

    @Override
    public void setMinPriceIncrement(Double minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
    }

    @XmlAttribute(name = "UOM")
    @Override
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @XmlAttribute(name = "TmUnit")
    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @XmlAttribute(name = "CpnRt")
    @Override
    public Double getCouponRate() {
        return couponRate;
    }

    @Override
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    @XmlAttribute(name = "Exch")
    @Override
    public String getSecurityExchange() {
        return securityExchange;
    }

    @Override
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
    }

    @XmlAttribute(name = "PosLmt")
    @Override
    public Integer getPositionLimit() {
        return positionLimit;
    }

    @Override
    public void setPositionLimit(Integer positionLimit) {
        this.positionLimit = positionLimit;
    }

    @XmlAttribute(name = "NTPosLmt")
    @Override
    public Integer getNtPositionLimit() {
        return ntPositionLimit;
    }

    @Override
    public void setNtPositionLimit(Integer ntPositionLimit) {
        this.ntPositionLimit = ntPositionLimit;
    }

    @XmlAttribute(name = "EncIssr")
    @Override
    public byte[] getEncodedIssuer() {
        return encodedIssuer;
    }

    @Override
    public void setEncodedIssuer(byte[] encodedIssuer) {
        this.encodedIssuer = encodedIssuer;
        if (encodedIssuerLen == null) {
            encodedIssuerLen = new Integer(encodedIssuer.length);
        }
    }

    @XmlAttribute(name = "EncIssrLen")
    @Override
    public Integer getEncodedIssuerLen() {
        return encodedIssuerLen;
    }

    @Override
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        this.encodedIssuerLen = encodedIssuerLen;
    }

    @XmlAttribute(name = "Desc")
    @Override
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
    }

    @XmlAttribute(name = "EncSecDesc")
    @Override
    public byte[] getEncodedSecurityDesc() {
        return encodedSecurityDesc;
    }

    @Override
    public void setEncodedSecurityDesc(byte[] encodedSecurityDesc) {
        this.encodedSecurityDesc = encodedSecurityDesc;
        if (encodedSecurityDescLen == null) {
            encodedSecurityDescLen = new Integer(encodedSecurityDesc.length);
        }
    }

    @XmlAttribute(name = "EncSecDescLen")
    @Override
    public Integer getEncodedSecurityDescLen() {
        return encodedSecurityDescLen;
    }

    @Override
    public void setEncodedSecurityDescLen(Integer encodedSecurityDescLen) {
        this.encodedSecurityDescLen = encodedSecurityDescLen;
    }

    @XmlAttribute(name = "Pool")
    @Override
    public String getPool() {
        return pool;
    }

    @Override
    public void setPool(String pool) {
        this.pool = pool;
    }

    @XmlAttribute(name = "CSetMo")
    @Override
    public String getContractSettlMonth() {
        return contractSettlMonth;
    }

    @Override
    public void setContractSettlMonth(String contractSettlMonth) {
        this.contractSettlMonth = contractSettlMonth;
    }

    @XmlAttribute(name = "CPPgm")
    @Override
    public Integer getCpProgram() {
        return cpProgram;
    }

    @Override
    public void setCpProgram(Integer cpProgram) {
        this.cpProgram = cpProgram;
    }

    @XmlAttribute(name = "CPRegT")
    @Override
    public String getCpRegType() {
        return cpRegType;
    }

    @Override
    public void setCpRegType(String cpRegType) {
        this.cpRegType = cpRegType;
    }

    @Override
    public Integer getNoEvents() {
        return noEvents;
    }

    @Override
    public void setNoEvents(Integer noEvents) {
        this.noEvents = noEvents;
        if (noEvents != null) {
            events = new EventGroup[noEvents.intValue()];
            for (int i = 0; i < events.length; i++) {
                events[i] = new EventGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public EventGroup[] getEvents() {
        return events;
    }

    public void setEvents(EventGroup[] events) {
        this.events = events;
        if (events != null) {
            noEvents = new Integer(events.length);
        }
    }

    @Override
    public EventGroup addEvent() {

        EventGroup group = new EventGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<EventGroup> groups = new ArrayList<EventGroup>();
        if (events != null && events.length > 0) {
            groups = new ArrayList<EventGroup>(Arrays.asList(events));
        }
        groups.add(group);
        events = groups.toArray(new EventGroup[groups.size()]);
        noEvents = new Integer(events.length);

        return group;
    }

    @Override
    public EventGroup deleteEvent(int index) {

        EventGroup result = null;

        if (events != null && events.length > 0 && events.length > index) {
            List<EventGroup> groups = new ArrayList<EventGroup>(Arrays.asList(events));
            result = groups.remove(index);
            events = groups.toArray(new EventGroup[groups.size()]);
            if (events.length > 0) {
                noEvents = new Integer(events.length);
            } else {
                events = null;
                noEvents = null;
            }
        }

        return result;
    }

    @Override
    public int clearEvents() {

        int result = 0;
        if (events != null && events.length > 0) {
            result = events.length;
            events = null;
            noEvents = null;
        }

        return result;
    }

    @XmlAttribute(name = "Dated")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getDatedDate() {
        return datedDate;
    }

    @Override
    public void setDatedDate(Date datedDate) {
        this.datedDate = datedDate;
    }

    @XmlAttribute(name = "IntAcrl")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getInterestAccrualDate() {
        return interestAccrualDate;
    }

    @Override
    public void setInterestAccrualDate(Date interestAccrualDate) {
        this.interestAccrualDate = interestAccrualDate;
    }

    @Override
    public InstrumentParties getInstrumentParties() {
        return instrumentParties;
    }

    @Override
    public void setInstrumentParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrumentParties = new InstrumentParties50(context);
    }

    @Override
    public void clearInstrumentParties() {
        instrumentParties = null;
    }

    @XmlElementRef
    public InstrumentPartyGroup[] getInstrumentPartyGroups() {
        return instrumentParties == null ? null : instrumentParties.getInstrumentPartyGroups();
    }

    public void setInstrumentPartyGroups(InstrumentPartyGroup[] instrumentPartyGroups) {
        if (instrumentPartyGroups != null) {
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            instrumentParties = new InstrumentParties50(context);
            instrumentParties.setNoInstrumentParties(instrumentPartyGroups.length);
            ((InstrumentParties50) instrumentParties).setInstrumentPartyGroups(instrumentPartyGroups);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {

        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (noSecurityAltID != null && noSecurityAltID.intValue() > 0) {
            if (SEC_ALT_ID_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                securityAltIDGroups = new SecurityAltIDGroup[noSecurityAltID.intValue()];
                for (int i = 0; i < noSecurityAltID.intValue(); i++) {
                    SecurityAltIDGroup group = new SecurityAltIDGroup50(context);
                    group.decode(message);
                    securityAltIDGroups[i] = group;
                }
            }
        }
        if (noEvents != null && noEvents.intValue() > 0) {
            if (EVENT_GROUP_TAGS.contains(tag.tagNum)) {
                message.reset();
                events = new EventGroup[noEvents.intValue()];
                for (int i = 0; i < noEvents.intValue(); i++) {
                    EventGroup group = new EventGroup50(context);
                    group.decode(message);
                    events[i] = group;
                }
            }
        }
        if (INSTRUMENT_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (instrumentParties == null) {
                instrumentParties = new InstrumentParties50(context);
            }
            instrumentParties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Instrument] component version [5.0].";
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
