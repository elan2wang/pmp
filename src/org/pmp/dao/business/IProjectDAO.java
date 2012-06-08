/**
 * Author            : Jason
 * Created On        : 2012-3-21 下午02:37:41
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IProjectDAO {
    public void saveProject(Project project);
    public void updateProject(Project project);
    public void deleteProject(Project project);
    public Project getProjectByID(Integer projectID);
    public Project getProjectByName(String project);
    
    public List<Project> loadProjectList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
    
    public void batchSaveProject(List<Project> projectList);
}
