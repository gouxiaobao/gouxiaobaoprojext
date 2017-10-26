package ntest.util;


import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

	protected static Logger log = LoggerFactory.getLogger(IOUtils.class.getName());
	public static String stream2String(InputStream in,String encoding){
		
		if(in == null){
			log.debug("input stream is null");
			return null;
		}
		/*String charset = "utf-8";
		boolean encode = false;
		*/
		 StringBuffer   out   =   new   StringBuffer(); 
		 try {
			 
			 char[]   b   =   new   char[1024]; 
			  InputStreamReader inread = new InputStreamReader(in,encoding); 

		        for(int n; (n=inread.read(b))!= -1;)   
		        { 
		        	String line = new  String(b,0,n);
		           out.append(line); 
		        } 
		        
			
		} catch (Exception e) {
			log.debug("Exception "+e.getMessage());
		}
	
	      return out.toString();
	}

	public static boolean isWinSystem()
	{
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("vista") || os.contains("windows 7")) {
			return true;
		}
		return false;
	}
}
