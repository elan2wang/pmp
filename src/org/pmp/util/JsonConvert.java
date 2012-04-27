/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午05:23:15
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * @author Elan
 * @version 1.0
 * @update 2012-3-22 上午10:25:00 添加output2Jason方法
 */
public class JsonConvert {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(JsonConvert.class.getName ());
   
    //~ Methods ========================================================================================================
    static String string2Json(String s) {
	StringBuilder sb = new StringBuilder(s.length()+20);
        sb.append('\"');
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                sb.append("\\\"");
                break;
                case '\\':
                sb.append("\\\\");
                break;
                case '/':
                sb.append("\\/");
                break;
                case '\b':
                sb.append("\\b");
                break;
                case '\f':
                sb.append("\\f");
                break;
                case '\n':
                sb.append("\\n");
                break;
                case '\r':
                sb.append("\\r");
                break;
                case '\t':
                sb.append("\\t");
                break;
                default:
                sb.append(c);
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    static String boolean2Json(Boolean bool) {
        return string2Json(bool.toString());
    }
    
    static String number2Json(Number number) {
        return string2Json(number.toString());
    }
    
    static String date2Json(Date date) {
	return string2Json(date.toString().substring(0, 10));
    }
    
    public static String toJson(Object o) {
        if (o==null)
            return string2Json("null");
        if (o instanceof String)
            return string2Json((String)o);
        if (o instanceof Boolean)
            return boolean2Json((Boolean)o);
        if (o instanceof Number)
            return number2Json((Number)o);
        if (o instanceof Date)
            return date2Json((Date)o);
        
        /* decide whether it is a user-defined type */
        if (o instanceof org.pmp.vo.Owner)
            return string2Json(((org.pmp.vo.Owner)o).getOwnerName());
        if (o instanceof org.pmp.vo.Project)
            return string2Json(((org.pmp.vo.Project)o).getProName());
        if (o instanceof org.pmp.vo.Company)
            return string2Json(((org.pmp.vo.Company)o).getComName());
        if (o instanceof org.pmp.vo.House)
            return string2Json(((org.pmp.vo.House)o).getHouseNum());
        if (o instanceof org.pmp.vo.CondoFeeItem)
            return string2Json(((org.pmp.vo.CondoFeeItem)o).getItemName());
	if (o instanceof org.pmp.vo.FixedParkFeeItem)
	    return string2Json(((org.pmp.vo.FixedParkFeeItem)o).getItemName());
	
	/* if none of the above is matched,then throw an exception*/
        throw new RuntimeException("Unsupported type: " + o.getClass().getName());
    }

    static String object2Json(Object obj,String beanType){
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	String getter = null;
	String fieldType = null;
	String fieldName = null;
	try {
	    Class<?> cls = Class.forName(beanType);
	    Field[] fields = cls.getDeclaredFields();
	    for(Field field : fields){
		/* generate the getter method of the field */
		fieldType = field.getType().toString();
		fieldName = field.getName();
		if (fieldType.equals("boolean")){
		    getter = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		} else {
		    getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
		Method getterMethod = cls.getMethod(getter, new Class[] {});
		Object value = getterMethod.invoke(obj, new Object[] {});
		sb.append(toJson(fieldName)+":"+toJson(value)+",");
	    }
	    sb.setCharAt(sb.length()-1, '}');
	    
	} catch (ClassNotFoundException e) {
	    logger.error(beanType+" class not found",e);
	} catch (NoSuchMethodException e) {
	    logger.error(getter+" method not found",e);
	} catch (IllegalArgumentException e) {
	    logger.error(getter+" IllegalArgumentException",e);
	} catch (IllegalAccessException e) {
	    logger.error(getter+" IllegalAccessException",e);
	} catch (InvocationTargetException e) {
	    logger.error(getter+" IllegalAccessException",e);
	}
	
	return sb.toString();
    }
    
    static String object2Json(Object obj,String beanType,List<String> show){
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	String getter = null;
	String fieldType = null;
	String fieldName = null;
	try {
	    Class<?> cls = Class.forName(beanType);
	    Field[] fields = cls.getDeclaredFields();
	    for(Field field : fields){
		/* generate the getter method of the field */
		fieldType = field.getType().toString();
		fieldName = field.getName();
		/* if the field is not in show list then continue */
		if (!show.contains(fieldName))continue;
		
		/* if the field is in show list then append to StringBuffer */
		if (fieldType.equals("boolean")){
		    getter = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		} else {
		    getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
		Method getterMethod = cls.getMethod(getter, new Class[] {});
		Object value = getterMethod.invoke(obj, new Object[] {});
		sb.append(toJson(fieldName)+":"+toJson(value)+",");
	    }
	    sb.setCharAt(sb.length()-1, '}');
	    
	} catch (ClassNotFoundException e) {
	    logger.error(beanType+" class not found",e);
	} catch (NoSuchMethodException e) {
	    logger.error(getter+" method not found",e);
	} catch (IllegalArgumentException e) {
	    logger.error(getter+" IllegalArgumentException",e);
	} catch (IllegalAccessException e) {
	    logger.error(getter+" IllegalAccessException",e);
	} catch (InvocationTargetException e) {
	    logger.error(getter+" IllegalAccessException",e);
	}
	
	return sb.toString();
    }
    
    public static String list2Json(List<?> list,String beanType){
	StringBuffer sb = new StringBuffer();
	sb.append("{\n  "+toJson("Rows")+":[\n");
	Iterator<?> ite = list.iterator();
	while(ite.hasNext()){
	    Object obj = ite.next();
	    sb.append("    "+object2Json(obj,beanType)+",\n");
	}
	sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}\n");
	return sb.toString();
    }
    
    public static String list2Json(Pager pager,List<?> list,String beanType){
	StringBuffer sb = new StringBuffer();
	sb.append("{\n");
	sb.append("  "+toJson("RowsCount")+":"+toJson(pager.getRowsCount())+",\n");
	sb.append("  "+toJson("PageSize")+":"+toJson(pager.getPageSize())+",\n");
	sb.append("  "+toJson("CurrentPage")+":"+toJson(pager.getCurrentPage())+",\n");
	sb.append("  "+toJson("PagesCount")+":"+toJson(pager.getPagesCount())+",\n");
	sb.append("  "+toJson("Rows")+":[\n");
	
	Iterator<?> ite = list.iterator();
	while(ite.hasNext()){
	    Object obj = ite.next();
	    sb.append("    "+object2Json(obj,beanType)+",\n");
	}
	sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}\n");
	
	return sb.toString();
    }
    
    public static String list2Json(List<?> list,String beanType,List<String> show){
	StringBuffer sb = new StringBuffer();
	sb.append("{\n  "+toJson("Rows")+":[\n");
	Iterator<?> ite = list.iterator();
	while(ite.hasNext()){
	    Object obj = ite.next();
	    sb.append("    "+object2Json(obj,beanType,show)+",\n");
	}
	sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}\n");
	return sb.toString();
    }
    
    public static String list2Json(Pager pager,List<?> list,String beanType,List<String> show){
	StringBuffer sb = new StringBuffer();
	sb.append("{\n");
	sb.append("  "+toJson("RowsCount")+":"+toJson(pager.getRowsCount())+",\n");
	sb.append("  "+toJson("PageSize")+":"+toJson(pager.getPageSize())+",\n");
	sb.append("  "+toJson("CurrentPage")+":"+toJson(pager.getCurrentPage())+",\n");
	sb.append("  "+toJson("PagesCount")+":"+toJson(pager.getPagesCount())+",\n");
	sb.append("  "+toJson("Rows")+":[\n");
	
	Iterator<?> ite = list.iterator();
	while(ite.hasNext()){
	    Object obj = ite.next();
	    sb.append("    "+object2Json(obj,beanType,show)+",\n");
	}
	sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}\n");
	
	return sb.toString();
    }
    
    public static void output(String data){
	try {    
            HttpServletResponse response = ServletActionContext.getResponse();  
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(data);
        } catch (IOException e) {                     
            e.printStackTrace();  
        } 
    }

}
