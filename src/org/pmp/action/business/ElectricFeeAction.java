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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IElectricFeeChargeService;
import org.pmp.service.business.IElectricFeeItemService;
import org.pmp.service.business.IElectricFeeService;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.util.Pager;
import org.pmp.vo.ElectricFee;
import org.pmp.vo.ElectricFeeCharge;
import org.pmp.vo.ElectricFeeItem;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeAction extends BaseAction {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeAction.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IElectricFeeService electricFeeService;
    private IHouseOwnerService houseOwnerService;
    private IElectricFeeItemService electricFeeItemService;
    private IElectricFeeChargeService electricFeeChargeService;
    
    private Integer efiId;
    private Integer comId;
    private Integer proId;
    private Integer houseId;
    
    private String idStr;
    private String action;
    
    private String[] proMeterFee;
    private String[] liftMeterFee;
   
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void loadElectricFeeList_ByEFI(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	/* invoke service to get list */
	List<ElectricFee> efList = electricFeeService.loadElectricFeeList_ByEFI(efiId, params, order, pager);
	
	/* 设置显示在表格上方的信息 */
	StringBuilder title = new StringBuilder();
	ElectricFeeItem efi = electricFeeItemService.getElectricFeeItemByID(efiId);
	title.append("电费总额:"+efi.getTotalMoney()+"元");
	
	/* transfer list to JsonData */
	String[] attrs = {"efId","houseOwner.house.building.project.proName","houseOwner.house.houseNum","houseOwner.owner.ownerName","proMeterFee","liftMeterFee","totalMoney"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(efList, title.toString(), pager);
	logger.debug(data);
	json.output(data);
    }
    
    public void loadElectricFeeList_ByHouse(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	/* invoke service to get list */
	List<ElectricFee> efList= electricFeeService.loadElectricFeeList_ByHouse(houseId, params, order, pager);
	
	String[] attrs = {"efId","electricFeeItem.beginDate","electricFeeItem.endDate","proMeterFee","liftMeterFee","totalMoney"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	/* 获取业主姓名和联系方式 */
	Owner owner = houseOwnerService.getHouseOwner_ByHouse(houseId).getOwner();
	String contact = null;
	if (owner.getMobile()!=null && owner.getHomePhone()!=null){
	    contact = owner.getMobile()+" / "+owner.getHomePhone();
	} else if (owner.getMobile()!=null && owner.getHomePhone()==null){
	    contact = owner.getMobile();
	} else if (owner.getMobile()==null && owner.getHomePhone()!=null){
	    contact = owner.getHomePhone();
	} else {
	    contact = "无";
	}
	/* 计算总共已缴纳的电费 */
	List<ElectricFeeCharge> list = electricFeeChargeService.loadElectricFeeChargeList_ByHouse(houseId, new HashMap<String,Object>(), "", new Pager(10000,1));
	Iterator<ElectricFeeCharge> ite = list.iterator();
	Double totalChargeMoney = 0.0;
	while(ite.hasNext()){
	    ElectricFeeCharge efc = ite.next();
	    totalChargeMoney += efc.getChargeMoney();
	}
	/* 计算总共需缴纳的电费 */
	List<ElectricFee> efList2 = electricFeeService.loadElectricFeeList_ByHouse(houseId, new HashMap<String, Object>(), "", new Pager(10000,1));
	Iterator<ElectricFee> ite2 = efList2.iterator();
	Double totalMoney = 0.0;
	while(ite2.hasNext()){
	    ElectricFee ef = ite2.next();
	    totalMoney += ef.getTotalMoney();
	}
	DecimalFormat df=new DecimalFormat("#.## ");
	String title = "业主姓名："+owner.getOwnerName()+",&nbsp;&nbsp;&nbsp;&nbsp;联系电话："+contact+
	               "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已缴电费总计："+totalChargeMoney+"元,&nbsp;&nbsp;&nbsp;&nbsp;需缴电费总计："+totalMoney+"元,&nbsp;&nbsp;&nbsp;&nbsp;余额："+df.format(totalChargeMoney-totalMoney)+"元";
	String data = json.toJson(efList, title, pager);
	json.output(data);
    }
    
    public void efDelete(){
	List<ElectricFee> efList = new ArrayList<ElectricFee>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    ElectricFee ef = electricFeeService.getElectricFee_ByID(Integer.parseInt(checkedID[i]));
	    efList.add(ef);
	}
	electricFeeService.batchDeleteElectricFee(efList);
    }
    
    public void efEdit(){
	List<ElectricFee> efList = new ArrayList<ElectricFee>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    ElectricFee ef = electricFeeService.getElectricFee_ByID(Integer.parseInt(checkedID[i].trim()));
	    ef.setProMeterFee(Double.parseDouble(proMeterFee[i].trim()));
	    ef.setLiftMeterFee(Double.parseDouble(liftMeterFee[i].trim()));
	    ef.setTotalMoney(ef.getProMeterFee()+ef.getLiftMeterFee());
	    efList.add(ef);
	}
	electricFeeService.batchEditElectricFee(efList);
    }
    
    public String selectElectricFee(){
	List<ElectricFee> efList = new ArrayList<ElectricFee>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    ElectricFee ef = electricFeeService.getElectricFee_ByID(Integer.parseInt(checkedID[i]));
	    efList.add(ef);
	}
	logger.debug("efList.size="+efList.size());
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("efList", efList);
	
	if (action.equals("edit")) return "edit";
	
	return SUCCESS;
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

    public IElectricFeeItemService getElectricFeeItemService() {
        return electricFeeItemService;
    }

    public void setElectricFeeItemService(
    	IElectricFeeItemService electricFeeItemService) {
        this.electricFeeItemService = electricFeeItemService;
    }

    public IElectricFeeChargeService getElectricFeeChargeService() {
        return electricFeeChargeService;
    }

    public void setElectricFeeChargeService(
    	IElectricFeeChargeService electricFeeChargeService) {
        this.electricFeeChargeService = electricFeeChargeService;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String[] getProMeterFee() {
        return proMeterFee;
    }

    public void setProMeterFee(String[] proMeterFee) {
        this.proMeterFee = proMeterFee;
    }

    public String[] getLiftMeterFee() {
        return liftMeterFee;
    }

    public void setLiftMeterFee(String[] liftMeterFee) {
        this.liftMeterFee = liftMeterFee;
    }
}
