package Practice5.poms;

import Practice5.controls.WebButton;
import Practice5.controls.WebTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class AddNewUser extends AbstractPOM {

	public AddNewUser(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (id="user_login")
	private WebTextInput inputUsername;

	@FindBy (id="email")
	private WebTextInput inputEmail;

	@FindBy (id="first_name")
	private WebTextInput inputFirstName;

	@FindBy (id="last_name")
	private WebTextInput inputLastName;

	@FindBy (id="createusersub")
	private WebButton btnAddNewUser;

	public void fillForm(String username, String email, String firstName, String lastName){
		inputUsername.sendKeys(username);
		inputEmail.sendKeys(email);
		inputFirstName.sendKeys(firstName);
		inputLastName.sendKeys(lastName);
		btnAddNewUser.click();
	}
}
