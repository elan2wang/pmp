/**
 * Author            : Jason
 * Created On        : 2012-4-19 下午03:42:03
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
import org.pmp.util.SpringContextUtil;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectImport {
	public static List projectList(String filePath){
		List projectList = new ArrayList<Project>();
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
             Project project = new Project();
             project.setProName((String)list.get(0));
             project.setProType((String)list.get(1));
//             project.setCompany(companyService.getCompanyByName((String)list.get(2)));
            		 ICompanyService companyService = (ICompanyService)SpringContextUtil.getBean("companyService");
            		 Company company = companyService.getCompanyByName("AAA");
             project.setCompany(company);
             project.setProDistrict((String)list.get(3));
             project.setProStreet((String)list.get(4));
             project.setProAddress((String)list.get(5));
             DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
             Date date;
			try {
				date = format.parse((String)list.get(6));
				project.setDeliveryTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
             project.setProHouseCount(Integer.parseInt((String)list.get(7)));
             boolean enabled = false;
             if(((String)list.get(8)).equals("是")){
            	 enabled = true;
             }
             project.setEnabled(enabled);
             boolean fireEnable = false;
             if(((String)list.get(9)).equals("是")){
            	 fireEnable = true;
             }
             project.setFireEnabled(fireEnable);
             project.setProDesc((String)list.get(10));
             projectList.add(project);
           }
           workBook.close();
		return projectList;
	}

}
