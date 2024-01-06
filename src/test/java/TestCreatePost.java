import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;


public class TestCreatePost extends TestBase {

    private WebDriver driver;

    public static void main(String[] args) {

    }
    @DataProvider(name = "postPicture")
    private Object[][] postPicture() {

        File postPicture = new File("src\\main\\resources\\upload\\AutomationQA.png");
        String caption = "The image caption content";

        return new Object[][]{
                {"Test_MA", "Test123", "Test_MA", postPicture, caption}
        };
    }

    //This test creates a new Private Post
    @Test(dataProvider = "postPicture")
    private void testCreatePost(String username, String password, String usernameProfilePage, File postPicture, String caption) {

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

        //Click on the "New post" button
        Header headerPage = new Header(super.getDriver());
        headerPage.clickNewPost();

        //Validate the "New post" page is loaded
        PostPage postPage = new PostPage(super.getDriver());
        Assert.assertTrue(postPage.isUrlLoaded());

        //Upload a picture
        postPage.uploadPicture(postPicture);

        //Validate that the picture has been uploaded
        Assert.assertTrue(postPage.isImageVisible(), "The image is not uploaded!");

        //Validate that the picture is the correct one
        Assert.assertEquals(postPage.getImageName(), postPicture.getName());

        //Fill in the caption
        postPage.populateCaption(caption);

        //Toggle to a Private Post
        postPage.privatePostToggle();

        //Click on the "Create Post" button
        postPage.clickCreatePost();

        //Click on "All" posts
        profilePage.clickAllPosts();
    }
}