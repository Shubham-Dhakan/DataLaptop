package rahulShettyQaClass.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyQaClass.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;							// This is a Null driver
	
	/*
	 * We have passed LoginPage class method in main class,
	 * From there we are passing chromeDriver object,
	 * as argument in LoginPage class method parenthesis.
	 * 
	 *  Now in this file the constructor class method will execute first of all,
	 *  so passing that instance in constructor class arguments  
	 */
	
	public LoginPage(WebDriver driver) {	
		super(driver);
		this.driver=driver;						
		PageFactory.initElements(driver, this);
	}
	
	// WebElement userEmail = driver.findElement(By.xpath("//input[@type='email']"));	
	@FindBy(xpath="//input[@type='email']")				// This @FindBy method works same as above mentioned line 
	WebElement userEmail;

	// WebElement userPassword = driver.findELement(By.xpath("//input[contains(@id,'userPassword')]"))
	@FindBy(xpath="//input[contains(@id,'userPassword')]") 
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(xpath="//div[text()='Incorrect email or password.']")
	WebElement wrongCredentials;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalogue catalogue = new ProductCatalogue(driver);
		return catalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		System.out.println(driver.getTitle());
	}
	public String ErrorValidation() {
		waitForWebElementToAppear(wrongCredentials);
		return wrongCredentials.getText();
	}
}
