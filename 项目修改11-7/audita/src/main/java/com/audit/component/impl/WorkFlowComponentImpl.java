package com.audit.component.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.audit.common.AuditStringUtils;
import com.audit.component.IWorkFlowComponent;
import com.audit.component.IWorkFlowUserAccountComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Flow;
import com.audit.entity.FlowRecord;
import com.audit.entity.FlowState;
import com.audit.entity.FlowStateUserAccount;
import com.audit.entity.project.ProSuspendAuditInfo;

@Component
public class WorkFlowComponentImpl implements IWorkFlowComponent {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowUserAccountComponent WorkFlowUserAccountComponent;

	/**
	 * 创建流程 2013-6-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#createWorkFlow(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void createWorkFlow(String projectDateId, String userAccount, String stateValue, String dateState)
			throws Exception {

		// 判断该条数据是否存在未关闭的流程
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);
		if (!AuditStringUtils.isNotEmpty(id)) {
			id = AuditStringUtils.getUUID();
		} else {

			// 删除之前的用户
			WorkFlowUserAccountComponent.deleteUserAccount(id, projectDateId, userAccount, stateValue);
			// 删除之前的任务状态
			ibatisCommonDAO.executeDelete("deleteFlowState", id);
		}
		// 创建任务表
		Flow flow = new Flow();
		flow.setId(id);
		flow.setCraetePeople(userAccount);
		flow.setProjectDateId(projectDateId);
		flow.setState(dateState);
		ibatisCommonDAO.executeInsert("insertFlow", flow);

		// 获取状态ID
		String stateId = ibatisCommonDAO.executeForObject("getFlowStateId", stateValue, String.class);
		FlowState flowState = new FlowState();
		flowState.setId(AuditStringUtils.getUUID());
		flowState.setFlowId(id);
		flowState.setStateId(stateId);

		// 加入任务状态
		ibatisCommonDAO.executeInsert("insertFlowState", flowState);

		// 获取目标对象
		List<String> userAccounts = WorkFlowUserAccountComponent.getUserAccount(projectDateId, stateValue, stateId);

		for (String targetUserAccount : userAccounts) {

			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState.getId());
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
		}

		// 流程记录
		runFlowRecord(id, userAccount, "0", stateValue, projectDateId, "0", "创建任务");
	}

	/**
	 * 流程切换下一个状态 2013-6-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#changeWorkFlow(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void changeWorkFlow(String projectDateId, String stateValue, String userAccount) throws Exception {

		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		// 获取状态ID
		String stateId = ibatisCommonDAO.executeForObject("getFlowStateId", stateValue, String.class);
		FlowState flowState = new FlowState();
		flowState.setId(AuditStringUtils.getUUID());
		flowState.setFlowId(id);
		flowState.setStateId(stateId);

		// 查询当前一个状态
		String beforeState = ibatisCommonDAO.executeForObject("getBeforeFlowStateId", id, String.class);

		// 删除之前的用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, projectDateId, userAccount, stateValue);

		// 删除之前的任务状态
		int count = ibatisCommonDAO.executeDelete("deleteFlowState", id);

		if (count == 0) {
			return;
		}

		// 判定是否存在
		Integer countState = ibatisCommonDAO.executeForObject("checkExistFlowStateId", flowState, Integer.class);

		if (countState == 0) {

			// 加入新的任务状态
			ibatisCommonDAO.executeInsert("insertFlowState", flowState);
		}

		// 获取目标对象
		List<String> userAccounts = WorkFlowUserAccountComponent.getUserAccount(projectDateId, stateValue, stateId);

		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState.getId());
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
		}

		// 流程记录
		runFlowRecord(id, userAccount, beforeState, stateValue, projectDateId, "0", "切换到下一个任务");
	}

	/**
	 * 加入状态 2013-6-27
	 * 
	 * @see com.audit.component.IWorkFlowComponent#addWorkFlow(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void addWorkFlow(String projectDateId, String stateValue, String userAccount) throws Exception {

		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		// 获取状态ID
		String stateId = ibatisCommonDAO.executeForObject("getFlowStateId", stateValue, String.class);
		FlowState flowState = new FlowState();
		flowState.setId(AuditStringUtils.getUUID());
		flowState.setFlowId(id);
		flowState.setStateId(stateId);

		// 查询上一个状态
		String beforeState = ibatisCommonDAO.executeForObject("getNowFlowStateId", id, String.class);

		// 删除之前的用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, projectDateId, userAccount, stateValue);

		// 删除之前的任务状态
		int count = ibatisCommonDAO.executeDelete("deleteFlowState", id);

		if (count == 0) {
			return;
		}

		// 判定是否存在
		Integer countState = ibatisCommonDAO.executeForObject("checkExistFlowStateId", flowState, Integer.class);

		if (countState == 0) {

			// 加入新的任务状态
			ibatisCommonDAO.executeInsert("insertFlowState", flowState);
		}

		// 获取目标对象
		List<String> userAccounts = WorkFlowUserAccountComponent.getUserAccount(projectDateId, stateValue, stateId);

		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState.getId());
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
		}

		// 流程记录
		runFlowRecord(id, userAccount, beforeState, stateValue, projectDateId, "0", "增加一个状态");
	}

	/**
	 * 直接指定用户的任务，状态切换 2013-6-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#changeWorkFlow(java.lang.String,
	 *      java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public void changeWorkFlow(String projectDateId, String stateValue, List<String> userAccounts, String userAccount)
			throws Exception {

		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		// 获取状态ID
		String stateId = ibatisCommonDAO.executeForObject("getFlowStateId", stateValue, String.class);
		FlowState flowState = new FlowState();
		flowState.setFlowId(id);
		flowState.setStateId(stateId);

		// 查询前一个状态
		String beforeState = ibatisCommonDAO.executeForObject("getBeforeFlowStateId", id, String.class);

		// 删除之前的用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, projectDateId, userAccount, stateValue);

		// 删除之前的任务状态
		int count = ibatisCommonDAO.executeDelete("deleteFlowState", id);

		if (count == 0) {
			return;
		}

		// 判定是否存在
		Integer countState = ibatisCommonDAO.executeForObject("checkExistFlowStateId", flowState, Integer.class);

		if (countState == 0) {
			flowState.setId(AuditStringUtils.getUUID());
			// 加入新的任务状态
			ibatisCommonDAO.executeInsert("insertFlowState", flowState);
		}

		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState.getId());
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
		}

		// 流程记录
		runFlowRecord(id, userAccount, beforeState, stateValue, projectDateId, "0", "切换到下一个任务");
	}

	/**
	 * 流程业务数据切换 2013-6-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#changePrjectDateId(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void changePrjectDateId(String oldProjectDateId, String newProjectDateId, String userAccount)
			throws Exception {

		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", oldProjectDateId, String.class);

		Flow flow = new Flow();
		flow.setId(id);
		flow.setProjectDateId(newProjectDateId);
		int count = ibatisCommonDAO.executeUpdate("updateFlowInfo", flow);

		if (count == 0) {
			return;
		}

		// 查询前一个状态
		String beforeState = ibatisCommonDAO.executeForObject("getBeforeFlowStateId", id, String.class);

		// 流程记录
		runFlowRecord(id, userAccount, beforeState, beforeState, newProjectDateId, "0", "更改任务的业务数据");
	}

	/**
	 * 结束流程，做归档处理 2013-6-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#addWorkFlowOfUserAccount(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public void endWorkFlow(String projctDateId, String userAccount) throws Exception {

		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projctDateId, String.class);

		int count = ibatisCommonDAO.executeUpdate("updateFlowInfoStateOff", id);

		if (count == 0) {
			return;
		}
		// 删除之前的用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, null, userAccount, null);

		// 查询前一个状态
		String beforeState = ibatisCommonDAO.executeForObject("getBeforeFlowStateId", id, String.class);

		// 归档记录
		runFlowRecord(id, userAccount, beforeState, "99", projctDateId, "1", "结束任务");
	}

	/**
	 * 添加流程处置用户 2013-6-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#addWorkFlowOfUserAccount(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public void addWorkFlowOfUserAccount(String projectDateId, List<String> userAccounts) throws Exception {

		// 获取任务表
		String flowId = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		// 获取当前任务的状态
		String flowState = ibatisCommonDAO.executeForObject("findFlowStateByProjectDateId", flowId, String.class);

		StringBuilder executeMessage = new StringBuilder("任务添加用户");
		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState);
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
			executeMessage.append("," + targetUserAccount);
		}

		// 检索当前最新任务信息
		FlowRecord flowRecord = ibatisCommonDAO.executeForObject("getNowFlowRecord", flowId, FlowRecord.class);
		flowRecord.setId(AuditStringUtils.getUUID());
		flowRecord.setRemark(executeMessage.toString());
		ibatisCommonDAO.executeInsert("insertFlowRecord", flowRecord);
	}

	/**
	 * 2013-6-8 切换流程处置用户
	 * 
	 * @see com.audit.component.IWorkFlowComponent#changeWorkFlowOfUserAcccount(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void changeWorkFlowOfUserAcccount(String projectDateId, List<String> userAccounts) throws Exception {

		// 获取任务表
		String flowId = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		// 获取当前任务的状态
		String flowState = ibatisCommonDAO.executeForObject("findFlowStateByProjectDateId", flowId, String.class);

		// 查询流程状态ID
		String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", flowId, String.class);

		// 删除上个状态目标用户
		int flowStateUserCount = ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		if (flowStateUserCount == 0) {
			return;
		}
		StringBuilder executeMessage = new StringBuilder("切换该任务用户为：");
		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState);
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
			executeMessage.append(targetUserAccount + ",");
		}

		// 流程记录
		FlowRecord flowRecord = ibatisCommonDAO.executeForObject("getNowFlowRecord", flowId, FlowRecord.class);
		flowRecord.setId(AuditStringUtils.getUUID());
		flowRecord.setRemark(executeMessage.toString());
		ibatisCommonDAO.executeInsert("insertFlowRecord", flowRecord);
	}

	/**
	 * 记录流程
	 * 
	 * @throws Exception
	 */
	private void runFlowRecord(String flowId, String userAccount, String beforeState, String afterState,
			String projectDateId, String flowState, String remark) throws Exception {

		FlowRecord flowRecord = new FlowRecord();
		flowRecord.setId(AuditStringUtils.getUUID());
		flowRecord.setAfterState(afterState);
		flowRecord.setBeforeState(beforeState);
		flowRecord.setFlowId(flowId);
		flowRecord.setProjectDateId(projectDateId);
		flowRecord.setUserAccount(userAccount);
		flowRecord.setRemark(remark);
		ibatisCommonDAO.executeInsert("insertFlowRecord", flowRecord);
	}

