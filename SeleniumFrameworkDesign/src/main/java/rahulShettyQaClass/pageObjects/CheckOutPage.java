package rahulShettyQaClass.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;



import rahulShettyQaClass.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {				// Constructor of Class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(xpath="(//input[@type='text'])[5]")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement confirmCountry;

	@FindBy(xpath="(//input[@class='input txt'])[1]")
	WebElement cvvNumber;
	
	@FindBy(xpath="(//input[@class='input txt'])[2]")
	WebElement cardName;
	
	@FindBy(xpath="(//a[contains(@class,action__submit)])[2]")
	WebElement placeOrderButton;
	
	@Test(priority=1)
	public void Email(String email) throws InterruptedException {
		userEmail.clear();
		Thread.sleep(1000);
		userEmail.sendKeys(email);
	}
	@Test(priority=2)
	public void Country(String country) {
		
		selectCountry.sendKeys(country);
		selectCountry.sendKeys(Keys.ARROW_DOWN.ENTER);
		confirmCountry.click();
	}
	@Test(priority=3)
	public void Cvv(String cvv) {
		cvvNumber.sendKeys(cvv);
	}
	@Test(priority=4)
	public void cardHolderName(String nameOnCard) {
		cardName.sendKeys(nameOnCard);
	}
	@Test(priority=5)
	public OrderPlacedMessage PlaceOrder() throws InterruptedException {
		javaScriptExecutor();
		Thread.sleep(5000);					// This Element is taking long time to load so longTimeout is given
		WebElement placeOrder = placeOrderButton;
		placeOrder.click();
		OrderPlacedMessage orderPlacedText = new OrderPlacedMessage(driver);
		return orderPlacedText;
		
	}
}
