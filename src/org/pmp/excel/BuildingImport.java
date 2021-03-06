/**
 * Author            : Elan
 * Created On        : 2012-6-28 上午11:47:32
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
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.validate.BuildingValidate;
import org.pmp.vo.Building;
import org.pmp.vo.Project;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class BuildingImport {
	  //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ProjectImport.class.getName());
    
    //~ Methods ========================================================================================================
    public static boolean execute(InputStream is,OutputStream os,List<Building> builList){
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
	    
            IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
            Cell cell = null;
            /* 从第二行开始读 ， 因为第一行是表头 */
            for (int j = 1; j < sheet.getRows(); j++) {
            	Building buil = new Building();
                List<Cell> list = new ArrayList<Cell>();
                for (int i = 0; i < sheet.getColumns(); i++) {
                    cell = sheet.getCell(i, j);
                    list.add(cell);
                }
                if(!BuildingValidate.isRight(list)){
                    hasError = true;
                } else {
                    Project pro = projectService.getProjectByName(list.get(0).getContents().trim());
                    /* 如果没有该小区则标记为错误记录  */
                    if(pro==null){
                	hasError = true;
                	continue;
                    }
                    buil.setProject(pro);
                    buil.setBuilNum(list.get(1).getContents().trim());
                    buil.setFloorCount(Integer.parseInt(list.get(2).getContents().trim()));
                    buil.setUnitCount(Integer.parseInt(list.get(3).getContents().trim()));
                    buil.setHousesPer(Integer.parseInt(list.get(4).getContents().trim()));
                    buil.setSkipFloor(list.get(5).getContents().trim());
                    buil.setBuilType(list.get(6).getContents().trim());
                    
                    builList.add(buil);
                    /* 在可读写的Sheet中删除正确的记录  */
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
        return hasError;
    }
}
