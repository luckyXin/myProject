package com.audit.entity.system;

import java.io.Serializable;

public class CheckFunctionImpowerByRoleSqlParam implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -723762184536086621L;

    /**
     * 角色ID
     */
    private String roleId;
    
    /**
     * 模块ID
     */
    private String moduleId;
    
    /**
     * 模块方法ID
     */
    private String moduleFunctionId;
    

    public String getModuleFunctionId() {
		return moduleFunctionId;
	}

	public void setModuleFunctionId(String moduleFunctionId) {
		this.moduleFunctionId = moduleFunctionId;
	}

	/**
     * @return the roleId
     */
    public String getRoleId()
    {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    /**
     * @return the moduleId
     */
    public String getModuleId()
    {
        return moduleId;
    }

    /**
     * @param moduleId the moduleId to set
     */
    public void setModuleId(String moduleId)
    {
        this.moduleId = moduleId;
    }
}
