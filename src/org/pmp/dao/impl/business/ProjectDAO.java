/**
 * Author            : Elan
 * Created On        : 2012-6-29 下午05:25:48
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
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IProjectDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Project;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ProjectDAO extends BaseDAO implements IProjectDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger
	    .getLogger(ProjectDAO.class.getName());

    /**
     * @see org.pmp.dao.business.IProjectDAO#saveProject(org.pmp.vo.Project)
     */
    @Override
    public void saveProject(Project instance) {
	String debugMsg = "save Project";
	try {
	    saveInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IProjectDAO#updateProject(org.pmp.vo.Project)
     */
    @Override
    public void updateProject(Project instance) {
	String debugMsg = "update Project";
	try {
	    updateInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IProjectDAO#deleteProject(org.pmp.vo.Project)
     */
    @Override
    public void deleteProject(Project instance) {
	String debugMsg = "delete Project";
	try {
	    deleteInstance(instance,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    /**
     * @see org.pmp.dao.business.IProjectDAO#batchSaveProject(java.util.List)
     */
    @Override
    public void batchSaveProject(final List<Project> projectList) {
	logger.debug("begin to batch save project");
	logger.debug("list.size="+projectList.size());
	Work work = new Work(){
	    public void execute(Connection connection)throws SQLException{
		String sql = "insert into tb_Project(Pro_Name,Com_ID,Pro_District,Pro_Street,Pro_Address,Delivery_Time," +
			"Pro_HouseCount,Pro_Desc,Pro_Type,FIRE_ENABLED,ENABLED) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (Project pro : projectList){
		    stmt.setString(1, pro.getProName());
		    stmt.setInt(2, pro.getCompany().getComId());
		    stmt.setString(3, pro.getProDistrict());
		    stmt.setString(4, pro.getProStreet());
		    stmt.setString(5, pro.getProAddress());
		    if(pro.getDeliveryTime()!=null){
			java.sql.Date deliveryTime=new java.sql.Date(pro.getDeliveryTime().getTime());
			stmt.setDate(6, deliveryTime);
		    } else {
			stmt.setDate(6, null);
		    }
		    stmt.setInt(7, pro.getProHouseCount());
		    stmt.setString(8, pro.getProDesc());
		    stmt.setString(9, pro.getProType());
		    stmt.setBoolean(10, true);
		    stmt.setBoolean(11, true);
		    stmt.executeUpdate();
		}
	    }
	};
	executeWork(work);
	logger.debug("successfully batch save project");
    }

    /**
     * @see org.pmp.dao.business.IProjectDAO#getProjectByID(java.lang.Integer)
     */
    @Override
    public Project getProjectByID(Integer proId) {
	String debugMsg = "begin to get a project by ID";
	String hql = "from Project where proId ="+proId;
	Project pro = null;
	try {
	    pro = (Project)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return pro;
    }

    /**
     * @see org.pmp.dao.business.IProjectDAO#getProjectByName(java.lang.String)
     */
    @Override
    public Project getProjectByName(String proName) {
	String debugMsg = "begin to get a project by Name";
	String hql = "from Project where proName ='"+ proName+"'";
	Project pro = null;
	try {
	    pro = (Project)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return pro;
    }

    /**
     * @see org.pmp.dao.business.IProjectDAO#loadProjectList_ByCompany(java.lang.Integer, java.util.Map, java.lang.String, org.pmp.util.Pager)
     */
    @Override
    public List<Project> loadProjectList_ByCompany(Integer comId,
	    Map<String, Object> params, String order, Pager pager) {
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
    
}
