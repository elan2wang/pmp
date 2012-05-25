/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:01:54
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IUserDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.CondoFee;
import org.pmp.vo.TbUser;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
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

    public void batchDelete(final List<TbUser> list){
	logger.debug("begin to batch delete TbUser");
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_User where User_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getUserId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete TbUser");
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
    
    public TbUser getUserById(Integer userID) {
	TbUser user = null;
	String hql = "from TbUser user where user.userId="+userID;
	String debugMsg = "getUserByUsername user.userId="+userID;
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
    
    public List loadUserList_ByProject(Pager pager,Integer proId)
    {
    //	String proName = 
    	List list = null;
    	String hql = "select ugr.tbUser from TbUserGroupRole ugr,Project p,TbGroup  tg where p.proName = tg.refDomain and tg.groupId = ugr.tbGroup and p.proId = "+proId.toString();
    	String debugMsg = "loadUserList_ByProject proId="+proId.toString();
    	try{
    	    list = loadListByCondition(hql,pager,debugMsg);
    	} catch (RuntimeException e){
    	    throw e;
    	}
    	return list;
    }
}
