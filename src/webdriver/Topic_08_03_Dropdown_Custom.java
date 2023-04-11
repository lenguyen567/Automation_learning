package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_03_Dropdown_Custom {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		expliciWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.id("speed-button")).click();
		// lấy đến thẻ chứa text
		expliciWait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		for (WebElement tempitem : speedDropdownItems) {
			String itemName = tempitem.getText();
			if (itemName.equals("Fast")) {
				tempitem.click();
				break;
			}

		}
	}

	@Test
	public void TC_02_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectIteminDropdown("speed-button", "ul#speed-menu div[role='option']", "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				"Faster");
		sleepInsecond(3);
		selectIteminDropdown("salutation-button", "ul#salutation-menu>li[class='ui-menu-item']>div[role='option']",
				"Prof.");
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),
				"Prof.");
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectIteminDropdown2("i.dropdown.icon", "div.menu span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Stevie Feliciano");
	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectIteminDropdown3("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		// assertTrue(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText().contains("Second
		// Option"));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}

	@Test
	public void TC_05_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
		List<WebElement> speedDropdownItems = expliciWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu a")));
		for (WebElement tempitem : speedDropdownItems) {
			if (tempitem.getText().equals("Second Option")) {
				tempitem.click();
				System.out.println(tempitem.getText());
				break;
			}
		}

	}
	@Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectandenterIteminDropdown("input.search","div.visible.menu.transition span","Angola");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Angola");
		

	}

	public void selectIteminDropdown(String parentID, String cssItemlist, String expectedItem) {
		driver.findElement(By.id(parentID)).click();
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssItemlist)));
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(cssItemlist));
		for (WebElement tempitem : speedDropdownItems) {
			String itemname = tempitem.getText();
			if (itemname.equals(expectedItem)) {
				tempitem.click();
				break;
			}

		}

	}

	public void selectIteminDropdown2(String parentID, String cssItemlist, String expectedItem) {
		driver.findElement(By.cssSelector(parentID)).click();
		List<WebElement> speedDropdownItems = expliciWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssItemlist)));
		for (WebElement tempitem : speedDropdownItems) {
			if (tempitem.getText().equals(expectedItem)) {
				tempitem.click();
				break;
			}

		}

	}

	public void selectIteminDropdown3(String parentID, String cssItemlist, String expectedItem) {
		driver.findElement(By.cssSelector(parentID)).click();
		List<WebElement> speedDropdownItems = expliciWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssItemlist)));
		for (WebElement tempitem : speedDropdownItems) {
			if (tempitem.getText().trim().equals(expectedItem)) {
				tempitem.click();
				System.out.println(tempitem.getText());
				break;
			}

		}

	}
	public void selectandenterIteminDropdown(String entercss, String cssItemlist, String expectedItem) {
		driver.findElement(By.cssSelector(entercss)).sendKeys(expectedItem);
		List<WebElement> speedDropdownItems = expliciWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssItemlist)));
		for (WebElement tempitem : speedDropdownItems) {
			if (tempitem.getText().trim().equals(expectedItem)) {
				tempitem.click();
				System.out.println(tempitem.getText());
				break;
			}

		}

	}

	public void sleepInsecond(long timeinSecond) {
		try {
			Thread.sleep(timeinSecond * 1000);
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