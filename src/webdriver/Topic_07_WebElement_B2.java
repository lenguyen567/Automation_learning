package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_WebElement_B2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email;
	Random rand;
	String firstname;
	String lastname,password,fullname ;

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
		rand = new Random();
		email = "ngthile5625+" + rand.nextInt(99) + "@gmail.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstname = "Le" + rand.nextInt(10);
		lastname = "Nguyen";
		fullname = firstname + " " + lastname;
		password = "abcd1234";
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_05_Create_new_user() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInsecond(2);
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInsecond(2);
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.success-msg")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(fullname));
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(email));
		driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
//		sleepInsecond(6);
//		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/");
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}

	@Test
	public void TC_06_Login() {
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInsecond(2);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(fullname));
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(email));
		
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