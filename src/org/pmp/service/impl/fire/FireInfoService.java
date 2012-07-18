package org.pmp.service.impl.fire;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.pmp.dao.fire.IFireInfoBakDAO;
import org.pmp.dao.fire.IFireInfoDAO;
import org.pmp.service.fire.IFireInfoService;
import org.pmp.vo.FireInfo;
import org.pmp.vo.FireInfoBak;
import org.pmp.vo.TbUser;

public class FireInfoService implements IFireInfoService {

	private IFireInfoDAO fireInfoDAO;
	
	private IFireInfoBakDAO fireInfoBakDAO;
	
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
	public boolean editFireInfoStateByDeviceNum(String deviceNum, Integer state,String operator) {
		try {
			FireInfo fireInfo=this.fireInfoDAO.getFireInfoByDeviceNum(deviceNum);
			if(fireInfo!=null){
				//fireInfo.setState(state);
				//this.fireInfoDAO.editFireInfo(fireInfo);
				fireInfoDAO.delteFireInfo(fireInfo);
			}
			
			FireInfoBak fireInfoBak=new FireInfoBak();
			
			fireInfoBak.setOperator(operator);
			fireInfoBak.setDeviceNumber(deviceNum);
			fireInfoBak.setDisposeTime(new Date());
			fireInfoBak.setReceiveInfo(fireInfo.getReceiveInfo());
			fireInfoBak.setReceiveTime(fireInfo.getReceiveTime());
			fireInfoBak.setZone(fireInfo.getZone());
			fireInfoBak.setState(state);
			
			fireInfoBakDAO.saveFireInfoBak(fireInfoBak);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public IFireInfoBakDAO getFireInfoBakDAO() {
		return fireInfoBakDAO;
	}

	public void setFireInfoBakDAO(IFireInfoBakDAO fireInfoBakDAO) {
		this.fireInfoBakDAO = fireInfoBakDAO;
	}
	
}
