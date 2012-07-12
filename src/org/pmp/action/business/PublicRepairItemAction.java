/**
 * Author            : Elan
 * Created On        : 2012-7-10 上午09:46:46
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.IProjectService;
import org.pmp.service.business.IPublicRepairItemService;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Project;
import org.pmp.vo.PublicRepairItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PublicRepairItemAction extends BaseAction {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PublicRepairItemAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IPublicRepairItemService publicRepairItemService;
    
    private PublicRepairItem publicRepairItem;
    private IProjectService projectService;
    
    private String idStr;
    private Integer fbiId;
    private Integer proId;
    
    //~ Methods ========================================================================================================
    public String addPublicRepairItem(){
	publicRepairItem.setProject(projectService.getProjectByID(proId));
	publicRepairItemService.addPublicRepairItem(publicRepairItem);
	return SUCCESS;
    }
    
    public String editPublicRepairItem(){
	PublicRepairItem item = publicRepairItemService.getPublicRepairItemByID(publicRepairItem.getFbiId());
	publicRepairItem.setProject(item.getProject());
	
	publicRepairItemService.editPublicRepairItem(publicRepairItem);
	return SUCCESS;
    }
    
    public void deletePublicRepairItem(){
	List<PublicRepairItem> list = new ArrayList<PublicRepairItem>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    PublicRepairItem item = publicRepairItemService.getPublicRepairItemByID(Integer.parseInt(checkedID[i]));
	    list.add(item);
	}
	logger.debug("cfList.size="+list.size());
	publicRepairItemService.batchDeletePublicRepairItem(list);
	Map<String,String> result = new HashMap<String, String>();
	MyJson json = new MyJson();
	result.put("msg", "公共维修项目删除成功");
	json.output(json.toJson(result));
    }
    
    public String getPublicRepairItemByID(){
	PublicRepairItem item = publicRepairItemService.getPublicRepairItemByID(fbiId);
	String[] options = item.getEquipList().split(",");
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("publicRepairItem", item);
	request.setAttribute("options", options);
	return SUCCESS;
    }
    
    public void loadPublicRepairItemList(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	
	/* retrieve user's refDomain and set proId */
	List<PublicRepairItem> list = null;
	Object obj = SessionHandler.getUserRefDomain();
	
	if (obj instanceof Project){
	    list = publicRepairItemService.loadPublicRepairItemList_ByProject(((Project)obj).getProId(), params, order, pager);
	} else if (obj instanceof Company){
	    list = publicRepairItemService.loadPublicRepairItemList_ByCompany(((Company)obj).getComId(), params, order, pager);
	}
	String[] attrs = {"fbiId","project.proName","itemName","itemType"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(list, "",pager);
	json.output(data);
    }
    
    //~ Getters and Setters ============================================================================================
    public IPublicRepairItemService getPublicRepairItemService() {
        return publicRepairItemService;
    }

    public void setPublicRepairItemService(
    	IPublicRepairItemService publicRepairItemService) {
        this.publicRepairItemService = publicRepairItemService;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public void setPublicRepairItem(PublicRepairItem publicRepairItem) {
        this.publicRepairItem = publicRepairItem;
    }
    
    public PublicRepairItem getPublicRepairItem(){
	return publicRepairItem;
    }

    public Integer getFbiId() {
        return fbiId;
    }

    public void setFbiId(Integer fbiId) {
        this.fbiId = fbiId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }
}
