/**
 * Author            : Jason
 * Created On        : 2012-4-9 下午04:33:43
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.pmp.dao.business.IOwnerDAO;
import org.pmp.service.business.IOwnerService;
import org.pmp.util.Pager;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class OwnerService implements IOwnerService {
	
	private IOwnerDAO ownerDao;


	/**
	 * @Title: addOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void addOwner(Owner owner) {
		ownerDao.saveOwner(owner);

	}

	/**
	 * @Title: editCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void editOwner(Owner owner) {
		ownerDao.updateOwner(owner);

	}

	/**
	 * @Title: deleteCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteOwner(Integer ownerId) {
		ownerDao.deleteOwner(ownerId);

	}

	/**
	 * @Title: getOwnerById
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Owner getOwnerById(Integer ownerId) {
		return ownerDao.getOwnerByID(ownerId);
	}

	/**
	 * @Title: getAllCompany
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getAllOwner() {
		return null;
	}

	/**
	 * @Title: loadOwnerList
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadOwnerList(Pager pager) {
		return ownerDao.loadOwnerList(pager);
	}

	/**
	 * @Title: loadOwnerByCondition
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List loadOwnerByCondition(Integer projectId, Integer buildingId,
			String keyWord, Pager pager) {
		return null;
	}

	/**
	 * @param ownerDao the ownerDao to set
	 */
	public void setOwnerDao(IOwnerDAO ownerDao) {
		this.ownerDao = ownerDao;
	}

	/**
	 * @Title: batchSaveOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List batchSaveOwner(List<Owner> ownerList) {
		return ownerDao.batchSaveOwner(ownerList);
	}

	
}
