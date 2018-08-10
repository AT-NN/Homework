package Practice5.tests;


import Practice5.poms.WordPress;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class CreateAndDeleteNewPage extends BaseTest {

	@Test
	public void createAndDeleteNewPage(){
		WordPress wp = new WordPress(driver);
		wp.loginPage.login(user, password);
		wp.dashboardPage.getPageTitle().equals("Dashboard");
		wp.dashboardPage.getWelcomeMsg().equals("Welcome to WordPress!");

		wp.leftMenu.addNewPage();
		wp.addNewPage.getPageTitle().equals("Add New Page");
		String timeStamp = sdf.format(new Date());
		String myPageTitle = "Page title" + timeStamp;
		wp.addNewPage.fillNewPage(myPageTitle,"Page content" + timeStamp);
		wp.addNewPost.publish();

		wp.leftMenu.openAllPages();
		wp.pages.getPageTitle().equals("Pages");
		Assert.assertTrue(wp.pages.checkPagePresence(myPageTitle,"publish"),"No published page with title '" + myPageTitle + "' was found.");

		wp.pages.clickPage(myPageTitle);
		wp.addNewPage.deletePage();

		Assert.assertFalse(wp.pages.checkPagePresence(myPageTitle,"publish"),"No published page with title '" + myPageTitle + "' was found.");

	}
}