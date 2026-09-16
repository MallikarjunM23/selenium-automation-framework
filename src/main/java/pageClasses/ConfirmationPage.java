package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1.hero-primary")
	WebElement successMsg;

	@FindBy(css = "label.ng-star-inserted")
	WebElement CompleteOrderId;

	@FindBy(xpath = "//*[contains(text(), 'ORDERS')]")
	WebElement OdrersButton;

	public String verifyConfirmationMSG() {

		String confirmationMsg = successMsg.getText();
		return confirmationMsg;

	}

	public String fetchOrderID() {
		String confirmOrderId = CompleteOrderId.getText();
		String OrderId = confirmOrderId.replace("|", "").trim();
		System.out.println(OrderId);
		return OrderId;

	}

	public OrdersPage goToOrdersPage() {
		// Thread.sleep(3000);
		OdrersButton.click();
		return new OrdersPage(driver);
	}

}
