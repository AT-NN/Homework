package Practice5.tests;

import Practice5.poms.WordPress;
import org.testng.annotations.Test;

public class LoginSignOutTest extends BaseTest {
	WordPress wp;

	@Test
	public void login(){
		wp = new WordPress(driver);
		wp.loginPage.login(user, password);
		wp.dashboardPage.getPageTitle().equals("Dashboard");
		wp.dashboardPage.getWelcomeMsg().equals("Welcome to WordPress!");
	}

	@Test (dependsOnMethods = {"login"})
	public void signOut() {
		wp.dashboardPage.clickSubMenu(driver,wp.dashboardPage.getLnkMyAccount(),wp.dashboardPage.getLnkLogOut());
		wp.loginPage.getLogOutMsg().equals("You are now logged out.");
	}
}
