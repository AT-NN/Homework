package Practice5.poms;

import Practice5.controls.WebButton;
import Practice5.controls.WebTextBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class Preview extends AbstractPOM{

    public Preview(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//h1[@class='entry-title']")
    private WebTextBlock txtEntryTitle;

    @FindBy(xpath="//div[@class='entry-content']")
    private WebTextBlock txtEntryContent;

    @FindBy(xpath="//a[@class='post-edit-link']")
    private WebButton btnEditEntry;

     public void validate (String entryTitle, String entryContent){

         String currentWindow = driver.getWindowHandle();
         String previewWindow = "";
         Set<String> allWindows = driver.getWindowHandles();
         for (String s:allWindows)
             if (!s.equals(currentWindow)) previewWindow = s;
         driver.switchTo().window(previewWindow);

         this.txtEntryTitle.equals(entryTitle);
         this.txtEntryContent.equals(entryContent);

         btnEditEntry.click();

    }
}
