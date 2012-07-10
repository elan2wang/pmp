/**
 * Author            : Elan
 * Created On        : 2012-7-10 下午12:34:41
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
import org.pmp.vo.PublicRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IPublicRepairService {
    public void addPublicRepair(PublicRepair instance);
    
    public void editPublicRepair(PublicRepair instance);
    
    public void batchDeletePublicRepair(List<PublicRepair> list);
    
    public PublicRepair getPublicRepairByID(Integer fbId);
    
    public List<PublicRepair> loadPublicRepairList_ByFBI(Integer fbiId, Map<String, Object> params, String order, Pager pager);
}
