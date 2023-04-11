package webdriver;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class Topic_05_Web_browser {
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
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_getUrl() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	    driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	    sleepInsecond(3);
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_getTitle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();;
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	@Test
	public void TC_03_Form() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInsecond(3);
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		sleepInsecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
	}
	@Test
	public void TC_04_getPagesource() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();;
		sleepInsecond(3);
		System.out.println(driver.findElement(By.cssSelector("div.page-title>h1")).getText());
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"LOGIN OR CREATE AN ACCOUNT");
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
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