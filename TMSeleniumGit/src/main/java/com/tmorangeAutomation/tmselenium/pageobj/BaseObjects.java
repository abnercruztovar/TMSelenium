package com.tmorangeAutomation.tmselenium.pageobj;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Clase de metodos utilitarios para eventos o popups
 * 
 * @author Abner Cruz Tovar
 *
 */
public class BaseObjects {

	/**
	 * Espera que el elemento sea clicable
	 * 
	 * @param driver
	 * @param by
	 * @param duration
	 */
	public static void waitForClick(WebDriver driver, By by, int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(by));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Espera que el elemento sea clicable
	 * 
	 * @param driver
	 * @param element
	 * @param duration
	 */
	public static void waitForClick(WebDriver driver, WebElement element, int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Espera que el elemento sea clicable
	 * 
	 * @param driver
	 * @param elementName
	 * @param duration
	 */
	public static void waitForClick(WebDriver driver, String elementName, int duration) {
		for (int x = 0; x <= 5; x++) {
			try {
				new WebDriverWait(driver, duration)
						.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(elementName))));
				break;
			} catch (StaleElementReferenceException e) {
				e.printStackTrace();
			}
		}
	}

}
