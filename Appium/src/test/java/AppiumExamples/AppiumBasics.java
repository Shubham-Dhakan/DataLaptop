package AppiumExamples;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumBasics {
	@Test
	public void AppiumTest() throws MalformedURLException
	{
		
		UiAutomator2Options options = new UiAutomator2Options();			// Appium Framework for automating android apps 
		options.setDeviceName("ShubhamEmulator");							// Take name from Android Studio
		
		//App.apk for testing and with its location in system
		options.setApp("C:\\Users\\Shubham Dhakan\\eclipse-workspace\\Appium\\src\\test\\java\\resoucers\\ApiDemos-debug.apk");		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
		
	}
	
}
