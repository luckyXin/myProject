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
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.ISectionChiefAuditService;
import com.audit.service.system.IUserInfoService;

/**
 * @author User
 */
public class SectionChiefAuditServiceImpl implements ISectionChiefAuditService {

	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize, String filed, String order,
			String projectName, String ownerName, String arrangeType, String auditType, String userAccount,
			String moduleId, String sectionChiefAuditType, String sectionChiefAuditProjectType) {

		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		auditInfo.setSectionChiefAuditType(sectionChiefAuditType);
		Integer count = 0;
		List<AuditInfo> auditInfos = new ArrayList<AuditInfo>();

		if (CommonConstant.SECTIONCHIEFAUDITPROJECTTYPE_YES.equals(sectionChiefAuditProjectType)) {

			count = ibatisCommonDAO.executeForObject("findsectionChiefSelfAuditProjectCount", auditInfo, Integer.class);

			auditInfos = ibatisCommonDAO.executeForObjectList("findsectionChiefSelfAuditProject", auditInfo);

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
				count = ibatisCommonDAO.executeForObject("findSectionChiefAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList("findSectionChiefAuditSingleProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
							"getAllEmployeeNameByArrangeId", str.getId());
					str.setGovernmentEmployee(AuditStringUtils.addString(govenmentEmployees));
				}
				// 未审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject("findNoSectionChiefAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList("findNoSectionChiefAuditSingleProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
							"getAllEmployeeNameByArrangeId", str.getId());
					str.setGovernmentEmployee(AuditStringUtils.addString(govenmentEmployees));
				}
			}
		} else if (CommonConstant.AUDIT_ARRANGETYPE_PACKPROJECT.equals(arrangeType)) {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject("findSectionChiefAuditPackProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList("findSectionChiefAuditPackProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
							"getAllEmployeeNameByArrangeId", str.getId());
					str.setGovernmentEmployee(AuditStringUtils.addString(govenmentEmployees));
				}

				// 查询未审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject("findNoSectionChiefAuditPackProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList("findNoSectionChiefAuditPackProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
							"getAllEmployeeNameByArrangeId", str.getId());
					str.setGovernmentEmployee(AuditStringUtils.addString(govenmentEmployees));
				}
			}
		} else {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject("findSectionChiefAuditAllProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList("findSectionChiefAuditAllProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
							"getAllEmployeeNameByArrangeId", str.getId());
					str.setGovernmentEmployee(AuditStringUtils.addString(govenmentEmployees));
				}

				// 查询未审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject("findNoSectionChiefAuditAllProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList("findNoSectionChiefAuditAllProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
							"getAllEmployeeNameByArrangeId", str.getId());
					str.setGovernmentEmployee(AuditStringUtils.addString(govenmentEmployees));
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
	public SectionChiefAuditInfo getSectionChiefAuditInfo(String projectId) {

		SectionChiefAuditInfo sectionChiefAuditInfo = new SectionChiefAuditInfo();

		ArrangeProject arrangeProject = ibatisCommonDAO.executeForObject("getProjectBaseInfo", projectId,
				ArrangeProject.class);

		ProIntermediaryAudit intermediaryAudit = new ProIntermediaryAudit();
		GovermentEmployeeAudit govermentEmployeeAudit = new GovermentEmployeeAudit();
		if (projectId.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
			intermediaryAudit = ibatisCommonDAO.executeForObject("getPackIntermediaryAudit", projectId,
					ProIntermediaryAudit.class);
			govermentEmployeeAudit = ibatisCommonDAO.executeForObject("getPackGovermentEmployeeAudit", projectId,
					GovermentEmployeeAudit.class);
		} else {
			intermediaryAudit = ibatisCommonDAO.executeForObject("getIntermediaryAudit", projectId,
					ProIntermediaryAudit.class);
			govermentEmployeeAudit = ibatisCommonDAO.executeForObject("getGovermentEmployeeAudit", projectId,
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
	public SectionChiefAuditBaseInfo getSectionChiefAuditBaseInfo(String sectionChiefAuditId, String userAccount) {
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = new SectionChiefAuditBaseInfo();
		sectionChiefAuditBaseInfo.setUserAccount(userAccount);
		sectionChiefAuditBaseInfo.setProjectDateId(sectionChiefAuditId);
		SectionChiefAuditBaseInfo scabi = ibatisCommonDAO.executeForObject("getSectionChiefAuditBaseInfo",
				sectionChiefAuditBaseInfo, SectionChiefAuditBaseInfo.class);
		return scabi;
	}

	/**
	 * 科长审批添加 2013-6-29
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#audit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> audit(String projectId, String auditContext, String remark, String submitState,
			String passState, String userAccount, String auditType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = new SectionChiefAuditBaseInfo();
		sectionChiefAuditBaseInfo.setId(AuditStringUtils.getUUID());
		sectionChiefAuditBaseInfo.setIsSubmit(submitState);
		sectionChiefAuditBaseInfo.setPassState(passState);
		sectionChiefAuditBaseInfo.setRemark(remark);
		sectionChiefAuditBaseInfo.setUserAccount(userAccount);
		sectionChiefAuditBaseInfo.setProjectDateId(projectId);

		// 加入审批信息
		Integer count = ibatisCommonDAO.executeInsert("addSectionChiefAudit", sectionChiefAuditBaseInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("audit.add.fail"));
		}

		// 状态是提交的情况
		if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {
			// 获取预审项目ID
			List<String> projectIds = ibatisCommonDAO.executeForObjectList("getBaseProejctIds", projectId);

			// 同意的情况
			if (CommonConstant.AUDIT_STATE_PASS.equals(passState)) {
				for (String id : projectIds) {

					iWorkFlowComponent
							.changeWorkFlow(id,
									PropertiesGetValue.getContextProperty("kezhang.auditGovernmentEmployee.agree"),
									userAccount);
				}
			} else {
				// 不同意的情况
				for (String id : projectIds) {
					iWorkFlowComponent.changeWorkFlow(id,
							PropertiesGetValue.getContextProperty("kezhang.auditGovernmentEmployee.disagree"),
							userAccount);

				}
				// 修改提交状态
				/*
				 * if ("0".equals(auditType)) { ProIntermediaryAudit
				 * intermediaryAudit = new ProIntermediaryAudit(); if
				 * (projectId.
				 * contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				 * List<ProIntermediaryAudit> intermediaryAudits =
				 * ibatisCommonDAO.executeForObjectList(
				 * "getPackIntermediaryAuditByArrangeId", projectId); for
				 * (ProIntermediaryAudit proIntermediaryAudit :
				 * intermediaryAudits) { Integer countZ =
				 * ibatisCommonDAO.executeUpdate
				 * ("updateIntermediaryAuditSubmitState",
				 * proIntermediaryAudit.getId()); if (countZ == 0) { throw new
				 * AuditException
				 * (PropertiesGetValue.getContextProperty("audit.add.fail")); }
				 * } } else { intermediaryAudit =
				 * ibatisCommonDAO.executeForObject("getIntermediaryAudit",
				 * projectId, ProIntermediaryAudit.class); }
				 * ibatisCommonDAO.executeUpdate
				 * ("updateIntermediaryAuditSubmitState",
				 * intermediaryAudit.getId()); } else {
				 */
				List<GovermentEmployeeAudit> govermentEmployeeAudits = ibatisCommonDAO.executeForObjectList(
						"getGovermentEmployeeAudit", projectId);
				for (GovermentEmployeeAudit govermentEmployeeAudit : govermentEmployeeAudits) {
					ibatisCommonDAO.executeUpdate("updateEmploeeAuditSubmitState", govermentEmployeeAudit.getId());
				}
				ibatisCommonDAO.executeUpdate("updateArrangeEmploeeAuditSubmitState", projectId);
				/* } */
			}
		}
		map.put("id", sectionChiefAuditBaseInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("audit.add.success"));
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
	public Map<String, Object> auditUpdate(String auditId, String projectId, String auditContext, String remark,
			String submitState, String passState, String userAccount, String auditType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = new SectionChiefAuditBaseInfo();
		sectionChiefAuditBaseInfo.setId(auditId);
		sectionChiefAuditBaseInfo.setIsSubmit(submitState);
		sectionChiefAuditBaseInfo.setPassState(passState);
		sectionChiefAuditBaseInfo.setRemark(remark);
		// 加入审批信息
		Integer count = ibatisCommonDAO.executeUpdate("updateSectionChiefAudit", sectionChiefAuditBaseInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("audit.add.fail"));
		}

		// 状态是提交的情况
		if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {

			// 获取预审项目ID
			List<String> projectIds = ibatisCommonDAO.executeForObjectList("getBaseProejctIds", projectId);

			// 同意的情况
			if (CommonConstant.AUDIT_STATE_PASS.equals(passState)) {

				for (String id : projectIds) {
					iWorkFlowComponent
							.changeWorkFlow(id,
									PropertiesGetValue.getContextProperty("kezhang.auditGovernmentEmployee.agree"),
									userAccount);
				}
			} else {
				// 不同意的情况
				for (String id : projectIds) {

					iWorkFlowComponent.changeWorkFlow(id,
							PropertiesGetValue.getContextProperty("kezhang.auditGovernmentEmployee.disagree"),
							userAccount);

				}
				// 修改提交状态
				/*
				 * if ("0".equals(auditType)) { ProIntermediaryAudit
				 * intermediaryAudit = new ProIntermediaryAudit(); if
				 * (projectId.
				 * contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				 * List<ProIntermediaryAudit> intermediaryAudits =
				 * ibatisCommonDAO.executeForObjectList(
				 * "getPackIntermediaryAuditByArrangeId", projectId); for
				 * (ProIntermediaryAudit proIntermediaryAudit :
				 * intermediaryAudits) { Integer countZ =
				 * ibatisCommonDAO.executeUpdate
				 * ("updateIntermediaryAuditSubmitState",
				 * proIntermediaryAudit.getId()); if (countZ == 0) { throw new
				 * AuditException
				 * (PropertiesGetValue.getContextProperty("audit.add.fail")); }
				 * } } else { intermediaryAudit =
				 * ibatisCommonDAO.executeForObject("getIntermediaryAudit",
				 * projectId, ProIntermediaryAudit.class); Integer countZ =
				 * ibatisCommonDAO
				 * .executeUpdate("updateIntermediaryAuditSubmitState",
				 * intermediaryAudit.getId()); if (countZ == 0) { throw new
				 * AuditException
				 * (PropertiesGetValue.getContextProperty("audit.add.fail")); }
				 * }
				 * 
				 * }else {
				 */
				List<GovermentEmployeeAudit> govermentEmployeeAudits = ibatisCommonDAO.executeForObjectList(
						"getGovermentEmployeeAudit", projectId);
				for (GovermentEmployeeAudit govermentEmployeeAudit : govermentEmployeeAudits) {
					ibatisCommonDAO.executeUpdate("updateEmploeeAuditSubmitState", govermentEmployeeAudit.getId());
				}
				Integer countZ = ibatisCommonDAO.executeUpdate("updateArrangeEmploeeAuditSubmitState", projectId);
				if (countZ == 0) {
					throw new AuditException(PropertiesGetValue.getContextProperty("audit.add.fail"));
				}
				/* } */
			}
		}
		// 更新法制科长审批状态
		ibatisCommonDAO.executeUpdate("updateLowSectionChiefAuditState", projectId);
		// 更改分管领导状态
		ibatisCommonDAO.executeUpdate("updateareaLeaderAudit", projectId);
		map.put("id", sectionChiefAuditBaseInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * 获取领导审批信息 2013-7-2
	 * 
	 * @see com.audit.service.project.ISectionChiefAuditService#getLowSectionChiefAuditBaseInfo(java.lang.String)
	 */
	@Override
	public SectionChiefAuditBaseInfo getLowSectionChiefAuditBaseInfo(String sectionChiefAuditId) {
		SectionChiefAuditBaseInfo scabiLow = ibatisCommonDAO.executeForObject("getLowSectionChiefAuditBaseInfo",
				sectionChiefAuditId, SectionChiefAuditBaseInfo.class);

		SectionChiefAuditBaseInfo scabiArea = ibatisCommonDAO.executeForObject("getAreaLeaderAuditBaseInfoInSection",
				sectionChiefAuditId, SectionChiefAuditBaseInfo.class);

		if (scabiLow != null) {
			return scabiLow;
		}
		return scabiArea;
	}

	/**
	 * 查询资料审核信息
	 * 
	 * @param projectId
	 * @return
	 */
	@Override
	public ResultAuditInfo findauditinfo(String id, String state) {
		ResultAuditInfo auditinfo = null;
		// 判断科长
		if ("1".equals(state)) {
			auditinfo = ibatisCommonDAO.executeForObject("selectkezhangaudit", id, ResultAuditInfo.class);
		}
		// 法制科长
		if ("2".equals(state)) {
			auditinfo = ibatisCommonDAO.executeForObject("selectfazhikezhangaudit", id, ResultAuditInfo.class);
		}
		// 法制分管
		if ("3".equals(state)) {
			auditinfo = ibatisCommonDAO.executeForObject("selectfazhifenguanaudit", id, ResultAuditInfo.class);
		}
		// 分管领导
		if ("4".equals(state)) {
			auditinfo = ibatisCommonDAO.executeForObject("selectfenguanaudit", id, ResultAuditInfo.class);
		}
		if (auditinfo != null) {
			if (userInfoService.findbyuserAccountobject(auditinfo.getUsername()) != null) {
				auditinfo.setUsername(userInfoService.findbyuserAccountobject(auditinfo.getUsername()).getUsername());
			}
			auditinfo.setAudittime(AuditStringUtils.getDatetoyyyyMMdd(auditinfo.getAudittime()));
			if ("2".equals(auditinfo.getIsagree())) {
				auditinfo.setIsagree("同意");
			} else {
				auditinfo.setIsagree("不同意");
			}
		}
		return auditinfo;
	}
}
