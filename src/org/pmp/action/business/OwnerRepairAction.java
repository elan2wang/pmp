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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.IOperateDetailService;
import org.pmp.service.business.IOwnerRepairService;
import org.pmp.service.business.IRepairAttachService;
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
public class OwnerRepairAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerRepairAction.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IOwnerRepairService ownerRepairService;
    private IRepairFeeService repairFeeService;
    private IRepairAttachService repairAttachService;
    private IOperateDetailService operateDetailService;
    
    private IHouseOwnerService houseOwnerService;
    
    private OwnerRepair ownerRepair;
    private OperateDetail operateDetail;
    
    /* 对应表单的费用项 */
    private String[] itemName;
    private Integer[] itemAmount;
    private String[] itemMoney;
    private String[] itemComment;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
   
    //~ Methods ========================================================================================================
    public String addOwnerRepair(){
	
	return SUCCESS;
    }
    
    public String editOwnerRepair(){
	
	//新增维修收费项目
	List<RepairFee> rfList = new ArrayList<RepairFee>();
	for(int i=0;i<itemName.length;i++){
	    RepairFee rf = new RepairFee();
	    rf.setRfName(itemName[i].trim());
	    rf.setAmount(itemAmount[i]);
	    rf.setMoney(Double.parseDouble(itemMoney[i].toString()));
	    rf.setComment(itemComment[i].trim());
	    rf.setOwnerRepair(ownerRepair);
	    rfList.add(rf);
	}
	repairFeeService.batchAddRepairFee(rfList);
	return SUCCESS;
    }
    
    public void loadOwnerRepairList(){
	List<OwnerRepair> list = new ArrayList<OwnerRepair>();
	/* 设置查询条件  */
	Map<String, Object> params = new HashMap<String, Object>();
	String order ;
	setParams(params);
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order="order by applyTime desc";
	}
	Pager pager = new Pager(rp,page);
	
	/* 根据用户权限获取数据 */
	Object refDomain = SessionHandler.getUserRefDomain();
	if (refDomain instanceof Company){
	    Integer comId = ((Company)refDomain).getComId();
	    list = ownerRepairService.loadOwnerRepairList_ByCompany(comId, params, order, pager);
	} else if(refDomain instanceof Project){
	    Integer proId = ((Project)refDomain).getProId();
	    list = ownerRepairService.loadOwnerRepairList_ByProject(proId, params, order, pager);
	} else {
	    throw new RuntimeException("访问该模块的必须是小区或物业公司用户，但是您不是！！");
	}
	
	/* 调用MyJson将列表数据转换成Json数据 */
	String[] attrs = {"opId","opNum","applyPerson","contactPhone","repairType","applyTime","state","laborFee",
		"materialFee","totalFee"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(list, "", pager);
	json.output(data);
    }
    
    private void setParams(Map<String,Object> params){
	String[] qtypes = qtype.split(",");
	String[] querys = query.split(",");
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

    public IRepairAttachService getRepairAttachService() {
        return repairAttachService;
    }

    public void setRepairAttachService(IRepairAttachService repairAttachService) {
        this.repairAttachService = repairAttachService;
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
