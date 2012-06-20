package org.pmp.service.impl.fire;

import java.util.List;
import java.util.Map;

import org.pmp.dao.fire.IFireInfoDAO;
import org.pmp.service.fire.IFireInfoService;
import org.pmp.vo.FireInfo;

public class FireInfoService implements IFireInfoService {

	private IFireInfoDAO fireInfoDAO;
	
	@Override
	public List<FireInfo> getCallFireInfos(Map<String,Object>params, String order) {
		return this.fireInfoDAO.getCallFireInfos(params, order);
	}

	public IFireInfoDAO getFireInfoDAO() {
		return fireInfoDAO;
	}

	public void setFireInfoDAO(IFireInfoDAO fireInfoDAO) {
		this.fireInfoDAO = fireInfoDAO;
	}

	@Override
	public List<FireInfo> getWarnFireInfos(Map<String, Object> params,String order,List<String> receiveInfos) {
		return this.fireInfoDAO.getWarnFireInfos(params,order,receiveInfos);
	}

	@Override
	public boolean deleteFireInfoByParams(Map<String, Object> params) {
		return this.fireInfoDAO.deleteFireInfoByParams(params);
	}

	@Override
	public boolean editFireInfoStateByDeviceNum(String deviceNum, Integer state) {
		try {
			FireInfo fireInfo=this.fireInfoDAO.getFireInfoByDeviceNum(deviceNum);
			if(fireInfo!=null){
				fireInfo.setState(state);
				this.fireInfoDAO.editFireInfo(fireInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
