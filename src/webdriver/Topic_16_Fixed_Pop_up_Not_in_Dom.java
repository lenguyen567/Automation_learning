package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

public class Topic_16_Fixed_Pop_up_Not_in_Dom {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	public void TC_01_Fix_Pop_up_not_in_DOM() {
		driver.get("https://www.facebook.com/");
		sleepInsecond(2);
		By login_pop_up = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		//Check pop up k có trong DOM
		Assert.assertEquals(driver.findElements(login_pop_up).size(), 0);
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInsecond(2);
//		Assert.assertEquals(driver.findElements(login_pop_up).size(), 1);
		Assert.assertTrue(driver.findElement(login_pop_up).isDisplayed());
		driver.findElement(By.name("firstname")).sendKeys("Le");
		driver.findElement(By.name("lastname")).sendKeys("Nguyen");
		driver.findElement(By.name("reg_email__")).sendKeys("123456789");
		driver.findElement(By.name("reg_passwd__")).sendKeys("abcd1234");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Sep");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("20");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1997");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInsecond(3);
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElements(login_pop_up).size(), 0);
		
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