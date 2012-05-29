package org.pmp.dao.impl.fire;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IZoneDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.Zone;

public class ZoneDAO extends BaseDAO implements IZoneDAO{
    
	@Override
	public List<Zone> loadZoneListByProId(Integer proId,Map<String,Object>params,String order,Pager pager) {
			List<Zone> list = null;
			
			String debugMsg = "load Zone list by project, proId="+proId;
			
			StringBuilder hql = new StringBuilder();
			
			String aa = "from Zone where project.proId="+proId;
			
			hql.append(aa);
			
			hql.append(ParamsToString.toString(params));
			
			if (order==null){
			    hql.append(" order by zoneId desc");
			} else {
			    hql.append(" "+order);
			}
			
			list=(List<Zone>) loadListByCondition(hql.toString(), pager, debugMsg);
			
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

	@Override
	public Zone updateZone(Zone zone) {
		String debugMsg="update zone!";
		updateInstance(zone, debugMsg);
		return zone;
	}

	@Override
	public Zone deleteZone(Zone zone) {
		String debugMsg="delete zone!";
		deleteInstance(zone, debugMsg);
		return zone;
	}

	@Override
	public List<Zone> loadZoneListByProIdList(List<Integer> proIdList,Map<String,Object>params,String order,Pager paper) {
		List<Zone> list = null;
		
		String debugMsg = "load Zone list by projectList, proIdList="+proIdList;
		
		StringBuilder hql = new StringBuilder();
		
		StringBuilder aa = new StringBuilder("from Zone where project.proId in (");
		
		for (int i=0;i<proIdList.size();i++) {
            if(i==proIdList.size()-1){
            	aa.append(proIdList.get(i));
            	break;
            }
			aa.append(proIdList.get(i)+",");
		}
		aa.append(")");
		
		hql.append(aa);
	  
		hql.append(ParamsToString.toString(params));
		
		if (order==null){
		    hql.append(" order by zoneId desc");
		} else {
		    hql.append(" "+order);
		}
		
		list=(List<Zone>) loadListByCondition(hql.toString(), paper, debugMsg);
		
	    return list;
	}

	@Override
	public Zone queryZoneByParams(Map<String, Object> params) {
		
		String debugMsg = "query Zone by params!";
		
		StringBuilder hql = new StringBuilder();
		
		String aa = "from Zone where 1=1";
		
		hql.append(aa);
		
		hql.append(ParamsToString.toString(params));
		
		return (Zone)getInstance(hql.toString(),debugMsg);
	}
	
}