/**
 * 
 */
package com.audit.service.project.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.component.IWorkFlowComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.GovermentEmployeeAudit;
import com.audit.entity.project.LegalAreaLeaderAuditBaseInfo;
import com.audit.entity.project.LegalAreaLeaderAuditInfo;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.exception.AuditException;
import com.audit.service.project.ILegalAreaLeaderAuditService;

/**
 * @author User
 * 
 */
public class LegalAreaLeaderAuditServiceImpl implements
		ILegalAreaLeaderAuditService {
	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO = null;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize,
			String filed, String order, String projectName, String ownerName,
			String arrangeType, String auditType, String userAccount,
			String moduleId) {

		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		Integer count = 0;
		List<AuditInfo> auditInfos = new ArrayList<AuditInfo>();
		// 查询单项目
		if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT.equals(arrangeType)) {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findlegalSectionChiefAuditSingleProjectCounts",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findlegalSectionChiefAuditSingleProjects", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
				}
				// 未审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"selectLegalAreaLeaderAuditount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"selectlegalSectionChiefAuditpages", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
				}
			}
		} else if (CommonConstant.AUDIT_ARRANGETYPE_PACKPROJECT
				.equals(arrangeType)) {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findlegalSectionChiefAuditPackProjectCounts",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findlegalSectionChiefAuditPackProjects", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
				}

				// 查询未审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findNolegalSectionChiefAuditPackProjectCounts",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNolegalSectionChiefAuditPackProjects", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
				}
			}
		} else {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findlegalSectionChiefAuditAllProjectCounts",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findlegalSectionChiefAuditAllProjects", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
				}

				// 查询未审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findNolegalSectionChiefAuditAllProjectCounts",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNolegalSectionChiefAuditAllProjects", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
				}
			}
		}
		GridDataModel<AuditInfo> AuditInfoGrid = new GridDataModel<AuditInfo>();
		AuditInfoGrid.setTotal(count);
		AuditInfoGrid.setRows(auditInfos);
		return AuditInfoGrid;
	}

	/**
	 * 获取项目审核信息 2013-6-29
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#getSectionChiefAuditInfo(java.lang.String)
	 */
	@Override
	public LegalAreaLeaderAuditInfo getSlegalectionChiefAuditInfo(
			String projectId) {

		LegalAreaLeaderAuditInfo sectionChiefAuditInfo = new LegalAreaLeaderAuditInfo();

		ArrangeProject arrangeProject = ibatisCommonDAO.executeForObject(
				"getProjectBaseInfo", projectId, ArrangeProject.class);

		ProIntermediaryAudit intermediaryAudit = new ProIntermediaryAudit();
		GovermentEmployeeAudit govermentEmployeeAudit = new GovermentEmployeeAudit();
		if (projectId.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
			intermediaryAudit = ibatisCommonDAO.executeForObject(
					"getPackIntermediaryAudit", projectId,
					ProIntermediaryAudit.class);
			govermentEmployeeAudit = ibatisCommonDAO.executeForObject(
					"getPackGovermentEmployeeAudit", projectId,
					GovermentEmployeeAudit.class);
		} else {
			intermediaryAudit = ibatisCommonDAO.executeForObject(
					"getIntermediaryAudit", projectId,
					ProIntermediaryAudit.class);
			govermentEmployeeAudit = ibatisCommonDAO.executeForObject(
					"getGovermentEmployeeAudit", projectId,
					GovermentEmployeeAudit.class);
		}

		sectionChiefAuditInfo.setArrangeProject(arrangeProject);
		sectionChiefAuditInfo.setGovermentEmployeeAudit(govermentEmployeeAudit);
		sectionChiefAuditInfo.setIntermediaryAudit(intermediaryAudit);
		return sectionChiefAuditInfo;
	}

	/**
	 * 审计信息获取 2013-6-29
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#getSectionChiefAuditBaseInfo(java.lang.String)
	 */
	@Override
	public LegalAreaLeaderAuditBaseInfo getlegalSectionChiefAuditBaseInfo(
			String sectionChiefAuditId, String userAccount) {
		LegalAreaLeaderAuditBaseInfo legalsectionChiefAuditBaseInfo = new LegalAreaLeaderAuditBaseInfo();

		legalsectionChiefAuditBaseInfo.setProjectDateId(sectionChiefAuditId);

		legalsectionChiefAuditBaseInfo.setUserAccount(userAccount);
		LegalAreaLeaderAuditBaseInfo scabi = ibatisCommonDAO.executeForObject(
				"getlegalAreaLeaderAudit", legalsectionChiefAuditBaseInfo,
				LegalAreaLeaderAuditBaseInfo.class);
		return scabi;
	}

	/**
	 * 法制科长审批添加 2013-6-29
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#audit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> audit(String projectId, String auditContext,
			String remark, String submitState, String passState,
			String userAccount, String auditType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 定义法制分管对象
		LegalAreaLeaderAuditBaseInfo legalAreaLeaderAudit = new LegalAreaLeaderAuditBaseInfo();
		legalAreaLeaderAudit.setId(AuditStringUtils.getUUID());
		legalAreaLeaderAudit.setIsSubmit(submitState);
		legalAreaLeaderAudit.setPassState(passState);
		legalAreaLeaderAudit.setRemark(remark);
		legalAreaLeaderAudit.setUserAccount(userAccount);
		legalAreaLeaderAudit.setProjectDateId(projectId);

		// 加入法制分管审批信息
		Integer count = ibatisCommonDAO.executeInsert(
				"addlegalAreaLeaderAudit", legalAreaLeaderAudit);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}

		// 状态是提交的情况
		if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {

			// 获取预审项目ID
			List<String> projectIds = ibatisCommonDAO.executeForObjectList(
					"getBaseProejctIds", projectId);

			// 同意的情况
			if (CommonConstant.AUDIT_STATE_PASS.equals(passState)) {
				for (String id : projectIds) {
					// 下一步
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.areaLeaderAudit.state"),
							userAccount);
				}
				// 更改分管领导状态
				ibatisCommonDAO.executeUpdate("updateareaLeaderAudit",
						projectId);
			} else {
				// 不同意的情况
				for (String id : projectIds) {
					// 上一步
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.legalSection.state"),
							userAccount);

				}
				// 更改法制科长领导状态
				ibatisCommonDAO.executeUpdate("updatelegalsection", projectId);
			}
		}
		map.put("id", legalAreaLeaderAudit.getId());
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-29
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#auditUpdate(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> auditUpdate(String auditId, String projectId,
			String auditContext, String remark, String submitState,
			String passState, String userAccount, String auditType)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		LegalAreaLeaderAuditBaseInfo legalAreaLeaderAudit = new LegalAreaLeaderAuditBaseInfo();
		legalAreaLeaderAudit.setId(auditId);
		legalAreaLeaderAudit.setIsSubmit(submitState);
		legalAreaLeaderAudit.setPassState(passState);
		legalAreaLeaderAudit.setRemark(remark);
		// 加入审批信息
		Integer count = ibatisCommonDAO.executeUpdate(
				"updatelegalAreaLeaderAudit", legalAreaLeaderAudit);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}

		// 状态是提交的情况
		if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {

			// 获取预审项目ID
			List<String> projectIds = ibatisCommonDAO.executeForObjectList(
					"getBaseProejctIds", projectId);

			// 同意的情况
			if (CommonConstant.AUDIT_STATE_PASS.equals(passState)) {
				for (String id : projectIds) {
					// 下一步
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.areaLeaderAudit.state"),
							userAccount);
				}
				// 更改分管领导状态
				ibatisCommonDAO.executeUpdate("updateareaLeaderAudit",
						projectId);
			} else {
				// 不同意的情况
				for (String id : projectIds) {
					// 上一步
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.legalSection.state"),
							userAccount);
				}
				// 更改法制科长领导状态
				ibatisCommonDAO.executeUpdate("updatelegalsection", projectId);
			}
		}
		map.put("id", legalAreaLeaderAudit.getId());
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}
}
