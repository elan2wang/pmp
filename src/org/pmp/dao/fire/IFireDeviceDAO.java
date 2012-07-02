package org.pmp.dao.fire;

import java.util.Map;

import org.pmp.vo.FireDevice;

public interface IFireDeviceDAO{
     FireDevice saveFireDevice(FireDevice fireDevice);
     boolean deleteFireDeviceByParams(Map<String, Object> params);
     FireDevice getFireDeviceByNumber(String deviceNumber);
}
