package Practice5.poms;

import Practice5.controls.WebButton;
import Practice5.controls.WebTextBlock;
import Practice5.controls.WebTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPOM {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='user_login']")
	private WebTextInput userNameInput;
	
	@FindBy(id = "user_pass")
	private WebTextInput userPassInput;
	
	@FindBy(id = "wp-submit")
	private WebButton submitBtn;

	@FindBy(xpath = "//div[@id='login_error']")
	private WebTextBlock errorMsg;

	@FindBy(xpath = "//div[@id='login']//p[@class='message']")
	private WebTextBlock logOutMsg;

	public void login(String strUserName, String strPasword) {
		userNameInput.sendKeys(strUserName);
		userPassInput.sendKeys(strPasword);
		submitBtn.click();
	}

	public WebTextBlock getLogOutMsg() {
		return logOutMsg;
	}

	public WebTextBlock getErrorMsg() {
		return errorMsg;
	}
}