/**
 * Author            : Elan
 * Created On        : 2012-3-27 上午11:13:55
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbAuthority;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IAuthorityDAO {
    public void saveAuthority(TbAuthority instance);
    public void updateAuthority(TbAuthority instance);
    public void deleteAuthority(Integer authID);
    
    
    /**
     * @Title: getAuthorityByID
     * @Description: 根据ID获得TbAuthority的实体对象
     *
     * @param  authID  权限的ID
     * @return TbAuthority  权限实体对象
     */
    public TbAuthority getAuthorityByID(Integer authID);
    
    /**
     * @Title: getAllAuthorities
     * @Description: 获取所有权限的权限名信息
     *
     * @return List  string类型的数组，保存所有权限的authName字段值
     */
    public List getAllAuthorities();
    
    /**
     * @Title: getAuthorityList
     * @Description: 获取所有权限实体的列表
     *
     * @return List  TbAuthority类型的列表，用户保存所有权限实体
     */
    public List getAuthorityList();
    
    /**
     * @Title: getAuthoritiesByRoleID
     * @Description: 根据角色ID获取该角色所有的权限实体列表
     *
     * @param  roleID  角色的ID
     * @return List  TbAuthority类型的列表，保存所有该角色的权限实体列表
     */
    
    public List getAuthoritiesByRoleID(Integer roleID);
    
    /**
     * @Title: getNoneGrantedAuthByRoleID
     * @Description: 根据角色ID获取该角色所没有的权限实体列表
     *
     * @param  roleID  角色的ID
     * @return List  TbAuthority类型的列表，保存所有该角色的权限实体列表
     */
    public List getNoneGrantedAuthByRoleID(Integer roleID);
    
    /**
     * @Title: loadUserAuthoritiesByUsername
     * @Description: 根据用户名加载用户的权限信息列表
     *
     * @param  username 用户名
     * @return List  TbAuthority类型的数据，保存用户所有权限信息
     */
    public List loadUserAuthoritiesByUsername(String username);
    
    
    
}
