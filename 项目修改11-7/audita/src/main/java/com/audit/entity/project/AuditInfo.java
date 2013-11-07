/**
 * 
 */
package com.audit.entity.project;

import com.audit.entity.Common;

/**
 * @author User
 * 
 */
public class AuditInfo extends Common{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1753995169868604705L;

	/**
	 * 审批信息ID
	 */
	private String id;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目业主
	 */
	private String ownerName;
	
	/**
	 * 中介机构
	 */
	private String intermediary;
	
	/**
	 * 政府雇员
	 */
	private String governmentEmployee;
	
	/**
	 * 主审
	 */
	private String mainAuditName;
	
	/**
	 * 科长审批类型   
	 */ 
	private String sectionChiefAuditType;
	
    /**
     * 项目安排时间	
     */
	private String projectTime;
	
	/**
	 * 安排用户名称
	 */
	private String userName;
	
	/**
	 * 审计类型
	 */
	private String auditType;
	
	
	private String beginTime;   //复核开始时间
	
	private String endTime;     //复核结束时间
	
	
	private String dataTransferTime;  //复核工程师资料交接时间
	
	
	private String isUrgent;     //是否加急
	
	private String timeLimit;     //限时
	
	/**
	 * 送审金额
	 */
	private String sentAmount;
	
	/**
	 * 审定金额
	 */
	private String auditMoney;
	
	/**
	 * 中介审减金额
	 */
	private String intermediaryAuditCutMoney;
	
	/**
	 * 中介审减率
	 */
	private String intermediaryAuditLv;
	
	/**
	 * 雇员审定金额
	 */
	private String employeeAuditMoney;
	
	/**
	 * 雇员审减金额
	 */
	private String employeeAuditCutMoney;
	
	/**
	 * 复核工程师审减率
	 */
	private String employeeAuditLv;
	
	/**
	 * 工程编码
	 */
	private String datapreno;
	
	/**
	 * 总的审减金额
	 */
	private String totalAuditcutMoney;
	
	/**
	 * 总的审减率
	 */
	private String totalAuditLv;
	
	
	/**
	 * 限时开始时间
	 */
	private String timeLimitStart;
	
	/**
	 * 限时结束时间
	 */
	private String timeLimitEnd;
	
	/**
	 * 最小审减
	 * 用于条件查询
	 * greaterThan  大于的意思
	 */
	private String greaterThan ;
	
	/**
	 * 最大审减
	 * 用于条件查询
	 * lessThan 小于的意思
	 */
	private String lessThan ;
	
	
	
	/**
	 * @return the auditType
	 */
	public String getAuditType() {
		return auditType;
	}


	/**
	 * @param auditType the auditType to set
	 */
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}


	/**
	 * @return the projectTime
	 */
	public String getProjectTime() {
		return projectTime;
	}


	/**
	 * @param projectTime the projectTime to set
	 */
	public void setProjectTime(String projectTime) {
		this.projectTime = projectTime;
	}

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
	 * @return the sectionChiefAuditType
	 */
	public String getSectionChiefAuditType() {
		return sectionChiefAuditType;
	}

	/**
	 * @param sectionChiefAuditType the sectionChiefAuditType to set
	 */
	public void setSectionChiefAuditType(String sectionChiefAuditType) {
		this.sectionChiefAuditType = sectionChiefAuditType;
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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
	 * @return the governmentEmployee
	 */
	public String getGovernmentEmployee() {
		return governmentEmployee;
	}

	/**
	 * @param governmentEmployee the governmentEmployee to set
	 */
	public void setGovernmentEmployee(String governmentEmployee) {
		this.governmentEmployee = governmentEmployee;
	}

	/**
	 * @return the mainAuditName
	 */
	public String getMainAuditName() {
		return mainAuditName;
	}

	/**
	 * @param mainAuditName the mainAuditName to set
	 */
	public void setMainAuditName(String mainAuditName) {
		this.mainAuditName = mainAuditName;
	}


	public String getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getDataTransferTime() {
		return dataTransferTime;
	}


	public void setDataTransferTime(String dataTransferTime) {
		this.dataTransferTime = dataTransferTime;
	}


	public String getIsUrgent() {
		return isUrgent;
	}


	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}


	public String getTimeLimit() {
		return timeLimit;
	}


	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}


	public String getTimeLimitStart() {
		return timeLimitStart;
	}


	public void setTimeLimitStart(String timeLimitStart) {
		this.timeLimitStart = timeLimitStart;
	}


	public String getTimeLimitEnd() {
		return timeLimitEnd;
	}


	public void setTimeLimitEnd(String timeLimitEnd) {
		this.timeLimitEnd = timeLimitEnd;
	}


	public String getAuditMoney() {
		return auditMoney;
	}


	public void setAuditMoney(String auditMoney) {
		this.auditMoney = auditMoney;
	}


	public String getIntermediaryAuditCutMoney() {
		return intermediaryAuditCutMoney;
	}


	public void setIntermediaryAuditCutMoney(String intermediaryAuditCutMoney) {
		this.intermediaryAuditCutMoney = intermediaryAuditCutMoney;
	}


	public String getIntermediaryAuditLv() {
		return intermediaryAuditLv;
	}


	public void setIntermediaryAuditLv(String intermediaryAuditLv) {
		this.intermediaryAuditLv = intermediaryAuditLv;
	}


	public String getEmployeeAuditCutMoney() {
		return employeeAuditCutMoney;
	}


	public void setEmployeeAuditCutMoney(String employeeAuditCutMoney) {
		this.employeeAuditCutMoney = employeeAuditCutMoney;
	}


	public String getEmployeeAuditLv() {
		return employeeAuditLv;
	}


	public void setEmployeeAuditLv(String employeeAuditLv) {
		this.employeeAuditLv = employeeAuditLv;
	}


	public String getDatapreno() {
		return datapreno;
	}


	public void setDatapreno(String datapreno) {
		this.datapreno = datapreno;
	}


	public String getEmployeeAuditMoney() {
		return employeeAuditMoney;
	}


	public void setEmployeeAuditMoney(String employeeAuditMoney) {
		this.employeeAuditMoney = employeeAuditMoney;
	}


	public String getSentAmount() {
		return sentAmount;
	}


	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
	}


	public String getTotalAuditcutMoney() {
		return totalAuditcutMoney;
	}


	public void setTotalAuditcutMoney(String totalAuditcutMoney) {
		this.totalAuditcutMoney = totalAuditcutMoney;
	}


	public String getTotalAuditLv() {
		return totalAuditLv;
	}


	public void setTotalAuditLv(String totalAuditLv) {
		this.totalAuditLv = totalAuditLv;
	}


	public String getGreaterThan() {
		return greaterThan;
	}


	public void setGreaterThan(String greaterThan) {
		this.greaterThan = greaterThan;
	}


	public String getLessThan() {
		return lessThan;
	}


	public void setLessThan(String lessThan) {
		this.lessThan = lessThan;
	}
	
	
	
	
}
