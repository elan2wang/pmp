/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:19:22
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

import org.pmp.util.Pager;
import org.pmp.vo.ElectricFeeItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IElectricFeeItemService {
    public void addElectricFeeItem(ElectricFeeItem instance);
    public void deleteElectricFeeItem(ElectricFeeItem instance);
    
    public ElectricFeeItem getElectricFeeItemByID(Integer efiId);
    public List<ElectricFeeItem> loadElectricFeeItemList_ByCompany(Integer comId, String order,Pager pager);
    public List<ElectricFeeItem> loadElectricFeeItemList_ByProject(Integer proId, String order,Pager pager);
}
