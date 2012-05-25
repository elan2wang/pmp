/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午02:55:21
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IRoleDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.TbRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleDAO extends BaseDAO implements IRoleDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleDAO.class.getName());
    
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void saveRole(TbRole instance){
	String debugMsg = "save role instance";
        try {
            saveInstance(instance,debugMsg);
        } catch (RuntimeException e){
            throw e;
        }
    }
    
    public void updateRole(TbRole instance){
    	String debugMsg = "update role,roleId="+instance.getRoleId();
        try {
            updateInstance(instance,debugMsg);
        } catch (RuntimeException e){
            throw e;
        }
    }
    
    public void deleteRole(Integer roleID){
    	String hql = "delete TbRole where roleId="+roleID;
    	String debugMsg = "deleteRole";
    	try{
    	    deleteInstance(hql, debugMsg);
    	}catch(RuntimeException e){
    	    throw e;
    	}
    }
    
    public TbRole getRoleByID(Integer roleID){
	String hql = "from TbRole role where role.roleId="+roleID;
	String debugMsg = "get role instance by id,roleId="+roleID;
	TbRole role = null;
	try {
	    role = (TbRole)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
            throw e;
	}
	return role;
    }
    
    public List getRoleList(){
	Session session = getSession();
	List resourceList = null;
	logger.debug("begin to get all the role entities.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TbRole role");
            resourceList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get all the role entities failed.",e);
            session.close();
            throw e;
	}
	logger.debug("get all the role entities successfully.");
        session.close();
	return resourceList;
    }
    
    public List<?> loadRoleList_LevelNotBellow(Integer level,String order){
	List<?> list = null;
	String debugMsg = "load Role list";
	StringBuilder hql = new StringBuilder();
	hql.append("from TbRole where roleLevel>="+level);
	if(order==null){
	    hql.append(" order by roleLevel asc");
	} else {
	    hql.append(" "+order);
	}
	try {
	    list = loadListByCondition(hql.toString(),new Pager(10000,1),debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
    }
   
}
