/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午05:55:32
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.MyJson;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.CondoFeeItem;
import org.pmp.vo.Project;
import org.pmp.vo.Building;
import org.pmp.vo.House;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeItemAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static final long serialVersionUID = -523253506798519261L;
    private static Logger logger = Logger.getLogger(CondoFeeItemAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private ICondoFeeItemService condoFeeItemService;
    private IProjectService projectService;
    private IBuildingService buildingService;
    private IHouseService houseService;
    
    private CondoFeeItem condoFeeItem;
    private Integer proId;
    
    /* used by the action cf_item_preview */
    private Integer itemYear;
    private String months;
    
    /* used by the action cf_item_add */
    private String[] itemMonth;
    
    /* used when deleteCondoFeeItem */
    private Integer cfiId;
    
    //~ Methods ========================================================================================================
    public void previewItemInfo(){
	StringBuffer sb = new StringBuffer();
	Project project = projectService.getProjectByID(proId);
	Pager pager = new Pager(1000,1);
	List<Building> builList = buildingService.loadBuildingList_ByProject(proId, new HashMap<String,Object>(), "", pager);
	
	/* set the response string */
	sb.append("小区名称："+project.getProName()+"    楼宇数量："+builList.size()+"幢"+"    时间："
		+itemYear+"年"+months+"月    ");
	Integer houseCount = 0;
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("isempty", "已入住");
	Iterator<Building> ite = builList.iterator();
	while(ite.hasNext()){
	    Building building = (Building)ite.next();
	    List<House> houseList = houseService.loadHouseList_ByBuilding(building.getBuilId(), params, "", pager);
	    houseCount += houseList.size();
	}
	Integer monthCount = (months.split(",")).length;
	sb.append("业主数量："+houseCount+"    生成物业费记录数目："+houseCount*monthCount);
	MyJson.print("{\"info\":"+JsonConvert.toJson(sb.toString())+"}");
    }
    
    public void check_Month(){  
    	Pager pager = new Pager(10000,1);
    	List<?> condoFeeItemList =condoFeeItemService.loadCondoFeeItemListBy_ProID(pager, proId);
    	boolean fail = false;
    	for(int i=0;i<condoFeeItemList.size();++i)
    	{
    	    CondoFeeItem condoFeeItem = (CondoFeeItem)condoFeeItemList.get(i);
    	    if(condoFeeItem.getItemYear().equals(itemYear)){
    		String items[] = condoFeeItem.getItemMonth().split(",");
    		String items_js[]=months.split(",");
    		for(int item_i=0;item_i<items_js.length;item_i++){
    		    for(int item_j=0;item_j<items.length;++item_j){
    			if(items_js[item_i].equals(items[item_j])){
    			    fail = true;
    			    break;
    			}
    		    }
    		    if(fail)break;	
    		}
    		if(fail)break;
    	    }
    	    else continue;
    	}
    	MyJson json = new MyJson();
    	Map<String, Object> params = new HashMap<String,Object>();
    	if(fail){
    	    params.put("result", "Failed");
    	}
    	else{
    	    params.put("result", "Success");
    	}
    	json.output(json.toJson(params));
    }
    
    
    public String addCondoFeeItem(){
	/* set the generatePerson with the value retrieved from SessionHandler */
	condoFeeItem.setGeneratePerson(SessionHandler.getUser().getUsername());
	/* set generateTime */
	condoFeeItem.setGenerateTime(new Date());
	/* set itemMonth */
	StringBuilder months = new StringBuilder();
	for (int i=0;i<itemMonth.length;i++){
	    months.append(itemMonth[i]+",");
	}
	months.deleteCharAt(months.length()-1);
	condoFeeItem.setItemMonth(months.toString());
	/* set project */
	Project project = projectService.getProjectByID(proId);
	condoFeeItem.setProject(project);
	
	/* set itemName by merge the proName,itemYear,itemMonth */
	condoFeeItem.setItemName(condoFeeItem.getItemYear()+"年"
		+months+"月物业费清单");
	
	/* save condoFeeItem instance */
	/* the method addCondoFeeItem of condoFeeItemService did two things: */
	/* First, save the condoFeeItem instance into table tb_CondoFeeItem */
	/* Second, call the procedure to generate the condoFee list */
	condoFeeItemService.addCondoFeeItem(condoFeeItem);
	
	return SUCCESS;
    }
    
    public void deleteCondoFeeItem(){
    	Map<String,String> params = new HashMap<String, String>();
	condoFeeItemService.deleteCondoFeeItem(condoFeeItemService.getCondoFeeItemByID(cfiId));
	MyJson json = new MyJson();
	params.put("msg", "物业费项目删除成功");
	MyJson.print(json.toJson(params));
    }
    
    //~ Getters and Setters ============================================================================================

    public ICondoFeeItemService getCondoFeeItemService() {
        return condoFeeItemService;
    }

    public void setCondoFeeItemService(ICondoFeeItemService condoFeeItemService) {
        this.condoFeeItemService = condoFeeItemService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    public IBuildingService getBuildingService() {
        return buildingService;
    }

    public void setBuildingService(IBuildingService buildingService) {
        this.buildingService = buildingService;
    }

    public IHouseService getHouseService() {
        return houseService;
    }

    public void setHouseService(IHouseService houseService) {
        this.houseService = houseService;
    }

    public CondoFeeItem getCondoFeeItem() {
        return condoFeeItem;
    }

    public void setCondoFeeItem(CondoFeeItem condoFeeItem) {
        this.condoFeeItem = condoFeeItem;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String[] getItemMonth() {
        return itemMonth;
    }

    public void setItemMonth(String[] itemMonth) {
        this.itemMonth = itemMonth;
    }

    public Integer getItemYear() {
        return itemYear;
    }

    public void setItemYear(Integer itemYear) {
        this.itemYear = itemYear;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Integer getCfiId() {
        return cfiId;
    }

    public void setCfiId(Integer cfiId) {
        this.cfiId = cfiId;
    }
}
