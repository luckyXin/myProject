/**
 * 跟踪审计项目变更签证管理
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
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ITractProjectArrangeService;
import com.audit.service.project.ITractProjectChangeCardService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.project.ITractProjectMonthReportService;

/**
 * @author dengxin
 */
@Controller
@RequestMapping("/project/tractProjectChangeCard")
public class TractProjectChangeCardContoller implements IControllerBase {
	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectArrangeService iTractProjectArrangeService;

	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	@Autowired
	private ITractProjectMonthReportService iTractProjectMonthReportService;
	
	@Autowired
	private ITractProjectChangeCardService iTractProjectChangeCardService;

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String addChangeCard = request.getParameter("addChangeCard");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		if (AuditStringUtils.isNotEmpty(add)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("changeCardModuleId") + "add");
				return "/project/tractProjectChangeCardEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				TractArrangeProjectInfo tractAuditProjectInfo = iTractProjectArrangeService.getTractArrangeProjectInfo(key);
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
				return "/project/tractProjectChangeCardBiaoDuanEdit";
			}
		} else if (AuditStringUtils.isNotEmpty(addChangeCard)) {
			String key = request.getParameter("key");
			// 获取操作用户
			request.setAttribute("nowUser", user.getUsername());
			TractProjectChangeCardInfo tractProjectChangeCardInfo = iTractProjectChangeCardService.getTractProjectChangeCardInfo(key);
			request.setAttribute("chanegCardInfo", tractProjectChangeCardInfo);
			id = request.getSession().getAttribute("changeCardModuleId") + "add";
			request.setAttribute("id", id);
			return "/project/tractProjectChangeCardBaseInfoEdit";
		} else {
			request.getSession().setAttribute("changeCardModuleId", id);
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectChangeCardIndex";
		}
	}

	/**
	 * (non-Javadoc) 2013-7-27
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
		String changeCardState = request.getParameter("changeCardState");
		String biaoDuanId = request.getParameter("biaoDuanId");
		String changeType = request.getParameter("changeType");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		if (AuditStringUtils.isNotEmpty(changeCardState)) {
			TractProjectChangeCardInfo tractProjectChangeCardInfo = new TractProjectChangeCardInfo();
			tractProjectChangeCardInfo.setPageno(page);
			tractProjectChangeCardInfo.setPagesize(pagesize);
			tractProjectChangeCardInfo.setSort(order);
			tractProjectChangeCardInfo.setFiled(name);
			tractProjectChangeCardInfo.setCreateUserAccount(userAccount);
			tractProjectChangeCardInfo.setBiaoDuanId(biaoDuanId);
			tractProjectChangeCardInfo.setChangeType(changeType);
			GridDataModel<TractProjectChangeCardInfo> projectBiaoDuan = iTractProjectChangeCardService.findTractProjectChangeCardInfo(tractProjectChangeCardInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else if (AuditStringUtils.isNotEmpty(biaoDuanFind)) {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setProjectId(projectId);
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			proejctBiaoDuanInfo.setUserAccount(userAccount);
			// 查询指定项目下的标段信息
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectMonthReportService.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
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
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectMonthReportService.finProejctBiaoDuanInfoAll(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String changeCode = request.getParameter("changeCode");
		String changeTime = request.getParameter("changeTime");
		String changeContext = request.getParameter("changeContext");
		String constructSentMoney = request.getParameter("constructSentMoney");
		String tractAuditAdviceMoney = request.getParameter("tractAuditAdviceMoney");
		String affirmChangeMoney = request.getParameter("affirmChangeMoney");
		String changeType = request.getParameter("changeType");
		String changeAndNowSiteInfo = request.getParameter("changeAndNowSiteInfo");
		String changeProccessCondition = request.getParameter("changeProccessCondition");
		String prospectSurMoney=request.getParameter("prospectSurMoney");
		
		TractProjectChangeCardInfo tractProjectChangeCardInfo = new TractProjectChangeCardInfo();
		tractProjectChangeCardInfo.setBiaoDuanId((String)request.getSession().getAttribute("biaoDuanId"));
		tractProjectChangeCardInfo.setId(id);
		tractProjectChangeCardInfo.setChangeCode(changeCode);
		tractProjectChangeCardInfo.setChangeTime(changeTime);
		tractProjectChangeCardInfo.setChangeContext(changeContext);
		tractProjectChangeCardInfo.setConstructSentMoney(constructSentMoney);
		tractProjectChangeCardInfo.setTractAuditAdviceMoney(tractAuditAdviceMoney);
		tractProjectChangeCardInfo.setAffirmChangeMoney(affirmChangeMoney);
		tractProjectChangeCardInfo.setChangeType(changeType);
		tractProjectChangeCardInfo.setChangeAndNowSiteInfo(changeAndNowSiteInfo);
		tractProjectChangeCardInfo.setChangeProccessCondition(changeProccessCondition);
		tractProjectChangeCardInfo.setProspectSurMoney(prospectSurMoney);
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			tractProjectChangeCardInfo.setCreateUserAccount(userAccount);
			return iTractProjectChangeCardService.editTractProjectChangeCardInfo(tractProjectChangeCardInfo);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			return iTractProjectChangeCardService.deleteTractProjectChangeCard(id);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
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
	public void addWord(HttpServletRequest request, MultipartHttpServletRequest requestMultipart, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanId = request.getParameter("biaoDuanId");
		String createTime = request.getParameter("createTime");
		// 获取导入的文件信息
		List<MultipartFile> monthReportFile = requestMultipart.getFiles("monthReportFile");
		// 获取上传文件
		List<MultipartFile> uploadFile = requestMultipart.getFiles("uploadFile");
		List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(uploadFile, "changeCard", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					FileBelongRelate fbr = new FileBelongRelate();
					fbr.setFileName(listmap.get(i).get("fileName"));
					fbr.setUrl(listmap.get(i).get("url"));
					fbr.setUploadTime(new Date());
					listfile.add(fbr);
				}
			}
			map = iTractProjectChangeCardService.toLoadExcel(userAccount, createTime, monthReportFile, biaoDuanId, listfile);
		} catch (IOException e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("{'isSuccess':'" + map.get("isSuccess") + "','msg':'" + map.get("msg") + "','id':'" + map.get("id") + "'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		
		return null;
	}
}
