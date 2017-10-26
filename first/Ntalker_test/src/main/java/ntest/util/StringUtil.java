package ntest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**   
 * 工具类，提供一些方便的方法   
 */   
public class StringUtil {

	public static  String checkEmptyObject(Object param)
	{
		if(param==null)
			return "";
		return param.toString().trim();
	}
	public static boolean isEmpty(String str){
		if(str == null || str.length()<=0){
			return true;
		}
		return false;
	}
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}
		return false;
	}
	public static boolean str2boolean(String str){
		boolean res = false;
		if(str == null || str.length()<=0){
			return res;
		}
		try {
			res = Boolean.parseBoolean(str);
		} catch (Exception e) {
			
		}
		return res;
	}
	public static Integer str2Integer(String str){
		Integer res = -1;
		if(str == null || str.length()<=0){
			return res;
		}
		try {
			res =Integer.valueOf(str);
		} catch (Exception e) {
			
		}
		return res;

	}
	public static  boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");	
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	} 
}
