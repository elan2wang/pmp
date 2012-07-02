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

import com.google.gson.Gson;

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
        return bool.toString();
    }
    
    static String number2Json(Number number) {
        return number.toString();
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
        if (o instanceof org.pmp.vo.Building)
            return string2Json(((org.pmp.vo.Building)o).getBuilNum());
        if (o instanceof org.pmp.vo.House)
            return string2Json(((org.pmp.vo.House)o).getHouseNum());
        if (o instanceof org.pmp.vo.CondoFeeItem){
            org.pmp.vo.CondoFeeItem obj = (org.pmp.vo.CondoFeeItem)o;
            return string2Json(obj.getProject().getProName());
        }
	if (o instanceof org.pmp.vo.SMSCompany)
	    return string2Json(((org.pmp.vo.SMSCompany)o).getCompany().getComName());
	if (o instanceof org.pmp.vo.Zone)
	    return string2Json(((org.pmp.vo.Zone)o).getZoneName());
	if (o instanceof org.pmp.vo.TbUser)
	    return string2Json(((org.pmp.vo.TbUser)o).getRealname());
	
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
    
    static String object2FlexJson(Object obj,String beanType){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	String getter = null;
    	String fieldType = null;
    	String fieldName = null;
    	try {
    	    Class<?> cls = Class.forName(beanType);
    	    Field[] fields = cls.getDeclaredFields();
    	    
    	    //找到属性名包含Id的主键的Field
    	    Field Id_Field = getIdField(fields);
    	    if(Id_Field!=null)
    	    {
    	    	fieldType = Id_Field.getType().toString();
    	    	fieldName = Id_Field.getName();
    	    	/* if the field is in show list then append to StringBuffer */
    	    	if (fieldType.equals("boolean")){
    	    		getter = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    	    	} else {
    	    		getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    	    	}
    	    	Method getterMethod = cls.getMethod(getter, new Class[] {});
    	    	Object value = getterMethod.invoke(obj, new Object[] {});
    	    	sb.append(toJson("id")+":\""+toJson(value)+"\",");//增加"id":"idValue"
    	    }
    	    sb.append(toJson("cell")+":"+"{");//增加"id":"idValue"
    	    
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
    
    //找到属性名包含Id的主键的Field
    static Field getIdField(Field[] fields)
    {
    	String getter = null;
    	String fieldType = null;
    	String fieldName = null;
    	boolean idtrue=false;
    	Field Id=null;
    	try {
    		for(Field field : fields){
    			/* generate the getter method of the field */
    			fieldType = field.getType().toString();
    			fieldName = field.getName();
    			if (fieldName.contains("Id"))
    			{ 
    				Id = field;
    				idtrue=true;
    				break; 				
    			}    			
    		}
    		 //判断是否找到了包含Id的主键属性
		    if(!idtrue)
		    {
		    	logger.error(" class not a fieldname contains \'Id\'");
		    }
		    return Id;
    	}
    	catch(Exception e)
    	{
    		logger.error(" error:",e);
    		return Id;
    		
    	}
	   
    }

    //返回{"id":"1","cell":{"realname":"aw","position":"qteacher","userDesc":"userDesc"}   
    //主键的判断：fieledname中含有”Id”的fieledname为主键属性
    static String object2FlexJson(Object obj,String beanType,List<String> show){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	String getter = null;
    	String fieldType = null;
    	String fieldName = null;
    	try {
    	    Class<?> cls = Class.forName(beanType);
    	    
    	    Field[] fields = cls.getDeclaredFields();
    	    //找到属性名包含Id的主键的Field
    	    Field Id_Field = getIdField(fields);
    	    if(Id_Field!=null)
    	    {
    	    	fieldType = Id_Field.getType().toString();
    	    	fieldName = Id_Field.getName();
    	    	/* if the field is in show list then append to StringBuffer */
    	    	if (fieldType.equals("boolean")){
    	    		getter = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    	    	} else {
    	    		getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    	    	}
    	    	Method getterMethod = cls.getMethod(getter, new Class[] {});
    	    	Object value = getterMethod.invoke(obj, new Object[] {});
    	    	sb.append(toJson("id")+":\""+toJson(value)+"\",");//增加"id":"idValue"
    	    }
    	    sb.append(toJson("cell")+":"+"{");//增加"cell"
    	    for(Field field2 : fields){
    	    	/* generate the getter method of the field */
    	    	fieldType = field2.getType().toString();
    	    	fieldName = field2.getName();
    	    	/* if the field is not in show list then continue */
    	    	if (!show.contains(fieldName))continue;
    		
    	    	/* if the field is in show list then append to StringBuffer 
    	    	 * 如果fieldType不是基本的包装类，而是比如Building这样的class时*/
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
	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
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
	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}\n");
	
	return sb.toString();
    }
    
    
    public static String list2FlexJson(Pager pager,List<?> list,String beanType){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{\n");
    	sb.append("  "+toJson("page")+":\""+toJson(pager.getCurrentPage())+"\",\n");
    	sb.append("  "+toJson("total")+":"+toJson(pager.getRowsCount())+",\n");
    	sb.append("  "+toJson("rows")+":[\n");
    	
    	Iterator<?> ite = list.iterator();
    	while(ite.hasNext()){
    	    Object obj = ite.next();
    	    sb.append("    "+object2FlexJson(obj,beanType)+"},\n");
    	}
    	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
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
	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
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
	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}\n");
	
	return sb.toString();
    }
    
    public static String list2FlexJson(Pager pager,List<?> list,String beanType,List<String> show){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{\n");
    	sb.append("  "+toJson("page")+":\""+toJson(pager.getCurrentPage())+"\",\n");
    	sb.append("  "+toJson("total")+":"+toJson(pager.getRowsCount())+",\n");
    	sb.append("  "+toJson("rows")+":[\n");
    	
    	Iterator<?> ite = list.iterator();
    	while(ite.hasNext()){
    	    Object obj = ite.next();
    	    sb.append("    "+object2FlexJson(obj,beanType,show)+"},\n");
    	}
    	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
    	sb.append("  ]\n}\n");
    	
    	return sb.toString();
        }
    
    public static String list2Json(List<?> list,String beanType,List<String> show,String arrayName){
	StringBuffer sb = new StringBuffer();
	sb.append("  "+toJson(arrayName)+":[\n");
	Iterator<?> ite = list.iterator();
	while(ite.hasNext()){
	    Object obj = ite.next();
	    sb.append("    "+object2Json(obj,beanType,show)+",\n");
	}
	if(list.size()!=0)sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n");
	return sb.toString();
    }
    
    public static String merge(String[] data){
	StringBuffer sb = new StringBuffer();
	sb.append("{\n");
	for(int i=0;i<data.length;i++){
	    sb.append(data[i]);
	    sb.deleteCharAt(sb.length()-1);
	    sb.append(",\n");
	}
	sb.deleteCharAt(sb.length()-2);
	sb.append("}\n");
	return sb.toString();
    }

    public static String toJsonTreeNode(Integer id, Integer pid, String name, String url, String title, 
	    String target, String icon, String iconOpen, Boolean open ){
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	sb.append(toJson("id")+":"+toJson(id)+",");
	sb.append(toJson("pid")+":"+toJson(pid)+",");
	sb.append(toJson("name")+":"+toJson(name)+",");
	sb.append(toJson("url")+":"+toJson(url)+",");
	sb.append(toJson("title")+":"+toJson(title)+",");
	sb.append(toJson("target")+":"+toJson(target)+",");
	sb.append(toJson("icon")+":"+toJson(icon)+",");
	sb.append(toJson("iconOpen")+":"+toJson(iconOpen)+",");
	sb.append(toJson("open")+":"+toJson(open)+",");
	sb.append("}");
	
	return sb.toString();
    }
    
    public static String toJsonFireInfos(int zoneId,String zoneName,String deviceNumber,String deviceType,Date receiveTime,String receiveInfo){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	sb.append(toJson("zoneId")+":"+toJson(zoneId)+",");
    	sb.append(toJson("zoneName")+":"+toJson(zoneName)+",");
    	sb.append(toJson("deviceNumber")+":"+toJson(deviceNumber)+",");
    	sb.append(toJson("deviceType")+":"+toJson(deviceType)+",");
    	sb.append(toJson("receiveTime")+":\""+receiveTime.toString()+"\",");
    	sb.append(toJson("receiveInfo")+":"+toJson(receiveInfo)+",");
    	sb.append("}");
    	return sb.toString();
    }

    public static String toJsonZones(int zoneId,String zoneName,String zoneImgUrl,String zoneConfigUrl){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");
    	sb.append(toJson("zoneId")+":"+toJson(zoneId)+",");
    	sb.append(toJson("zoneName")+":"+toJson(zoneName)+",");
    	sb.append(toJson("zoneImgUrl")+":"+toJson(zoneImgUrl)+",");
    	sb.append(toJson("zoneConfigUrl")+":"+toJson(zoneConfigUrl)+",");
    	sb.append("}");
    	return sb.toString();
    }
    
    public static String toJsonZoneList(List<String> nodes){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{\n  "+toJson("zones")+":[\n");
    	for (int i=0;i<nodes.size();i++){
    	    sb.append("    "+nodes.get(i)+",\n");
    	}
    	sb.deleteCharAt(sb.length()-2);
    	sb.append("  ]\n}");
    	return sb.toString().replaceAll(",}", "}");
    }
    
    public static String toJsonFireInfoList(List<String> callNodes,List<String> warnNodes){
    	StringBuffer sb = new StringBuffer();
    	sb.append("{\n  "+toJson("callFireInfos")+":[\n");
    	
    	for (int i=0;i<callNodes.size();i++){
    	    if(i==callNodes.size()-1){
    	    	sb.append("    "+callNodes.get(i)+"\n");
    	    	break;
    	    }
    	    sb.append("    "+callNodes.get(i)+",\n");
    	}
    	
    	sb.append("]\n");
    	
    	sb.append(",\n"+toJson("warnFireInfos")+":[\n");
    	for (int i=0;i<warnNodes.size();i++){
    	    if(i==warnNodes.size()-1){
    	    	sb.append("    "+warnNodes.get(i)+"\n");
    	    	break;
    	    }
    	    sb.append("    "+warnNodes.get(i)+",\n");
    	}
    	sb.append("]\n");
    	sb.append("}");
    	return sb.toString().replaceAll(",}", "}");
    }
    
    public static String toJsonTree(List<String> nodes){
	StringBuffer sb = new StringBuffer();
	sb.append("{\n  "+toJson("Nodes")+":[\n");
	for (int i=0;i<nodes.size();i++){
	    sb.append("    "+nodes.get(i)+",\n");
	}
	sb.deleteCharAt(sb.length()-2);
	sb.append("  ]\n}");
	return sb.toString().replaceAll(",}", "}");
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
