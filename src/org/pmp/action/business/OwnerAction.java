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
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.OwnerExport;
import org.pmp.excel.OwnerImport;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.IMemberService;
import org.pmp.service.business.IOwnerService;
import org.pmp.service.business.IProjectService;
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

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerAction extends BaseAction{

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
	Map<String,String> result = new HashMap<String, String>();
	MyJson json = new MyJson();
	result.put("msg", "业主删除成功");
	json.output(json.toJson(result));
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
    
    public void loadOwnerList(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	
	/* retrieve user's refDomain and set proId */
	List<Map<String, Object>> list = null;
	Object obj = SessionHandler.getUserRefDomain();
	
	/* ========  已弃用，查询效率低 ============================================
	List<HouseOwner> hoList = new ArrayList<HouseOwner>();
	if (obj instanceof Project){
	    hoList = houseOwnerService.loadHouseOwnerList_ByPro(((Project)obj).getProId(), params, order, pager);
	}
	if (obj instanceof Company){
	    hoList = houseOwnerService.loadHouseOwnerList_ByCom(((Company)obj).getComId(), params, order, pager);
	}
	
	String[] attrs = {"owner.ownerId","house.building.project.proName","owner.ownerName","owner.gender","owner.mobile","owner.homePhone",
		          "house.houseNum","house.houseArea","owner.organization"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(hoList, "", pager);
	========  已弃用，查询效率低 ============================================*/
	
	if (obj instanceof Project){
	    list = houseOwnerService.loadOwnerList_ByPro(((Project)obj).getProId(), params, order, pager);
	} else if (obj instanceof Company){
	    list = houseOwnerService.loadOwnerList_ByCom(((Company)obj).getComId(), params, order, pager);
	}
	MyJson json = new MyJson();
	String data = json.toJson(list, "",pager);
	
	logger.debug(data);
	json.output(data);
	
    }
    
    public void importOwner() throws Exception{
	Map<String, Object> params = new LinkedHashMap<String,Object>();
	String data = null;
	MyJson json = new MyJson();
        String message = null;
	if(!MyfileUtil.validate(ownerFileFileName,"xls")){
	    logger.debug("文件格式不对");
	    String postfix = MyfileUtil.getPostfix(ownerFileFileName);
	    message = postfix+"类型的文件暂不支持，请选择xls类型文件";
	    params.put("error", "filetype_error");
	    params.put("msg", message);
	    data = json.toJson(params);
	    MyJson.print(data);
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
	    params.put("error", "record_error");
	    params.put("msg", message);
	    data = json.toJson(params);
	    MyJson.print(data);
	    return;
	}
	
	/* data import success */
	message = "数据导入成功";
	params.put("error", "");
	params.put("msg", message);
	data = json.toJson(params);
	MyJson.print(data);
	return;
    }
    
    public void exportOwner() throws IOException{
		Pager pager = new Pager(100000,1);
		Map<String,Object> params = getParams();
		String order = null;
		List<HouseOwner> list = null;
		Object obj = SessionHandler.getUserRefDomain();
		if (obj instanceof Project){
		    list = houseOwnerService.loadHouseOwnerList_ByPro(((Project)obj).getProId(), params, order, pager);
		} else if (obj instanceof Company){
		    list = houseOwnerService.loadHouseOwnerList_ByCom(((Company)obj).getComId(), params, order, pager);
		}
		MyfileUtil.createDir("download");
		String fileName = MyfileUtil.createFilename();
		String fullName = ServletActionContext.getServletContext().getRealPath("download")+"\\"+fileName+".xls";
		String downLoad = ServletActionContext.getServletContext().getContextPath()+"/download/"+fileName+".xls";
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/vnd.ms-excel;charset=gb2312");
//		response.setHeader("Content-Disposition", "attachment;filename=Owner.xls");
		OutputStream os = new FileOutputStream(fullName);
		OwnerExport.execute(os, list);
		Map<String, Object> params2 = new LinkedHashMap<String,Object>();
		params2.put("download_link", downLoad);
		MyJson json = new MyJson();
		json.output(json.toJson(params2));
	}
    
    //~ Getters and Setters ============================================================================================
    public IOwnerService getOwnerService() {
        return ownerService;
    }

    public void setOwnerService(IOwnerService ownerService) {
        this.ownerService = ownerService;
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
