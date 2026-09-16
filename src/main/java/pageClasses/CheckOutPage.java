package pageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.form-group input")
	WebElement CountryField;

	@FindBy(css = "button.ta-item")
	List<WebElement> countryDropdown;

	@FindBy(css = ".btnn")
	WebElement PlaceOrderButton;

	public ConfirmationPage selectCountryByName(String CountryName) {

		CountryField.sendKeys("IND");

		countryDropdown.stream().filter(cd -> cd.getText().equals(CountryName)).findFirst()
				.orElseThrow(() -> new RuntimeException("country not found")).click();
		PlaceOrderButton.click();
		return new ConfirmationPage(driver);
	}

}
