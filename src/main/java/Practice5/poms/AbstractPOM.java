package Practice5.poms;

import Practice5.controls.WebTextBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class AbstractPOM {
	
	protected WebDriver driver;
	private Logger log = LogManager.getLogger(AbstractPOM.class.getSimpleName());

	@FindBy(xpath = "//div[@class='wrap']/h1")
	protected WebTextBlock pageNameLabel;

	public AbstractPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
	}

	public void clickSubMenu(WebDriver driver, WebElement menu, WebElement submenu) {
		Actions action = new Actions(driver);
		String menuText = menu.getText();
		action.moveToElement(menu).pause(1000).build().perform();
		log.info("Clicking submenu: " + menuText + " -> " + submenu.getText());
		action.moveToElement(submenu).click().build().perform();
	}

	public void waitForAvailability(WebElement element) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.not(ExpectedConditions.attributeContains(element,"class","disabled")));
	}

	public WebTextBlock getPageTitle() {
		return pageNameLabel;
	}
}
