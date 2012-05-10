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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.BuildingImport;
import org.pmp.service.business.IBuildingService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
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
	private Integer currentPage;
	private Integer pageSize;
	
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
	
	public void loadBuildingList(){
		logger.debug("进入loadBuildingList方法");
		Pager pager = new Pager(pageSize,currentPage);
		List buildingList = null;
		if(projectId==-1){
			buildingList = buildingService.loadBuildingList(pager);
		}else{ 
			buildingList = buildingService.loadBuildingListByProject(pager, projectId);
		}
		logger.debug("得到的buildingList为"+buildingList.toString());
		
		String data = JsonConvert.list2Json(pager, buildingList, "org.pmp.vo.Building");
		JsonConvert.output(data);
	}
	
	public void getBuildingByProject(){
		Pager pager = new Pager(100,1);
		List buildingList = buildingService.loadBuildingListByProject(pager, projectId);
		if(buildingList!=null && buildingList.size()!=0)
		{
			List show = new ArrayList<String>();
			show.add("builId");
			show.add("builNum");
			String data = JsonConvert.list2Json(buildingList, "org.pmp.vo.Building", show);
			JsonConvert.output(data);
		}
		else
		{
			System.out.println("data is null");
		}
	}
	
	public String uploadFile(){
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
				List buildingList = BuildingImport.buildingList(is,isError,errorPath);
				buildingService.batchSaveBuilding(buildingList);
				HttpServletRequest request = ServletActionContext.getRequest();
			    request.setAttribute("errorPath", errorPath);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isError.toString().equals("是")){
				return ERROR;
			}
		return SUCCESS;
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
