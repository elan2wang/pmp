/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午04:04:41
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
import org.pmp.service.admin.IAuthorityService;
import org.pmp.service.admin.IRoleAuthorityService;
import org.pmp.service.admin.IRoleService;
import org.pmp.vo.TbAuthority;
import org.pmp.vo.TbRole;
import org.pmp.vo.TbRoleAuthority;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleAuthorityAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleAuthorityAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IRoleService roleService;
    private IAuthorityService authorityService;
    private IRoleAuthorityService roleAuthorityService;
    
    private Integer roleId;
    
    private String[] authList;
    
    //~ Constructor ====================================================================================================
    
    //~ Methods ========================================================================================================
    public String editRoleAuth(){
	if (authList == null){
	    roleAuthorityService.batchDeleteByRoleID(roleId);
	    return SUCCESS;
	}
	else{
	    List<TbRoleAuthority> list = new ArrayList<TbRoleAuthority>();
	    TbRole role = roleService.getRoleByID(roleId);
	    for(int i=0;i<authList.length;i++){
	        TbAuthority auth = authorityService.getAuthorityByID(Integer.parseInt(authList[i].trim()));
		TbRoleAuthority ra = new TbRoleAuthority();
		ra.setTbAuthority(auth);
		ra.setTbRole(role);
		list.add(ra);
	    }
            roleAuthorityService.batchEditByRoleID(roleId, list);
	    return SUCCESS;
	}
    }
    
    public String getRoleAuth(){
	List noneGrantedAuthList = authorityService.getNoneGrantedAuthByRoleID(roleId);
	List grantedAuthList = authorityService.getAuthoritiesByRoleID(roleId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("noneGrantedAuthList", noneGrantedAuthList);
	request.setAttribute("grantedAuthList", grantedAuthList);
	request.setAttribute("roleId", roleId);
    	return SUCCESS;
    }
    //~ Getters and Setters ============================================================================================

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public IRoleAuthorityService getRoleAuthorityService() {
        return roleAuthorityService;
    }

    public void setRoleAuthorityService(IRoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public IAuthorityService getAuthorityService() {
        return authorityService;
    }

    public void setAuthorityService(IAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    public String[] getAuthList() {
        return authList;
    }

    public void setAuthList(String[] authList) {
        this.authList = authList;
    }
	
}
