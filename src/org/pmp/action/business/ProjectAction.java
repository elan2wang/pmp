/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����05:30:34
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
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.ProjectImport;
import org.pmp.service.business.IProjectService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectAction extends ActionSupport {
	static Logger logger = Logger.getLogger(ProjectAction.class.getName());
	
	private IProjectService projectService;
	private Project project;
	private Integer projectId;
	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private String district;
	private List projectList;
	
	private File refFile;
	private String refFileFileName;
	private String refFileContentType;


	public String addProject(){
			projectService.addProject(project);
		return SUCCESS;
	}
	
	public void deleteProject(){
		projectService.deleteProject(projectId);
	}
	
	public String updateProject(){
		projectService.editProject(project);
		return SUCCESS;
	}
	
	public void loadProjectList(){
		Pager pager = new Pager(pageSize,currentPage);
		List projectList = projectService.loadProjectList(pager);
		logger.debug("list.size="+projectList.size());
		
		String data = JsonConvert.list2Json(pager, projectList, "org.pmp.vo.Project");
		logger.debug(data);
		JsonConvert.output(data);
	}
	public void getProjectByDistrict(){
		logger.debug("进入getProjectByDistrict()");
		String name = district;
		Pager pager = new Pager(pageSize,currentPage);
		List projectList = projectService.getProjectByDistrict("普陀区", pager);
		logger.debug("list.size="+projectList.size());
		
		String data = JsonConvert.list2Json(pager, projectList, "org.pmp.vo.Project");
		logger.debug(data);
		JsonConvert.output(data);
	}
	
	public String getAllProject(){
		projectList = projectService.getAllProject();
		return SUCCESS;
	}
	
	public String getProjectById(){
		logger.debug("进入方法");
		project = projectService.getProjectByID(projectId);
		logger.debug("得到的project为:"+project.toString());
		return SUCCESS;
	}
	
	public String uploadFile(){
	if(!MyfileUtil.validate(refFileFileName,"xls")){
	    String postfix = MyfileUtil.getPostfix(refFileFileName);
	    String message = postfix+"类型的文件暂不支持，请选择xls类型文件";
	    HttpServletRequest request = ServletActionContext.getRequest();
	    request.setAttribute("message", message);
	    return "filetype_error";
	}
		boolean isError = false;
		StringBuffer errorPath = new StringBuffer();
		try {
			InputStream is = new FileInputStream(refFile);
			List projectList = ProjectImport.projectList(is,isError,errorPath);
			projectService.batchSaveProject(projectList);
			HttpServletRequest request = ServletActionContext.getRequest();
		    request.setAttribute("errorPath", errorPath);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isError){
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
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
	private String projectName;
	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
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
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the projectList
	 */
	public List getProjectList() {
		return projectList;
	}
	/**
	 * @param projectList the projectList to set
	 */
	public void setProjectList(List projectList) {
		this.projectList = projectList;
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

	public Integer getPageSize() {
	    return pageSize;
	}

	public void setPageSize(Integer pageSize) {
	    this.pageSize = pageSize;
	}

	public IProjectService getProjectService() {
	    return projectService;
	}
}
