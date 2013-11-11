/**
 * 
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.LegalAreaLeaderAuditBaseInfo;
import com.audit.entity.project.LegalAreaLeaderAuditInfo;

/**
 * @author User
 *
 */
public interface ILegalAreaLeaderAuditService {
   
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
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize, String filed, String order,
			String projectName, String ownerName, String arrangeType, String auditType, String userAccount,
			String moduleId);

	/**
	 * 获取审批信息
	 */
	public LegalAreaLeaderAuditInfo getSlegalectionChiefAuditInfo(String projectId);

	/**
	 * 审批信息
	 */
	public LegalAreaLeaderAuditBaseInfo getlegalSectionChiefAuditBaseInfo(String sectionChiefAuditId, String userAccount);

	/**
	 * 法制科长审批操作
	 */
	public Map<String, Object> audit(String projectId,String auditContext, String remark, String submitState, String passState,
			String userAccount, String auditType) throws Exception;

	/**
	 * 法制科长审批操作
	 */
	public Map<String, Object> auditUpdate(String auditId, String projectId,String auditContext, String remark, String submitState,
			String passState, String userAccount, String auditType) throws Exception;
}
