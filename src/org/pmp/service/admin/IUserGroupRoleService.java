/**
 * Author            : Elan
 * Created On        : 2012-3-30 下午12:07:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.TbUserGroupRole;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IUserGroupRoleService {
    public void addUGR(TbUserGroupRole instance);
    public void editUGR(TbUserGroupRole instance);
    
    /**
     * @Title: getUGR_ByUserID
     * @Description: 根据用户编号获取一个TbUserGroupRole对象
     *
     * @param  userID  Integer类型，表示用户编号
     * @return TbUserGroupRole  用户实体对象
     * @throws RuntimeException
     */
    public TbUserGroupRole getUGR_ByUserID(Integer userID);
    
    public List<?> loadUGRList(Map<String,Object> params,String order,Pager pager);
    
    public List<?> loadUGRList_ByPro(String proName,Map<String,Object> params,String order,Pager pager);

    public List<?> loadUGRList_ByCom(String comName,Map<String,Object> params,String order,Pager pager);

}
