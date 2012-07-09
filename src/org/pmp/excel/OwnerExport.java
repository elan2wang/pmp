/**
 * Author            : Jason
 * Created On        : 2012-7-9 上午11:07:52
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.pmp.vo.HouseOwner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class OwnerExport {
	private static Logger logger = Logger.getLogger(OwnerExport.class.getName());
	public static void execute(OutputStream os,List<?> houseOwnerList){
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			String[] header = {"物业公司名称","小区名称","楼宇号","房号","房屋面积","业主姓名","手机号码","家庭电话",
					"工作单位","使用情况","储藏室号","车位库号","车牌号码","车型号","籍贯","民族","性别","婚姻状况",	
					"生日","证件类型","证件编号","领房时间","装修时间","入住时间","其他地址",
					"其他邮编","紧急联系人","紧急联系人电话","个人爱好","其他信息"};
			ExportUtil.writeHead(ws, header);
			logger.debug("houseOwnerList.size="+houseOwnerList.size());
			for(int i=1;i<=houseOwnerList.size();i++){
				HouseOwner houseOwner = (HouseOwner)houseOwnerList.get(i-1);
				ws.addCell(ExportUtil.toCell(houseOwner.getHouse().getBuilding().getProject().getCompany().getComName(), 0, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getHouse().getBuilding().getProject().getProName(), 1, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getHouse().getBuilding().getBuilNum(), 2, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getHouse().getHouseNum(), 3, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getHouse().getHouseArea(), 4, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getOwnerName(), 5, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getMobile(), 6, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getHomePhone(), 7, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getOrganization(), 8, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getUseStyle(), 9, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getStoreroom(), 10, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getParkNum(), 11, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getCarNum(), 12, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getCarType(), 13, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getNative_(), 14, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getNationality(), 15, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getGender(), 16, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().isIsmarried(), 17, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getBirthday(), 18, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getCarType(), 19, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getCarNum(), 20, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getGetTime(), 21, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getDecorateTime(), 22, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getInTime(), 23, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getOtherAddress(), 24, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getOtherPostcode(), 25, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getEmergencyContact(), 26, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getEmergencyPhone(), 27, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getHobby(), 28, i));
				ws.addCell(ExportUtil.toCell(houseOwner.getOwner().getOtherInfo(), 29, i));
			}
			wwb.write();
		    wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		    logger.error("create WritableWorkbook failed");
		}catch (WriteException e) {
		    e.printStackTrace();
		    logger.error("write data to excel failed");
		}
	}
}
