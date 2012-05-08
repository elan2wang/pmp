/**
 * Author            : Jason
 * Created On        : 2012-4-15 下午02:51:21
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.OwnerImport;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IMemberService;
import org.pmp.service.business.IOwnerService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class OwnerAction extends ActionSupport {
	static Logger logger = Logger.getLogger(OwnerAction.class.getName());
	
	private Owner owner;
	private House house;
	private Integer houseId;
	private String houseNum;
	private String oldHouse;

	private String[] memberName;
	private String[] memberRelation;
	private String[] cardNum;
	private String[] phoneNum;
	private Integer[] memberId;

	private String projectName;
	private String buildingName;
	

	private Integer buildingNum;
	private Integer currentPage;
	private Integer pageSize;
	private Integer ownerId;
	
	private File refFile;
	private String refFileFileName;
	private String refFileContentType;
	

	private IOwnerService ownerService;
	private IHouseOwnerService houseOwnerService;
	private IHouseService houseService;
	private IMemberService memberService;
	

	public String saveOwner(){
		System.out.println("加载owner"+owner);
		String ownerDesc=projectName+","+buildingName+","+houseNum;
		owner.setOwnerDesc(ownerDesc);
		owner.setHouseNum(houseNum);
		ownerService.addOwner(owner);
		HouseOwner houseOwner = new HouseOwner();
		House house = houseService.getHouseById(houseId);
		house.setHouseArea(owner.getHouseArea());
		house.setIsempty(false);
		houseOwner.setHouse(house);
		houseOwner.setOwner(owner);
		houseService.updateHouse(house);
		houseOwnerService.saveHouseOwner(houseOwner);
		List memberList = new ArrayList<Member>();
		for (int i=0;i<memberName.length; i++) {
			Member member = new Member();
			member.setMemName(memberName[i]);
			member.setMemRelation(memberRelation[i]);
			member.setMemIdentity(cardNum[i]);
			member.setMemPhone(phoneNum[i]);
			memberList.add(member);
		}
		memberService.batchSaveMember(memberList, owner.getOwnerId());
		return SUCCESS;
	}
	
	public void getOwnerList(){
		Pager pager = new Pager(pageSize,currentPage);
		pager.getCurrentPage();
		List ownerList = ownerService.loadOwnerList(pager);
		StringBuffer sb = new StringBuffer();
		sb.append("{\n");
		sb.append("  "+JsonConvert.toJson("RowsCount")+":"+JsonConvert.toJson(pager.getRowsCount())+",\n");
		sb.append("  "+JsonConvert.toJson("PageSize")+":"+JsonConvert.toJson(pager.getPageSize())+",\n");
		sb.append("  "+JsonConvert.toJson("CurrentPage")+":"+JsonConvert.toJson(pager.getCurrentPage())+",\n");
		sb.append("  "+JsonConvert.toJson("PagesCount")+":"+JsonConvert.toJson(pager.getPagesCount())+",\n");
		sb.append("  "+JsonConvert.toJson("Rows")+":[\n");
		Iterator ite = ownerList.iterator();
		while(ite.hasNext()){
			sb.append("    {");
			Owner owner = (Owner)ite.next();
			sb.append(JsonConvert.toJson("ownerId")+":"+JsonConvert.toJson(owner.getOwnerId().toString())+",");
			sb.append(JsonConvert.toJson("ownerName")+":"+JsonConvert.toJson(owner.getOwnerName().toString())+",");
			sb.append(JsonConvert.toJson("mobile")+":"+JsonConvert.toJson(owner.getMobile().toString())+",");
			sb.append(JsonConvert.toJson("ownerDesc")+":"+JsonConvert.toJson(owner.getOwnerDesc())+",");
			sb.deleteCharAt(sb.length()-1);
		    sb.append("},\n");
		}
		sb.deleteCharAt(sb.length()-2);
		sb.append("  ]\n}\n");
		logger.debug("Json data = "+sb.toString());
		//output the Jason data
		try {    
	            HttpServletResponse response = ServletActionContext.getResponse();  
	            response.setContentType("application/json;charset=UTF-8");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().println(sb.toString());     
	        } catch (IOException e) {                     
	            e.printStackTrace();  
	        } 
	}
	
	public void deleteOwnerById(){
		ownerService.deleteOwner(ownerId);
	}
	
	public String getOwnerById(){
		owner = ownerService.getOwnerById(ownerId);
		getProjectAndBuildingAndHouseByOwnerID();
		Owner owner = new Owner();
		owner.setOwnerId(ownerId);
		List memberList = memberService.getMemberByOwner(owner);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("memberList", memberList);
		return SUCCESS;
	}
	public String getProjectAndBuildingAndHouseByOwnerID()
	{
		HouseOwner ho = houseOwnerService.getHouseByOwner(owner);
		House hou = ho.getHouse();
		Building bui = hou.getBuilding();
		Project pro = bui.getProject();		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("hou", hou);
		request.setAttribute("bui", bui);
		request.setAttribute("pro", pro);
		System.out.println(hou.getHouseNum());
		System.out.println(bui.getBuilNum());
		System.out.println(pro.getProName());
		return SUCCESS;
	}
	
	public String updateOwner(){
		System.out.println("更新owner"+owner);
		String ownerDesc=projectName+","+buildingName+","+houseNum;
		owner.setOwnerDesc(ownerDesc);
		owner.setHouseNum(houseNum);
		ownerService.editOwner(owner);
		House house = houseService.getHouseByHouseNum(houseNum);
		house.setHouseArea(owner.getHouseArea());
		house.setIsempty(false);
		House oldHouseInfo = houseService.getHouseByHouseNum(oldHouse);
		HouseOwner houseOwner = houseOwnerService.getOwnerByHouse(oldHouseInfo);
		houseOwner.setHouse(house);
		houseOwner.setOwner(owner);
		houseService.updateHouse(house);
		houseOwnerService.updateHouseOwner(houseOwner);
		List memberList = new ArrayList<Member>();
		for (int i=0;i<memberName.length; i++) {
			Member member = new Member();
			member.setMemId(memberId[i]);
			member.setMemName(memberName[i]);
			member.setMemRelation(memberRelation[i]);
			member.setMemIdentity(cardNum[i]);
			member.setMemPhone(phoneNum[i]);
			memberList.add(member);
		}
		memberService.batchUpdateMember(memberList, owner.getOwnerId());
		return SUCCESS;
	}
	
	public String uploadFile(){
	    	Map map = new HashMap<String, House>();
		if(!MyfileUtil.validate(refFileFileName,"xls")){
		    String postfix = MyfileUtil.getPostfix(refFileFileName);
		    String message = postfix+"类型的文件暂不支持，请选择xls类型文件";
		    HttpServletRequest request = ServletActionContext.getRequest();
		    request.setAttribute("message", message);
		    return "filetype_error";
		}
		StringBuffer errorPath = new StringBuffer();
		StringBuffer isError = new StringBuffer();
		try {
			InputStream is = new FileInputStream(refFile);
			List ownerList = OwnerImport.ownerList(is,map,isError,errorPath);
			List ownerIdList = ownerService.batchSaveOwner(ownerList);
			houseOwnerService.batchAddHouseOwner(ownerIdList, map);
			HttpServletRequest request = ServletActionContext.getRequest();
		    request.setAttribute("errorPath", errorPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isError.toString().equals("是")){
			return ERROR;
		}
		return SUCCESS;

	}
	/**
	 * @return the ownerService
	 */
	public IOwnerService getOwnerService() {
		return ownerService;
	}
	/**
	 * @param ownerService the ownerService to set
	 */
	public void setOwnerService(IOwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	
	/**
	 * @return the houseOwnerService
	 */
	public IHouseOwnerService getHouseOwnerService() {
		return houseOwnerService;
	}
	/**
	 * @param houseOwnerService the houseOwnerService to set
	 */
	public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
		this.houseOwnerService = houseOwnerService;
	}
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	/**
	 * @return the house
	 */
	public House getHouse() {
		return house;
	}

	/**
	 * @param house the house to set
	 */
	public void setHouse(House house) {
		this.house = house;
	}
	/**
	 * @return the houseId
	 */
	public Integer getHouseId() {
		return houseId;
	}

	/**
	 * @param houseId the houseId to set
	 */
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	
	/**
	 * @return the houseNum
	 */
	public String getHouseNum() {
		return houseNum;
	}

	/**
	 * @param houseNum the houseNum to set
	 */
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	
	/**
	 * @param houseService the houseService to set
	 */
	public void setHouseService(IHouseService houseService) {
		this.houseService = houseService;
	}
	/**
	 * @param memberService the memberService to set
	 */
	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}
	
	/**
	 * @return the memberName
	 */
	public String[] getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String[] memberName) {
		this.memberName = memberName;
	}
	
	/**
	 * @return the memberRelation
	 */
	public String[] getMemberRelation() {
		return memberRelation;
	}

	/**
	 * @param memberRelation the memberRelation to set
	 */
	public void setMemberRelation(String[] memberRelation) {
		this.memberRelation = memberRelation;
	}

	/**
	 * @return the cardNum
	 */
	public String[] getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(String[] cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return the phoneNum
	 */
	public String[] getPhoneNum() {
		return phoneNum;
	}


	/**
	 * @return the houseService
	 */
	public IHouseService getHouseService() {
		return houseService;
	}

	/**
	 * @return the memberService
	 */
	public IMemberService getMemberService() {
		return memberService;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String[] phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * @return the ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * @return the memberId
	 */
	public Integer[] getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Integer[] memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * @return the oldHouse
	 */
	public String getOldHouse() {
		return oldHouse;
	}

	/**
	 * @param oldHouse the oldHouse to set
	 */
	public void setOldHouse(String oldHouse) {
		this.oldHouse = oldHouse;
	}
	
	/**
	 * @return the buildingNum
	 */
	public Integer getBuildingNum() {
		return buildingNum;
	}

	/**
	 * @param buildingNum the buildingNum to set
	 */
	public void setBuildingNum(Integer buildingNum) {
		this.buildingNum = buildingNum;
	}
	
	/**
	 * @return the refFile
	 */
	public File getRefFile() {
		return refFile;
	}

	/**
	 * @param refFile the refFile to set
	 */
	public void setRefFile(File refFile) {
		this.refFile = refFile;
	}

	/**
	 * @return the refFileFileName
	 */
	public String getRefFileFileName() {
		return refFileFileName;
	}

	/**
	 * @param refFileFileName the refFileFileName to set
	 */
	public void setRefFileFileName(String refFileFileName) {
		this.refFileFileName = refFileFileName;
	}

	/**
	 * @return the refFileContentType
	 */
	public String getRefFileContentType() {
		return refFileContentType;
	}

	/**
	 * @param refFileContentType the refFileContentType to set
	 */
	public void setRefFileContentType(String refFileContentType) {
		this.refFileContentType = refFileContentType;
	}
}
