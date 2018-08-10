package Practice5.poms;

import Practice5.controls.WebButton;
import Practice5.controls.WebTextBlock;
import Practice5.controls.WebTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AddNewPost extends AbstractPOM {
	
	public AddNewPost(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='post_title']")
	private WebTextInput postTitleInput;
	
	@FindBy(xpath = "//textarea[@name='content']")
	private WebTextBlock contentInput;

	@FindBy(id = "publish")
	private WebButton btnPublish;

	@FindBy(id = "save-post")
	private WebButton btnSaveDraft;

	public void fillNewPost(String postTitle, String postContent) {
		postTitleInput.sendKeys(postTitle);
		contentInput.sendKeys(postContent);
	}

	public void publish() {
		waitForAvailability(btnPublish);
		btnPublish.click();
	}

	public void saveDraft() {
		waitForAvailability(btnSaveDraft);
		btnSaveDraft.click();
	}
}
