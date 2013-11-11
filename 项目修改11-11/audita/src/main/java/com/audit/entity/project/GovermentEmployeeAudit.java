/**
 * 
 */
package com.audit.entity.project;

import com.audit.entity.Common;

/**
 * @author User
 *
 */
public class GovermentEmployeeAudit extends Common{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3933111611717755441L;

	/**
	 * 审计ID
	 */
	private String id;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 审计金额
	 */
	private String auditMoney;
	
	/**
	 * 审减金额
	 */
	private String reduceMoney;
	
	/**
	 *  审减率
	 */
	private String auditlv;   
	
	/**
	 * 审计开始时间
	 */
	private String auditStartTime;
	
	/**
	 * 审计结束时间
	 */
	private String auditEndTime;
	
	/**
	 * 审计天数
	 */
	private String auditDayCount;
	
	/**
	 * 审计备注
	 */
	private String auditRemark;
	
	/**
	 * 提交状态
	 */
	private String submitState;
	
	/**
	 * 修改人
	 */
	private String username;
	/**
	 * 修改时间
	 */
	
	private String createtime;
	
    private String datapreId;  //预审资料id
	
	private String auditstate;  //审计状态
	
	private String deskAuditRemark; //业务科室专职复核人员意见
	
	
	

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
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the auditMoney
	 */
	public String getAuditMoney() {
		return auditMoney;
	}

	/**
	 * @param auditMoney the auditMoney to set
	 */
	public void setAuditMoney(String auditMoney) {
		this.auditMoney = auditMoney;
	}

	/**
	 * @return the reduceMoney
	 */
	public String getReduceMoney() {
		return reduceMoney;
	}

	/**
	 * @param reduceMoney the reduceMoney to set
	 */
	public void setReduceMoney(String reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

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
	 * @return the auditDayCount
	 */
	public String getAuditDayCount() {
		return auditDayCount;
	}

	/**
	 * @param auditDayCount the auditDayCount to set
	 */
	public void setAuditDayCount(String auditDayCount) {
		this.auditDayCount = auditDayCount;
	}

	/**
	 * @return the auditRemark
	 */
	public String getAuditRemark() {
		return auditRemark;
	}

	/**
	 * @param auditRemark the auditRemark to set
	 */
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	/**
	 * @return the submitState
	 */
	public String getSubmitState() {
		return submitState;
	}

	/**
	 * @param submitState the submitState to set
	 */
	public void setSubmitState(String submitState) {
		this.submitState = submitState;
	}

	public String getDatapreId() {
		return datapreId;
	}

	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

	public String getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}

	/**
	 * @return the auditlv
	 */
	public String getAuditlv() {
		return auditlv;
	}

	/**
	 * @param auditlv the auditlv to set
	 */
	public void setAuditlv(String auditlv) {
		this.auditlv = auditlv;
	}

	public String getDeskAuditRemark() {
		return deskAuditRemark;
	}

	public void setDeskAuditRemark(String deskAuditRemark) {
		this.deskAuditRemark = deskAuditRemark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
