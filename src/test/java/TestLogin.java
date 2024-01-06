import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class TestLogin extends TestBase {

    private WebDriver driver;

    public static void main(String[] args) {

    }

    //This test verifies if the user can log in successfully
    @Parameters({"username", "password", "usernameProfilePage"})
    @Test
    private void testLogin(String username, String password, String usernameProfilePage) {
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

        //Get Sign in text
        String elemText = loginPage.getSignInText();
        Assert.assertEquals(elemText, "Sign in");

        //Input valid username
        loginPage.populateUsername(username);

        //Input valid password
        loginPage.populatePassword(password);

        //Click the Sign in button
        loginPage.clickSignIn();

        //Click the Profile button
        headerMenu.clickProfileLink();

        //Profile Class
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        profilePage.isUrlLoaded();

        //Get Logged in user text
        Assert.assertEquals(profilePage.getUsername(), usernameProfilePage);
    }
}