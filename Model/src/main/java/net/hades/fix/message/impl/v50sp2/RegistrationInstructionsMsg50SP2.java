/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RegistrationInstructionsMsg50SP2.java
 *
 * $Id: RegistrationInstructionsMsg50SP2.java,v 1.2 2011-10-29 01:31:22 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.OwnershipType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.RegistrationInstructionsMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DistribInstsGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.group.impl.v50sp2.DistribInstsGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.RgstDtlsGroup50SP2;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.RegistTransType;

/**
 * FIX version 5.0SP2 RegistrationInstructionsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="RgstInstrctns")
@XmlType(propOrder = {"header", "partyIDGroups", "rgstDtlsGroups", "distribInstsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RegistrationInstructionsMsg50SP2 extends RegistrationInstructionsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    protected static final Set<Integer> RGST_DTLS_GROUP_TAGS = new RgstDtlsGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> DISTRIB_INSTS_GROUP_TAGS = new DistribInstsGroup50SP2().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(RGST_DTLS_GROUP_TAGS);
        ALL_TAGS.addAll(DISTRIB_INSTS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(RGST_DTLS_GROUP_TAGS);
        START_COMP_TAGS.addAll(DISTRIB_INSTS_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RegistrationInstructionsMsg50SP2() {
        super();
    }

    public RegistrationInstructionsMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public RegistrationInstructionsMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public RegistrationInstructionsMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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
        RegistrationInstructionsMsg50SP2 fixml = (RegistrationInstructionsMsg50SP2) fragment;
        if (fixml.getRegistID() != null) {
            registID = fixml.getRegistID();
        }
        if (fixml.getRegistTransType() != null) {
            registTransType = fixml.getRegistTransType();
        }
        if (fixml.getRegistRefID() != null) {
            registRefID = fixml.getRegistRefID();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getAccount() != null) {
            account = fixml.getAccount();
        }
        if (fixml.getAcctIDSource() != null) {
            acctIDSource = fixml.getAcctIDSource();
        }
        if (fixml.getRegistAcctType() != null) {
            registAcctType = fixml.getRegistAcctType();
        }
        if (fixml.getTaxAdvantageType() != null) {
            taxAdvantageType = fixml.getTaxAdvantageType();
        }
        if (fixml.getOwnershipType() != null) {
            ownershipType = fixml.getOwnershipType();
        }
        if (fixml.getRgstDtlsGroups() != null && fixml.getRgstDtlsGroups().length > 0) {
            setRgstDtlsGroups(fixml.getRgstDtlsGroups());
        }
        if (fixml.getDistribInstsGroups() != null && fixml.getDistribInstsGroups().length > 0) {
            setDistribInstsGroups(fixml.getDistribInstsGroups());
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
    public String getRegistID() {
        return registID;
    }

    @Override
    public void setRegistID(String registID) {
        this.registID = registID;
    }

    @XmlAttribute(name = "TransTyp")
    @Override
    public RegistTransType getRegistTransType() {
        return registTransType;
    }

    @Override
    public void setRegistTransType(RegistTransType registTransType) {
        this.registTransType = registTransType;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getRegistRefID() {
        return registRefID;
    }

    @Override
    public void setRegistRefID(String registRefID) {
        this.registRefID = registRefID;
    }

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50SP2(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50SP2) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "Acct")
    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    @XmlAttribute(name = "AcctIDSrc")
    @Override
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    @Override
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    @XmlAttribute(name = "AcctTyp")
    @Override
    public String getRegistAcctType() {
        return registAcctType;
    }

    @Override
    public void setRegistAcctType(String registAcctType) {
        this.registAcctType = registAcctType;
    }

    @XmlAttribute(name = "TaxAdvantageTyp")
    @Override
    public Integer getTaxAdvantageType() {
        return taxAdvantageType;
    }

    @Override
    public void setTaxAdvantageType(Integer taxAdvantageType) {
        this.taxAdvantageType = taxAdvantageType;
    }

    @XmlAttribute(name = "OwnershipTyp")
    @Override
    public OwnershipType getOwnershipType() {
        return ownershipType;
    }

    @Override
    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }


    @Override
    public Integer getNoRegistDtls() {
        return noRegistDtls;
    }

    @Override
    public void setNoRegistDtls(Integer noRegistDtls) {
        this.noRegistDtls = noRegistDtls;
        if (noRegistDtls != null) {
            registDtlsGroups = new RgstDtlsGroup[noRegistDtls.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < registDtlsGroups.length; i++) {
                registDtlsGroups[i] = new RgstDtlsGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public RgstDtlsGroup[] getRgstDtlsGroups() {
        return registDtlsGroups;
    }

    public void setRgstDtlsGroups(RgstDtlsGroup[] registDtlsGroups) {
        this.registDtlsGroups = registDtlsGroups;
        if (registDtlsGroups != null) {
            noRegistDtls = new Integer(registDtlsGroups.length);
        }
    }

    @Override
    public RgstDtlsGroup addRgstDtlsGroup() {
        RgstDtlsGroup group = new RgstDtlsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<RgstDtlsGroup> groups = new ArrayList<RgstDtlsGroup>();
        if (registDtlsGroups != null && registDtlsGroups.length > 0) {
            groups = new ArrayList<RgstDtlsGroup>(Arrays.asList(registDtlsGroups));
        }
        groups.add(group);
        registDtlsGroups = groups.toArray(new RgstDtlsGroup[groups.size()]);
        noRegistDtls = new Integer(registDtlsGroups.length);

        return group;
    }

    @Override
    public RgstDtlsGroup deleteRgstDtlsGroup(int index) {
        RgstDtlsGroup result = null;
        if (registDtlsGroups != null && registDtlsGroups.length > 0 && registDtlsGroups.length > index) {
            List<RgstDtlsGroup> groups = new ArrayList<RgstDtlsGroup>(Arrays.asList(registDtlsGroups));
            result = groups.remove(index);
            registDtlsGroups = groups.toArray(new RgstDtlsGroup[groups.size()]);
            if (registDtlsGroups.length > 0) {
                noRegistDtls = new Integer(registDtlsGroups.length);
            } else {
                registDtlsGroups = null;
                noRegistDtls = null;
            }
        }

        return result;
    }

    @Override
    public int clearRgstDtlsGroup() {
        int result = 0;
        if (registDtlsGroups != null && registDtlsGroups.length > 0) {
            result = registDtlsGroups.length;
            registDtlsGroups = null;
            noRegistDtls = null;
        }

        return result;
    }

    @Override
    public Integer getNoDistribInsts() {
        return noDistribInsts;
    }

    @Override
    public void setNoDistribInsts(Integer noDistribInsts) {
        this.noDistribInsts = noDistribInsts;
        if (noDistribInsts != null) {
            distribInstsGroups = new DistribInstsGroup[noDistribInsts.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < distribInstsGroups.length; i++) {
                distribInstsGroups[i] = new DistribInstsGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public DistribInstsGroup[] getDistribInstsGroups() {
        return distribInstsGroups;
    }

    public void setDistribInstsGroups(DistribInstsGroup[] distribInstsGroups) {
        this.distribInstsGroups = distribInstsGroups;
        if (distribInstsGroups != null) {
            noDistribInsts = new Integer(distribInstsGroups.length);
        }
    }

    @Override
    public DistribInstsGroup addDistribInstsGroup() {
        DistribInstsGroup group = new DistribInstsGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<DistribInstsGroup> groups = new ArrayList<DistribInstsGroup>();
        if (distribInstsGroups != null && distribInstsGroups.length > 0) {
            groups = new ArrayList<DistribInstsGroup>(Arrays.asList(distribInstsGroups));
        }
        groups.add(group);
        distribInstsGroups = groups.toArray(new DistribInstsGroup[groups.size()]);
        noDistribInsts = new Integer(distribInstsGroups.length);

        return group;
    }

    @Override
    public DistribInstsGroup deleteDistribInstsGroup(int index) {
        DistribInstsGroup result = null;
        if (distribInstsGroups != null && distribInstsGroups.length > 0 && distribInstsGroups.length > index) {
            List<DistribInstsGroup> groups = new ArrayList<DistribInstsGroup>(Arrays.asList(distribInstsGroups));
            result = groups.remove(index);
            distribInstsGroups = groups.toArray(new DistribInstsGroup[groups.size()]);
            if (distribInstsGroups.length > 0) {
                noDistribInsts = new Integer(distribInstsGroups.length);
            } else {
                distribInstsGroups = null;
                noDistribInsts = null;
            }
        }

        return result;
    }

    @Override
    public int clearDistribInstsGroups() {
        int result = 0;
        if (distribInstsGroups != null && distribInstsGroups.length > 0) {
            result = distribInstsGroups.length;
            distribInstsGroups = null;
            noDistribInsts = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
        if (RGST_DTLS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRegistDtls != null && noRegistDtls.intValue() > 0) {
                message.reset();
                registDtlsGroups = new RgstDtlsGroup[noRegistDtls.intValue()];
                for (int i = 0; i < noRegistDtls.intValue(); i++) {
                    RgstDtlsGroup group = new RgstDtlsGroup50SP2(context);
                    group.decode(message);
                    registDtlsGroups[i] = group;
                }
            }
        }
        if (DISTRIB_INSTS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noDistribInsts != null && noDistribInsts.intValue() > 0) {
                message.reset();
                distribInstsGroups = new DistribInstsGroup[noDistribInsts.intValue()];
                for (int i = 0; i < noDistribInsts.intValue(); i++) {
                    DistribInstsGroup component = new DistribInstsGroup50SP2(context);
                    component.decode(message);
                    distribInstsGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RegistrationInstructionsMsg] message version [5.0SP2].";
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
