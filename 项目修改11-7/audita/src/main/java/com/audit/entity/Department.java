package com.audit.entity;

import java.io.Serializable;
/**
 * 部门对象
 * @author dengyong
 *
 */
public class Department extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;        //机构id
	
	private String deptname;  //机构名称
	
	private String pid;       //父级节点
	
	private Integer depttype;  //机构类型(0表示单位,1表示部门)
	
	private String remark;     //备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getDepttype() {
		return depttype;
	}

	public void setDepttype(Integer depttype) {
		this.depttype = depttype;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
