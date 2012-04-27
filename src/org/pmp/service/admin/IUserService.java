/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午01:28:07
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.TbUser;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IUserService {
    public void addUser(TbUser instance);
    public void editUser(TbUser instance);
    public void deleteUser(Integer userID);
    public TbUser getUserById(Integer userId);
    
    /**
     * @Title: passwordReset
     * @Description: 用户密码重置
     *
     * @param  password  String类型，表示用户密码
     * @param  userID  Integer类型，表示用户编号
     * @return void
     */
    public void passwordReset(String password,Integer userID);
    
    /**
     * @Title: getUserByUsername
     * @Description: 根据用户名获取用户实体
     *
     * @param  username  String类型，表示用户名信息
     * @return TbUser  用户实体
     */
    public TbUser getUserByUsername(String username);
    
    /**
     * @Title: getUserList
     * @Description: 分页获取所有用户实体的列表
     *
     * @param  pager  Pager类型，表示分页对象
     * @return List  保存所有的TbUser对象
     */
    public List getUserList(Pager pager);
    
    /**
     * @Title: getUserListByGroup
     * @Description: 根据用户组编号分页获取用户实体列表
     *
     * @param  pager  Pager类型，表示分页对象
     * @param  groupID  Integer类型，表示用户组编号
     * @return List  保存该用户组的所有用户
     */
    public List getUserListByGroup(Pager pager,Integer groupID);
}
