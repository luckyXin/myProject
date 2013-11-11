package com.audit.entity.staff;


import java.io.Serializable;

import com.audit.entity.Common;
/**
 * 中介机构管理对象
 * @author dengyong
 *
 */
public class Intermediaryagency extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;//id
	
	private String intermediaryname;//中介机构名称
	
	private String referred;        //中介机构简称
	
	private String businesstype;//业务类型
	
	private String deptqualifica;//企业资质
	
	private String legal;//法人代表
	
	private String regaddress;    //注册地址
	
	private String address;       //详细地址
	
	private Integer istenderunit;  //是否政府公开招标单位
	
	private String bidyear;   //中标年度
	
	private String manageragency;//管理机构
	
	private String remark;           //备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIntermediaryname() {
		return intermediaryname;
	}

	public void setIntermediaryname(String intermediaryname) {
		this.intermediaryname = intermediaryname;
	}

	public String getReferred() {
		return referred;
	}

	public void setReferred(String referred) {
		this.referred = referred;
	}


	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getRegaddress() {
		return regaddress;
	}

	public void setRegaddress(String regaddress) {
		this.regaddress = regaddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIstenderunit() {
		return istenderunit;
	}

	public void setIstenderunit(Integer istenderunit) {
		this.istenderunit = istenderunit;
	}



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getDeptqualifica() {
		return deptqualifica;
	}

	public void setDeptqualifica(String deptqualifica) {
		this.deptqualifica = deptqualifica;
	}

	public String getBidyear() {
		return bidyear;
	}

	public void setBidyear(String bidyear) {
		this.bidyear = bidyear;
	}

	public String getManageragency() {
		return manageragency;
	}

	public void setManageragency(String manageragency) {
		this.manageragency = manageragency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
