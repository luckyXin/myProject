package com.audit.entity.system;

import java.io.Serializable;

import com.audit.entity.Common;

public class ModuleParam extends Common implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 211835790781508804L;

    /**
     * 模糊查询 模块名称
     */
    private String moduleName;
    
    /**
     * 模块ID
     */
    private String moduleId;
    
    /**
     * 访问地址
     */
    private String url;
    
    /**
     * 上层菜单ID
     */
    private String menuId;
    
    /**
     * 状态
     */
    private String state;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 方法ID
     */
    private String functionId;
    
    
    public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
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

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return the menuId
     */
    public String getMenuId()
    {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the remark
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * @return the moduleName
     */
    public String getModuleName()
    {
        return moduleName;
    }

    /**
     * @param moduleName
     *            the moduleName to set
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

}
