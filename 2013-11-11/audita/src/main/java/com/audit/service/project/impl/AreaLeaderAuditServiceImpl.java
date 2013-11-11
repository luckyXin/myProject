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
import com.audit.entity.project.ArrangeInfo;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.GovermentEmployeeAudit;
import com.audit.entity.project.InvestDepartAuditView;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.IAreaLeaderAuditService;

/**
 * @author User
 * 
 */
public class AreaLeaderAuditServiceImpl implements IAreaLeaderAuditService {

	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * (non-Javadoc) 2013-7-1
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize,
			String filed, String order, String projectName, String ownerName,
			String arrangeType, String auditType, String userAccount,
			String moduleId, String legalState) {

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

		if (CommonConstant.LEGAL_RETENTION_PROJECT.equals(legalState)) {
			// 查询单项目
			if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT
					.equals(arrangeType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"getLegalRetentionSingleProejctCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"getLegalRetentionSingleProejct", auditInfo);

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
				// 查询打包项目
			} else if (CommonConstant.AUDIT_ARRANGETYPE_PACKPROJECT
					.equals(arrangeType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"getLegalRetentionPackProejctCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"getLegalRetentionPackProejct", auditInfo);

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
				// 查询全部
			} else {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"getLegalRetentionProejctCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"getLegalRetentionProejct", auditInfo);

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
			// 查询单项目
			if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT
					.equals(arrangeType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findAreaLeaderAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findAreaLeaderAuditSingleProject", auditInfo);
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

				// 查询已经审批过的项目
				if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
					// 查询总数
					count = ibatisCommonDAO.executeForObject(
							"findAreaLeaderAuditSingleProjectCount", auditInfo,
							Integer.class);

					// 未审批过的项目
				} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE
						.equals(auditType)) {
					// 查询总数
					count = ibatisCommonDAO.executeForObject(
							"findNoSectionChiefAuditSingleProjectCount",
							auditInfo, Integer.class);
					// 查询总记录
					auditInfos = ibatisCommonDAO.executeForObjectList(
							"findNoSectionChiefAuditSingleProject", auditInfo);

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
							"findAreaLeaderAuditPackProjectCount", auditInfo,
							Integer.class);
					// 查询总记录
					auditInfos = ibatisCommonDAO.executeForObjectList(
							"findAreaLeaderAuditPackProject", auditInfo);

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
				} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE
						.equals(auditType)) {
					// 查询总数
					count = ibatisCommonDAO.executeForObject(
							"findNoSectionChiefAuditPackProjectCount",
							auditInfo, Integer.class);
					// 查询总记录
					auditInfos = ibatisCommonDAO.executeForObjectList(
							"findNoSectionChiefAuditPackProject", auditInfo);

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
							"findAreaLeaderAuditAllProjectCount", auditInfo,
							Integer.class);
					// 查询总记录
					auditInfos = ibatisCommonDAO.executeForObjectList(
							"findAreaLeaderAuditAllProject", auditInfo);

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
				} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE
						.equals(auditType)) {
					// 查询总数
					count = ibatisCommonDAO.executeForObject(
							"findNoSectionChiefAuditAllProjectCount",
							auditInfo, Integer.class);
					// 查询总记录
					auditInfos = ibatisCommonDAO.executeForObjectList(
							"findNoSectionChiefAuditAllProject", auditInfo);

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
		}
		GridDataModel<AuditInfo> AuditInfoGrid = new GridDataModel<AuditInfo>();
		AuditInfoGrid.setTotal(count);
		AuditInfoGrid.setRows(auditInfos);
		return AuditInfoGrid;
	}

	/**
	 * (non-Javadoc) 2013-7-1
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#getSectionChiefAuditInfo(java.lang.String)
	 */
	@Override
	public SectionChiefAuditInfo getSectionChiefAuditInfo(String projectId) {

		SectionChiefAuditInfo sectionChiefAuditInfo = new SectionChiefAuditInfo();
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
	 * (non-Javadoc) 2013-7-1
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#getSectionChiefAuditBaseInfo(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public SectionChiefAuditBaseInfo getSectionChiefAuditBaseInfo(
			String sectionChiefAuditId, String userAccount) {
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = new SectionChiefAuditBaseInfo();
		sectionChiefAuditBaseInfo.setUserAccount(userAccount);
		sectionChiefAuditBaseInfo.setProjectDateId(sectionChiefAuditId);
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo01 = ibatisCommonDAO
				.executeForObject("getAreaLeaderAuditBaseInfo",
						sectionChiefAuditBaseInfo,
						SectionChiefAuditBaseInfo.class);
		return sectionChiefAuditBaseInfo01;
	}

	/**
	 * (non-Javadoc) 2013-7-1
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#audit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> audit(String projectId, String remark,
			String submitState, String passState, String userAccount)
			throws Exception {
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = new SectionChiefAuditBaseInfo();
		sectionChiefAuditBaseInfo.setId(AuditStringUtils.getUUID());
		sectionChiefAuditBaseInfo.setIsSubmit(submitState);
		sectionChiefAuditBaseInfo.setPassState(passState);
		sectionChiefAuditBaseInfo.setRemark(remark);
		sectionChiefAuditBaseInfo.setUserAccount(userAccount);
		sectionChiefAuditBaseInfo.setProjectDateId(projectId);
		Map<String, Object> map = new HashMap<String, Object>();
		// 加入审批信息
		Integer count = ibatisCommonDAO.executeInsert(
				"addAreaLeaderAuditAudit", sectionChiefAuditBaseInfo);
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
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.AreaLeaderAudit.agree"),
							userAccount);
				}
			} else {
				// 判断是否存在法制科分管领导的审批信息
				String countLegalAreaLeaderAudit = ibatisCommonDAO
						.executeForObject(
								"checkIsExistLegalAreaLeaderAuditInfo",
								projectId, String.class);
				if (AuditStringUtils.isNotEmpty(countLegalAreaLeaderAudit)) {
					// 不同意法制科分管领导情况
					for (String id : projectIds) {
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.AreaLeaderAudit.disagreeLegalAreaLeader"),
										userAccount);
						// 修改法制分管审批状态
						Integer countOne = ibatisCommonDAO.executeUpdate(
								"updateLegalAreaLeaderAuditState",
								countLegalAreaLeaderAudit);
						if (countOne == 0) {
							throw new AuditException(PropertiesGetValue
									.getContextProperty("audit.add.fail"));
						}
					}
				} else {

					// 判断是否存在法制科科长的审批信息
					String countLegalSectionChiefAudit = ibatisCommonDAO
							.executeForObject(
									"checkIsExistLegalSectionChiefAuditInfo",
									projectId, String.class);
					if (AuditStringUtils
							.isNotEmpty(countLegalSectionChiefAudit)) {
						// 不同意法制科科长的情况
						for (String id : projectIds) {
							iWorkFlowComponent
									.changeWorkFlow(
											id,
											PropertiesGetValue
													.getContextProperty("Flow.AreaLeaderAudit.disagreeLegalSectionChief"),
											userAccount);
						}
						// 修改法制分管审批状态
						Integer countOne = ibatisCommonDAO.executeUpdate(
								"updateLegalSectionChiefAuditState",
								countLegalSectionChiefAudit);
						if (countOne == 0) {
							throw new AuditException(PropertiesGetValue
									.getContextProperty("audit.add.fail"));
						}
					} else {

						// 不同意科长的情况
						for (String id : projectIds) {
							iWorkFlowComponent
									.changeWorkFlow(
											id,
											PropertiesGetValue
													.getContextProperty("Flow.AreaLeaderAudit.disagreeSectionChief"),
											userAccount);
						}
						// 修改法制分管审批状态
						Integer countOne = ibatisCommonDAO.executeUpdate(
								"updateSectionChiefAuditState", projectId);
						if (countOne == 0) {
							throw new AuditException(PropertiesGetValue
									.getContextProperty("audit.add.fail"));
						}
					}
				}
			}
		}
		map.put("id", sectionChiefAuditBaseInfo.getId());
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-1
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#auditUpdate(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> auditUpdate(String auditId, String projectId,
			String remark, String submitState, String passState,
			String userAccount) throws Exception {
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = new SectionChiefAuditBaseInfo();
		sectionChiefAuditBaseInfo.setPassState(passState);
		sectionChiefAuditBaseInfo.setRemark(remark);
		sectionChiefAuditBaseInfo.setId(auditId);
		Map<String, Object> map = new HashMap<String, Object>();
		// 加入审批信息
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateAreaLeaderAuditInfo", sectionChiefAuditBaseInfo);
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
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.AreaLeaderAudit.agree"),
							userAccount);
				}
			} else {
				// 判断是否存在法制科分管领导的审批信息
				String countLegalAreaLeaderAudit = ibatisCommonDAO
						.executeForObject(
								"checkIsExistLegalAreaLeaderAuditInfo",
								projectId, String.class);
				if (AuditStringUtils.isNotEmpty(countLegalAreaLeaderAudit)) {
					// 不同意法制科分管领导情况
					for (String id : projectIds) {
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.AreaLeaderAudit.disagreeLegalAreaLeader"),
										userAccount);
						// 修改法制分管审批状态
						Integer countOne = ibatisCommonDAO.executeUpdate(
								"updateLegalAreaLeaderAuditState", projectId);
						if (countOne == 0) {
							throw new AuditException(PropertiesGetValue
									.getContextProperty("audit.add.fail"));
						}
					}
				} else {

					// 判断是否存在法制科科长的审批信息
					String countLegalSectionChiefAudit = ibatisCommonDAO
							.executeForObject(
									"checkIsExistLegalSectionChiefAuditInfo",
									projectId, String.class);
					if (AuditStringUtils
							.isNotEmpty(countLegalSectionChiefAudit)) {
						// 不同意法制科科长的情况
						for (String id : projectIds) {
							iWorkFlowComponent
									.changeWorkFlow(
											id,
											PropertiesGetValue
													.getContextProperty("Flow.AreaLeaderAudit.disagreeLegalSectionChief"),
											userAccount);
						}
						// 修改法制分管审批状态
						Integer countOne = ibatisCommonDAO.executeUpdate(
								"updateLegalSectionChiefAuditState", projectId);
						if (countOne == 0) {
							throw new AuditException(PropertiesGetValue
									.getContextProperty("audit.add.fail"));
						}
					} else {
						// 不同意科长的情况
						for (String id : projectIds) {
							iWorkFlowComponent
									.changeWorkFlow(
											id,
											PropertiesGetValue
													.getContextProperty("Flow.AreaLeaderAudit.disagreeSectionChief"),
											userAccount);
						}
						// 修改法制分管审批状态
						Integer countOne = ibatisCommonDAO.executeUpdate(
								"updateSectionChiefAuditState", projectId);
						if (countOne == 0) {
							throw new AuditException(PropertiesGetValue
									.getContextProperty("audit.add.fail"));
						}
					}
				}
			}
		}
		map.put("id", sectionChiefAuditBaseInfo.getId());
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * 跳过滞留法制科的项目 2013-7-1
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#jumpLegalRetentionProejct(java.lang.String)
	 */
	@Override
	public void jumpLegalRetentionProejct(String proejctId, String userAccount)
			throws Exception {

		// 取出安排项目中的预审资料ID
		List<String> projectIds = ibatisCommonDAO.executeForObjectList(
				"getBaseProejctIds", proejctId);

		for (String id : projectIds) {
			iWorkFlowComponent.changeWorkFlowNoRecord(id, PropertiesGetValue
					.getContextProperty("jump.legalRetentionproject"),
					userAccount);
		}
	}

	/**
	 * 查询安排信息项目 2013-7-16
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#find(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize,
			String filed, String order, String userAccount, String moduelId,
			String arrangeType, String auditType, String ownerName,
			String projectName) {
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setAuditType(auditType);
		auditInfo.setProjectName(projectName);
		auditInfo.setModuleId(moduelId);

		Integer count = 0;

		List<AuditInfo> auditInfos = new ArrayList<AuditInfo>();

		// 打包项目安排信息
		if (CommonConstant.AUDIT_ARRANGETYPE_PACKPROJECT.equals(arrangeType)) {
			// TODO

		} else if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT
				.equals(arrangeType)) {
			// 单项目安排信息
			// TODO

		} else {
			// 确认安排信息
			count = ibatisCommonDAO.executeForObject(
					"findAreaLeaderArrangeAffirmProjectCount", auditInfo,
					Integer.class);

			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAreaLeaderArrangeAffirmProject", auditInfo);

			// 获取政府雇员
			for (AuditInfo str : auditInfos) {
				// 获取单项目安排关联的政府雇员
				List<String> govenmentEmployees = ibatisCommonDAO
						.executeForObjectList("getAllEmployeeNameByArrangeId",
								str.getId());
				str.setGovernmentEmployee(AuditStringUtils
						.addString(govenmentEmployees));
			}
		}
		GridDataModel<AuditInfo> AuditInfoGrid = new GridDataModel<AuditInfo>();
		AuditInfoGrid.setTotal(count);
		AuditInfoGrid.setRows(auditInfos);
		return AuditInfoGrid;
	}

	/**
	 * 更新安排信息（是否加急，时限） 2013-7-16
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#updateArrangeInfo(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateArrangeInfo(String arrangeId,
			String isUrgent, String timeLimit, String userAccount,
			String areaauditRemark) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ArrangeInfo arrangeInfo = new ArrangeInfo();
		arrangeInfo.setArrangeId(arrangeId);
		arrangeInfo.setIsUrgent(isUrgent);
		arrangeInfo.setTimeLimit(timeLimit);
		arrangeInfo.setAreaauditRemark(areaauditRemark);
		// 更新安排信息
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateArrangeInfoByAreaLeader", arrangeInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}
		// 获取预审项目ID
		List<String> projectIds = ibatisCommonDAO.executeForObjectList(
				"getBaseProejctIds", arrangeId);
		for (String id : projectIds) {
			InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
			investDepartAuditView.setMainAuditId(id);
			investDepartAuditView.setInvestLeaderUserAccount(userAccount);
			// 判断项目是否在自己手中
			Integer count1 = ibatisCommonDAO.executeForObject(
					"isMySelfProject", investDepartAuditView, Integer.class);
			// 判断是否切换流程，第一次更新安排信息的情况更新流程
			if (count1 != 0) {
				// 跳转下一个流程，政府交办
				iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
						.getContextProperty("Flow.SingleProjectStateOne"),
						userAccount);
			}
		}

		map.put("id", arrangeId);
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * 获取安排信息（是否加急，限时） 2013-7-16
	 * 
	 * @see com.audit.service.project.IAreaLeaderAuditService#getArrangeInfo(java.lang.String)
	 */
	@Override
	public ArrangeInfo getArrangeInfo(String arrangeId) {

		// 获取安排信息
		ArrangeInfo arrangeInfo = ibatisCommonDAO.executeForObject(
				"selectArrangeInfoByAreaLeader", arrangeId, ArrangeInfo.class);

		// 如果检索不到安排信息
		if (arrangeInfo == null) {
			return new ArrangeInfo();
		}
		return arrangeInfo;
	}
}
