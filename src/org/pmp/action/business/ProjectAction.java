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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
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
	private Integer page = 1;
	private Integer rp = 15;
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
	
	public void loadProjectListBySessionHandler(){
		logger.debug("进入getProjectBySessionHander");
		Object obj = SessionHandler.getUserRefDomain();
		projectList = new ArrayList<Project>();
		//如果是小区管理员，则只显示本小区内的项目
		if(obj instanceof Project)
		{
			Project pro = (Project)obj;
			System.out.println(pro.getProName());
			projectList.add(pro);
		}
		else if(obj instanceof Company)
		{
			Company com = (Company)obj;
			Pager pager = new Pager(10000,1);
			Map<String,Object> params = new HashMap<String,Object>();
			String order = "order by proId asc";
			projectList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
		}	
		if(projectList!=null&&projectList.size()!=0)
		{
			Pager pager = new Pager(rp,page);
			pager.setRowsCount(projectList.size());
			String data = JsonConvert.list2FlexJson(pager, projectList, "org.pmp.vo.Project");
			System.out.println(data);
			logger.debug(data);
			JsonConvert.output(data);
		}
		else
		{
			System.out.println("projectList is null");
		}
	}
	
	
	public String getProjectBySessionHander(){
		logger.debug("进入getProjectBySessionHander");
		Object obj = SessionHandler.getUserRefDomain();
		String objName = obj.getClass().getName();
		// = null;
		projectList = new ArrayList<Project>();
		//如果是小区管理员，则只显示本小区内的业主
		if(objName.equals("org.pmp.vo.Project"))
		{
			Project pro = (Project)obj;
			projectList.add(pro);
		}
		else if(objName.equals("org.pmp.vo.Company"))
		{
			Company com = (Company)obj;
			Pager pager = new Pager(10000,1);
			Map<String,Object> params = new HashMap<String,Object>();
			String order = "order by proId asc";
			projectList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
		}		
		
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
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
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

	public Integer getRp() {
	    return rp;
	}

	public void setRp(Integer rp) {
	    this.rp = rp;
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
