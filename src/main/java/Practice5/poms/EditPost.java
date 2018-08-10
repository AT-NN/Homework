package Practice5.poms;

import Practice5.controls.WebButton;
import Practice5.controls.WebLink;
import Practice5.controls.WebTextBlock;
import Practice5.controls.WebTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class EditPost extends AbstractPOM{

    public EditPost(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    private WebTextInput postTitleInput;

    @FindBy(id="content")
    private WebTextBlock postContentInput;

    @FindBy(id="post-preview")
    private WebLink btnPreview;

    @FindBy(id = "publish")
    private WebButton btnUpdate;

    public void editPost(String newPostTitle, String newPostContent){
        postTitleInput.sendKeys(newPostTitle);
        postContentInput.clear();
        postContentInput.sendKeys(newPostContent);
    }

    public void preview(){
        waitForAvailability(btnPreview);
        btnPreview.click();
    }

    public void update() {
        waitForAvailability(btnUpdate);
        btnUpdate.click();
    }
}
