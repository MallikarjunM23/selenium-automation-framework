package pageClasses;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "ul h3")
	List<WebElement> cartProducts;

	@FindBy(css = "li.totalRow button[class='btn btn-primary']")
	WebElement chkoutbutton;

	public boolean VerifyProuctInCart(String product) {

		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(product));
		return match;

	}

	public CheckOutPage goToCheckOutPage() throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", chkoutbutton);
		waitForelementToBeClickable();
		// Thread.sleep(3000);
		chkoutbutton.click();
		return new CheckOutPage(driver);

	}

}
