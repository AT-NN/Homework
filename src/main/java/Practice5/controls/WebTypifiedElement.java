package Practice5.controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class WebTypifiedElement extends TypifiedElement {

	private Logger log = LogManager.getLogger(WebTypifiedElement.class.getSimpleName());
	
	public WebTypifiedElement(WebElement wrappedElement) {
		super(wrappedElement);
	}
	
	public void equals(String str) {
		checkIfDisplayed();
		log.info("Check content coincidence for " + getName());
		check2StringIfEquals(this, str);
	}

	@Override
	public void click() {
		log.info("Clicking on element " + getName());
		getWrappedElement().click();
	}

	public void checkIfDisplayed() {
		log.info("Element " + getName() + " is displayed");
		Assert.assertTrue(this.isDisplayed(), "Element " + getName() + " is displayed");
	}

	public void check2StringIfEquals(WebElement el, String str) {
		check2StringIfEquals(el.getText(), str);
	}

	public  void check2StringIfEquals(String str1, String str2) {
		log.info("Check 2 strings if equals\n\t1st:" + str1 + "\n\t2nd:" + str2);
		Assert.assertEquals(str1, str2, "Check 2 string if equals");
	}
}
