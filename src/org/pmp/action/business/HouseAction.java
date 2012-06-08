/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午04:42:11
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class HouseAction extends ActionSupport {
	
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
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */

    public String saveHouse(){
	houseService.addHouse(house);
	return SUCCESS;
    }
	
    public String updateHouse(){
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
    }
	
    public String getHouseById(){
        house = houseService.getHouseByID(houseId);
        String[] houseInfo = house.getHouseNum().split("-");
        buildingNum = houseInfo[0];
        unit = houseInfo[1];
        houseNum = houseInfo[2];
        return SUCCESS;
    }
	
    public void loadHouseListBySessionHandler(){
        List<?> houseList = null;
        houseList = new ArrayList<House>();
        Object obj = SessionHandler.getUserRefDomain();
        //如果是小区管理员，则只显示本小区内的楼宇
		
        Pager pager = new Pager(rp,page);
        String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by building.builId asc, houseNum asc";
	}
	
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
        
        /* convert ugrList instance to JsonData */
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  "+JsonConvert.toJson("page")+":\""+JsonConvert.toJson(pager.getCurrentPage())+"\",\n");
        sb.append("  "+JsonConvert.toJson("total")+":"+JsonConvert.toJson(pager.getRowsCount())+",\n");
    	sb.append("  "+JsonConvert.toJson("rows")+":[\n");
        Iterator<?> ite = houseList.iterator();
        while(ite.hasNext()){
            House house = (House)ite.next();
            sb.append("    {"+JsonConvert.toJson("id")+":\""+JsonConvert.toJson(house.getHouseId())+"\",");
            sb.append(JsonConvert.toJson("cell")+":{");
            sb.append(JsonConvert.toJson("project")+":"+JsonConvert.toJson(house.getBuilding().getProject().getProName())+",");
            sb.append(JsonConvert.toJson("houseNum")+":"+JsonConvert.toJson(house.getHouseNum())+",");
            sb.append(JsonConvert.toJson("houseArea")+":"+JsonConvert.toJson(house.getHouseArea())+",");
            sb.append(JsonConvert.toJson("houseDesc")+":"+JsonConvert.toJson(house.getHouseDesc())+",");
            sb.append(JsonConvert.toJson("condoFeeRate")+":"+JsonConvert.toJson(house.getCondoFeeRate())+",");
            sb.append(JsonConvert.toJson("cycleMonth")+":"+JsonConvert.toJson(house.getCycleMonth())+",");
            sb.append(JsonConvert.toJson("isempty")+":"+JsonConvert.toJson(house.isIsempty())+"}},\n");
        }
        if(houseList.size()!=0)sb.deleteCharAt(sb.length()-2);
        sb.append("  ]\n}");
        JsonConvert.output(sb.toString());		
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRp() {
        return rp;
    }

    public void setRp(Integer rp) {
        this.rp = rp;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
    
}
