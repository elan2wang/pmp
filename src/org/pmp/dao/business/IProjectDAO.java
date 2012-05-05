/**
 * Author            : Jason
 * Created On        : 2012-3-21 下午02:37:41
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IProjectDAO {
    public void saveProject(Project project);
    public void updateProject(Project project);
    public void deleteProject(Integer projectID);
    public Project getProjectByID(Integer projectID);
    public Project getProjectByName(String project);
    public List<?> getAllProject();
    public List<?> getProjectByDistrict(String district,Pager pager);
    public List<?> vagueProject(String key);
    public List<?> loadProjectList(Pager pager);
    public List<?> loadEnabledProjectList(Pager pager);
    public List<?> loadDisabledProjectList(Pager pager);
    public List<?> loadProjectByComID(Pager pager,Integer comId);
    public void batchSaveProject(List<Project> projectList);
}
