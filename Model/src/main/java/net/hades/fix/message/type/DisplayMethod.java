/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DisplayMethod.java
 *
 * $Id: DisplayMethod.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Instructs when to display DisplayQty.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum DisplayMethod {

    @XmlEnumValue("1") Initial                             ('1'),
    @XmlEnumValue("2") New                                 ('2'),
    @XmlEnumValue("3") Random                              ('3'),
    @XmlEnumValue("4") Undisclosed                         ('4');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, DisplayMethod> stringToEnum = new HashMap<String, DisplayMethod>();

    static {
        for (DisplayMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Benchmark */
    DisplayMethod(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static DisplayMethod valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
