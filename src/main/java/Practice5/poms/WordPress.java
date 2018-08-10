package Practice5.poms;

import org.openqa.selenium.WebDriver;

public class WordPress {
	private WordPress wordPress;
	
	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public LeftMenu leftMenu;
	public Posts posts;
	public Pages pages;
	public Users users;
	public AddNewPost addNewPost;
	public AddNewPage addNewPage;
	public AddNewUser addNewUser;
	public EditPost editPost;
	public Preview preview;

	public WordPress(WebDriver driver) {
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		leftMenu = new LeftMenu(driver);
		posts = new Posts(driver);
		pages = new Pages(driver);
		users = new Users(driver);
		addNewPost = new AddNewPost(driver);
		addNewPage = new AddNewPage(driver);
		addNewUser = new AddNewUser(driver);
		editPost = new EditPost(driver);
		preview = new Preview(driver);
	}

}
