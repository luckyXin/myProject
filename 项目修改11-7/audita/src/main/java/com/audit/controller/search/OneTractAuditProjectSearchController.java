/**
 * 个人跟踪项目查询
 */
package com.audit.controller.search;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.Dictionary;
import com.audit.entity.User;
import com.audit.entity.project.ProTractMonthFileInfo;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectDetail;
import com.audit.entity.project.TractClaimIndemnityContext;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.entity.project.TractOtherManage;
import com.audit.entity.project.TractPolicyChange;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.entity.project.TractProjectContract;
import com.audit.entity.project.TractProjectDataEnquiry;
import com.audit.entity.project.TractProjectDataTransferInfo;
import com.audit.entity.project.TractProjectQingBiao;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IProTractProjectContractService;
import com.audit.service.project.IProjectDataEnquiryService;
import com.audit.service.project.IProjectOtherManageService;
import com.audit.service.project.ITractClaimIndemnityService;
import com.audit.service.project.ITractPolicyChangeService;
import com.audit.service.project.ITractProjectChangeCardService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.project.ITractProjectDataTransferService;
import com.audit.service.project.ITractProjectMonthReportService;
import com.audit.service.project.ITractProjectQingBiaoService;
import com.audit.service.search.IAllProjectSearchService;
import com.audit.service.system.IDictionaryService;

/**
 * @author dengxin
 * 
 */
