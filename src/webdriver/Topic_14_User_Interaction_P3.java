package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicTreeUI.TreeTraverseAction;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.util.Scanner.BulkListener;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_User_Interaction_P3 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	WebDriverWait excpliWait;
	Actions action;
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
		action = new Actions(driver);
		excpliWait = new WebDriverWait(driver,10);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	

	
	public void TC_03_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Scroll to the element to click
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
//		sleepInsecond(2);
//		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
//		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}

	public void TC_04_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInsecond(1);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Cut']")))
			.click(driver.findElement(By.xpath("//span[text()='Cut']")))
			.perform();
		sleepInsecond(1);
//		alert = excpliWait.until(ExpectedConditions.alertIsPresent());
//		Assert.assertEquals(alert.getText(), "clicked: cut");
//		alert.accept();
		driver.switchTo().alert().accept();
		
	}
	
	@Test
	public void TC_05_Drag_move () {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallcircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement Bigcircle = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(smallcircle, Bigcircle).perform();
		sleepInsecond(1);
		Color smallCirclebackgroundcoColor = Color.fromString(driver.findElement(By.cssSelector("div#draggable")).getCssValue("background-color"));
		Color BigCirclebackgroundcoColor = Color.fromString(driver.findElement(By.cssSelector("div#draggable")).getCssValue("background-color"));
		Assert.assertEquals(smallCirclebackgroundcoColor.asHex().toUpperCase(), BigCirclebackgroundcoColor.asHex().toUpperCase());		
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