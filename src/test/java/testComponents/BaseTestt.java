package testComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageClasses.LandingPage;

public class BaseTestt {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	// protected WebDriver driver;
	protected LandingPage landingPage;

	public WebDriver BrowserSetup() {

		WebDriver webDriver = new EdgeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.set(webDriver);

		return webDriver;

	}

	@BeforeMethod
	public void launchApplication() {
		WebDriver webDriver = BrowserSetup();

		webDriver.get("https://rahulshettyacademy.com/client/#/auth/login");
		landingPage = new LandingPage(webDriver);

	}

	public WebDriver getDriver() {
		return driver.get();
	}

	@AfterMethod
	public void teardown() {

		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
