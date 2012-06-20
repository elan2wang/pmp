/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午02:31:29
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
import org.pmp.vo.ProMeterItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IProMeterItemDAO {
    public void batchSaveProMeterItem(List<ProMeterItem> list);
    
    public List<ProMeterItem> loadProMeterItemList_ByEFI(Integer efiId,  Map<String,Object>params,String order,Pager pager);
}
