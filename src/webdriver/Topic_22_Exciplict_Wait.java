package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Exciplict_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	String nature = "nature-gd62b6c8bd_1920.jpg";
	String flower = "rosemary-gb324be761_1920.jpg";
	String sparrow = "sparrow-g1bb0453bc_1920.jpg";
	String apple = "apple-gc78430e0f_1920.jpg";
	
	String naturePath = projectPath + "/Upload file/"+nature;
	String flowerPath = projectPath + "/Upload file/" + flower;
	String sparrowPath = projectPath + "/Upload file/" + sparrow;
	String applePath = projectPath + "/Upload file/" + apple;

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
		explicitWait = new WebDriverWait(driver, 15);
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
			}

	
	public void TC_01_Explicit_Wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	
	public void TC_02_BT6() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// wait cho calendar hiện ra
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		By locator = By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span");
		Assert.assertEquals(driver.findElement(locator).getText(), "No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='16']")).click();
		// wait cho icon loading biến mất, calendar updale lại
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		// wait ngày click được hiển thị , khi icon loading chưa mất thì nó đã hiển thị rồi, nên phải verify là clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='16']")));
//		no need this step
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='16']")).isDisplayed());
		Assert.assertEquals(driver.findElement(locator).getText(), "Tuesday, May 16, 2023");
	}
	
	public void TC_03_BT6_V2() {
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		WebElement element = driver.findElement(By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span"));
		Assert.assertEquals(element.getText(), "No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='16']")).click();
		
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
//		element biến mất đồng nghĩa việc icon loading đã load xong, calendar đã được update lại
		explicitWait.until(ExpectedConditions.stalenessOf(element));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='16']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='16']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//legend[text()='Selected Dates:']/following-sibling::div/span")).getText(), "Tuesday, May 16, 2023");	
	}
	
	public void TC_04_BT7_upload_file() {
		driver.get("https://gofile.io/uploadFiles");
		By addbutton = By.xpath("//input[@type='file']");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add files']")));
		driver.findElement(addbutton).sendKeys(naturePath+ "\n" +flowerPath + "\n" +sparrowPath + "\n"+applePath);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainUpload']//span[contains(text(),'remaining files')]/span")));
		int numberofImages = Integer.parseInt(driver.findElement(By.xpath("//div[@id='mainUpload']//span[contains(text(),'remaining files')]/span")).getText());
		System.out.println(numberofImages);
		for (int i = 1; i <= numberofImages; i++) {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col mainUploadFilesListDetails']//span[@class='text-success ms-1'])["+i+"]")));
		}	
		// c2
		// explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='col mainUploadFilesListDetails']//span[@class='text-success ms-1']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div.alert.border-success")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.mainUploadSuccess div.alert.border-success")).getText(), "Your files have been successfully uploaded");
	}

	public void TC_05_BT7_UPLOAD_FILE_2() {
		driver.get("https://gofile.io/uploadFiles");
		By addbutton = By.xpath("//input[@type='file']");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add files']")));
		driver.findElement(addbutton).sendKeys(naturePath+ "\n" +flowerPath);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainUpload']//span[contains(text(),'remaining files')]/span")));
//		int numberofImages = Integer.parseInt(driver.findElement(By.xpath("//div[@id='mainUpload']//span[contains(text(),'remaining files')]/span")).getText());
//		System.out.println(numberofImages);
//		for (int i = 1; i <= numberofImages; i++) {
//			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col mainUploadFilesListDetails']//span[@class='text-success ms-1'])["+i+"]")));
//		}	
//		nếu dùng invisibility progress bar thì k cần đk chờ text success thành công, 
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.mainUploadFilesListDetails  div.progress"))));
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccess div.alert.border-success")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.mainUploadSuccess div.alert.border-success")).getText(), "Your files have been successfully uploaded");
	}
	
	
	public void sleepInsecond (long timeinSecond) {
		try {
			Thread.sleep(timeinSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getDatetime() {
		Date date = new Date() ;
		return date.toString();
	}
//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}