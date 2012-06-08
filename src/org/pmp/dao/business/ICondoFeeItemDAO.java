/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午05:25:50
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.CondoFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICondoFeeItemDAO {
    public void saveCondoFeeItem(CondoFeeItem instance);
    public void deleteCondoFeeItem(CondoFeeItem instance);
    
    public List<?> getCondoFeeYear();
    
    public CondoFeeItem getCondoFeeItemByID(Integer cfiId);
    public List<?> loadCondoFeeItemListBy_ComID(Pager pager,Integer comId);
    public List<?> loadCondoFeeItemListBy_ProID(Pager pager,Integer proId);
}
