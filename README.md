# CleanerTestProject

CleanerTestProject is a Java project for automation testing downloading and install process.
Install process tests include:
- checkLicenseAndPolicyTest(). For verification License and Policy as a part of (ACR-065) point from checklist. Open this links is out of scope, and should be added in full functionality coverage.
```
    /**
     * (ACR-065) Show both links and assent language to the app's EULA and/or Terms of Service,
     * Returns and Cancellation Policy, Privacy Policy.
     */
```

- checkTopicTest(). Check that 'CCleaner' program name present on install window screen, as a part of (ACR-002) point from checklist. This point in checklist also will be covered in other tests, with verification file names.
```
    /**
     * (ACR-002) App's name is consistent across all points of user interaction: code signing, ads, offers,
     * landing pages, install locations, and system/browser uninstall names.
     */
```
- checkDescriptionTest(). Check that install window screen contains explanation for user about the goal of process, as a part of (ACR-045) point from checklist.
```
    /**
     * (ACR-045) The app's purpose/intent, main effects on the consumer's computer,
     * and its significant functions and settings changes are described in clear and straightforward language
     * that is clearly visible and easy to read on the screen.
     */
```

- checkDescriptionTest(). Check installation location by default, folder name and file naming, as a part of (ACR-045) point from checklist.
```
    /**
     * (ACR-040) Installation location is by default in standard locations, and identifies the name, source,
     * and the actual install date.
     */
```
- verifyDownloadProcessTest(). Check download process from web and download location.

- checkHealthCheckTest() and other the same are for demonstrate testing in desktop app.

N.B. All this tests can not guarantee full coverage of this checklist and created as an example and technology demonstration. Prevent of using this scripts for real testing.



#### Stack of Technologies:
1. Java
2. Maven
3. Appium WindowsDriver
4. Selenium WebDriver
5. Allure reports

## Install & Verify Maven and JDK(Java)
To verify Java and Maven version use:
```bash
mvn -version
```
Application tested on:
```bash
Apache Maven 3.6.3
Java version: 15.0.2
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```
## Install WinAppDriver

1.  Download Windows Application Driver installer from [github](https://github.com/Microsoft/WinAppDriver/releases) or use it from **sources** folder.
2.  Run the installer on a Windows 10 machine where your application under test is installed and will be tested
3.  Enable **Developer Mode** in Windows settings

Windows Application Driver will then be running on the test machine listening to requests on the default IP address and port (127.0.0.1:4723).

## Install Allure reports

1. Download the latest version as zip archive from [Maven Central](https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/) or use it from **sources** folder.
2. Unpack the archive to allure-commandline directory.
3. Navigate to **bin** directory.
4. Use **allure.bat** for Windows or **allure** for other Unix platforms.
5. Add allure to system PATH.

Make sure that allure is now available:
```bash
allure --version
```
Application tested on:
```bash
2.13.8
```
## Install Chrome browser
Application tested on:
```bash
Version 110.0.5481.178 (Official Build) (64-bit)
```

## Usage

1. Download **current project** to some folder. (E.g. C:\projects\currentProject)
2. Run WinAppDriver **as administrator** WinAppDriver.exe from the installation directory  (E.g. C:\Program Files (x86)\Windows Application Driver)
3. Run tests by mvn command:
```bash
C:\projects\currentProject> mvn clean test
```
4. Waiting until 'some magic' happened and all tests finishing.

**N.B.** the expected result according to exercise is **BUILD FAILURE**, in the reason of test **failing** demonstration.
4. Build Allure report by command:
```bash
C:\projects\currentProject> allure serve
```
### Example of results:
![This is an image](https://myoctocat.com/assets/images/base-octocat.svg)
