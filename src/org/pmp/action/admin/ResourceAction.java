/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:13:02
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.NewCondoFeeImport;
import org.pmp.excel.ResourceImport;
import org.pmp.service.admin.IResourceService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;
import org.pmp.vo.TbResource;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ResourceAction extends ActionSupport {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ResourceAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IResourceService resourceService;
    private TbResource resource;
    private Integer resId;
    
    private File resFile;
    private String resFileFileName;
    private String resFileContentType;
    
    /* used when deleteRes */
    private String idStr;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public void importRes() throws IOException{
	HttpServletRequest request = ServletActionContext.getRequest();
        String message = null;
	if(!MyfileUtil.validate(resFileFileName,"xls")){
	    String postfix = MyfileUtil.getPostfix(resFileFileName);
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
	
	/* import data from the upload file and store in the resList */
	List<TbResource> resList = new ArrayList<TbResource>();
	Boolean hasError = ResourceImport.execute(new FileInputStream(resFile), os, resList);
	/* close OutputStream */
	os.flush();os.close();
	
	/* batch save resource instance list */
	resourceService.batchAdd(resList);
	
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
    
    public String addResource(){
	resourceService.addResource(resource);
	return SUCCESS;
    }
    
    public void loadResList(){
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by resType asc, resLink asc";
	}
	List<?> resList = resourceService.loadResourceList(params, order, pager);
	
	String data = JsonConvert.list2FlexJson(pager, resList, "org.pmp.vo.TbResource");
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public String getResById(){
    	resource = resourceService.getResourceByID(resId);
    	return SUCCESS;
    }
    
    public String updateRes(){
    	resourceService.editResource(resource);
    	return SUCCESS;
    }
    
    public void deleteRes(){
	List<TbResource> resList = new ArrayList<TbResource>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    TbResource res = resourceService.getResourceByID(Integer.parseInt(checkedID[i]));
	    resList.add(res);
	}
	resourceService.batchDelete(resList);
    }
    //~ Getters and Setters ============================================================================================

    public IResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public TbResource getResource() {
        return resource;
    }

    public void setResource(TbResource resource) {
        this.resource = resource;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public File getResFile() {
        return resFile;
    }

    public void setResFile(File resFile) {
        this.resFile = resFile;
    }

    public String getResFileFileName() {
        return resFileFileName;
    }

    public void setResFileFileName(String resFileFileName) {
        this.resFileFileName = resFileFileName;
    }

    public String getResFileContentType() {
        return resFileContentType;
    }

    public void setResFileContentType(String resFileContentType) {
        this.resFileContentType = resFileContentType;
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

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
}
