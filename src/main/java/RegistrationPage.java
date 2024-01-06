import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class RegistrationPage {
    public static final String REGISTER_PAGE_URL = "http://training.skillo-bg.com:4200/users/register";
    public static final String HOME_PAGE_URL = "http://training.skillo-bg.com:4200/posts/all";
    public static final String USERS_PAGE_URL = "http://training.skillo-bg.com:4200/users/";

    private final WebDriver driver;

    @FindBy(xpath = "//*[contains(text(), \"Sign up\")]")
    private WebElement signUpForm;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@formcontrolname=\"email\"]")
    private WebElement emailField;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordField;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    @FindBy(id = "nav-link-profile")
    private WebElement profileButton;

    @FindBy(tagName = "h2")
    private WebElement profileUsername;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isRegisterUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlToBe(REGISTER_PAGE_URL));
    }

    public boolean isHomeUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlToBe(HOME_PAGE_URL));
    }

    public boolean isUsersUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        return wait.until(ExpectedConditions.urlContains(USERS_PAGE_URL));
    }

    public String getSignUpText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(signUpForm));
        return signUpForm.getText();
    }

    public void populateUsername(String username){
        usernameField.sendKeys(username);
    }

    public void populateEmail(String email){
        emailField.sendKeys(email);
    }

    public void populatePassword(String password){
        passwordField.sendKeys(password);
    }

    public void populateConfirmPassword(String password){
        confirmPasswordField.sendKeys(password);
    }

    public void clickSingInButton(){
        signInButton.click();
    }

    public void clickProfileButton(){
        profileButton.click();
    }

    public String validateUsername(){
        return profileUsername.getText();
    }
}