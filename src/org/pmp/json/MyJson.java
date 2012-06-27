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


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.util.Pager;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyJson {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(MyJson.class.getName());
    
    //~ Instance Fields ================================================================================================
    private final FieldsFactory fieldsfactory;
    private final JsonFactory jsonFactory;
    
    private Includer includer;
    
    //~ Constructor ====================================================================================================
    public MyJson(){
	this.includer = Includer.DEFAULT;
	this.fieldsfactory = new FieldsFactory(this.includer); 
	this.jsonFactory = new JsonFactory(this.fieldsfactory);
    }
    
    public MyJson(Includer includer){
	this.includer = includer;
	this.fieldsfactory = new FieldsFactory(this.includer); 
	this.jsonFactory = new JsonFactory(this.fieldsfactory);
    }
    
    public MyJson(Includer includer, FieldsFactory fieldsFactory, Pager pager, JsonFactory jsonFactory){
	this.includer = includer;
	this.fieldsfactory = fieldsFactory;
	this.jsonFactory = jsonFactory;
    }
    
    //~ Methods ========================================================================================================
    public String toJson(Object src) {
	try {
	    return jsonFactory.toJson(src);
	} catch (IllegalArgumentException e) {
	    logger.error("IllegalArgumentException");
	    e.printStackTrace();
	} catch (IOException e) {
	    logger.error("IOException");
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    logger.error("IllegalAccessException");
	    e.printStackTrace();
	}
	return null;
    }
    
    public String toJson(List<?> list, String title, Pager pager) {
	try {
	    return jsonFactory.toJson(list, title, pager);
	} catch (IllegalArgumentException e) {
	    logger.error("IllegalArgumentException");
	    e.printStackTrace();
	} catch (IOException e) {
	    logger.error("IOException");
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    logger.error("IllegalAccessException");
	    e.printStackTrace();
	}
	return null;
    }
    
    public String toJson(Map<String,Object> params){
	try {
	    return jsonFactory.toJson(params);
	} catch (IllegalArgumentException e) {
	    logger.error("IllegalArgumentException");
	    e.printStackTrace();
	} catch (IOException e) {
	    logger.error("IOException");
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    logger.error("IllegalAccessException");
	    e.printStackTrace();
	}
	return null;
    }
    
    public void output(String data){
	try {    
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(data);
        } catch (IOException e) {                     
            e.printStackTrace();  
        } 
    }
    
    public static void print(String data){
	try {    
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(data);
        } catch (IOException e) {                     
            e.printStackTrace();  
        } 
    }
    //~ Getters and Setters ============================================================================================
    public Includer getIncluder() {
        return includer;
    }

    public void setIncluder(Includer includer) {
        this.includer = includer;
    }

    public FieldsFactory getFieldsfactory() {
        return fieldsfactory;
    }

    public JsonFactory getJsonFactory() {
	return jsonFactory;
    }

}
