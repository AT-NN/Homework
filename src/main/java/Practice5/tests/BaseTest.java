package Practice5.tests;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
	
	private Logger log;
	
	WebDriver driver;

	configuration conf;
	String practice5Folder = System.getProperty("user.dir") + "\\src\\main\\java\\Practice5\\";
	String confFile = practice5Folder + "config.json";
	String logFileConfig = practice5Folder + "log4j2.xml";
	String screenshotsFolder = practice5Folder + "screenshots\\";
	String logsFolder = practice5Folder + "logs\\";

	SimpleDateFormat sdf = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");

	String url;
	String user;
	String password;
	String browser;

	@BeforeSuite
	void clearAllFolders() {
		clearFolder(logsFolder);
		clearFolder(screenshotsFolder);
	}

	void clearFolder(String folderName) {
		File folder = new File(folderName);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File f : files) f.delete();
		}
	}

	@BeforeTest
	public void setupEnvironment() throws IOException {
		System.setProperty("log4j2.configurationFile",logFileConfig);
		System.setProperty("logFilename",logsFolder + getClass().getSimpleName() + sdf.format(new Date()) + ".log");
		log = LogManager.getLogger(BaseTest.class.getSimpleName());
		log.info(getClass().getSimpleName());

		ObjectMapper om = new ObjectMapper();
		conf = om.readValue(new File(confFile),configuration.class);
		url = conf.getUrl();
		user = conf.getUser();
		password = conf.getPassword();
		browser = conf.getBrowser();

		if ("Firefox".equals(browser)) {
			System.setProperty ("webdriver.gecko.driver","drivers\\firefox\\0.19.1\\windows\\geckodriver.exe" );
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("security.insecure_password.ui.enabled", false);
			profile.setPreference("security.insecure_field_warning.contextual.enabled", false);
			DesiredCapabilities capabillities = new DesiredCapabilities().firefox();
			capabillities.setCapability(FirefoxDriver.PROFILE , profile);
			driver = new FirefoxDriver(capabillities);
		}
		else {
			if ("Chrome".equals(browser)) {
					System.setProperty ("webdriver.chrome.driver","drivers\\chrome\\2.40\\windows\\chromedriver.exe" );
					driver = new ChromeDriver();
			}
			else {
				System.out.println("Unknown browser");
				System.exit(1);
			}
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@BeforeTest (dependsOnMethods = "setupEnvironment")
	public void openAndNavigate() {
		log.info("Open browser " + conf.getBrowser());
		log.info("Navigate to: " + url);
		driver.get(url);
	}
	
	@AfterTest
	void quit() throws InterruptedException {
		System.out.println("Please check log file in " + System.getProperty("logFilename"));
		Thread.sleep(5000);
		LogManager.shutdown();
		driver.quit();
	}

	@AfterMethod
	void checkMethodResult(ITestResult testResult) {
		String timeStamp = sdf.format(new Date());
		String failedTest = testResult.getTestClass().getRealClass().getSimpleName();
		if (testResult.getStatus() == ITestResult.FAILURE) {
			takeScreenshot(timeStamp,failedTest);
			log.error(String.format("%s FAILED.\nPlease check screenshot file in %s\n",failedTest,screenshotsFolder));
		}
	}

	private void takeScreenshot(String timeStamp, String testName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = "screenshot-for-test[" + testName + "]-taken" + timeStamp + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(screenshotsFolder + fileName));
		} catch (IOException e) {
			System.out.println("Failed to take screenshot " + fileName);
			e.printStackTrace();
		}
	}
}
class configuration{
	String url;
	String user;
	String password;
	String browser;

	public configuration() {
	}

	public configuration(String url, String user, String password, String browser) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.browser = browser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
