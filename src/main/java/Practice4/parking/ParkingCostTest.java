package Practice4.parking;
/*
●	http://adam.goucher.ca/parkcalc/index.php
○	Test with TestNG DataProvider
*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class ParkingCostTest {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty ("webdriver.gecko.driver","drivers\\firefox\\0.19.1\\windows\\geckodriver.exe" );
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://adam.goucher.ca/parkcalc/");
    }

    @Test(dataProvider = "testCases")
    public void testParking(String lot,String entryTimeHour, String entryTimeSuffix,String entryDate,
                            String leavingTimeHour, String leavingTimeSuffix,String leavingDate,
                            String expectedCost, String expectedPeriodLength){
        ParkingPage parkingPage = new ParkingPage(driver);
        parkingPage.choose(lot);
        parkingPage.setEntry(entryTimeHour,entryTimeSuffix,entryDate);
        parkingPage.setLeaving(leavingTimeHour,leavingTimeSuffix,leavingDate);
        parkingPage.calculate();

        Assert.assertEquals(parkingPage.getParkingCost(),expectedCost, "Parking cost mismatch: ");
        Assert.assertEquals(parkingPage.getParkingPeriodLength(),expectedPeriodLength, "Parking period length mismatch: ");
    }

    @DataProvider
    public Object[][] testCases(){
        return new Object[][]{
                {"Short-Term Parking","10:00","AM","7/1/2018","9:00","PM","7/1/2018","$ 22.00","(0 Days, 11 Hours, 0 Minutes)"},
                {"Economy Parking","11:00","AM","7/2/2018","10:15","PM","7/3/2018","$ 18.00","(1 Days, 11 Hours, 15 Minutes)"},
                {"Long-Term Surface Parking","9:00","PM","7/3/2018","7:30","AM","7/7/2018","$ 40.00","(3 Days, 10 Hours, 30 Minutes)"},
                {"Long-Term Garage Parking","6:45","PM","7/4/2018","8:00","AM","7/10/2018","$ 72.00","(5 Days, 13 Hours, 15 Minutes)"},
                {"Valet Parking","12:00","AM","7/5/2018","12:00","AM","7/6/2018","$ 42.00","(1 Days, 0 Hours, 0 Minutes)"},

        };
    }

    @AfterTest
    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
