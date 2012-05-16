/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午05:31:58
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.business.IMemberDAO;
import org.pmp.service.business.IMemberService;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MemberService implements IMemberService {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(MemberService.class.getName());

    //~ Instance Fields ================================================================================================
    private IMemberDAO memberDAO;
    
    //~ Methods ========================================================================================================

    /**
     * @see org.pmp.service.business.IMemberService#batchSave(java.util.List)
     */
    @Override
    public void batchSave(List<Member> list) {
	memberDAO.batchSave(list);
    }

    /**
     * @see org.pmp.service.business.IMemberService#batchUpdate(java.util.List)
     */
    @Override
    public void batchUpdate(List<Member> list) {
	memberDAO.batchUpdate(list);
    }

    /**
     * @see org.pmp.service.business.IMemberService#deleteMember_ByOwner(org.pmp.vo.Owner)
     */
    @Override
    public void deleteMember_ByOwner(Owner instance) {
	memberDAO.deleteMember_ByOwner(instance);
    }

    /**
     * @see org.pmp.service.business.IMemberService#loadMemberList_ByOwner(java.lang.Integer)
     */
    @Override
    public List<?> loadMemberList_ByOwner(Integer ownerId) {
	return memberDAO.loadMemberList_ByOwner(ownerId);
    }

    public IMemberDAO getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(IMemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    //~ Getters and Setters ============================================================================================

}
