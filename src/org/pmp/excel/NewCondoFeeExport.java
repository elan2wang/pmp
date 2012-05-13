/**
 * Author            : Elan
 * Created On        : 2012-5-10 下午06:25:40
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
public class NewCondoFeeExport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(NewCondoFeeExport.class
	    .getName());

    //~ Methods ========================================================================================================
    public static void execute(OutputStream os,List<?> cfList){
	try {
	    WritableWorkbook wwb = Workbook.createWorkbook(os);
	    WritableSheet ws = wwb.createSheet("sheet1", 0);
	    /* set the header of the excel file */
	    String[] header = {"编号","所在小区","房号","面积","时间","业主","应收物业费"};
	    ExportUtil.writeHead(ws, header);
	    
	    /* i begin with 1 not 0 */
	    for (int i=1;i<=cfList.size();i++){
		CondoFee cf = (CondoFee)cfList.get(i-1);
		ws.addCell(ExportUtil.toCell(cf.getCfId(), 0, i));
		ws.addCell(ExportUtil.toCell(cf.getHouse().getBuilding().getProject().getProName(), 1, i));
		ws.addCell(ExportUtil.toCell(cf.getHouse().getHouseNum().toString(), 2, i));
		ws.addCell(ExportUtil.toCell(cf.getHouse().getHouseArea(), 3, i));
		ws.addCell(ExportUtil.toCell(cf.getCfYear()+"-"+cf.getCfMonth(), 4, i));
		ws.addCell(ExportUtil.toCell(cf.getOwner().getOwnerName(), 5, i));
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
