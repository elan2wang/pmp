/**
 * Author            : Elan
 * Created On        : 2012-4-28 下午12:07:40
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.pmp.validate.OwnerValidate;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerImport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerImport.class.getName());

    //~ Methods ========================================================================================================
    public static boolean execute(InputStream is,OutputStream os,List<Owner> ownerList){
	boolean hasError = false;
	try {
	    /* create the input workbook and sheet */
	    Workbook workbook = Workbook.getWorkbook(is);
	    Sheet sheet = workbook.getSheet(0);
	    /* ceate the error data workbook and sheet */
	    WorkbookSettings settings = new WorkbookSettings ();
	    settings.setWriteAccess(null);
	    WritableWorkbook wwb = Workbook.createWorkbook(os,workbook,settings);
	    WritableSheet ws = wwb.getSheet(0);
	    ws.setName("errorSheet");
	    /* create a variable to count the removed rows */
	    Integer removedRows = 0;
	  
            Cell cell = null;
            for (int j = 1; j < sheet.getRows(); j++) {
                List<Cell> list = new ArrayList<Cell>();
                for (int i = 0; i < sheet.getColumns(); i++) {
                    cell = sheet.getCell(i, j);
                    list.add(cell);
                }
	        if(!OwnerValidate.isRight(list)){
	            hasError = true;
	        } else {
	            /* if current record is right remove it from ws */
	            ws.removeRow(j-removedRows++);
	            
	            /* create a owner instance */
	            Owner owner = new Owner();
	            /* set its properties, list(0)-list(6) are required field */
	            owner.setHouseNum(list.get(3).getContents().trim());
	            owner.setHouseArea(Double.parseDouble(list.get(4).getContents().trim()));
	            owner.setOwnerName(list.get(5).getContents().trim());
	            owner.setMobile(list.get(6).getContents().trim());
	            /* ownerDesc必须设置为物业项目名称  */
	            owner.setOwnerDesc(list.get(1).getContents().trim());
	            
	            /* set other none required field */
	            
	            if(!list.get(7).getContents().trim().equals(""))owner.setHomePhone(list.get(7).getContents().trim());
	            if(!list.get(8).getContents().trim().equals(""))owner.setOrganization(list.get(8).getContents().trim());
	            if(!list.get(9).getContents().trim().equals(""))owner.setUseStyle(list.get(9).getContents().trim());
	            if(!list.get(10).getContents().trim().equals(""))owner.setStoreroom(list.get(10).getContents().trim());
	            if(!list.get(11).getContents().trim().equals(""))owner.setParkNum(list.get(11).getContents().trim());
	            if(!list.get(12).getContents().trim().equals(""))owner.setCarNum(list.get(12).getContents().trim());
	            if(!list.get(13).getContents().trim().equals(""))owner.setCarType(list.get(13).getContents().trim());
	            if(!list.get(14).getContents().trim().equals(""))owner.setNative_(list.get(14).getContents().trim());
	            if(!list.get(15).getContents().trim().equals(""))owner.setNationality(list.get(15).getContents().trim());
	            if(!list.get(16).getContents().trim().equals(""))owner.setGender(list.get(16).getContents().trim());
	            if(!list.get(17).getContents().trim().equals("")){
	        	owner.setIsmarried(Boolean.parseBoolean(list.get(17).getContents().trim()));
	            } else {
	        	owner.setIsmarried(false);
	            }
	            if(!list.get(18).getContents().trim().equals(""))owner.setBirthday(((DateCell)list.get(18)).getDate());
	            if(!list.get(19).getContents().trim().equals(""))owner.setIdentityType(list.get(19).getContents().trim());
	            if(!list.get(20).getContents().trim().equals(""))owner.setIdentityCode(list.get(20).getContents().trim());
	            if(!list.get(21).getContents().trim().equals(""))owner.setGetTime(((DateCell)list.get(21)).getDate());
	            if(!list.get(22).getContents().trim().equals(""))owner.setDecorateTime(((DateCell)list.get(22)).getDate());
	            if(!list.get(23).getContents().trim().equals(""))owner.setInTime(((DateCell)list.get(23)).getDate());
	            if(!list.get(24).getContents().trim().equals(""))owner.setOtherAddress(list.get(24).getContents().trim());
	            if(!list.get(25).getContents().trim().equals(""))owner.setOtherPostcode(list.get(25).getContents().trim());
	            if(!list.get(26).getContents().trim().equals(""))owner.setEmergencyContact(list.get(26).getContents().trim());
	            if(!list.get(27).getContents().trim().equals(""))owner.setEmergencyPhone(list.get(27).getContents().trim());
	            if(!list.get(28).getContents().trim().equals(""))owner.setHobby(list.get(28).getContents().trim());
	            if(!list.get(29).getContents().trim().equals(""))owner.setOtherInfo(list.get(29).getContents().trim());
	            /* add the instance into ownerList */
	            ownerList.add(owner);
	            
	        }
	    }
            wwb.write();
            wwb.close();
	} catch (IOException e) {
	    logger.error("get inputStream failed");
	    e.printStackTrace();
	} catch (BiffException e) {
	    logger.error("BiffException");
	    e.printStackTrace();
	} catch (WriteException e) {
	    logger.error("close WritableWorkbook failed");
	    e.printStackTrace();
	} catch (Exception e){
	    logger.error("exception:"+e.getMessage());
	    e.printStackTrace();
	}
	
	return hasError;
    }
    //~ Getters and Setters ============================================================================================

}
