package com.audit.entity;

import java.io.Serializable;

public class MenuUserInfoInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3256927986849197132L;

	/**
	 * 顶层菜单ID
	 */
	private String topMenuID; 
	
	/**
	 * 用户帐号
	 */
	private String userAccount;
	
	/**
	 * 用户密码
	 */
	private String password;

	public String getTopMenuID() {
		return topMenuID;
	}
	public void setTopMenuID(String topMenuID) {
		this.topMenuID = topMenuID;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
