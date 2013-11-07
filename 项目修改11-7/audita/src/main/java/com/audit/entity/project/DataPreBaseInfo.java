/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class DataPreBaseInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6402574720443309351L;
	
	/**
	 * 预审资料ID
	 */
	private String id;
	
	/**
	 * 立项文号
	 */
	private String projectNo;
	
	/**
	 * 批复金额
	 */
	private String auditMoney;
	
	/**
	 * 立项时间
	 */
	private String projectTime;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目业主ID
	 */
	private String proOwnerId;
	
	/**
	 * 项目业主名称
	 */
	private String proOwnerName;
	
	/**
	 * 项目业主联系人
	 */
	private String proOwnerLink;
	
	/**
	 * 项目业主联系电话
	 */
	private String proOwnerTelphone;
	
	/**
	 * 项目施工单位
	 */
	private String constructId;
	
	/**
	 * 项目施工单位名称
	 */
	private String constructName;

	/**
	 * 施工单位联系人
	 */
	private String constructLink;
	
	/**
	 * 施工单位联系电话
	 */
	private String constructTelphone;
	
	/**
	 * 审计类型
	 */
	private String auditType;
	
	/**
	 * 送审金额
	 */
	private String sentAmount;
	
	/**
	 * 送审时间
	 */
	private String datapreTime;
	
	/**
	 * 是否加急
	 */
	private String isExpedited;
	
	/**
	 * 资料预审意见
	 */
	private String dataPreOpinion;
	
	/**
	 * 资料送审人员
	 */
	private String dataPreOperate;
	
	/**
	 * 工作量时间
	 */
	private String workloadTime;
	
	/**
	 * 勘察时间
	 */
	private String prospectTime;
	
	/**
	 * 补充立项附件
	 */
	private String addProjectApprovalFile;
	
	/**
	 * 概算信息
	 */
	private String budgetInfo;
	
	/**
	 * 概算信息附件
	 */
	private String budgetInfoFile;
	
	/**
	 * 财务收支取证
	 */
	private String financialRAE;
	
	/**
	 * 财务收支取证时间
	 */
	private String financialRAETime;

	
	/**
	 * 补充立项
	 */
	private String addProjectApproval;
	
	/**
	 * 项目总投资
	 */
	private String projectAllInvest;
	
	/**
	 * 备注
	 */
	private String auditNoteRemark;
	

	/**
	 * @return the proOwnerName
	 */
	public String getProOwnerName() {
		return proOwnerName;
	}

	/**
	 * @param proOwnerName the proOwnerName to set
	 */
	public void setProOwnerName(String proOwnerName) {
		this.proOwnerName = proOwnerName;
	}

	/**
	 * @return the projectAllInvest
	 */
	public String getProjectAllInvest() {
		return projectAllInvest;
	}

	/**
	 * @param projectAllInvest the projectAllInvest to set
	 */
	public void setProjectAllInvest(String projectAllInvest) {
		this.projectAllInvest = projectAllInvest;
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
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * @param projectNo the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
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
	 * @return the proOwnerId
	 */
	public String getProOwnerId() {
		return proOwnerId;
	}

	/**
	 * @param proOwnerId the proOwnerId to set
	 */
	public void setProOwnerId(String proOwnerId) {
		this.proOwnerId = proOwnerId;
	}

	/**
	 * @return the proOwnerLink
	 */
	public String getProOwnerLink() {
		return proOwnerLink;
	}

	/**
	 * @param proOwnerLink the proOwnerLink to set
	 */
	public void setProOwnerLink(String proOwnerLink) {
		this.proOwnerLink = proOwnerLink;
	}

	/**
	 * @return the proOwnerTelphone
	 */
	public String getProOwnerTelphone() {
		return proOwnerTelphone;
	}

	/**
	 * @param proOwnerTelphone the proOwnerTelphone to set
	 */
	public void setProOwnerTelphone(String proOwnerTelphone) {
		this.proOwnerTelphone = proOwnerTelphone;
	}

	/**
	 * @return the constructId
	 */
	public String getConstructId() {
		return constructId;
	}

	/**
	 * @param constructId the constructId to set
	 */
	public void setConstructId(String constructId) {
		this.constructId = constructId;
	}

	/**
	 * @return the constructLink
	 */
	public String getConstructLink() {
		return constructLink;
	}

	/**
	 * @param constructLink the constructLink to set
	 */
	public void setConstructLink(String constructLink) {
		this.constructLink = constructLink;
	}

	/**
	 * @return the constructTelphone
	 */
	public String getConstructTelphone() {
		return constructTelphone;
	}

	/**
	 * @param constructTelphone the constructTelphone to set
	 */
	public void setConstructTelphone(String constructTelphone) {
		this.constructTelphone = constructTelphone;
	}

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
	 * @return the isExpedited
	 */
	public String getIsExpedited() {
		return isExpedited;
	}

	/**
	 * @param isExpedited the isExpedited to set
	 */
	public void setIsExpedited(String isExpedited) {
		this.isExpedited = isExpedited;
	}

	/**
	 * @return the dataPreOpinion
	 */
	public String getDataPreOpinion() {
		return dataPreOpinion;
	}

	/**
	 * @param dataPreOpinion the dataPreOpinion to set
	 */
	public void setDataPreOpinion(String dataPreOpinion) {
		this.dataPreOpinion = dataPreOpinion;
	}

	/**
	 * @return the dataPreOperate
	 */
	public String getDataPreOperate() {
		return dataPreOperate;
	}

	/**
	 * @param dataPreOperate the dataPreOperate to set
	 */
	public void setDataPreOperate(String dataPreOperate) {
		this.dataPreOperate = dataPreOperate;
	}

	/**
	 * @return the workloadTime
	 */
	public String getWorkloadTime() {
		return workloadTime;
	}

	/**
	 * @param workloadTime the workloadTime to set
	 */
	public void setWorkloadTime(String workloadTime) {
		this.workloadTime = workloadTime;
	}

	/**
	 * @return the prospectTime
	 */
	public String getProspectTime() {
		return prospectTime;
	}

	/**
	 * @param prospectTime the prospectTime to set
	 */
	public void setProspectTime(String prospectTime) {
		this.prospectTime = prospectTime;
	}

	/**
	 * @return the addProjectApprovalFile
	 */
	public String getAddProjectApprovalFile() {
		return addProjectApprovalFile;
	}

	/**
	 * @param addProjectApprovalFile the addProjectApprovalFile to set
	 */
	public void setAddProjectApprovalFile(String addProjectApprovalFile) {
		this.addProjectApprovalFile = addProjectApprovalFile;
	}

	/**
	 * @return the budgetInfo
	 */
	public String getBudgetInfo() {
		return budgetInfo;
	}

	/**
	 * @param budgetInfo the budgetInfo to set
	 */
	public void setBudgetInfo(String budgetInfo) {
		this.budgetInfo = budgetInfo;
	}

	/**
	 * @return the budgetInfoFile
	 */
	public String getBudgetInfoFile() {
		return budgetInfoFile;
	}

	/**
	 * @param budgetInfoFile the budgetInfoFile to set
	 */
	public void setBudgetInfoFile(String budgetInfoFile) {
		this.budgetInfoFile = budgetInfoFile;
	}

	/**
	 * @return the financialRAE
	 */
	public String getFinancialRAE() {
		return financialRAE;
	}

	/**
	 * @param financialRAE the financialRAE to set
	 */
	public void setFinancialRAE(String financialRAE) {
		this.financialRAE = financialRAE;
	}

	/**
	 * @return the addProjectApproval
	 */
	public String getAddProjectApproval() {
		return addProjectApproval;
	}

	/**
	 * @param addProjectApproval the addProjectApproval to set
	 */
	public void setAddProjectApproval(String addProjectApproval) {
		this.addProjectApproval = addProjectApproval;
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
	 * @return the auditNoteRemark
	 */
	public String getAuditNoteRemark() {
		return auditNoteRemark;
	}

	/**
	 * @param auditNoteRemark the auditNoteRemark to set
	 */
	public void setAuditNoteRemark(String auditNoteRemark) {
		this.auditNoteRemark = auditNoteRemark;
	}

	/**
	 * @return the financialRAETime
	 */
	public String getFinancialRAETime() {
		return financialRAETime;
	}

	/**
	 * @param financialRAETime the financialRAETime to set
	 */
	public void setFinancialRAETime(String financialRAETime) {
		this.financialRAETime = financialRAETime;
	}
	
	
	
}
