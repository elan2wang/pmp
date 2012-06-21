/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:01:55
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

import org.pmp.vo.BuilFeeRate;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IBuilFeeRate {
    public void batchSaveBuilFeeRate(List<BuilFeeRate> list);
    
    public List<BuilFeeRate> loadBuilFeeRateList_ByEFIandBuilID(Integer efiId, Integer builId);
}
