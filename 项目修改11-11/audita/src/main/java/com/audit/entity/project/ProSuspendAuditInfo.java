/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author dengyong
 * 项目暂停审计申请表
 *
 */
public class ProSuspendAuditInfo extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;    //主键
	
	private String projectName; //项目名称
	
	private String childProjectName;//子项目名称
	
	private String datapreId;    //资料预审id
	
	private String ownnerName;      //建设单位
	
	private String constructName;     //施工单位
	
	private String suspendRemark;        //暂停审计原因
	
	private String operatorUser;         //操作人
	
	private String createTime;            //操作时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getChildProjectName() {
		return childProjectName;
	}

	public void setChildProjectName(String childProjectName) {
		this.childProjectName = childProjectName;
	}

	public String getDatapreId() {
		return datapreId;
	}

	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

	public String getOwnnerName() {
		return ownnerName;
	}

	public void setOwnnerName(String ownnerName) {
		this.ownnerName = ownnerName;
	}

	public String getConstructName() {
		return constructName;
	}

	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}

	public String getSuspendRemark() {
		return suspendRemark;
	}

	public void setSuspendRemark(String suspendRemark) {
		this.suspendRemark = suspendRemark;
	}

	public String getOperatorUser() {
		return operatorUser;
	}

	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	

}
