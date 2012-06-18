/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午04:09:58
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.security.SecuritySupport;
import org.pmp.service.admin.IAuthorityResourceService;
import org.pmp.service.admin.IAuthorityService;
import org.pmp.service.admin.IResourceService;
import org.pmp.vo.TbAuthority;
import org.pmp.vo.TbAuthorityResource;
import org.pmp.vo.TbResource;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class AuthorityResourceAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(AuthorityResourceAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IAuthorityService authorityService;
    private IResourceService resourceService;
    private IAuthorityResourceService authorityResourceService;
    
    private Integer authID;
    
    private String[] resList;

    //~ Methods ========================================================================================================
    /**
     * @Title: getAuthRes
     * @Description: 权限列表中点击"分配资源"按钮所触发；
     *               读取该权限拥有的资源列表和未拥有的资源列表
     *               noneGrantedResList保存未授权的资源列表
     *               grantedResList保存已授权的资源列表
     */
    public String getAuthRes(){
	List noneGrantedResList = resourceService.getNoneGrantedResourceListByAuthoriy(authID);
	List grantedResList = resourceService.getResourceListByAuthority(authID);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("noneGrantedResList", noneGrantedResList);
	request.setAttribute("grantedResList", grantedResList);
	request.setAttribute("authID", authID);
	
    	return SUCCESS;
    }
    
    /**
     * @Title: editAuthRes
     * @Description: 编辑权限的资源信息
     *
     */
    public String editAuthRes(){
	if (resList == null){
	    authorityResourceService.batchDeleteByAuthID(authID);
	}
	else
	{
	    List<TbAuthorityResource> list = new ArrayList<TbAuthorityResource>();
	    TbAuthority auth = authorityService.getAuthorityByID(authID);
	    for(int i=0;i<resList.length;i++){
		TbResource res = resourceService.getResourceByID(Integer.parseInt(resList[i].trim()));
		TbAuthorityResource ar = new TbAuthorityResource();
		ar.setTbAuthority(auth);
		ar.setTbResource(res);
		list.add(ar);
	    }
	    authorityResourceService.batchEditByAuthID(list,authID);
	}
	/* after successfully edit the authorityResource, it is essential to reload the SecurityMetadataSource*/
	SecuritySupport.reloadSecurityMetadataSource();
	return SUCCESS;
	
    }
    
    
    //~ Getters and Setters ============================================================================================
    public IAuthorityService getAuthorityService() {
        return authorityService;
    }
    public void setAuthorityService(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }
    public IResourceService getResourceService() {
        return resourceService;
    }
    public void setResourceService(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public Integer getAuthID() {
        return authID;
    }

    public void setAuthID(Integer authID) {
        this.authID = authID;
    }

    public IAuthorityResourceService getAuthorityResourceService() {
        return authorityResourceService;
    }

    public void setAuthorityResourceService(
    	IAuthorityResourceService authorityResourceService) {
        this.authorityResourceService = authorityResourceService;
    }

    public String[] getResList() {
        return resList;
    }

    public void setResList(String[] resList) {
        this.resList = resList;
    }
    
}
