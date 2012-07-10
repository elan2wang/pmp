/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:25:37
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface ICondoFeeService {
    public void batchSetOughtMoney(List<CondoFee> list);
    public void batchInput(List<CondoFee> list);
    public void batchAudit(List<CondoFee> list);
    public void batchDelete(List<CondoFee> list);
    
    /**
     * @Title: getAmount_By_Com_State
     * @Description: 根据公司编号，获取该公司状态为State的记录总数
     */
    public Integer getAmount_By_Com_State(Integer comId, String state, Map<String,Object> params);
    
    /**
     * @Title: getAmount_By_Pro_State
     * @Description: 根据小区编号，获取该小区状态为State的记录总数
     */
    public Integer getAmount_By_Pro_State(Integer proId, String state, Map<String,Object> params);
    
    public Integer getAmount_By_Item_State(Integer cfiId, String state, Map<String,Object> params);
    
    public List<Double> getMoneyInfo_ByCom(Integer comId, Map<String,Object> params);
    
    public List<Double> getMoneyInfo_ByPro(Integer proId, Map<String,Object> params);
    
    public List<Double> getMoneyInfo_ByItem(Integer cfiId, Map<String,Object> params);
    
    /**
     * @Title: getCondoFee_ById
     * @Description: 根据物业费记录的ID获取该物业费记录的实体对象
     *
     * @param  cfId  Integer类型，表示物业费记录的编号
     * @return CondoFee  CondoFee的实体对象
     */
    public CondoFee getCondoFee_ById(Integer cfId);
   

    /**
     * @Title: loadCondoFeeList_ByIds
     * @Description: 获取指定编号的物业费记录列表
     *
     * @param  ids  List<Integer> 物业费编号列表
     * @return List<CondoFee>  返回物业费记录实体列表
     * @throws RuntimeException
     */
    public List<CondoFee> loadCondoFeeList_ByIds(List<Integer> ids);
    
    
    /**
     * @Title: loadCondoFeeList_ByOwner
     * @Description: 根据房屋编号获取物业费缴费记录
     *
     * @param  houseId  Integer  该参数用于设置检索的主条件
     * @param  params  Map<String,Object>  该参数用于设置检索的条件，
     *         key  String类型,表示CondoFee的属性名称,
     *         value  Object类型,表示CondoFee的属性值
     * @param  order  String  表示排序方式,当order==null时,采用默认的主键降序,order示例："order by cfYear desc"
     * @param  pager  Pager类型,表示分页信息
     * 
     * @return List<CondoFee>  返回CondoFee的实体对象列表
     * @throws RuntimeException
     */
    public List<CondoFee> loadCondoFeeList_ByHouse(Integer houseId,Map<String,Object>params,String order,Pager pager);
    
    public List<Map<String, Object>> loadCondoFeeList_ByCom(Integer comId,Map<String,Object>params,String order,Pager pager);
    
    public List<Map<String, Object>> loadCondoFeeList_ByPro(Integer proId,Map<String,Object>params,String order,Pager pager);
    
    public List<Map<String, Object>> loadCondoFeeList_ByItem(Integer cfiId,Map<String,Object>params,String order,Pager pager);
    
    /**
     * @Title: loadCondoFeeList_ByCFI
     * @Description: 根据物业费创建时的项目编号获取物业费缴费记录（已经启用，查询效率太低）
     *
     * @param  cfiId  Integer  物业费项目编号,该参数用户设置检索的主条件
     * @param  params  Map<String,Object>  该参数用户设置检索的条件，
     *         key  String类型,表示CondoFee的属性名称,
     *         value  Object类型,表示CondoFee的属性值
     * @param  order  String  表示排序方式,当order==null时,采用默认的主键降序,order示例："order by cfYear desc"
     * @param  pager  Pager类型,表示分页信息
     * 
     * @return List<CondoFee>  返回CondoFee的实体列表
     * @throws RuntimeException
     */
    public List<CondoFee> loadCondoFeeList_ByCFI(Integer cfiId,Map<String,Object>params,String order,Pager pager);
    
    /**
     * @Title: loadCondoFeeList_ByCompany
     * @Description: 已弃用，因为查询效率太低
     */
    public List<CondoFee> loadCondoFeeList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
    
    /**
     * @Title: loadCondoFeeList_ByProject
     * @Description: 已弃用，因为查询效率太低
     */
    public List<CondoFee> loadCondoFeeList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
    
}
