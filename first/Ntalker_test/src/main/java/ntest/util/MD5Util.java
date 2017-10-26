package ntest.util;


import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {

	private static final Logger log = LoggerFactory.getLogger(MD5Util.class);
	public static String encode(String value){
		String hs = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte[] ctext = md.digest();
			String stmp = "";
			for (int n = 0; n < ctext.length; n++) {
				stmp = (Integer.toHexString(ctext[n] & 0xff));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
		} catch (Exception e) {
			log.debug("MD5 error ! Exception : {}", e.getMessage());
		}
		return hs;
	}
}
