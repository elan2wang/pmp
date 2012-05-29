package org.pmp.dao.impl.fire;

import java.util.List;
import java.util.Map;

import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IFireInfoDAO;
import org.pmp.util.ParamsToString;
import org.pmp.vo.FireInfo;

public class FireInfoDAO extends BaseDAO implements IFireInfoDAO{

	@Override
	public List<FireInfo> getCallFireInfos(Map<String,Object>params,String order) {

			List<FireInfo> list = null;
			
			String debugMsg = "load CallFireInfos list by params";
			
			StringBuilder hql = new StringBuilder();
			
			String aa = "from FireInfo where 1=1";
			
			hql.append(aa);
			
			hql.append(ParamsToString.toString(params));
			
			if (order==null){
			    hql.append(" order by receiveTime desc");
			} else {
			    hql.append(" "+order);
			}
			
			list=(List<FireInfo>) loadListByCondition(hql.toString(), null, debugMsg);
			
		    return list;
	}

	@Override
	public List<FireInfo> getWarnFireInfos(Map<String, Object> params,
			List<String> receiveInfos, String order) {
		List<FireInfo> list = null;
		
		String debugMsg = "load CallFireInfos list by params";
		
		StringBuilder hql = new StringBuilder();
		
		String aa = "from FireInfo where 1=1";
		
		hql.append(aa);
		
		hql.append(" and receiveInfo in(");
		String strtmp="";
		for (String receiveInfo : receiveInfos) {
			strtmp+=receiveInfo+",";
		}
		strtmp=strtmp.substring(0,strtmp.length()-1);
		
		hql.append(strtmp);
		hql.append(")");
		
		hql.append(ParamsToString.toString(params));
		
		if (order==null){
		    hql.append(" order by receiveTime desc");
		} else {
		    hql.append(" "+order);
		}
		
		list=(List<FireInfo>) loadListByCondition(hql.toString(), null, debugMsg);
		
	    return list;
	}  
}