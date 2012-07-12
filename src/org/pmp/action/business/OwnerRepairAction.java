/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:38:10
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.IOperateDetailService;
import org.pmp.service.business.IOwnerRepairService;
import org.pmp.service.business.IRepairFeeService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.OperateDetail;
import org.pmp.vo.OwnerRepair;
import org.pmp.vo.Project;
import org.pmp.vo.RepairFee;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerRepairAction extends BaseAction{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerRepairAction.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IOwnerRepairService ownerRepairService;
    private IRepairFeeService repairFeeService;
    private IOperateDetailService operateDetailService;
    private IHouseOwnerService houseOwnerService;
    
    private OwnerRepair ownerRepair;
    private OperateDetail operateDetail;
    
    private Integer houseId;
    private Integer opId;
    private Integer rfId;
    private String idStr;
    
    /* 对应表单的费用项 */
    private String[] itemName;
    private Integer[] itemAmount;
    private String[] itemMoney;
    private String[] itemComment;
    
    //~ Methods ========================================================================================================
    public String addOwnerRepair(){
	/* 创建维修单 */
	ownerRepair.setHouseOwner(houseOwnerService.getHouseOwner_ByHouse(houseId));
	ownerRepair.setState("等待派单");
	ownerRepair.setAccepted(false);
	ownerRepair.setLaborFee(0.0);
	ownerRepair.setMaterialFee(0.0);
	ownerRepair.setTotalFee(0.0);
	ownerRepairService.addOwnerRepair(ownerRepair);
	/* 创建操作记录 */
	OperateDetail operate = new OperateDetail();
	operate.setOperatePerson(SessionHandler.getUser().getRealname());
	operate.setOperateTime(new Date());
	operate.setOwnerRepair(ownerRepair);
	operate.setOperateDetail("新建维修项目");
	operateDetailService.addOperateDetail(operate);
	return SUCCESS;
    }
    
    public String getOwnerRepairByID(){
	OwnerRepair ownerRepair = ownerRepairService.getOwnerRepair_ByID(opId);
	List<OperateDetail> odList = operateDetailService.loadOperateDetailList_ByOP(opId);
	List<RepairFee> rfList = repairFeeService.loadRepairFeeList_ByOP(opId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("ownerRepair", ownerRepair);
	request.setAttribute("odList", odList);
	request.setAttribute("rfList", rfList);
	if(rfList.size()==0){
	    request.setAttribute("showLaborFee","true");
	} else {
	    request.setAttribute("showLaborFee","false");
	}
	return SUCCESS;
    }
    
    public String editOwnerRepair(){
	logger.debug("opId="+ownerRepair.getOpId());
	OwnerRepair repair = ownerRepairService.getOwnerRepair_ByID(ownerRepair.getOpId());
	//新增维修收费项目
	if(itemName!=null){
	    List<RepairFee> rfList = new ArrayList<RepairFee>();
	    Double materialFee = 0.0;
	    Double laborFee = 0.0;
	    for(int i=0;i<itemName.length;i++){
	        RepairFee rf = new RepairFee();
		rf.setRfName(itemName[i].trim());
		rf.setAmount(itemAmount[i]);
		rf.setMoney(Double.parseDouble(itemMoney[i].trim()));
		rf.setComment(itemComment[i].trim());
		rf.setOwnerRepair(ownerRepair);
	        rfList.add(rf);
		//计算人工费和材料费
		if(itemName[i].equals("人工费")){
	            laborFee += Double.parseDouble(itemMoney[i].trim());
		} else {
		    materialFee += Double.parseDouble(itemMoney[i].trim());
		}
	    }
	    repairFeeService.batchAddRepairFee(rfList);
	    //修改维修单的费用信息
	    logger.debug("getLaborFee="+repair.getLaborFee());
	    ownerRepair.setLaborFee(laborFee+repair.getLaborFee());
	    ownerRepair.setMaterialFee(materialFee+repair.getMaterialFee());
	    ownerRepair.setTotalFee(ownerRepair.getLaborFee()+ownerRepair.getMaterialFee());
	} else {
	    //修改维修单的费用信息
	    ownerRepair.setLaborFee(repair.getLaborFee());
	    ownerRepair.setMaterialFee(repair.getMaterialFee());
	    ownerRepair.setTotalFee(repair.getTotalFee());
	}
	
	//修改维修单信息
	ownerRepair.setHouseOwner(repair.getHouseOwner());
	ownerRepairService.editOwnerRepair(ownerRepair);
	
	//新增操作记录
	operateDetail.setOperatePerson(SessionHandler.getUser().getRealname());
	operateDetail.setOperateTime(new Date());
	operateDetail.setOwnerRepair(ownerRepair);
	operateDetailService.addOperateDetail(operateDetail);
	
	return SUCCESS;
    }
    
    public void loadOwnerRepairList(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	
	/* 根据用户权限获取数据 */
	List<OwnerRepair> list = new ArrayList<OwnerRepair>();
	Object refDomain = SessionHandler.getUserRefDomain();
	if (refDomain instanceof Company){
	    Integer comId = ((Company)refDomain).getComId();
	    list = ownerRepairService.loadOwnerRepairList_ByCompany(comId, params, order, pager);
	    logger.debug("list.size="+list.size());
	} else if(refDomain instanceof Project){
	    Integer proId = ((Project)refDomain).getProId();
	    list = ownerRepairService.loadOwnerRepairList_ByProject(proId, params, order, pager);
	} else {
	    throw new RuntimeException("访问该模块的必须是小区或物业公司用户，但是您不是！！");
	}
	
	/* 调用MyJson将列表数据转换成Json数据 */
	String[] attrs = {"opId","opNum","houseOwner.house.building.project.proName","houseOwner.house.houseNum",
		"applyPerson","contactPhone","repairType","applyTime","state","laborFee","materialFee","totalFee"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(list, "", pager);
	json.output(data);
    }
    
    public void deleteOwnerRepair(){
    	Map<String,String> params = new HashMap<String, String>();
	List<OwnerRepair> list = new ArrayList<OwnerRepair>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    OwnerRepair op = ownerRepairService.getOwnerRepair_ByID(Integer.parseInt(checkedID[i]));
	    list.add(op);
	}
	ownerRepairService.batchDeleteOwnerRepair(list);
	MyJson json = new MyJson();
	params.put("msg", "业主维修记录删除成功");
	MyJson.print(json.toJson(params));
    }
    
    public void deleteRepairFee(){
	//删除维修费用
	RepairFee rf = repairFeeService.getRepairFee_ByID(rfId);
	repairFeeService.deleteRepairFee(rf);
	//更新维修单的总费用、人工费或材料费
	OwnerRepair op = rf.getOwnerRepair();
	if(rf.getRfName().equals("人工费")){
	    op.setLaborFee(op.getLaborFee()-rf.getMoney());
	} else {
	    op.setMaterialFee(op.getMaterialFee()-rf.getMoney());
	}
	op.setTotalFee(op.getTotalFee()-rf.getMoney());
	ownerRepairService.editOwnerRepair(op);
    }
    
    //~ Getters and Setters ============================================================================================
    public IOwnerRepairService getOwnerRepairService() {
        return ownerRepairService;
    }


    public void setOwnerRepairService(IOwnerRepairService ownerRepairService) {
        this.ownerRepairService = ownerRepairService;
    }


    public IHouseOwnerService getHouseOwnerService() {
        return houseOwnerService;
    }


    public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }


    public IRepairFeeService getRepairFeeService() {
        return repairFeeService;
    }

    public void setRepairFeeService(IRepairFeeService repairFeeService) {
        this.repairFeeService = repairFeeService;
    }

    public IOperateDetailService getOperateDetailService() {
        return operateDetailService;
    }

    public void setOperateDetailService(IOperateDetailService operateDetailService) {
        this.operateDetailService = operateDetailService;
    }

    public OwnerRepair getOwnerRepair() {
        return ownerRepair;
    }
    
    public void setOwnerRepair(OwnerRepair ownerRepair) {
        this.ownerRepair = ownerRepair;
    }

    public OperateDetail getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(OperateDetail operateDetail) {
        this.operateDetail = operateDetail;
    }


    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Integer getRfId() {
        return rfId;
    }

    public void setRfId(Integer rfId) {
        this.rfId = rfId;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String[] getItemName() {
        return itemName;
    }


    public void setItemName(String[] itemName) {
        this.itemName = itemName;
    }

    public String[] getItemMoney() {
        return itemMoney;
    }


    public void setItemMoney(String[] itemMoney) {
        this.itemMoney = itemMoney;
    }


    public String[] getItemComment() {
        return itemComment;
    }


    public void setItemComment(String[] itemComment) {
        this.itemComment = itemComment;
    }

    public Integer[] getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer[] itemAmount) {
        this.itemAmount = itemAmount;
    }

}
