package org.pmp.dao.fire;

import java.util.List;
import java.util.Map;

import org.pmp.vo.FireInfo;

public interface IFireInfoDAO{
     List<FireInfo> getCallFireInfos(Map<String,Object>params,String order);
     List<FireInfo> getWarnFireInfos(Map<String,Object>params,String order,List<String> receiveInfos);
     boolean deleteFireInfoByParams(Map<String,Object>params);
     FireInfo getFireInfoByDeviceNum(String deviceNum);
     FireInfo editFireInfo(FireInfo fireInfo);
}
