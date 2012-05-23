/**
 * Author            : Jason
 * Created On        : 2012-4-27 下午03:09:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.pmp.service.business.ICompanyService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

import jxl.Cell;
import jxl.CellType;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectValidate {
	public static boolean isRight(List<Cell> list){
		//判断excel表格是否是9列
		if(list.size()!=9)
			return false;
		//判断项目名称是否为空，或者是否重复
		if((list.get(0).getContents()).equals("")){
			return false;
		}
		else
		{
			IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
			Project pro = projectService.getProjectByName(list.get(0).getContents());
			if(pro!=null)
				return false;			
		}
		//判断公司名称是否为空，或者该公司不存在
		if((list.get(1).getContents()).equals("")){
			return false;
		}
		else
		{
			ICompanyService companyService = (ICompanyService)SpringContextUtil.getBean("companyService");
        	Company com = companyService.getCompanyByName(list.get(1).getContents());//第二列为comName, 公司名称
        	if(com==null)
        		return false;
		}
		if(list.get(5).getType()!=CellType.DATE||(list.get(5).getContents().equals(""))){
			return false;
		}
		if(list.get(6).getType()!=CellType.NUMBER||list.get(6).getContents().equals("")){
			return false;
		}
//		if(!(((String)list.get(8)).equals("是")||((String)list.get(8)).equals("否"))){
//			return false;
//		}
//		if(!(((String)list.get(9)).equals("是")||((String)list.get(9)).equals("否"))){
//			return false;
//		}
		return true;
	}
}
