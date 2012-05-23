package org.pmp.service.fire;

import java.util.List;

import org.pmp.util.Pager;
import org.pmp.vo.Zone;

public interface IZoneService {
    List<?> loadZoneListByProId(Integer proId,Pager pager);
    
    Zone saveZone(Zone zone);
    
    Zone getZoneById(Integer zoneID);
    
}
