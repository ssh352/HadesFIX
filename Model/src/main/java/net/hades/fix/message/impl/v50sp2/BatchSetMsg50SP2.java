/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BatchSetMsg50SP2.java
 *
 * $Id: BatchSetMsg50SP2.java,v 1.1 2011-04-27 23:28:23 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;

import net.hades.fix.message.BatchSetMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.Batch;
import net.hades.fix.message.comp.impl.v50sp2.Batch50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;

/**
 * FIX 5.0SP2 implementation of the BatchSetMsg.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/04/2011
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BatchSetMsg50SP2 extends BatchSetMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BatchSetMsg50SP2() {
        super();
    }

    public BatchSetMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public BatchSetMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public BatchSetMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        BatchSetMsg50SP2 fixml = (BatchSetMsg50SP2) fragment;
        if (fixml.getHeader() != null) {
            setHeader(fixml.getHeader());
        }
        if (fixml.getBatches() != null && fixml.getBatches().length > 0) {
            setBatches(fixml.getBatches());
        }
    }

    @Override
    public void setNoBatches(Integer noBatches) {
        this.noBatches = noBatches;
        if (noBatches != null) {
            batches = new Batch[noBatches.intValue()];
            for (int i = 0; i < batches.length; i++) {
                batches[i] = new Batch50SP2();
            }
        }
    }

    @XmlElementRef
    @Override
    public Batch[] getBatches() {
        return batches;
    }

    @Override
    public void setBatches(Batch[] batches) {
        this.batches = batches;
        if (batches != null) {
            noBatches = new Integer(batches.length);
        }
    }

    @Override
    public Batch addBatch() {
        Batch batch = new Batch50SP2();
        List<Batch> msgs = new ArrayList<Batch>();
        if (batches != null && batches.length > 0) {
            msgs = new ArrayList<Batch>(Arrays.asList(batches));
        }
        msgs.add(batch);
        batches = msgs.toArray(new Batch[msgs.size()]);
        noBatches = new Integer(batches.length);

        return batch;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BatchSet] component version [5.0SP2].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
