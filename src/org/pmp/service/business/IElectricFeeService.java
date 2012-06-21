/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:20:18
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
import org.pmp.vo.ElectricFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IElectricFeeService {
    public void generateElectricFee(Integer efiId);
    
    public void batchEditElectricFee(List<ElectricFee> list);
    
    public void batchDeleteElectricFee(List<ElectricFee> list);
    
    public List<ElectricFee> loadElectricFeeList_ByEFI(Integer efiId,  Map<String,Object>params,String order,Pager pager);
    
    public List<ElectricFee> loadElectricFeeList_ByCompany(Integer comId,  Map<String,Object>params,String order,Pager pager);
    
    public List<ElectricFee> loadElectricFeeList_ByProject(Integer proId,  Map<String,Object>params,String order,Pager pager);

}
