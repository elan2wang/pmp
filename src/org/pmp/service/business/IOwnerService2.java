/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午01:20:30
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
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IOwnerService2 {
    public void addOwner(Owner instance);
    public void editOwner(Owner instance);
    public void batchDelete(List<Owner> list);
    
    public Owner getOwner_ById(Integer ownerId);
    
    public List<?> loadOwnerList_ByPro(Integer proId,Map<String,Object> params,String order,Pager pager);
    public List<?> loadOwnerList_ByCom(Integer comId,Map<String,Object> params,String order,Pager pager);
    
}
