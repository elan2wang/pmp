/**
 * Author            : Elan
 * Created On        : 2012-4-19 下午08:44:10
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
import org.pmp.dao.admin.IRoleModuleDAO;
import org.pmp.vo.TbRoleAuthority;
import org.pmp.vo.TbRoleModule;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RoleModuleDAO extends BaseDAO implements IRoleModuleDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(RoleModuleDAO.class.getName());
    //~ Instance Fields ================================================================================================

    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void batchSave(List<TbRoleModule> list){
	Session session = getSession();
        logger.debug("begin to batch save roleModule.");
        String hql = "insert into tb_RoleModule(Role_ID,Mod_ID) values(?,?)";
        try {
            Transaction tx = session.beginTransaction();
            Connection conn = session.connection();
            PreparedStatement stmt = conn.prepareStatement(hql);
            Iterator<TbRoleModule> ite = list.iterator();
            while(ite.hasNext()){
        	TbRoleModule ra = ite.next();
        	stmt.setInt(1, ra.getTbRole().getRoleId());
                stmt.setInt(2, ra.getTbModule().getModId());
                stmt.executeUpdate();
            }
            tx.commit();
        } catch (SQLException e1){
            logger.error("batch save roleModule failed",e1);
        } catch (RuntimeException e2){
            logger.error("batch save roleModule failed",e2);
            throw e2;
        }
        logger.debug("batch save roleModule successfully.");
        session.close();
    }
    
    public void batchDeleteByRoleID(Integer roleID){
	Session session = getSession();
        logger.debug("begin to batch delete roleModule by roleID.");
        String hql = "delete TbRoleModule rm where rm.tbRole.roleId = ?";
        try {
            Transaction tx = session.beginTransaction();
            session.createQuery(hql).setParameter(0, roleID).executeUpdate();
            tx.commit();
        } catch (RuntimeException e){
            logger.error("batch delete roleModule by roleID failed",e);
            session.close();
            throw e;
        }
        logger.debug("batch delete roleModule by roleID successfully.");
        session.close();
    }
    public void batchUpdateByRoleID(Integer roleID,List<TbRoleModule> list){
	logger.debug("bacthUpdate(1):batchDelete begin");
	batchDeleteByRoleID(roleID);
	logger.debug("bacthUpdate(1):batchDelete successfully");
	logger.debug("bacthUpdate(2):batchSave begin");
	batchSave(list);
	logger.debug("bacthUpdate(2):batchSave successfully");
    }
    //~ Getters and Setters ============================================================================================

}
