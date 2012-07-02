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
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IProjectDAO {
    public void saveProject(Project instance);
    public void updateProject(Project instance);
    public void deleteProject(Project instance);
    
    public void batchSaveProject(List<Project> projectList);
    
    public Project getProjectByID(Integer projectID);
    public Project getProjectByName(String proName);
    
    public List<Project> loadProjectList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
    
}
