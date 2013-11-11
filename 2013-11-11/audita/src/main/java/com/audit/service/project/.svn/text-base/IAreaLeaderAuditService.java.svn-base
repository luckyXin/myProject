/**
 * 
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ArrangeInfo;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.SectionChiefAuditInfo;

/**
 * @author User
 * 
 */
public interface IAreaLeaderAuditService {
	/**
	 * 查询打包项目的子项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param packProjectId
	 * @return
	 */
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize,
			String filed, String order, String projectName, String ownerName,
			String arrangeType, String auditType, String userAccount,
			String moduleId, String legalState);

	/**
	 * 获取审批信息
	 */
	public SectionChiefAuditInfo getSectionChiefAuditInfo(String projectId);

	/**
	 * 审批信息
	 */
	public SectionChiefAuditBaseInfo getSectionChiefAuditBaseInfo(
			String sectionChiefAuditId, String userAccount);

	/**
	 * 分管领导审批操作
	 */
	public Map<String, Object> audit(String projectId, String remark,
			String submitState, String passState, String userAccount)
			throws Exception;

	/**
	 * 分管领导更新审批操作
	 */
	public Map<String, Object> auditUpdate(String auditId, String projectId,
			String remark, String submitState, String passState,
			String userAccount) throws Exception;

	/**
	 * 审批滞留法制科的项目
	 */
	public void jumpLegalRetentionProejct(String proejctId, String userAccount)
			throws Exception;

	/**
	 * 分管领导确认安排信息
	 */
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize,
			String filed, String order, String userAccount, String moduelId,
			String arrangeType, String auditType, String ownerName,
			String projectName);

	/**
	 * 更新安排信息
	 */
	public Map<String, Object> updateArrangeInfo(String arrangeId,
			String isUrgent, String timeLimit, String userAccount,
			String areaauditRemark) throws Exception;

	/**
	 * 获取安排信息
	 */
	public ArrangeInfo getArrangeInfo(String arrangeId);
}
