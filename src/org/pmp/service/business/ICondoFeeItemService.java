/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午05:50:52
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.CondoFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICondoFeeItemService {
    public void addCondoFeeItem(CondoFeeItem instance);
    public void deleteCondoFeeItem(Integer cfiId);
    
    public List<?> getCondoFeeYear();
    
    public CondoFeeItem getCondoFeeItemByID(Integer cfiId);
    public List<?> loadCondoFeeItemListBy_ComID(Pager pager,Integer comId);
}
