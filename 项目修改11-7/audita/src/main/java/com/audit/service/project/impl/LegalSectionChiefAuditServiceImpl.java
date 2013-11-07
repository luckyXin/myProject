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
import com.audit.entity.project.LegalSectionChiefAuditBaseInfo;
import com.audit.entity.project.LegalSectionChiefAuditInfo;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.exception.AuditException;
import com.audit.service.project.ILegalSectionChiefAuditService;

/**
 * @author User
 * 
 */
public class LegalSectionChiefAuditServiceImpl implements
		ILegalSectionChiefAuditService {
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
			String moduleId, String projectType) {

		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		auditInfo.setAuditType(auditType);
		Integer count = 0;
		List<AuditInfo> auditInfos = new ArrayList<AuditInfo>();
		if ("1".equals(projectType)) {
			// 查询总数
			count = ibatisCommonDAO.executeForObject(
					"getNoWriteIntermediatyProjectCount", auditInfo,
					Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"getNoWriteIntermediatyProject", auditInfo);

			GridDataModel<AuditInfo> AuditInfoGrid = new GridDataModel<AuditInfo>();
			AuditInfoGrid.setTotal(count);
			AuditInfoGrid.setRows(auditInfos);
			return AuditInfoGrid;
		}

		// 查询单项目
		if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT.equals(arrangeType)) {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findlegalSectionChiefAuditSingleProjectCount",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findlegalSectionChiefAuditSingleProject", auditInfo);

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
						"selectlegalSectionChiefAuditcount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"selectlegalSectionChiefAuditpage", auditInfo);

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
						"findlegalSectionChiefAuditPackProjectCount",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findlegalSectionChiefAuditPackProject", auditInfo);

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
						"findNolegalSectionChiefAuditPackProjectCount",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNolegalSectionChiefAuditPackProject", auditInfo);

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
						"findlegalSectionChiefAuditAllProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findlegalSectionChiefAuditAllProject", auditInfo);

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
						"findNolegalSectionChiefAuditAllProjectCount",
						auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNolegalSectionChiefAuditAllProject", auditInfo);

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
	public LegalSectionChiefAuditInfo getSlegalectionChiefAuditInfo(
			String projectId) {

		LegalSectionChiefAuditInfo sectionChiefAuditInfo = new LegalSectionChiefAuditInfo();

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
	public LegalSectionChiefAuditBaseInfo getlegalSectionChiefAuditBaseInfo(
			String sectionChiefAuditId, String userAccount) {
		LegalSectionChiefAuditBaseInfo legalsectionChiefAuditBaseInfo = new LegalSectionChiefAuditBaseInfo();
		legalsectionChiefAuditBaseInfo.setProjectDateId(sectionChiefAuditId);
		legalsectionChiefAuditBaseInfo.setUserAccount(userAccount);
		LegalSectionChiefAuditBaseInfo scabi = ibatisCommonDAO
				.executeForObject("getlegalSectionChiefAuditBaseInfo",
						legalsectionChiefAuditBaseInfo,
						LegalSectionChiefAuditBaseInfo.class);
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
		// 定义法制科长对象
		LegalSectionChiefAuditBaseInfo legalsectionChiefAuditBaseInfo = new LegalSectionChiefAuditBaseInfo();
		legalsectionChiefAuditBaseInfo.setId(AuditStringUtils.getUUID());
		legalsectionChiefAuditBaseInfo.setIsSubmit(submitState);
		legalsectionChiefAuditBaseInfo.setPassState(passState);
		legalsectionChiefAuditBaseInfo.setRemark(remark);
		legalsectionChiefAuditBaseInfo.setUserAccount(userAccount);
		legalsectionChiefAuditBaseInfo.setProjectDateId(projectId);

		// 加入法制科长审批信息
		Integer count = ibatisCommonDAO.executeInsert(
				"addlegalSectionChiefAudit", legalsectionChiefAuditBaseInfo);
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
					// 判断是否是200万以上的项目
					Integer countTwo = ibatisCommonDAO.executeForObject(
							"checkIsTwoMilonProject", id, Integer.class);
					if (countTwo == 0) {
						// 下一步
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.areaLeaderAudit.state"),
										userAccount);
						// 更改分管领导状态
						ibatisCommonDAO.executeUpdate("updateareaLeaderAudit",
								projectId);
					} else {
						// 下一步
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.legalAreaLeaderAudit.state"),
										userAccount);
						// 更改法制分管领导状态
						ibatisCommonDAO.executeUpdate("updatelegalAreeLeader",
								projectId);
					}
				}

			} else {
				// 不同意的情况
				for (String id : projectIds) {
					// 上一步
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.kezhang.state"),
							userAccount);

				}
				// 更新科长审批信息状态
				ibatisCommonDAO.executeUpdate("updateCectionChiefAuditstate",
						projectId);
			}
		}
		map.put("id", legalsectionChiefAuditBaseInfo.getId());
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
		LegalSectionChiefAuditBaseInfo legalsectionChiefAuditBaseInfo = new LegalSectionChiefAuditBaseInfo();
		legalsectionChiefAuditBaseInfo.setId(auditId);
		legalsectionChiefAuditBaseInfo.setIsSubmit(submitState);
		legalsectionChiefAuditBaseInfo.setPassState(passState);
		legalsectionChiefAuditBaseInfo.setRemark(remark);
		// 加入审批信息
		Integer count = ibatisCommonDAO.executeUpdate(
				"updatelegalSectionChiefAudit", legalsectionChiefAuditBaseInfo);
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
					// 判断是否是200万以上的项目
					Integer countTwo = ibatisCommonDAO.executeForObject(
							"checkIsTwoMilonProject", id, Integer.class);
					if (countTwo == 0) {
						// 下一步
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.areaLeaderAudit.state"),
										userAccount);
						// 更改分管领导状态
						ibatisCommonDAO.executeUpdate("updateareaLeaderAudit",
								projectId);
					} else {
						// 下一步
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.legalAreaLeaderAudit.state"),
										userAccount);
						// 更改法制分管领导状态
						ibatisCommonDAO.executeUpdate("updatelegalAreeLeader",
								projectId);
					}
				}
				// 更改法制分管领导状态
				ibatisCommonDAO.executeUpdate("updatelegalAreeLeader",
						projectId);
				// 更改分管领导状态
				ibatisCommonDAO.executeUpdate("updateareaLeaderAudit",
						projectId);
			} else {
				// 不同意的情况
				for (String id : projectIds) {
					// 上一步
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.kezhang.state"),
							userAccount);
				}
				// 更新科长审批信息状态
				ibatisCommonDAO.executeUpdate("updateCectionChiefAuditstate",
						projectId);
			}
		}
		map.put("id", legalsectionChiefAuditBaseInfo.getId());
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-19
	 * 
	 * @throws Exception
	 * @see com.audit.service.project.ILegalSectionChiefAuditService#updateIntermediaryInfo(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> updateIntermediaryInfo(String arrangeId,
			String intermediary, String projectArrangeTime) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrangeProject ap = new ArrangeProject();
		ap.setId(arrangeId);
		ap.setIntermediary(intermediary);
		ap.setDatapreTime(projectArrangeTime);
		Integer count = 0;
		if (arrangeId.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
			count = ibatisCommonDAO.executeUpdate(
					"updatePackIntermediaryByLegalSectionChief", ap);
		} else {
			count = ibatisCommonDAO.executeUpdate(
					"updateSingleIntermediaryByLegalSectionChief", ap);
		}
		if (count == 0) {
			throw new AuditException("中介机构更新失败");
		}
		map.put("msg", "中介机构更新成功");
		map.put("id", "中介机构更新成功");
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-19
	 * 
	 * @see com.audit.service.project.ILegalSectionChiefAuditService#getArrangeProject(java.lang.String)
	 */
	@Override
	public ArrangeProject getArrangeProject(String arrangeId) {
		ArrangeProject arrangeProject = ibatisCommonDAO.executeForObject(
				"getArrangeProjectBaseInfoById", arrangeId,
				ArrangeProject.class);
		return arrangeProject;
	}
}