	/**
	 * 禁用流程 2013-6-25
	 * 
	 * @see com.audit.component.IWorkFlowComponent#offWorkFlow(java.lang.String)
	 */
	@Override
	public void offWorkFlow(String projectDateId) throws Exception {
		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		ibatisCommonDAO.executeUpdate("updateFlowInfoStateOff", id);
	}

	/**
	 * 开启流程 2013-6-25
	 * 
	 * @see com.audit.component.IWorkFlowComponent#onWorkFlow(java.lang.String)
	 */
	@Override
	public void onWorkFlow(String projectDateId) throws Exception {
		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("getMaxUpdateTimeProject", projectDateId, String.class);

		ibatisCommonDAO.executeUpdate("updateFlowInfoStateOn", id);
	}

	/**
	 * 不做记录切换流程 2013-7-8
	 * 
	 * @see com.audit.component.IWorkFlowComponent#changeWorkFlowNoRecord(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void changeWorkFlowNoRecord(String projectDateId, String stateValue, String userAccount) throws Exception {
		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDateId, String.class);

		// 获取状态ID
		String stateId = ibatisCommonDAO.executeForObject("getFlowStateId", stateValue, String.class);
		FlowState flowState = new FlowState();
		flowState.setId(AuditStringUtils.getUUID());
		flowState.setFlowId(id);
		flowState.setStateId(stateId);

		// 删除之前的用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, projectDateId, userAccount, stateValue);

		// 删除之前的任务状态
		int count = ibatisCommonDAO.executeDelete("deleteFlowState", id);

		if (count == 0) {
			return;
		}

		// 判定是否存在
		Integer countState = ibatisCommonDAO.executeForObject("checkExistFlowStateId", flowState, Integer.class);

		if (countState == 0) {

			// 加入新的任务状态
			ibatisCommonDAO.executeInsert("insertFlowState", flowState);
		}

		// 获取目标对象
		List<String> userAccounts = WorkFlowUserAccountComponent.getUserAccount(projectDateId, stateValue, stateId);

		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState.getId());
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
		}

		// 流程记录
		runFlowRecord(id, userAccount, stateValue, stateValue, projectDateId, "0", "切换到下一个任务");
	}

	/**
	 * 更新任务人员 2013-7-17
	 * @throws Exception 
	 * 
	 * @see com.audit.component.IWorkFlowComponent#backChangeWorKFlowUserAccount(java.lang.String)
	 */
	@Override
	public void backChangeWorKFlowUserAccount(String projectDeteId) throws Exception {
		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId", projectDeteId, String.class);

		// 查询当前一个状态
		String beforeState = ibatisCommonDAO.executeForObject("getBeforeFlowStateId", id, String.class);
		
		// 获取状态ID
		String stateId = ibatisCommonDAO.executeForObject("getFlowStateId", beforeState, String.class);
		FlowState flowState = new FlowState();
		flowState.setId(AuditStringUtils.getUUID());
		flowState.setFlowId(id);
		flowState.setStateId(stateId);

		// 删除之前的用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, projectDeteId, "", beforeState);

		// 删除之前的任务状态
		int count = ibatisCommonDAO.executeDelete("deleteFlowState", id);

		if (count == 0) {
			return;
		}

		// 判定是否存在
		Integer countState = ibatisCommonDAO.executeForObject("checkExistFlowStateId", flowState, Integer.class);

		if (countState == 0) {

			// 加入新的任务状态
			ibatisCommonDAO.executeInsert("insertFlowState", flowState);
		}

		// 获取目标对象
		List<String> userAccounts = WorkFlowUserAccountComponent.getUserAccount(projectDeteId, beforeState, stateId);

		for (String targetUserAccount : userAccounts) {
			FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
			flowStateUserAccount.setId(AuditStringUtils.getUUID());
			flowStateUserAccount.setUserAccount(targetUserAccount);
			flowStateUserAccount.setFlowStateId(flowState.getId());
			// 加入任务对象
			ibatisCommonDAO.executeInsert("insertFlowStateUserAccount", flowStateUserAccount);
		}
	}

