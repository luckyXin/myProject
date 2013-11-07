/**
 * 
 */
package com.audit.entity.search;

import java.io.Serializable;

/**
 * @author dengyong
 * 项目基本信息
 *
 */
public class SearchProjectBaseInfoResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 预审资料id
	 */
	private String id;
	
	/**
	 * 序号
	 */
	private String datapreno;
	
	/**
	 * 项目立项文号
	 */
	private String projectNo;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 被审计单位项目业主
	 */
	private String proownerid;
	
	/**
	 * 项目状态
	 */
	private String projectState;
	
	/**
	 * 主审
	 */
	private String mainAuditId;
	
	/**
	 * 事务所
	 */
	private String intermediaryId;
	
	/**
	 * 复核工程师
	 */
	private String employees;
	
	/**
	 * 安排id
	 */
	private String arrangeid;
	
	/**
	 * 是否自审
	 */
	private String isMySelfState;
	
	/**
	 * 施工企业
	 */
	private String constructId;
	
	/**
	 * 送审金额
	 */
	private String sentAmount;
	
	/**
	 * 中介审定金额
	 */
	private String interauditmoney;
	
	/**
	 * 中介审减金额
	 */
	private String intercutmoney;
	
	/**
	 * 雇员审定金额
	 */
	private String auditMoney;
	
	/**
	 * 雇员审减金额
	 */
	private String reduceMoney;
	
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
     * 主审id
     */
    private String mainId;
    
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
     * 审计身份
     */
    private String auditidentity;
    
    /**
     * 用户
     */
    private String userId;
    
    /**
     * 获取审计开始时间
     */
    private String datapreStartTime;
	/**
	 * 获取审计结束时间
	 */
	private String datapreEndTime;
	
	/**
	 * 方式
	 */
	private String methodPro;

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

	public String getMainAuditId() {
		return mainAuditId;
	}

	public void setMainAuditId(String mainAuditId) {
		this.mainAuditId = mainAuditId;
	}

	public String getIntermediaryId() {
		return intermediaryId;
	}

	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public String getArrangeid() {
		return arrangeid;
	}

	public void setArrangeid(String arrangeid) {
		this.arrangeid = arrangeid;
	}

	public String getIsMySelfState() {
		return isMySelfState;
	}

	public void setIsMySelfState(String isMySelfState) {
		this.isMySelfState = isMySelfState;
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

	public String getInterauditmoney() {
		return interauditmoney;
	}

	public void setInterauditmoney(String interauditmoney) {
		this.interauditmoney = interauditmoney;
	}

	public String getIntercutmoney() {
		return intercutmoney;
	}

	public void setIntercutmoney(String intercutmoney) {
		this.intercutmoney = intercutmoney;
	}

	public String getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(String reduceMoney) {
		this.reduceMoney = reduceMoney;
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

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	public String getAuditidentity() {
		return auditidentity;
	}

	public void setAuditidentity(String auditidentity) {
		this.auditidentity = auditidentity;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDatapreStartTime() {
		return datapreStartTime;
	}

	public void setDatapreStartTime(String datapreStartTime) {
		this.datapreStartTime = datapreStartTime;
	}

	public String getDatapreEndTime() {
		return datapreEndTime;
	}

	public void setDatapreEndTime(String datapreEndTime) {
		this.datapreEndTime = datapreEndTime;
	}

	public String getMethodPro() {
		return methodPro;
	}

	public void setMethodPro(String methodPro) {
		this.methodPro = methodPro;
	}

	public String getAuditMoney() {
		return auditMoney;
	}

	public void setAuditMoney(String auditMoney) {
		this.auditMoney = auditMoney;
	}

	/**
	 * @return the mainId
	 */
	public String getMainId() {
		return mainId;
	}

	/**
	 * @param mainId the mainId to set
	 */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	

    
    
    

}
