package AlmosaferHome;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomeTest {
	
	WebDriver driver = new ChromeDriver();
	@BeforeTest
	private void setUp() {
		driver.manage().window().maximize();
		driver.get("https://almosafer.com/en");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	private void publ() {

	}
}
