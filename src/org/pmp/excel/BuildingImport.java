/**
 * Author            : Jason
 * Created On        : 2012-4-23 上午11:47:32
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingImport {
	public static List buildingList(String filePath){
		List buildingList = new ArrayList<Building>();
		InputStream fs = null;
        Workbook workBook = null;
        try {
            // 加载excel文件
            fs = new FileInputStream(filePath);
            // 得到 workbook
                workBook = Workbook.getWorkbook(fs);
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
        for (int j = 1; j < sheet.getRows(); j++) {
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < sheet.getColumns(); i++) {
             cell = sheet.getCell(i, j);
             String context = cell.getContents();
             list.add(context);
             }
             Building building = new Building();
             building.setBuilNum(Integer.parseInt((String)list.get(0)));
             IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
             Project project = projectService.getProjectByName((String)list.get(1));
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
           workBook.close();
		return buildingList;
	}
}
