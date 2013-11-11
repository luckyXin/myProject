/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class Problems implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 *  问题类型

	 */
	private String type;
	
	/**
	 * 金额
	 */
	private String money;
	
	/**
	 * 原因
	 */
	private String reason;
	
	/**
	 * 天数
	 */
	private String day;
	
	/**
	 * yes null 外键id
	 */
	private String mainAuditInfoid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMainAuditInfoid() {
		return mainAuditInfoid;
	}

	public void setMainAuditInfoid(String mainAuditInfoid) {
		this.mainAuditInfoid = mainAuditInfoid;
	}

}
