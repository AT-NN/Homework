package Practice5.tests;


import Practice5.poms.WordPress;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class CreateAndDraftNewPost extends BaseTest {

	@Test
	public void createAndDraftNewPost(){
		WordPress wp = new WordPress(driver);
		wp.loginPage.login(user, password);
		wp.dashboardPage.getPageTitle().equals("Dashboard");
		wp.dashboardPage.getWelcomeMsg().equals("Welcome to WordPress!");

		wp.leftMenu.addNewPost();
		wp.addNewPost.getPageTitle().equals("Add a New Post");
		String timeStamp = sdf.format(new Date());
		String myPostTitle = "Post title" + timeStamp;
		wp.addNewPost.fillNewPost(myPostTitle,"Post content" + timeStamp);
		wp.addNewPost.saveDraft();

		wp.leftMenu.openAllPosts();
		wp.posts.getPageTitle().equals("Posts");
		Assert.assertTrue(wp.posts.checkPostPresence(myPostTitle,"draft"),"No draft post with title '" + myPostTitle + "' was found.");
	}
}