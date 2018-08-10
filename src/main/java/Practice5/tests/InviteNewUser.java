package Practice5.tests;


import Practice5.poms.WordPress;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class InviteNewUser extends BaseTest {

	@Test
	public void inviteNewUser(){
		WordPress wp = new WordPress(driver);
		wp.loginPage.login(user, password);
		wp.dashboardPage.getPageTitle().equals("Dashboard");
		wp.dashboardPage.getWelcomeMsg().equals("Welcome to WordPress!");

		wp.leftMenu.addNewUser();
		wp.addNewPage.getPageTitle().equals("Add New User");
		String timeStamp = sdf.format(new Date());
		String username = "user" + timeStamp;
		wp.addNewUser.fillForm(username,"email" + timeStamp + "@practice5.com","Ivan","Ivanov");


		wp.users.getPageTitle().equals("Users Add New");
		Assert.assertTrue(wp.users.checkUserPresence(username),"No user with username '" + username + "' was found.");

	}
}