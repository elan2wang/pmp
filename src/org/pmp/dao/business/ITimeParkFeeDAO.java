/**
 * Author            : Elan
 * Created On        : 2012-4-18 上午11:12:38
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.TimeParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ITimeParkFeeDAO {
    public void saveTimeParkFee(TimeParkFee instance);
    public void updateTimeParkFee(TimeParkFee instance);
    public void deleteTimeParkFee(Integer tpfId);
    public void batchAdd(List<TimeParkFee> list);
    
    public TimeParkFee getTimeParkFeeByID(Integer tpfId);
    public List<?> loadByProject(Pager pager,Integer proId);
}
