package org.pmp.service.fire;

import java.util.List;
import java.util.Map;

import org.pmp.vo.FireInfo;


public interface IFireInfoService {
	List<FireInfo> getCallFireInfos(Map<String,Object>params,String order) ;
	List<FireInfo> getWarnFireInfos(Map<String, Object> params,
			List<String> receiveInfos, String order);
	boolean deleteFireInfoByParams(Map<String, Object> params);
}
