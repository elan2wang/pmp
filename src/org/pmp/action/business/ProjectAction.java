/**
 * Author            : Elan
 * Created On        : 2012-3-22 下午05:30:34
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
 * @author Elan
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
	private File proFile;
	private String proFileFileName;
	private String proFileContentType;
	public String addProject(){
		//调用该方法的必定是公司管理员
		Object obj = SessionHandler.getUserRefDomain();
		Company com = (Company)obj;
		project.setCompany(com);
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
	 public void checkProjectByName(){
		    	try
		    	{
		    		projectName = new String(projectName.getBytes("ISO-8859-1"),"UTF-8");
		    	}    	
		    	catch(Exception e){}
		    	Project project = projectService.getProjectByName(projectName);
		    	String data = null;
		    	if(project!=null)
		    	{
		    		data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Failed")+"}";
		       
		    	}
		    	else
		    	{
		    		data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Success")+"}";
		    	}
		     	logger.debug(data);
		    	JsonConvert.output(data);
		    	
		      }
	 
	public void loadProjectListBySessionHandler(){
		logger.debug("进入getProjectBySessionHander");
		Object obj = SessionHandler.getUserRefDomain();
		Pager pager = new Pager(rp,page);
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
			Map<String,Object> params = new HashMap<String,Object>();
			String order = "order by proId asc";
			projectList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
		}	
		
	//	pager.setRowsCount(projectList.size());
		String data = JsonConvert.list2FlexJson(pager, projectList, "org.pmp.vo.Project");
		System.out.println(data);
		logger.debug(data);
		JsonConvert.output(data);
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
	
	public void uploadFile()throws IOException{
		    HttpServletRequest request = ServletActionContext.getRequest();
		    String message = null;
		    System.out.println("proFileFileName:"+proFileFileName);
			if(!MyfileUtil.validate(proFileFileName,"xls")){
			    logger.debug("文件格式不对");
			    String postfix = MyfileUtil.getPostfix(proFileFileName);
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
			List<Project> proList = new ArrayList<Project>();
			Boolean hasError = ProjectImport.execute(new FileInputStream(proFile), os, proList);
			/* close OutputStream */
			os.flush();
			os.close();
			
			/* call the method batchSetOughtMoney to update the condoFee*/
			projectService.batchSaveProject(proList);
			
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
	
	public File getProFile() {
		return proFile;
	}

	public void setProFile(File proFile) {
		this.proFile = proFile;
	}

	public String getProFileFileName() {
		return proFileFileName;
	}

	public void setProFileFileName(String proFileFileName) {
		this.proFileFileName = proFileFileName;
	}

	public String getProFileContentType() {
		return proFileContentType;
	}

	public void setProFileContentType(String proFileContentType) {
		this.proFileContentType = proFileContentType;
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
