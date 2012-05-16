/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午01:06:55
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
import org.pmp.dao.business.IOwnerDAO2;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerDAO2 extends BaseDAO implements IOwnerDAO2 {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerDAO2.class.getName());

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IOwnerDAO2#saveOwner(org.pmp.vo.Owner)
     */
    @Override
    public void saveOwner(Owner instance) {
	String debugMsg = "save one owner";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IOwnerDAO2#updateOwner(org.pmp.vo.Owner)
     */
    @Override
    public void updateOwner(Owner instance) {
	String debugMsg = "update owner,ownerId="+instance.getOwnerId();
	try {
	    updateInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }


    /**
     * @see org.pmp.dao.business.IOwnerDAO2#batchDelete(java.util.List)
     */
    @Override
    public void batchDelete(final List<Owner> list) {
	logger.debug("begin to batch delete Owner");
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_Owner where Owner_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getOwnerId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete Owner");
    }


    /**
     * @see org.pmp.dao.business.IOwnerDAO2#getOwner_ById(java.lang.Integer)
     */
    @Override
    public Owner getOwner_ById(Integer ownerId) {
	String hql = "from Onwer where ownerId="+ownerId;
	String debugMsg = "get owner by id,ownerId="+ownerId;
	Owner owner = null;
	try {
	    owner = (Owner)getInstance(hql,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return owner;
    }

    /**
     * @see org.pmp.dao.business.IOwnerDAO2#loadOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	List<?> list = null;
	String debugMsg = "load Owner list by project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	
	String aa = "from Owner where ownerId in (select owner.ownerId " +
                    "from HouseOwner where house.houseId in (select houseId from House " + 
                    "where building.builId in (select builId from Building where project.proId="+proId +
                    ")))";
	hql.append(aa);
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by ownerId desc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IOwnerDAO2#loadOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	// TODO Auto-generated method stub
	return null;
    }

    //~ Getters and Setters ============================================================================================

}
