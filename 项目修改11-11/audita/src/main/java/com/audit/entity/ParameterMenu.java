package com.audit.entity;

import java.io.Serializable;

/**
 * 操作菜单及权限的参数对象
 * @author dengyong
 *
 */
public class ParameterMenu implements Serializable{
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userid;  //用户id
       
    private String menuid;  //菜单id

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
       
       
}
