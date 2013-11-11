package com.audit.entity;

import java.io.Serializable;

public class ModuleFun implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5282317316977699890L;

	private String id;

	private String moduleId;

	private String functionId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

}
