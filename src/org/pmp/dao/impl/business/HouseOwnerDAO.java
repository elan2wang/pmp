/**
 * Author            : Elan
 * Created On        : 2012-4-15 下午12:15:03
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IHouseOwnerDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Building;
import org.pmp.vo.CondoFee;
import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class HouseOwnerDAO extends BaseDAO implements IHouseOwnerDAO {
    Logger logger = Logger.getLogger(HouseOwnerDAO.class.getName());
    public void batchSave(final List<Integer> ownerIdList){
        logger.debug("begin to batch add houseOwner with ownerIdList by call procedure add_house_owner");
        Work work = new Work(){
            public void execute(Connection connection)throws SQLException{
                String procedure = "{call add_house_owner(?) }";
                CallableStatement cstmt = connection.prepareCall(procedure);
                for(Integer id : ownerIdList){
                    cstmt.setInt(1, id);
                    cstmt.executeUpdate();
                }
            }
        };
	executeWork(work);
	logger.debug("successfully batch add houseOwner with ownerIdList by call procedure add_house_owner");
    }
    
    public void saveHouseOwner(HouseOwner instance){
	String debugMsg = "save houseOwner instance";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public void updateHouseOwner(HouseOwner instance){
	String debugMsg = "update houseOwner instance";
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public HouseOwner getHouseOwner_ByHouse(Integer houseId){
	String debugMsg = "get houseOwner instance by house,houseId="+houseId;
	String hql = "from HouseOwner where house.houseId="+houseId;
	HouseOwner houseOwner = null;
	try {
	    houseOwner = (HouseOwner)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return houseOwner;
    }
    
   
    public HouseOwner getHouseOwner_ByOwner(Integer ownerId){
	String debugMsg = "get houseOwner instance by owner,ownerId="+ownerId;
	String hql = "from HouseOwner where owner.ownerId="+ownerId;
	HouseOwner houseOwner = null;
	try {
	    houseOwner = (HouseOwner)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return houseOwner;
    }
    
    public void deleteHouseOwner(HouseOwner instance){
	String debugMsg = "delete houseOwner instance,hoId="+instance.getHoId();
	String hql = "delete from HouseOwner where hoId="+instance.getHoId();
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IHouseOwnerDAO#loadHouseOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<HouseOwner> loadHouseOwnerList_ByPro(Integer proId,
	    Map<String, Object> params, String order, Pager pager) {
	List<HouseOwner> list = null;
	String debugMsg = "load HouseOwner list by project, proId="+proId;
	StringBuilder hql = new StringBuilder();
	
	hql.append("from HouseOwner where house.houseId in (" +
		   "select houseId from House where building.builId in (" +
		   "select builId from Building where project.proId="+proId+"))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by house.houseId asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<HouseOwner>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }

    /**
     * @see org.pmp.dao.business.IHouseOwnerDAO#loadHouseOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<HouseOwner> loadHouseOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<HouseOwner> list = null;
	String debugMsg = "load HouseOwner list by Company, proId="+comId;
	StringBuilder hql = new StringBuilder();
	
	hql.append("from HouseOwner where house.houseId in (" +
		   "select houseId from House where building.builId in (" +
		   "select builId from Building where project.proId in (" +
		   "select proId from Project where company.comId="+comId+")))");
	hql.append(ParamsToString.toString(params));
	if (order==null){
	    hql.append(" order by house.houseId asc");
	} else {
	    hql.append(" "+order);
	}
	logger.debug(hql);
	try {
	    list = (List<HouseOwner>) loadListByCondition(hql.toString(),pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	
	return list;
    }
	
}
