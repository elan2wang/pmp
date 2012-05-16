/**
 * Author            : Elan
 * Created On        : 2012-5-10 下午07:26:00
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
import org.pmp.service.business.ICondoFeeService;
import org.pmp.util.SpringContextUtil;
import org.pmp.validate.CondoFeeValidate;
import org.pmp.vo.CondoFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class NewCondoFeeImport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(NewCondoFeeImport.class
	    .getName());
    
    //~ Methods ========================================================================================================
    public static boolean execute(InputStream is,OutputStream os,List<CondoFee> cfList){
	Boolean hasError = false;
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
	    /*  */
            Cell cell = null;
            for (int j = 1; j < sheet.getRows(); j++) {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < sheet.getColumns(); i++) {
                    cell = sheet.getCell(i, j);
                    String context = cell.getContents().trim();
                    list.add(context);
                }
	        if(!CondoFeeValidate.isRight(list)){
	            hasError = true;
	        } else {
	            ICondoFeeService condoFeeService = (ICondoFeeService)SpringContextUtil.getBean("condoFeeService");
	            CondoFee item = condoFeeService.getCondoFee_ById(Integer.parseInt(list.get(0).trim()));
	            if (item != null){
	        	item.setOughtMoney(Double.parseDouble(list.get(6)));
                        cfList.add(item);
	                /* remove the right row */
		        logger.debug("j="+j+"   removedRows="+removedRows);
		        ws.removeRow(j-removedRows++);
	            } else {
	        	hasError = true;
	            }
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
	logger.debug("import cfList.size="+cfList.size());
        return hasError;
    }
}
