/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStrikePriceMsg44TestData.java
 *
 * $Id: ListStrikePriceMsg44TestData.java,v 1.1 2011-04-15 04:37:42 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.data;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import net.hades.fix.TestUtils;
import net.hades.fix.message.MsgTest;
import net.hades.fix.message.ListStrikePriceMsg;
import net.hades.fix.message.group.impl.v44.InstrmtStrikePriceGroup44TestData;

/**
 * Test utility for ListStrikePriceMsg44 message class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/05/2009, 12:08:30 PM
 */
public class ListStrikePriceMsg44TestData extends MsgTest {

    private static final ListStrikePriceMsg44TestData INSTANCE;

    static {
        INSTANCE = new ListStrikePriceMsg44TestData();
    }

    public static ListStrikePriceMsg44TestData getInstance() {
        return INSTANCE;
    }

    public void populate(ListStrikePriceMsg msg) throws UnsupportedEncodingException {
        TestUtils.populate44HeaderAll(msg);
        msg.setListID("LST564567");
        msg.setTotNoStrikes(3);
        msg.setLastFragment(Boolean.FALSE);

        msg.setNoStrikes(2);
        InstrmtStrikePriceGroup44TestData.getInstance().populate1(msg.getInstrmtStrikePriceGroups()[0]);
        InstrmtStrikePriceGroup44TestData.getInstance().populate2(msg.getInstrmtStrikePriceGroups()[1]);
    }

    public void check(ListStrikePriceMsg expected, ListStrikePriceMsg actual) throws Exception {
        assertEquals(expected.getListID(), actual.getListID());
        assertEquals(expected.getTotNoStrikes(), actual.getTotNoStrikes());
        assertEquals(expected.getLastFragment(), actual.getLastFragment());

        assertEquals(expected.getNoStrikes(), actual.getNoStrikes());
        InstrmtStrikePriceGroup44TestData.getInstance().check(expected.getInstrmtStrikePriceGroups()[0], actual.getInstrmtStrikePriceGroups()[0]);
        InstrmtStrikePriceGroup44TestData.getInstance().check(expected.getInstrmtStrikePriceGroups()[1], actual.getInstrmtStrikePriceGroups()[1]);
    }
}
