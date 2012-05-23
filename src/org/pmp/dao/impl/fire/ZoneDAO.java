package org.pmp.dao.impl.fire;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IZoneDAO;
import org.pmp.util.Pager;
import org.pmp.vo.Zone;

public class ZoneDAO extends BaseDAO implements IZoneDAO{
    
	@Override
	public List<?> loadZoneListByProId(Integer proId,Pager pager) {
			List<?> list = null;
			
			String debugMsg = "load Zone list by project, proId="+proId;
			
			StringBuilder hql = new StringBuilder();
			
			String aa = "from Zone z where z.project.proId="+proId;
			
			hql.append(aa);
			
			list=loadListByCondition(hql.toString(), pager, debugMsg);
			
		    return list;
	}

	@Override
	public Zone saveZone(Zone zone) {
		String debugMsg="Save zone!";
		saveInstance(zone, debugMsg);
		return zone;
	}

	@Override
	public Zone getZoneById(Integer zoneID) {
		String debugMsg="get zone by ID!";
		return (Zone)getInstanceById(Zone.class, zoneID, debugMsg);
	}
	
}