package Practice5.poms;

import Practice5.controls.WebButton;
import Practice5.controls.WebLink;
import Practice5.controls.WebTextBlock;
import Practice5.controls.WebTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class AddNewPage extends AbstractPOM {

	public AddNewPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='post_title']")
	private WebTextInput postTitleInput;

	@FindBy(xpath = "//textarea[@name='content']")
	private WebTextBlock contentInput;

	@FindBy(id = "publish")
	private WebButton btnPublish;

	@FindBy(id="post-preview")
	private WebLink btnPreview;

	@FindBy(id="delete-action")
	private WebLink lnkMoveToBin;

	public void fillNewPage(String pageTitle, String pageContent) {
		postTitleInput.sendKeys(pageTitle);
		contentInput.sendKeys(pageContent);
	}

	public void publish() {
		waitForAvailability(btnPublish);
		btnPublish.click();
	}

	public void preview(){
		waitForAvailability(btnPreview);
		btnPreview.click();
	}

	public void deletePage(){
		waitForAvailability(lnkMoveToBin);
		lnkMoveToBin.click();
	}

}
