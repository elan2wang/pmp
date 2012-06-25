/**
 * Author            : Elan
 * Created On        : 2012-6-23 下午01:37:41
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.action.business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IElectricFeeService;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.vo.ElectricFee;
import org.pmp.vo.Owner;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeAction.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IElectricFeeService electricFeeService;
    private IHouseOwnerService houseOwnerService;
    
    private Integer efiId;
    private Integer comId;
    private Integer proId;
    private Integer houseId;
    
    private String idStr;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void loadElectricFeeList_ByEFI(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	setParams(params);
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by houseOwner.house.houseNum asc";
	}
	/* invoke service to get list */
	List<ElectricFee> efList = electricFeeService.loadElectricFeeList_ByEFI(efiId, params, order, pager);
	
	/* transfer list to JsonData */
	String[] attrs = {"efId","houseOwner.house.building.project.proName","houseOwner.house.houseNum","houseOwner.owner.ownerName","proMeterFee","liftMeterFee","totalMoney"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(efList, "", pager);
	logger.debug(data);
	json.output(data);
    }
    
    public void loadElectricFeeList_ByHouse(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	//setParams(params);
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by electricFeeItem desc";
	}
	/* invoke service to get list */
	List<?> efList= electricFeeService.loadElectricFeeList_ByHouse(houseId, params, order, pager);
	
	String[] attrs = {"efId","electricFeeItem.beginDate","electricFeeItem.endDate","proMeterFee","liftMeterFee","totalMoney"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	Owner owner = houseOwnerService.getHouseOwner_ByHouse(houseId).getOwner();
	String contact = null;
	if (owner.getMobile()!=null && owner.getHomePhone()!=null){
	    contact = owner.getMobile()+"/"+owner.getHomePhone();
	} else if (owner.getMobile()!=null && owner.getHomePhone()==null){
	    contact = owner.getMobile();
	} else if (owner.getMobile()==null && owner.getHomePhone()!=null){
	    contact = owner.getHomePhone();
	} else {
	    contact = "无";
	}
	String title = "业主姓名："+owner.getOwnerName()+"  联系电话："+contact;
	String data = json.toJson(efList, title, pager);
	json.output(data);
    }
    
    private void setParams(Map<String,Object> params){
	String[] qtypes = qtype.split(",");
	String[] querys = query.split(",");
	for(int i=0;i<qtypes.length;i++){
	    if (!querys[i].equals("null")){
		params.put(qtypes[i], querys[i]);
	    }
	}
    }
    
    //~ Getters and Setters ============================================================================================

    public IElectricFeeService getElectricFeeService() {
        return electricFeeService;
    }

    public void setElectricFeeService(IElectricFeeService electricFeeService) {
        this.electricFeeService = electricFeeService;
    }

    public IHouseOwnerService getHouseOwnerService() {
        return houseOwnerService;
    }

    public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }

    public Integer getEfiId() {
        return efiId;
    }

    public void setEfiId(Integer efiId) {
        this.efiId = efiId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
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

}
