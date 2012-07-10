/**
 * Author            : Jason
 * Created On        : 2012-7-3 下午09:48:18
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.service.impl.business;

import java.util.List;
import java.util.Map;

import org.pmp.dao.business.IComplaintDAO;
import org.pmp.service.business.IComplaintService;
import org.pmp.util.Pager;
import org.pmp.vo.Complaint;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ComplaintService implements IComplaintService {

	//~ Instance Fields ================================================================================================
	private IComplaintDAO complaintDAO;
	

	public void addComplaint(Complaint complaint) {
		complaintDAO.saveComplaint(complaint);
	}

	public void editComplaint(Complaint complaint) {
		complaintDAO.updateComplaint(complaint);
	}

	public void batchDeleteComplaint(List<Complaint> list) {
		complaintDAO.batchDeleteComplaint(list);
	}
	
	public Complaint getComplaintByID(Integer complaintID) {
		return complaintDAO.getComplaintById(complaintID);
	}

	public List<Complaint> loadComplaintList_ByCompany(Integer comId,
			Map<String, Object> params, String order, Pager pager) {
		return complaintDAO.loadComplaintList_ByCompany(comId, params, order, pager);
	}

	public List<Complaint> loadComplaintList_ByProject(Integer proId,
			Map<String, Object> params, String order, Pager pager) {
		return complaintDAO.loadComplaintList_ByProject(proId, params, order, pager);
	}

	
	public IComplaintDAO getComplaintDAO() {
		return complaintDAO;
	}

	public void setComplaintDAO(IComplaintDAO complaintDAO) {
		this.complaintDAO = complaintDAO;
	}

}
