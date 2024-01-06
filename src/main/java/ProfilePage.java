import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users";

    private final WebDriver driver;

    @FindBy(tagName = "h2")
    private WebElement loggedInUser;
    @FindBy(xpath = "(//a[@class=\"post-user\"])[1]")
    private WebElement postUser;
    @FindBy(xpath = "//*[contains(text(), \"All\")]")
    private WebElement allPosts;
    @FindBy(css = ".profile-edit-btn")
    private WebElement followButton;
    @FindBy(css = "div.post-img")
    private WebElement firstImage;
    @FindBy(css = "i.like")
    private WebElement likeButton;
    @FindBy(xpath = "//*[@formcontrolname=\"content\"]")
    private WebElement commentField;
    @FindBy(xpath = "//*[contains(text(), \"Test_MA\")]")
    private WebElement visibleComment;
    @FindBy(xpath = "//li[contains(text(), \"posts\")]")
    private WebElement numberPosts;
    @FindBy(id = "followers")
    private WebElement numberFollowers;
    @FindBy(id = "following")
    private WebElement numberFollowings;


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }

    public String getUsername(){
        return loggedInUser.getText();
    }

    public void clickPostUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(postUser));
        postUser.click();
    }

    public void clickAllPosts(){
        allPosts.click();
    }

    public void clickFollowButton(){
        followButton.click();
    }

    public void clickFirstImage(){
        firstImage.click();
    }

    public void clickLikeButton(){
        likeButton.click();
    }

    public void commentPost(String comment){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        commentField.sendKeys(comment);
        commentField.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(visibleComment));
    }

    public void getNumberPosts(){
        System.out.println("The number of posts is: " + numberPosts.getText());
    }

    public void getNumberFollowers(){
        System.out.println("The number of followers is: " + numberFollowers.getText());
    }

    public void getNumberFollowings(){
        System.out.println("The number of followings is: " + numberFollowings.getText());
    }
}
