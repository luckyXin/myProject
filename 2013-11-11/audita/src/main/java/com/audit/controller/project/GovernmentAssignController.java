/**
 * 政府交办管理
 */
package com.audit.controller.project;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.DataWord;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProGovernmentAssign;
import com.audit.entity.project.ResultClassProConference;
import com.audit.entity.staff.ProjectOwner;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IGovernmentAssignService;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.project.ISingleProjectArrangeService;
import com.audit.service.staff.IProjectOwnerService;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * @author dengyong
 */
@Controller
@RequestMapping("/project/governmentAssign")
public class GovernmentAssignController implements IControllerBase {

	@Autowired
	private IGovernmentAssignService governmentAssignService;

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ISingleProjectArrangeService iSingleProjectArrangeService;

	@Autowired
	private IProjectDatePreService projectDatePreService;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	/**
	 * (non-Javadoc) 2013-6-25
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String reportBatch = request.getParameter("reportBatch");
		String reportTime = request.getParameter("reportTime");
		String assignProjectState = request.getParameter("assignProjectState");
		String assignId = request.getParameter("assignId");
		String assignProjectId = request.getParameter("assignProjectId");
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(assignProjectState)) {
				if (AuditStringUtils.isNotEmpty(assignProjectId)) {
					assignProjectId = assignProjectId.substring(0, assignProjectId.length() - 1);
				}
				return governmentAssignService.addAssignProject(assignProjectId, assignId, userAccount);
			} else {
				return governmentAssignService.add(reportBatch, reportTime);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
		}
		return map;
	}

	/**
	 * 交办删除请求 2013-6-25
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		String assignId = request.getParameter("assignId");
		String assignProjectId = request.getParameter("assignProjectId");
		String deleteAssignProjectState = request.getParameter("deleteAssignProjectState");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (AuditStringUtils.isNotEmpty(deleteAssignProjectState)) {
				return governmentAssignService.deleteAssignProject(assignProjectId, assignId, userAccount);
			}
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
		}
		return map;
	}

	/**
	 * 销毁交办信息 2013-6-25
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String assignId = request.getParameter("id");
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			return governmentAssignService.destroy(assignId, userAccount);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
		}
		return map;
	}

	/**
	 * 查询政府交办列表
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String findProjectState = request.getParameter("findProjectState");
		String ownerName = request.getParameter("ownerName");
		String proejctName = request.getParameter("projectName");
		String findAssignSubProject = request.getParameter("findAssignSubProject");
		String assignId = request.getParameter("assignId");
		String assignCode = request.getParameter("assignCode");
		String reportBatch = request.getParameter("reportBatch");
		String assignProjectInfoState = request.getParameter("assignProjectInfoState");
		String allAssignProjectInfoState = request.getParameter("allAssignProjectInfoState");

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> map = new HashMap<String, Object>();

		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		if (AuditStringUtils.isNotEmpty(allAssignProjectInfoState)) {
			GridDataModel<ArrangeProject> arrangeProjects = governmentAssignService.findAllAssignCompleteProject(
					intPage, pagesize, sort, order, ownerName, proejctName, userAccount);
			map.put("rows", arrangeProjects.getRows());
			map.put("total", arrangeProjects.getTotal());
		} else if (AuditStringUtils.isNotEmpty(assignProjectInfoState)) {
			// 项目信息检索
			GridDataModel<ArrangeProject> arrangeProjects = governmentAssignService.findAssignCompleteProject(intPage,
					pagesize, sort, order, ownerName, proejctName);
			map.put("rows", arrangeProjects.getRows());
			map.put("total", arrangeProjects.getTotal());
		} else if (AuditStringUtils.isNotEmpty(findAssignSubProject)) {
			// 项目信息检索
			GridDataModel<ResultClassProConference> arrangeProjects = governmentAssignService.findAssignProject(
					intPage, pagesize, sort, order, assignId);
			map.put("rows", arrangeProjects.getRows());
			map.put("total", arrangeProjects.getTotal());
		} else if (AuditStringUtils.isNotEmpty(findProjectState)) {
			// 项目信息检索
			GridDataModel<ArrangeProject> arrangeProjects = iSingleProjectArrangeService.findgov(intPage, pagesize,
					sort, order, ownerName, proejctName, userAccount,"M016");
			map.put("rows", arrangeProjects.getRows());
			map.put("total", arrangeProjects.getTotal());
		} else {
			GridDataModel<ProGovernmentAssign> gridmodel = governmentAssignService.find(intPage, pagesize, sort, order,
					assignCode, reportBatch);
			map.put("rows", gridmodel.getRows());
			map.put("total", gridmodel.getTotal());
		}
		return map;
	}

	
	/**
	 * 入口
	 */
	@Override
	public String input(HttpServletRequest request) {

		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String assignCodeAddState = request.getParameter("assignCodeAddState");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("govmoduelId", id);
		if (AuditStringUtils.isNotEmpty(assignCodeAddState)) {
			// 处理类别
			request.setAttribute("processType", "assignCodeAddState");
			// 处理地址
			request.setAttribute("url", "/project/governmentAssign/updateAssignCode.do");
			// 查询交办项目信息
			String key = request.getParameter("key");
			ProGovernmentAssign gov = governmentAssignService.getAssignInfo(key);
			if (AuditStringUtils.isNotEmpty(gov.getAssignTime())) {
				gov.setAssignTime(AuditStringUtils.getDatetoyyyyMMdd(gov.getAssignTime()));
			}
			if (AuditStringUtils.isNotEmpty(gov.getReportTime())) {
				gov.setReportTime(AuditStringUtils.getDatetoyyyyMMdd(gov.getReportTime()));
			}
			request.setAttribute("proGovernmentAssign", gov);
		} else if (AuditStringUtils.isNotEmpty(add)) {
			// 处理类别
			request.setAttribute("processType", "add");
			// 处理地址
			request.setAttribute("url", "/project/governmentAssign/add.do");
		} else if (AuditStringUtils.isNotEmpty(update)) {
			// 处理类别
			request.setAttribute("processType", "update");
			// 处理地址
			request.setAttribute("url", "/project/governmentAssign/updateAssignCode.do");
			// 查询交办项目信息
			String key = request.getParameter("key");
			ProGovernmentAssign gov = governmentAssignService.getAssignInfo(key);
			if (AuditStringUtils.isNotEmpty(gov.getAssignTime())) {
				gov.setAssignTime(AuditStringUtils.getDatetoyyyyMMdd(gov.getAssignTime()));
			}
			if (AuditStringUtils.isNotEmpty(gov.getReportTime())) {
				gov.setReportTime(AuditStringUtils.getDatetoyyyyMMdd(gov.getReportTime()));
			}
			request.setAttribute("proGovernmentAssign", gov);
		} else {
			request.getSession().setAttribute("moduleId", id);
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			String isArrange = request.getParameter("key");
			if (AuditStringUtils.isNotEmpty(isArrange)) {
				request.setAttribute("isAssign", isArrange);
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.setAttribute("govmap", cf);
			return "/project/proGovernmentAssignIndex";
		}
		return "/project/proGovernMentAssignEdit";
	}

	/**
	 * (non-Javadoc) 2013-6-25
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String reportBatch = request.getParameter("reportBatch");
		String reportTime = request.getParameter("reportTime");
		String assignCode = request.getParameter("assignCode");
		String assignTime = request.getParameter("assignTime");
		String assignId = request.getParameter("assignId");
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			return governmentAssignService.updateAssignCode(null, reportBatch, reportTime, assignCode, assignTime,
					assignId, userAccount);
		} catch (Exception e) {
			map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
		}
		return map;
	}

	/**
	 * 创建生成word
	 * 
	 * @param request
	 */
	@RequestMapping("createword")
	public void createWord(HttpServletRequest request, HttpServletResponse response) {
		// 创建word文档,并设置纸张的大小
		Document document = new Document(PageSize.A4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filepath = "";
		try {
			String url = request.getSession().getServletContext().getRealPath("/upload/template/file");
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
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			// 获取交办时间
			// String time = request.getParameter("time");
			// 获取当前时间
			String time = sf.format(new Date());
			// 查询项目交办的是决算还是竣工
			List<Datapreinfo> audittype = projectDatePreService.findproaudittype(id);
			String method = "1";
			for (Datapreinfo type : audittype) {
				if (PropertiesGetValue.getContextProperty("gengaudittype").equals(type.getAudittype())) {
					method = "2";
					break;
				}
			}
			if ("1".equals(method)) {
				filepath = url + "/金牛区审计局政府投资建设工程竣工决算审计请示汇总表" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else {
				filepath = url + "/金牛区审计局政府投资建设工程跟踪审计请示汇总表" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			}
			document.open();
			// 设置合同头
			Paragraph ph = new Paragraph();
			Font f = new Font();
			if ("1".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局政府投资建设工程\n竣工决算审计请示汇总表", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("2".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局政府投资建设工程\n跟踪审计请示汇总表", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			ph.setFont(f);
			// 创建有三列的表格
			Table table = new Table(3);
			table.setAlignment(Element.ALIGN_CENTER);// 居中显示
			table.setBorderWidth(1);
			table.setBorderColor(Color.BLACK);
			table.setPadding(5);
			table.setSpacing(0);
			int[] a = new int[] { 1, 4, 7 };
			table.setWidths(a);
			// 添加表头的元素
			Cell cell = new Cell("序号");// 单元格
			cell.setHeader(true);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new Cell("组织实施单位");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new Cell("工程名称");
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			table.endHeaders();// 表头结束
			// 定义序号
			Integer num = 0;
			// 根据政府交办查询施工企业
			List<Datapreinfo> listdatapre = projectDatePreService.findConstructId(id);
			if (null != listdatapre && listdatapre.size() != 0) {
				for (int i = 0; i < listdatapre.size(); i++) {
					// 判断是否施工单位id为空
					if (AuditStringUtils.isNotEmpty(listdatapre.get(i).getProownerid())) {
						// 查询项目业主
						ProjectOwner proowner = iProjectOwnerService
								.getProjectOwner(listdatapre.get(i).getProownerid());
						DataWord dw = new DataWord();
						dw.setProownerid(proowner.getId());
						dw.setGovernmentAssignId(id);
						// 查询施工单位下面的工程名称
						List<Datapreinfo> listdata = projectDatePreService.findProjectName(dw);
						if (null != listdata && listdata.size() != 0) {
							// 表格的主体
							int length = listdata.size();
							for (int m = 0; m < length; m++) {
								num = num + 1;
								cell = new Cell(num.toString());
								cell.setVerticalAlignment(Element.ALIGN_CENTER);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								table.addCell(cell);
								// table.addCell(cell);
								/*
								 * if (length - m >= length) { cell = new
								 * Cell(proowner.getOwnerName()); if (length >
								 * 1) { cell.setRowspan(length); }
								 * cell.setVerticalAlignment
								 * (Element.ALIGN_CENTER);
								 * cell.setHorizontalAlignment
								 * (Element.ALIGN_CENTER);
								 * 
								 * table.addCell(cell); }
								 */
								cell = new Cell(proowner.getOwnerName());
								cell.setVerticalAlignment(Element.ALIGN_CENTER);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								table.addCell(cell);
								cell = new Cell(listdata.get(m).getProjectName());
								cell.setVerticalAlignment(Element.ALIGN_CENTER);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								table.addCell(cell);
							}
						}
					}
				}
			}
			document.add(table);
			String year = "";
			String month = "";
			String day = "";
			if (AuditStringUtils.isNotEmpty(time)) {
				String times[] = time.split("-");
				year = times[0];
				month = times[1];
				day = times[2].trim();
			}
			document.add(new Paragraph("                                                   " + year + "年" + month + "月"
					+ day + "日"));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileName = ""; // 文件名，输出到用户的下载对话框
		try {
			if (filepath.lastIndexOf("/") > 0) {
				fileName = new String(filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length()).getBytes(
						"GB2312"), "ISO8859_1");
			} else if (filepath.lastIndexOf("\\") > 0) {
				fileName = new String(filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length()).getBytes(
						"GB2312"), "ISO8859_1");
			}
			// 打开指定文件的流信息
			FileInputStream fs = null;
			fs = new FileInputStream(new File(filepath));
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
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
	 * 交办文号录入
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/updateAssignCode")
	public void updateAssignCode(HttpServletRequest request, MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MultipartFile> file = requestMultipart.getFiles("fileName");
		String reportBatch = request.getParameter("reportBatch");
		String reportTime = request.getParameter("reportTime");
		String assignCode = request.getParameter("assignCode");
		String assignTime = request.getParameter("assignTime");
		String assignId = request.getParameter("assignId");
		
		List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
		String msg = "";
		String ids = "";
		// 上传文件
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			if (file != null && file.size() > 0) {
				List<Map<String, String>> listmap = AuditStringUtils.uploadfile(file, "governmentAssign", request);
				for (Map<String, String> strMap : listmap) {
					FileBelongRelate fileBelongRelate = new FileBelongRelate();
					fileBelongRelate.setFileName(strMap.get("fileName"));
					fileBelongRelate.setUrl(strMap.get("url"));
					fileBelongRelate.setBelongToId(assignId);
					fileBelongRelate.setId(AuditStringUtils.getUUID());
					listfile.add(fileBelongRelate);
				}
			}
			map = governmentAssignService.updateAssignCode(listfile, reportBatch, reportTime, assignCode, assignTime,
					assignId, userAccount);
		} catch (AuditException e) {
			msg = "保存失败";

		} catch (IOException e) {
			msg = "保存失败";

		} catch (Exception e) {
			msg = "保存失败";
		}
		msg = map.get("msg").toString();
		ids = map.get("assignId").toString();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{'id':'" + ids + "','msg':'" + msg + "'}");
	}

}
