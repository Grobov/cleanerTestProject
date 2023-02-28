package tests;

import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.screens.CCleanerAppPage;
import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.io.ByteArrayInputStream;

@TestMethodOrder(OrderAnnotation.class)
public class CCleanerTest extends BaseTest {

    private static WindowsDriver driver = null;
    private final static String FILE_FOLDER_PATH = "C:\\Program Files\\CCleaner";
    private final static String INSTALLED_FILE_NAME = "CCleaner64.exe";
    private final static String APP = FILE_FOLDER_PATH + "\\" + INSTALLED_FILE_NAME;

    @BeforeAll
    public static void setUp() {
        driver = getWindowsDriver(setCapabilitiesForApp(FILE_FOLDER_PATH, APP));
    }

    @Order(7)
    @Story("Main App page")
    @Description("Check Health Check")
    @Test
    public void checkHealthCheckTest() {
        var cCleanerAppPage = new CCleanerAppPage(driver);
        cCleanerAppPage.goToHealthCheck();
        cCleanerAppPage.checkHealthCheck();
        Allure.addAttachment("Health Check Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Order(8)
    @Story("Main App page")
    @Description("Check Custom Clean")
    @Test
    public void checkCustomCleanTest() {
        var cCleanerAppPage = new CCleanerAppPage(driver);
        cCleanerAppPage.goToCustomClean();
        cCleanerAppPage.checkCustomClean();
        Allure.addAttachment("Custom Clean Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Order(9)
    @Story("Main App page")
    @Description("Check Driver Updater")
    @Test
    public void checkDriverUpdaterTest() {
        var cCleanerAppPage = new CCleanerAppPage(driver);
        cCleanerAppPage.goToDriverUpdater();
        cCleanerAppPage.checkDriverUpdater();
        Allure.addAttachment("Driver Updater Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}
