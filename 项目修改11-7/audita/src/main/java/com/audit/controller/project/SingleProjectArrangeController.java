/**
 * 单项目安排管理
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
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.Employee;
import com.audit.entity.project.PackProjectArrange;
import com.audit.entity.project.ProjectInfo;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ISingleProjectArrangeService;
import com.audit.service.staff.IIntermediaryagencyService;

/**
 * @author DengXin
 */
@Controller
@RequestMapping("/project/SingleProjectArrange")
public class SingleProjectArrangeController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ISingleProjectArrangeService iSingleProjectArrangeService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	/**
	 * 页面跳转控制 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String look = request.getParameter("look");
		String packProjectArrange = request.getParameter("packProjectArrangeAdd");
		String id = request.getParameter("id");
		String url="";
		if (AuditStringUtils.isNotEmpty(packProjectArrange)) {
			// 处理类别
			request.setAttribute("processType", "packProjectArrangeAdd");
			// 菜单 ID
			request.setAttribute("id", id);
			// 处理地址
			request.setAttribute("url", "/project/SingleProjectArrange/add.do?packProjectArrangeAdd=0");
			url="/project/singleProjectArrangeEdit";
		} else if (AuditStringUtils.isNotEmpty(add)) {
			// 处理类别
			request.setAttribute("processType", "add");
			// 菜单 ID
			request.setAttribute("id", id);
			// 获取项目信息
			String key = request.getParameter("key");
			ProjectInfo projectInfo = iSingleProjectArrangeService.getProject(key);
			request.setAttribute("projectInfo", projectInfo);
			// 获取项目安排基本信息
			SingleProjectArrange singProjectInfo = iSingleProjectArrangeService.getSingleProjectInfoById(key);
			request.setAttribute("singProjectInfo", singProjectInfo);
			if (singProjectInfo != null) {
				// 处理类别
				request.setAttribute("processType", "update");
				// 处理地址
				request.setAttribute("url", "/project/SingleProjectArrange/update.do");
			} else {
				// 处理类别
				request.setAttribute("processType", "add");
				// 处理地址
				request.setAttribute("url", "/project/SingleProjectArrange/add.do");
			}
			url="/project/singleProjectArrangeEdit";
		} else if (AuditStringUtils.isNotEmpty(update)) {

			// 菜单 ID
			request.setAttribute("id", id);
			// 处理地址
			request.setAttribute("url", "/project/SingleProjectArrange/update.do");
			// 获取项目安排ID
			String key = request.getParameter("key");
			if (key.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				// 处理类别
				request.setAttribute("processType", "packProjectArrangeUpdate");
				// 获取打包项目安排信息
				PackProjectArrange packProjectArrange1 = iSingleProjectArrangeService.getPackProjectArrange(key);
				request.setAttribute("packProjectArrange", packProjectArrange1);
			} else {
				// 处理类别
				request.setAttribute("processType", "update");
				// 获取项目安排基本信息
				SingleProjectArrange singProjectInfo = iSingleProjectArrangeService.getSingleProjectInfoById(key);
				request.setAttribute("singProjectInfo", singProjectInfo);
			}
			url="/project/singleProjectArrangeEdit";
		}else if (AuditStringUtils.isNotEmpty(look)) {

			// 菜单 ID
			request.setAttribute("id", id);
			// 处理地址
			request.setAttribute("url", "/project/SingleProjectArrange/update.do");
			// 获取项目安排ID
			String key = request.getParameter("key");
			if (key.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				// 处理类别
				request.setAttribute("processType", "packProjectArrangeUpdate");
				// 获取打包项目安排信息
				PackProjectArrange packProjectArrange1 = iSingleProjectArrangeService.getPackProjectArrange(key);
				request.setAttribute("packProjectArrange", packProjectArrange1);
			} else {
				// 处理类别
				request.setAttribute("processType", "update");
				// 获取项目安排基本信息
				SingleProjectArrange singProjectInfo = iSingleProjectArrangeService.getSingleProjectInfoById(key);
				request.setAttribute("singProjectInfo", singProjectInfo);
			}
		   url="/project/singleProjectArrangeLook";
		}
		else {
			String isArrange = request.getParameter("key");
			if (AuditStringUtils.isNotEmpty(isArrange)) {
				request.setAttribute("isArrange", isArrange);
			}
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			url= "/project/singleProjectArrangeIndex";
		}
		return url;
	}

	/**
	 * 单项目安排查询 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String ownerName = request.getParameter("ownerName");
		String proejctName = request.getParameter("proejctName");
		String findProjectState = request.getParameter("findProjectState");
		String findUserState = request.getParameter("findUserState");
		String searchUserName = request.getParameter("searchUserName");
		String findIntermediaryState = request.getParameter("findIntermediaryState");
		String intermediaryName = request.getParameter("intermediaryName");
		String findAllProjectState = request.getParameter("findAllProjectState");
		String findPackSubProject = request.getParameter("findPackSubProject");
		String packProjectId = request.getParameter("packProjectId");

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();

			// 当前页
			int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
			// 每页显示条数
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);

			if (AuditStringUtils.isNotEmpty(findPackSubProject)) {
				// 查询打包项目下面子项目
				GridDataModel<ArrangeProject> arrangeProjects = iSingleProjectArrangeService.findPackSubProject(page,
						pagesize, name, order, packProjectId);
				map.put("rows", arrangeProjects.getRows());
				map.put("total", arrangeProjects.getTotal());

			} else if (AuditStringUtils.isNotEmpty(findAllProjectState)) {
				// 查询所有项目
				GridDataModel<ArrangeProject> arrangeProjects = iSingleProjectArrangeService.findAllProject(page,
						pagesize, name, order, ownerName, proejctName, userAccount);
				map.put("rows", arrangeProjects.getRows());
				map.put("total", arrangeProjects.getTotal());

			} else if (AuditStringUtils.isNotEmpty(findIntermediaryState)) {

				// 中介机构检索
				GridDataModel<Intermediaryagency> intermediaryagencys = iIntermediaryagencyService.find(page, pagesize,
						name, order, intermediaryName, "");
				map.put("rows", intermediaryagencys.getRows());
				map.put("total", intermediaryagencys.getTotal());

			} else if (AuditStringUtils.isNotEmpty(findUserState)) {

				// 人员检索
				GridDataModel<Employee> users = iSingleProjectArrangeService.find(page, pagesize, name, order,
						searchUserName);
				map.put("rows", users.getRows());
				map.put("total", users.getTotal());

			} else if (AuditStringUtils.isNotEmpty(findProjectState)) {
				String ownerId = request.getParameter("proid");
				// 项目信息检索
				GridDataModel<ArrangeProject> arrangeProjects = iSingleProjectArrangeService.find(page, pagesize, name,
						order, ownerName, proejctName, userAccount,ownerId,"M015");
				map.put("rows", arrangeProjects.getRows());
				map.put("total", arrangeProjects.getTotal());
			} else {

				// 单项目安排检索
				GridDataModel<ArrangeProject> singleProject = iSingleProjectArrangeService.findSingleProject(page,
						pagesize, name, order, ownerName, proejctName, userAccount);
				map.put("rows", singleProject.getRows());
				map.put("total", singleProject.getTotal());
			}
		} catch (NumberFormatException e) {

			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		} catch (Exception e) {

			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * 添加单项目安排信息 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		String projectId = request.getParameter("projectId");
		String projectPackName = request.getParameter("projectPackName");
		String ownerId = request.getParameter("ownerId");
		String packProjectArrangeAdd = request.getParameter("packProjectArrangeAdd");
		String projectArrangeTime = request.getParameter("projectArrangeTime");
		String intermediaryId=request.getParameter("intermediaryId");
		String projectArrangeRemark = request.getParameter("projectArrangeRemark");
		String mainAuditId = request.getParameter("mainAuditId");
		String[] governmentEmployees = request.getParameterValues("governmentEmployeeId");
		String intermediarySendTime = request.getParameter("intermediarySendTime");
		String intermediaryAuditTime = request.getParameter("intermediaryAuditTime");
		String dataTransferTime = request.getParameter("dataTransferTime");
		//获取是否自审
		String isMySelfState=request.getParameter("isMySelfState");
		String state = request.getParameter("state");
		String packProjectId = request.getParameter("packProjectId");
		String subProjectId = request.getParameter("subProjectId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();

			if (AuditStringUtils.isNotEmpty(packProjectId) && AuditStringUtils.isNotEmpty(subProjectId)) {
				
				subProjectId=subProjectId.substring(0, subProjectId.length()-1);
				// 添加打包项目的子项目
				return iSingleProjectArrangeService.addSubProject(packProjectId, subProjectId, userAccount);
			} else if (AuditStringUtils.isNotEmpty(packProjectArrangeAdd)) {

				// 添加打包项目安排
				return iSingleProjectArrangeService.addPackProject(projectPackName, projectArrangeTime,intermediaryId,
						projectArrangeRemark, mainAuditId, governmentEmployees, intermediarySendTime,
						intermediaryAuditTime, state, userAccount, ownerId,isMySelfState,dataTransferTime);
			} else {
				// 添加单项目安排
				return iSingleProjectArrangeService.add(projectId, projectArrangeTime, intermediaryId,projectArrangeRemark,
						mainAuditId, governmentEmployees, intermediarySendTime, intermediaryAuditTime,
						state, userAccount,isMySelfState,dataTransferTime);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * 更新单项目安排信息 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {

		String singleProjectArrangeId = request.getParameter("singleProjectArrangeId");
		String projectId = request.getParameter("projectId");
		String projectArrangeTime = request.getParameter("projectArrangeTime");
		String projectArrangeRemark = request.getParameter("projectArrangeRemark");
		String mainAuditId = request.getParameter("mainAuditId");
		String[] governmentEmployees = request.getParameterValues("governmentEmployeeId");
		String intermediarySendTime = request.getParameter("intermediarySendTime");
		String intermediaryAuditTime = request.getParameter("intermediaryAuditTime");
		String state = request.getParameter("state");
		String projectPackId = request.getParameter("projectPackId");
		String ownerId = request.getParameter("ownerId");
		String projectPackName = request.getParameter("projectPackName");
		String sentAmount = request.getParameter("sentAmount");
		String dataTransferTime = request.getParameter("dataTransferTime");
		String intermediaryId=request.getParameter("intermediaryId");
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(projectPackId) && AuditStringUtils.isNotEmpty(ownerId)
					&& AuditStringUtils.isNotEmpty(projectPackName)) {

				map = iSingleProjectArrangeService.updatePackProject(projectPackId, projectPackName,
						projectArrangeTime, intermediaryId,projectArrangeRemark, mainAuditId, governmentEmployees,
						intermediarySendTime, intermediaryAuditTime, state, ownerId, sentAmount,dataTransferTime);
			} else {
				map = iSingleProjectArrangeService.update(singleProjectArrangeId, projectId, projectArrangeTime,intermediaryId,
						projectArrangeRemark, mainAuditId, governmentEmployees, intermediarySendTime,
						intermediaryAuditTime, state, dataTransferTime,userAccount);
			}

		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * 更改单项目安排信息，为删除状态 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		String packProjectId = request.getParameter("packProjectId");
		String subProjectId = request.getParameter("subProjectId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(packProjectId) && AuditStringUtils.isNotEmpty(subProjectId)) {
				return iSingleProjectArrangeService.deleteSubProject(packProjectId, subProjectId, userAccount);
			} else if (id.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				return iSingleProjectArrangeService.deletePackProjectArrange(id, userAccount);
			} else {
				return iSingleProjectArrangeService.delete(id);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * 销毁单项目安排信息 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取用户信息
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (!id.contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				return iSingleProjectArrangeService.destroy(id, userAccount);
			} else {
				return iSingleProjectArrangeService.destroyPackProjectArrange(id, userAccount);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}
}
