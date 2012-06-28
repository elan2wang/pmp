/**
 * Author            : Jason
 * Created On        : 2012-4-19 下午03:42:03
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.pmp.service.business.ICompanyService;
import org.pmp.util.SpringContextUtil;
import org.pmp.validate.ProjectValidate;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectImport {
	  //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ProjectImport.class
	    .getName());
    
    //~ Methods ========================================================================================================
    public static boolean execute(InputStream is,OutputStream os,List<Project> proList){
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
    	
            ICompanyService companyService = (ICompanyService)SpringContextUtil.getBean("companyService");
            Cell cell = null;
            //从第二行开始读 ， 因为第一行是表头
            for (int j = 1; j < sheet.getRows(); j++) {
            	Project pro = new Project();
                List<Cell> list = new ArrayList<Cell>();
                for (int i = 0; i < sheet.getColumns(); i++) {
                    cell = sheet.getCell(i, j);
                    list.add(cell);
                }
                if(!ProjectValidate.isRight(list)){
                    hasError = true;
                } else {
                    Company com = companyService.getCompanyByName(list.get(0).getContents());
                    if(com==null){
                	hasError = true;
                	continue;
                    }
                    pro.setCompany(com);
                    pro.setProName(list.get(1).getContents());
                    pro.setProDistrict(list.get(2).getContents());
                    pro.setProStreet(list.get(3).getContents());
                    pro.setProAddress(list.get(4).getContents());
                    pro.setProType(list.get(5).getContents());
                    pro.setProHouseCount(Integer.parseInt(list.get(6).getContents()));
                    pro.setDeliveryTime(((DateCell)list.get(7)).getDate());
                    pro.setProDesc(list.get(8).getContents());

                    proList.add(pro);
                    /* remove the right row */
                    logger.debug("j="+j+"   removedRows="+removedRows);
                    ws.removeRow(j-removedRows++);
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
	logger.debug("import cfList.size="+proList.size());
        return hasError;
    }

}
