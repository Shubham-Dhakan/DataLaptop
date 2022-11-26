package rahulShettyQaClass.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyQaClass.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
		
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	 
	@FindBy(xpath="//div[@class='card-body']")
	List <WebElement> items; 
	
	By products = By.xpath("//div[@class='card-body']");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By productAddedMessage = By.xpath("//div[text()=' Product Added To Cart ']");
	
	public List<WebElement> listOfProducts() {
		waitForElementToAppear(products);
		return items; 
	}
	
	public WebElement getProductByName(String product) {
		WebElement itemsText = items.stream().filter(item->item.findElement(By.tagName("b"))
		.getText().contains(product)).findFirst().orElse(null);
		return itemsText;
	} 
	
	public void addProductToCart(String product) {
		  WebElement prod = getProductByName(product);
		  prod.findElement(addToCart).click();
		  waitForElementToAppear(productAddedMessage);
	}
	
	public void  goToCartPage() {
		  goToCart();
		  
	}
	public OrderPage OrderHistoryPage(){
	 	return goToOrderPage();
		
		
}


}
