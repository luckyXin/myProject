package com.audit.entity;

import java.io.Serializable;

public class Menu extends Common implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3165063430911502158L;
	/**
	 * 菜单ID
	 */
	private String id;
	
	/**
	 * 菜单名称
	 */
	private String menuName;
	
	/**
	 * 上级菜单
	 */
	private String pid;
	
	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 顺序
	 */
	private String sort;
	
	/**
	 * 地址
	 */
	private String url;
	
	/**
	 * 备注
	 */
	private String remark;
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
