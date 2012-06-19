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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pmp.util.Pager;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class JsonFactory {
    //~ Instance Fields ================================================================================================
    private FieldsFactory fieldsFactory;
    
    //~ Constructor ====================================================================================================
    public JsonFactory(FieldsFactory fieldsFactory){
	this.fieldsFactory = fieldsFactory;
    }
    
    //~ Methods ========================================================================================================
    
    public void write(JsonWriter out, Object src) throws IOException, IllegalArgumentException, IllegalAccessException{
	writeObject(out, src);
	out.flush();
	out.close();
    }
    
    public void write(JsonWriter out, List<?> list, String title, Pager pager) throws IOException, IllegalArgumentException, IllegalAccessException{
	out.beginObject();
	out.name("title");
	out.value(title);
	out.name("page");
	out.value(pager.getCurrentPage());
	out.name("total");
	out.value(pager.getRowsCount());
	writeObjectArray(out, list);
	out.endObject();
	out.flush();
	out.close();
    }
    
    private void writeObject(JsonWriter out, Object src) throws IOException, IllegalArgumentException, IllegalAccessException{
	Map<String, Object> fieldsMap = new LinkedHashMap<String, Object>();
	Class<?> raw = src.getClass();
	fieldsFactory.getFields(raw, src, fieldsMap, "");
	out.beginObject();
	int i = 0;
	for (Entry<String,Object> entry : fieldsMap.entrySet()){
	    if (i==0){
		out.name("id");
		out.value(entry.getValue());
		out.name("cell");
		out.beginObject();
	    } else {
		out.name(entry.getKey());
		out.value(entry.getValue());
	    }
	    i++;
	}
	out.endObject();
	out.endObject();
    }
    
    private void writeObjectArray(JsonWriter out, List<?> list)  throws IOException, IllegalArgumentException, IllegalAccessException{
	if (list == null || list.size() == 0){
	    out.name("rows").nullValue();
	} else {
	    out.name("rows");
	    out.beginArray();
	    for (Object obj : list){
		writeObject(out, obj);
	    }
	    out.endArray();
	}
    }
    
    public final void toJson(Writer out, Object src) throws IOException, IllegalArgumentException, IllegalAccessException{
	JsonWriter writer = new JsonWriter(out);
	write(writer, src);
    }
    
    public final void toJson(Writer out, List<?> list, String title, Pager pager) throws IOException, IllegalArgumentException, IllegalAccessException{
	JsonWriter writer = new JsonWriter(out);
	write(writer, list, title, pager);
    }
    
    public final String toJson(Object src) throws IOException, IllegalArgumentException, IllegalAccessException{
	StringWriter stringWriter = new StringWriter();
	toJson(stringWriter, src);
	return stringWriter.toString();
    }
    
    public final String toJson(List<?> list, String title, Pager pager) throws IOException, IllegalArgumentException, IllegalAccessException{
	StringWriter stringWriter = new StringWriter();
	toJson(stringWriter, list, title, pager);
	return stringWriter.toString();
    }
    
}
