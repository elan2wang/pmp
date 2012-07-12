/**
 * Author            : Jason
 * Created On        : 2012-7-3 下午10:14:17
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
import org.pmp.service.business.IComplaintService;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Complaint;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ComplaintAction extends BaseAction {
    static Logger logger = Logger.getLogger(ComplaintAction.class.getName());
	
    private IComplaintService complaintService;
    private IHouseOwnerService houseOwnerService;
	
    private Complaint complaint;
	
    private Integer compId;
    private Integer houseId;
    private String idStr;
	
    public String addComplaint(){
	HouseOwner houseOwner = new HouseOwner();
	houseOwner = houseOwnerService.getHouseOwner_ByHouse(houseId);
	complaint.setHouseOwner(houseOwner);
	complaintService.addComplaint(complaint);
	return SUCCESS;
    }
    
    public String editComplaint(){
	Complaint comp = complaintService.getComplaintByID(complaint.getCompId());
	comp.setCompTel(complaint.getCompTel());
	comp.setHandlePerson(complaint.getHandlePerson());
	comp.setHandleResult(complaint.getHandleResult());
	comp.setHandleTime(complaint.getHandleTime());
	
	complaintService.editComplaint(comp);
	return SUCCESS;
    }
    
    public void deleteComplaint(){
    Map<String,String> params = new HashMap<String, String>();
	List<Complaint> list = new ArrayList<Complaint>();
	String[] checkedID = idStr.split(",");
	for(int i=0;i<checkedID.length;i++){
	    Complaint comp = complaintService.getComplaintByID(Integer.parseInt(checkedID[i]));
	    list.add(comp);
	}
	complaintService.batchDeleteComplaint(list);
	MyJson json = new MyJson();
	params.put("msg", "用户投诉删除成功");
	MyJson.print(json.toJson(params));
    }
    
    public String getComplaintById(){
	complaint = complaintService.getComplaintByID(compId);
	return SUCCESS;
    }
    
    public void loadComplaintList(){
	List<?> complaintList = null;
	complaintList = new ArrayList<Complaint>();
	Object obj = SessionHandler.getUserRefDomain();
	
	Pager pager = getPager();
	Map<String,Object> params = getParams();
	String order = getOrder();
	
	if(obj instanceof Project){
	    Project pro = (Project)obj;
	    complaintList = complaintService.loadComplaintList_ByProject(pro.getProId(), params, order, pager);
	}else if(obj instanceof Company){
	    Company com = (Company)obj;
	    complaintList = complaintService.loadComplaintList_ByCompany(com.getComId(), params, order, pager);
	}
		
	String[] attrs = {"compId","houseOwner.house.building.project.proName","compPerson","compTel","compTime","compContent","handlePerson","handleTime"};
        List<String> show = Arrays.asList(attrs);
        Includer includer = new Includer(show);
        MyJson json = new MyJson(includer);
        String data = json.toJson(complaintList, "", pager);
        json.output(data);
    }

    //~ getters and setters =======================================================================================
    public IComplaintService getComplaintService() {
        return complaintService;
    }

    public void setComplaintService(IComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    public IHouseOwnerService getHouseOwnerService() {
        return houseOwnerService;
    }

    public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
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
    
}