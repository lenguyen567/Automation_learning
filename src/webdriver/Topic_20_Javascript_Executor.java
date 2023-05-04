package webdriver;

import java.awt.Window;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.AbstractParallelWorker.Arguments;

public class Topic_20_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js;
	Random rand= new Random();
	int number;
	String pass = "12345678";
	WebDriverWait wait;

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
		wait = new WebDriverWait(driver, 20);
		number = rand.nextInt(999);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_JE() {
		navigatetoURl("http://live.techpanda.org/");
		sleepInsecond(2);
		//get domain
		String domain= (String)js.executeScript("return document.domain;");
		Assert.assertEquals(domain, "live.techpanda.org");
		
		Assert.assertEquals(getDomain(), "live.techpanda.org");
		// get url
		String URL = js.executeScript("return document.URL").toString();
		Assert.assertEquals(URL, "http://live.techpanda.org/");
		//click the mobie
		JSclick("//a[text()='Mobile']");
		//Click add to cart
		JSclick("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button");
		sleepInsecond(3);
		String confirmText = js.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(confirmText.contains("Samsung Galaxy was added to your shopping cart."));
		// click customer service
		JSclick("//a[text()='Customer Service']");
		sleepInsecond(2);
		String title = js.executeScript("return document.title").toString();
		Assert.assertEquals(title, "Customer Service");
		//Scroll to new leter textbox
		scrolltoelement("//input[@id='newsletter']");
		getElement("//input[@id='newsletter']").sendKeys("ngthile567+"+ number +"@gmail.com");
		sleepInsecond(2);
		JSclick("//div[@class='actions']/button[@type='submit']");
		sleepInsecond(3);
		navigatetoURl("http://demo.guru99.com/v4/");
		sleepInsecond(5);
		String domain1 = js.executeScript("return document.domain").toString();
		Assert.assertEquals(domain1, "demo.guru99.com");
	}

	public void TC_02_JS_validationMessage() {
		//Step01
		navigatetoURl("https://automationfc.github.io/html5/index.html");
		sleepInsecond(2);
		String namelocator = "//input[@id='fname']";
		String passlocator = "//input[@id='pass']";
		String emaillocator = "//input[@id='em']";
		String buttonlocator = "//input[@name='submit-btn']";	
		String addresslocator = "//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select";
		//step02;
		JSclick(buttonlocator);
		sleepInsecond(2);
		Assert.assertEquals(valiationMessage(namelocator),"Please fill out this field.");
		//Step 03: input name
		getElement(namelocator).sendKeys("Lee");
		JSclick(buttonlocator);
		sleepInsecond(2);
		Assert.assertEquals(valiationMessage(passlocator),"Please fill out this field.");
		//step04
		getElement(passlocator).sendKeys("12345678");
		JSclick(buttonlocator);
		sleepInsecond(2);
		Assert.assertEquals(valiationMessage(emaillocator),"Please fill out this field.");
		//step05
		getElement(emaillocator).sendKeys("1234@#$");
		JSclick(buttonlocator);
		sleepInsecond(2);
		Assert.assertEquals(valiationMessage(emaillocator),"Please enter an email address.");
		//step6
		getElement(emaillocator).clear();
		sleepInsecond(2);
		getElement(emaillocator).sendKeys("12345@222");
		JSclick(buttonlocator);
		sleepInsecond(2);
		Assert.assertEquals(valiationMessage(emaillocator),"Please match the requested format.");
//		Please match the requested format.
		//step07
		getElement(emaillocator).clear();
		sleepInsecond(2);
		getElement(emaillocator).sendKeys("ngthile567+"+number+"@gmail.com");
		JSclick(buttonlocator);
		sleepInsecond(2);
		Assert.assertEquals(valiationMessage(addresslocator),"Please select an item in the list.");
		
		
	}

	@Test
	public void TC_03_JS_Create_account() {
		navigatetoURl("http://live.techpanda.org/");
		sleepInsecond(2);
		JSclick("//div[@id='header-account']//a[text()='My Account']");
		sleepInsecond(2);
		JSclick("//span[text()='Create an Account']");
		sleepInsecond(2);
		//input thoong tin đăng ký
		getElement("//input[@id='firstname']").sendKeys("Lee"+number);
		getElement("//input[@id='lastname']").sendKeys("Nguyen"+number);
		getElement("//input[@id='email_address']").sendKeys("ngthile5625+"+number+"@gmail.com");
		getElement("//input[@id='password']").sendKeys(pass);
		getElement("//input[@id='confirmation']").sendKeys(pass);
		JSclick("//span[text()='Register']");
		sleepInsecond(2);
		String confirmText = js.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(confirmText.contains("Thank you for registering with Main Website Store."));
		
		JSclick("//div[@id='header-account']//a[text()='Log Out']");
	
		By logo = By.xpath("//div[@class='page-title']//img[contains(@src,'logo')]");
		sleepInsecond(7);
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(logo));
//		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.findElement(logo).isDisplayed());	
		
	}
	
	public String getDomain () {
		return (String) js.executeScript("return document.domain");
	}
	
	public void scrolltoelement (String locator) {
		js.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public void navigatetoURl (String url) {
		js.executeScript("window.location ='"+url+"'");
	}
	
	public void JSclick (String locator) {
		js.executeScript("arguments[0].click();", getElement(locator));
	}
	public String valiationMessage (String locator) {
		return (String) js.executeScript("return arguments[0].validationMessage",getElement(locator));
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