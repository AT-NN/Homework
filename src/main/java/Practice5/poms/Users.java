package Practice5.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Users extends AbstractPOM {

	public Users(WebDriver driver) {
		super(driver);
	}

	public boolean checkUserPresence(String username) {
		try {
			driver.findElement(By.xpath("//*[contains(text(),'" + username + "')]"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
