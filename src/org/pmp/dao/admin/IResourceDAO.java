/**
 * Author            : Elan
 * Created On        : 2012-3-27 上午11:18:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IResourceDAO {
    public void saveResource(TbResource instance);
    public void updateResource(TbResource instance);
    public void deleteResource(Integer resID);
    
    /**
     * @Title: getResourceByID
     * @Description: 根据资源的ID获取资源实体对象
     *
     * @param  resID  资源编号
     * @return TbResource  资源实体对象
     */
    public TbResource getResourceByID(Integer resID);
    
    /**
     * @Title: getAllResources
     * @Description: 获取所有资源的resLink列表
     *
     * @return List  string类型数组，保存所有资源的resLink值
     */
    public List getAllResources();
    
    /**
     * @Title: getResourceList
     * @Description: 获取所有资源实体列表
     *
     * @return List  TbResource类型列表，保存所有TbResource实体
     */
    public List getResourceList();
    
    /**
     * @Title: getResourcesByAuthority
     * @Description: 根据权限名称获取资源的resLink列表
     *
     * @param  authName 权限名称
     * @return List  string类型数组，保存资源的resLink字段值
     */
    public List getResourcesByAuthority(String authName);
    
    /**
     * @Title: getResourceListByAuthority
     * @Description: 根据权限ID获取相应资源实体列表
     *
     * @param  authID  权限编号
     * @return List  TbResource类型的列表，保存所有资源实体的列表
     */
    public List getResourceListByAuthority(Integer authID);
    
    /**
     * @Title: getNoneGrantedResourceListByAuthoriy
     * @Description: 根据权限ID获取该权限没有拥有的资源
     *
     * @param  authID  权限编号
     * @return List  TbResource类型的列表，保存所有资源实体的列表
     */
    public List getNoneGrantedResourceListByAuthoriy(Integer authID);
}
