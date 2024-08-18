package AlmosaferHome;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomeTest {

	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://www.almosafer.com/en";

	@BeforeTest
	private void setUp() {

		driver.manage().window().maximize();
		driver.get(almosaferURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement saudiBtn = driver.findElement(By.className("cta__saudi"));
		saudiBtn.click();
	}

	@Test(priority = 1, enabled = true)
	private void checkDefaultLanguage() {

		WebElement htmlTag = driver.findElement(By.tagName("html"));

		String actualLanguage = htmlTag.getAttribute("lang");
		String expectLanguage = "en";

		Assert.assertEquals(actualLanguage, expectLanguage);
	}

	@Test(priority = 2, enabled = true)
	private void checkDefaultCurrency() {

		WebElement currencyBtn = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));

		String currencyText = currencyBtn.getText();

		String actualCurrency = currencyText;
		String expectCurrency = "SAR";

		Assert.assertEquals(actualCurrency, expectCurrency);

	}
	
	@Test(priority = 3, enabled = true)
	private void contactPhone() {
		
		WebElement contactNumber = driver.findElement(By.tagName("strong"));
		
		String actualNumber = contactNumber.getText();
		String expectNumber = "+966554400000";
		
		Assert.assertEquals(actualNumber, expectNumber);
		
	}

	@Test(priority = 4, enabled = true)
	private void qitafLogoDisplayed() {
		
		
		
	}
	
	@AfterTest
	private void cleanUp() {
		driver.quit();
	}
}