	/**
	 * 多人对单一任务操作的场合，单个人完成任务，不切换任务状态，记录操作 2013-7-17
	 * @throws Exception 
	 * 
	 * @see com.audit.component.IWorkFlowComponent#singlePeopleCompeleteWorkFlow(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void singlePeopleCompeleteWorkFlow(String projectDeteId,
			String userAccount) throws Exception {

		// 获取任务表
		String id = ibatisCommonDAO.executeForObject("findFlowByProjectDateId",
				projectDeteId, String.class);

		// 查询当前一个状态
		String beforeState = ibatisCommonDAO.executeForObject(
				"getBeforeFlowStateId", id, String.class);
		
		// 删除指定用户
		WorkFlowUserAccountComponent.deleteUserAccount(id, userAccount);
		
		// 流程记录
		runFlowRecord(id, userAccount, beforeState, beforeState, projectDeteId, "0", "切换到下一个任务");
	}
	
	/**
	 * 暂停工作流流程
	 * @param datapreId
	 */
	public  void stopWorkFlow(String datapreId) throws Exception{
		ibatisCommonDAO.executeUpdate("stopworkflow",datapreId);
	}
	/**
	 * 启用项目流程
	 * @param datapreId
	 */
	public  void startWorkFlow(String datapreId)throws Exception{
		ibatisCommonDAO.executeUpdate("startworkflow",datapreId);
	}
	
	/**
	 * 增加暂停项目申请
	 * @param pro
	 * @return
	 * @throws Exception
	 */
	public int addProSupAuditInfo(ProSuspendAuditInfo pro)throws Exception{
		//增加项目暂停申请
		int row=ibatisCommonDAO.executeInsert("addprosupauditinfo", pro);
		if(row>0){
		    //暂停项目
			ibatisCommonDAO.executeUpdate("stopworkflow",pro.getDatapreId());
		}
		return row;
	}
}
