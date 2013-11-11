/**
 * 项目业主管理
 */
package com.audit.controller.staff;

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
import com.audit.entity.staff.Link;
import com.audit.entity.staff.ProjectOwner;
import com.audit.service.common.ICompetenceService;
import com.audit.service.staff.IProjectOwnerService;

/**
 * @author dengxin
 */
@Controller
@RequestMapping("staff/projectOwners")
public class ProjectOwnerController implements IControllerBase {

	@Autowired
	private ICompetenceService iCompetenceService;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	/**
	 * 页面跳转控制
	 */
	@RequestMapping("input")
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String id = request.getParameter("id");
		if (AuditStringUtils.isNotEmpty(add)) {

			// 处理类别
			request.setAttribute("processType", "add");

			// 菜单 ID
			request.setAttribute("id", id);

			// 处理地址
			request.setAttribute("url", "/staff/projectOwners/add.do");

			// 获取单位类型下拉内容
			request.setAttribute("unitTypes", iProjectOwnerService.getUnitType());

			return "/staff/staffProOwnersEdit";
		} else if (AuditStringUtils.isNotEmpty(update)) {

			// 获取更新的ID
			String key = request.getParameter("key");
			ProjectOwner projectOwner = iProjectOwnerService.getProjectOwner(key);
			request.setAttribute("projectOwner", projectOwner);

			// 获取单位类型下拉内容
			request.setAttribute("unitTypes", iProjectOwnerService.getUnitType());

			// 处理类别
			request.setAttribute("processType", "update");

			// 更新菜单旧内容
			request.setAttribute("id", id);

			// 获取联系人
			List<Link> links = iProjectOwnerService.getLinks(projectOwner.getId());

			// 第一联系人设定
			if (links.size() != 0) {
				request.setAttribute("firstLink", links.get(0));
				links.remove(0);
			} else {
				request.setAttribute("firstLink", new Link());
			}

			// 获取联系人
			request.setAttribute("links", links);

			// 处理地址
			request.setAttribute("url", "/staff/projectOwners/update.do");

			return "/staff/staffProOwnersEdit";
		} else {

			User user = (User) request.getSession().getAttribute("user");

			String userAccount = user.getUserAccount();

			List<String> commonFunction = iCompetenceService.find(id, userAccount);

			request.setAttribute("conmap", commonFunction);

			request.setAttribute("id", id);

			return "/staff/staffProOwnersIndex";
		}
	}

	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String projectOwnerName = request.getParameter("projectOwnerName");
		String state = request.getParameter("checkState");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		Map<String, Object> map = new HashMap<String, Object>();

		GridDataModel<ProjectOwner> ProjectOwners = iProjectOwnerService.find(page, pagesize, name, order,
				projectOwnerName, state);
		map.put("rows", ProjectOwners.getRows());
		map.put("total", ProjectOwners.getTotal());
		return map;
	}

	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {

		// 业主名称
		String ownerName = request.getParameter("ownerName");

		// 业主简称
		String ownerReferred = request.getParameter("ownerReferred");

		// 单位类型
		String unitType = request.getParameter("unitType");

		// 是否政府投资项目建设主要单位
		String isMainUnit = request.getParameter("isMainUnit");

		// 状态
		String state = request.getParameter("state");

		// 备注
		String remark = request.getParameter("remark");

		String[] linktellphones = request.getParameterValues("linktellphone");

		String[] linknames = request.getParameterValues("linkname");

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map = iProjectOwnerService.addProjectOwner(ownerName, ownerReferred, unitType, isMainUnit, state, remark,
					linknames, linktellphones);
		} catch (Exception e) {

			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {

		// 业主ID
		String ownerId = request.getParameter("ownerId");

		// 业主名称
		String ownerName = request.getParameter("ownerName");

		// 业主简称
		String ownerReferred = request.getParameter("ownerReferred");

		// 单位类型
		String unitType = request.getParameter("unitType");

		// 是否政府投资项目建设主要单位
		String isMainUnit = request.getParameter("isMainUnit");

		// 状态
		String state = request.getParameter("state");

		// 备注
		String remark = request.getParameter("remark");

		String[] linktellphones = request.getParameterValues("linktellphone");

		String[] linknames = request.getParameterValues("linkname");

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			map = iProjectOwnerService.updateProjectOwner(ownerId, ownerName, ownerReferred, unitType, isMainUnit,
					state, remark, linknames, linktellphones);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {

		// 获取业主ID 执行删除操作
		String ownerId = request.getParameter("ownerId");

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = iProjectOwnerService.deleteProjectOwner(ownerId);

		} catch (Exception e) {

			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}

		return map;
	}

	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 获取业主ID
		String ownerId = request.getParameter("ownerId");

		// 执行注销操作
		try {

			map = iProjectOwnerService.destroyProjectOwner(ownerId);

		} catch (Exception e) {

			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

}
