package com.audit.component;

import java.util.List;

public interface IWorkFlowUserAccountComponent {

	/**
	 * 获取用户
	 */
	public List<String> getUserAccount(String projectDateId, String userAccount, String stateId);

	/**
	 * 删除用户
	 */
	public void deleteUserAccount(String id, String projectDateId, String userAccount, String stateValue)
			throws Exception;
	
	/**
	 * 删除指定用户
	 */
	public void deleteUserAccount(String id, String userAccount) throws Exception;
}
