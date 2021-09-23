package com.tmorangeAutomation.tmselenium.pageobj;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tmorangeAutomation.tmselenium.base.BaseUser;
import com.tmorangeAutomation.tmselenium.base.Status;

/**
 * Clase que ilustra los elementos y acciones sobre el page de Administración
 * 
 * @author Abner Cruz Tovar
 *
 */
public class UserPage extends BaseObjects {

	public static final Logger log = Logger.getLogger(UserPage.class.getName());
	private WebDriver driver;

	@FindBy(id = "UserHeading")
	private WebElement txtUserHeading;

	@FindBy(id = "systemUser_userType")
	private WebElement lstRole;

	@FindBy(id = "systemUser_employeeName_empName")
	private WebElement txtUserFullName;

	@FindBy(id = "systemUser_userName")
	private WebElement txtUserName;

	@FindBy(id = "systemUser_status")
	private WebElement lstStatus;

	@FindBy(id = "systemUser_password")
	private WebElement txtPassword;

	@FindBy(id = "systemUser_confirmPassword")
	private WebElement txtConfirmPassword;

	@FindBy(id = "btnSave")
	private WebElement btnSave;

	@FindBy(id = "btnCancel")
	private WebElement btnCancel;

	@FindBy(id = "errMessage")
	private WebElement errMessage;

	@FindBy(id = "resultTable")
	private WebElement tblResults;

	@FindBy(id = "btnDelete")
	private WebElement btnDelete;

	@FindBy(id = "dialogDeleteBtn")
	private WebElement btnConfirmDelete;

	@FindBy(id = "searchBtn")
	private WebElement btnSearchUser;

	@FindBy(id = "resetBtn")
	private WebElement btnResetSearch;

	@FindBy(id = "searchSystemUser_userName")
	private WebElement txtSearchUser;

	/**
	 * Constructor que inicializa los elementos del page
	 * 
	 * @param driver
	 */
	public UserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @return the txtUserHeading
	 */
	public WebElement getTxtUserHeading() {
		return txtUserHeading;
	}

	/**
	 * @param txtUserHeading the txtUserHeading to set
	 */
	public void setTxtUserHeading(WebElement txtUserHeading) {
		this.txtUserHeading = txtUserHeading;
	}

	/**
	 * @return the txtUserFullName
	 */
	public WebElement getTxtUserFullName() {
		return txtUserFullName;
	}

	/**
	 * @param txtUserFullName the txtUserFullName to set
	 */
	public void setTxtUserFullName(WebElement txtUserFullName) {
		this.txtUserFullName = txtUserFullName;
	}

	/**
	 * @return the txtUserName
	 */
	public WebElement getTxtUserName() {
		return txtUserName;
	}

	/**
	 * @param txtUserName the txtUserName to set
	 */
	public void setTxtUserName(WebElement txtUserName) {
		this.txtUserName = txtUserName;
	}

	/**
	 * @return the lstStatus
	 */
	public WebElement getLstStatus() {
		return lstStatus;
	}

	/**
	 * @param lstStatus the lstStatus to set
	 */
	public void setLstStatus(WebElement lstStatus) {
		this.lstStatus = lstStatus;
	}

	/**
	 * @return the txtPassword
	 */
	public WebElement getTxtPassword() {
		return txtPassword;
	}

	/**
	 * @param txtPassword the txtPassword to set
	 */
	public void setTxtPassword(WebElement txtPassword) {
		this.txtPassword = txtPassword;
	}

	/**
	 * @return the txtConfirmPassword
	 */
	public WebElement getTxtConfirmPassword() {
		return txtConfirmPassword;
	}

