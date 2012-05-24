package org.pmp.service.impl.fire;

import java.util.List;
import java.util.Map;

import org.pmp.dao.fire.IZoneDAO;
import org.pmp.service.fire.IZoneService;
import org.pmp.util.Pager;
import org.pmp.vo.Zone;

public class ZoneService implements IZoneService{

	private IZoneDAO zoneDAO;
	
	@Override
	public List<Zone> loadZoneListByProId(Integer proId,Map<String,Object>params,String order,Pager pager) {
		return zoneDAO.loadZoneListByProId(proId,params,order,pager);
	}

	public IZoneDAO getZoneDAO() {
		return zoneDAO;
	}

	public void setZoneDAO(IZoneDAO zoneDAO) {
		this.zoneDAO = zoneDAO;
	}

	@Override
	public Zone saveZone(Zone zone) {
		this.zoneDAO.saveZone(zone);
		return zone;
	}

	@Override
	public Zone getZoneById(Integer zoneID) {
		return this.zoneDAO.getZoneById(zoneID);
	}

	@Override
	public Zone updateZone(Zone zone) {
		this.zoneDAO.updateZone(zone);
		return zone;
	}

	@Override
	public Zone deleteZone(Zone zone) {
		this.zoneDAO.deleteZone(zone);
		return zone;
	}

	@Override
	public List<Zone> loadZoneListByProIdList(List<Integer> proIdList,Map<String,Object>params,String order,Pager paper) {
		return this.zoneDAO.loadZoneListByProIdList(proIdList,params,order,paper);
	}
	
}
