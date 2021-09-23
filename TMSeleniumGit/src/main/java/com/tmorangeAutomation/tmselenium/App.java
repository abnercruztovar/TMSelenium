package com.tmorangeAutomation.tmselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Desarrollo\\JAR\\Selenium\\drivers\\geckodriver-v0.30.0-win64\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers");

		String verifyBrowserStackTitle = driver.getTitle();

		System.out.println(verifyBrowserStackTitle);

//        assertEquals("Most Reliable App & Cross Browser Testing Platform | BrowserStack",verifyBrowserStackTitle);

	}
}
