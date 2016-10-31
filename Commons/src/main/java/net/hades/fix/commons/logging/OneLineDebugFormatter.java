/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * OneLineDebugFormatter.java
 *
 * $Id: OneLineDebugFormatter.java,v 1.3 2010-06-27 03:01:20 vrotaru Exp $
 */
package net.hades.fix.commons.logging;

import net.hades.fix.commons.exception.ExceptionUtil;

import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Formatter to be used in debuggin mode.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/06/2010
 */
public class OneLineDebugFormatter extends Formatter {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final String FORMAT = "{0,date,yyyy/MM/dd} {0,time,hh:mm:ss.SSS} [{1}] {2} {3}.{4}() - {5}{6}{7}";
    private static final String LINE_SEP = System.getProperty("line.separator");

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private Date date = new Date();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    @Override
    public String format(LogRecord record) {
        date.setTime(record.getMillis());
        String clazzName = record.getSourceClassName();
        if (clazzName == null) {
            clazzName = record.getLoggerName();
        }
        if (clazzName.lastIndexOf(".") > 0) {
            clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
        }
        String methodName = "unknown";
        if (record.getSourceMethodName() != null) {
            methodName = record.getSourceMethodName();
        }
        String exception = "";
        if (record.getThrown() != null) {
            exception = ExceptionUtil.getStackTrace(record.getThrown());
        }

        return MessageFormat.format(FORMAT, date, record.getThreadID(), record.getLevel(), clazzName, methodName,
                formatMessage(record), exception, LINE_SEP);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
