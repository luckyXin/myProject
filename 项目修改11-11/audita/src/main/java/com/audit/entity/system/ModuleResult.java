package com.audit.entity.system;

import java.io.Serializable;

public class ModuleResult implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5544683500002913525L;

    /**
     * 模块ID
     */
    private String moduleId;
    
    /**
     * 模块名称
     */
    private String moduleName;
    
    /**
     * 上级菜单
     */
    private String menuId;
    
    /**
     * 菜单名称
     */
    private String menuName;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 状态
     */
    private String state;
    
    /**
     * 地址
     */
    private String url;
    
    /**
     * 顶层菜单ID
     */
    private String topMenuId;
    
    /**
     * 创建时间
     */
    private String createtime;
    
	/**
	 * 授权与否
	 */
	private String impower;
    
    public String getImpower() {
		return impower;
	}

	public void setImpower(String impower) {
		this.impower = impower;
	}

	/**
     * @return the createtime
     */
    public String getCreatetime()
    {
        return createtime;
    }

    /**
     * @param createtime the createtime to set
     */
    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    /**
     * @return the topMenuId
     */
    public String getTopMenuId()
    {
        return topMenuId;
    }

    /**
     * @param topMenuId the topMenuId to set
     */
    public void setTopMenuId(String topMenuId)
    {
        this.topMenuId = topMenuId;
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
     * @return the moduleName
     */
    public String getModuleName()
    {
        return moduleName;
    }

    /**
     * @param moduleName the moduleName to set
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
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
     * @return the menuName
     */
    public String getMenuName()
    {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
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
    
    
}
