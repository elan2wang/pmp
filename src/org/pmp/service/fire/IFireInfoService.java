package org.pmp.service.fire;

import java.util.List;
import java.util.Map;

import org.pmp.vo.FireInfo;
import org.pmp.vo.TbUser;


public interface IFireInfoService {
	List<FireInfo> getCallFireInfos(Map<String,Object>params,String order) ;
	List<FireInfo> getWarnFireInfos(Map<String, Object> params,String order,List<String> receiveInfos);
	boolean deleteFireInfoByParams(Map<String, Object> params);
    boolean editFireInfoStateByDeviceNum(String deviceNum,Integer state,TbUser tbUser);
}
