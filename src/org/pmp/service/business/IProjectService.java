/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����05:18:23
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IProjectService {
    public void addProject(Project project);
    public void editProject(Project project);
    public void deleteProject(Project project);
    public Project getProjectByID(Integer projectID);
    public Project getProjectByName(String projectName);
    public List<Project> loadProjectList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
    public void batchSaveProject(List<Project> projectList);
}
