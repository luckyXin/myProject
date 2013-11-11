/**
 * 法制科室签审管理
 */
package com.audit.controller.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.Capitalexpenditures;
import com.audit.entity.project.LegalDepartAuditView;
import com.audit.entity.project.MainAuditInfo;
import com.audit.entity.project.Problems;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IMainEmployeeAuditService;
import com.audit.service.project.ISectionChiefAuditService;

/**
 * @author User
 * 
 */
@Controller
@RequestMapping("/project/legalAudit")
public class LegalDepartViewAuditController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	@Autowired
	private IMainEmployeeAuditService mainEmployeeAuditService;

	/**
	 * (non-Javadoc) 2013-8-15
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		// 获取模块id
		String id = request.getParameter("id");

		if (AuditStringUtils.isNotEmpty(add)) {

			String projectId = request.getParameter("key");

			ResultClassArrangeInfo project = null;
			Integer protype = 0;
			// 查询单项目
			project = intermediaryAuditService.findbyid(projectId);
			// 如果单项目没有查询到查询打包项目
			if (null == project) {
				// 查询打包项目
				project = intermediaryAuditService.findpackbyid(projectId);
				protype = 1;
			} else {
				request.setAttribute("datareId", project.getDatapreId());
			}
			// 项目基本信息
			request.setAttribute("projectInfo", project);
			// 保存项目类型
			request.setAttribute("protype", protype);

			SectionChiefAuditInfo sectionChiefAuditInfo = sectionChiefAuditService.getSectionChiefAuditInfo(projectId);

			if (sectionChiefAuditInfo.getGovermentEmployeeAudit() == null
					&& sectionChiefAuditInfo.getIntermediaryAudit() != null) {
				// 中介审核
				request.setAttribute("intermediaryAudit", sectionChiefAuditInfo.getIntermediaryAudit());
			} else {
				// 中介审核
				request.setAttribute("intermediaryAudit", sectionChiefAuditInfo.getIntermediaryAudit());
				// 政府雇员审核
				request.setAttribute("govermentEmployeeAudit", sectionChiefAuditInfo.getGovermentEmployeeAudit());
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

			// 法制科室签审意见
			LegalDepartAuditView legalDepartAuditView = mainEmployeeAuditService.getLegalDepartAuditView(projectId);
			request.setAttribute("legalDepartAuditView", legalDepartAuditView);

			return "/project/legalAuditEdit";
		} else {
			String useraccount = "";
			request.getSession().setAttribute("moduelId", id);
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			String isArrange = request.getParameter("key");
			if (AuditStringUtils.isNotEmpty(isArrange)) {
				request.setAttribute("isAudit", isArrange);
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.setAttribute("commonFunction", cf);
			return "/project/legalAuditIndex";
		}
	}

	/**
	 * (non-Javadoc) 2013-8-15
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
		String ownerName = request.getParameter("ownerName");
		String projectName = request.getParameter("projectName");
		String arrangeType = request.getParameter("arrangeType");
		String auditType = request.getParameter("auditType");
		String moduleId = request.getParameter("moduleId");
		String sectionChiefAuditProjectType = request.getParameter("sectionChiefAuditProjectType");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		GridDataModel<AuditInfo> auditInfo = new GridDataModel<AuditInfo>();
		if (AuditStringUtils.equals("0", auditType)) {
			auditInfo = sectionChiefAuditService.find(page, pagesize, name, order, projectName, ownerName, arrangeType,
					auditType, userAccount, moduleId, "2", sectionChiefAuditProjectType);
		} else {
			auditInfo = mainEmployeeAuditService.findLegalAlreadyAudit(page, pagesize, name, order, projectName,
					ownerName, userAccount);
		}
		map.put("rows", auditInfo.getRows());
		map.put("total", auditInfo.getTotal());
		return map;
	}

	/**
	 * 添加投资科室审签意见 2013-8-15
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String mainAuditId = request.getParameter("mainAuditId");
		String investLeaderAuditRemark = request.getParameter("investLeaderAuditRemark");
		String isInvestLeaderAudit = request.getParameter("isInvestLeaderAudit");
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		try {
			return mainEmployeeAuditService.updateLegalDepartAuditView(mainAuditId, investLeaderAuditRemark,
					isInvestLeaderAudit, userAccount);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-8-15
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-8-15
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-8-15
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

}
