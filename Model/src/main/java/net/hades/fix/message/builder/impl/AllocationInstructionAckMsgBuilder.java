/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionAckMsgBuilder.java
 *
 * $Id: AllocationInstructionAckMsgBuilder.java,v 1.1 2011-02-16 11:24:35 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v41.AllocationInstructionAckMsg41;
import net.hades.fix.message.impl.v44.AllocationInstructionAckMsg44;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;

import net.hades.fix.message.AllocationInstructionAckMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.AllocationInstructionAckMsg40;
import net.hades.fix.message.impl.v42.AllocationInstructionAckMsg42;
import net.hades.fix.message.impl.v43.AllocationInstructionAckMsg43;
import net.hades.fix.message.impl.v50.AllocationInstructionAckMsg50;
import net.hades.fix.message.impl.v50sp1.AllocationInstructionAckMsg50SP1;
import net.hades.fix.message.impl.v50sp2.AllocationInstructionAckMsg50SP2;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a AllocationInstructionAckMsgBuilder message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class AllocationInstructionAckMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationInstructionAckMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        AllocationInstructionAckMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new AllocationInstructionAckMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new AllocationInstructionAckMsg41(header, message);
                break;

            case FIX_4_2:
                fixMsg = new AllocationInstructionAckMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new AllocationInstructionAckMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new AllocationInstructionAckMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new AllocationInstructionAckMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new AllocationInstructionAckMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new AllocationInstructionAckMsg50SP2(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                fixMsg = createFIXTMessage(header, message);
                break;
        }

        return fixMsg;
    }

    @Override
    public FIXMsg build(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        AllocationInstructionAckMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new AllocationInstructionAckMsg40(version);
                break;

            case FIX_4_1:
                message = new AllocationInstructionAckMsg41(version);
                break;

            case FIX_4_2:
                message = new AllocationInstructionAckMsg42(version);
                break;

            case FIX_4_3:
                message = new AllocationInstructionAckMsg43(version);
                break;

            case FIX_4_4:
                message = new AllocationInstructionAckMsg44(version);
                break;

            case FIX_5_0:
                message = new AllocationInstructionAckMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new AllocationInstructionAckMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new AllocationInstructionAckMsg50SP2(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                message = createFIXTMessage(version, applVerID);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private AllocationInstructionAckMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        AllocationInstructionAckMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new AllocationInstructionAckMsg40(version, applVerID);
                break;

            case FIX41:
                message = new AllocationInstructionAckMsg41(version, applVerID);
                break;

            case FIX42:
                message = new AllocationInstructionAckMsg42(version, applVerID);
                break;

            case FIX43:
                message = new AllocationInstructionAckMsg43(version, applVerID);
                break;

            case FIX44:
                message = new AllocationInstructionAckMsg44(version, applVerID);
                break;

            case FIX50:
                message = new AllocationInstructionAckMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new AllocationInstructionAckMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new AllocationInstructionAckMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private AllocationInstructionAckMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        AllocationInstructionAckMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new AllocationInstructionAckMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new AllocationInstructionAckMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new AllocationInstructionAckMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new AllocationInstructionAckMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new AllocationInstructionAckMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new AllocationInstructionAckMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new AllocationInstructionAckMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new AllocationInstructionAckMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
