package EndToEndTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductOrderRaw {
	static String ProductName = "ZARA COAT 3";

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("moon@test.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Moon@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		System.out.println("logged in successfully");
		String title = driver.getTitle();
		Assert.assertEquals("Let's Shop", title);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> product = driver.findElements(By.cssSelector(".mb-3"));
		for (WebElement prod : product) {
			if (prod.getText().contains(ProductName)) {
				prod.findElement(By.xpath(".//button[@class='btn w-10 rounded']")).click();
				Thread.sleep(2000);
			}
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		String toastmsg = driver.findElement(By.id("toast-container")).getText();
		Assert.assertEquals(toastmsg, "Product Added To Cart");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("toast-container")));
		driver.findElement(By.xpath("//*[text()='  Cart ']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("ul h3"));
		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("li.totalRow button[class='btn btn-primary']")).click();
		driver.findElement(By.cssSelector("div.form-group input")).sendKeys("IND");
		Thread.sleep(3000);
		List<WebElement> countryDropdown = driver.findElements(By.cssSelector("button.ta-item"));
		countryDropdown.stream().filter(cd -> cd.getText().equals("India")).findFirst()
				.orElseThrow(() -> new RuntimeException("country not found")).click();
		driver.findElement(By.cssSelector(".btnn")).click();
		String confirmationMsg = driver.findElement(By.cssSelector("h1")).getText();
		Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");
		String CompleteOrderId = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
		String OrderId = CompleteOrderId.replace("|", "").trim();
		System.out.println(OrderId);
		driver.findElement(By.xpath("//*[contains(text(), 'ORDERS')]")).click();
		List<WebElement> AllOrders = driver.findElements(By.cssSelector("tbody tr th"));
		for (WebElement order : AllOrders) {
			String orderText = order.getText();
			if (orderText.contains(OrderId)) {
				Assert.assertEquals(orderText, OrderId);
				System.out.println("success");
			}
		}

		driver.quit();
	}

}
