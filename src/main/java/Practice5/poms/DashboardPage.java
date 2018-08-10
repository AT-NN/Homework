package Practice5.poms;

import Practice5.controls.WebLink;
import Practice5.controls.WebTextBlock;
import Practice5.controls.WebTypifiedElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DashboardPage extends AbstractPOM {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath ="//div[@class='welcome-panel-content']/h2")
	private WebTextBlock welcomeMsg;

    @FindBy(id="wp-admin-bar-my-account")
	private WebElement lnkMyAccount;

	@FindBy(id="wp-admin-bar-logout")
	private WebElement lnkLogOut;

	public WebElement getLnkLogOut() {
		return lnkLogOut;
	}

	public WebTextBlock getWelcomeMsg() {
		return welcomeMsg;
	}

	public WebElement getLnkMyAccount() {
		return lnkMyAccount;
	}

	public void logOut(){
		clickSubMenu(driver,lnkMyAccount,lnkLogOut);
	}
}