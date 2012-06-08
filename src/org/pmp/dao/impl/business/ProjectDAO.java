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
		saveInstance(project,"saving a project");
	}

	

	public void updateProject(Project project) {
		updateInstance(project,"begin to update a project");
	}

	public void deleteProject(Project project) {
		String hql = "delete Project pro where pro.proId = "+ project.getProId();
		deleteInstance(hql,"begin to delete a project by ID");
	}

	public Project getProjectByID(Integer projectID) {
		String hql = "from Project pro where pro.proId="+projectID;
		Project project = (Project)getInstance(hql,"get a Project By ID");
		return project;
	}
	
	public Project getProjectByName(String projectName) {
		String hql = "from Project where proName ='"+ projectName+"'";
		Project project = (Project)getInstance(hql,"begin to get a project by Name");
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
