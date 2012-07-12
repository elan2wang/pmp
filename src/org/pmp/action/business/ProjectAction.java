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
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.ProjectImport;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IProjectService;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Project;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ProjectAction extends BaseAction {
    static Logger logger = Logger.getLogger(ProjectAction.class.getName());
	
    private IProjectService projectService;
    private Project project;
    private Integer proId;
    private String proName;
	
    /* used when uploadFile */
    private File proFile;
    private String proFileFileName;
    private String proFileContentType;
    
    //~ Methods ===========================================================================================================
    public String addProject(){
        //调用该方法的必定是公司管理员
        Object obj = SessionHandler.getUserRefDomain();
        Company com = (Company)obj;
        project.setCompany(com);
        projectService.addProject(project);
        return SUCCESS;
    }
	
    public void deleteProject(){
    	Map<String,String> params = new HashMap<String, String>();
        projectService.deleteProject(projectService.getProjectByID(proId));
        MyJson json = new MyJson();
    	params.put("msg", "物业项目删除成功");
    	MyJson.print(json.toJson(params));
    }
	
    public String editProject(){
	Project pro = projectService.getProjectByID(project.getProId());
	project.setCompany(pro.getCompany());
        projectService.editProject(project);
        return SUCCESS;
    }
	 
    public String getProjectById(){
        logger.debug("进入方法");
        project = projectService.getProjectByID(proId);
        logger.debug("得到的project为:"+project.toString());
        return SUCCESS;
    }
    
    public void isExist(){
        try{
            //FireFox的URL默认编码格式为ISO-8859-1
            String name = new String(proName.getBytes("ISO-8859-1"),"UTF-8");
            StringBuilder sb = new StringBuilder();
            Project project = projectService.getProjectByName(name);
            if(project!=null){
                sb.append("{\"result\":\"Failed\"}");
            }else{
                sb.append("{\"result\":\"Success\"}");
            }
            MyJson.print(sb.toString());
        }catch(Exception e){
            logger.debug("字符串转换失败："+proName);
        }
    }
	 
    public void loadProjectList(){
        Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();

        Object obj = SessionHandler.getUserRefDomain();
        List<Project> projectList = new ArrayList<Project>();
        //如果是小区管理员，则只显示本小区内的项目
        if(obj instanceof Project){
            Project pro = (Project)obj;
            projectList.add(pro);
        }
        else if(obj instanceof Company){
            Company com = (Company)obj;
            projectList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
        }
	String[] attrs = {"proId","company.comName","proName","proDistrict","proStreet","proHouseCount","proType",
		          "proAddress","deliveryTime","proDesc","fireEnabled","enabled"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(projectList, "", pager);
	MyJson.print(data);
    }
    
    public void uploadFile()throws IOException{
        Map<String,Object> params = new LinkedHashMap<String, Object>();
        String data = null;
        MyJson json = new MyJson();
        String message = null;
        if(!MyfileUtil.validate(proFileFileName,"xls")){
            logger.debug("文件格式不对");
            String postfix = MyfileUtil.getPostfix(proFileFileName);
            message = postfix+"类型的文件暂不支持，请选择xls类型文件";
            params.put("error", "filetype_error");
	    params.put("msg", message);
	    data = json.toJson(params);
	    json.output(data);
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
        
        logger.debug("开始保存");
        /* call the method batchSetOughtMoney to update the condoFee*/
        projectService.batchSaveProject(proList);
	logger.debug("保存成功");		
	
        /* if there are some mistakes of the file */
        if (hasError){
            message = "记录有错误,正确数据已导入，请下载错误数据<a href=\""+downLoad+"\">下载</a>";
            params.put("error", "record_error");
	    params.put("msg", message);
	    data = json.toJson(params);
	    json.output(data);
            return;
        }
			
        /* data import success */
        message = "数据导入成功";
        params.put("error", "");
	params.put("msg", message);
        data = json.toJson(params);
        json.output(data);
        return;
    }
    
    public String getProjectBySessionHander(){
        Object obj = SessionHandler.getUserRefDomain();
        String objName = obj.getClass().getName();
        List<Project> projectList = new ArrayList<Project>();
        //如果是小区管理员，则只显示本小区内的业主
        if(objName.equals("org.pmp.vo.Project")){
            Project pro = (Project)obj;
            projectList.add(pro);
        }else if(objName.equals("org.pmp.vo.Company")){
            Company com = (Company)obj;
            Pager pager = new Pager(10000,1);
            Map<String,Object> params = new HashMap<String,Object>();
            String order = "order by proId asc";
            projectList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
        }	    
        
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("projectList", projectList);
        return SUCCESS;
    }
    
    //~ setters and getters ===================================================================================================
    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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
}
