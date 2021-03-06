/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties450SP1.java
 *
 * $Id: NestedParties450SP1.java,v 1.2 2011-04-14 23:44:46 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties4;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.Nested4PartyGroup;
import net.hades.fix.message.group.impl.v50sp1.Nested4PartyGroup50SP1;
import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * FIX version 5.0SP1 implementation of NestedParties4 component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 07/04/2009, 8:40:46 AM
 */
@XmlTransient
public class NestedParties450SP1 extends NestedParties4 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED4_PARTY_GROUP_TAGS = new Nested4PartyGroup50SP1().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED4_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED4_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties450SP1() {
        super();
    }

    public NestedParties450SP1(FragmentContext context) {
        super(context);
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
    public Nested4PartyGroup[] getNested4PartyIDGroups() {
        return nested4PartyGroups;
    }

    public void setNested4PartyIDGroups(Nested4PartyGroup[] nested4PartyGroups) {
        this.nested4PartyGroups = nested4PartyGroups;
        if (nested4PartyGroups != null) {
            noNested4PartyIDs = new Integer(nested4PartyGroups.length);
        }
    }

    @Override
    public void setNoNested4PartyIDs(Integer noNested4PartyIDs) {
        this.noNested4PartyIDs = noNested4PartyIDs;
        if (noNested4PartyIDs != null) {
            nested4PartyGroups = new Nested4PartyGroup[noNested4PartyIDs.intValue()];
            for (int i = 0; i < nested4PartyGroups.length; i++) {
                nested4PartyGroups[i] = new Nested4PartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @Override
    public Nested4PartyGroup addNested4PartyGroup() {
        Nested4PartyGroup group = new Nested4PartyGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<Nested4PartyGroup> groups = new ArrayList<Nested4PartyGroup>();
        if (nested4PartyGroups != null && nested4PartyGroups.length > 0) {
            groups = new ArrayList<Nested4PartyGroup>(Arrays.asList(nested4PartyGroups));
        }
        groups.add(group);
        nested4PartyGroups = groups.toArray(new Nested4PartyGroup[groups.size()]);
        noNested4PartyIDs = new Integer(nested4PartyGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (NESTED4_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNested4PartyIDs != null && noNested4PartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
                message.reset();
                if (nested4PartyGroups == null) {
                    nested4PartyGroups = new Nested4PartyGroup[noNested4PartyIDs.intValue()];
                }
                for (int i = 0; i < nested4PartyGroups.length; i++) {
                    Nested4PartyGroup group = new Nested4PartyGroup50SP1(context);
                    group.decode(message);
                    nested4PartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedParties4] component version [5.0SP1].";
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
