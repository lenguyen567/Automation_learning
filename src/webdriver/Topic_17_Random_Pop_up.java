package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.AbstractDocument.LeafElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Random_Pop_up {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	int emailRandom = new Random().nextInt(99);

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			} else {
//	 		 System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			}
		
//		driver = new FirefoxDriver();
		Map <String,Integer>prefs = new HashMap<String, Integer>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Random_Pop_up_not_in_DOM() {
		driver.get("https://dehieu.vn/");
		By login_pop_up = By.cssSelector("div.popup-content");
		//Check pop up k có trong DOM
		if (driver.findElement(login_pop_up).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Le Nguyen");
			driver.findElement(By.id("popup-email")).sendKeys("ngthile5625"+emailRandom+"@gmail.com");
			driver.findElement(By.id("popup-phone")).sendKeys("1234567890");
		}
		
		
	}

	@Test
	public void TC_02_Logo() {
	}

	@Test
	public void TC_03_Form() {
		
	}
	
	public void sleepInsecond (long timeinSecond) {
		try {
			Thread.sleep(timeinSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}