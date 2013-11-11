package com.audit.controller.system;

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
import com.audit.entity.ComboboxJson;
import com.audit.entity.User;
import com.audit.entity.system.ModuleResult;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IMenuInfoService;
import com.audit.service.system.IModuleInfoService;

/**
 * @author 邓鑫
 */
@Controller
@RequestMapping("system/Module")
public class ModuleController implements IControllerBase {

	@Autowired
	private ICompetenceService  competenceService;

	@Autowired
	private IModuleInfoService iModuleInfoService;

	@Autowired
	private IMenuInfoService iMenuInfoService;

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
			request.setAttribute("url", "/system/Module/add.do");
			// 获取顶层菜单
			request.setAttribute("menus", iMenuInfoService.getTopMenu());
			return "/system/moduleEdit";
		} else if (update != null && !"".equals(update)) {
			// 处理类别
			request.setAttribute("processType", "update");
			// 菜单 ID
			request.setAttribute("id", id);
			// 处理地址
			request.setAttribute("url", "/system/Module/update.do");

			// 获取更新mouduleId
			String mouduleId = request.getParameter("key");

			// 获取模块信息
			ModuleResult module = iModuleInfoService.getModulById(mouduleId);
			request.setAttribute("module", module);

			// 获取顶层菜单
			request.setAttribute("topMenus", iMenuInfoService.getTopMenu());

			// 获取子级菜单
			request.setAttribute("subMenus",
					iModuleInfoService.getModelMenus(module.getTopMenuId()));

			return "/system/moduleEdit";
		} else {
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id,
					userAccount);
			request.setAttribute("menuMap", commonFunction);
			request.setAttribute("id", id);
			return "/system/moduleIndex";
		}
	}

	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String moduleName = request.getParameter("moduleName");

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0")
				? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0")
				? "2"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();

		GridDataModel<ModuleResult> gridmodel = iModuleInfoService.findModule(
				intPage, pagesize, sort, order, moduleName);

		map.put("rows", gridmodel.getRows());

		map.put("total", gridmodel.getTotal());

		return map;
	}

	/**
	 * 获取子菜单的下拉框内容
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getSubMenu")
	@ResponseBody
	public List<ComboboxJson> getSubMenu(HttpServletRequest request) {
		String topMenuId = request.getParameter("topMenuId");
		return iModuleInfoService.getModelMenus(topMenuId);
	}

	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String moduleName = request.getParameter("moduleName");
		String state = request.getParameter("state");
		String menuId = request.getParameter("subMenu");
		String url = request.getParameter("url");
		String remark = request.getParameter("remark");
		try {
			return iModuleInfoService.add(moduleName, state, menuId, url, remark);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String functionId = request.getParameter("functionId");
		String moduleName = request.getParameter("moduleName");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		String subMenu = request.getParameter("subMenu");
		String url = request.getParameter("url");
		String moduleId = request.getParameter("moduleId");

		try {
		if (AuditStringUtils.isNotEmpty(functionId)) {
			return iModuleInfoService.impower(moduleId, functionId);
		}
			return iModuleInfoService.update(moduleName, state, remark, subMenu, url, moduleId);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String moduleId = request.getParameter("id");
		String state = request.getParameter("state");
		try {
			return iModuleInfoService.destroy(moduleId, state);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String moduleId = request.getParameter("id");
		try {
			return iModuleInfoService.delete(moduleId);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}
}
