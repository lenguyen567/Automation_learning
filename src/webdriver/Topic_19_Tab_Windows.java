package webdriver;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Tab_Windows {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js;
	WebDriverWait expclipWait;
	Alert alert;
	Actions action;

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
		js = (JavascriptExecutor) driver;
		expclipWait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Windows_Tab_1() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath("//legend[text()='Window']")));
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInsecond(2);
		String parentPageID = driver.getWindowHandle();
		Set<String> allpageIDs = driver.getWindowHandles();
		for (String ID : allpageIDs) {
			if (!ID.equals(parentPageID)) {
				driver.switchTo().window(ID);
			}
		}
		driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("Zingmp3");
		
	}

	public void TC_02_Windows_Tab_2() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.xpath("//legend[text()='Window']")));
		click("//a[text()='GOOGLE']");
//		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInsecond(2);
		switchtoexpectedPage("Google");
		driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("Zingmp3");
		sleepInsecond(2);
		switchtoexpectedPage("Selenium WebDriver");
		sleepInsecond(1);
//		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();	
		click("//a[text()='FACEBOOK']");
		sleepInsecond(3);
		switchtoexpectedPage("Facebook - log in or sign up");
		sleepInsecond(2);
		driver.findElement(By.id("email")).sendKeys("ngthile5625@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		sleepInsecond(1);
		switchtoexpectedPage("Selenium WebDriver");
		click("//a[text()='TIKI']");
		sleepInsecond(2);
		switchtoexpectedPage("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.cssSelector("input[class^='FormSearch']")).sendKeys("Cây cam ngọt");
		sleepInsecond(3);
		closeexpectedPage("Selenium WebDriver");
		
	}

	public void TC_03_Windows_Tab_Kyna() {
		driver.get("https://kyna.vn/");
		sleepInsecond(5);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna')]")));
		driver.findElement(By.cssSelector("div.pluginConnectButton a")).click();
		sleepInsecond(2);
		driver.switchTo().defaultContent();
		sleepInsecond(2);
		String arr[] = {"div.hotline div.social>a[href*='facebook']","div.hotline div.social>a[href*='youtube']","a[href*='HomePage']"};
		List<String> locatorList = Arrays.asList(arr);
		for (String locatoritem : locatorList) {
			clicktoelement(locatoritem);
			sleepInsecond(2);
		}
		switchtoexpectedPage("Kyna.vn - Home | Facebook");
		sleepInsecond(2);
		switchtoexpectedPage("Kyna.vn - YouTube");
		sleepInsecond(2);
		switchtoexpectedPage("Thông tin website thương mại điện tử - Online.Gov.VN");
		sleepInsecond(2);
		closeexpectedPage("Kyna.vn - Học online cùng chuyên gia");
		sleepInsecond(2);
		System.out.println(driver.getTitle());
	}
	
	public void TC_04_Windows_Tab_Livetechpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div/ul/li/a[@class='link-compare']")).click();
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		sleepInsecond(1);
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/ul/li/a[@class='link-compare']")).click();
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		sleepInsecond(2);
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		sleepInsecond(1);
		switchtoexpectedPage("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		closeexpectedPage("Mobile");
		System.out.println(driver.getTitle());
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInsecond(1);
		alert = expclipWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
	}
	
	@Test
	public void TC_05_Dictionary() {
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInsecond(2);
		String pID = driver.getWindowHandle();
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		sleepInsecond(5);
		switchtoexpectedPageID(pID);
		
		sleepInsecond(2);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//h2[text()='Log in with your email account']/following-sibling::div/input[@name='username']")).sendKeys("ngthile5625@gmail.com");
		sleepInsecond(2);
		driver.findElement(By.xpath("//h2[text()='Log in with your email account']/following-sibling::div/input[@name='password']")).sendKeys("12345678");
		sleepInsecond(2);
		closeexpectedPage("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInsecond(2);
		driver.findElement(By.id("searchword")).sendKeys("automatic");
		sleepInsecond(2);
		//Tap phím enter trên bàn phím
		driver.findElement(By.id("searchword")).sendKeys(Keys.RETURN);
		//keyDown/ Up : được dùng cho modifer key (Crtl, ALT, shift)
//		action.keyDown(Keys.RETURN);
//		action.perform();
	
		sleepInsecond(3);
		
	}

	
	public void click (String element) {
		driver.findElement(By.xpath(element)).click();
	}
	
	public void closeexpectedPage(String expectedPageTitle) {
		Set<String> allpageIDs = driver.getWindowHandles();
		for (String id : allpageIDs) {
			driver.switchTo().window(id);
			if (!driver.getTitle().equals(expectedPageTitle)) {
				driver.close();
				switchtoexpectedPage(expectedPageTitle);
			}

		}
	}
	
	public void switchtoexpectedPage(String expectedPageTitle) {
		Set<String> allpageIDs = driver.getWindowHandles();
		for (String id : allpageIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(expectedPageTitle)) {
				sleepInsecond(1);
				break;
			}	
		}
	}
	public void switchtoexpectedPageID (String parentID) {
		Set<String> allpageIDs = driver.getWindowHandles();
		for (String id : allpageIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
	}
	public void clicktoelement (String locator) {
		WebElement element = driver.findElement(By.cssSelector(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
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