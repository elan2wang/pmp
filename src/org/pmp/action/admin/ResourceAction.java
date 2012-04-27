/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:13:02
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.service.admin.IResourceService;
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
    private String enabled = "true";
    private String issys = "false";
    private List resList;
    private Integer resId;
    //~ Constructor ====================================================================================================


	

	//~ Methods ========================================================================================================
    public String addResource(){
	if (enabled.equals("true"))
	    resource.setEnabled(true);
	else
	    resource.setEnabled(false);
	
	if (issys.equals("true"))
	    resource.setIssys(true);
	else
	    resource.setIssys(false);
	
	resourceService.addResource(resource);
	return SUCCESS;
    }
    
    public String loadResList(){
    	resList = resourceService.getResourceList();
    	return SUCCESS;
    }
    
    public String getResById(){
    	resource = resourceService.getResourceByID(resId);
    	return SUCCESS;
    }
    
    public String updateRes(){
    	resourceService.editResource(resource);
    	return SUCCESS;
    }
    
    public void deleteResById(){
    	resourceService.deleteResource(resId);
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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getIssys() {
        return issys;
    }

    public void setIssys(String issys) {
        this.issys = issys;
    }

    public List getResList() {
		return resList;
	}

	public void setResList(List resList) {
		this.resList = resList;
	}
	
	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}
}
