/**
 * Author            : Jason
 * Created On        : 2012-4-17 下午01:33:31
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IMemberService {
    public void batchSaveMember(List<Member> memberList,Integer ownerId);
    public void batchUpdateMember(List<Member> memberList,Integer ownerId);
    public void deleteMember(Integer memId);
    public Member getMemberByID(Integer memId);
    public Member getMemberByName(String memName);
    public List<?> getMemberByOwner(Owner owner);
}
