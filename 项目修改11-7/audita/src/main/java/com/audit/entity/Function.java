package com.audit.entity;

public class Function extends Common{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 方法ID
	 */
	private String id;
	
	/**
	 * 方法名称
	 */
	private String name;
	
	/**
	 * 方法逻辑名称
	 */
	private String method;
	
	/**
	 * 方法图标
	 */
	private String icon;
	
	/**
	 * 方法状态   0:有效 1:无效
	 */
	private String state;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 授权与否
	 */
	private String impower;
	
	/**
	 * 
	 * @return
	 */
	private String moduleid;
	
	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getImpower() {
		return impower;
	}

	public void setImpower(String impower) {
		this.impower = impower;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
