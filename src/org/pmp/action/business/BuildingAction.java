/**
 * Author            : Jason
 * Created On        : 2012-3-27 下午05:45:43
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.BuildingImport;
import org.pmp.excel.ProjectImport;
import org.pmp.service.business.IBuildingService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingAction extends ActionSupport {
	
	static Logger logger = Logger.getLogger(BuildingAction.class.getName());
	private Pager pager;
	private Integer buildingId;
	private Integer projectId;
	private Integer thisProjectId;
	

	private Building building;
	private IBuildingService buildingService;
	private List list;
	private Integer page;
	

	private Integer rp;
	
	private File refFile;
	private String refFileFileName;
	private String refFileContentType;
	
	

	public String saveBuilding(){
		Project project = new Project();
		project.setProId(thisProjectId);
		building.setProject(project);
		buildingService.saveBuilding(building);
		return SUCCESS;
	}
	
	public String updateBuilding(){
		buildingService.updateBuilding(building);
		return SUCCESS;
	}
	
	public void deleteBuilding(){
		buildingService.deleteBuilding(buildingId);
	}
	
	public String getBuildingById(){
		logger.debug("进入getBuildingById方法");
		building = buildingService.getBuildingById(buildingId);
		return SUCCESS;
	}
	
	public void loadBuildingListBySessionHandler(){		
		logger.debug("进入loadBuildingListBySessionHandler方法");
		List buildingList = null;
		buildingList = new ArrayList<Building>();
		Object obj = SessionHandler.getUserRefDomain();
		//如果是小区管理员，则只显示本小区内的楼宇
		
		Pager pager = new Pager(rp,page);
		Pager pager2 = new Pager(10000,1);
		Map<String,Object> params = new HashMap<String,Object>();
		String order = "order by builId asc";
		if(projectId==0){		
			if(obj instanceof Project)
			{
				Project pro = (Project)obj;
				System.out.println(pro.getProName());
				buildingList = buildingService.loadBuildingList_ByProject(pro.getProId(), params, order, pager2);
			}
			else if(obj instanceof Company)
			{
				Company com = (Company)obj;
				buildingList = buildingService.loadBuildingList_ByCompany(com.getComId(), params, order, pager2);
			}
		}
		else{ 
			buildingList = buildingService.loadBuildingList_ByProject(projectId, params, order, pager2);
		}
		logger.debug("得到的buildingList为"+buildingList.toString());
		pager.setRowsCount(buildingList.size());
		String data = JsonConvert.list2FlexJson(pager, buildingList, "org.pmp.vo.Building");
		System.out.println(data);
		logger.debug(data);
		JsonConvert.output(data);
	}
	

	
	public void uploadFile()throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
	    String message = null;
	    System.out.println("proFileFileName:"+refFileFileName);

		if(!MyfileUtil.validate(refFileFileName,"xls")){
		    logger.debug("文件格式不对");
		    String postfix = MyfileUtil.getPostfix(refFileFileName);
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
		System.out.println(fullName);
		/* import data from the upload file and store in the cfList */
		List<Building> builList = new ArrayList<Building>();
		Boolean hasError = BuildingImport.execute(new FileInputStream(refFile), os, builList);
		/* close OutputStream */
		os.flush();
		os.close();
		
		/* call the method batchSetOughtMoney to update the condoFee*/
		buildingService.batchSaveBuilding(builList);
		
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
	
	/**
	 * @return the buildingId
	 */
	public Integer getBuildingId() {
		return buildingId;
	}

	/**
	 * @param buildingId the buildingId to set
	 */
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	/**
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}

	/**
	 * @return the buildingService
	 */
	public IBuildingService getBuildingService() {
		return buildingService;
	}

	/**
	 * @param buildingService the buildingService to set
	 */
	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}

	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}

	/**
	 * @return the pager
	 */
	public Pager getPager() {
		return pager;
	}

	/**
	 * @param pager the pager to set
	 */
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	/**
	 * @return the currentPage
	 */
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the rp
	 */
	public Integer getRp() {
		return rp;
	}

	/**
	 * @param rp the rp to set
	 */
	public void setRp(Integer rp) {
		this.rp = rp;
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * @return the thisProjectId
	 */
	public Integer getThisProjectId() {
		return thisProjectId;
	}

	/**
	 * @param thisProjectId the thisProjectId to set
	 */
	public void setThisProjectId(Integer thisProjectId) {
		this.thisProjectId = thisProjectId;
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
