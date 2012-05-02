/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:25:37
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICondoFeeService {
    /**
     * @Title: getCondoFeeByID
     * @Description: 根据物业费记录的ID获取该物业费记录的实体对象
     *
     * @param  cfId  Integer类型，表示物业费记录的编号
     * @return CondoFee  CondoFee的实体对象
     */
    public CondoFee getCondoFeeByID(Integer cfId);
    
    /**
     * @Title: inputCondoFee
     * @Description: 录入物业费缴费信息
     *
     * @param  instance  CondoFee类型，表示物业费记录
     * @return void  无返回值
     */
    public void inputCondoFee(CondoFee instance);
    
    /**
     * @Title: auditCondoFee
     * @Description: 审核物业费缴费信息
     *
     * @param  instance  CondoFee类型，表示物业费记录
     * @return void  无返回值
     */
    public void auditCondoFee(CondoFee instance);
    
    /**
     * @Title: deleteCondoFee
     * @Description: 根据物业费记录的编号删除物业费记录
     *
     * @param  cfId  Integer类型，表示物业费记录编号
     * @return void  无返回值
     */
    public void deleteCondoFee(Integer cfId);
    
    /**
     * @Title: loadCondoFeeListBy_cfiId
     * @Description: 根据物业费项目编号，获取与其关联的所有物业费记录实体列表
     *
     * @param  cfiId  Integer类型，表示物业费项目编号
     * @param  pager  Pager类型，表示分页对象
     * @return List  CondoFee类型的列表
     */
    public List loadCondoFeeListBy_cfiId(Integer cfiId,Pager pager);
    
    public List loadPayedCondoFeeList(Integer cfiId,Pager pager);
    
    public List loadNonePayedCondoFeeList(Integer cfiId,Pager pager);
    
}
