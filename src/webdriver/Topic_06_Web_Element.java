package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.reporters.EmailableReporter;

public class Topic_06_Web_Element {
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
	@Test
	public void TC_01_Web_element() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		WebElement element= driver.findElement(By.xpath("//h2[@class='product-name']/a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		System.out.println(element.getCssValue("background"));
		System.out.println(element.getSize());
		System.out.println(element.getLocation());
		System.out.println(element.getRect());
		// lấy ra cái tên thẻ tag name -> truyền vào cho 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		driver.findElement(By.cssSelector("#Email")).getTagName();
		// dùng getText: khi text ở ngoài
		//Dùng getAttribute khi text ở trong
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		//Tất cả element đều nằm trong thẻ form 
		// tương ứng với action enter của end user
		element.submit();
		
	}

	@Test
	public void TC_02_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("ngthile5625@gmail.com");
			System.out.println("Email textbox is displayed");
			};
		if (driver.findElement(ageunder18Radio).isDisplayed()==true) {
			driver.findElement(ageunder18Radio).click();
			System.out.println("Age radio button is displayed");
		}
		if (driver.findElement(education).isDisplayed()) {
			driver.findElement(education).sendKeys("University");
			System.out.println("Education textbox is displayed");
			
		}
		if(driver.findElement(user5).isDisplayed()==false) {
			System.out.println("Name: User5 textbox is NOT displayed");	
		}
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