	/**
	 * @param txtConfirmPassword the txtConfirmPassword to set
	 */
	public void setTxtConfirmPassword(WebElement txtConfirmPassword) {
		this.txtConfirmPassword = txtConfirmPassword;
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

	/**
	 * @return the btnCancel
	 */
	public WebElement getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel the btnCancel to set
	 */
	public void setBtnCancel(WebElement btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * Retorna un WebElement que se despliega dinamicamente
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 */
	public static WebElement getElement(WebDriver driver, String elementName) {
		waitForClick(driver, elementName, 40);
		return driver.findElement((By) driver.findElement(By.id(elementName)));
	}

	/**
	 * @return the lstRole
	 */
	public WebElement getLstRole() {
		return lstRole;
	}

	/**
	 * @param lstRole the lstRole to set
	 */
	public void setLstRole(WebElement lstRole) {
		this.lstRole = lstRole;
	}

	/**
	 * Adiciona uyn usuario al sistema
	 * 
	 * @param user
	 */
	public BaseUser addUser(BaseUser user) {

		waitForClick(driver, lstRole, 40);
		Select oRole = new Select(getLstRole());
		oRole.selectByVisibleText(user.getRole());

		txtUserFullName.clear();
		txtUserFullName.sendKeys(user.getName());
		txtUserName.clear();
		txtUserName.sendKeys(user.getUserName());
		waitForClick(driver, lstStatus, 40);
		Select oStatus = new Select(getLstStatus());
		oStatus.selectByVisibleText(user.getStatus());

		txtPassword.clear();
		txtPassword.sendKeys(user.getPassword());
		txtConfirmPassword.clear();
		txtConfirmPassword.sendKeys(user.getPassword());

		btnSave.click();

		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			final WebElement msj = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div[1]/div[3]/div/div[2]/form/fieldset/ol/li[3]/span")));

			if ("Already exists".equals(msj.getText()))
				user.setExist(true);
			log.info(String.format("El empleado %s ya tiene usuario en el sistema", user.getUserName()));
			return user;
		} catch (Exception e) {
			// empty
		}

		log.info(String.format("Se creo el usuario %s", user.getName()));
		return user;

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
	 * Elimina un usuario del sistema
	 * 
	 * @param webElement
	 */
	public void deleteUser(WebElement webElement) {
		webElement.click();
		btnDelete.click();
		waitForClick(driver, btnConfirmDelete, 40);
		btnConfirmDelete.click();
		btnResetSearch.click();

		log.info(String.format("Se elimino el usuario %s", webElement.getText()));

	}

	public WebElement getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(WebElement errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * Metodo que busca usuarios del sistema Y puede retornar de la tabla de
	 * busqueda, el link, el checkbox o nullo de acuerdo al valor de parametro.</br>
	 * </br>
	 * 
	 * param = 0 retorna el webElement de link</br>
	 * param = 1 retorna el WebElement del checkbox
	 * 
	 * @param userName
	 * @param param
	 * @return
	 */
	public WebElement findUser(String userName, int param) {
		btnResetSearch.click();
		txtSearchUser.sendKeys(userName);
		btnSearchUser.click();

		String msjRecordNotFound = driver
				.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/form/div[4]/table/tbody/tr/td"))
				.getText();

		if ("No Records Found".equals(msjRecordNotFound)) {
			log.info(String.format("No se encuentran resultados para username %s", userName));
			return null;
		}

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/*/td/a"));
		log.info(String.format("Cantidad de resultados %s", rows.size()));

		if (param == 0) {
			return driver.findElement(By.linkText(userName));
		} else if (param == 1) {
			return driver.findElement(By.xpath("//table[@id='resultTable']/tbody/*/td/input[@type='checkbox']"));
		} else {
			return null;
		}

	}

	/**
	 * Actualiza un usuario del sistema
	 * 
	 * @param userName
	 */
	public void updateUser(String userName) {

		if ("Edit User".equals(txtUserHeading.getText())) {
			btnSave.click();
			waitForClick(driver, lstStatus, 40);
			Select oStatus = new Select(getLstStatus());
			oStatus.selectByVisibleText(Status.Disabled.toString());

			btnSave.click();
			log.info(String.format("Se actualizo el usuario %s", userName));

		}

	}

	/**
	 * Actualiza un usuario del sistema
	 * 
	 * @param webElement
	 */
	public void updateUser(WebElement webElement) {

		if ("Edit User".equals(txtUserHeading.getText())) {
			btnSave.click();
			waitForClick(driver, lstStatus, 40);
			Select oStatus = new Select(getLstStatus());
			oStatus.selectByVisibleText(Status.Disabled.toString());

			btnSave.click();
			log.info(String.format("Se actualizo el usuario satisfactoriamente"));
		}

	}

	/**
	 * @return the btnDelete
	 */
	public WebElement getBtnDelete() {
		return btnDelete;
	}

	/**
	 * @param btnDelete the btnDelete to set
	 */
	public void setBtnDelete(WebElement btnDelete) {
		this.btnDelete = btnDelete;
	}

	/**
	 * @return the btnConfirmDelete
	 */
	public WebElement getBtnConfirmDelete() {
		return btnConfirmDelete;
	}

	/**
	 * @param btnConfirmDelete the btnConfirmDelete to set
	 */
	public void setBtnConfirmDelete(WebElement btnConfirmDelete) {
		this.btnConfirmDelete = btnConfirmDelete;
	}

	/**
	 * @return the btnSearchUser
	 */
	public WebElement getBtnSearchUser() {
		return btnSearchUser;
	}

	/**
	 * @param btnSearchUser the btnSearchUser to set
	 */
	public void setBtnSearchUser(WebElement btnSearchUser) {
		this.btnSearchUser = btnSearchUser;
	}

	/**
	 * @return the btnResetSearch
	 */
	public WebElement getBtnResetSearch() {
		return btnResetSearch;
	}

	/**
	 * @param btnResetSearch the btnResetSearch to set
	 */
	public void setBtnResetSearch(WebElement btnResetSearch) {
		this.btnResetSearch = btnResetSearch;
	}

	/**
	 * @return the txtSearchUser
	 */
	public WebElement getTxtSearchUser() {
		return txtSearchUser;
	}

	/**
	 * @param txtSearchUser the txtSearchUser to set
	 */
	public void setTxtSearchUser(WebElement txtSearchUser) {
		this.txtSearchUser = txtSearchUser;
	}

}
