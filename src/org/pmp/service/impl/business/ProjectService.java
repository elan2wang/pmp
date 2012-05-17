/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����05:21:56
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

import org.pmp.dao.business.IProjectDAO;
import org.pmp.service.business.IProjectService;
import org.pmp.util.Pager;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectService implements IProjectService {

	IProjectDAO projectDAO;
    
	/**
	 * @param projectDAO the projectDAO to set
	 */
	public void setProjectDAO(IProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * @Title: addProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void addProject(Project project) {
		projectDAO.saveProject(project);
	}

	/**
	 * @Title: editProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void editProject(Project project) {
		projectDAO.updateProject(project);
	}

	/**
	 * @Title: deleteProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteProject(Integer projectID) {
		projectDAO.deleteProject(projectID);
	}

	/**
	 * @Title: getProjectByID
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Project getProjectByID(Integer projectID) {
		return projectDAO.getProjectByID(projectID);
	}
	
	/**
	 * @Title: getProjectByName
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Project getProjectByName(String projectName) {
		return projectDAO.getProjectByName(projectName);
	}
	/**
	 * @Title: loadProjectList_ByCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List<Project> loadProjectList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager)
	{
		return projectDAO.loadProjectList_ByCompany(comId, params, order, pager);
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
		projectDAO.batchSaveProject(projectList);
		
	}

}
