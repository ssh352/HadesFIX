/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties44.java
 *
 * $Id: Parties44.java,v 1.10 2011-04-14 23:44:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v44.PartyGroup44;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of Parties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlTransient
public class Parties44 extends Parties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> PARTY_ID_GROUP_TAGS = new PartyGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(PARTY_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTY_ID_GROUP_TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Parties44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public Parties44(FragmentContext context) {
        super(context);
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
    public Integer getNoPartyIDs() {
        return noPartyIDs;
    }

    @Override
    public void setNoPartyIDs(Integer noPartyIDs) {
        this.noPartyIDs = noPartyIDs;
        if (noPartyIDs != null) {
            partyIDGroups = new PartyGroup[noPartyIDs.intValue()];
            for (int i = 0; i < partyIDGroups.length; i++) {
                partyIDGroups[i] = new PartyGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public PartyGroup[] getPartyIDGroups() {
        return partyIDGroups;
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        this.partyIDGroups = partyIDGroups;
        if (partyIDGroups != null) {
            noPartyIDs = new Integer(partyIDGroups.length);
        }
    }

    @Override
    public PartyGroup addPartyIDGroup() {
        PartyGroup group = new PartyGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<PartyGroup> groups = new ArrayList<PartyGroup>();
        if (partyIDGroups != null && partyIDGroups.length > 0) {
            groups = new ArrayList<PartyGroup>(Arrays.asList(partyIDGroups));
        }
        groups.add(group);
        partyIDGroups = groups.toArray(new PartyGroup[groups.size()]);
        noPartyIDs = new Integer(partyIDGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.NoPartyIDs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoPartyIDs, noPartyIDs);
                if (partyIDGroups != null && partyIDGroups.length == noPartyIDs.intValue()) {
                    for (int i = 0; i < noPartyIDs.intValue(); i++) {
                        if (partyIDGroups[i] != null) {
                            bao.write(partyIDGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "PartyGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNestedPartyIDs.getValue(), error);
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
        if (PARTY_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noPartyIDs != null && noPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                message.reset();
                if (partyIDGroups == null) {
                    partyIDGroups = new PartyGroup[noPartyIDs.intValue()];
                }
                for (int i = 0; i < partyIDGroups.length; i++) {
                    PartyGroup group = new PartyGroup44(context);
                    group.decode(message);
                    partyIDGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [Parties] component version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
