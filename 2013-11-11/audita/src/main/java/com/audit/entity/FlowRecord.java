package com.audit.entity;

import java.io.Serializable;

public class FlowRecord implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8381349939318903962L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 任务ID
	 */
	private String flowId;

	/**
	 * 操作用户
	 */
	private String userAccount;

	/**
	 * 操作之前的状态
	 */
	private String beforeState;

	/**
	 * 操作之后的状态
	 */
	private String afterState;

	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 业务数据
	 */
	private String projectDateId;

	/**
	 * 该条流程记录状态 0 开启 1 关闭
	 */
	private String flowState;

	/**
	 * 操作备注
	 */
	private String remark;

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the flowState
	 */
	public String getFlowState() {
		return flowState;
	}

	/**
	 * @param flowState
	 *            the flowState to set
	 */
	public void setFlowState(String flowState) {
		this.flowState = flowState;
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
	 * @return the flowId
	 */
	public String getFlowId() {
		return flowId;
	}

	/**
	 * @param flowId
	 *            the flowId to set
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the beforeState
	 */
	public String getBeforeState() {
		return beforeState;
	}

	/**
	 * @param beforeState
	 *            the beforeState to set
	 */
	public void setBeforeState(String beforeState) {
		this.beforeState = beforeState;
	}

	/**
	 * @return the afterState
	 */
	public String getAfterState() {
		return afterState;
	}

	/**
	 * @param afterState
	 *            the afterState to set
	 */
	public void setAfterState(String afterState) {
		this.afterState = afterState;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

}
