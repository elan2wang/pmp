package org.pmp.service.impl.fire;

import java.util.Map;

import org.pmp.dao.fire.IFireDeviceDAO;
import org.pmp.service.fire.IFireDeviceService;
import org.pmp.vo.FireDevice;

public class FireDeviceService implements IFireDeviceService {

	private IFireDeviceDAO fireDeviceDAO;
	
	@Override
	public FireDevice saveFireDevice(FireDevice fireDevice) {
		fireDeviceDAO.saveFireDevice(fireDevice);
		return fireDevice;
	}

	public IFireDeviceDAO getFireDeviceDAO() {
		return fireDeviceDAO;
	}

	public void setFireDeviceDAO(IFireDeviceDAO fireDeviceDAO) {
		this.fireDeviceDAO = fireDeviceDAO;
	}

	@Override
	public boolean deleteFireDeviceByParams(Map<String, Object> params) {
		return this.fireDeviceDAO.deleteFireDeviceByParams(params);
	}

	@Override
	public FireDevice getFireDeviceByNumber(String deviceNumber) {
		return this.fireDeviceDAO.getFireDeviceByNumber(deviceNumber);
	}
}
