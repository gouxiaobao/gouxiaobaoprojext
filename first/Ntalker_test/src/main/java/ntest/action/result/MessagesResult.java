package ntest.action.result;

/**
 * Created by guo on 2017/3/27.
 */
public class MessagesResult {
    public String KF_sendMessages ;
    public String KF_recvMessages ;

    public String WEB_sendMessages;
    public String WEB_recvMessages;

    public FileTestResult fileTestResult =null;

    public String getKF_sendMessages() {
        return this.KF_sendMessages;
    }

    public void setKF_sendMessages(String KF_sendMessages) {
        this.KF_sendMessages = KF_sendMessages;
    }

    public String getKF_recvMessages() {
        return this.KF_recvMessages;
    }

    public void setKF_recvMessages(String KF_recvMessages) {
        this.KF_recvMessages = KF_recvMessages;
    }

    public String getWEB_sendMessages() {
        return this.WEB_sendMessages;
    }

    public void setWEB_sendMessages(String WEB_sendMessages) {
        this.WEB_sendMessages = WEB_sendMessages;
    }

    public String getWEB_recvMessages() {
        return this.WEB_recvMessages;
    }

    public void setWEB_recvMessages(String WEB_recvMessages) {
        this.WEB_recvMessages = WEB_recvMessages;
    }

    public FileTestResult getFileTestResult() {
        return this.fileTestResult;
    }

    public void setFileTestResult(FileTestResult fileTestResult) {
        this.fileTestResult = fileTestResult;
    }
}
