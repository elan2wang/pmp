/**
 * Author            : Elan
 * Created On        : 2012-7-10 下午12:30:14
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
import org.pmp.vo.PublicRepair;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IPublicRepairDAO {
    public void savePublicRepair(PublicRepair instance);
    
    public void updatePublicRepair(PublicRepair instance);
    
    public void batchDeletePublicRepair(List<PublicRepair> list);
    
    public PublicRepair getPublicRepairByID(Integer fbId);
    
    public List<PublicRepair> loadPublicRepairList_ByFBI(Integer fbiId, Map<String, Object> params, String order, Pager pager);

}
