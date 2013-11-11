package com.audit.entity;

import java.io.Serializable;

public class Flow implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8147354216303834260L;

	/**
	 * 任务ID
	 */
	private String id;

	/**
	 * 项目数据ID
	 */
	private String projectDateId;

	/**
	 * 创建时间
	 */
	private String craeteTime;

	/**
	 * 创建者
	 */
	private String craetePeople;

	/**
	 * 任务状态 1 ：关闭 2 ：开启
	 */
	private String state;

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the projectDateId
	 */
	public String getProjectDateId() {
		return projectDateId;
	}

	/**
	 * @param projectDateId
	 *            the projectDateId to set
	 */
	public void setProjectDateId(String projectDateId) {
		this.projectDateId = projectDateId;
	}

	/**
	 * @return the craeteTime
	 */
	public String getCraeteTime() {
		return craeteTime;
	}

	/**
	 * @param craeteTime
	 *            the craeteTime to set
	 */
	public void setCraeteTime(String craeteTime) {
		this.craeteTime = craeteTime;
	}

	/**
	 * @return the craetePeople
	 */
	public String getCraetePeople() {
		return craetePeople;
	}

	/**
	 * @param craetePeople
	 *            the craetePeople to set
	 */
	public void setCraetePeople(String craetePeople) {
		this.craetePeople = craetePeople;
	}

}
