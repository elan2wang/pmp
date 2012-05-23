package org.pmp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

public class FileUploadUtil {
   
   public static String fileUpload(File uploadFile,String uploadFileName,String toUploadDir) throws IOException{
	    	
		String uploadDir=MyfileUtil.createDir(toUploadDir);
		
		FileInputStream fis = new FileInputStream(uploadFile);
		
		uploadFileName=MyfileUtil.createFilename()+"_"+uploadFileName;
		
		FileOutputStream fos = new FileOutputStream(uploadDir+File.separator+uploadFileName);
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=fis.read(buffer))!=-1){
			fos.write(buffer, 0, len);
		}
		fos.close();
		fis.close();
		
		return toUploadDir+"/"+uploadFileName;
   }
   
}
