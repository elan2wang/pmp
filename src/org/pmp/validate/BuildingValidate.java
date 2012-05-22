/**
 * Author            : Jason
 * Created On        : 2012-4-28 上午11:22:40
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.validate;

import java.util.List;

import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SpringContextUtil;
import org.pmp.vo.Building;
import org.pmp.vo.Project;

import jxl.Cell;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingValidate {
	public static boolean isRight(List<Cell> list){
		Project pro =null;
		
		//判断excel表格是否是10列
		if(list.size()!=10)
			return false;
		//判断项目名称是否为空，或者该项目不存在
		if((list.get(1).getContents()).equals("")){
			return false;
		}
		else
		{
			IProjectService projectService = (IProjectService)SpringContextUtil.getBean("projectService");
			pro = projectService.getProjectByName(list.get(1).getContents());
			if(pro==null)
				return false;	
		}
		//判断楼号是否为空，或者是否重复
		if((list.get(0).getContents()).equals("")){
			return false;
		}
		else
		{
			IBuildingService buildingService = (IBuildingService)SpringContextUtil.getBean("buildingService");
			Building buil = buildingService.getBuildingByProjectIdAndBuildingNum(pro.getProId(), Integer.parseInt(list.get(0).getContents()));
			if(buil!=null)
				return false;			
		}
		//从builType到condoFeeRate都不能为0
		for(int i=2;i<list.size()-1;++i)
		{
			if(list.get(i).getContents().equals(""))
				return false;
		}
//		if(!(((String)list.get(9)).equals("是")||((String)list.get(9)).equals("否"))){
//			return false;
//		}
		return true;
	}
}
