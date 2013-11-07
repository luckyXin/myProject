package com.audit.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.ComboboxJson;
import com.audit.entity.Function;
import com.audit.entity.Menu;
import com.audit.entity.Role;
import com.audit.entity.User;
import com.audit.entity.system.ModuleResult;
import com.audit.entity.system.TreeMenuResult;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IMenuInfoService;
import com.audit.service.system.IModuleInfoService;
import com.audit.service.system.IRoleInfoService;

/**
 * @author 邓鑫
 */
@Controller
@RequestMapping("system/Role")
public class RoleController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IRoleInfoService iRoleInfoService;

	@Autowired
	private IMenuInfoService iMenuInfoService;

	@Autowired
	private IModuleInfoService iModuleInfoService;

	@RequestMapping("input")
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		if (add != null && !"".equals(add)) {

			// 处理类别
			request.setAttribute("processType", "add");

			// 菜单 ID
			request.setAttribute("id", id);

			// 处理地址
			request.setAttribute("url", "/system/Role/add.do");

			return "/system/roleEdit";
		} else if (update != null && !"".equals(update)) {

			// 处理类别
			request.setAttribute("processType", "update");

			// 获取角色基本信息
			String key = request.getParameter("key");
			Role role = iRoleInfoService.getRoleInfoById(key);
			request.setAttribute("role", role);

			// 菜单 ID
			request.setAttribute("id", id);

			// 获取导航菜单
			request.setAttribute("topMenus", iMenuInfoService.getTopMenu());

			// 处理地址
			request.setAttribute("url", "/system/Role/update.do");

			return "/system/roleEdit";
		} else {
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("roleMap", commonFunction);
			request.setAttribute("id", id);
			return "/system/roleIndex";
		}
	}

	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String proccessType = request.getParameter("proccessType");
		String roleId = request.getParameter("roleId");
		String topMenuId = request.getParameter("topMenuId");
		String subMenuId = request.getParameter("subMenuId");
		String moduleId = request.getParameter("moduleId");

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);

		Map<String, Object> map = new HashMap<String, Object>();

		if (proccessType == null || "".equals(proccessType)) {

			// 获取角色信息
			GridDataModel<Role> roles = iRoleInfoService.find(intPage, pagesize, sort, order);

			map.put("rows", roles.getRows());

			map.put("total", roles.getTotal());

		} else if ("getTopMenu".equals(proccessType)) {

			// 获取顶层菜单信息
			GridDataModel<Menu> topMenus = iRoleInfoService.findTopMenu(intPage, pagesize, sort, order, roleId);

			map.put("rows", topMenus.getRows());

			map.put("total", topMenus.getTotal());

		} else if ("getSubMenu".equals(proccessType)) {

			// 获取子级菜单信息
			GridDataModel<Menu> topMenus = iRoleInfoService.findSubMenu(intPage, pagesize, sort, order, topMenuId,
					roleId);

			map.put("rows", topMenus.getRows());

			map.put("total", topMenus.getTotal());

		} else if ("getModuleMenu".equals(proccessType)) {

			// 获取模块菜单信息
			GridDataModel<ModuleResult> modules = iRoleInfoService.findModule(intPage, pagesize, sort, order,
					subMenuId, roleId);

			map.put("rows", modules.getRows());

			map.put("total", modules.getTotal());

		} else if ("getFunctionMenu".equals(proccessType)) {

			// 获取方法信息
			GridDataModel<Function> functions = iRoleInfoService.findFunction(intPage, pagesize, sort, order, moduleId,
					roleId);

			map.put("rows", functions.getRows());

			map.put("total", functions.getTotal());
		} else if ("getTreeMenu".equals(proccessType)) {
			// 获取权限菜单树状图
			GridDataModel<TreeMenuResult> treeMenuResult = iRoleInfoService.getTreeMenu(intPage, pagesize, sort, order,
					roleId);
			map.put("rows", treeMenuResult.getRows());

			map.put("total", treeMenuResult.getTotal());
		}
		return map;
	}

	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String roleName = request.getParameter("roleName");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		try {
			return iRoleInfoService.add(roleName, state, remark);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String proccessType = request.getParameter("proccessType");
		String topMenuId = request.getParameter("topMenuId");
		String impower = request.getParameter("impower");
		String roleId = request.getParameter("roleId");
		String subMenuId = request.getParameter("subMenuId");
		String moduleId = request.getParameter("moduleId");
		String functionId = request.getParameter("functionId");
		String roleName = request.getParameter("rolename");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		try {
			if ("updateTopMenu".equals(proccessType)) {
				return iRoleInfoService.updateImpowerByTopMenuId(topMenuId, roleId, impower);
			} else if ("updateSubMenu".equals(proccessType)) {

				return iRoleInfoService.updateImpowerBySubMenuId(subMenuId, roleId, impower);
			} else if ("updateModuleMenu".equals(proccessType)) {

				return iRoleInfoService.updateImpowerByModuleId(moduleId, roleId, impower);
			} else if ("updateFunction".equals(proccessType)) {
				return iRoleInfoService.updateImpowerByFunctionId(functionId, roleId, impower, moduleId);
			}
			return iRoleInfoService.updateRoleInfo(roleId, roleName, state, remark);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			return iRoleInfoService.delete(id);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		try {
			return iRoleInfoService.destroy(id, state);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("getModule")
	@ResponseBody
	public List<ComboboxJson> getModule(HttpServletRequest request) {
		String subMenuId = request.getParameter("subMenuId");
		return iModuleInfoService.getModules(subMenuId);
	}
}
