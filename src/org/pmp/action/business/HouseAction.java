/**
 * Author            : Elan
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
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IHouseService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.House;
import org.pmp.vo.Project;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class HouseAction extends BaseAction {
	
    private static Logger logger = Logger.getLogger(HouseAction.class.getName());
	
    private IHouseService houseService;
    private House house;
    private Integer houseId;

    private Integer projectId;
    private Integer buildingId;
	
    private String buildingNum;
    private String unit;
    private String houseNum;
    
    private String idStr;
   

    public String addHouse(){
	houseService.addHouse(house);
	return SUCCESS;
    }
	
    public String editHouse(){
        houseService.editHouse(house);
        return SUCCESS;
    }
	
    public void deleteHouse(){
	List<House> houseList = new ArrayList<House>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    House house = houseService.getHouseByID(Integer.parseInt(checkedID[i]));
	    houseList.add(house);
	}
	houseService.batchDelete(houseList);
	Map<String,String> result = new HashMap<String, String>();
	MyJson json = new MyJson();
	result.put("msg", "房屋信息删除成功");
	json.output(json.toJson(result));
    }
	
    public String getHouseById(){
        house = houseService.getHouseByID(houseId);
        String[] houseInfo = house.getHouseNum().split("-");
        buildingNum = houseInfo[0];
        unit = houseInfo[1];
        houseNum = houseInfo[2];
        return SUCCESS;
    }
	
    public void loadHouseList(){
        List<?> houseList = null;
        houseList = new ArrayList<House>();
        Object obj = SessionHandler.getUserRefDomain();
        //如果是小区管理员，则只显示本小区内的楼宇
		
        Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	
        if(buildingId!=0){
            houseList = houseService.loadHouseList_ByBuilding(buildingId, params, order, pager);
        }
        else if(projectId==0){
            if(obj instanceof Project){
                Project pro = (Project)obj;
                houseList = houseService.loadHouseList_ByProject(pro.getProId(), params, order, pager);
            }
            else if(obj instanceof Company){
                Company com = (Company)obj;
                houseList = houseService.loadHouseList_ByCompany(com.getComId(), params, order, pager);
            }
        }  else {
            houseList = houseService.loadHouseList_ByProject(projectId, params, order, pager);
        }
        
        String[] attrs = {"houseId","building.project.proName","houseNum","houseArea","isempty","houseDesc"};
        List<String> show = Arrays.asList(attrs);
        Includer includer = new Includer(show);
        MyJson json = new MyJson(includer);
        String data = json.toJson(houseList, "", pager);
        json.output(data);
    }

    //~ getters and setters ===========================================================================================
    public IHouseService getHouseService() {
        return houseService;
    }

    public void setHouseService(IHouseService houseService) {
        this.houseService = houseService;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
    
}
