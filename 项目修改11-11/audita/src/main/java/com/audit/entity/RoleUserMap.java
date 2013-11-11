package com.audit.entity;

import java.io.Serializable;

/**
 * 用户和角色
 * @author dengyong
 *
 */
public class RoleUserMap extends Common implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;//id
	
	private String rolename;//角色名称
	
	private String remark; //备注
	
	private Integer state; //状态
	
	private String  impower;  //是否授权(0表示未授权1表示授权)

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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getImpower() {
		return impower;
	}

	public void setImpower(String impower) {
		this.impower = impower;
	}
	
	

}
