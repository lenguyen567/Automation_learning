package webdriver;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_04_Dropdown_Custom_optional {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Object wait;

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

	@Test
	public void TC_01_honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInsecond(2);
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		sleepInsecond(3);
		selectitemindropdown("button.dropdown-toggle", "div.dropdown-menu>a", "CIVIC E (Trắng ngọc)");
		driver.findElement(By.id("province")).click();
		Actions movetoitem = new Actions(driver);
		movetoitem.moveToElement(driver.findElement(By.xpath("//select[@id='province']/option[text()='Hà Nội']")));
		movetoitem.perform();
		driver.findElement(By.xpath("//select[@id='province']/option[text()='Hà Nội']")).click();
//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='province']/option[text()='Hà Nội']")));
		
//		Assert.assertEquals(new Select(driver.findElement(By.id("province"))).getFirstSelectedOption(), "Hà Nội");
//		Assert.assertEquals(new Select(driver.findElement(By.id("registration_fee"))).getFirstSelectedOption(), "Khu vực II");
		}



	

	@Test
	public void TC_02_Logo() {
	}

	@Test
	public void TC_03_Form() {
		
	}
	public void selectitemindropdown(String parentCss,String itemcss,String expecteditem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		List<WebElement> listitemindropdown = driver.findElements(By.cssSelector(itemcss));
		for (WebElement tempitem : listitemindropdown) {
			if (tempitem.getText().equals(expecteditem)) {
				tempitem.click();
				break;		
			}
		}
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