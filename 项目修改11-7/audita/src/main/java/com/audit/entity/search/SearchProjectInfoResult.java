/**
 * 
 */
package com.audit.entity.search;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class SearchProjectInfoResult implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8280016096902856553L;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 施工单位
	 */
	private String constructName;

	/**
	 * 中介机构
	 */
	private String intermediary;

	/**
	 * 政府工程师
	 */
	private String employeeAudits;

	/**
	 * 主审
	 */
	private String mainAuditName;

	/**
	 * 项目业主
	 */
	private String ownerName;
	
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
	 * 接收建设单位资料时间
	 */
	private String datapretime;
	
	/**
	 * 资料发出时间
	 */
    private String intermediarySendTime;
    
    
    /**
     * 应完成时间
     */
    private String intermediaryAuditTime;
    
    /**
     * 交回复核时间
     */
    private String dataTransferTime;
    
    /**
     * 超时天数
     */
    private String deferday;
    
    /**
     * 开始复核时间
     */
    private String auditStartTime;
    
    /**
     * 复核结果确认时间
     */
    private String auditEndTime;
    
    /**
     * 实际复核天数
     */
    private String auditDayCount;
    
    /**
     * 复核工程师进度说明
     */
    private String auditRemark;
    
    /**
     * 审计报告时间
     */
    private String auditReportTime;
    
    /**
     * 报告所用天数
     */
    private String dayCount;
    
    /**
     * 审计报告文号
     */
    private String auditReportCode;
    
    /**
     * 开工时间
     */
    private String projectStartTime;
    
    /**
     * 竣工时间
     */
    private String projectEndTime;
	

	/**
	 * @return the sentAmount
	 */
	public String getSentAmount() {
		return sentAmount;
	}

	/**
	 * @param sentAmount the sentAmount to set
	 */
	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
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
	 * @return the intermediaryAuditCutMoney
	 */
	public String getIntermediaryAuditCutMoney() {
		return intermediaryAuditCutMoney;
	}

	/**
	 * @param intermediaryAuditCutMoney the intermediaryAuditCutMoney to set
	 */
	public void setIntermediaryAuditCutMoney(String intermediaryAuditCutMoney) {
		this.intermediaryAuditCutMoney = intermediaryAuditCutMoney;
	}

	/**
	 * @return the intermediaryAuditLv
	 */
	public String getIntermediaryAuditLv() {
		return intermediaryAuditLv;
	}

	/**
	 * @param intermediaryAuditLv the intermediaryAuditLv to set
	 */
	public void setIntermediaryAuditLv(String intermediaryAuditLv) {
		this.intermediaryAuditLv = intermediaryAuditLv;
	}

	/**
	 * @return the employeeAuditCutMoney
	 */
	public String getEmployeeAuditCutMoney() {
		return employeeAuditCutMoney;
	}

	/**
	 * @param employeeAuditCutMoney the employeeAuditCutMoney to set
	 */
	public void setEmployeeAuditCutMoney(String employeeAuditCutMoney) {
		this.employeeAuditCutMoney = employeeAuditCutMoney;
	}

	/**
	 * @return the employeeAuditLv
	 */
	public String getEmployeeAuditLv() {
		return employeeAuditLv;
	}

	/**
	 * @param employeeAuditLv the employeeAuditLv to set
	 */
	public void setEmployeeAuditLv(String employeeAuditLv) {
		this.employeeAuditLv = employeeAuditLv;
	}

	/**
	 * @return the datapreno
	 */
	public String getDatapreno() {
		return datapreno;
	}

	/**
	 * @param datapreno the datapreno to set
	 */
	public void setDatapreno(String datapreno) {
		this.datapreno = datapreno;
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
	 * @return the constructName
	 */
	public String getConstructName() {
		return constructName;
	}

	/**
	 * @param constructName the constructName to set
	 */
	public void setConstructName(String constructName) {
		this.constructName = constructName;
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
	 * @return the employeeAudits
	 */
	public String getEmployeeAudits() {
		return employeeAudits;
	}

	/**
	 * @param employeeAudits the employeeAudits to set
	 */
	public void setEmployeeAudits(String employeeAudits) {
		this.employeeAudits = employeeAudits;
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

	public String getDatapretime() {
		return datapretime;
	}

	public void setDatapretime(String datapretime) {
		this.datapretime = datapretime;
	}

	public String getIntermediarySendTime() {
		return intermediarySendTime;
	}

	public void setIntermediarySendTime(String intermediarySendTime) {
		this.intermediarySendTime = intermediarySendTime;
	}

	public String getIntermediaryAuditTime() {
		return intermediaryAuditTime;
	}

	public void setIntermediaryAuditTime(String intermediaryAuditTime) {
		this.intermediaryAuditTime = intermediaryAuditTime;
	}

	public String getDataTransferTime() {
		return dataTransferTime;
	}

	public void setDataTransferTime(String dataTransferTime) {
		this.dataTransferTime = dataTransferTime;
	}

	public String getDeferday() {
		return deferday;
	}

	public void setDeferday(String deferday) {
		this.deferday = deferday;
	}

	public String getAuditStartTime() {
		return auditStartTime;
	}

	public void setAuditStartTime(String auditStartTime) {
		this.auditStartTime = auditStartTime;
	}

	public String getAuditEndTime() {
		return auditEndTime;
	}

	public void setAuditEndTime(String auditEndTime) {
		this.auditEndTime = auditEndTime;
	}

	public String getAuditDayCount() {
		return auditDayCount;
	}

	public void setAuditDayCount(String auditDayCount) {
		this.auditDayCount = auditDayCount;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public String getAuditReportTime() {
		return auditReportTime;
	}

	public void setAuditReportTime(String auditReportTime) {
		this.auditReportTime = auditReportTime;
	}

	public String getDayCount() {
		return dayCount;
	}

	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	public String getAuditReportCode() {
		return auditReportCode;
	}

	public void setAuditReportCode(String auditReportCode) {
		this.auditReportCode = auditReportCode;
	}

	public String getProjectStartTime() {
		return projectStartTime;
	}

	public void setProjectStartTime(String projectStartTime) {
		this.projectStartTime = projectStartTime;
	}

	public String getProjectEndTime() {
		return projectEndTime;
	}

	public void setProjectEndTime(String projectEndTime) {
		this.projectEndTime = projectEndTime;
	}
	
	
}
