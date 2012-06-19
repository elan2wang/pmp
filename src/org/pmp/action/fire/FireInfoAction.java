package org.pmp.action.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.service.business.IProjectService;
import org.pmp.service.fire.IFireInfoBakService;
import org.pmp.service.fire.IFireInfoService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.FireInfo;
import org.pmp.vo.FireInfoBak;
import org.pmp.vo.Project;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.impl.io.FVDCodeBaseImpl;


public class FireInfoAction extends ActionSupport{
	
    private static Logger logger = Logger.getLogger(FireInfoAction.class.getName());
	
	private IFireInfoService fireInfoService;
	
	private IFireInfoBakService fireInfoBakService;
	
	private IProjectService projectService;
	
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
	
	private String deviceNum;
	private Integer state;
	
	private String idStr; 
	
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
		
		 Pager pager = new Pager(rp,page);
    	 Object obj=SessionHandler.getUserRefDomain();
    	 //aaa 项目管理员        
    	 //bbb 公司管理员
    	 String order = null;
		 Map<String, Object> params=new HashMap<String, Object>();
		 if (!qtype.equals("")&&!query.equals("")){
		       params.put(qtype, query);
		 }
		 if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
		       order= "order by "+sortname+" "+sortorder;
		 } else{
		       order = "order by disposeTime desc";
		 }
		 
		 List<Integer> proIdList=new ArrayList();
		 
    	 if(obj instanceof Company){
    		     //公司管理员
    		     Company company=(Company)obj;
    		     logger.info("公司ID:###########"+company.getComId());
				 List<Project> pList=projectService.loadProjectList_ByCompany(company.getComId(), null, null, null);
				 for (Project pro : pList) {
					proIdList.add(pro.getProId());
				 }
    	 }else{
	    		 //项目管理员
	    		 Project project=(Project)obj;
	    		 logger.info("项目ID:############"+project.getProId()); //项目ID
	    		 proIdList.add(project.getProId());
    	 }
		 
		 List<FireInfoBak> fireInfoBaks=fireInfoBakService.loadFireInfoBakListByProIdList(proIdList, params, order, pager);
         
		 String[] attrs = {"zone","deviceNumber","receiveTime","receiveInfo","state","disposeTime","tbUser"};
		 
		 List<String> show = Arrays.asList(attrs);
		 
		 String data = JsonConvert.list2FlexJson(pager, fireInfoBaks, "org.pmp.vo.FireInfoBak", show);
		 
		 logger.debug(data);
		 
		 JsonConvert.output(data);
	}
	
	public void deleteFireInfoBak(){
		String[] checkedID = idStr.split(",");
		for (int i = 0; i < checkedID.length; i++) {
			FireInfoBak fireInfoBak = fireInfoBakService.getFireInfoBakById(Integer.valueOf(checkedID[i]));
			fireInfoBakService.deleteFireInfoBak(fireInfoBak);
		}
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

	public IFireInfoBakService getFireInfoBakService() {
		return fireInfoBakService;
	}

	public void setFireInfoBakService(IFireInfoBakService fireInfoBakService) {
		this.fireInfoBakService = fireInfoBakService;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRp() {
		return rp;
	}

	public void setRp(Integer rp) {
		this.rp = rp;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	
}
