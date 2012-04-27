/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午02:12:31
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IAuthorityService;
import org.pmp.vo.TbAuthority;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class AuthorityAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(AuthorityAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IAuthorityService authorityService;
    private TbAuthority authority;
    private String enabled = "true";
    private String issys = "false";
    private Integer authId;
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public String addAuthority(){
	authorityService.addResource(authority);
	return SUCCESS;
    }
    
    public String loadAuthList(){
	List authorities = authorityService.getAuthorityList();
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("authorityList", authorities);
	return SUCCESS;
    }
    
    public String updateAuth(){
    	authorityService.editResource(authority);
    	return SUCCESS;
    }
    
    public String getAuthById(){
    	authority = authorityService.getAuthorityByID(authId);
    	return SUCCESS;
    }
    
    public void deleteAuthById(){
    	authorityService.deleteResource(authId);
    }
    
    //~ Getters and Setters ============================================================================================
    public IAuthorityService getAuthorityService() {
        return authorityService;
    }

    public void setAuthorityService(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    public TbAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(TbAuthority authority) {
        this.authority = authority;
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
    
    public Integer getAuthId() {
	return authId;
    }
    
    public void setAuthId(Integer authId) {
	this.authId = authId;
    }

}
