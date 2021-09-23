package com.tmorangeAutomation.tmselenium.pageobj;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Clase que ilustra los elementos y acciones sobre el page de Login
 * (Autenticación)
 * 
 * @author Abner Cruz Tovar
 *
 */
public class LoginPage {

	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	public static final String INVALID_CREDENTIALS = "Invalid credentials";
	public static final String PASSWORD_EMPTY = "Password cannot be empty";
	public static final String USERNAME_EMPTY = "Username cannot be empty";
	private WebDriver driver;

	@FindBy(id = "txtUsername")
	private WebElement userName;

	@FindBy(id = "txtPassword")
	private WebElement userPassword;

	@FindBy(id = "btnLogin")
	private WebElement btnLogin;

	@FindBy(id = "spanMessage")
	private WebElement errMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Constructor que inicializa los elementos del page
	 * 
	 * @param user
	 * @param pass
	 */
	public void userLogin(String user, String pass) {

		userName.clear();
		userName.sendKeys(user);
		userPassword.clear();
		userPassword.sendKeys(pass);
		log.info(String.format("Credenciales de acceso ingresadas por %s", user));
		btnLogin.click();

	}

	public WebElement getUserName() {
		return userName;
	}

	public void setUserName(WebElement userName) {
		this.userName = userName;
	}

	public WebElement getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(WebElement userPassword) {
		this.userPassword = userPassword;
	}

	public WebElement getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(WebElement errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
