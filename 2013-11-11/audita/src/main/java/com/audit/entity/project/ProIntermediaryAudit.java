/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.audit.entity.Common;

/**
 * @author 中介审核
 * 
 */
public class ProIntermediaryAudit extends Common implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id; // id

	private BigDecimal auditmoney; // 审定金额

	private BigDecimal cutmoney; // 审减金额、
	
	private String auditlv;       //审减率

	private Date auditoktime; // 提交初审结果时间

	private String deferday; // 延期天数

	private String overday; // 超期天数

	private String remark; // 备注

	private Integer isconfirm; // 是否提交

	private String arrangeId; // 安排关联id

	private String intermediaryName; // 中介机构名称

	private String userAccount; // 操作用户

	private String auditTime; // 审计时间
	
    private String datapreId;  //预审资料id
	
	private String auditstate;  //审计状态
	
	
	/**
	 * @return the auditlv
	 */
	public String getAuditlv() {
		return auditlv;
	}

	/**
	 * @param auditlv the auditlv to set
	 */
	public void setAuditlv(String auditlv) {
		this.auditlv = auditlv;
	}

	/**
	 * @return the auditTime
	 */
	public String getAuditTime() {
		return auditTime;
	}

	/**
	 * @param auditTime the auditTime to set
	 */
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAuditmoney() {
		return auditmoney;
	}

	public void setAuditmoney(BigDecimal auditmoney) {
		this.auditmoney = auditmoney;
	}

	public BigDecimal getCutmoney() {
		return cutmoney;
	}

	public void setCutmoney(BigDecimal cutmoney) {
		this.cutmoney = cutmoney;
	}

	public Date getAuditoktime() {
		return auditoktime;
	}

	public void setAuditoktime(Date auditoktime) {
		this.auditoktime = auditoktime;
	}

	public String getDeferday() {
		return deferday;
	}

	public void setDeferday(String deferday) {
		this.deferday = deferday;
	}

	public String getOverday() {
		return overday;
	}

	public void setOverday(String overday) {
		this.overday = overday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsconfirm() {
		return isconfirm;
	}

	public void setIsconfirm(Integer isconfirm) {
		this.isconfirm = isconfirm;
	}

	public String getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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
    
	
}
