/**
 * Author            : Elan
 * Created On        : 2012-4-23 上午10:46:46
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.FixedParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IFixedParkFeeDAO {
    public FixedParkFee getFixedParkFeeByID(Integer fpfId);
    public void updateFixedParkFee(FixedParkFee instance);
    public void deleteFixedParkFee(Integer fpfId);
    
    public List<?> loadFixedParkFeeListBy_fpfiId(Pager pager,Integer fpfiId);
}
