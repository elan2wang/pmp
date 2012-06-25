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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.json.MyJson;
import org.pmp.service.business.IOwnerRepairService;
import org.pmp.service.business.IRepairAttachService;
import org.pmp.util.FileUploadUtil;
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
	if(!MyfileUtil.validate(raFileFileName)){
	    logger.debug("文件格式不对");
	    String postfix = MyfileUtil.getPostfix(raFileFileName);
	    message = postfix+"类型的文件暂不支持";
	    String data="{\"result\":\"error\",\"message\":\""+message+"\"}";
	    MyJson.print(data);
	    return;
	}
	MyfileUtil.createDir("repairAttach");
	String attachUrl;
	if(raFile!=null){
	    attachUrl = FileUploadUtil.fileUpload(raFile, raFileFileName, "repairAttach");
	} else {
	    attachUrl = "";
	}
	RepairAttach ra = new RepairAttach();
	ra.setAttachName(raFileFileName);
	ra.setAttachUrl(attachUrl);
	ra.setUploadPerson(SessionHandler.getUser().getRealname());
	ra.setUploadTime(new Date());
	ra.setOwnerRepair(ownerRepairService.getOwnerRepair_ByID(opId));
	repairAttachService.addRepairAttach(ra);
	message="附件上传成功";
	String data="{\"result\":\"success\",\"message\":\""+message+"\"}";
	logger.debug(data);
	MyJson.print(data);
	return;
    }
    
    public void deleteRepairAttach(){
	repairAttachService.deleteRepairAttach(repairAttachService.getRepairAttach_ByID(raId));
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
