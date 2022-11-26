package rahulShettyQaClass.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulShettyQaClass.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//div[@class='cartSection']/h3")
	List <WebElement> cartPageProducts;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement checkOutButton;
	

	public Boolean verifyCartPageProducts(String product) {
		
		Boolean match = cartPageProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(product));  
		return match;
		}  

	public CheckOutPage goToCheckOut() {
		checkOutButton.click();
		CheckOutPage checkOutPageObject = new CheckOutPage(driver);
		return checkOutPageObject;
	}
	
}
