/**
 * 跟踪审计立项管理
 */
package com.audit.controller.project;

import java.io.IOException;
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
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.system.IUserInfoService;

/**
 * @author dengxin
 */
@Controller
@RequestMapping("/project/tractProjectCreate")
public class TractProjectCreateController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 跟踪项目信息页面跳转 2013-7-24
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String addBiaoDuan = request.getParameter("addBiaoDuan");
		String updateBiaoDuan = request.getParameter("updateBiaoDuan");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		if (AuditStringUtils.isNotEmpty(addBiaoDuan)) {

			// 标段添加页面
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			String key = request.getParameter("key");
			request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
			String method = (String) request.getSession().getAttribute("tractMethod");
			request.setAttribute("id", request.getSession().getAttribute("tractModuleId") + method);
			request.setAttribute("projectId", key);
			return "/project/tractProjectBiaoDuanCreateEdit";
		} else if (AuditStringUtils.isNotEmpty(updateBiaoDuan)) {

			// 标段更新页面
			String key = request.getParameter("key");
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
			request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
			String method = (String) request.getSession().getAttribute("tractMethod");
			request.setAttribute("id", request.getSession().getAttribute("tractModuleId") + method);
			return "/project/tractProjectBiaoDuanCreateEdit";
		} else if (AuditStringUtils.isNotEmpty(add)) {
			request.getSession().setAttribute("tractMethod", "add");
			TractAuditProjectInfo tractAuditProjectInfo = new TractAuditProjectInfo();
			// 获取当前用户信息
			if (userInfoService.findbyuserAccountobject(userAccount) != null) {
				tractAuditProjectInfo.setCreateUserAccount(userInfoService.findbyuserAccountobject(userAccount).getUsername());
			}
			request.setAttribute("tractProject", tractAuditProjectInfo);
			request.setAttribute("id", id);
			return "/project/tractProjectCreateEdit";
		} else if (AuditStringUtils.isNotEmpty(update)) {
			request.getSession().setAttribute("tractMethod", "update");
			String key = request.getParameter("key");
			// 获取选中的跟踪审计项目信息
			TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
			request.setAttribute("tractProject", tractAuditProjectInfo);
			request.setAttribute("id", id);
			return "/project/tractProjectCreateEdit";
		} else {
			request.getSession().setAttribute("tractModuleId", id);
			String key = request.getParameter("key");
			String isArrange = request.getParameter("key");
			if (AuditStringUtils.isNotEmpty(isArrange)) {
				request.setAttribute("key", key);
			}
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectCreateIndex";
		}
	}

	/**
	 * 跟踪项目信息查询 2013-7-24
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
		String proejctName = request.getParameter("proejctName");
		String submitState = request.getParameter("submitState");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		String projectId = request.getParameter("projectId");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
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
			// 获取页面显示内容
			GridDataModel<TractAuditProjectInfo> tractAuditProjectInfo = iTractProjectCreateService.findTractProject(page, pagesize, name, order, ownerName, proejctName, userAccount, submitState);
			map.put("rows", tractAuditProjectInfo.getRows());
			map.put("total", tractAuditProjectInfo.getTotal());
		}
		return map;
	}

	/**
	 * 标段信息添加 2013-7-24
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanName = request.getParameter("biaoDuanName");
		String projectGaiKuang = request.getParameter("projectGaiKuang");
		String supervisorUtil = request.getParameter("supervisorUtil");
		String buildManageUtil = request.getParameter("buildManageUtil");
		String prospectUtil = request.getParameter("prospectUtil");
		String buildUtil = request.getParameter("buildUtil");
		String designUtil = request.getParameter("designUtil");
		String constructUtil = request.getParameter("constructUtil");
		String preAuditMoney = request.getParameter("preAuditMoney");
		String zhongBiaoMoney = request.getParameter("zhongBiaoMoney");
		String reallyStartTime = request.getParameter("reallyStartTime");
		String buildContent = request.getParameter("buildContent");
		String projectId = request.getParameter("projectId");
		String preAuditObligateMoney = request.getParameter("preAuditObligateMoney");
		String zhongBiaoObligateMoney = request.getParameter("zhongBiaoObligateMoney");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
		proejctBiaoDuanInfo.setProjectId(projectId);
		proejctBiaoDuanInfo.setUserAccount(userAccount);
		proejctBiaoDuanInfo.setBiaoDuanName(biaoDuanName);
		proejctBiaoDuanInfo.setProjectGaiKuang(projectGaiKuang);
		proejctBiaoDuanInfo.setSupervisorUtil(supervisorUtil);
		proejctBiaoDuanInfo.setBuildManageUtil(buildManageUtil);
		proejctBiaoDuanInfo.setProspectUtil(prospectUtil);
		proejctBiaoDuanInfo.setBuildUtil(buildUtil);
		proejctBiaoDuanInfo.setDesignUtil(designUtil);
		proejctBiaoDuanInfo.setConstructUtil(constructUtil);
		proejctBiaoDuanInfo.setPreAuditMoney(preAuditMoney);
		proejctBiaoDuanInfo.setZhongBiaoMoney(zhongBiaoMoney);
		proejctBiaoDuanInfo.setReallyStartTime(reallyStartTime);
		proejctBiaoDuanInfo.setBuildContent(buildContent);
		proejctBiaoDuanInfo.setPreAuditObligateMoney(preAuditObligateMoney);
		proejctBiaoDuanInfo.setZhongBiaoObligateMoney(zhongBiaoObligateMoney);
		try {
			return iTractProjectCreateService.addBiaoDuanInfo(proejctBiaoDuanInfo);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * 跟踪审计项目编辑（添加，更新）
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public void add(HttpServletRequest request, MultipartHttpServletRequest requestMultipart, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String projectName = request.getParameter("projectName");
		String projectNo = request.getParameter("projectNo");
		String projectNumber = request.getParameter("projectNumber");
		String projectCreateTime = request.getParameter("projectCreateTime");
		String projectType = request.getParameter("projectType");
		String ownerId = request.getParameter("ownerId");
		String ownerLinker = request.getParameter("proownerlinkname");
		String ownerTelPhone = request.getParameter("proownertelphone");
		String jianSheGuiMo = request.getParameter("jianSheGuiMo");
		String jianSheShiJian = request.getParameter("jianSheShiJian");
		String remark = request.getParameter("remark");
		String createUserAccount = request.getParameter("createUserAccount");
		String sentAuditMoney = request.getParameter("sentAuditMoney");
		String submitState = request.getParameter("submitState");
		String budgetCode = request.getParameter("budgetCode");
		String budgetMoney = request.getParameter("budgetMoney");
		String directMoney = request.getParameter("directMoney");
		List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
		try {
			List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
			// 上传文件
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(file, "datapre", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					FileBelongRelate fbr = new FileBelongRelate();
					fbr.setFileName(listmap.get(i).get("fileName"));
					fbr.setUrl(listmap.get(i).get("url"));
					fbr.setState("");
					fbr.setUploadTime(new Date());
					listfile.add(fbr);
				}
			}
			TractAuditProjectInfo tractAuditProjectInfo = new TractAuditProjectInfo();
			tractAuditProjectInfo.setId(id);
			tractAuditProjectInfo.setCreateUserAccount(createUserAccount);
			tractAuditProjectInfo.setJianSheGuiMo(jianSheGuiMo);
			tractAuditProjectInfo.setJianSheShiJian(jianSheShiJian);
			tractAuditProjectInfo.setOwnerId(ownerId);
			tractAuditProjectInfo.setOwnerLinker(ownerLinker);
			tractAuditProjectInfo.setOwnerTelPhone(ownerTelPhone);
			tractAuditProjectInfo.setProjectCreateNo(projectNo);
			tractAuditProjectInfo.setProjectCreateTime(projectCreateTime);
			tractAuditProjectInfo.setProjectName(projectName);
			tractAuditProjectInfo.setProjectType(projectType);
			tractAuditProjectInfo.setRemark(remark);
			tractAuditProjectInfo.setSentAuditMoney(sentAuditMoney);
			tractAuditProjectInfo.setSubmitState(submitState);
			tractAuditProjectInfo.setDirectMoney(directMoney);
			tractAuditProjectInfo.setBudgetCode(budgetCode);
			tractAuditProjectInfo.setBudgetMoney(budgetMoney);
			tractAuditProjectInfo.setProjectNumber(projectNumber);
			if (AuditStringUtils.isNotEmpty(id)) {
				map = iTractProjectCreateService.updateTractAuditProject(tractAuditProjectInfo, listfile);
			} else {
				map = iTractProjectCreateService.addTractAuditProject(tractAuditProjectInfo, listfile);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	 * 标段信息更新 2013-7-24
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanName = request.getParameter("biaoDuanName");
		String projectGaiKuang = request.getParameter("projectGaiKuang");
		String supervisorUtil = request.getParameter("supervisorUtil");
		String buildManageUtil = request.getParameter("buildManageUtil");
		String prospectUtil = request.getParameter("prospectUtil");
		String buildUtil = request.getParameter("buildUtil");
		String designUtil = request.getParameter("designUtil");
		String constructUtil = request.getParameter("constructUtil");
		String preAuditMoney = request.getParameter("preAuditMoney");
		String zhongBiaoMoney = request.getParameter("zhongBiaoMoney");
		String reallyStartTime = request.getParameter("reallyStartTime");
		String buildContent = request.getParameter("buildContent");
		String projectId = request.getParameter("projectId");
		String preAuditObligateMoney = request.getParameter("preAuditObligateMoney");
		String zhongBiaoObligateMoney = request.getParameter("zhongBiaoObligateMoney");
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
		proejctBiaoDuanInfo.setId(id);
		proejctBiaoDuanInfo.setProjectId(projectId);
		proejctBiaoDuanInfo.setUserAccount(userAccount);
		proejctBiaoDuanInfo.setBiaoDuanName(biaoDuanName);
		proejctBiaoDuanInfo.setProjectGaiKuang(projectGaiKuang);
		proejctBiaoDuanInfo.setSupervisorUtil(supervisorUtil);
		proejctBiaoDuanInfo.setBuildManageUtil(buildManageUtil);
		proejctBiaoDuanInfo.setProspectUtil(prospectUtil);
		proejctBiaoDuanInfo.setBuildUtil(buildUtil);
		proejctBiaoDuanInfo.setDesignUtil(designUtil);
		proejctBiaoDuanInfo.setConstructUtil(constructUtil);
		proejctBiaoDuanInfo.setPreAuditMoney(preAuditMoney);
		proejctBiaoDuanInfo.setZhongBiaoMoney(zhongBiaoMoney);
		proejctBiaoDuanInfo.setReallyStartTime(reallyStartTime);
		proejctBiaoDuanInfo.setBuildContent(buildContent);
		proejctBiaoDuanInfo.setPreAuditObligateMoney(preAuditObligateMoney);
		proejctBiaoDuanInfo.setZhongBiaoObligateMoney(zhongBiaoObligateMoney);
		try {
			return iTractProjectCreateService.updateBiaoDuanInfo(proejctBiaoDuanInfo);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}

	/**
	 * 刪除跟蹤審計項目信息 2013-7-24
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			if (AuditStringUtils.contains(id, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && id.length() < 8) {
				return iTractProjectCreateService.deleteTractAuditProject(id);
			} else {
				return iTractProjectCreateService.deleteBiaoDuanInfo(id);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}
	
	/**
	 * 判断是否名称重复
	 * @param request
	 * @return
	 */
	@RequestMapping("checkNameIsExist")
	@ResponseBody
	public Map<String, Object> checkName(HttpServletRequest request) {
		String biaoDuanName = request.getParameter("biaoDuanName");
		String projectName = request.getParameter("projectName");
		if (AuditStringUtils.isNotEmpty(biaoDuanName)) {
			return iTractProjectCreateService.checkBiaoDuanNameIsExist(biaoDuanName);
		} else {
			return iTractProjectCreateService.checkProjectNameIsExist(projectName);
		}
	}

	/**
	 * (non-Javadoc) 2013-7-24
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

	@RequestMapping("/checkProjectNameIsExist")
	@ResponseBody
	public Map<String,Object> checkProjectNameIsExist(HttpServletRequest request) {
		
		return null;
	}
}
