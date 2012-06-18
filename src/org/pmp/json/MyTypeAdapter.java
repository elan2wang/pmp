/**
 * Author            : Elan
 * Created On        : 2012-6-17 下午12:30:50
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
import java.io.StringWriter;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public abstract class MyTypeAdapter<T> {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(MyTypeAdapter.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public abstract void write(MyJsonWriter out, T value) throws IOException;
    
    public final void toJson(Writer out, T value) throws IOException{
	MyJsonWriter writer = new MyJsonWriter(out);
	write(writer, value);
    }
    
    public final String toJson(T value) throws IOException{
	StringWriter stringWriter = new StringWriter();
	toJson(stringWriter, value);
	return stringWriter.toString();
    }
}
