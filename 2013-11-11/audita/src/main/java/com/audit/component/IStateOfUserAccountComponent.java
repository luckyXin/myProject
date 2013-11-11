package com.audit.component;

import java.util.List;

public interface IStateOfUserAccountComponent {

	/**
	 * 待安排状态下， 流程对应的用户 state : 1
	 */
	public List<String> getWaitPlanStateOfUserAccount(String stateValue);

	/**
	 * 待政府交办下，流程对应的用户 state : 2
	 */
	public List<String> getGovernmentManageOfUserAccount(String stateValue);

	/**
	 * 待中介审核下，流程对应的用户 state : 3
	 */
	public List<String> getIntermediaryCheckOfUserAccount(String stateValue);

	/**
	 * 待复核工程师审核下，流程对应的用户 state : 4
	 * @param projectId  安排的项目ID
	 */
	public List<String> getGovernmentEmployeeOfUserAccount(String projectId);
	
	/**
	 * 待科长审核下，流程对应的用户 state : 5
	 */
	public List<String> getSectionChiefCheckOfUserAccount(String stateValue);

	/**
	 * 待分管领导审核下，流程对应的用户state : 6
	 */
	public List<String> getAreaLeaderCheckOfUserAccount(String stateValue);
	
	/**
	 * 待主审审核下，流程对应的用户state : 7 
	 */
	public List<String> getMainCheckOfUserAccount(String stateValue);
}
