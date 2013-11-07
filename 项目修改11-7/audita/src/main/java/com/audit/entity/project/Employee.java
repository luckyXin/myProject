/**
 * 雇员信息
 */
package com.audit.entity.project;

import com.audit.entity.Common;

/**
 * @author dengXin
 */
public class Employee extends Common {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2915717412493285275L;
	
	/**
	 * 雇员用户ID
	 */
	private String id;

	/**
	 * 雇员名称
	 */
	private String userName;

	/**
	 * 雇员性别
	 */
	private String sex;

	/**
	 * 雇员所属部门
	 */
	private String deptName;

	/**
	 * 雇员联系电话
	 */
	private String telPhone;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the telPhone
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 * @param telPhone the telPhone to set
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

}
