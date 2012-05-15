/**
 * Author            : Jason
 * Created On        : 2012-4-9 下午04:14:09
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IOwnerService {
    public void addOwner(Owner owner);
    public void editOwner(Owner owner);
    public void deleteOwner(Integer ownerId);
    public Owner getOwnerById(Integer ownerId);
    public List<?> getAllOwner();
    public List<?> loadOwnerList(Pager pager);
    public List<?> loadOwnerByCondition(Integer projectId,Integer buildingId,String keyWord,Pager pager);
    public List<?> batchSaveOwner(List<Owner> ownerList);
    
    /**
     * @author Elan
     */
    public List<?> loadOwnerList_ByProject(Integer proId,Pager pager);
    
    public List<?> loadOwnerList_ByBuilding(Integer builId,Pager pager);
}
