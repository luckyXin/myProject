/**
 * 
 */
package com.audit.entity.project;

/**
 * @author 合同审核对象
 *
 */
public class TractProjectContract {

	private String  id;    //合同审核id
	
	private String htName;  //合同条款名称
	
	private String htFile;   //合同文件
	
	private String existproblem;   //存在问题
	
	private String auditview;   //审核意见
	
	private String executecase;   //执行情况
	
	private String createUser;    //操作人
	
	private String createTime;     //操作时间
	
	private String biaoDuanId;      //标段id
	
	private String sort;          //排序

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

	

	public String getHtName() {
		return htName;
	}

	public void setHtName(String htName) {
		this.htName = htName;
	}

	public String getHtFile() {
		return htFile;
	}

	public void setHtFile(String htFile) {
		this.htFile = htFile;
	}

	public String getExistproblem() {
		return existproblem;
	}

	public void setExistproblem(String existproblem) {
		this.existproblem = existproblem;
	}

	public String getAuditview() {
		return auditview;
	}

	public void setAuditview(String auditview) {
		this.auditview = auditview;
	}

	public String getExecutecase() {
		return executecase;
	}

	public void setExecutecase(String executecase) {
		this.executecase = executecase;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	
}
