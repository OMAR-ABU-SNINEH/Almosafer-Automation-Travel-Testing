package AlmosaferHome;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomeTest {

	WebDriver driver = new ChromeDriver();

	Random rand = new Random();

	String almosaferURLEnglish = "https://www.almosafer.com/en";
	String almosaferURLArabic = "https://www.almosafer.com/ar";

	@BeforeTest
	private void setUp() {

		driver.manage().window().maximize();
		driver.get(almosaferURLEnglish);
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

		WebElement footer = driver.findElement(By.tagName("footer"));
		WebElement qitafDiv = footer.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
		WebElement qitafLogo = qitafDiv.findElement(By.xpath(
				"//*[@data-testid ='Footer__QitafLogo']"));/*
															 * you can sure that in dev tool > check this command on
															 * console $x("//*[@data-testid ='Footer__QitafLogo']")
															 */

		boolean actualDisplayLogo = qitafLogo.isDisplayed();
		boolean expectDisplayLogo = true;

		Assert.assertEquals(actualDisplayLogo, expectDisplayLogo);

	}

	@Test(priority = 5, enabled = true)
	private void hotelsNotSelected() {

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String actualSelected = hotelTab.getAttribute("aria-selected");
		String expectSelected = "false";

		Assert.assertEquals(actualSelected, expectSelected);

	}

	@Test(priority = 6, enabled = true)
	private void flightDatesCheck() {

		WebElement theDiv = driver.findElement(By.cssSelector(".sc-jRhVzh.bAdWIl"));
		List<WebElement> flightDates = theDiv.findElements(By.cssSelector(".sc-cPuPxo.LiroG"));

		WebElement departureElementDate = flightDates.getFirst();
		WebElement arrivalElementDate = flightDates.getLast();

		String departureDateStr = departureElementDate.getText();
		String arrivalDateStr = arrivalElementDate.getText();

		int departureDate = Integer.parseInt(departureDateStr);// actual
		int arrivalDate = Integer.parseInt(arrivalDateStr);// actual

		LocalDate dateNow = LocalDate.now();

		int tomorrow = dateNow.plusDays(1).getDayOfMonth();// expected
		int theDayAfterTomorrow = dateNow.plusDays(2).getDayOfMonth();// expected

		Assert.assertEquals(departureDate, tomorrow);// Assert for departure flight
		Assert.assertEquals(arrivalDate, theDayAfterTomorrow);// Assert for arrival flight

	}

	@Test(priority = 7, enabled = true)
	private void switchLanguageRandomly() {

		String[] URLs = { almosaferURLEnglish, almosaferURLArabic };

		int randomlySwitchNum = rand.nextInt(URLs.length);

		String randomURLLang = URLs[randomlySwitchNum];

		driver.get(URLs[randomlySwitchNum]);

		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");

		String expectLang;

		if (randomlySwitchNum == 0) {
			expectLang = "en";
		} else {
			expectLang = "ar";
		}

		Assert.assertEquals(actualLang, expectLang);
	}

	@Test(priority = 8, enabled = true)
	private void switchToHotelTabAndSearch() {

		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelsTab.click();

		WebElement searchHotels = driver.findElement(By.xpath("//*[@data-testid='AutoCompleteInput']"));

		String[] cities;
		int randCityNum;

		if (driver.getCurrentUrl().contains("en")) {
			cities = new String[] { "Dubai", "Jeddah", "Riyadh" };
		} else {
			cities = new String[] { "دبي", "جدة" };
		}

		randCityNum = rand.nextInt(cities.length);

		searchHotels.sendKeys(cities[randCityNum]);

		WebElement locationsDropdown = driver.findElement(By.xpath("//ul[@data-testid='AutoCompleteResultsList']"));
		WebElement firstLocation = locationsDropdown
				.findElement(By.xpath("//*[@data-testid='AutoCompleteResultItem0']"));
		firstLocation.click();

	}

	@Test(priority = 9, enabled = true)
	private void reservationSelectedRandomly() {

		WebElement reservationDropdown = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select reservationSelect = new Select(reservationDropdown);

		int randNum = rand.nextInt(2);

		reservationSelect.selectByIndex(randNum);

		WebElement searchBtn = driver.findElement(By.xpath("//*[@data-testid='HotelSearchBox__SearchButton']"));
		searchBtn.click();
	}

	@Test(priority = 10, enabled = true)
	private void loadingFullyResult() throws InterruptedException {

		Thread.sleep(Duration.ofSeconds(10));
		WebElement resultsFoundMessage = driver.findElement(By.cssSelector(".sc-ctwKVn.imWanD"));

		/* 1 */

		boolean actualMessage = resultsFoundMessage.isDisplayed();
		boolean expectedMessage = true;

		Assert.assertEquals(actualMessage, expectedMessage);

		/* 2 */

//		String results= resultsFoundMessage.getText();
//		boolean finished = results.contains ("وجدنا") || results.contains("found");
//		
//		Assert.assertEquals(finished, "true");

	}

	@AfterTest
	private void cleanUp() {
		driver.quit();
	}
}

//Omar Abu Snineh :)