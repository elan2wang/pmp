/**
 * Author            : Jason
 * Created On        : 2012-3-28 下午04:42:11
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.IHouseService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.House;
import org.pmp.vo.Owner;

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
//	private Pager pager;
	private Integer currentPage;
	private Integer pageSize;
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
	
	public void loadHouseList(){
		logger.debug("进入loadHouseList方法");
		Pager pager = new Pager(pageSize,currentPage);
		List houseList = houseService.loadHouseList(pager);
		logger.debug("得到的houseList为"+houseList.toString());
		StringBuffer sb = new StringBuffer();
		sb.append("{\n");
		sb.append("  "+JsonConvert.toJson("RowsCount")+":"+JsonConvert.toJson(pager.getRowsCount())+",\n");
		sb.append("  "+JsonConvert.toJson("PageSize")+":"+JsonConvert.toJson(pager.getPageSize())+",\n");
		sb.append("  "+JsonConvert.toJson("CurrentPage")+":"+JsonConvert.toJson(pager.getCurrentPage())+",\n");
		sb.append("  "+JsonConvert.toJson("PagesCount")+":"+JsonConvert.toJson(pager.getPagesCount())+",\n");
		sb.append("  "+JsonConvert.toJson("Rows")+":[\n");
		Iterator ite = houseList.iterator();
		while(ite.hasNext()){
			sb.append("    {");
			House house = (House)ite.next();
			String[] houseInfo = house.getHouseNum().split("-");
			String houseFloor = houseInfo[2].substring(0, 1);
			sb.append(JsonConvert.toJson("houseId")+":"+JsonConvert.toJson(house.getHouseId().toString())+",");
//			sb.append(JsonConvert.toJson("building")+":"+JsonConvert.toJson(house.getBuilding().getBuilNum().toString())+",");
//			sb.append(JsonConvert.toJson("ownerName")+":"+JsonConvert.toJson(house.getOwner().getOwnerName().toString())+",");
			sb.append(JsonConvert.toJson("houseUnit")+":"+JsonConvert.toJson(houseInfo[1].toString())+",");
			sb.append(JsonConvert.toJson("houseFloor")+":"+JsonConvert.toJson(houseFloor)+",");
			sb.append(JsonConvert.toJson("houseNum")+":"+JsonConvert.toJson(house.getHouseNum().toString())+",");
//			sb.append(JsonConvert.toJson("houseArea")+":"+JsonConvert.toJson(house.getHouseArea().toString())+",");
//			sb.append(JsonConvert.toJson("houseDesc")+":"+JsonConvert.toJson(house.getHouseDesc().toString())+",");
//			sb.append(JsonConvert.toJson("condoFeeRate")+":"+JsonConvert.toJson(house.getCondoFeeRate().toString())+",");
			sb.append(JsonConvert.toJson("isempty")+":"+JsonConvert.toJson(house.isIsempty())+",");
			sb.deleteCharAt(sb.length()-1);
		    sb.append("},\n");
		}
		sb.deleteCharAt(sb.length()-2);
		sb.append("  ]\n}\n");
		logger.debug("得到的json数据为"+sb.toString());
		//output the Jason data
		try {    
	            HttpServletResponse response = ServletActionContext.getResponse();  
	            response.setContentType("application/json;charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().println(sb.toString());     
	        } catch (IOException e) {                     
	            e.printStackTrace();  
	        } 
	}
	
	public void getHouseByProjectOrBuilding(){
		logger.debug("进入getHouseByProjectOrBuilding方法");
		Pager pager = new Pager(pageSize,currentPage);
		List houseList = houseService.getHouseByProjectOrBuilding(projectId, buildingId, pager);
		logger.debug("得到的houseList为"+houseList.toString());
		StringBuffer sb = new StringBuffer();
		sb.append("{\n");
		sb.append("  "+JsonConvert.toJson("RowsCount")+":"+JsonConvert.toJson(pager.getRowsCount())+",\n");
		sb.append("  "+JsonConvert.toJson("PageSize")+":"+JsonConvert.toJson(pager.getPageSize())+",\n");
		sb.append("  "+JsonConvert.toJson("CurrentPage")+":"+JsonConvert.toJson(pager.getCurrentPage())+",\n");
		sb.append("  "+JsonConvert.toJson("PagesCount")+":"+JsonConvert.toJson(pager.getPagesCount())+",\n");
		sb.append("  "+JsonConvert.toJson("Rows")+":[\n");
		Iterator ite = houseList.iterator();
		while(ite.hasNext()){
			sb.append("    {");
			House house = (House)ite.next();
			String[] houseInfo = house.getHouseNum().split("-");
			String houseFloor = houseInfo[2].substring(0, 1);
			sb.append(JsonConvert.toJson("houseId")+":"+JsonConvert.toJson(house.getHouseId().toString())+",");
//			sb.append(JsonConvert.toJson("building")+":"+JsonConvert.toJson(house.getBuilding().getBuilNum().toString())+",");
//			sb.append(JsonConvert.toJson("ownerName")+":"+JsonConvert.toJson(house.getOwner().getOwnerName().toString())+",");
			sb.append(JsonConvert.toJson("houseUnit")+":"+JsonConvert.toJson(houseInfo[1].toString())+",");
			sb.append(JsonConvert.toJson("houseFloor")+":"+JsonConvert.toJson(houseFloor)+",");
			sb.append(JsonConvert.toJson("houseNum")+":"+JsonConvert.toJson(house.getHouseNum().toString())+",");
//			sb.append(JsonConvert.toJson("houseArea")+":"+JsonConvert.toJson(house.getHouseArea().toString())+",");
//			sb.append(JsonConvert.toJson("houseDesc")+":"+JsonConvert.toJson(house.getHouseDesc().toString())+",");
//			sb.append(JsonConvert.toJson("condoFeeRate")+":"+JsonConvert.toJson(house.getCondoFeeRate().toString())+",");
			sb.append(JsonConvert.toJson("isempty")+":"+JsonConvert.toJson(house.isIsempty())+",");
			sb.deleteCharAt(sb.length()-1);
		    sb.append("},\n");
		}
		sb.deleteCharAt(sb.length()-2);
		sb.append("  ]\n}\n");
		logger.debug("得到的json数据为"+sb.toString());
		//output the Jason data
		try {    
	            HttpServletResponse response = ServletActionContext.getResponse();  
	            response.setContentType("application/json;charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().println(sb.toString());     
	        } catch (IOException e) {                     
	            e.printStackTrace();  
	        }
	}
	
	public void getAllHouseNum(){
		Building building = new Building();
		building.setBuilId(buildingId);
		List houseList = houseService.getHouseByBuilding(building);
		List show = new ArrayList<String>();
		show.add("houseId");
		show.add("houseNum");
		String data = JsonConvert.list2Json(houseList, "org.pmp.vo.House", show);
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
	 * @return the pager
	 */
//	public Pager getPager() {
//		return pager;
//	}
//	/**
//	 * @param pager the pager to set
//	 */
//	public void setPager(Pager pager) {
//		this.pager = pager;
//	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
}
