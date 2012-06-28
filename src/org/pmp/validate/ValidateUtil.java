package org.pmp.validate;

import java.util.regex.Pattern;

public class ValidateUtil {

    public static boolean isValidEmail(String str) {
	String regex = "^(?:[a-zA-Z])(?:\\w{5,17})@(?:[a-zA-Z0-9]+)\\.(?:(com|cn|net))$";
	return match(regex, str.trim());
    }

    public static boolean isValidDate(String str){
	String regex = "^(?:(19|20)\\d{2})-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])$";
        return match(regex, str.trim());
    }

    public static boolean isYearMonth(String str){
	String regex = "^((19|20)[0-9]{2})-(0[1-9]|1[0-2])";
	return match(regex, str.trim());
    }
    
    public static boolean isValidInteger(String str){
	String regex = "\\d+";
	return match(regex, str.trim());
    }
   
    public static boolean isValidDouble(String str){
	String regex = "^\\d+\\.?\\d+$|^\\d{1}$";
	return match(regex, str.trim());
    }
    
    public static boolean isNotNullString(String str){
    	String regex = "^.+$";
    	return match(regex, str.trim());
    }
    
    public static boolean isNullString(String str){
    	String regex = "^$";
    	return match(regex, str.trim());
    }
    
    public static boolean isValidIPAddress(String str){
    	String num = "(25[0-5]|2[0-4]//d|[0-1]//d{2}|[1-9]?//d)";
    	String regex = "^" + num + "//." + num + "//." + num + "//." + num + "$";
        return match(regex, str.trim());
    }
    
    public static boolean isBoolean(String str){
        String regex = "^(?:[Tt][Rr][Uu][Ee])|(?:[Ff][Aa][Ll][Ss][Ee])$";
        return match(regex, str.trim());
    }
	
    public static boolean isNotBoolean(String str){
        String regex = "^(?:[Tt][Rr][Uu][Ee])|(?:[Ff][Aa][Ll][Ss][Ee])$";
        return !match(regex, str.trim());
    }
    
    private static boolean match(String regex, String str) {
        boolean b = Pattern.matches(regex, str);
        return b;
    }
	
}
