package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement UserEmail;

	@FindBy(xpath = "//input[@type='password']")
	WebElement pwd;

	@FindBy(xpath = "//input[@id='login']")
	WebElement Login;

	public ProductCatolouge Login(String username, String password) {
		UserEmail.sendKeys(username);
		pwd.sendKeys(password);
		Login.click();
		System.out.println("logged in successfully");
		return new ProductCatolouge(driver);
	}

}
