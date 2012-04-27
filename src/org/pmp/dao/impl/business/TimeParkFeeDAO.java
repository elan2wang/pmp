/**
 * Author            : Elan
 * Created On        : 2012-4-18 上午11:16:18
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.dao.impl.business;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pmp.dao.admin.BaseDAO;
import org.pmp.dao.business.ITimeParkFeeDAO;
import org.pmp.util.Pager;
import org.pmp.vo.TimeParkFee;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class TimeParkFeeDAO extends BaseDAO implements ITimeParkFeeDAO{

    //~ Static Fields ==================================================================================================
    static Logger logger = Logger.getLogger(TimeParkFeeDAO.class.getName());
    //~ Instance Fields ================================================================================================
   
    //~ Constructor ====================================================================================================

    //~ Methods ========================================================================================================
    public void saveTimeParkFee(TimeParkFee instance) {
	String debugMsg = "save TimeParkFee";
	try {
	    saveInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    }
    
    public void batchAdd(List<TimeParkFee> list) {
	Session session = getSession();
        logger.debug("begin to batch save TimeParkFee.");
        String hql = "insert into tb_TimeParkFee(Pro_ID,Car_Num,Park_Date,Park_Time,ParkFee_Rate," +
        	     "Fetch_Money,Fetch_Person,Record_Person,Record_Time,State) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            Transaction tx = session.beginTransaction();
            Connection conn = session.connection();
            PreparedStatement stmt = conn.prepareStatement(hql);
            Iterator<TimeParkFee> ite = list.iterator();
            while(ite.hasNext()){
        	TimeParkFee tpf = ite.next();
                stmt.setInt(1, tpf.getProject().getProId());
                stmt.setString(2, tpf.getCarNum());
                Date date1=new java.sql.Date(tpf.getParkDate().getTime());
                stmt.setDate(3, date1);
                stmt.setInt(4, tpf.getParkTime());
                stmt.setDouble(5, tpf.getParkFeeRate());
                stmt.setDouble(6, tpf.getFetchMoney());
                stmt.setString(7, tpf.getFetchPerson());
                stmt.setString(8, tpf.getRecordPerson());
                Date date2 = new java.sql.Date(tpf.getRecordTime().getTime());
                stmt.setDate(9, date2);
                stmt.setString(10, tpf.getState());
                stmt.executeUpdate();
            }
            tx.commit();
        } catch (SQLException e1){
            logger.error("batch save TimeParkFee failed",e1);
            e1.printStackTrace();
        } catch (RuntimeException e2){
            logger.error("batch save TimeParkFee failed",e2);
            e2.printStackTrace();
            throw e2;
        }
        logger.debug("batch save TimeParkFee successfully.");
        session.close();
    }

    public void updateTimeParkFee(TimeParkFee instance) {
	String debugMsg = "update TimeParkFee instance,tpfId="+instance.getTpfId();
	try {
	    updateInstance(instance,debugMsg);
	} catch(RuntimeException e){
	    throw e;
	}
    } 
    
    public void deleteTimeParkFee(Integer tpfId) {
	String debugMsg = "delete timeParkFee,tpfId="+tpfId;
	String hql = "delete from TimeParkFee where tpfId="+tpfId;
	try {
	    deleteInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
    }

    public TimeParkFee getTimeParkFeeByID(Integer tpfId){
	String debugMsg = "get TimeParkFee by ID,tpfId="+tpfId;
	String hql = "from TimeParkFee where tpfId="+tpfId;
	TimeParkFee fee = null;
	try {
	    fee = (TimeParkFee)getInstance(hql,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return fee;
    }
    
    public List loadByProject(Pager pager, Integer proId) {
	String debugMsg = "load timeParkFee List by Project ID,proId="+proId;
	String hql = "from TimeParkFee where project.proId="+proId;
	List timeParkFeeList = null;
	try {
	    timeParkFeeList = loadListByCondition(hql,pager,debugMsg);
	} catch (RuntimeException e){
	    throw e;
	}
	return timeParkFeeList;
    }

    //~ Getters and Setters ============================================================================================

}
