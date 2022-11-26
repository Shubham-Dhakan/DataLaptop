package rahulShettyQaClass.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyQaClass.pageObjects.CartPage;
import rahulShettyQaClass.pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartPageButton;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[2]")
	WebElement ordersHistoryButton;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public CartPage goToCart() {
		cartPageButton.click();
		CartPage cartClassObject = new CartPage(driver);
		 return cartClassObject;
	}
	public OrderPage goToOrderPage() {
		//waitForWebElementToAppear(ordersHistoryButton);
		ordersHistoryButton.click();
		OrderPage orderHistoryPageObject = new OrderPage(driver);
		return orderHistoryPageObject;
		
	}
	public void javaScriptExecutor() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		
	}
}
