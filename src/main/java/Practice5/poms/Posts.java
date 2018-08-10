package Practice5.poms;

import Practice5.controls.WebLink;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class Posts extends AbstractPOM {
	
	public Posts(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@class='wp-list-table widefat fixed striped posts']//tbody//tr")
	private List<WebLink> listPosts;

	public void clickPost(String postTitle){
		int postIndex;
		for (postIndex=0;postIndex<listPosts.size();postIndex++)
			if (listPosts.get(postIndex).findElements(By.xpath(".//*[contains(text(),'" + postTitle + "')]")).size()>0) break;
		listPosts.get(postIndex).findElement(By.xpath(".//td//strong//a")).click();
	}

	public boolean checkPostPresence(String postTitle, String status) {
		try {
			driver.findElement(By.xpath("//*[contains(text(),'" + postTitle + "')]/following-sibling::div[@class='_status' and contains(text(),'" + status + "')]"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
