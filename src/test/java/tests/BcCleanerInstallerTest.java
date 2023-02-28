package tests;

import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.screens.InstallFirstPage;
import pages.screens.RootPage;
import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import utils.FileUtils;

import java.io.ByteArrayInputStream;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class BcCleanerInstallerTest extends BaseTest {

    private static WindowsDriver driver = null;
    private final static String APP = DOWNLOADS_FILE_FOLDER_PATH + "\\" + INSTALL_FILE_NAME;
    private final static String FILE_FOLDER_PATH = "C:\\Program Files\\CCleaner";
    private final static String INSTALLED_FILE_NAME = "CCleaner64.exe";


    @BeforeAll
    public static void setUp() {
        FileUtils.execute(APP);
        try {
            sleep(40000);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        driver = getWindowsDriver(setCapabilitiesForRoot());
        var topLevelWindowHandle = new RootPage(driver).getTopLevelWindowHandleByProcessName(INSTALL_FILE_NAME);
        var capabilities = setCapabilitiesByTopLevelWindowHandle(topLevelWindowHandle);
        driver = getWindowsDriver(capabilities);
    }

    /**
     * (ACR-065) Show both links and assent language to the app's EULA and/or Terms of Service,
     * Returns and Cancellation Policy, Privacy Policy.
     */
    @Order(2)
    @Story("License and Policy")
    @Description("Check license and policy links according to (ACR-065)")
    @Test
    public void checkLicenseAndPolicyTest() {
        var installFirstPage = new InstallFirstPage(driver);
        installFirstPage.checkViewPrivacyPolicy();
        installFirstPage.checkViewLicenseAgreement();
    }

    /**
     * (ACR-002) App's name is consistent across all points of user interaction: code signing, ads, offers,
     * landing pages, install locations, and system/browser uninstall names.
     */
    @Order(3)
    @Story("Install window")
    @Description("Check names according to (ACR-002)")
    @Test
    public void checkTopicTest() {
        var installFirstPage = new InstallFirstPage(driver);
        installFirstPage.checkNameInTitle();
    }

    /**
     * (ACR-045) The app's purpose/intent, main effects on the consumer's computer,
     * and its significant functions and settings changes are described in clear and straightforward language
     * that is clearly visible and easy to read on the screen.
     */
    @Order(4)
    @Story("Install window")
    @Description("Check texts according to (ACR-045)")
    @Test
    public void checkDescriptionTest() {
        var installFirstPage = new InstallFirstPage(driver);
        installFirstPage.checkDescriptionText();
        Allure.addAttachment("Description Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    /**
     * (ACR-040) Installation location is by default in standard locations, and identifies the name, source,
     * and the actual install date.
     */
    @Order(5)
    @Story("Install process")
    @Description("Check installation location by default according to (ACR-040)")
    @Test
    public void checkInstallerTest() {
        var installFirstPage = new InstallFirstPage(driver);
        installFirstPage.goToInstall();
        installFirstPage.checkInstallFolder(FILE_FOLDER_PATH, INSTALLED_FILE_NAME);
        Allure.addAttachment("Installer Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    /**
     * This is failing test
     */
    @Order(6)
    @Story("Install window")
    @Description("Fail test")
    @Test
    public void failedAssertionTest() {
        assertTrue("failed Assertion", false);
        Allure.addAttachment("failed Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }
}
