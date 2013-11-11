/**
 * 跟踪审计项目合同审核
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
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.entity.project.TractProjectContract;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IProTractProjectContractService;
import com.audit.service.project.ITractProjectCreateService;

/**
 * @author dengyong
 */
@Controller
@RequestMapping("/project/contract/")
public class ProTractProjectContractController {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	@Autowired
	private IProTractProjectContractService proTractProjectContractService;

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
				request.setAttribute("htaudit", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				// 根据标段查询合同审核
				List<TractProjectContract> htlist = proTractProjectContractService.findbybdid(key);
				request.setAttribute("htlist", htlist);
				return "/project/tractProjectArrangeBiaoDuanHtauditEdit";
			}
		} else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService
						.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId") + "update");
				request.setAttribute("htaudit", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				// 根据标段查询合同审核
				List<TractProjectContract> htlist = proTractProjectContractService.findbybdid(key);
				request.setAttribute("htlist", htlist);
				return "/project/tractProjectArrangeBiaoDuanHtauditEdit";
			}
		} else {
			request.getSession().setAttribute("arrangeModuleId", id);
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectContractIndex";
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
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = proTractProjectContractService
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
			if (null != user) {
				proejctBiaoDuanInfo.setBduserid(user.getId());
			}
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = proTractProjectContractService
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 定义合同审核集合
		List<TractProjectContract> list = new ArrayList<TractProjectContract>();

		// 实例化合同审核条款对象
		TractProjectContract ht = new TractProjectContract();
		User user = (User) request.getSession().getAttribute("user");
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setBiaoDuanId(biaoDuanId);
		ht.setCreateTime(sdf.format(new Date()));
		// 得到id
		String id = AuditStringUtils.getUUID();
		ht.setId(id);

		// 获取地勘合同
		String dikanht = request.getParameter("dikanht");
		ht.setHtName(dikanht);
		ht.setSort("1");
		// 获取地勘合同文件
		List<MultipartFile> filehttkaudit = requestMultipart.getFiles("dikanhtfile");
		// 上传文件
		List<Map<String, String>> listmap = AuditStringUtils.uploadfile(filehttkaudit, "htaudit", request);
		if (null != listmap && listmap.size() != 0) {
			for (int i = 0; i < listmap.size(); i++) {
				ht.setHtFile(listmap.get(i).get("url"));
			}
		}
		// 获取该合同意见信息
		String dikanexistproblem = request.getParameter("dikanexistproblem");
		String dikanauditview = request.getParameter("dikanauditview");
		String dikanexecutecase = request.getParameter("dikanexecutecase");
		ht.setAuditview(dikanauditview);
		ht.setExistproblem(dikanexistproblem);
		ht.setExecutecase(dikanexecutecase);

		// 添加合同审核集合
		list.add(ht);

		ht = new TractProjectContract();
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setBiaoDuanId(biaoDuanId);
		ht.setCreateTime(sdf.format(new Date()));
		ht.setId(AuditStringUtils.getUUID());

		// 获取设计合同
		String shejiht = request.getParameter("shejiht");
		ht.setHtName(shejiht);
		ht.setSort("2");
		// 获取设计合同文件
		List<MultipartFile> shejihtlist = requestMultipart.getFiles("shejihtfile");
		// 上传文件
		List<Map<String, String>> shejihtmap = AuditStringUtils.uploadfile(shejihtlist, "htaudit", request);
		if (null != shejihtmap && shejihtmap.size() != 0) {
			for (int i = 0; i < shejihtmap.size(); i++) {
				ht.setHtFile(shejihtmap.get(i).get("url"));
			}
		}
		// 获取该合同意见信息
		String shejiexistproblem = request.getParameter("shejiexistproblem");
		String shejiauditview = request.getParameter("shejiauditview");
		String shejiexecutecase = request.getParameter("shejiexecutecase");
		ht.setAuditview(shejiauditview);
		ht.setExistproblem(shejiexistproblem);
		ht.setExecutecase(shejiexecutecase);
		// 添加合同审核集合
		list.add(ht);

		ht = new TractProjectContract();
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setBiaoDuanId(biaoDuanId);
		ht.setCreateTime(sdf.format(new Date()));
		ht.setId(AuditStringUtils.getUUID());
		// 获取项目管理合同
		String proht = request.getParameter("proht");
		ht.setHtName(proht);
		ht.setSort("3");
		// 获取目管理合同文件
		List<MultipartFile> prohtlist = requestMultipart.getFiles("prohtfile");
		// 上传文件
		List<Map<String, String>> prohtmap = AuditStringUtils.uploadfile(prohtlist, "htaudit", request);
		if (null != prohtmap && prohtmap.size() != 0) {
			for (int i = 0; i < prohtmap.size(); i++) {
				ht.setHtFile(prohtmap.get(i).get("url"));
			}
		}
		// 获取该合同意见信息
		String proexistproblem = request.getParameter("proexistproblem");
		String proauditview = request.getParameter("proauditview");
		String proexecutecase = request.getParameter("proexecutecase");
		ht.setAuditview(proauditview);
		ht.setExistproblem(proexistproblem);
		ht.setExecutecase(proexecutecase);
		// 添加合同审核集合
		list.add(ht);

		ht = new TractProjectContract();
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setBiaoDuanId(biaoDuanId);
		ht.setCreateTime(sdf.format(new Date()));
		ht.setId(AuditStringUtils.getUUID());
		// 获取监理合同
		String jianliht = request.getParameter("jianliht");
		ht.setHtName(jianliht);
		ht.setSort("4");
		// 获取监理合同文件
		List<MultipartFile> jianlihtlist = requestMultipart.getFiles("jianlihtfile");
		// 上传文件
		List<Map<String, String>> jianlihtmap = AuditStringUtils.uploadfile(jianlihtlist, "htaudit", request);
		if (null != jianlihtmap && jianlihtmap.size() != 0) {
			for (int i = 0; i < jianlihtmap.size(); i++) {
				ht.setHtFile(jianlihtmap.get(i).get("url"));
			}
		}
		// 获取该合同意见信息
		String jianliexistproblem = request.getParameter("jianliexistproblem");
		String jianliauditview = request.getParameter("jianliauditview");
		String jianliexecutecase = request.getParameter("jianliexecutecase");
		ht.setAuditview(jianliauditview);
		ht.setExistproblem(jianliexistproblem);
		ht.setExecutecase(jianliexecutecase);
		// 添加合同审核集合
		list.add(ht);

		ht = new TractProjectContract();
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setBiaoDuanId(biaoDuanId);
		ht.setCreateTime(sdf.format(new Date()));
		ht.setId(AuditStringUtils.getUUID());
		// 获取总承包合同
		String zongcbht = request.getParameter("zongcbht");
		ht.setHtName(zongcbht);
		ht.setSort("5");
		// 获取总承包合同文件
		List<MultipartFile> zongcbhtlist = requestMultipart.getFiles("zongcbhtfile");
		// 上传文件
		List<Map<String, String>> zongcbhtmap = AuditStringUtils.uploadfile(zongcbhtlist, "htaudit", request);
		if (null != zongcbhtmap && zongcbhtmap.size() != 0) {
			for (int i = 0; i < zongcbhtmap.size(); i++) {
				ht.setHtFile(zongcbhtmap.get(i).get("url"));
			}
		}
		// 获取该合同意见信息
		String zongcbexistproblem = request.getParameter("zongcbexistproblem");
		String zongcbauditview = request.getParameter("zongcbauditview");
		String zongcbexecutecase = request.getParameter("zongcbexecutecase");
		ht.setAuditview(zongcbauditview);
		ht.setExistproblem(zongcbexistproblem);
		ht.setExecutecase(zongcbexecutecase);
		// 添加合同审核集合
		list.add(ht);

		ht = new TractProjectContract();
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setBiaoDuanId(biaoDuanId);
		ht.setCreateTime(sdf.format(new Date()));
		ht.setId(AuditStringUtils.getUUID());
		// 获取设备供应合同
		String shebeiht = request.getParameter("shebeiht");
		ht.setHtName(shebeiht);
		ht.setSort("6");
		// 获取设备供应合同文件
		List<MultipartFile> shebeihtlist = requestMultipart.getFiles("shebeihtfile");
		// 上传文件
		List<Map<String, String>> shebeihtmap = AuditStringUtils.uploadfile(shebeihtlist, "htaudit", request);
		if (null != shebeihtmap && shebeihtmap.size() != 0) {
			for (int i = 0; i < shebeihtmap.size(); i++) {
				ht.setHtFile(shebeihtmap.get(i).get("url"));
			}
		}
		// 获取该合同意见信息
		String shebeiexistproblem = request.getParameter("shebeiexistproblem");
		String shebeiauditview = request.getParameter("shebeiauditview");
		String shebeiexecutecase = request.getParameter("shebeiexecutecase");
		ht.setAuditview(shebeiauditview);
		ht.setExistproblem(shebeiexistproblem);
		ht.setExecutecase(shebeiexecutecase);
		// 添加合同审核集合
		list.add(ht);

		// 获取分包个数
		String rownumber = request.getParameter("rownumber");
		Integer num = Integer.parseInt(rownumber);
		if (!"0".equals(rownumber)) {
			for (int i = 1; i <= num; i++) {
				ht = new TractProjectContract();
				if (null != user) {
					ht.setCreateUser(user.getUsername());
				}
				ht.setBiaoDuanId(biaoDuanId);
				ht.setCreateTime(sdf.format(new Date()));
				ht.setId(AuditStringUtils.getUUID());
				// 获取分包合同
				String fenbao = request.getParameter("fenbao" + i);
				ht.setHtName(fenbao);
				Integer sort = 6 + i;
				ht.setSort(sort.toString());
				// 获取分包合同文件
				List<MultipartFile> fenbaolist = requestMultipart.getFiles("fenbaofile" + i);
				// 上传文件
				List<Map<String, String>> fenbaomap = AuditStringUtils.uploadfile(fenbaolist, "htaudit", request);
				if (null != fenbaomap && fenbaomap.size() != 0) {
					for (int j = 0; j < fenbaomap.size(); j++) {
						ht.setHtFile(fenbaomap.get(j).get("url"));
					}
				}
				// 获取该合同意见信息
				String existproblem = request.getParameter("existproblem" + i);
				String auditview = request.getParameter("auditview" + i);
				String executecase = request.getParameter("executecase" + i);
				ht.setAuditview(auditview);
				ht.setExistproblem(existproblem);
				ht.setExecutecase(executecase);
				// 添加合同审核集合
				list.add(ht);
			}
		}
		Integer row = proTractProjectContractService.add(list);
		String msg = "";
		String falg = "";
		if (row > 0) {
			msg = "合同审核条款成功";
			falg = "success";
		} else {
			msg = "合同审核条款失败";
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

		// 获取标段id
		String biaoDuanId = request.getParameter("biaoDuanId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 定义合同审核集合
		List<TractProjectContract> list = new ArrayList<TractProjectContract>();

		// 实例化合同审核条款对象
		// 获取合同审核地勘合同id
		String dikanid = request.getParameter("dikanid");
		TractProjectContract ht = proTractProjectContractService.findbyid(dikanid);
		User user = (User) request.getSession().getAttribute("user");
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setCreateTime(sdf.format(new Date()));
		ht.setSort("1");
		// 获取地勘合同文件
		List<MultipartFile> filehttkaudit = requestMultipart.getFiles("dikanhtfile");
		if (null != filehttkaudit && filehttkaudit.size() != 0) {
			// 上传文件
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(filehttkaudit, "htaudit", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					ht.setHtFile(listmap.get(i).get("url"));
				}
			}
		}
		// 获取该合同意见信息
		String dikanexistproblem = request.getParameter("dikanexistproblem");
		String dikanauditview = request.getParameter("dikanauditview");
		String dikanexecutecase = request.getParameter("dikanexecutecase");
		ht.setAuditview(dikanauditview);
		ht.setExistproblem(dikanexistproblem);
		ht.setExecutecase(dikanexecutecase);

		// 添加合同审核集合
		list.add(ht);

		// 获取设计合同id
		String shejiid = request.getParameter("shejiid");
		ht = proTractProjectContractService.findbyid(shejiid);
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setCreateTime(sdf.format(new Date()));
		ht.setSort("2");
		// 获取设计合同文件
		List<MultipartFile> shejihtlist = requestMultipart.getFiles("shejihtfile");
		if (null != shejihtlist && shejihtlist.size() != 0) {
			// 上传文件
			List<Map<String, String>> shejihtmap = AuditStringUtils.uploadfile(shejihtlist, "htaudit", request);
			if (null != shejihtmap && shejihtmap.size() != 0) {
				for (int i = 0; i < shejihtmap.size(); i++) {
					ht.setHtFile(shejihtmap.get(i).get("url"));
				}
			}
		}
		// 获取该合同意见信息
		String shejiexistproblem = request.getParameter("shejiexistproblem");
		String shejiauditview = request.getParameter("shejiauditview");
		String shejiexecutecase = request.getParameter("shejiexecutecase");
		ht.setAuditview(shejiauditview);
		ht.setExistproblem(shejiexistproblem);
		ht.setExecutecase(shejiexecutecase);
		// 添加合同审核集合
		list.add(ht);

		// 获取项目管理合同id
		String proid = request.getParameter("proid");
		ht = proTractProjectContractService.findbyid(proid);
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setCreateTime(sdf.format(new Date()));
		// 获取项目管理合同
		ht.setSort("3");
		// 获取目管理合同文件
		List<MultipartFile> prohtlist = requestMultipart.getFiles("prohtfile");
		if (null != prohtlist && prohtlist.size() != 0) {
			// 上传文件
			List<Map<String, String>> prohtmap = AuditStringUtils.uploadfile(prohtlist, "htaudit", request);
			if (null != prohtmap && prohtmap.size() != 0) {
				for (int i = 0; i < prohtmap.size(); i++) {
					ht.setHtFile(prohtmap.get(i).get("url"));
				}
			}
		}
		// 获取该合同意见信息
		String proexistproblem = request.getParameter("proexistproblem");
		String proauditview = request.getParameter("proauditview");
		String proexecutecase = request.getParameter("proexecutecase");
		ht.setAuditview(proauditview);
		ht.setExistproblem(proexistproblem);
		ht.setExecutecase(proexecutecase);
		// 添加合同审核集合
		list.add(ht);

		// 获取监理合同id
		String jianliid = request.getParameter("jianliid");
		ht = proTractProjectContractService.findbyid(jianliid);
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setCreateTime(sdf.format(new Date()));
		// 获取监理合同
		ht.setSort("4");
		// 获取监理合同文件
		List<MultipartFile> jianlihtlist = requestMultipart.getFiles("jianlihtfile");
		if (null != jianlihtlist && jianlihtlist.size() != 0) {
			// 上传文件
			List<Map<String, String>> jianlihtmap = AuditStringUtils.uploadfile(jianlihtlist, "htaudit", request);
			if (null != jianlihtmap && jianlihtmap.size() != 0) {
				for (int i = 0; i < jianlihtmap.size(); i++) {
					ht.setHtFile(jianlihtmap.get(i).get("url"));
				}
			}
		}
		// 获取该合同意见信息
		String jianliexistproblem = request.getParameter("jianliexistproblem");
		String jianliauditview = request.getParameter("jianliauditview");
		String jianliexecutecase = request.getParameter("jianliexecutecase");
		ht.setAuditview(jianliauditview);
		ht.setExistproblem(jianliexistproblem);
		ht.setExecutecase(jianliexecutecase);
		// 添加合同审核集合
		list.add(ht);

		// 获取总承包合同id
		String zongcbid = request.getParameter("zongcbid");
		ht = proTractProjectContractService.findbyid(zongcbid);
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setCreateTime(sdf.format(new Date()));
		// 获取总承包合同
		ht.setSort("5");
		// 获取总承包合同文件
		List<MultipartFile> zongcbhtlist = requestMultipart.getFiles("zongcbhtfile");
		if (null != zongcbhtlist && zongcbhtlist.size() != 0) {
			// 上传文件
			List<Map<String, String>> zongcbhtmap = AuditStringUtils.uploadfile(zongcbhtlist, "htaudit", request);
			if (null != zongcbhtmap && zongcbhtmap.size() != 0) {
				for (int i = 0; i < zongcbhtmap.size(); i++) {
					ht.setHtFile(zongcbhtmap.get(i).get("url"));
				}
			}
		}
		// 获取该合同意见信息
		String zongcbexistproblem = request.getParameter("zongcbexistproblem");
		String zongcbauditview = request.getParameter("zongcbauditview");
		String zongcbexecutecase = request.getParameter("zongcbexecutecase");
		ht.setAuditview(zongcbauditview);
		ht.setExistproblem(zongcbexistproblem);
		ht.setExecutecase(zongcbexecutecase);
		// 添加合同审核集合
		list.add(ht);

		// 获取设备供应合同id
		String shebeiid = request.getParameter("shebeiid");
		ht = proTractProjectContractService.findbyid(shebeiid);
		if (null != user) {
			ht.setCreateUser(user.getUsername());
		}
		ht.setCreateTime(sdf.format(new Date()));
		// 获取设备供应合同
		ht.setSort("6");
		// 获取设备供应合同文件
		List<MultipartFile> shebeihtlist = requestMultipart.getFiles("shebeihtfile");
		if (null != shebeihtlist && shebeihtlist.size() != 0) {
			// 上传文件
			List<Map<String, String>> shebeihtmap = AuditStringUtils.uploadfile(shebeihtlist, "htaudit", request);
			if (null != shebeihtmap && shebeihtmap.size() != 0) {
				for (int i = 0; i < shebeihtmap.size(); i++) {
					ht.setHtFile(shebeihtmap.get(i).get("url"));
				}
			}
		}
		// 获取该合同意见信息
		String shebeiexistproblem = request.getParameter("shebeiexistproblem");
		String shebeiauditview = request.getParameter("shebeiauditview");
		String shebeiexecutecase = request.getParameter("shebeiexecutecase");
		ht.setAuditview(shebeiauditview);
		ht.setExistproblem(shebeiexistproblem);
		ht.setExecutecase(shebeiexecutecase);
		// 添加合同审核集合
		list.add(ht);

		// 获取分包个数
		String rownumber = request.getParameter("rownumber");
		Integer num = Integer.parseInt(rownumber);
		if (!"0".equals(rownumber)) {
			for (int i = 1; i <= num; i++) {
				// 获取分包id
				String fenbaoid = request.getParameter("fenbaoid" + i);
				ht = proTractProjectContractService.findbyid(fenbaoid);
				if (null == ht) {
					ht = new TractProjectContract();
					ht.setBiaoDuanId(biaoDuanId);
				}
				if (null != user) {
					ht.setCreateUser(user.getUsername());
				}
				ht.setCreateTime(sdf.format(new Date()));
				// 获取分包合同
				String fenbao = request.getParameter("fenbao" + i);
				ht.setHtName(fenbao);
				Integer sort = 6 + i;
				ht.setSort(sort.toString());
				// 获取分包合同文件
				List<MultipartFile> fenbaolist = requestMultipart.getFiles("fenbaofile" + i);
				if (null != fenbaolist && fenbaolist.size() != 0) {
					// 上传文件
					List<Map<String, String>> fenbaomap = AuditStringUtils.uploadfile(fenbaolist, "htaudit", request);
					if (null != fenbaomap && fenbaomap.size() != 0) {
						for (int j = 0; j < fenbaomap.size(); j++) {
							ht.setHtFile(fenbaomap.get(j).get("url"));
						}
					}
				}
				// 获取该合同意见信息
				String existproblem = request.getParameter("existproblem" + i);
				String auditview = request.getParameter("auditview" + i);
				String executecase = request.getParameter("executecase" + i);
				ht.setAuditview(auditview);
				ht.setExistproblem(existproblem);
				ht.setExecutecase(executecase);
				// 添加合同审核集合
				list.add(ht);
			}
		}
		Integer sort = num + 6;
		// 调用方法删除分包数
		Integer row = proTractProjectContractService.delete(biaoDuanId, sort.toString());
		row = proTractProjectContractService.update(list);
		String msg = "";
		String falg = "";
		if (row > 0) {
			msg = "合同审核条款成功";
			falg = "success";
		} else {
			msg = "合同审核条款失败";
			falg = "fail";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{'success':'" + falg + "','msg':'" + msg + "'}");
	}

}
