/**
 * Author            : Jason
 * Created On        : 2012-4-19 下午03:42:03
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
import org.omg.CORBA.Request;
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
	public static List projectList(InputStream is, boolean isError,StringBuffer errorPath) {
		List projectList = new ArrayList<Project>();
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
			ICompanyService companyService = (ICompanyService) SpringContextUtil
					.getBean("companyService");
			// Company company =
			// companyService.getCompanyByName((String)list.get(2));
			Company company = companyService.getCompanyByName("舟山兴颖物业");
			if (company == null || !ProjectValidate.dateValidate(list)) {
				isError = true;
				List errorList1 = new ArrayList();
				errorList1.addAll(list);
				errorList.add(errorList1);
				list.clear();
				continue;
			}
			Project project = new Project();
			project.setProName((String) list.get(0));
			project.setProType((String) list.get(1));
			project.setCompany(company);
			project.setProDistrict((String) list.get(3));
			project.setProStreet((String) list.get(4));
			project.setProAddress((String) list.get(5));
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			Date date;
			try {
				date = format.parse((String) list.get(6));
				project.setDeliveryTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			project.setProHouseCount(Integer.parseInt((String) list.get(7)));
			boolean enabled = false;
			if (((String) list.get(8)).equals("是")) {
				enabled = true;
			}
			project.setEnabled(enabled);
			boolean fireEnabled = false;
			if (((String) list.get(8)).equals("是")) {
				fireEnabled = true;
			}
			project.setFireEnabled(fireEnabled);
			project.setProDesc((String) list.get(10));
			projectList.add(project);
			list.clear();
		}
		if (isError) {
			String path = ServletActionContext.getRequest().getRealPath("/")
					+ "\\error" + "\\errorProject.xls";
			WritableWorkbook wwb;
			try {
				wwb = Workbook.createWorkbook(new File(path));
				WritableSheet ws = wwb.createSheet("sheet1", 0);
				for (int i = 0; i < sheet.getColumns(); i++) {
					cell = sheet.getCell(i, 0);
					String context = cell.getContents();
					WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
							WritableFont.BOLD);
					WritableCellFormat wcf = new WritableCellFormat(wf);
					Label label = new Label(i, 0, context, wcf);
					ws.addCell(label);
				}
				for (int i = 0; i < errorList.size(); i++) {
					for (int j = 0; j < ((List) errorList.get(0)).size(); j++) {
						WritableFont wf = new WritableFont(WritableFont.ARIAL,
								10, WritableFont.BOLD);
						WritableCellFormat wcf = new WritableCellFormat(wf);
						Label label = new Label(j, i + 1,
								(String) ((List) errorList.get(i)).get(j), wcf);
						ws.addCell(label);
					}
				}
				wwb.write();
				wwb.close();
				errorPath.append(ServletActionContext.getRequest().getContextPath()+"/error/errorProject.xls");
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
		return projectList;
	}

}
