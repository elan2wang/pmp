/**
 * Author            : Elan
 * Created On        : 2012-6-16 下午04:38:09
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

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyTypeAdapterFactory {
    
    //~ Instance Fields ================================================================================================
    private Includer includer;

    //~ Constructor ====================================================================================================
    public MyTypeAdapterFactory(Includer includer){
	this.includer = includer;
    }
    
    //~ Methods ========================================================================================================
    public boolean includeField(String fieldName){
	return includer.includeField(fieldName);
    }
    
    public boolean selfDefinedClass(Class<?> type){
	String packageName = type.getPackage().getName();
	if (packageName.equals("org.pmp.vo")){
	    return true;
	} else {
	    return false;
	}
    }
    
    public int getFields(Class<?> raw, Object obj, Map<String, Object> result, String fieldNamePrefix) 
          throws IllegalArgumentException, IllegalAccessException{
	if (raw.isInterface()){
	    return 0;
	}
	
	Field[] fields = raw.getDeclaredFields();
	for(Field field : fields){
	    field.setAccessible(true);
	    /* set fieldName like "id, obj.id, obj.obj.id" */
	    String fieldName = null;
	    if (fieldNamePrefix==null || fieldNamePrefix.equals("")){
		fieldName = field.getName();
	    } else {
		fieldName = fieldNamePrefix+field.getName();
	    }
	    boolean include = includeField(fieldName);
	    if (!include)continue;
	    Class<?> fieldType = field.getType();
	    if (!selfDefinedClass(fieldType)){
		result.put(fieldName, field.get(obj));
	    } else {
		Object obj2 = field.get(obj);
		getFields(fieldType, obj2, result, fieldName+".");
	    }
	}
	return result.size();
    }
    
    //~ Getters and Setters ============================================================================================

    public Includer getIncluder() {
        return includer;
    }

    public void setIncluder(Includer includer) {
        this.includer = includer;
    }

}
