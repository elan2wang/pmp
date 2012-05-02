/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����02:25:06
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IHouseDAO;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
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
	 * @Title: getHouseByBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getHouseByBuilding(Building building) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get House by Building");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from House where building.builId = "+building.getBuilId());
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get House by building error",e);
			throw e;
		}
		logger.debug("get House by building is success");
		session.close();
		return list;
	}

	/**
	 * @Title: getHouseByOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getHouseByOwner(Owner owner) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get House by Owner");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from House where House.owner = "+owner);
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get House by Owner error",e);
			throw e;
		}
		logger.debug("get House by Owner is success");
		session.close();
		return list;
	}

	/**
	 * @Title: getHouseByIsempty
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getHouseByIsempty(Boolean isempty) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get House by isempty");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from House where House.isempty = "+isempty);
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get House by isempty error",e);
			throw e;
		}
		logger.debug("get House by isempty is success");
		session.close();
		return list;
	}

	/**
	 * @Title: loadBuildingList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadHouseList(Pager pager) {
		List list = null;
		String sql = "from House";
		logger.debug("begin to invoke loadHouseListByCondition()");
		try{
		    list = loadHouseListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load all House list successfully");
		    throw e;
		}
		logger.debug("load all House list successfully");
		return list;
	    }

	/**
	 * @Title: loadEnabledBuildingList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadEnabledHouseList(Pager pager) {
		List list = null;
		String sql = "from House where House.isempty = true";
		logger.debug("begin to invoke loadHouseListByCondition()");
		try{
		    list = loadHouseListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load empty House list failed");
		    throw e;
		}
		logger.debug("load all empty House list successfully");
		return list;
	    }

	/**
	 * @Title: loadDisabledBuildingList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadDisabledHouseList(Pager pager) {
		List list = null;
		String sql = "from House bul where House.isempty = false";
		logger.debug("begin to invoke loadHouseListByCondition()");
		try{
		    list = loadHouseListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load noEmpty House list failed");
		    throw e;
		}
		logger.debug("load all noEmpty House list successfully");
		return list;
	    }
	
	private List loadHouseListByCondition(String sql,Pager pager) {
		Session session = getSession();
		Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize();
		List list1 = null;
		List list2 = null;
		logger.debug("begin to get House list.");
		try {
		    Transaction tx = session.beginTransaction();
		    Query query = session.createQuery(sql);
		    list1 = query.list();
		    pager.setRowsCount(list1.size());
		    query.setFirstResult(startRow);
		    query.setMaxResults(pager.getPageSize());
		    list2 = query.list();
		    tx.commit();
		} catch (RuntimeException e){
		    logger.error("get House list failed.", e);
		    throw e;
		}
		logger.debug("get House list successfully.");
		session.close();
		return list2;
	    }

	/**
	 * @Title: getHouseByProjectOrBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getHouseByProjectOrBuilding(Integer projectId,Integer buildingId,Pager pager) {
		List list = null;
		String sql = null;
		if(buildingId == 0){
			sql = "from House where House.building.project.proId="+projectId;
		}
		else{
			sql = "from House where House.building.project.proId="+projectId+" and House.building.builId="+buildingId;
		}
		logger.debug("begin to invoke loadHouseListByCondition()");
		try{
		    list = loadHouseListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load empty House list failed");
		    throw e;
		}
		logger.debug("load all empty House list successfully");
		return list;
	}
//	select * from tb_House where Buil_ID in (select Buil_ID from tb_Building where Pro_ID=6);

	/**
	 * @Title: getHouseByBuildingNumAndHouseNum
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public House getHouseByBuildingIdAndHouseNum(Integer buildingId,String houseNum) {
		Session session = getSession();
		House house = null;
		List list = null;
		try{
			Query query = session.createQuery("from House house where building.builId="+buildingId+" and houseNum='"+houseNum+"'");
			list = query.list();
			house = (House)list.get(0);
		}catch(RuntimeException e){
			System.out.println(e);
		}
		return house;
	}
}
