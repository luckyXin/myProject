/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author User
 * 
 */
public class IntermediaryAudit extends Common implements Serializable {

	private static final long serialVersionUID = 1L;

	private String arrangeId; // 项目安排id
	
	private String datapreId;  //预审资料id
	
	private String auditstate;  //审计状态

	private String projectName; // 项目名称

	private String proownerid; // 项目业主

	private String sentAmount; // 送审金额

	private String intermediaryId; // 中介机构id

	private String interid; // 中介id

	private String intermediaryName; // 中介机构名称

	private String intermediarySendTime; // 发放时间

	private String intermediaryAuditTime; // 应返初审结果时间

	private String deferday; // 超期天数

	private String isconfirm; // 是否提交

	private String userAccount; // 用户名

	private String module; // 菜单id
	
	private String beginTime;  //初审审核完成开始时间
	
	private String endTime;    //初审审核完成结束时间
	
	private String isUrgent;   //是否加急
	
	private String timeLimit;  //限时
	
	private String timeLimitStart;  //限时开始时间
	
	private String timeLimitEnd;  //限时结束时间

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

	public String getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
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

	public String getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
	}

	public String getIntermediaryId() {
		return intermediaryId;
	}

	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	public String getInterid() {
		return interid;
	}

	public void setInterid(String interid) {
		this.interid = interid;
	}

	public String getIntermediarySendTime() {
		return intermediarySendTime;
	}

	public void setIntermediarySendTime(String intermediarySendTime) {
		this.intermediarySendTime = intermediarySendTime;
	}

	public String getDeferday() {
		return deferday;
	}

	public void setDeferday(String deferday) {
		this.deferday = deferday;
	}

	public String getIsconfirm() {
		return isconfirm;
	}

	public void setIsconfirm(String isconfirm) {
		this.isconfirm = isconfirm;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getIntermediaryAuditTime() {
		return intermediaryAuditTime;
	}

	public void setIntermediaryAuditTime(String intermediaryAuditTime) {
		this.intermediaryAuditTime = intermediaryAuditTime;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDatapreId() {
		return datapreId;
	}

	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

	public String getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
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
    
	
	
}
