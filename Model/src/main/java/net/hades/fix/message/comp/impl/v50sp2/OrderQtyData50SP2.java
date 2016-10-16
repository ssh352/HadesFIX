/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderQtyData50SP2.java
 *
 * $Id: OrderQtyData50SP2.java,v 1.6 2010-02-04 10:11:09 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.type.RoundingDirection;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * FIX version 5.0SP2 implementation of OrderQtyData component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 14/02/2009, 7:19:18 PM
 */
@XmlRootElement(name="OrdQty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class OrderQtyData50SP2 extends OrderQtyData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -1708951969812954548L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderQtyData50SP2() {
        super();
    }

    public OrderQtyData50SP2(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "Qty")
    @Override
    public Double getOrderQty() {
        return orderQty;
    }

    @Override
    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    @XmlAttribute(name = "Cash")
    @Override
    public Double getCashOrderQty() {
        return cashOrderQty;
    }

    @Override
    public void setCashOrderQty(Double cashOrderQty) {
        this.cashOrderQty = cashOrderQty;
    }

    @XmlAttribute(name = "Pct")
    @Override
    public Double getOrderPercent() {
        return orderPercent;
    }

    @Override
    public void setOrderPercent(Double orderPercent) {
        this.orderPercent = orderPercent;
    }

    @XmlAttribute(name = "RndDir")
    @Override
    public RoundingDirection getRoundingDirection() {
        return roundingDirection;
    }

    @Override
    public void setRoundingDirection(RoundingDirection roundingDirection) {
        this.roundingDirection = roundingDirection;
    }

    @XmlAttribute(name = "RndMod")
    @Override
    public Double getRoundingModulus() {
        return roundingModulus;
    }

    @Override
    public void setRoundingModulus(Double roundingModulus) {
        this.roundingModulus = roundingModulus;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderQtyData] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
