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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IHouseOwnerDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.HouseOwner;

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
     * 
     * 已弃用，查询效率太低
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
     * 
     * 已弃用，查询效率太低
     */
    @Override
    public List<HouseOwner> loadHouseOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<HouseOwner> list = null;
	String debugMsg = "load HouseOwner list by Company, comId="+comId;
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

    /**
     * @see org.pmp.dao.business.IHouseOwnerDAO#loadOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Map<String, Object>> loadOwnerList_ByPro(final Integer proId,
	   final Map<String, Object> params, final String order, final Pager pager) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Session session = getSession();
	Transaction tx = null;
	
	Connection conn = session.connection();
	StringBuilder sql = new StringBuilder();
	sql.append("select tb_Owner.Owner_ID,Pro_Name,Owner_Name,Gender,Mobile,Home_Phone,tb_House.House_Num,tb_House.House_Area,Organization " + 
                   "from tb_HouseOwner,tb_House,tb_Owner,tb_Building,tb_Project,tb_Company " +
                   "where tb_HouseOwner.Owner_ID = tb_Owner.Owner_ID and tb_HouseOwner.House_ID = tb_House.House_ID and " +
                   "tb_House.Buil_ID = tb_Building.Buil_ID and tb_Building.Pro_ID = tb_Project.Pro_ID and tb_Project.Pro_ID = "+proId);
	sql.append(ParamsToString.toString(params));
	if (order==null){
	    sql.append(" order by tb_House.House_Num asc");
	} else {
	    sql.append(" "+order);
	}
	logger.debug(sql.toString());
	PreparedStatement stmt = null;
	ResultSet rs= null;
	try {
	    stmt = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    rs = stmt.executeQuery();
	    list = generateList(rs,pager);
            conn.close();
            stmt.close();
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
	 }
         return list;
    }

    /**
     * @see org.pmp.dao.business.IHouseOwnerDAO#loadOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Map<String, Object>> loadOwnerList_ByCom(final Integer comId,
		   final Map<String, Object> params, final String order, final Pager pager) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Session session = getSession();
	Transaction tx = null;
	
	Connection conn = session.connection();
	StringBuilder sql = new StringBuilder();
	sql.append("select tb_Owner.Owner_ID,Pro_Name,Owner_Name,Gender,Mobile,Home_Phone,tb_House.House_Num,tb_House.House_Area,Organization " + 
                   "from tb_HouseOwner,tb_House,tb_Owner,tb_Building,tb_Project,tb_Company " +
                   "where tb_HouseOwner.Owner_ID = tb_Owner.Owner_ID and tb_HouseOwner.House_ID = tb_House.House_ID and " +
                   "tb_House.Buil_ID = tb_Building.Buil_ID and tb_Building.Pro_ID = tb_Project.Pro_ID and " +
                   "tb_Project.Com_ID = tb_Company.Com_ID and tb_Company.Com_ID = "+comId);
	sql.append(ParamsToString.toString(params));
	if (order==null){
	    sql.append(" order by Pro_Name asc,tb_House.House_Num asc");
	} else {
	    sql.append(" "+order);
	}
	logger.debug(sql.toString());
	PreparedStatement stmt = null;
	ResultSet rs= null;
	try {
	    stmt = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    rs = stmt.executeQuery();
	    list = generateList(rs,pager);
            conn.close();
            stmt.close();
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
	 }
         return list;
    }
    
    private List<Map<String, Object>> generateList(ResultSet rs,Pager pager) throws SQLException{
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	rs.last();
        Integer totalRows = rs.getRow();
        pager.setRowsCount(totalRows);
        Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize()+1;
        if(rs.absolute(startRow)){
    	    for(int i =0; i<pager.getPageSize();i++){
    	        Map<String, Object> attrMap = new LinkedHashMap<String, Object>();
    	        attrMap.put("tb_Owner.Owner_ID", rs.getObject(1));
    	        attrMap.put("Pro_Name", rs.getObject(2));
    	        attrMap.put("Owner_Name", rs.getObject(3));
    	        attrMap.put("Gender", rs.getObject(4));
    	        attrMap.put("Mobile", rs.getObject(5));
    	        attrMap.put("Home_Phone", rs.getObject(6));
    	        attrMap.put("tb_House.House_Num", rs.getObject(7));
    	        attrMap.put("tb_House.House_Area", rs.getObject(8));
    	        attrMap.put("Organization", rs.getObject(9));
    	        list.add(attrMap);
    	        rs.next();
    	        if(rs.isLast())break;
    	    }
        }
    	return list;
    }
    
}
