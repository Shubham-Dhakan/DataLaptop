package rahulShettyQaClass.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import org.testng.annotations.DataProvider;
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


public class StandAloneTest extends BaseTest {
		
		String product = "IPHONE 13 PRO";
		
		@Test(dataProvider= "getData",groups= {"smoke"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		String LoginErrorMsg = "Incorrect email or password.";
		ProductCatalogue catalogue = landingPage.loginApplication(input.get("email"),input.get("password"));		// Username, Password
	
		List<WebElement> productsList = catalogue.listOfProducts();	
		catalogue.getProductByName(input.get("product"));
		catalogue.addProductToCart(input.get("product"));
		CartPage cartClassObject = catalogue.goToCart();
		Boolean match = cartClassObject.verifyCartPageProducts(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPageObject = cartClassObject.goToCheckOut(); 
		checkOutPageObject.Email("abc@gmail.com");
		checkOutPageObject.Country("India");
		checkOutPageObject.Cvv("555");
		checkOutPageObject.cardHolderName("xyz");
		OrderPlacedMessage orderPlaceText = checkOutPageObject.PlaceOrder();
		String confirmMessage = orderPlaceText.getOrderText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		}

		@Test		// TestNG Helper Attribute, reason for implementation is becuase testNG is case sensitive 
		public void OrderHistoryTest() {	
			
			ProductCatalogue catalogue = landingPage.loginApplication("kr3608149@gmail.com","FordMustang@12");		// Username, Password
			OrderPage orderHistoryPageObject = catalogue.OrderHistoryPage();
			Assert.assertTrue(orderHistoryPageObject.OrderHistoryVerification(product));
		}

		@DataProvider		// Using DataProvider to enter multiple data sets in testing  
		public Object[][] getData() throws IOException {
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulShettyAcademy\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};		// Returning object or else system will throw Null value error
			//return new Object[] [] {{"kr3608149@gmail.com","FordMustang@12","IPHONE 13 PRO"},{"abc@gmail.com","adfaisf","ADIDAS ORIGINAL"}};
		}
}





	// HashMap Method to get multiple data dynamically 
/*
 			HashMap<String,String> map = new HashMap<String,String>();
			map.put("email","kr3608149@gmail.com");
			map.put("password","FordMustang@12");
			map.put("product","IPHONE 13 PRO");
			

			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email","abcd@gmail.com");
			map1.put("password","Shelby@12");
			map1.put("product","ADIDAS ORIGINAL");
			return new Object[][] {{map},{map1}};		// Returning object or else system will throw Null value error
*/
