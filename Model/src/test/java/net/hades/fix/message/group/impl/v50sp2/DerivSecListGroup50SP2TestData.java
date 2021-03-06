/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivSecListGroup50SP2TestData.java
 *
 * $Id: DerivSecListGroup50SP2TestData.java,v 1.2 2011-10-29 09:42:22 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.impl.v44.InstrumentExtension44TestData;
import net.hades.fix.message.comp.impl.v50sp1.SecondaryPriceLimits50SP1TestData;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2TestData;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.type.Currency;

/**
 * Test utility for DerivSecListGroup group class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/04/2009, 10:08:38 AM
 */
public class DerivSecListGroup50SP2TestData extends MsgTest {

    private static final DerivSecListGroup50SP2TestData INSTANCE;

    static {
        INSTANCE = new DerivSecListGroup50SP2TestData();
    }

    public static DerivSecListGroup50SP2TestData getInstance() {
        return INSTANCE;
    }

    public void populate1(DerivSecListGroup grp) {
        Calendar cal = Calendar.getInstance();
        
        grp.setInstrument();
        Instrument50SP2TestData.getInstance().populate(grp.getInstrument());
        
        grp.setSecondaryPriceLimits();
        SecondaryPriceLimits50SP1TestData.getInstance().populate(grp.getSecondaryPriceLimits());
        
        grp.setCurrency(Currency.AustralianDollar);
        grp.setCorporateAction("A");
        
        grp.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(grp.getInstrumentExtension());

        grp.setNoLegs(2);
        InstrmtLegDerivSecListGroup50SP2TestData.getInstance().populate1(grp.getInstrmtLegDerivSecListGroups()[0]);
        InstrmtLegDerivSecListGroup50SP2TestData.getInstance().populate2(grp.getInstrmtLegDerivSecListGroups()[1]);

        cal.set(2011, 6, 11, 12, 35, 45);
        grp.setRelSymTransactTime(cal.getTime());
        grp.setText("Some text 1");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 13, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void populate2(DerivSecListGroup grp) throws UnsupportedEncodingException {
        Calendar cal = Calendar.getInstance();
        
        grp.setInstrument();
        Instrument50SP2TestData.getInstance().populate(grp.getInstrument());
        
        grp.setSecondaryPriceLimits();
        SecondaryPriceLimits50SP1TestData.getInstance().populate(grp.getSecondaryPriceLimits());

        grp.setCurrency(Currency.UnitedStatesDollar);
        grp.setCorporateAction("B");
        
        grp.setInstrumentExtension();
        InstrumentExtension44TestData.getInstance().populate(grp.getInstrumentExtension());

        grp.setNoLegs(2);
        InstrmtLegDerivSecListGroup50SP2TestData.getInstance().populate1(grp.getInstrmtLegDerivSecListGroups()[0]);
        InstrmtLegDerivSecListGroup50SP2TestData.getInstance().populate2(grp.getInstrmtLegDerivSecListGroups()[1]);

        cal.set(2011, 2, 11, 12, 24, 45);
        grp.setRelSymTransactTime(cal.getTime());
        grp.setText("Some text 2");
        grp.setEncodedTextLen(new Integer(8));
        byte[] encText = new byte[] {(byte) 16, (byte) 33, (byte) 44, (byte) 96,
            (byte) 177, (byte) 199, (byte) 223, (byte) 253};
        grp.setEncodedText(encText);
    }

    public void check(DerivSecListGroup expected, DerivSecListGroup actual) {
        // Instrument
        Instrument50SP2TestData.getInstance().check(expected.getInstrument(), actual.getInstrument());
        
        SecondaryPriceLimits50SP1TestData.getInstance().check(expected.getSecondaryPriceLimits(), actual.getSecondaryPriceLimits());

        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getCorporateAction(), actual.getCorporateAction());
        
        InstrumentExtension44TestData.getInstance().check(expected.getInstrumentExtension(), actual.getInstrumentExtension());

        assertEquals(expected.getNoLegs(), actual.getNoLegs());
        InstrmtLegDerivSecListGroup50SP2TestData.getInstance().check(expected.getInstrmtLegDerivSecListGroups()[0], actual.getInstrmtLegDerivSecListGroups()[0]);
        InstrmtLegDerivSecListGroup50SP2TestData.getInstance().check(expected.getInstrmtLegDerivSecListGroups()[1], actual.getInstrmtLegDerivSecListGroups()[1]);

        assertUTCTimestampEquals(expected.getRelSymTransactTime(), actual.getRelSymTransactTime(), false);
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getEncodedTextLen(), actual.getEncodedTextLen());
        assertArrayEquals(expected.getEncodedText(), actual.getEncodedText());
    }
}
