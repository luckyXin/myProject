/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author dengyong
 *
 */
public class ProTractAdjustment extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;   //ID
	
	private String adjustmentwh;//调整文号
	
	private String delabourmoney;//定额人工费
	
	private String tzlv;//调整费率
	
	private String mantzmoney;//人工费调整金额
	
	private String tztime;//调整时间
	
	private String policyChangeId;//政策性调整id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdjustmentwh() {
		return adjustmentwh;
	}

	public void setAdjustmentwh(String adjustmentwh) {
		this.adjustmentwh = adjustmentwh;
	}

	public String getDelabourmoney() {
		return delabourmoney;
	}

	public void setDelabourmoney(String delabourmoney) {
		this.delabourmoney = delabourmoney;
	}

	public String getTzlv() {
		return tzlv;
	}

	public void setTzlv(String tzlv) {
		this.tzlv = tzlv;
	}

	public String getMantzmoney() {
		return mantzmoney;
	}

	public void setMantzmoney(String mantzmoney) {
		this.mantzmoney = mantzmoney;
	}

	public String getTztime() {
		return tztime;
	}

	public void setTztime(String tztime) {
		this.tztime = tztime;
	}

	public String getPolicyChangeId() {
		return policyChangeId;
	}

	public void setPolicyChangeId(String policyChangeId) {
		this.policyChangeId = policyChangeId;
	}
	
	

}
