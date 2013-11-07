/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class AuditReportInfo implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3343684376003186823L;

	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 施工企业
	 */
	private String constuctUtil;
	
	/**
	 * 业主名称
	 */
	private String ownerName;
	
	/**
	 * 业主简称
	 */
	private String ownerAbbreviation;
	
	/**
	 * 开工年
	 */
	private String startYear;
	
	/**
	 * 开工月
	 */
	private String startMonth;
	
	/**
	 * 开工日
	 */
	private String startDay;
	
	/**
	 * 竣工年
	 */
	private String endYear;
	
	/**
	 * 竣工月
	 */
	private String endMonth;
	
	/**
	 * 竣工日
	 */
	private String endDay;
	
	/**
	 * 代拟人
	 */
	private String proxyPeople;
	
	/**
	 * 工程直接费用
	 */
	private String projectDirectMoney;
	
	/**
	 * 工程总投资概算(万元)
	 */
	private String totalInvestmentBudget;
	
	/**
	 * 审减率
	 */
	private String auditCutRate;
	
	/**
	 * 设计单位
	 */
	private String designCompany;
	
	/**
	 * 勘察单位
	 */
	private String prospectCompany;
	
	/**
	 * 项目管理单位
	 */
	private String projectManageCompany;
	
	/**
	 * 监理公司
	 */
	private String supervisorCompany;
	
	/**
	 * 建设金额
	 */
	private String buildMoney;
	
	/**
	 * 到位资金
	 */
	private String alreadyMoney;
	
	/**
	 * 工程进度款
	 */
	private String progressPayment;
	
	/**
	 * 设计费用
	 */
	private String designMoney;
	
	/**
	 * 勘察费
	 */
	private String prospectMoney;
	
	/**
	 * 招标代理费
	 */
	private String theTenderFee;
	
	/**
	 * 实际完成投资金额
	 */
	private String actualCompeleteMoney;
	
	/**
	 * 超过批复的工程概算总投资额
	 */
	private String beyondMoney;
	
	/**
	 * 区财政预算安排资金
	 */
	private String areaMoney;
	
	/**
	 * 市级及以上资金
	 */
	private String cityMoney;
	
	/**
	 * 自筹资金
	 */
	private String selfRaisedMoney;
	
	/**
	 * 银行贷款
	 */
	private String bankMoney;
	
	/**
	 * 其他资金
	 */
	private String otherInputMoney;
	
	/**
	 * 工程咨询费用
	 */
	private String projectConsultMoney;
	
	/**
	 * 环评费
	 */
	private String eiaMoney;
	
	/**
	 * 清理费
	 */
	private String clearMoney;
	
	/**
	 * 项管费
	 */
	private String projectManageMoney;
	
	/**
	 * 审计费
	 */
	private String auditMoney;
	
	/**
	 * 其他费用
	 */
	private String otherOutMoney;
	
	/**
	 * @return the areaMoney
	 */
	public String getAreaMoney() {
		return areaMoney;
	}

	/**
	 * @param areaMoney the areaMoney to set
	 */
	public void setAreaMoney(String areaMoney) {
		this.areaMoney = areaMoney;
	}

	/**
	 * @return the cityMoney
	 */
	public String getCityMoney() {
		return cityMoney;
	}

	/**
	 * @param cityMoney the cityMoney to set
	 */
	public void setCityMoney(String cityMoney) {
		this.cityMoney = cityMoney;
	}

	/**
	 * @return the selfRaisedMoney
	 */
	public String getSelfRaisedMoney() {
		return selfRaisedMoney;
	}

	/**
	 * @param selfRaisedMoney the selfRaisedMoney to set
	 */
	public void setSelfRaisedMoney(String selfRaisedMoney) {
		this.selfRaisedMoney = selfRaisedMoney;
	}

	/**
	 * @return the bankMoney
	 */
	public String getBankMoney() {
		return bankMoney;
	}

	/**
	 * @param bankMoney the bankMoney to set
	 */
	public void setBankMoney(String bankMoney) {
		this.bankMoney = bankMoney;
	}

	/**
	 * @return the otherInputMoney
	 */
	public String getOtherInputMoney() {
		return otherInputMoney;
	}

	/**
	 * @param otherInputMoney the otherInputMoney to set
	 */
	public void setOtherInputMoney(String otherInputMoney) {
		this.otherInputMoney = otherInputMoney;
	}

	/**
	 * @return the projectConsultMoney
	 */
	public String getProjectConsultMoney() {
		return projectConsultMoney;
	}

	/**
	 * @param projectConsultMoney the projectConsultMoney to set
	 */
	public void setProjectConsultMoney(String projectConsultMoney) {
		this.projectConsultMoney = projectConsultMoney;
	}

	/**
	 * @return the eiaMoney
	 */
	public String getEiaMoney() {
		return eiaMoney;
	}

	/**
	 * @param eiaMoney the eiaMoney to set
	 */
	public void setEiaMoney(String eiaMoney) {
		this.eiaMoney = eiaMoney;
	}

	/**
	 * @return the clearMoney
	 */
	public String getClearMoney() {
		return clearMoney;
	}

	/**
	 * @param clearMoney the clearMoney to set
	 */
	public void setClearMoney(String clearMoney) {
		this.clearMoney = clearMoney;
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
	 * @return the otherOutMoney
	 */
	public String getOtherOutMoney() {
		return otherOutMoney;
	}

	/**
	 * @param otherOutMoney the otherOutMoney to set
	 */
	public void setOtherOutMoney(String otherOutMoney) {
		this.otherOutMoney = otherOutMoney;
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
	 * @return the constuctUtil
	 */
	public String getConstuctUtil() {
		return constuctUtil;
	}

	/**
	 * @param constuctUtil the constuctUtil to set
	 */
	public void setConstuctUtil(String constuctUtil) {
		this.constuctUtil = constuctUtil;
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
	 * @return the ownerAbbreviation
	 */
	public String getOwnerAbbreviation() {
		return ownerAbbreviation;
	}

	/**
	 * @param ownerAbbreviation the ownerAbbreviation to set
	 */
	public void setOwnerAbbreviation(String ownerAbbreviation) {
		this.ownerAbbreviation = ownerAbbreviation;
	}

	/**
	 * @return the startYear
	 */
	public String getStartYear() {
		return startYear;
	}

	/**
	 * @param startYear the startYear to set
	 */
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	/**
	 * @return the startMonth
	 */
	public String getStartMonth() {
		return startMonth;
	}

	/**
	 * @param startMonth the startMonth to set
	 */
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	/**
	 * @return the startDay
	 */
	public String getStartDay() {
		return startDay;
	}

	/**
	 * @param startDay the startDay to set
	 */
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	/**
	 * @return the endYear
	 */
	public String getEndYear() {
		return endYear;
	}

	/**
	 * @param endYear the endYear to set
	 */
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	/**
	 * @return the endMonth
	 */
	public String getEndMonth() {
		return endMonth;
	}

	/**
	 * @param endMonth the endMonth to set
	 */
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	/**
	 * @return the endDay
	 */
	public String getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay the endDay to set
	 */
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	/**
	 * @return the proxyPeople
	 */
	public String getProxyPeople() {
		return proxyPeople;
	}

	/**
	 * @param proxyPeople the proxyPeople to set
	 */
	public void setProxyPeople(String proxyPeople) {
		this.proxyPeople = proxyPeople;
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
	 * @return the totalInvestmentBudget
	 */
	public String getTotalInvestmentBudget() {
		return totalInvestmentBudget;
	}

	/**
	 * @param totalInvestmentBudget the totalInvestmentBudget to set
	 */
	public void setTotalInvestmentBudget(String totalInvestmentBudget) {
		this.totalInvestmentBudget = totalInvestmentBudget;
	}

	/**
	 * @return the auditCutRate
	 */
	public String getAuditCutRate() {
		return auditCutRate;
	}

	/**
	 * @param auditCutRate the auditCutRate to set
	 */
	public void setAuditCutRate(String auditCutRate) {
		this.auditCutRate = auditCutRate;
	}

	/**
	 * @return the designCompany
	 */
	public String getDesignCompany() {
		return designCompany;
	}

	/**
	 * @param designCompany the designCompany to set
	 */
	public void setDesignCompany(String designCompany) {
		this.designCompany = designCompany;
	}

	/**
	 * @return the prospectCompany
	 */
	public String getProspectCompany() {
		return prospectCompany;
	}

	/**
	 * @param prospectCompany the prospectCompany to set
	 */
	public void setProspectCompany(String prospectCompany) {
		this.prospectCompany = prospectCompany;
	}

	/**
	 * @return the projectManageCompany
	 */
	public String getProjectManageCompany() {
		return projectManageCompany;
	}

	/**
	 * @param projectManageCompany the projectManageCompany to set
	 */
	public void setProjectManageCompany(String projectManageCompany) {
		this.projectManageCompany = projectManageCompany;
	}

	/**
	 * @return the supervisorCompany
	 */
	public String getSupervisorCompany() {
		return supervisorCompany;
	}

	/**
	 * @param supervisorCompany the supervisorCompany to set
	 */
	public void setSupervisorCompany(String supervisorCompany) {
		this.supervisorCompany = supervisorCompany;
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
	 * @return the alreadyMoney
	 */
	public String getAlreadyMoney() {
		return alreadyMoney;
	}

	/**
	 * @param alreadyMoney the alreadyMoney to set
	 */
	public void setAlreadyMoney(String alreadyMoney) {
		this.alreadyMoney = alreadyMoney;
	}

	/**
	 * @return the progressPayment
	 */
	public String getProgressPayment() {
		return progressPayment;
	}

	/**
	 * @param progressPayment the progressPayment to set
	 */
	public void setProgressPayment(String progressPayment) {
		this.progressPayment = progressPayment;
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
	 * @return the theTenderFee
	 */
	public String getTheTenderFee() {
		return theTenderFee;
	}

	/**
	 * @param theTenderFee the theTenderFee to set
	 */
	public void setTheTenderFee(String theTenderFee) {
		this.theTenderFee = theTenderFee;
	}

	/**
	 * @return the actualCompeleteMoney
	 */
	public String getActualCompeleteMoney() {
		return actualCompeleteMoney;
	}

	/**
	 * @param actualCompeleteMoney the actualCompeleteMoney to set
	 */
	public void setActualCompeleteMoney(String actualCompeleteMoney) {
		this.actualCompeleteMoney = actualCompeleteMoney;
	}

	/**
	 * @return the beyondMoney
	 */
	public String getBeyondMoney() {
		return beyondMoney;
	}

	/**
	 * @param beyondMoney the beyondMoney to set
	 */
	public void setBeyondMoney(String beyondMoney) {
		this.beyondMoney = beyondMoney;
	}
}
