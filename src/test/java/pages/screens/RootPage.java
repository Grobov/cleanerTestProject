package pages.screens;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static utils.WindowHandle.getProcessId;

public class RootPage {
    WindowsDriver driver;

    public RootPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Get top level windowHandleByProcessName")
    public String getTopLevelWindowHandleByProcessName(String fileName) {
        var pid = getProcessId(fileName);
        var window = driver.findElementByXPath("//*[@ProcessId=\"" + pid + "\"]");
        var topLevelWindowHandle = window.getAttribute("NativeWindowHandle");
        topLevelWindowHandle = "0x" + Integer.toHexString(Integer.parseInt(topLevelWindowHandle)).toUpperCase();
        return topLevelWindowHandle;
    }
}
