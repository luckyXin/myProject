/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class LegalDepartAuditView implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5407201993197051998L;
	
	/**
	 * 主审信息ID
	 */
	private String mainAuditId;

	/**
	 * 法制科室签审用户
	 */
	private String legalLeaderUserAccount;
	
	/**
	 * 法制科室签审意见
	 */
	private String legalLeaderAuditRemark;
	
	/**
	 * 法制科室签审时间
	 */
	private String legalLeaderAuditTime;
	
	/**
	 * 法制科室签审状态
	 */
	private String isLegalLeaderAudit;

	/**
	 * @return the mainAuditId
	 */
	public String getMainAuditId() {
		return mainAuditId;
	}

	/**
	 * @param mainAuditId the mainAuditId to set
	 */
	public void setMainAuditId(String mainAuditId) {
		this.mainAuditId = mainAuditId;
	}

	/**
	 * @return the legalLeaderUserAccount
	 */
	public String getLegalLeaderUserAccount() {
		return legalLeaderUserAccount;
	}

	/**
	 * @param legalLeaderUserAccount the legalLeaderUserAccount to set
	 */
	public void setLegalLeaderUserAccount(String legalLeaderUserAccount) {
		this.legalLeaderUserAccount = legalLeaderUserAccount;
	}

	/**
	 * @return the legalLeaderAuditRemark
	 */
	public String getLegalLeaderAuditRemark() {
		return legalLeaderAuditRemark;
	}

	/**
	 * @param legalLeaderAuditRemark the legalLeaderAuditRemark to set
	 */
	public void setLegalLeaderAuditRemark(String legalLeaderAuditRemark) {
		this.legalLeaderAuditRemark = legalLeaderAuditRemark;
	}

	/**
	 * @return the legalLeaderAuditTime
	 */
	public String getLegalLeaderAuditTime() {
		return legalLeaderAuditTime;
	}

	/**
	 * @param legalLeaderAuditTime the legalLeaderAuditTime to set
	 */
	public void setLegalLeaderAuditTime(String legalLeaderAuditTime) {
		this.legalLeaderAuditTime = legalLeaderAuditTime;
	}

	/**
	 * @return the isLegalLeaderAudit
	 */
	public String getIsLegalLeaderAudit() {
		return isLegalLeaderAudit;
	}

	/**
	 * @param isLegalLeaderAudit the isLegalLeaderAudit to set
	 */
	public void setIsLegalLeaderAudit(String isLegalLeaderAudit) {
		this.isLegalLeaderAudit = isLegalLeaderAudit;
	}
	
}
