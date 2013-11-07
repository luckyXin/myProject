/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class MainAuditInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 270680086955485246L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 审减总金额
	 */
	private String auditReduceAllMoney;

	/**
	 * 主审人员
	 */
	private String mainAuditPeople;

	/**
	 * 出审计报告时间
	 */
	private String auditReportTime;

	/**
	 * 审计报告文件编号
	 */
	private String auditReportCode;

	/**
	 * 审计报告文件
	 */
	private String auditReportFile;

	/**
	 * 审计进度
	 */
	private String auditReportRemark;

	/**
	 * 审定投资完成额
	 */
	private String auditInvestAllMoney;

	/**
	 * 审定建设内容及规模
	 */
	private String auditContentAndScale;

	/**
	 * 项目建设中违规金额
	 */
	private String noNormMoney;

	/**
	 * 其它不规范金额
	 */
	private String otherNoNormMoney;

	/**
	 * 其他不违规描述
	 */
	private String otherNoNormRemark;

	/**
	 * 其他问题
	 */
	private String otherNoNormProblem;
	
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 违规问题描述
	 */
	private String problemRemark;

	/**
	 * 违规问题
	 */
	private String noNormProblem;
	
	/**
	 * 提交状态
	 */
	private String submitState;
	
	/**
	 * 安排项目ID
	 */
	private String arrangeId;
	
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
	 * 项目开工时间
	 */
	private String projectStartTime;
	
	/**
	 * 项目竣工时间
	 */
	private String projectEndTime;
	
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
	 * 天数
	 */
	private String dayCount;
	
	/**
	 * 金额
	 */
	private String money;
	
	/**
	 * 监理费
	 */
	private String supervisorMoney;
	
	/**
	 * 送审金额
	 */
	private String sentAuditMone;
	
	/**
	 * 工程主要施工内容
	 */
	private String maincontent;
	
	/**
	 * 资金支出 表id  外键。
	 */
	private String capitalexpendituresid;
	
	/**
	 * 问题表 id   外键
	 */
	private String roblemsid;
	/**
	 * 工程间接费用
	 */
	
	private String indirectcosts;
	/**
	 * 主审审定金额
	 */
	private String createtime;
	
	/**
	 * @return the supervisorMoney
	 */
	public String getSupervisorMoney() {
		return supervisorMoney;
	}

	/**
	 * @param supervisorMoney the supervisorMoney to set
	 */
	public void setSupervisorMoney(String supervisorMoney) {
		this.supervisorMoney = supervisorMoney;
	}

	/**
	 * @return the dayCount
	 */
	public String getDayCount() {
		return dayCount;
	}

	/**
	 * @param dayCount the dayCount to set
	 */
	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

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
	 * @return the projectStartTime
	 */
	public String getProjectStartTime() {
		return projectStartTime;
	}

	/**
	 * @param projectStartTime the projectStartTime to set
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
	 * @param projectEndTime the projectEndTime to set
	 */
	public void setProjectEndTime(String projectEndTime) {
		this.projectEndTime = projectEndTime;
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

	/**
	 * @return the otherNoNormProblem
	 */
	public String getOtherNoNormProblem() {
		return otherNoNormProblem;
	}

	/**
	 * @param otherNoNormProblem the otherNoNormProblem to set
	 */
	public void setOtherNoNormProblem(String otherNoNormProblem) {
		this.otherNoNormProblem = otherNoNormProblem;
	}

	/**
	 * @return the arrangeId
	 */
	public String getArrangeId() {
		return arrangeId;
	}

	/**
	 * @param arrangeId the arrangeId to set
	 */
	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
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

	/**
	 * @return the noNormProblem
	 */
	public String getNoNormProblem() {
		return noNormProblem;
	}

	/**
	 * @param noNormProblem the noNormProblem to set
	 */
	public void setNoNormProblem(String noNormProblem) {
		this.noNormProblem = noNormProblem;
	}

	/**
	 * @return the problemRemark
	 */
	public String getProblemRemark() {
		return problemRemark;
	}

	/**
	 * @param problemRemark the problemRemark to set
	 */
	public void setProblemRemark(String problemRemark) {
		this.problemRemark = problemRemark;
	}

	/**
	 * @return the otherNoNormRemark
	 */
	public String getOtherNoNormRemark() {
		return otherNoNormRemark;
	}

	/**
	 * @param otherNoNormRemark the otherNoNormRemark to set
	 */
	public void setOtherNoNormRemark(String otherNoNormRemark) {
		this.otherNoNormRemark = otherNoNormRemark;
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
	 * @return the auditReduceAllMoney
	 */
	public String getAuditReduceAllMoney() {
		return auditReduceAllMoney;
	}

	/**
	 * @param auditReduceAllMoney the auditReduceAllMoney to set
	 */
	public void setAuditReduceAllMoney(String auditReduceAllMoney) {
		this.auditReduceAllMoney = auditReduceAllMoney;
	}

	/**
	 * @return the mainAuditPeople
	 */
	public String getMainAuditPeople() {
		return mainAuditPeople;
	}

	/**
	 * @param mainAuditPeople the mainAuditPeople to set
	 */
	public void setMainAuditPeople(String mainAuditPeople) {
		this.mainAuditPeople = mainAuditPeople;
	}

	/**
	 * @return the auditReportTime
	 */
	public String getAuditReportTime() {
		return auditReportTime;
	}

	/**
	 * @param auditReportTime the auditReportTime to set
	 */
	public void setAuditReportTime(String auditReportTime) {
		this.auditReportTime = auditReportTime;
	}

	/**
	 * @return the auditReportCode
	 */
	public String getAuditReportCode() {
		return auditReportCode;
	}

	/**
	 * @param auditReportCode the auditReportCode to set
	 */
	public void setAuditReportCode(String auditReportCode) {
		this.auditReportCode = auditReportCode;
	}

	/**
	 * @return the auditReportFile
	 */
	public String getAuditReportFile() {
		return auditReportFile;
	}

	/**
	 * @param auditReportFile the auditReportFile to set
	 */
	public void setAuditReportFile(String auditReportFile) {
		this.auditReportFile = auditReportFile;
	}

	/**
	 * @return the auditReportRemark
	 */
	public String getAuditReportRemark() {
		return auditReportRemark;
	}

	/**
	 * @param auditReportRemark the auditReportRemark to set
	 */
	public void setAuditReportRemark(String auditReportRemark) {
		this.auditReportRemark = auditReportRemark;
	}

	/**
	 * @return the auditInvestAllMoney
	 */
	public String getAuditInvestAllMoney() {
		return auditInvestAllMoney;
	}

	/**
	 * @param auditInvestAllMoney the auditInvestAllMoney to set
	 */
	public void setAuditInvestAllMoney(String auditInvestAllMoney) {
		this.auditInvestAllMoney = auditInvestAllMoney;
	}

	/**
	 * @return the auditContentAndScale
	 */
	public String getAuditContentAndScale() {
		return auditContentAndScale;
	}

	/**
	 * @param auditContentAndScale the auditContentAndScale to set
	 */
	public void setAuditContentAndScale(String auditContentAndScale) {
		this.auditContentAndScale = auditContentAndScale;
	}

	/**
	 * @return the noNormMoney
	 */
	public String getNoNormMoney() {
		return noNormMoney;
	}

	/**
	 * @param noNormMoney the noNormMoney to set
	 */
	public void setNoNormMoney(String noNormMoney) {
		this.noNormMoney = noNormMoney;
	}

	/**
	 * @return the otherNoNormMoney
	 */
	public String getOtherNoNormMoney() {
		return otherNoNormMoney;
	}

	/**
	 * @param otherNoNormMoney the otherNoNormMoney to set
	 */
	public void setOtherNoNormMoney(String otherNoNormMoney) {
		this.otherNoNormMoney = otherNoNormMoney;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSentAuditMone() {
		return sentAuditMone;
	}

	public void setSentAuditMone(String sentAuditMone) {
		this.sentAuditMone = sentAuditMone;
	}

	public String getMaincontent() {
		return maincontent;
	}

	public void setMaincontent(String maincontent) {
		this.maincontent = maincontent;
	}

	public String getCapitalexpendituresid() {
		return capitalexpendituresid;
	}

	public void setCapitalexpendituresid(String capitalexpendituresid) {
		this.capitalexpendituresid = capitalexpendituresid;
	}

	public String getRoblemsid() {
		return roblemsid;
	}

	public void setRoblemsid(String roblemsid) {
		this.roblemsid = roblemsid;
	}

	public String getIndirectcosts() {
		return indirectcosts;
	}

	public void setIndirectcosts(String indirectcosts) {
		this.indirectcosts = indirectcosts;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
