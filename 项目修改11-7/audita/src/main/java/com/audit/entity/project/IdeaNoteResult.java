/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class IdeaNoteResult implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6236774571306067014L;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 实施企业名称
	 */
	private String constructionName;

	/**
	 * 实施企业简称
	 */
	private String abbreviation;

	/**
	 * 概算内容
	 */
	private String budgetInfo;

	/**
	 * 项目总投资
	 */
	private String projectAllInvest;

	/**
	 * 招标代理机构
	 */
	private String inviteBidsCompany;

	/**
	 * 施工合同价
	 */
	private String constructionMoney;

	/**
	 * 中标价
	 */
	private String winTheBiddingMoney;

	/**
	 * 项目管理单位
	 */
	private String ownerProject;

	/**
	 * 工程开工时间
	 */
	private String startDate;

	/**
	 * 工程竣工时间
	 */
	private String endDate;

	/**
	 * 建设资金
	 */
	private String buildMoney;

	/**
	 * 报告截止日
	 */
	private String reportEndDate;

	/**
	 * 工程到位资金
	 */
	private String projectAlreadyExistMoney;

	/**
	 * 工程进度款
	 */
	private String projectPlanMoney;
	
	/**
	 * 设计费
	 */
	private String designMoney;
	
	/**
	 * 勘察费
	 */
	private String prospectMoney;
	
	/**
	 * 招标代理费
	 */
	private String agencyInviteBidsMoney;
	
	/**
	 * 监理费
	 */
	private String supervisionMoney;
	
	/**
	 * 项目管理费
	 */
	private String projectManageMoney;
	
	/**
	 * 其他费用
	 */
	private String otherMoney;
	
	/**
	 * 合同工期
	 */
	private String contractTime;
	
	/**
	 * 实际工期
	 */
	private String realityTime;
	
	/**
	 * 合同工期相比延期
	 */
	private String delayTime;
	
	/**
	 * 工期延期主要原因
	 */
	private String delayReason;
	
	/**
	 * 竣工结算多计工程款
	 */
	private String endWorkManyProjectMoney;
	
	/**
	 * 工程直接费用
	 */
	private String projectDirectMoney;

	/**
	 * 工程间接费用
	 */
	private String indirectMoney;
	
	/**
	 * 结算审计费
	 */
	private String settleAccountsAuditMoney;

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
	 * @return the constructionName
	 */
	public String getConstructionName() {
		return constructionName;
	}

	/**
	 * @param constructionName the constructionName to set
	 */
	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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
	 * @return the inviteBidsCompany
	 */
	public String getInviteBidsCompany() {
		return inviteBidsCompany;
	}

	/**
	 * @param inviteBidsCompany the inviteBidsCompany to set
	 */
	public void setInviteBidsCompany(String inviteBidsCompany) {
		this.inviteBidsCompany = inviteBidsCompany;
	}

	/**
	 * @return the constructionMoney
	 */
	public String getConstructionMoney() {
		return constructionMoney;
	}

	/**
	 * @param constructionMoney the constructionMoney to set
	 */
	public void setConstructionMoney(String constructionMoney) {
		this.constructionMoney = constructionMoney;
	}

	/**
	 * @return the winTheBiddingMoney
	 */
	public String getWinTheBiddingMoney() {
		return winTheBiddingMoney;
	}

	/**
	 * @param winTheBiddingMoney the winTheBiddingMoney to set
	 */
	public void setWinTheBiddingMoney(String winTheBiddingMoney) {
		this.winTheBiddingMoney = winTheBiddingMoney;
	}

	/**
	 * @return the ownerProject
	 */
	public String getOwnerProject() {
		return ownerProject;
	}

	/**
	 * @param ownerProject the ownerProject to set
	 */
	public void setOwnerProject(String ownerProject) {
		this.ownerProject = ownerProject;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the buildMoney
	 */
	public String getBuildMoney() {
		return buildMoney;
	}

	/**
	 * @param buildMoney the buildMoney to set
	 */
	public void setBuildMoney(String buildMoney) {
		this.buildMoney = buildMoney;
	}

	/**
	 * @return the reportEndDate
	 */
	public String getReportEndDate() {
		return reportEndDate;
	}

	/**
	 * @param reportEndDate the reportEndDate to set
	 */
	public void setReportEndDate(String reportEndDate) {
		this.reportEndDate = reportEndDate;
	}

	/**
	 * @return the projectAlreadyExistMoney
	 */
	public String getProjectAlreadyExistMoney() {
		return projectAlreadyExistMoney;
	}

	/**
	 * @param projectAlreadyExistMoney the projectAlreadyExistMoney to set
	 */
	public void setProjectAlreadyExistMoney(String projectAlreadyExistMoney) {
		this.projectAlreadyExistMoney = projectAlreadyExistMoney;
	}

	/**
	 * @return the projectPlanMoney
	 */
	public String getProjectPlanMoney() {
		return projectPlanMoney;
	}

	/**
	 * @param projectPlanMoney the projectPlanMoney to set
	 */
	public void setProjectPlanMoney(String projectPlanMoney) {
		this.projectPlanMoney = projectPlanMoney;
	}

	/**
	 * @return the designMoney
	 */
	public String getDesignMoney() {
		return designMoney;
	}

	/**
	 * @param designMoney the designMoney to set
	 */
	public void setDesignMoney(String designMoney) {
		this.designMoney = designMoney;
	}

	/**
	 * @return the prospectMoney
	 */
	public String getProspectMoney() {
		return prospectMoney;
	}

	/**
	 * @param prospectMoney the prospectMoney to set
	 */
	public void setProspectMoney(String prospectMoney) {
		this.prospectMoney = prospectMoney;
	}

	/**
	 * @return the agencyInviteBidsMoney
	 */
	public String getAgencyInviteBidsMoney() {
		return agencyInviteBidsMoney;
	}

	/**
	 * @param agencyInviteBidsMoney the agencyInviteBidsMoney to set
	 */
	public void setAgencyInviteBidsMoney(String agencyInviteBidsMoney) {
		this.agencyInviteBidsMoney = agencyInviteBidsMoney;
	}

	/**
	 * @return the supervisionMoney
	 */
	public String getSupervisionMoney() {
		return supervisionMoney;
	}

	/**
	 * @param supervisionMoney the supervisionMoney to set
	 */
	public void setSupervisionMoney(String supervisionMoney) {
		this.supervisionMoney = supervisionMoney;
	}

	/**
	 * @return the projectManageMoney
	 */
	public String getProjectManageMoney() {
		return projectManageMoney;
	}

	/**
	 * @param projectManageMoney the projectManageMoney to set
	 */
	public void setProjectManageMoney(String projectManageMoney) {
		this.projectManageMoney = projectManageMoney;
	}

	/**
	 * @return the otherMoney
	 */
	public String getOtherMoney() {
		return otherMoney;
	}

	/**
	 * @param otherMoney the otherMoney to set
	 */
	public void setOtherMoney(String otherMoney) {
		this.otherMoney = otherMoney;
	}

	/**
	 * @return the contractTime
	 */
	public String getContractTime() {
		return contractTime;
	}

	/**
	 * @param contractTime the contractTime to set
	 */
	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}

	/**
	 * @return the realityTime
	 */
	public String getRealityTime() {
		return realityTime;
	}

	/**
	 * @param realityTime the realityTime to set
	 */
	public void setRealityTime(String realityTime) {
		this.realityTime = realityTime;
	}

	/**
	 * @return the delayTime
	 */
	public String getDelayTime() {
		return delayTime;
	}

	/**
	 * @param delayTime the delayTime to set
	 */
	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}

	/**
	 * @return the delayReason
	 */
	public String getDelayReason() {
		return delayReason;
	}

	/**
	 * @param delayReason the delayReason to set
	 */
	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}

	/**
	 * @return the endWorkManyProjectMoney
	 */
	public String getEndWorkManyProjectMoney() {
		return endWorkManyProjectMoney;
	}

	/**
	 * @param endWorkManyProjectMoney the endWorkManyProjectMoney to set
	 */
	public void setEndWorkManyProjectMoney(String endWorkManyProjectMoney) {
		this.endWorkManyProjectMoney = endWorkManyProjectMoney;
	}

	/**
	 * @return the projectDirectMoney
	 */
	public String getProjectDirectMoney() {
		return projectDirectMoney;
	}

	/**
	 * @param projectDirectMoney the projectDirectMoney to set
	 */
	public void setProjectDirectMoney(String projectDirectMoney) {
		this.projectDirectMoney = projectDirectMoney;
	}

	/**
	 * @return the indirectMoney
	 */
	public String getIndirectMoney() {
		return indirectMoney;
	}

	/**
	 * @param indirectMoney the indirectMoney to set
	 */
	public void setIndirectMoney(String indirectMoney) {
		this.indirectMoney = indirectMoney;
	}

	/**
	 * @return the settleAccountsAuditMoney
	 */
	public String getSettleAccountsAuditMoney() {
		return settleAccountsAuditMoney;
	}

	/**
	 * @param settleAccountsAuditMoney the settleAccountsAuditMoney to set
	 */
	public void setSettleAccountsAuditMoney(String settleAccountsAuditMoney) {
		this.settleAccountsAuditMoney = settleAccountsAuditMoney;
	}
}
