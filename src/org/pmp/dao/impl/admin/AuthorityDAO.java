/**
 * Author            : Elan
 * Created On        : 2012-3-27 上午11:22:28
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IAuthorityDAO;
import org.pmp.vo.TbAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class AuthorityDAO extends BaseDAO implements IAuthorityDAO {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(AuthorityDAO.class.getName()); 

    //~ Methods ========================================================================================================

    public void saveAuthority(TbAuthority instance) {
        Session session = getSession();
        logger.debug("begin to save a authority.");
        try {
            Transaction tx = session.beginTransaction();
            session.save(instance);
            tx.commit();
        } catch (RuntimeException e){
            logger.error("save a authority failed",e);
            throw e;
        }
        logger.debug("save a authority successfully.");
        session.close();
    }

    public void updateAuthority(TbAuthority instance) {
    	String debugMsg="updateAuthority";
    	try{
    		updateInstance(instance, debugMsg);
    	}catch(RuntimeException e){
    		throw e;
    	}
    }

    public void deleteAuthority(Integer authID) {
    	String hql = "delete TbAuthority where authId="+authID;
    	String debugMsg = "delete Authority";
    	try{
    		deleteInstance(hql, debugMsg);
    	}catch(RuntimeException e){
    		throw e;
    	}
    }
    
    public TbAuthority getAuthorityByID(Integer authID){
	Session session = getSession();
        TbAuthority authority = null;
        logger.debug("begin to get authority by ID.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TbAuthority auth where auth.authId = ?");
            query.setParameter(0, authID);
            authority = (TbAuthority)query.list().get(0);
            tx.commit();
	} catch (RuntimeException e){
	    logger.error("get authority by ID failed.", e);
	    throw e;
	}
        logger.debug("get authority by ID successfully.");
        session.close();
        return authority;
    }

    public List getAllAuthorities() {
        Session session = getSession();
        List authorityList = null;
        logger.debug("begin to get all authorities name.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select auth.authName from TbAuthority auth");
            authorityList = query.list();
            tx.commit();
	} catch (RuntimeException e){
	    logger.error("get all authorities name failed.", e);
	    throw e;
	}
        logger.debug("get all authorities name successfully.");
        session.close();
        return authorityList;
    }
    
    public List getAuthorityList() {
	Session session = getSession();
        List authorityList = null;
        logger.debug("begin to get all authority entities.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TbAuthority auth");
            authorityList = query.list();
            tx.commit();
	} catch (RuntimeException e){
	    logger.error("get all authority entities failed.", e);
	    throw e;
	}
        logger.debug("get all authority entities successfully.");
        session.close();
        return authorityList;
    }
    
    public List loadUserAuthoritiesByUsername(String username){
	Session session = getSession();
        List authorityList = null;
        String hql = "select auth.authName from TbAuthority auth,TbUser user,TbUserGroupRole ugr,"
            + "TbRoleAuthority ra where auth.authId = ra.tbAuthority.authId and ra.tbRole.roleId = "
            + "ugr.tbRole.roleId and ugr.tbUser.userId = user.userId and user.username = ?";
        
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(0, username);
            authorityList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("load user authorities by account failed", e);
            throw e;
	}
	logger.debug("load user authorities by account successfully");
        session.close();
	return authorityList;
    }

    public List getAuthoritiesByRoleID(Integer roleID) {
	Session session = getSession();
        List authorityList = null;
        String hql = "select auth from TbAuthority auth,TbRole role,TbRoleAuthority ra " +
        	"where auth.authId = ra.tbAuthority.authId and ra.tbRole.roleId = role.roleId " +
        	"and role.roleId = ?";
        
        logger.debug("begin to get all authority entities by role ID.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(0, roleID);
            authorityList = query.list();
            tx.commit();
	} catch (RuntimeException e){
	    logger.error("get all authority entities by role ID failed.", e);
	    session.close();
	    throw e;
	}
        logger.debug("get all authority entities by role ID entitiessuccessfully.");
        session.close();
        return authorityList;
    }

    public List getNoneGrantedAuthByRoleID(Integer roleID){
	Session session = getSession();
        List authorityList = null;
        String hql = "select distinct auth from TbAuthority auth where auth.authId not in " +
        	"(select ra.tbAuthority.authId from TbRoleAuthority ra where ra.tbRole.roleId = ?) " +
        	"order by auth.authId desc";
        
        logger.debug("begin to get none granted authority entities by role ID.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(0, roleID);
            authorityList = query.list();
            tx.commit();
	} catch (RuntimeException e){
	    logger.error("get none granted authority entities by role ID failed.", e);
	    session.close();
	    throw e;
	}
        logger.debug("get none granted authority entities by role ID entitiessuccessfully.");
        session.close();
        return authorityList;
    }
}
