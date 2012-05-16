/**
 * Author            : Elan
 * Created On        : 2012-5-16 下午05:31:12
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.service.business;

import java.util.List;

import org.pmp.vo.Member;
import org.pmp.vo.Owner;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public interface IMemberService {
    public void batchSave(List<Member> list);
    public void batchUpdate(List<Member> list);
    
    public void deleteMember_ByOwner(Owner instance);
    
    public List<?> loadMemberList_ByOwner(Integer ownerId);
}
