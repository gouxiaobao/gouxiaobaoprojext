package ntest.action.result;

public class PingResult {
	
	public boolean issuccess = false;
	public float ping_minTime = -2;
	public float ping_maxTime = -2;
	public float ping_avgTime = -2;
	public long ping_lossPacket = -2;

	@Override
	public String toString() {
		return "PingResult [ping_minTime=" + ping_minTime
				+ ", ping_maxTime=" + ping_maxTime
				+ ", ping_avgTime=" + ping_avgTime
				+ ", ping_lossPacket=" + ping_lossPacket + "]";
	}
}
