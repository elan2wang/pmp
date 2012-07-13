/**
 * Author            : Elan
 * Created On        : 2012-6-22 上午11:19:16
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
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.MyJson;
import org.pmp.service.business.IOwnerRepairService;
import org.pmp.service.business.IRepairAttachService;
import org.pmp.util.MyfileUtil;
import org.pmp.util.SessionHandler;
import org.pmp.vo.RepairAttach;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class RepairAttachAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(RepairAttachAction.class
	    .getName());
    //~ Instance Fields ================================================================================================
    private IRepairAttachService repairAttachService;
    private IOwnerRepairService ownerRepairService;
    
    private Integer opId;
    private Integer raId;
    
    /* used when upload file */
    private File raFile;
    private String raFileFileName;
    private String raFileContentType;
     
    //~ Methods ========================================================================================================
    public void addRepairAttach() throws IOException{
        String message = null;
        MyJson json = new MyJson();
        String data = null;
        Map<String, Object> params = new LinkedHashMap<String, Object>();
	if(!MyfileUtil.validate(raFileFileName)){
	    logger.debug("文件格式不对");
	    String postfix = MyfileUtil.getPostfix(raFileFileName);
	    message = postfix+"类型的文件暂不支持";
	    params.put("result", "error");
	    params.put("message", message);
	    data = json.toJson(params);
	    json.output(data);
	    return;
	}
	String attachUrl;
	if(raFile!=null){
	    attachUrl = MyfileUtil.fileUpload(raFile, raFileFileName, "repairAttach");
	} else {
	    attachUrl = "";
	}
	logger.debug(attachUrl);
	
	RepairAttach ra = new RepairAttach();
	ra.setAttachName(raFileFileName);
	ra.setAttachUrl(attachUrl);
	ra.setUploadPerson(SessionHandler.getUser().getRealname());
	ra.setUploadTime(new Date());
	ra.setOwnerRepair(ownerRepairService.getOwnerRepair_ByID(opId));
	repairAttachService.addRepairAttach(ra);
	
	message="附件上传成功";
	params.put("result", "success");
	params.put("message", message);
	data = json.toJson(params);
	json.output(data);
	return;
    }
    
    public void deleteRepairAttach(){
	repairAttachService.deleteRepairAttach(repairAttachService.getRepairAttach_ByID(raId));
	Map<String,String> result = new HashMap<String, String>();
	MyJson json = new MyJson();
	result.put("msg", "附件删除成功");
	json.output(json.toJson(result));
    }
    
    public String loadRepairAttachList(){
	List<RepairAttach> raList = repairAttachService.loadRepairAttachList_ByOP(opId);
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("opId", opId);
	request.setAttribute("raList", raList);
	return SUCCESS;
    }
    
    //~ Getters and Setters ============================================================================================
    public IRepairAttachService getRepairAttachService() {
        return repairAttachService;
    }

    public void setRepairAttachService(IRepairAttachService repairAttachService) {
        this.repairAttachService = repairAttachService;
    }

    public IOwnerRepairService getOwnerRepairService() {
        return ownerRepairService;
    }

    public void setOwnerRepairService(IOwnerRepairService ownerRepairService) {
        this.ownerRepairService = ownerRepairService;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Integer getRaId() {
        return raId;
    }

    public void setRaId(Integer raId) {
        this.raId = raId;
    }

    public File getRaFile() {
        return raFile;
    }

    public void setRaFile(File raFile) {
        this.raFile = raFile;
    }

    public String getRaFileFileName() {
        return raFileFileName;
    }

    public void setRaFileFileName(String raFileFileName) {
        this.raFileFileName = raFileFileName;
    }

    public String getRaFileContentType() {
        return raFileContentType;
    }

    public void setRaFileContentType(String raFileContentType) {
        this.raFileContentType = raFileContentType;
    }
}
