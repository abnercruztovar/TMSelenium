package com.tmorangeAutomation.tmselenium.pageobj;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Clase que ilustra los elementos y acciones sobre el page de Administración
 * 
 * @author Abner Cruz Tovar
 *
 */
public class AdminPage extends BaseObjects {

	public static final Logger log = Logger.getLogger(AdminPage.class.getName());
	private static final By byAdmMenu = By.id("menu_admin_viewAdminModule");
	private WebDriver driver;

	@FindBy(id = "menu_admin_viewAdminModule")
	private WebElement adminMenu;

	@FindBy(id = "menu_admin_UserManagement")
	private WebElement adminUserManagement;

	@FindBy(id = "btnAdd")
	private static WebElement btnAddUser;

	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement pimMenu;

	@FindBy(id = "menu_pim_viewEmployeeList")
	private WebElement pimEmployeeManagement;

	@FindBy(id = "btnAdd")
	private static WebElement btnAddEmployee;

	@FindBy(id = "resultTable")
	private static WebElement tblSearchResults;

	@FindBy(id = "btnSave")
	private WebElement btnSave;

	/**
	 * Constructor que inicializa los elementos del page
	 * 
	 * @param driver
	 */
	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the adminMenu
	 */
	public WebElement getAdminMenu() {
		return adminMenu;
	}

	/**
	 * @param adminMenu the adminMenu to set
	 */
	public void setAdminMenu(WebElement adminMenu) {
		this.adminMenu = adminMenu;
	}

	/**
	 * @return the adminUserManagement
	 */
	public WebElement getAdminUserManagement() {
		return adminUserManagement;
	}

	/**
	 * @param adminUserManagement the adminUserManagement to set
	 */
	public void setAdminUserManagement(WebElement adminUserManagement) {
		this.adminUserManagement = adminUserManagement;
	}

	/**
	 * @return the btnAddUser
	 */
	public static WebElement getBtnAddUser() {
		return btnAddUser;
	}

	/**
	 * @param btnAddUser the btnAddUser to set
	 */
	public void setBtnAddUser(WebElement btnAddUser) {
		AdminPage.btnAddUser = btnAddUser;
	}

	public static WebElement getAdmMenu(WebDriver driver) {
		waitForClick(driver, byAdmMenu, 40);
		return driver.findElement(byAdmMenu);
	}

	public static WebElement getAddUserBtn(WebDriver driver) {
		waitForClick(driver, btnAddUser, 40);
		return btnAddUser;
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

	/**
	 * @return the pimMenu
	 */
	public WebElement getPimMenu() {
		return pimMenu;
	}

	/**
	 * @param pimMenu the pimMenu to set
	 */
	public void setPimMenu(WebElement pimMenu) {
		this.pimMenu = pimMenu;
	}

	/**
	 * @return the pimEmployeeManagement
	 */
	public WebElement getPimEmployeeManagement() {
		return pimEmployeeManagement;
	}

	/**
	 * @param pimEmployeeManagement the pimEmployeeManagement to set
	 */
	public void setPimEmployeeManagement(WebElement pimEmployeeManagement) {
		this.pimEmployeeManagement = pimEmployeeManagement;
	}

	/**
	 * @return the btnAddEmployee
	 */
	public static WebElement getBtnAddEmployee() {
		return btnAddEmployee;
	}

	/**
	 * @param btnAddEmployee the btnAddEmployee to set
	 */
	public static void setBtnAddEmployee(WebElement btnAddEmployee) {
		AdminPage.btnAddEmployee = btnAddEmployee;
	}

	/**
	 * @return the byadmmenu
	 */
	public static By getByadmmenu() {
		return byAdmMenu;
	}

	/**
	 * @return the tblSearchResults
	 */
	public static WebElement getTblSearchResults() {
		return tblSearchResults;
	}

	/**
	 * @param tblSearchResults the tblSearchResults to set
	 */
	public static void setTblSearchResults(WebElement tblSearchResults) {
		AdminPage.tblSearchResults = tblSearchResults;
	}

	/**
	 * @return the btnSave
	 */
	public WebElement getBtnSave() {
		return btnSave;
	}

	/**
	 * @param btnSave the btnSave to set
	 */
	public void setBtnSave(WebElement btnSave) {
		this.btnSave = btnSave;
	}

}
