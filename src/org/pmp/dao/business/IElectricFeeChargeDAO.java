/**
 * Author            : Elan
 * Created On        : 2012-6-24 下午10:07:50
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
import org.pmp.vo.ElectricFeeCharge;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IElectricFeeChargeDAO {
    public void saveElectricFeeCharge(ElectricFeeCharge instance);
    
    public void batchUpdateElectricFeeCharge(List<ElectricFeeCharge> list);
    
    public void deleteElectricFeeCharge(ElectricFeeCharge instance);
    
    public ElectricFeeCharge getElectricFeeCharge_ByID(Integer efcId);
    
    public List<ElectricFeeCharge> loadElectricFeeChargeList_ByHouse(Integer houseId, Map<String, Object> params, String order, Pager pager);
}
