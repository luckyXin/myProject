package com.audit.controller.work;

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
import com.audit.entity.work.AuditState;
import com.audit.service.common.ICompetenceService;
import com.audit.service.work.IFlowStateStaffInfoService;

@Controller
@RequestMapping("work/FlowStateStaffInfo")
public class FlowStateStaffController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IFlowStateStaffInfoService iFlowStateStaffInfoService;

	/**
	 * 跳转index页面 2013-6-8
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String id = request.getParameter("id");
		if (AuditStringUtils.isNotEmpty(add)) {

			return "/work/flowStateStaffInfo";
		} else if (AuditStringUtils.isNotEmpty(update)) {

			return "/work/flowStateStaffInfo";
		} else {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/work/flowStateStaffInfo";
		}
	}

	/**
	 * 检索信息 2013-6-8
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
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

		GridDataModel<AuditState> gridmodel = null;
		try {
			gridmodel = iFlowStateStaffInfoService.find(intPage, pagesize, sort, order);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
			return map;
		}

		map.put("rows", gridmodel.getRows());

		map.put("total", gridmodel.getTotal());

		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-8
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-8
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-8
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-8
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

}
