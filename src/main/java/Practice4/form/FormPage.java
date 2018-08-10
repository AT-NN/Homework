package Practice4.form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.security.SecureRandom;
import java.util.List;

public class FormPage {
    private WebDriver driver;
    private Select dropDown;
    SecureRandom sr = new SecureRandom();

    @FindBy(id = "name_3_firstname")
    private WebElement inputFirstName;

    @FindBy(id = "name_3_lastname")
    private WebElement inputLastName;

    @FindBy(name = "radio_4[]")
    private List<WebElement> radioMartialStatus;

    @FindBy(name = "checkbox_5[]")
    private List<WebElement> chbHobby;

    @FindBy(id = "dropdown_7")
    private WebElement ddownCountry;

    @FindBy(id = "mm_date_8")
    private WebElement ddownMonthOB;

    @FindBy(id = "dd_date_8")
    private WebElement ddownDayOB;

    @FindBy(id = "yy_date_8")
    private WebElement ddownYearOB;

    @FindBy(id = "phone_9")
    private WebElement inputPhoneNumber;

    @FindBy(id = "username")
    private WebElement inputUsername;

    @FindBy(id = "email_1")
    private WebElement inputEmail;

    @FindBy(id = "profile_pic_10")
    private WebElement btnBrowse;

    @FindBy(id = "description")
    private WebElement tareaAbout;

    @FindBy(id = "password_2")
    private WebElement inputPassword;

    @FindBy(id = "confirm_password_password_2")
    private WebElement inputConfirmPassword;

    @FindBy(name = "pie_submit")
    private WebElement btnSubmit;

    @FindBy(className = "piereg_message")
    private WebElement txtRegistrationConfirmation;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName){
        inputFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        inputLastName.sendKeys(lastName);
    }
    public void setRandomMartialStatus(){
        setRandomValue(radioMartialStatus);
    }

    public void setRandomHobby(){
        setRandomValue(chbHobby);
    }

    public void setRandomCountry(){
        Select dropDown = new Select(ddownCountry);
        setRandomValueFromDropDown(dropDown,true);
    }

    public void setRandomDateOfBirth(){
        Select dropDown = new Select(ddownMonthOB);
        setRandomValueFromDropDown(dropDown, false);
        dropDown = new Select(ddownDayOB);
        setRandomValueFromDropDown(dropDown,false);
        dropDown = new Select(ddownYearOB);
        setRandomValueFromDropDown(dropDown,false);
    }

    public void setPhoneNumber(String phoneNumber){
        inputPhoneNumber.sendKeys(phoneNumber);
    }

    public void setUsername(String username){
        inputUsername.sendKeys(username);
    }

    public void setEmail(String email){
        inputEmail.sendKeys(email);
    }

    public void addPicture(String filePath){
        btnBrowse.sendKeys(filePath);
    }

    public void setAboutInfo(String info){
        tareaAbout.sendKeys(info);
    }

    public void setPasswordAndConfirm(String password){
        inputPassword.sendKeys(password);
        inputConfirmPassword.sendKeys(password);
    }

    public void submit(){
        btnSubmit.click();
    }

    public String getRegistrationConfirmation(){
        if(txtRegistrationConfirmation.isDisplayed())return txtRegistrationConfirmation.getText();
        else return null;
    }

    private void setRandomValue(List<WebElement> list){
        int index = getRandomIndex(list, true);
        list.get(index).click();
    }
    private int getRandomIndex(List<WebElement> list, boolean includingFirst){
        int totalNumber = list.size();
        if (includingFirst == true) return sr.nextInt(totalNumber);
        else return 1 + sr.nextInt(totalNumber-1);
    }

    private void setRandomValueFromDropDown(Select dropDown, boolean includingFirst){
        int index = getRandomIndex(dropDown.getOptions(),includingFirst);
        dropDown.selectByIndex(index);
    }
}
