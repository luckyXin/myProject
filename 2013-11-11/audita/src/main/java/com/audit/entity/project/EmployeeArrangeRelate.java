/**
 * 项目安排与政府关联信息
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author DengXin
 * 
 */
public class EmployeeArrangeRelate implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6438826960402936704L;

	/**
	 * 项目安排雇员关联ID
	 */
	private String id;

	/**
	 * 项目安排ID
	 */
	private String projectArrangeId;

	/**
	 * 雇员ID
	 */
	private String governmentEmployeeId;

	/**
	 * 审批状态 0 ：未审批 1：通过 2：未通过
	 */
	private String auditState;

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
	 * @return the projectArrangeId
	 */
	public String getProjectArrangeId() {
		return projectArrangeId;
	}

	/**
	 * @param projectArrangeId the projectArrangeId to set
	 */
	public void setProjectArrangeId(String projectArrangeId) {
		this.projectArrangeId = projectArrangeId;
	}

	/**
	 * @return the governmentEmployeeId
	 */
	public String getGovernmentEmployeeId() {
		return governmentEmployeeId;
	}

	/**
	 * @param governmentEmployeeId the governmentEmployeeId to set
	 */
	public void setGovernmentEmployeeId(String governmentEmployeeId) {
		this.governmentEmployeeId = governmentEmployeeId;
	}

	/**
	 * @return the auditState
	 */
	public String getAuditState() {
		return auditState;
	}

	/**
	 * @param auditState the auditState to set
	 */
	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
}
