/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
/**
 * @author User
 *
 */
public class ArrangeInfo implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7964525963922674101L;

	/**
	 * 安排项目ID
	 */
	private String arrangeId;
	
	/**
	 * 是否加急
	 */
	private String isUrgent;
	
	/**
	 * 时限
	 */
	private String timeLimit;
	
	
	/**
	 * 领导审核意见
	 */
	private String areaauditRemark;

	/**
	 * @return the arrangeId
	 */
	public String getArrangeId() {
		return arrangeId;
	}

	/**
	 * @param arrangeId the arrangeId to set
	 */
	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	/**
	 * @return the isUrgent
	 */
	public String getIsUrgent() {
		return isUrgent;
	}

	/**
	 * @param isUrgent the isUrgent to set
	 */
	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}

	/**
	 * @return the timeLimit
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * @param timeLimit the timeLimit to set
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getAreaauditRemark() {
		return areaauditRemark;
	}

	public void setAreaauditRemark(String areaauditRemark) {
		this.areaauditRemark = areaauditRemark;
	}
	
	
}
