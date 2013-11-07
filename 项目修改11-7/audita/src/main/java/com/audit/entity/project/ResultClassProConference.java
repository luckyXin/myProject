/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author 输出审计项目信息
 *
 */
public class ResultClassProConference extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String datapreId;            //项目id
	
	private String datapreno;            //项目编号
	
	private String projectNo;            //项目立项文号
	
	private String projectName;          //项目名称
	
	private String proownerid;           //项目业主
	
	private String arrangeId;           //安排id
	
	private String intermediaryId;      //中介机构
	
	private String mainAuditId;        //主审人员
	
	private String governmentEmp;       //政府雇员
	
	private String shigongunit;          //施工单位
	
	private String sendMoney;             //送审金额
	
	private String governmentAssignId;     //交办信息id
	
	private String datapretime;            //接收资料时间
	
	private String isMySelfState;           //是否自审
	
	private String intermediarySendTime;    //事务所接收资料时间
	
	private String intermediaryAuditTime;     //事务所应完成时间

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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the proownerid
	 */
	public String getProownerid() {
		return proownerid;
	}

	/**
	 * @param proownerid the proownerid to set
	 */
	public void setProownerid(String proownerid) {
		this.proownerid = proownerid;
	}

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
	 * @return the intermediaryId
	 */
	public String getIntermediaryId() {
		return intermediaryId;
	}

	/**
	 * @param intermediaryId the intermediaryId to set
	 */
	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	/**
	 * @return the mainAuditId
	 */
	public String getMainAuditId() {
		return mainAuditId;
	}

	/**
	 * @param mainAuditId the mainAuditId to set
	 */
	public void setMainAuditId(String mainAuditId) {
		this.mainAuditId = mainAuditId;
	}

	/**
	 * @return the governmentEmp
	 */
	public String getGovernmentEmp() {
		return governmentEmp;
	}

	/**
	 * @param governmentEmp the governmentEmp to set
	 */
	public void setGovernmentEmp(String governmentEmp) {
		this.governmentEmp = governmentEmp;
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
	 * @return the shigongunit
	 */
	public String getShigongunit() {
		return shigongunit;
	}

	/**
	 * @param shigongunit the shigongunit to set
	 */
	public void setShigongunit(String shigongunit) {
		this.shigongunit = shigongunit;
	}

	/**
	 * @return the sendMoney
	 */
	public String getSendMoney() {
		return sendMoney;
	}

	/**
	 * @param sendMoney the sendMoney to set
	 */
	public void setSendMoney(String sendMoney) {
		this.sendMoney = sendMoney;
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
	 * @return the governmentAssignId
	 */
	public String getGovernmentAssignId() {
		return governmentAssignId;
	}

	/**
	 * @param governmentAssignId the governmentAssignId to set
	 */
	public void setGovernmentAssignId(String governmentAssignId) {
		this.governmentAssignId = governmentAssignId;
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
	 * @return the intermediarySendTime
	 */
	public String getIntermediarySendTime() {
		return intermediarySendTime;
	}

	/**
	 * @param intermediarySendTime the intermediarySendTime to set
	 */
	public void setIntermediarySendTime(String intermediarySendTime) {
		this.intermediarySendTime = intermediarySendTime;
	}

	/**
	 * @return the intermediaryAuditTime
	 */
	public String getIntermediaryAuditTime() {
		return intermediaryAuditTime;
	}

	/**
	 * @param intermediaryAuditTime the intermediaryAuditTime to set
	 */
	public void setIntermediaryAuditTime(String intermediaryAuditTime) {
		this.intermediaryAuditTime = intermediaryAuditTime;
	}
	
	

}
