/**
 * Author            : Elan
 * Created On        : 2012-7-10 下午12:35:41
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IPublicRepairDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.PublicRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PublicRepairDAO extends BaseDAO implements IPublicRepairDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PublicRepairDAO.class.getName());

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IPublicRepairDAO#savePublicRepair(org.pmp.vo.PublicRepair)
     */
    @Override
    public void savePublicRepair(PublicRepair instance) {
	String debugMsg = "save publicRepair";
	try {
	    saveInstance(instance, debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairDAO#updatePublicRepair(org.pmp.vo.PublicRepair)
     */
    @Override
    public void updatePublicRepair(PublicRepair instance) {
	String debugMsg = "update publicRepair";
	try {
	    updateInstance(instance, debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairDAO#batchDeletePublicRepair(java.util.List)
     */
    @Override
    public void batchDeletePublicRepair(final List<PublicRepair> list) {
	logger.debug("begin to batch delete PublicRepair");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_PublicRepair where FB_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getFbId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete PublicRepair");

    }

    /**
     * @see org.pmp.dao.business.IPublicRepairDAO#getPublicRepairByID(java.lang.Integer)
     */
    @Override
    public PublicRepair getPublicRepairByID(Integer fbId) {
	String debugMsg = "get PublicRepair by Id ,fbId="+fbId;
	String hql = "from PublicRepair where fbId="+fbId;
	PublicRepair instance = null;
	try {
	    instance = (PublicRepair)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return instance;
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairDAO#loadPublicRepairList_ByFBI(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<PublicRepair> loadPublicRepairList_ByFBI(Integer fbiId,
	    Map<String, Object> params, String order, Pager pager) {
	List<PublicRepair> list = null;
	String debugMsg = "load PublicRepair list by Item, fbiId="+fbiId;
	StringBuilder hql = new StringBuilder();
	hql.append("from PublicRepair where publicRepairItem.fbiId ="+fbiId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by fbId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<PublicRepair>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

}
