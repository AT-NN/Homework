package Practice5.tests;

import Practice5.poms.WordPress;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvalidUsersLogin extends BaseTest {
	WordPress wp;

	@Test
	public void openLoginPage(){
		wp = new WordPress(driver);
	}

	@Test(dataProvider = "testUsers",dependsOnMethods = "openLoginPage")
	public void invalidUsersLogin(String userName, String userPassword, String errorMessage){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wp.loginPage.login(userName, userPassword);
		wp.loginPage.getErrorMsg().equals(errorMessage);
	}

	@DataProvider
	public Object[][] testUsers(){
		return new Object[][]{
				{"invalidUser",password, "ERROR: Invalid username. Lost your password?"},
				{"invalidUser","invalidPassword", "This test case should fail."},
				{user, "invalidPassword","ERROR: The password you entered for the username " + user + " is incorrect. Lost your password?"}};

	}
}
