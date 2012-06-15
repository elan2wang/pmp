package org.pmp.service.fire;

import java.util.List;
import java.util.Map;

import org.pmp.util.Pager;
import org.pmp.vo.FireInfoBak;

public interface IFireInfoBakService {
     List<FireInfoBak> loadFireInfoBakListByProIdList(List<Integer> proIdList,Map<String,Object>params,String order,Pager paper);
}
