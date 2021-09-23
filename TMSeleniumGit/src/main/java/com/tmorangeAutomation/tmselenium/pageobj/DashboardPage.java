package com.tmorangeAutomation.tmselenium.pageobj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Clase que ilustra los elementos y acciones sobre el page de Dashboar
 * 
 * @author Abvner Cruz Tovar
 *
 */
public class DashboardPage extends BaseObjects {

	private static final By byWelcome = By.id("welcome");

	public static WebElement getWelcome(WebDriver driver) {
		waitForClick(driver, byWelcome, 40);
		return driver.findElement(byWelcome);
	}

}
