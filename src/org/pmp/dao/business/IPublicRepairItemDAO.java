/**
 * Author            : Elan
 * Created On        : 2012-7-10 上午09:23:22
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
import org.pmp.vo.PublicRepairItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IPublicRepairItemDAO {
    public void savePublicRepairItem(PublicRepairItem instance);
    
    public void updatePublicRepairItem(PublicRepairItem instance);
    
    public void batchDeletePublicRepairItem(List<PublicRepairItem> list);
    
    public PublicRepairItem getPublicRepairItemByID(Integer fbiId);
    
    public List<PublicRepairItem> loadPublicRepairItemList_ByCompany(Integer comId, Map<String, Object> params, String order, Pager pager);
    
    public List<PublicRepairItem> loadPublicRepairItemList_ByProject(Integer proId, Map<String, Object> params, String order, Pager pager);
}
