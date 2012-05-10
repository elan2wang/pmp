/**
 * Author            : Jason
 * Created On        : 2012-3-22 ����03:37:15
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Building;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IOwnerDAO {
    public void saveOwner(Owner owner);
    public void updateOwner(Owner owner);
    public void deleteOwner(Integer ownerId);
    public Owner getOwnerByID(Integer ownerId);
    public List<?> getOwnerByownerName(String ownerName);
    public List<?> getOwnerByProject(Project project);
    public List<?> getOwnerByBuilding(Building building);
    public List<?> getOwnerByKeyWorld(String keyWorld);
    public List<?> loadOwnerList(Pager pager);
    public List<Integer> batchSaveOwner(List<Owner> ownerList);
    
    /**
     * @author Elan
     */
    public List<?> loadOwnerList_ByProject(Integer proId,Pager pager);
    
    public List<?> loadOwnerList_ByBuilding(Integer builId,Pager pager);
}
