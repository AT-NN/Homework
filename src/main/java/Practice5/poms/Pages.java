package Practice5.poms;

import Practice5.controls.WebLink;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class Pages extends AbstractPOM {

	public Pages(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@class='wp-list-table widefat fixed striped pages']//tbody//tr")
	private List<WebLink> listPages;

	public void clickPage(String pageTitle){
		int pageIndex;
		for (pageIndex=0;pageIndex<listPages.size();pageIndex++)
			if (listPages.get(pageIndex).findElements(By.xpath(".//*[contains(text(),'" + pageTitle + "')]")).size()>0) break;
		listPages.get(pageIndex).findElement(By.xpath(".//td//strong//a")).click();
	}

	public boolean checkPagePresence(String pageTitle, String status) {
		try {
			driver.findElement(By.xpath("//*[contains(text(),'" + pageTitle + "')]/following-sibling::div[@class='_status' and contains(text(),'" + status + "')]"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
