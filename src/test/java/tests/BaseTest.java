package tests;

import com.sun.jna.platform.unix.solaris.LibKstat.KstatNamed.UNION.STR;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    final static String WINDOWS_DRIVER_URL = "http://127.0.0.1:4723";
    final static String PLATFORM_NAME = "Windows";
    final static String DEVICE_NAME = "WindowsPC";
    final static String TIMEOUT_SECONDS = "15";
    final static String DOWNLOADS_FILE_FOLDER_PATH = System.getProperty("user.home") + "\\Downloads";
    final static String INSTALL_FILE_NAME = "ccsetup609.exe";

    static DesiredCapabilities setCapabilitiesByTopLevelWindowHandle(String topLevelWindowHandle) {
        var capabilities = new DesiredCapabilities();
        capabilities.setCapability("appTopLevelWindow", topLevelWindowHandle);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("ms:experimental-webdriver", true);
        return capabilities;
    }

    static DesiredCapabilities setCapabilitiesForRoot() {
        var capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("ms:experimental-webdriver", true);
        return capabilities;
    }

    static DesiredCapabilities setCapabilitiesForApp(String fileFolderPath, String app) {
        var capabilities = new DesiredCapabilities();
        capabilities.setCapability("appWorkingDir", fileFolderPath);
        capabilities.setCapability("app", app);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("ms:experimental-webdriver", true);
        capabilities.setCapability("ms:waitForAppLaunch", TIMEOUT_SECONDS);
        return capabilities;
    }

    static WindowsDriver getWindowsDriver(DesiredCapabilities capabilities) {
        try {
            var winDriver = new WindowsDriver(new URL(WINDOWS_DRIVER_URL), capabilities);
            winDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            return winDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static Map<String, Object> getPrefs(String fileFolderPath) {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", fileFolderPath + "\\");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.extensions_to_open", "application/xml");
        prefs.put("safebrowsing.enabled", true);
        return prefs;
    }

    static ChromeOptions getOptions(Map<String, Object> prefs){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("safebrowsing-disable-extension-blacklist");
        options.addArguments("--window-size=800,600");
        return options;
    }
}
