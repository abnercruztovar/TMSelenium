package com.tmorangeAutomation.tmselenium.test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tmorangeAutomation.tmselenium.base.BaseAutomation;
import com.tmorangeAutomation.tmselenium.pageobj.DashboardPage;
import com.tmorangeAutomation.tmselenium.pageobj.LoginPage;

/**
 * @author Abner Cruz Tovar
 *
 */
public class LoginTest extends BaseAutomation {

	protected static final String ADMINPWD = "Adminpwd";
	protected static final String ADMIN_LOGIN = "AdminLogin";
	public static final Logger log = Logger.getLogger(LoginTest.class.getName());
	private static int caseCount = 0;

	@BeforeMethod
	public static void startMethod() {
		log.info(String.format("Test Case # %s:", caseCount++));

	}

	@AfterMethod
	public void endTest() {
		getDriver().close();
	}

	/**
	 * Successful login
	 */
	@Test
	public void successLogin() {
		init();
		LoginPage lp = new LoginPage(getDriver());

		String userToLogin = getProperties().getProperty(ADMIN_LOGIN);
		String userPassToLogin = getProperties().getProperty(ADMINPWD);
		lp.userLogin(userToLogin, userPassToLogin);
		Assert.assertTrue(DashboardPage.getWelcome(getDriver()).getText().contains("Welcome"));
	}

	/**
	 * Validate wrong passwords
	 */
	@Test
	public void rightUserNameWrongPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userToLogin = getProperties().getProperty(ADMIN_LOGIN);
		lp.userLogin(userToLogin, "1234655");
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.INVALID_CREDENTIALS));
	}

	/**
	 * Validate empty password
	 */
	@Test
	public void rightUserNameEmptyPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userToLogin = getProperties().getProperty(ADMIN_LOGIN);
		lp.userLogin(userToLogin, "");
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.PASSWORD_EMPTY));
	}

	/**
	 * Validate wrong user name
	 */
	@Test
	public void wrongUserNameRightPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userPassToLogin = getProperties().getProperty(ADMINPWD);
		lp.userLogin("tes3t123", userPassToLogin);
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.INVALID_CREDENTIALS));
	}

	/**
	 * Validate wrong user name and wrong password
	 */
	@Test
	public void wrongUserNameWrongPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		lp.userLogin("tesat123", "esdr54");
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.INVALID_CREDENTIALS));
	}

	/**
	 * Validate wrong user name and empty password
	 */
	@Test
	public void wrongUserNameEmptyPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		lp.userLogin("teyst123", "");
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.PASSWORD_EMPTY));
	}

	/**
	 * Validate empty user name
	 */
	@Test
	public void emptyUserNameRightPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userPassToLogin = getProperties().getProperty(ADMINPWD);
		lp.userLogin("", userPassToLogin);
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.USERNAME_EMPTY));
	}

	/**
	 * Validate empty user name and wrong password
	 */
	@Test
	public void emptyUserNameWrongPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		lp.userLogin("", "dfgghcgh");
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.USERNAME_EMPTY));
	}

	/**
	 * Validate empty user name and empty password
	 */
	@Test
	public void emptyUserNameEmptyPass() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		lp.userLogin("", "");
		Assert.assertTrue(lp.getErrMessage().getText().contains(LoginPage.USERNAME_EMPTY));
	}

}
