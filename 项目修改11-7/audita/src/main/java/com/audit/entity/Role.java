package com.audit.entity;

import java.io.Serializable;

public class Role extends Common implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4403556221189857971L;

	/**
	 * 角色ID
	 */
	private String id;

	/**
	 * 角色名称
	 */
	private String rolename;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 创建时间
	 */
	private String createtime;
	
	/**
	 * 菜单ID 
	 */
	private String menuId;

	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
