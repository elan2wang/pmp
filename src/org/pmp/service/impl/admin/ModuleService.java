/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:18:11
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IModuleDAO;
import org.pmp.service.admin.IModuleService;
import org.pmp.vo.TbModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ModuleService implements IModuleService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ModuleService.class.getName());
    //~ Instance Fields ================================================================================================
    private IModuleDAO moduleDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addModule(TbModule instance){
	moduleDAO.saveModule(instance);
    }
    public void editModule(TbModule instance){
	moduleDAO.updateModule(instance);
    }
    public void deleteModule(TbModule instance){
	moduleDAO.deleteModule(instance);
    }
    
    public TbModule getModuleByID(Integer modId){
	return moduleDAO.getModuleByID(modId);
    }
    
    public List getModuleList(){
	return moduleDAO.getModuleList();
    }
    
    public List getModuleListByRoleID(Integer roleId){
	return moduleDAO.getModuleListByRoleID(roleId);
    }
    
    public List getNoneGrantedModuleByRoleID(Integer roleId){
	return moduleDAO.getNoneGrantedModuleByRoleID(roleId);
    }
    
    //~ Getters and Setters ============================================================================================
    public IModuleDAO getModuleDAO() {
        return moduleDAO;
    }
    public void setModuleDAO(IModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }

}
