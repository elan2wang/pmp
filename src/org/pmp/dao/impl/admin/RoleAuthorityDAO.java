/**
 * Author            : Elan
 * Created On        : 2012-3-28 下午03:54:05
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.admin.IRoleAuthorityDAO;
import org.pmp.vo.TbRoleAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleAuthorityDAO extends BaseDAO implements IRoleAuthorityDAO {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleAuthorityDAO.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void batchSave(List<TbRoleAuthority> list){
	Session session = getSession();
        logger.debug("begin to batch save roleAuth.");
        String hql = "insert into tb_RoleAuthority(Role_ID,Auth_ID) values(?,?)";
        try {
            Transaction tx = session.beginTransaction();
            Connection conn = session.connection();
            PreparedStatement stmt = conn.prepareStatement(hql);
            Iterator<TbRoleAuthority> ite = list.iterator();
            while(ite.hasNext()){
        	TbRoleAuthority ra = ite.next();
        	stmt.setInt(1, ra.getTbRole().getRoleId());
                stmt.setInt(2, ra.getTbAuthority().getAuthId());
                stmt.executeUpdate();
            }
            tx.commit();
        } catch (SQLException e1){
            logger.error("batch save roleAuth failed",e1);
        } catch (RuntimeException e2){
            logger.error("batch save roleAuth failed",e2);
            throw e2;
        }
        logger.debug("batch save roleAuth successfully.");
        session.close();
    }
    
    public void batchDeleteByRoleID(Integer roleID){
	Session session = getSession();
        logger.debug("begin to batch delete roleAuth by roleID.");
        String hql = "delete TbRoleAuthority ra where ra.tbRole.roleId = ?";
        try {
            Transaction tx = session.beginTransaction();
            session.createQuery(hql).setParameter(0, roleID).executeUpdate();
            tx.commit();
        } catch (RuntimeException e){
            logger.error("batch delete roleAuth by roleID failed",e);
            session.close();
            throw e;
        }
        logger.debug("batch delete roleAuth by roleID successfully.");
        session.close();
    }
    
    public void batchUpdateByRoleID(List<TbRoleAuthority> list,Integer roleID){
	logger.debug("bacthUpdate(1):batchDelete begin");
	batchDeleteByRoleID(roleID);
	logger.debug("bacthUpdate(1):batchDelete successfully");
	logger.debug("bacthUpdate(2):batchSave begin");
	batchSave(list);
	logger.debug("bacthUpdate(2):batchSave successfully");
    }
    //~ Getters and Setters ============================================================================================
}
