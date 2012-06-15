package org.pmp.service.impl.fire;

import java.util.List;
import java.util.Map;

import org.pmp.dao.impl.fire.FireInfoBakDAO;
import org.pmp.service.fire.IFireInfoBakService;
import org.pmp.util.Pager;
import org.pmp.vo.FireInfoBak;

public class FireInfoBakService implements IFireInfoBakService {

	private FireInfoBakDAO fireInfoBakDAO;
	private ZoneService zoneService;
	
	@Override
	public List<FireInfoBak> loadFireInfoBakListByProIdList(
			List<Integer> proIdList, Map<String, Object> params, String order,
			Pager paper) {
		//List<Integer> zoneIdList=fireInfoBakDAO.loadZon
		return null;
	}

	
	
	public ZoneService getZoneService() {
		return zoneService;
	}



	public void setZoneService(ZoneService zoneService) {
		this.zoneService = zoneService;
	}



	public FireInfoBakDAO getFireInfoBakDAO() {
		return fireInfoBakDAO;
	}

	public void setFireInfoBakDAO(FireInfoBakDAO fireInfoBakDAO) {
		this.fireInfoBakDAO = fireInfoBakDAO;
	}
}
