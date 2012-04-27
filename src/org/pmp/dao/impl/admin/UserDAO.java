/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:01:54
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IUserDAO;
import org.pmp.util.Pager;
import org.pmp.vo.TbUser;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class UserDAO extends BaseDAO implements IUserDAO {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(UserDAO.class.getName());

    //~ Methods ========================================================================================================
    public void saveUser(TbUser instance){
	String debugMsg = "saveUser";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    public void updateUser(TbUser instance){
    	String debugMsg = "updateUser";
    	try{
    	    updateInstance(instance, debugMsg);
    	}catch(RuntimeException e){
    	    throw e;
    	}
    }
    public void deleteUser(Integer userID){
    	String hql = "delete TbUser where userId="+userID;
    	String debugMsg = "deleteUse";
    	try{
    	    deleteInstance(hql, debugMsg);
    	}catch(RuntimeException e){
    	    throw e;
    	}
    }
    
    public void passwordReset(String password,Integer userID){}
    
    public TbUser getUserByUsername(String username) {
	TbUser user = null;
	String hql = "from TbUser user where user.username = '"+username+"'";
	String debugMsg = "getUserByUsername username="+username;
	try {
	    user = (TbUser)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
            throw e;
	}
	return user;
    }
    
    public List getUserList(Pager pager){
	List list = null;
	String hql = "from TbUser user order by user.position desc";
	String debugMsg = "getUserList";
	try{
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
    
    public List getUserListByGroup(Pager pager, Integer groupID){
	List list = null;
	String hql = "select ugr.tbUser from TbUserGroupRole ugr where ugr.ugrId = "+groupID.toString();
	String debugMsg = "getUserListByGroup groupID="+groupID.toString();
	try{
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
	@Override
	public TbUser getUserById(Integer userID) {
		String hql = "from TbUser where userId="+userID;
		String debugMsg = "get User By Id";
		
		return (TbUser)getInstance(hql, debugMsg);
	}
}
