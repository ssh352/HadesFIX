/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidReqDescriptorGroup44.java
 *
 * $Id: BidReqDescriptorGroup44.java,v 1.1 2011-04-14 11:44:44 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.BidDescriptorType;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.BidReqDescriptorGroup;
import net.hades.fix.message.type.SideValueInd;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of PreTradeAllocGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="DescReq")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class BidReqDescriptorGroup44 extends BidReqDescriptorGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;
 
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidReqDescriptorGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public BidReqDescriptorGroup44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "BidDescptrTyp")
    @Override
    public BidDescriptorType getBidDescriptorType() {
        return bidDescriptorType;
    }

    @Override
    public void setBidDescriptorType(BidDescriptorType bidDescriptorType) {
        this.bidDescriptorType = bidDescriptorType;
    }

    @XmlAttribute(name = "BidDescptr")
    @Override
    public String getBidDescriptor() {
        return bidDescriptor;
    }

    @Override
    public void setBidDescriptor(String bidDescriptor) {
        this.bidDescriptor = bidDescriptor;
    }

    @XmlAttribute(name = "SideValuInd")
    @Override
    public SideValueInd getSideValueInd() {
        return sideValueInd;
    }

    @Override
    public void setSideValueInd(SideValueInd sideValueInd) {
        this.sideValueInd = sideValueInd;
    }

    @XmlAttribute(name = "LqdtyValu")
    @Override
    public Double getLiquidityValue() {
        return liquidityValue;
    }

    @Override
    public void setLiquidityValue(Double liquidityValue) {
        this.liquidityValue = liquidityValue;
    }

    @XmlAttribute(name = "LqdtyNumSecurities")
    @Override
    public Integer getLiquidityNumSecurities() {
        return liquidityNumSecurities;
    }

    @Override
    public void setLiquidityNumSecurities(Integer liquidityNumSecurities) {
        this.liquidityNumSecurities = liquidityNumSecurities;
    }

    @XmlAttribute(name = "LqdtyPctLow")
    @Override
    public Double getLiquidityPctLow() {
        return liquidityPctLow;
    }

    @Override
    public void setLiquidityPctLow(Double liquidityPctLow) {
        this.liquidityPctLow = liquidityPctLow;
    }

    @XmlAttribute(name = "LqdtyPctHigh")
    @Override
    public Double getLiquidityPctHigh() {
        return liquidityPctHigh;
    }

    @Override
    public void setLiquidityPctHigh(Double liquidityPctHigh) {
        this.liquidityPctHigh = liquidityPctHigh;
    }

    @XmlAttribute(name = "EFPTrkngErr")
    @Override
    public Double getEFPTrackingError() {
        return EFPTrackingError;
    }

    @Override
    public void setEFPTrackingError(Double EFPTrackingError) {
        this.EFPTrackingError = EFPTrackingError;
    }

    @XmlAttribute(name = "FairValu")
    @Override
    public Double getFairValue() {
        return fairValue;
    }

    @Override
    public void setFairValue(Double fairValue) {
        this.fairValue = fairValue;
    }

    @XmlAttribute(name = "OutsideNdxPct")
    @Override
    public Double getOutsideIndexPct() {
        return outsideIndexPct;
    }

    @Override
    public void setOutsideIndexPct(Double outsideIndexPct) {
        this.outsideIndexPct = outsideIndexPct;
    }

    @XmlAttribute(name = "ValuOfFuts")
    @Override
    public Double getValueOfFutures() {
        return valueOfFutures;
    }

    @Override
    public void setValueOfFutures(Double valueOfFutures) {
        this.valueOfFutures = valueOfFutures;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (bidDescriptorType != null && MsgUtil.isTagInList(TagNum.BidDescriptorType, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.BidDescriptorType, bidDescriptorType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.BidDescriptor, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.BidDescriptor, bidDescriptor);
            }
            if (sideValueInd != null && MsgUtil.isTagInList(TagNum.SideValueInd, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.SideValueInd, sideValueInd.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.LiquidityValue, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.LiquidityValue, liquidityValue);
            }
            if (MsgUtil.isTagInList(TagNum.LiquidityNumSecurities, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.LiquidityNumSecurities, liquidityNumSecurities);
            }
            if (MsgUtil.isTagInList(TagNum.LiquidityPctLow, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.LiquidityPctLow, liquidityPctLow);
            }
            if (MsgUtil.isTagInList(TagNum.LiquidityPctHigh, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.LiquidityPctHigh, liquidityPctHigh);
            }
            if (MsgUtil.isTagInList(TagNum.EFPTrackingError, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.EFPTrackingError, EFPTrackingError);
            }
            if (MsgUtil.isTagInList(TagNum.FairValue, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.FairValue, fairValue);
            }
            if (MsgUtil.isTagInList(TagNum.OutsideIndexPct, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.OutsideIndexPct, outsideIndexPct);
            }
            if (MsgUtil.isTagInList(TagNum.ValueOfFutures, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.ValueOfFutures, valueOfFutures);
            }

            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BidDescriptorGroup] group version [4.4].";
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
