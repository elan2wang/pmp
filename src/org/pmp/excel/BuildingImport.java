/**
 * Author            : Jason
 * Created On        : 2012-4-23 上午11:47:32
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.validate.BuildingValidate;
import org.pmp.validate.ProjectValidate;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingImport {
	  //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ProjectImport.class
	    .getName());
    
    //~ Methods ========================================================================================================
    public static boolean execute(InputStream is,OutputStream os,List<Building> builList){
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
            //从第二行开始读 ， 因为第一行是表头
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
                	/*
                	 *  private Project project;
    						private String builNum;
    						 private String builType;
    private Integer floorCount;
    private String skipFloor;
    private Integer housesPer;
    private Integer unitCount;
    private String unitTag;
    private Double condoFeeRate;
    private String builDesc;
    private boolean enabled;
                	 */
                	IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
                	Project pro = projectService.getProjectByName(list.get(1).getContents());//第二列为proName, 项目名称
                	buil.setBuilNum(list.get(0).getContents());
                	buil.setProject(pro);
                	buil.setBuilType(list.get(2).getContents());
                	buil.setFloorCount(Integer.parseInt(list.get(3).getContents()));
                	buil.setSkipFloor(list.get(4).getContents());
                	buil.setHousesPer(Integer.parseInt(list.get(5).getContents()));
                	buil.setUnitCount(Integer.parseInt(list.get(6).getContents()));
                	buil.setUnitTag(list.get(7).getContents());
                	buil.setCondoFeeRate(Double.parseDouble(list.get(8).getContents()));
                	buil.setBuilDesc(list.get(9).getContents());

                	builList.add(buil);
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
	logger.debug("import cfList.size="+builList.size());
        return hasError;
    }
}
