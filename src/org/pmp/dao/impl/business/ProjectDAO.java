/**
 * Author            : Jason
 * Created On        : 2012-3-21 下午03:05:32
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
import org.pmp.dao.business.IProjectDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectDAO extends BaseDAO implements IProjectDAO {

	static Logger logger = Logger.getLogger(ProjectDAO.class.getName());
	
    /** 
     * @author Elan
     * Created On : 2012-5-3 下午02:46:32
     */
//    public List<?> loadProjectByComID(Pager pager,Integer comId){
//	String debugMsg = "load project by company,comId="+comId;
//        String sql = "from Project where company.comId="+comId;
//        List<?> list = null;
//        try {
//            list = loadListByCondition(sql,pager,debugMsg);
//        } catch(RuntimeException e){
//            throw e;
//        }
//        return list;
//    }
	public void saveProject(Project project) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			session.save(project);
			tx.commit();
		}catch(RuntimeException e){
			logger.error("saving a project failed",e);
			throw e;
		}
		logger.debug("saving a project successful");
		session.close();
	}

	

	public void updateProject(Project project) {
		logger.debug("begin to update a project");
		String hql = "update Project set company=?,proName=?,proDistrict=?,proStreet=?,proAddress=?,deliveryTime=?,"+
					"proHouseCount=?,proDesc=?,proType=?,fireEnabled=?,enabled=? where proId="+project.getProId();
		Session session = getSession();
		try{
		Query query = session.createQuery(hql);
		query.setParameter(0, project.getCompany());
		query.setParameter(1, project.getProName());
		query.setParameter(2, project.getProDistrict());
		query.setParameter(3, project.getProStreet());
		query.setParameter(4, project.getProAddress());
		query.setParameter(5, project.getDeliveryTime());
		query.setParameter(6, project.getProHouseCount());
		query.setParameter(7, project.getProDesc());
		query.setParameter(8, project.getProType());
		query.setParameter(9, project.isFireEnabled());
		query.setParameter(10, project.isEnabled());
			Transaction tx = session.beginTransaction();
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("update a project failed",e);
			throw e;
		}
		logger.debug("update a project success");
		session.close();
	}

	public void deleteProject(Integer projectID) {
		Session session = getSession();
		logger.debug("begin to delete a project by ID");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete Project pro where pro.proId = "+ projectID);
			query.executeUpdate();
			tx.commit();
		}catch(RuntimeException e){
			logger.error("delete a project bu ID failed", e);
			throw e;
		}
		logger.debug("delete a project by ID success");
		session.close();
	}

	public Project getProjectByID(Integer projectID) {
		String hql = "from Project pro where pro.proId="+projectID;
		Project project = (Project)getInstance(hql,"get a Project By ID");
		return project;
	}
	
	public Project getProjectByName(String projectName) {
		Session session = getSession();
		Project project = null;
		List list = null;
		logger.debug("begin to get a project by Name");
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Project where proName ='"+ projectName+"'");
			list = query.list();
			project = (Project)list.get(0);
		}catch(RuntimeException e){
			logger.error("get a project by Name failed",e);
			throw e;
		}
		logger.debug("get a project by Name success");
		session.close();
		return project;
	}
	/**
	 * @Title: loadProjectList_ByCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	 public List<Project> loadProjectList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager)
	 {
		 List<Project> list = null;
			String debugMsg = "load project list by company, comId="+comId;
			StringBuilder hql = new StringBuilder();
			hql.append("from Project where company.comId="+comId);
			hql.append(ParamsToString.toString(params));
			if (order==null){
			    hql.append(" order by proId desc");
			} else {
			    hql.append(" "+order);
			}
			logger.debug(hql);
			try {
			    list = (List<Project>) loadListByCondition(hql.toString(),pager,debugMsg);
			} catch (RuntimeException e){
			    throw e;
			}
			
			return list;
	 }
	 
	/**
	 * @Title: batchSaveProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchSaveProject(List<Project> projectList) {
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Connection conn = session.connection();
			PreparedStatement stmt = conn.prepareStatement("insert into tb_Project(Pro_Name,Com_ID,Pro_District,Pro_Street,Pro_Address,Delivery_Time," +
					"Pro_HouseCount,Pro_Desc,Pro_Type,FIRE_ENABLED,ENABLED) values(?,?,?,?,?,?,?,?,?,?,?)");
			for (Project project : projectList) {
				stmt.setString(1, project.getProName());
				stmt.setInt(2, project.getCompany().getComId());
				stmt.setString(3, project.getProDistrict());
				stmt.setString(4, project.getProStreet());
				stmt.setString(5, project.getProAddress());
				java.sql.Date date=new java.sql.Date(project.getDeliveryTime().getTime());
				stmt.setDate(6, date);
				stmt.setInt(7, project.getProHouseCount());
				stmt.setString(8, project.getProDesc());
				stmt.setString(9, project.getProType());
				stmt.setBoolean(10, project.isFireEnabled());
				stmt.setBoolean(11, project.isEnabled());
				stmt.executeUpdate();
			}
			tx.commit();
		}catch(Exception e){
			System.out.println("Exception"+e);
		}
	}
}
