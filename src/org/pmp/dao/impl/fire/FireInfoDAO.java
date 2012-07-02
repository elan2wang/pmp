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
	public List<FireInfo> getWarnFireInfos(Map<String, Object> params,String order,List<String> receiveInfos) {
		List<FireInfo> list = null;
		
		String debugMsg = "load CallFireInfos list by params";
		
		StringBuilder hql = new StringBuilder();
		
		//String aa = "from FireInfo where 1=1";
		String aa = "from FireInfo where 1=1 and receiveInfo!='01'";
		
		hql.append(aa);
		
/*		hql.append(" and receiveInfo in(");
		String strtmp="";
		for (String receiveInfo : receiveInfos) {
			strtmp+=("'"+receiveInfo+"',");
		}
		strtmp=strtmp.substring(0,strtmp.length()-1);
		
		hql.append(strtmp);
		hql.append(")");*/
		
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
	public boolean deleteFireInfoByParams(Map<String, Object> params) {
		try {
			String debugMsg="delete FireInfo by params!";
			
			StringBuilder hql = new StringBuilder();
			
			String aa = "delete from FireInfo where 1=1";
			
			hql.append(aa);
			
			hql.append(ParamsToString.toString(params));
			
			deleteInstance(hql.toString(), debugMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
		return true;
	}

	@Override
	public FireInfo getFireInfoByDeviceNum(String deviceNum) {
        String debugMsg = "getFireInfo by deviceNum, deviceNum="+deviceNum;
        String hql="from FireInfo where deviceNumber='"+deviceNum+"'";
		return (FireInfo)getInstance(hql, debugMsg);
	}

	@Override
	public FireInfo editFireInfo(FireInfo fireInfo) {
		String debugMsg="update FireInfo!";
		updateInstance(fireInfo, debugMsg);
		return fireInfo;
	}

	@Override
	public FireInfo delteFireInfo(FireInfo fireInfo) {
		String debugMsg="delete FireInfo!"+fireInfo;
		deleteInstance(fireInfo, debugMsg);
		return fireInfo;
	}  
}