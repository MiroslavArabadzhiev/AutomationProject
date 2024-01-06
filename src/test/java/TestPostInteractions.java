import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class TestPostInteractions extends TestBase {

    private WebDriver driver;

    public static void main(String[] args) {

    }

    //This test opens the first user on the Home Page, Follows the user, likes and dislikes their most recent post
    @Parameters({"username", "password", "usernameProfilePage", "comment"})
    @Test
    private void testPostInteractions(String username, String password, String usernameProfilePage, String comment) {

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

        //Click the "Home" button
        headerMenu.clickHome();

        //Click on the profile of the first user
        profilePage.clickPostUser();

        //Click the "Follow" button
        profilePage.clickFollowButton();

        //Click on "All" posts
        profilePage.clickAllPosts();

        //Click the first image
        profilePage.clickFirstImage();

        //Click the "Like" button
        profilePage.clickLikeButton();

        //Write a comment and wait until it is visible
        profilePage.commentPost(comment);
    }
}