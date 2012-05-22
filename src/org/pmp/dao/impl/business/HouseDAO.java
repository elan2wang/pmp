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
import org.pmp.vo.Project;

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
		saveInstance(house,"saving a house");
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
		updateInstance(house,"begin to update a house");
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
		String hql = "delete House where houseId = "+ houseID;
		deleteInstance(hql,"begin to delete a House by ID");
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
		String hql = "from House where houseId="+houseId;
		House house = (House)getInstance(hql,"get a House By ID");
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
		String hql = "from House where houseNum="+houseNum;
		House house = (House)getInstance(hql,"begin to get a building by bulNum.");
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
