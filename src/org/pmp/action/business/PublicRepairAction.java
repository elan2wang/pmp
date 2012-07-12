/**
 * Author            : Elan
 * Created On        : 2012-7-10 下午12:45:05
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
import org.pmp.service.business.IPublicRepairItemService;
import org.pmp.service.business.IPublicRepairService;
import org.pmp.util.Pager;
import org.pmp.vo.PublicRepair;
import org.pmp.vo.PublicRepairItem;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class PublicRepairAction extends BaseAction {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(PublicRepairAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IPublicRepairService publicRepairService;
    private IPublicRepairItemService publicRepairItemService;
    
    private PublicRepair publicRepair;
    private String idStr;
    private Integer fbiId;
    private Integer fbId;
    
    //~ Methods ========================================================================================================
    public void addPublicRepair(){
	publicRepair.setPublicRepairItem(publicRepairItemService.getPublicRepairItemByID(fbiId));
	publicRepairService.addPublicRepair(publicRepair);
	
//	MyJson json = new MyJson();
//	Map<String, Object> params = new LinkedHashMap<String,Object>();
//	params.put("result", "success");
//	params.put("msg", "维修记录添加成功");
//	json.output(json.toJson(params));
	
    }
    
    public void editPublicRepair(){
	PublicRepair instance = publicRepairService.getPublicRepairByID(publicRepair.getFbId());
	publicRepair.setPublicRepairItem(instance.getPublicRepairItem());
	
	publicRepairService.editPublicRepair(publicRepair);
    }
    
    public String getPublicRepairByID(){
	PublicRepair instance = publicRepairService.getPublicRepairByID(fbId);
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("publicRepair", instance);
	return SUCCESS;
    }

    public void deletePublicRepair(){
    	Map<String,String> params = new HashMap<String, String>();
	List<PublicRepair> list = new ArrayList<PublicRepair>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    PublicRepair item = publicRepairService.getPublicRepairByID(Integer.parseInt(checkedID[i]));
	    list.add(item);
	}
	logger.debug("cfList.size="+list.size());
	publicRepairService.batchDeletePublicRepair(list);
	MyJson json = new MyJson();
	params.put("msg", "公共维修信息删除成功");
	MyJson.print(json.toJson(params));
    }
    
    public void loadPublicRepairList_ByFBI(){
	Pager pager = getPager();
	/* set query parameter */
	Map<String,Object> params = getParams();
	/* set sorter type */
	String order = getOrder();
	
	/* retrieve user's refDomain and set proId */
	List<PublicRepair> list = null;
	
	list = publicRepairService.loadPublicRepairList_ByFBI(fbiId, params, order, pager);
	
	PublicRepairItem item = publicRepairItemService.getPublicRepairItemByID(fbiId);
	String title = item.getProject().getProName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+item.getItemName();
	String[] attrs = {"fbId","equipNum","repairDate","repairType","dutyMan","state" };
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	String data = json.toJson(list, title, pager);
	json.output(data);
    }
    
    //~ Getters and Setters ============================================================================================

    public IPublicRepairService getPublicRepairService() {
        return publicRepairService;
    }

    public void setPublicRepairService(IPublicRepairService publicRepairService) {
        this.publicRepairService = publicRepairService;
    }

    public IPublicRepairItemService getPublicRepairItemService() {
        return publicRepairItemService;
    }

    public void setPublicRepairItemService(
    	IPublicRepairItemService publicRepairItemService) {
        this.publicRepairItemService = publicRepairItemService;
    }

    public PublicRepair getPublicRepair() {
        return publicRepair;
    }

    public void setPublicRepair(PublicRepair publicRepair) {
        this.publicRepair = publicRepair;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public Integer getFbiId() {
        return fbiId;
    }

    public void setFbiId(Integer fbiId) {
        this.fbiId = fbiId;
    }

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }
    
    
}
