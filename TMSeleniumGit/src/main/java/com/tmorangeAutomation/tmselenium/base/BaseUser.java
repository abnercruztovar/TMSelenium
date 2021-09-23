package com.tmorangeAutomation.tmselenium.base;

/**
 * Entidad de Usuario
 * 
 * @author Abner Cruz Tovar
 *
 */
public class BaseUser {

	private BaseEmployee employee;
	private String name;
	private String userName;
	private String status;
	private String password;
	private String role;
	private Boolean exist = false;

	public BaseUser() {
		// default constructor
	}

	public BaseUser(BaseEmployee employee, String name, String userName, String status, String password, String role) {
		super();
		this.employee = employee;
		this.name = name;
		this.userName = userName;
		this.status = status;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the employee
	 */
	public BaseEmployee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(BaseEmployee employee) {
		this.employee = employee;
	}

	/**
	 * @return the exist
	 */
	public Boolean getExist() {
		return exist;
	}

	/**
	 * @param exist the exist to set
	 */
	public void setExist(Boolean exist) {
		this.exist = exist;
	}

}
