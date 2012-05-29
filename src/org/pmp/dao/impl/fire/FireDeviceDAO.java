package org.pmp.dao.impl.fire;

import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IFireDeviceDAO;
import org.pmp.vo.FireDevice;

public class FireDeviceDAO extends BaseDAO implements IFireDeviceDAO {

	public FireDevice saveFireDevice(FireDevice fireDevice) {
		String debugMsg="save fireDevice!";
		saveInstance(fireDevice, debugMsg);
		return fireDevice;
	}
	
}
