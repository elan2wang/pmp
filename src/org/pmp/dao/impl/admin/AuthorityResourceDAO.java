/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午05:00:58
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
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IAuthorityResourceDAO;
import org.pmp.vo.TbAuthorityResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class AuthorityResourceDAO extends BaseDAO implements
	IAuthorityResourceDAO {
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(AuthorityResourceDAO.class.getName());

    //~ Methods ========================================================================================================
    public void batchSave(List<TbAuthorityResource> list) {
	Session session = getSession();
        logger.debug("begin to batch save AuthRes.");
        String hql = "insert into tb_AuthorityResource(Auth_ID,Res_ID) values(?,?)";
        try {
            Transaction tx = session.beginTransaction();
            Connection conn = session.connection();
            PreparedStatement stmt = conn.prepareStatement(hql);
            Iterator<TbAuthorityResource> ite = list.iterator();
            while(ite.hasNext()){
        	TbAuthorityResource ar = ite.next();
                stmt.setInt(1, ar.getTbAuthority().getAuthId());
                stmt.setInt(2, ar.getTbResource().getResId());
                stmt.executeUpdate();
            }
            tx.commit();
        } catch (SQLException e1){
            logger.error("batch save AuthRes failed",e1);
        } catch (RuntimeException e2){
            logger.error("batch save AuthRes failed",e2);
            throw e2;
        }
        logger.debug("batch save AuthRes successfully.");
        session.close();
    }

    public void batchDeleteByAuthID(Integer authID) {
	Session session = getSession();
        logger.debug("begin to batch delete AuthRes by AuthID.");
        String hql = "delete TbAuthorityResource ar where ar.tbAuthority.authId = ?";
        try {
            Transaction tx = session.beginTransaction();
            session.createQuery(hql).setParameter(0, authID).executeUpdate();
            tx.commit();
        } catch (RuntimeException e){
            logger.error("batch delete AuthRes by AuthID failed",e);
            throw e;
        }
        logger.debug("batch delete AuthRes by AuthID successfully.");
        session.close();
    }

    public void batchUpdateByAuthID(Integer authID,List<TbAuthorityResource> list) {
	logger.debug("bacthUpdate(1):batchDelete begin");
	batchDeleteByAuthID(authID);
	logger.debug("bacthUpdate(1):batchDelete successfully");
	logger.debug("bacthUpdate(2):batchSave begin");
	batchSave(list);
	logger.debug("bacthUpdate(2):batchSave successfully");
    }
    
    //~ Getters and Setters ============================================================================================

}
