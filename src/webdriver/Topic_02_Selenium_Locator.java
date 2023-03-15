package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

//		driver = (WebDriver) new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Nguyen Thi Le");
	}

	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Automation");
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("advs")).click();
		
	}
	
	/*@Test
	public void TC_04_TagName() {
		System.out.println(driver.findElement(By.tagName("input")).size()); 
		
	} */
	
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Blog")).click();
		
	}
	
	
	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("viewed products")).click();
		
	}
	
	@Test
	public void TC_07_CSS() {
		
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Thi Le");
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Nguyen");
		//3
		driver.findElement(By.cssSelector("input[name='Password']")).sendKeys("abcd1234");
	}
	
	@Test
	public void TC_08_XPath() {
		
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Supremetech");
	}
	
	
	
	
//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}