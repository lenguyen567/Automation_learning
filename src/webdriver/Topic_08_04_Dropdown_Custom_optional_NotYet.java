package webdriver;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.tools.DocumentationTool.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.LongPressAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_04_Dropdown_Custom_optional_NotYet {
	WebDriver driver;
	WebDriverWait expliciWait;
	Actions action;
	JavascriptExecutor jsexExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Object wait;

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
		action = new Actions(driver);
		jsexExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//done
	public void TC_01_honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInsecond(2);
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		sleepInsecond(3);

		clicktoEmlement("//button[@class='btn dropdown-toggle']");
		clicktoEmlement("//a[text()='HR-V G (Đỏ cá tính/ trắng ngọc quý phái)']");
		sleepInsecond(2);
		new Select(driver.findElement(By.id("province"))).selectByVisibleText("Đồng Tháp");
		new Select(driver.findElement(By.id("registration_fee"))).selectByVisibleText("Khu vực II");
	}
	//Done
	
	public void TC_02_Honda_v2() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInsecond(2);
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		sleepInsecond(3);

//		clicktoEmlement("//button[@class='btn dropdown-toggle']");
		jsexExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath("//button[@class='btn dropdown-toggle']")));
		sleepInsecond(2);
		driver.findElement(By.xpath("//button[@class='btn dropdown-toggle']")).click();
		jsexExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath("//a[text()='HR-V G (Đỏ cá tính/ trắng ngọc quý phái)']")));
		sleepInsecond(2);
		driver.findElement(By.xpath("//a[text()='HR-V G (Đỏ cá tính/ trắng ngọc quý phái)']")).click();
//		clicktoEmlement("//a[text()='HR-V G (Đỏ cá tính/ trắng ngọc quý phái)']");
		sleepInsecond(2);
		new Select(driver.findElement(By.id("province"))).selectByVisibleText("Đồng Tháp");
		new Select(driver.findElement(By.id("registration_fee"))).selectByVisibleText("Khu vực II");
	}

	// not done yet - presence k thể click
	@Test
	public void TC_03_Explicit_Wait() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInsecond(2);
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		sleepInsecond(3);
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn dropdown-toggle']")));
		driver.findElement(By.xpath("//button[@class='btn dropdown-toggle']")).click();
		

	}

	public void selectitemindropdown(String parentCss, String itemcss, String expecteditem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		List<WebElement> listitemindropdown = driver.findElements(By.cssSelector(itemcss));
		for (WebElement tempitem : listitemindropdown) {
			if (tempitem.getText().equals(expecteditem)) {
				tempitem.click();
				break;
			}
		}
	}

	public void sleepInsecond(long timeinSecond) {
		try {
			Thread.sleep(timeinSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clicktoEmlement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}
//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}