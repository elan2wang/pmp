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
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IBuildingDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
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
		saveInstance(building,"saving a building");
	}

	 
	public void updateBuilding(Building building) {
		updateInstance(building,"begin to update a building");
	}

	public void deleteBuilding(Integer buildingID) {
		String hql = "delete Building bui where bui.builId = "+ buildingID;
		deleteInstance(hql,"begin to delete a building by ID");
	}

	
	public Building getBuildingByID(Integer buildingID) {
		String hql = "from Building bui where bui.builId="+buildingID;
		Building building = (Building)getInstance(hql,"begin to get a building by ID");
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
		String hql = "from Building bul where bul.builNum = "+bulNum;
		Building building = (Building)getInstance(hql,"begin to get a building by bulNum.");
		return building;
	    }

	


	/**
	 * @Title: loadBuildingList_ByCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<Building> loadBuildingList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager)
	{
		List<Building> list = null;
		String debugMsg = "load building list by company, comId="+comId;
		StringBuilder hql = new StringBuilder();
		hql.append("from Building where project in (from Project where company.comId="+comId+")");
		hql.append(ParamsToString.toString(params));
		if (order==null){
		    hql.append(" order by builId desc");
		} else {
		    hql.append(" "+order);
		}
		logger.debug(hql);
		try {
		    list = (List<Building>) loadListByCondition(hql.toString(),pager,debugMsg);
		} catch (RuntimeException e){
		    throw e;
		}
		
		return list;
	}
	
	/**
	 * @Title: loadBuildingList_ByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
    public List<Building> loadBuildingList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager)
    {
				List<Building> list = null;
				String debugMsg = "load building list by project, proId="+proId;
				StringBuilder hql = new StringBuilder();
				hql.append("from Building where project.proId="+proId);
				hql.append(ParamsToString.toString(params));
				if (order==null){
				    hql.append(" order by builId desc");
				} else {
				    hql.append(" "+order);
				}
				logger.debug(hql);
				try {
				    list = (List<Building>) loadListByCondition(hql.toString(),pager,debugMsg);
				} catch (RuntimeException e){
				    throw e;
				}
				
				return list;
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
		String hql = "from Building where project.proId="+projectId+" and builNum="+buildingNum;
		Building building = (Building)getInstance(hql,"get a Buidling By projectId and buildNum");
		return building;
	}
}
