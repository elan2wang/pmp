package org.pmp.dao.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pmp.util.Pager;

public class BaseDAO {
    static Logger logger = Logger.getLogger(BaseDAO.class.getName());
    
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession(){
        Session session = sessionFactory.openSession();
        return session;
    }
    
    /**
     * @Title: saveInstance
     * @Description: 将一个实体对象保存到数据库
     *
     * @param  obj  Object类型，表示一个实体对象
     * @param  debugMsg  String类型，表示debug提示语句，用于写log
     * @return  void
     * @throws  RuntimeException
     */
    protected void saveInstance(Object obj,String debugMsg){
	Session session = getSession();
        logger.debug("begin to save a instance: "+debugMsg);
        try {
            Transaction tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
        } catch (RuntimeException e){
            logger.error("save a instance failed: "+debugMsg,e);
            session.close();
            throw e;
        }
        logger.debug("save a instance successfully: "+debugMsg);
        session.close();
    }
    
    
    protected void updateInstance(Object obj,String debugMsg){
    	Session session = getSession();
    	logger.debug("begin to update a instance: "+debugMsg);
    	try{
    	    Transaction tx = session.beginTransaction();
    	    session.update(obj);
    	    tx.commit();
    	}catch(RuntimeException e){
    	    logger.error("update failed"+e);
    	    session.close();
    	    throw e;
    	}
    	logger.debug("update successfully: "+debugMsg);
        session.close();
    }
    
    /**
     * @Title: deleteInstance
     * @Description: 根据hql删除语句从数据库中删除单条记录
     *
     * @param  hql  String类型，表示删除单条记录的hql语句
     * @param  debugMsg  String类型，表示debug提示信息，用于写log
     * @return void
     * @throws RuntimeException
     */
    protected void deleteInstance(String hql, String debugMsg){
	Session session = getSession();
	logger.debug("begin to delete a instance: "+debugMsg);
	try {
	    Transaction tx = session.beginTransaction();
	    Query query = session.createQuery(hql);
	    query.executeUpdate();
	    tx.commit();
	} catch (RuntimeException e){
	    logger.error("delete a instance failed: "+debugMsg,e);
	    session.close();
	    throw e;
	}
	logger.debug("delete a instance successfully: "+debugMsg);
	session.close();
    }
    
    /**
     * @Title: getInstance
     * @Description: 根据hql的查询语句获得一个实体对象，或者返回null
     *
     * @param  hql  String类型，表示查询语句
     * @param  debugMsg  String类型，表示debug提示语句，用于写log
     * @return  Object  返回的实体对象，或者null
     * @throws  RuntimeException
     */
    protected Object getInstance(String hql, String debugMsg){
	Session session = getSession();
	Object obj = null;
	logger.debug("begin to get instance: "+debugMsg);
	try {
	    Transaction tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            List<?> list = query.list();
            if (list.size()!=0)obj = list.get(0);
            tx.commit();
	} catch (RuntimeException e){
            logger.error("get instance failed: "+debugMsg, e);
            session.close();
            throw e;
	}
	logger.debug("get instance successfully: "+debugMsg);
	session.close();
	return obj;
    }
    
    /**
     * @Title: loadListByCondition
     * @Description: 根据条件获取列表信息，该方法用户在其子类中调用
     *
     * @param  hql  String类型，具体的查询语句
     * @param  pager  Pager类型，分页对象
     * @param  debugMsg  String类型，表示debug提示语句，用于写log
     * @return  List  返回的列表
     * @throws  RuntimeException
     */
    protected List loadListByCondition(String hql,Pager pager,String debugMsg) {
	Session session = getSession();
	Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize();
	List list1 = null;
	List list2 = null;
	logger.debug("begin to load list: "+debugMsg);
	try {
	    Transaction tx = session.beginTransaction();
	    Query query = session.createQuery(hql);
	    list1 = query.list();
	    pager.setRowsCount(list1.size());
	    query.setFirstResult(startRow);
	    query.setMaxResults(pager.getPageSize());
	    list2 = query.list();
	    tx.commit();
	} catch (RuntimeException e){
	    logger.error("load list failed: "+debugMsg, e);
	    session.close();
	    throw e;
	}
	logger.debug("load list successfully: "+debugMsg);
	session.close();
	return list2;
    }
}
