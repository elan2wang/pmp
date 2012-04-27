/**
 * Author            : Elan
 * Created On        : 2012-3-30 下午12:08:47
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.admin;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.IUserGroupRoleDAO;
import org.pmp.service.admin.IUserGroupRoleService;
import org.pmp.vo.TbUserGroupRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserGroupRoleService implements IUserGroupRoleService{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(UserGroupRoleService.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IUserGroupRoleDAO ugrDAO;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void addUGR(TbUserGroupRole instance){
	ugrDAO.saveUGR(instance);
    }
    public void deleteUGR_ByUserID(Integer userID){
	ugrDAO.deleteUGR_ByUserID(userID);
    }
    public void editUGR(TbUserGroupRole instance){
	ugrDAO.updateUGR(instance);
    }

    public TbUserGroupRole getUGR_ByUserID(Integer userID){
	return ugrDAO.getUGR_ByUserID(userID);
    }
    //~ Getters and Setters ============================================================================================
    public IUserGroupRoleDAO getUgrDAO() {
        return ugrDAO;
    }
    public void setUgrDAO(IUserGroupRoleDAO ugrDAO) {
        this.ugrDAO = ugrDAO;
    }

}
