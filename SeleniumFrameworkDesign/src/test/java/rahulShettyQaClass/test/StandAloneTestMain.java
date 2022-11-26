package rahulShettyQaClass.test;

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
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestMain {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();	// WebDriverManager is a Dependency which we have to add in POM file.  
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("kr3608149@gmail.com");							// Email
		driver.findElement(By.xpath("//input[contains(@id,'userPassword')]")).sendKeys("FordMustang@12");				// Password  
		driver.findElement(By.id("login")).click();																		// Login Button
		
		// Now add all the items to cart which are displayed after logging in .. 
		// Storing all the WebElements in List
		
	/*  List <WebElement> items = driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));		
		System.out.println(items.size());									// Use this list for Mehtod 1
	*/	
		
		// Method 1 of adding product to cart 
	/*		for(int i=0;i<items.size();i++) {
			System.out.println(items.get(i).getText());
			String itemsText = items.get(i).getText();
			if(itemsText.contains("ADIDAS ORIGINAL")) {
				driver.findElement(By.xpath("(//button[@class='btn w-10 rounded'])[2]")).click();		// Index xpath - Because 3 buttons had same xpath.  
			}
		}
	*/
		String product = "IPHONE 13 PRO";
		// Method 2 of adding product to cart with JAVA STREAMS 
		List <WebElement> items = driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement itemsText = items.stream().filter(item->item.findElement(By.tagName("b"))
		.getText().contains(product)).findFirst().orElse(null);
		 itemsText.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		/* Implementing Explicit wait concept for code sync with WebElements 
		 * So we will wait for pop message of item added to cart and then land on cart page
		 */
		 
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Product Added To Cart ']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=' Product Added To Cart ']")));
		
		 WebElement cartPage = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
		 System.out.println(cartPage.isDisplayed());
		 cartPage.click();
		 
		 // CheckOut
		List <WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(product));  
		Assert.assertTrue(match);
		
		WebElement checkOut = driver.findElement(By.xpath("(//button[@type='button'])[2]"));
		checkOut.click();
		
		// PaymentPage - information and DropDown 
		WebElement emailBox = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
		emailBox.clear();
		Thread.sleep(1000);
		emailBox.sendKeys("abc@gmail.com");
		
		WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		selectCountry.sendKeys("India");
		selectCountry.sendKeys(Keys.ARROW_DOWN.ENTER);
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		
		// Enter CVV
		WebElement CVV = driver.findElement(By.xpath("(//input[@class='input txt'])[1]"));
		CVV.sendKeys("555");
		
		// Enter Name on Card
		WebElement cardName = driver.findElement(By.xpath("(//input[@class='input txt'])[2]"));
		cardName.sendKeys("XYZ");
	
		// Click Place Order Button
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		Thread.sleep(5000);					// This Element is taking long time to load so longTimeout is given
		WebElement placeOrder = driver.findElement(By.xpath("(//a[contains(@class,action__submit)])[2]"));		// Locator --> cssSelector
		placeOrder.click();
		//driver.findElement(with(By.tagName("a")).below(selectCountry)).click();						// Relative Locator Stratergy 
		
		String message = driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
