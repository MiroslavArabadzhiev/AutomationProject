import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class PostPage {
    private WebDriver driver;

    public static String PAGE_URL = "http://training.skillo-bg.com:4200/posts/create";

    @FindBy(css = ".file[type=\"file\"]")
    private WebElement uploadField;

    @FindBy(css = "img.image-preview")
    private WebElement imagePreview;

    @FindBy(css = "input.input-lg")
    private WebElement imageNameElement;

    @FindBy(name = "caption")
    private WebElement captionElement;

    @FindBy(xpath = "//*[@for=\"customSwitch2\"]")
    private WebElement publicPrivateToggle;

    @FindBy(id = "create-post")
    private WebElement createPostButton;


    public PostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL));
    }

    public void uploadPicture(File imagePath){
        uploadField.sendKeys(imagePath.getAbsolutePath());
    }

    public boolean isImageVisible(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(imagePreview)).isDisplayed();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }

    public String getImageName(){
        String imageName = imageNameElement.getAttribute("placeholder");
        return imageName;
    }

    public void populateCaption(String caption) {
        captionElement.sendKeys(caption);
    }

    public void privatePostToggle(){
        publicPrivateToggle.click();
    }

    public void clickCreatePost(){
        createPostButton.click();
    }
}