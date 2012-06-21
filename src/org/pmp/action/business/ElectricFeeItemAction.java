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

import org.apache.log4j.Logger;
import org.pmp.service.impl.business.ElectricFeeItemService;
import org.pmp.vo.BuilFeeRate;
import org.pmp.vo.ElectricFeeItem;
import org.pmp.vo.LiftMeterItem;
import org.pmp.vo.ProMeterItem;

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
    private ElectricFeeItemService electricFeeItemService;
    private ElectricFeeItem electricFeeItem;
    private Integer proId;
    private Integer[] builId;
    private Integer[] pmNum;
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
    
    //~ Methods ========================================================================================================
    public String addElectricFeeItem(){
	
	
	return SUCCESS;
    }
    //~ Getters and Setters ============================================================================================


    public ElectricFeeItemService getElectricFeeItemService() {
        return electricFeeItemService;
    }

    public void setElectricFeeItemService(
    	ElectricFeeItemService electricFeeItemService) {
        this.electricFeeItemService = electricFeeItemService;
    }

    public Integer getProId() {
        return proId;
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


    public Integer[] getPmNum() {
        return pmNum;
    }


    public void setPmNum(Integer[] pmNum) {
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
