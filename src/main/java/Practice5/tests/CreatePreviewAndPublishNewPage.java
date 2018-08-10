package Practice5.tests;

import Practice5.poms.WordPress;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class CreatePreviewAndPublishNewPage extends BaseTest {


	@Test
	public void createPreviewAndPublishNewPage() {
		WordPress wp = new WordPress(driver);
		wp.loginPage.login(user, password);
		wp.dashboardPage.getPageTitle().equals("Dashboard");
		wp.dashboardPage.getWelcomeMsg().equals("Welcome to WordPress!");

		wp.leftMenu.addNewPage();
		wp.addNewPage.getPageTitle().equals("Add New Page");
		String timeStamp = sdf.format(new Date());
		String pageTitle = "Page title" + timeStamp;
		String pageContent = "Page content" + timeStamp;
		wp.addNewPage.fillNewPage(pageTitle, pageContent);
		wp.addNewPage.preview();
		wp.preview.validate(pageTitle.toUpperCase(), pageContent);
		wp.addNewPage.publish();

		wp.leftMenu.openAllPages();
		wp.pages.getPageTitle().equals("Pages");
		Assert.assertTrue(wp.posts.checkPostPresence(pageTitle, "publish"), "No published post with title '" + pageTitle + "' was found.");

	}
}