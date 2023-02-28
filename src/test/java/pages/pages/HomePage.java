package pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static utils.FileUtils.waitForFile;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "button button secondary regular orange")
    WebElement getProBtn;

    @FindBy(xpath = "//div/a[@class='button button primary regular blue']")
    WebElement getTrialBtn;

    @FindBy(xpath = "//*[@id='heroPrimaryLeftModuleE1']/div/div[2]/div[2]/div[2]/a/div")
    WebElement getFreeBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("go to download pro version")
    public void downloadProVersion() {
        getProBtn.click();
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("go to download trial version")
    public void downloadTrialVersion() {
        getTrialBtn.click();
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("go to download free version")
    public void downloadFreeVersion(String fileFolderPath, String fileName) {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getFreeBtn.click();
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitForFile(fileFolderPath, fileName);
    }

    @Step("Accept cookies if notification bar present")
    public HomePage acceptCookeIfPresent() {
        var page = new OneTrustBanner(driver);
        if (page.isPresent()) page.accept();
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new HomePage(driver);
    }

    @Step("Open Home page by URL")
    public static HomePage openPage(WebDriver driver) {
        driver.get("https://www.ccleaner.com/homepage-modular");
        //wait until loaded
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new HomePage(driver);
    }

    private class OneTrustBanner {

        public OneTrustBanner(WebDriver driver) {
            PageFactory.initElements(driver, this);
            //wait until loaded
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @FindBy(id = "onetrust-banner-sdk")
        WebElement onetrustBanner;

        @FindBy(id = "onetrust-pc-btn-handler")
        WebElement pcBtn;

        @FindBy(id = "onetrust-reject-all-handler")
        WebElement rejectAllBtn;

        @FindBy(id = "onetrust-accept-btn-handler")
        WebElement acceptBtn;

        public void rejectAll() {
            rejectAllBtn.click();
            //wait until loaded
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void accept() {
            acceptBtn.click();
            //wait until loaded
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void change() {
            pcBtn.click();
            //wait until loaded
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean isPresent() {
            return onetrustBanner.isDisplayed();
        }
    }
}
