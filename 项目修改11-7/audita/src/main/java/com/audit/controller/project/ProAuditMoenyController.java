/**
 * 审计费用控制层
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

import com.audit.common.GridDataModel;
import com.audit.entity.User;
import com.audit.entity.project.ResultClassProAuditMoney;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IProAuditMoenyService;

/**
 * @author dengyong 
 * 
 */
@Controller
@RequestMapping("/project/proAuditMoeny")
public class ProAuditMoenyController {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IProAuditMoenyService proAuditMoenyService;

	/**
	 * 查询审计费用
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String projectName = request.getParameter("projectName");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ResultClassProAuditMoney> gridmodel = proAuditMoenyService.find(intPage, pagesize, sort, order,
				projectName);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 
	 * 跳转入口
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("proauditmoduelId", id);
		String useraccount = "";
		if (null != user) {
			useraccount = user.getUserAccount();
		}
		List<String> cf = competenceService.find(id, useraccount);
		request.setAttribute("auditmoneymap", cf);
		return "/project/ProAuditMoenyIndex";
	}
}
