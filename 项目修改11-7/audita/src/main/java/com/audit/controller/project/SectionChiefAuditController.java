/**
 * 科长复核审批
 */
package com.audit.controller.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.ISectionChiefAuditService;

/**
 * @author dengxin
 * 
 */
@Controller
@RequestMapping("/project/SectionChiefAudit")
public class SectionChiefAuditController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	/**
	 * 科长审批页面跳转 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("moduelId", id);
		if (AuditStringUtils.isNotEmpty(add)) {

			request.setAttribute("processType", "add");

			String projectId = request.getParameter("key");

			// 获取法制科长和分管领导审批信息
			SectionChiefAuditBaseInfo leaderAuditBaseInfo = sectionChiefAuditService
					.getLowSectionChiefAuditBaseInfo(projectId);

			request.setAttribute("leaderAuditBaseInfo", leaderAuditBaseInfo);

			// 获取科长审批信息
			SectionChiefAuditBaseInfo sectionChiefAuditBaseInfo = sectionChiefAuditService
					.getSectionChiefAuditBaseInfo(projectId, user.getUserAccount());
			if (sectionChiefAuditBaseInfo != null) {
				request.setAttribute("sectionChiefAuditBaseInfo", sectionChiefAuditBaseInfo);
			}
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

			if (sectionChiefAuditInfo.getIntermediaryAudit() != null) {
				// 中介审核
				request.setAttribute("intermediaryAudit", sectionChiefAuditInfo.getIntermediaryAudit());
			}
			if (sectionChiefAuditInfo.getGovermentEmployeeAudit() != null) {
				// 政府雇员审核
				request.setAttribute("govermentEmployeeAudit", sectionChiefAuditInfo.getGovermentEmployeeAudit());
			}

			request.setAttribute("url", "/project/SectionChiefAudit/audit.do");
			if (AuditStringUtils.equals(PropertiesGetValue.getContextProperty("sectionChiefAudit.moduleId"), id)) {
				return "/project/sectionChiefAuditInfoEdit";
			}
			return "/project/sectionChiefAuditEdit";
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
			if (AuditStringUtils.equals(PropertiesGetValue.getContextProperty("sectionChiefAudit.moduleId"), id)) {
				return "/project/sectionChiefAuditInfoIndex";
			}
			return "/project/sectionChiefAuditIndex";
		}
	}

	/**
	 * 审批项目信息检索 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@ResponseBody
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
		GridDataModel<AuditInfo> auditInfo = sectionChiefAuditService.find(page, pagesize, name, order, projectName,
				ownerName, arrangeType, auditType, userAccount, moduleId, "2", sectionChiefAuditProjectType);
		map.put("rows", auditInfo.getRows());
		map.put("total", auditInfo.getTotal());
		return map;
	}

	/**
	 * 科长审批操作
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/audit")
	@ResponseBody
	public Map<String, Object> audit(HttpServletRequest request) {
		String projectId = request.getParameter("projectId");
		String remark = request.getParameter("remark");
		String submitState = request.getParameter("submitState");
		String passState = request.getParameter("passState");
		String auditType = request.getParameter("auditType");
		String intermediaryAuditId = request.getParameter("intermediaryAuditId");
		String governmentAuditId = request.getParameter("governmentAuditId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(governmentAuditId)) {
				return sectionChiefAuditService.audit(projectId, governmentAuditId, remark, submitState, passState,
						userAccount, auditType);
			} else {
				return sectionChiefAuditService.audit(projectId, intermediaryAuditId, remark, submitState, passState,
						userAccount, auditType);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		String auditId = request.getParameter("auditId");
		String projectId = request.getParameter("projectId");
		String remark = request.getParameter("remark");
		String submitState = request.getParameter("submitState");
		String passState = request.getParameter("passState");
		String auditType = request.getParameter("auditType");
		String intermediaryAuditId = request.getParameter("intermediaryAuditId");
		String governmentAuditId = request.getParameter("governmentAuditId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(governmentAuditId)) {
				return sectionChiefAuditService.auditUpdate(auditId, projectId, governmentAuditId, remark, submitState,
						passState, userAccount, auditType);
			} else {
				return sectionChiefAuditService.auditUpdate(auditId, projectId, intermediaryAuditId, remark,
						submitState, passState, userAccount, auditType);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
