/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class AreaLeaderView implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8147477743967607583L;

	/**
	 * 主审信息ID
	 */
	private String mainAuditId;
	
	/**
	 * 分管领导审签时间
	 */
	private String areaLeaderAuditTime;
	
	/**
	 * 审签用户
	 */
	private String areaLeaderAuditUserAccount;
	
	/**
	 * 分管领导审签意见
	 */
	private String areaLeaderAuditRemark;
	
	/**
	 * 分管领导审签状态
	 */
	private String isAreaLeaderAudit;

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
	 * @return the areaLeaderAuditTime
	 */
	public String getAreaLeaderAuditTime() {
		return areaLeaderAuditTime;
	}

	/**
	 * @param areaLeaderAuditTime the areaLeaderAuditTime to set
	 */
	public void setAreaLeaderAuditTime(String areaLeaderAuditTime) {
		this.areaLeaderAuditTime = areaLeaderAuditTime;
	}

	/**
	 * @return the areaLeaderAuditUserAccount
	 */
	public String getAreaLeaderAuditUserAccount() {
		return areaLeaderAuditUserAccount;
	}

	/**
	 * @param areaLeaderAuditUserAccount the areaLeaderAuditUserAccount to set
	 */
	public void setAreaLeaderAuditUserAccount(String areaLeaderAuditUserAccount) {
		this.areaLeaderAuditUserAccount = areaLeaderAuditUserAccount;
	}

	/**
	 * @return the areaLeaderAuditRemark
	 */
	public String getAreaLeaderAuditRemark() {
		return areaLeaderAuditRemark;
	}

	/**
	 * @param areaLeaderAuditRemark the areaLeaderAuditRemark to set
	 */
	public void setAreaLeaderAuditRemark(String areaLeaderAuditRemark) {
		this.areaLeaderAuditRemark = areaLeaderAuditRemark;
	}

	/**
	 * @return the isAreaLeaderAudit
	 */
	public String getIsAreaLeaderAudit() {
		return isAreaLeaderAudit;
	}

	/**
	 * @param isAreaLeaderAudit the isAreaLeaderAudit to set
	 */
	public void setIsAreaLeaderAudit(String isAreaLeaderAudit) {
		this.isAreaLeaderAudit = isAreaLeaderAudit;
	}
}
