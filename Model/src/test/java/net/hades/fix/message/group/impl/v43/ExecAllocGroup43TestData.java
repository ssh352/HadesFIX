/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecAllocGroup43TestData.java
 *
 * $Id: ExecAllocGroup43TestData.java,v 1.1 2011-02-16 11:24:34 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.group.ExecAllocGroup;
import net.hades.fix.message.type.LastCapacity;

/**
 * Test utility for ExecAllocGroup43 group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:12:46 AM
 */
public class ExecAllocGroup43TestData extends MsgTest {

    private static final ExecAllocGroup43TestData INSTANCE;

    static {
        INSTANCE = new ExecAllocGroup43TestData();
    }

    public static ExecAllocGroup43TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(ExecAllocGroup grp) throws UnsupportedEncodingException {
        grp.setLastQty(22.0d);
        grp.setExecID("EXEC384744");
        grp.setSecondaryExecID("SEX99333");
        grp.setLastPx(33.44d);
        grp.setLastCapacity(LastCapacity.Principal);
    }

    public void populate2(ExecAllocGroup grp) throws UnsupportedEncodingException {
        grp.setLastQty(23.0d);
        grp.setExecID("EXEC384755");
        grp.setSecondaryExecID("SEX99444");
        grp.setLastPx(33.55d);
        grp.setLastCapacity(LastCapacity.Agent);
    }

    public void check(ExecAllocGroup expected, ExecAllocGroup actual) throws Exception {
        assertEquals(expected.getLastQty(), actual.getLastQty());
        assertEquals(expected.getExecID(), actual.getExecID());
        assertEquals(expected.getSecondaryExecID(), actual.getSecondaryExecID());
        assertEquals(expected.getLastPx(), actual.getLastPx());
        assertEquals(expected.getLastCapacity(), actual.getLastCapacity());
    }
}
