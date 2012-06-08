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
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IResourceDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
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
	Work work = new Work(){
	    public void execute(Connection connection) throws SQLException{
                final String hql = "insert into tb_Resource(Res_Name,Res_Type,Res_Link,Res_Desc,ENABLED,ISSYS,Mod_ID) " +
                                   "values(?,?,?,?,?,?,?)";
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
	executeWork(work);
	logger.debug("successfully batch save TbResource");
    }
    
    public void batchDelete(final List<TbResource> list){
	logger.debug("begin to batch delete resource,size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection) throws SQLException{
                final String hql = "delete tb_Resource where Res_ID = ?";
                PreparedStatement stmt = connection.prepareStatement(hql);
                Iterator<TbResource> ite = list.iterator();
                while(ite.hasNext()){
                    TbResource res = ite.next();
                    stmt.setInt(1, res.getResId());
                    stmt.executeUpdate();
                }
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete resource");
    }
    
    public void saveResource(TbResource instance){
	String debugMsg = "save an resource instance";
        try {
            saveInstance(instance,debugMsg);
        } catch (RuntimeException e){
            throw e;
        }
    }
    
    public void updateResource(TbResource instance){
	String debugMsg = "update an resource instance,resId="+instance.getResId();
        try {
            updateInstance(instance,debugMsg);
        } catch (RuntimeException e){
            throw e;
        }
    }
    
    public void deleteResource(TbResource instance){
    	String hql = "delete TbResource where resId="+instance.getResId();
    	String debugMsg = "delete TbResource where resId="+instance.getResId();
    	try {
    	    deleteInstance(hql, debugMsg);
	} catch (RuntimeException e) {
	    throw e;
	}
    }
    
    public TbResource getResourceByID(Integer resId){
	String hql = "from TbResource where resId="+resId;
	String debugMsg = "get resource by id,resId="+resId;
	TbResource res = null;
	try {
	    res = (TbResource)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
            throw e;
	}
	return res;
    }
    
    public List loadResourceList(Map<String, Object> params, String order,
	    Pager pager) {
	List<TbResource> list = null;
	String debugMsg = "load TbResource list";
	StringBuilder hql = new StringBuilder();
	hql.append("from TbResource");
	if (params.size()!=0){
	    hql.append(" where ");
	    hql.append(ParamsToString.toString(params).substring(5));
	}
	if (order==null){
	    hql.append(" order by resId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<TbResource>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return list;
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
