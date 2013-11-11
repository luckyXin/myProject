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
import com.audit.entity.Function;
import com.audit.entity.User;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IFunctionInfoService;

/**
 * @author 邓鑫
 */
@Controller
@RequestMapping("system/Function")
public class FunctionController implements IControllerBase {

	@Autowired
	private ICompetenceService  competenceService;

	@Autowired
	private IFunctionInfoService iFunctionInfoService;

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
			request.setAttribute("url", "/system/Function/add.do");
			return "/system/functionEdit";
		} else if (update != null && !"".equals(update)) {

			// 获取更新的ID
			String key = request.getParameter("key");

			// 更新菜单旧内容
			request.setAttribute("function",
					iFunctionInfoService.getFunctionById(key));

			// 处理类别
			request.setAttribute("processType", "update");

			// 更新菜单旧内容
			request.setAttribute("id", id);

			// 处理地址
			request.setAttribute("url", "/system/Function/update.do");

			// 获取导航菜单
			return "/system/functionEdit";
		} else {
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id,
					userAccount);
			request.setAttribute("map", commonFunction);
			request.setAttribute("id", id);
			return "/system/functionIndex";
		}
	}

	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {

		String moduleId = request.getParameter("moduleId");

		String page = request.getParameter("page");

		String rows = request.getParameter("rows");

		String sort = request.getParameter("sort");

		String order = request.getParameter("order");

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0")
				? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		GridDataModel<Function> gridmodel = null;
		
		if (!AuditStringUtils.isNotEmpty(moduleId)) {
			gridmodel = iFunctionInfoService.find(intPage, pagesize, sort, order);
		} else {
			gridmodel = iFunctionInfoService.find(intPage, pagesize, sort, order, moduleId);
		}
		
		map.put("rows", gridmodel.getRows());
		
		map.put("total", gridmodel.getTotal());
		
		return map;
	}

	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String method = request.getParameter("method");
		String remark = request.getParameter("remark");
		String icon = request.getParameter("icon");
		try {
			return iFunctionInfoService.add(name, state, method, remark, icon);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String method = request.getParameter("method");
		String remark = request.getParameter("remark");
		String icon = request.getParameter("icon");
		String id = request.getParameter("id");
		try {
			return iFunctionInfoService.update(name, state, method, remark, icon,
					id);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		return iFunctionInfoService.delete(id);
	}

	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		String state = request.getParameter("state");
		String id = request.getParameter("id");
		return iFunctionInfoService.destory(id, state);
	}

}
