/**
 * Author            : Elan
 * Created On        : 2012-4-25 下午02:58:25
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyfileUtil {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(MyfileUtil.class.getName());
    static final int BUFFER_SIZE = 16 * 1024 ;
    
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
	filetypes.add("jpg");
	filetypes.add("gif");
	
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
    
    public static String createDir(String path){
	try {
	    String dirPath = ServletActionContext.getServletContext().getRealPath(path);
	    File dirFile = new File(dirPath);
	    Boolean isExist = dirFile.exists();
	    if (isExist){
		logger.debug("this dir already exist");
	    } else {
		dirFile.mkdir();
	    }
	    return dirPath;
	} catch (Exception err){
	    logger.error("create dir failed");
	    err.printStackTrace();
	    return null;
	}
    }
    
    public static boolean deleteFileOrDirectory(String path){
	boolean b=false;
    	try {
    	    if(path!=null){
    		File f=new File(path);
    		if(f.exists()){
    		    b=f.delete();
    		}	
    	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return b;
    }
    
    public static String fileUpload(File src,String filename,String dir) throws FileNotFoundException{
	String newFilename = createFilename()+"."+getPostfix(filename);
	String realdir = createDir(dir);
	FileOutputStream dst = new FileOutputStream(realdir+File.separator+newFilename);
	copy(src,dst);
	return ServletActionContext.getServletContext().getContextPath()+"/"+dir+"/"+newFilename;
    }
    
    private static void copy(File src,FileOutputStream dst){
	try {
	    InputStream in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
	    OutputStream out = new BufferedOutputStream(dst,BUFFER_SIZE);
	    byte[] buffer = new byte[BUFFER_SIZE];
	    while(in.read(buffer)>0){
		out.write(buffer);
	     }
	    
	    if(null!=in)in.close();
	    if(null!=out)out.close();
	} catch (FileNotFoundException e) {
	    logger.error("file not found exception");
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
