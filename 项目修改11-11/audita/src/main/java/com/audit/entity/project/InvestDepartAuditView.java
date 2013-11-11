/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class InvestDepartAuditView implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5407201993197051998L;
	
	/**
	 * 主审信息ID
	 */
	private String mainAuditId;

	/**
	 * 投资科室签审用户
	 */
	private String investLeaderUserAccount;
	
	/**
	 * 投资科室签审意见
	 */
	private String investLeaderAuditRemark;
	
	/**
	 * 投资科室签审时间
	 */
	private String investLeaderAuditTime;
	
	/**
	 * 投资科室签审状态
	 */
	private String isInvestLeaderAudit;

	/**
	 * @return the investLeaderUserAccount
	 */
	public String getInvestLeaderUserAccount() {
		return investLeaderUserAccount;
	}

	/**
	 * @param investLeaderUserAccount the investLeaderUserAccount to set
	 */
	public void setInvestLeaderUserAccount(String investLeaderUserAccount) {
		this.investLeaderUserAccount = investLeaderUserAccount;
	}

	/**
	 * @return the investLeaderAuditRemark
	 */
	public String getInvestLeaderAuditRemark() {
		return investLeaderAuditRemark;
	}

	/**
	 * @param investLeaderAuditRemark the investLeaderAuditRemark to set
	 */
	public void setInvestLeaderAuditRemark(String investLeaderAuditRemark) {
		this.investLeaderAuditRemark = investLeaderAuditRemark;
	}

	/**
	 * @return the investLeaderAuditTime
	 */
	public String getInvestLeaderAuditTime() {
		return investLeaderAuditTime;
	}

	/**
	 * @param investLeaderAuditTime the investLeaderAuditTime to set
	 */
	public void setInvestLeaderAuditTime(String investLeaderAuditTime) {
		this.investLeaderAuditTime = investLeaderAuditTime;
	}

	/**
	 * @return the isInvestLeaderAudit
	 */
	public String getIsInvestLeaderAudit() {
		return isInvestLeaderAudit;
	}

	/**
	 * @param isInvestLeaderAudit the isInvestLeaderAudit to set
	 */
	public void setIsInvestLeaderAudit(String isInvestLeaderAudit) {
		this.isInvestLeaderAudit = isInvestLeaderAudit;
	}

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
	
}
