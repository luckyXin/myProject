package com.audit.entity;

import java.io.Serializable;
/**
 * 角色用户关联对象
 * @author dnegyong
 *
 */
public class RoleUser extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;      //id
	
	private String roleid;    //角色id
	
	private String userid;     //用户id

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	

}
