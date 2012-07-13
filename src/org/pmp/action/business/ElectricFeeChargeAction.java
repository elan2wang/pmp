/**
 * Author            : Elan
 * Created On        : 2012-6-24 下午10:33:50
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.MyJson;
import org.pmp.service.business.IElectricFeeChargeService;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.ElectricFeeCharge;
import org.pmp.vo.Owner;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeChargeAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger
	    .getLogger(ElectricFeeChargeAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IElectricFeeChargeService electricFeeChargeService;
    private IHouseOwnerService houseOwnerService;
    
    private ElectricFeeCharge electricFeeCharge;
    private Integer houseId;
    private Integer efcId;
    
    //~ Methods ========================================================================================================
    public void addElectricFeeCharge(){
	electricFeeCharge.setChargeTime(new Date());
	electricFeeCharge.setInputPerson(SessionHandler.getUser().getRealname());
	electricFeeCharge.setHouseOwner(houseOwnerService.getHouseOwner_ByHouse(houseId));
	electricFeeChargeService.addElectricFeeCharge(electricFeeCharge);
    }
    
    public void deleteElectricFeeCharge(){ 
	electricFeeChargeService.deleteElectricFeeCharge(electricFeeChargeService.getElectricFeeCharge_ByID(efcId));
	Map<String,String> result = new HashMap<String, String>();
	MyJson json = new MyJson();
	result.put("msg", "充值记录删除成功");
	json.output(json.toJson(result));
    }
    
    public String loadElectricFeeChargeList_ByHouse(){
	List<?> efcList= electricFeeChargeService.loadElectricFeeChargeList_ByHouse(houseId, new HashMap<String, Object>(), "", new Pager(1000,1));
	
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
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("efcList", efcList);
	request.setAttribute("title", title);
	return SUCCESS;
    }
    
    //~ Getters and Setters ============================================================================================
    public IElectricFeeChargeService getElectricFeeChargeService() {
        return electricFeeChargeService;
    }

    public void setElectricFeeChargeService(
    	IElectricFeeChargeService electricFeeChargeService) {
        this.electricFeeChargeService = electricFeeChargeService;
    }

    public IHouseOwnerService getHouseOwnerService() {
        return houseOwnerService;
    }

    public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }

    public ElectricFeeCharge getElectricFeeCharge() {
        return electricFeeCharge;
    }

    public void setElectricFeeCharge(ElectricFeeCharge electricFeeCharge) {
        this.electricFeeCharge = electricFeeCharge;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getEfcId() {
        return efcId;
    }

    public void setEfcId(Integer efcId) {
        this.efcId = efcId;
    }

}
