package com.audit.entity;

import java.io.Serializable;
import java.util.Date;

public class EditUser implements Serializable{

	private static final long serialVersionUID = 1L;

    private String id;      //用户id
	
	private String userAccount;  //用户账号
	
	private String password;     //用户密码

	private String username;    //用户姓名

	private String sex;     //性别

	private Date birthday;     //生日

	private String cardId;     //卡号

	private String telPhone;   //电话
	
	private String email;    //邮箱

	private String qualification;  //政府雇员资质

	private String deptid;     //部门
	
	private Integer state;      //状态(1表示禁用0表示启用2表示删除)
	
	private String remark;      //备注

	private Date createTime;  //创建时间
	
	private Integer istype;  //是系统账号还是政府雇员(0表示系统账号,1表示政府雇员)

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIstype() {
		return istype;
	}

	public void setIstype(Integer istype) {
		this.istype = istype;
	}

	
	
	
}
