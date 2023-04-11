package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Custom_checkbox_radio_button {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		jsExecutor =(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Url() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By radioButon = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		Assert.assertFalse(driver.findElement(radioButon).isSelected());
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButon));
		sleepInsecond(1);
		Assert.assertTrue(driver.findElement(radioButon).isSelected());
	}

	@Test
	public void TC_02_Logo() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By radiobutton = By.cssSelector("div[data-value='Đà Nẵng'][aria-checked='false']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Nam'][aria-checked='false']");
		Assert.assertTrue(driver.findElement(radiobutton).isDisplayed());
		Assert.assertTrue(driver.findElement(checkbox).isDisplayed());
		jsExecutor.executeScript(("arguments[0].click();"), driver.findElement(radiobutton));
		sleepInsecond(1);
		jsExecutor.executeScript(("arguments[0].click();"), driver.findElement(checkbox));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div[data-value='Đà Nẵng']")).getAttribute("aria-checked"), "true");
		//Assert.assertTrue(driver.findElement(checkbox).isDisplayed());
		
		
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