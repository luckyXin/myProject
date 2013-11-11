/**
 * 进度审核管理
 */
package com.audit.controller.project;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ITractProjectArrangeService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.project.ITractProjectMonthReportService;

/**
 * @author dengxin
 */
@Controller
@RequestMapping("/project/tractMonthReport")
public class TractProjectMonthReportController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectArrangeService iTractProjectArrangeService;

	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	@Autowired
	private ITractProjectMonthReportService iTractProjectMonthReportService;

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String addMonth = request.getParameter("addMonth");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		if (AuditStringUtils.isNotEmpty(add)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService
						.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId") + "add");
				return "/project/tractProjectMonthReportProjectEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				TractArrangeProjectInfo tractAuditProjectInfo = iTractProjectArrangeService
						.getTractArrangeProjectInfo(key);
				// 获取安排信息通过标段信息
				request.setAttribute("arrangeInfo", tractAuditProjectInfo);
				// 获取操作用户
				request.setAttribute("nowUser", user.getUsername());
				// 获取系统当前时间
				try {
					request.setAttribute("nowTime", AuditStringUtils.getSystem(AuditStringUtils.DATE_YYYYMMMDD));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 标段ID
				request.getSession().setAttribute("biaoDuanId", key);
				return "/project/tractProjectMonthReportBiaoDuanEdit";
			}
		} else if (AuditStringUtils.isNotEmpty(addMonth)) {
			String key = request.getParameter("key");
			// 获取操作用户
			request.setAttribute("nowUser", user.getUsername());
			TractMonthReportInfo tractMonthReportInfo = iTractProjectMonthReportService.getTractMonthReportInfo(key);
			request.setAttribute("monthReportInfo", tractMonthReportInfo);
			id = request.getSession().getAttribute("monthReportModuleId") + "add";
			request.setAttribute("id", id);
			return "/project/tractProjectMonthReportEdit";
		} else {
			request.getSession().setAttribute("monthReportModuleId", id);
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectMonthReportIndex";
		}
	}

	/**
	 * (non-Javadoc) 2013-7-26
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
		String projectId = request.getParameter("projectId");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		String monthReprotState = request.getParameter("monthReprotState");
		String biaoDuanId = request.getParameter("biaoDuanId");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		if (AuditStringUtils.isNotEmpty(monthReprotState)) {
			TractMonthReportInfo tractMonthReportInfo = new TractMonthReportInfo();
			tractMonthReportInfo.setBiaoDuanId(biaoDuanId);
			tractMonthReportInfo.setPageno(page);
			tractMonthReportInfo.setPagesize(pagesize);
			tractMonthReportInfo.setSort(order);
			tractMonthReportInfo.setFiled(name);
			// 查询指定标段下的月报信息
			GridDataModel<TractMonthReportInfo> tractMonthReportInfos = iTractProjectMonthReportService
					.findProejctMonthReportInfo(tractMonthReportInfo);
			map.put("rows", tractMonthReportInfos.getRows());
			map.put("total", tractMonthReportInfos.getTotal());
		} else if (AuditStringUtils.isNotEmpty(biaoDuanFind)) {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setProjectId(projectId);
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			proejctBiaoDuanInfo.setUserAccount(userAccount);
			// 查询指定项目下的标段信息
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectMonthReportService
					.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
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
			proejctBiaoDuanInfo.setUserAccount(userAccount);
			// 所有项目已经所有标段信息
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectMonthReportService
					.finProejctBiaoDuanInfoAll(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}

	/**
	 * 导入word,上传文件
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 */
	@RequestMapping("/addWord")
	public void addWord(HttpServletRequest request, MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanId = request.getParameter("biaoDuanId");
		String userAccount = request.getParameter("createUserAccount");
		String createTime = request.getParameter("createTime");
		// 获取导入的文件信息
		List<MultipartFile> monthReportFile = requestMultipart.getFiles("monthReportFile");
		// 获取上传文件
		List<MultipartFile> uploadFile = requestMultipart.getFiles("uploadFile");
		List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
		try {
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(uploadFile, "mouthReport", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					FileBelongRelate fbr = new FileBelongRelate();
					fbr.setFileName(listmap.get(i).get("fileName"));
					fbr.setUrl(listmap.get(i).get("url"));
					fbr.setUploadTime(new Date());
					listfile.add(fbr);
				}
			}
			map = iTractProjectMonthReportService.toLoadExcel(userAccount, createTime, monthReportFile, biaoDuanId,
					listfile);
		} catch (IOException e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(
					"{'isSuccess':'" + map.get("isSuccess") + "','msg':'" + map.get("msg") + "','id':'" + map.get("id")
							+ "'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取累计完成产值
	 * @return
	 */
	@RequestMapping("getTotalCompleteValue")
	@ResponseBody
	public Map<String, Object> getTotalCompleteValue(HttpServletRequest request) {
		String nowMonthCompleteValue = request.getParameter("nowMonthCompleteValue");
		String biaoDuanId = (String) request.getSession().getAttribute("biaoDuanId");
		return iTractProjectMonthReportService.getTotalCompleteValue(biaoDuanId, nowMonthCompleteValue);
	}
	
	/**
	 * 获取累计完成产值
	 * @return
	 */
	@RequestMapping("getAddPayProjectMoney")
	@ResponseBody
	public Map<String, Object> getAddPayProjectMoney(HttpServletRequest request) {
		String nowMonthPayValue = request.getParameter("nowMonthPayValue");
		String biaoDuanId = (String) request.getSession().getAttribute("biaoDuanId");
		return iTractProjectMonthReportService.getAddPayProjectMoney(biaoDuanId, nowMonthPayValue);
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String biaoDuanId = (String) request.getSession().getAttribute("biaoDuanId");
		String createTime = request.getParameter("createTime");
		String createUserAccount = request.getParameter("createUserAccount");
		String nowMonthCompleteValue = request.getParameter("nowMonthCompleteValue");
		String totalCompleteValue = request.getParameter("totalCompleteValue");
		String nowMonthPayValue = request.getParameter("nowMonthPayValue");
		
		String addPayProjectMoney=request.getParameter("addPayProjectMoney");
		String projectImagePlan=request.getParameter("projectImagePlan");
		String existProblem=request.getParameter("existProblem");
		TractMonthReportInfo tractMonthReportInfo = new TractMonthReportInfo();
		tractMonthReportInfo.setId(id);
		tractMonthReportInfo.setBiaoDuanId(biaoDuanId);
		tractMonthReportInfo.setCreateTime(createTime);
		tractMonthReportInfo.setUpdateUserAccount(createUserAccount);
		tractMonthReportInfo.setCreateUserAccount(createUserAccount);
		tractMonthReportInfo.setNowMonthCompleteValue(nowMonthCompleteValue);
		tractMonthReportInfo.setNowMonthPayValue(nowMonthPayValue);
		tractMonthReportInfo.setTotalCompleteValue(totalCompleteValue);
		tractMonthReportInfo.setAddPayProjectMoney(addPayProjectMoney);
		tractMonthReportInfo.setProjectImagePlan(projectImagePlan);
		tractMonthReportInfo.setExistProblem(existProblem);
		try {
			return iTractProjectMonthReportService.addTractMonthReportInfo(tractMonthReportInfo);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			return iTractProjectMonthReportService.delTractMonthReportInfo(id);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

}
