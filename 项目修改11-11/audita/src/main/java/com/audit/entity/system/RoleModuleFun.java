package com.audit.entity.system;

import java.io.Serializable;

public class RoleModuleFun implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5990040039241867687L;

	private String id;
	
	private String roleId;
	
	private String modulefunid;
	
	private String moduleId;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getModulefunid() {
		return modulefunid;
	}

	public void setModulefunid(String modulefunid) {
		this.modulefunid = modulefunid;
	}
}
