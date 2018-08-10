package Practice4.parking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParkingPage {
    WebDriver driver;
    private Select dropDown;

    @FindBy (id = "Lot")
    private WebElement parkingLot;

    @FindBy (id = "EntryTime")
    private WebElement entryTime;

    @FindBy (xpath = "//input[@name='EntryTimeAMPM' and @value='AM']")
    private WebElement entryPeriodAM;

    @FindBy (xpath = "//input[@name='EntryTimeAMPM' and @value='PM']")
    private WebElement entryPeriodPM;

    @FindBy (id = "EntryDate")
    private WebElement entryDate;

    @FindBy (id = "ExitTime")
    private WebElement leavingTime;

    @FindBy (xpath = "//input[@name='ExitTimeAMPM' and @value='AM']")
    private WebElement leavingPeriodAM;

    @FindBy (xpath = "//input[@name='ExitTimeAMPM' and @value='PM']")
    private WebElement leavingPeriodPM;

    @FindBy (id = "ExitDate")
    private WebElement leavingDate;

    @FindBy (name = "Submit")
    public WebElement btnCalculate;

    @FindBy (xpath = "//span[@class='SubHead']")
    private WebElement parkingCost;

    @FindBy (xpath = "//span[@class='BodyCopy']")
    private WebElement parkingPeriodLength;

    public ParkingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void choose (String option){
        dropDown = new Select(parkingLot);
        dropDown.selectByVisibleText(option);
    }

    private void setValue(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    public void setEntry (String hour, String suffix, String date){
        setValue(entryTime,hour);
        if (suffix == "AM") entryPeriodAM.click();
        else entryPeriodPM.click();
        setValue(entryDate,date);
    }

    public void setLeaving (String hour, String suffix, String date){
        setValue(leavingTime,hour);
        if (suffix == "AM") leavingPeriodAM.click();
        else leavingPeriodPM.click();
        setValue(leavingDate,date);
    }

    public void calculate(){
        btnCalculate.click();
    }

    public String getParkingCost() {
        return parkingCost.getText();
    }
    public String getParkingPeriodLength() {
        return parkingPeriodLength.getText().trim();
    }
}
