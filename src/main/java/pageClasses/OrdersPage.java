package pageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tbody tr th")
	List<WebElement> AllOrders;

	public String VerifyOrderdetails(String orderId) {

		for (WebElement order : AllOrders) {
			String orderText = order.getText();
			if (orderText.contains(orderId)) {
				return orderText;

			}
		}

		return null;

	}

}
