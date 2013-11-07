/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author 政策性调整
 *
 */
public class TractPolicyChange extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;         //id
	
	private String artificialfile;    //人工价
	
	private String datamoney;        //材料价
	
	private String machinemoney;     //机械
	
	private String biaoDuanId;          //标段id
	
	private String otherContext;        //其他价
	
	private String remark;               //（金额）调整依据
	
	private String createUserAccount;     //操作人
	
	private String createTime;         //操作时间

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the artificialfile
	 */
	public String getArtificialfile() {
		return artificialfile;
	}

	/**
	 * @param artificialfile the artificialfile to set
	 */
	public void setArtificialfile(String artificialfile) {
		this.artificialfile = artificialfile;
	}

	/**
	 * @return the biaoDuanId
	 */
	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	/**
	 * @param biaoDuanId the biaoDuanId to set
	 */
	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	/**
	 * @return the otherContext
	 */
	public String getOtherContext() {
		return otherContext;
	}

	/**
	 * @param otherContext the otherContext to set
	 */
	public void setOtherContext(String otherContext) {
		this.otherContext = otherContext;
	}

	/**
	 * @return the createUserAccount
	 */
	public String getCreateUserAccount() {
		return createUserAccount;
	}

	/**
	 * @param createUserAccount the createUserAccount to set
	 */
	public void setCreateUserAccount(String createUserAccount) {
		this.createUserAccount = createUserAccount;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDatamoney() {
		return datamoney;
	}

	public void setDatamoney(String datamoney) {
		this.datamoney = datamoney;
	}

	public String getMachinemoney() {
		return machinemoney;
	}

	public void setMachinemoney(String machinemoney) {
		this.machinemoney = machinemoney;
	}
	
	

}
