package ntest.util;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ntest.action.result.PingResult;

public class Statics {

	protected static Logger log = LoggerFactory.getLogger(Statics.class
			.getName());

	public static boolean isNullOrEmpty(String param) {

		if (param == null || param.isEmpty())
			return true;

		return false;
	}

	public static String getConfigRoot() {
		String root = null;
		if (root == null) {
			root = System.getProperty("user.dir");
		}
		if (root != null) {
			if (File.separatorChar != '/') {
				root = root.replaceAll("\\\\", "/");
			}
			if (root.charAt(root.length() - 1) == '/') {
				root = root.substring(0, root.length() - 1);
			}

			root = root + "/conf/monitor";

			log.warn("root_config: %s\n" + root);
		}

		return root;
	}

	/**
	 * 找到rtmp协议serverurl
	 * 
	 * @return
	 */
	public static String paraseServerUrlForRtmp(String serverurl) {
		if (StringUtil.isEmpty(serverurl)) {
			return serverurl;
		}
		String[] protocalarray = serverurl.split(";");
		if (protocalarray.length <= 0) {
			return serverurl;
		}
		for (String protocalitem : protocalarray) {
			if (StringUtil.isEmpty(protocalitem)) {
				continue;
			}

			if (protocalitem.startsWith("rtmp://")) {
				return protocalitem;
			}

		}
		return serverurl;
	}

