/**
 * Author            : Elan
 * Created On        : 2012-5-8 上午09:22:17
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.action.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.service.business.IElectricFeeItemService;
import org.pmp.service.business.IHouseService;
import org.pmp.service.business.IProjectService;
import org.pmp.service.fire.IZoneService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Building;
import org.pmp.vo.Company;
import org.pmp.vo.CondoFeeItem;
import org.pmp.vo.ElectricFeeItem;
import org.pmp.vo.House;
import org.pmp.vo.Project;
import org.pmp.vo.Zone;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class TreeAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(TreeAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IProjectService projectService;
    private IBuildingService buildingService;
    private IHouseService houseService;
    private ICondoFeeItemService condoFeeItemService;
    private IElectricFeeItemService electricFeeItemService;
    private IZoneService zoneService;
    
    private String target;
    private String url;
    
    private Integer proId;
    private Integer builId;
    
    //~ Methods ========================================================================================================
    
    /**
     * @Title: projectTree
     * @Description: 读取用户权限范围内的所有小区信息
     */
    public void projectTree(){
	List<String> nodes = new ArrayList<String>();
	List<Project> proList = getProList();
	
	Iterator<Project> ite = proList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Project pro = ite.next();
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "javascript: load_data('buildingTree?proId="+pro.getProId()+"','"+url+"','"+target+"');", 
		    "", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    /**
     * @Title: buildingTree
     * @Description: 读取用户权限范围的所有小区和指定小区的所有楼宇信息
     */
    public void buildingTree(){
	List<String> nodes = new ArrayList<String>();
	List<Project> proList = getProList();
	Pager pager = new Pager(1000,1);
	Iterator<Project> ite = proList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Project pro = ite.next();
	    if (proId.equals(pro.getProId())){
		nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "javascript: load_data('buildingTree?proId="+pro.getProId()+"','"+url+"','"+target+"');", 
			"", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", true));
		List<Building> builList = buildingService.loadBuildingList_ByProject(pro.getProId(), new HashMap<String,Object>(), "order by builId asc", pager);
		Iterator<Building> ite1 = builList.iterator();
		Integer pid1 = index-1;
		while(ite1.hasNext()){
		    Building buil = ite1.next();
		    nodes.add(JsonConvert.toJsonTreeNode(index++, pid1, buil.getBuilNum()+"号楼", "javascript:load_data('houseTree2?proId="+proId+"&builId="+buil.getBuilId()+"','"+url+"','"+target+"');", 
			    buil.getBuilType(), "", "../Images/dtree/buil.jpg", "../Images/dtree/buil.jpg", false));
		}
	    } else {
		nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "javascript: load_data('buildingTree?proId="+pro.getProId()+"','"+url+"','"+target+"');",
			"", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	    }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    /**
     * @Title: houseTree2
     * @Description: 读取用户权限范围内的所有小区、指定小区的所有楼宇、指定楼宇的所有房屋信息
     */
    public void houseTree2(){
	List<String> nodes = new ArrayList<String>();
	List<Project> proList = getProList();
	Pager pager = new Pager(1000,1);
	Iterator<Project> ite = proList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Project pro = ite.next();
	    logger.debug(proId);
	    if (proId.equals(pro.getProId())){
		nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "javascript: load_data('buildingTree?proId="+pro.getProId()+"','"+url+"','"+target+"');", 
			"", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", true));
		List<Building> builList = buildingService.loadBuildingList_ByProject(pro.getProId(), new HashMap<String,Object>(), "order by builId asc", pager);
		Iterator<Building> ite1 = builList.iterator();
		Integer pid1 = index-1;
		while(ite1.hasNext()){
		    Building buil = ite1.next();
		    if (builId.equals(buil.getBuilId())){
			nodes.add(JsonConvert.toJsonTreeNode(index++, pid1, buil.getBuilNum()+"号楼", "javascript:load_data('houseTree2?proId="+proId+"&builId="+buil.getBuilId()+"','"+url+"','"+target+"');", 
				buil.getBuilType(), "", "../Images/dtree/buil.jpg", "../Images/dtree/buil.jpg", true));
			List<House> houseList = houseService.loadHouseList_ByBuilding(buil.getBuilId(), new HashMap<String,Object>(), "order by houseId asc", pager);
			Iterator<House> ite2 = houseList.iterator();
			Integer pid2 = index-1;
			while(ite2.hasNext()){
			    House house = ite2.next();
			    logger.debug(target);
			    nodes.add(JsonConvert.toJsonTreeNode(index++, pid2, house.getHouseNum(), url+"?houseId="+house.getHouseId(),
				    "", target, "../Images/dtree/house.jpg", "../Images/dtree/house.jpg", false));
			}
		    } else {
			nodes.add(JsonConvert.toJsonTreeNode(index++, pid1, buil.getBuilNum()+"号楼", "javascript:load_data('houseTree2?proId="+proId+"&builId="+buil.getBuilId()+"','"+url+"','"+target+"');", 
				buil.getBuilType(), "", "../Images/dtree/buil.jpg", "../Images/dtree/buil.jpg", false));
		    }
		}
	    } else {
		nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "javascript: load_data('buildingTree?proId="+pro.getProId()+"','"+url+"','"+target+"');",
			"", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	    }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    
    /**
     * @Title: houseTree
     * @Description: 一次性将该用户权限范围内的所有House信息都读取出来，执行效率低
     */
    public void houseTree(){
	List<String> nodes = new ArrayList<String>();
	List<Project> proList = getProList();
	Pager pager = new Pager(1000,1);
	
	Iterator<Project> ite = proList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Project pro = ite.next();
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "", 
		    "", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	    List<Building> builList = buildingService.loadBuildingList_ByProject(pro.getProId(), new HashMap<String,Object>(), "order by builId asc", pager);
	    Iterator<Building> ite1 = builList.iterator();
	    Integer pid1 = index-1;
	    while(ite1.hasNext()){
		Building buil = ite1.next();
		nodes.add(JsonConvert.toJsonTreeNode(index++, pid1, buil.getBuilNum()+"号楼", "", 
			    buil.getBuilType(), "", "../Images/dtree/buil.jpg", "../Images/dtree/buil.jpg", false));
		List<House> houseList = houseService.loadHouseList_ByBuilding(buil.getBuilId(), new HashMap<String,Object>(), "order by houseId asc", pager);
		Iterator<House> ite2 = houseList.iterator();
		Integer pid2 = index-1;
		while(ite2.hasNext()){
		    House house = ite2.next();
		    nodes.add(JsonConvert.toJsonTreeNode(index++, pid2, house.getHouseNum(), url+"?houseId="+house.getHouseId(),
			    "", target, "../Images/dtree/house.jpg", "../Images/dtree/house.jpg", false));
		}
            }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public void condoFeeTimeTree(){
	/* get current user's refDomain */
	Object obj = SessionHandler.getUserRefDomain();
        /* tree nodes */
	List<String> nodes = new ArrayList<String>();
	/* get all the year that has condoFee item */
	List<?> yearList = condoFeeItemService.getCondoFeeYear();
	Iterator<Integer> ite = (Iterator<Integer>) yearList.iterator();
	Integer index = 1;
	while(ite.hasNext()){
	    Integer year = ite.next();
	    StringBuilder url0 = new StringBuilder();
	    if (obj instanceof Project){
		url0.append(url+"?proId="+((Project)obj).getProId());
	    } else if (obj instanceof Company){
		url0.append(url+"?comId="+((Company)obj).getComId());
	    }
	    /* add the first level node year */
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, year.toString()+"年物业费清单",
		    url0.append("&year="+year).toString(),"", target, "", "", false));
	    /* add second level node month */
	    Integer pid=index-1;
	    Integer month = 13;
	    while(month-- > 1){
		StringBuilder url1 = new StringBuilder();
		if (obj instanceof Project){
		    url1.append(url+"?proId="+((Project)obj).getProId());
		} else if (obj instanceof Company){
		    url1.append(url+"?comId="+((Company)obj).getComId());
		}
		nodes.add(JsonConvert.toJsonTreeNode(index++, pid, month+"月份清单", 
			url1.append("&year="+year+"&month="+month).toString(),"", target, "", "", false));
	    }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public void condoFeeItemTree(){
	List<String> nodes = new ArrayList<String>();
	Company company = (Company)SessionHandler.getUserRefDomain();
	Pager pager = new Pager(10000,1);
	List<?> list = condoFeeItemService.loadCondoFeeItemListBy_ComID(pager, company.getComId());
	List<Project> proList = new ArrayList<Project>();
	Iterator<?> ite = list.iterator();
	while(ite.hasNext()){
	    Project pro = ((CondoFeeItem)ite.next()).getProject();
	    if(!proList.contains(pro)){
		proList.add(pro);
	    }
	}
	logger.debug("proList.size="+proList.size());
        Integer index = 1;
	Iterator<Project> ite2 = proList.iterator();
	while(ite2.hasNext()){
	    Project pro = ite2.next();
	    /* add the first level node project */
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "",
		    "", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	    List<?> cfiList = condoFeeItemService.loadCondoFeeItemListBy_ProID(pager, pro.getProId());
	    Iterator<?> ite3 = cfiList.iterator();
	    Integer pid = index-1;
	    while(ite3.hasNext()){
		CondoFeeItem cfi = (CondoFeeItem)ite3.next();
		nodes.add(JsonConvert.toJsonTreeNode(index++, pid, cfi.getItemName(), url+"?cfiId="+cfi.getCfiId(),
			    "", target, "", "", false));
	    }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public void electricFeeItemTree(){
	List<String> nodes = new ArrayList<String>();
	Company company = (Company)SessionHandler.getUserRefDomain();
	Pager pager = new Pager(10000,1);
	Integer comId = company.getComId();
	List<ElectricFeeItem> efiList = electricFeeItemService.loadElectricFeeItemList_ByCompany(comId, "", pager);
	List<Project> proList = new ArrayList<Project>();
	Iterator<?> ite = efiList.iterator();
	while(ite.hasNext()){
	    Project pro = ((ElectricFeeItem)ite.next()).getProject();
	    if(!proList.contains(pro)){
		proList.add(pro);
	    }
	}
	Integer index = 1;
	Iterator<Project> ite2 = proList.iterator();
	while(ite2.hasNext()){
	    Project pro = ite2.next();
	    /* add the first level node project */
	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "",
		    "", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
	    List<?> efiList2 = electricFeeItemService.loadElectricFeeItemList_ByProject(pro.getProId(), "", pager);
	    Iterator<?> ite3 = efiList2.iterator();
	    Integer pid = index-1;
	    while(ite3.hasNext()){
		ElectricFeeItem efi = (ElectricFeeItem)ite3.next();
		nodes.add(JsonConvert.toJsonTreeNode(index++, pid, efi.getItemName(), url+"?efiId="+efi.getEfiId(),
			    "", target, "", "", false));
	    }
	}
	String data = JsonConvert.toJsonTree(nodes);
	logger.debug(data);
	JsonConvert.output(data);
    }
    
    public void zoneTree(){
    	
    	Object obj = SessionHandler.getUserRefDomain();
    	List<String> nodes = new ArrayList<String>();
    	Pager pager = new Pager(10000,1);
    	
    	List<Project> proList = new ArrayList<Project>();
    	
    	if (obj instanceof Company){
    	    Company com = (Company)obj;
    	    Map<String,Object> params = new HashMap<String,Object>();
    	    String order = "order by proId asc";
    	    proList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
    	}
    	
    	if (obj instanceof Project){
    	    Project pro = (Project)obj;
    	    proList.add(pro);
    	}
    	
    	Iterator<Project> ite = proList.iterator();
    	Integer index = 1;
    	
    	while(ite.hasNext()){
    		
    	    Project pro = ite.next();
    	    nodes.add(JsonConvert.toJsonTreeNode(index++, 0, pro.getProName(), "", 
    		    "", "", "../Images/dtree/pro.jpg", "../Images/dtree/pro.jpg", false));
    	    
    	    String order = "order by zoneId asc";
    	    
    	    List<Zone> zoneList = zoneService.loadZoneListByProId(pro.getProId(), null, order, null);
            
    	    Iterator<Zone> ite1= zoneList.iterator();
    	    
    	    Integer pid1 = index-1;
    	    
    	    while(ite1.hasNext()){
    	    	    
		    		Zone zone = ite1.next();
		    		
		    		nodes.add(JsonConvert.toJsonTreeNode(index++, pid1,zone.getZoneName(),"javascript:linkZone("+zone.getZoneId()+");", 
		    			    "", "", "../Images/dtree/buil.jpg", "../Images/dtree/buil.jpg", false));
            }
    	}
    	
    	String data = JsonConvert.toJsonTree(nodes);
    	logger.debug(data);
    	JsonConvert.output(data);
    }
    
    private List<Project> getProList(){
	Object obj = SessionHandler.getUserRefDomain();
	Pager pager = new Pager(10000,1);
	List<Project> proList = new ArrayList<Project>();
	
	if (obj instanceof Company){
	    Company com = (Company)obj;
	    Map<String,Object> params = new HashMap<String,Object>();
	    String order = "order by proId asc";
	    proList = projectService.loadProjectList_ByCompany(com.getComId(), params, order, pager);
	}
	if (obj instanceof Project){
	    Project pro = (Project)obj;
	    proList.add(pro);
	}
	return proList;
    }
    
    //~ Getters and Setters ============================================================================================
    public IProjectService getProjectService() {
        return projectService;
    }
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }
    public IBuildingService getBuildingService() {
        return buildingService;
    }
    public void setBuildingService(IBuildingService buildingService) {
        this.buildingService = buildingService;
    }
    public IHouseService getHouseService() {
        return houseService;
    }
    public void setHouseService(IHouseService houseService) {
        this.houseService = houseService;
    }

    public ICondoFeeItemService getCondoFeeItemService() {
        return condoFeeItemService;
    }

    public void setCondoFeeItemService(ICondoFeeItemService condoFeeItemService) {
        this.condoFeeItemService = condoFeeItemService;
    }

	public IElectricFeeItemService getElectricFeeItemService() {
        return electricFeeItemService;
    }

    public void setElectricFeeItemService(
    	IElectricFeeItemService electricFeeItemService) {
        this.electricFeeItemService = electricFeeItemService;
    }

	public IZoneService getZoneService() {
		return zoneService;
	}

	public void setZoneService(IZoneService zoneService) {
		this.zoneService = zoneService;
	}

	public String getTarget() {
	    return target;
	}

	public void setTarget(String target) {
	    this.target = target;
	}

	public String getUrl() {
	    return url;
	}

	public void setUrl(String url) {
	    this.url = url;
	}

	public Integer getProId() {
	    return proId;
	}

	public void setProId(Integer proId) {
	    this.proId = proId;
	}

	public Integer getBuilId() {
	    return builId;
	}

	public void setBuilId(Integer builId) {
	    this.builId = builId;
	}
    
}
