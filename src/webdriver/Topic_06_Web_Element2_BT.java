package webdriver;

import java.awt.Color;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import javax.swing.table.TableStringConverter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element2_BT {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextbox = By.id("mail");
	By ageunder18Radio = By.id("under_18");
	By education = By.cssSelector("#edu");
	By user5 = By.xpath("//h5[text()='Name: User5']");
	By Jobrole01 = By.id("job1");
	By devcheckbox = By.cssSelector("label[for='development']");
	By slide1 = By.id("slider-1");
	By disablePass = By.cssSelector("#disable_password");
	By biography = By.cssSelector("#bio");
	By language = By.cssSelector("#java");	

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
	String specColorGreen = "rgb("+Colorconvert2R("#008547")+", "+Colorconvert2G("#008547")+", "+Colorconvert2B("#008547")+")";
	/*
	public void checkValidate_lowercase() {
		
		if (driver.findElement(lowercase).getAttribute("class").contains("not-completed")) {
			Assert.assertEquals(driver.findElement(lowercase).getCssValue("color"),specColorBlack);	
		} else {
			Assert.assertEquals(driver.findElement(lowercase).getCssValue("color"),specColorGreen);
		}

	} */

	/*
	@Test
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("ngthile5625@gmail.com");
			System.out.println("Email textbox is displayed");
			} else {
				System.out.println("Email textbox is NOT displayed");
			}
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
	} */

	/*
	@Test
	public void TC_03_Enable() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(Jobrole01).isEnabled()) {
			System.out.println("Jobrole01 dropdown is enabled");
			} else {
				System.out.println("Jobrole01 dropdown is disabled");
			};
		
		if (driver.findElement(ageunder18Radio).isEnabled()==true) {
			System.out.println("Age radio button is enabled");
		} else {
			System.out.println("Age radio button is disabled");
		};
		
		if (driver.findElement(devcheckbox).isEnabled()) {
			System.out.println("Devcheckbox is enabled");
			
		} else {
			System.out.println("Devcheckbox is disabled");

		};
		if(driver.findElement(slide1).isEnabled()) {
			System.out.println("slide1 is enabled");	
		} else {
			System.out.println("slide1 is NOT enabled");
		};
		
		if (driver.findElement(biography).isEnabled()) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography is disabled");
		};
		if (driver.findElement(disablePass).isEnabled()) {
			System.out.println("Password is enabled");
		} else {
			System.out.println("Password is disabled");
		}
	} */
	/*
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(ageunder18Radio).click();
		driver.findElement(language).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(ageunder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(language).isSelected());		
		driver.findElement(language).click();
		Assert.assertFalse(driver.findElement(language).isSelected());		
		
	}*/
	@Test
	public void TC_04_combine() {
		
//		String specColorBlack = "rgb("+Colorconvert2R("#bdbbb9")+", "+Colorconvert2G("#bdbbb9")+", "+Colorconvert2B("#bdbbb9")+")";

		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("#email")).sendKeys("ngthile5625@gmail.com");
		
//		driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.id("new_username")).getAttribute("class").contains("av-text success-check"));
		driver.findElement(By.cssSelector("#new_password")).sendKeys("1");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.number-char")).getCssValue("color"), specColorGreen);
		
	}
		
	
	}
