/**
 * Author            : Elan
 * Created On        : 2012-5-10 下午05:23:43
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.NewCondoFeeExport;
import org.pmp.excel.NewCondoFeeImport;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.service.business.ICondoFeeService;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.CondoFee;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeAction2 extends ActionSupport{
    
    //~ Static Fields ==================================================================================================
    private static final long serialVersionUID = -7036535552079642151L;
    private static Logger logger = Logger.getLogger(CondoFeeAction2.class.getName());
    
    //~ Instance Fields ================================================================================================
    private ICondoFeeService condoFeeService;
    private ICondoFeeItemService condoFeeItemService;
    
    
    /* used when import new condoFee */
    private File cfFile;
    private String cfFileFileName;
    private String cfFileContentType;
    
    /* used when loadCondoFeeList_ByOwner */
    private Integer houseId;
    
    /* used when loadCondoFeeList_ByCFI */
    private Integer cfiId;
    
    /* used when loadCondoFeeList_ByCompany or loadCondoFeeList_ByProject */
    private Integer year;
    private Integer month;
    private Integer comId;
    private Integer proId;
    
    /* used when selectCondoFee or cfInput or cfEdit or cfAudit */
    private Integer[] ids;
    
    /* used when selectCondoFee */
    private String action;
    
    /* used when cfInput */
    private Double[] fetchMoney;
    
    /* used when cfEdit */
    private Double[] oughtMoney;
    
    /* used when cfAudit */
    private String[] state;
    
    //~ Methods ========================================================================================================
    public String loadCondoFeeList_ByCFI(){
	Pager pager = new Pager(1000,1);
	Map<String,Object> params = new HashMap<String,Object>();
	String order = "order by house asc, cfMonth desc";
	List<?> cfList = condoFeeService.loadCondoFeeList_ByCFI(cfiId, params, order, pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("cfList", cfList);
	
	return SUCCESS;
    }
    
    public String loadCondoFeeList_ByHouse(){
	Map<String,Object> params = new HashMap<String,Object>();
	String order = "order by cfYear desc,cfMonth desc";
	Pager pager = new Pager(1000,1);
	List<?> cfList= condoFeeService.loadCondoFeeList_ByHouse(houseId, params, order, pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("cfList", cfList);
	
	return SUCCESS;
    }
    
    public String loadCondoFeeList_ByCompany(){
	Map<String,Object> params = new HashMap<String,Object>();
	if(year!=null)params.put("cfYear", year);
	if(month!=null)params.put("cfMonth", month);
	String order = "order by house.houseId asc";
	Pager pager = new Pager(10000,1);
	List<?> cfList = condoFeeService.loadCondoFeeList_ByCompany(comId, params, order, pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("cfList", cfList);
	
	return SUCCESS;
    }
    
    public String loadCondoFeeList_ByProject(){
	Map<String,Object> params = new HashMap<String,Object>();
	if(year!=null)params.put("cfYear", year);
	if(month!=null)params.put("cfMonth", month);
	String order = "order by house.houseId asc";
	Pager pager = new Pager(10000,1);
	List<?> cfList = condoFeeService.loadCondoFeeList_ByProject(proId, params, order, pager);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("cfList", cfList);
	
	return SUCCESS;
    }
    
    public void exportNewCondoFee(){
	Pager pager = new Pager(1000,1);
	Map<String,Object> params = new HashMap<String,Object>();
	String order = "order by house asc";
	List<?> cfList = condoFeeService.loadCondoFeeList_ByCFI(cfiId, params, order, pager);
	
	HttpServletResponse response = ServletActionContext.getResponse();
	/* set ContentType of MIME response to browser */
	response.setContentType("application/vnd.ms-excel;charset=gb2312");
	/* set name of the output file */
	response.setHeader("Content-Disposition", "attachment;filename=newcondofeelist.xls");
	
	try {
	    NewCondoFeeExport.execute(response.getOutputStream(), cfList);
	} catch (IOException e) {
	    logger.error("get response outputStream failed");
	    e.printStackTrace();
	}
    }
    
    public String importNewCondoFee() throws IOException{
        HttpServletRequest request = ServletActionContext.getRequest();

	if(!MyfileUtil.validate(cfFileFileName,"xls")){
	    String postfix = MyfileUtil.getPostfix(cfFileFileName);
	    String message = postfix+"类型的文件暂不支持，请选择xls类型文件";
	    request.setAttribute("message", message);
	    return "filetype_error";
	}
	/* create the dir to store error data */
	MyfileUtil.createDir("error_data");
	/* create the error data file in this dir */
	String fileName = MyfileUtil.createFilename();
	String fullName = ServletActionContext.getServletContext().getRealPath("error_data")+"\\"+fileName+".xls";
	String downLoad = ServletActionContext.getServletContext().getContextPath()+"/error_data/"+fileName+".xls";
	OutputStream os = new FileOutputStream(fullName);
	
	/* import data from the upload file and store in the cfList */
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	Boolean hasError = NewCondoFeeImport.execute(new FileInputStream(cfFile), os, cfList);
	/* close OutputStream */
	os.flush();os.close();
	
	/* call the method batchSetOughtMoney to update the condoFee*/
	condoFeeService.batchSetOughtMoney(cfList);
	
	if (hasError){
	    request.setAttribute("message", "记录有错误,正确数据已导入，请下载错误数据<a href=\""+downLoad+"\">下载</a>");
	    return "conent_error";
	}
	
	return SUCCESS;
    }

    public String selectCondoFee(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	/* ------------ test begin -----------------------------*/
	int[] idss = {97,98,99,100};
	for (int i=0;i<idss.length;i++){
	    CondoFee cf = condoFeeService.getCondoFeeByID(idss[i]);
	    cfList.add(cf);
	}
	/* ------------ test end -----------------------------*/
	/*
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFeeByID(ids[i]);
	    cfList.add(cf);
	}
	*/
	logger.debug("cfList.size="+cfList.size());
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("cfList", cfList);
	
	if (action.equals("record")) return "record";
	if (action.equals("edit")) return "edit";
	if (action.equals("audit")) return "audit";
	
	return SUCCESS;
    }
    
    public String cfEdit(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFeeByID(ids[i]);
	    cf.setOughtMoney(oughtMoney[i]);
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchSetOughtMoney(cfList);
	
	return SUCCESS;
    }
    
    public String cfInput(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	logger.debug("fetchMoney="+fetchMoney.length);
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFeeByID(ids[i]);
	    cf.setRecordPerson(SessionHandler.getUser().getRealname());
	    cf.setInputTime(new Date());
	    cf.setState("payed");
	    cf.setFetchMoney(fetchMoney[i]);
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchInput(cfList);
	
	return SUCCESS;
    }
    
    public String cfAudit(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFeeByID(ids[i]);
	    cf.setAuditPerson(SessionHandler.getUser().getRealname());
	    cf.setAuditTime(new Date());
	    if (state[i].equals("pass")) cf.setState("pass");
	    else cf.setState("denied");
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchAudit(cfList);
	
	return SUCCESS;
    }
    
    //~ Getters and Setters ============================================================================================

    public ICondoFeeService getCondoFeeService() {
        return condoFeeService;
    }

    public void setCondoFeeService(ICondoFeeService condoFeeService) {
        this.condoFeeService = condoFeeService;
    }

    public ICondoFeeItemService getCondoFeeItemService() {
        return condoFeeItemService;
    }

    public void setCondoFeeItemService(ICondoFeeItemService condoFeeItemService) {
        this.condoFeeItemService = condoFeeItemService;
    }

    public Integer getCfiId() {
        return cfiId;
    }

    public void setCfiId(Integer cfiId) {
        this.cfiId = cfiId;
    }

    public File getCfFile() {
        return cfFile;
    }

    public void setCfFile(File cfFile) {
        this.cfFile = cfFile;
    }

    public String getCfFileFileName() {
        return cfFileFileName;
    }

    public void setCfFileFileName(String cfFileFileName) {
        this.cfFileFileName = cfFileFileName;
    }

    public String getCfFileContentType() {
        return cfFileContentType;
    }

    public void setCfFileContentType(String cfFileContentType) {
        this.cfFileContentType = cfFileContentType;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Double[] getOughtMoney() {
        return oughtMoney;
    }

    public void setOughtMoney(Double[] oughtMoney) {
        this.oughtMoney = oughtMoney;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Double[] getFetchMoney() {
        return fetchMoney;
    }

    public void setFetchMoney(Double[] fetchMoney) {
        this.fetchMoney = fetchMoney;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
