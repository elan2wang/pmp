/**
 * Author            : Elan
 * Created On        : 2012-3-27 下午04:46:11
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.admin;

import java.util.List;

import org.pmp.vo.TbAuthorityResource;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IAuthorityResourceDAO {
    public void batchSave(List<TbAuthorityResource> list);
    public void batchDeleteByAuthID(Integer authID);
    public void batchUpdateByAuthID(Integer authID,List<TbAuthorityResource> list);
}
