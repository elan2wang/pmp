/**
 * Author            : Jason
 * Created On        : 2012-7-3 下午09:41:18
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.business;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Complaint;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public interface IComplaintService {
	public void addComplaint(Complaint complaint);
	public void editComplaint(Complaint complaint);
	public void batchDeleteComplaint(List<Complaint> list);
	public Complaint getComplaintByID(Integer complaintID);
	public List<Complaint> loadComplaintList_ByCompany(Integer comId,Map<String,Object>params,String order,Pager pager);
	public List<Complaint> loadComplaintList_ByProject(Integer proId,Map<String,Object>params,String order,Pager pager);
}
