/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:52:41
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IRoleModuleDAO;
import org.pmp.service.admin.IRoleModuleService;
import org.pmp.vo.TbRoleModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleModuleService implements IRoleModuleService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleModuleService.class.getName());
    //~ Instance Fields ================================================================================================
    private IRoleModuleDAO roleModuleDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void batchSave(List<TbRoleModule> list){
	roleModuleDAO.batchSave(list);
    }
    public void batchDeleteByRoleID(Integer roleID){
	roleModuleDAO.batchDeleteByRoleID(roleID);
    }
    public void batchUpdateByRoleID(List<TbRoleModule> list,Integer roleID){
	roleModuleDAO.batchUpdateByRoleID(roleID, list);
    }
    //~ Getters and Setters ============================================================================================
    public IRoleModuleDAO getRoleModuleDAO() {
        return roleModuleDAO;
    }
    public void setRoleModuleDAO(IRoleModuleDAO roleModuleDAO) {
        this.roleModuleDAO = roleModuleDAO;
    }

}
