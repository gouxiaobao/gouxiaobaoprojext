package ntest.util;

import java.security.MessageDigest;

public class MD5 {
	public static String encode(String text) {
		String hs = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
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
			e.printStackTrace();
		}
		return hs;
	}
}
