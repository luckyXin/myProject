/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author 审核费用
 *
 */
public class ProAuditMoeny extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;    //id
	
	private String datapreId;  //预审资料id
	
	private String basemoney;  //基本费用
	
	private String xiaoyimoney;  //效益费用

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
	 * @return the datapreId
	 */
	public String getDatapreId() {
		return datapreId;
	}

	/**
	 * @param datapreId the datapreId to set
	 */
	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

    

	/**
	 * @return the basemoney
	 */
	public String getBasemoney() {
		return basemoney;
	}

	/**
	 * @param basemoney the basemoney to set
	 */
	public void setBasemoney(String basemoney) {
		this.basemoney = basemoney;
	}

	/**
	 * @return the xiaoyimoney
	 */
	public String getXiaoyimoney() {
		return xiaoyimoney;
	}

	/**
	 * @param xiaoyimoney the xiaoyimoney to set
	 */
	public void setXiaoyimoney(String xiaoyimoney) {
		this.xiaoyimoney = xiaoyimoney;
	}

	
	

}
