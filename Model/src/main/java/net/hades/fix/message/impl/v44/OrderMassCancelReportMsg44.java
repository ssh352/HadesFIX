/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg44.java
 *
 * $Id: OrderMassCancelReportMsg44.java,v 1.3 2011-05-07 07:05:31 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.OrderMassCancelReportMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v44.Instrument44;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.AffectedOrdGroup;
import net.hades.fix.message.group.impl.v44.AffectedOrdGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MassCancelRejectReason;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.MassCancelResponse;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 OrderMassCancelReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/05/2011, 9:32:41 AM
 */
@XmlRootElement(name="OrdMassCxlRpt")
@XmlType(propOrder = {"header", "affectedOrdGroups", "instrument", "underlyingInstrument"})
@XmlAccessorType(XmlAccessType.NONE)
public class OrderMassCancelReportMsg44 extends OrderMassCancelReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> AFFECTED_ORD_GROUP_TAGS = new AffectedOrdGroup44().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument44().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(AFFECTED_ORD_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(AFFECTED_ORD_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderMassCancelReportMsg44() {
        super();
    }

    public OrderMassCancelReportMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderMassCancelReportMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public OrderMassCancelReportMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        OrderMassCancelReportMsg44 fixml = (OrderMassCancelReportMsg44) fragment;
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getSecondaryClOrdID() != null) {
            secondaryClOrdID = fixml.getSecondaryClOrdID();
        }
        if (fixml.getOrderID() != null) {
            orderID = fixml.getOrderID();
        }
        if (fixml.getSecondaryOrderID() != null) {
            secondaryOrderID = fixml.getSecondaryOrderID();
        }
        if (fixml.getMassCancelRequestType() != null) {
            massCancelRequestType = fixml.getMassCancelRequestType();
        }
        if (fixml.getMassCancelResponse() != null) {
            massCancelResponse = fixml.getMassCancelResponse();
        }
        if (fixml.getMassCancelRejectReason() != null) {
            massCancelRejectReason = fixml.getMassCancelRejectReason();
        }
        if (fixml.getTotalAffectedOrders() != null) {
            totalAffectedOrders = fixml.getTotalAffectedOrders();
        }
        if (fixml.getAffectedOrdGroups() != null && fixml.getAffectedOrdGroups().length > 0) {
            setAffectedOrdGroups(fixml.getAffectedOrdGroups());
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getUnderlyingInstrument() != null) {
            setUnderlyingInstrument(fixml.getUnderlyingInstrument());
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
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

    @XmlAttribute(name = "ID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "ID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @XmlAttribute(name = "ReqTyp")
    @Override
    public MassCancelRequestType getMassCancelRequestType() {
        return massCancelRequestType;
    }

    @Override
    public void setMassCancelRequestType(MassCancelRequestType massCancelRequestType) {
        this.massCancelRequestType = massCancelRequestType;
    }

    @XmlAttribute(name = "Rsp")
    @Override
    public MassCancelResponse getMassCancelResponse() {
        return massCancelResponse;
    }

    @Override
    public void setMassCancelResponse(MassCancelResponse massCancelResponse) {
        this.massCancelResponse = massCancelResponse;
    }

    @XmlAttribute(name = "RejRsn")
    @Override
    public MassCancelRejectReason getMassCancelRejectReason() {
        return massCancelRejectReason;
    }

    @Override
    public void setMassCancelRejectReason(MassCancelRejectReason massCancelRejectReason) {
        this.massCancelRejectReason = massCancelRejectReason;
    }

    @XmlAttribute(name = "TotAffctdOrds")
    @Override
    public Integer getTotalAffectedOrders() {
        return totalAffectedOrders;
    }

    @Override
    public void setTotalAffectedOrders(Integer totalAffectedOrders) {
        this.totalAffectedOrders = totalAffectedOrders;
    }

    @Override
    public Integer getNoAffectedOrders() {
        return noAffectedOrders;
    }

    @Override
    public void setNoAffectedOrders(Integer noAffectedOrders) {
        this.noAffectedOrders = noAffectedOrders;
        if (noAffectedOrders != null) {
            affectedOrdGroups = new AffectedOrdGroup[noAffectedOrders.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < affectedOrdGroups.length; i++) {
                affectedOrdGroups[i] = new AffectedOrdGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public AffectedOrdGroup[] getAffectedOrdGroups() {
        return affectedOrdGroups;
    }

    public void setAffectedOrdGroups(AffectedOrdGroup[] affectedOrdGroups) {
        this.affectedOrdGroups = affectedOrdGroups;
        if (affectedOrdGroups != null) {
            noAffectedOrders = new Integer(affectedOrdGroups.length);
        }
    }

    @Override
    public AffectedOrdGroup addAffectedOrdGroup() {
        AffectedOrdGroup group = new AffectedOrdGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<AffectedOrdGroup> groups = new ArrayList<AffectedOrdGroup>();
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0) {
            groups = new ArrayList<AffectedOrdGroup>(Arrays.asList(affectedOrdGroups));
        }
        groups.add(group);
        affectedOrdGroups = groups.toArray(new AffectedOrdGroup[groups.size()]);
        noAffectedOrders = new Integer(affectedOrdGroups.length);

        return group;
    }

    @Override
    public AffectedOrdGroup deleteAffectedOrdGroup(int index) {
        AffectedOrdGroup result = null;
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0 && affectedOrdGroups.length > index) {
            List<AffectedOrdGroup> groups = new ArrayList<AffectedOrdGroup>(Arrays.asList(affectedOrdGroups));
            result = groups.remove(index);
            affectedOrdGroups = groups.toArray(new AffectedOrdGroup[groups.size()]);
            if (affectedOrdGroups.length > 0) {
                noAffectedOrders = new Integer(affectedOrdGroups.length);
            } else {
                affectedOrdGroups = null;
                noAffectedOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearAffectedOrdGroups() {
        int result = 0;
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0) {
            result = affectedOrdGroups.length;
            affectedOrdGroups = null;
            noAffectedOrders = null;
        }

        return result;
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.instrument = new Instrument44(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.underlyingInstrument = new UnderlyingInstrument44(context);
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (orderID == null || orderID.trim().isEmpty()) {
            errorMsg.append(" [OrderID]");
            hasMissingTag = true;
        }
        if (massCancelRequestType == null) {
            errorMsg.append(" [MassCancelRequestType]");
            hasMissingTag = true;
        }
        if (massCancelResponse == null) {
            errorMsg.append(" [MassCancelResponse]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryClOrdID, secondaryClOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.OrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OrderID, orderID);
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryOrderID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryOrderID, secondaryOrderID);
            }
            if (massCancelRequestType != null && MsgUtil.isTagInList(TagNum.MassCancelRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MassCancelRequestType, massCancelRequestType.getValue());
            }
            if (massCancelResponse != null && MsgUtil.isTagInList(TagNum.MassCancelResponse, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MassCancelResponse, massCancelResponse.getValue());
            }
            if (massCancelRejectReason != null && MsgUtil.isTagInList(TagNum.MassCancelRejectReason, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MassCancelRejectReason, massCancelRejectReason.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TotalAffectedOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotalAffectedOrders, totalAffectedOrders);
            }
            if (noAffectedOrders != null && MsgUtil.isTagInList(TagNum.NoAffectedOrders, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoAffectedOrders, noAffectedOrders);
                if (affectedOrdGroups != null && affectedOrdGroups.length == noAffectedOrders.intValue()) {
                    for (int i = 0; i < noAffectedOrders.intValue(); i++) {
                        if (affectedOrdGroups[i] != null) {
                            bao.write(affectedOrdGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "AffectedOrdGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAffectedOrders.getValue(), error);
                }
            }
            
            
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (instrument != null) {
                bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (AFFECTED_ORD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAffectedOrders != null && noAffectedOrders.intValue() > 0) {
                message.reset();
                affectedOrdGroups = new AffectedOrdGroup[noAffectedOrders.intValue()];
                for (int i = 0; i < noAffectedOrders.intValue(); i++) {
                    AffectedOrdGroup component = new AffectedOrdGroup44(context);
                    component.decode(message);
                    affectedOrdGroups[i] = component;
                }
            }
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument44(context);
            }
            instrument.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument44(context);
            }
            underlyingInstrument.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderMassCancelReportMsg] message version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
