/**
 * 跟踪审计项目清标控制层
 */
package com.audit.controller.project;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.audit.entity.User;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.entity.project.TractProjectQingBiao;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.project.ITractProjectQingBiaoService;

/**
 * @author dengyong
 * 
 */
@Controller
@RequestMapping("/project/tractQingBiao/")
public class ProQingBiaoController {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	@Autowired
	private ITractProjectQingBiaoService tractProjectQingBiaoService;

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/input")
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
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService
						.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId") + "add");
				request.setAttribute("qiaobiao", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				// 根据标段查询清标对象
				TractProjectQingBiao qingbiao = tractProjectQingBiaoService.findbybdid(key);
				request.setAttribute("qingbiao", qingbiao);

				// TractArrangeProjectInfo tractAuditProjectInfo =
				// iTractProjectArrangeService.getTractArrangeProjectInfo(key);
				// 获取安排信息通过标段信息
				// request.setAttribute("arrangeInfo", tractAuditProjectInfo);
				return "/project/tractProjectArrangeBiaoDuanqingbiaoEdit";
			}
		} else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService
						.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId") + "update");
				request.setAttribute("qiaobiao", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);

				// 根据标段查询清标对象
				TractProjectQingBiao qingbiao = tractProjectQingBiaoService.findbybdid(key);
				request.setAttribute("qingbiao", qingbiao);

				// TractArrangeProjectInfo tractArrangeProjectInfo =
				// iTractProjectArrangeService.getTractArrangeProjectInfo(key);
				// 获取安排信息通过标段信息
				// request.setAttribute("arrangeInfo", tractArrangeProjectInfo);
				return "/project/tractProjectArrangeBiaoDuanqingbiaoEdit";
			}
		} else {
			request.getSession().setAttribute("arrangeModuleId", id);
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectQingBiaoIndex";
		}
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String ownerName = request.getParameter("ownerName");
		String projectName = request.getParameter("projectName");
		String qingBiaoState = request.getParameter("qingBiaoState");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		String projectId = request.getParameter("projectId");

		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");

		if (AuditStringUtils.isNotEmpty(biaoDuanFind)) {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setProjectId(projectId);
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			if (null != user) {
				proejctBiaoDuanInfo.setBduserid(user.getId());
			}
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = tractProjectQingBiaoService
					.finProejctBiaoDuanBaseInfo(proejctBiaoDuanInfo);
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
			proejctBiaoDuanInfo.setQingBiaoState(qingBiaoState);
			if (null != user) {
				proejctBiaoDuanInfo.setBduserid(user.getId());
			}
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = tractProjectQingBiaoService
					.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @throws IOException
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request, MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) throws Exception {
		// 获取参数
		String biaoDuanId = request.getParameter("biaoDuanId");
		String afterMoney = request.getParameter("afterMoney");
		String measureUtil = request.getParameter("measureUtil");
		String projectCount = request.getParameter("projectCount");
		String maxUtilPrice = request.getParameter("maxUtilPrice");
		String bidUtilPrice = request.getParameter("bidUtilPrice");
		String exceedOneTwenty = request.getParameter("exceedOneTwenty");
		String underEighty = request.getParameter("underEighty");
		String exceedControllerPrice = request.getParameter("exceedControllerPrice");
		String underControllerPrice = request.getParameter("underControllerPrice");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 实例化清标对象
		TractProjectQingBiao qb = new TractProjectQingBiao();
		User user = (User) request.getSession().getAttribute("user");
		if (null != user) {
			qb.setCreateUserAccount(user.getUsername());
		}
		qb.setBiaoDuanId(biaoDuanId);
		qb.setAfterMoney(afterMoney);
		qb.setCreateTime(sdf.format(new Date()));
		qb.setMaxUtilPrice(maxUtilPrice);
		qb.setMeasureUtil(measureUtil);
		qb.setProjectCount(projectCount);
		qb.setBidUtilPrice(bidUtilPrice);
		qb.setExceedControllerPrice(exceedControllerPrice);
		qb.setExceedOneTwenty(exceedOneTwenty);
		qb.setUnderControllerPrice(underControllerPrice);
		qb.setUnderEighty(underEighty);
		// 得到id
		String id = AuditStringUtils.getUUID();
		qb.setId(id);
		List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
		List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
		// 上传文件
		List<Map<String, String>> listmap = AuditStringUtils.uploadfile(file, "qingBiao", request);
		if (null != listmap && listmap.size() != 0) {
			for (int i = 0; i < listmap.size(); i++) {
				FileBelongRelate fbr = new FileBelongRelate();
				fbr.setFileName(listmap.get(i).get("fileName"));
				fbr.setUrl(listmap.get(i).get("url"));
				fbr.setState("");
				fbr.setBelongToId(id);
				fbr.setUploadTime(new Date());
				listfile.add(fbr);
			}
		}
		Integer row = tractProjectQingBiaoService.add(qb, listfile);
		String msg = "";
		String falg = "";
		if (row > 0) {
			msg = "清标成功";
			falg = "success";
		} else {
			msg = "清标失败";
			falg = "fail";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{'success':'" + falg + "','msg':'" + msg + "'}");
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request, MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) throws Exception {
		// 获取参数
		String afterMoney = request.getParameter("afterMoney");
		String measureUtil = request.getParameter("measureUtil");
		String projectCount = request.getParameter("projectCount");
		String maxUtilPrice = request.getParameter("maxUtilPrice");
		String bidUtilPrice = request.getParameter("bidUtilPrice");
		String exceedOneTwenty = request.getParameter("exceedOneTwenty");
		String underEighty = request.getParameter("underEighty");
		String exceedControllerPrice = request.getParameter("exceedControllerPrice");
		String underControllerPrice = request.getParameter("underControllerPrice");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String id = request.getParameter("id");
		TractProjectQingBiao qb = tractProjectQingBiaoService.findbyid(id);
		// 清标对象
		User user = (User) request.getSession().getAttribute("user");
		if (null != user) {
			qb.setCreateUserAccount(user.getUsername());
		}
		qb.setAfterMoney(afterMoney);
		qb.setCreateTime(sdf.format(new Date()));
		qb.setMaxUtilPrice(maxUtilPrice);
		qb.setMeasureUtil(measureUtil);
		qb.setProjectCount(projectCount);
		qb.setBidUtilPrice(bidUtilPrice);
		qb.setExceedControllerPrice(exceedControllerPrice);
		qb.setExceedOneTwenty(exceedOneTwenty);
		qb.setUnderControllerPrice(underControllerPrice);
		qb.setUnderEighty(underEighty);
		List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
		List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
		// 上传文件
		List<Map<String, String>> listmap = AuditStringUtils.uploadfile(file, "qingBiao", request);
		if (null != listmap && listmap.size() != 0) {
			for (int i = 0; i < listmap.size(); i++) {
				FileBelongRelate fbr = new FileBelongRelate();
				fbr.setFileName(listmap.get(i).get("fileName"));
				fbr.setUrl(listmap.get(i).get("url"));
				fbr.setState("");
				fbr.setBelongToId(id);
				fbr.setUploadTime(new Date());
				listfile.add(fbr);
			}
		}
		Integer row = tractProjectQingBiaoService.update(qb, listfile);
		String msg = "";
		String falg = "";
		if (row > 0) {
			msg = "清标成功";
			falg = "success";
		} else {
			msg = "清标失败";
			falg = "fail";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{'success':'" + falg + "','msg':'" + msg + "'}");
	}
}
