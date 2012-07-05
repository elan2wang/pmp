/**
 * Author            : Jason
 * Created On        : 2012-7-3 下午08:22:54
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Complaint;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IComplaintDAO {
    public void saveComplaint(Complaint complaint);
    public void updateComplaint(Complaint complaint);
	
    public void batchDeleteComplaint(List<Complaint> list);
	
    public Complaint getComplaintById(Integer complaintId);
	
    public List<Complaint> loadComplaintList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
    public List<Complaint> loadComplaintList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
}
