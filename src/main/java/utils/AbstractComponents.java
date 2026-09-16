package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponents(WebDriver driver) {
//super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	By AllProducts = By.cssSelector(".mb-3");
	By VisibletoastContainer = By.id("toast-container");
	By InvisibletoastContainer = By.id("toast-container");

	@FindBy(css = "li.totalRow button[class='btn btn-primary']")
	WebElement chkoutbutton;

	// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	public void waitForAllProductsToVisible() {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AllProducts));

	}

	public void waitForToastContainerToVisible() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(VisibletoastContainer));

	}

	public void waitForToastContainertext(WebElement Tst) {

		// wait.until(ExpectedConditions.visibilityOfElementLocated(VisibletoastContainer));
		wait.until(ExpectedConditions.textToBePresentInElement(Tst, "Product Added To Cart"));

	}

	public void waitForToastContainerToInVisible() {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(InvisibletoastContainer));

	}

	public void waitForelementToBeClickable() {
		wait.until(ExpectedConditions.elementToBeClickable(chkoutbutton));
	}

}
