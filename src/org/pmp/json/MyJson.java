/**
 * Author            : Elan
 * Created On        : 2012-6-17 下午12:25:45
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


import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyJson {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(MyJson.class.getName());
    //~ Instance Fields ================================================================================================
    private final MyTypeAdapterFactory factory;
    private final Includer includer;
    
    //~ Constructor ====================================================================================================
    public MyJson(){
	this.includer = Includer.DEFAULT;
	this.factory = new MyTypeAdapterFactory(this.includer); 
    }
    
    public MyJson(Includer includer, MyTypeAdapterFactory factory){
	this.includer = includer;
	this.factory = factory;
    }
    
    //~ Methods ========================================================================================================
    public String toJson(Object src){
	Class<?> raw = src.getClass();
	return toJson(src, raw);
    }
    
    public String toJson(Object src, Class<?> typeOfSrc){
	StringWriter writer = new StringWriter();
	toJson(src, src.getClass(), writer);
	return writer.toString();
    }
    
    public void toJson(Object src, Class<?> typeOfSrc, Appendable writer){
	
    }
    
    public void toJson(Object src, Class<?> typeOfSrc, MyJsonWriter writer){
	
    }
    //~ Getters and Setters ============================================================================================

}
