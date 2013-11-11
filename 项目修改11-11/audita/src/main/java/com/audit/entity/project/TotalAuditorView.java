/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class TotalAuditorView implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5156961866637398302L;
	
	/**
	 * 主审ID
	 */
	private String mainAuditId;

	/**
	 * 总审计师审签时间
	 */
	private String totalAuditTime;
	
	/**
	 * 总审计师审签用户
	 */
	private String totalAuditUserAccount;
	
	/**
	 * 总审计师审签意见
	 */
	private String totalAuditRemark;
	
	/**
	 * 总审计师审签状态
	 */
	private String isTotalAudit;

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
	 * @return the totalAuditTime
	 */
	public String getTotalAuditTime() {
		return totalAuditTime;
	}

	/**
	 * @param totalAuditTime the totalAuditTime to set
	 */
	public void setTotalAuditTime(String totalAuditTime) {
		this.totalAuditTime = totalAuditTime;
	}

	/**
	 * @return the totalAuditUserAccount
	 */
	public String getTotalAuditUserAccount() {
		return totalAuditUserAccount;
	}

	/**
	 * @param totalAuditUserAccount the totalAuditUserAccount to set
	 */
	public void setTotalAuditUserAccount(String totalAuditUserAccount) {
		this.totalAuditUserAccount = totalAuditUserAccount;
	}

	/**
	 * @return the totalAuditRemark
	 */
	public String getTotalAuditRemark() {
		return totalAuditRemark;
	}

	/**
	 * @param totalAuditRemark the totalAuditRemark to set
	 */
	public void setTotalAuditRemark(String totalAuditRemark) {
		this.totalAuditRemark = totalAuditRemark;
	}

	/**
	 * @return the isTotalAudit
	 */
	public String getIsTotalAudit() {
		return isTotalAudit;
	}

	/**
	 * @param isTotalAudit the isTotalAudit to set
	 */
	public void setIsTotalAudit(String isTotalAudit) {
		this.isTotalAudit = isTotalAudit;
	}
}
