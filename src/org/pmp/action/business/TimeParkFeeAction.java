/**
 * Author            : Elan
 * Created On        : 2012-4-18 上午11:34:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.action.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.excel.TimeParkFeeImport;
import org.pmp.service.business.ITimeParkFeeService;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.vo.TimeParkFee;


import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class TimeParkFeeAction extends ActionSupport {

    private static final long serialVersionUID = -3583760463303202667L;
    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(TimeParkFeeAction.class.getName());
    //~ Instance Fields ================================================================================================
    private ITimeParkFeeService timeParkFeeService;
    
    private TimeParkFee timeParkFee;
    private Integer proId;
    private Integer currentPage=1;
    private Integer pageSize=10;
    
    //excelImport related variable
    private File tpfFile;
    private String tpfFileFileName;
    private String tpfFileContentType;
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public String addTimeParkFee(){
	//录入人员从seesion获得
	timeParkFee.setRecordPerson("elan");
	//录入时间为当前时间
	timeParkFee.setRecordTime(new Date());
	timeParkFee.setState("待审核");
	//关联物业项目从当前用户的用户组信息中获得
	//timeParkFee.setProject();
	
	timeParkFeeService.saveTimeParkFee(timeParkFee);
	
	return SUCCESS;
    }
    
    public String excelImport(){
	if(!MyfileUtil.validate(tpfFileFileName,"xls")){
	    String postfix = MyfileUtil.getPostfix(tpfFileFileName);
	    String message = postfix+"类型的文件暂不支持，请选择xls类型文件";
	    HttpServletRequest request = ServletActionContext.getRequest();
	    request.setAttribute("message", message);
	    return "filetype_error";
	}
	List<TimeParkFee> tpfList = null;
	try {
	    tpfList = TimeParkFeeImport.getTimeParkFeeList(new FileInputStream(tpfFile));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	//batch add the TimeParkFee
	timeParkFeeService.batchAdd(tpfList);
	
	return SUCCESS;
    }
    
    public String auditTimeParkFee(){
	TimeParkFee fee = timeParkFeeService.getTimeParkFeeByID(timeParkFee.getTpfId());
	//审核人员设置为从Session获取的用户
	fee.setAuditPerson("elan");
        //审核人员设置为当前时间
	fee.setAuditTime(new Date());
	//审核状态设置为"已审核"
	fee.setState("已审核");
	
	timeParkFeeService.editTimeParkFee(fee);
	
	return SUCCESS;
    }
    
    public void loadTimeParkFeeByProject(){
	Pager pager = new Pager(pageSize,currentPage);
	List<?> timeParkFeeList = timeParkFeeService.loadByProject(pager, proId);
	
	String sb = JsonConvert.list2Json(pager, timeParkFeeList, "org.pmp.vo.TimeParkFee");
        logger.debug(sb);
        JsonConvert.output(sb);
    }

    //~ Getters and Setters ============================================================================================

    public ITimeParkFeeService getTimeParkFeeService() {
        return timeParkFeeService;
    }

    public void setTimeParkFeeService(ITimeParkFeeService timeParkFeeService) {
        this.timeParkFeeService = timeParkFeeService;
    }

    public TimeParkFee getTimeParkFee() {
        return timeParkFee;
    }

    public void setTimeParkFee(TimeParkFee timeParkFee) {
        this.timeParkFee = timeParkFee;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public File getTpfFile() {
        return tpfFile;
    }

    public void setTpfFile(File tpfFile) {
        this.tpfFile = tpfFile;
    }

    public String getTpfFileFileName() {
        return tpfFileFileName;
    }

    public void setTpfFileFileName(String tpfFileFileName) {
        this.tpfFileFileName = tpfFileFileName;
    }

    public String getTpfFileContentType() {
        return tpfFileContentType;
    }

    public void setTpfFileContentType(String tpfFileContentType) {
        this.tpfFileContentType = tpfFileContentType;
    }
    
}
