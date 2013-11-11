/**
 * 
 */
package com.audit.entity.search;

import com.audit.entity.Common;

/**
 * @author User
 * 
 */
public class SearchProjectInfoParam extends Common {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5103717864025315663L;

	/**
	 * 审计开始时间
	 */
	private String auditStartTime;

	/**
	 * 审计结束时间
	 */
	private String auditEndTime;

	/**
	 * 雇员名称
	 */
	private String employeeName;

	/**
	 * 审计身份 0 ：主审 1： 复审
	 */
	private String auditIdentity;

	/**
	 * 中介机构
	 */
	private String intermediary;

	/**
	 * 施工单位
	 */
	private String construction;

	/**
	 * 建设年度
	 */
	private String buildYear;

	/**
	 * 项目业主
	 */
	private String projectOwner;

	/**
	 * @return the auditStartTime
	 */
	public String getAuditStartTime() {
		return auditStartTime;
	}

	/**
	 * @param auditStartTime the auditStartTime to set
	 */
	public void setAuditStartTime(String auditStartTime) {
		this.auditStartTime = auditStartTime;
	}

	/**
	 * @return the auditEndTime
	 */
	public String getAuditEndTime() {
		return auditEndTime;
	}

	/**
	 * @param auditEndTime the auditEndTime to set
	 */
	public void setAuditEndTime(String auditEndTime) {
		this.auditEndTime = auditEndTime;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the auditIdentity
	 */
	public String getAuditIdentity() {
		return auditIdentity;
	}

	/**
	 * @param auditIdentity the auditIdentity to set
	 */
	public void setAuditIdentity(String auditIdentity) {
		this.auditIdentity = auditIdentity;
	}

	/**
	 * @return the intermediary
	 */
	public String getIntermediary() {
		return intermediary;
	}

	/**
	 * @param intermediary the intermediary to set
	 */
	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}

	/**
	 * @return the construction
	 */
	public String getConstruction() {
		return construction;
	}

	/**
	 * @param construction the construction to set
	 */
	public void setConstruction(String construction) {
		this.construction = construction;
	}

	/**
	 * @return the buildYear
	 */
	public String getBuildYear() {
		return buildYear;
	}

	/**
	 * @param buildYear the buildYear to set
	 */
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	/**
	 * @return the projectOwner
	 */
	public String getProjectOwner() {
		return projectOwner;
	}

	/**
	 * @param projectOwner the projectOwner to set
	 */
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
}
