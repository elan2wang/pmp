/**
 * Author            : Jason
 * Created On        : 2012-4-17 下午01:35:03
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.pmp.dao.business.IMemberDAO;
import org.pmp.service.business.IMemberService;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class MemberService implements IMemberService {
	private IMemberDAO memberDao;

	/**
	 * @Title: batchSaveMember
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchSaveMember(List<Member> memberList, Integer ownerId) {
		memberDao.batchSaveMember(memberList, ownerId);

	}

	/**
	 * @Title: batchUpdateMember
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void batchUpdateMember(List<Member> memberList, Integer ownerId) {
		memberDao.batchUpdateMember(memberList, ownerId);

	}

	/**
	 * @Title: deleteMember
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public void deleteMember(Integer memId) {
		memberDao.deleteMember(memId);

	}

	/**
	 * @Title: getMemberByID
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Member getMemberByID(Integer memId) {
		return memberDao.getMemberByID(memId);
	}

	/**
	 * @Title: getMemberByName
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public Member getMemberByName(String memName) {
		return memberDao.getMemberByName(memName);
	}

	/**
	 * @Title: getMemberByOwner
	 * @Description: TODO
	 *
	 * @param  TODO
	 * @return TODO
	 * @throws TODO
	 */
	@Override
	public List getMemberByOwner(Owner owner) {
		return memberDao.getMemberByOwner(owner);
	}
	
	/**
	 * @param memberDao the memberDao to set
	 */
	public void setMemberDao(IMemberDAO memberDao) {
		this.memberDao = memberDao;
	}

}
