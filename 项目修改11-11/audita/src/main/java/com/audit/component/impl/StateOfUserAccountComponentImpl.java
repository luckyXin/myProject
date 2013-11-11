package com.audit.component.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.audit.component.IStateOfUserAccountComponent;
import com.audit.dao.IbatisCommonDAO;

@Component
public class StateOfUserAccountComponentImpl implements IStateOfUserAccountComponent {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 等待安排状态下项目所对应的人员
	 * 
	 * @see
	 * com.audit.component.StateOfUserAccountComponent#getWaitPlanStateOfUserAccount
	 * ()
	 */
	@Override
	public List<String> getWaitPlanStateOfUserAccount(String stateId) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getUserAccountByFlowState", stateId);
		return userAccounts;
	}

	/**
	 * 待政府交办下，流程对应的用户
	 * 
	 * @see com.audit.component.StateOfUserAccountComponent#
	 * getGovernmentManageOfUserAccount()
	 */
	@Override
	public List<String> getGovernmentManageOfUserAccount(String stateValue) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getUserAccountByFlowState", stateValue);
		return userAccounts;
	}

	/**
	 * 获取该审批状态下的审批角色
	 * 
	 * @see com.audit.component.StateOfUserAccountComponent#
	 * getIntermediaryCheckOfUserAccount()
	 */
	@Override
	public List<String> getIntermediaryCheckOfUserAccount(String stateValue) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getUserAccountByFlowState", stateValue);
		return userAccounts;
	}

	/**
	 * 获取该审批状态下的审批角色
	 * 
	 * @see com.audit.component.StateOfUserAccountComponent#
	 * getGovernmentEmployeeOfUserAccount(java.lang.String)
	 */
	@Override
	public List<String> getGovernmentEmployeeOfUserAccount(String projectId) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getGovernmentEmployeeOfUserAccount",
				projectId);
		return userAccounts;
	}

	/**
	 * 获取该审批状态下的审批角色
	 * 
	 * @see com.audit.component.StateOfUserAccountComponent#
	 *      getSectionChiefCheckOfUserAccount()
	 */
	@Override
	public List<String> getSectionChiefCheckOfUserAccount(String stateValue) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getUserAccountByFlowState", stateValue);
		return userAccounts;
	}

	/**
	 * 获取该审批状态下的审批角色
	 * 
	 * @see com.audit.component.StateOfUserAccountComponent#
	 * getAreaLeaderCheckOfUserAccount()
	 */
	@Override
	public List<String> getAreaLeaderCheckOfUserAccount(String stateValue) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getUserAccountByFlowState", stateValue);
		return userAccounts;
	}

	/**
	 * 获取主审人员
	 * 
	 * @see com.audit.component.StateOfUserAccountComponent#getMainCheckOfUserAccount()
	 */
	@Override
	public List<String> getMainCheckOfUserAccount(String projectId) {
		List<String> userAccounts = ibatisCommonDAO.executeForObjectList("getMianAuditEmployee", projectId);
		return userAccounts;
	}
}
