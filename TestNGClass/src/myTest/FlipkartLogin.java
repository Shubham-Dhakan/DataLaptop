package myTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.*;


public class FlipkartLogin {
	WebDriver driver;

	
	@BeforeTest
	@Parameters({"URL"})		// Pass argument in public void setup(String s1)
	public void setup(String s1) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver","C:\\Web Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.flipkart.com");
	}
	
	/* TestNG runs testcases/Methods on basis of Alphabetical Order
	* so we have to set " PRIORITY " so desired test cases runs first. 
	* 
	* Or else we can use " HELPER ATTRIBUTE " method to run our 
	* desired test cases " dependsOnMethods = " eg: " "
	*/
	
	@Test 			
	public void login() {
		WebElement email = driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']"));
		email.sendKeys("Enter Your Email  ");
		WebElement password = driver.findElement(with(By.tagName("input")).below(email));
		password.sendKeys("Enter your password");
		WebElement button = driver.findElement(with(By.tagName("button")).below(password));
		button.click();
		
	}
	
	@Test(dependsOnMethods={"login"}) 
	public void logout() {
		WebElement MyAccount = driver.findElement(By.xpath("//div[@class='exehdJ']"));
		System.out.println("Myaccount is displayed: "+MyAccount.isDisplayed());
		WebElement exit = driver.findElement(By.xpath("//div[@class='_3vhnxf']"));
		System.out.println("Logout is displayed: "+exit.isDisplayed());
		
		// Mouse-Hover Operation to Logout of Flipkart
		Actions action = new Actions(driver);
		action.moveToElement(MyAccount).moveToElement(exit).click().build().perform();
		
	}

}
