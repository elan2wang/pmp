/**
 * Author            : Elan
 * Created On        : 2012-4-25 下午02:58:25
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyfileUtil {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(MyfileUtil.class.getName());
    
    //~ Methods ========================================================================================================
    public static boolean validate(String filename,String filetype){
	String postfix = getPostfix(filename);
	return filetype.equals(postfix) ? true : false;
    }
    
    public static boolean validate(String filename){
	List<String> filetypes = new ArrayList<String>();
	filetypes.add("xls");
	filetypes.add("doc");
	filetypes.add("ppt");
	filetypes.add("xlsx");
	filetypes.add("docx");
	filetypes.add("pptx");
	filetypes.add("rar");
	filetypes.add("zip");
	filetypes.add("txt");
	filetypes.add("pdf");
	
	String postfix = getPostfix(filename);
	return filetypes.contains(postfix) ? true : false;
    }
    
    public static String getPostfix(String filename){
	return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }
    
    public static String createFilename(){
	StringBuffer sb = new StringBuffer();
	String now = (new Timestamp(System.currentTimeMillis())).toString();
	sb.append(now.replace("-", "").replace(":", "").replace(" ", "").replace(".", ""));
	return sb.toString();
    }
    
    
}
