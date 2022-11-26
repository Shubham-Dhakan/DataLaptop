package rahulShettyQaClass.test;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShetty.TestComponents.BaseTest;
import rahulShettyQaClass.pageObjects.CartPage;
import rahulShettyQaClass.pageObjects.CheckOutPage;
import rahulShettyQaClass.pageObjects.LoginPage;
import rahulShettyQaClass.pageObjects.OrderPage;
import rahulShettyQaClass.pageObjects.OrderPlacedMessage;
import rahulShettyQaClass.pageObjects.ProductCatalogue;


public class ErrorValidation extends BaseTest {

		@Test(groups= {"ErrorHandling"})											// Annotation	
		public void loginErrorValidation() throws IOException, InterruptedException {
		
		String product = "IPHONE 13 PRO";
		ProductCatalogue catalogue = landingPage.loginApplication("kr36081@gmail.com","FordMustang@12");		// Username, Password
		landingPage.ErrorValidation();
		Assert.assertEquals("Incorrect email or password.", landingPage.ErrorValidation());
	}
		
		@Test											// Annotation 
		public void productErrorValidation() throws InterruptedException {
			String product = "IPHONE 13 PRO";
			ProductCatalogue catalogue = landingPage.loginApplication("kr3608149@gmail.com","FordMustang@12");		// Username, Password
			List<WebElement> productList = catalogue.listOfProducts();
			catalogue.getProductByName(product);
			catalogue.addProductToCart(product);
			CartPage cartClassObject = catalogue.goToCart();
			Boolean match = cartClassObject.verifyCartPageProducts(product);
			Assert.assertTrue(match);
			CheckOutPage checkOutPageObject = cartClassObject.goToCheckOut();
			checkOutPageObject.Email("abc@gmail.com");
			checkOutPageObject.Country("India");
			checkOutPageObject.Cvv("555");
			checkOutPageObject.cardHolderName("xyz");
			OrderPlacedMessage orderPlacedText = checkOutPageObject.PlaceOrder();
			Assert.assertEquals("THANKYOU FOR THE ORDER.",orderPlacedText.getOrderText());
		}
		
		@Test(dependsOnMethods= {"productErrorValidation"})			// Helper attribute to prioritize TestCases
		public void OrderPageValidation() throws InterruptedException {
			String product = "IPHONE 13 PRO";
			ProductCatalogue catalogue = landingPage.loginApplication("kr3608149@gmail.com","FordMustang@12");		// Username, Password
			OrderPage orderHistoryPageObject = catalogue.OrderHistoryPage();
			Assert.assertTrue(orderHistoryPageObject.OrderHistoryVerification(product));
			
	} 
		
	/*	
		List<WebElement> productsList = catalogue.listOfProducts();		
		catalogue.addProductToCart(product);
		CartPage cartClassObject = catalogue.goToCart();
		Boolean match = cartClassObject.verifyCartPageProducts(product);
		Assert.assertTrue(match);
		CheckOutPage checkOutPageObject = cartClassObject.goToCheckOut(); 
		checkOutPageObject.Email("abc@gmail.com");
		checkOutPageObject.Country("India");
		checkOutPageObject.Cvv("555");
		checkOutPageObject.cardHolderName("xyz");
		OrderPlacedMessage orderPlaceText = checkOutPageObject.PlaceOrder();
		String confirmMessage = orderPlaceText.getOrderText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	*/	
		
		
}


