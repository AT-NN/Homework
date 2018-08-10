package Practice5.poms;

import Practice5.controls.WebLink;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftMenu extends AbstractPOM {

	@FindBy(xpath = "//div[@id='adminmenuwrap']/ul/li/a[./div[contains(text(),'Posts')]]")
	private WebElement postsLnk;

	@FindBy(xpath = "//div[@class='wp-menu-name' and contains(text(),'Pages')]")
	private WebElement lnkPages;

	@FindBy(xpath = "//div[@class='wp-menu-name' and contains(text(),'Users')]")
	private WebElement lnkUsers;

	@FindBy(xpath = "//li[a/div[text()='Users']]")
	private WebLink usersLnk;

	@FindBy(xpath = "//li[a/div[text()='Posts']]/ul/li/a[text()='Add New']")
	private WebElement addNewPostLnk;

	@FindBy(xpath = "//li[a/div[text()='Pages']]/ul/li/a[text()='Add New']")
	private WebElement lnkAddNewPage;

	@FindBy(xpath = "//li[a/div[text()='Users']]/ul/li/a[text()='Add New']")
	private WebElement lnkAddNewUser;

	@FindBy(xpath = "//li[@id='menu-posts']//a[contains(text(),'All Posts')]")
	private WebLink lnkAllPosts;

	@FindBy(xpath = "//li[@id='menu-pages']//a[contains(text(),'All Pages')]")
	private WebLink lnkAllPages;

	public LeftMenu(WebDriver driver) {
		super(driver);
	}
	
	public void addNewPost(){
		clickSubMenu(driver,postsLnk,addNewPostLnk);
	}

	public void addNewPage(){
		clickSubMenu(driver,lnkPages,lnkAddNewPage);
	}

	public void addNewUser(){
		clickSubMenu(driver,lnkUsers,lnkAddNewUser);
	}

	public void openAllPosts(){
		lnkAllPosts.click();
	}

	public void openAllPages(){
		lnkAllPages.click();
	}
}
