/**
 * 会议记录项目查询控制层
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

import com.audit.entity.User;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.ProConferenceinfo;

import com.audit.entity.project.ResultClassProConference;
import com.audit.entity.staff.ProjectOwner;

import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IProConferenceService;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.staff.IProjectOwnerService;

/**
 * @author dengyong
 */
@Controller
@RequestMapping("/project/proConference")
public class ProConferenceController {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IProConferenceService proConferenceService;

	@Autowired
	private IProjectDatePreService projectDatePreService;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	/**
	 * 项目资料信息查询
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String projectName = request.getParameter("projectName");
		String proownerid = request.getParameter("proownerid");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			GridDataModel<ResultClassProConference> gridmodel = proConferenceService.find(intPage, pagesize, sort,
					order, projectName, proownerid);
			map.put("rows", gridmodel.getRows());
			map.put("total", gridmodel.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 会议纪要查询
	 */
	@RequestMapping("/findlist")
	@ResponseBody
	public Map<String, Object> findlist(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String datapreId = request.getParameter("datapreId");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			GridDataModel<ProConferenceinfo> gridmodel = proConferenceService.findlist(intPage, pagesize, sort, order,
					datapreId);
			map.put("rows", gridmodel.getRows());
			map.put("total", gridmodel.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * 跳转入口
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		String lookconference = request.getParameter("lookconference");
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("proconferencemoduelId", id);
		// 增加
		if (AuditStringUtils.isNotEmpty(add)) {
			String key = request.getParameter("key");
			request.setAttribute("datapreIds", key);
			return "/project/ProConferenceAdd";
		}
		// 编辑
		else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			ProConferenceinfo conference = proConferenceService.findbyid(key);
			request.setAttribute("conference", conference);
			return "/project/ProConferenceEdit";
		}

		// 查询会议纪要录入
		else if (AuditStringUtils.isNotEmpty(lookconference)) {
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			String module = request.getParameter("id");
			request.setAttribute("lookconference", module);
			List<String> cf = competenceService.find(module.split("-")[0].toString(), useraccount);
			request.setAttribute("conferencdataemap", cf);
			// 获取key
			String key = request.getParameter("key");
			Datapreinfo data = projectDatePreService.findtoid(key);
			if (AuditStringUtils.isNotEmpty(data.getProownerid())) {
				ProjectOwner p = iProjectOwnerService.getProjectOwner(data.getProownerid());
				data.setProownerid(p.getOwnerName());
			}
			request.setAttribute("data", data);
			return "/project/ProConferenceIndex";

		} else {
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.setAttribute("conferencemap", cf);
			return "/project/ProConferencedataIndex";
		}
	}

	/**
	 * 录入会议纪要
	 * 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			String datapreId = request.getParameter("datapreId");
			String conferencetime = request.getParameter("conferencetime");
			String conferenceaddress = request.getParameter("conferenceaddress");
			String conferenunit = request.getParameter("conferenunit");
			String conferenpeople = request.getParameter("conferenpeople");
			String conferentitle = request.getParameter("conferentitle");
			String mainpeople = request.getParameter("mainpeople");
			String conferencontent = request.getParameter("conferencontent");
			ProConferenceinfo conference = new ProConferenceinfo();
			conference.setId(AuditStringUtils.getUUID());
			conference.setConferenceaddress(conferenceaddress);
			conference.setConferentitle(conferentitle);
			conference.setConferenunit(conferenunit);
			conference.setDatapreId(datapreId);
			conference.setMainpeople(mainpeople);
			conference.setConferencontent(conferencontent);
			conference.setConferenpeople(conferenpeople);
			conference.setConferencetime(AuditStringUtils.strToDate(conferencetime, "yyyy-MM-dd"));
			map = proConferenceService.add(conference);
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 编辑会议纪要
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			String id = request.getParameter("id");
			String datapreId = request.getParameter("datapreId");
			String conferencetime = request.getParameter("conferencetime");
			String conferenceaddress = request.getParameter("conferenceaddress");
			String conferenunit = request.getParameter("conferenunit");
			String conferenpeople = request.getParameter("conferenpeople");
			String conferentitle = request.getParameter("conferentitle");
			String mainpeople = request.getParameter("mainpeople");
			String conferencontent = request.getParameter("conferencontent");
			ProConferenceinfo conference = proConferenceService.findbyid(id);
			conference.setConferenceaddress(conferenceaddress);
			conference.setConferentitle(conferentitle);
			conference.setConferenunit(conferenunit);
			conference.setDatapreId(datapreId);
			conference.setMainpeople(mainpeople);
			conference.setConferencontent(conferencontent);
			conference.setConferenpeople(conferenpeople);
			conference.setConferencetime(AuditStringUtils.strToDate(conferencetime, "yyyy-MM-dd"));
			map = proConferenceService.update(conference);
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {

			String id = request.getParameter("id");
			// 调用业务层方法
			map = proConferenceService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
