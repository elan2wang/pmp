/**
 * Author            : Elan
 * Created On        : 2012-6-20 下午03:27:52
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.pmp.service.business.IBuilFeeRateService;
import org.pmp.service.business.IBuildingService;
import org.pmp.service.business.IElectricFeeItemService;
import org.pmp.service.business.IElectricFeeService;
import org.pmp.service.business.ILiftMeterItemService;
import org.pmp.service.business.IProMeterItemService;
import org.pmp.service.business.IProjectService;
import org.pmp.util.SessionHandler;
import org.pmp.vo.BuilFeeRate;
import org.pmp.vo.ElectricFeeItem;
import org.pmp.vo.LiftMeterItem;
import org.pmp.vo.ProMeterItem;
import org.pmp.vo.Project;

import com.opensymphony.xwork2.ActionSupport;


/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class ElectricFeeItemAction extends ActionSupport {

    //~ Static Fields ==================================================================================================
    private static Logger logger = Logger.getLogger(ElectricFeeItemAction.class.getName());
    
    //~ Instance Fields ================================================================================================
    private IElectricFeeItemService electricFeeItemService;
    private IElectricFeeService electricFeeService;
    private IProMeterItemService proMeterItemService;
    private ILiftMeterItemService liftMeterItemService;
    private IBuilFeeRateService builFeeRateService;
    private IBuildingService buildingService;
    private IProjectService projectService;
    
    private ElectricFeeItem electricFeeItem;
    private Integer proId;
    private Integer efiId;
    
    /* used when ef_item_add */
    private Integer[] builId;
    private String[] pmNum;
    private String[] pmBeginDegree;
    private String[] pmEndDegree;
    private String[] pmPrice;
    private String[] lmBeginDegree;
    private String[] lmEndDegree;
    private String[] lmPrice;
    private Integer[] bfrBuilId;
    private Integer[] beginFloor;
    private Integer[] endFloor;
    private String[] rate;
    /* ========================= */
    
    //~ Methods ========================================================================================================
    public String addElectricFeeItem(){
	List<ProMeterItem> pmiList = new ArrayList<ProMeterItem>();
	List<LiftMeterItem> lmiList = new ArrayList<LiftMeterItem>();
	List<BuilFeeRate> bfrList = new ArrayList<BuilFeeRate>();
	//创建并保存电费项目对象
	electricFeeItem.setGeneratePerson(SessionHandler.getUser().getUsername());
	electricFeeItem.setGenerateTime(new Date());
	Project pro = projectService.getProjectByID(proId);
	electricFeeItem.setProject(projectService.getProjectByID(proId));
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String beginDate = formatter.format(electricFeeItem.getBeginDate());
	String endDate = formatter.format(electricFeeItem.getEndDate());
	electricFeeItem.setItemName(pro.getProName()+beginDate+"-"+endDate+"的公摊电费");
	electricFeeItemService.addElectricFeeItem(electricFeeItem);
	
	Double beginDegree = 0.0;
	Double endDegree = 0.0;
	Double price = 0.0;
	//创建并保存小区电表记录对象
	for(int i=0;i<pmNum.length;i++){
	    ProMeterItem pmi = new ProMeterItem();
	    beginDegree = Double.parseDouble(pmBeginDegree[i].trim());
	    endDegree = Double.parseDouble(pmEndDegree[i].trim());
	    price = Double.parseDouble(pmPrice[i].trim());
	    pmi.setBeginDegree(beginDegree);
	    pmi.setEndDegree(endDegree);
	    pmi.setPrice(price);
	    pmi.setPmNum(pmNum[i].trim());
	    pmi.setElectricFeeItem(electricFeeItem);
	    pmi.setTotalMoney((endDegree-beginDegree)*price);
	    pmiList.add(pmi);
	}
	proMeterItemService.batchAddProMeterItem(pmiList);
	
	//创建并保存电梯电表记录对象
	if(builId!=null&&builId.length!=0){
	    for(int i=0;i<builId.length;i++){
                LiftMeterItem lmi = new LiftMeterItem();
                lmi.setBuilding(buildingService.getBuildingById(builId[i]));
                beginDegree = Double.parseDouble(lmBeginDegree[i].trim());
                endDegree = Double.parseDouble(lmEndDegree[i].trim());
                price = Double.parseDouble(lmPrice[i].trim());
                lmi.setBeginDegree(beginDegree);
                lmi.setEndDegree(endDegree);
                lmi.setPrice(price);
                lmi.setTotalMoney((endDegree-beginDegree)*price);
                lmi.setElectricFeeItem(electricFeeItem);
                lmiList.add(lmi);
            }
            liftMeterItemService.batchAddLiftMeterItem(lmiList);
	}
	
	//创建并保存楼层段收费倍率对象
	if(bfrBuilId!=null && bfrBuilId.length!=0){
	    for(int i=0;i<bfrBuilId.length;i++){
                BuilFeeRate bfr = new BuilFeeRate();
                bfr.setBeginFloor(beginFloor[i]);
                bfr.setEndFloor(endFloor[i]);
                bfr.setRate(Double.parseDouble(rate[i].trim()));
                bfr.setBuilding(buildingService.getBuildingById(bfrBuilId[i]));
                bfr.setElectricFeeItem(electricFeeItem);
                bfrList.add(bfr);
	    }
            builFeeRateService.batchAddBuilFeeRate(bfrList);
	}
	
	//生成电费清单
	electricFeeService.generateElectricFee(electricFeeItem.getEfiId());
	
	return SUCCESS;
    }
    
    public void deleteElectricFeeItem(){
	electricFeeItemService.deleteElectricFeeItem(electricFeeItemService.getElectricFeeItemByID(efiId));
    }
    
    //~ Getters and Setters ============================================================================================

    public IElectricFeeItemService getElectricFeeItemService() {
        return electricFeeItemService;
    }

    public void setElectricFeeItemService(
    	IElectricFeeItemService electricFeeItemService) {
        this.electricFeeItemService = electricFeeItemService;
    }

    public IProMeterItemService getProMeterItemService() {
        return proMeterItemService;
    }

    public IElectricFeeService getElectricFeeService() {
        return electricFeeService;
    }
    public void setElectricFeeService(IElectricFeeService electricFeeService) {
        this.electricFeeService = electricFeeService;
    }
    public void setProMeterItemService(IProMeterItemService proMeterItemService) {
        this.proMeterItemService = proMeterItemService;
    }

    public ILiftMeterItemService getLiftMeterItemService() {
        return liftMeterItemService;
    }

    public void setLiftMeterItemService(ILiftMeterItemService liftMeterItemService) {
        this.liftMeterItemService = liftMeterItemService;
    }

    public IBuilFeeRateService getBuilFeeRateService() {
        return builFeeRateService;
    }

    public void setBuilFeeRateService(IBuilFeeRateService builFeeRateService) {
        this.builFeeRateService = builFeeRateService;
    }

    public IBuildingService getBuildingService() {
        return buildingService;
    }

    public void setBuildingService(IBuildingService buildingService) {
        this.buildingService = buildingService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }
    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }
    public Integer getProId() {
        return proId;
    }

    public Integer getEfiId() {
        return efiId;
    }

    public void setEfiId(Integer efiId) {
        this.efiId = efiId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }
    
    public Integer[] getBuilId() {
        return builId;
    }

    public void setBuilId(Integer[] builId) {
        this.builId = builId;
    }


    public String[] getPmNum() {
        return pmNum;
    }


    public void setPmNum(String[] pmNum) {
        this.pmNum = pmNum;
    }


    public ElectricFeeItem getElectricFeeItem() {
        return electricFeeItem;
    }


    public void setElectricFeeItem(ElectricFeeItem electricFeeItem) {
        this.electricFeeItem = electricFeeItem;
    }


    public String[] getPmBeginDegree() {
        return pmBeginDegree;
    }


    public void setPmBeginDegree(String[] pmBeginDegree) {
        this.pmBeginDegree = pmBeginDegree;
    }


    public String[] getPmEndDegree() {
        return pmEndDegree;
    }


    public void setPmEndDegree(String[] pmEndDegree) {
        this.pmEndDegree = pmEndDegree;
    }


    public String[] getPmPrice() {
        return pmPrice;
    }


    public void setPmPrice(String[] pmPrice) {
        this.pmPrice = pmPrice;
    }


    public String[] getLmBeginDegree() {
        return lmBeginDegree;
    }


    public void setLmBeginDegree(String[] lmBeginDegree) {
        this.lmBeginDegree = lmBeginDegree;
    }


    public String[] getLmEndDegree() {
        return lmEndDegree;
    }


    public void setLmEndDegree(String[] lmEndDegree) {
        this.lmEndDegree = lmEndDegree;
    }


    public String[] getLmPrice() {
        return lmPrice;
    }


    public void setLmPrice(String[] lmPrice) {
        this.lmPrice = lmPrice;
    }


    public Integer[] getBfrBuilId() {
        return bfrBuilId;
    }


    public void setBfrBuilId(Integer[] bfrBuilId) {
        this.bfrBuilId = bfrBuilId;
    }


    public Integer[] getBeginFloor() {
        return beginFloor;
    }


    public void setBeginFloor(Integer[] beginFloor) {
        this.beginFloor = beginFloor;
    }


    public Integer[] getEndFloor() {
        return endFloor;
    }


    public void setEndFloor(Integer[] endFloor) {
        this.endFloor = endFloor;
    }


    public String[] getRate() {
        return rate;
    }


    public void setRate(String[] rate) {
        this.rate = rate;
    }

}
