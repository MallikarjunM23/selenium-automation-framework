package EndToEndTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageClasses.CartPage;
import pageClasses.CheckOutPage;
import pageClasses.ConfirmationPage;
import pageClasses.OrdersPage;
import pageClasses.ProductCatolouge;
import resources.DataReader;
import testComponents.BaseTestt;

public class ErrorValidation extends BaseTestt {
	// String product = "ZARA COAT 3";

	@Test(dataProvider = "loginData", dataProviderClass = DataReader.class)
	public void ErrorValidationTest(String testCase, String username, String password, String product, String country)
			throws InterruptedException {
		// LandingPage landingPag = launchApplication();

		ProductCatolouge productCatolouge = landingPage.Login(username, password);
		String toastmsg = productCatolouge.addProductToCart(product);
		Assert.assertEquals(toastmsg, "Prroduct Added To Cart");
		CartPage cartPage = productCatolouge.GoTOCart();
		boolean match = cartPage.VerifyProuctInCart(product);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		ConfirmationPage confirmationPage = checkOutPage.selectCountryByName("India");
		String confirmationMsg = confirmationPage.verifyConfirmationMSG();
		Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");
		String OrderId = confirmationPage.fetchOrderID();
		OrdersPage ordersPage = confirmationPage.goToOrdersPage();
		String orderText = ordersPage.VerifyOrderdetails(OrderId);
		Assert.assertEquals(orderText, OrderId);

	}
}
