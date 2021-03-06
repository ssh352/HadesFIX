/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlDetailsGroup50SP1.java
 *
 * $Id: SettlDetailsGroup50SP1.java,v 1.2 2011-10-25 08:29:20 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SettlPartyGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SettlObligSource;

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

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SettlDetailsGroup;

/**
 * FIX 5.0SP1 implementation of SettlDetailsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 12/02/2009, 7:22:35 PM
 */
@XmlRootElement(name="SettlDetails")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SettlDetailsGroup50SP1 extends SettlDetailsGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> SETTL_PARTY_GROUP_TAGS = new SettlPartyGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(SETTL_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SETTL_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlDetailsGroup50SP1() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SettlDetailsGroup50SP1(FragmentContext context) {
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

    @XmlAttribute(name = "SettlSrc")
    @Override
    public SettlObligSource getSettlObligSource() {
        return settlObligSource;
    }

    @Override
    public void setSettlObligSource(SettlObligSource settlObligSource) {
        this.settlObligSource = settlObligSource;
    }

    @Override
    public Integer getNoSettlPartyIDs() {
        return noSettlPartyIDs;
    }

    @Override
    public void setNoSettlPartyIDs(Integer noSettlPartyIDs) {
        this.noSettlPartyIDs = noSettlPartyIDs;
        if (noSettlPartyIDs != null) {
            settlPartyGroups = new SettlPartyGroup[noSettlPartyIDs.intValue()];
            for (int i = 0; i < settlPartyGroups.length; i++) {
                settlPartyGroups[i] = new SettlPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SettlPartyGroup[] getSettlPartyGroups() {
        return settlPartyGroups;
    }

    public void setSettlPartyGroups(SettlPartyGroup[] settlPartyGroups) {
        this.settlPartyGroups = settlPartyGroups;
        if (settlPartyGroups != null) {
            noSettlPartyIDs = new Integer(settlPartyGroups.length);
        }
    }

    @Override
    public SettlPartyGroup addSettlPartyGroup() {
        SettlPartyGroup group = new SettlPartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<SettlPartyGroup> groups = new ArrayList<SettlPartyGroup>();
        if (settlPartyGroups != null && settlPartyGroups.length > 0) {
            groups = new ArrayList<SettlPartyGroup>(Arrays.asList(settlPartyGroups));
        }
        groups.add(group);
        settlPartyGroups = groups.toArray(new SettlPartyGroup[groups.size()]);
        noSettlPartyIDs = new Integer(settlPartyGroups.length);

        return group;
    }

    @Override
    public SettlPartyGroup deleteSettlPartyGroup(int index) {
        SettlPartyGroup result = null;
        if (settlPartyGroups != null && settlPartyGroups.length > 0 && settlPartyGroups.length > index) {
            List<SettlPartyGroup> groups = new ArrayList<SettlPartyGroup>(Arrays.asList(settlPartyGroups));
            result = groups.remove(index);
            settlPartyGroups = groups.toArray(new SettlPartyGroup[groups.size()]);
            if (settlPartyGroups.length > 0) {
                noSettlPartyIDs = new Integer(settlPartyGroups.length);
            } else {
                settlPartyGroups = null;
                noSettlPartyIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearSettlPartyGroups() {
        int result = 0;
        if (settlPartyGroups != null && settlPartyGroups.length > 0) {
            result = settlPartyGroups.length;
            settlPartyGroups = null;
            noSettlPartyIDs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (SETTL_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSettlPartyIDs != null && noSettlPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (settlPartyGroups == null) {
                    settlPartyGroups = new SettlPartyGroup[noSettlPartyIDs.intValue()];
                }
                for (int i = 0; i < settlPartyGroups.length; i++) {
                    SettlPartyGroup group = new SettlPartyGroup50SP1(context);
                    group.decode(message);
                    settlPartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlDetailsGroup] group version [5.0SP1].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
