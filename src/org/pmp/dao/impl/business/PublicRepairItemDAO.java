/**
 * Author            : Elan
 * Created On        : 2012-7-10 上午09:32:47
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
import org.pmp.dao.business.IPublicRepairItemDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.PublicRepairItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PublicRepairItemDAO extends BaseDAO implements
	IPublicRepairItemDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PublicRepairItemDAO.class.getName());


    //~ Methods ========================================================================================================
    /**
     * @see org.pmp.dao.business.IPublicRepairItemDAO#savePublicRepairItem(org.pmp.vo.PublicRepairItem)
     */
    @Override
    public void savePublicRepairItem(PublicRepairItem instance) {
	String debugMsg = "save publicRepairItem";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairItemDAO#updatePublicRepairItem(org.pmp.vo.PublicRepairItem)
     */
    @Override
    public void updatePublicRepairItem(PublicRepairItem instance) {
	String debugMsg = "update publicRepairItem";
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairItemDAO#batchDeletePublicRepairItem(java.util.List)
     */
    @Override
    public void batchDeletePublicRepairItem(final List<PublicRepairItem> list) {
	logger.debug("begin to batch delete publicRepairItem");
	logger.debug("list.size="+list.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_PublicRepairItem where FBI_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getFbiId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete publicRepairItem");

    }

    /**
     * @see org.pmp.dao.business.IPublicRepairItemDAO#getPublicRepairItemByID(java.lang.Integer)
     */
    @Override
    public PublicRepairItem getPublicRepairItemByID(Integer fbiId) {
	String debugMsg = "get PublicRepairItem by Id ,fbiId="+fbiId;
	String hql = "from PublicRepairItem where fbiId="+fbiId;
	PublicRepairItem instance = null;
	try {
	    instance = (PublicRepairItem)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return instance;
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairItemDAO#loadPublicRepairItemList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<PublicRepairItem> loadPublicRepairItemList_ByCompany(
	    Integer comId, Map<String, Object> params, String order, Pager pager) {
	List<PublicRepairItem> list = null;
	String debugMsg = "load PublicRepairItem list by Company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	hql.append("from PublicRepairItem where project.company.comId ="+comId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by fbiId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<PublicRepairItem>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IPublicRepairItemDAO#loadPublicRepairItemList_ByProject(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<PublicRepairItem> loadPublicRepairItemList_ByProject(
	    Integer proId, Map<String, Object> params, String order, Pager pager) {
	List<PublicRepairItem> list = null;
	String debugMsg = "load PublicRepairItem list by Project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	hql.append("from PublicRepairItem where project.proId ="+proId);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by fbiId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<PublicRepairItem>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }
}
