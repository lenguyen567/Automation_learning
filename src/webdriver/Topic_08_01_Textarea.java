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

public class Topic_08_01_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String lastname,firstname,username,password, employID;
	Random rand;
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
//		lastname = "Nguyen";
//		firstname = "Lee";
		username ="Leenguyen"+ rand.nextInt(99);
		password ="Aa12345@&";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/*
	@Test
	public void TC_01_Textarea() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		sleepInsecond(2);
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInsecond(5);
		driver.findElement(By.xpath("//a[contains(@href,'viewPimModule')]")).click();
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInsecond(4);
		driver.findElement(By.name("firstName")).sendKeys("Lee");
		driver.findElement(By.name("lastName")).sendKeys("Nguyen");
		
		driver.findElement(By.cssSelector("span[class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
		employID =driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//contains(string,'')
		sleepInsecond(8);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Lee");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Nguyen");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employID );
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();
		sleepInsecond(3);
		driver.findElement(By.xpath("//label[contains(string(),'Number')]/parent::div/following-sibling::div/input")).sendKeys("1111234567890");
		driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys("Check automation!\nLearning automation");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInsecond(5);
		driver.findElement(By.xpath("//i[contains(@class,'pencil-fill')]")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(string(),'Number')]/parent::div/following-sibling::div/input")).getAttribute("value"), "1111234567890");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), "Check automation!\nLearning automation");
		driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInsecond(5);
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInsecond(4);
		driver.findElement(By.xpath("//a[contains(@href,'viewMyDetails')]")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Lee");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Nguyen");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employID );
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInsecond(5);
		driver.findElement(By.xpath("//i[contains(@class,'pencil-fill')]")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(string(),'Number')]/parent::div/following-sibling::div/input")).getAttribute("value"), "1111234567890");
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), "Check automation!\nLearning automation");
			
	} */

	@Test
	public void TC_02_Logo() {
		driver.get("http://demo.guru99.com/v4");
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