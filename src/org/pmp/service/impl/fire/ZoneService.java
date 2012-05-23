package org.pmp.service.impl.fire;

import java.util.List;

import org.pmp.dao.fire.IZoneDAO;
import org.pmp.service.fire.IZoneService;
import org.pmp.util.Pager;
import org.pmp.vo.Zone;

public class ZoneService implements IZoneService{

	private IZoneDAO zoneDAO;
	
	@Override
	public List<?> loadZoneListByProId(Integer proId, Pager pager) {
		return zoneDAO.loadZoneListByProId(proId, pager);
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
	
}
