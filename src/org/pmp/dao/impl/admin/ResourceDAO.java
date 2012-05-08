/**
 * Author            : Elan
 * Created On        : 2012-3-27 上午11:30:39
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IResourceDAO;
import org.pmp.vo.TbResource;
/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ResourceDAO extends BaseDAO implements IResourceDAO {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(ResourceDAO.class.getName ());

    //~ Methods ========================================================================================================
    
    public void batchSave(final List<TbResource> list){
	logger.debug("begin to batch save TbResource");
	final String hql = "insert into tb_Resource(Res_Name,Res_Type,Res_Link,Res_Desc,ENABLED,ISSYS,Mod_ID) " +
                           "values(?,?,?,?,?,?,?)";
	Session session = getSession();
	Transaction tx = session.beginTransaction();
	try {
	    Work work = new Work(){
		public void execute(Connection connection) throws SQLException{
		    PreparedStatement stmt = connection.prepareStatement(hql);
		    Iterator<TbResource> ite = list.iterator();
		    while(ite.hasNext()){
			TbResource res = ite.next();
			logger.debug(res.getResName()+" "+res.getResType()+" "+res.getResLink()+" " +res.getResDesc()+" " +res.isEnabled()+" "+res.isIssys()+" "+res.getModId());
			stmt.setString(1, res.getResName());
			stmt.setString(2, res.getResType());
			stmt.setString(3, res.getResLink());
			stmt.setString(4, res.getResDesc());
			if (res.isEnabled()) stmt.setInt(5, 1);
			else stmt.setInt(5, 0);
			if (res.isIssys()) stmt.setInt(6, 1);
			else stmt.setInt(6, 0);
			stmt.setInt(7, res.getModId());
			stmt.executeUpdate();
		    }
		}
	    };
	    session.doWork(work);
	} catch (RuntimeException e){
	    tx.rollback();
	    logger.error(e.getStackTrace());
	    throw e;
	} finally{
            tx.commit();
	    session.close();
	}
	
    }
    
    public void saveResource(TbResource instance){
	Session session = getSession();
        logger.debug("begin to save a resource.");
        try {
            Transaction tx = session.beginTransaction();
            session.save(instance);
            tx.commit();
        } catch (RuntimeException e){
            logger.error("save a resource failed",e);
            throw e;
        }
        logger.debug("save a resource successfully.");
        session.close();
    }
    
    public void updateResource(TbResource instance){
    	String debugMsg = "updateResource";
    	try{
    		updateInstance(instance, debugMsg);
    	}catch (RuntimeException e) {
    		throw e;
		}
    }
    public void deleteResource(Integer resID){
    	String hql = "delete TbResource where resId="+resID;
    	String debugMsg = "deleteResource";
    	try {
			deleteInstance(hql, debugMsg);
		} catch (RuntimeException e) {
			throw e;
		}
    }
    
    public TbResource getResourceByID(Integer authID){
	Session session = getSession();
	TbResource resource = null;
	logger.debug("begin to get resource entity.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TbResource res where res.resId = ?");
            query.setParameter(0, authID);
            resource = (TbResource)query.list().get(0);
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get resource entity failed.",e);
            throw e;
	}
	logger.debug("get resource entity successfully.");
        session.close();
	return resource;
    }
    
    public List getAllResources(){
	Session session = getSession();
	List resourceList = null;
	logger.debug("begin to get all the resources.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select res.resLink from TbResource res");
            resourceList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get all the resources failed.",e);
            throw e;
	}
	logger.debug("get all the resources successfully.");
        session.close();
	return resourceList;
    }
    
    public List getResourceList(){
	Session session = getSession();
	List resourceList = null;
	logger.debug("begin to get all the resource entities.");
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from TbResource res");
            resourceList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get all the resource entities failed.",e);
            throw e;
	}
	logger.debug("get all the resource entities successfully.");
        session.close();
	return resourceList;
    }
    
    public List getResourcesByAuthority(String authName){
	Session session = getSession();
	List resourceList = null;
	logger.debug("begin to get resources by authority name.");
	String sql = "select res.resLink from TbResource res,TbAuthority auth,"
	    + "TbAuthorityResource ar where res.resId = ar.tbResource.resId and "
	    + "auth.authId = ar.tbAuthority.authId and auth.authName = ? order by res.resId desc";
	
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter(0, authName);
            resourceList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get resources by authority name failed.", e);
            throw e;
	}
	logger.debug("get resources by authority name successfully.");
        session.close();
	return resourceList;
    }

    public List getResourceListByAuthority(Integer authID) {
	Session session = getSession();
	List resourceList = null;
	logger.debug("begin to get resource entities by authority ID.");
	String sql = "select res from TbResource res,TbAuthority auth,"
	    + "TbAuthorityResource ar where res.resId = ar.tbResource.resId and "
	    + "auth.authId = ar.tbAuthority.authId and auth.authId = ? order by res.resId desc";
	
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter(0, authID);
            resourceList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get resource entities by authority ID failed.", e);
            throw e;
	}
	logger.debug("get resource entities by authority ID successfully.");
        session.close();
	return resourceList;
    }
    
    public List getNoneGrantedResourceListByAuthoriy(Integer authID){
	Session session = getSession();
	List resourceList = null;
	logger.debug("begin to get none granted resource entities by authority ID.");
	String sql = "select distinct res from TbResource res where res.resId not in "
	    + "(select ar.tbResource.resId from TbAuthorityResource ar where ar.tbAuthority.authId = ?) "
	    + "order by res.resId desc";
	
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter(0, authID);
            resourceList = query.list();
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get none granted resource entities by authority ID failed.", e);
            throw e;
	}
	logger.debug("get none granted resource entities by authority ID successfully.");
        session.close();
	return resourceList;
    }
}
