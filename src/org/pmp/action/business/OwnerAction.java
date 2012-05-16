/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午02:03:06
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

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IMemberService;
import org.pmp.service.business.IOwnerService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Building;
import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IOwnerService ownerService;
    private IMemberService memberService;
    private IHouseOwnerService houseOwnerService;
    
    /* used when saveOwner or editOwner */
    private Owner owner;
    private String[] memberName;
    private String[] memberRelation;
    private String[] cardNum;
    private String[] phoneNum;
    private String projectName;
    private String buildingNum;
    private String houseNum;
    private Integer houseId;
    
    /* used when loadOwnerList_ByPro */
    private Integer proId;

    /* used when getOwnerInfo */
    private Integer ownerId;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
    //~ Methods ========================================================================================================
    public void addOwner(){
	/* set owner description */
	String ownerDesc=projectName+","+buildingNum+","+houseNum;
	owner.setOwnerDesc(ownerDesc);
	owner.setHouseNum(houseNum);
	
	/* set owner's membership list */
	List<Member> list = new ArrayList<Member>();
	for (int i=0; i<memberName.length; i++){
	    Member mem = new Member();
	    mem.setMemName(memberName[i]);
	    mem.setMemRelation(memberRelation[i]);
	    mem.setMemIdentity(cardNum[i]);
	    mem.setMemPhone(phoneNum[i]);
	    list.add(mem);
	}
	/* invoke ownerService.addOwner to save owner */
	/* also update related house info, and save houseOwner instance */
	ownerService.addOwner(owner, list, houseId);
    }
    
    public void editOwner(){
	/* set owner description */
	String ownerDesc=projectName+","+buildingNum+","+houseNum;
	owner.setOwnerDesc(ownerDesc);
	owner.setHouseNum(houseNum);
	
	/* set owner's membership list */
	List<Member> list = new ArrayList<Member>();
	for (int i=0; i<memberName.length; i++){
	    Member mem = new Member();
	    mem.setMemName(memberName[i]);
	    mem.setMemRelation(memberRelation[i]);
	    mem.setMemIdentity(cardNum[i]);
	    mem.setMemPhone(phoneNum[i]);
	    list.add(mem);
	}
	ownerService.editOwner(owner, list, houseId);
    }
    
    public String getOwnerInfo(){
	Owner owner = ownerService.getOwner_ById(ownerId);
	List<?> list = memberService.loadMemberList_ByOwner(ownerId);
	
	Object obj = SessionHandler.getUserRefDomain();
	String objName = obj.getClass().getName();
	HouseOwner ho = houseOwnerService.getHouseByOwner(owner);
	House hou = ho.getHouse();
	Building bui = hou.getBuilding();
	Project pro = bui.getProject();		
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("memberList", list);
	request.setAttribute("hou", hou);
	request.setAttribute("bui", bui);
	request.setAttribute("pro", pro);
	request.setAttribute("objName",objName);
	
	return SUCCESS;
    }
    
    public void loadOwnerList_ByPro(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by ownerId desc";
	}
	/* invoke service to get list */
	List<?> cfList = ownerService.loadOwnerList_ByPro(proId, params, order, pager);
	
	String[] attrs = {"ownerName","gender","mobile","houseNum","houseArea","organization"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2FlexJson(pager, cfList, "org.pmp.vo.Owner", show);
	
	logger.debug(data);
	JsonConvert.output(data);
	
    }
    //~ Getters and Setters ============================================================================================

    public IOwnerService getOwnerService() {
        return ownerService;
    }

    public void setOwnerService(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
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

    public IMemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(IMemberService memberService) {
        this.memberService = memberService;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String[] getMemberName() {
        return memberName;
    }

    public void setMemberName(String[] memberName) {
        this.memberName = memberName;
    }

    public String[] getMemberRelation() {
        return memberRelation;
    }

    public void setMemberRelation(String[] memberRelation) {
        this.memberRelation = memberRelation;
    }

    public String[] getCardNum() {
        return cardNum;
    }

    public void setCardNum(String[] cardNum) {
        this.cardNum = cardNum;
    }

    public String[] getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String[] phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public IHouseOwnerService getHouseOwnerService() {
        return houseOwnerService;
    }

    public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }

}
