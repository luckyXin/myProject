/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class SectionChiefAuditBaseInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4983825098861732631L;

	/**
	 * 审批ID
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectDateId;

	/**
	 * 审批意见
	 */
	private String remark;

	/**
	 * 审批用户账户
	 */
	private String userAccount;

	/**
	 * 审批状态
	 */
	private String passState;

	/**
	 * 是否提交
	 */
	private String isSubmit;

	/**
	 * 审批时间
	 */
	private String createTime;
	
	/**
	 * 安排ID
	 */
	private String arrangeId;
	
	/**
	 * @return the arrangeId
	 */
	public String getArrangeId() {
		return arrangeId;
	}

	/**
	 * @param arrangeId the arrangeId to set
	 */
	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the projectDateId
	 */
	public String getProjectDateId() {
		return projectDateId;
	}

	/**
	 * @param projectDateId
	 *            the projectDateId to set
	 */
	public void setProjectDateId(String projectDateId) {
		this.projectDateId = projectDateId;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the passState
	 */
	public String getPassState() {
		return passState;
	}

	/**
	 * @param passState
	 *            the passState to set
	 */
	public void setPassState(String passState) {
		this.passState = passState;
	}

	/**
	 * @return the isSubmit
	 */
	public String getIsSubmit() {
		return isSubmit;
	}

	/**
	 * @param isSubmit
	 *            the isSubmit to set
	 */
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
