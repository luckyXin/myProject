/**
 * 法制科长复核审批
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
import com.audit.entity.project.LegalSectionChiefAuditBaseInfo;
import com.audit.entity.project.LegalSectionChiefAuditInfo;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.ILegalSectionChiefAuditService;
import com.audit.service.project.ISectionChiefAuditService;

/**
 * @author dengyong
 */
@Controller
@RequestMapping("/project/legalSectionChiefAudit")
public class ProLegalSectionChiefAuditController implements IControllerBase {
	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ILegalSectionChiefAuditService legalSectionChiefAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	/**
	 * 科长审批页面跳转 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String intermediary = request.getParameter("intermediary");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("moduelId", id);
		if (AuditStringUtils.isNotEmpty(intermediary)) {
			request.setAttribute("processType", "intermediary");
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
			}
			// 项目基本信息
			request.setAttribute("projectInfo", project);

			// 保存项目类型
			request.setAttribute("protype", protype);

			// 获取信息
			request.setAttribute("arrangeInfo", legalSectionChiefAuditService.getArrangeProject(projectId));

			return "/project/legalsectionChiefAuditAddIntermediary";
		} else if (AuditStringUtils.isNotEmpty(add)) {

			request.setAttribute("processType", "add");

			String projectId = request.getParameter("key");

			// 获取法制科长审批信息
			LegalSectionChiefAuditBaseInfo legalsectionChiefAuditBaseInfo = legalSectionChiefAuditService
					.getlegalSectionChiefAuditBaseInfo(projectId, user.getUserAccount());
			if (legalsectionChiefAuditBaseInfo != null) {
				request.setAttribute("legalsectionChiefAuditBaseInfo", legalsectionChiefAuditBaseInfo);
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
			// 查询法制科长信息
			LegalSectionChiefAuditInfo sectionChiefAuditInfo = legalSectionChiefAuditService
					.getSlegalectionChiefAuditInfo(projectId);
			if (sectionChiefAuditInfo.getIntermediaryAudit() != null) {
				// 中介审核
				request.setAttribute("intermediaryAudit", sectionChiefAuditInfo.getIntermediaryAudit());
			}
			if (sectionChiefAuditInfo.getGovermentEmployeeAudit() != null) {
				// 政府雇员审核
				request.setAttribute("govermentEmployeeAudit", sectionChiefAuditInfo.getGovermentEmployeeAudit());
			}
			request.setAttribute("url", "/project/legalSectionChiefAudit/audit.do");
			// 查询科长审核的意见
			ResultAuditInfo kezhang = sectionChiefAuditService.findauditinfo(projectId, "1");
			request.setAttribute("kezhang", kezhang);

			return "/project/legalsectionChiefAuditEdit";
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
			return "/project/LegalSectionChiefAudit";
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
		String projectType = request.getParameter("projectType");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		GridDataModel<AuditInfo> auditInfo = legalSectionChiefAuditService.find(page, pagesize, name, order,
				projectName, ownerName, arrangeType, auditType, userAccount, moduleId, projectType);
		map.put("rows", auditInfo.getRows());
		map.put("total", auditInfo.getTotal());
		return map;
	}

	/**
	 * 法制科长审批操作
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
		String intermediary = request.getParameter("intermediary");
		String projectArrangeTime = request.getParameter("projectArrangeTime");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(governmentAuditId)) {
				return legalSectionChiefAuditService.audit(projectId, governmentAuditId, remark, submitState,
						passState, userAccount, auditType);
			} else if (AuditStringUtils.isNotEmpty(intermediary)) {
				return legalSectionChiefAuditService
						.updateIntermediaryInfo(projectId, intermediary, projectArrangeTime);
			} else {
				return legalSectionChiefAuditService.audit(projectId, intermediaryAuditId, remark, submitState,
						passState, userAccount, auditType);
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
	 * 法制科长修改审核
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
				return legalSectionChiefAuditService.auditUpdate(auditId, projectId, governmentAuditId, remark,
						submitState, passState, userAccount, auditType);
			} else {
				return legalSectionChiefAuditService.auditUpdate(auditId, projectId, intermediaryAuditId, remark,
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
