/**
 * 
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.LegalSectionChiefAuditBaseInfo;
import com.audit.entity.project.LegalSectionChiefAuditInfo;


/**
 * @author User
 *
 */
public interface ILegalSectionChiefAuditService {
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
			String moduleId,String projectType);

	/**
	 * 获取审批信息
	 */
	public LegalSectionChiefAuditInfo getSlegalectionChiefAuditInfo(String projectId);

	/**
	 * 审批信息
	 */
	public LegalSectionChiefAuditBaseInfo getlegalSectionChiefAuditBaseInfo(String sectionChiefAuditId, String userAccount);

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
	
	/**
	 * 录入中介机构信息
	 */
	public Map<String, Object> updateIntermediaryInfo(String arrangeId, String intermediary, String projectArrangeTime) throws Exception;
	
	/**
	 * 查询安排项目信息
	 */
	public ArrangeProject getArrangeProject(String arrangeId);
}
