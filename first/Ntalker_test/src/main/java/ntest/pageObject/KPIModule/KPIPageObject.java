package ntest.pageObject.KPIModule;
import ntest.pageObject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KPIPageObject extends BasePageObject {
    @FindBy (xpath = ".//li[contains(text(),'详细数据')]")
    public WebElement detailLink;
    @FindBy (xpath= ".//a[contains(text(),'下载全部报表')]")
    public WebElement downloadBtn;
    public KPIPageObject (String url) {
        this.url = url;
        fetchPageObject(this);
    }
    public void downloadFile() throws Exception {
        detailLink.click();
        Thread.sleep(1000);
        downloadBtn.click();
        Thread.sleep(2000);
//        SeleniumUtil.downloadFile();
    }
}
