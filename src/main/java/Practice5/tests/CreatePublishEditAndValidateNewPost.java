package Practice5.tests;

import Practice5.poms.WordPress;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class CreatePublishEditAndValidateNewPost extends BaseTest {

	@Test
	public void createPublishEditAndValidateNewPost(){
		WordPress wp = new WordPress(driver);
		wp.loginPage.login(user, password);
		wp.dashboardPage.getPageTitle().equals("Dashboard");
		wp.dashboardPage.getWelcomeMsg().equals("Welcome to WordPress!");

		wp.leftMenu.addNewPost();
		wp.addNewPost.getPageTitle().equals("Add a New Post");
		String timeStamp = sdf.format(new Date());
		String postTitle = "Post title" + timeStamp;
		String postContent = "Post content" + timeStamp;
		wp.addNewPost.fillNewPost(postTitle,postContent);
		wp.addNewPost.publish();

		wp.leftMenu.openAllPosts();
		wp.posts.getPageTitle().equals("Posts");
		Assert.assertTrue(wp.posts.checkPostPresence(postTitle,"publish"),"No published post with title '" + postTitle + "' was found.");

		wp.posts.clickPost(postTitle);
		wp.editPost.getPageTitle().equals("Edit Post");

		wp.editPost.preview();
		wp.preview.validate(postTitle,postContent);

		wp.editPost.getPageTitle().equals("Edit Post");
		postTitle = postTitle + " edited title";
		postContent = postContent + " edited content";
		wp.editPost.editPost(postTitle,postContent);
		wp.editPost.update();

		wp.editPost.preview();
		wp.preview.validate(postTitle,postContent);
	}
}