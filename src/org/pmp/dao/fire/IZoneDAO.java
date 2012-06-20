package org.pmp.dao.fire;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.Zone;


public interface IZoneDAO{
	   List<Zone> loadZoneListByProId(Integer proId,Map<String,Object>params,String order,Pager pager);
	  
	   Zone saveZone(Zone zone);
	  
	   Zone getZoneById(Integer zoneID);
	  
	   Zone updateZone(Zone zone);
	   
	   Zone deleteZone(Zone zone);
	   
	   List<Zone> loadZoneListByProIdList(List<Integer> proIdList,Map<String,Object>params,String order,Pager paper);
	   
	   Zone queryZoneByParams(Map<String, Object> params);
}
