/**
 * Author            : Jason
 * Created On        : 2012-4-23 下午03:11:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

import java.io.File;
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
import java.util.Map;

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
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.validate.OwnerValidate;
import org.pmp.vo.Building;
import org.pmp.vo.House;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class OwnerImport {
	public static List ownerList(InputStream is,Map map,StringBuffer isError,StringBuffer errorPath){
		List ownerList = new ArrayList<Owner>();
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
        Project project = null;
        Building building = null;
        List errorList = new ArrayList<List>();
        for (int j = 1; j < sheet.getRows(); j++) {
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < sheet.getColumns(); i++) {
             cell = sheet.getCell(i, j);
             String context = cell.getContents();
             list.add(context);
             }
             OwnerValidate.dateValidate(list);
             if(!OwnerValidate.dateValidate(list)){
            	 isError.append("是");
 				 List errorList1 = new ArrayList();
 				 errorList1.addAll(list);
 				 errorList.add(errorList1);
            	 list.clear();
            	 continue;
             }
             House house = new House();
             try{
            	 IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
            	 IBuildingService buildingService = (IBuildingService)SpringContextUtil.getBean("buildingService");
            	 IHouseService houseService = (IHouseService)SpringContextUtil.getBean("houseService");
            	 project = projectService.getProjectByName((String)list.get(0));
            	 building = buildingService.getBuildingByProjectIdAndBuildingNum(project.getProId(), Integer.parseInt((String)list.get(1)));
            	 house = houseService.getHouseByBuildingIdAndHouseNum(building.getBuilId(), (String)list.get(2));
             }catch(RuntimeException e){
            	 System.out.println(e);
            	 isError.append("是");
 				 List errorList1 = new ArrayList();
 				 errorList1.addAll(list);
 				 errorList.add(errorList1);
            	 list.clear();
            	 continue;
             }
             
             Owner owner = new Owner();
             owner.setOwnerName((String)list.get(3));
             owner.setGender((String)list.get(4));
             owner.setNationality((String)list.get(5));
             owner.setNative_((String)list.get(6));
             DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
             Date date;
			try {
				date = format.parse((String)list.get(7));
				owner.setBirthday(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			boolean idMarried = false;
            if(((String)list.get(8)).equals("是")){
            	idMarried = true;
            }
             owner.setIsmarried(idMarried);
             owner.setOrganization((String)list.get(9));
             owner.setHobby((String)list.get(10));
             owner.setCarType((String)list.get(11));
             owner.setCarNum((String)list.get(12));
             owner.setHomePhone((String)list.get(13));
             owner.setMobile((String)list.get(14));
             try {
 				date = format.parse((String)list.get(15));
 				owner.setGetTime(date);
 				date = format.parse((String)list.get(16));
 				owner.setDecorateTime(date);
 				date = format.parse((String)list.get(17));
 				owner.setInTime(date);
 			} catch (ParseException e) {
 				e.printStackTrace();
 			}
 			 owner.setParkNum((String)list.get(18));
             owner.setCarNum((String)list.get(19));
             owner.setCarType((String)list.get(20));
             owner.setStoreroom((String)list.get(21));
             owner.setHouseArea(Double.parseDouble((String)list.get(22)));
             owner.setUseStyle((String)list.get(23));
             owner.setOtherAddress((String)list.get(24));
             owner.setOtherPostcode((String)list.get(25));
             owner.setEmergencyContact((String)list.get(26));
             owner.setEmergencyPhone((String)list.get(27));
             owner.setHouseNum((String)list.get(2));
             String ownerDes = (String)list.get(0)+","+(String)list.get(1)+","+(String)list.get(2);
             owner.setOwnerDesc(ownerDes);
             house.setHouseArea(Double.parseDouble((String)list.get(22)));
             house.setIsempty(false);
             map.put(ownerDes, house);
             ownerList.add(owner);
             list.clear();
           }
        if (isError.toString().equals("是")) {
			String path = ServletActionContext.getRequest().getRealPath("/")
					+ "\\error" + "\\errorOwner.xls";
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
    	    errorPath.append(ServletActionContext.getRequest().getContextPath()+"/error/errorOwner.xls");
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
		return ownerList;
	}
}
