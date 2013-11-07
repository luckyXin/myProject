package com.audit.entity.system;

import java.io.Serializable;

public class ModuleFunctionRoleParam implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6636595535570372319L;

	private String moduleId;
	
	private String roleId;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
