/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.mgmt.data;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

/**
 * Stream management data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class StreamProcessData extends ProcessData implements CompositeDataView {

    private static final long serialVersionUID = 1L;

    private static final String[] STREAM_DATA_ITEMS;
    private static final String[] STREAM_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] STREAM_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    static {
	try {
            STREAM_DATA_ITEMS = new String[] {"id", "name", "status", "config"};
            STREAM_DATA_ITEMS_DESCRIPTION = new String[] {"Thread identifier", "Process name", "Status of the process",
                "Configuration data string"};
            STREAM_DATA_OPEN_TYPES = new OpenType<?>[] {SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING};
            DataType = new CompositeType("StreamCompositeType", "Stream data composite type", STREAM_DATA_ITEMS,
                    STREAM_DATA_ITEMS_DESCRIPTION, STREAM_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    PrintWriter pout = new PrintWriter(bout);
	    e.printStackTrace(pout);
	    pout.flush();
	    throw new RuntimeException(bout.toString());
	}
    }

    public StreamProcessData() {
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<String>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<String>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<OpenType<?>>();
            for (String item : itemNames) {
                itemDescriptions.add(ct.getDescription(item));
                itemTypes.add(ct.getType(item));
            }

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, STREAM_DATA_ITEMS, new Object[] {id, name, 
                status != null ? status.name() : "UNKNOWN", config});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
