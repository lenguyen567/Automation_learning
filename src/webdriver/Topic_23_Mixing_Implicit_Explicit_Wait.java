package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Mixing_Implicit_Explicit_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	String nature = "nature-gd62b6c8bd_1920.jpg";
	String flower = "rosemary-gb324be761_1920.jpg";
	String naturePath = projectPath + "/Upload file/"+nature;
	String flowerPath = projectPath + "/Upload file/" + flower;

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
		
		driver.manage().window().maximize();

	}


	public void TC_01_Not_Found_element_impplicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		System.out.println("Start:"+getDatetime());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("End:"+getDatetime());
		}
		
	}

	
	public void TC_02_Not_found_element_ex_implicitwait() {
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
//		System.out.println("Start:"+getDatetime());
//		try {
//			driver.findElement(By.cssSelector("input#selenium"));
//		} catch (Exception e) {
//			System.out.println("End:"+getDatetime());
//		}
		
		System.out.println("Start_ex:"+getDatetime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("End_ex:"+getDatetime());
		}
	}
	
 
	public void TC_02_Not_found_element_explicittwaitBy() {
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
//		System.out.println("Start:"+getDatetime());
//		try {
//			driver.findElement(By.cssSelector("input#selenium"));
//		} catch (Exception e) {
//			System.out.println("End:"+getDatetime());
//		}
		
		System.out.println("Start_ex:"+getDatetime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("End_ex:"+getDatetime());
		}
	}
	
	public void TC_05_Not_found_element_explicittwaitElement() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
//		System.out.println("Start:"+getDatetime());
//		try {
//			driver.findElement(By.cssSelector("input#selenium"));
//		} catch (Exception e) {
//			System.out.println("End:"+getDatetime());
//		}
		
		System.out.println("Start_ex:"+getDatetime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("End_ex:"+getDatetime());
		}
	}
	
	public void TC_06_real() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email"))));
		driver.findElement(By.cssSelector("input#email")).sendKeys("abcd");
		
	}
	@Test
	public void TC_06_VisibilityOfwithoutimplicitWait() {
//		case này muốn pass phải thêm implicitWait, bổi vì findelement k có implicit wait sẽ =0 -> hàm visibilityOf fail
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://gofile.io/uploadFiles");
		By addbutton = By.xpath("//input[@type='file']");
		System.out.println(getDatetime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Add files']"))));
		} catch (Exception e) {
			System.out.println(getDatetime());
		}
	
		driver.findElement(addbutton).sendKeys(naturePath+ "\n" +flowerPath);
	}

	public void sleepInsecond(long timeinSecond) {
		try {
			Thread.sleep(timeinSecond * 1000);
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