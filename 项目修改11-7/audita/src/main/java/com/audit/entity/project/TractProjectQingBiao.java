/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author 清标对象
 *
 */
public class TractProjectQingBiao implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1333919638018221530L;

	/**
	 * 清标id
	 */
	private String id; 

	/**
	 * 标段id
	 */
	private String biaoDuanId; 

	/**
	 *  清标后金额
	 */
	private String afterMoney;

	/**
	 * 清标人
	 */
	private String createUserAccount;

	/**
	 * 清标时间
	 */
	private String createTime;
	
	/**
	 * 计量单位
	 */
	private String measureUtil;

	/**
	 * 工程数量
	 */
	private String projectCount;
	
	/**
	 * 综合单价最高限价
	 */
	private String maxUtilPrice;
	
	/**
	 * 投标    综合单价
	 */
	private String bidUtilPrice;
	
	/**
	 * 高于控制价120%的投标价
	 */
	private String exceedOneTwenty;
	
	/**
	 * 低于控制价80%的投标价
	 */
	private String underEighty;
	
	/**
	 * 高出控价总金额（投标价-控价）万元
	 */
	private String exceedControllerPrice;
	
	/**
	 * 低出控价总金额（投标价-控价）万元
	 */
	private String underControllerPrice;
	
	/**
	 * @return the measureUtil
	 */
	public String getMeasureUtil() {
		return measureUtil;
	}

	/**
	 * @param measureUtil the measureUtil to set
	 */
	public void setMeasureUtil(String measureUtil) {
		this.measureUtil = measureUtil;
	}

	/**
	 * @return the projectCount
	 */
	public String getProjectCount() {
		return projectCount;
	}

	/**
	 * @param projectCount the projectCount to set
	 */
	public void setProjectCount(String projectCount) {
		this.projectCount = projectCount;
	}

	/**
	 * @return the maxUtilPrice
	 */
	public String getMaxUtilPrice() {
		return maxUtilPrice;
	}

	/**
	 * @param maxUtilPrice the maxUtilPrice to set
	 */
	public void setMaxUtilPrice(String maxUtilPrice) {
		this.maxUtilPrice = maxUtilPrice;
	}

	/**
	 * @return the bidUtilPrice
	 */
	public String getBidUtilPrice() {
		return bidUtilPrice;
	}

	/**
	 * @param bidUtilPrice the bidUtilPrice to set
	 */
	public void setBidUtilPrice(String bidUtilPrice) {
		this.bidUtilPrice = bidUtilPrice;
	}

	/**
	 * @return the exceedOneTwenty
	 */
	public String getExceedOneTwenty() {
		return exceedOneTwenty;
	}

	/**
	 * @param exceedOneTwenty the exceedOneTwenty to set
	 */
	public void setExceedOneTwenty(String exceedOneTwenty) {
		this.exceedOneTwenty = exceedOneTwenty;
	}

	/**
	 * @return the underEighty
	 */
	public String getUnderEighty() {
		return underEighty;
	}

	/**
	 * @param underEighty the underEighty to set
	 */
	public void setUnderEighty(String underEighty) {
		this.underEighty = underEighty;
	}

	/**
	 * @return the exceedControllerPrice
	 */
	public String getExceedControllerPrice() {
		return exceedControllerPrice;
	}

	/**
	 * @param exceedControllerPrice the exceedControllerPrice to set
	 */
	public void setExceedControllerPrice(String exceedControllerPrice) {
		this.exceedControllerPrice = exceedControllerPrice;
	}

	/**
	 * @return the underControllerPrice
	 */
	public String getUnderControllerPrice() {
		return underControllerPrice;
	}

	/**
	 * @param underControllerPrice the underControllerPrice to set
	 */
	public void setUnderControllerPrice(String underControllerPrice) {
		this.underControllerPrice = underControllerPrice;
	}

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
	 * @return the afterMoney
	 */
	public String getAfterMoney() {
		return afterMoney;
	}

	/**
	 * @param afterMoney the afterMoney to set
	 */
	public void setAfterMoney(String afterMoney) {
		this.afterMoney = afterMoney;
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
	
	

}
