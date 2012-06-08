/**
 * Author            : Elan
 * Created On        : 2012-5-6 下午01:17:15
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.pmp.validate.ResourceValidate;
import org.pmp.vo.TbResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ResourceImport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ResourceImport.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public static boolean execute(InputStream is,OutputStream os,List<TbResource> resList){
	boolean hasError = false;
	
	/* create the input workbook and sheet */
        Workbook workbook;
	try {
	    workbook = Workbook.getWorkbook(is);
	
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
                if (ResourceValidate.execute(list)){
                    hasError = true;
                } else {
	            /* if current record is right remove it from ws */
	            ws.removeRow(j-removedRows++);
	            
	            /* create and add a resource instance into resList */
                    TbResource res = new TbResource();
                    res.setResName(list.get(0).getContents().trim());
                    res.setResType(list.get(1).getContents().trim());
                    res.setResLink(list.get(2).getContents().trim());
                    res.setResDesc(list.get(3).getContents().trim());
                    res.setEnabled(Boolean.parseBoolean(list.get(4).getContents().trim()));
                    res.setIssys(Boolean.parseBoolean(list.get(5).getContents().trim()));
                    res.setModId(Integer.parseInt(list.get(6).getContents().trim()));
                    resList.add(res);
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
