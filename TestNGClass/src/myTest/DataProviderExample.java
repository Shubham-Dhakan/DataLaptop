package myTest;
import org.testng.annotations.*;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;


public class DataProviderExample {

WebDriver driver;


@BeforeTest
public void setup() {
	System.setProperty("webdriver.chrome.driver", "C:\\Web Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	System.out.println(driver.getCurrentUrl());
	System.out.println(driver.getTitle());
}

@Test(priority = 1)
public void Login() {
	WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
	username.sendKeys("Admin");
	WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
	password.sendKeys("admin123");
	WebElement loginbttn = driver.findElement(By.xpath("//button[@type='submit']"));
	loginbttn.click();
}

@Test(dependsOnMethods = {"Login"})				// Helper Attribute Example  
public void demo() {
	System.out.println("Automated message from TestNg");
}

@Test(dataProvider="getData")
public void test(String d1, String d2) {
	System.out.println(d1);
	System.out.println(d2);
	
}

@AfterTest
public void quit() {
	driver.quit();
}

@DataProvider
public Object[][] getData() {
	Object[][] data = new Object[3][2];			// Array with 3-->rows, 2-->Columns
	
	// 1 set
	data[0][0]="firstUsername";
	data[0][1]="firstPassword";
	
	// 2 set
	data[1][0]="secondUsername";
	data[1][1]="secondPassword";
	
	// 3 set
	data[2][0]="thirdUsername";
	data[2][1]="thirdPassword";
	
	return data;					// Also we have to return data from array
}



}
