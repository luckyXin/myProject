/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * 政府交办
 * 
 * @author dengyong
 * 
 */
public class ProGovernmentAssign extends Common implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id; // id

	private String reportBatch; // 报批批次

	private String reportTime; // 报批时间

	private String assignCode; // 政府交办文号

	private String assignTime; // 政府交办时间

	private Integer state; // 状态

	private String fileName; // 文件名称
	
	/**
	 * 数据接收用户
	 */
	private String userAccount;
	
	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportBatch() {
		return reportBatch;
	}

	public void setReportBatch(String reportBatch) {
		this.reportBatch = reportBatch;
	}

	public String getAssignCode() {
		return assignCode;
	}

	public void setAssignCode(String assignCode) {
		this.assignCode = assignCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the reportTime
	 */
	public String getReportTime() {
		return reportTime;
	}

	/**
	 * @param reportTime the reportTime to set
	 */
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * @return the assignTime
	 */
	public String getAssignTime() {
		return assignTime;
	}

	/**
	 * @param assignTime the assignTime to set
	 */
	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}
}