	/**
	 * win环境 往返的最小时间
	 * 
	 * @param pingresult
	 * @return
	 */
	public static float paraseMinTimeForWin(String pingresult) {
		float mintime = 0;

		try {

			int s2 = pingresult.indexOf("最短");
			if (s2 == -1) {
				return mintime;
			}
			String avestr = null;
			int avg1 = pingresult.indexOf("=", s2);
			if (avg1 == -1) {
				return mintime;
			}
			int avg2 = pingresult.indexOf("ms", avg1);
			if (avg2 == -1) {
				return mintime;
			}
			avestr = pingresult.substring(avg1 + 1, avg2);
			if (avestr != null) {
				avestr = avestr.replace("ms", "");
			}
			if (avestr != null) {
				mintime = Float.parseFloat(avestr.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return mintime;
	}

	/**
	 * win环境 往返的最大时间
	 * 
	 * @param pingresult
	 * @return
	 */
	public static float paraseMaxTimeForWin(String pingresult) {
		float mintime = 0;

		try {

			int s2 = pingresult.indexOf("最长");
			if (s2 == -1) {
				return mintime;
			}
			String avestr = null;
			int avg1 = pingresult.indexOf("=", s2);
			if (avg1 == -1) {
				return mintime;
			}
			int avg2 = pingresult.indexOf("ms", avg1);
			if (avg2 == -1) {
				return mintime;
			}
			avestr = pingresult.substring(avg1 + 1, avg2);
			if (avestr != null) {
				avestr = avestr.replace("ms", "");
			}
			if (avestr != null) {
				mintime = Float.parseFloat(avestr.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return mintime;
	}

	/**
	 * linux环境 往返的最大时间
	 * 
	 * @param pingresult
	 * @return
	 */
	public static float paraseMaxTimeForLinux(String pingresult) {
		float maxtime = 0;

		try {

			// rtt min/avg/max/mdev = 0.042/0.052/0.085/0.016 ms
			int s2 = pingresult.indexOf("rtt");
			if (s2 == -1) {
				return maxtime;
			}
			int end = pingresult.lastIndexOf("/");
			if (end == -1) {
				return maxtime;
			}

			int start = pingresult.lastIndexOf("/", end - 1);
			if (start == -1) {
				return maxtime;
			}

			String avgstr = pingresult.substring(start + 1, end);
			if (avgstr != null) {
				maxtime = Float.parseFloat(avgstr.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return maxtime;
	}

	/**
	 * linux环境 往返的最小时间
	 * 
	 * @param pingresult
	 * @return
	 */
	public static float paraseMinTimeForLinux(String pingresult) {
		float maxtime = -1;

		try {

			// rtt min/avg/max/mdev = 0.042/0.052/0.085/0.016 ms
			int s2 = pingresult.indexOf("rtt");
			if (s2 == -1) {
				return maxtime;
			}
			int end = pingresult.lastIndexOf("/");
			if (end == -1) {
				return maxtime;
			}

			int start = pingresult.lastIndexOf("/", end);
			if (start == -1) {
				return maxtime;
			}

			String avgstr = pingresult.substring(start + 1, end);
			if (avgstr != null) {
				maxtime = Float.parseFloat(avgstr.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return maxtime;
	}

	/**
	 * linux环境 往返的平均时间
	 * 
	 * @param pingresult
	 * @return
	 */
	public static float paraseAvgTimeForLinux(String pingresult) {
		float avgtime = 0;

		try {

			// rtt min/avg/max/mdev = 0.042/0.052/0.085/0.016 ms
			int s2 = pingresult.indexOf("rtt");
			if (s2 == -1) {
				return avgtime;
			}
			int index1 = pingresult.indexOf("=", s2);
			if (index1 == -1) {
				return avgtime;
			}

			int avg1 = pingresult.indexOf("/", index1);
			if (avg1 == -1) {
				return avgtime;
			}

			int avg2 = pingresult.indexOf("/", avg1 + 1);
			if (avg2 == -1) {
				return avgtime;
			}

			String avgstr = pingresult.substring(avg1 + 1, avg2);
			if (avgstr != null) {
				avgtime = Float.parseFloat(avgstr.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return avgtime;
	}

	/**
	 * win环境 往返的平均时间
	 * 
	 * @param pingresult
	 * @return
	 */
	public static float paraseAvgTimeForWin(String pingresult) {
		float avgtime = 0;

		try {

			int s2 = pingresult.indexOf("平均");
			if (s2 == -1) {
				return avgtime;
			}
			int strlen = pingresult.length();
			String avestr = null;
			int avg1 = pingresult.indexOf("=", s2);
			if (avg1 != -1) {
				avestr = pingresult.substring(avg1 + 1, strlen);
			}
			if (avestr != null) {
				avestr = avestr.replace("ms", "");
			}
			if (avestr != null) {
				avgtime = Float.parseFloat(avestr.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return avgtime;
	}

	/**
	 * win环境 计算丢失率
	 * 
	 * @param pingresult
	 * @return
	 */
	public static long paraseLossPacketForWin(String pingresult) {
		long lostpacket = 100;

		try {

			// 计算丢包率
			int s1 = pingresult.indexOf("丢失");
			if (s1 == -1) {
				return lostpacket;
			}

			int lost1 = pingresult.indexOf("%", s1);
			if (lost1 == -1) {
				return lostpacket;
			}

			String loststr1 = pingresult.substring(s1, lost1);
			if (loststr1 == null) {
				return lostpacket;
			}

			int lost2 = loststr1.lastIndexOf("(");
			if (lost2 == -1) {
				return lostpacket;
			}

			loststr1 = loststr1.substring(lost2 + 1, loststr1.length());

			if (loststr1 == null) {
				return lostpacket;
			}
			lostpacket = Integer.parseInt(loststr1.trim());

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return lostpacket;
	}

	/**
	 * linux环境 计算丢失率
	 * 
	 * @param pingresult
	 * @return
	 */
	public static long paraseLossPacketForLinux(String pingresult) {
		long lostpacket = 100;

		try {

			int s1 = pingresult.indexOf("loss");
			if (s1 == -1) {
				return lostpacket;
			}
			int lost1 = pingresult.lastIndexOf("%", s1);
			if (lost1 == -1) {
				return lostpacket;
			}
			int lost2 = pingresult.lastIndexOf(",", lost1);
			String loststr1 = pingresult.substring(lost2 + 1, lost1);
			if (loststr1 == null) {
				return lostpacket;
			}
			if (loststr1 != null) {
				lostpacket = Integer.parseInt(loststr1.trim());
			}

		} catch (Exception e) {
			log.error("Exception " + e.toString());
		}

		return lostpacket;
	}

	/**
	 * 88888888 ping 异常 99999999 丢包>=10%
	 * 
	 * @param pingresult
	 * @return
	 */
	public static void parasePingResult(String pingresult, PingResult pingResult) {
		if (IOUtils.isWinSystem()) {

			pingResult.ping_lossPacket = paraseLossPacketForWin(pingresult);
			pingResult.ping_avgTime = paraseAvgTimeForWin(pingresult);
			pingResult.ping_maxTime = paraseMaxTimeForWin(pingresult);
			pingResult.ping_minTime = paraseMinTimeForWin(pingresult);

			return;
		}

		pingResult.ping_lossPacket = paraseLossPacketForLinux(pingresult);
		pingResult.ping_avgTime = paraseAvgTimeForLinux(pingresult);
		pingResult.ping_maxTime = paraseMaxTimeForLinux(pingresult);
		pingResult.ping_minTime = paraseMinTimeForWin(pingresult);

		return;
	}

	public static String GetKFUserIdByUid(String sitid, String uid) {
		return sitid + "_ISME9754_T2D_" + uid;
	}

	public static boolean isWID(String id) {
		if (id == null || id.isEmpty()) {
			return false;
		}
		if (id.contains("_ISME9754_T2D_"))
			return true;
		return false;
	}

	public static boolean isEID(String id) {
		if (id == null || id.isEmpty()) {
			return false;
		}
		if (id.contains("_ISME9754_GT2D"))
			return true;
		return false;
	}

	public static boolean isVID(String id) {
		if (isWID(id) || isEID(id))
			return false;

		if (id.contains("_ISME9754_"))
			return true;

		return false;
	}

	public static String webTrailResult2String(long time)
	{
		if(time == 99999999)
		{
			return " PageTypeError";
		}
		if(time == 88888888)
		{
			return " IOError";
		}
		return String.valueOf(time);
	}
	public static String fileUploadResult2String(long time)
	{
		if(time == -1)
		{
			return " -1";
		}
		if(time==8888)
		{
			return "failed / 0";
		}
		return "success / "+time+" ms";
	}
	public static String fileDownloadResult2String(long time)
	{
		if(time == -1)
		{
			return " -1";
		}
		if(time==8888)
		{
			return "failed / 0";
		}
		return "success / "+time+" ms";
	}
	public static String pingResponseTime2String(float avgtime,float losspacket)
	{
		return losspacket+"% / "+avgtime+" ms";
	}
	
	public static String marketingStatisticsTimeToString(long time){
//		if(time>5000){
//			return "failed / " + time +"ms";
//		}
		return time +"ms";
	}
}
