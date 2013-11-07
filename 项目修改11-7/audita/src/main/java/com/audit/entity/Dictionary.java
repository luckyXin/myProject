package com.audit.entity;

import java.io.Serializable;
/**
 * 字典对象
 * @author dengyong
 *
 */
public class Dictionary extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
   
	
	private String id;    //字典id
	
	private String dictionaryname;//字典名称
	
	private String pid;//上级字典
	
	private Integer issystem;//是否系统字典（1表示系统0表示非系统）
	
	private String remark;   //描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictionaryname() {
		return dictionaryname;
	}

	public void setDictionaryname(String dictionaryname) {
		this.dictionaryname = dictionaryname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getIssystem() {
		return issystem;
	}

	public void setIssystem(Integer issystem) {
		this.issystem = issystem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
