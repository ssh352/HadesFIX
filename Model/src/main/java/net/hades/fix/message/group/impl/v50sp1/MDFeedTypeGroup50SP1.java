/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MDFeedTypeGroup50SP1.java
 *
 * $Id: MDFeedTypeGroup50SP1.java,v 1.3 2011-04-20 00:32:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp1;

import net.hades.fix.message.FragmentContext;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.MDFeedTypeGroup;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.type.MarketDepth;

/**
 * FIX 5.0SP1 implementation of MDFeedTypeGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 05/04/2009, 11:39:24 AM
 */
@XmlRootElement(name="MDFeedTyps")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class MDFeedTypeGroup50SP1 extends MDFeedTypeGroup {

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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MDFeedTypeGroup50SP1() {
    }

    public MDFeedTypeGroup50SP1(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    @XmlAttribute(name = "MDFeedTyp")
    @Override
    public String getMDFeedType() {
        return MDFeedType;
    }

    @Override
    public void setMDFeedType(String MDFeedType) {
        this.MDFeedType = MDFeedType;
    }

    @XmlAttribute(name = "MDBkTyp")
    @Override
    public MDBookType getMDBookType() {
        return MDBookType;
    }

    @Override
    public void setMDBookType(MDBookType MDBookType) {
        this.MDBookType = MDBookType;
    }

    @XmlAttribute(name = "MktDepth")
    @Override
    public MarketDepth getMarketDepth() {
        return marketDepth;
    }

    @Override
    public void setMarketDepth(MarketDepth marketDepth) {
        this.marketDepth = marketDepth;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MDFeedTypeGroup] group version [5.0SP1].";
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
