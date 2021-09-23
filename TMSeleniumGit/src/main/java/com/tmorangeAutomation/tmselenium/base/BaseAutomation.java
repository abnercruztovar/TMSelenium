package com.tmorangeAutomation.tmselenium.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Clase que inicializa los parametros de la automatizacion
 * 
 * @author Abner Cruz Tovar
 *
 */
public class BaseAutomation {

	public static final Logger log = Logger.getLogger(BaseAutomation.class.getName());
	private WebDriver driver;
	public static final String BROWSER = "firefox";

	private Properties properties;

	/**
	 * Inicia el sistema de automatización
	 */
	public void init() {

		setProperties();
		selectBrowser(properties.getProperty("defaultbrowser"));

	}

	/**
	 * Carga la URL en la cual se va a navegar
	 * 
	 * @param url
	 */
	private void getURL(String url) {
		driver.get(url);
		log.info(String.format("Web Site: %s inciando", url));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.info("Sitio cargando...");

	}

	/**
	 * Selecciona e inicial el browser en el cual se va a trabajar
	 * 
	 * @param browser
	 */
	private void selectBrowser(String browser) {

		if (browser.equalsIgnoreCase(BROWSER)) {
			log.info("MOZILLA FIREFOX SE ESTA INICIANDO...");

			System.setProperty("webdriver.gecko.driver", properties.getProperty("selenium.driver.path"));
			driver = new FirefoxDriver();
			getURL(getProperties().getProperty("URL"));

		}

	}

	/**
	 * Retorna el webDriver
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Retorna las propiedades del sistema
	 * 
	 * @return
	 */
	public void setProperties() {

		PropertyConfigurator.configure("conf//Log4j.properties");

//		Propiedades del sistema
		File archivo = new File("conf//properties.properties");
		properties = new Properties();

		try (FileInputStream fileInputStream = new FileInputStream(archivo);) {
			properties.load(fileInputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return properties;
	}

}
