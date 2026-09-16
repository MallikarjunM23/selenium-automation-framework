package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class ProductCatolouge extends AbstractComponents {

	WebDriver driver;

	public ProductCatolouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> AllProducts;
	@FindBy(id = "toast-container")
	WebElement Tst;

	@FindBy(xpath = "//*[text()='  Cart ']")
	WebElement CartButton;

	By prodcutFound = By.xpath(".//button[@class='btn w-10 rounded']");

	public String addProductToCart(String product) {

		waitForAllProductsToVisible();

		for (WebElement prod : AllProducts) {
			if (prod.getText().contains(product)) {

				prod.findElement(prodcutFound).click();
				waitForToastContainertext(Tst);
				String toastmsg = Tst.getText();

				return toastmsg;
			}
			waitForToastContainerToInVisible();
		}
		return null;

	}

	public CartPage GoTOCart() {

		CartButton.click();
		return new CartPage(driver);
	}

}
