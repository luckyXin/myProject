/**
 * 单项目安排信息
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.util.List;

import com.audit.entity.Common;

/**
 * @author dengXin
 */
public class SingleProjectInfo extends Common implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8577839823927738708L;

	/**
	 * 单项目安排ID
	 */
	private String id;

	/**
	 * 项目预审资料ID
	 */
	private String datapreId;

	/**
	 * 项目安排时间
	 */
	private String projectTime;

	/**
	 * 项目安排备注
	 */
	private String projectRemark;

	/**
	 * 中介机构ID
	 */
	private String intermediaryId;

	/**
	 * 提交中介送审时间
	 */
	private String intermediarySendTime;

	/**
	 * 中介机构审批状态 0 ：未审批 1：审批
	 */
	private String intermediaryAuditState;

	/**
	 * 政府雇员审批状态0 ：未审批 1：审批
	 */
	private String governmentEmployeeAuditState;

	/**
	 * 科长审批状态 0 ：未审批 1：通过 2：未通过
	 */
	private String sectionChiefAuditState;

	/**
	 * 区域领导审批状态 0 ：未审批 1：通过 2：未通过
	 */
	private String areaLeaderAuditState;

	/**
	 * 主审审批状态 0 ：未审批 1：通过 2：未通过
	 */
	private String mainAuditState;

	/**
	 * 主审人员ID
	 */
	private String mainAuditId;

	/**
	 * 主审人员名称
	 */
	private String mainAuditName;

	/**
	 * 政府雇员审批人员
	 */
	private List<String> governmentEmployee;

	/**
	 * 投资项目名称
	 */
	private String projectName;

	/**
	 * 送审金额
	 */
	private String sentAmount;

	/**
	 * 资料预审时间
	 */
	private String datapreTime;

	/**
	 * 项目安排状态 0 ：禁用 1：启用 2：删除
	 */
	private String state;

	/**
	 * 业主名称
	 */
	private String ownerName;
	
	/**
	 * 创建用户
	 */
	private String userAccount;
	
	/**
	 * 安排状态  0 ：未安排 1：已安排 
	 */
	private String isArrangement;

	/**
	 * @return the isArrangement
	 */
	public String getIsArrangement() {
		return isArrangement;
	}

	/**
	 * @param isArrangement the isArrangement to set
	 */
	public void setIsArrangement(String isArrangement) {
		this.isArrangement = isArrangement;
	}

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the datapreTime
	 */
	public String getDatapreTime() {
		return datapreTime;
	}

	/**
	 * @param datapreTime the datapreTime to set
	 */
	public void setDatapreTime(String datapreTime) {
		this.datapreTime = datapreTime;
	}

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
	 * @return the datapreId
	 */
	public String getDatapreId() {
		return datapreId;
	}

	/**
	 * @param datapreId the datapreId to set
	 */
	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
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
	 * @return the projectRemark
	 */
	public String getProjectRemark() {
		return projectRemark;
	}

	/**
	 * @param projectRemark the projectRemark to set
	 */
	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	/**
	 * @return the intermediaryId
	 */
	public String getIntermediaryId() {
		return intermediaryId;
	}

	/**
	 * @param intermediaryId the intermediaryId to set
	 */
	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	/**
	 * @return the intermediarySendTime
	 */
	public String getIntermediarySendTime() {
		return intermediarySendTime;
	}

	/**
	 * @param intermediarySendTime the intermediarySendTime to set
	 */
	public void setIntermediarySendTime(String intermediarySendTime) {
		this.intermediarySendTime = intermediarySendTime;
	}

	/**
	 * @return the intermediaryAuditState
	 */
	public String getIntermediaryAuditState() {
		return intermediaryAuditState;
	}

	/**
	 * @param intermediaryAuditState the intermediaryAuditState to set
	 */
	public void setIntermediaryAuditState(String intermediaryAuditState) {
		this.intermediaryAuditState = intermediaryAuditState;
	}

	/**
	 * @return the governmentEmployeeAuditState
	 */
	public String getGovernmentEmployeeAuditState() {
		return governmentEmployeeAuditState;
	}

	/**
	 * @param governmentEmployeeAuditState the governmentEmployeeAuditState to
	 *            set
	 */
	public void setGovernmentEmployeeAuditState(String governmentEmployeeAuditState) {
		this.governmentEmployeeAuditState = governmentEmployeeAuditState;
	}

	/**
	 * @return the sectionChiefAuditState
	 */
	public String getSectionChiefAuditState() {
		return sectionChiefAuditState;
	}

	/**
	 * @param sectionChiefAuditState the sectionChiefAuditState to set
	 */
	public void setSectionChiefAuditState(String sectionChiefAuditState) {
		this.sectionChiefAuditState = sectionChiefAuditState;
	}

	/**
	 * @return the areaLeaderAuditState
	 */
	public String getAreaLeaderAuditState() {
		return areaLeaderAuditState;
	}

	/**
	 * @param areaLeaderAuditState the areaLeaderAuditState to set
	 */
	public void setAreaLeaderAuditState(String areaLeaderAuditState) {
		this.areaLeaderAuditState = areaLeaderAuditState;
	}

	/**
	 * @return the mainAuditState
	 */
	public String getMainAuditState() {
		return mainAuditState;
	}

	/**
	 * @param mainAuditState the mainAuditState to set
	 */
	public void setMainAuditState(String mainAuditState) {
		this.mainAuditState = mainAuditState;
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
}
