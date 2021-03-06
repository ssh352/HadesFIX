/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteCancelMsg42.java
 *
 * $Id: QuoteCancelMsg42.java,v 1.9 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.QuoteCancelMsg;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.QuoteCancelGroup;
import net.hades.fix.message.group.impl.v42.QuoteCancelGroup42;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.QuoteResponseLevel;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.2 QuoteCancelMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class QuoteCancelMsg42 extends QuoteCancelMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -6553444643509341313L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> QUOTE_CANCEL_GROUP_TAGS = new QuoteCancelGroup42().getFragmentTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(QUOTE_CANCEL_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(QUOTE_CANCEL_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public QuoteCancelMsg42() {
        super();
    }
    
    public QuoteCancelMsg42(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteCancelMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public QuoteCancelMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    @Override
    public Header getHeader() {
        return header;
    }
    
    @Override
    public void setHeader(Header header) {
        this.header = header;
    }
    
    @Override
    public String getQuoteReqID() {
        return quoteReqID;
    }

    @Override
    public void setQuoteReqID(String quoteReqID) {
        this.quoteReqID = quoteReqID;
    }

    @Override
    public String getQuoteID() {
        return quoteID;
    }

    @Override
    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    @Override
    public Integer getQuoteCancelType() {
        return quoteCancelType;
    }

    @Override
    public void setQuoteCancelType(Integer quoteCancelType) {
        this.quoteCancelType = quoteCancelType;
    }

    @Override
    public QuoteResponseLevel getQuoteResponseLevel() {
        return quoteResponseLevel;
    }

    @Override
    public void setQuoteResponseLevel(QuoteResponseLevel quoteResponseLevel) {
        this.quoteResponseLevel = quoteResponseLevel;
    }

    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @Override
    public Integer getNoQuoteEntries() {
        return noQuoteEntries;
    }

    @Override
    public void setNoQuoteEntries(Integer noQuoteEntries) {
        this.noQuoteEntries = noQuoteEntries;
        if (noQuoteEntries != null) {
            quoteCancelEntries = new QuoteCancelGroup[noQuoteEntries.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < quoteCancelEntries.length; i++) {
                quoteCancelEntries[i] = new QuoteCancelGroup42(context);
            }
        }
    }

    @Override
    public QuoteCancelGroup[] getQuoteCancelEntries() {
        return quoteCancelEntries;
    }

    public void setQuoteCancelEntries(QuoteCancelGroup[] quoteCancelEntries) {
        this.quoteCancelEntries = quoteCancelEntries;
        if (quoteCancelEntries != null) {
            noQuoteEntries = new Integer(quoteCancelEntries.length);
        }
    }

    @Override
    public QuoteCancelGroup addQuoteCancelEntry() {
        QuoteCancelGroup group = new QuoteCancelGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<QuoteCancelGroup> groups = new ArrayList<QuoteCancelGroup>();
        if (quoteCancelEntries != null && quoteCancelEntries.length > 0) {
            groups = new ArrayList<QuoteCancelGroup>(Arrays.asList(quoteCancelEntries));
        }
        groups.add(group);
        quoteCancelEntries = groups.toArray(new QuoteCancelGroup[groups.size()]);
        noQuoteEntries = new Integer(quoteCancelEntries.length);

        return group;
    }

    @Override
    public QuoteCancelGroup deleteQuoteCancelEntry(int index) {
        QuoteCancelGroup result = null;
        if (quoteCancelEntries != null && quoteCancelEntries.length > 0 && quoteCancelEntries.length > index) {
            List<QuoteCancelGroup> groups = new ArrayList<QuoteCancelGroup>(Arrays.asList(quoteCancelEntries));
            result = groups.remove(index);
            quoteCancelEntries = groups.toArray(new QuoteCancelGroup[groups.size()]);
            if (quoteCancelEntries.length > 0) {
                noQuoteEntries = new Integer(quoteCancelEntries.length);
            } else {
                quoteCancelEntries = null;
                noQuoteEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearQuoteCancelEntries() {
        int result = 0;
        if (quoteCancelEntries != null && quoteCancelEntries.length > 0) {
            result = quoteCancelEntries.length;
            quoteCancelEntries = null;
            noQuoteEntries = null;
        }

        return result;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (quoteID == null || quoteID.trim().isEmpty()) {
            errorMsg.append(" [QuoteID]");
            hasMissingTag = true;
        }
        if (quoteCancelType == null) {
            errorMsg.append(" [QuoteCancelType]");
            hasMissingTag = true;
        }
        if (noQuoteEntries == null || quoteCancelEntries == null || quoteCancelEntries.length == 0) {
            errorMsg.append(" [NoQuoteEntries]");
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
            if (MsgUtil.isTagInList(TagNum.QuoteID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteID, quoteID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteReqID, quoteReqID);
            }
            if (MsgUtil.isTagInList(TagNum.QuoteCancelType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteCancelType, quoteCancelType);
            }
            if (quoteResponseLevel != null && MsgUtil.isTagInList(TagNum.QuoteResponseLevel, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuoteResponseLevel, quoteResponseLevel.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (noQuoteEntries != null) {
                if (MsgUtil.isTagInList(TagNum.NoQuoteEntries, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoQuoteEntries, noQuoteEntries);
                    if (quoteCancelEntries != null && quoteCancelEntries.length == noQuoteEntries.intValue()) {
                        for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                            if (quoteCancelEntries[i] != null) {
                                bao.write(quoteCancelEntries[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "QuoteCancelEntries field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoQuoteEntries.getValue(), error);
                    }
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
        if (QUOTE_CANCEL_GROUP_TAGS.contains(tag.tagNum)) {
            if (noQuoteEntries != null && noQuoteEntries.intValue() > 0) {
                message.reset();
                quoteCancelEntries = new QuoteCancelGroup[noQuoteEntries.intValue()];
                for (int i = 0; i < noQuoteEntries.intValue(); i++) {
                    QuoteCancelGroup group = new QuoteCancelGroup42(context);
                    group.decode(message);
                    quoteCancelEntries[i] = group;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [QuoteCancelMsg] message version [4.2].";
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
