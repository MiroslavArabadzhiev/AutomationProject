import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class TestLogout extends TestBase {

    private WebDriver driver;

    @DataProvider(name = "userData")
    private Object[][] userData() {
        return new Object[][]{
                {"Test_MA", "Test123", "Test_MA"}
        };
    }

    //This test verifies if the user can log in successfully
    @Test(dataProvider = "userData")
    private void testLogout(String username, String password, String usernameProfilePage) {

        //Open the Skillo website
        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();

        //Check we are on the correct page
        homePage.isUrlLoaded();

        //Click the "Login" button
        Header headerMenu = new Header(super.getDriver());
        headerMenu.clickLoginLink();

        //Validate the "Login" page is loaded
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.isUrlLoaded();

        //Validate the "Sign In" form is visible
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

        //Validate the "Profile" page is loaded
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        profilePage.isUrlLoaded();

        //Validate that the username matches the username provided
        Assert.assertEquals(profilePage.getUsername(), usernameProfilePage);

        //Get the number of posts and print it
        profilePage.getNumberPosts();

        //Get the number of followers and print it
        profilePage.getNumberFollowers();

        //Get the number of followings and print it
        profilePage.getNumberFollowings();

        //Click the "Logout" button
        headerMenu.clickLogoutLink();

        //Validate that the user has been logged out
        elemText = loginPage.getSignInText();
        Assert.assertEquals(elemText, "Sign in");
    }
}