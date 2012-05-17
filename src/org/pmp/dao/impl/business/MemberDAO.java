/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午05:06:23
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.dao.impl.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IMemberDAO;
import org.pmp.util.Pager;
import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MemberDAO extends BaseDAO implements IMemberDAO {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(MemberDAO.class.getName());

    //~ Instance Fields ================================================================================================

    //~ Methods ========================================================================================================
    
    /**
     * @see org.pmp.dao.business.IMemberDAO#batchSave(java.util.List)
     */
    @Override
    public void batchSave(List<Member> list) {
        String debugMsg = "save member instance";
	for (int i=0;i<list.size();i++){
	    try {
		saveInstance(list.get(i),debugMsg);
	    } catch(RuntimeException e){
		throw e;
	    }
	}
    }

    /**
     * @see org.pmp.dao.business.IMemberDAO#batchUpdate(java.util.List)
     */
    @Override
    public void batchUpdate(List<Member> list) {
	deleteMember_ByOwner(list.get(0).getOwner());
	batchSave(list);
    }

    /**
     * @see org.pmp.dao.business.IMemberDAO#deleteMember_ByOwner(org.pmp.vo.Owner)
     */
    @Override
    public void deleteMember_ByOwner(Owner instance) {
	String debugMsg = "delete member by owner,ownerId="+instance.getOwnerId();
	String hql = "delete from Member where owner.ownerId="+instance.getOwnerId();
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    
    /**
     * @see org.pmp.dao.business.IMemberDAO#loadMemberList_ByOwner(java.lang.Integer)
     */
    @Override
    public List<?> loadMemberList_ByOwner(Integer ownerId) {
	String debugMsg = "load member list by owner,ownerId="+ownerId;
	String hql = "from Member where owner.ownerId="+ownerId;
	Pager pager = new Pager(1000,1);
	List<?> list = null;
	try {
	    list = loadListByCondition(hql,pager,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
	return list;
    }
 
}
