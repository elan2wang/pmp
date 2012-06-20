package org.pmp.service.impl.fire;

import java.util.List;
import java.util.Map;
import org.pmp.dao.fire.IFireInfoBakDAO;
import org.pmp.service.fire.IFireInfoBakService;
import org.pmp.util.Pager;
import org.pmp.vo.FireInfoBak;

public class FireInfoBakService implements IFireInfoBakService {

	private IFireInfoBakDAO fireInfoBakDAO;
    	
	@Override
	public List<FireInfoBak> loadFireInfoBakListByProIdList(
			List<Integer> proIdList, Map<String, Object> params, String order,
			Pager paper) {
		return fireInfoBakDAO.loadFireInfoBakListByProIdList(proIdList, params, order, paper);
	}

	public IFireInfoBakDAO getFireInfoBakDAO() {
		return fireInfoBakDAO;
	}

	public void setFireInfoBakDAO(IFireInfoBakDAO fireInfoBakDAO) {
		this.fireInfoBakDAO = fireInfoBakDAO;
	}

	@Override
	public FireInfoBak getFireInfoBakById(Integer fireInfoBakId) {
		return fireInfoBakDAO.getFireInfoBakById(fireInfoBakId);
	}

	@Override
	public FireInfoBak deleteFireInfoBak(FireInfoBak fireInfoBak) {
		return fireInfoBakDAO.deleteFireInfoBak(fireInfoBak);
	}
}
