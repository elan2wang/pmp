/**
 * Author            : Elan
 * Created On        : 2012-5-14 下午02:19:36
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.business.IProjectService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PageTriggerAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PageTriggerAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IProjectService projectService;
    
    //~ Methods ========================================================================================================
    public String select_project(){
	List<Project> list = null;
	Object obj = SessionHandler.getUserRefDomain();
	if (obj instanceof Company){
	    Pager pager = new Pager(1000,1);
	    list = (List<Project>) projectService.loadProjectByComID(pager, ((Company)obj).getComId());
	} else{
	    list = new ArrayList<Project>();
	    list.add((Project)obj);
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("proList", list);
	return SUCCESS;
    }
    //~ Getters and Setters ============================================================================================

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

}
