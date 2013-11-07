/**
 * 
 */
package com.audit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
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
import com.audit.component.IWorkFlowComponent;
import com.audit.entity.Dictionary;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.DataWord;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.Employee;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProGovernmentAssign;
import com.audit.entity.project.ProSuspendAuditInfo;
import com.audit.entity.project.ProTractAdjustment;
import com.audit.entity.project.Problems;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.ResultClassAuditlookproject;
import com.audit.entity.project.ResultClassProConference;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.entity.project.TractPolicyChange;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.entity.project.TractProjectQingBiao;
import com.audit.entity.search.SearchProjectBaseInfoResult;
import com.audit.entity.staff.Construction;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.Link;
import com.audit.entity.staff.ProjectOwner;
import com.audit.entity.system.MessageInfo;
import com.audit.service.common.ICommonPorjectExcelService;
import com.audit.service.project.IGovernmentAssignService;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.project.ISingleProjectArrangeService;
import com.audit.service.project.ITractPolicyChangeService;
import com.audit.service.project.ITractProjectArrangeService;
import com.audit.service.project.ITractProjectChangeCardService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.project.ITractProjectMonthReportService;
import com.audit.service.search.IAllProjectSearchService;
import com.audit.service.staff.IConstructionService;
import com.audit.service.staff.IIntermediaryagencyService;
import com.audit.service.staff.IProjectOwnerService;
import com.audit.service.system.IDictionaryService;
import com.audit.service.system.IMessageInfoService;
import com.audit.service.system.IUserInfoService;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * @author User
 * 
 */
@Controller
@RequestMapping("/common/project")
public class ProCommonController {

	@Autowired
	private ITractProjectMonthReportService iTractProjectMonthReportService;

	@Autowired
	private IProjectDatePreService projectDatePreService;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	@Autowired
	private IConstructionService constructionService;

	@Autowired
	private IDictionaryService dictionaryService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	@Autowired
	private ISingleProjectArrangeService iSingleProjectArrangeService;

	@Autowired
	private IMessageInfoService messageInfoService;

	@Autowired
	private IGovernmentAssignService governmentAssignService;

	@Autowired
	private ITractProjectChangeCardService tractProjectChangeCardService;

	@Autowired
	private IWorkFlowComponent workFlowComponent;

	@Autowired
	private IAllProjectSearchService iAllProjectSearchService;

	@Autowired
	private ITractProjectArrangeService tractProjectArrangeService;

	@Autowired
	private ITractProjectCreateService tractProjectCreateService;

	@Autowired
	private IProjectOwnerService projectOwnerService;

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private ICommonPorjectExcelService commonPorjectExcelService;
	
	@Autowired
	private ITractPolicyChangeService  tractPolicyChangeService;

