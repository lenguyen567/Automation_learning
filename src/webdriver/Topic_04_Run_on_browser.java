package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class Topic_04_Run_on_browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test
	public void TC_01_Chrome() {
		if (osName.contains("Windows")) {
			
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			
			} else {
				
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			}
		
		driver = new ChromeDriver();
		
		driver.get("https://tinhte.vn/");
		
		driver.quit();
		
	}

	@Test
	public void TC_02_Firefox() {
		if (osName.contains("Windows")) {
			
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

			} else {
	 		 System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//				
			}
		
		driver = new FirefoxDriver();
		
		driver.get("https://tinhte.vn/");
		
		driver.quit();
		
			
		
	}

	@Test
	public void TC_03_Edge() {
		
		if (osName.contains("Windows")) {
			
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");

			} else {
	 		 System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
//				
			}
		
		driver = new EdgeDriver();
		
		driver.get("https://tinhte.vn/");
		
		driver.quit();

	}
}
