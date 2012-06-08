/**
 * Author            : Elan
 * Created On        : 2012-4-28 下午12:07:40
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
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class OwnerValidate {
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
		if(((String)list.get(3)).equals("")){
			return false;
		}
		if(!(((String)list.get(4)).equals("男")||((String)list.get(4)).equals("女"))){
			return false;
		}
		if(!((String)list.get(7)).equals("")){
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	            Date date;
					try {
						date = format.parse((String)list.get(7));
					} catch (ParseException e) {
						e.printStackTrace();
						return false;
					}
		}else{
			return false;
		}
		if(!(((String)list.get(8)).equals("是")||((String)list.get(8)).equals("否"))){
			return false;
		}
		if(((String)list.get(11)).equals("")){
			return false;
		}
		if(!((String)list.get(12)).equals("")){
			java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("[0-9]*");
	        java.util.regex.Matcher match=pattern.matcher((String)list.get(12));
	        if(!match.matches()){
	        	return false;
	        }
		}else{
			return false;
		}
		if(!((String)list.get(14)).equals("")){
			java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("[0-9]*");
	        java.util.regex.Matcher match=pattern.matcher((String)list.get(14));
	        if(!match.matches()){
	        	return false;
	        }
		}else{
			return false;
		}
		if(!((String)list.get(15)).equals("")){
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date;
				try {
					date = format.parse((String)list.get(15));
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
	}else{
		return false;
	}
		if(!((String)list.get(17)).equals("")){
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date;
				try {
					date = format.parse((String)list.get(17));
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
	}else{
		return false;
	}
		if(!((String)list.get(22)).equals("")){
			try{
				Integer.parseInt((String)list.get(22));
			}catch(RuntimeException e){
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
}
