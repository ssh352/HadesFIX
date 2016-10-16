/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsg.java
 *
 * $Id: EmailMsg.java,v 1.13 2011-04-28 10:07:41 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.MsgType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.RelatedSymbolGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.EmailType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The email message is similar to the format and purpose of the News message,
 * however, it is intended for private use between two parties.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 * @created 01/04/2009, 8:34:33 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EmailMsg extends FIXMsg  {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EmailThreadID.getValue(),
        TagNum.EmailType.getValue(),
        TagNum.OrigTime.getValue(),
        TagNum.Subject.getValue(),
        TagNum.NoRoutingIDs.getValue(),
        TagNum.NoRelatedSym.getValue(),
        TagNum.NoLegs.getValue(),
        TagNum.NoUnderlyings.getValue(),
        TagNum.OrderID.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.NoLinesOfText.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedSubjectLen.getValue(),
        TagNum.RawDataLength.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EmailThreadID.getValue(),
        TagNum.EmailType.getValue(),
        TagNum.Subject.getValue(),
        TagNum.NoLinesOfText.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 164 REQUIRED. Starting with 4.1 version.
     */
    protected String emailThreadID;

    /**
     * TagNum = 94 REQUIRED. Starting with 4.0 version.
     */
    protected EmailType emailType;

    /**
     * TagNum = 42. Starting with 4.0 version.
     */
    protected Date origTime;

    /**
     * TagNum = 147 REQUIRED. Starting with 4.1 version.
     */
    protected String subject;

    /**
     * TagNum = 356. Starting with 4.2 version.
     */
    protected Integer encodedSubjectLen;

    /**
     * TagNum = 356. Starting with 4.2 version.
     */
    protected byte[] encodedSubject;
    
    /**
     * TagNum = 215. Starting with 4.2 version.
     */
    protected Integer noRoutingIDs;

    /**
     * Starting with 4.2 version.
     */
    protected RoutingIDGroup[] routingIDGroups;

    /**
     * TagNum = 146. Starting with 4.1 version.
     */
    protected Integer noRelatedSyms;

    /**
     * Starting with 4.0 version.
     */
    protected RelatedSymbolGroup[] relatedSymGroups;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument[] instruments;

    /**
     * TagNum = 555. Starting with 4.4 version.
     */
    protected Integer noLegs;

    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg[] instrumentLegs;

    /**
     * TagNum = 711. Starting with 4.4 version.
     */
    protected Integer noUnderlyings;

    /**
     * Starting with 4.4 version.
     */
    protected UnderlyingInstrument[] underlyingInstruments;

    /**
     * TagNum = 37. Starting with 4.0 version.
     */
    protected String orderID;

    /**
     * TagNum = 11. Starting with 4.0 version.
     */
    protected String clOrdID ;

    /**
     * TagNum = 33. Starting with 4.0 version.
     */
    protected Integer noLinesOfText;

    /**
     * Starting with 4.0 version.
     */
    protected LinesOfTextGroup[] linesOfTextGroups;

    /**
     * TagNum = 95. Starting with 4.0 version.
     */
    protected Integer rawDataLength;

    /**
     * TagNum = 96. Starting with 4.0 version.
     */
    protected byte[] rawData;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public EmailMsg() {
        super();
    }

    public EmailMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public EmailMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.Email.getValue(), beginString);
    }

    public EmailMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.Email.getValue(), beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.EmailThreadID, required=true)
    public String getEmailThreadID() {
        return emailThreadID;
    }

    /**
     * Message field setter.
     * @param emailThreadID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.EmailThreadID, required=true)
    public void setEmailThreadID(String emailThreadID) {
        this.emailThreadID = emailThreadID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.EmailType, required=true)
    public EmailType getEmailType() {
        return emailType;
    }

    /**
     * Message field setter.
     * @param emailType field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.EmailType, required=true)
    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrigTime)
    public Date getOrigTime() {
        return origTime;
    }

    /**
     * Message field setter.
     * @param origTime field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrigTime)
    public void setOrigTime(Date origTime) {
        this.origTime = origTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.Subject, required=true)
    public String getSubject() {
        return subject;
    }

    /**
     * Message field setter.
     * @param subject field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.Subject, required=true)
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedSubjectLen)
    public Integer getEncodedSubjectLen() {
        return encodedSubjectLen;
    }

    /**
     * Message field setter.
     * @param encodedSubjectLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedSubjectLen)
    public void setEncodedSubjectLen(Integer encodedSubjectLen) {
        this.encodedSubjectLen = encodedSubjectLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedSubject)
    public byte[] getEncodedSubject() {
        return encodedSubject;
    }

    /**
     * Message field setter.
     * @param encodedSubject field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedSubject)
    public void setEncodedSubject(byte[] encodedSubject) {
        this.encodedSubject = encodedSubject;
        if (encodedSubjectLen == null) {
            encodedSubjectLen = new Integer(encodedSubject.length);
        }
    }
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRoutingIDs)
    public Integer getNoRoutingIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RoutingIDGroup} groups. It will also create an array
     * of {@link RoutingIDGroup} objects and set the <code>routingIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>routingIDGroups</code> array they will be discarded.<br/>
     * @param noRoutingIDs field value
     */
    @FIXVersion(introduced="4.2", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRoutingIDs)
    public void setNoRoutingIDs(Integer noRoutingIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RoutingIDGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired = "5.0SP2")
    public RoutingIDGroup[] getRoutingIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RoutingIDGroup} object to the existing array of <code>routingIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRoutingIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoRoutingIDs</code> method has been called there will already be a number of objects in the
     * <code>routingIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.2", retired = "5.0SP2")
    public RoutingIDGroup addRoutingIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RoutingIDGroup} object from the existing array of <code>routingIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRoutingIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.2", retired = "5.0SP2")
    public RoutingIDGroup deleteRoutingIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RoutingIDGroup} objects from the <code>routingIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRoutingIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.2", retired = "5.0SP2")
    public int clearRoutingIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRelatedSym)
    public Integer getNoRelatedSyms() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RelatedSymbolGroup} groups. It will also create an array
     * of {@link RelatedSymbolGroup} objects and set the <code>relatedSymGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>relatedSymGroups</code> array they will be discarded.<br/>
     * @param noRelatedSym field value
     */
    @FIXVersion(introduced="4.1", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoRelatedSym)
    public void setNoRelatedSyms(Integer noRelatedSym) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RelatedSymbolGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.0", retired="4.3")
    public RelatedSymbolGroup[] getRelatedSymGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RelatedSymbolGroup} object to the existing array of <code>relatedSymGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * Note: If the <code>setNoRelatedSyms</code> method has been called there will already be a number of objects in the
     * <code>relatedSymGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.0", retired="4.3")
    public RelatedSymbolGroup addRelatedSymGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RelatedSymbolGroup} object from the existing array of <code>relatedSymGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.0", retired="4.3")
    public RelatedSymbolGroup deleteRelatedSymGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RelatedSymbolGroup} objects from the <code>relatedSymGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRelatedSyms</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.0", retired="4.3")
    public int clearRelatedSymGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Instrument} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.0", retired = "5.0SP2")
    public Instrument[] getInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Instrument} object to the existing array of <code>instruments</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * Note: If the <code>setNoRelatedSyms</code> method has been called there will already be a number of objects in the
     * <code>instruments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.3", retired = "5.0SP2")
    public Instrument addInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Instrument} object from the existing array of <code>instruments</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRelatedSyms</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.3", retired = "5.0SP2")
    public Instrument deleteInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link Instrument} objects from the <code>instruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRelatedSyms</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.3", retired = "5.0SP2")
    public int clearInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public Integer getNoLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentLeg} components. It will also create an array
     * of {@link InstrumentLeg} objects and set the <code>instrumentLegs</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentLegs</code> array they will be discarded.<br/>
     * @param noLegs field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLegs)
    public void setNoLegs(Integer noLegs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link InstrumentLeg} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public InstrumentLeg[] getInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrumentLeg} object to the existing array of <code>instrumentLegs</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegs</code> method has been called there will already be a number of objects in the
     * <code>instrumentLegs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public InstrumentLeg addInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrumentLeg} object from the existing array of <code>instrumentLegs</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public InstrumentLeg deleteInstrumentLeg(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentLeg} objects from the <code>instrumentLegs</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public int clearInstrumentLegs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public Integer getNoUnderlyings() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UnderlyingInstrument} components. It will also create an array
     * of {@link UnderlyingInstrument} objects and set the <code>underlyingInstruments</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>underlyingInstruments</code> array they will be discarded.<br/>
     * @param noUnderlyings field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoUnderlyings)
    public void setNoUnderlyings(Integer noUnderlyings) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link UnderlyingInstrument} object to the existing array of <code>underlyingInstruments</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * Note: If the <code>setNoUnderlyings</code> method has been called there will already be a number of objects in the
     * <code>underlyingInstruments</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public UnderlyingInstrument addUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UnderlyingInstrument} object from the existing array of <code>underlyingInstruments</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUnderlyings</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UnderlyingInstrument} objects from the <code>underlyingInstruments</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUnderlyings</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public int clearUnderlyingInstruments() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrderID)
    public String getOrderID() {
        return orderID;
    }

    /**
     * Message field setter.
     * @param orderID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrderID)
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLinesOfText, required=true)
    public Integer getNoLinesOfText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link LinesOfTextGroup} components. It will also create an array
     * of {@link LinesOfTextGroup} objects and set the <code>linesOfTextGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>linesOfTextGroups</code> array they will be discarded.<br/>
     * @param noLinesOfText field value
     */
    @FIXVersion(introduced="4.0", retired = "5.0SP2")
    @TagNumRef(tagNum=TagNum.NoLinesOfText, required=true)
    public void setNoLinesOfText(Integer noLinesOfText) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UnderlyingInstrument} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "5.0SP2")
    public LinesOfTextGroup[] getLinesOfTextGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link LinesOfTextGroup} object to the existing array of <code>linesOfTextGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLinesOfText</code> field to the proper value.<br/>
     * Note: If the <code>setNoLinesOfText</code> method has been called there will already be a number of objects in the
     * <code>linesOfTextGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.0", retired = "5.0SP2")
    public LinesOfTextGroup addLinesOfTextGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link LinesOfTextGroup} object from the existing array of <code>linesOfTextGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLinesOfText</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.0", retired = "5.0SP2")
    public LinesOfTextGroup deleteLinesOfTextGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link LinesOfTextGroup} objects from the <code>linesOfTextGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLinesOfText</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.0", retired = "5.0SP2")
    public int clearLinesOfTextGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawDataLength)
    public Integer getRawDataLength() {
        return rawDataLength;
    }

    /**
     * Message field setter.
     * @param rawDataLength field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawDataLength)
    public void setRawDataLength(Integer rawDataLength) {
        this.rawDataLength = rawDataLength;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawData)
    public byte[] getRawData() {
        return rawData;
    }

    /**
     * Message field setter.
     * @param rawData field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RawData)
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
        if (rawDataLength == null) {
            rawDataLength = new Integer(rawData.length);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;

        if (emailThreadID == null || emailThreadID.trim().isEmpty()) {
            errorMsg.append(" [EmailThreadID]");
            hasMissingTag = true;
        }
        if (emailType == null) {
            errorMsg.append(" [EmailType]");
            hasMissingTag = true;
        }
        if (subject == null) {
            errorMsg.append(" [Subject]");
            hasMissingTag = true;
        }
        if (noLinesOfText == null) {
            errorMsg.append(" [NoLinesOfText]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.EmailThreadID, emailThreadID);
            if (emailType != null) {
                TagEncoder.encode(bao, TagNum.EmailType, emailType.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigTime, origTime);
            TagEncoder.encode(bao, TagNum.Subject, subject);
            if (encodedSubjectLen != null && encodedSubjectLen.intValue() > 0) {
                if (encodedSubject != null && encodedSubject.length > 0) {
                    encodedSubjectLen = new Integer(encodedSubject.length);
                    TagEncoder.encode(bao, TagNum.EncodedSubjectLen, encodedSubjectLen);
                    TagEncoder.encode(bao, TagNum.EncodedSubject, encodedSubject);
                }
            }
            if (noRoutingIDs != null) {
                TagEncoder.encode(bao, TagNum.NoRoutingIDs, noRoutingIDs);
                if (routingIDGroups != null && routingIDGroups.length == noRoutingIDs.intValue()) {
                    for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                        if (routingIDGroups[i] != null) {
                            bao.write(routingIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "RoutingIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRoutingIDs.getValue(), error);
                }
            }
            if (noRelatedSyms != null && relatedSymGroups != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (relatedSymGroups != null && relatedSymGroups.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (relatedSymGroups[i] != null) {
                            bao.write(relatedSymGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "RelatedSymGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
                }
            }
            if (noRelatedSyms != null && instruments != null) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (instruments != null && instruments.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (instruments[i] != null) {
                            bao.write(instruments[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "Instrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRelatedSym.getValue(), error);
                }
            }
            if (noUnderlyings != null) {
                TagEncoder.encode(bao, TagNum.NoUnderlyings, noUnderlyings);
                if (underlyingInstruments != null && underlyingInstruments.length == noUnderlyings.intValue()) {
                    for (int i = 0; i < noUnderlyings.intValue(); i++) {
                        if (underlyingInstruments[i] != null) {
                            bao.write(underlyingInstruments[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UnderlyingInstrument field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoUnderlyings.getValue(), error);
                }
            }
            if (noLegs != null) {
                TagEncoder.encode(bao, TagNum.NoLegs, noLegs);
                if (instrumentLegs != null && instrumentLegs.length == noLegs.intValue()) {
                    for (int i = 0; i < noLegs.intValue(); i++) {
                        if (instrumentLegs[i] != null) {
                            bao.write(instrumentLegs[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrumentLeg field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
                }
            }
            TagEncoder.encode(bao, TagNum.OrderID, orderID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            if (noLinesOfText != null) {
                TagEncoder.encode(bao, TagNum.NoLinesOfText, noLinesOfText);
                if (linesOfTextGroups != null && linesOfTextGroups.length == noLinesOfText.intValue()) {
                    for (int i = 0; i < noLinesOfText.intValue(); i++) {
                        if (linesOfTextGroups[i] != null) {
                            bao.write(linesOfTextGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "LinesOfTextGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLinesOfText.getValue(), error);
                }
            }
            if (rawDataLength != null && rawDataLength.intValue() > 0) {
                if (rawData != null && rawData.length > 0) {
                    rawDataLength = new Integer(rawData.length);
                    TagEncoder.encode(bao, TagNum.RawDataLength, rawDataLength);
                    TagEncoder.encode(bao, TagNum.RawData, rawData);
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case EmailThreadID:
                emailThreadID = new String(tag.value, sessionCharset);
                break;

            case EmailType:
                emailType = EmailType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case OrigTime:
                origTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case Subject:
                subject = new String(tag.value, sessionCharset);
                break;

            case NoRoutingIDs:
                noRoutingIDs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NoRelatedSym:
                noRelatedSyms = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NoLegs:
                noLegs = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case NoUnderlyings:
                noUnderlyings = new Integer(new String(tag.value, getSessionCharset()));
                break;

            case OrderID:
                orderID = new String(tag.value, sessionCharset);
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case NoLinesOfText:
                noLinesOfText = new Integer(new String(tag.value, getSessionCharset()));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in FIX EmailMsg.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;

        if (tag.tagNum == TagNum.EncodedSubjectLen.getValue()) {
            try {
                encodedSubjectLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedSubjectLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.EncodedSubjectLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedSubjectLen.intValue());
            encodedSubject = dataTag.value;
        }
        if (tag.tagNum == TagNum.RawDataLength.getValue()) {
            try {
                rawDataLength = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [RawDataLength] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(),
                        TagNum.RawDataLength.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, rawDataLength.intValue());
            rawData = dataTag.value;
        }

        return result;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{EmailMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.EmailThreadID, emailThreadID);
        printTagValue(b, TagNum.EmailType, emailType);
        printUTCDateTimeTagValue(b, TagNum.OrigTime, origTime);
        printTagValue(b, TagNum.Subject, subject);
        printTagValue(b, TagNum.EncodedSubjectLen, encodedSubjectLen);
        printTagValue(b, TagNum.EncodedSubject, encodedSubject);
        printTagValue(b, TagNum.NoRoutingIDs, noRoutingIDs);
        printTagValue(b, routingIDGroups);
        printTagValue(b, TagNum.NoRelatedSym, noRelatedSyms);
        printTagValue(b, relatedSymGroups);
        printTagValue(b, instruments);
        printTagValue(b, TagNum.NoLegs, noLegs);
        printTagValue(b, instrumentLegs);
        printTagValue(b, TagNum.NoUnderlyings, noUnderlyings);
        printTagValue(b, underlyingInstruments);
        printTagValue(b, TagNum.OrderID, orderID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.NoLinesOfText, noLinesOfText);
        printTagValue(b, linesOfTextGroups);
        printTagValue(b, TagNum.RawDataLength, rawDataLength);
        printBase64TagValue(b, TagNum.RawData, rawData);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
