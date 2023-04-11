package webdriver;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.awt.Color;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Frozen;

public class Topic_09_Button {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/*
	 * @Test public void TC_01_Button() {
	 * driver.get("https://www.fahasa.com/customer/account/create");
	 * driver.findElement(By.cssSelector("li.popup-login-tab-login")).click(); By
	 * loginbutton = By.cssSelector("button.fhs-btn-login");
	 * Assert.assertFalse(driver.findElement(loginbutton).isEnabled());
	 * driver.findElement(loginbutton).getCssValue("background-color");
	 * System.out.println(driver.findElement(loginbutton).getCssValue(
	 * "background-color"));
	 * driver.findElement(By.id("login_username")).sendKeys("0373706904");
	 * driver.findElement(By.id("login_password")).sendKeys("1234567890le"); String
	 * loginbuttonbackground =
	 * driver.findElement(loginbutton).getCssValue("background-color"); Color
	 * loginbuttonbackgroundColor = Color.fromString(loginbuttonbackground); assert
	 * loginbuttonbackgroundColor.asHex().toUpperCase().equals("#C92127"); 
	 */
	@Test
	public void TC_02_Radiobutton_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		By radio = By.xpath("//label[contains(text(),'1-2 days')]/preceding-sibling::input;");
		if (!driver.findElement(radio).isSelected()) {
			driver.findElement(radio).click();
		}
		Assert.assertTrue(driver.findElement(radio).isSelected());
		sleepInsecond(1);
		
		List<WebElement> allcheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		String arr[] = { "Asthma", "Digestive Problems", "Neurological Disorders" };
		List<String> listexpecteditems = Arrays.asList(arr);
		for (WebElement checkbox : allcheckboxes) {
			if (listexpecteditems.contains(checkbox.getAttribute("value"))) {
				checkbox.click();
				Assert.assertTrue(checkbox.isSelected());
			}
		}
	}

	@Test
	public void TC_03_Form() {

	}
	

	public void sleepInsecond(long timeinSecond) {
		try {
			Thread.sleep(timeinSecond * 1000);
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