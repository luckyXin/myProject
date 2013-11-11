/**
 * 主审审核管理
 */
package com.audit.controller.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.component.IAuditDocumentComponent;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.AreaLeaderView;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.Capitalexpenditures;
import com.audit.entity.project.DataPreBaseWordInfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.InvestDepartAuditView;
import com.audit.entity.project.LegalDepartAuditView;
import com.audit.entity.project.MainAuditInfo;
import com.audit.entity.project.Problems;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.entity.project.TotalAuditorView;
import com.audit.entity.project.WorkloadInfo;
import com.audit.entity.staff.ProjectOwner;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IAreaLeaderAuditService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IMainEmployeeAuditService;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.project.ISectionChiefAuditService;
import com.audit.service.staff.IProjectOwnerService;

/**
 * @author dengxin
 * 
 */
@Controller
@RequestMapping("/project/MainAudit")
public class MainEmployeeAuditController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IMainEmployeeAuditService mainEmployeeAuditService;

	@Autowired
	private IAuditDocumentComponent auditDocumentComponent;

	@Autowired
	private IAreaLeaderAuditService areaLeaderAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	@Autowired
	private IProjectDatePreService projectDatePreService;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	/**
	 * 画面跳转 2013-7-2
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String auditReport = request.getParameter("auditReport");
		String auditAdviceNote = request.getParameter("auditAdviceNote");
		String auditIdeaNote = request.getParameter("auditIdeaNote");
		String auditReportSub = request.getParameter("auditReportSub");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("moduelId", id);
		// 生成审计通知书页面跳转
		if (AuditStringUtils.isNotEmpty(auditAdviceNote)) {

			// 取得预审资料的基本信息
			String dataPreId = request.getParameter("key");
			request.setAttribute("dataPreBaseInfo", mainEmployeeAuditService
					.getDataPreBaseInfo(dataPreId));

			// 获取主审第一阶段录入信息
			request.setAttribute("mainAuditOne", mainEmployeeAuditService
					.getIdeaNoteResult(dataPreId));

			List<WorkloadInfo> workloadInfos = mainEmployeeAuditService
					.getWorkloadInfos(dataPreId);

			if (workloadInfos.size() != 0) {
				// 获取第一次核对工作量信息
				request.setAttribute("firstWorkInfo", workloadInfos.get(0));
				workloadInfos.remove(0);
			}

			// 获取核对工作量信息
			request.setAttribute("workInfo", workloadInfos);
			return "/project/mainAuditEditAdviceNote";
		} else if (AuditStringUtils.isNotEmpty(auditReport)
				|| AuditStringUtils.isNotEmpty(auditReportSub)) {

			// 取得预审资料的基本信息
			String projectId = request.getParameter("key");

			if (projectId.contains(CommonConstant.STR_DATAPRE)) {

				// 获取子项目的基本信息
				SectionChiefAuditInfo sectionChiefAuditInfo = mainEmployeeAuditService
						.getProjectMainEmployeeAuditInfo(projectId);

				// 中介审核
				request.setAttribute("intermediaryAudit", sectionChiefAuditInfo
						.getIntermediaryAudit());

				// 复核工程师审核
				request.setAttribute("govermentEmployeeAudit",
						sectionChiefAuditInfo.getGovermentEmployeeAudit());

				request.setAttribute("projectInfo", intermediaryAuditService
						.findpackauditbyid(projectId));

				// 存在审计信息的场合，获取审计信息
				request.setAttribute("mainAuditInfo", mainEmployeeAuditService
						.getMainAuditInfo(projectId));

				MainAuditInfo mainAuditInfo = mainEmployeeAuditService
						.getMainAuditInfo(projectId);

				request.setAttribute("projectId", projectId);

				request.setAttribute("dtatpreId", projectId);

				request.setAttribute("moduelId", id + auditReport);

				// 保存项目类型
				request.setAttribute("protype", 3);

				// 投资科室签审意见
				InvestDepartAuditView investDepartAuditView = mainEmployeeAuditService
						.getInvestDepartAuditView(projectId);
				request.setAttribute("investDepartAuditView",
						investDepartAuditView);

				// 获取法制科签审意见
				LegalDepartAuditView legalDepartAuditView = mainEmployeeAuditService
						.getLegalDepartAuditView(projectId);
				request.setAttribute("legalDepartAuditView",
						legalDepartAuditView);

				// 获取总审计师审签意见
				TotalAuditorView totalAuditorAuditView = mainEmployeeAuditService
						.getTotalAuditorAuditView(projectId);
				request.setAttribute("totalAuditorAuditView",
						totalAuditorAuditView);

				// 获取分管领导审签意见
				AreaLeaderView areaLeaderView = mainEmployeeAuditService
						.getAreaLeaderAuditView(projectId);
				request.setAttribute("areaLeaderView", areaLeaderView);

				// 获取资金支出表
				// 获取问题表
				try {
					List<Problems> problems = mainEmployeeAuditService
							.getProblems(mainAuditInfo.getId());
					List<Capitalexpenditures> capitalexpenditures = mainEmployeeAuditService
							.selectCapitalexpenditures(mainAuditInfo.getId());
					if (capitalexpenditures != null) {
						if (capitalexpenditures.size() <= 3) {
							capitalexpenditures = null;
						}
					}
					request.setAttribute("capitalexpenditures",
							capitalexpenditures);
					request.setAttribute("problems", problems);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}

				return "/project/mainAuditEdit";
			} else {
				SectionChiefAuditInfo sectionChiefAuditInfo = areaLeaderAuditService
						.getSectionChiefAuditInfo(projectId);
				if (sectionChiefAuditInfo.getGovermentEmployeeAudit() != null) {
					// 政府雇员审核
					request.setAttribute("govermentEmployeeAudit",
							sectionChiefAuditInfo.getGovermentEmployeeAudit());
				}
				
				if (sectionChiefAuditInfo.getIntermediaryAudit() != null) {
					// 中介审核
					request.setAttribute("intermediaryAudit",
							sectionChiefAuditInfo.getIntermediaryAudit());
				}

				// 获取项目安排基本信息
				ResultClassArrangeInfo project = null;
				Integer protype = 0;
				// 查询单项目
				project = intermediaryAuditService.findbyid(projectId);
				// 如果单项目没有查询到查询打包项目
				if (null == project) {
					// 查询打包项目
					project = intermediaryAuditService.findpackbyid(projectId);
					protype = 1;
				}
				if (project != null) {
					// 预审资料id
					request.setAttribute("dtatpreId", project.getDatapreId());
				}
				// 项目基本信息
				request.setAttribute("projectInfo", project);
				// 保存项目类型
				request.setAttribute("protype", protype);

				// 存在审计信息的场合，获取审计信息
				request.setAttribute("mainAuditInfo", mainEmployeeAuditService
						.getMainAuditInfo(projectId));

				// 查询科长审核的意见
				ResultAuditInfo kezhang = sectionChiefAuditService
						.findauditinfo(projectId, "1");
				request.setAttribute("kezhang", kezhang);
				// 查询法制科长审核意见
				ResultAuditInfo fazhikezhang = sectionChiefAuditService
						.findauditinfo(projectId, "2");
				request.setAttribute("fazhikezhang", fazhikezhang);
				// 查询法制分管审核信息
				ResultAuditInfo fazhifenguan = sectionChiefAuditService
						.findauditinfo(projectId, "3");
				request.setAttribute("fazhifenguan", fazhifenguan);

				// 查询分管领导审核信息
				ResultAuditInfo fenguan = sectionChiefAuditService
						.findauditinfo(projectId, "4");
				request.setAttribute("fenguan", fenguan);

				// 投资科室签审意见
				InvestDepartAuditView investDepartAuditView = mainEmployeeAuditService
						.getInvestDepartAuditView(projectId);
				request.setAttribute("investDepartAuditView",
						investDepartAuditView);

				// 获取法制科签审意见
				LegalDepartAuditView legalDepartAuditView = mainEmployeeAuditService
						.getLegalDepartAuditView(projectId);
				request.setAttribute("legalDepartAuditView",
						legalDepartAuditView);

				// 获取总审计师审签意见
				TotalAuditorView totalAuditorAuditView = mainEmployeeAuditService
						.getTotalAuditorAuditView(projectId);
				request.setAttribute("totalAuditorAuditView",
						totalAuditorAuditView);

				// 获取分管领导审签意见
				AreaLeaderView areaLeaderView = mainEmployeeAuditService
						.getAreaLeaderAuditView(projectId);
				request.setAttribute("areaLeaderView", areaLeaderView);
				MainAuditInfo mainAuditInfo = mainEmployeeAuditService
						.getMainAuditInfo(projectId);

				// 获取资金支出表
				// 获取问题表
				try {
					List<Problems> problems = mainEmployeeAuditService
							.getProblems(mainAuditInfo.getId());
					List<Capitalexpenditures> capitalexpenditures = mainEmployeeAuditService
							.selectCapitalexpenditures(mainAuditInfo.getId());
					if (capitalexpenditures != null) {
						if (capitalexpenditures.size() <= 3) {
							capitalexpenditures = null;
						}
					}
					request.setAttribute("capitalexpenditures",
							capitalexpenditures);
					request.setAttribute("problems", problems);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}

				return "/project/mainAuditEdit";

			}

		} else if (AuditStringUtils.isNotEmpty(auditIdeaNote)) {
			// 取得预审资料的基本信息
			String dataPreId = request.getParameter("key");
			request.setAttribute("dataPreBaseInfo", mainEmployeeAuditService
					.getDataPreBaseInfo(dataPreId));

			// 获取主审第二阶段信息
			request.setAttribute("mainAuditTwo", mainEmployeeAuditService
					.getIdeaNoteResult(dataPreId));

			return "/project/mainAuditEditIdeaNote";
		} else {
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			String isArrange = request.getParameter("key");
			if (AuditStringUtils.isNotEmpty(isArrange)) {
				request.setAttribute("isAudit", isArrange);
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.setAttribute("commonFunction", cf);
		}
		return "/project/mainAuditIndex";
	}

	/**
	 * (non-Javadoc) 2013-7-2
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String ownerName = request.getParameter("ownerName");
		String projectName = request.getParameter("projectName");
		String arrangeType = request.getParameter("arrangeType");
		String handleType = request.getParameter("handleType");
		String moduleId = request.getParameter("moduleId");
		String auditType = request.getParameter("auditType");
		String isUrgent = request.getParameter("isUrgent");
		String timeLimitStart = request.getParameter("timeLimitStart");
		String timeLimitEnd = request.getParameter("timeLimitEnd");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1"
				: strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2"
				: rows);
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		GridDataModel<ArrangeProject> auditInfo = new GridDataModel<ArrangeProject>();
		if (CommonConstant.AUDIT_ADVICE_NOTE_FLG.equals(handleType)) {
			auditInfo = mainEmployeeAuditService.findAdviceNote(page, pagesize,
					sort, order, ownerName, projectName, arrangeType,
					auditType, moduleId, userAccount, isUrgent, timeLimitStart,
					timeLimitEnd);
		} else if (CommonConstant.AUDIT_IDEA_NOTE_FLG.equals(handleType)) {
			auditInfo = mainEmployeeAuditService.findIdeaNoteFlg(page,
					pagesize, sort, order, ownerName, projectName, arrangeType,
					auditType, moduleId, userAccount, isUrgent, timeLimitStart,
					timeLimitEnd);
		} else if (CommonConstant.AUDIT_REPORT_FLG.equals(handleType)) {
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			auditInfo = mainEmployeeAuditService.findReport(page, pagesize,
					sort, order, ownerName, projectName, arrangeType,
					auditType, moduleId, userAccount, beginTime, endTime,
					isUrgent, timeLimitStart, timeLimitEnd);
		}
		map.put("rows", auditInfo.getRows());
		map.put("total", auditInfo.getTotal());
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-2
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		String auditReportTime = request.getParameter("auditReportTime");
		String auditReportCode = request.getParameter("auditReportCode");
		String auditReportRemark = request.getParameter("auditReportRemark");
		String auditReduceAllMoney = request
				.getParameter("auditReduceAllMoney");
		String auditInvestAllMoney = request
				.getParameter("auditInvestAllMoney");
		String auditContentAndScale = request
				.getParameter("auditContentAndScale");
		String noNormMoney = request.getParameter("noNormMoney");
		String otherNoNormMoney = request.getParameter("otherNoNormMoney");
		String remark = request.getParameter("remark");
		String noNormProblem = request.getParameter("noNormProblem");
		String otherNoNormProblem = request.getParameter("otherNoNormProblem");
		String problemRemark = request.getParameter("problemRemark");
		String submitState = request.getParameter("submitState");
		String projectDirectMoney = request.getParameter("projectDirectMoney");
		String totalInvestmentBudget = request
				.getParameter("totalInvestmentBudget");
		String designCompany = request.getParameter("designCompany");
		String projectStartTime = request.getParameter("projectStartTime");
		String projectEndTime = request.getParameter("projectEndTime");
		String prospectCompany = request.getParameter("prospectCompany");
		String projectManageCompany = request
				.getParameter("projectManageCompany");
		String supervisorCompany = request.getParameter("supervisorCompany");
		String buildMoney = request.getParameter("buildMoney");
		String alreadyMoney = request.getParameter("alreadyMoney");
		String progressPayment = request.getParameter("progressPayment");
		String designMoney = request.getParameter("designMoney");
		String prospectMoney = request.getParameter("prospectMoney");
		String theTenderFee = request.getParameter("theTenderFee");
		String actualCompeleteMoney = request
				.getParameter("actualCompeleteMoney");
		String beyondMoney = request.getParameter("beyondMoney");
		String areaMoney = request.getParameter("areaMoney");
		String cityMoney = request.getParameter("cityMoney");
		String selfRaisedMoney = request.getParameter("selfRaisedMoney");
		String bankMoney = request.getParameter("bankMoney");
		String otherInputMoney = request.getParameter("otherInputMoney");
		String projectConsultMoney = request
				.getParameter("projectConsultMoney");
		String eiaMoney = request.getParameter("eiaMoney");
		String clearMoney = request.getParameter("clearMoney");
		String projectManageMoney = request.getParameter("projectManageMoney");
		String auditMoney = request.getParameter("auditMoney");
		String otherOutMoney = request.getParameter("otherOutMoney");
		String supervisorMoney = request.getParameter("supervisorMoney");
		String dayCount = request.getParameter("dayCount");
		String money = request.getParameter("money");
		String sentAuditMone = request.getParameter("sentAuditMone");
		String maincontent = request.getParameter("maincontent");
		String indirectcosts =request.getParameter("indirectcosts");
		// 获取资金支出
		List<Capitalexpenditures> listCap = new ArrayList<Capitalexpenditures>();
		for (int i = 1; i <= 7; i++) {
			Capitalexpenditures capitalexpenditures = new Capitalexpenditures();
			String capitalexpendituresUnit1 = request
					.getParameter("capitalexpendituresUnit" + i + "");
			String contractamount1 = request.getParameter("contractamount" + i
					+ "");
			String stopAuditTime1 = request.getParameter("stopAuditTime" + i
					+ "");
			String amountpaid1 = request.getParameter("amountpaid" + i + "");
			String unit1 = request.getParameter("unit" + i + "");
			String ttvmoney = request.getParameter("ttvmoney"+i+"");
			capitalexpenditures.setId(AuditStringUtils.getUUID());
			capitalexpenditures.setTtvmoney(ttvmoney);
			capitalexpenditures.setUnit(unit1);
			capitalexpenditures.setContractamount(contractamount1);
			capitalexpenditures.setStopAuditTime(stopAuditTime1);
			capitalexpenditures.setAmountpaid(amountpaid1);
			capitalexpenditures
					.setCapitalexpendituresUnit(capitalexpendituresUnit1);
			// 放入集合
			listCap.add(capitalexpenditures);
		}
		String[] capitalexpendituresUnits = request
				.getParameterValues("capitalexpendituresUnit8");
		String[] contractamounts = request
				.getParameterValues("contractamount8");
		String[] stopAuditTimes = request.getParameterValues("stopAuditTime8");
		String[] amountpaids = request.getParameterValues("amountpaid8");
		String[] ttvmoney = request.getParameterValues("ttvmoney8");
		if (capitalexpendituresUnits != null) {
			for (int i = 0; i < capitalexpendituresUnits.length; i++) {
				Capitalexpenditures capitalexpenditures = new Capitalexpenditures();
				capitalexpenditures.setId(AuditStringUtils.getUUID());
				capitalexpenditures.setUnit("其他");
				capitalexpenditures.setContractamount(contractamounts[i]);
				capitalexpenditures.setStopAuditTime(stopAuditTimes[i]);
				capitalexpenditures.setAmountpaid(amountpaids[i]);
				capitalexpenditures
						.setCapitalexpendituresUnit(capitalexpendituresUnits[i]);
				capitalexpenditures.setTtvmoney(ttvmoney[i]);
				// 放入集合
				listCap.add(capitalexpenditures);
			}
		}

		// 获取问题集合
		String[] type = request.getParameterValues("type");
		String[] money1 = request.getParameterValues("money");
		String[] reason = request.getParameterValues("reason");
		String[] day = request.getParameterValues("day");
		List<Problems> listpro = new ArrayList<Problems>();
		if (type != null) {
			for (int i = 0; i < type.length; i++) {
				Problems problems = new Problems();
				problems.setId(AuditStringUtils.getUUID());
				problems.setType(type[i]);
				problems.setMoney(money1[i]);
				problems.setReason(reason[i]);
				problems.setDay(day[i]);
				// 放入集合
				listpro.add(problems);
			}
		}

		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			// 添加审计信息
			return mainEmployeeAuditService.addMainAudit(projectId,
					auditReportTime, auditReportCode, auditReportRemark,
					auditReduceAllMoney, auditInvestAllMoney,
					auditContentAndScale, noNormMoney, otherNoNormMoney,
					remark, noNormProblem, otherNoNormProblem, problemRemark,
					submitState, userAccount, projectDirectMoney,
					totalInvestmentBudget, designCompany, projectStartTime,
					projectEndTime, prospectCompany, projectManageCompany,
					supervisorCompany, buildMoney, alreadyMoney,
					progressPayment, designMoney, prospectMoney, theTenderFee,
					actualCompeleteMoney, beyondMoney, areaMoney, cityMoney,
					selfRaisedMoney, bankMoney, otherInputMoney,
					projectConsultMoney, eiaMoney, clearMoney,
					projectManageMoney, auditMoney, otherOutMoney,
					supervisorMoney, dayCount, money, listCap, listpro,
					sentAuditMone, maincontent,indirectcosts);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-2
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ainAuditId = request.getParameter("mainAuditId");
		String projectId = request.getParameter("projectId");
		String auditReportTime = request.getParameter("auditReportTime");
		String auditReportCode = request.getParameter("auditReportCode");
		String auditReportRemark = request.getParameter("auditReportRemark");
		String auditReduceAllMoney = request
				.getParameter("auditReduceAllMoney");
		String auditInvestAllMoney = request
				.getParameter("auditInvestAllMoney");
		String auditContentAndScale = request
				.getParameter("auditContentAndScale");
		String noNormMoney = request.getParameter("noNormMoney");
		String otherNoNormMoney = request.getParameter("otherNoNormMoney");
		String remark = request.getParameter("remark");
		String noNormProblem = request.getParameter("noNormProblem");
		String otherNoNormProblem = request.getParameter("otherNoNormProblem");
		String problemRemark = request.getParameter("problemRemark");
		String submitState = request.getParameter("submitState");
		String projectDirectMoney = request.getParameter("projectDirectMoney");
		String totalInvestmentBudget = request
				.getParameter("totalInvestmentBudget");
		String designCompany = request.getParameter("designCompany");
		String projectStartTime = request.getParameter("projectStartTime");
		String projectEndTime = request.getParameter("projectEndTime");
		String prospectCompany = request.getParameter("prospectCompany");
		String projectManageCompany = request
				.getParameter("projectManageCompany");
		String supervisorCompany = request.getParameter("supervisorCompany");
		String buildMoney = request.getParameter("buildMoney");
		String alreadyMoney = request.getParameter("alreadyMoney");
		String progressPayment = request.getParameter("progressPayment");
		String designMoney = request.getParameter("designMoney");
		String prospectMoney = request.getParameter("prospectMoney");
		String theTenderFee = request.getParameter("theTenderFee");
		String actualCompeleteMoney = request
				.getParameter("actualCompeleteMoney");
		String beyondMoney = request.getParameter("beyondMoney");
		String areaMoney = request.getParameter("areaMoney");
		String cityMoney = request.getParameter("cityMoney");
		String selfRaisedMoney = request.getParameter("selfRaisedMoney");
		String bankMoney = request.getParameter("bankMoney");
		String otherInputMoney = request.getParameter("otherInputMoney");
		String projectConsultMoney = request
				.getParameter("projectConsultMoney");
		String eiaMoney = request.getParameter("eiaMoney");
		String clearMoney = request.getParameter("clearMoney");
		String projectManageMoney = request.getParameter("projectManageMoney");
		String auditMoney = request.getParameter("auditMoney");
		String otherOutMoney = request.getParameter("otherOutMoney");
		String supervisorMoney = request.getParameter("supervisorMoney");
		String dayCount = request.getParameter("dayCount");
		String money = request.getParameter("money");
		String sentAuditMone = request.getParameter("sentAuditMone");
		String maincontent = request.getParameter("maincontent");
		String indirectcosts = request.getParameter("indirectcosts");
		String datapreId = request.getParameter("datapreId");
		// 获取资金支出
		List<Capitalexpenditures> listCap = new ArrayList<Capitalexpenditures>();
		for (int i = 1; i <= 7; i++) {
			Capitalexpenditures capitalexpenditures = new Capitalexpenditures();
			String capitalexpendituresUnit1 = request
					.getParameter("capitalexpendituresUnit" + i + "");
			String contractamount1 = request.getParameter("contractamount" + i
					+ "");
			String stopAuditTime1 = request.getParameter("stopAuditTime" + i
					+ "");
			String amountpaid1 = request.getParameter("amountpaid" + i + "");
			String unit1 = request.getParameter("unit" + i + "");
			String ttvmoney1 = request.getParameter("ttvmoney"+i+"");
			capitalexpenditures.setId(AuditStringUtils.getUUID());
			capitalexpenditures.setTtvmoney(ttvmoney1);
			capitalexpenditures.setUnit(unit1);
			capitalexpenditures.setContractamount(contractamount1);
			capitalexpenditures.setStopAuditTime(stopAuditTime1);
			capitalexpenditures.setAmountpaid(amountpaid1);
			capitalexpenditures
					.setCapitalexpendituresUnit(capitalexpendituresUnit1);
			// 放入集合
			listCap.add(capitalexpenditures);
		}
		String[] capitalexpendituresUnits = request
				.getParameterValues("capitalexpendituresUnit8");
		String[] contractamounts = request
				.getParameterValues("contractamount8");
		String[] stopAuditTimes = request.getParameterValues("stopAuditTime8");
		String[] amountpaids = request.getParameterValues("amountpaid8");
		String[] ttvmoney = request.getParameterValues("ttvmoney8");
		if (capitalexpendituresUnits != null) {
			for (int i = 0; i < capitalexpendituresUnits.length; i++) {
				Capitalexpenditures capitalexpenditures = new Capitalexpenditures();
				capitalexpenditures.setId(AuditStringUtils.getUUID());
				capitalexpenditures.setUnit("其他");
				capitalexpenditures.setContractamount(contractamounts[i]);
				capitalexpenditures.setStopAuditTime(stopAuditTimes[i]);
				capitalexpenditures.setAmountpaid(amountpaids[i]);
				capitalexpenditures
						.setCapitalexpendituresUnit(capitalexpendituresUnits[i]);
				capitalexpenditures.setTtvmoney(ttvmoney[i]);
				// 放入集合
				listCap.add(capitalexpenditures);
			}
		}
		// 获取问题集合
		String[] type = request.getParameterValues("type");
		String[] money1 = request.getParameterValues("money");
		String[] reason = request.getParameterValues("reason");
		String[] day = request.getParameterValues("day");
		List<Problems> listpro = new ArrayList<Problems>();
		if (type != null) {
			for (int i = 0; i < type.length; i++) {
				Problems problems = new Problems();
				problems.setId(AuditStringUtils.getUUID());
				problems.setType(type[i]);
				problems.setMoney(money1[i]);
				problems.setReason(reason[i]);
				problems.setDay(day[i]);
				// 放入集合
				listpro.add(problems);
			}
		}

		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			// 添加审计信息
			return mainEmployeeAuditService.updateMainAudit(ainAuditId,
					projectId, auditReportTime, auditReportCode,
					auditReportRemark, auditReduceAllMoney,
					auditInvestAllMoney, auditContentAndScale, noNormMoney,
					otherNoNormMoney, remark, noNormProblem,
					otherNoNormProblem, problemRemark, submitState,
					userAccount, projectDirectMoney, totalInvestmentBudget,
					designCompany, projectStartTime, projectEndTime,
					prospectCompany, projectManageCompany, supervisorCompany,
					buildMoney, alreadyMoney, progressPayment, designMoney,
					prospectMoney, theTenderFee, actualCompeleteMoney,
					beyondMoney, areaMoney, cityMoney, selfRaisedMoney,
					bankMoney, otherInputMoney, projectConsultMoney, eiaMoney,
					clearMoney, projectManageMoney, auditMoney, otherOutMoney,
					supervisorMoney, dayCount, money, listCap, listpro,
					sentAuditMone, auditMoney, maincontent, indirectcosts,datapreId);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-2
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-2
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

	@RequestMapping("/checkIsCanCraeteReport")
	@ResponseBody
	public Map<String, Object> checkIsCanCraeteReport(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = mainEmployeeAuditService.checkIsCanCraeteReport(request
				.getParameter("mainAuditId"));
		return map;
	}

	@RequestMapping("/getWord")
	public void getWord(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取项目信息
		String projectId = request.getParameter("projectId");
		String prospectTime = request.getParameter("prospectTime");
		String workloadTime = request.getParameter("workloadTime");
		String adviceNoteType = "";
		Map<String, String> map = mainEmployeeAuditService.auditAdviceNot(
				projectId, prospectTime, workloadTime, adviceNoteType);
		adviceNoteType = map.get("adviceNoteType");
		String filePath = "";
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/auditAdviceNote");
		if (CommonConstant.AUDIT_ADVICENOTE_FINALACCOUNTS
				.equals(adviceNoteType)) {
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.adviceNote.finalAccounts"), map);
		} else if (CommonConstant.AUDIT_ADVICENOTE_TRACKING
				.equals(adviceNoteType)) {
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.adviceNote.tracking"), map);
		} else if (CommonConstant.AUDIT_ADVICENOTE_JINXINYUANREVIEW
				.equals(adviceNoteType)) {
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.adviceNote.JinXinYuanReview"),
					map);
		}
		// 文件名，输出到用户的下载对话框
		String fileName = "";
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("\\") + 1, filePath.length())
					.getBytes("GB2312"), "ISO8859_1");
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		fs = new FileInputStream(new File(filePath));
		if (null != fs) {
			// 设置响应头和保存文件名
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 写出流信息
			int b = 0;
			PrintWriter out = response.getWriter();
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
			fs.close();
			out.close();
		}
	}

	@RequestMapping("/updateAuditNoteInfo")
	public void updateAuditNoteInfo(HttpServletRequest request,
			MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) {
		String msg = "";
		String falg = "";
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取项目信息
		String projectId = request.getParameter("projectId");
		String prospectTime = request.getParameter("prospectTime");
		String workloadTime = request.getParameter("workloadTime");
		String adviceNoteType = request.getParameter("adviceNoteType");
		String auditNoteRemark = request.getParameter("auditNoteRemark");
		String[] workloadStartTime = request
				.getParameterValues("workloadStartTime");
		String[] workloadEndTime = request
				.getParameterValues("workloadEndTime");
		String[] workloadContext = request
				.getParameterValues("workloadContext");
		try {
			List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
			List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
			// 上传文件
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(
					file, "datapre", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					FileBelongRelate fbr = new FileBelongRelate();
					fbr.setFileName(listmap.get(i).get("fileName"));
					fbr.setUrl(listmap.get(i).get("url"));
					fbr.setState(CommonConstant.FILEZHUSHENSTAE);
					fbr.setBelongToId(projectId);
					fbr.setUploadTime(new Date());
					listfile.add(fbr);
				}
			}
			map = mainEmployeeAuditService.updateAdviceNote(projectId,
					prospectTime, workloadTime, adviceNoteType,
					auditNoteRemark, listfile, workloadStartTime,
					workloadEndTime, workloadContext);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("data.error.message"));
		}
		if (null != map.get("id")) {
			msg = "保存成功";
			falg = "success";
		} else {
			msg = "保存失败";
			falg = "fail";
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(
					"{'success':'" + falg + "','msg':'" + msg + "'}");
		} catch (IOException e) {

		}
	}

	@RequestMapping("/updateIdeaNoteInfo")
	public void updateIdeaNoteInfo(HttpServletRequest request,
			MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) throws Exception {
		List<MultipartFile> budgetInfoFile = requestMultipart
				.getFiles("budgetInfoFile");
		List<MultipartFile> addProjectApprovalFile = requestMultipart
				.getFiles("addProjectApprovalFile");
		List<MultipartFile> financialRAE = requestMultipart
				.getFiles("financialRAE");
		String projectAllInvest = request.getParameter("projectAllInvest");
		String addProjectApproval = request.getParameter("addProjectApproval");
		String budgetInfo = request.getParameter("budgetInfo");
		String projectId = request.getParameter("projectId");
		String financialRAETime = request.getParameter("financialRAETime");
		String budgetUpdateTime = request.getParameter("budgetUpdateTime");
		String budgetDirectMoney = request.getParameter("budgetDirectMoney");
		String budgetTotalMoney = request.getParameter("budgetTotalMoney");

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, String>> listmap0 = AuditStringUtils.uploadfile(
					budgetInfoFile, "mainEmployeeAudit", request);
			String budgetInfoFileUrl = AuditStringUtils.EMPTY;
			if (listmap0.size() != 0) {
				budgetInfoFileUrl = listmap0.get(0).get("url");
			}
			List<Map<String, String>> listmap1 = AuditStringUtils.uploadfile(
					addProjectApprovalFile, "mainEmployeeAudit", request);
			String addProjectApprovalFileUrl = AuditStringUtils.EMPTY;
			if (listmap1.size() != 0) {
				addProjectApprovalFileUrl = listmap1.get(0).get("url");
			}
			List<Map<String, String>> listmap2 = AuditStringUtils.uploadfile(
					financialRAE, "mainEmployeeAudit", request);
			String financialRAEUrl = AuditStringUtils.EMPTY;
			if (listmap2.size() != 0) {
				financialRAEUrl = listmap2.get(0).get("url");
			}
			map = mainEmployeeAuditService.updateMainAuditTwo(projectId,
					projectAllInvest, addProjectApproval, budgetInfo,
					budgetInfoFileUrl, addProjectApprovalFileUrl,
					financialRAEUrl, financialRAETime, budgetUpdateTime,
					budgetDirectMoney, budgetTotalMoney);
		} catch (IOException e) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("data.error.message"));
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("data.error.message"));
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter()
				.write(
						"{'id':'" + map.get("id") + "','msg':'"
								+ map.get("msg") + "'}");
	}

	@RequestMapping("/getIdeaNote")
	public void getIdeaNote(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String projectAllInvest = request.getParameter("projectAllInvest");
		String addProjectApproval = request.getParameter("addProjectApproval");
		String budgetInfo = request.getParameter("budgetInfo");
		String projectId = request.getParameter("projectId");
		// 工程开工时间
		String startDate = request.getParameter("startDate");

		// 工程竣工时间
		String endDate = request.getParameter("endDate");

		// 建设资金
		String buildMoney = request.getParameter("buildMoney");

		// 报告截止日
		String reportEndDate = request.getParameter("reportEndDate");

		// 工程到位资金
		String projectAlreadyExistMoney = request
				.getParameter("projectAlreadyExistMoney");

		// 工程进度款
		String projectPlanMoney = request.getParameter("projectPlanMoney");

		// 设计费
		String designMoney = request.getParameter("designMoney");

		// 勘察费
		String prospectMoney = request.getParameter("prospectMoney");

		// 招标代理费
		String agencyInviteBidsMoney = request
				.getParameter("agencyInviteBidsMoney");

		// 监理费
		String supervisionMoney = request.getParameter("supervisionMoney");

		// 项目管理费
		String projectManageMoney = request.getParameter("projectManageMoney");

		// 其他费用
		String otherMoney = request.getParameter("otherMoney");

		// 合同工期
		String contractTime = request.getParameter("contractTime");

		// 实际工期
		String realityTime = request.getParameter("realityTime");

		// 合同工期相比延期
		String delayTime = request.getParameter("delayTime");

		// 工期延期主要原因
		String delayReason = request.getParameter("delayReason");

		// 竣工结算多计工程款
		String endWorkManyProjectMoney = request
				.getParameter("endWorkManyProjectMoney");

		// 工程直接费用
		String projectDirectMoney = request.getParameter("projectDirectMoney");

		// 工程间接费用
		String indirectMoney = request.getParameter("indirectMoney");

		// 结算审计费
		String settleAccountsAuditMoney = request
				.getParameter("settleAccountsAuditMoney");

		// 招标代理机构
		String inviteBidsCompany = request.getParameter("inviteBidsCompany");

		// 施工合同价
		String constructionMoney = request.getParameter("constructionMoney");

		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		Map<String, String> map = mainEmployeeAuditService.updateMainAuditTwo(
				projectId, projectAllInvest, addProjectApproval, budgetInfo,
				"", "", "", startDate, endDate, buildMoney, reportEndDate,
				projectAlreadyExistMoney, projectPlanMoney, designMoney,
				prospectMoney, agencyInviteBidsMoney, supervisionMoney,
				projectManageMoney, otherMoney, contractTime, realityTime,
				delayTime, delayReason, endWorkManyProjectMoney,
				projectDirectMoney, indirectMoney, settleAccountsAuditMoney,
				inviteBidsCompany, constructionMoney, userAccount);

		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/auditIdeaNote");
		// 获取生成报告方式
		String method = mainEmployeeAuditService.getIdeaNoteType(projectId);
		// 获取资料预存id
		map = new HashMap<String, String>();
		// 根据预审资料id查询资料相应信息
		DataPreBaseWordInfo data = projectDatePreService.findObject(projectId);

		String filePath = "";
		if ("0".equals(method)) {
			// 项目名称
			map.put("projectName", data.getProjectName());
			//
			map.put("projectCode", data.getDatapreno());
			// 根据id查询项目业主
			ProjectOwner owner = iProjectOwnerService.getProjectOwner(data
					.getProownerid());
			if (null != owner) {
				// 项目业主
				map.put("ownerName", owner.getOwnerName());
				map.put("constuctUtil", owner.getOwnerName());
			} else {
				// 项目业主
				map.put("ownerName", "");
				map.put("constuctUtil", "");
			}
			// 工程直接费用概算（万元）
			if (AuditStringUtils.isNotEmpty(data.getProjectDirectMoney())) {
				Double a = Double.parseDouble(data.getProjectDirectMoney()) / 10000.0;
				map.put("zjMoney", a.toString());
			} else {
				map.put("zjMoney", "");
			}
			if (AuditStringUtils.isNotEmpty(data.getProjectStartTime())) {
				// 得到工程开工时间
				String time = data.getProjectStartTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程开工年月日
				map.put("sn", times[0].toString());
				map.put("sy", times[1].toString());
				map.put("sd", times[2].toString());

			} else {
				// 工程开工年月日
				map.put("sn", "");
				map.put("sy", "");
				map.put("sd", "");
			}

			if (AuditStringUtils.isNotEmpty(data.getProjectEndTime())) {
				// 得到工程竣工时间
				String time = data.getProjectEndTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程竣工年月日
				map.put("en", times[0].toString());
				map.put("ny", times[1].toString());
				map.put("nd", times[2].toString());

			} else {
				// 工程竣工年月日
				map.put("en", "");
				map.put("ny", "");
				map.put("nd", "");
			}
			// 工程建设资金
			map.put("buildMoney", data.getBuildMoney());
			// 工程到位资金
			map.put("alreadyMoney", data.getAlreadyMoney());
			// 工程实际完成投资金额
			map.put("actualCompeleteMoney", data.getActualCompeleteMoney());
			// 超过批复的工程概算总投资
			map.put("beyondMoney", data.getBeyondMoney());

			// 工程审定的工程直接费用
			map.put("auditZjMoney", data.getZjMoney());
			// 超施工合同价款
			map.put("beyondConstructMoney", "");
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.ideaNote.one"), map);
		}
		if ("2".equals(method)) {
			// 项目名称
			map.put("projectName", data.getProjectName());
			// 根据id查询项目业主
			ProjectOwner owner = iProjectOwnerService.getProjectOwner(data
					.getProownerid());
			if (null != owner) {
				// 项目业主
				map.put("ownerName", owner.getOwnerName());
				map.put("constructUtil", owner.getOwnerName());
			} else {
				// 项目业主
				map.put("ownerName", "");
				map.put("constructUtil", "");
			}
			// 工程审定的工程直接费用
			map.put("zjMoney", data.getZjMoney());
			// 超施工合同价款
			map.put("constructConstractMoney", "");
			// 工程合同工期
			map.put("contractDay", "");
			// 实际工期
			map.put("reallyContractDay", "");

			// 合同工期相比延期
			map.put("gapDay", "");
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.ideaNote.three"), map);
		}

		if ("1".equals(method)) {
			// 项目名称
			map.put("projectName", data.getProjectName());
			//
			map.put("projectCode", data.getDatapreno());
			// 根据id查询项目业主
			ProjectOwner owner = iProjectOwnerService.getProjectOwner(data
					.getProownerid());
			if (null != owner) {
				// 项目业主
				map.put("ownerName", owner.getOwnerName());
				map.put("constuctUtil", owner.getOwnerName());
			} else {
				// 项目业主
				map.put("ownerName", "");
				map.put("constuctUtil", "");
			}
			// 工程直接费用概算（万元）
			if (AuditStringUtils.isNotEmpty(data.getProjectDirectMoney())) {
				Double a = Double.parseDouble(data.getProjectDirectMoney()) / 10000.0;
				map.put("zjMoney", a.toString());
			} else {
				map.put("zjMoney", "");
			}
			if (AuditStringUtils.isNotEmpty(data.getProjectStartTime())) {
				// 得到工程开工时间
				String time = data.getProjectStartTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程开工年月日
				map.put("sn", times[0].toString());
				map.put("sy", times[1].toString());
				map.put("sd", times[2].toString());

			} else {
				// 工程开工年月日
				map.put("sn", "");
				map.put("sy", "");
				map.put("sd", "");
			}

			if (AuditStringUtils.isNotEmpty(data.getProjectEndTime())) {
				// 得到工程竣工时间
				String time = data.getProjectEndTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程竣工年月日
				map.put("en", times[0].toString());
				map.put("ny", times[1].toString());
				map.put("nd", times[2].toString());

			} else {
				// 工程竣工年月日
				map.put("en", "");
				map.put("ny", "");
				map.put("nd", "");
			}
			// 工程建设资金
			map.put("buildMoney", data.getBuildMoney());
			// 工程到位资金
			map.put("alreadyMoney", data.getAlreadyMoney());
			// 工程实际完成投资金额
			map.put("actualCompeleteMoney", data.getActualCompeleteMoney());
			// 超过批复的工程概算总投资
			map.put("beyondMoney", data.getBeyondMoney());

			// 工程审定的工程直接费用
			map.put("auditZjMoney", data.getZjMoney());
			// 超施工合同价款
			map.put("beyondConstructMoney", "");
			filePath = auditDocumentComponent.writeWord(url, "200万元以下简易报告.doc",
					map);
		}
		// 文件名，输出到用户的下载对话框
		String fileName = "";
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("\\") + 1, filePath.length())
					.getBytes("GB2312"), "ISO8859_1");
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		fs = new FileInputStream(new File(filePath));
		if (null != fs) {
			// 设置响应头和保存文件名
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 写出流信息
			int b = 0;
			PrintWriter out = response.getWriter();
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
			fs.close();
			out.close();
		}

	}

	@RequestMapping("/getAllCountMoney")
	@ResponseBody
	public Map<String, Object> getAllCountMoney(HttpServletRequest request) {
		String projectId = request.getParameter("projectId");
		Map<String, Object> map = new HashMap<String, Object>();
		MainAuditInfo mainAuditInfo = mainEmployeeAuditService
				.getMainAuditInfo(projectId);
		map.put("mainAuditInfo", mainAuditInfo);
		return map;
	}

	/**
	 * 生成主审报告
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/outmainauditword")
	public void outmainauditword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String mainAuditId = request.getParameter("mainAuditId");

		// 获取生成报告方式
		String method = mainEmployeeAuditService.getIdeaNoteType(mainAuditId);
		// 获取资料预存id
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/mainAuditNote");
		Map<String, String> map = new HashMap<String, String>();

		// 根据预审资料id查询资料相应信息
		DataPreBaseWordInfo data = projectDatePreService
				.findObject(mainAuditId);
		
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();

		// 更改审计报告状态
		mainEmployeeAuditService.updateMainAuditReportState(mainAuditId,
				userAccount);

		String filePath = "";
		if ("0".equals(method)) {
			
			if (data != null) {
				// 项目名称
				map.put("projectName", data.getProjectName());
				//
				map.put("projectCode", data.getDatapreno());
				// 根据id查询项目业主
				ProjectOwner owner = iProjectOwnerService.getProjectOwner(data
						.getProownerid());
				if (null != owner) {
					// 项目业主
					map.put("ownerName", owner.getOwnerName());
					map.put("constuctUtil", owner.getOwnerName());
			}
			} else {
				// 项目业主
				map.put("ownerName", "");
				map.put("constuctUtil", "");
			}
			
			// 工程直接费用概算（万元）
			if (AuditStringUtils.isNotEmpty(data.getProjectDirectMoney())) {
				Double a = Double.parseDouble(data.getProjectDirectMoney()) / 10000.0;
				map.put("zjMoney", a.toString());
			} else {
				map.put("zjMoney", "");
			}
			if (AuditStringUtils.isNotEmpty(data.getProjectStartTime())) {
				// 得到工程开工时间
				String time = data.getProjectStartTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程开工年月日
				map.put("sn", times[0].toString());
				map.put("sy", times[1].toString());
				map.put("sd", times[2].toString());

			} else {
				// 工程开工年月日
				map.put("sn", "");
				map.put("sy", "");
				map.put("sd", "");
			}

			if (AuditStringUtils.isNotEmpty(data.getProjectEndTime())) {
				// 得到工程竣工时间
				String time = data.getProjectEndTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程竣工年月日
				map.put("en", times[0].toString());
				map.put("ny", times[1].toString());
				map.put("nd", times[2].toString());

			} else {
				// 工程竣工年月日
				map.put("en", "");
				map.put("ny", "");
				map.put("nd", "");
			}
			// 工程建设资金
			map.put("buildMoney", data.getBuildMoney());
			// 工程到位资金
			map.put("alreadyMoney", data.getAlreadyMoney());
			// 工程实际完成投资金额
			map.put("actualCompeleteMoney", data.getActualCompeleteMoney());
			// 超过批复的工程概算总投资
			map.put("beyondMoney", data.getBeyondMoney());

			// 工程审定的工程直接费用
			map.put("auditZjMoney", data.getZjMoney());
			// 超施工合同价款
			map.put("beyondConstructMoney", "");
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.ideaNote.one"), map);
		}
		if ("2".equals(method)) {
			// 项目名称
			map.put("projectName", data.getProjectName());
			// 根据id查询项目业主
			ProjectOwner owner = iProjectOwnerService.getProjectOwner(data
					.getProownerid());
			if (null != owner) {
				// 项目业主
				map.put("ownerName", owner.getOwnerName());
				map.put("constructUtil", owner.getOwnerName());
			} else {
				// 项目业主
				map.put("ownerName", "");
				map.put("constructUtil", "");
			}
			// 工程审定的工程直接费用
			map.put("zjMoney", data.getZjMoney());
			// 超施工合同价款
			map.put("constructConstractMoney", "");
			// 工程合同工期
			map.put("contractDay", "");
			// 实际工期
			map.put("reallyContractDay", "");

			// 合同工期相比延期
			map.put("gapDay", "");
			filePath = auditDocumentComponent.writeWord(url, PropertiesGetValue
					.getContextProperty("audit.ideaNote.three"), map);
		}
		if ("1".equals(method)) {
			// 项目名称
			map.put("projectName", data.getProjectName());
			//
			map.put("projectCode", data.getDatapreno());
			// 根据id查询项目业主
			ProjectOwner owner = iProjectOwnerService.getProjectOwner(data
					.getProownerid());
			if (null != owner) {
				// 项目业主
				map.put("ownerName", owner.getOwnerName());
				map.put("constuctUtil", owner.getOwnerName());
			} else {
				// 项目业主
				map.put("ownerName", "");
				map.put("constuctUtil", "");
			}
			// 工程直接费用概算（万元）
			if (AuditStringUtils.isNotEmpty(data.getProjectDirectMoney())) {
				Double a = Double.parseDouble(data.getProjectDirectMoney()) / 10000.0;
				map.put("zjMoney", a.toString());
			} else {
				map.put("zjMoney", "");
			}
			if (AuditStringUtils.isNotEmpty(data.getProjectStartTime())) {
				// 得到工程开工时间
				String time = data.getProjectStartTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程开工年月日
				map.put("sn", times[0].toString());
				map.put("sy", times[1].toString());
				map.put("sd", times[2].toString());

			} else {
				// 工程开工年月日
				map.put("sn", "");
				map.put("sy", "");
				map.put("sd", "");
			}

			if (AuditStringUtils.isNotEmpty(data.getProjectEndTime())) {
				// 得到工程竣工时间
				String time = data.getProjectEndTime();
				// 分割工程开工时间的年月日
				time = AuditStringUtils.getDatetoyyyyMMdd(time);
				String[] times = time.split("-");
				// 工程竣工年月日
				map.put("en", times[0].toString());
				map.put("ny", times[1].toString());
				map.put("nd", times[2].toString());

			} else {
				// 工程竣工年月日
				map.put("en", "");
				map.put("ny", "");
				map.put("nd", "");
			}
			// 工程建设资金
			map.put("buildMoney", data.getBuildMoney());
			// 工程到位资金
			map.put("alreadyMoney", data.getAlreadyMoney());
			// 工程实际完成投资金额
			map.put("actualCompeleteMoney", data.getActualCompeleteMoney());
			// 超过批复的工程概算总投资
			map.put("beyondMoney", data.getBeyondMoney());

			// 工程审定的工程直接费用
			map.put("auditZjMoney", data.getZjMoney());
			// 超施工合同价款
			map.put("beyondConstructMoney", "");
			filePath = auditDocumentComponent.writeWord(url, "200万元以下简易报告.doc",
					map);
		}
		String fileName = ""; // 文件名，输出到用户的下载对话框
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("\\") + 1, filePath.length())
					.getBytes("GB2312"), "ISO8859_1");
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}

}
