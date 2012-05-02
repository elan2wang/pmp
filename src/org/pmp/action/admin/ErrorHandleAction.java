/**
 * Author            : Elan
 * Created On        : 2012-5-2 下午03:42:29
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IUserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ErrorHandleAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ErrorHandleAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IUserService userService;
    //~ Methods ========================================================================================================
    public String accessDenied(){
	HttpServletRequest request = ServletActionContext.getRequest();
	AccessDeniedException ex = (AccessDeniedException)request.getAttribute(AccessDeniedHandlerImpl   
	    .SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY);
	request.setAttribute("errorDetails", ex.getMessage());
	
	return SUCCESS;
    }
    //~ Getters and Setters ============================================================================================
    public IUserService getUserService() {
        return userService;
    }
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

}
