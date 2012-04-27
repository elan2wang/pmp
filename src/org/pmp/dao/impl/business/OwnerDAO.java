/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����04:17:02
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IOwnerDAO;
import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class OwnerDAO extends BaseDAO implements IOwnerDAO {
	Logger logger = Logger.getLogger(OwnerDAO.class.getName());
	/**
	 * @Title: saveOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void saveOwner(Owner owner) {
		logger.debug("begin to save a Owner");
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(owner);
			tx.commit();
		}catch(RuntimeException e){
			logger.error("save a Owner failed",e);
			throw e;
		}
		logger.debug("save a Owner success");
		session.close();
	}

	/**
	 * @Title: updateOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void updateOwner(Owner owner) {
		Session session = getSession();
		logger.debug("begin to update a Owner");
//		String hql = "update Owner set ownerName=?,gender=?,nationality=?,native_=?birthday=?,"+
//					"ismarried=?,organization=?,hobby=?,identityType=?,identityCode=?,homePhone=?,mobile=?,getTime=?,decorateTime=?,inTime=?,"+
//					"parkNum=?,carNum=?,carType=?,storeroom=?,houseArea=?,useStyle=?,otherAddress=?,otherPostcode=?,emergencyContact=?," +
//					"emergencyPhone=?,houseNum=?,ownerDesc=?";
//		Query query = session.createQuery(hql);
//		query.setParameter(0, owner.getOwnerName());
//		query.setParameter(1, owner.getGender());
//		query.setParameter(2, owner.getNationality());
//		query.setParameter(3, owner.getNative_());
//		query.setParameter(4, owner.getBirthday());
//		query.setParameter(5, owner.isIsmarried());
//		query.setParameter(6, owner.getOrganization());
//		query.setParameter(7, owner.getHobby());
//		query.setParameter(8, owner.getIdentityType());
//		query.setParameter(9, owner.getIdentityCode());
//		query.setParameter(10, owner.getHomePhone());
//		query.setParameter(11, owner.getMobile());
//		query.setParameter(12, owner.getGetTime());
//		query.setParameter(13, owner.getDecorateTime());
//		query.setParameter(14, owner.getInTime());
//		query.setParameter(15, owner.getParkNum());
//		query.setParameter(16, owner.getCarNum());
//		query.setParameter(17, owner.getCarType());
//		query.setParameter(18, owner.getStoreroom());
//		query.setParameter(19, owner.getHouseArea());
//		query.setParameter(20, owner.getUseStyle());
//		query.setParameter(21, owner.getOtherAddress());
//		query.setParameter(22, owner.getOtherPostcode());
//		query.setParameter(23, owner.getEmergencyContact());
//		query.setParameter(24, owner.getEmergencyPhone());
//		query.setParameter(25, owner.getHouseNum());
//		query.setParameter(26, owner.getOwnerDesc());
		try{
			Transaction tx = session.beginTransaction();
			session.update(owner);
			tx.commit();
		}catch(RuntimeException e){
			logger.error("update a Owner is error",e);
			throw e;
		}
		logger.debug("update a Owner success");
		session.close();
	}

	/**
	 * @Title: deleteOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteOwner(Integer ownerId) {
		Session session = getSession();
		logger.debug("begin to delete a Owner by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Owner where ownerId= "+ ownerId);
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("delete a Owner bu ID failed", e);
			throw e;
		}
		logger.debug("delete a Owner by ID success");
		session.close();
	}

	/**
	 * @Title: getOwnerByID
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Owner getOwnerByID(Integer ownerId) {
		Session session = getSession();
		Owner owner = null;
		List list = null;
		logger.debug("begin to get a Owner by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Owner where ownerId="+ownerId);
			list = query.list();
			owner = (Owner)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a Owner by ID failed",e);
			throw e;
		}
		logger.debug("get a Owner by ID success");
		session.close();
		return owner;
	}

	/**
	 * @Title: getOwnerByownerName
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getOwnerByownerName(String ownerName) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get Owner by ownerName");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Owner where ownerName = "+ownerName);
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get Owner by ownerName error",e);
			throw e;
		}
		logger.debug("get Owner by ownerName is success");
		session.close();
		return list;
	}

	/**
	 * @Title: getOwnerByProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getOwnerByProject(Project project) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get Owner by project");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Owner where project = "+project);
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get Owner by project error",e);
			throw e;
		}
		logger.debug("get Owner by project is success");
		session.close();
		return list;
	}

	/**
	 * @Title: getOwnerByBuilding
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getOwnerByBuilding(Building building) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get Owner by building");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Owner where building = "+building);
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get Owner by building error",e);
			throw e;
		}
		logger.debug("get Owner by building is success");
		session.close();
		return list;
	}

	/**
	 * @Title: getOwnerByKeyWorld
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getOwnerByKeyWorld(String keyWorld) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get Owner by keyword");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Owner where ownerName like '%"+keyWorld+"%'");
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get Owner by keyworld is failed");
			throw e;
		}
		logger.debug("get Owner by keyworld is success");
		session.close();
		return list;
	}

	/**
	 * @Title: loadOwnerList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadOwnerList(Pager pager) {
		List list = null;
		String sql = "from Owner";
		logger.debug("begin to invoke loadOwnerListByCondition()");
		try{
		    list = loadOwnerListByCondition(sql,pager);
		} catch (RuntimeException e){
		    logger.error("load all Owner list successfully");
		    throw e;
		}
		logger.debug("load all Owner list successfully");
		return list;
	    }
	private List loadOwnerListByCondition(String sql,Pager pager) {
		Session session = getSession();
		Integer startRow = (pager.getCurrentPage()-1)*pager.getPageSize();
		List list1 = null;
		List list2 = null;
		logger.debug("begin to get Owner list.");
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
		    logger.error("get Owner list failed.", e);
		    throw e;
		}
		logger.debug("get Owner list successfully.");
		session.close();
		return list2;
	    }

	/**
	 * @Title: batchSaveOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<Integer> batchSaveOwner(List<Owner> ownerList) {
		Session session = getSession();
		List ownerIdList = new ArrayList();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			String sql = "insert into tb_Owner(Owner_Name,Gender,Nationality,Native," +
			"Birthday,ISMARRIED,Organization,Hobby,Identity_Type,Identity_Code,Home_Phone,Mobile,Get_Time,Decorate_Time," +
			"In_Time,Park_Num,Car_Num,Car_Type,Storeroom,House_Area,Use_Style,Other_Address,Other_Postcode,Emergency_Contact," +
			"Emergency_Phone,House_Num,Owner_Desc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ResultSet rs = null;
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (Owner owner : ownerList) {
				stmt.setString(1, owner.getOwnerName());
				stmt.setString(2, owner.getGender());
				stmt.setString(3, owner.getNationality());
				stmt.setString(4, owner.getNative_());
				java.sql.Date date=new java.sql.Date(owner.getBirthday().getTime());
				stmt.setDate(5, date);
				stmt.setBoolean(6, owner.isIsmarried());
				stmt.setString(7, owner.getOrganization());
				stmt.setString(8, owner.getHobby());
				stmt.setString(9, owner.getIdentityType());
				stmt.setString(10, owner.getIdentityCode());
				stmt.setString(11, owner.getHomePhone());
				stmt.setString(12, owner.getMobile());
				date = new java.sql.Date(owner.getGetTime().getTime());
				stmt.setDate(13, date);
				date = new java.sql.Date(owner.getDecorateTime().getTime());
				stmt.setDate(14, date);
				date = new java.sql.Date(owner.getInTime().getTime());
				stmt.setDate(15, date);
				stmt.setString(16, owner.getParkNum());
				stmt.setString(17, owner.getCarNum());
				stmt.setString(18, owner.getCarType());
				stmt.setString(19, owner.getStoreroom());
				stmt.setInt(20, owner.getHouseArea());
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
				ownerIdList.add(id);
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
		return ownerIdList;
	}
}