@Controller
@RequestMapping("/search/myselfTractProject")
public class OneTractAuditProjectSearchController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;
	
	@Autowired
	private IAllProjectSearchService iAllProjectSearchService;
	
	@Autowired
	private ITractProjectMonthReportService iTractProjectMonthReportService;
	
	@Autowired
	private ITractProjectQingBiaoService tractProjectQingBiaoService;
	
	@Autowired
	private ITractProjectChangeCardService iTractProjectChangeCardService;
	
	@Autowired
	private IDictionaryService  dictionaryService;
	
	@Autowired
	private ITractClaimIndemnityService  tractClaimIndemnityService;
	
	@Autowired
	private IProTractProjectContractService proTractProjectContractService;
	
	@Autowired
	private ITractPolicyChangeService  tractPolicyChangeService;
	
	@Autowired
	private IProjectDataEnquiryService projectDataEnquiryService;
	
	@Autowired
	private IProjectOtherManageService projectOtherManageService;
	
	@Autowired
	private ITractProjectDataTransferService iTractProjectDataTransfer;
	
	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;
	
	/**
	 * (non-Javadoc) 2013-8-21
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		String showProject = request.getParameter("showProject");
		String templateimport=request.getParameter("templateimport");
		//录入模板
		String add=request.getParameter("add");
		String id = request.getParameter("id");
		if (AuditStringUtils.isNotEmpty(showProject)) {
			
			// 获取选中ID
			String key = request.getParameter("key");

			// 获取跟踪项目标段信息
			TractAuditProjectDetail tractAuditProjectDetail = iAllProjectSearchService.getTractAuditProjectDetail(key);

			// 安排信息
			request.setAttribute("tractArrangeProjectInfo", tractAuditProjectDetail.getTractArrangeProjectInfo());

			// 跟踪项目信息
			request.setAttribute("projectBaseInfo", tractAuditProjectDetail);
			
			//获取索赔类型
			List<Dictionary> listsptype=dictionaryService.findbyid(PropertiesGetValue.getContextProperty("dictionary.type.suopei"));
			request.setAttribute("listsptype",listsptype );
			
			// 根据标段查询合同审核
			List<TractProjectContract> htlist = proTractProjectContractService.findbybdid(key);
			request.setAttribute("htlist", htlist);
			
			// 标段信息获取
			TractProjectDataTransferInfo  tractProjectDataTransferInfo 	= iTractProjectDataTransfer.getTractProjectDataTransferInfo(key);
			request.setAttribute("dataTransfer", tractProjectDataTransferInfo);

			return "/search/OneTractAuditProjectSearchDetailInfo";
		} else if (AuditStringUtils.isNotEmpty(templateimport)) {
			// 获取选中ID
			String key = request.getParameter("key");
			// 标段信息获取
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
			request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
			request.setAttribute("id", id);
			request.setAttribute("biaoDuanId", key);
			return "/search/proTempleImportIndex";
		} else if (AuditStringUtils.isNotEmpty(add)) {
			// 获取选中ID
			String key = request.getParameter("key");
			request.setAttribute("id", id);
			request.setAttribute("biaoDuanId", key);
			return "/search/proTempleImportAdd";
		}
		else {
			String userAccount = user.getUserAccount();
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/search/MyselfTractAuditProjectSearchIndex";
		}
	}

	/**
	 * 跟踪项目查询 2013-8-21
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String qingBiaoState = request.getParameter("qingBiaoState");
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String ownerName = request.getParameter("projectOwner");
		String projectName = request.getParameter("projectName");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		String projectId = request.getParameter("projectId");
		String monthReprotState = request.getParameter("monthReprotState");
		String biaoDuanId = request.getParameter("biaoDuanId");
		String changeCardState = request.getParameter("changeCardState");
		String changeType = request.getParameter("changeType");
		String claimindemnity=request.getParameter("claimindemnity");
		String claimIndemnityType = request.getParameter("claimIndemnityType");
		String zhengCeChangeFind = request.getParameter("zhengCeChangeFind");
		String cailiaoxunjia = request.getParameter("cailiaoxunjia");
		String otherManageFind = request.getParameter("otherManageFind");
		String projectType = request.getParameter("projectType");
		String dataTransferState = request.getParameter("dataTransferState");
		String reallyStartTime = request.getParameter("reallyStartTime");
		String reallyEndTime = request.getParameter("reallyEndTime");
		
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		if (AuditStringUtils.isNotEmpty(otherManageFind)) {
			// 其他管理信息查询
			GridDataModel<TractOtherManage> projectBiaoDuan = projectOtherManageService.findbybd(page, pagesize, name,
					order, biaoDuanId);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else if (AuditStringUtils.isNotEmpty(cailiaoxunjia)) {
			// 材料询价信息查询
			GridDataModel<TractProjectDataEnquiry> projectBiaoDuan = projectDataEnquiryService.findbybd(page, pagesize,
					name, order, biaoDuanId);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else if (AuditStringUtils.isNotEmpty(zhengCeChangeFind)) {
			// 政策性调整信息查询
			GridDataModel<TractPolicyChange> projectBiaoDuan = tractPolicyChangeService.findbybd(page, pagesize, name,
					order, biaoDuanId);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else if (AuditStringUtils.isNotEmpty(claimindemnity)) {
			// 索赔额信息查询
			GridDataModel<TractClaimIndemnityContext> projectBiaoDuan = tractClaimIndemnityService.findbybd(page,
					pagesize, name, order, biaoDuanId, claimIndemnityType);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else if (AuditStringUtils.isNotEmpty(changeCardState)) {
			// 查询变更签证信息
			TractProjectChangeCardInfo tractProjectChangeCardInfo = new TractProjectChangeCardInfo();
			tractProjectChangeCardInfo.setPageno(page);
			tractProjectChangeCardInfo.setPagesize(pagesize);
			tractProjectChangeCardInfo.setSort(order);
			tractProjectChangeCardInfo.setFiled(name);
			tractProjectChangeCardInfo.setBiaoDuanId(biaoDuanId);
			tractProjectChangeCardInfo.setChangeType(changeType);
			GridDataModel<TractProjectChangeCardInfo> projectBiaoDuan = iTractProjectChangeCardService.findTractProjectChangeCardInfo(tractProjectChangeCardInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else  if (AuditStringUtils.isNotEmpty(monthReprotState)) {
	    	// 查询月报表信息的场合
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
			proejctBiaoDuanInfo.setProjectType(projectType);
			proejctBiaoDuanInfo.setReallyEndTime(reallyEndTime);
			proejctBiaoDuanInfo.setReallyStartTime(reallyStartTime);
			proejctBiaoDuanInfo.setDataTransferState(dataTransferState);
			if (null != user) {
				proejctBiaoDuanInfo.setBduserid(user.getId());
			}
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = tractProjectQingBiaoService.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}
	
	
	
	/**
	 * 根据标段查询模板对象信息
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("findTemple")
	@ResponseBody
	public Map<String, Object> findTemple(HttpServletRequest request) {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sort =request.getParameter("sort");
		String order=request.getParameter("order");
		//获取标段
		String biaoDuanId=request.getParameter("biaoDuanId");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ProTractMonthFileInfo>  gridmodel=projectOtherManageService.findTemple(intPage, pagesize, sort,order,biaoDuanId);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}
	
	/**
	 * 获取标段各个阶段详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getDedailInfo")
	@ResponseBody
	public Map<String, Object> getDedailInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanId = request.getParameter("biaoDuanId");
		String infoType = request.getParameter("infoType");
		String changeType = request.getParameter("changeType");
		String claimIndemnityType = request.getParameter("claimIndemnityType");
		if ("0".equals(infoType)) {
			TractProjectQingBiao qingbiaoInfo = tractProjectQingBiaoService.findbybdid(biaoDuanId);
			map.put("qingbiaoInfo", qingbiaoInfo);
		} else if ("1".equals(infoType)) {
			TractProjectChangeCardInfo tractProjectChangeCardInfo = new TractProjectChangeCardInfo();
			tractProjectChangeCardInfo.setBiaoDuanId(biaoDuanId);
			tractProjectChangeCardInfo.setChangeType(changeType);
			String totalChangeMoney = iAllProjectSearchService.getChangeTotalMoneyByCadeType(tractProjectChangeCardInfo);
			map.put("totalChangeMoney", totalChangeMoney);
		} else if ("2".equals(infoType)) {
			TractProjectChangeCardInfo tractProjectChangeCardInfo = new TractProjectChangeCardInfo();
			tractProjectChangeCardInfo.setBiaoDuanId(biaoDuanId);
			tractProjectChangeCardInfo.setChangeType(claimIndemnityType);
			String totalIndemnityMoney = iAllProjectSearchService.getTotalIndemnityMoney(tractProjectChangeCardInfo);
			map.put("totalIndemnityMoney", totalIndemnityMoney);
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-8-21
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 导入word,上传文件
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 */
	@RequestMapping("/addExcelTemp")
	public void addExcelTemp(HttpServletRequest request, MultipartHttpServletRequest requestMultipart, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanId = request.getParameter("biaoDuanId");
		//获取当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		// 获取导入的文件信息
		List<MultipartFile> monthReportFile = requestMultipart.getFiles("fileurl");
		//定义模板文件对象
		ProTractMonthFileInfo  month=new  ProTractMonthFileInfo();
		month.setBiaoDuanId(biaoDuanId);
		month.setId(AuditStringUtils.getUUID());
		try {
			User user = (User) request.getSession().getAttribute("user");
			month.setCreateuser(user.getUsername());
			month.setCreatetime(sdf.format(new Date()));
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(monthReportFile, "templeImport", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					month.setFilename(listmap.get(i).get("fileName"));
					month.setFileurl(listmap.get(i).get("url"));
				}
			}
			map = projectOtherManageService.toLoadExcel(biaoDuanId,user.getUsername(),sdf.format(new Date()),month, monthReportFile);
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
	 * (non-Javadoc) 2013-8-21
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-8-21
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-8-21
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
