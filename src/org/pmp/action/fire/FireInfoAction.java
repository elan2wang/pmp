package org.pmp.action.fire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.service.fire.IFireInfoService;
import org.pmp.util.JsonConvert;
import org.pmp.vo.FireInfo;
import org.pmp.vo.Project;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.impl.io.FVDCodeBaseImpl;


public class FireInfoAction extends ActionSupport{
	
    private static Logger logger = Logger.getLogger(FireInfoAction.class.getName());
	
	private IFireInfoService fireInfoService;
	
	private String deviceNum;
	private Integer state;
	
	public void getFireInfos(){

			logger.info("加载报警数据列表######################");
			
			Map<String, Object> params=new HashMap<String, Object>();//报警数据
			params.put("state", 1);
			params.put("receiveInfo", "01");
			
			List<FireInfo> callList=fireInfoService.getCallFireInfos(params, null);
			
			params.clear();
			params.put("state", 1);
/*			List<String> receiveInfos=new ArrayList<String>();
			receiveInfos.add("02");
			receiveInfos.add("03");
			receiveInfos.add("04");
			receiveInfos.add("05");
			receiveInfos.add("06");
			receiveInfos.add("07");
			receiveInfos.add("08");
			receiveInfos.add("09");*/
            
			List<FireInfo> warnList=fireInfoService.getWarnFireInfos(params, null,null);
			
			List<String> callNodes = new ArrayList<String>();
			Iterator<FireInfo> calls = callList.iterator();
			while(calls.hasNext()){
				  FireInfo fireInfo = calls.next();
				  callNodes.add(JsonConvert.toJsonFireInfos(fireInfo.getZone().getZoneId(), fireInfo.getDeviceNumber(), fireInfo.getReceiveTime(), fireInfo.getReceiveInfo()));
			}
			
			List<String> warnNodes = new ArrayList<String>();
			Iterator<FireInfo> warns = warnList.iterator();
			while(warns.hasNext()){
				  FireInfo fireInfo = warns.next();
				  warnNodes.add(JsonConvert.toJsonFireInfos(fireInfo.getZone().getZoneId(), fireInfo.getDeviceNumber(), fireInfo.getReceiveTime(), fireInfo.getReceiveInfo()));
			}
			
			String data=JsonConvert.toJsonFireInfoList(callNodes,warnNodes);
			logger.info(data);
			JsonConvert.output(data);
	}

	public void updateFireInfoState(){
		    logger.info("########################"+deviceNum+":"+state);
		    fireInfoService.editFireInfoStateByDeviceNum(deviceNum, state);
	}
	
	/**
	 * 加载消控历史记录列表
	 */
	public void loadFireInfoBakList(){
		 logger.info("加载消控历史数据列表######################");
		 
	}
	
	public IFireInfoService getFireInfoService() {
		return fireInfoService;
	}

	public void setFireInfoService(IFireInfoService fireInfoService) {
		this.fireInfoService = fireInfoService;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
