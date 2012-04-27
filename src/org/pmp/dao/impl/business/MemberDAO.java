/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����05:04:56
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
import org.pmp.dao.business.IMemberDAO;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class MemberDAO extends BaseDAO implements IMemberDAO {
	Logger logger = Logger.getLogger(Member.class.getName());
	/**
	 * @Title: saveMember
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchSaveMember(List<Member> memberList,Integer ownerId ) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			PreparedStatement stmt = conn.prepareStatement("insert into tb_Member(Owner_ID,Mem_Name,Mem_Relation,Mem_Identity,Mem_Phone) values(?,?,?,?,?)");
			for (Member member : memberList) {
				stmt.setInt(1,ownerId);
				stmt.setString(2,member.getMemName());
				stmt.setString(3, member.getMemRelation());
				stmt.setString(4, member.getMemIdentity());
				stmt.setString(5, member.getMemPhone());
				stmt.executeUpdate();
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
	}

	/**
	 * @Title: updateMember
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchUpdateMember(List<Member> memberList,Integer ownerId) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			PreparedStatement stmt = conn.prepareStatement("update tb_Member set Owner_ID=?,Mem_Name=?,Mem_Relation=?,Mem_Identity=?,Mem_Phone=? where Mem_ID=?");
			for (Member member : memberList) {
				stmt.setInt(1,ownerId);
				stmt.setString(2,member.getMemName());
				stmt.setString(3, member.getMemRelation());
				stmt.setString(4, member.getMemIdentity());
				stmt.setString(5, member.getMemPhone());
				stmt.setInt(6, member.getMemId());
				stmt.executeUpdate();
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
	}

	/**
	 * @Title: deleteMember
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteMember(Integer memId) {
		Session session = getSession();
		logger.debug("begin to delete a Member by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Member where Member.memId = "+ memId);
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("delete a Member bu memId failed", e);
			throw e;
		}
		logger.debug("delete a Member by memId success");
		session.close();
	}

	/**
	 * @Title: getMemberByID
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Member getMemberByID(Integer memId) {
		Session session = getSession();
		Member member = null;
		List list = null;
		logger.debug("begin to get a Member by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Member where Member.memId="+memId);
			list = query.list();
			member = (Member)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a Member by ID failed",e);
			throw e;
		}
		logger.debug("get a Member by ID success");
		session.close();
		return member;
	}

	/**
	 * @Title: getMemberByName
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Member getMemberByName(String memName) {
		Session session = getSession();
		Member member = null;
		List list = null;
		logger.debug("begin to get a Member by memName");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Member where Member.memName='"+memName+"'");
			list = query.list();
			member = (Member)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a Member by memName failed",e);
			throw e;
		}
		logger.debug("get a Member by memName success");
		session.close();
		return member;
	}

	/**
	 * @Title: getMemberByOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getMemberByOwner(Owner owner) {
		Session session = getSession();
		List list = null;
		logger.debug("begin to get Member by Owner");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Member where owner.ownerId = "+owner.getOwnerId());
			list = query.list();
		}catch(RuntimeException e){
			logger.error("get Member by Owner error",e);
			throw e;
		}
		logger.debug("get Member by Owner is success");
		session.close();
		return list;
	}

}
