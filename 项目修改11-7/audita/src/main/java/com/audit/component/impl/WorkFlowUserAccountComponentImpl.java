package com.audit.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.audit.common.AuditStringUtils;
import com.audit.common.PropertiesGetValue;
import com.audit.component.IStateOfUserAccountComponent;
import com.audit.component.IWorkFlowUserAccountComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.FlowStateUserAccount;

@Component
public class WorkFlowUserAccountComponentImpl implements IWorkFlowUserAccountComponent {

	@Autowired
	private IStateOfUserAccountComponent iStateOfUserAccountComponent;

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 获取用户角色
	 * 
	 * @see com.audit.component.IWorkFlowUserAccountComponent#getUserAccount(java.lang.String,
	 *      java.lang.String)
	 */
	public List<String> getUserAccount(String projectDateId, String stateValue, String stateId) {

		List<String> userAccounts = new ArrayList<String>();

		if (AuditStringUtils.equals(stateValue, PropertiesGetValue.getContextProperty("workFlow.state.projectPlan"))) {
			userAccounts = iStateOfUserAccountComponent.getWaitPlanStateOfUserAccount(stateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.governmentManage"))) {
				userAccounts = iStateOfUserAccountComponent.getGovernmentManageOfUserAccount(stateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.intermediaryAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getIntermediaryCheckOfUserAccount(stateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.governmentEmployeeAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getGovernmentEmployeeOfUserAccount(projectDateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.sectionChiefAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getSectionChiefCheckOfUserAccount(stateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.areaLeaderAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getAreaLeaderCheckOfUserAccount(stateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.mainEmployeeAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getMainCheckOfUserAccount(projectDateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.lowSectionChiefAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getSectionChiefCheckOfUserAccount(stateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.lowAreaLeaderAudit"))) {
			userAccounts = iStateOfUserAccountComponent.getSectionChiefCheckOfUserAccount(stateId);
		} else {
			userAccounts = ibatisCommonDAO.executeForObjectList("getUserAccountByFlowState", stateId);
		}
		return userAccounts;
	}

	/**
	 * (non-Javadoc) 2013-6-25
	 * 
	 * @see com.audit.component.IWorkFlowUserAccountComponent#deleteUserAccount(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteUserAccount(String id, String projectDateId, String userAccount, String stateValue)
			throws Exception {

		if (AuditStringUtils.equals(stateValue, PropertiesGetValue.getContextProperty("workFlow.state.projectPlan"))) {

			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.governmentManage"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);

		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.intermediaryAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.governmentEmployeeAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.sectionChiefAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.areaLeaderAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.mainEmployeeAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.lowSectionChiefAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else if (AuditStringUtils.equals(stateValue,
				PropertiesGetValue.getContextProperty("workFlow.state.lowAreaLeaderAudit"))) {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		} else {
			// 查询流程状态ID
			String flowStateId = ibatisCommonDAO.executeForObject("getFlowStateIdByFlowId", id, String.class);
			// 删除项目安排角色下面的用户
			ibatisCommonDAO.executeDelete("deleteFlowStateUserInfo", flowStateId);
		}
	}

	/**
	 * 删除指定用户 2013-7-17
	 * @throws Exception 
	 * 
	 * @see com.audit.component.IWorkFlowUserAccountComponent#deleteUserAccount(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void deleteUserAccount(String id, String userAccount)
			throws Exception {
		FlowStateUserAccount flowStateUserAccount = new FlowStateUserAccount();
		// 查询流程状态ID
		String flowStateId = ibatisCommonDAO.executeForObject(
				"getFlowStateIdByFlowId", id, String.class);
		flowStateUserAccount.setFlowStateId(flowStateId);
		flowStateUserAccount.setUserAccount(userAccount);
		// 删除项目安排角色下面的用户
		ibatisCommonDAO.executeDelete("deleteFlowStateSingleUserAccount",
				flowStateUserAccount);
	}
}
