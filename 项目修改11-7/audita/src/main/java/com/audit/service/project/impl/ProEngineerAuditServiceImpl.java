/**
 * 
 */
package com.audit.service.project.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.audit.entity.User;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.DataPreBaseInfo;
import com.audit.entity.project.GovermentEmployeeAudit;
import com.audit.entity.project.InvestDepartAuditView;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.WorkloadInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.IProEngineerAuditService;

/**
 * @author Administrator
 * 
 */
public class ProEngineerAuditServiceImpl implements IProEngineerAuditService {
	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * (non-Javadoc) 2013-6-30
	 * 
	 * @see com.audit.service.project.IProEngineerAuditService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize,
			String filed, String order, String projectName, String ownerName,
			String arrangeType, String auditType, String userAccount,
			String moduleId, String beginTime, String endTime,
			String timeLimitStart, String timeLimitEnd, String isUrgent,
			String greaterThan, String lessThan) {
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		auditInfo.setBeginTime(beginTime);
		auditInfo.setEndTime(endTime);
		auditInfo.setIsUrgent(isUrgent);
		auditInfo.setTimeLimitStart(timeLimitStart);
		auditInfo.setTimeLimitEnd(timeLimitEnd);
		auditInfo.setGreaterThan(greaterThan);
		auditInfo.setLessThan(lessThan);
		// System.out.println(auditInfo.getGreaterThan());
		// System.out.println(auditInfo.getLessThan());
		Integer count = 0;
		List<AuditInfo> auditInfos = new ArrayList<AuditInfo>();
		// 查询单项目
		if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT.equals(arrangeType)) {

			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findEmployeeAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findEmployeeAuditSingleProject", auditInfo);

				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
					// 获取总的审减金额
					BigDecimal cutmoney = new BigDecimal(0.00);
					BigDecimal totallv = new BigDecimal(0.00);
					if (null != str.getIntermediaryAuditCutMoney()
							&& !"".equals(str.getIntermediaryAuditCutMoney())) {
						cutmoney = cutmoney.add(new BigDecimal(str
								.getIntermediaryAuditCutMoney()));
					}
					if (null != str.getEmployeeAuditCutMoney()
							&& !"".equals(str.getEmployeeAuditCutMoney())) {
						cutmoney = cutmoney.add(new BigDecimal(str
								.getEmployeeAuditCutMoney()));
					}
					cutmoney = cutmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
					totallv = cutmoney.divide(new BigDecimal(str
							.getSentAmount()), 4, BigDecimal.ROUND_HALF_UP);
					if ("0.0000".equals(totallv.toString())) {
						str.setTotalAuditLv("0");
					} else {
						totallv = totallv.multiply(new BigDecimal(100));
						totallv = totallv.setScale(2, BigDecimal.ROUND_HALF_UP);
						str.setTotalAuditLv(totallv.toString() + "%");
					}
					// 获取总的审减金额
					str.setTotalAuditcutMoney(cutmoney.toString());
				}

				// 已经审批过的项目
			} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findNoEngineerAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNoEngineerAuditSingleProject", auditInfo);
				// 获取政府雇员
				for (AuditInfo str : auditInfos) {
					// 获取单项目安排关联的政府雇员
					List<String> govenmentEmployees = ibatisCommonDAO
							.executeForObjectList(
									"getAllEmployeeNameByArrangeId", str
											.getId());
					str.setGovernmentEmployee(AuditStringUtils
							.addString(govenmentEmployees));
					// 获取总的审减金额
					BigDecimal cutmoney = new BigDecimal(0.00);
					BigDecimal totallv = new BigDecimal(0.00);
					if (null != str.getIntermediaryAuditCutMoney()
							&& !"".equals(str.getIntermediaryAuditCutMoney())) {
						cutmoney = cutmoney.add(new BigDecimal(str
								.getIntermediaryAuditCutMoney()));
					}
					if (null != str.getEmployeeAuditCutMoney()
							&& !"".equals(str.getEmployeeAuditCutMoney())) {
						cutmoney = cutmoney.add(new BigDecimal(str
								.getEmployeeAuditCutMoney()));
					}
					cutmoney = cutmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
					totallv = cutmoney.divide(new BigDecimal(str
							.getSentAmount()), 4, BigDecimal.ROUND_HALF_UP);
					if ("0.0000".equals(totallv.toString())) {
						str.setTotalAuditLv("0");
					} else {
						totallv = totallv.multiply(new BigDecimal(100));
						totallv = totallv.setScale(2, BigDecimal.ROUND_HALF_UP);
						str.setTotalAuditLv(totallv.toString() + "%");
					}
					// 获取总的审减金额
					str.setTotalAuditcutMoney(cutmoney.toString());
				}
			}
		}

		else if (CommonConstant.AUDIT_ARRANGETYPE_PACKPROJECT
				.equals(arrangeType)) {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {

				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findEmployeeAuditPackProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findEmployeeAuditPackProject", auditInfo);

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
						"findNoEngineerAuditPackProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNoEngineerAuditPackProject", auditInfo);

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
						"findEmployeeAuditProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findEmployeeAuditProject", auditInfo);

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
						"findNoEngineerAuditAllProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNoEngineerAuditAllProject", auditInfo);

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
	 * 审核信息获取 2013-6-30
	 * 
	 * @see com.audit.service.project.IProEngineerAuditService#getGovermentEmployeeAudit(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GovermentEmployeeAudit getGovermentEmployeeAudit(String projectId,
			String userAccount) {
		GovermentEmployeeAudit govermentEmployeeAudit = new GovermentEmployeeAudit();
		govermentEmployeeAudit.setUserAccount(userAccount);
		govermentEmployeeAudit.setProjectId(projectId);
		return ibatisCommonDAO.executeForObject(
				"getGovermentEmployeeAuditInfo", govermentEmployeeAudit,
				GovermentEmployeeAudit.class);

	}

	/**
	 * 根据资料id获取政府雇员审批信息
	 * 
	 * @param projectId
	 * @param userAccount
	 * @return
	 */
	@Override
	public GovermentEmployeeAudit getGovermentEmployeeAuditbydatapreId(
			String datapreId) {
		return ibatisCommonDAO.executeForObject(
				"getpackGovermentEmployeeAudit", datapreId,
				GovermentEmployeeAudit.class);
	}

	/**
	 * 创建政府雇员审核信息 2013-6-30
	 * 
	 * @see com.audit.service.project.IProEngineerAuditService#addGovermentEmployeeAudit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> addGovermentEmployeeAudit(String datapreId,
			String projecttype, String projectId, String auditMoney,
			String reduceMoney, String auditStartTime, String auditEndTime,
			String auditDayCount, String auditRemark, String submitState,
			String userAccount, String sectionChiefAuditId, String auditlv,
			String deskAuditRemark) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		GovermentEmployeeAudit govermentEmployeeAudit = new GovermentEmployeeAudit();
		govermentEmployeeAudit.setUserAccount(userAccount);
		govermentEmployeeAudit.setAuditDayCount(auditDayCount);
		govermentEmployeeAudit.setAuditEndTime(auditEndTime);
		govermentEmployeeAudit.setAuditMoney(auditMoney);
		govermentEmployeeAudit.setAuditRemark(auditRemark);
		govermentEmployeeAudit.setAuditStartTime(auditStartTime);
		govermentEmployeeAudit.setProjectId(projectId);
		govermentEmployeeAudit.setId(AuditStringUtils.getUUID());
		govermentEmployeeAudit.setReduceMoney(reduceMoney);
		govermentEmployeeAudit.setDatapreId(datapreId);
		govermentEmployeeAudit.setAuditlv(auditlv);
		govermentEmployeeAudit.setDeskAuditRemark(deskAuditRemark);
		//获取当前时间
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		govermentEmployeeAudit.setCreatetime(sdf.format(new Date()));
		if ("1".equals(projecttype)) {
			govermentEmployeeAudit
					.setAuditstate(CommonConstant.INTEROKAUDITSTATE);
		}
		if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {
			// 更新审批状态
			Integer count = ibatisCommonDAO.executeUpdate(
					"updateEmployeeAuditState", govermentEmployeeAudit);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}
			// 判断是否所有政府雇员都已经提交
			Integer countAudit = ibatisCommonDAO.executeForObject(
					"checkEmployeeIsCompeleteAllAudti", projectId,
					Integer.class);
			if (countAudit == 0) {
				govermentEmployeeAudit
						.setSubmitState(CommonConstant.SUBMIT_STATE_ON);
				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", projectId);
				// 切换流程
				for (String id : projectIds) {
					User user = new User();
					user.setUserAccount(userAccount);
					user.setRemark(PropertiesGetValue
							.getContextProperty("sectionChiefAudit.roleId"));
					Integer count1 = ibatisCommonDAO.executeForObject(
							"checkIsKeZhang", user, Integer.class);
					if (count1 == 0) {
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("employee.audit.State"),
										user.getUserAccount());
					} else {
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("workFlow.state.lowSectionChiefAudit"),
										user.getUserAccount());
					}
				}
				// 更新科长审批信息状态
				ibatisCommonDAO.executeUpdate(
						"updateCectionChiefAuditInfoByArrangeId", projectId);
			} else {
				govermentEmployeeAudit
						.setSubmitState(CommonConstant.SUBMIT_STATE_OFF);
				govermentEmployeeAudit
						.setAuditstate(CommonConstant.SUBMIT_STATE_ON);
				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", projectId);
				// 切换流程
				for (String id : projectIds) {
					// 单人任务结束
					iWorkFlowComponent.singlePeopleCompeleteWorkFlow(id,
							userAccount);
				}
			}
		}

		// 添加雇员审核信息
		Integer countEmployeeAudit = ibatisCommonDAO.executeInsert(
				"addGovernmentEmployeeAudit", govermentEmployeeAudit);
		if (countEmployeeAudit == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		map.put("id", govermentEmployeeAudit.getId());
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-30
	 * 
	 * @see com.audit.service.project.IProEngineerAuditService#updateGovermentEmployeeAudit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateGovermentEmployeeAudit(String datapreId,
			String projecttype, String auditId, String projectId,
			String auditMoney, String reduceMoney, String auditStartTime,
			String auditEndTime, String auditDayCount, String auditRemark,
			String submitState, String userAccount, String sectionChiefAuditId,
			String auditlv, String deskAuditRemark) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		GovermentEmployeeAudit govermentEmployeeAudit = this
				.getGovermentEmployeeAuditbydatapreId(datapreId);
		govermentEmployeeAudit.setUserAccount(userAccount);
		govermentEmployeeAudit.setAuditDayCount(auditDayCount);
		govermentEmployeeAudit.setAuditEndTime(auditEndTime);
		govermentEmployeeAudit.setAuditMoney(auditMoney);
		govermentEmployeeAudit.setAuditRemark(auditRemark);
		govermentEmployeeAudit.setAuditStartTime(auditStartTime);
		govermentEmployeeAudit.setProjectId(projectId);
		govermentEmployeeAudit.setReduceMoney(reduceMoney);
		govermentEmployeeAudit.setAuditlv(auditlv);
		govermentEmployeeAudit.setDeskAuditRemark(deskAuditRemark);
		//获取当前时间
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		govermentEmployeeAudit.setCreatetime(sdf.format(new Date()));
		if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {
			// 更新审批状态
			Integer count = ibatisCommonDAO.executeUpdate(
					"updateEmployeeAuditState", govermentEmployeeAudit);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}
			// 判断是否所有政府雇员都已经提交
			Integer countAudit = ibatisCommonDAO.executeForObject(
					"checkEmployeeIsCompeleteAllAudti", projectId,
					Integer.class);
			if (countAudit == 0) {

				// 更新科长审批信息状态
				ibatisCommonDAO.executeUpdate(
						"updateCectionChiefAuditInfoByArrangeId", projectId);
				govermentEmployeeAudit
						.setSubmitState(CommonConstant.SUBMIT_STATE_ON);
				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", projectId);
				// 切换流程
				for (String id : projectIds) {
					User user = new User();
					user.setUserAccount(userAccount);
					user.setRemark(PropertiesGetValue
							.getContextProperty("sectionChiefAudit.roleId"));
					// 判断项目是否在自己手中
					InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
					investDepartAuditView.setMainAuditId(id);
					investDepartAuditView
							.setInvestLeaderUserAccount(userAccount);
					Integer count2 = ibatisCommonDAO.executeForObject(
							"isMySelfProject", investDepartAuditView,
							Integer.class);
					if (count2 != 0) {
						Integer count1 = ibatisCommonDAO.executeForObject(
								"checkIsKeZhang", user, Integer.class);
						if (count1 == 0) {
							iWorkFlowComponent
									.changeWorkFlow(
											id,
											PropertiesGetValue
													.getContextProperty("employee.audit.State"),
											user.getUserAccount());
						} else {
							iWorkFlowComponent
									.changeWorkFlow(
											id,
											PropertiesGetValue
													.getContextProperty("workFlow.state.lowSectionChiefAudit"),
											user.getUserAccount());
						}
					}
				}
			} else {
				govermentEmployeeAudit
						.setSubmitState(CommonConstant.SUBMIT_STATE_OFF);
				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", projectId);
				// 切换流程
				for (String id : projectIds) {
					// 单人任务结束
					iWorkFlowComponent.singlePeopleCompeleteWorkFlow(id,
							userAccount);
				}
			}
		}

		// 添加雇员审核信息
		Integer countEmployeeAudit = ibatisCommonDAO.executeUpdate(
				"updateGovernmentEmployeeAudit", govermentEmployeeAudit);
		if (countEmployeeAudit == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		map.put("id", govermentEmployeeAudit.getId());
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-1
	 * 
	 * @see com.audit.service.project.IProEngineerAuditService#getCectionChiefAuditInfo(java.lang.String)
	 */
	@Override
	public SectionChiefAuditBaseInfo getCectionChiefAuditInfo(String projcetId) {
		SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = ibatisCommonDAO
				.executeForObject("getCectionChiefAuditInfo", projcetId,
						SectionChiefAuditBaseInfo.class);
		return sectionChiefAuditBaseInfo;
	}

	@Override
	public List<GovermentEmployeeAudit> findauditcutmoney(String id) {
		List<GovermentEmployeeAudit> list = ibatisCommonDAO
				.executeForObjectList("selectgovauditandcutmoney", id);
		if (null == list || list.size() == 0) {
			return null;
		} else {
			return list;
		}

	}

	/**
	 * 提交政府雇员审核
	 * 
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addsubmit(String arrangeId,
			String auditStartTime, String auditEndTime, String auditDayCount,
			User user, String state) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		GovermentEmployeeAudit govermentEmployeeAudit = new GovermentEmployeeAudit();
		govermentEmployeeAudit.setProjectId(arrangeId);
		govermentEmployeeAudit.setAuditDayCount(auditDayCount);
		govermentEmployeeAudit.setAuditEndTime(auditEndTime);
		govermentEmployeeAudit.setAuditStartTime(auditStartTime);
		govermentEmployeeAudit.setUserAccount(user.getUserAccount());
		govermentEmployeeAudit.setSubmitState(state);

		if (CommonConstant.SUBMIT_STATE_ON.equals(state)) {
			// 更新审批状态
			Integer countOne = ibatisCommonDAO.executeUpdate(
					"updateEmployeeAuditState", govermentEmployeeAudit);
			if (countOne == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}

			// 判断是否所有政府雇员都已经提交
			Integer countAudit = ibatisCommonDAO.executeForObject(
					"checkEmployeeIsCompeleteAllAudti", arrangeId,
					Integer.class);
			if (countAudit == 0) {
				// 更新科长审批信息状态
				ibatisCommonDAO.executeUpdate(
						"updateCectionChiefAuditInfoByArrangeId", arrangeId);
				govermentEmployeeAudit
						.setSubmitState(CommonConstant.SUBMIT_STATE_ON);
				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", arrangeId);
				// 切换流程
				for (String id : projectIds) {
					user.setUserAccount(user.getUserAccount());
					user.setRemark(PropertiesGetValue
							.getContextProperty("sectionChiefAudit.roleId"));
					Integer count = ibatisCommonDAO.executeForObject(
							"checkIsKeZhang", user, Integer.class);
					if (count == 0) {
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("employee.audit.State"),
										user.getUserAccount());
					} else {
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("workFlow.state.lowSectionChiefAudit"),
										user.getUserAccount());
					}
				}
			} else {
				govermentEmployeeAudit
						.setSubmitState(CommonConstant.SUBMIT_STATE_OFF);
				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", arrangeId);
				// 切换流程
				for (String id : projectIds) {
					// 单人任务结束
					iWorkFlowComponent.singlePeopleCompeleteWorkFlow(id, user
							.getUserAccount());
				}
			}
			// 更新审批状态
			Integer count = ibatisCommonDAO.executeUpdate(
					"updateEmployeeAuditStateAndOtherInfo",
					govermentEmployeeAudit);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}
		} else {

			// 更新审批状态
			Integer count = ibatisCommonDAO.executeUpdate(
					"updateEmployeeAuditStateAndOtherInfo",
					govermentEmployeeAudit);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}
		}

		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		map.put("id", 1);
		return map;
	}

	/**
	 * 查询政府雇员查看的相应的资料信息
	 * 
	 * @param datapreId
	 * @return
	 */
	public List<DataPreBaseInfo> finddatapreBydateId(String datapreId) {
		List<DataPreBaseInfo> list = ibatisCommonDAO.executeForObjectList(
				"selectdatapreguyuan", datapreId);
		if (null == list || list.size() == 0) {
			return null;
		} else {
			for (DataPreBaseInfo data : list) {
				if (AuditStringUtils.isNotEmpty(data.getProspectTime())) {
					data.setProspectTime(AuditStringUtils
							.getDatetoyyyyMMdd(data.getProspectTime()));
				}
				/*
				 * if(AuditStringUtils.isNotEmpty(data.getWorkloadTime())){
				 * data.setWorkloadTime(AuditStringUtils.getDatetoyyyyMMdd(data.
				 * getWorkloadTime())); }
				 */
				if (AuditStringUtils.isNotEmpty(data.getFinancialRAETime())) {
					data.setFinancialRAETime(AuditStringUtils
							.getDatetoyyyyMMdd(data.getFinancialRAETime()));
				}

			}
			return list;
		}
	}

	/**
	 * 查询最新核对工作量时间
	 * 
	 * @param datapreId
	 * @return
	 */
	public WorkloadInfo findworkTime(String id) {
		WorkloadInfo wt = ibatisCommonDAO.executeForObject("selectworktime",
				id, WorkloadInfo.class);
		if (null != wt) {
			if (AuditStringUtils.isNotEmpty(wt.getStartTime())) {
				wt.setStartTime(AuditStringUtils.getDatetoyyyyMMdd(wt
						.getStartTime()));
			}
			if (AuditStringUtils.isNotEmpty(wt.getEndTime())) {
				wt.setEndTime(AuditStringUtils.getDatetoyyyyMMdd(wt
						.getEndTime()));
			}
			return wt;
		} else {
			return null;
		}
	}

	/**
	 * 根据安排查询领导审核意见
	 * 
	 * @param arrangeId
	 * @param method
	 * @return
	 */
	public ResultAuditInfo findAuditView(String id, String method) {
		ResultAuditInfo result = null;
		if ("1".equals(method)) {
			result = ibatisCommonDAO.executeForObject("selectfuheview", id,
					ResultAuditInfo.class);
		}
		if ("2".equals(method)) {
			result = ibatisCommonDAO.executeForObject("selectkezhangview", id,
					ResultAuditInfo.class);
		}
		if ("3".equals(method)) {
			result = ibatisCommonDAO.executeForObject("selectfzkezhangview",
					id, ResultAuditInfo.class);
		}
		if ("4".equals(method)) {
			result = ibatisCommonDAO.executeForObject("selectfzfenguanview",
					id, ResultAuditInfo.class);
		}
		if ("5".equals(method)) {
			result = ibatisCommonDAO.executeForObject("selectfenguanview", id,
					ResultAuditInfo.class);
		}
		return result;

	}

	/**
	 * (non-Javadoc) 2013-10-15 复核意见单记录保留分页查询
	 * 
	 * @see com.audit.service.project.IProEngineerAuditService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<GovermentEmployeeAudit> find(Integer page,
			Integer pagesize, String name, String order, String datapreId) {
		GovermentEmployeeAudit govermentEmployeeAudit = new GovermentEmployeeAudit();
		govermentEmployeeAudit.setFiled(name);
		govermentEmployeeAudit.setSort(order);
		govermentEmployeeAudit.setDatapreId(datapreId);
		GridDataModel<GovermentEmployeeAudit> gm = null;
		try {

			List<GovermentEmployeeAudit> list = ibatisCommonDAO
					.executeForObjectList("selectcopyGovermentEmployeeAudit",
							govermentEmployeeAudit, pagesize * (page - 1),
							pagesize);
			Integer count = ibatisCommonDAO.executeForObject(
					"selectcopyGovermentEmployeeAuditcount",
					govermentEmployeeAudit, Integer.class);
			gm = new GridDataModel<GovermentEmployeeAudit>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;

	}
}
