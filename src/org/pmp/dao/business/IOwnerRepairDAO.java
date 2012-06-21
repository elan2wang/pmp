/**
 * Author            : Elan
 * Created On        : 2012-6-21 下午03:05:17
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.OwnerRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IOwnerRepairDAO {
    public void saveOwnerRepair(OwnerRepair instance);
    
    public void updateOwnerRepair(OwnerRepair instance);
    
    public void batchDeleteOwnerRepair(List<OwnerRepair> list);
    
    public List<OwnerRepair> loadOwnerRepairList_ByCompany(Integer comId, Map<String, Object> params, String order, Pager pager);
    
    public List<OwnerRepair> loadOwnerRepairList_ByProject(Integer proId, Map<String, Object> params, String order, Pager pager);
}
