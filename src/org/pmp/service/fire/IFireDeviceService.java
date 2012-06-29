package org.pmp.service.fire;

import java.util.Map;

import org.pmp.vo.FireDevice;

public interface IFireDeviceService {
    FireDevice saveFireDevice(FireDevice fireDevice);
    boolean deleteFireDeviceByParams(Map<String, Object> params);
    FireDevice getFireDeviceByNumber(String deviceNumber);
}
