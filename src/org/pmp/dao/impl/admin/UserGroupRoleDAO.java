/**
 * Author            : Elan
 * Created On        : 2012-3-30 上午10:49:29
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IUserGroupRoleDAO;
import org.pmp.vo.TbUserGroupRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserGroupRoleDAO extends BaseDAO implements IUserGroupRoleDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(UserGroupRoleDAO.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================
   
    //~ Methods ========================================================================================================
    public void saveUGR(TbUserGroupRole instance){
	String debugMsg = "SaveUGR";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public void deleteUGR_ByUserID(Integer userID){
	String debugMsg = "deleteUGR_ByUserID userID="+userID.toString();
	String hql = "delete from TbUserGroupRole ugr where ugr.tbUser.userId = "+userID.toString();
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public void updateUGR(TbUserGroupRole instance){
	String debugMsg = "updateUGR userID="+instance.getTbUser().getUserId();
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public TbUserGroupRole getUGR_ByUserID(Integer userID){
	String debugMsg = "getUGR_ByUserID userID="+userID.toString();
	String hql = "from TbUserGroupRole ugr where ugr.tbUser.userId = "+userID.toString();
	TbUserGroupRole ugr = null;
	try {
	    ugr = (TbUserGroupRole)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return ugr;
    }
    //~ Getters and Setters ============================================================================================

}
