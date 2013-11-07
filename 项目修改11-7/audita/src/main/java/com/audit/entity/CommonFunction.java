package com.audit.entity;

import java.io.Serializable;

public class CommonFunction implements Serializable{
	private static final long serialVersionUID = 1L;
	private String add;             //增加
	
	private String update;         //修改
	
	private String delete;         //删除
	
	private String find;           //查询
	
	private String destroy;          //销毁

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getDestroy() {
		return destroy;
	}

	public void setDestroy(String destroy) {
		this.destroy = destroy;
	}
}
