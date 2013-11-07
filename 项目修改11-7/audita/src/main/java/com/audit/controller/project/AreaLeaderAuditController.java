/**
 * 分管领导复核审批
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
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IAreaLeaderAuditService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.ISectionChiefAuditService;

/**
 * @author DengXin
 * 
 */
@Controller
@RequestMapping("/project/AreaLeaderAudit")
public class AreaLeaderAuditController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IAreaLeaderAuditService areaLeaderAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	/**
	 * 分管领导审批页面跳转 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String legalState = request.getParameter("legalState");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("moduelId", id);

		if (AuditStringUtils.isNotEmpty(add) || AuditStringUtils.isNotEmpty(legalState)) {

			request.setAttribute("processType", "add");

			String projectId = request.getParameter("key");

			// 编辑滞留法制科的项目
			if (AuditStringUtils.isNotEmpty(legalState)) {
				try {
					areaLeaderAuditService.jumpLegalRetentionProejct(projectId, user.getUserAccount());
				} catch (Exception e) {
					e.printStackTrace();
					return "/login/exception";
				}
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
			SectionChiefAuditInfo sectionChiefAuditInfo = areaLeaderAuditService.getSectionChiefAuditInfo(projectId);
			if (sectionChiefAuditInfo.getIntermediaryAudit() != null) {
				// 中介审核
				request.setAttribute("intermediaryAudit", sectionChiefAuditInfo.getIntermediaryAudit());
			}
			if (sectionChiefAuditInfo.getGovermentEmployeeAudit() != null) {
				// 复核工程师审核
				request.setAttribute("govermentEmployeeAudit", sectionChiefAuditInfo.getGovermentEmployeeAudit());
			}
			request.setAttribute("url", "/project/AreaLeaderAudit/add.do");

			request.setAttribute("areaLeaderAuditBaseInfo",
					areaLeaderAuditService.getSectionChiefAuditBaseInfo(projectId, user.getUserAccount()));

			// 查询科长审核的意见
			ResultAuditInfo kezhang = sectionChiefAuditService.findauditinfo(projectId, "1");
			request.setAttribute("kezhang", kezhang);
			// 查询法制科长审核意见
			ResultAuditInfo fazhikezhang = sectionChiefAuditService.findauditinfo(projectId, "2");
			request.setAttribute("fazhikezhang", fazhikezhang);
			// 查询法制分管审核信息
			ResultAuditInfo fazhifenguan = sectionChiefAuditService.findauditinfo(projectId, "3");
			request.setAttribute("fazhifenguan", fazhifenguan);
			return "/project/areaLeaderAuditEdit";
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
			return "/project/areaLeaderAuditIndex";
		}
	}

	/**
	 * 审批项目信息检索 2013-6-28
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
		String legalState = request.getParameter("legalState");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		GridDataModel<AuditInfo> auditInfo = areaLeaderAuditService.find(page, pagesize, name, order, projectName,
				ownerName, arrangeType, auditType, userAccount, moduleId, legalState);
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
	public Map<String, Object> audit(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-28
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		String projectId = request.getParameter("projectId");
		String remark = request.getParameter("remark");
		String submitState = request.getParameter("submitState");
		String passState = request.getParameter("passState");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			return areaLeaderAuditService.audit(projectId, remark, submitState, passState, userAccount);
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
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		String auditId = request.getParameter("auditId");
		String projectId = request.getParameter("projectId");
		String remark = request.getParameter("remark");
		String submitState = request.getParameter("submitState");
		String passState = request.getParameter("passState");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			return areaLeaderAuditService.auditUpdate(auditId, projectId, remark, submitState, passState, userAccount);
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
