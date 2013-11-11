/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class TractArrangeProjectInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5008121633553386353L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 标段ID
	 */
	private String biaoDuanId;

	/**
	 * 主审
	 */
	private String mainAuditId;
	
	/**
	 * 主审名称
	 */
	private String mainAuditName;

	/**
	 * 复审
	 */
	private List<String> governmentEmployeeIds;
	
	/**
	 * 复审ID
	 */
	private List<String> governmentEmployee;
	
	/**
	 * 复审人员
	 */
	private String governmentEmployeeName;

	/**
	 * 中介机构ID
	 */
	private String intermediaryId;

	/**
	 * 中介机构名称
	 */
	private String intermediaryName;
	
	/**
	 * 中介联系人
	 */
	private String intermediaryLinker;
	
	/**
	 * 联系电话
	 */
	private String intermediaryTelphone;
	
	/**
	 * 中介机构成员
	 */
	private String intermediaryTeamName;

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 是否付费
	 */
	private String isPay;

	/**
	 * 中介机构责任人
	 */
	private String intermediaryDutyName;

	/**
	 * 中介机构组员
	 */
	private List<String> intermediaryNames;

	/**
	 * 项目安排时间
	 */
	private String projectArrangeTime;

	/**
	 * 项目完成时间
	 */
	private String projectCompleteTime;

	/**
	 * 项目开始时间
	 */
	private String projectStartTime;

	/**
	 * 项目结束时间
	 */
	private String projectEndTime;

	/**
	 * 开工令时间
	 */
	private String orderStartTime;

	/**
	 * 安排人员
	 */
	private String arrangeUserAccount;

	/**
	 * 最终更新时间
	 */
	private String updateTime;
	
	/**
	 * @return the intermediaryLinker
	 */
	public String getIntermediaryLinker() {
		return intermediaryLinker;
	}

	/**
	 * @param intermediaryLinker the intermediaryLinker to set
	 */
	public void setIntermediaryLinker(String intermediaryLinker) {
		this.intermediaryLinker = intermediaryLinker;
	}

	/**
	 * @return the intermediaryTelphone
	 */
	public String getIntermediaryTelphone() {
		return intermediaryTelphone;
	}

	/**
	 * @param intermediaryTelphone the intermediaryTelphone to set
	 */
	public void setIntermediaryTelphone(String intermediaryTelphone) {
		this.intermediaryTelphone = intermediaryTelphone;
	}

	/**
	 * @return the intermediaryTeamName
	 */
	public String getIntermediaryTeamName() {
		return intermediaryTeamName;
	}

	/**
	 * @param intermediaryTeamName the intermediaryTeamName to set
	 */
	public void setIntermediaryTeamName(String intermediaryTeamName) {
		this.intermediaryTeamName = intermediaryTeamName;
	}

	/**
	 * @return the governmentEmployee
	 */
	public List<String> getGovernmentEmployee() {
		return governmentEmployee;
	}

	/**
	 * @param governmentEmployee the governmentEmployee to set
	 */
	public void setGovernmentEmployee(List<String> governmentEmployee) {
		this.governmentEmployee = governmentEmployee;
	}

	/**
	 * @return the governmentEmployeeName
	 */
	public String getGovernmentEmployeeName() {
		return governmentEmployeeName;
	}

	/**
	 * @param governmentEmployeeName the governmentEmployeeName to set
	 */
	public void setGovernmentEmployeeName(String governmentEmployeeName) {
		this.governmentEmployeeName = governmentEmployeeName;
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

	/**
	 * @return the governmentEmployeeIds
	 */
	public List<String> getGovernmentEmployeeIds() {
		return governmentEmployeeIds;
	}

	/**
	 * @param governmentEmployeeIds
	 *            the governmentEmployeeIds to set
	 */
	public void setGovernmentEmployeeIds(List<String> governmentEmployeeIds) {
		this.governmentEmployeeIds = governmentEmployeeIds;
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
	 * @return the biaoDuanId
	 */
	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	/**
	 * @param biaoDuanId
	 *            the biaoDuanId to set
	 */
	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	/**
	 * @return the mainAuditId
	 */
	public String getMainAuditId() {
		return mainAuditId;
	}

	/**
	 * @param mainAuditId
	 *            the mainAuditId to set
	 */
	public void setMainAuditId(String mainAuditId) {
		this.mainAuditId = mainAuditId;
	}

	/**
	 * @return the intermediaryId
	 */
	public String getIntermediaryId() {
		return intermediaryId;
	}

	/**
	 * @param intermediaryId
	 *            the intermediaryId to set
	 */
	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	/**
	 * @return the intermediaryName
	 */
	public String getIntermediaryName() {
		return intermediaryName;
	}

	/**
	 * @param intermediaryName
	 *            the intermediaryName to set
	 */
	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the isPay
	 */
	public String getIsPay() {
		return isPay;
	}

	/**
	 * @param isPay
	 *            the isPay to set
	 */
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	/**
	 * @return the intermediaryDutyName
	 */
	public String getIntermediaryDutyName() {
		return intermediaryDutyName;
	}

	/**
	 * @param intermediaryDutyName
	 *            the intermediaryDutyName to set
	 */
	public void setIntermediaryDutyName(String intermediaryDutyName) {
		this.intermediaryDutyName = intermediaryDutyName;
	}

	/**
	 * @return the intermediaryNames
	 */
	public List<String> getIntermediaryNames() {
		return intermediaryNames;
	}

	/**
	 * @param intermediaryNames
	 *            the intermediaryNames to set
	 */
	public void setIntermediaryNames(List<String> intermediaryNames) {
		this.intermediaryNames = intermediaryNames;
	}

	/**
	 * @return the projectArrangeTime
	 */
	public String getProjectArrangeTime() {
		return projectArrangeTime;
	}

	/**
	 * @param projectArrangeTime
	 *            the projectArrangeTime to set
	 */
	public void setProjectArrangeTime(String projectArrangeTime) {
		this.projectArrangeTime = projectArrangeTime;
	}

	/**
	 * @return the projectCompleteTime
	 */
	public String getProjectCompleteTime() {
		return projectCompleteTime;
	}

	/**
	 * @param projectCompleteTime
	 *            the projectCompleteTime to set
	 */
	public void setProjectCompleteTime(String projectCompleteTime) {
		this.projectCompleteTime = projectCompleteTime;
	}

	/**
	 * @return the projectStartTime
	 */
	public String getProjectStartTime() {
		return projectStartTime;
	}

	/**
	 * @param projectStartTime
	 *            the projectStartTime to set
	 */
	public void setProjectStartTime(String projectStartTime) {
		this.projectStartTime = projectStartTime;
	}

	/**
	 * @return the projectEndTime
	 */
	public String getProjectEndTime() {
		return projectEndTime;
	}

	/**
	 * @param projectEndTime
	 *            the projectEndTime to set
	 */
	public void setProjectEndTime(String projectEndTime) {
		this.projectEndTime = projectEndTime;
	}

	/**
	 * @return the orderStartTime
	 */
	public String getOrderStartTime() {
		return orderStartTime;
	}

	/**
	 * @param orderStartTime
	 *            the orderStartTime to set
	 */
	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	/**
	 * @return the arrangeUserAccount
	 */
	public String getArrangeUserAccount() {
		return arrangeUserAccount;
	}

	/**
	 * @param arrangeUserAccount
	 *            the arrangeUserAccount to set
	 */
	public void setArrangeUserAccount(String arrangeUserAccount) {
		this.arrangeUserAccount = arrangeUserAccount;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
