/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午03:04:39
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.pmp.dao.admin.IRoleDAO;
import org.pmp.service.admin.IRoleService;
import org.pmp.vo.TbRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleService implements IRoleService{

    //~ Static Fields ==================================================================================================
  
    //~ Instance Fields ================================================================================================
    private IRoleDAO roleDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addRole(TbRole instance){
	roleDAO.saveRole(instance);
    }
    
    public void editRole(TbRole instance){
	roleDAO.updateRole(instance);
    }
    
    public void deleteRole(TbRole instance){
	roleDAO.deleteRole(instance);
    }
    
    public TbRole getRoleByID(Integer roleID){
	return roleDAO.getRoleByID(roleID);
    }
    
    public List getRoleList(){
	return roleDAO.getRoleList();
    }
    
    public List<?> loadRoleList_LevelNotBellow(Integer level,String order){
	return roleDAO.loadRoleList_LevelNotBellow(level, order);
    }
    //~ Getters and Setters ============================================================================================

    public IRoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

}
