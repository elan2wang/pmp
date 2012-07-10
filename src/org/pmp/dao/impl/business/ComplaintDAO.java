/**
 * Author            : Jason
 * Created On        : 2012-7-3 下午08:55:00
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.IComplaintDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Complaint;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ComplaintDAO extends BaseDAO implements IComplaintDAO {

	//~ Static Fields ==================================================================================================
	static Logger logger = Logger.getLogger(ComplaintDAO.class.getName());
	
	public void saveComplaint(Complaint complaint) {
		String debugMsg = "save complaint";
		try {
			saveInstance(complaint,debugMsg);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void updateComplaint(Complaint complaint) {
		String debugMsg = "update complaint";
		try {
			updateInstance(complaint,debugMsg);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void batchDeleteComplaint(final List<Complaint> list) {
		logger.debug("begin to batch delete complaint");
		logger.debug("list.size"+list.size());
		Work work = new Work(){
			public void execute(Connection connection) throws SQLException {
				String sql = "delete tb_Complaint where Comp_ID=?";
				PreparedStatement stmt = connection.prepareStatement(sql);
				for(int i=0;i<list.size();i++){
					stmt.setInt(1, list.get(i).getCompId());
					stmt.executeUpdate();
				}
			}
		};
		executeWork(work);
		logger.debug("successfully batch delete complaint");
	}

	public Complaint getComplaintById(Integer complaintId) {
		String hql = "from Complaint comp where comp.compId="+complaintId;
		String debugMsg = "get company by ID, comp.compId="+complaintId;
		Complaint complaint = null;
		try {
			complaint = (Complaint)getInstance(hql,debugMsg);
		} catch (RuntimeException e) {
			throw e;
		}
		return complaint;
	}

	public List<Complaint> loadComplaintList_ByCompany(Integer comId,
			Map<String, Object> params, String order, Pager pager) {
		List<Complaint> list = null;
		String debugMsg = "load complaint list by company,comId="+comId;
		StringBuilder hql = new StringBuilder();
		hql.append("from Complaint where houseOwner.house in (from House where building in (from Building " +
				"where project in (from Project where company.comId="+comId+")))");
		hql.append(ParamsToString.toString(params));
		if(order==null){
			hql.append(" order by compId desc");
		}else{
			hql.append(" "+order);
		}
		logger.debug(hql);
		try {
			list = (List<Complaint>) loadListByCondition(hql.toString(),pager,debugMsg);
		} catch (RuntimeException e) {
			throw e;
		}
		return list;
	}

	public List<Complaint> loadComplaintList_ByProject(Integer proId,
			Map<String, Object> params, String order, Pager pager) {
		List<Complaint> list = null;
		String debugMsg = "load complaint list by project,proId="+proId;
		StringBuilder hql = new StringBuilder();
		hql.append("from Complaint where houseOwner.house in (from House where building in (from Building " +
				"where project.proId="+proId+"))");
		hql.append(ParamsToString.toString(params));
		if(order==null){
			hql.append(" order by compId desc");
		}else{
			hql.append(" "+order);
		}
		logger.debug(hql);
		try {
			list = (List<Complaint>) loadListByCondition(hql.toString(),pager,debugMsg);
		} catch (RuntimeException e) {
			throw e;
		}
		return list;
	}

}
