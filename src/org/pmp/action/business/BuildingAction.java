/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午05:45:43
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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.BuildingImport;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
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
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class BuildingAction extends ActionSupport {
	
    private static Logger logger = Logger.getLogger(BuildingAction.class.getName());

    private IBuildingService buildingService;
    
    private Integer buildingId;
    private Integer projectId;
    private Integer builNum;

    private Building building;
    
    /* used when uploadFile */
    private File refFile;
    private String refFileFileName;
    private String refFileContentType;

    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */

    public String saveBuilding(){
        buildingService.addBuilding(building);
        return SUCCESS;
    }
	
    public void checkBuildingByBuilNumAndProjectId(){
        Building building = buildingService.getBuildingByProjectIdAndBuildingNum(projectId,builNum);
        String data = null;
        if(building!=null){
            data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Failed")+"}";   
        } else {
            data="{"+JsonConvert.toJson("result")+":"+JsonConvert.toJson("Success")+"}";
	}
        logger.debug(data);
        JsonConvert.output(data);
    }
    
    public String updateBuilding(){
        buildingService.editBuilding(building);
        return SUCCESS;
    }
	
    public void deleteBuilding(){
        buildingService.deleteBuilding(buildingService.getBuildingById(buildingId));
    }
	
    public String getBuildingById(){
        building = buildingService.getBuildingById(buildingId);
        return SUCCESS;
    }
	
    public void loadBuildingListBySessionHandler(){
        List<?> buildingList = new ArrayList<Building>();
        Object obj = SessionHandler.getUserRefDomain();
        //如果是小区管理员，则只显示本小区内的楼宇
		
        Pager pager = new Pager(rp,page);
        String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by project.proId asc, builNum asc";
	}
	
        if(projectId==0){
            if(obj instanceof Project){
                Project pro = (Project)obj;
                buildingList = buildingService.loadBuildingList_ByProject(pro.getProId(), params, order, pager);
            } else if(obj instanceof Company){
        	Company com = (Company)obj;
                buildingList = buildingService.loadBuildingList_ByCompany(com.getComId(), params, order, pager);
            }
	} else {
	    buildingList = buildingService.loadBuildingList_ByProject(projectId, params, order, pager);
	}
        
        String[] attrs = {"builId","project.proName","builNum","builType","floorCount","skipFloor","housesPer","unitCount"};
        List<String> show = Arrays.asList(attrs);
        Includer includer = new Includer(show);
        MyJson json = new MyJson(includer);
        
        String data = json.toJson(buildingList, "", pager);
        logger.debug(data);
        JsonConvert.output(data);
    }
	
    public void uploadFile()throws IOException{
        HttpServletRequest request = ServletActionContext.getRequest();
        String message = null;
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

    //~ getters and setters ================================================================================================
    public IBuildingService getBuildingService() {
        return buildingService;
    }

    public void setBuildingService(IBuildingService buildingService) {
        this.buildingService = buildingService;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBuilNum() {
        return builNum;
    }

    public void setBuilNum(Integer builNum) {
        this.builNum = builNum;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
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
}
