/**
 * Author            : Elan
 * Created On        : 2012-3-29 下午12:26:36
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.TbGroup;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IGroupService {
    public void addGroup(TbGroup instance);
    public void editGroup(TbGroup instance);
    public void deleteGroup(Integer groupID);
    
    /**
     * @Title: getGroupByID
     * @Description: 根据用户组ID获得用户组事体
     *
     * @param  groupID  Integer类型，表示用户组编号
     * @return TbGroup  用户组实体对象
     */
    public TbGroup getGroupByID(Integer groupID);
    
    /**
     * @Title: getGroupList
     * @Description: 分页获取用户组实体列表
     *
     * @param  oager  Pager对象，表示分页对象
     * @return List  用户组实体列表
     */
    public List getGroupList(Pager pager);
    
    /**
     * @Title: getGroupListByLevel
     * @Description: 根据用户的level获得用户组实体列表
     *
     * @param  level  Integer类型，表示用户组的Level：1表示系统级、2表示公司级、3表示项目级
     * @param  pager Pager类型，表示分页对象 
     * @return List  用户组列表
     */
    public List getGroupListByLevel(Pager pager,Integer level);
}
