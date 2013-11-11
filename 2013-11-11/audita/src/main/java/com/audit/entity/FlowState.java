package com.audit.entity;

import java.io.Serializable;

public class FlowState implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2976326434558316638L;

	private String id;
	private String flowId;
	private String stateId;

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
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

}
