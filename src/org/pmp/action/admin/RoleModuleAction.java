/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午09:08:45
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
import org.pmp.service.admin.IModuleService;
import org.pmp.service.admin.IRoleModuleService;
import org.pmp.service.admin.IRoleService;
import org.pmp.vo.TbAuthority;
import org.pmp.vo.TbModule;
import org.pmp.vo.TbRole;
import org.pmp.vo.TbRoleAuthority;
import org.pmp.vo.TbRoleModule;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleModuleAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleModuleAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IRoleModuleService roleModuleService;
    private IModuleService moduleService;
    private IRoleService roleService;
    
    private String[] moduleList;
    private Integer roleId;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String getRoleModule(){
	List grantedModuleList = moduleService.getModuleListByRoleID(roleId);
	List noneGrantedModuleList = moduleService.getNoneGrantedModuleByRoleID(roleId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("grantedModuleList", grantedModuleList);
	request.setAttribute("noneGrantedModuleList", noneGrantedModuleList);
	request.setAttribute("roleId", roleId);
	
	logger.debug("grantedModuleList.size="+grantedModuleList.size());
	logger.debug("noneGrantedModuleList.size="+noneGrantedModuleList.size());
	
	return SUCCESS;
	
    }
    
    public String editRoleModule(){
	if (moduleList == null){
	    roleModuleService.batchDeleteByRoleID(roleId);
	    return SUCCESS;
	}
	else{
	    logger.debug("moduleList.size"+moduleList.length);
	    List<TbRoleModule> list = new ArrayList<TbRoleModule>();
	    TbRole role = roleService.getRoleByID(roleId);
	    for(int i=0;i<moduleList.length;i++){
		TbModule module = moduleService.getModuleByID(Integer.parseInt(moduleList[i].trim()));
	        TbRoleModule rm = new TbRoleModule();
		rm.setTbModule(module);
		rm.setTbRole(role);
		list.add(rm);
	    }
	    logger.debug("list.size"+list.size());
            roleModuleService.batchUpdateByRoleID(roleId, list);
	    return SUCCESS;
	}
    }
    //~ Getters and Setters ============================================================================================

    public IRoleModuleService getRoleModuleService() {
        return roleModuleService;
    }

    public void setRoleModuleService(IRoleModuleService roleModuleService) {
        this.roleModuleService = roleModuleService;
    }

    public IModuleService getModuleService() {
        return moduleService;
    }

    public void setModuleService(IModuleService moduleService) {
        this.moduleService = moduleService;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String[] getModuleList() {
        return moduleList;
    }

    public void setModuleList(String[] moduleList) {
        this.moduleList = moduleList;
    }

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

}
