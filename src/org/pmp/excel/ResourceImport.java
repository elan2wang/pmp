/**
 * Author            : Elan
 * Created On        : 2012-5-6 下午01:17:15
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.pmp.vo.TbResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ResourceImport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ResourceImport.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public static List<TbResource> getResourceList(InputStream is){
	List<TbResource> resList = new ArrayList<TbResource>();
	
	Workbook workbook = null;
	try {
	    workbook = Workbook.getWorkbook(is);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (BiffException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	Sheet sheet = workbook.getSheet(0);
	Cell cell = null;
	for (int j = 1; j < sheet.getRows(); j++) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < sheet.getColumns(); i++) {
                cell = sheet.getCell(i, j);
                String context = cell.getContents().trim();
                list.add(context);
            }
            TbResource res = new TbResource();
            res.setResName(list.get(0));
            res.setResType(list.get(1));
            res.setResLink(list.get(2));
            res.setResDesc(list.get(3));
            res.setEnabled(Boolean.parseBoolean(list.get(4)));
            res.setIssys(Boolean.parseBoolean(list.get(5)));
            res.setModId(Integer.parseInt(list.get(6)));
            resList.add(res);
	}
	
	return resList;
	
    }
    //~ Getters and Setters ============================================================================================

}
