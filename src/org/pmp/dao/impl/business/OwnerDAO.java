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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IOwnerDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerDAO extends BaseDAO implements IOwnerDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(OwnerDAO.class.getName());

    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.dao.business.IOwnerDAO#saveOwner(org.pmp.vo.Owner)
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
     * @see org.pmp.dao.business.IOwnerDAO#updateOwner(org.pmp.vo.Owner)
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
     * @see org.pmp.dao.business.IOwnerDAO#batchDelete(java.util.List)
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
     * @see org.pmp.dao.business.IOwnerDAO#getOwner_ById(java.lang.Integer)
     */
    @Override
    public Owner getOwner_ById(Integer ownerId) {
	String hql = "from Owner where ownerId="+ownerId;
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
     * @see org.pmp.dao.business.IOwnerDAO#loadOwnerList_ByBuil(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByBuil(Integer builId,
	    Map<String, Object> params, String order, Pager pager) {
	List<?> list = null;
	String debugMsg = "load Owner list by building, builId="+builId;
	StringBuilder hql = new StringBuilder();
	
	String aa = "from Owner where ownerId in (select owner.ownerId " +
                    "from HouseOwner where house.houseId in (select houseId from House " + 
                    "where building.builId="+builId +"))";
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
     * @see org.pmp.dao.business.IOwnerDAO#loadOwnerList_ByPro(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
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
     * @see org.pmp.dao.business.IOwnerDAO#loadOwnerList_ByCom(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<?> loadOwnerList_ByCom(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
	List<?> list = null;
	String debugMsg = "load Owner list by company, comId="+comId;
	StringBuilder hql = new StringBuilder();
	
	String aa = "from Owner where ownerId in (select owner.ownerId " +
                    "from HouseOwner where house.houseId in (select houseId from House " + 
                    "where building.builId in (select builId from Building where project.proId in ("+
                    "select proId from Project where company.comId="+comId+"))))";
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
     * @see org.pmp.dao.business.IOwnerDAO#batchSave(java.util.List)
     */
    
    @Override
    public List<Integer> batchSave(List<Owner> list) {
	List<Integer> idList = new ArrayList<Integer>();
	Session session = getSession();
	Transaction tx = null;
	try {
	    Connection conn = session.connection();
	    String sql = "insert into tb_Owner(Owner_Name,Gender,Nationality,Native," +
		"Birthday,ISMARRIED,Organization,Hobby,Identity_Type,Identity_Code,Home_Phone,Mobile,Get_Time,Decorate_Time," +
		"In_Time,Park_Num,Car_Num,Car_Type,Storeroom,House_Area,Use_Style,Other_Address,Other_Postcode,Emergency_Contact," +
		"Emergency_Phone,House_Num,Owner_Desc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    ResultSet rs = null;
	    
            PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	    int i = 0;
	    tx = session.beginTransaction();
	    for (Owner owner : list){
		stmt.setString(1, owner.getOwnerName());
		stmt.setString(2, owner.getGender());
		stmt.setString(3, owner.getNationality());
		stmt.setString(4, owner.getNative_());
		if (owner.getBirthday()!=null){
		    java.sql.Date date=new java.sql.Date(owner.getBirthday().getTime());
	    	    stmt.setDate(5, date);
		} else {
		    stmt.setDate(5, null);
		}
    		stmt.setBoolean(6, owner.isIsmarried());
    		stmt.setString(7, owner.getOrganization());
    		stmt.setString(8, owner.getHobby());
    		stmt.setString(9, owner.getIdentityType());
    		stmt.setString(10, owner.getIdentityCode());
    		stmt.setString(11, owner.getHomePhone());
    		stmt.setString(12, owner.getMobile());
    		if(owner.getGetTime()!=null){
    		    java.sql.Date date1 = new java.sql.Date(owner.getGetTime().getTime());
    		    stmt.setDate(13, date1);
    		} else {
    		    stmt.setDate(13, null);
    		}
    		if(owner.getDecorateTime()!=null){
    		    java.sql.Date date2 = new java.sql.Date(owner.getDecorateTime().getTime());
    		    stmt.setDate(14, date2);
    		} else {
    		    stmt.setDate(14, null);
    		}
    		if(owner.getInTime()!=null){
    		    java.sql.Date date3 = new java.sql.Date(owner.getInTime().getTime());
    		    stmt.setDate(15, date3);
    		} else {
    		    stmt.setDate(15, null);
    		}
    		stmt.setString(16, owner.getParkNum());
    		stmt.setString(17, owner.getCarNum());
    		stmt.setString(18, owner.getCarType());
    		stmt.setString(19, owner.getStoreroom());
    		stmt.setDouble(20, owner.getHouseArea());
    		stmt.setString(21, owner.getUseStyle());
    		stmt.setString(22, owner.getOtherAddress());
    	        stmt.setString(23, owner.getOtherPostcode());
    		stmt.setString(24, owner.getEmergencyContact());
    		stmt.setString(25, owner.getEmergencyPhone());
    		stmt.setString(26, owner.getHouseNum());
    		stmt.setString(27, owner.getOwnerDesc());
    		stmt.execute();
    		rs = stmt.getGeneratedKeys();
    		rs.next();
    		Integer id = rs.getInt(1);
    		idList.add(id);
	    }
	} catch (RuntimeException e){
	    tx.rollback();
	    session.close();
	    e.printStackTrace();
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

}
