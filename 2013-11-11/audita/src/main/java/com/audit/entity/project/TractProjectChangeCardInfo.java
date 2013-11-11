/**
 * 
 */
package com.audit.entity.project;

import com.audit.entity.Common;

/**
 * @author Administrator
 *
 */
public class TractProjectChangeCardInfo extends Common{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5817374177041407706L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 变更编码
	 */
	private String changeCode;
	
	/**
	 * 变更内容
	 */
	private String changeContext;
	
	/**
	 * 变更时间
	 */
	private String changeTime;
	
	/**
	 * 签证类型
	 */
	private String changeType;
	
	/**
	 * 施工单位报送变更金额
	 */
	private String constructSentMoney;
	
	/**
	 * 创建用户
	 */
	private String createUserAccount;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	/**
	 * 标段ID
	 */
	private String biaoDuanId;
	
	/**
	 * 跟踪审计建议变更金额
	 */
	private String tractAuditAdviceMoney;
	
	/**
	 * 确认变更金额
	 */
	private String affirmChangeMoney;
	
	/**
	 * 变更执行情况
	 */
	private String changeAndNowSiteInfo;
	
	/**
	 * 变更及现场签证履行程序情况
	 */
	private String changeProccessCondition;

	
	/**
	 * 预估剩余预留金
	 */
	private String prospectSurMoney;
	
	
	/**
	 * 总计
	 */
	private String totalMoney;
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
	 * @return the changeCode
	 */
	public String getChangeCode() {
		return changeCode;
	}

	/**
	 * @param changeCode the changeCode to set
	 */
	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}

	/**
	 * @return the changeContext
	 */
	public String getChangeContext() {
		return changeContext;
	}

	/**
	 * @param changeContext the changeContext to set
	 */
	public void setChangeContext(String changeContext) {
		this.changeContext = changeContext;
	}

	/**
	 * @return the changeTime
	 */
	public String getChangeTime() {
		return changeTime;
	}

	/**
	 * @param changeTime the changeTime to set
	 */
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * @return the changeType
	 */
	public String getChangeType() {
		return changeType;
	}

	/**
	 * @param changeType the changeType to set
	 */
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	/**
	 * @return the constructSentMoney
	 */
	public String getConstructSentMoney() {
		return constructSentMoney;
	}

	/**
	 * @param constructSentMoney the constructSentMoney to set
	 */
	public void setConstructSentMoney(String constructSentMoney) {
		this.constructSentMoney = constructSentMoney;
	}

	/**
	 * @return the createUserAccount
	 */
	public String getCreateUserAccount() {
		return createUserAccount;
	}

	/**
	 * @param createUserAccount the createUserAccount to set
	 */
	public void setCreateUserAccount(String createUserAccount) {
		this.createUserAccount = createUserAccount;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the biaoDuanId
	 */
	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	/**
	 * @param biaoDuanId the biaoDuanId to set
	 */
	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	/**
	 * @return the tractAuditAdviceMoney
	 */
	public String getTractAuditAdviceMoney() {
		return tractAuditAdviceMoney;
	}

	/**
	 * @param tractAuditAdviceMoney the tractAuditAdviceMoney to set
	 */
	public void setTractAuditAdviceMoney(String tractAuditAdviceMoney) {
		this.tractAuditAdviceMoney = tractAuditAdviceMoney;
	}

	/**
	 * @return the affirmChangeMoney
	 */
	public String getAffirmChangeMoney() {
		return affirmChangeMoney;
	}

	/**
	 * @param affirmChangeMoney the affirmChangeMoney to set
	 */
	public void setAffirmChangeMoney(String affirmChangeMoney) {
		this.affirmChangeMoney = affirmChangeMoney;
	}

	/**
	 * @return the changeAndNowSiteInfo
	 */
	public String getChangeAndNowSiteInfo() {
		return changeAndNowSiteInfo;
	}

	/**
	 * @param changeAndNowSiteInfo the changeAndNowSiteInfo to set
	 */
	public void setChangeAndNowSiteInfo(String changeAndNowSiteInfo) {
		this.changeAndNowSiteInfo = changeAndNowSiteInfo;
	}

	/**
	 * @return the changeProccessCondition
	 */
	public String getChangeProccessCondition() {
		return changeProccessCondition;
	}

	/**
	 * @param changeProccessCondition the changeProccessCondition to set
	 */
	public void setChangeProccessCondition(String changeProccessCondition) {
		this.changeProccessCondition = changeProccessCondition;
	}

	public String getProspectSurMoney() {
		return prospectSurMoney;
	}

	public void setProspectSurMoney(String prospectSurMoney) {
		this.prospectSurMoney = prospectSurMoney;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	
}
