/**
 * Author            : Elan
 * Created On        : 2012-4-20 下午01:47:46
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IModuleService;
import org.pmp.util.SessionHandler;
import org.pmp.vo.TbRole;
import org.pmp.vo.TbUser;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class LoginAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(LoginAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IModuleService moduleService;
    
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String loadWelcomePage(){
	TbUser user = SessionHandler.getUser();
	TbRole role = SessionHandler.getUserRole();
	List<?> moduleList = moduleService.getModuleListByRoleID(role.getRoleId());
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("user", user);
	request.setAttribute("role", role);
	request.setAttribute("loginTime", new Date());
	request.setAttribute("moduleList", moduleList);
	
	return SUCCESS;
    }
    
    
    //~ Getters and Setters ============================================================================================

    public IModuleService getModuleService() {
        return moduleService;
    }

    public void setModuleService(IModuleService moduleService) {
        this.moduleService = moduleService;
    }

}
