/**
 * 跟踪项目安排管理
 */
package com.audit.controller.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ITractProjectArrangeService;
import com.audit.service.project.ITractProjectCreateService;

/**
 * @author dengxin
 */
@Controller
@RequestMapping("/project/tractProjectArrange")
public class TractProjectArrangeController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectArrangeService iTractProjectArrangeService;
	
	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		if (AuditStringUtils.isNotEmpty(add)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId")+"add");
				return "/project/tractProjectArrangeProjectEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				TractArrangeProjectInfo tractAuditProjectInfo = iTractProjectArrangeService.getTractArrangeProjectInfo(key);
				// 获取安排信息通过标段信息
				request.setAttribute("arrangeInfo", tractAuditProjectInfo);
				return "/project/tractProjectArrangeBiaoDuanEdit";
			}
		} else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId")+"update");
				return "/project/tractProjectArrangeProjectEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				TractArrangeProjectInfo tractArrangeProjectInfo = iTractProjectArrangeService.getTractArrangeProjectInfo(key);
				// 获取安排信息通过标段信息
				request.setAttribute("arrangeInfo", tractArrangeProjectInfo);
				return "/project/tractProjectArrangeBiaoDuanEdit";
			}
		} else {
			request.getSession().setAttribute("arrangeModuleId", id);
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectArrangeIndex";
		}
	}

	/**
	 * (non-Javadoc) 2013-7-25
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
		String arrangeState = request.getParameter("arrangeState");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		String projectId = request.getParameter("projectId");

		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);

	
		if (AuditStringUtils.isNotEmpty(biaoDuanFind)) {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setProjectId(projectId);
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectCreateService.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			proejctBiaoDuanInfo.setOwnerName(ownerName);
			proejctBiaoDuanInfo.setProjectName(projectName);
			proejctBiaoDuanInfo.setIsArrange(arrangeState);
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectArrangeService.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanId = request.getParameter("biaoDuanId");
		String mainAuditId = request.getParameter("mainAuditId");
		String[] governmentEmployeeId = request.getParameterValues("governmentEmployeeId");
		String startTime = request.getParameter("startTime");
		String isPay = request.getParameter("isPay");
		String orderStartTime = request.getParameter("orderStartTime");
		String projectArrangeTime = request.getParameter("projectArrangeTime");
		String projectCompleteTime = request.getParameter("projectCompleteTime");
		String projectStartTime = request.getParameter("projectStartTime");
		String projectEndTime = request.getParameter("projectEndTime");
		String intermediaryTelphone = request.getParameter("intermediaryTelphone");
		String intermediaryTeamName = request.getParameter("intermediaryTeamName");
		String intermediaryDutyName = request.getParameter("intermediaryDutyName");
		String intermediaryId = request.getParameter("intermediaryId");
		String intermediaryLinker = request.getParameter("intermediaryLinker");
		List<String> governmentEmployeeIds = new ArrayList<String>();
		if (governmentEmployeeId != null) {
			governmentEmployeeIds = Arrays.asList(governmentEmployeeId);
		}
		TractArrangeProjectInfo tractArrangeProjectInfo = new TractArrangeProjectInfo();
		tractArrangeProjectInfo.setIntermediaryTelphone(intermediaryTelphone);
		tractArrangeProjectInfo.setIntermediaryTeamName(intermediaryTeamName);
		tractArrangeProjectInfo.setIntermediaryDutyName(intermediaryDutyName);
		tractArrangeProjectInfo.setIntermediaryId(intermediaryId);
		tractArrangeProjectInfo.setIntermediaryLinker(intermediaryLinker);
		tractArrangeProjectInfo.setMainAuditId(mainAuditId);
		tractArrangeProjectInfo.setBiaoDuanId(biaoDuanId);
		tractArrangeProjectInfo.setGovernmentEmployeeIds(governmentEmployeeIds);
		tractArrangeProjectInfo.setStartTime(startTime);
		tractArrangeProjectInfo.setIsPay(isPay);
		tractArrangeProjectInfo.setOrderStartTime(orderStartTime);
		tractArrangeProjectInfo.setProjectArrangeTime(projectArrangeTime);
		tractArrangeProjectInfo.setProjectCompleteTime(projectCompleteTime);
		tractArrangeProjectInfo.setProjectStartTime(projectStartTime);
		tractArrangeProjectInfo.setProjectEndTime(projectEndTime);
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		tractArrangeProjectInfo.setArrangeUserAccount(userAccount);
		try {
			return iTractProjectArrangeService.addTractArrangeProjectInfo(tractArrangeProjectInfo);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String arrangeId = request.getParameter("arrangeId");
		String biaoDuanId = request.getParameter("biaoDuanId");
		String mainAuditId = request.getParameter("mainAuditId");
		String[] governmentEmployeeId = request.getParameterValues("governmentEmployeeId");
		String startTime = request.getParameter("startTime");
		String isPay = request.getParameter("isPay");
		String orderStartTime = request.getParameter("orderStartTime");
		String projectArrangeTime = request.getParameter("projectArrangeTime");
		String projectCompleteTime = request.getParameter("projectCompleteTime");
		String projectStartTime = request.getParameter("projectStartTime");
		String projectEndTime = request.getParameter("projectEndTime");
		String intermediaryTelphone = request.getParameter("intermediaryTelphone");
		String intermediaryTeamName = request.getParameter("intermediaryTeamName");
		String intermediaryDutyName = request.getParameter("intermediaryDutyName");
		String intermediaryId = request.getParameter("intermediaryId");
		String intermediaryLinker = request.getParameter("intermediaryLinker");
		List<String> governmentEmployeeIds = new ArrayList<String>();
		if (governmentEmployeeId != null) {
			governmentEmployeeIds = Arrays.asList(governmentEmployeeId);
		}
		TractArrangeProjectInfo tractArrangeProjectInfo = new TractArrangeProjectInfo();
		tractArrangeProjectInfo.setIntermediaryTelphone(intermediaryTelphone);
		tractArrangeProjectInfo.setIntermediaryTeamName(intermediaryTeamName);
		tractArrangeProjectInfo.setIntermediaryDutyName(intermediaryDutyName);
		tractArrangeProjectInfo.setIntermediaryId(intermediaryId);
		tractArrangeProjectInfo.setIntermediaryLinker(intermediaryLinker);
		tractArrangeProjectInfo.setMainAuditId(mainAuditId);
		tractArrangeProjectInfo.setBiaoDuanId(biaoDuanId);
		tractArrangeProjectInfo.setGovernmentEmployeeIds(governmentEmployeeIds);
		tractArrangeProjectInfo.setStartTime(startTime);
		tractArrangeProjectInfo.setIsPay(isPay);
		tractArrangeProjectInfo.setOrderStartTime(orderStartTime);
		tractArrangeProjectInfo.setProjectArrangeTime(projectArrangeTime);
		tractArrangeProjectInfo.setProjectCompleteTime(projectCompleteTime);
		tractArrangeProjectInfo.setProjectStartTime(projectStartTime);
		tractArrangeProjectInfo.setProjectEndTime(projectEndTime);
		tractArrangeProjectInfo.setId(arrangeId);
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		tractArrangeProjectInfo.setArrangeUserAccount(userAccount);
		try {
			return iTractProjectArrangeService.updateTractArrangeProjectInfo(tractArrangeProjectInfo);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

}
