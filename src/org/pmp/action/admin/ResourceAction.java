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
import org.pmp.action.business.BaseAction;
import org.pmp.excel.ResourceImport;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.admin.IResourceService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.vo.TbResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ResourceAction extends BaseAction{
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
   
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public void importRes() throws IOException{
	MyJson json = new MyJson();
	Map<String, Object> params = new LinkedHashMap<String, Object>();
        String message = null;
        String data = null;
	if(!MyfileUtil.validate(resFileFileName,"xls")){
	    String postfix = MyfileUtil.getPostfix(resFileFileName);
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
	
	/* import data from the upload file and store in the resList */
	List<TbResource> resList = new ArrayList<TbResource>();
	Boolean hasError = ResourceImport.execute(new FileInputStream(resFile), os, resList);
	/* close OutputStream */
	os.flush();os.close();
	
	/* batch save resource instance list */
	resourceService.batchAdd(resList);
	
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
    
    public String addResource(){
	resourceService.addResource(resource);
	return SUCCESS;
    }
    
    public void loadResList(){
	Pager pager = getPager();
	/* set query parameter */
        Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();	    
		
	List<?> resList = resourceService.loadResourceList(params, order, pager);
	String[] attrs = {"resId","resName","resType","issys","enabled","resLink","resDesc"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
		
	String data = json.toJson(resList, "", pager);
	json.output(data);
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
    Map<String,String> params = new HashMap<String, String>();
	List<TbResource> resList = new ArrayList<TbResource>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    TbResource res = resourceService.getResourceByID(Integer.parseInt(checkedID[i]));
	    resList.add(res);
	}
	resourceService.batchDelete(resList);
	MyJson json = new MyJson();
	params.put("msg", "资源删除成功");
	MyJson.print(json.toJson(params));
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

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
}
