/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午05:39:35
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.admin;

import java.util.List;

import org.pmp.vo.TbAuthorityResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IAuthorityResourceService {
    public void batchAdd(List<TbAuthorityResource> list);
    public void batchDeleteByAuthID(Integer authID);
    public void batchEditByAuthID(List<TbAuthorityResource> list,Integer authID);
}
