/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午04:01:31
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import java.util.List;

import org.pmp.dao.admin.IRoleAuthorityDAO;
import org.pmp.service.admin.IRoleAuthorityService;
import org.pmp.vo.TbRoleAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleAuthorityService implements IRoleAuthorityService{

    //~ Static Fields ==================================================================================================

    //~ Instance Fields ================================================================================================
    private IRoleAuthorityDAO roleAuthorityDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void batchAdd(List<TbRoleAuthority> list){
	roleAuthorityDAO.batchSave(list);
    }
    
    public void batchDeleteByRoleID(Integer roleID){
	roleAuthorityDAO.batchDeleteByRoleID(roleID);
    }
    
    public void batchEditByRoleID(Integer roleID,List<TbRoleAuthority> list){
	roleAuthorityDAO.batchUpdateByRoleID(roleID, list);
    }
    
    //~ Getters and Setters ============================================================================================

    public IRoleAuthorityDAO getRoleAuthorityDAO() {
        return roleAuthorityDAO;
    }
    public void setRoleAuthorityDAO(IRoleAuthorityDAO roleAuthorityDAO) {
        this.roleAuthorityDAO = roleAuthorityDAO;
    }
}
