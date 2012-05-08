/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����05:21:56
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

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
    
	public List<?> loadProjectByComID(Pager pager,Integer comId){
	    return this.projectDAO.loadProjectByComID(pager, comId);
	}
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
	 * @Title: loadProjectList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadProjectList(Pager pager) {
		return projectDAO.loadProjectList(pager);
	}

	/**
	 * @Title: loadEnabledProjectList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadEnabledProjectList(Pager pager) {
		return projectDAO.loadEnabledProjectList(pager);
	}

	/**
	 * @Title: loadDisabledProjectList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadDisabledProjectList(Pager pager) {
		return projectDAO.loadDisabledProjectList(pager);
	}

	/**
	 * @Title: getProjectByDistrict
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getProjectByDistrict(String district,Pager pager) {
		return projectDAO.getProjectByDistrict(district,pager);
	}

	/**
	 * @Title: getAllProject
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getAllProject() {
		return projectDAO.getAllProject();
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
