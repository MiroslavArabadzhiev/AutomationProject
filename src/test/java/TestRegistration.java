import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestRegistration extends TestBase {

    private WebDriver driver;

    public static void main(String[] args) {

    }

    @DataProvider(name = "userData")
    private Object[][] userData() {
        TestBase testBase = new TestBase();
        String username = testBase.generateRandomString(7,10);
        String email = testBase.generateRandomEmail(5,8);
        return new Object[][]{
                {username, email, "Test123"}
        };
    }

    //This test registers a new user
    @Test(dataProvider = "userData")
    private void testRegistration (String username, String email, String password) {
        WebDriverWait wait = new WebDriverWait(super.getDriver(), Duration.ofSeconds(30));

        //Home Page Class
        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigatTo();
        homePage.isUrlLoaded();

        //Header Class
        Header headerMenu = new Header(super.getDriver());
        headerMenu.clickLoginLink();

        //Login Class
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.isUrlLoaded();

        //Validate Sign In form is visible
        String elemText = loginPage.getSignInText();
        Assert.assertEquals(elemText, "Sign in");

        //Click the Register button
        loginPage.clickRegisterButton();

        //Validate the URL of the Registration form
        RegistrationPage registrationPage = new RegistrationPage(super.getDriver());
        registrationPage.isRegisterUrlLoaded();

        //Validate Sign Up form is visible
        elemText = registrationPage.getSignUpText();
        Assert.assertEquals(elemText, "Sign up");

        //Input username
        registrationPage.populateUsername(username);

        //Input email
        registrationPage.populateEmail(email);

        //Input password
        registrationPage.populatePassword(password);

        //Input confirm password
        registrationPage.populateConfirmPassword(password);

        //Click the Sign In button
        registrationPage.clickSingInButton();

        //Wait for the Home page to load
        registrationPage.isHomeUrlLoaded();

        //Click on Profile button
        registrationPage.clickProfileButton();

        //Validate the user profile link is loaded
        registrationPage.isUsersUrlLoaded();

        //Validate the username provided matches the username on the Profile page
        Assert.assertEquals(registrationPage.validateUsername(), username);
    }
}