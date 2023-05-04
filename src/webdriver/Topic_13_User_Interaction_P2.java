package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicTreeUI.TreeTraverseAction;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.seleniumhq.jetty9.util.Scanner.BulkListener;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_User_Interaction_P2 {
	WebDriver driver;
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
		action = new Actions(driver);
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Click_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listnumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		action.clickAndHold(listnumber.get(0))
			.moveToElement(listnumber.get(7))
			.release()
			.perform();	
		sleepInsecond(2);
		List<WebElement> listselected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listselected.size(), 8);
			}

	@Test
	public void TC_02_Click_random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		Keys key = Keys.NULL;
		if (osName.contains("Windown")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listnumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		action.keyDown(key).perform();
		action.click(listnumber.get(1))
			.click(listnumber.get(4))
			.click(listnumber.get(6))
			.click(listnumber.get(5))
			.click(listnumber.get(10))
			.keyDown(key)
			.perform();
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