/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午04:05:52
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.OwnerRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IOwnerRepairService {
    public void addOwnerRepair(OwnerRepair instance);
    
    public void editOwnerRepair(OwnerRepair instance);
    
    public void batchDeleteOwnerRepair(List<OwnerRepair> list);
    
    public OwnerRepair getOwnerRepair_ByID(Integer opId);
    
    public List<OwnerRepair> loadOwnerRepairList_ByCompany(Integer comId, Map<String, Object> params, String order, Pager pager);
    
    public List<OwnerRepair> loadOwnerRepairList_ByProject(Integer proId, Map<String, Object> params, String order, Pager pager);
}
