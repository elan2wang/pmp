package org.pmp.dao.impl.fire;

import java.util.Map;

import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.fire.IFireDeviceDAO;
import org.pmp.util.ParamsToString;
import org.pmp.vo.FireDevice;
import org.pmp.vo.Zone;

public class FireDeviceDAO extends BaseDAO implements IFireDeviceDAO {

	public FireDevice saveFireDevice(FireDevice fireDevice) {
		String debugMsg="save fireDevice!";
		saveInstance(fireDevice, debugMsg);
		return fireDevice;
	}

	@Override
	public boolean deleteFireDeviceByParams(Map<String, Object> params) {
		try {
			String debugMsg="delete fireDevice by params!";
			
			StringBuilder hql = new StringBuilder();
			
			String aa = "delete from FireDevice where 1=1";
			
			hql.append(aa);
			
			hql.append(ParamsToString.toString(params));
			
			deleteInstance(hql.toString(), debugMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        
		return true;
	}

	@Override
	public FireDevice getFireDeviceByNumber(String deviceNumber) {
		Object obj=null;
		try {
			String debugMsg="getFireDeviceByNumber number"+deviceNumber;
			StringBuilder hql = new StringBuilder();
			
			hql.append("from FireDevice where deviceNumber="+deviceNumber);
			
			obj = getInstance(hql.toString(), debugMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (FireDevice)obj;
	}
}
