import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class TestBase {

    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.edgedriver().setup();
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    protected final void setupMethod() {
        //this.driver = new ChromeDriver();
        this.driver = new EdgeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.quit();
        }
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

}