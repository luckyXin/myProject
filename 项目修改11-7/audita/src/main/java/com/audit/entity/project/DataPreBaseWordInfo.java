/**
 * 
 */
package com.audit.entity.project;



/**
 * @author User
 *
 */
public class DataPreBaseWordInfo {
	
    private static final long serialVersionUID = 1L;
	
    private String id;   //id
    
    private String datapreno;      //工程编号
	
	private String projectNo;      //立项文号唯一
	
	private String auditMoney;    //批复金额
	
	private String  projectTime;    //立项时间
	
	private String projectName;   //项目名称
	
	private String proownerid;   //项目业主id
	
	private String ownerName;  //项目业主名称
	
	
	private String proownerlink;  //项目业主联系人
	
	private String proownertelphone;//项目业主联系电话
	
	private String constructId;   //施工企业id
	
    private String sentAmount;  //送审金额
	
	private String datapretime;   //资料预审时间
	
	/**
	 * 工程直接费用
	 */
	private String projectDirectMoney;
	
	/**
	 * 工程总投资概算(万元)
	 */
	private String totalInvestmentBudget;
	
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
	
	private String ZjMoney;   //工程审定的工程直接费用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatapreno() {
		return datapreno;
	}

	public void setDatapreno(String datapreno) {
		this.datapreno = datapreno;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getAuditMoney() {
		return auditMoney;
	}

	public void setAuditMoney(String auditMoney) {
		this.auditMoney = auditMoney;
	}

	public String getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(String projectTime) {
		this.projectTime = projectTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProownerid() {
		return proownerid;
	}

	public void setProownerid(String proownerid) {
		this.proownerid = proownerid;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getProownerlink() {
		return proownerlink;
	}

	public void setProownerlink(String proownerlink) {
		this.proownerlink = proownerlink;
	}

	public String getProownertelphone() {
		return proownertelphone;
	}

	public void setProownertelphone(String proownertelphone) {
		this.proownertelphone = proownertelphone;
	}

	public String getConstructId() {
		return constructId;
	}

	public void setConstructId(String constructId) {
		this.constructId = constructId;
	}

	public String getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
	}

	public String getDatapretime() {
		return datapretime;
	}

	public void setDatapretime(String datapretime) {
		this.datapretime = datapretime;
	}

	public String getProjectDirectMoney() {
		return projectDirectMoney;
	}

	public void setProjectDirectMoney(String projectDirectMoney) {
		this.projectDirectMoney = projectDirectMoney;
	}

	public String getTotalInvestmentBudget() {
		return totalInvestmentBudget;
	}

	public void setTotalInvestmentBudget(String totalInvestmentBudget) {
		this.totalInvestmentBudget = totalInvestmentBudget;
	}

	public String getBuildMoney() {
		return buildMoney;
	}

	public void setBuildMoney(String buildMoney) {
		this.buildMoney = buildMoney;
	}

	public String getAlreadyMoney() {
		return alreadyMoney;
	}

	public void setAlreadyMoney(String alreadyMoney) {
		this.alreadyMoney = alreadyMoney;
	}

	public String getProgressPayment() {
		return progressPayment;
	}

	public void setProgressPayment(String progressPayment) {
		this.progressPayment = progressPayment;
	}

	public String getDesignMoney() {
		return designMoney;
	}

	public void setDesignMoney(String designMoney) {
		this.designMoney = designMoney;
	}

	public String getProspectMoney() {
		return prospectMoney;
	}

	public void setProspectMoney(String prospectMoney) {
		this.prospectMoney = prospectMoney;
	}

	public String getTheTenderFee() {
		return theTenderFee;
	}

	public void setTheTenderFee(String theTenderFee) {
		this.theTenderFee = theTenderFee;
	}

	public String getActualCompeleteMoney() {
		return actualCompeleteMoney;
	}

	public void setActualCompeleteMoney(String actualCompeleteMoney) {
		this.actualCompeleteMoney = actualCompeleteMoney;
	}

	public String getBeyondMoney() {
		return beyondMoney;
	}

	public void setBeyondMoney(String beyondMoney) {
		this.beyondMoney = beyondMoney;
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

	public String getAreaMoney() {
		return areaMoney;
	}

	public void setAreaMoney(String areaMoney) {
		this.areaMoney = areaMoney;
	}

	public String getCityMoney() {
		return cityMoney;
	}

	public void setCityMoney(String cityMoney) {
		this.cityMoney = cityMoney;
	}

	public String getSelfRaisedMoney() {
		return selfRaisedMoney;
	}

	public void setSelfRaisedMoney(String selfRaisedMoney) {
		this.selfRaisedMoney = selfRaisedMoney;
	}

	public String getBankMoney() {
		return bankMoney;
	}

	public void setBankMoney(String bankMoney) {
		this.bankMoney = bankMoney;
	}

	public String getOtherInputMoney() {
		return otherInputMoney;
	}

	public void setOtherInputMoney(String otherInputMoney) {
		this.otherInputMoney = otherInputMoney;
	}

	public String getProjectConsultMoney() {
		return projectConsultMoney;
	}

	public void setProjectConsultMoney(String projectConsultMoney) {
		this.projectConsultMoney = projectConsultMoney;
	}

	public String getEiaMoney() {
		return eiaMoney;
	}

	public void setEiaMoney(String eiaMoney) {
		this.eiaMoney = eiaMoney;
	}

	public String getClearMoney() {
		return clearMoney;
	}

	public void setClearMoney(String clearMoney) {
		this.clearMoney = clearMoney;
	}

	public String getProjectManageMoney() {
		return projectManageMoney;
	}

	public void setProjectManageMoney(String projectManageMoney) {
		this.projectManageMoney = projectManageMoney;
	}

	public String getOtherOutMoney() {
		return otherOutMoney;
	}

	public void setOtherOutMoney(String otherOutMoney) {
		this.otherOutMoney = otherOutMoney;
	}

	public String getDayCount() {
		return dayCount;
	}

	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getSupervisorMoney() {
		return supervisorMoney;
	}

	public void setSupervisorMoney(String supervisorMoney) {
		this.supervisorMoney = supervisorMoney;
	}

	public String getZjMoney() {
		return ZjMoney;
	}

	public void setZjMoney(String zjMoney) {
		ZjMoney = zjMoney;
	}
	
	
	

}
