/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午10:39:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.FixedParkFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IFixedParkFeeItemService {
    public void saveFixedParkFeeItem(FixedParkFeeItem instance);
    public void editFixedParkFeeItem(FixedParkFeeItem instance);
    public void deleteFixedParkFeeItem(Integer fpfiId);
    
    public FixedParkFeeItem getFixedParkFeeItemByID(Integer fpfiId);
    
    public List loadFixedParkFeeItemList_ByPro(Pager pager,Integer proId);
    public List loadFixedParkFeeItemList_ByCom(Pager pager,Integer comId);
}
