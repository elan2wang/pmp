/**
 * Author            : Jason
 * Created On        : 2012-7-9 下午01:37:55
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
import org.pmp.vo.ElectricFee;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeExport {
	private static Logger logger = Logger.getLogger(ElectricFeeExport.class.getName());
	public static void execute(OutputStream os,List<?> ElectricFeeList){
		try {
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("sheet1", 0);
				String[] header = {"小区名称","楼号","房号","业主姓名","起始日期","结束日期","小区电表均摊费","电梯电表均摊费","总均摊费","备注"
					};
				ExportUtil.writeHead(ws, header);
				logger.debug("ElectricFeeList.size="+ElectricFeeList.size());
				for(int i=1;i<=ElectricFeeList.size();i++){
					ElectricFee electricFee = (ElectricFee)ElectricFeeList.get(i-1);
					ws.addCell(ExportUtil.toCell(electricFee.getHouseOwner().getHouse().getBuilding().getProject().getProName(), 0, i));
					ws.addCell(ExportUtil.toCell(electricFee.getHouseOwner().getHouse().getBuilding().getBuilNum(), 1, i));
					ws.addCell(ExportUtil.toCell(electricFee.getHouseOwner().getHouse().getHouseNum(), 2, i));
					ws.addCell(ExportUtil.toCell(electricFee.getHouseOwner().getOwner().getOwnerName(), 3, i));
					ws.addCell(ExportUtil.toCell(electricFee.getElectricFeeItem().getBeginDate(), 4, i));
					ws.addCell(ExportUtil.toCell(electricFee.getElectricFeeItem().getEndDate(), 5, i));
					ws.addCell(ExportUtil.toCell(electricFee.getProMeterFee(), 6, i));
					ws.addCell(ExportUtil.toCell(electricFee.getLiftMeterFee(), 7, i));
					ws.addCell(ExportUtil.toCell(electricFee.getTotalMoney(), 8, i));
					ws.addCell(ExportUtil.toCell(electricFee.getComment(), 9, i));
				}
				wwb.write();
			    wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		    logger.error("create WritableWorkbook failed");
		}  catch (WriteException e) {
			e.printStackTrace();
		    logger.error("write data to excel failed");
		}
	}
}
