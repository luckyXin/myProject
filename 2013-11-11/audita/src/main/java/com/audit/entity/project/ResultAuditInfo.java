/**
 * 
 */
package com.audit.entity.project;

/**
 * @author 审核信息
 * 
 */
public class ResultAuditInfo {
	private String username; // 审核人

	private String audittime; // 审核时间

	private String isagree; // 是否同意

	private String remark; // 同意意见
	
	private String deskAuditRemark;//业务科室意见

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}

	public String getIsagree() {
		return isagree;
	}

	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the deskAuditRemark
	 */
	public String getDeskAuditRemark() {
		return deskAuditRemark;
	}

	/**
	 * @param deskAuditRemark the deskAuditRemark to set
	 */
	public void setDeskAuditRemark(String deskAuditRemark) {
		this.deskAuditRemark = deskAuditRemark;
	}
	
	

}
