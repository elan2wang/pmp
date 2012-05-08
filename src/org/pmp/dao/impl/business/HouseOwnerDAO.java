/**
 * Author            : Jason
 * Created On        : 2012-4-15 下午12:15:03
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
import org.pmp.dao.business.IHouseOwnerDAO;
import org.pmp.vo.Building;
import org.pmp.vo.House;
import org.pmp.vo.HouseOwner;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class HouseOwnerDAO extends BaseDAO implements IHouseOwnerDAO {

	Logger logger = Logger.getLogger(HouseOwnerDAO.class.getName());
	/**
	 * @Title: addHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void addHouseOwner(HouseOwner houseOwner) {
		logger.debug("begin save houseOwner");
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(houseOwner);
			tx.commit();
		}catch(RuntimeException e){
			logger.error("save houseOwnerFailed"+e);
			session.close();
			throw e;
		}
		logger.debug("save houseOwnerSuccess");
		session.close();
	}

	/**
	 * @Title: updateHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void updateHouseOwner(HouseOwner houseOwner) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.update(houseOwner);
			tx.commit();
		}catch(RuntimeException e){
			session.close();
			throw e;
		}
		session.close();
	}

	/**
	 * @Title: getOwnerByHouse
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public HouseOwner getOwnerByHouse(House house) {
		Session session = getSession();
		List list = null;
		HouseOwner houseOwner = null;
		try{
			Query query = session.createQuery("from HouseOwner where house.houseId="+house.getHouseId());
//			Query query = session.createQuery("from HouseOwner where house.houseId=53");
			list = query.list();
			houseOwner = (HouseOwner)list.get(0);
		}catch(RuntimeException e){
			session.close();
			throw e;
		}
		session.close();
		return houseOwner;
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
	public HouseOwner getHouseByOwner(Owner owner)
	{
		Session session = getSession();
		List list = null;
		HouseOwner houseOwner = null;
		try{
			Query query = session.createQuery("from HouseOwner where owner.ownerId="+owner.getOwnerId());
//			Query query = session.createQuery("from HouseOwner where house.houseId=53");
			list = query.list();
			houseOwner = (HouseOwner)list.get(0);
		}catch(RuntimeException e){
			session.close();
			throw e;
		}
		session.close();
		return houseOwner;
	}
	/**
	 * @Title: deleteHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteHouseOwner(Integer hoId) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete HouseOwner where hoId="+hoId);
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("deleteHouseOwner failed"+e);
			session.close();
			throw e;
		}
		session.close();
	}

	/**
	 * @Title: batchAddHouseOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchAddHouseOwner(List<Integer> ownerIdList,Map<?,?> map) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			Owner owner = null;
			House house = null;
			PreparedStatement stmt1 = conn.prepareStatement("insert into tb_HouseOwner(House_ID,Owner_ID) values(?,?)");
			PreparedStatement stmt2 = conn.prepareStatement("update tb_House set House_Area=?,ISEMPTY=? where House_ID=?");
			for (Integer ownerId : ownerIdList) {
				Query query = session.createQuery("from Owner where ownerId="+ownerId);
				owner = (Owner)query.list().get(0);
				house = (House)map.get(owner.getOwnerDesc());
				stmt1.setInt(1,house.getHouseId());
				stmt1.setInt(2,owner.getOwnerId());
				stmt2.setDouble(1,house.getHouseArea());
				stmt2.setBoolean(2,false);
				stmt2.setInt(3, house.getHouseId());
				stmt1.executeUpdate();
				stmt2.executeUpdate();
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
	}

}
