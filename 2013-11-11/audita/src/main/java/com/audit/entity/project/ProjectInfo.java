/**
 * 项目基础信息
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author DengXin
 * 
 */
public class ProjectInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4189893525409078563L;

	/**
	 * 项目ID
	 */
	private String id;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 项目业主名称
	 */
	private String projectOwnerName;
	
	/**
	 * 送审金额
	 */
	private String sentAmount;
	
	/**
	 * @return the sentAmount
	 */
	public String getSentAmount() {
		return sentAmount;
	}

	/**
	 * @param sentAmount the sentAmount to set
	 */
	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
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
	 * @return the projectOwnerName
	 */
	public String getProjectOwnerName() {
		return projectOwnerName;
	}

	/**
	 * @param projectOwnerName the projectOwnerName to set
	 */
	public void setProjectOwnerName(String projectOwnerName) {
		this.projectOwnerName = projectOwnerName;
	}
}
