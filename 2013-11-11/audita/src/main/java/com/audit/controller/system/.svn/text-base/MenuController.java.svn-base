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
import com.audit.entity.Menu;
import com.audit.entity.User;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IMenuInfoService;

/**
 * @author 邓鑫
 */
@Controller
@RequestMapping("system/Menu")
public class MenuController implements IControllerBase {

	@Autowired
	private IMenuInfoService iMenuInfoService;

	@Autowired
	private ICompetenceService competenceService;

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
			request.setAttribute("url", "/system/Menu/add.do");
			// 获取导航菜单
			request.setAttribute("topMenus", iMenuInfoService.getTopMenu());
			return "/system/menuEdit";
		} else if (update != null && !"".equals(update)) {

			// 获取更新的ID
			String key = request.getParameter("key");
			Menu menu = iMenuInfoService.getMenu(key);
			// 更新菜单旧内容
			request.setAttribute("menu", menu);
			// 处理类别
			request.setAttribute("processType", "update");
			// 更新菜单旧内容
			request.setAttribute("id", id);
			// 处理地址
			request.setAttribute("url", "/system/Menu/update.do");
			// 获取导航菜单
			request.setAttribute("topMenus", iMenuInfoService.getTopMenu());
			return "/system/menuEdit";
		} else {
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("menuMap", commonFunction);
			request.setAttribute("id", id);
			return "/system/menuIndex";
		}
	}

	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<Menu> gridmodel = iMenuInfoService.findSubMenu(intPage, pagesize, sort, order);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String menuName = request.getParameter("menuName");
		String pid = request.getParameter("pid");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		String sort = request.getParameter("sort");
		try {
			map = iMenuInfoService.add(menuName, state, sort, remark, pid);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String menuId = request.getParameter("menuId");
		String menuName = request.getParameter("menuName");
		String pid = request.getParameter("pid");
		String state = request.getParameter("state");
		String remark = request.getParameter("remark");
		String sort = request.getParameter("sort");
		try {
			map = iMenuInfoService.update(menuName, state, sort, remark, pid, menuId);
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
		String state = request.getParameter("state");
		Menu menu = new Menu();
		menu.setState(state);
		menu.setId(id);
		try {
			map = iMenuInfoService.destroy(menu);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (iMenuInfoService.delete(id)) {
				map.put("msg", PropertiesGetValue.getContextProperty("daoHang.delete.success"));
			} else {
				map.put("msg", PropertiesGetValue.getContextProperty("daoHang.delete.fail"));
			}
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}
}
