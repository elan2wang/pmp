/**
 * Author            : Jason
 * Created On        : 2012-4-27 下午03:09:30
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class ProjectValidate {
	public static boolean dateValidate(List list){
		if(((String)list.get(0)).equals("")){
			return false;
		}
		if(((String)list.get(1)).equals("")){
			return false;
		}
		if(((String)list.get(2)).equals("")){
			return false;
		}
		if(((String)list.get(3)).equals("")){
			return false;
		}
		if(((String)list.get(4)).equals("")){
			return false;
		}
		if(((String)list.get(5)).equals("")){
			return false;
		}
		if(((String)list.get(6)).equals("")){
			return false;
		}else{
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date;
				try {
					date = format.parse((String)list.get(6));
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
		}
		if(((String)list.get(7)).equals("")){
			return false;
		}else{
			try{
				Integer.parseInt((String)list.get(7));
			}catch(RuntimeException e){
				e.printStackTrace();
				return false;
			}
		}
		if(!(((String)list.get(8)).equals("是")||((String)list.get(8)).equals("否"))){
			return false;
		}
		if(!(((String)list.get(9)).equals("是")||((String)list.get(9)).equals("否"))){
			return false;
		}
		return true;
	}
}
