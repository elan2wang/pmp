/**
 * Author            : Jason
 * Created On        : 2012-4-28 上午11:22:40
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.validate;

import java.util.List;

/**
 * @author Jason
 * @version 1.0
 * @update TODO
 */
public class BuildingValidate {
	public static boolean dateValidate(List list){
		if(!((String)list.get(0)).equals("")){
			try{
				Integer.parseInt((String)list.get(0));
			}catch(RuntimeException e){
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		if(((String)list.get(1)).equals("")){
			return false;
		}
		if(((String)list.get(2)).equals("")){
			return false;
		}
		if(!((String)list.get(3)).equals("")){
			try{
				Integer.parseInt((String)list.get(3));
			}catch(RuntimeException e){
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		if(((String)list.get(4)).equals("")){
			return false;
		}
		if(!((String)list.get(5)).equals("")){
			try{
				Integer.parseInt((String)list.get(5));
			}catch(RuntimeException e){
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		if(!((String)list.get(6)).equals("")){
			try{
				Integer.parseInt((String)list.get(6));
			}catch(RuntimeException e){
				System.out.println(e);
				return false;
			}
		}else{
			return false;
		}
		if(((String)list.get(7)).equals("")){
			return false;
		}
		if(!(((String)list.get(9)).equals("是")||((String)list.get(9)).equals("否"))){
			return false;
		}
		if(!((String)list.get(10)).equals("")){
			try{
				Integer.parseInt((String)list.get(10));
			}catch(RuntimeException e){
				System.out.println(e);
				return false;
			}
		}
		return true;
	}
}
