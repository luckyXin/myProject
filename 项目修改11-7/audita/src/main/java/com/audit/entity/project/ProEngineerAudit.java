/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.audit.entity.Common;

/**
 * @author dengyong
 * 政府雇员审核
 *
 */
public class ProEngineerAudit extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;    //主键id
	
    private BigDecimal auditmoney;  //审定金额
	
	private BigDecimal  cutmoney;  //审减金额
	
	private Date  audiitbegintime;  //审计开始时间
	
	private Date  audiitendtime;  //审计结束时间
	
	private String auditday;   //审计天数
	
	private String remark;       //备注
	
	private Integer isconfirm;  //是否提交
	
	private String arrangeId;   //安排关联id
	
	
	private String userAccount;   //操作用户


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public BigDecimal getAuditmoney() {
		return auditmoney;
	}


	public void setAuditmoney(BigDecimal auditmoney) {
		this.auditmoney = auditmoney;
	}


	public BigDecimal getCutmoney() {
		return cutmoney;
	}


	public void setCutmoney(BigDecimal cutmoney) {
		this.cutmoney = cutmoney;
	}


	public Date getAudiitbegintime() {
		return audiitbegintime;
	}


	public void setAudiitbegintime(Date audiitbegintime) {
		this.audiitbegintime = audiitbegintime;
	}


	public Date getAudiitendtime() {
		return audiitendtime;
	}


	public void setAudiitendtime(Date audiitendtime) {
		this.audiitendtime = audiitendtime;
	}


	public String getAuditday() {
		return auditday;
	}


	public void setAuditday(String auditday) {
		this.auditday = auditday;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getIsconfirm() {
		return isconfirm;
	}


	public void setIsconfirm(Integer isconfirm) {
		this.isconfirm = isconfirm;
	}


	public String getArrangeId() {
		return arrangeId;
	}


	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}


	public String getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	

}
