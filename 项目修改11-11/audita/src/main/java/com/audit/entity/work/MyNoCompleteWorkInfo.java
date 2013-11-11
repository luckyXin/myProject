/**
 * 我的工作信息
 */
package com.audit.entity.work;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author DengXin
 * 
 */
public class MyNoCompleteWorkInfo extends Common implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8845991391864736364L;

	/**
	 * 流程ID
	 */
	private String id;

	/**
	 * 处理模块ID
	 */
	private String moduleId;

	/**
	 * 处理模块名称
	 */
	private String moduleName;

	/**
	 * 处理模块URL
	 */
	private String mouduleUrl;

	/**
	 * 业务数据ID
	 */
	private String projectId;

	/**
	 * 业务数据名称
	 */
	private String projectName;

	/**
	 * 业务状态ID
	 */
	private String stateId;

	/**
	 * 业务状态名称
	 */
	private String stateName;

	/**
	 * 操作方法
	 */
	private String functionName;

	/**
	 * 用户
	 */
	private String userAccount;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

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
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the mouduleUrl
	 */
	public String getMouduleUrl() {
		return mouduleUrl;
	}

	/**
	 * @param mouduleUrl the mouduleUrl to set
	 */
	public void setMouduleUrl(String mouduleUrl) {
		this.mouduleUrl = mouduleUrl;
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
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * @param functionName the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
