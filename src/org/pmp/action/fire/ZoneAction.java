package org.pmp.action.fire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.ZoneView;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.pmp.dao.fire.IZoneDAO;
import org.pmp.service.business.IProjectService;
import org.pmp.service.fire.IZoneService;
import org.pmp.util.FileUploadUtil;
import org.pmp.util.JsonConvert;
import org.pmp.util.MyfileUtil;
import org.pmp.util.Pager;
import org.pmp.util.SessionHandler;
import org.pmp.vo.Company;
import org.pmp.vo.Owner;
import org.pmp.vo.Project;
import org.pmp.vo.Zone;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ZoneAction extends ActionSupport{

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ZoneAction.class.getName());
    //~ Instance Fields ================================================================================================
    private IZoneService zoneService;
    private IProjectService projectService;
    
    /* =========FlexiGrid post parameters======= */
    private Integer page=1;
    private Integer rp=15;
    
    private Integer zoneID;
    private String idStr; 
    
    private Zone zone;
    private Integer projectId;
    private String query;
    private String qtype;
    
    private File zoneImg;
    private String zoneImgFileName;
    private String zoneImgContentType;
    
    private File zoneConfig;
    private String zoneConfigFileName;
    private String zoneConfigContentType;
    
    //~ Methods ========================================================================================================
    
    public void loadZoneList(){
    	
    	 logger.info("###################场地信息管理!");
    	 
    	 Pager pager = new Pager(rp,page);
    	 Object obj=SessionHandler.getUserRefDomain();
    	 //aaa 项目管理员         bbb 公司管理员
    	 
		 Map<String, Object> params=new HashMap<String, Object>();
		 if (!qtype.equals("")&&!query.equals("")){
		       params.put(qtype, query);
		 }
		 
    	 if(obj instanceof Company){
    		 //公司管理员
    		 Company company=(Company)obj;
    		 logger.info("公司ID:###########"+company.getComId());
    		 
    		 try {
				 List<Project> pList=projectService.loadProjectList_ByCompany(company.getComId(), null, null, null);
				 List<Integer> proIdList=new ArrayList();
				 
				 for (Project pro : pList) {
					proIdList.add(pro.getProId());
				 }
				 
				 List<Zone> zoneList=zoneService.loadZoneListByProIdList(proIdList,params,null,pager);
				 
				 String[] attrs = {"zoneName","project","zoneType","zoneImgUrl","zoneConfigUrl","zoneDesc"};
				 
				 List<String> show = Arrays.asList(attrs);
				 
				 String data = JsonConvert.list2FlexJson(pager, zoneList, "org.pmp.vo.Zone", show);
				
				 logger.debug(data);
				 
				 JsonConvert.output(data);
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
    	 }
    	 
    	 else{
    		 //项目管理员
    		 Project project=(Project)obj;
    		 logger.info("项目ID:############"+project.getProId()); //项目ID
    		 
    		 List<Zone> zoneList=zoneService.loadZoneListByProId(project.getProId(),params,null,pager);
    		 
			 String[] attrs = {"zoneName","project","zoneType","zoneImgUrl","zoneConfigUrl","zoneDesc"};
			 
			 List<String> show = Arrays.asList(attrs);
			 
			 String data = JsonConvert.list2FlexJson(pager, zoneList, "org.pmp.vo.Zone", show);
			
			 logger.debug(data);
			 
			 JsonConvert.output(data);
    	 }
    	 
    }

    public String toZoneView() {
    	Integer zoneId=zone.getZoneId();
    	zone=zoneService.getZoneById(zoneId);
    	ActionContext context = ActionContext.getContext();
    	return SUCCESS;
    }
    
    public String addZone() throws IOException{
    	logger.info("###################添加场地信息!");    	
    	    	
		if(zoneImg!=null&&zoneImgFileName!=null){
			String zoneImgUrl=FileUploadUtil.fileUpload(zoneImg, zoneImgFileName, "fireConfig");
			zone.setZoneImgUrl(zoneImgUrl);
		}
    	
		if(zoneConfig!=null&&zoneConfigFileName!=null){
			String zoneConfigUrl=FileUploadUtil.fileUpload(zoneConfig, zoneConfigFileName, "fireConfig");
			zone.setZoneConfigUrl(zoneConfigUrl);
		}
		
    	Project project=new Project();
    	project=projectService.getProjectByID(projectId);
    	
    	zone.setProject(project);
    	
    	logger.info(zone);
    	
    	zoneService.saveZone(zone);
    	
    	return SUCCESS;
    }

    public String editZone(){
    	logger.info("修改场地信息!==ID:"+zoneID);
        zone=zoneService.getZoneById(zoneID);
    	return SUCCESS;
    }
    
    public void deleteZone(){
    	String dirPath = ServletActionContext.getServletContext().getRealPath("/");
    	try {
			String[] checkedID = idStr.split(",");
			for (int i = 0; i < checkedID.length; i++) {
				zone = zoneService.getZoneById(Integer.valueOf(checkedID[i]));

				MyfileUtil.deleteFileOrDirectory(dirPath+zone.getZoneImgUrl());
				MyfileUtil.deleteFileOrDirectory(dirPath+zone.getZoneConfigUrl());

				zoneService.deleteZone(zone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String updateZone() throws IOException{
    	String dirPath = ServletActionContext.getServletContext().getRealPath("/");
		if(zoneImg!=null&&zoneImgFileName!=null){
			String zoneImgUrl=FileUploadUtil.fileUpload(zoneImg, zoneImgFileName, "fireConfig");
			MyfileUtil.deleteFileOrDirectory(dirPath+zone.getZoneImgUrl());
			zone.setZoneImgUrl(zoneImgUrl);
		}
    	
		if(zoneConfig!=null&&zoneConfigFileName!=null){
			String zoneConfigUrl=FileUploadUtil.fileUpload(zoneConfig, zoneConfigFileName, "fireConfig");
			MyfileUtil.deleteFileOrDirectory(dirPath+zone.getZoneConfigUrl());
			zone.setZoneConfigUrl(zoneConfigUrl);
		}
		
    	Project project=new Project();
    	project=projectService.getProjectByID(projectId);
    	
    	zone.setProject(project);
    	
		System.out.println(zone);
		
		zoneService.updateZone(zone);
		
    	return SUCCESS;
    }
    
	public IZoneService getZoneService() {
		return zoneService;
	}



	public void setZoneService(IZoneService zoneService) {
		this.zoneService = zoneService;
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

	public File getZoneImg() {
		return zoneImg;
	}

	public void setZoneImg(File zoneImg) {
		this.zoneImg = zoneImg;
	}

	public String getZoneImgFileName() {
		return zoneImgFileName;
	}

	public void setZoneImgFileName(String zoneImgFileName) {
		this.zoneImgFileName = zoneImgFileName;
	}

	public String getZoneImgContentType() {
		return zoneImgContentType;
	}

	public void setZoneImgContentType(String zoneImgContentType) {
		this.zoneImgContentType = zoneImgContentType;
	}

	public File getZoneConfig() {
		return zoneConfig;
	}

	public void setZoneConfig(File zoneConfig) {
		this.zoneConfig = zoneConfig;
	}

	public String getZoneConfigFileName() {
		return zoneConfigFileName;
	}

	public void setZoneConfigFileName(String zoneConfigFileName) {
		this.zoneConfigFileName = zoneConfigFileName;
	}

	public String getZoneConfigContentType() {
		return zoneConfigContentType;
	}

	public void setZoneConfigContentType(String zoneConfigContentType) {
		this.zoneConfigContentType = zoneConfigContentType;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public Integer getZoneID() {
		return zoneID;
	}

	public void setZoneID(Integer zoneID) {
		this.zoneID = zoneID;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
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
}


