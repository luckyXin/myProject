/**
 * 分管领导确认安排信息
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
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IAreaLeaderAuditService;
import com.audit.service.project.IIntermediaryAuditService;

/**
 * @author DengXin
 */
@Controller
@RequestMapping("/project/ArrangeAffirm")
public class AreaLeaderArrangeInfoAffirmController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IAreaLeaderAuditService areaLeaderAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	/**
	 * 页面跳转控制 2013-7-16
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String update = request.getParameter("update");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("moduelId", id);
		if (AuditStringUtils.isNotEmpty(update)) {

			// 检索安排信息
			String projectId = request.getParameter("key");

			ResultClassArrangeInfo project = null;
			// 查询单项目
			project = intermediaryAuditService.findbyid(projectId);
			// 如果单项目没有查询到查询打包项目
			if (null == project) {
				// 查询打包项目
				project = intermediaryAuditService.findpackbyid(projectId);
			} else {
				request.setAttribute("datareId", project.getDatapreId());
			}

			// 项目基本信息
			request.setAttribute("projectInfo", project);

			// 获取安排信息（是否加急，限时）
			request.setAttribute("arrangeInfo", areaLeaderAuditService.getArrangeInfo(projectId));

			return "/project/areaLeaderArrageInfoAffirmEdit";
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

			return "/project/areaLeaderArrangeInfoAffirmIndex";
		}
	}

	/**
	 * 查询待分管领导确认的信息 2013-7-16
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
		User user = (User) request.getSession().getAttribute("user");
		String moduleId = request.getParameter("moduleId");
		String arrangeType = request.getParameter("arrangeType");
		String auditType = request.getParameter("auditType");
		String ownerName = request.getParameter("ownerName");
		String projectName = request.getParameter("projectName");
		String userAccount = user.getUserAccount();
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		GridDataModel<AuditInfo> auditInfo = areaLeaderAuditService.find(page, pagesize, name, order, userAccount,
				moduleId, arrangeType, auditType, ownerName, projectName);
		map.put("rows", auditInfo.getRows());
		map.put("total", auditInfo.getTotal());
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-16
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		return null;
	}

	/**
	 * 更新安排信息（是否加急，限时） 2013-7-16
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String arrangeId = request.getParameter("arrangeId");
		String isUrgent = request.getParameter("isUrgent");
		String timeLimit = request.getParameter("timeLimit");
		String areaauditRemark=request.getParameter("areaauditRemark");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		try {
			// 更新安排信息
			return areaLeaderAuditService.updateArrangeInfo(arrangeId, isUrgent, timeLimit, userAccount,areaauditRemark);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-16
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-16
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
