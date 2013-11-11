/**
 * 雇员审计项目综合查询
 */
package com.audit.controller.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.Capitalexpenditures;
import com.audit.entity.project.MainAuditInfo;
import com.audit.entity.project.Problems;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.entity.search.SearchProjectInfoResult;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IMainEmployeeAuditService;
import com.audit.service.project.ISectionChiefAuditService;
import com.audit.service.search.IAllProjectSearchService;

/**
 * @author DengXin
 * 
 */
@Controller
@RequestMapping("/search/employeeAudit")
public class EmployeeAuditSearchController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IAllProjectSearchService iAllProjectSearchService;

	@Autowired
	private IMainEmployeeAuditService mainEmployeeAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String id = request.getParameter("id");
		if (AuditStringUtils.isNotEmpty(add)) {
			String projectId = request.getParameter("key");

			// 获取子项目的基本信息
			SectionChiefAuditInfo sectionChiefAuditInfo = mainEmployeeAuditService
					.getProjectMainEmployeeAuditInfo(projectId);

			// 中介审核
			request.setAttribute("intermediaryAudit", sectionChiefAuditInfo.getIntermediaryAudit());

			// 政府雇员审核
			request.setAttribute("govermentEmployeeAudit", sectionChiefAuditInfo.getGovermentEmployeeAudit());

			ResultClassArrangeInfo resultClassArrangeInfo = intermediaryAuditService.findpackauditbyid(projectId);
			if (resultClassArrangeInfo == null) {
				resultClassArrangeInfo = intermediaryAuditService.findsingleauditbyid(projectId);
			}
			request.setAttribute("projectInfo", resultClassArrangeInfo);
			
			if (resultClassArrangeInfo != null) {
				// 查询科长审核的意见
				ResultAuditInfo kezhang = sectionChiefAuditService.findauditinfo(resultClassArrangeInfo.getArrangeId(), "1");
				request.setAttribute("kezhang", kezhang);

				// 查询法制科长审核意见
				ResultAuditInfo fazhikezhang = sectionChiefAuditService.findauditinfo(resultClassArrangeInfo.getArrangeId(), "2");
				request.setAttribute("fazhikezhang", fazhikezhang);

				// 查询法制分管审核信息
				ResultAuditInfo fazhifenguan = sectionChiefAuditService.findauditinfo(resultClassArrangeInfo.getArrangeId(), "3");
				request.setAttribute("fazhifenguan", fazhifenguan);

				// 查询分管领导审核信息
				ResultAuditInfo fenguan = sectionChiefAuditService.findauditinfo(resultClassArrangeInfo.getArrangeId(), "4");
				request.setAttribute("fenguan", fenguan);
			}
			// 存在审计信息的场合，获取审计信息
			MainAuditInfo mainAuditInfo = mainEmployeeAuditService.getMainAuditInfo(projectId);
			request.setAttribute("mainAuditInfo",mainAuditInfo );
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
			return "/search/auditProjectInfoShow";
		} else {
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/search/employeeAuditSearch";
		}
	}

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String auditStartTime = request.getParameter("auditStartTime");
		String auditEndTime = request.getParameter("auditEndTime");
		String employeeName = request.getParameter("employeeName");
		String auditIdentity = request.getParameter("auditIdentity");
		String intermediary = request.getParameter("intermediary");
		String construction = request.getParameter("construction");
		String buildYear = request.getParameter("buildYear");
		String projectOwner = request.getParameter("projectOwner");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		GridDataModel<SearchProjectInfoResult> searchProjectInfoResult = iAllProjectSearchService.findPackSubProject(
				page, pagesize, name, order, auditStartTime, auditEndTime, employeeName, auditIdentity, intermediary,
				construction, buildYear, projectOwner);
		map.put("rows", searchProjectInfoResult.getRows());
		map.put("total", searchProjectInfoResult.getTotal());
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
