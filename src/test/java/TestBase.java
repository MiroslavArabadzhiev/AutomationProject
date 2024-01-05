import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.FileUtils.cleanDirectory;

public class TestBase {
    public static final String TEST_RESOURCES_DIR = "src\\main\\resources\\";
    public static final String DOWNLOAD_DIR = TEST_RESOURCES_DIR.concat("download\\");
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots\\");
    public static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports\\");
    public static final String UPLOAD_DIR = TEST_RESOURCES_DIR.concat("upload\\");

    private WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(REPORTS_DIR);
        cleanDirectory(SCREENSHOTS_DIR);
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setupMethod() {
        //this.driver = new EdgeDriver();
        this.driver = new EdgeDriver(configEdgeOptions());
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    private EdgeOptions configEdgeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat(DOWNLOAD_DIR));
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setExperimentalOption("prefs", prefs);
        return edgeOptions;
    }

    //Cleans files in a directory provided as input parameter
    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory:%s%n", directoryPath);
        }
    }

    @AfterMethod
    protected final void tearDownTest(ITestResult testResult) {
        takeScreenshot(testResult);
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @AfterSuite
    public void deleteDownloadedFiles() throws IOException {
        cleanDirectory(DOWNLOAD_DIR);
    }

    //Click on element identified by identifier
    public void waitAndClick(By identifier){
        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(identifier));
        elem.click();
    }

    //Click on WebElement
    public void waitAndClickElement(WebElement element){
        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(element));
        elem.click();
    }

    private String generateRandomString(int minLenghtInclusive, int maxLenghtInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLenghtInclusive, maxLenghtInclusive);
    }

    private String generateRandomEmail(int minLenghtInclusive, int maxLenghtInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLenghtInclusive, maxLenghtInclusive) + "@gmail.com";
    }

    public boolean isFilePresent(File file) throws InterruptedException {
        int waitTime = 20;
        int counter = 0;

        while (counter < waitTime) {
            if (file.exists()) {
                return true;
            }
            Thread.sleep(1000);
            counter++;
        }
        return false;
    }

    private void takeScreenshot(ITestResult testResult){
        if(ITestResult.FAILURE == testResult.getStatus()){
            try{
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            }catch (IOException e){
                System.out.println("Unable to create screenshot file: " + e.getMessage());
            }
        }
    }

}