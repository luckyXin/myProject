/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class PackProjectRelate implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1555379771912745432L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 项目包ID
	 */
	private String projectPackId;

	/**
	 * 项目ID
	 */
	private String projectId;

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
	 * @return the projectPackId
	 */
	public String getProjectPackId() {
		return projectPackId;
	}

	/**
	 * @param projectPackId the projectPackId to set
	 */
	public void setProjectPackId(String projectPackId) {
		this.projectPackId = projectPackId;
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
}
