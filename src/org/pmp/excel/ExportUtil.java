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
	    return new Label(i,j,"");
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
	
	/* if none of the above is matched,then throw an exception*/
        throw new RuntimeException("Unsupported type: " + obj.getClass().getName());
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
