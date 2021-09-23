package com.tmorangeAutomation.tmselenium.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tmorangeAutomation.tmselenium.GB;
import com.tmorangeAutomation.tmselenium.base.BaseAutomation;
import com.tmorangeAutomation.tmselenium.base.BaseEmployee;
import com.tmorangeAutomation.tmselenium.base.BaseUser;
import com.tmorangeAutomation.tmselenium.base.Role;
import com.tmorangeAutomation.tmselenium.base.Status;
import com.tmorangeAutomation.tmselenium.pageobj.AdminPage;
import com.tmorangeAutomation.tmselenium.pageobj.EmployeePage;
import com.tmorangeAutomation.tmselenium.pageobj.LoginPage;
import com.tmorangeAutomation.tmselenium.pageobj.UserPage;

/**
 * @author Abner Cruz Tovar
 *
 */
public class UserTest extends BaseAutomation {

	private static final String DEFAULT_PASS = "T0n4V4L3*.!";
	public static final Logger log = Logger.getLogger(UserTest.class.getName());
	private static int caseCount = 0;

	private List<BaseUser> usertoTest = new ArrayList<>();

	@BeforeMethod
	public static void startMethod() {
		log.info(String.format("Test Case # %s:", caseCount++));

	}

	@Test
	public void createUser() {
		init();

		WebDriver wd = getDriver();
		LoginPage lp = new LoginPage(wd);
		String userToLogin = getProperties().getProperty(LoginTest.ADMIN_LOGIN);
		String userPassToLogin = getProperties().getProperty(LoginTest.ADMINPWD);
		lp.userLogin(userToLogin, userPassToLogin);

//		Se instancian los empleados
		BaseEmployee employee1 = GB.of(new BaseEmployee()).push(e -> e.setFirstName("Abner"))
				.push(e -> e.setMiddleName("")).push(e -> e.setLastName("Cruz")).push(e -> e.setId(80723325)).build();

		BaseEmployee employee2 = GB.of(new BaseEmployee()).push(e -> e.setFirstName("Yanira"))
				.push(e -> e.setMiddleName("")).push(e -> e.setLastName("Tada")).push(e -> e.setId(99988877)).build();

		BaseEmployee employee3 = GB.of(new BaseEmployee()).push(e -> e.setFirstName("Carolina"))
				.push(e -> e.setMiddleName("")).push(e -> e.setLastName("Amaya")).push(e -> e.setId(55544477)).build();

		List<BaseEmployee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);

		// Se crean (persisten) los empleados
		employees.stream().forEach(e -> {

			AdminPage admPage = new AdminPage(wd);
			admPage.getPimMenu().click();
			AdminPage.getBtnAddEmployee().click();

			EmployeePage pimPage = new EmployeePage(wd);

			BaseEmployee eUser = pimPage.addEmployee(e);

			BaseUser user = GB.of(new BaseUser()).push(u -> u.setEmployee(eUser))
					.push(u -> u.setName(eUser.getFullName())).push(u -> u.setPassword(DEFAULT_PASS))
					.push(u -> u.setRole(Role.ESS.toString())).push(u -> u.setStatus(Status.Enabled.toString()))
					.push(u -> u.setUserName(
							eUser.getFirstName().substring(0, 1).toLowerCase() + eUser.getLastName().toLowerCase()))
					.push(u -> u.setExist(false)).build();

			// Se adiciona el nuevo empleado a la lista de usuarios a crear del sistema
			usertoTest.add(user);

		});

		usertoTest.stream().forEach(u -> {

			if (Boolean.TRUE.equals(u.getEmployee().getExist())) {

				AdminPage admPage = new AdminPage(wd);
				admPage.getAdminMenu().click();
				AdminPage.getBtnAddUser().click();

				UserPage addUserPage = new UserPage(wd);
				addUserPage.addUser(u);
			}

		});

	}

	@Test
	public void findUser() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userToLogin = getProperties().getProperty(LoginTest.ADMIN_LOGIN);
		String userPassToLogin = getProperties().getProperty(LoginTest.ADMINPWD);
		lp.userLogin(userToLogin, userPassToLogin);

		AdminPage admPage = new AdminPage(getDriver());
		admPage.getAdminMenu().click();

		UserPage usrPage = new UserPage(getDriver());
		usrPage.findUser("acruz", 0);

	}

	@Test
	public void updateUser() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userToLogin = getProperties().getProperty(LoginTest.ADMIN_LOGIN);
		String userPassToLogin = getProperties().getProperty(LoginTest.ADMINPWD);
		lp.userLogin(userToLogin, userPassToLogin);

		AdminPage admPage = new AdminPage(getDriver());
		admPage.getAdminMenu().click();

		UserPage usrPage = new UserPage(getDriver());
		WebElement userLink = usrPage.findUser("acruz", 0);

		if (userLink != null) {
			userLink.click();
			usrPage.updateUser("acruz");
		} else {
			log.info(String.format("El usuario %s no pudo ser actualizado, ya que no existe", "acruz"));
		}

	}

	@Test
	public void deleteUser() {
		init();
		LoginPage lp = new LoginPage(getDriver());
		String userToLogin = getProperties().getProperty(LoginTest.ADMIN_LOGIN);
		String userPassToLogin = getProperties().getProperty(LoginTest.ADMINPWD);
		lp.userLogin(userToLogin, userPassToLogin);

		AdminPage admPage = new AdminPage(getDriver());
		admPage.getAdminMenu().click();

		UserPage usrPage = new UserPage(getDriver());
		WebElement userLink = usrPage.findUser("acruz", 1);

		if (userLink != null) {
			userLink.click();
			usrPage.deleteUser(userLink);
		} else {
			log.info(String.format("El usuario %s no pudo ser eliminado , ya que no existe", "acruz"));
		}

	}

	@AfterMethod
	public void endTest() {
		getDriver().close();
	}

	/**
	 * @return the usertoTest
	 */
	public List<BaseUser> getUsertoTest() {
		return usertoTest;
	}

	/**
	 * @param usertoTest the usertoTest to set
	 */
	public void setUsertoTest(List<BaseUser> usertoTest) {
		this.usertoTest = usertoTest;
	}

}
