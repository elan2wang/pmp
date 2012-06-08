/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午03:08:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IRoleService;
import org.pmp.vo.TbRole;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IRoleService roleService;
    private TbRole role;
    private Integer roleId;
    //~ Constructor ====================================================================================================


    //~ Methods ========================================================================================================
    public String addRole(){
	roleService.addRole(role);
	return SUCCESS;
    }
    
    public String loadRoleList(){
	List roles = roleService.getRoleList();
	logger.debug("roles.size="+roles.size());
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("roleList", roles);
	return SUCCESS;
    }
    
    public String getRoleById(){
    	role = roleService.getRoleByID(roleId);
    	return SUCCESS;
    }
    
    public String updateRole(){
    	roleService.editRole(role);
    	return SUCCESS;
    }
    
    public void deleteRoleById(){
    	roleService.deleteRole(roleService.getRoleByID(roleId));
    }
    //~ Getters and Setters ============================================================================================

    public IRoleService getRoleService() {
        return roleService;
    }
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public TbRole getRole() {
        return role;
    }

    public void setRole(TbRole role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
}
