package Practice4.form;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
●	Test case to complete form
○	http://demoqa.com/registration/
 */

public class FormFillingTest {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty ("webdriver.gecko.driver","drivers\\firefox\\0.19.1\\windows\\geckodriver.exe" );
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        driver.get("http://demoqa.com/registration/");
    }

    @Test
    public void fillForm() throws InterruptedException {
        FormPage formPage = new FormPage(driver);
        formPage.setFirstName("Ivan");
        formPage.setLastName("Ivanov");
        formPage.setRandomMartialStatus();

        //clicking 3 times in Hobby section => min 1, max 3 hobbies will be checked
        formPage.setRandomHobby();
        formPage.setRandomHobby();
        formPage.setRandomHobby();

        formPage.setRandomCountry();
        formPage.setRandomDateOfBirth();

        String timeStamp = new SimpleDateFormat("MMddHHmmss").format(new Date());
        formPage.setPhoneNumber(timeStamp);
        formPage.setUsername("Ivanushka" + timeStamp);
        formPage.setEmail("iivanov" + timeStamp + "@practice4.com");

        formPage.addPicture(System.getProperty("user.dir") + "\\src\\main\\java\\Practice4\\form\\Ivanushka.jpg");
        formPage.setAboutInfo("I'm Ivanov Ivan");
        formPage.setPasswordAndConfirm("my_Password");
        Thread.sleep(5000);
        formPage.submit();
        Assert.assertEquals(formPage.getRegistrationConfirmation(),"Thank you for your registration","Registration as " + "Ivanushka" + timeStamp + " failed...");
   }

    @AfterTest
    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
