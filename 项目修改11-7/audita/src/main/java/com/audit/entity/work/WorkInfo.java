/**
 * 
 */
package com.audit.entity.work;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class WorkInfo implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4709437826401659919L;

	/**
	 * 处理模块ID
	 */
	private String moduleId;
	
	/**
	 * 访问地址
	 */
	private String url;
	
	/**
	 * 处理名称
	 */
	private String moduleName;
	
	/**
	 * 数据状态
	 */
	private String stateName;
	
	/**
	 * 处理数量
	 */
	private String count;
	
	/**
	 * 处理时间
	 */
	private String workTime;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the workTime
	 */
	public String getWorkTime() {
		return workTime;
	}

	/**
	 * @param workTime the workTime to set
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
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
}
