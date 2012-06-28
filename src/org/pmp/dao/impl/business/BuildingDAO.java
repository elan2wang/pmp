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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IBuildingDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Building;


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

    public void saveBuilding(Building instance) {
	String debugMsg = "save building";
	try {
	    saveInstance(instance, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    public List<Integer> batchSaveBuilding(List<Building> buildingList){
	List<Integer> idList = new ArrayList<Integer>();
	Session session = getSession();
	Transaction tx = null;
	try {
	    Connection conn = session.connection();
	    String sql = "insert into tb_Building(Buil_Num,Pro_ID,Buil_Type," +
		"Floor_Count,Skip_Floor,Houses_Per,Unit_Count,ENABLED) values(?,?,?,?,?,?,?,?)";
	    ResultSet rs = null;
            PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	    int i = 0;
	    tx = session.beginTransaction();
	    for(Building buil : buildingList){
		stmt.setString(1, buil.getBuilNum());
		stmt.setInt(2, buil.getProject().getProId());
		stmt.setString(3, buil.getBuilType());
		stmt.setInt(4, buil.getFloorCount());
		stmt.setString(5, buil.getSkipFloor());
		stmt.setInt(6, buil.getHousesPer());
		stmt.setInt(7, buil.getUnitCount());
		stmt.setBoolean(8, true);
		stmt.execute();
		rs = stmt.getGeneratedKeys();
		rs.next();
		Integer id = rs.getInt(1);
		idList.add(id);
	    }
	    
	} catch (RuntimeException e){
	    tx.rollback();
	    session.close();
	    logger.debug(e.getMessage());
	    throw e;
	} catch (SQLException e) {
	    tx.rollback();
	    session.close();
	    logger.debug(e.getMessage());
	} finally {
	    tx.commit();
	    session.close();
	}
	
	return idList;
    }

    public void updateBuilding(Building instance) {
	String debugMsg = "update building";
	try {
	    updateInstance(instance, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    public void deleteBuilding(Building instance) {
	String debugMsg = "delete building";
	String hql = "delete Building bui where bui.builId = "+ instance.getBuilId();
	try {
	    deleteInstance(hql, debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }
    
    public Building getBuildingByID(Integer builId){
	String hql = "from Building bui where bui.builId="+builId;
	String debugMsg = "get building by ID, builId="+builId;
	Building building = null;
	try {
	    building = (Building)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return building;
    }
    
    public Building getBuildingByBuilNum(Integer bulNum) {
	String hql = "from Building bul where bul.builNum = "+bulNum;
	String debugMsg = "get building by builNum, builNum="+bulNum;
	Building building = null;
	try {
	    building = (Building)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return building;
    }

    public Building getBuildingByProjectIdAndBuildingNum(Integer projectId,Integer buildingNum) {
	String debugMsg = "get a Buidling By projectId and buildNum";
        String hql = "from Building where project.proId="+projectId+" and builNum="+buildingNum;
        Building building = null;
        try {
            building = (Building)getInstance(hql, debugMsg);
        } catch (RuntimeException e){
            throw e;
        }
        return building;
    }

    public List<Building> loadBuildingList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager){
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

    public List<Building> loadBuildingList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager){
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
    
}
