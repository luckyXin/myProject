package com.audit.component;

import java.util.List;

import com.audit.entity.project.ProSuspendAuditInfo;

/**
 * 流程控制
 * 
 * @author xinDeng
 */
public interface IWorkFlowComponent {

	/**
	 * 开启流程
	 */
	public void createWorkFlow(String projectDateId, String userAccount, String stateValue, String DateState) throws Exception;

	/**
	 * 切换流程
	 */
	public void changeWorkFlow(String projectDateId, String stateValue, String userAccount) throws Exception;

	/**
	 * 切换流程指定用户
	 */
	public void changeWorkFlow(String projectDateId, String stateValues, List<String> userAccounts, String userAccount)
			throws Exception;

	/**
	 * 改变项目数据
	 */
	public void changePrjectDateId(String oldProjectDateId, String newProjectDateId, String userAccount)
			throws Exception;

	/**
	 * 结束流程
	 */
	public void endWorkFlow(String projctDateId, String userAccount) throws Exception;

	/**
	 * 切换人员
	 */
	public void changeWorkFlowOfUserAcccount(String projectDateId, List<String> userAccounts) throws Exception;

	/**
	 * 增加该流程处置人员
	 */
	public void addWorkFlowOfUserAccount(String projectDateId, List<String> userAccounts) throws Exception;

	/**
	 * 禁用流程状态
	 */
	public void offWorkFlow(String projectDateId) throws Exception;

	/**
	 * 开启流程状态
	 */
	public void onWorkFlow(String projectDateId) throws Exception;
	
	/**
	 * 加入状态
	 */
	public void addWorkFlow(String projectDateId, String stateValue, String userAccount) throws Exception;
	
	/**
	 * 切换流程
	 */
	public void changeWorkFlowNoRecord(String projectDateId, String stateValue, String userAccount) throws Exception;
	
	/**
	 * 重新切换人员
	 */
	public void backChangeWorKFlowUserAccount(String projectDeteId) throws Exception;
	
	/**
	 * 多人对单一任务操作的场合，单个人完成任务，不切换任务状态，记录操作
	 */
	public void singlePeopleCompeleteWorkFlow(String projectDeteId, String userAccount) throws Exception;
	
	
	/**
	 * 暂停项目流程
	 * @param datapreId
	 */
	public  void stopWorkFlow(String datapreId) throws Exception;
	
	/**
	 * 启用项目流程
	 * @param datapreId
	 */
	public  void startWorkFlow(String datapreId)throws Exception;
	
	/**
	 * 增加暂停项目申请
	 * @param pro
	 * @return
	 * @throws Exception
	 */
	public int addProSupAuditInfo(ProSuspendAuditInfo pro)throws Exception;
}
