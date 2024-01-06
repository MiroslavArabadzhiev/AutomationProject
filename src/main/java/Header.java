import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver driver;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;
    @FindBy(id = "nav-link-home")
    private WebElement homeLink;
    @FindBy(css = ".fa-sign-out-alt")
    private WebElement logoutLink;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickProfileLink(){
        //Validate the Profile link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();

    }

    public void clickLoginLink(){
        loginLink.click();
    }

    public void clickNewPost(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(newPostLink));
        newPostLink.click();
    }

    public void clickHome(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(homeLink));
        homeLink.click();
    }

    public void clickLogoutLink(){
        logoutLink.click();
    }
}