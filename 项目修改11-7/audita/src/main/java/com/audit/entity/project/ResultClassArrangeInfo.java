/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class ResultClassArrangeInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String datapreId;     //资料预审id
	
	private String arrangeId;     //项目安排id
	
	private String projectName;    //项目名称
	
	private String datapretime;    //资料接收时间
	
	private String projectTime;    //项目安排时间
	
	private String mainAuditId;    //项目主审
	
	private String ownnerId;        //业主id
	
	private String proownerid;     //项目业主
	
	private String proownerlink;    //业主联系人
	
	private String proownertelphone;  //业主联系人电话
	
	private String intermediaryId;  //中介机构
	
	private String  interlink;        //中介联系人
	
	private String  intertelphone;      //中介联系人电话
	
	private String sentAmount;     //项目安排送审金额
	
	private String intermediarySendTime;
	
	private String intermediaryAuditTime;
	
	private String empusers;      //政府雇员
	
	private String isMySelfState;    //是否自审
	
	private String datapreno;         //工程编号
	
	private String projectNo;          //立项文号
	
	private String constructId;        //施工单位 
	
	private String constructlink;        //施工联系人
	
	private String constructtelphone;      //施工单位联系电话
	
	private String datapreopinion;       //资料预审备注
	
	private String zbMoney;                //中标价
	
	private String htmoney;               //合同价
	
	private String zhanliemoney;          //其他暂列金额
	
	private String isUrgent;           //是否加急
	
	private String timeLimit;             //限时

	public String getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(String arrangeId) {
		this.arrangeId = arrangeId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(String projectTime) {
		this.projectTime = projectTime;
	}

	public String getMainAuditId() {
		return mainAuditId;
	}

	public void setMainAuditId(String mainAuditId) {
		this.mainAuditId = mainAuditId;
	}

	public String getProownerid() {
		return proownerid;
	}

	public void setProownerid(String proownerid) {
		this.proownerid = proownerid;
	}

	public String getIntermediaryId() {
		return intermediaryId;
	}

	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	public String getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
	}

	public String getIntermediarySendTime() {
		return intermediarySendTime;
	}

	public void setIntermediarySendTime(String intermediarySendTime) {
		this.intermediarySendTime = intermediarySendTime;
	}

	public String getIntermediaryAuditTime() {
		return intermediaryAuditTime;
	}

	public void setIntermediaryAuditTime(String intermediaryAuditTime) {
		this.intermediaryAuditTime = intermediaryAuditTime;
	}

	public String getEmpusers() {
		return empusers;
	}

	public void setEmpusers(String empusers) {
		this.empusers = empusers;
	}

	public String getDatapreId() {
		return datapreId;
	}

	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

	/**
	 * @return the isMySelfState
	 */
	public String getIsMySelfState() {
		return isMySelfState;
	}

	/**
	 * @param isMySelfState the isMySelfState to set
	 */
	public void setIsMySelfState(String isMySelfState) {
		this.isMySelfState = isMySelfState;
	}

	/**
	 * @return the datapreno
	 */
	public String getDatapreno() {
		return datapreno;
	}

	/**
	 * @param datapreno the datapreno to set
	 */
	public void setDatapreno(String datapreno) {
		this.datapreno = datapreno;
	}

	/**
	 * @return the projectNo
	 */
	public String getProjectNo() {
		return projectNo;
	}

	/**
	 * @param projectNo the projectNo to set
	 */
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
	 * @return the constructId
	 */
	public String getConstructId() {
		return constructId;
	}

	/**
	 * @param constructId the constructId to set
	 */
	public void setConstructId(String constructId) {
		this.constructId = constructId;
	}

	/**
	 * @return the datapreopinion
	 */
	public String getDatapreopinion() {
		return datapreopinion;
	}

	/**
	 * @param datapreopinion the datapreopinion to set
	 */
	public void setDatapreopinion(String datapreopinion) {
		this.datapreopinion = datapreopinion;
	}

	/**
	 * @return the datapretime
	 */
	public String getDatapretime() {
		return datapretime;
	}

	/**
	 * @param datapretime the datapretime to set
	 */
	public void setDatapretime(String datapretime) {
		this.datapretime = datapretime;
	}

	/**
	 * @return the htmoney
	 */
	public String getHtmoney() {
		return htmoney;
	}

	/**
	 * @param htmoney the htmoney to set
	 */
	public void setHtmoney(String htmoney) {
		this.htmoney = htmoney;
	}

	/**
	 * @return the zhanliemoney
	 */
	public String getZhanliemoney() {
		return zhanliemoney;
	}

	/**
	 * @param zhanliemoney the zhanliemoney to set
	 */
	public void setZhanliemoney(String zhanliemoney) {
		this.zhanliemoney = zhanliemoney;
	}

	/**
	 * @return the proownerlink
	 */
	public String getProownerlink() {
		return proownerlink;
	}

	/**
	 * @param proownerlink the proownerlink to set
	 */
	public void setProownerlink(String proownerlink) {
		this.proownerlink = proownerlink;
	}

	/**
	 * @return the proownertelphone
	 */
	public String getProownertelphone() {
		return proownertelphone;
	}

	/**
	 * @param proownertelphone the proownertelphone to set
	 */
	public void setProownertelphone(String proownertelphone) {
		this.proownertelphone = proownertelphone;
	}

	/**
	 * @return the interlink
	 */
	public String getInterlink() {
		return interlink;
	}

	/**
	 * @param interlink the interlink to set
	 */
	public void setInterlink(String interlink) {
		this.interlink = interlink;
	}

	/**
	 * @return the intertelphone
	 */
	public String getIntertelphone() {
		return intertelphone;
	}

	/**
	 * @param intertelphone the intertelphone to set
	 */
	public void setIntertelphone(String intertelphone) {
		this.intertelphone = intertelphone;
	}

	/**
	 * @return the constructlink
	 */
	public String getConstructlink() {
		return constructlink;
	}

	/**
	 * @param constructlink the constructlink to set
	 */
	public void setConstructlink(String constructlink) {
		this.constructlink = constructlink;
	}

	/**
	 * @return the constructtelphone
	 */
	public String getConstructtelphone() {
		return constructtelphone;
	}

	/**
	 * @param constructtelphone the constructtelphone to set
	 */
	public void setConstructtelphone(String constructtelphone) {
		this.constructtelphone = constructtelphone;
	}

	/**
	 * @return the zbMoney
	 */
	public String getZbMoney() {
		return zbMoney;
	}

	/**
	 * @param zbMoney the zbMoney to set
	 */
	public void setZbMoney(String zbMoney) {
		this.zbMoney = zbMoney;
	}

	/**
	 * @return the ownnerId
	 */
	public String getOwnnerId() {
		return ownnerId;
	}

	/**
	 * @param ownnerId the ownnerId to set
	 */
	public void setOwnnerId(String ownnerId) {
		this.ownnerId = ownnerId;
	}

	

	public String getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(String isUrgent) {
		this.isUrgent = isUrgent;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	
	

}
