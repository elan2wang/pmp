/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:22:24
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.service.admin.IModuleService;
import org.pmp.vo.TbModule;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ModuleAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ModuleAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IModuleService moduleService;
    private TbModule module;
    
    private Integer modId;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String addModule(){
	moduleService.addModule(module);
	return SUCCESS;
    }
    
    public String editModule(){
	moduleService.editModule(module);
	return SUCCESS;
    }
    
    public void deleteModule(){
	moduleService.deleteModule(moduleService.getModuleByID(modId));
    }
    
    public String loadModule(){
	TbModule module = moduleService.getModuleByID(modId);
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("module", module);
	return SUCCESS;
    }
    
    public String loadModuleList(){
	List moduleList = moduleService.getModuleList();
	
	HttpServletRequest request = ServletActionContext.getRequest();
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

    public TbModule getModule() {
        return module;
    }

    public void setModule(TbModule module) {
        this.module = module;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

}
