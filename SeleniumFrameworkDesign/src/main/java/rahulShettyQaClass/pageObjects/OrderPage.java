package rahulShettyQaClass.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyQaClass.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements( driver, this);
	}
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover ng-star-inserted']//tbody/tr/td[2]")
	List <WebElement> orderPageproductsList; 
	
	public Boolean OrderHistoryVerification(String product) {
		Boolean match1 = orderPageproductsList.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(product));
		return match1;
	}
	
}
