/**
 * Author            : Elan
 * Created On        : 2012-7-9 上午11:50:56
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
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
import org.pmp.vo.CondoFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeExport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(CondoFeeExport.class.getName());
    
    //~ Methods ========================================================================================================
    public static void execute(OutputStream os,List<CondoFee> cfList){
	try {
	    WritableWorkbook wwb = Workbook.createWorkbook(os);
	    WritableSheet ws = wwb.createSheet("sheet1", 0);
	    /* set the header of the excel file */
	    String[] header = {"所在小区","房号","业主","年份","月份","应收金额","实收金额","录入时间","录入人员","备注"};
	    ExportUtil.writeHead(ws, header);
	    logger.debug("cfList.size="+cfList.size());
	    /* i begin with 1 not 0 */
	    for(int i=1;i<=cfList.size();i++){
		CondoFee cf = (CondoFee)cfList.get(i-1);
		ws.addCell(ExportUtil.toCell(cf.getHouse().getBuilding().getProject().getProName(), 0, i));
		ws.addCell(ExportUtil.toCell(cf.getHouse().getHouseNum().toString(), 1, i));
		ws.addCell(ExportUtil.toCell(cf.getOwner().getOwnerName(), 2, i));
		ws.addCell(ExportUtil.toCell(cf.getCfYear(), 3, i));
		ws.addCell(ExportUtil.toCell(cf.getCfMonth(), 4, i));
		ws.addCell(ExportUtil.toCell(cf.getOughtMoney(), 5, i));
		ws.addCell(ExportUtil.toCell(cf.getFetchMoney(), 6, i));
		ws.addCell(ExportUtil.toCell(cf.getInputTime(), 7, i));
		ws.addCell(ExportUtil.toCell(cf.getRecordPerson(), 8, i));
		ws.addCell(ExportUtil.toCell(cf.getComment(), 9, i));
	    }
	    /* put the data in cache to file and close cache */
	    wwb.write();
	    wwb.close();
	} catch (IOException e) {
	    e.printStackTrace();
	    logger.error("create WritableWorkbook failed");
	} catch (WriteException e) {
	    e.printStackTrace();
	    logger.error("write data to excel failed");
	}
    }
}
