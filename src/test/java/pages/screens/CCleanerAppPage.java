package pages.screens;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class CCleanerAppPage {

    private static final String HEALTH_CHECK = "Health Check";
    private static final String CUSTOM_CLEAN = "Custom Clean";
    private static final String DRIVER_UPDATER = "Driver Updater";
    private static final String ATTRIBUTE_NAME = "Name";
    WindowsDriver driver;

    public CCleanerAppPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //wait until loaded
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Check Health Check")
    public void checkHealthCheck() {
        assertEquals(driver.findElementByName(HEALTH_CHECK).getAttribute(ATTRIBUTE_NAME), HEALTH_CHECK);
    }

    @Step("Go to Health Check")
    public void goToHealthCheck() {
        driver.findElementByName(HEALTH_CHECK).click();
    }

    @Step("Check Custom Clean")
    public void checkCustomClean() {
        assertEquals(driver.findElementByName(CUSTOM_CLEAN).getAttribute(ATTRIBUTE_NAME), CUSTOM_CLEAN);
    }

    @Step("Go to custom clean")
    public void goToCustomClean() {
        driver.findElementByName(CUSTOM_CLEAN).click();
    }

    @Step("Check driver updater")
    public void checkDriverUpdater() {
        assertEquals(driver.findElementByName(DRIVER_UPDATER).getAttribute(ATTRIBUTE_NAME), DRIVER_UPDATER);
    }

    @Step("Go to Driver Updater")
    public void goToDriverUpdater() {
        driver.findElementByName(DRIVER_UPDATER).click();
    }
}