	/**
	 * 分页查询子资料预审信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/selectsubproject")
	public Map<String, Object> findsubproject(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String arrangeId = request.getParameter("arrangeId");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ResultClassAuditlookproject> gridmodel = projectDatePreService
				.findsubproject(intPage, pagesize, sort, order, arrangeId);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 分页查询子资料预审信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/findMainAuditSubproject")
	public Map<String, Object> findMainAuditSubproject(
			HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String arrangeId = request.getParameter("arrangeId");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ResultClassAuditlookproject> gridmodel = projectDatePreService
				.findMainAuditSubproject(intPage, pagesize, sort, order,
						arrangeId);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 分页查询中介审核子资料预审信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/selectintersubproject")
	public Map<String, Object> selectintersubproject(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String arrangeId = request.getParameter("arrangeId");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ResultClassAuditlookproject> gridmodel = projectDatePreService
				.selectintersubproject(intPage, pagesize, sort, order,
						arrangeId);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 查询用户目前的消息数量
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findmessage")
	public Map<String, Object> findmessage(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		Integer num = 0;
		MessageInfo m = new MessageInfo();
		if (null != user) {
			m.setAcceptuser(user.getId());
		}
		m.setMessagestate(CommonConstant.NOREAD);
		num = messageInfoService.findjieshouMessageNum(m);
		map.put("num", num);
		return map;
	}

	/**
	 * 分页查询政府雇员审核子资料预审信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/selectgoversubproject")
	public Map<String, Object> selectgoversubproject(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String arrangeId = request.getParameter("arrangeId");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ResultClassAuditlookproject> gridmodel = projectDatePreService
				.selectgoversubproject(intPage, pagesize, sort, order,
						arrangeId);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 查询资料预审详细信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/finddata")
	public Datapreinfo finddata(HttpServletRequest request) {
		String id = request.getParameter("id");
		Datapreinfo data = projectDatePreService.findtoid(id);

		if (AuditStringUtils.isNotEmpty(data.getProownerid())) {
			// 项目业主
			ProjectOwner p = iProjectOwnerService.getProjectOwner(data
					.getProownerid());
			data.setOwnerName(p.getOwnerName());
		}
		/*
		 * if (AuditStringUtils.isNotEmpty(data.getConstructId())) { // 施工单位
		 * Construction c = constructionService.findbyid(data.getConstructId());
		 * if(null!=c){ data.setConstructName(c.getConstructname()); }
		 * 
		 * }
		 */
		if (AuditStringUtils.isNotEmpty(data.getAudittype())) {
			// 审计类型
			Dictionary d = dictionaryService.finddicbyid(data.getAudittype());
			data.setAudittype(d.getDictionaryname());
		}
		return data;
	}

	@RequestMapping("findOwner")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String projectOwnerName = request.getParameter("projectOwnerName");
		String state = request.getParameter("checkState");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1"
				: strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();

		GridDataModel<ProjectOwner> ProjectOwners = iProjectOwnerService.find(
				page, pagesize, name, order, projectOwnerName, state);
		map.put("rows", ProjectOwners.getRows());
		map.put("total", ProjectOwners.getTotal());
		return map;
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/download")
	public void downfile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getParameter("url");
		// 处理乱码
		if (AuditStringUtils.isNotEmpty(url)) {
			url = URLDecoder.decode(url, "UTF-8");
		}
		String fileName = ""; // 文件名，输出到用户的下载对话框
		// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
		String filePath = request.getSession().getServletContext().getRealPath(
				"/upload/" + url);
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("\\") + 1, filePath.length())
					.getBytes("GB2312"), "ISO8859_1");
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			// 设置响应头和保存文件名
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			// 写出流信息
			int b = 0;
			PrintWriter out = response.getWriter();
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
			fs.close();
			out.close();
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()
					+ "/common/errorFile.jsp");
		}
	}

	/**
	 * 查看文件
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/lookWord")
	public void lookWord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getParameter("url");
		// 处理乱码
		if (AuditStringUtils.isNotEmpty(url)) {
			url = URLDecoder.decode(url, "UTF-8");
		}
		String fileName = ""; // 文件名，输出到用户的下载对话框
		// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
		String filePath = request.getSession().getServletContext().getRealPath(
				"/upload/" + url);
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("\\") + 1, filePath.length())
					.getBytes("GB2312"), "ISO8859_1");
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			// 设置响应头和保存文件名
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "inline; filename=\""
					+ fileName + "\"");
			// 写出流信息
			int b = 0;
			PrintWriter out = response.getWriter();
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
			fs.close();
			out.close();
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()
					+ "/common/errorFile.jsp");
		}
	}

	@RequestMapping("findIntermediary")
	@ResponseBody
	public Map<String, Object> findIntermediary(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String intermediaryName = request.getParameter("intermediaryName");
		String bidyear = request.getParameter("bidyear");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1"
				: strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2"
				: rows);
		// 中介机构检索
		GridDataModel<Intermediaryagency> intermediaryagencys = iIntermediaryagencyService
				.find(page, pagesize, name, order, intermediaryName, bidyear);
		map.put("rows", intermediaryagencys.getRows());
		map.put("total", intermediaryagencys.getTotal());
		return map;
	}

	@RequestMapping("findConstruction")
	@ResponseBody
	public Map<String, Object> findConstruction(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		try {
			// 处理乱码
			if (AuditStringUtils.isNotEmpty(name)) {
				name = URLDecoder.decode(name, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<Construction> gridmodel = constructionService.find(
				intPage, pagesize, sort, order, name, state);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	@RequestMapping("findEmployee")
	@ResponseBody
	public Map<String, Object> findEmployee(HttpServletRequest request) {
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String searchUserName = request.getParameter("searchUserName");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1"
				: strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		// 人员检索
		GridDataModel<Employee> users = iSingleProjectArrangeService.find(page,
				pagesize, name, order, searchUserName);
		map.put("rows", users.getRows());
		map.put("total", users.getTotal());
		return map;
	}

	/**
	 * 查询该预审资料是否存在资料文件
	 * 
	 */
	@ResponseBody
	@RequestMapping("/findhavedatafile")
	public Map<String, Object> findhavedatafile(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取预审资料id
		String datapreId = request.getParameter("datapreId");
		Integer count = projectDatePreService.findFileBelongRelate(datapreId);
		map.put("count", count);
		return map;
	}

	/**
	 * 分页查询资料预审信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/findFile")
	public Map<String, Object> findFile(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String projectName = request.getParameter("projectName");
		String proownerid = request.getParameter("proownerid");
		String issubmit = request.getParameter("issubmit");

		try {
			// 处理乱码
			if (AuditStringUtils.isNotEmpty(projectName)) {
				projectName = URLDecoder.decode(projectName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String method = request.getParameter("method");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		Datapreinfo datapre = new Datapreinfo();
		datapre.setProjectName(projectName);
		datapre.setProownerid(proownerid);
		if (AuditStringUtils.isNotEmpty(issubmit)) {
			datapre.setIsconfirmSubmit(Integer.parseInt(issubmit));
		}
		FileBelongRelate file = new FileBelongRelate();
		file.setBelongToId(method);
		GridDataModel<FileBelongRelate> gridmodel = projectDatePreService
				.findfile(intPage, pagesize, sort, order, file);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 删除文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public Map<String, Object> deleteFile(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = null;
		String key = request.getParameter("key");
		boolean falgstate;
		try {
			map = new HashMap<String, Object>();
			falgstate = projectDatePreService.deletefile(key, request);
			if (falgstate) {
				map.put("success", "success");
				map.put("msg", "删除成功");
			} else {
				map.put("success", "fail");
				map.put("msg", "删除失败");
			}
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 删除导入模板文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteTempFile")
	@ResponseBody
	public Map<String, Object> deleteTempFile(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = null;
		String id = request.getParameter("id");
		boolean falgstate;
		try {
			map = new HashMap<String, Object>();
			falgstate = projectDatePreService.deletetempfile(id, request);
			if (falgstate) {
				map.put("success", "success");
				map.put("msg", "删除成功");
			} else {
				map.put("success", "fail");
				map.put("msg", "删除失败");
			}
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 增加项目暂停申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addprosup")
	@ResponseBody
	public Map<String, Object> addprosup(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = null;
		// 获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		// 获取暂停项目数据
		String datapreId = request.getParameter("zhiliaoid");
		String projectName = request.getParameter("project");
		String ownnerName = request.getParameter("ownnerName");
		String constructName = request.getParameter("constructName");
		String suspendRemark = request.getParameter("suspendRemark");
		// 定义项目暂停申请对象
		ProSuspendAuditInfo pro = new ProSuspendAuditInfo();
		pro.setId(AuditStringUtils.getUUID());
		pro.setDatapreId(datapreId);
		pro.setProjectName(projectName);
		pro.setOwnnerName(ownnerName);
		pro.setConstructName(constructName);
		pro.setSuspendRemark(suspendRemark);
		pro.setOperatorUser(user.getUserAccount());
		try {
			pro
					.setCreateTime(AuditStringUtils
							.getSystem("yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e1) {
		}
		try {
			map = new HashMap<String, Object>();
			int row = workFlowComponent.addProSupAuditInfo(pro);
			if (row > 0) {
				map.put("success", "success");
				map.put("msg", "项目暂停申请成功");
			} else {
				map.put("success", "fail");
				map.put("msg", "项目暂停申请失败");
			}
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 读取excel内容
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/writeexcel")
	public Map<String, Object> writeexcel(HttpServletRequest request,
			MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) throws IOException {
		// 获取文件
		List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
		InputStream fs = null;
		try {
			fs = file.get(0).getInputStream();
			Workbook book = Workbook.getWorkbook(fs);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到单元格
			for (int j = 0; j < sheet.getRows(); j++) {
				for (int i = 0; i < sheet.getColumns(); i++) {
					Cell cell = sheet.getCell(i, j);
					System.out
							.println("----------" + cell.getContents() + "  ");
				}
				System.out.println();
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 业主联系人 获取
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findlinkbyid")
	@ResponseBody
	public List<Map<String, String>> findlinkbyid(HttpServletRequest request) {
		List<Map<String, String>> m_listmap = new ArrayList<Map<String, String>>();
		// 获取参数
		String key = request.getParameter("key");
		// 查询联系人
		List<Link> listlink = constructionService.findLink(key);
		if (null != listlink && listlink.size() != 0) {
			for (int i = 0; i < listlink.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", listlink.get(i).getId().toString());
				map.put("referid", listlink.get(i).getReferid());
				map.put("linkname", listlink.get(i).getLinkname());
				map.put("linktellphone", listlink.get(i).getLinktellphone());
				m_listmap.add(map);
			}
		}
		return m_listmap;
	}

	/**
	 * 业主联系人电话获取
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findlinkbyphone")
	@ResponseBody
	public Map<String, String> findlinkbyphone(HttpServletRequest request) {
		// 获取参数
		String id = request.getParameter("id");
		Link link = constructionService.findLinkObject(id);
		Map<String, String> map = new HashMap<String, String>();
		if (null != link) {
			map.put("phone", link.getLinktellphone());
		} else {
			map.put("phone", "");
		}
		return map;
	}

	/**
	 * 业主联系人电话获取
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findMonthReportFile")
	@ResponseBody
	public Map<String, Object> findMonthReportFile(HttpServletRequest request) {
		String biaoDuanId = request.getParameter("biaoDuanId");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		FileBelongRelate fileBelongRelate = new FileBelongRelate();
		fileBelongRelate.setBelongToId(biaoDuanId);
		fileBelongRelate.setSort(order);
		fileBelongRelate.setFiled(sort);
		fileBelongRelate.setPageno(intPage);
		fileBelongRelate.setPagesize(pagesize);
		GridDataModel<FileBelongRelate> fileBelongRelates = iTractProjectMonthReportService
				.findMonthReportFile(fileBelongRelate);
		map.put("rows", fileBelongRelates.getRows());
		map.put("total", fileBelongRelates.getTotal());
		return map;
	}

	@RequestMapping("/findChangeCardFile")
	@ResponseBody
	public Map<String, Object> findChangeCardFile(HttpServletRequest request) {
		String biaoDuanId = request.getParameter("biaoDuanId");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> map = new HashMap<String, Object>();
		FileBelongRelate fileBelongRelate = new FileBelongRelate();
		fileBelongRelate.setBelongToId(biaoDuanId);
		fileBelongRelate.setSort(order);
		fileBelongRelate.setFiled(sort);
		fileBelongRelate.setPageno(intPage);
		fileBelongRelate.setPagesize(pagesize);
		GridDataModel<FileBelongRelate> fileBelongRelates = tractProjectChangeCardService
				.findfile(fileBelongRelate);
		map.put("rows", fileBelongRelates.getRows());
		map.put("total", fileBelongRelates.getTotal());
		return map;
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/outexcel")
	public void outexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取交办id
		String assignId = request.getParameter("assignId");
		// 查询交下面的项目信息
		List<ResultClassProConference> list = governmentAssignService
				.findAllAssignProject(assignId);
		// 查询交办信息
		ProGovernmentAssign progov = governmentAssignService
				.getAssignInfo(assignId);
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/file");
		// 删除该目录下面所有excel
		// 删除该目录下面的所有文件
		File newfile = new File(url);
		File[] files = newfile.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String a = url + "/项目批次信息" + sdf.format(new Date()) + ".xls";
		FileOutputStream os = new FileOutputStream(a);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		s.addMergedRegion(new Region(0, (short) 0, 0, (short) 12));
		// 设置列宽度
		s.setColumnWidth(0, 2500);
		s.setColumnWidth(1, 4500);
		s.setColumnWidth(2, 4000);
		s.setColumnWidth(3, 5500);
		s.setColumnWidth(4, 3500);
		s.setColumnWidth(5, 6000);
		s.setColumnWidth(6, 4500);
		s.setColumnWidth(7, 4000);
		s.setColumnWidth(8, 7000);
		s.setColumnWidth(9, 5000);
		s.setColumnWidth(10, 5000);
		s.setColumnWidth(11, 5000);
		s.setColumnWidth(12, 5000);

		// 设置样式
		HSSFCellStyle style = wb.createCellStyle();
		wb.setSheetName(0, "项目批次信息");
		// 创建第一行
		HSSFRow row = s.createRow(0);
		// 第一行第一列
		HSSFCell cell = row.createCell((short) 0);
		// 第一行第一列
		HSSFFont font = wb.createFont();
		// 设定字体大小
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 加入样式
		cell.setCellStyle(style);
		// 设置该列的参数
		cell.setCellValue(sf.format(new Date()) + "年政府投资审计"
				+ progov.getReportBatch());
		// 设置单元格普通样式
		style = wb.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);// 设置自动换行
		// 设置excel单元名称
		row = s.createRow(1);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("工程编号");
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("题 名");
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("项目立项文号");
		cell = row.createCell((short) 3);
		cell.setCellStyle(style);
		cell.setCellValue("被审计单位");
		cell = row.createCell((short) 4);
		cell.setCellStyle(style);
		cell.setCellValue("主审");
		cell = row.createCell((short) 5);
		cell.setCellStyle(style);
		cell.setCellValue("事务所名称");
		cell = row.createCell((short) 6);
		cell.setCellStyle(style);
		cell.setCellValue("事务所签字");
		cell = row.createCell((short) 7);
		cell.setCellStyle(style);
		cell.setCellValue("复核工程师");
		cell = row.createCell((short) 8);
		cell.setCellStyle(style);
		cell.setCellValue("施工单位");
		cell = row.createCell((short) 9);
		cell.setCellStyle(style);
		cell.setCellValue("送审金额（元）");

		cell = row.createCell((short) 10);
		cell.setCellStyle(style);
		cell.setCellValue("接收资料时间");
		cell = row.createCell((short) 11);
		cell.setCellStyle(style);
		cell.setCellValue("事务所接收资料时间");
		cell = row.createCell((short) 12);
		cell.setCellStyle(style);
		cell.setCellValue("事务所应完成时间");

		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				// 创建行
				row = s.createRow(2 + i);
				// 设置行高
				row.setHeight((short) 700);
				cell = row.createCell((short) 0);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDatapreno());
				cell = row.createCell((short) 1);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectName());
				cell = row.createCell((short) 2);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectNo());
				cell = row.createCell((short) 3);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProownerid());
				cell = row.createCell((short) 4);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getMainAuditId());
				// 判断是否自审
				if (!AuditStringUtils
						.isNotEmpty(list.get(i).getIsMySelfState())) {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getIntermediaryId());
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					cell.setCellValue("");
					cell = row.createCell((short) 7);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getGovernmentEmp());
				} else {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					cell.setCellValue("自审(" + list.get(i).getGovernmentEmp()
							+ ")");
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					cell.setCellValue("");
					cell = row.createCell((short) 7);
					cell.setCellStyle(style);
					cell.setCellValue("");
				}

				cell = row.createCell((short) 8);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getShigongunit());
				cell = row.createCell((short) 9);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getSendMoney());
				cell = row.createCell((short) 10);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i).getDatapretime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getDatapretime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 11);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediarySendTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediarySendTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 12);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediaryAuditTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediaryAuditTime()));
				} else {
					cell.setCellValue("");
				}
			}
		}
		// 写入
		wb.write(os);
		// 关闭
		os.flush();
		os.close();
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(a));
			String filename = "项目批次信息" + sdf.format(new Date()) + ".xls";
			filename = new String(filename.getBytes("GB2312"), "ISO8859_1");
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}
	
	
	
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/outArrangeexcel")
	public void outArrangeexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//查询所有交办集合
		List<ProGovernmentAssign> listassign=  governmentAssignService.findAllAssignInfo();
		String url = request.getSession().getServletContext().getRealPath(
		"/upload/template/file");
		// 删除该目录下面所有excel
		// 删除该目录下面的所有文件
		File newfile = new File(url);
		File[] files = newfile.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String a = url + "/项目批次信息" + sdf.format(new Date()) + ".xls";
		FileOutputStream os = new FileOutputStream(a);
		HSSFWorkbook wb = new HSSFWorkbook();
		for(int m=0;m<=listassign.size();m++)
		{
		HSSFSheet s = wb.createSheet();	
		
		List<ResultClassProConference> list=null;
		ProGovernmentAssign progov=null;
		if(m==listassign.size()){
			list = governmentAssignService.findAllNoAssignProject();
		}else{
			list = governmentAssignService.findAllAssignProject(listassign.get(m).getId());
			// 查询交办信息
		    progov = governmentAssignService.getAssignInfo(listassign.get(m).getId());
		}
		s.addMergedRegion(new Region(0, (short) 0, 0, (short) 12));
		// 设置列宽度
		s.setColumnWidth(0, 2500);
		s.setColumnWidth(1, 4500);
		s.setColumnWidth(2, 4000);
		s.setColumnWidth(3, 5500);
		s.setColumnWidth(4, 3500);
		s.setColumnWidth(5, 6000);
		s.setColumnWidth(6, 4500);
		s.setColumnWidth(7, 4000);
		s.setColumnWidth(8, 7000);
		s.setColumnWidth(9, 5000);
		s.setColumnWidth(10, 5000);
		s.setColumnWidth(11, 5000);
		s.setColumnWidth(12, 5000);

		// 设置样式
		HSSFCellStyle style = wb.createCellStyle();
		if(m==listassign.size()){
			wb.setSheetName(m, "无批次项目信息");
		}else{
			wb.setSheetName(m, progov.getReportBatch());
		}
		
		// 创建第一行
		HSSFRow row = s.createRow(0);
		// 第一行第一列
		HSSFCell cell = row.createCell((short) 0);
		// 第一行第一列
		HSSFFont font = wb.createFont();
		// 设定字体大小
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 加入样式
		cell.setCellStyle(style);
		// 设置该列的参数
		if(m==listassign.size()){
			 cell.setCellValue("无批次项目信息");
		}else
		{
		  cell.setCellValue(sf.format(new Date()) + "年政府投资审计"+ progov.getReportBatch());
		}
		// 设置单元格普通样式
		style = wb.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);// 设置自动换行
		// 设置excel单元名称
		row = s.createRow(1);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("工程编号");
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("题 名");
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("项目立项文号");
		cell = row.createCell((short) 3);
		cell.setCellStyle(style);
		cell.setCellValue("被审计单位");
		cell = row.createCell((short) 4);
		cell.setCellStyle(style);
		cell.setCellValue("主审");
		cell = row.createCell((short) 5);
		cell.setCellStyle(style);
		cell.setCellValue("事务所名称");
		cell = row.createCell((short) 6);
		cell.setCellStyle(style);
		cell.setCellValue("事务所签字");
		cell = row.createCell((short) 7);
		cell.setCellStyle(style);
		cell.setCellValue("复核工程师");
		cell = row.createCell((short) 8);
		cell.setCellStyle(style);
		cell.setCellValue("施工单位");
		cell = row.createCell((short) 9);
		cell.setCellStyle(style);
		cell.setCellValue("送审金额（元）");

		cell = row.createCell((short) 10);
		cell.setCellStyle(style);
		cell.setCellValue("接收资料时间");
		cell = row.createCell((short) 11);
		cell.setCellStyle(style);
		cell.setCellValue("事务所接收资料时间");
		cell = row.createCell((short) 12);
		cell.setCellStyle(style);
		cell.setCellValue("事务所应完成时间");
		  if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				// 创建行
				row = s.createRow(2 + i);
				// 设置行高
				row.setHeight((short) 700);
				cell = row.createCell((short) 0);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDatapreno());
				cell = row.createCell((short) 1);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectName());
				cell = row.createCell((short) 2);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectNo());
				cell = row.createCell((short) 3);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProownerid());
				cell = row.createCell((short) 4);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getMainAuditId());
				// 判断是否自审
				if (!AuditStringUtils
						.isNotEmpty(list.get(i).getIsMySelfState())) {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getIntermediaryId());
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					cell.setCellValue("");
					cell = row.createCell((short) 7);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getGovernmentEmp());
				} else {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					cell.setCellValue("自审(" + list.get(i).getGovernmentEmp()
							+ ")");
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					cell.setCellValue("");
					cell = row.createCell((short) 7);
					cell.setCellStyle(style);
					cell.setCellValue("");
				}

				cell = row.createCell((short) 8);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getShigongunit());
				cell = row.createCell((short) 9);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getSendMoney());
				cell = row.createCell((short) 10);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i).getDatapretime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getDatapretime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 11);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediarySendTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediarySendTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 12);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediaryAuditTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediaryAuditTime()));
				} else {
					cell.setCellValue("");
				}
			}
		}
		
		
		}
		
		
		
		
		
		// 写入
		wb.write(os);
		// 关闭
		os.flush();
		os.close();
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(a));
			String filename = "项目批次信息" + sdf.format(new Date()) + ".xls";
			filename = new String(filename.getBytes("GB2312"), "ISO8859_1");
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}
	
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/shenjihuizong")
	public void shenjihuizong(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getSession().getServletContext().getRealPath("/upload/template/file");
		// 删除该目录下面所有excel
		// 删除该目录下面的所有文件
		File newfile = new File(url);
		File[] files = newfile.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
		// 获取政府交办id
		String id = request.getParameter("id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 查询项目交办的是决算还是竣工
		List<Datapreinfo> audittype = projectDatePreService.findproaudittype(id);
		String method = "1";
		for (Datapreinfo type : audittype) {
			if (PropertiesGetValue.getContextProperty("gengaudittype").equals(type.getAudittype())) {
				method = "2";
				break;
			}
		}
		String a = "";
		String name="";
		if ("1".equals(method)) {
			a = url + "/金牛区审计局政府投资建设工程竣工决算审计请示汇总表" + sdf.format(new Date()) + ".xls";
			name="金牛区审计局政府投资建设工程竣工决算审计请示汇总表";
		} else {
			a = url + "/金牛区审计局政府投资建设工程跟踪审计请示汇总表" + sdf.format(new Date()) + ".xls";
			name="金牛区审计局政府投资建设工程跟踪审计请示汇总表";
		}
		
		FileOutputStream os = new FileOutputStream(a);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		s.addMergedRegion(new Region(0, (short) 0, 0, (short) 2));
		// 设置列宽度
		s.setColumnWidth(0, 2500);
		s.setColumnWidth(1, 7500);
		s.setColumnWidth(2, 17000);
		// 设置样式
		HSSFCellStyle style = wb.createCellStyle();
		wb.setSheetName(0, "审计请示汇总表");
		// 创建第一行
		HSSFRow row = s.createRow(0);
		// 第一行第一列
		HSSFCell cell = row.createCell((short) 0);
		// 第一行第一列
		HSSFFont font = wb.createFont();
		// 设定字体大小
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 加入样式
		cell.setCellStyle(style);
		// 设置该列的参数
		cell.setCellValue(name);
		// 设置单元格普通样式
		style = wb.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);// 设置自动换行
		// 设置excel单元名称
		row = s.createRow(1);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("序号");
		
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("组织实施单位");
		
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("工程名称");
		DataWord dw = new DataWord();
		dw.setGovernmentAssignId(id);
		// 查询施工单位下面的工程名称
		List<Datapreinfo> listdata = projectDatePreService.findProjectName(dw);
		if(null!=listdata && listdata.size()!=0){
			for(int j=0;j<listdata.size();j++){
				// 创建行
				row = s.createRow(2 + j);
				// 设置行高
				row.setHeight((short) 700);
				cell = row.createCell((short) 0);
				cell.setCellStyle(style);
				cell.setCellValue(j+1);
				cell = row.createCell((short) 1);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(listdata.get(j).getProownerid())) {
					// 查询项目业主
					ProjectOwner proowner = iProjectOwnerService.getProjectOwner(listdata.get(j).getProownerid());
					if(null!=proowner){
						cell.setCellValue(proowner.getOwnerName());
					}else{
						cell.setCellValue("");
					}
				}else{
					cell.setCellValue("");
				}
				cell = row.createCell((short) 2);
				cell.setCellStyle(style);
				cell.setCellValue(listdata.get(j).getProjectName());
			}
		}
		
		// 写入
		wb.write(os);
		// 关闭
		os.flush();
		os.close();
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(a));
			String filename = name+"" + sdf.format(new Date()) + ".xls";
			filename = new String(filename.getBytes("GB2312"), "ISO8859_1");
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 查询属于该用户的标段是否存在月报信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/haveuserMonth")
	@ResponseBody
	public Map<String, Object> haveuserMonth(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = null;
		// 获取当前登陆用户
		User user = (User) request.getSession().getAttribute("user");
		// 获取所有的标段信息
		List<TractArrangeProjectInfo> listbd = tractProjectArrangeService
				.findBdByUserid(user.getId());

		// 根据标段查询所有的月份信息
		List<String> liststr = tractProjectArrangeService
				.findMonthByBdid(listbd);
		try {
			map = new HashMap<String, Object>();
			if (null != liststr && liststr.size() != 0) {
				map.put("success", "success");
			} else {
				map.put("success", "fail");
			}
		} catch (Exception e) {
			map.put("success", "fail");
		}
		return map;
	}

	/**
	 * 跟踪审计台账导出excel
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/tractoneExcel")
	public void tractoneExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前登陆用户
		User user = (User) request.getSession().getAttribute("user");
		// 获取所有的标段信息
		List<TractArrangeProjectInfo> listbd = tractProjectArrangeService
				.findBdByUserid(user.getId());

		// 根据标段查询所有的月份信息
		List<String> liststr = tractProjectArrangeService
				.findMonthByBdid(listbd);
		String result = "";
		if (null != liststr && liststr.size() != 0) {

			// 循环时间去掉重复年月
			for (int i = 0; i < liststr.size(); i++) {
				// 判断是否为空
				if (AuditStringUtils.isNotEmpty(liststr.get(i))) {
					if (result.indexOf(AuditStringUtils.getYYYYMM(liststr
							.get(i))) == -1) {
						result = result
								+ AuditStringUtils.getYYYYMM(liststr.get(i))
								+ ",";

					}

				}
			}
		}
		// 去掉最后一个字符
		if (!"".equals(result)) {
			result = result.substring(0, result.length() - 1);
		}
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/file");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String a = url + "/跟踪审计台账.xls";
		if (null != user) {
			a = url + "/跟踪审计台账(" + user.getUsername() + ")"
					+ sdf.format(new Date()) + ".xls";
		}
		FileOutputStream os = new FileOutputStream(a);

		HSSFWorkbook wb = new HSSFWorkbook();

		if (AuditStringUtils.isNotEmpty(result)) {
			// 循环月份
			String[] months = result.split(",");

			// 循环创建月份的sheet
			for (int i = 0; i < months.length; i++) {
				HSSFSheet s = wb.createSheet();
				s.addMergedRegion(new Region(0, (short) 0, 0, (short) 38));
				// 设置列宽度
				s.setColumnWidth(0, 1300);
				s.setColumnWidth(1, 3500);
				s.setColumnWidth(2, 2000);
				s.setColumnWidth(3, 1500);
				s.setColumnWidth(4, 3000);
				s.setColumnWidth(5, 2000);
				s.setColumnWidth(6, 2500);
				s.setColumnWidth(7, 2500);
				s.setColumnWidth(8, 2500);
				s.setColumnWidth(9, 3000);
				s.setColumnWidth(10, 3000);
				s.setColumnWidth(11, 3000);
				s.setColumnWidth(12, 3000);

				s.setColumnWidth(13, 4500);
				s.setColumnWidth(14, 4500);
				s.setColumnWidth(15, 4500);
				s.setColumnWidth(16, 4500);
				s.setColumnWidth(17, 3500);
				s.setColumnWidth(18, 3800);
				s.setColumnWidth(19, 2700);
				s.setColumnWidth(20, 3500);
				s.setColumnWidth(21, 4800);
				s.setColumnWidth(22, 1700);
				s.setColumnWidth(23, 1700);
				s.setColumnWidth(24, 1700);
				s.setColumnWidth(25, 1700);
				s.setColumnWidth(26, 1700);
				s.setColumnWidth(27, 1700);
				s.setColumnWidth(28, 1700);
				s.setColumnWidth(29, 1700);
				s.setColumnWidth(30, 3000);
				s.setColumnWidth(31, 4000);
				s.setColumnWidth(32, 2500);
				s.setColumnWidth(33, 2000);
				s.setColumnWidth(34, 4800);
				s.setColumnWidth(35, 3000);
				s.setColumnWidth(36, 2500);
				s.setColumnWidth(37, 3000);
				s.setColumnWidth(38, 3000);
				String[] time = months[i].toString().split("-");
				wb.setSheetName(i, time[0] + "年" + time[1] + "月");
				// 设置样式
				HSSFCellStyle style = wb.createCellStyle();

				// 创建第一行
				HSSFRow row = s.createRow(0);
				// 第一行第一列
				HSSFCell cell = row.createCell((short) 0);
				// 第一行第一列
				HSSFFont font = wb.createFont();
				// 设定字体大小
				font.setFontHeightInPoints((short) 15);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
				style.setFont(font);
				style.setAlignment((short) 2);
				style.setVerticalAlignment((short) 1);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
				// 加入样式
				cell.setCellStyle(style);
				// 设置该列的参数
				cell.setCellValue("跟踪审计台账(" + user.getUsername() + ")");
				// 设置单元格普通样式
				style = wb.createCellStyle();
				font = wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
				style.setFont(font);
				style.setAlignment((short) 2);
				style.setVerticalAlignment((short) 1);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
				style.setWrapText(true);// 设置自动换行
				// 设置excel单元名称
				row = s.createRow(1);
				// 设置行高
				row.setHeight((short) 600);
				cell = row.createCell((short) 0);
				cell.setCellStyle(style);
				cell.setCellValue("序号");

				cell = row.createCell((short) 1);
				cell.setCellStyle(style);
				cell.setCellValue("工程名称");
				cell = row.createCell((short) 2);
				cell.setCellStyle(style);
				cell.setCellValue("建设方");
				cell = row.createCell((short) 3);
				cell.setCellStyle(style);
				cell.setCellValue("主审");
				cell = row.createCell((short) 4);
				cell.setCellStyle(style);
				cell.setCellValue("中介机构");
				cell = row.createCell((short) 5);
				cell.setCellStyle(style);
				cell.setCellValue("施工方");
				cell = row.createCell((short) 6);
				cell.setCellStyle(style);
				cell.setCellValue("工程概况");
				cell = row.createCell((short) 7);
				cell.setCellStyle(style);
				cell.setCellValue("招标情况");
				cell = row.createCell((short) 8);
				cell.setCellStyle(style);
				cell.setCellValue("标段划分");
				cell = row.createCell((short) 9);
				cell.setCellStyle(style);
				cell.setCellValue("立项批复文号");

				cell = row.createCell((short) 10);
				cell.setCellStyle(style);
				cell.setCellValue("立项批复金额(万元)");
				cell = row.createCell((short) 11);
				cell.setCellStyle(style);
				cell.setCellValue("概算批复文号");
				cell = row.createCell((short) 12);
				cell.setCellStyle(style);
				cell.setCellValue("概算批复金额(万元)");

				cell = row.createCell((short) 13);
				cell.setCellStyle(style);
				cell.setCellValue("预算控制价(万元)小计");
				cell = row.createCell((short) 14);
				cell.setCellStyle(style);
				cell.setCellValue("预算控制价(万元)其中预留金");
				cell = row.createCell((short) 15);
				cell.setCellStyle(style);
				cell.setCellValue("中标合同价(万元)小计");
				cell = row.createCell((short) 16);
				cell.setCellStyle(style);
				cell.setCellValue("中标合同价(万元)其中预留金");
				cell = row.createCell((short) 17);
				cell.setCellStyle(style);
				cell.setCellValue("累计已完产值");
				cell = row.createCell((short) 18);
				cell.setCellStyle(style);
				cell.setCellValue("累计已付工程款");
				cell = row.createCell((short) 19);
				cell.setCellStyle(style);
				cell.setCellValue("开工日期");
				cell = row.createCell((short) 20);
				cell.setCellStyle(style);
				cell.setCellValue("工程形象进度");
				cell = row.createCell((short) 21);
				cell.setCellStyle(style);
				cell.setCellValue("存在问题及解决方案");
				cell = row.createCell((short) 22);
				cell.setCellStyle(style);
				cell.setCellValue("工程量差");
				cell = row.createCell((short) 23);
				cell.setCellStyle(style);
				cell.setCellValue("设计变更");
				cell = row.createCell((short) 24);
				cell.setCellStyle(style);
				cell.setCellValue("技术核定");
				cell = row.createCell((short) 25);
				cell.setCellStyle(style);
				cell.setCellValue("现场签证");

				cell = row.createCell((short) 26);
				cell.setCellStyle(style);
				cell.setCellValue("漏项增项");
				cell = row.createCell((short) 27);
				cell.setCellStyle(style);
				cell.setCellValue("预估金额");
				cell = row.createCell((short) 28);
				cell.setCellStyle(style);
				cell.setCellValue("人工调整");
				cell = row.createCell((short) 29);
				cell.setCellStyle(style);
				cell.setCellValue("材料调整");
				cell = row.createCell((short) 30);
				cell.setCellStyle(style);
				cell.setCellValue("小计");
				cell = row.createCell((short) 31);
				cell.setCellStyle(style);
				cell.setCellValue("预估剩余预留金");
				cell = row.createCell((short) 32);
				cell.setCellStyle(style);
				cell.setCellValue("送审金额");

				cell = row.createCell((short) 33);
				cell.setCellStyle(style);
				cell.setCellValue("清标价");
				cell = row.createCell((short) 34);
				cell.setCellStyle(style);
				cell.setCellValue("已付中介机构审核费");
				cell = row.createCell((short) 35);
				cell.setCellStyle(style);
				cell.setCellValue("开竣工时间");
				cell = row.createCell((short) 36);
				cell.setCellStyle(style);
				cell.setCellValue("工作日志");
				cell = row.createCell((short) 37);
				cell.setCellStyle(style);
				cell.setCellValue("电子版月报");
				cell = row.createCell((short) 38);
				cell.setCellStyle(style);
				cell.setCellValue("书面版月报");

				// 跟踪月份查询标段
				List<TractMonthReportInfo> list = tractProjectArrangeService
						.findBiaoDuanByTime(months[i].toString());
				// 去掉重复标段信息
				String biaoduanStr = "";
				if (null != list && list.size() != 0) {
					for (int h = 0; h < list.size(); h++) {
						// 判断是否为空
						if (AuditStringUtils.isNotEmpty(list.get(h)
								.getBiaoDuanId())) {
							if (biaoduanStr
									.indexOf(list.get(h).getBiaoDuanId()) == -1) {
								biaoduanStr = biaoduanStr
										+ list.get(h).getBiaoDuanId() + ",";

							}

						}
					}
				}
				// 去掉最后一个字符
				if (!"".equals(biaoduanStr)) {
					biaoduanStr = biaoduanStr.substring(0,
							biaoduanStr.length() - 1);
				}

				style = wb.createCellStyle();
				style.setAlignment((short) 2);
				style.setVerticalAlignment((short) 1);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
				style.setWrapText(true);// 设置自动换行

				String[] bds = biaoduanStr.split(",");
				for (int m = 0; m < bds.length; m++) {
					// 创建行
					row = s.createRow(2 + m);
					// 设置行高
					row.setHeight((short) 2500);
					cell = row.createCell((short) 0);
					cell.setCellStyle(style);
					cell.setCellValue(m + 1);
					cell = row.createCell((short) 1);
					cell.setCellStyle(style);
					// 查询标段名称
					ProejctBiaoDuanInfo bd = tractProjectCreateService
							.getProejctBiaoDuanInfo(bds[m]);
					cell.setCellValue(bd.getBiaoDuanName());
					cell = row.createCell((short) 2);
					cell.setCellStyle(style);
					// 查询建设单位
					ProejctBiaoDuanInfo pro = tractProjectArrangeService
							.findOwnerbyBdid(bd.getProjectId());
					ProjectOwner proowner = projectOwnerService
							.getProjectOwner(pro.getOwnerId());
					cell.setCellValue(proowner.getOwnerName());
					cell = row.createCell((short) 3);
					cell.setCellStyle(style);
					// 查询主审
					TractArrangeProjectInfo tractArrange = tractProjectArrangeService
							.getTractArrangeProjectInfo(bds[m]);
					EditUser u = userInfoService.findbyid(tractArrange
							.getMainAuditId());
					cell.setCellValue(u.getUsername());
					cell = row.createCell((short) 4);
					cell.setCellStyle(style);
					// 中介机构
					Intermediaryagency inter = iIntermediaryagencyService
							.getIntermediaryagency(tractArrange
									.getIntermediaryId());
					if (null != inter) {
						cell.setCellValue(inter.getIntermediaryname());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					// 施工方
					cell.setCellValue(bd.getConstructUtil());
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					// 工程概况
					cell.setCellValue(bd.getProjectGaiKuang());
					cell = row.createCell((short) 7);
					cell.setCellStyle(style);
					// 招标情况
					if ("0".equals(pro.getProjectType())) {
						cell.setCellValue("公开招标");
					} else if ("1".equals(pro.getProjectType())) {
						cell.setCellValue("EPC");
					} else if ("2".equals(pro.getProjectType())) {
						cell.setCellValue("BT");
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 8);
					cell.setCellStyle(style);
					// 标段划分
					cell.setCellValue("");
					cell = row.createCell((short) 9);
					cell.setCellStyle(style);
					// 立项批复文号
					cell.setCellValue(pro.getProjectCreateNo());
					cell = row.createCell((short) 10);
					cell.setCellStyle(style);
					// 立项批复金额
					cell.setCellValue(pro.getSentAuditMoney());
					cell = row.createCell((short) 11);
					cell.setCellStyle(style);
					// 概算批复文号
					cell.setCellValue(pro.getBudgetCode());
					cell = row.createCell((short) 12);
					cell.setCellStyle(style);
					// 概算批复金额
					cell.setCellValue(pro.getBudgetMoney());
					cell = row.createCell((short) 13);
					cell.setCellStyle(style);

					// 预算控制价(万元)小计preAuditMoney
					Double preAuditMoney = Double.parseDouble(bd
							.getPreAuditMoney());
					preAuditMoney = preAuditMoney / 10000.00;
					cell.setCellValue(preAuditMoney.toString());
					cell = row.createCell((short) 14);
					cell.setCellStyle(style);
					cell.setCellValue("");
					cell = row.createCell((short) 15);
					cell.setCellStyle(style);
					// 中标合同价(万元)小计
					Double zhongBiaoMoney = Double.parseDouble(bd
							.getZhongBiaoMoney());
					zhongBiaoMoney = zhongBiaoMoney / 10000.00;
					cell.setCellValue(zhongBiaoMoney.toString());
					cell = row.createCell((short) 16);
					cell.setCellStyle(style);
					cell.setCellValue("");
					cell = row.createCell((short) 17);
					cell.setCellStyle(style);

					TractMonthReportInfo month = tractProjectArrangeService
							.findMonthReportInfoByBdtime(bds[m], months[i]
									.toString());
					// 累计已完产值
					if (AuditStringUtils.isNotEmpty(month
							.getTotalCompleteValue())) {
						Double totalCompleteValue = Double.parseDouble(month
								.getTotalCompleteValue());
						totalCompleteValue = totalCompleteValue / 10000.00;
						cell.setCellValue(totalCompleteValue.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 18);
					cell.setCellStyle(style);
					// 累计已付工程款
					if (AuditStringUtils.isNotEmpty(month
							.getAddPayProjectMoney())) {
						Double addPayProjectMoney = Double.parseDouble(month
								.getAddPayProjectMoney());
						addPayProjectMoney = addPayProjectMoney / 10000.00;
						cell.setCellValue(addPayProjectMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 19);
					cell.setCellStyle(style);
					// 开工日期
					if (AuditStringUtils.isNotEmpty(bd.getReallyStartTime())) {
						cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(bd
								.getReallyStartTime()));
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 20);
					cell.setCellStyle(style);
					// 工程形象进度
					cell.setCellValue(month.getProjectImagePlan());
					cell = row.createCell((short) 21);
					cell.setCellStyle(style);
					// 存在问题及解决方案
					cell.setCellValue(month.getExistProblem());
					cell = row.createCell((short) 22);
					cell.setCellStyle(style);
					Double xjMoney = 0.0;
					// 工程量差
					TractProjectChangeCardInfo change1 = tractProjectArrangeService
							.findProjectChangeCardTotalMoney(bds[m], months[i]
									.toString(), "1");
					if (AuditStringUtils.isNotEmpty(change1.getTotalMoney())) {
						Double totalMoney = Double.parseDouble(change1
								.getTotalMoney());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 23);
					cell.setCellStyle(style);
					// 设计变更
					TractProjectChangeCardInfo change2 = tractProjectArrangeService
							.findProjectChangeCardTotalMoney(bds[m], months[i]
									.toString(), "2");
					if (AuditStringUtils.isNotEmpty(change2.getTotalMoney())) {
						Double totalMoney = Double.parseDouble(change2
								.getTotalMoney());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 24);
					cell.setCellStyle(style);
					// 技术核定
					TractProjectChangeCardInfo change3 = tractProjectArrangeService
							.findProjectChangeCardTotalMoney(bds[m], months[i]
									.toString(), "3");
					if (AuditStringUtils.isNotEmpty(change3.getTotalMoney())) {
						Double totalMoney = Double.parseDouble(change3
								.getTotalMoney());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 25);
					cell.setCellStyle(style);
					// 现场签证
					TractProjectChangeCardInfo change4 = tractProjectArrangeService
							.findProjectChangeCardTotalMoney(bds[m], months[i]
									.toString(), "4");
					if (AuditStringUtils.isNotEmpty(change4.getTotalMoney())) {
						Double totalMoney = Double.parseDouble(change4
								.getTotalMoney());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 26);
					cell.setCellStyle(style);
					// 漏项增项
					TractProjectChangeCardInfo change5 = tractProjectArrangeService
							.findProjectChangeCardTotalMoney(bds[m], months[i]
									.toString(), "0");
					if (AuditStringUtils.isNotEmpty(change5.getTotalMoney())) {
						Double totalMoney = Double.parseDouble(change5
								.getTotalMoney());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 27);
					cell.setCellStyle(style);
					// 预估金额
					cell.setCellValue("");
					cell = row.createCell((short) 28);
					cell.setCellStyle(style);
					// 人工调整

					TractPolicyChange policy = tractProjectArrangeService
							.findTractPolicyChange(bds[m], months[i].toString());
					if (AuditStringUtils.isNotEmpty(policy.getArtificialfile())) {
						Double totalMoney = Double.parseDouble(policy
								.getArtificialfile());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 29);
					cell.setCellStyle(style);
					// 材料调整
					if (AuditStringUtils.isNotEmpty(policy.getDatamoney())) {
						Double totalMoney = Double.parseDouble(policy
								.getDatamoney());
						totalMoney = totalMoney / 10000.00;
						xjMoney = xjMoney + totalMoney;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 30);
					cell.setCellStyle(style);
					// 小计
					cell.setCellValue(xjMoney);
					cell = row.createCell((short) 31);
					cell.setCellStyle(style);
					// 预估剩余预留金
					cell.setCellValue("");
					cell = row.createCell((short) 32);
					cell.setCellStyle(style);
					// 送审金额
					cell.setCellValue("");
					cell = row.createCell((short) 33);
					cell.setCellStyle(style);
					// 清标价
					TractProjectQingBiao qb = tractProjectArrangeService
							.findQingBiaoMoney(bds[m]);
					if (AuditStringUtils.isNotEmpty(qb.getAfterMoney())) {
						Double totalMoney = Double.parseDouble(qb
								.getAfterMoney());
						totalMoney = totalMoney / 10000.00;
						cell.setCellValue(totalMoney.toString());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 34);
					cell.setCellStyle(style);
					// 已付中介机构审核费
					cell.setCellValue("");
					cell = row.createCell((short) 35);
					cell.setCellStyle(style);
					// 开竣工时间
					cell.setCellValue("");
					cell = row.createCell((short) 36);
					cell.setCellStyle(style);
					// 工作日志
					cell.setCellValue("");
					cell = row.createCell((short) 37);
					cell.setCellStyle(style);
					// 电子版月报
					cell.setCellValue("");
					cell = row.createCell((short) 38);
					cell.setCellStyle(style);
					// 书面版月报
					cell.setCellValue("");

				}

			}
		}

		// 写入
		wb.write(os);
		// 关闭
		os.flush();
		os.close();

		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(a));
			String filename = "跟踪审计台账(" + user.getUsername() + ")"
					+ sdf.format(new Date()) + ".xls";
			filename = new String(filename.getBytes("GB2312"), "ISO8859_1");
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}

	@RequestMapping("/getDataPreInfo")
	@ResponseBody
	public Map<String, Object> getDataPreInfo(HttpServletRequest request) {
		String projectId = request.getParameter("projectId");
		Map<String, Object> map = new HashMap<String, Object>();
		Datapreinfo datapreinfo = iAllProjectSearchService
				.getDatapreinfo(projectId);
		map.put("projectName", datapreinfo.getProjectName());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("projectNo", datapreinfo.getProjectNo());
		map.put("auditMoney", datapreinfo.getAuditMoney());
		map.put("projectTime", datapreinfo.getProjectTime());
		map.put("ownerName", datapreinfo.getOwnerName());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		map.put("datapreno", datapreinfo.getDatapreno());
		return map;
	}

	/**
	 * 导出审计项目未完成情况Excel
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/importprojectexcel")
	public void importprojectexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取审计时间
		String auditStartTime = request.getParameter("auditStartTime");
		// 获取项目业主
		String projectOwner = request.getParameter("projectOwner");
		// 获取项目名称
		String projectName = URLDecoder.decode(request
				.getParameter("projectName"), "UTF-8");
		// 定义对象
		SearchProjectBaseInfoResult pro = new SearchProjectBaseInfoResult();
		pro.setDatapretime(auditStartTime);
		pro.setProownerid(projectOwner);
		pro.setProjectName(projectName);
		// 调用业务层查询项目相应审计信息
		List<SearchProjectBaseInfoResult> list = commonPorjectExcelService
				.findProjectBaseInfo(pro);
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/file");
		// 删除该目录下面所有excel
		// 删除该目录下面的所有文件
		File newfile = new File(url);
		File[] files = newfile.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String a = url + "/项目审计工作情况表" + sdf.format(new Date()) + ".xls";
		FileOutputStream os = new FileOutputStream(a);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		s.addMergedRegion(new Region(0, (short) 0, 0, (short) 35));
		// 设置列宽度
		s.setColumnWidth(0, 2500);
		s.setColumnWidth(1, 9000);
		s.setColumnWidth(2, 4000);
		s.setColumnWidth(3, 5500);
		s.setColumnWidth(4, 3500);
		s.setColumnWidth(5, 6000);
		s.setColumnWidth(6, 4500);
		s.setColumnWidth(7, 8000);
		s.setColumnWidth(8, 7000);
		s.setColumnWidth(9, 5000);
		s.setColumnWidth(10, 5000);
		s.setColumnWidth(11, 5000);
		s.setColumnWidth(12, 5000);
		s.setColumnWidth(13, 2500);
		s.setColumnWidth(14, 4500);
		s.setColumnWidth(15, 4000);
		s.setColumnWidth(16, 5500);
		s.setColumnWidth(17, 3500);
		s.setColumnWidth(18, 6000);
		s.setColumnWidth(19, 4500);
		s.setColumnWidth(20, 4000);
		s.setColumnWidth(21, 7000);
		s.setColumnWidth(22, 5000);
		s.setColumnWidth(23, 5000);
		s.setColumnWidth(24, 5000);
		s.setColumnWidth(25, 5000);
		s.setColumnWidth(26, 2500);
		s.setColumnWidth(27, 4500);
		s.setColumnWidth(28, 4000);
		s.setColumnWidth(29, 5500);
		s.setColumnWidth(30, 3500);
		s.setColumnWidth(31, 6000);
		s.setColumnWidth(32, 4500);
		s.setColumnWidth(33, 4000);
		s.setColumnWidth(34, 7000);
		s.setColumnWidth(35, 15000);
		// 设置样式
		HSSFCellStyle style = wb.createCellStyle();
		wb.setSheetName(0, "审计工程情况表");
		// 创建第一行
		HSSFRow row = s.createRow(0);
		// 第一行第一列
		HSSFCell cell = row.createCell((short) 0);
		// 第一行第一列
		HSSFFont font = wb.createFont();
		// 设定字体大小
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 加入样式
		cell.setCellStyle(style);
		// 设置该列的参数
		cell.setCellValue(sf.format(new Date()) + "投资工程审计工作情况表");
		// 设置单元格普通样式
		style = wb.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);// 设置自动换行
		// 设置excel单元名称
		row = s.createRow(1);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("");
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("");

		s.addMergedRegion(new Region(1, (short) 2, 1, (short) 7));
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("管理单位");

		s.addMergedRegion(new Region(1, (short) 8, 1, (short) 13));
		cell = row.createCell((short) 8);
		cell.setCellStyle(style);
		cell.setCellValue("审减成果");

		s.addMergedRegion(new Region(1, (short) 14, 1, (short) 17));
		cell = row.createCell((short) 14);
		cell.setCellStyle(style);
		cell.setCellValue("中介机构审计费用（适用常规审计项目）");

		cell = row.createCell((short) 18);
		cell.setCellStyle(style);
		cell.setCellValue("");

		s.addMergedRegion(new Region(1, (short) 19, 1, (short) 24));
		cell = row.createCell((short) 19);
		cell.setCellStyle(style);
		cell.setCellValue("中介机构进度情况");

		s.addMergedRegion(new Region(1, (short) 25, 1, (short) 30));
		cell = row.createCell((short) 25);
		cell.setCellStyle(style);
		cell.setCellValue("复核情况");

		s.addMergedRegion(new Region(1, (short) 31, 1, (short) 35));
		cell = row.createCell((short) 31);
		cell.setCellStyle(style);
		cell.setCellValue("主审进度情况");

		// 设置excel单元名称
		row = s.createRow(2);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("序号");
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("题 名");
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("项目立项文号");
		cell = row.createCell((short) 3);
		cell.setCellStyle(style);
		cell.setCellValue("被审计单位");
		cell = row.createCell((short) 4);
		cell.setCellStyle(style);
		cell.setCellValue("主审");
		cell = row.createCell((short) 5);
		cell.setCellStyle(style);
		cell.setCellValue("事务所");
		cell = row.createCell((short) 6);
		cell.setCellStyle(style);
		cell.setCellValue("复核");
		cell = row.createCell((short) 7);
		cell.setCellStyle(style);
		cell.setCellValue("施工单位");
		cell = row.createCell((short) 8);
		cell.setCellStyle(style);
		cell.setCellValue("送审金额");
		cell = row.createCell((short) 9);
		cell.setCellStyle(style);
		cell.setCellValue("审定金额");
		cell = row.createCell((short) 10);
		cell.setCellStyle(style);
		cell.setCellValue("中介审减金额");
		cell = row.createCell((short) 11);
		cell.setCellStyle(style);
		cell.setCellValue("雇员审减金额");
		cell = row.createCell((short) 12);
		cell.setCellStyle(style);
		cell.setCellValue("总审减金额");
		cell = row.createCell((short) 13);
		cell.setCellStyle(style);
		cell.setCellValue("审减率");
		cell = row.createCell((short) 14);
		cell.setCellStyle(style);
		cell.setCellValue("基础审计费(已乘以0.8)");
		cell = row.createCell((short) 15);
		cell.setCellStyle(style);
		cell.setCellValue("效益审计费");
		cell = row.createCell((short) 16);
		cell.setCellStyle(style);
		cell.setCellValue("扣 款");
		cell = row.createCell((short) 17);
		cell.setCellStyle(style);
		cell.setCellValue("小计");
		cell = row.createCell((short) 18);
		cell.setCellStyle(style);
		cell.setCellValue("接收建设单位资料时间");
		cell = row.createCell((short) 19);
		cell.setCellStyle(style);
		cell.setCellValue("资料发出时间");
		cell = row.createCell((short) 20);
		cell.setCellStyle(style);
		cell.setCellValue("应完成时间");
		cell = row.createCell((short) 21);
		cell.setCellStyle(style);
		cell.setCellValue("交回复核时间");
		cell = row.createCell((short) 22);
		cell.setCellStyle(style);
		cell.setCellValue("超时天数");
		cell = row.createCell((short) 23);
		cell.setCellStyle(style);
		cell.setCellValue("进度说明");
		cell = row.createCell((short) 24);
		cell.setCellStyle(style);
		cell.setCellValue("开始复核时间");
		cell = row.createCell((short) 25);
		cell.setCellStyle(style);
		cell.setCellValue("初次复核结束时间");
		cell = row.createCell((short) 26);
		cell.setCellStyle(style);
		cell.setCellValue("复核结果确认时间");
		cell = row.createCell((short) 27);
		cell.setCellStyle(style);
		cell.setCellValue("等待复核天数");
		cell = row.createCell((short) 28);
		cell.setCellStyle(style);
		cell.setCellValue("实际复核天数");
		cell = row.createCell((short) 29);
		cell.setCellStyle(style);
		cell.setCellValue("进度说明");
		cell = row.createCell((short) 30);
		cell.setCellStyle(style);
		cell.setCellValue("审计报告时间");
		cell = row.createCell((short) 31);
		cell.setCellStyle(style);
		cell.setCellValue("报告所用天数");
		cell = row.createCell((short) 32);
		cell.setCellStyle(style);
		cell.setCellValue("审计报告文号");
		cell = row.createCell((short) 33);
		cell.setCellStyle(style);
		cell.setCellValue("开工时间");
		cell = row.createCell((short) 34);
		cell.setCellStyle(style);
		cell.setCellValue("竣工时间");
		cell = row.createCell((short) 35);
		cell.setCellStyle(style);
		cell.setCellValue("存在问题");

		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				// 创建行
				row = s.createRow(3 + i);
				// 设置行高
				row.setHeight((short) 800);
				cell = row.createCell((short) 0);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDatapreno());
				cell = row.createCell((short) 1);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectName());
				cell = row.createCell((short) 2);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectNo());
				cell = row.createCell((short) 3);
				cell.setCellStyle(style);
				// 查询项目业主
				ProjectOwner owner = iProjectOwnerService.getProjectOwner(list
						.get(i).getProownerid());
				if(null!=owner){
					cell.setCellValue(owner.getOwnerName());
				}else{
					cell.setCellValue("");
				}
				cell = row.createCell((short) 4);
				cell.setCellStyle(style);
				// 查询用户
				EditUser u = userInfoService.findbyid(list.get(i)
						.getMainAuditId());
				if(null!=u)
				{
				  cell.setCellValue(u.getUsername());
				}else{
				  cell.setCellValue("");
				}

				// 查询复核工程师
				List<EditUser> listuser = iSingleProjectArrangeService
						.findGovernmentEmpUserName(list.get(i).getArrangeid());
				String employes = "";
				for (EditUser eu : listuser) {
					employes += eu.getUsername() + ",";
				}
				if (AuditStringUtils.isNotEmpty(employes)) {
					employes = employes.substring(0, employes.length() - 1);
				}
				// 判断是否自审
				if (!AuditStringUtils.isNotEmpty(list.get(i).getIsMySelfState())) {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					// 查询事务所名称
					Intermediaryagency inter = iIntermediaryagencyService
							.getIntermediaryagency(list.get(i)
									.getIntermediaryId());
					if (null != inter) {
						cell.setCellValue(inter.getReferred());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);

					cell.setCellValue(employes);
				} else {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					cell.setCellValue(employes);
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					cell.setCellValue("/");
				}

				cell = row.createCell((short) 7);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getConstructId());
				cell = row.createCell((short) 8);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getSentAmount());	
				// 判断是否自审
				if (!AuditStringUtils.isNotEmpty(list.get(i).getIsMySelfState())) {
					cell = row.createCell((short) 9);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getInterauditmoney());
				} else {
					cell = row.createCell((short) 9);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getAuditMoney());
				}
				
				
				cell = row.createCell((short) 10);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getIntercutmoney());
				cell = row.createCell((short) 11);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getReduceMoney());

				// 查询项目安排的总金额
				BigDecimal totalcutmoney = new BigDecimal(0.00);
				BigDecimal intercut = new BigDecimal(0.00);
				BigDecimal reduce = new BigDecimal(0.00);
				BigDecimal sentmoney = new BigDecimal(0.00);
				BigDecimal totallv = new BigDecimal(0.00);
				if (AuditStringUtils.isNotEmpty(list.get(i).getIntercutmoney())) {
					intercut = new BigDecimal(list.get(i).getIntercutmoney());
				}
				if (AuditStringUtils.isNotEmpty(list.get(i).getReduceMoney())) {
					reduce = new BigDecimal(list.get(i).getReduceMoney());
				}
				if (AuditStringUtils.isNotEmpty(list.get(i).getSentAmount())) {
					sentmoney = new BigDecimal(list.get(i).getSentAmount());
				}
				totalcutmoney = reduce.add(intercut);
				totallv = totalcutmoney.divide(sentmoney, 10,
						BigDecimal.ROUND_HALF_UP);
				// 保留两位小数
				totalcutmoney = totalcutmoney.setScale(2,
						BigDecimal.ROUND_HALF_UP);
				totallv = totallv.setScale(4, BigDecimal.ROUND_HALF_UP);
				cell = row.createCell((short) 12);
				cell.setCellStyle(style);
				if (!"0.00".equals(totalcutmoney.toString())) {
					cell.setCellValue(totalcutmoney.toString());
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 13);
				cell.setCellStyle(style);
				if (!"0.0000".equals(totallv.toString())) {
					totallv = totallv.multiply(new BigDecimal(100));
					totallv = totallv.setScale(2, BigDecimal.ROUND_HALF_UP);
					cell.setCellValue(totallv.toString() + "%");
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell((short) 14);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 15);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 16);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 17);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 18);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i).getDatapretime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getDatapretime()));
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell((short) 19);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediarySendTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediarySendTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 20);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediaryAuditTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediaryAuditTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 21);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getDataTransferTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getDataTransferTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 22);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDeferday());
				cell = row.createCell((short) 23);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 24);
				cell.setCellStyle(style);
				if (AuditStringUtils
						.isNotEmpty(list.get(i).getAuditStartTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getAuditStartTime()));
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell((short) 25);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 26);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i).getAuditEndTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getAuditEndTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 27);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 28);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getAuditDayCount());
				cell = row.createCell((short) 29);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getAuditRemark());
				cell = row.createCell((short) 30);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getAuditReportTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getAuditReportTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 31);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDayCount());
				cell = row.createCell((short) 32);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getAuditReportCode());
				cell = row.createCell((short) 33);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getProjectStartTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getProjectStartTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 34);
				cell.setCellStyle(style);
				if (AuditStringUtils
						.isNotEmpty(list.get(i).getProjectEndTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getProjectEndTime()));
				} else {
					cell.setCellValue("");
				}
				
				//查询存在问题
				List<Problems> listpro= commonPorjectExcelService.findProblemsById(list.get(i).getMainId());
				cell = row.createCell((short) 35);
				cell.setCellStyle(style);
				if(null!=listpro && listpro.size()!=0){
					String msgs="问题类型:";
					for(Problems problems:listpro){
						if("0".equals(problems.getType())){
							msgs+="是否超合同价";
						}else if("1".equals(problems.getType())){
							msgs+="是否超概算";
						}else if("2".equals(problems.getType())){
							msgs+="是否超工期";
						}else if("3".equals(problems.getType())){
							msgs+="是否有无工程质量验收记录";
						}else if("4".equals(problems.getType())){
							msgs+="多计工程款";
						}else if("5".equals(problems.getType())){
							msgs+="其他";
						}
						msgs+="金额是:"+problems.getMoney();
						msgs+="原因是:"+problems.getReason();
						msgs+="天数是:"+problems.getDay()+"\n";
					}
					cell.setCellValue(msgs);
				}else
				{
				cell.setCellValue("");
				}
				
				
				
				
			}
		}
		// 写入
		wb.write(os);
		// 关闭
		os.flush();
		os.close();
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(a));
			String filename = "项目审计工作情况表" + sdf.format(new Date()) + ".xls";
			filename = new String(filename.getBytes("GB2312"), "ISO8859_1");
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 导出审计项目未完成情况Excel
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/importfinishprojectexcel")
	public void importfinishprojectexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取方式
		String method = request.getParameter("method");
		// 获取审计开始时间
		String datapreStartTime = request.getParameter("datapreStartTime");
		// 获取审计结束时间
		String datapreEndTime = request.getParameter("datapreEndTime");
		// 获取项目业主
		String projectOwner = request.getParameter("projectOwner");
		// 获取审计身份
		String auditidentity = request.getParameter("auditidentity");
		// 获取项目名称
		String projectName = request.getParameter("projectName");
		// 判斷是否為空
		if (AuditStringUtils.isNotEmpty(projectName)) {
			projectName = URLDecoder.decode(
					request.getParameter("projectName"), "UTF-8");
		}

		// 获取用户信息
		String userId = request.getParameter("userId");
		// 获取中介机构
		String intermediaryId = request.getParameter("intermediaryId");
		String constructId = request.getParameter("constructId");
		if (null != constructId && !"".equals(constructId)) {
			// 获取施工单位
			constructId = URLDecoder.decode(
					request.getParameter("constructId"), "UTF-8");
		}
		// 定义对象
		SearchProjectBaseInfoResult pro = new SearchProjectBaseInfoResult();
		pro.setProownerid(projectOwner);
		pro.setIntermediaryId(intermediaryId);
		pro.setConstructId(constructId);
		pro.setDatapreStartTime(datapreStartTime);
		pro.setDatapreEndTime(datapreEndTime);
		pro.setAuditidentity(auditidentity);
		pro.setUserId(userId);
		pro.setMethodPro(method);
		pro.setProjectName(projectName);
		// 调用业务层查询项目相应审计信息
		List<SearchProjectBaseInfoResult> list = commonPorjectExcelService
				.findProjectFinishBaseInfo(pro);
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template/file");
		// 删除该目录下面所有excel
		// 删除该目录下面的所有文件
		File newfile = new File(url);
		File[] files = newfile.listFiles();
		if (null != files) {
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String a = url + "/项目审计工作情况表" + sdf.format(new Date()) + ".xls";
		FileOutputStream os = new FileOutputStream(a);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		s.addMergedRegion(new Region(0, (short) 0, 0, (short) 35));
		// 设置列宽度
		s.setColumnWidth(0, 2500);
		s.setColumnWidth(1, 9000);
		s.setColumnWidth(2, 4000);
		s.setColumnWidth(3, 5500);
		s.setColumnWidth(4, 3500);
		s.setColumnWidth(5, 6000);
		s.setColumnWidth(6, 4500);
		s.setColumnWidth(7, 8000);
		s.setColumnWidth(8, 7000);
		s.setColumnWidth(9, 5000);
		s.setColumnWidth(10, 5000);
		s.setColumnWidth(11, 5000);
		s.setColumnWidth(12, 5000);
		s.setColumnWidth(13, 2500);
		s.setColumnWidth(14, 4500);
		s.setColumnWidth(15, 4000);
		s.setColumnWidth(16, 5500);
		s.setColumnWidth(17, 3500);
		s.setColumnWidth(18, 6000);
		s.setColumnWidth(19, 4500);
		s.setColumnWidth(20, 4000);
		s.setColumnWidth(21, 7000);
		s.setColumnWidth(22, 5000);
		s.setColumnWidth(23, 5000);
		s.setColumnWidth(24, 5000);
		s.setColumnWidth(25, 5000);
		s.setColumnWidth(26, 2500);
		s.setColumnWidth(27, 4500);
		s.setColumnWidth(28, 4000);
		s.setColumnWidth(29, 5500);
		s.setColumnWidth(30, 3500);
		s.setColumnWidth(31, 6000);
		s.setColumnWidth(32, 4500);
		s.setColumnWidth(33, 4000);
		s.setColumnWidth(34, 7000);
		s.setColumnWidth(35, 15000);
		// 设置样式
		HSSFCellStyle style = wb.createCellStyle();
		wb.setSheetName(0, "审计工程情况表");
		// 创建第一行
		HSSFRow row = s.createRow(0);
		// 第一行第一列
		HSSFCell cell = row.createCell((short) 0);
		// 第一行第一列
		HSSFFont font = wb.createFont();
		// 设定字体大小
		font.setFontHeightInPoints((short) 15);
		style.setFont(font);
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 加入样式
		cell.setCellStyle(style);
		// 设置该列的参数
		cell.setCellValue(sf.format(new Date()) + "投资工程审计工作情况表");
		// 设置单元格普通样式
		style = wb.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);// 设置自动换行
		// 设置excel单元名称
		row = s.createRow(1);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("");
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("");

		s.addMergedRegion(new Region(1, (short) 2, 1, (short) 7));
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("管理单位");

		s.addMergedRegion(new Region(1, (short) 8, 1, (short) 13));
		cell = row.createCell((short) 8);
		cell.setCellStyle(style);
		cell.setCellValue("审减成果");

		s.addMergedRegion(new Region(1, (short) 14, 1, (short) 17));
		cell = row.createCell((short) 14);
		cell.setCellStyle(style);
		cell.setCellValue("中介机构审计费用（适用常规审计项目）");

		cell = row.createCell((short) 18);
		cell.setCellStyle(style);
		cell.setCellValue("");

		s.addMergedRegion(new Region(1, (short) 19, 1, (short) 24));
		cell = row.createCell((short) 19);
		cell.setCellStyle(style);
		cell.setCellValue("中介机构进度情况");

		s.addMergedRegion(new Region(1, (short) 25, 1, (short) 30));
		cell = row.createCell((short) 25);
		cell.setCellStyle(style);
		cell.setCellValue("复核情况");

		s.addMergedRegion(new Region(1, (short) 31, 1, (short) 35));
		cell = row.createCell((short) 31);
		cell.setCellStyle(style);
		cell.setCellValue("主审进度情况");

		// 设置excel单元名称
		row = s.createRow(2);
		// 设置行高
		row.setHeight((short) 600);
		cell = row.createCell((short) 0);
		cell.setCellStyle(style);
		cell.setCellValue("序号");
		cell = row.createCell((short) 1);
		cell.setCellStyle(style);
		cell.setCellValue("题 名");
		cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("项目立项文号");
		cell = row.createCell((short) 3);
		cell.setCellStyle(style);
		cell.setCellValue("被审计单位");
		cell = row.createCell((short) 4);
		cell.setCellStyle(style);
		cell.setCellValue("主审");
		cell = row.createCell((short) 5);
		cell.setCellStyle(style);
		cell.setCellValue("事务所");
		cell = row.createCell((short) 6);
		cell.setCellStyle(style);
		cell.setCellValue("复核");
		cell = row.createCell((short) 7);
		cell.setCellStyle(style);
		cell.setCellValue("施工单位");
		cell = row.createCell((short) 8);
		cell.setCellStyle(style);
		cell.setCellValue("送审金额");
		cell = row.createCell((short) 9);
		cell.setCellStyle(style);
		cell.setCellValue("审定金额");
		cell = row.createCell((short) 10);
		cell.setCellStyle(style);
		cell.setCellValue("中介审减金额");
		cell = row.createCell((short) 11);
		cell.setCellStyle(style);
		cell.setCellValue("雇员审减金额");
		cell = row.createCell((short) 12);
		cell.setCellStyle(style);
		cell.setCellValue("总审减金额");
		cell = row.createCell((short) 13);
		cell.setCellStyle(style);
		cell.setCellValue("审减率");
		cell = row.createCell((short) 14);
		cell.setCellStyle(style);
		cell.setCellValue("基础审计费(已乘以0.8)");
		cell = row.createCell((short) 15);
		cell.setCellStyle(style);
		cell.setCellValue("效益审计费");
		cell = row.createCell((short) 16);
		cell.setCellStyle(style);
		cell.setCellValue("扣 款");
		cell = row.createCell((short) 17);
		cell.setCellStyle(style);
		cell.setCellValue("小计");
		cell = row.createCell((short) 18);
		cell.setCellStyle(style);
		cell.setCellValue("接收建设单位资料时间");
		cell = row.createCell((short) 19);
		cell.setCellStyle(style);
		cell.setCellValue("资料发出时间");
		cell = row.createCell((short) 20);
		cell.setCellStyle(style);
		cell.setCellValue("应完成时间");
		cell = row.createCell((short) 21);
		cell.setCellStyle(style);
		cell.setCellValue("交回复核时间");
		cell = row.createCell((short) 22);
		cell.setCellStyle(style);
		cell.setCellValue("超时天数");
		cell = row.createCell((short) 23);
		cell.setCellStyle(style);
		cell.setCellValue("进度说明");
		cell = row.createCell((short) 24);
		cell.setCellStyle(style);
		cell.setCellValue("开始复核时间");
		cell = row.createCell((short) 25);
		cell.setCellStyle(style);
		cell.setCellValue("初次复核结束时间");
		cell = row.createCell((short) 26);
		cell.setCellStyle(style);
		cell.setCellValue("复核结果确认时间");
		cell = row.createCell((short) 27);
		cell.setCellStyle(style);
		cell.setCellValue("等待复核天数");
		cell = row.createCell((short) 28);
		cell.setCellStyle(style);
		cell.setCellValue("实际复核天数");
		cell = row.createCell((short) 29);
		cell.setCellStyle(style);
		cell.setCellValue("进度说明");
		cell = row.createCell((short) 30);
		cell.setCellStyle(style);
		cell.setCellValue("审计报告时间");
		cell = row.createCell((short) 31);
		cell.setCellStyle(style);
		cell.setCellValue("报告所用天数");
		cell = row.createCell((short) 32);
		cell.setCellStyle(style);
		cell.setCellValue("审计报告文号");
		cell = row.createCell((short) 33);
		cell.setCellStyle(style);
		cell.setCellValue("开工时间");
		cell = row.createCell((short) 34);
		cell.setCellStyle(style);
		cell.setCellValue("竣工时间");
		cell = row.createCell((short) 35);
		cell.setCellStyle(style);
		cell.setCellValue("存在问题");

		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				// 创建行
				row = s.createRow(3 + i);
				// 设置行高
				row.setHeight((short) 800);
				cell = row.createCell((short) 0);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDatapreno());
				cell = row.createCell((short) 1);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectName());
				cell = row.createCell((short) 2);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getProjectNo());
				cell = row.createCell((short) 3);
				cell.setCellStyle(style);
				// 查询项目业主
				ProjectOwner owner = iProjectOwnerService.getProjectOwner(list
						.get(i).getProownerid());
				if(null!=owner){
					cell.setCellValue(owner.getOwnerName());	
				}else{
					cell.setCellValue("");
				}
				
				cell = row.createCell((short) 4);
				cell.setCellStyle(style);
				// 查询用户
				EditUser u = userInfoService.findbyid(list.get(i).getMainAuditId());
				if(null!=u){
					cell.setCellValue(u.getUsername());
				}else{
					cell.setCellValue("");
				}
				

				// 查询复核工程师
				List<EditUser> listuser = iSingleProjectArrangeService
						.findGovernmentEmpUserName(list.get(i).getArrangeid());
				String employes = "";
				for (EditUser eu : listuser) {
					employes += eu.getUsername() + ",";
				}
				if (AuditStringUtils.isNotEmpty(employes)) {
					employes = employes.substring(0, employes.length() - 1);
				}
				// 判断是否自审
				if (!AuditStringUtils
						.isNotEmpty(list.get(i).getIsMySelfState())) {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					// 查询事务所名称
					Intermediaryagency inter = iIntermediaryagencyService
							.getIntermediaryagency(list.get(i)
									.getIntermediaryId());
					if (null != inter) {
						cell.setCellValue(inter.getReferred());
					} else {
						cell.setCellValue("");
					}
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);

					cell.setCellValue(employes);
				} else {
					cell = row.createCell((short) 5);
					cell.setCellStyle(style);
					cell.setCellValue(employes);
					cell = row.createCell((short) 6);
					cell.setCellStyle(style);
					cell.setCellValue("/");
				}

				cell = row.createCell((short) 7);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getConstructId());
				cell = row.createCell((short) 8);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getSentAmount());
				// 判断是否自审
				if (!AuditStringUtils.isNotEmpty(list.get(i).getIsMySelfState())) {
					cell = row.createCell((short) 9);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getInterauditmoney());
				} else {
					cell = row.createCell((short) 9);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i).getAuditMoney());
				}
				cell = row.createCell((short) 10);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getIntercutmoney());
				cell = row.createCell((short) 11);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getReduceMoney());

				// 查询项目安排的总金额
				BigDecimal totalcutmoney = new BigDecimal(0.00);
				BigDecimal intercut = new BigDecimal(0.00);
				BigDecimal reduce = new BigDecimal(0.00);
				BigDecimal sentmoney = new BigDecimal(0.00);
				BigDecimal totallv = new BigDecimal(0.00);
				if (AuditStringUtils.isNotEmpty(list.get(i).getIntercutmoney())) {
					intercut = new BigDecimal(list.get(i).getIntercutmoney());
				}
				if (AuditStringUtils.isNotEmpty(list.get(i).getReduceMoney())) {
					reduce = new BigDecimal(list.get(i).getReduceMoney());
				}
				if (AuditStringUtils.isNotEmpty(list.get(i).getSentAmount())) {
					sentmoney = new BigDecimal(list.get(i).getSentAmount());
				}
				totalcutmoney = reduce.add(intercut);
				totallv = totalcutmoney.divide(sentmoney, 10,
						BigDecimal.ROUND_HALF_UP);
				// 保留两位小数
				totalcutmoney = totalcutmoney.setScale(2,
						BigDecimal.ROUND_HALF_UP);
				totallv = totallv.setScale(4, BigDecimal.ROUND_HALF_UP);
				cell = row.createCell((short) 12);
				cell.setCellStyle(style);
				if (!"0.00".equals(totalcutmoney.toString())) {
					cell.setCellValue(totalcutmoney.toString());
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 13);
				cell.setCellStyle(style);
				if (!"0.0000".equals(totallv.toString())) {
					totallv = totallv.multiply(new BigDecimal(100));
					totallv = totallv.setScale(2, BigDecimal.ROUND_HALF_UP);
					cell.setCellValue(totallv.toString() + "%");
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell((short) 14);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 15);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 16);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 17);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 18);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i).getDatapretime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getDatapretime()));
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell((short) 19);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediarySendTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediarySendTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 20);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getIntermediaryAuditTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getIntermediaryAuditTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 21);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getDataTransferTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getDataTransferTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 22);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDeferday());
				cell = row.createCell((short) 23);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 24);
				cell.setCellStyle(style);
				if (AuditStringUtils
						.isNotEmpty(list.get(i).getAuditStartTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getAuditStartTime()));
				} else {
					cell.setCellValue("");
				}

				cell = row.createCell((short) 25);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 26);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i).getAuditEndTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getAuditEndTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 27);
				cell.setCellStyle(style);
				cell.setCellValue("");
				cell = row.createCell((short) 28);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getAuditDayCount());
				cell = row.createCell((short) 29);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getAuditRemark());
				cell = row.createCell((short) 30);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getAuditReportTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getAuditReportTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 31);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getDayCount());
				cell = row.createCell((short) 32);
				cell.setCellStyle(style);
				cell.setCellValue(list.get(i).getAuditReportCode());
				cell = row.createCell((short) 33);
				cell.setCellStyle(style);
				if (AuditStringUtils.isNotEmpty(list.get(i)
						.getProjectStartTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getProjectStartTime()));
				} else {
					cell.setCellValue("");
				}
				cell = row.createCell((short) 34);
				cell.setCellStyle(style);
				if (AuditStringUtils
						.isNotEmpty(list.get(i).getProjectEndTime())) {
					cell.setCellValue(AuditStringUtils.getDatetoyyyyMMdd(list
							.get(i).getProjectEndTime()));
				} else {
					cell.setCellValue("");
				}
				
				//查询存在问题
				List<Problems> listpro= commonPorjectExcelService.findProblemsById(list.get(i).getMainId());
				cell = row.createCell((short) 35);
				cell.setCellStyle(style);
				if(null!=listpro && listpro.size()!=0){
					String msgs="问题类型:";
					for(Problems problems:listpro){
						if("0".equals(problems.getType())){
							msgs+="是否超合同价";
						}else if("1".equals(problems.getType())){
							msgs+="是否超概算";
						}else if("2".equals(problems.getType())){
							msgs+="是否超工期";
						}else if("3".equals(problems.getType())){
							msgs+="是否有无工程质量验收记录";
						}else if("4".equals(problems.getType())){
							msgs+="多计工程款";
						}else if("5".equals(problems.getType())){
							msgs+="其他";
						}
						msgs+="金额是:"+problems.getMoney();
						msgs+="原因是:"+problems.getReason();
						msgs+="天数是:"+problems.getDay()+"\n";
					}
					cell.setCellValue(msgs);
				}else
				{
				cell.setCellValue("");
				}
				
			}
		}
		// 写入
		wb.write(os);
		// 关闭
		os.flush();
		os.close();
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(a));
			String filename = "项目审计工作情况表" + sdf.format(new Date()) + ".xls";
			filename = new String(filename.getBytes("GB2312"), "ISO8859_1");
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 录入中介机构信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addDataPreInter")
	@ResponseBody
	public Map<String, Object> addDataPreInter(HttpServletRequest request) {
		// 获取参数
		String arrangeId = request.getParameter("arrangeId");
		String intermediary = request.getParameter("intermediary");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 定义单项目安排对象
			SingleProjectArrange single = new SingleProjectArrange();
			single.setId(arrangeId);
			single.setIntermediaryId(intermediary);
			Integer row = projectDatePreService.updateZJByArrangeId(single);
			if (row > 0) {
				map.put("success", "success");
				map.put("msg", "录入中介机构成功");
			} else {
				map.put("success", "fail");
				map.put("msg", "录入中介机构失败");
			}
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("data.error.message"));
		}
		return map;
	}
	
	/**
	 * 分页查询人工调整信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/findrengongtz")
	public Map<String, Object> findrengongtz(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String policyId = request.getParameter("id");
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<ProTractAdjustment> gridmodel=tractPolicyChangeService.findrengongbypolicy(intPage, pagesize, sort, order, policyId);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}
	/**
	 * 添加人工调整费用信息
	 */
	@RequestMapping("/addrengong")
	@ResponseBody
	public Map<String, Object> addrengong(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			//获取参数
			String policyChangeId=request.getParameter("policyChangeId");
			String adjustmentwh=request.getParameter("adjustmentwh");
			String delabourmoney=request.getParameter("delabourmoney");
			String tzlv=request.getParameter("tzlv");
			String mantzmoney=request.getParameter("mantzmoney");
			String tztime=request.getParameter("tztime");
			//定义对象
			ProTractAdjustment pro=new ProTractAdjustment();
			pro.setId(AuditStringUtils.getUUID());
			pro.setPolicyChangeId(policyChangeId);
			pro.setAdjustmentwh(adjustmentwh);
			pro.setDelabourmoney(delabourmoney);
			pro.setTzlv(tzlv);
			pro.setMantzmoney(mantzmoney);
			pro.setTztime(tztime);
			Integer row=tractPolicyChangeService.addrengong(pro);
			map=new HashMap<String,Object>();
			if(row>0){
	    		map.put("success", "success");
	    		map.put("msg", "添加成功");
	    	}else{
	    		map.put("msg", "添加失败");
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 修改人工调整费用信息
	 */
	@RequestMapping("/updaterengong")
	@ResponseBody
	public Map<String, Object> updaterengong(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
				//获取参数
				String adjustmentwh=request.getParameter("adjustmentwh");
				String delabourmoney=request.getParameter("delabourmoney");
				String tzlv=request.getParameter("tzlv");
				String mantzmoney=request.getParameter("mantzmoney");
				String tztime=request.getParameter("tztime");
				String id=request.getParameter("id");
				//定义对象
				ProTractAdjustment pro=tractPolicyChangeService.findbyrengongid(id);
				pro.setAdjustmentwh(adjustmentwh);
				pro.setDelabourmoney(delabourmoney);
				pro.setTzlv(tzlv);
				pro.setTztime(tztime);
				pro.setMantzmoney(mantzmoney);
				Integer row=tractPolicyChangeService.updaterengong(pro);
				map=new HashMap<String,Object>();
				if(row>0){
		    		map.put("success", "success");
		    		map.put("msg", "修改成功");
		    	}else{
		    		map.put("msg", "修改失败");
		    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除人工调整费用信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleterengong")
	@ResponseBody
	public Map<String, Object> deleterengong(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		String key = request.getParameter("key");
		try {
			map=tractPolicyChangeService.deleterengong(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询累积的人工费用调整
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findtotalrengong")
	@ResponseBody
	public Map<String, Object> findtotalrengong(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		String key = request.getParameter("key");
		try {
			map=tractPolicyChangeService.findtotalrengong(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
