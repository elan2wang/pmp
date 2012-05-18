/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����02:25:06
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IHouseDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Building;
import org.pmp.vo.CondoFee;
import org.pmp.vo.House;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class HouseDAO extends BaseDAO implements IHouseDAO {
	Logger logger = Logger.getLogger(HouseDAO.class.getName());

	
    
	/**
	 * @Title: saveHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void saveHouse(House house) {
		logger.debug("begin to save a House");
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(house);
			tx.commit();
		}catch(RuntimeException e){
			logger.error("save a house failed",e);
			throw e;
		}
		logger.debug("save a house success");
		session.close();
	}

	/**
	 * @Title: updateHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void updateHouse(House house) {
		Session session = getSession();
		logger.debug("begin to update a House");
//		String hql = "update House set building=?,houseNum=?,houseArea=?,houseDesc=?,condoFeeRate=?,isempty=? where houseId ="+house.getHouseId();
//		Query query = session.createQuery(hql);
//		query.setParameter(0, house.getBuilding());
//		query.setParameter(1, house.getHouseNum());
//		query.setParameter(2, house.getHouseArea());
//		query.setParameter(3, house.getHouseDesc());
//		query.setParameter(4, house.getCondoFeeRate());
//		query.setParameter(5, house.isIsempty());
		try{
			Transaction tx = session.beginTransaction();
			session.update(house);
//			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("update a House is error",e);
			throw e;
		}
		logger.debug("update a House success");
		session.close();
	}

	/**
	 * @Title: deleteHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteHouse(Integer houseID) {
		Session session = getSession();
		logger.debug("begin to delete a House by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete House where houseId = "+ houseID);
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("delete a House bu ID failed", e);
			throw e;
		}
		logger.debug("delete a House by ID success");
		session.close();
	}

	/**
	 * @Title: getHouseByID
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public House getHouseByID(Integer houseId) {
		Session session = getSession();
		House house = null;
		List list = null;
		logger.debug("begin to get a House by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from House where houseId="+houseId);
			list = query.list();
			house = (House)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a House by ID failed",e);
			throw e;
		}
		logger.debug("get a House by ID success");
		session.close();
		return house;
	}

	/**
	 * @Title: getHouseByHouseNum
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public House getHouseByHouseNum(String houseNum) {
		Session session = getSession();
		House house = null;
		List list = null;
		logger.debug("begin to get a House by houseNum");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from House where houseNum='"+houseNum+"'");
			list = query.list();
			house = (House)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a House by houseNum failed",e);
			throw e;
		}
		logger.debug("get a House by houseNum success");
		session.close();
		return house;
	}

	
	/**
	 * @Title: loadHouseList_ByCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	   public List<House> loadHouseList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager)
	   {
		List<House> list = null;
		String debugMsg = "load house list by company, comId="+comId;
		StringBuilder hql = new StringBuilder();
		hql.append("from House where building in " +
			   "(from Building where project in (from Project where company.comId="+comId+"))");
		hql.append(ParamsToString.toString(params));
		if (order==null){
		    hql.append(" order by houseId desc");
		} else {
		    hql.append(" "+order);
		}
		logger.debug(hql);
		try {
		    list = (List<House>) loadListByCondition(hql.toString(),pager,debugMsg);
		} catch (RuntimeException e){
		    throw e;
		}
		
		return list;
	   }
	
	/**
	 * @Title: loadHouseList_ByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<House> loadHouseList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager)
	{
				List<House> list = null;
				String debugMsg = "load house list by project, proId="+proId;
				StringBuilder hql = new StringBuilder();
				hql.append("from House where building in " +
					   "(from Building where project.proId="+proId+")");
				hql.append(ParamsToString.toString(params));
				if (order==null){
				    hql.append(" order by houseId desc");
				} else {
				    hql.append(" "+order);
				}
				logger.debug(hql);
				try {
				    list = (List<House>) loadListByCondition(hql.toString(),pager,debugMsg);
				} catch (RuntimeException e){
				    throw e;
				}
				
				return list;	
	}

	/**
	 * @Title: loadHouseList_ByBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
    public List<House> loadHouseList_ByBuilding(Integer builId,Map<String,Object>params,String order,Pager pager)
    {
		List<House> list = null;
		String debugMsg = "load house list by building, builId="+builId;
		StringBuilder hql = new StringBuilder();
		hql.append("from House where building.builId="+builId);
		hql.append(ParamsToString.toString(params));
		if (order==null){
		    hql.append(" order by houseId desc");
		} else {
		    hql.append(" "+order);
		}
		logger.debug(hql);
		try {
		    list = (List<House>) loadListByCondition(hql.toString(),pager,debugMsg);
		} catch (RuntimeException e){
		    throw e;
		}
		
		return list;	
    }
    
}
