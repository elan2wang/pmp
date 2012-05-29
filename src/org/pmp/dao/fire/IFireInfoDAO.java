package org.pmp.dao.fire;

import java.util.List;
import java.util.Map;

import org.pmp.vo.FireInfo;

public interface IFireInfoDAO{
     List<FireInfo> getCallFireInfos(Map<String,Object>params,String order);
     List<FireInfo> getWarnFireInfos(Map<String,Object>params,List<String> receiveInfos,String order);
}
