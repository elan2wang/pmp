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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.NewCondoFeeExport;
import org.pmp.excel.NewCondoFeeImport;
import org.pmp.jms.JmsPublisher;
import org.pmp.json.Includer;
import org.pmp.json.MyJson;
import org.pmp.service.business.ICondoFeeItemService;
import org.pmp.service.business.ICondoFeeService;
import org.pmp.service.business.IHouseOwnerService;
import org.pmp.service.business.ISmsSendService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.CondoFee;
import org.pmp.vo.Owner;
import org.pmp.vo.SMSSend;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class CondoFeeAction extends ActionSupport{
    
    //~ Static Fields ==================================================================================================
    private static final long serialVersionUID = -7036535552079642151L;
    private static Logger logger = Logger.getLogger(CondoFeeAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private ICondoFeeService condoFeeService;
    private ICondoFeeItemService condoFeeItemService;
    private ISmsSendService smsSendService;
    private IHouseOwnerService houseOwnerService;
    
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
    
    /* used when cfInput or cfEdit or cfAudit */
    private Integer[] ids;
    
    /* used when selectCondoFee or validate */
    private String action;
    private String idStr;
    
    /* used when cfInput */
    private Double[] fetchMoney;
    private String[] comment;
    
    /* used when cfEdit */
    private Double[] oughtMoney;
    
    /* used when cfAudit */
    private String[] state;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    private String sortname;
    private String sortorder;
    private String query;
    private String qtype;
    /* =========FlexiGrid post parameters======= */
    
    //~ Methods ========================================================================================================
    
    public void loadCondoFeeList_ByCFI(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by house asc, cfMonth desc";
	}
	/* invoke service to get list */
	List<?> cfList = condoFeeService.loadCondoFeeList_ByCFI(cfiId, params, order, pager);
	
	/* transfer list to JsonData */
	String[] attrs = {"cfId","house.houseNum","owner.ownerName","cfYear","cfMonth","state","oughtMoney",
		          "fetchMoney","recordPerson","inputTime","comment"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(cfList, "", pager);
	logger.debug(data);
	json.output(data);
    }
    
    public void loadCondoFeeList_ByHouse(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by cfYear desc,cfMonth desc";
	}
	/* invoke service to get list */
	List<?> cfList= condoFeeService.loadCondoFeeList_ByHouse(houseId, params, order, pager);
	
	String[] attrs = {"cfId","cfYear","cfMonth","state","oughtMoney","fetchMoney","inputTime","comment"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	Owner owner = houseOwnerService.getHouseOwner_ByHouse(houseId).getOwner();
	String contact = null;
	if (owner.getMobile()!=null && owner.getHomePhone()!=null){
	    contact = owner.getMobile()+"/"+owner.getHomePhone();
	} else if (owner.getMobile()!=null && owner.getHomePhone()==null){
	    contact = owner.getMobile();
	} else if (owner.getMobile()==null && owner.getHomePhone()!=null){
	    contact = owner.getHomePhone();
	} else {
	    contact = "无";
	}
	String title = "业主姓名："+owner.getOwnerName()+"  联系电话："+contact;
	String data = json.toJson(cfList, title, pager);
	json.output(data);
    }
    
    public void loadCondoFeeList_ByCompany(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by house.houseId asc,cfMonth desc";
	}
	if(year!=null)params.put("cfYear", year);
	if(month!=null)params.put("cfMonth", month);
	/* invoke service to get list */
	List<?> cfList = condoFeeService.loadCondoFeeList_ByCompany(comId, params, order, pager);
	/* get the statistical data */
	String message = count(cfList);
	
	String[] attrs = {"cfId","condoFeeItem.project.proName","house.houseNum","owner.ownerName","cfMonth",
		"state","oughtMoney","fetchMoney","inputTime","comment"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(cfList, message, pager);

	json.output(data);
    }
    
    public void loadCondoFeeList_ByProject(){
	/* set query parameters */
	Pager pager = new Pager(rp,page);
	String order = null;
	Map<String,Object> params = new HashMap<String,Object>();
	if (!qtype.equals("")&&!query.equals("")){
	    params.put(qtype, query);
	}
	if (!sortname.equals("undefined")&&!sortorder.equals("undefined")){
	    order= "order by "+sortname+" "+sortorder;
	} else{
	    order = "order by house.houseId asc,cfMonth desc";
	}
	if(year!=null)params.put("cfYear", year);
	if(month!=null)params.put("cfMonth", month);
	/* invoke service to get list */
	List<?> cfList = condoFeeService.loadCondoFeeList_ByProject(proId, params, order, pager);
	/* get the statistical data */
	String message = count(cfList);
	
	String[] attrs = {"cfId","condoFeeItem.project.proName","house.houseNum","owner.ownerName",
		"state","oughtMoney","fetchMoney","cfMonth","inputTime","comment"};
	List<String> show = Arrays.asList(attrs);
	Includer includer = new Includer(show);
	MyJson json = new MyJson(includer);
	
	String data = json.toJson(cfList, message, pager);

	json.output(data);
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
    
    public void importNewCondoFee() throws IOException{
        HttpServletRequest request = ServletActionContext.getRequest();
        String message = null;
	if(!MyfileUtil.validate(cfFileFileName,"xls")){
	    logger.debug("文件格式不对");
	    String postfix = MyfileUtil.getPostfix(cfFileFileName);
	    message = postfix+"类型的文件暂不支持，请选择xls类型文件";
	    request.setAttribute("message", message);
	    JsonConvert.output("{\"error\":\"filetype_error\",\"msg\":"+JsonConvert.toJson(message)+"}");
	    return;
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
	
	/* if there are some mistakes of the file */
	if (hasError){
	    message = "记录有错误,正确数据已导入，请下载错误数据<a href=\""+downLoad+"\">下载</a>";
	    JsonConvert.output("{\"error\":\"record_error\",\"msg\":"+JsonConvert.toJson(message)+"}");
	    return;
	}
	
	/* data import success */
	message = "数据导入成功";
	JsonConvert.output("{\"error\":\"\",\"msg\":"+JsonConvert.toJson(message)+"}");
	return;
    }

    public String selectCondoFee(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    CondoFee cf = condoFeeService.getCondoFee_ById(Integer.parseInt(checkedID[i]));
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("cfList", cfList);
	
	if (action.equals("record")) return "record";
	if (action.equals("edit")) return "edit";
	if (action.equals("audit")) return "audit";
	
	return SUCCESS;
    }
    
    public void cfEdit(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFee_ById(ids[i]);
	    cf.setOughtMoney(oughtMoney[i]);
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchSetOughtMoney(cfList);
	
    }
    
    public void cfInput(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	logger.debug("fetchMoney="+fetchMoney.length);
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFee_ById(ids[i]);
	    cf.setRecordPerson(SessionHandler.getUser().getRealname());
	    cf.setInputTime(new Date());
	    cf.setState("payed");
	    cf.setFetchMoney(fetchMoney[i]);
	    cf.setComment(comment[i]);
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchInput(cfList);
	
    }
    
    public void cfAudit(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	for (int i=0;i<ids.length;i++){
	    CondoFee cf = condoFeeService.getCondoFee_ById(ids[i]);
	    cf.setAuditPerson(SessionHandler.getUser().getRealname());
	    cf.setAuditTime(new Date());
	    if (state[i].equals("pass")) cf.setState("pass");
	    else cf.setState("denied");
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchAudit(cfList);
	
    }
    
    public void cfDelete(){
	List<CondoFee> cfList = new ArrayList<CondoFee>();
	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    CondoFee cf = condoFeeService.getCondoFee_ById(Integer.parseInt(checkedID[i]));
	    cfList.add(cf);
	}
	logger.debug("cfList.size="+cfList.size());
	condoFeeService.batchDelete(cfList);
    }
    
    public void preCheck(){
	/* if action is deleteItem */
	if (action.equals("deleteItem")){
	    List<CondoFee> list = condoFeeService.loadCondoFeeList_ByCFI(cfiId, new HashMap<String,Object>(), "", new Pager(1000000,1));
	    Iterator<CondoFee> ite = list.iterator();
	    while(ite.hasNext()){
		CondoFee cf = ite.next();
		if(!(cf.getState().equals("new")||cf.getState().equals("input"))){
		    JsonConvert.output("{\"result\":\"failed\"}");
		    return;
		}
	    }
	    JsonConvert.output("{\"result\":\"success\"}");
	    return;
	}
	/* if action is not deleteItem */

	String[] checkedID = idStr.split(",");
	for (int i=0;i<checkedID.length;i++){
	    CondoFee cf = condoFeeService.getCondoFee_ById(Integer.parseInt(checkedID[i]));
	    if (action.equals("audit")){
		if(!cf.getState().equals("payed")){
		    logger.debug(cf.getFetchMoney());
		    JsonConvert.output("{\"result\":\"failed\"}");
                    return;
		}
	    }
	    if (action.equals("deleteList")||action.equals("edit")){
		if(!(cf.getState().equals("new")||cf.getState().equals("input"))){
		    JsonConvert.output("{\"result\":\"failed\"}");
		    return;
		}
	    }
	    if (action.equals("record")){
		if(!(cf.getState().equals("input")||cf.getState().equals("denied"))){
		    JsonConvert.output("{\"result\":\"failed\"}");
		    return;
		}
	    }
	}
	JsonConvert.output("{\"result\":\"success\"}");
	return;
    }
    
    public void smsInform(){
	Pager pager = new Pager(1000,1);
	Map<String,Object> params = new HashMap<String,Object>();
	params.put("state", "input");
	List<CondoFee> list = condoFeeService.loadCondoFeeList_ByHouse(houseId, params, "", pager);
	if (list.size()!=0){
	    /* create a SMSSend instance and set its properties */
	    SMSSend smsSend = new SMSSend();
	    smsSend.setSMSCompany(SessionHandler.getSMSCompany());
	    smsSend.setSmssPerson(SessionHandler.getUser().getUsername());
	    smsSend.setSmssTime(new Date());
	    smsSend.setSmssState("new");
	    smsSend.setSmssReceiver(list.get(0).getOwner().getMobile());
	    /* generate sms content */
	    StringBuilder sb = new StringBuilder();
	    sb.append("尊敬的业主："+list.get(0).getOwner().getOwnerName()+",您好！您尚有");
	    Iterator<CondoFee> ite = list.iterator();
	    Double totalMoney = 0.0;
	    while (ite.hasNext()){
		CondoFee cf = ite.next();
		totalMoney += cf.getOughtMoney();
		sb.append(cf.getCfYear()+"-"+cf.getCfMonth()+",");
	    }
	    sb.deleteCharAt(sb.length()-1);
	    sb.append("的物业费未缴纳，总计："+totalMoney+"元，如有疑问请及时联系我们！");
	    smsSend.setSmssContent(sb.toString());
	    
	    //save this instance
	    smsSendService.addSmsSend(smsSend);
	    
	    //send message to message queue
	    JmsPublisher.sendMessgae(smsSend.getSmssId().toString());
	}
	
    }
    
    private String count(List<?> cfList){
	StringBuilder result = new StringBuilder();
	Double oughtMoney = 0.0;
	Double fetchMoney = 0.0;
	Integer fetchItems = 0;
	Iterator<?> ite = cfList.iterator();
	while(ite.hasNext()){
	    CondoFee cf = (CondoFee)ite.next();
	    if (cf.getOughtMoney()!=null){
		oughtMoney += cf.getOughtMoney();
	    }
	    if (cf.getFetchMoney()!=null){
		fetchMoney += cf.getFetchMoney();
		fetchItems += 1;
	    }
	}
	result.append("   总计:"+cfList.size()+" 项        |   已收:"+fetchItems+" 项        |   应收:"+oughtMoney+" 元        |   实收:"+fetchMoney+" 元");
	return result.toString();
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

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String[] getComment() {
        return comment;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
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

    public ISmsSendService getSmsSendService() {
        return smsSendService;
    }

    public void setSmsSendService(ISmsSendService smsSendService) {
        this.smsSendService = smsSendService;
    }

    public IHouseOwnerService getHouseOwnerService() {
        return houseOwnerService;
    }

    public void setHouseOwnerService(IHouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }


}
