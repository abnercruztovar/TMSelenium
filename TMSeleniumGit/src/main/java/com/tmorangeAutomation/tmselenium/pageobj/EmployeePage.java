package com.tmorangeAutomation.tmselenium.pageobj;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tmorangeAutomation.tmselenium.base.BaseEmployee;

/**
 * // * Clase que ilustra los elementos y acciones sobre el page de Empleados
 * 
 * @author Abner Cruz Tovar
 *
 */
public class EmployeePage extends BaseObjects {

	public static final Logger log = Logger.getLogger(EmployeePage.class.getName());
	private WebDriver driver;

	@FindBy(id = "firstName")
	private WebElement txtfirstName;

	@FindBy(id = "middleName")
	private WebElement txtMidleName;

	@FindBy(id = "lastName")
	private WebElement txtLastName;

	@FindBy(id = "employeeId")
	private WebElement txtId;

	@FindBy(id = "btnSave")
	private WebElement btnSave;

	/**
	 * Constructor que inicializa los elementos del page
	 * 
	 * @param driver
	 */
	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	 * @return the txtfirstName
	 */
	public WebElement getTxtfirstName() {
		return txtfirstName;
	}

	/**
	 * @param txtfirstName the txtfirstName to set
	 */
	public void setTxtfirstName(WebElement txtfirstName) {
		this.txtfirstName = txtfirstName;
	}

	/**
	 * @return the txtMidleName
	 */
	public WebElement getTxtMidleName() {
		return txtMidleName;
	}

	/**
	 * @param txtMidleName the txtMidleName to set
	 */
	public void setTxtMidleName(WebElement txtMidleName) {
		this.txtMidleName = txtMidleName;
	}

	/**
	 * @return the txtLastName
	 */
	public WebElement getTxtLastName() {
		return txtLastName;
	}

	/**
	 * @param txtLastName the txtLastName to set
	 */
	public void setTxtLastName(WebElement txtLastName) {
		this.txtLastName = txtLastName;
	}

	/**
	 * @return the txtId
	 */
	public WebElement getTxtId() {
		return txtId;
	}

	/**
	 * @param txtId the txtId to set
	 */
	public void setTxtId(WebElement txtId) {
		this.txtId = txtId;
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
	 * Adiciona un empleado al sistema
	 * 
	 * @param employee
	 */
	public BaseEmployee addEmployee(BaseEmployee employee) {

		txtfirstName.clear();
		txtfirstName.sendKeys(employee.getFirstName());
		txtMidleName.clear();
		txtMidleName.sendKeys(employee.getMiddleName());
		txtLastName.clear();
		txtLastName.sendKeys(employee.getLastName());
		txtId.clear();
		txtId.sendKeys(employee.getId().toString());

		btnSave.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			final WebElement msj = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(text(),'Failed To Save: Employee Id Exists')]")));

			if (msj != null)
				employee.setExist(true);
			log.info(String.format("El empleado %s ya existe en sistema", employee.getFullName()));
			return employee;
		} catch (Exception e) {
			// empty
		}

		log.info(String.format("Se creo el empleado %s", employee.getFullName()));
		return employee;

	}

}
