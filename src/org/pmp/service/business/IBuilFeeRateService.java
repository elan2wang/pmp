/**
 * Author            : Elan
 * Created On        : 2012-6-21 上午11:07:29
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

import org.pmp.vo.BuilFeeRate;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IBuilFeeRateService {
    public void batchAddBuilFeeRate(List<BuilFeeRate> list);
    
    public List<BuilFeeRate> loadBuilFeeRateList_ByEFIandBuilID(Integer efiId, Integer builId);
}
