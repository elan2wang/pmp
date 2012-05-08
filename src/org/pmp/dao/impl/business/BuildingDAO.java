/**
 * Author            : Jason
 * Created On        : 2012-3-21 下午10:02:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IBuildingDAO;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.Project;


/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingDAO extends BaseDAO implements IBuildingDAO {

	static Logger logger = Logger.getLogger(BuildingDAO.class.getName());
	
	 
	public void saveBuilding(Building building) {
		logger.debug("begin to save a building");
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(building);
			tx.commit();
		}catch(RuntimeException e){
			logger.error("save a building failed",e);
			session.close();
			throw e;
		}
		logger.debug("save a building success");
		session.close();
	}

	 
	public void updateBuilding(Building building) {
		Session session = getSession();
		logger.debug("begin to update a building");
		String hql = "update Building set project=?,builNum=?,builType=?,floorCount=?,skipFloor=?,housesPer=?,unitCount=?,"+
					"unitTag=?,builDesc=?,enabled=? where builId="+building.getBuilId();
		Query query = session.createQuery(hql);
		query.setParameter(0, building.getProject());
		query.setParameter(1, building.getBuilNum());
		query.setParameter(2, building.getBuilType());
		query.setParameter(3, building.getFloorCount());
		query.setParameter(4, building.getSkipFloor());
		query.setParameter(5, building.getHousesPer());
		query.setParameter(6, building.getUnitCount());
		query.setParameter(7, building.getUnitTag());
		query.setParameter(8, building.getBuilDesc());
		query.setParameter(9, building.isEnabled());
		try{
			Transaction tx = session.beginTransaction();
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("update a building is error",e);
			throw e;
		}
		logger.debug("update a building success");
		session.close();
	}

	public void deleteBuilding(Integer buildingID) {
		Session session = getSession();
		logger.debug("begin to delete a building by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Building bui where bui.builId = "+ buildingID);
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("delete a building bu ID failed", e);
			throw e;
		}
		logger.debug("delete a building by ID success");
		session.close();
	}

	
	public Building getBuildingByID(Integer buildingID) {
		Session session = getSession();
		Building building = null;
		List list = null;
		logger.debug("begin to get a building by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Building bui where bui.builId="+buildingID);
			list = query.list();
			building = (Building)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a building by ID failed",e);
			throw e;
		}
		logger.debug("get a building by ID success");
		session.close();
		return building;
	}


	
	/**
	 * @Title: getBuildingByBuilNum
	 * @Description: ͨ��bulNum����Building
	 *
	 * @param  Integer bulNum
	 * @return Building
	 * @throws TODO
	 */
	public Building getBuildingByBuilNum(Integer bulNum) {
		Session session = getSession();
		Building building = null;
		List list = null;
		logger.debug("begin to get a building by bulNum.");
		try {
		    Transaction tx = session.beginTransaction();
		    Query query = session.createQuery("from Building bul where bul.builNum = "+bulNum);
		    list = query.list();
		    building = (Building)list.get(0);
		} catch (RuntimeException e){
		    logger.error("get a building by bulNum failed.",e);
		    throw e;
		}
		logger.debug("get a building by bulNum successfully.");
		session.close();
		return building;
	    }

	
	/**
	 * @Title: getBuildingByProID
	 * @Description: ͨ��proID����Building
	 *
	 * @param  Integer proID
	 * @return List<Building>
	 * @throws TODO
	 */
	public List getBuildingByPro(Project project) {
		
		Session session = getSession();
		List list = null;
		logger.debug("begin to get building by project");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Building bul where bul.project = "+project);
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get building by project error",e);
			throw e;
		}
		logger.debug("get building by project is success");
		session.close();
		return list;
	}

	
	/**
	 * @Title: getBuildingByBuilType
	 * @Description: ��ݽ������Ͳ鿴
	 *
	 * @param  String builType
	 * @return List<Building>
	 * @throws TODO
	 */
	public List getBuildingByBuilType(String builType) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get building by builType");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Building bul where bul.builType like '%"+builType+"%'");
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get building by builType is failed");
			throw e;
		}
		logger.debug("get building by builType is success");
		session.close();
		return list;
	}

	
	public List loadBuildingList(Pager pager) {
		List list = null;
		String sql = "from Building bul";
		logger.debug("begin to invoke loadBuildingListByCondition()");
		try{
		    list = loadBuildingListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load all Building list successfully");
		    throw e;
		}
		logger.debug("load all Building list successfully");
		return list;
	    }

	public List loadEnabledBuildingList(Pager pager) {
		List list = null;
		String sql = "from Building bul where bul.enable = true";
		logger.debug("begin to invoke loadBuildingListByCondition()");
		try{
		    list = loadBuildingListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load Enabled Building list failed");
		    throw e;
		}
		logger.debug("load all Enabled Building list successfully");
		return list;
	    }

	public List loadDisabledBuildingList(Pager pager) {
		List list = null;
		String sql = "from Building bul where bul.enabled = false";
		logger.debug("begin to invoke loadBuildingListByCondition()");
		try{
		    list = loadBuildingListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load Disabled Building list failed");
		    throw e;
		}
		logger.debug("load all Disabled Building list successfully");
		return list;
	    }
	private List loadBuildingListByCondition(String sql,Pager pager) {
		Session session = getSession();
		Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize();
		List list1 = null;
		List list2 = null;
		logger.debug("begin to get building list.");
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
		    logger.error("get building list failed.", e);
		    throw e;
		}
		logger.debug("get building list successfully.");
		session.close();
		return list2;
	    }


	/**
	 * @Title: saveBuildingHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void saveBuildingHouse(List<String> houseNum, Building building) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			PreparedStatement stmt = conn.prepareStatement("insert into tb_House(Buil_ID,House_Num,ISEMPTY) values(?,?,?)");
			for (String string : houseNum) {
				stmt.setInt(1, building.getBuilId());
				stmt.setString(2, string);
				stmt.setBoolean(3, true);
				stmt.executeUpdate();
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
	}


	/**
	 * @Title: loadBuildingListByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadBuildingListByProject(Pager pager, Integer projectId) {
		List list = null;
		String sql = "from Building bul where bul.project.proId="+projectId;
		logger.debug("begin to invoke loadBuildingListByCondition()");
		try{
		    list = loadBuildingListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load all Building list successfully");
		    throw e;
		}
		logger.debug("load all Building list successfully");
		return list;
	}


	/**
	 * @Title: batchSaveBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchSaveBuilding(List<Building> buildingList) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			PreparedStatement stmt = conn.prepareStatement("insert into tb_Building(Buil_Num,Pro_ID,Buil_Type," +
					"Floor_Count,Skip_Floor,Houses_Per,Unit_Count,Unit_Tag,Buil_Desc,ENABLED," +
					"CondoFee_Rate) values(?,?,?,?,?,?,?,?,?,?,?)");
			for (Building building : buildingList) {
				stmt.setString(1, building.getBuilNum());
				stmt.setInt(2, building.getProject().getProId());
				stmt.setString(3, building.getBuilType());
				stmt.setInt(4, building.getFloorCount());
				stmt.setString(5, building.getSkipFloor());
				stmt.setInt(6, building.getHousesPer());
				stmt.setInt(7, building.getUnitCount());
				stmt.setString(8, building.getUnitTag());
				stmt.setString(9, building.getBuilDesc());
				stmt.setBoolean(10, building.isEnabled());
				stmt.setDouble(11, building.getCondoFeeRate());
				stmt.executeUpdate();
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
	}


	/**
	 * @Title: getBuildingByProjectIdAndBuildingNum
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,
			Integer buildingNum) {
		Building building = null;
		List list = null;
		Session session = getSession();
		Query query = session.createQuery("from Building where project.proId="+projectId+" and builNum="+buildingNum);
		list = query.list();
		building = (Building)list.get(0);
		return building;
	}
}
