/**
 * Author            : Elan
 * Created On        : 2012-4-16 下午07:28:32
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.ExportUtil;
import org.pmp.jms.JmsPublisher;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.service.business.ICondoFeeService;
import org.pmp.util.JsonConvert;
import org.pmp.util.Pager;
import org.pmp.vo.CondoFee;
import org.pmp.vo.CondoFeeItem;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(CondoFeeAction.class.getName());
    //~ Instance Fields ================================================================================================
    private ICondoFeeService condoFeeService;
    private ICondoFeeItemService condoFeeItemService;
    
    private CondoFee condoFee;
    
    private Integer cfiId;
    private Integer cfId;
    private String type = "all";
    
    private Integer ownerId;
    private String state;
    
    /* the element order of this array should be in line with the CondoFee */
    private final String[] attrStr = {"物业费项目名称","房号","业主姓名","开始日期","结束日期",
	    "收费日期","收费标准","应收金额","实收金额","收费票据","收费人员","录入人员",
            "审核人员","状态","生成时间","录入时间","审核时间","短信已发"};
    
    /* corresponding to cf_attr.jsp */
    private String[] showAttrs;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void loadCondoFeeList_ByOwner(){
	Map<String,Object> params = new HashMap<String,Object>();
	params.put("state", state);
	String order = null;
	Pager pager = new Pager(1000,1);
	List<?> list= condoFeeService.loadCondoFeeList_ByOwner(ownerId, params, order, pager);
        String data = JsonConvert.list2Json(list, "org.pmp.vo.CondoFee");   
        logger.debug(data);
<<<<<<< HEAD
<<<<<<< .merge_file_a04748
        String ids = "1";
        JmsPublisher.sendMessgae(ids);
=======
=======
>>>>>>> 781c28d5e9915da32a8fab0216a9239da04ff970
        
        
        //String ids = "1";
        //JmsPublisher.sendMessgae(ids);
<<<<<<< HEAD
>>>>>>> .merge_file_a05832
=======
>>>>>>> 781c28d5e9915da32a8fab0216a9239da04ff970
    }
    
    public String loadCondoFeeInstance(){
	CondoFee condoFee = condoFeeService.getCondoFeeByID(cfId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("condoFee", condoFee);
	return SUCCESS;
    }
    
    public String inputCondoFee(){
	CondoFee fee = condoFeeService.getCondoFeeByID(condoFee.getCfId());
	//设置记录时间为当前时间
	fee.setInputTime(new Date());
	//设置状态为"已缴费"
	fee.setState("已缴费");
	//设置录入人员为，当前登录用户
	fee.setRecordPerson("elan");
	//设置缴费信息
	fee.setFetchDate(condoFee.getFetchDate());
	fee.setFetchMoney(condoFee.getFetchMoney());
	fee.setFetchPerson(condoFee.getFetchPerson());
	fee.setFetchTicket(condoFee.getFetchTicket());
	
	//物业费记录的其他信息保持不变
	condoFeeService.inputCondoFee(fee);
	
	//编辑成功后获取该项目的物业费列表
	Pager pager = new Pager(10000,1);
	cfiId = fee.getCondoFeeItem().getCfiId();
	List<?> condoFeeList = condoFeeService.loadCondoFeeListBy_cfiId(cfiId, pager);
	CondoFeeItem condoFeeItem = condoFeeItemService.getCondoFeeItemByID(cfiId);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("condoFeeList", condoFeeList);
	request.setAttribute("condoFeeItem", condoFeeItem);
	
	return SUCCESS;
    }
    
    public String auditCondoFee(){
	CondoFee fee = condoFeeService.getCondoFeeByID(condoFee.getCfId());
	//设置审核时间为当前时间
	fee.setAuditTime(new Date());
	//设置审核人员为当前登录的用户
	fee.setAuditPerson("elan2");
	//设置状态为"已审核"
	fee.setState("已审核");
	
	condoFeeService.auditCondoFee(fee);
	
	return SUCCESS;
    }
    
    public String loadCondoFeeList(){
	Pager pager = new Pager(10000,1);
	List<?> payedList = condoFeeService.loadPayedCondoFeeList(cfiId, pager);
	List<?> nonePayedList = condoFeeService.loadNonePayedCondoFeeList(cfiId, pager);
	List<?> condoFeeList = condoFeeService.loadCondoFeeListBy_cfiId(cfiId, pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	if (type.equals("payed")){
	    request.setAttribute("condoFeeList", payedList);
	} else if (type.equals("none")){
	    request.setAttribute("condoFeeList", nonePayedList);
	} else {
	    request.setAttribute("condoFeeList", condoFeeList);
	}
	
	CondoFeeItem condoFeeItem = condoFeeItemService.getCondoFeeItemByID(cfiId);
	request.setAttribute("condoFeeItem", condoFeeItem);
	request.setAttribute("nonePayedCount", nonePayedList.size());
	request.setAttribute("payedCount", payedList.size());
	
	return SUCCESS;
    }
    
    public String loadAttributes(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, String> attrs = new HashMap<String, String>();
 	this.setAttr(attrs);
	request.setAttribute("attrs", attrs);
	return SUCCESS;
    }
    
    public void excelExport(){
	HttpServletResponse response = ServletActionContext.getResponse();
	/* set ContentType of MIME response to browser */
	response.setContentType("application/vnd.ms-excel;charset=gb2312");
	/* set name of the output file */
	response.setHeader("Content-Disposition", "attachment;filename=condofeelist.xls");

	List<String> showAttr = Arrays.asList(showAttrs);
	
	/* set the header which used to generate table header */
	String[] header = new String[showAttr.size()];
	Map<String, String> attrs = new HashMap<String, String>();
	this.setAttr(attrs);
	int j = 0;
	for (int i=0;i<attrStr.length;i++){
	    String key = (String) getKey(attrs,attrStr[i]);
	    if(showAttr.contains(key)){
		logger.debug("j="+j+"  i="+i+"  "+attrStr[i]);
		header[j++] = attrStr[i];
	    }
	}
	
	Pager pager = new Pager(10000,1);
	List<?> cfList = null;
	if (type.equals("payed")){
	    cfList = condoFeeService.loadPayedCondoFeeList(1, pager);
	} else if (type.equals("none")){
	    cfList = condoFeeService.loadNonePayedCondoFeeList(1, pager);
	} else {
	    cfList = condoFeeService.loadCondoFeeListBy_cfiId(1, pager);
	}
	/* execute ExportUtil method to output file */
	try {
	    ExportUtil.execute(response.getOutputStream(),header,cfList,"org.pmp.vo.CondoFee",showAttr);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * @Title: setAttr
     * @Description: 把CondoFee的所有属性名(cfId除外)作为key，属性的别名作为value,存到Map对象中
     *
     * @param  attrs Map<String, String> attrs  表示存储属性名和描述的Map
     * @param  showAttr  List<String>  表示要显示的属性名列表
     */
    private void setAttr(Map<String, String> attrs){
	try {
	    Class<?> cls= Class.forName("org.pmp.vo.CondoFee");
            Field[] fields = cls.getDeclaredFields();
            int i = 0;
            for(Field field : fields){
                String fieldName = field.getName();
                if(fieldName.equals("cfId"))continue;
                attrs.put(fieldName, attrStr[i]);
                logger.debug("attrStr = "+attrStr[i]);
                i++;
            }
            
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * @Title: getKey
     * @Description: 根据value从map中获取key
     *
     * @param  map  Map类型
     * @param  value  Object类型
     * @return Object  value对应的key
     */
    private Object getKey(Map map,Object value){
        for(Object key:map.keySet()){
            if(map.get(key).equals(value))return key;
        }
        return null;
    }
    
    //~ Getters and Setters ============================================================================================

    public ICondoFeeService getCondoFeeService() {
        return condoFeeService;
    }

    public void setCondoFeeService(ICondoFeeService condoFeeService) {
        this.condoFeeService = condoFeeService;
    }

    public Integer getCfiId() {
        return cfiId;
    }

    public void setCfiId(Integer cfiId) {
        this.cfiId = cfiId;
    }

    public Integer getCfId() {
        return cfId;
    }

    public void setCfId(Integer cfId) {
        this.cfId = cfId;
    }

    public CondoFee getCondoFee() {
        return condoFee;
    }

    public void setCondoFee(CondoFee condoFee) {
        this.condoFee = condoFee;
    }

    public ICondoFeeItemService getCondoFeeItemService() {
        return condoFeeItemService;
    }

    public void setCondoFeeItemService(ICondoFeeItemService condoFeeItemService) {
        this.condoFeeItemService = condoFeeItemService;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getShowAttrs() {
        return showAttrs;
    }

    public void setShowAttrs(String[] showAttrs) {
        this.showAttrs = showAttrs;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
