/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TradingSessionStatusMsgBuilder.java
 *
 * $Id: TradingSessionStatusMsgBuilder.java,v 1.1 2011-04-23 00:19:06 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.impl.v42.TradingSessionStatusMsg42;
import net.hades.fix.message.impl.v43.TradingSessionStatusMsg43;
import net.hades.fix.message.impl.v44.TradingSessionStatusMsg44;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.TradingSessionStatusMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v50.TradingSessionStatusMsg50;
import net.hades.fix.message.impl.v50sp1.TradingSessionStatusMsg50SP1;
import net.hades.fix.message.impl.v50sp2.TradingSessionStatusMsg50SP2;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a TradingSessionStatusMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class TradingSessionStatusMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TradingSessionStatusMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        TradingSessionStatusMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
            case FIX_4_1:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.TradingSessionStatus.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX_4_2:
                fixMsg = new TradingSessionStatusMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new TradingSessionStatusMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new TradingSessionStatusMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new TradingSessionStatusMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new TradingSessionStatusMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new TradingSessionStatusMsg50SP2(header, message);
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
        TradingSessionStatusMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
            case FIX_4_1:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.TradingSessionStatus.getValue(),
                        version, applVerID));

            case FIX_4_2:
                message = new TradingSessionStatusMsg42(version);
                break;

            case FIX_4_3:
                message = new TradingSessionStatusMsg43(version);
                break;

            case FIX_4_4:
                message = new TradingSessionStatusMsg44(version);
                break;

            case FIX_5_0:
                message = new TradingSessionStatusMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new TradingSessionStatusMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new TradingSessionStatusMsg50SP2(version);
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

    private TradingSessionStatusMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        TradingSessionStatusMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
            case FIX41:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.TradingSessionStatus.getValue(),
                        version, applVerID));

            case FIX42:
                message = new TradingSessionStatusMsg42(version, applVerID);
                break;

            case FIX43:
                message = new TradingSessionStatusMsg43(version, applVerID);
                break;

            case FIX44:
                message = new TradingSessionStatusMsg44(version, applVerID);
                break;

            case FIX50:
                message = new TradingSessionStatusMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new TradingSessionStatusMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new TradingSessionStatusMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private TradingSessionStatusMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        TradingSessionStatusMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
            case FIX41:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.TradingSessionStatus.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX42:
                message = new TradingSessionStatusMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new TradingSessionStatusMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new TradingSessionStatusMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new TradingSessionStatusMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new TradingSessionStatusMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new TradingSessionStatusMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
