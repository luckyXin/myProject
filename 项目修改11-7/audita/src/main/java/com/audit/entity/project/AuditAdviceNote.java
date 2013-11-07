/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class AuditAdviceNote implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 479217119686562712L;

	/**
	 * 施工单位
	 */
	private String constructUnit;
	
	/**
	 * @return the constructUnit
	 */
	public String getConstructUnit() {
		return constructUnit;
	}

	/**
	 * @param constructUnit the constructUnit to set
	 */
	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}
}
