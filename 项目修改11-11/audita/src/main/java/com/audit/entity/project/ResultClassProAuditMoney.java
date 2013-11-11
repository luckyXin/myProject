/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author dengyong
 * 
 */
public class ResultClassProAuditMoney extends Common implements Serializable{
	private static final long serialVersionUID = 1L;

	private String auditProjectName; // 审计项目名称

	private String baseauditmoney; // 基本审计费用

	private String xiaoyiauditmoney; // 效益审计费用

	private String totalauditmoney; // 合计

	/**
	 * @return the auditProjectName
	 */
	public String getAuditProjectName() {
		return auditProjectName;
	}

	/**
	 * @param auditProjectName
	 *            the auditProjectName to set
	 */
	public void setAuditProjectName(String auditProjectName) {
		this.auditProjectName = auditProjectName;
	}

	/**
	 * @return the baseauditmoney
	 */
	public String getBaseauditmoney() {
		return baseauditmoney;
	}

	/**
	 * @param baseauditmoney
	 *            the baseauditmoney to set
	 */
	public void setBaseauditmoney(String baseauditmoney) {
		this.baseauditmoney = baseauditmoney;
	}

	/**
	 * @return the xiaoyiauditmoney
	 */
	public String getXiaoyiauditmoney() {
		return xiaoyiauditmoney;
	}

	/**
	 * @param xiaoyiauditmoney
	 *            the xiaoyiauditmoney to set
	 */
	public void setXiaoyiauditmoney(String xiaoyiauditmoney) {
		this.xiaoyiauditmoney = xiaoyiauditmoney;
	}

	/**
	 * @return the totalauditmoney
	 */
	public String getTotalauditmoney() {
		return totalauditmoney;
	}

	/**
	 * @param totalauditmoney
	 *            the totalauditmoney to set
	 */
	public void setTotalauditmoney(String totalauditmoney) {
		this.totalauditmoney = totalauditmoney;
	}

}
