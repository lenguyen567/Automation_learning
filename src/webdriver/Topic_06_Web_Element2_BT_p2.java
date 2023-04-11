package webdriver;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.reporters.EmailableReporter;

public class Topic_06_Web_Element2_BT_p2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.id("mail");
	By ageunder18Radio = By.id("under_18");
	By education = By.cssSelector("#edu");
	By user5 = By.xpath("//h5[text()='Name: User5']");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			} else {
	 		 System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			}
		
		driver = new FirefoxDriver();
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public void sleepInSecond (long sleepinsecond) {
		try {
			Thread.sleep(sleepinsecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int Colorconvert2R (String Hexcolor) {
		
		return Color.decode(Hexcolor).getRed();
	}
	public int Colorconvert2B (String Hexcolor) {
		
		return Color.decode(Hexcolor).getBlue();
	}
	public int Colorconvert2G (String Hexcolor) {
		
		return Color.decode(Hexcolor).getGreen();
	}
	String specColorGreen = "rgb("+Colorconvert2R("#008547")+", "+Colorconvert2G("#008547")+", "+Colorconvert2B("#008547")+")";
	@Test
	public void TC_01_Web_element() {
		
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("#email")).sendKeys("ngthile5625@gmail.com");
//		driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
		sleepInSecond(4);
		Assert.assertTrue(driver.findElement(By.id("new_username")).getAttribute("class").contains("av-text success-check"));
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#new_password")).sendKeys("2");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char")).getCssValue("color"),specColorGreen);
		driver.findElement(By.cssSelector("#new_password")).sendKeys("a");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement((By.cssSelector("li.lowercase-char"))).getCssValue("color"), specColorGreen);
		
	}

	@Test
	public void TC_03_Form() {
		
	}
//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}