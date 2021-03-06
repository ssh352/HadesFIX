/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderMultilegMsgBuilder.java
 *
 * $Id: NewOrderMultilegMsgBuilder.java,v 1.1 2011-09-08 08:50:58 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.Header;
import net.hades.fix.message.NewOrderMultilegMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v43.NewOrderMultilegMsg43;
import net.hades.fix.message.impl.v44.NewOrderMultilegMsg44;
import net.hades.fix.message.impl.v50sp1.NewOrderMultilegMsg50SP1;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v50.NewOrderMultilegMsg50;
import net.hades.fix.message.impl.v50sp2.NewOrderMultilegMsg50SP2;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a NewOrderMultilegMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/05/2011, 3:15:57 PM
 */
public class NewOrderMultilegMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NewOrderMultilegMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        NewOrderMultilegMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.NewOrderMultileg.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX_4_3:
                fixMsg = new NewOrderMultilegMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new NewOrderMultilegMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new NewOrderMultilegMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new NewOrderMultilegMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new NewOrderMultilegMsg50SP2(header, message);
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
        NewOrderMultilegMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.NewOrderMultileg.getValue(),
                        version, applVerID));

            case FIX_4_3:
                message = new NewOrderMultilegMsg43(version);
                break;

            case FIX_4_4:
                message = new NewOrderMultilegMsg44(version);
                break;

            case FIX_5_0:
                message = new NewOrderMultilegMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new NewOrderMultilegMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new NewOrderMultilegMsg50SP2(version);
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

    private NewOrderMultilegMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        NewOrderMultilegMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
            case FIX41:
            case FIX42:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.NewOrderMultileg.getValue(),
                        version, applVerID));

            case FIX43:
                message = new NewOrderMultilegMsg43(version, applVerID);
                break;

            case FIX44:
                message = new NewOrderMultilegMsg44(version, applVerID);
                break;

            case FIX50:
                message = new NewOrderMultilegMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new NewOrderMultilegMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new NewOrderMultilegMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private NewOrderMultilegMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        NewOrderMultilegMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
            case FIX41:
            case FIX42:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.NewOrderMultileg.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX43:
                message = new NewOrderMultilegMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new NewOrderMultilegMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new NewOrderMultilegMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new NewOrderMultilegMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new NewOrderMultilegMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
