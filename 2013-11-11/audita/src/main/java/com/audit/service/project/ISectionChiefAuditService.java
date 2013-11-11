/**
 * 
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.SectionChiefAuditInfo;

/**
 * @author User
 * 
 */
public interface ISectionChiefAuditService {

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
			String moduleId, String sectionChiefAuditType, String sectionChiefAuditProjectType);

	/**
	 * 获取审批信息
	 */
	public SectionChiefAuditInfo getSectionChiefAuditInfo(String projectId);

	/**
	 * 审批信息
	 */
	public SectionChiefAuditBaseInfo getSectionChiefAuditBaseInfo(String sectionChiefAuditId, String userAccount);

	/**
	 * 科长审批操作
	 */
	public Map<String, Object> audit(String projectId, String auditContext, String remark, String submitState,
			String passState, String userAccount, String auditType) throws Exception;

	/**
	 * 科长审批操作
	 */
	public Map<String, Object> auditUpdate(String auditId, String projectId, String auditContext, String remark,
			String submitState, String passState, String userAccount, String auditType) throws Exception;

	/**
	 * 获取法制科长审批信息
	 */
	public SectionChiefAuditBaseInfo getLowSectionChiefAuditBaseInfo(String sectionChiefAuditId);
	
	/**
	 *查询资料审核信息
	 * @param projectId
	 * @return
	 */
	public  ResultAuditInfo findauditinfo(String projectId,String state);
}
