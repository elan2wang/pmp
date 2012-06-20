package org.pmp.dao.impl.fire;

import java.util.List;
import java.util.Map;

import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IFireInfoBakDAO;
import org.pmp.util.Pager;
import org.pmp.util.ParamsToString;
import org.pmp.vo.FireInfoBak;


public class FireInfoBakDAO extends BaseDAO implements IFireInfoBakDAO {
	
	public List<FireInfoBak> loadFireInfoBakListByProIdList(
			List<Integer> proIdList, Map<String, Object> params, String order,
			Pager paper) {
		
		List<FireInfoBak> list = null;
		
		String debugMsg = "load FireInfoBak list by projectList, proIdList="+proIdList;
		
		StringBuilder hql = new StringBuilder();
		
		StringBuilder aa = new StringBuilder("from FireInfoBak where zone.project.proId in (");
		
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
		
		hql.append(" "+order);
        
		list=(List<FireInfoBak>) loadListByCondition(hql.toString(), paper, debugMsg);
		
		return list;
	}

	@Override
	public FireInfoBak getFireInfoBakById(Integer fireInfoBakId) {
		String debugMsg="get FireInfoBakById by ID"+fireInfoBakId;
		return (FireInfoBak)this.getInstanceById(FireInfoBak.class, fireInfoBakId, debugMsg);
	}

	@Override
	public FireInfoBak deleteFireInfoBak(FireInfoBak fireInfoBak) {
		String debugMsg="delete FireInfoBakById="+fireInfoBak;
		this.deleteInstance(fireInfoBak, debugMsg);
		return fireInfoBak;
	}
}
