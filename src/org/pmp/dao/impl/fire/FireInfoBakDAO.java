package org.pmp.dao.impl.fire;

import java.util.List;
import java.util.Map;

import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IFireInfoBakDAO;
import org.pmp.util.Pager;
import org.pmp.vo.FireInfoBak;


public class FireInfoBakDAO extends BaseDAO implements IFireInfoBakDAO {
	
	public List<FireInfoBak> loadFireInfoBakListByProIdList(
			List<Integer> proIdList, Map<String, Object> params, String order,
			Pager paper) {
		
		List<FireInfoBak> list = null;
		
		String debugMsg = "load FireInfoBak list by projectList, proIdList="+proIdList;
		
		StringBuilder hql = new StringBuilder();
		
		StringBuilder aa = new StringBuilder("from FireInfoBak where project.proId in (");
		
		return null;
	}
	
}
