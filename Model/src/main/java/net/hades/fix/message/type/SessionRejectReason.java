/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Method of encryption.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public enum SessionRejectReason {

    InvalidTagNumber                        (0),
    RequiredTagMissing                      (1),          
    TagNotDefinedForMessageType             (2),           
    UndefinedTag                            (3),          
    TagWithoutValue                         (4),        
    ValueOuOfRange                          (5),
    IncorrectDataFormat                     (6),
    DecryptionProblem                       (7),        
    SignatureProblem                        (8),
    CompIDProblem                           (9),
    SendingTimeAccuracyProblem              (10),        
    InvalidMessageType                      (11),
    XMLValidationError                      (12),
    TagAppearsMoreThanOnce                  (13),        
    TagOutOfRequiredOrder                   (14),
    RepeatingGroupOuOfOrder                 (15),
    IncorrectCountForGroups                 (16),        
    NonDataContainsSOH                      (17),
    Other                                   (99);

    private static final long serialVersionUID = -6833915870878139496L;
    
    private final int value;

    private static final Map<String, SessionRejectReason> stringToEnum = new HashMap<>();

    static {
        for (SessionRejectReason tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SessionRejectReason */
    SessionRejectReason(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static SessionRejectReason valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
