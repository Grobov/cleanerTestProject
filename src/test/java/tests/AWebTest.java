package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;

import static pages.pages.HomePage.openPage;

@TestMethodOrder(OrderAnnotation.class)
public class AWebTest extends BaseTest {

    private static WebDriver driver = null;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver(getOptions(getPrefs(DOWNLOADS_FILE_FOLDER_PATH)));
    }

    @Order(1)
    @Story("Download")
    @Description("Verify download process")
    @Test
    void verifyDownloadProcessTest() {
        openPage(driver)
                .acceptCookeIfPresent()
                .downloadFreeVersion(DOWNLOADS_FILE_FOLDER_PATH, INSTALL_FILE_NAME);
        Allure.addAttachment("Download Process Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }
}
