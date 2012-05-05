/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午02:55:21
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
import org.pmp.dao.admin.IRoleDAO;
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
	Session session = getSession();
        logger.debug("begin to save a role.");
        try {
            Transaction tx = session.beginTransaction();
            session.save(instance);
            tx.commit();
        } catch (RuntimeException e){
            logger.error("save a role failed",e);
            session.close();
            throw e;
        }
        logger.debug("save a role successfully.");
        session.close();
    }
    
    public void updateRole(TbRole instance){
    	Session session = getSession();
        logger.debug("update role.");
        try {
            Transaction tx = session.beginTransaction();
            session.update(instance);
            tx.commit();
        } catch (RuntimeException e){
            logger.error("update a role failed",e);
            session.close();
            throw e;
        }
        logger.debug("update a role successfully.");
        session.close();
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
	Session session = getSession();
	TbRole role = null;
	logger.debug("begin to get role entity.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TbRole role where role.roleId = ?");
            query.setParameter(0, roleID);
            role = (TbRole)query.list().get(0);
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get role entity failed.",e);
            session.close();
            throw e;
	}
	logger.debug("get role entity successfully.");
        session.close();
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
    
    //~ Getters and Setters ============================================================================================

}
