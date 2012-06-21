/**
 * Author            : Elan
 * Created On        : 2012-6-21 上午11:05:58
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
import org.pmp.vo.ProMeterItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IProMeterItemService {
    public void batchAddProMeterItem(List<ProMeterItem> list);
    
    public List<ProMeterItem> loadProMeterItemList_ByEFI(Integer efiId,  Map<String,Object>params,String order,Pager pager);
}