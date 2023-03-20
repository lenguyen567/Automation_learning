package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_CSS {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
 		 System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new FirefoxDriver();
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Register_with_empty_data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	     
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Register_with_invalid_email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.cssSelector("input[id='txtFirstname']")).sendKeys("Bella");
		driver.findElement(By.cssSelector("input[id='txtEmail']")).sendKeys("1234@.com");
		driver.findElement(By.cssSelector("input[id='txtCEmail']")).sendKeys("1234@.com");
		driver.findElement(By.cssSelector("input[id='txtPassword']")).sendKeys("abcd1234");
		driver.findElement(By.cssSelector("input[id='txtCPassword']")).sendKeys("abcd1234");
		driver.findElement(By.cssSelector("input[id='txtPhone']")).sendKeys("0373706904");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		
		
	}

	@Test
	public void TC_03_Register_with_incorrect_phone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.cssSelector("input[id='txtFirstname']")).sendKeys("Bella");
		driver.findElement(By.cssSelector("input[id='txtEmail']")).sendKeys("ngthile5625@gmail.com");
		driver.findElement(By.cssSelector("input[id='txtCEmail']")).sendKeys("ngthile5625@gmail.com");
		driver.findElement(By.cssSelector("input[id='txtPassword']")).sendKeys("abcd1234");
		driver.findElement(By.cssSelector("input[id='txtCPassword']")).sendKeys("abcd1234");
		driver.findElement(By.cssSelector("input[id='txtPhone']")).sendKeys("073706904");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.cssSelector("input[id='txtPhone']")).sendKeys("37370690004");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		
	}
//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}