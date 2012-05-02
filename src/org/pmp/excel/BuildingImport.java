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
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.validate.BuildingValidate;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingImport {
	public static List buildingList(InputStream is,StringBuffer isError,StringBuffer errorPath){
		List buildingList = new ArrayList<Building>();
		InputStream fs = null;
        Workbook workBook = null;
        try {
                workBook = Workbook.getWorkbook(is);
           } catch (FileNotFoundException e) {
            e.printStackTrace();
           } catch (BiffException e) {
            e.printStackTrace();
           } catch (IOException e) {
            e.printStackTrace();
           }
        Sheet sheet = workBook.getSheet(0);
        Cell cell = null;
        List list = new ArrayList();
        List errorList = new ArrayList<List>();
        for (int j = 1; j < sheet.getRows(); j++) {
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < sheet.getColumns(); i++) {
             cell = sheet.getCell(i, j);
             String context = cell.getContents();
             list.add(context);
             }
             if(!BuildingValidate.dateValidate(list)){
            	 isError.append("是");
            	 List errorList1 = new ArrayList();
 				 errorList1.addAll(list);
 				 errorList.add(errorList1);
            	 list.clear();
            	 continue;
             }
             Project project = null;
             try{
            	 IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
            	 project = projectService.getProjectByName((String)list.get(1));
             }catch(RuntimeException e){
            	 System.out.println(e);
            	 isError.append("是");
            	 List errorList1 = new ArrayList();
 				 errorList1.addAll(list);
 				 errorList.add(errorList1);
            	 list.clear();
            	 continue;
             }
             Building building = new Building();
             building.setBuilNum(Integer.parseInt((String)list.get(0)));
             building.setProject(project);
             building.setBuilType((String)list.get(2));
             building.setFloorCount(Integer.parseInt((String)list.get(3)));
             building.setSkipFloor((String)list.get(4));
             building.setHousesPer(Integer.parseInt((String)list.get(5)));
             building.setUnitCount(Integer.parseInt((String)list.get(6)));
             building.setUnitTag((String)list.get(7));
             building.setBuilDesc((String)list.get(8));
             boolean enabled = false;
             if(((String)list.get(9)).equals("是")){
            	 enabled = true;
             }
             building.setEnabled(enabled);
             building.setCondoFeeRate(Integer.parseInt((String)list.get(10)));
             buildingList.add(building);
             list.clear();
           }
        if(isError.toString().equals("是")){
        	String path = ServletActionContext.getRequest().getRealPath("/")
			+ "\\error" + "\\errorBuilding.xls";
        WritableWorkbook wwb;
    	try {
    		wwb = Workbook.createWorkbook(new File(path));
    		WritableSheet ws = wwb.createSheet("sheet1", 0);
    		for(int i = 0; i < sheet.getColumns(); i++){
    			cell = sheet.getCell(i, 0);
                String context = cell.getContents();
                WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
    			WritableCellFormat wcf = new WritableCellFormat(wf);
    			Label label = new Label(i,0,context,wcf);
    			ws.addCell(label);
    		}
    		for(int i=0;i<errorList.size();i++){
    			for(int j=0;j<((List)errorList.get(0)).size();j++){
    				WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
    				WritableCellFormat wcf = new WritableCellFormat(wf);
    				Label label = new Label(j,i+1,(String)((List)errorList.get(i)).get(j),wcf);
    				ws.addCell(label);
    			}
    		}
    		wwb.write();
    	    wwb.close();
    		errorPath.append(ServletActionContext.getRequest().getContextPath()+"/error/errorBuilding.xls");
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (RowsExceededException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (WriteException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        }
           workBook.close();
		return buildingList;
	}
}
