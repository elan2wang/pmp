/**
 * Author            : Elan
 * Created On        : 2012-6-18 上午09:01:55
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.json;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.gson.TypeAdapter;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ObjectTypeAdapter extends MyTypeAdapter<Object> {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ObjectTypeAdapter.class.getName());
    
    //~ Instance Fields ================================================================================================
    private final MyJson json;
    
    //~ Constructor ====================================================================================================
    private ObjectTypeAdapter(MyJson json){
	this.json = json;
    }
    
    //~ Methods ========================================================================================================
    public void write(MyJsonWriter out, Object value) throws IOException {
	if (value == null){
	    out.nullValue();
	    return;
	}
	MyTypeAdapter<Object> typeAdapter = null;
	if (typeAdapter instanceof ObjectTypeAdapter){
	    out.beginObject();
	    out.endObject();
	    return;
	}
	typeAdapter.write(out, value);
    }
    //~ Getters and Setters ============================================================================================
}
