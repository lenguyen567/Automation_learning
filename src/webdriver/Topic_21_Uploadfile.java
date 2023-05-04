package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Uploadfile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String nature = "nature-gd62b6c8bd_1920.jpg";
	String flower = "rosemary-gb324be761_1920.jpg";
	String sparrow = "sparrow-g1bb0453bc_1920.jpg";
	String apple = "apple-gc78430e0f_1920.jpg";
	
	String naturePath = projectPath + "/Upload file/"+nature;
	String flowerPath = projectPath + "/Upload file/" + flower;
	String sparrowPath = projectPath + "/Upload file/" + sparrow;
	String applePath = projectPath + "/Upload file/" + apple;
	
	JavascriptExecutor js;

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
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void TC_01_Upload_1_file() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(naturePath);
		sleepInsecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(flowerPath);
		sleepInsecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(sparrowPath);
		sleepInsecond(1);
		
		Assert.assertTrue(getElement("//p[text()='"+nature+"']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='"+flower+"']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='"+sparrow+"']").isDisplayed());
		
		List <WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInsecond(3);
		}
		
		Assert.assertTrue(getElement("//a[text()='"+nature+"']").isDisplayed());
		Assert.assertTrue(getElement("//a[text()='"+flower+"']").isDisplayed());
		Assert.assertTrue(getElement("//a[text()='"+sparrow+"']").isDisplayed());
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+nature+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+flower+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+sparrow+"')]"));
		
	}

	@Test
	public void TC_02_Upload_Multi_files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(naturePath+ "\n" +flowerPath + "\n" +sparrowPath + "\n"+applePath);
		sleepInsecond(1);
		
		Assert.assertTrue(getElement("//p[text()='"+nature+"']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='"+flower+"']").isDisplayed());
		Assert.assertTrue(getElement("//p[text()='"+sparrow+"']").isDisplayed());
		
		List <WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInsecond(3);
		}
		
		Assert.assertTrue(getElement("//a[text()='"+nature+"']").isDisplayed());
		Assert.assertTrue(getElement("//a[text()='"+flower+"']").isDisplayed());
		Assert.assertTrue(getElement("//a[text()='"+sparrow+"']").isDisplayed());
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+nature+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+flower+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+sparrow+"')]"));
		
		
	}

	@Test
	public void TC_03_Form() {
		
	}
	
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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