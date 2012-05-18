/**
 * Author            : Jason
 * Created On        : 2012-3-28 下午04:42:11
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.pmp.service.business.IHouseService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.House;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class HouseAction extends ActionSupport {
	
	static Logger logger = Logger.getLogger(HouseAction.class.getName());
	
	private IHouseService houseService;
	private House house;
	private Integer houseId;

	private Integer page;
	private Integer rp;
	
	private Integer projectId;
	private Integer buildingId;
	
	private String buildingNum;
	private String unit;
	private String houseNum;
	
	



	public String saveHouse(){
		houseService.saveHouse(house);
		return SUCCESS;
	}
	
	public String updateHouse(){
		houseService.updateHouse(house);
		return SUCCESS;
	}
	
	public void deleteHouse(){
		houseService.deleteHouse(houseId);
	}
	
	public String getHouseById(){
		house = houseService.getHouseById(houseId);
		String[] houseInfo = house.getHouseNum().split("-");
		buildingNum = houseInfo[0];
		unit = houseInfo[1];
		houseNum = houseInfo[2];
		return SUCCESS;
	}
	
	public void loadHouseListBySessionHandler(){
		logger.debug("进入loadHouseListBySessionHandler方法");
		System.out.println("projectId:"+projectId);
		System.out.println("buildingId:"+buildingId);
		List houseList = null;
		houseList = new ArrayList<House>();
		Object obj = SessionHandler.getUserRefDomain();
		//如果是小区管理员，则只显示本小区内的楼宇
		
		Pager pager = new Pager(rp,page);
		Pager pager2 = new Pager(10000,1);
		Map<String,Object> params = new HashMap<String,Object>();
		String order = "order by houseId asc";
		if(projectId==0){		
			if(obj instanceof Project)
			{
				Project pro = (Project)obj;
				System.out.println(pro.getProName());
				houseList = houseService.loadHouseList_ByProject(pro.getProId(), params, order, pager2);
			}
			else if(obj instanceof Company)
			{
				Company com = (Company)obj;
				houseList = houseService.loadHouseList_ByCompany(com.getComId(), params, order, pager2);
			}
		}
		else if(projectId!=0 && buildingId==0){ 
			houseList = houseService.loadHouseList_ByProject(projectId, params, order, pager2);
		}
		else if(buildingId!=0)
			houseList = houseService.loadHouseList_ByBuilding(buildingId, params, order, pager2);
		logger.debug("得到的houseList为"+houseList.toString());
		pager.setRowsCount(houseList.size());
		String data = JsonConvert.list2FlexJson(pager, houseList, "org.pmp.vo.House");
		System.out.println(data);
		logger.debug(data);
		JsonConvert.output(data);
	
	}
	
	
	/**
	 * @return the house
	 */
	public House getHouse() {
		return house;
	}
	/**
	 * @param house the house to set
	 */
	public void setHouse(House house) {
		this.house = house;
	}
	/**
	 * @return the houseId
	 */
	public Integer getHouseId() {
		return houseId;
	}
	/**
	 * @param houseId the houseId to set
	 */
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	/**
	 * @param houseService the houseService to set
	 */
	public void setHouseService(IHouseService houseService) {
		this.houseService = houseService;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @return the buildingId
	 */
	public Integer getBuildingId() {
		return buildingId;
	}

	/**
	 * @param buildingId the buildingId to set
	 */
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	
	/**
	 * @return the buildingNum
	 */
	public String getBuildingNum() {
		return buildingNum;
	}

	/**
	 * @param buildingNum the buildingNum to set
	 */
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the houseNum
	 */
	public String getHouseNum() {
		return houseNum;
	}

	/**
	 * @param houseNum the houseNum to set
	 */
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the rp
	 */
	public Integer getRp() {
		return rp;
	}

	/**
	 * @param rp the rp to set
	 */
	public void setRp(Integer rp) {
		this.rp = rp;
	}
}
