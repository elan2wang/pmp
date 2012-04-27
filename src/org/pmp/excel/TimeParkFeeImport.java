/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午11:38:03
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.excel;

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

import org.apache.log4j.Logger;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.vo.TimeParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class TimeParkFeeImport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(TimeParkFeeImport.class.getName());
    
    //~ Methods ========================================================================================================
    public static List<TimeParkFee> getTimeParkFeeList(InputStream is){
	List<TimeParkFee> tpfList = new ArrayList<TimeParkFee>();
	
	//load the excel file
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
            TimeParkFee tpf = new TimeParkFee();
            //set value of project
            IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService"); 
            tpf.setProject(projectService.getProjectByName(list.get(0).toString()));
            //set value of parkDate
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");   
            try {
		Date date = format.parse(list.get(1).toString());
		tpf.setParkDate(date);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
            //set the other value of the TimeParkFee
	    tpf.setCarNum(list.get(2).toString());
	    tpf.setParkTime(Integer.parseInt(list.get(3).toString()));
	    tpf.setParkFeeRate(Double.parseDouble(list.get(4).toString()));
	    tpf.setFetchMoney(Double.parseDouble(list.get(5).toString()));
	    tpf.setFetchPerson(list.get(6).toString());
	    //set the recordPerson with the value retrieved from SessionHandler
	    tpf.setRecordPerson("elan");
	    tpf.setRecordTime(new Date());
	    //set the state
	    tpf.setState("待审核");
	    //add this instance into tpfList
	    tpfList.add(tpf);
	    
        }
 	return tpfList;
    }
    
}
