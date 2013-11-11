/**
 * 
 */
package com.audit.service.project.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.audit.entity.project.AreaLeaderView;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.Capitalexpenditures;
import com.audit.entity.project.DataPreBaseInfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.GovermentEmployeeAudit;
import com.audit.entity.project.IdeaNoteParam;
import com.audit.entity.project.InvestDepartAuditView;
import com.audit.entity.project.LegalDepartAuditView;
import com.audit.entity.project.MainAuditInfo;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.Problems;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.entity.project.TotalAuditorView;
import com.audit.entity.project.WorkloadInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.IMainEmployeeAuditService;

/**
 * @author User
 * 
 */
public class MainEmployeeAuditServiceImpl implements IMainEmployeeAuditService {

	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * 审计通知书 2013-7-3
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findAdviceNote(int,
	 *      int, java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findAdviceNote(int page, int pagesize,
			String sort, String order, String ownerName, String projectName,
			String arrangeType, String auditType, String moduleId,
			String userAccount, String isUrgent, String timeLimitStart,
			String timeLimitEnd) {
		ArrangeProject auditInfo = new ArrangeProject();
		auditInfo.setFiled(sort);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		auditInfo.setTimeLimitStart(timeLimitStart);
		auditInfo.setTimeLimitEnd(timeLimitEnd);
		auditInfo.setIsUrgent(isUrgent);
		Integer count = 0;
		List<ArrangeProject> auditInfos = new ArrayList<ArrangeProject>();

		// 已经录入的项目
		if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {

			// 查询总数
			count = ibatisCommonDAO.executeForObject(
					"findAuditAdviceNoteProjectCompleteCount", auditInfo,
					Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAuditAdviceNoteProjectComplete", auditInfo);

			for (ArrangeProject arrangeProject : auditInfos) {
				arrangeProject
						.setAuditStateTwo(CommonConstant.AUDIT_STATE_COMPLETE);
			}

		} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
			// 未录入的项目
			// 查询总数
			count = ibatisCommonDAO.executeForObject(
					"findAuditAdviceNoteProjectNoCompleteCount", auditInfo,
					Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAuditAdviceNoteProjectNoComplete", auditInfo);

			for (ArrangeProject arrangeProject : auditInfos) {
				arrangeProject
						.setAuditStateTwo(CommonConstant.AUDIT_STATE_NOCOMPLETE);
			}
		} else {
			// 查询总数
			count = ibatisCommonDAO
					.executeForObject("findAuditAdviceNoteProjectCount",
							auditInfo, Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAuditAdviceNoteProject", auditInfo);
		}

		GridDataModel<ArrangeProject> ArrangeProjects = new GridDataModel<ArrangeProject>();
		ArrangeProjects.setRows(auditInfos);
		ArrangeProjects.setTotal(count);
		return ArrangeProjects;
	}

	/**
	 * (non-Javadoc) 2013-7-3
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findIdeaNoteFlg(int,
	 *      int, java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findIdeaNoteFlg(int page,
			int pagesize, String sort, String order, String ownerName,
			String projectName, String arrangeType, String auditType,
			String moduleId, String userAccount, String isUrgent,
			String timeLimitStart, String timeLimitEnd) {

		ArrangeProject auditInfo = new ArrangeProject();
		auditInfo.setFiled(sort);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		auditInfo.setTimeLimitStart(timeLimitStart);
		auditInfo.setTimeLimitEnd(timeLimitEnd);
		auditInfo.setIsUrgent(isUrgent);
		Integer count = 0;
		List<ArrangeProject> auditInfos = new ArrayList<ArrangeProject>();

		// 已经录入的项目
		if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
			// 查询总数
			count = ibatisCommonDAO.executeForObject(
					"findAuditIdeaNoteProjectCompleteCount", auditInfo,
					Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAuditIdeaNoteProjectComplete", auditInfo);

			for (ArrangeProject arrangeProject : auditInfos) {
				arrangeProject
						.setAuditStateTwo(CommonConstant.AUDIT_STATE_COMPLETE);
			}

		} else if (CommonConstant.AUDIT_STATE_NOCOMPLETE.equals(auditType)) {
			// 未录入的项目
			// 查询总数
			count = ibatisCommonDAO.executeForObject(
					"findAuditIdeaNoteProjectNoCompleteCount", auditInfo,
					Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAuditIdeaNoteProjectNoComplete", auditInfo);

			for (ArrangeProject arrangeProject : auditInfos) {
				arrangeProject
						.setAuditStateTwo(CommonConstant.AUDIT_STATE_NOCOMPLETE);
			}
		} else {
			// 查询总数
			count = ibatisCommonDAO.executeForObject(
					"findAuditIdeaNoteProjectCount", auditInfo, Integer.class);
			// 查询总记录
			auditInfos = ibatisCommonDAO.executeForObjectList(
					"findAuditIdeaNoteProject", auditInfo);
		}

		GridDataModel<ArrangeProject> ArrangeProjects = new GridDataModel<ArrangeProject>();
		ArrangeProjects.setRows(auditInfos);
		ArrangeProjects.setTotal(count);
		return ArrangeProjects;
	}

	/**
	 * (non-Javadoc) 2013-7-3
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findReport(int,
	 *      int, java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findReport(int page, int pagesize,
			String sort, String order, String ownerName, String projectName,
			String arrangeType, String auditType, String moduleId,
			String userAccount, String beginTime, String endTime,
			String isUrgent, String timeLimitStart, String timeLimitEnd) {

		ArrangeProject auditInfo = new ArrangeProject();
		auditInfo.setFiled(sort);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);
		auditInfo.setModuleId(moduleId);
		auditInfo.setBeginTime(beginTime);
		auditInfo.setEndTime(endTime);
		auditInfo.setTimeLimitStart(timeLimitStart);
		auditInfo.setTimeLimitEnd(timeLimitEnd);
		auditInfo.setIsUrgent(isUrgent);
		Integer count = 0;
		List<ArrangeProject> auditInfos = new ArrayList<ArrangeProject>();
		// 查询单项目
		if (CommonConstant.AUDIT_ARRANGETYPE_SINGLEPROJECT.equals(arrangeType)) {
			// 查询已经审批过的项目
			if (CommonConstant.AUDIT_STATE_COMPLETE.equals(auditType)) {
				// 查询总数
				count = ibatisCommonDAO.executeForObject(
						"findMainAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findMainAuditSingleProject", auditInfo);

				// 获取政府雇员
				for (ArrangeProject str : auditInfos) {
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
						"findNoMainAuditSingleProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNoMainAuditSingleProject", auditInfo);

				// 获取政府雇员
				for (ArrangeProject str : auditInfos) {
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
						"findMainAuditPackProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findMainAuditPackProject", auditInfo);

				// 获取政府雇员
				for (ArrangeProject str : auditInfos) {
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
						"findNoMainAuditPackProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNoMainAuditPackProject", auditInfo);

				// 获取政府雇员
				for (ArrangeProject str : auditInfos) {
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
						"findMainAuditProjectCount", auditInfo, Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findMainAuditProject", auditInfo);

				// 获取政府雇员
				for (ArrangeProject str : auditInfos) {
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
						"findNoMainAuditAllProjectCount", auditInfo,
						Integer.class);
				// 查询总记录
				auditInfos = ibatisCommonDAO.executeForObjectList(
						"findNoMainAuditAllProject", auditInfo);

				// 获取政府雇员
				for (ArrangeProject str : auditInfos) {
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
		GridDataModel<ArrangeProject> AuditInfoGrid = new GridDataModel<ArrangeProject>();
		AuditInfoGrid.setTotal(count);
		AuditInfoGrid.setRows(auditInfos);
		return AuditInfoGrid;
	}

	/**
	 * 资料预审基本信息 2013-7-3
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getDataPreBaseInfo(java.lang.String)
	 */
	@Override
	public DataPreBaseInfo getDataPreBaseInfo(String dataPreId) {

		DataPreBaseInfo dataPreBaseInfo = ibatisCommonDAO.executeForObject(
				"getDataPreBaseInfo", dataPreId, DataPreBaseInfo.class);

		return dataPreBaseInfo;
	}

	/**
	 * 获取审计通知书导入的信息 2013-7-3
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#AuditAdviceNot(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, String> auditAdviceNot(String projectId,
			String prospectTime, String workloadTime, String adviceNoteType)
			throws Exception {

		// 获取预审项目基本信息
		DataPreBaseInfo dataPreBaseInfo = ibatisCommonDAO.executeForObject(
				"getDataPreBaseInfo", projectId, DataPreBaseInfo.class);

		// 获取该项目的主审
		String mainAuditName = ibatisCommonDAO.executeForObject(
				"getProjectMainAudit", projectId, String.class);

		// 获取该项目的复审人员
		List<String> auditEmployees = ibatisCommonDAO.executeForObjectList(
				"getProjectAuditEmployee", projectId);

		String auditEmployee = AuditStringUtils.addString(auditEmployees);

		// 获取当前时间
		Calendar calendar = Calendar.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		if (AuditStringUtils.equals(dataPreBaseInfo.getAuditType(), "决算审计")) {
			map.put("adviceNoteType", "0");
		} else if (AuditStringUtils.equals(dataPreBaseInfo.getAuditType(),
				"复核审计")) {
			map.put("adviceNoteType", "2");
		} else {
			map.put("adviceNoteType", "1");
		}
		// 获取勘探时间
		if (AuditStringUtils.isNotEmpty(prospectTime)) {
			Date prospectD = AuditStringUtils.strToDate(prospectTime,
					AuditStringUtils.DATE_YYYYMMMDD);
			Calendar prospectc = Calendar.getInstance();
			prospectc.setTime(prospectD);
			map.put("pY", AuditStringUtils.toString(prospectc
					.get(Calendar.YEAR)));
			map.put("pM", AuditStringUtils.toString(prospectc
					.get(Calendar.MONTH) + 1));
			map.put("pD", AuditStringUtils.toString(prospectc
					.get(Calendar.DAY_OF_MONTH)));
		}
		map.put("constructUnit", dataPreBaseInfo.getProOwnerName());
		map.put("projectName", dataPreBaseInfo.getProjectName());
		map.put("ny", AuditStringUtils.toString(calendar.get(Calendar.YEAR)));
		map.put("nm", AuditStringUtils
				.toString(calendar.get(Calendar.MONTH) + 1));
		map.put("nd", AuditStringUtils.toString(calendar
				.get(Calendar.DAY_OF_MONTH)));
		map.put("mainAuditName", mainAuditName);
		map.put("auditEmployee", auditEmployee);
		return map;
	}

	/**
	 * 获取审计信息 2013-7-4
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getMainAuditInfo(java.lang.String)
	 */
	@Override
	public MainAuditInfo getMainAuditInfo(String id) {
		if (AuditStringUtils.contains(id, CommonConstant.STR_DATAPRE)) {
			id= ibatisCommonDAO.executeForObject("ystosps",id, String.class);
		}
		MainAuditInfo mainAuditInfo = new MainAuditInfo();

		// 打包项目的场合
		if (AuditStringUtils.contains(id,
				CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
			mainAuditInfo = ibatisCommonDAO.executeForObject(
					"getPackProjectMainAuditInfo", id, MainAuditInfo.class);

			if (null != mainAuditInfo) {
				// 判断子项目提交状态
				Integer count = ibatisCommonDAO.executeForObject(
						"checkPackSubProjectSubmitState", id, Integer.class);
				if (count != 0) {
					mainAuditInfo.setSubmitState("0");
				} else {
					mainAuditInfo.setSubmitState("1");
				}
			}
		} else if (AuditStringUtils.contains(id,
				CommonConstant.STR_SINGLEPROJECTARRANGEPRIMARYKEY)) {
			// 单项目的场合
			mainAuditInfo = ibatisCommonDAO.executeForObject(
					"getSingleProjectMainAuditInfo", id, MainAuditInfo.class);
		} else {
			// 单项目的场合
			mainAuditInfo = ibatisCommonDAO.executeForObject(
					"getPreProjectMainAuditInfo", id, MainAuditInfo.class);
		}
		return mainAuditInfo;
	}

	/**
	 * 添加审计信息 2013-7-4
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#addMainAudit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> addMainAudit(String projectId,
			String auditReportTime, String auditReportCode,
			String auditReportRemark, String auditReduceAllMoney,
			String auditInvestAllMoney, String auditContentAndScale,
			String noNormMoney, String otherNoNormMoney, String remark,
			String noNormProblem, String otherNoNormProblem,
			String problemRemark, String submitState, String userAccount,
			String projectDirectMoney, String totalInvestmentBudget,
			String designCompany, String projectStartTime,
			String projectEndTime, String prospectCompany,
			String projectManageCompany, String supervisorCompany,
			String buildMoney, String alreadyMoney, String progressPayment,
			String designMoney, String prospectMoney, String theTenderFee,
			String actualCompeleteMoney, String beyondMoney, String areaMoney,
			String cityMoney, String selfRaisedMoney, String bankMoney,
			String otherInputMoney, String projectConsultMoney,
			String eiaMoney, String clearMoney, String projectManageMoney,
			String auditMoney, String otherOutMoney, String supervisorMoney,
			String dayCount, String money,List<Capitalexpenditures> listCap,List<Problems> listpro,
			String sentAuditMone,String maincontent,String indirectcosts) throws Exception {
		MainAuditInfo mainAuditInfo = new MainAuditInfo();
		mainAuditInfo.setDayCount(dayCount);
		mainAuditInfo.setMoney(money);
		mainAuditInfo.setOtherOutMoney(otherOutMoney);
		mainAuditInfo.setSupervisorMoney(supervisorMoney);
		mainAuditInfo.setAreaMoney(areaMoney);
		mainAuditInfo.setCityMoney(cityMoney);
		mainAuditInfo.setSelfRaisedMoney(selfRaisedMoney);
		mainAuditInfo.setBankMoney(bankMoney);
		mainAuditInfo.setOtherInputMoney(otherInputMoney);
		mainAuditInfo.setProjectConsultMoney(projectConsultMoney);
		mainAuditInfo.setEiaMoney(eiaMoney);
		mainAuditInfo.setClearMoney(clearMoney);
		mainAuditInfo.setProjectManageMoney(projectManageMoney);
		mainAuditInfo.setAuditMoney(auditMoney);
		mainAuditInfo.setProjectManageCompany(projectManageCompany);
		mainAuditInfo.setSupervisorCompany(supervisorCompany);
		mainAuditInfo.setBuildMoney(buildMoney);
		mainAuditInfo.setAlreadyMoney(alreadyMoney);
		mainAuditInfo.setProgressPayment(progressPayment);
		mainAuditInfo.setDesignMoney(designMoney);
		mainAuditInfo.setProspectMoney(prospectMoney);
		mainAuditInfo.setTheTenderFee(theTenderFee);
		mainAuditInfo.setActualCompeleteMoney(actualCompeleteMoney);
		mainAuditInfo.setBeyondMoney(beyondMoney);
		mainAuditInfo.setAuditContentAndScale(auditContentAndScale);
		mainAuditInfo.setAuditInvestAllMoney(auditInvestAllMoney);
		mainAuditInfo.setAuditReduceAllMoney(auditReduceAllMoney);
		mainAuditInfo.setAuditReportCode(auditReportCode);
		mainAuditInfo.setAuditReportRemark(auditReportRemark);
		mainAuditInfo.setAuditReportTime(auditReportTime);
		mainAuditInfo.setMainAuditPeople(userAccount);
		mainAuditInfo.setNoNormProblem(noNormProblem);
		mainAuditInfo.setOtherNoNormMoney(otherNoNormMoney);
		mainAuditInfo.setRemark(remark);
		mainAuditInfo.setSubmitState(submitState);
		mainAuditInfo.setId(AuditStringUtils.getUUID());
		mainAuditInfo.setNoNormMoney(noNormMoney);
		mainAuditInfo.setOtherNoNormProblem(otherNoNormProblem);
		mainAuditInfo.setProblemRemark(problemRemark);
		mainAuditInfo.setProjectDirectMoney(projectDirectMoney);
		mainAuditInfo.setTotalInvestmentBudget(totalInvestmentBudget);
		mainAuditInfo.setDesignCompany(designCompany);
		mainAuditInfo.setProjectStartTime(projectStartTime);
		mainAuditInfo.setProjectEndTime(projectEndTime);
		mainAuditInfo.setProspectCompany(prospectCompany);
		mainAuditInfo.setMaincontent(maincontent);
		mainAuditInfo.setSentAuditMone(sentAuditMone);
		mainAuditInfo.setIndirectcosts(indirectcosts);

		// 打包项目的场合
		if (AuditStringUtils.contains(projectId,
				CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
			mainAuditInfo.setArrangeId(projectId);
			// 判断是否提交
			if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {

				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", projectId);

				// 将子项目的状态全改为已提交
				Integer count = ibatisCommonDAO.executeUpdate(
						"updateMainAuditStateByArrangeId", projectId);

				if (count == 0) {
					throw new AuditException(PropertiesGetValue
							.getContextProperty("audit.add.fail"));
				}

				// 结束流程
				for (String id : projectIds) {
					// iWorkFlowComponent.endWorkFlow(id, userAccount);
					// 切换流程经历三级审批
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.investKeZhang.state"),
							userAccount);
				}
			}
			// 单项目的场合
		} else if (AuditStringUtils.contains(projectId,
				CommonConstant.STR_SINGLEPROJECTARRANGEPRIMARYKEY)) {
			mainAuditInfo.setArrangeId(projectId);

			// 查询子项目预审ID
			List<String> projectIds = ibatisCommonDAO.executeForObjectList(
					"getBaseProejctIds", projectId);

			// 添加审计信息
			mainAuditInfo.setProjectId(projectIds.get(0));
			Integer count = ibatisCommonDAO.executeInsert(
					"insertMainAuditInfo", mainAuditInfo);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}else  {
				
				//增加资金支出
				for(Capitalexpenditures cap:listCap ){
					cap.setMainAuditInfoid(mainAuditInfo.getId());
					count = ibatisCommonDAO.executeInsert(
							"instCapitalexpenditures", cap);
				}
				//增加问题
				for(Problems pro:listpro){
					pro.setMainAuditInfoid(mainAuditInfo.getId());
					count = ibatisCommonDAO.executeInsert("instproblems", pro);
					
				}
			}

			// 判断是否提交
			if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {
				// 结束流程
				// iWorkFlowComponent.endWorkFlow(projectIds.get(0),
				// userAccount);
				// 切换流程三级审批
				iWorkFlowComponent
						.changeWorkFlow(
								projectIds.get(0),
								PropertiesGetValue
										.getContextProperty("Flow.investKeZhang.state"),
								userAccount);
			}
		} else {

			String arrangeId = ibatisCommonDAO.executeForObject(
					"getProjectPackArrangeId", projectId, String.class);
			mainAuditInfo.setArrangeId(arrangeId);
			// 添加审计信息
			mainAuditInfo.setProjectId(projectId);
			mainAuditInfo.setSubmitState("0");
			Integer count = ibatisCommonDAO.executeInsert(
					"insertMainAuditInfo", mainAuditInfo);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}else{
				//增加资金支出
				for(Capitalexpenditures cap:listCap ){
					cap.setMainAuditInfoid(mainAuditInfo.getId());
					count = ibatisCommonDAO.executeInsert(
							"instCapitalexpenditures", cap);
				}
				//增加问题
				for(Problems pro:listpro){
					pro.setMainAuditInfoid(mainAuditInfo.getId());
					count = ibatisCommonDAO.executeInsert("instproblems", pro);
					
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", mainAuditInfo.getId());
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-4
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateMainAudit(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateMainAudit(String ainAuditId,
			String projectId, String auditReportTime, String auditReportCode,
			String auditReportRemark, String auditReduceAllMoney,
			String auditInvestAllMoney, String auditContentAndScale,
			String noNormMoney, String otherNoNormMoney, String remark,
			String noNormProblem, String otherNoNormProblem,
			String problemRemark, String submitState, String userAccount,
			String projectDirectMoney, String totalInvestmentBudget,
			String designCompany, String projectStartTime,
			String projectEndTime, String prospectCompany,
			String projectManageCompany, String supervisorCompany,
			String buildMoney, String alreadyMoney, String progressPayment,
			String designMoney, String prospectMoney, String theTenderFee,
			String actualCompeleteMoney, String beyondMoney, String areaMoney,
			String cityMoney, String selfRaisedMoney, String bankMoney,
			String otherInputMoney, String projectConsultMoney,
			String eiaMoney, String clearMoney, String projectManageMoney,
			String auditMoney, String otherOutMoney, String supervisorMoney,
			String dayCount, String money,List<Capitalexpenditures>listCap,List<Problems>listpro
			,String sentAuditMone,String auditmoney,String maincontent,String indirectcosts,String datapreId
			) throws Exception {

		MainAuditInfo mainAuditInfo = new MainAuditInfo();
		mainAuditInfo.setDayCount(dayCount);
		mainAuditInfo.setMoney(money);
		mainAuditInfo.setOtherOutMoney(otherOutMoney);
		mainAuditInfo.setSupervisorMoney(supervisorMoney);
		mainAuditInfo.setAreaMoney(areaMoney);
		mainAuditInfo.setCityMoney(cityMoney);
		mainAuditInfo.setSelfRaisedMoney(selfRaisedMoney);
		mainAuditInfo.setBankMoney(bankMoney);
		mainAuditInfo.setOtherInputMoney(otherInputMoney);
		mainAuditInfo.setProjectConsultMoney(projectConsultMoney);
		mainAuditInfo.setEiaMoney(eiaMoney);
		mainAuditInfo.setClearMoney(clearMoney);
		mainAuditInfo.setProjectManageMoney(projectManageMoney);
		mainAuditInfo.setAuditMoney(auditMoney);
		mainAuditInfo.setProjectManageCompany(projectManageCompany);
		mainAuditInfo.setSupervisorCompany(supervisorCompany);
		mainAuditInfo.setBuildMoney(buildMoney);
		mainAuditInfo.setAlreadyMoney(alreadyMoney);
		mainAuditInfo.setProgressPayment(progressPayment);
		mainAuditInfo.setDesignMoney(designMoney);
		mainAuditInfo.setProspectMoney(prospectMoney);
		mainAuditInfo.setTheTenderFee(theTenderFee);
		mainAuditInfo.setActualCompeleteMoney(actualCompeleteMoney);
		mainAuditInfo.setBeyondMoney(beyondMoney);
		mainAuditInfo.setProjectDirectMoney(projectDirectMoney);
		mainAuditInfo.setTotalInvestmentBudget(totalInvestmentBudget);
		mainAuditInfo.setDesignCompany(designCompany);
		
		
		mainAuditInfo.setProspectCompany(prospectCompany);
		mainAuditInfo.setAuditContentAndScale(auditContentAndScale);
		mainAuditInfo.setAuditInvestAllMoney(auditInvestAllMoney);
		mainAuditInfo.setAuditReduceAllMoney(auditReduceAllMoney);
		mainAuditInfo.setAuditReportCode(auditReportCode);
		mainAuditInfo.setAuditReportRemark(auditReportRemark);
		
		mainAuditInfo.setMainAuditPeople(userAccount);
		mainAuditInfo.setNoNormProblem(noNormProblem);
		mainAuditInfo.setOtherNoNormMoney(otherNoNormMoney);
		mainAuditInfo.setRemark(remark);
		mainAuditInfo.setSubmitState(submitState);
		mainAuditInfo.setId(ainAuditId);
		mainAuditInfo.setNoNormMoney(noNormMoney);
		mainAuditInfo.setProblemRemark(problemRemark);
		mainAuditInfo.setOtherNoNormProblem(otherNoNormProblem);
		mainAuditInfo.setSentAuditMone(sentAuditMone);
		mainAuditInfo.setAuditMoney(auditmoney);
		mainAuditInfo.setMaincontent(maincontent);
		mainAuditInfo.setIndirectcosts(indirectcosts);
		if (AuditStringUtils.isNotEmpty(projectStartTime)) {
			mainAuditInfo.setProjectStartTime(projectStartTime);
		}
		if (AuditStringUtils.isNotEmpty(projectEndTime)) {
			mainAuditInfo.setProjectEndTime(projectEndTime);
		}
		if (AuditStringUtils.isNotEmpty(auditReportTime)) {
			mainAuditInfo.setAuditReportTime(auditReportTime);
		}
		// 打包项目的场合
		if (AuditStringUtils.contains(projectId,
				CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
			mainAuditInfo.setArrangeId(projectId);
			// 判断是否提交
			if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {

				// 获取预审项目ID
				List<String> projectIds = ibatisCommonDAO.executeForObjectList(
						"getBaseProejctIds", projectId);

				// 将子项目的状态全改为已提交
				Integer count = ibatisCommonDAO.executeUpdate(
						"updateMainAuditStateByArrangeId", projectId);

				if (count == 0) {
					throw new AuditException(PropertiesGetValue
							.getContextProperty("audit.add.fail"));
				}

				// 结束流程
				for (String id : projectIds) {
					// 切换流程三级审批
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.investKeZhang.state"),
							userAccount);
				}
			}
			// 单项目的场合
		} else if (AuditStringUtils.contains(projectId,
				CommonConstant.STR_SINGLEPROJECTARRANGEPRIMARYKEY)) {
			mainAuditInfo.setArrangeId(projectId);

			// 查询子项目预审ID
			List<String> projectIds = ibatisCommonDAO.executeForObjectList(
					"getBaseProejctIds", projectId);

			// 添加审计信息
			mainAuditInfo.setProjectId(projectIds.get(0));
			Integer count = ibatisCommonDAO.executeInsert(
					"updateMainAuditInfo", mainAuditInfo);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}else{
				//修改问题和资金支出
				String mainAuditInid = mainAuditInfo.getId();
				//先删除
				 ibatisCommonDAO.executeInsert("deletcapitalexpenditures", mainAuditInid);
				 ibatisCommonDAO.executeInsert("deleteproblems", mainAuditInid);
				 //再添加
				//增加资金支出
					for(Capitalexpenditures cap:listCap ){
						cap.setMainAuditInfoid(mainAuditInfo.getId());
						count = ibatisCommonDAO.executeInsert(
								"instCapitalexpenditures", cap);
					}
					//增加问题
					for(Problems pro:listpro){
						pro.setMainAuditInfoid(mainAuditInfo.getId());
						count = ibatisCommonDAO.executeInsert("instproblems", pro);
						
					}
			}
			if (CommonConstant.SUBMIT_STATE_ON.equals(submitState)) {
				// 结束流程
				// iWorkFlowComponent.endWorkFlow(projectIds.get(0),
				// userAccount);
				// 切换流程三级审批
				iWorkFlowComponent
						.changeWorkFlow(
								projectIds.get(0),
								PropertiesGetValue
										.getContextProperty("Flow.investKeZhang.state"),
								userAccount);
			}
			
		} else {
			String arrangeId = ibatisCommonDAO.executeForObject(
					"getProjectPackArrangeId", projectId, String.class);
			mainAuditInfo.setArrangeId(arrangeId);
			// 添加审计信息
			mainAuditInfo.setProjectId(projectId);
			Integer count = ibatisCommonDAO.executeInsert(
					"updateMainAuditInfo", mainAuditInfo);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue
						.getContextProperty("audit.add.fail"));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ainAuditId);
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * 获取基本信息 2013-7-5
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getProjectMainEmployeeAuditInfo(java.lang.String)
	 */
	@Override
	public SectionChiefAuditInfo getProjectMainEmployeeAuditInfo(
			String projectId) {

		SectionChiefAuditInfo sectionChiefAuditInfo = new SectionChiefAuditInfo();

		ArrangeProject arrangeProject = ibatisCommonDAO.executeForObject(
				"getProjectBaseInfoByProjectId", projectId,
				ArrangeProject.class);

		ProIntermediaryAudit intermediaryAudit = ibatisCommonDAO
				.executeForObject("getIntermediaryAuditByProjectId", projectId,
						ProIntermediaryAudit.class);

		GovermentEmployeeAudit govermentEmployeeAudit = ibatisCommonDAO
				.executeForObject("getGovermentEmployeeAuditByProjectId",
						projectId, GovermentEmployeeAudit.class);

		sectionChiefAuditInfo.setArrangeProject(arrangeProject);
		sectionChiefAuditInfo.setGovermentEmployeeAudit(govermentEmployeeAudit);
		sectionChiefAuditInfo.setIntermediaryAudit(intermediaryAudit);
		return sectionChiefAuditInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-8
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateMainAuditTwo(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, String> updateMainAuditTwo(String projectId,
			String projectAllInvest, String addProjectApproval,
			String budgetInfo, String budgetInfoFileUrl,
			String addProjectApprovalFileUrl, String financialRAEUrl,
			String startDate, String endDate, String buildMoney,
			String reportEndDate, String projectAlreadyExistMoney,
			String projectPlanMoney, String designMoney, String prospectMoney,
			String agencyInviteBidsMoney, String supervisionMoney,
			String projectManageMoney, String otherMoney, String contractTime,
			String realityTime, String delayTime, String delayReason,
			String endWorkManyProjectMoney, String projectDirectMoney,
			String indirectMoney, String settleAccountsAuditMoney,
			String inviteBidsCompany, String constructionMoney,
			String userAccount) throws Exception {

		// 征求意见稿是否签审
		Integer count = ibatisCommonDAO.executeForObject(
				"checkIsIdeaNoteState", projectId, Integer.class);

		if (count != 0) {
			// 更新项目生成意见稿状态为(已经生成)通过预审资料ID
			ibatisCommonDAO.executeUpdate("updateIdeaNoteState", projectId);

			// 切换流程三级审批
			iWorkFlowComponent.changeWorkFlow(projectId, PropertiesGetValue
					.getContextProperty("Flow.investKeZhang.state"),
					userAccount);
		}

		// 更新意见稿内容
		IdeaNoteParam ideaNoteParam = new IdeaNoteParam();
		ideaNoteParam.setAddProjectApproval(addProjectApproval);
		ideaNoteParam.setAddProjectApprovalFile(addProjectApprovalFileUrl);
		ideaNoteParam.setBudgetInfo(budgetInfo);
		ideaNoteParam.setBudgetInfoFile(budgetInfoFileUrl);
		ideaNoteParam.setFinancialRAE(financialRAEUrl);
		ideaNoteParam.setProjectAllInvest(projectAllInvest);
		ideaNoteParam.setId(projectId);
		ideaNoteParam.setAgencyInviteBidsMoney(agencyInviteBidsMoney);
		ideaNoteParam.setBuildMoney(buildMoney);
		ideaNoteParam.setConstructionMoney(constructionMoney);
		ideaNoteParam.setContractTime(contractTime);
		ideaNoteParam.setDelayReason(delayReason);
		ideaNoteParam.setDelayTime(delayTime);
		ideaNoteParam.setEndDate(endDate);
		ideaNoteParam.setEndWorkManyProjectMoney(endWorkManyProjectMoney);
		ideaNoteParam.setIndirectMoney(indirectMoney);
		ideaNoteParam.setInviteBidsCompany(inviteBidsCompany);
		ideaNoteParam.setOtherMoney(otherMoney);
		ideaNoteParam.setProjectAlreadyExistMoney(projectAlreadyExistMoney);
		ideaNoteParam.setProjectDirectMoney(projectDirectMoney);
		ideaNoteParam.setProjectManageMoney(projectManageMoney);
		ideaNoteParam.setProjectPlanMoney(projectPlanMoney);
		ideaNoteParam.setProspectMoney(prospectMoney);
		ideaNoteParam.setRealityTime(realityTime);
		ideaNoteParam.setStartDate(startDate);
		ideaNoteParam.setReportEndDate(reportEndDate);
		ideaNoteParam.setSettleAccountsAuditMoney(settleAccountsAuditMoney);
		ideaNoteParam.setSupervisionMoney(supervisionMoney);

		Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	/**
	 * 获取打包项目审计整合信息 2013-7-9
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getPackMainAuditInfo(java.lang.String)
	 */
	@Override
	public MainAuditInfo getPackMainAuditInfo(String arrangeId) {
		MainAuditInfo mainAuditInfo = ibatisCommonDAO.executeForObject(
				"getPackProjectMainAuditInfo", arrangeId, MainAuditInfo.class);
		return mainAuditInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-11
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateMainAuditTwo(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateMainAuditTwo(String projectId,
			String projectAllInvest, String addProjectApproval,
			String budgetInfo, String budgetInfoFileUrl,
			String addProjectApprovalFileUrl, String financialRAEUrl,
			String financialRAETime, String budgetUpdateTime,
			String budgetDirectMoney, String budgetTotalMoney) throws Exception {
		// 更新意见稿内容
		IdeaNoteParam ideaNoteParam = new IdeaNoteParam();
		ideaNoteParam.setFinancialRAE(financialRAEUrl);
		ideaNoteParam.setId(projectId);
		ideaNoteParam.setFinancialRAETime(financialRAETime);
		// 更新项目信息
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateIdeaNoteParamInfo", ideaNoteParam);

		if (count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-11
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getIdeaNoteResult(java.lang.String)
	 */
	@Override
	public IdeaNoteParam getIdeaNoteResult(String projectId) {

		// 获取报告内容
		IdeaNoteParam ideaNoteResult = ibatisCommonDAO.executeForObject(
				"getIdeaNoteInfo", projectId, IdeaNoteParam.class);

		if (ideaNoteResult == null) {
			return new IdeaNoteParam();
		}
		return ideaNoteResult;
	}

	/**
	 * (non-Javadoc) 2013-7-11
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateAdviceNote(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateAdviceNote(String projectId,
			String prospectTime, String workloadTime, String adviceNoteType,
			String auditNoteRemark, List<FileBelongRelate> listfile,
			String[] workloadStartTime, String[] workloadEndTime,
			String[] workloadContext) throws Exception {
		DataPreBaseInfo input = new DataPreBaseInfo();
		input.setId(projectId);
		input.setProspectTime(prospectTime);
		input.setWorkloadTime(workloadTime);
		input.setAuditNoteRemark(auditNoteRemark);
		for (FileBelongRelate file : listfile) {
			file.setId(AuditStringUtils.getUUID());
			ibatisCommonDAO.executeInsert("addfile", file);
		}
		// 更新预审项目信息
		Integer count = ibatisCommonDAO.executeUpdate("updatePreBaseInfo",
				input);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("audit.add.fail"));
		}

		// 删除核对工作量信息
		ibatisCommonDAO.executeDelete("deleteWorkloadInfo", projectId);

		// 添加核对工作量时间和内容
		for (int i = 0; i < workloadStartTime.length; i++) {
			WorkloadInfo workloadInfo = new WorkloadInfo();
			workloadInfo.setId(AuditStringUtils.getUUID());
			workloadInfo.setContext(workloadContext[i]);
			workloadInfo.setEndTime(workloadEndTime[i]);
			workloadInfo.setStartTime(workloadStartTime[i]);
			workloadInfo.setProjectId(projectId);
			ibatisCommonDAO.executeInsert("insertWorkloadInfo", workloadInfo);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		map.put("msg", PropertiesGetValue
				.getContextProperty("audit.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-30
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#checkIsCanCraeteReport(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> checkIsCanCraeteReport(String mainAuditId,
			String dataPreId) {

		// 判断是否是200万以上的项目
		Integer isCreate = ibatisCommonDAO.executeForObject(
				"checkIsTwoWanProject", dataPreId, Integer.class);

		if (isCreate != 0) {
			// 判断是否生成了意见稿

		}
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-30
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getMainAuditReport(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, String> getMainAuditReport(String mainAuditId,
			String dataPreId) {

		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-31
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getIdeaNoteType(java.lang.String)
	 */
	@Override
	public String getIdeaNoteType(String projectId) {

		// 判断是否是金信源项目
		Integer count = ibatisCommonDAO.executeForObject(
				"checkIsJinXingYuanProject", projectId, Integer.class);
		if (count != 0) {
			return CommonConstant.AUDIT_ADVICENOTE_JINXINYUANREVIEW;
		}

		// 判断是否是200万以上的项目
		count = ibatisCommonDAO.executeForObject("checkIsTwoMillion",
				projectId, Integer.class);
		if (count != 0) {
			return CommonConstant.AUDIT_ADVICENOTE_FINALACCOUNTS;
		} else {
			return CommonConstant.AUDIT_ADVICENOTE_TRACKING;
		}
	}

	/**
	 * (non-Javadoc) 2013-7-31
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#checkIsCanCraeteReport(java.lang.String)
	 */
	@Override
	public Map<String, Object> checkIsCanCraeteReport(String dataPreId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断是否是两百万以上的项目
		Integer count = ibatisCommonDAO.executeForObject("checkIsTwoMillion",
				dataPreId, Integer.class);
		if (count != 0) {
			// 判断是否已经生成征求意见稿
			Integer count1 = ibatisCommonDAO.executeForObject(
					"checkIsHaveIdeaNote", dataPreId, Integer.class);
			if (count1 != 0) {
				map.put("auditState", 1);
			}
		} else {
			map.put("auditState", 2);
		}
		return map;
	}

	/**
	 * 获取核对工作量信息 2013-7-31
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getWorkloadInfos(java.lang.String)
	 */
	@Override
	public List<WorkloadInfo> getWorkloadInfos(String projectId) {
		return ibatisCommonDAO.executeForObjectList("selectWorkloadInfo",
				projectId);
	}

	/**
	 * 查询投资科室已经审签过的项目 2013-8-15
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findInvestAlreadyAudit(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> findInvestAlreadyAudit(Integer page,
			Integer pagesize, String filed, String order, String projectName,
			String ownerName, String userAccount) {

		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);

		// 查询总数
		Integer count = ibatisCommonDAO.executeForObject(
				"investAlreadyAuditProjectCount", auditInfo, Integer.class);

		// 查询项目
		List<AuditInfo> auditInfos = ibatisCommonDAO.executeForObjectList(
				"investAlreadyAuditProject", auditInfo);

		// 获取政府雇员
		for (AuditInfo str : auditInfos) {
			// 获取单项目安排关联的政府雇员
			List<String> govenmentEmployees = ibatisCommonDAO
					.executeForObjectList("getAllEmployeeNameByArrangeId", str
							.getId());
			str.setGovernmentEmployee(AuditStringUtils
					.addString(govenmentEmployees));
		}

		GridDataModel<AuditInfo> ma = new GridDataModel<AuditInfo>();
		ma.setRows(auditInfos);
		ma.setTotal(count);
		return ma;
	}

	/**
	 * 投资科室签审意见信息 2013-8-15
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getInvestDepartAuditView(java.lang.String)
	 */
	@Override
	public InvestDepartAuditView getInvestDepartAuditView(String mainAuditId) {
		InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
		// 判断是否第一次进入
		if (AuditStringUtils.isNotEmpty(mainAuditId)) {
			investDepartAuditView = ibatisCommonDAO.executeForObject(
					"getInvestDepartAuditView", mainAuditId,
					InvestDepartAuditView.class);
		}
		if (investDepartAuditView == null) {
			return new InvestDepartAuditView();
		}
		return investDepartAuditView;
	}

	/**
	 * 添加投资科室审签意见 2013-8-15
	 * 
	 * @throws Exception
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateInvestDepartAuditView(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateInvestDepartAuditView(String mainAuditId,
			String investLeaderAuditRemark, String isInvestLeaderAudit,
			String userAccount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
		investDepartAuditView.setMainAuditId(mainAuditId);
		investDepartAuditView
				.setInvestLeaderAuditRemark(investLeaderAuditRemark);
		investDepartAuditView.setInvestLeaderUserAccount(userAccount);
		investDepartAuditView.setIsInvestLeaderAudit(isInvestLeaderAudit);
		investDepartAuditView.setInvestLeaderAuditTime(AuditStringUtils
				.getSystem(AuditStringUtils.DATE_YYYYMMMDD));
		// 投资科室审签意见更新语句
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateInvestDepartAuditView", investDepartAuditView);
		if (count == 0) {
			throw new AuditException("审批失败");
		}

		// 获取预审项目ID
		List<String> projectIds = ibatisCommonDAO.executeForObjectList(
				"getProjectByMainAuditId", mainAuditId);

		for (String id : projectIds) {
			investDepartAuditView.setMainAuditId(id);
			// 判断项目是否在自己手中
			Integer count1 = ibatisCommonDAO.executeForObject(
					"isMySelfProject", investDepartAuditView, Integer.class);
			// 判断是否是征求意见稿
			Integer count3 = ibatisCommonDAO.executeForObject("isSeekIdeaNote",
					mainAuditId, Integer.class);
			if (count1 != 0) {
				if (CommonConstant.AUDIT_STATE_PASS.equals(isInvestLeaderAudit)) {
					// 跳转下一个阶段法制科审签
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.legalAudit.state"),
							userAccount);
				} else {
					Integer count2 = 0;
					if (count3 != 0) {
						// 修正征求意见稿状态
						count2 = ibatisCommonDAO.executeUpdate(
								"updateIdeaNoteStateBack", mainAuditId);
					} else {
						count2 = ibatisCommonDAO.executeUpdate(
								"updateAuditReportStateBack", mainAuditId);
					}
					if (count2 == 0) {
						throw new AuditException("审批失败");
					}
					// 返回主审
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.AreaLeaderAudit.agree"),
							userAccount);
				}
			}
		}
		map.put("msg", "审批成功");
		map.put("id", mainAuditId);
		return map;
	}

	/**
	 * 查询法制科已经审签的项目 2013-8-15
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findLegalAlreadyAudit(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> findLegalAlreadyAudit(Integer page,
			Integer pagesize, String filed, String order, String projectName,
			String ownerName, String userAccount) {
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);

		// 查询总数
		Integer count = ibatisCommonDAO.executeForObject(
				"legalAlreadyAuditProjectCount", auditInfo, Integer.class);

		// 查询项目
		List<AuditInfo> auditInfos = ibatisCommonDAO.executeForObjectList(
				"legalAlreadyAuditProject", auditInfo);

		// 获取政府雇员
		for (AuditInfo str : auditInfos) {
			// 获取单项目安排关联的政府雇员
			List<String> govenmentEmployees = ibatisCommonDAO
					.executeForObjectList("getAllEmployeeNameByArrangeId", str
							.getId());
			str.setGovernmentEmployee(AuditStringUtils
					.addString(govenmentEmployees));
		}

		GridDataModel<AuditInfo> ma = new GridDataModel<AuditInfo>();
		ma.setRows(auditInfos);
		ma.setTotal(count);
		return ma;
	}

	/**
	 * 添加法制审签意见 2013-8-15
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateLegalDepartAuditView(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateLegalDepartAuditView(String mainAuditId,
			String legalLeaderAuditRemark, String isLegalLeaderAudit,
			String userAccount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		LegalDepartAuditView legalDepartAuditView = new LegalDepartAuditView();
		legalDepartAuditView.setIsLegalLeaderAudit(isLegalLeaderAudit);
		legalDepartAuditView.setLegalLeaderAuditRemark(legalLeaderAuditRemark);
		legalDepartAuditView.setLegalLeaderUserAccount(userAccount);
		legalDepartAuditView.setLegalLeaderAuditTime(AuditStringUtils
				.getSystem(AuditStringUtils.DATE_YYYYMMMDD));
		legalDepartAuditView.setMainAuditId(mainAuditId);
		// 投资科室审签意见更新语句
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateLegalDepartAuditView", legalDepartAuditView);
		if (count == 0) {
			throw new AuditException("审批失败");
		}

		// 获取预审项目ID
		List<String> projectIds = ibatisCommonDAO.executeForObjectList(
				"getProjectByMainAuditId", mainAuditId);
		for (String id : projectIds) {
			InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
			investDepartAuditView.setMainAuditId(id);
			investDepartAuditView.setInvestLeaderUserAccount(userAccount);
			// 判断项目是否在自己手中
			Integer count1 = ibatisCommonDAO.executeForObject(
					"isMySelfProject", investDepartAuditView, Integer.class);
			if (count1 != 0) {
				// 判断是否同意
				if (CommonConstant.AUDIT_STATE_PASS.equals(isLegalLeaderAudit)) {
					// 跳转下一个阶段总审计师审签
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.allMainAudit.state"),
							userAccount);
				} else {
					// 跳转下一个阶段法制科审签
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.investKeZhang.state"),
							userAccount);
				}
			}
		}
		map.put("msg", "审批成功");
		map.put("id", mainAuditId);
		return map;
	}

	/**
	 * 法制科室审签意见 2013-8-15
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getLegalDepartAuditView(java.lang.String)
	 */
	@Override
	public LegalDepartAuditView getLegalDepartAuditView(String mainAuditId) {
		LegalDepartAuditView legalDepartAuditView = new LegalDepartAuditView();
		// 判断是否第一次进入
		if (AuditStringUtils.isNotEmpty(mainAuditId)) {
			legalDepartAuditView = ibatisCommonDAO.executeForObject(
					"getLegalDepartAuditView", mainAuditId,
					LegalDepartAuditView.class);
		}
		if (legalDepartAuditView == null) {
			return new LegalDepartAuditView();
		}
		return legalDepartAuditView;
	}

	/**
	 * 获取总审计师审签信息 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getTotalAuditorAuditView(java.lang.String)
	 */
	@Override
	public TotalAuditorView getTotalAuditorAuditView(String mainAuditId) {
		TotalAuditorView totalAuditorView = new TotalAuditorView();
		// 判断是否第一次进入
		if (AuditStringUtils.isNotEmpty(mainAuditId)) {
			totalAuditorView = ibatisCommonDAO.executeForObject(
					"getTotalAuditorAuditView", mainAuditId,
					TotalAuditorView.class);
		}
		if (totalAuditorView == null) {
			return new TotalAuditorView();
		}
		return totalAuditorView;
	}

	/**
	 * 总审计师审签意见 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateTotalAuditorAuditView(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateTotalAuditorAuditView(String mainAuditId,
			String totalAuditUserAccount, String totalAuditRemark,
			String isTotalAudit) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TotalAuditorView totalAuditorView = new TotalAuditorView();
		totalAuditorView.setMainAuditId(mainAuditId);
		totalAuditorView.setTotalAuditRemark(totalAuditRemark);
		totalAuditorView.setTotalAuditUserAccount(totalAuditUserAccount);
		totalAuditorView.setIsTotalAudit(isTotalAudit);
		totalAuditorView.setTotalAuditTime(AuditStringUtils
				.getSystem(AuditStringUtils.DATE_YYYYMMMDD));
		// 投资科室审签意见更新语句
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateTotalAuditorAuditView", totalAuditorView);
		if (count == 0) {
			throw new AuditException("审批失败");
		}
		// 获取预审项目ID
		List<String> projectIds = ibatisCommonDAO.executeForObjectList(
				"getProjectByMainAuditId", mainAuditId);
		for (String id : projectIds) {
			InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
			investDepartAuditView.setMainAuditId(id);
			investDepartAuditView
					.setInvestLeaderUserAccount(totalAuditUserAccount);
			// 判断项目是否在自己手中
			Integer count1 = ibatisCommonDAO.executeForObject(
					"isMySelfProject", investDepartAuditView, Integer.class);
			if (count1 != 0) {
				// 判断是否同意
				if (CommonConstant.AUDIT_STATE_PASS.equals(isTotalAudit)) {
					// 跳转下一个阶段分管领导审签
					iWorkFlowComponent
							.changeWorkFlow(
									id,
									PropertiesGetValue
											.getContextProperty("Flow.areaLeaderAuditOther.state"),
									totalAuditUserAccount);
				} else {
					// 判断是否是征求意见稿
					Integer count2 = ibatisCommonDAO.executeForObject(
							"isSeekIdeaNote", mainAuditId, Integer.class);
					if (count2 != 0) {
						// 跳转下一个阶段法制科审签
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.legalAudit.state"),
										totalAuditUserAccount);
					} else {
						// 跳过法制科 返回投资科室审签
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.investKeZhang.state"),
										totalAuditUserAccount);
					}
				}
			}
		}
		map.put("msg", "审批成功");
		map.put("id", mainAuditId);
		return map;
	}

	/**
	 * 总审计师已经审签 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findTotalAuditorAlreadyAudit(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> findTotalAuditorAlreadyAudit(Integer page,
			Integer pagesize, String filed, String order, String projectName,
			String ownerName, String userAccount) {
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);

		// 查询总数
		Integer count = ibatisCommonDAO.executeForObject(
				"totalAuditorAlreadyAuditProjectCount", auditInfo,
				Integer.class);

		// 查询项目
		List<AuditInfo> auditInfos = ibatisCommonDAO.executeForObjectList(
				"totalAuditorAlreadyAuditProject", auditInfo);

		// 获取政府雇员
		for (AuditInfo str : auditInfos) {
			// 获取单项目安排关联的政府雇员
			List<String> govenmentEmployees = ibatisCommonDAO
					.executeForObjectList("getAllEmployeeNameByArrangeId", str
							.getId());
			str.setGovernmentEmployee(AuditStringUtils
					.addString(govenmentEmployees));
		}

		GridDataModel<AuditInfo> ma = new GridDataModel<AuditInfo>();
		ma.setRows(auditInfos);
		ma.setTotal(count);
		return ma;
	}

	/**
	 * 获取分管领导审签意见 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#getAreaLeaderAuditView(java.lang.String)
	 */
	@Override
	public AreaLeaderView getAreaLeaderAuditView(String mainAuditId) {
		AreaLeaderView totalAuditorView = new AreaLeaderView();
		// 判断是否第一次进入
		if (AuditStringUtils.isNotEmpty(mainAuditId)) {
			totalAuditorView = ibatisCommonDAO
					.executeForObject("getAreaLeaderAuditView", mainAuditId,
							AreaLeaderView.class);
		}
		if (totalAuditorView == null) {
			return new AreaLeaderView();
		}
		return totalAuditorView;
	}

	/**
	 * 添加分管领导签审意见 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateAreaLeaderAuditView(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updateAreaLeaderAuditView(String mainAuditId,
			String areaLeaderAuditRemark, String isAreaLeaderAudit,
			String userAccount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		AreaLeaderView areaLeaderView = new AreaLeaderView();
		areaLeaderView.setMainAuditId(mainAuditId);
		areaLeaderView.setAreaLeaderAuditRemark(areaLeaderAuditRemark);
		areaLeaderView.setIsAreaLeaderAudit(isAreaLeaderAudit);
		areaLeaderView.setAreaLeaderAuditUserAccount(userAccount);
		areaLeaderView.setAreaLeaderAuditTime(AuditStringUtils
				.getSystem(AuditStringUtils.DATE_YYYYMMMDD));

		// 投资科室审签意见更新语句
		Integer count = ibatisCommonDAO.executeUpdate(
				"updateAreaLeaderAuditView", areaLeaderView);
		if (count == 0) {
			throw new AuditException("审批失败");
		}
		// 获取预审项目ID
		List<String> projectIds = ibatisCommonDAO.executeForObjectList(
				"getProjectByMainAuditId", mainAuditId);
		for (String id : projectIds) {
			InvestDepartAuditView investDepartAuditView = new InvestDepartAuditView();
			investDepartAuditView.setMainAuditId(id);
			investDepartAuditView.setInvestLeaderUserAccount(userAccount);
			// 判断项目是否在自己手中
			Integer count1 = ibatisCommonDAO.executeForObject(
					"isMySelfProject", investDepartAuditView, Integer.class);
			if (count1 != 0) {
				// 判断是否同意
				if (CommonConstant.AUDIT_STATE_PASS.equals(isAreaLeaderAudit)) {
					// 判断是否是征求意见稿
					Integer count2 = ibatisCommonDAO.executeForObject(
							"isSeekIdeaNote", mainAuditId, Integer.class);
					if (count2 != 0) {

						// 更新征求意见稿完成状态
						ibatisCommonDAO.executeUpdate(
								"updateIdeaNoteStateComplete", mainAuditId);

						// 征求意见稿完成，生成审计报告
						iWorkFlowComponent
								.changeWorkFlow(
										id,
										PropertiesGetValue
												.getContextProperty("Flow.AreaLeaderAudit.agree"),
										userAccount);
					} else {
						// 更新项目审计状态
						ibatisCommonDAO.executeUpdate(
								"updateProjectAuditStateComplete", mainAuditId);
						// 项目结束
						iWorkFlowComponent.endWorkFlow(id, userAccount);
					}
				} else {
					// 返回上一级
					iWorkFlowComponent.changeWorkFlow(id, PropertiesGetValue
							.getContextProperty("Flow.allMainAudit.state"),
							userAccount);
				}
			}
		}
		map.put("msg", "审批成功");
		map.put("id", mainAuditId);
		return map;
	}

	/**
	 * 查询分管领导已经签审过的项目 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#findAreaLeaderAlreadyAudit(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<AuditInfo> findAreaLeaderAlreadyAudit(Integer page,
			Integer pagesize, String filed, String order, String projectName,
			String ownerName, String userAccount) {
		AuditInfo auditInfo = new AuditInfo();
		auditInfo.setFiled(filed);
		auditInfo.setPagesize(pagesize);
		auditInfo.setPageno(page);
		auditInfo.setSort(order);
		auditInfo.setProjectName(projectName);
		auditInfo.setOwnerName(ownerName);
		auditInfo.setUserAccount(userAccount);

		// 查询总数
		Integer count = ibatisCommonDAO.executeForObject(
				"areaLeaderAlreadyAuditProjectCount", auditInfo, Integer.class);

		// 查询项目
		List<AuditInfo> auditInfos = ibatisCommonDAO.executeForObjectList(
				"areaLeaderAlreadyAuditProject", auditInfo);

		// 获取政府雇员
		for (AuditInfo str : auditInfos) {
			// 获取单项目安排关联的政府雇员
			List<String> govenmentEmployees = ibatisCommonDAO
					.executeForObjectList("getAllEmployeeNameByArrangeId", str
							.getId());
			str.setGovernmentEmployee(AuditStringUtils
					.addString(govenmentEmployees));
		}

		GridDataModel<AuditInfo> ma = new GridDataModel<AuditInfo>();
		ma.setRows(auditInfos);
		ma.setTotal(count);
		return ma;
	}

	/**
	 * 更新审计报告的状态 2013-8-16
	 * 
	 * @see com.audit.service.project.IMainEmployeeAuditService#updateMainAuditReportState(java.lang.String)
	 */
	@Override
	public void updateMainAuditReportState(String mainAuditId,
			String userAccount) throws Exception {
		// 审计报告是否签审
		Integer count = ibatisCommonDAO.executeForObject(
				"checkIsAuditReportState", mainAuditId, Integer.class);

		if (count != 0) {
			// 更新项目生成意见稿状态为(已经生成)通过预审资料ID
			ibatisCommonDAO
					.executeUpdate("updateAuditReportState", mainAuditId);

			// 切换流程三级审批
			iWorkFlowComponent.changeWorkFlow(mainAuditId, PropertiesGetValue
					.getContextProperty("Flow.investKeZhang.state"),
					userAccount);
		}
	}

	/** (non-Javadoc)
	 * 2013-10-19
	 * xwx
	 * 按照 mainAuditId 查找资金支出表
	 * @see com.audit.service.project.IMainEmployeeAuditService#selectCapitalexpenditures(java.lang.String)
	 */
	@Override
	public List<Capitalexpenditures> selectCapitalexpenditures(
			String mainAuditId) throws Exception {
		List<Capitalexpenditures>capitalexpenditures = null;
		capitalexpenditures=ibatisCommonDAO.executeForObjectList("getcapitalexpenditures",mainAuditId);
		
		return capitalexpenditures;
	}

	/** (non-Javadoc)
	 * 2013-10-19
	 * xwx
	 * 按照 mainAuditId 查照问题表
	 * @see com.audit.service.project.IMainEmployeeAuditService#getProblems(java.lang.String)
	 */
	
	@Override
	public List<Problems> getProblems(String mainAuditId) throws Exception {
		List<Problems>Problems = null;
		Problems=ibatisCommonDAO.executeForObjectList("getProblems",mainAuditId);
		
		return Problems;
	}
}
