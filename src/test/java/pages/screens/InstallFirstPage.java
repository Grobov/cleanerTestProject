package pages.screens;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static utils.FileUtils.waitForFile;

public class InstallFirstPage {

    private static final String VIEW_LICENSE_AGREEMENT = "View License Agreement";
    private static final String VIEW_PRIVACY_POLICY = "View Privacy Policy";
    private static final String TOPIC = "CCleaner v6.09 Setup";
    private static final String DESCRIPTION = "By installing this product you agree to our license agreement and privacy policy.";

    private static final String LICENSE_AGREEMENT_AUTOMATION_ID = "1003";
    private static final String PRIVACY_POLICY_AUTOMATION_ID = "1142";
    private static final String DESCRIPTION_AUTOMATION_ID = "1007";
    private static final String TITLE_AUTOMATION_ID = "1141";
    private static final String ATTRIBUTE_NAME = "Name";
    WindowsDriver driver;

    public InstallFirstPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Check view license agreement link")
    public void checkViewLicenseAgreement() {
        assertEquals(driver.findElementByAccessibilityId(LICENSE_AGREEMENT_AUTOMATION_ID).getAttribute(ATTRIBUTE_NAME),
                VIEW_LICENSE_AGREEMENT);
    }

    @Step("Check view license agreement link")
    public void checkViewPrivacyPolicy() {
        assertEquals(driver.findElementByAccessibilityId(PRIVACY_POLICY_AUTOMATION_ID).getAttribute(ATTRIBUTE_NAME),
                VIEW_PRIVACY_POLICY);
    }

    @Step("Check that title contains 'CCleaner'")
    public void checkNameInTitle() {
        assertEquals(driver.findElementByAccessibilityId(TITLE_AUTOMATION_ID).getAttribute(ATTRIBUTE_NAME),
                TOPIC);
    }

    @Step("Check description")
    public void checkDescriptionText() {
        assertEquals(driver.findElementByAccessibilityId(DESCRIPTION_AUTOMATION_ID).getAttribute(ATTRIBUTE_NAME),
                DESCRIPTION);
    }

    @Step("Failed assertion")
    public void checkFailedAssertion() {
        assertEquals(driver.findElementByAccessibilityId(DESCRIPTION_AUTOMATION_ID).getAttribute(ATTRIBUTE_NAME),
                "Expected wrong text");
    }

    @Step("Go to install")
    public void goToInstall() {
        driver.findElementByName("Install").click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Check install folder")
    public void checkInstallFolder(String fileFolderPath, String fileName) {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitForFile(fileFolderPath, fileName);
    }
}
