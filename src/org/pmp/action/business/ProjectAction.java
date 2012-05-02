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
	private String keyWord;
	private List projectList;
	private String projectName;
	
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
		Pager pager = new Pager(pageSize,currentPage);
		List projectList = projectService.getProjectByDistrict(district, pager);
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
		StringBuffer errorPath = new StringBuffer();
		StringBuffer isError = new StringBuffer();
		try {
			InputStream is = new FileInputStream(refFile);
			List projectList = ProjectImport.projectList(is,isError,errorPath);
			projectService.batchSaveProject(projectList);
			HttpServletRequest request = ServletActionContext.getRequest();
		    request.setAttribute("errorPath", errorPath);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isError.toString().equals("是")){
			return ERROR; 
		}
		return SUCCESS;
	}
	
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public List getProjectList() {
		return projectList;
	}
	public void setProjectList(List projectList) {
		this.projectList = projectList;
	}
	
	public File getRefFile() {
		return refFile;
	}

	public void setRefFile(File refFile) {
		this.refFile = refFile;
	}

	public String getRefFileFileName() {
		return refFileFileName;
	}

	public void setRefFileFileName(String refFileFileName) {
		this.refFileFileName = refFileFileName;
	}

	public String getRefFileContentType() {
		return refFileContentType;
	}

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
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
