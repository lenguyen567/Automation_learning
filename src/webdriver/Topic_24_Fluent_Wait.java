package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator.ForTotal;

public class Topic_24_Fluent_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait<WebDriver> Fluentdriver;
	FluentWait<WebElement> fluentelement;

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

	public void TC_01_Fluent_wait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		Fluentdriver = new FluentWait<WebDriver>(driver);
		Fluentdriver.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(200))
				.ignoring(NoSuchElementException.class);

		WebElement startbutton = Fluentdriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {

				return driver.findElement(By.cssSelector("div#start>button"));
			}
		});
		startbutton.click();
	}
	
	public void TC_02_FluentwaitFunction() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		FluentFindElement("//div[@id='start']/button", 10, 200).click();
		Assert.assertEquals(FluentFindElement("//div[@id='finish']/h4", 10, 20).getText(), "Hello World!");
	}

	@Test
	public void TC_08_countdown() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement element = FluentFindElement("//div[@id='javascript_countdown_time']", 5, 100);
		
		fluentelement = new FluentWait<WebElement>(element);
		fluentelement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
		fluentelement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement element) {
				System.out.println(element.getText());
				return element.getText().endsWith("00");
			}
		});
		

	}

	public void sleepInsecond(long timeinSecond) {
		try {
			Thread.sleep(timeinSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// hàm fluent wait
	public WebElement FluentFindElement(String xpathlocator, long totaltime, long Pollingtime) {

		Fluentdriver = new FluentWait<WebDriver>(driver);
		Fluentdriver.withTimeout(Duration.ofSeconds(totaltime)).pollingEvery(Duration.ofMillis(Pollingtime))
				.ignoring(NoSuchElementException.class);
		return Fluentdriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathlocator));
			}
		});
	}

//close after open the page
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//	change repo;
}