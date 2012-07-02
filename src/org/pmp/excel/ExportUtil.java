/**
 * Author            : Elan
 * Created On        : 2012-4-24 下午09:58:54
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ExportUtil {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ExportUtil.class.getName());
    
    //~ Methods ========================================================================================================
    static WritableCell toCell(Object obj,int i,int j){
	if (obj == null)
	    return new Label(i,j,"null");
	if (obj instanceof String)
	    return new Label(i,j,obj.toString());
	if (obj instanceof Double)
	    return new jxl.write.Number(i,j,(Double)obj);
	if (obj instanceof Integer)
	    return new jxl.write.Number(i,j,(Integer)obj);
	if (obj instanceof Boolean)
	    return new jxl.write.Boolean(i, j, (Boolean)obj);
	if (obj instanceof Date){
	    jxl.write.DateFormat df = new jxl.write.DateFormat("yyyy-MM-dd");
	    jxl.write.WritableCellFormat wcfDF = new jxl.write.WritableCellFormat(df);
	    jxl.write.DateTime label = new jxl.write.DateTime(i, j, (Date)obj,wcfDF);
	    return label;
	}
	
	/* decide whether it is a user-defined type */
        if (obj instanceof org.pmp.vo.Owner)
            return new Label(i,j,((org.pmp.vo.Owner)obj).getOwnerName());
        if (obj instanceof org.pmp.vo.Project)
            return new Label(i,j,((org.pmp.vo.Project)obj).getProName());
        if (obj instanceof org.pmp.vo.Company)
            return new Label(i,j,((org.pmp.vo.Company)obj).getComName());
        if (obj instanceof org.pmp.vo.House)
            return new Label(i,j,((org.pmp.vo.House)obj).getHouseNum());
	
	/* if none of the above is matched,then throw an exception*/
        throw new RuntimeException("Unsupported type: " + obj.getClass().getName());
    }
    
    static void object2Excel(WritableSheet ws,int row,Object obj,String beanType,List<String> showAttr){
        String getter = null;
        String fieldType = null;
        String fieldName = null;
	try {
	    Class<?> cls = Class.forName(beanType);
	    Field[] fields = cls.getDeclaredFields();
	    int i = 0;
	    for(Field field : fields){
		/* generate the getter method of the field */
		fieldType = field.getType().toString();
		fieldName = field.getName();
		/* if the field is not in show list then continue */
		if (!showAttr.contains(fieldName))continue;
		/* if the field is in show list then add to ws */
		if (fieldType.equals("boolean")){
		    getter = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		} else {
		    getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
		Method getterMethod = cls.getMethod(getter, new Class[] {});
		Object value = getterMethod.invoke(obj, new Object[] {});
		/* create a new cell and add to ws */
		WritableCell cell = toCell(value,i++,row);
		ws.addCell(cell);
	    }
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
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public static void execute(OutputStream os,String[] header,List<?> objList,String beanType,List<String> showAttr){
        try {
	    WritableWorkbook wwb = Workbook.createWorkbook(os);
	    WritableSheet ws = wwb.createSheet("sheet1", 0);
	    /* set the header of the excel file */
	    for (int i=0;i<header.length;i++){
		WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		Label label = new Label(i,0,header[i],wcf);
		ws.addCell(label);
	    }
            /* set the data of the excel file */
	    for(int i=1;i<=objList.size();i++){
		Object obj = objList.get(i-1);
		object2Excel(ws,i,obj,beanType,showAttr);
	    }
	    /* put the data in cache to file and close cache */
	    wwb.write();
	    wwb.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * @Title: writeHead
     * @Description: set the head line of excel sheet 
     */
    public static void writeHead(WritableSheet ws,String[] header){
        for (int i=0;i<header.length;i++){
            WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            Label label = new Label(i,0,header[i],wcf);
            try {
		ws.addCell(label);
	    } catch (WriteException e) {
		e.printStackTrace();
		logger.error("write sheet head failed");
	    }
        }
    }
   
}
