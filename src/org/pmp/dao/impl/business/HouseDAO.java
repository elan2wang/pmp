/**
 * Author            : ELAN
 * Created On        : 2012-3-22 下午02:25:06
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
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IHouseDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.House;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class HouseDAO extends BaseDAO implements IHouseDAO {
    Logger logger = Logger.getLogger(HouseDAO.class.getName());
    
    public void saveHouse(House house){
	String debugMsg = "save house instance";
	try {
	    saveInstance(house,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public void updateHouse(House house){
	String debugMsg = "update house instance,houseId="+house.getHouseId();
	try {
	    updateInstance(house,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public void batchDelete(final List<House> list){
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "delete tb_House where House_ID=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i=0;i<list.size();i++){
		    stmt.setInt(1, list.get(i).getHouseId());
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch delete house");
    }

    public void generateHouse(final List<Integer> idList){
	logger.debug("begin to generate House with builIdList by call procedure house_generate");
        Work work = new Work(){
            public void execute(Connection connection)throws SQLException{
                String procedure = "{call house_generate(?) }";
                CallableStatement cstmt = connection.prepareCall(procedure);
                for(Integer id : idList){
                    cstmt.setInt(1, id);
                    cstmt.executeUpdate();
                }
            }
        };
	executeWork(work);
	logger.debug("successfully generate House with builIdList by call procedure house_generate");
    }
    
    public House getHouseByID(Integer houseId){
	String debugMsg = "get house instance by houseId,houseId="+houseId;
	String hql = "from House where houseId="+houseId;
	House house = null;
	try {
	    house = (House)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return house;
    }
    
    public House getHouseByHouseNum(String houseNum){
	String debugMsg = "get house instance by houseNum,houseNum="+houseNum;
	String hql = "from House where houseNum="+houseNum;
	House house = null;
	try {
	    house = (House)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return house;
    }
    
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
    
    public List<House> loadHouseList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager)
    {
	List<House> list = null;String debugMsg = "load house list by project, proId="+proId;
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
