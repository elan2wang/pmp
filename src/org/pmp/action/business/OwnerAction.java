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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.OwnerImport;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.IMemberService;
import org.pmp.service.business.IOwnerService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
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
    private IProjectService projectService;
    
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
    
    /* used when getOwnerInfo */
    private Integer ownerId;
    
    /* used when deleteOwner */
    private String idStr; 
    
    /* used when import owner data */
    private File ownerFile;
    private String ownerFileFileName;
    private String ownerFileContentType;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
    //~ Methods ========================================================================================================
    public String addOwner(){
	/* set owner description */
	String ownerDesc=projectName+","+buildingNum+","+houseNum;
	owner.setOwnerDesc(ownerDesc);
	owner.setHouseNum(houseNum);
	
	/* set owner's membership list */
	List<Member> list = new ArrayList<Member>();
	for (int i=0; i<memberName.length; i++){
	    if(memberName[i].equals("") || memberName[i] == null)continue;
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
	return SUCCESS;
    }
    
    public String editOwner(){
	/* set owner description */
	String ownerDesc=projectName+","+buildingNum+","+houseNum;
	owner.setOwnerDesc(ownerDesc);
	owner.setHouseNum(houseNum);
	
	/* set owner's membership list */
	List<Member> list = new ArrayList<Member>();
	for (int i=0; i<memberName.length; i++){
	    if(memberName[i].equals("") || memberName[i] == null)continue;
	    Member mem = new Member();
	    mem.setMemName(memberName[i]);
	    mem.setMemRelation(memberRelation[i]);
	    mem.setMemIdentity(cardNum[i]);
	    mem.setMemPhone(phoneNum[i]);
	    list.add(mem);
	}
	ownerService.editOwner(owner, list, houseId);
	return SUCCESS;
    }
    
    public void deleteOwner(){
	List<Owner> ownerList = new ArrayList<Owner>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    Owner owner = ownerService.getOwner_ById(Integer.parseInt(checkedID[i]));
	    ownerList.add(owner);
	}
	ownerService.batchDelete(ownerList);
    }
    
    public String getOwnerInfo(){
	Owner owner = ownerService.getOwner_ById(ownerId);
	List<?> list = memberService.loadMemberList_ByOwner(ownerId);
	
	Object obj = SessionHandler.getUserRefDomain();
	String objName = obj.getClass().getName();
	HouseOwner ho = houseOwnerService.getHouseOwner_ByOwner(ownerId);
	House hou = ho.getHouse();
	Building bui = hou.getBuilding();
	Project pro = bui.getProject();		
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("owner", owner);
	request.setAttribute("memberList", list);
	request.setAttribute("house", hou);
	request.setAttribute("building", bui);
	request.setAttribute("project", pro);
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
	
	/* retrieve user's refDomain and set proId */
	List<?> cfList = new ArrayList<Owner>();
	Object obj = SessionHandler.getUserRefDomain();
	if (obj instanceof Project){
	    cfList = ownerService.loadOwnerList_ByPro(((Project)obj).getProId(), params, order, pager);
	}
	if (obj instanceof Company){
	    cfList = ownerService.loadOwnerList_ByCom(((Company)obj).getComId(), params, order, pager);
	}
	
	String[] attrs = {"ownerName","gender","mobile","houseNum","houseArea","organization"};
	List<String> show = Arrays.asList(attrs);
	String data = JsonConvert.list2FlexJson(pager, cfList, "org.pmp.vo.Owner", show);
	
	logger.debug(data);
	JsonConvert.output(data);
	
    }
    
    public void importOwner() throws Exception{
	HttpServletRequest request = ServletActionContext.getRequest();
        String message = null;
	if(!MyfileUtil.validate(ownerFileFileName,"xls")){
	    logger.debug("文件格式不对");
	    String postfix = MyfileUtil.getPostfix(ownerFileFileName);
	    message = postfix+"类型的文件暂不支持，请选择xls类型文件";
	    request.setAttribute("message", message);
	    JsonConvert.output("{\"error\":\"filetype_error\",\"msg\":"+JsonConvert.toJson(message)+"}");
	    return;
	}
	/* create the dir to store error data */
	MyfileUtil.createDir("error_data");
	/* create the error data file in this dir */
	String fileName = MyfileUtil.createFilename();
	String fullName = ServletActionContext.getServletContext().getRealPath("error_data")+"\\"+fileName+".xls";
	String downLoad = ServletActionContext.getServletContext().getContextPath()+"/error_data/"+fileName+".xls";
	OutputStream os = new FileOutputStream(fullName);
	
	/* import data from the upload file and store in the cfList */
	List<Owner> ownerList = new ArrayList<Owner>();
	Boolean hasError = OwnerImport.execute(new FileInputStream(ownerFile), os, ownerList);
	/* close OutputStream */
	os.flush();os.close();
	
	logger.debug("ownerList.size="+ownerList.size());
	
	/* call the method batchSetOughtMoney to update the condoFee*/
	ownerService.batchSave(ownerList);
	
	/* if there are some mistakes of the file */
	if (hasError){
	    message = "记录有错误,正确数据已导入，请下载错误数据<a href=\""+downLoad+"\">下载</a>";
	    JsonConvert.output("{\"error\":\"record_error\",\"msg\":"+JsonConvert.toJson(message)+"}");
	    return;
	}
	
	/* data import success */
	message = "数据导入成功";
	JsonConvert.output("{\"error\":\"\",\"msg\":"+JsonConvert.toJson(message)+"}");
	return;
    }
    
    //~ Getters and Setters ============================================================================================
    public IOwnerService getOwnerService() {
        return ownerService;
    }

    public void setOwnerService(IOwnerService ownerService) {
        this.ownerService = ownerService;
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

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
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

    public File getOwnerFile() {
        return ownerFile;
    }

    public void setOwnerFile(File ownerFile) {
        this.ownerFile = ownerFile;
    }

    public String getOwnerFileFileName() {
        return ownerFileFileName;
    }

    public void setOwnerFileFileName(String ownerFileFileName) {
        this.ownerFileFileName = ownerFileFileName;
    }

    public String getOwnerFileContentType() {
        return ownerFileContentType;
    }

    public void setOwnerFileContentType(String ownerFileContentType) {
        this.ownerFileContentType = ownerFileContentType;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        OwnerAction.logger = logger;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

}
