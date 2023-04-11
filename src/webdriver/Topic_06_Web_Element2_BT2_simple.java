package webdriver;

import java.awt.Color;
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

public class Topic_06_Web_Element2_BT2_simple {
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
	
	
	@Test
	public void TC_04_combine() {
		String specColorGreen = "rgb("+Colorconvert2R("#008547")+", "+Colorconvert2G("#008547")+", "+Colorconvert2B("#008547")+")";

		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("#email")).sendKeys("ngthile5625@gmail.com");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#new_password")).sendKeys("1");
		sleepInSecond(2);
//		nhập số
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
//		Assert.assertEquals(driver.findElement(By.cssSelector("li[class='number-char completed']")).getCssValue("color"), specColorGreen);
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
//		nhập chữ
		driver.findElement(By.cssSelector("#new_password")).sendKeys("ab");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
//		nhập chữ in hoa
		driver.findElement(By.cssSelector("#new_password")).sendKeys("AA");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	}
		
	
	}
