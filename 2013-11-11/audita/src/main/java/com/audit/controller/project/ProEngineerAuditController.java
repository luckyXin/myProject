/**
 * 政府雇员审核
 */
package com.audit.controller.project;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.DataPreBaseInfo;
import com.audit.entity.project.GovermentEmployeeAudit;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.ResultInterAuidt;
import com.audit.entity.project.SectionChiefAuditInfo;
import com.audit.entity.project.WorkloadInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IProEngineerAuditService;
import com.audit.service.project.ISectionChiefAuditService;
import com.audit.service.system.IUserInfoService;
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
@RequestMapping("/project/engineerAudit")
public class ProEngineerAuditController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private IProEngineerAuditService proEngineerAuditService;

	@Autowired
	private ISectionChiefAuditService sectionChiefAuditService;

	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 跳转入口
	 */
	public String input(HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String adds = request.getParameter("adds");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("engmoduelId", id);
		if (AuditStringUtils.isNotEmpty(add)) {

			String projectId = request.getParameter("key");

			ResultClassArrangeInfo project = null;
			Integer protype = 0;
			// 查询单项目
			project = intermediaryAuditService.findbyid(projectId);
			// 如果单项目没有查询到查询打包项目
			if (null == project) {
				// 查询打包项目
				project = intermediaryAuditService.findpackbyid(projectId);
				protype = 1;
			}
			String submitState = CommonConstant.SUBMIT_STATE_ON;
			String auditStartTime = AuditStringUtils.EMPTY;
			String auditEndTime = AuditStringUtils.EMPTY;
			String auditDayCount = AuditStringUtils.EMPTY;
			// 查询项目安排的总金额
			BigDecimal auditmoney = new BigDecimal(0.00);
			BigDecimal cutmoney = new BigDecimal(0.00);
			List<GovermentEmployeeAudit> listpro = proEngineerAuditService.findauditcutmoney(projectId);
			if (null != listpro && listpro.size() != 0) {
				for (GovermentEmployeeAudit pro : listpro) {
					if (AuditStringUtils.isNotEmpty(pro.getAuditMoney())) {
						BigDecimal a = new BigDecimal(Double.parseDouble(pro.getAuditMoney()));
						auditmoney = auditmoney.add(a);
					}
					if (AuditStringUtils.isNotEmpty(pro.getReduceMoney())) {
						cutmoney = cutmoney.add(new BigDecimal(Double.parseDouble(pro.getReduceMoney())));
					}
					if (CommonConstant.SUBMIT_STATE_OFF.equals(pro.getSubmitState())) {
						submitState = CommonConstant.SUBMIT_STATE_OFF;
					}
					auditStartTime = pro.getAuditStartTime();
					auditEndTime = pro.getAuditEndTime();
					auditDayCount = pro.getAuditDayCount();
				}
			} else {
				submitState = CommonConstant.SUBMIT_STATE_OFF;
			}
			// 保留两位小数
			auditmoney = auditmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
			cutmoney = cutmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
			request.setAttribute("zongauditmoney", auditmoney);
			request.setAttribute("zongcutmoney", cutmoney);
			request.setAttribute("submitState", submitState);
			request.setAttribute("auditStartTime", auditStartTime);
			request.setAttribute("auditEndTime", auditEndTime);
			request.setAttribute("auditDayCount", auditDayCount);
			// 项目基本信息
			request.setAttribute("projectInfo", project);
			// 保存项目类型
			request.setAttribute("protype", protype);

			if (protype == 1) {

			} else {
				// 获取基本信息
				SectionChiefAuditInfo sectionChiefAuditInfo = sectionChiefAuditService
						.getSectionChiefAuditInfo(projectId);
				ProIntermediaryAudit inter = sectionChiefAuditInfo.getIntermediaryAudit();
				if (null != inter) {
					// 查询人员
					inter.setUserAccount(userInfoService.findbyuserAccountobject(inter.getUserAccount()).getUsername());
				}

				// 中介审核
				request.setAttribute("intermediaryAudit", inter);
				GovermentEmployeeAudit govermentEmployeeAudit = proEngineerAuditService.getGovermentEmployeeAudit(
						projectId, user.getUserAccount());

				if (govermentEmployeeAudit != null) {
					// 雇员审核信息
					request.setAttribute("govermentEmployeeAudit", govermentEmployeeAudit);
				}
				// 获取单项目相应信息 雇员查看对应的资料
				String datapreId = project.getDatapreId();
				List<DataPreBaseInfo> datagov = proEngineerAuditService.finddatapreBydateId(datapreId);
				// 保存政府雇员看的资料信息
				request.setAttribute("datagov", datagov.get(0));
				// 查询核对工作量时间
				WorkloadInfo wt = proEngineerAuditService.findworkTime(datapreId);
				// 保存工作量时间
				request.setAttribute("wt", wt);

			}
			request.setAttribute("url", "/project/engineerAudit/add.do");
			return "/project/engineerAuditEdit";
		} else if (AuditStringUtils.isNotEmpty(adds)) {

			String module = request.getParameter("id");
			request.setAttribute("auditsubpromoduelId", module);

			// 获取项目基本信息
			String key = request.getParameter("key");
			ResultClassArrangeInfo project = null;
			// 查询单项目
			project = intermediaryAuditService.findsingleauditbyid(key);
			// 如果单项目没有查询到查询打包项目
			if (null == project) {
				// 查询打包项目
				project = intermediaryAuditService.findpackauditbyid(key);
			}
			// 项目基本信息
			request.setAttribute("projectInfo", project);
			// 保存资料id
			request.setAttribute("datapreId", key);
			// 获取资料的相应信息 雇员查看对应的资料
			List<DataPreBaseInfo> datagov = proEngineerAuditService.finddatapreBydateId(key);
			// 保存政府雇员看的资料信息
			request.setAttribute("datagov", datagov.get(0));

			// 根据资料id查询政府雇员信息
			GovermentEmployeeAudit govermentEmployeeAudit = proEngineerAuditService
					.getGovermentEmployeeAuditbydatapreId(key);
			if (govermentEmployeeAudit != null) {
				// 雇员审核信息
				request.setAttribute("govermentEmployeeAudit", govermentEmployeeAudit);
			}
			return "/project/engineerAuditEdits";
		}

		else {
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.setAttribute("engmap", cf);
			return "/project/engineerAuditIndex";
		}
	}

	/**
	 * 查询中介审核
	 */
	@RequestMapping("/findauditmoney")
	@ResponseBody
	public Map<String, Object> findauditmoney(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String arrangeId = request.getParameter("arrangeId");
		// 查询项目安排的总金额
		BigDecimal auditmoney = new BigDecimal(0.00);
		BigDecimal cutmoney = new BigDecimal(0.00);
		List<GovermentEmployeeAudit> listpro = proEngineerAuditService.findauditcutmoney(arrangeId);
		if (null != listpro && listpro.size() != 0) {
			for (GovermentEmployeeAudit pro : listpro) {
				if (AuditStringUtils.isNotEmpty(pro.getAuditMoney())) {
					BigDecimal a = new BigDecimal(Double.parseDouble(pro.getAuditMoney()));
					auditmoney = auditmoney.add(a);
				}
				if (AuditStringUtils.isNotEmpty(pro.getReduceMoney())) {
					cutmoney = cutmoney.add(new BigDecimal(Double.parseDouble(pro.getReduceMoney())));
				}
			}
		}
		// 保留两位小数
		auditmoney = auditmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		cutmoney = cutmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		map.put("auditmoney", auditmoney);
		map.put("cutmoney", cutmoney);
		return map;

	}

	/**
	 * (non-Javadoc) 2013-6-30
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
		String ownerName = request.getParameter("proownerid");
		String projectName = request.getParameter("projectName");
		String arrangeType = request.getParameter("arrangeType");
		String auditType = request.getParameter("auditType");
		String moduleId = request.getParameter("moduleId");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String timeLimitStart = request.getParameter("timeLimitStart");
		String timeLimitEnd = request.getParameter("timeLimitEnd");
		String isUrgent = request.getParameter("isUrgent");
		String greaterThan = request.getParameter("greaterThan");
		String lessThan = request.getParameter("lessThan");
		
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		GridDataModel<AuditInfo> auditInfo = proEngineerAuditService.find(page, pagesize, name, order, projectName,
				ownerName, arrangeType, auditType, userAccount, moduleId, endTime, beginTime, timeLimitStart,
				timeLimitEnd, isUrgent,greaterThan,lessThan);
		map.put("rows", auditInfo.getRows());
		map.put("total", auditInfo.getTotal());
		return map;
	}
	/**
	 * 分页查询复核审核记录
	 * 
	 */
	@RequestMapping("/findfh")
	@ResponseBody
	
	public Map<String, Object> findfh(HttpServletRequest request) {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sort =request.getParameter("sort");
		String order=request.getParameter("order");
		String datapreId =request.getParameter("datapreId");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<GovermentEmployeeAudit>  gridmodel=proEngineerAuditService.find(intPage, pagesize, sort, order, datapreId);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}
	/**
	 * (non-Javadoc) 2013-6-30
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String method = request.getParameter("method");
		String auditlv = request.getParameter("auditlv");

		if (AuditStringUtils.isNotEmpty(method)) {
			String auditStartTime = request.getParameter("auditStartTime");
			String auditEndTime = request.getParameter("auditEndTime");
			String auditDayCount = request.getParameter("auditDayCount");
			User user = (User) request.getSession().getAttribute("user");
			String arrangeId = request.getParameter("arrangeId");
			String state = request.getParameter("state");
			try {
				map = proEngineerAuditService.addsubmit(arrangeId, auditStartTime, auditEndTime, auditDayCount, user,
						state);
			} catch (AuditException e) {
				map.put("msg", e.getMessage());
			} catch (Exception e) {
				map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
			}
		} else {
			String deskAuditRemark = request.getParameter("deskAuditRemark");
			String projectId = request.getParameter("projectId");
			String auditMoney = request.getParameter("auditMoney");
			String reduceMoney = request.getParameter("reduceMoney");
			String auditStartTime = request.getParameter("auditStartTime");
			String auditEndTime = request.getParameter("auditEndTime");
			String auditDayCount = request.getParameter("auditDayCount");
			String auditRemark = request.getParameter("auditRemark");
			String submitState = request.getParameter("submitState");
			String datapreId = request.getParameter("datapreId");
			String projecttype = request.getParameter("projecttype");
			String sectionChiefAuditId = request.getParameter("sectionChiefAuditId");
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			try {
				map = proEngineerAuditService.addGovermentEmployeeAudit(datapreId, projecttype, projectId, auditMoney,
						reduceMoney, auditStartTime, auditEndTime, auditDayCount, auditRemark, submitState,
						userAccount, sectionChiefAuditId, auditlv,deskAuditRemark);
			} catch (AuditException e) {
				map.put("msg", e.getMessage());
			} catch (Exception e) {
				map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
			}
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-30
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String auditId = request.getParameter("auditId");
		String auditlv = request.getParameter("auditlv");
		String projectId = request.getParameter("projectId");
		String auditMoney = request.getParameter("auditMoney");
		String reduceMoney = request.getParameter("reduceMoney");
		String auditStartTime = request.getParameter("auditStartTime");
		String auditEndTime = request.getParameter("auditEndTime");
		String auditDayCount = request.getParameter("auditDayCount");
		String auditRemark = request.getParameter("auditRemark");
		String submitState = request.getParameter("submitState");
		String datapreId = request.getParameter("datapreId");
		String projecttype = request.getParameter("projecttype");
		String sectionChiefAuditId = request.getParameter("sectionChiefAuditId");
		String deskAuditRemark = request.getParameter("deskAuditRemark");
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		try {
			return proEngineerAuditService.updateGovermentEmployeeAudit(datapreId, projecttype, auditId, projectId,
					auditMoney, reduceMoney, auditStartTime, auditEndTime, auditDayCount, auditRemark, submitState,
					userAccount, sectionChiefAuditId, auditlv,deskAuditRemark);
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", map.put("msg", PropertiesGetValue.getContextProperty("data.error.message")));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-30
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-30
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

	/**
	 * 单项目创建生成四种格式意见单word
	 * 
	 * @param request
	 */
	@RequestMapping("projectauditlistword")
	public void projectauditlistword(HttpServletRequest request, HttpServletResponse response) {
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

			// 获取项目安排id
			String arrangeId = request.getParameter("arrangeId");
			// 获取method判断是哪一种意见单

			String method = "";
			ResultClassArrangeInfo pro = null;
			// 查询单项目
			pro = intermediaryAuditService.findbyid(arrangeId);
			// 如果单项目没有查询到查询打包项目
			if (null == pro) {
				// 查询打包项目
				pro = intermediaryAuditService.findpackbyid(arrangeId);
			}

			// 根据项目安排id查询复核审核意见    
			ResultAuditInfo resultEmpView = proEngineerAuditService.findAuditView(arrangeId, "1");
			// 根据安排id查询科长审核意见
			ResultAuditInfo resultSectionView = proEngineerAuditService.findAuditView(arrangeId, "2");
			// 根据安排id查询法制科长审核意见
			ResultAuditInfo resultLegarSectionView = proEngineerAuditService.findAuditView(arrangeId, "3");
			// 根据安排id查询法制分管领导审核意见
			ResultAuditInfo resultLegalLeaderView = proEngineerAuditService.findAuditView(arrangeId, "4");
			// 根据安排id查询分管领导审核意见
			ResultAuditInfo resultLeaderView = proEngineerAuditService.findAuditView(arrangeId, "5");
            //获取当前用户
			User user=(User)request.getSession().getAttribute("user");
			//判断当前用户登陆角色是否含有科长角色R011
			Integer count=userInfoService.findUserRole(user.getId());
			if(count>0){
				// 判断项目业主是否金信源
				if (PropertiesGetValue.getContextProperty("jing.owner").equals(pro.getOwnnerId())) {
					method = "2";
				}else{
					method = "1";
				}
			}else {
				if (Double.parseDouble(pro.getSentAmount()) <= 2000000) {
					method = "3";
				}
				if (Double.parseDouble(pro.getSentAmount()) > 2000000) {
					method = "4";
				}
			}

			if ("1".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目单项工程竣工决算审计复核意见单20万元（含20万元）以下的" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else if ("2".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目单项工程竣工决算审计复核意见单(金信源)" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else if ("3".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目竣工结算审计复核意见单(200万元以下)" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else if ("4".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目竣工结算审计复核意见单（200万元以上）" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			}
			document.open();
			// 设置合同头
			Paragraph ph = new Paragraph();
			Font f = new Font();
			if ("1".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目单项工程送审金额在20万元（含20万元）\n以下的项目竣工结算审计复核意见单", new Font(Font.NORMAL, 20,
						Font.BOLD, new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("2".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n成都金信源建设投资有限责任公司工程竣工决算复核审计意见单", new Font(Font.NORMAL, 20,
						Font.BOLD, new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("3".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目竣工结算审计复核意见单(200万以下)", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("4".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目竣工结算审计复核意见单(200万以上)", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			ph.setFont(f);

			if ("1".equals(method) || "2".equals(method)) {
				// 创建有六列的表格
				Table table = new Table(7);
				table.setAlignment(Element.ALIGN_CENTER);// 居中显示
				table.setBorderWidth(1);
				table.setBorderColor(Color.BLACK);
				table.setPadding(5);
				table.setSpacing(0);
				int[] a = new int[] { 10, 7, 7, 7, 7, 9, 9 };
				table.setWidths(a);

				// 查询单项目
				ResultClassArrangeInfo project = intermediaryAuditService.findbyid(arrangeId);
				document.add(new Paragraph(""));
				String zhongjie = "";
				if (AuditStringUtils.isNotEmpty(project.getIntermediaryId())) {
					zhongjie = project.getIntermediaryId();
				}
				document.add(new Paragraph("         送审中介机构：" + zhongjie + "             主审："
						+ project.getMainAuditId()));
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审核项目名称：" + project.getProjectName()));
				// 添加表头的元素

				Cell cell = new Cell("发包方式");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("合同价(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(project.getHtmoney());
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("批复概算");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构接资料时间");
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd((project.getIntermediarySendTime())));
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构审结时间");
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediaryAuditTime()));
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("单位工程名称");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审金额(元)");
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构审核结果(元)");
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构审核审减金额(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审减率(%)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				// 查询单个资料中介审核信息
				ResultInterAuidt audit = intermediaryAuditService.findinteraudit(project.getDatapreId());
				// 判断是否为空
				if (AuditStringUtils.isNotEmpty(audit.getProjectName())) {
					cell = new Cell(audit.getProjectName());
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				if (AuditStringUtils.isNotEmpty(audit.getSentAmount().toString())) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getAuditmoney().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				if (null != audit.getCutmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getCutmoney().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				// 算审计率
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("合计");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				if (AuditStringUtils.isNotEmpty(audit.getSentAmount().toString())) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getAuditmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != audit.getCutmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getCutmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				BigDecimal a1 = new BigDecimal("0.0");
				BigDecimal a2 = new BigDecimal("0.0");
				if (null != audit.getCutmoney()) {
					a1 = audit.getCutmoney().setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				if (null != audit.getSentAmount()) {
					a2 = audit.getSentAmount();
				}
				BigDecimal auditquotiety = a1.divide(a2, 4, BigDecimal.ROUND_HALF_UP);// 四舍五入

				auditquotiety = auditquotiety.multiply(new BigDecimal(100));
				// 保留两位小数
				auditquotiety = auditquotiety.setScale(2, BigDecimal.ROUND_HALF_UP);
				if ("0.0000".equals(auditquotiety.toString())) {
					cell = new Cell("0");
				} else {
					cell = new Cell(auditquotiety.toString() + "%");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("业务科室负责人意见");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultSectionView) {
					cell = new Cell(resultSectionView.getRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setColspan(6);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管领导意见");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultLeaderView) {
					cell = new Cell(resultLeaderView.getRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setColspan(6);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				document.add(table);
				document.close();
			}
			if ("3".equals(method)) {
				// 创建有六列的表格
				Table table = new Table(6);
				table.setAlignment(Element.ALIGN_CENTER);// 居中显示
				table.setBorderWidth(1);
				table.setBorderColor(Color.BLACK);
				table.setPadding(5);
				table.setSpacing(0);
				int[] a = new int[] { 9, 7, 7, 8, 7, 10 };
				table.setWidths(a);

				// 查询单项目
				ResultClassArrangeInfo project = intermediaryAuditService.findbyid(arrangeId);
				document.add(new Paragraph(""));
				document.add(new Paragraph("         送审中介机构：" + project.getIntermediaryId() + "          主审："
						+ project.getMainAuditId()));
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审核项目名称：" + project.getProjectName()));
				// 添加表头的元素

				Cell cell = new Cell("建设规模");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("批准直接费用概算或批复预算金额");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("发包方式");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("中标价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell(project.getZbMoney());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("合同价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell(project.getHtmoney());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("主要建筑特征");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				// 查询政府雇员审核审定金额信息
				ResultInterAuidt audit = intermediaryAuditService.findengaudit(project.getDatapreId());
				cell = new Cell("送审时间");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(audit.getStartaudittime()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审结时间");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(audit.getAudittime()));
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("单位工程名称");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审定金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审减金额(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(audit.getProjectName());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().toString()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				//总的审定金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getAuditmoney().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				//总的审减金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().subtract(audit.getAuditmoney()).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("合计");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().toString()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				
				//审定金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getAuditmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				//审减金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().subtract(audit.getAuditmoney()).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("其中复核审减金额");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				if (null != audit.getCutmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getCutmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("复核人员意见业务科室专职");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultEmpView) {
					cell = new Cell(resultEmpView.getRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("业务科室负责人意见");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultEmpView) {
					cell = new Cell(resultEmpView.getDeskAuditRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("法制科意见");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultLegarSectionView) {
					cell = new Cell(resultLegarSectionView.getRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管领导意见");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultLeaderView) {
					cell = new Cell(resultLeaderView.getRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				document.add(table);
				document.close();

			}
			if ("4".equals(method)) {
				// 创建有六列的表格
				Table table = new Table(6);
				table.setAlignment(Element.ALIGN_CENTER);// 居中显示
				table.setBorderWidth(1);
				table.setBorderColor(Color.BLACK);
				table.setPadding(5);
				table.setSpacing(0);
				int[] a = new int[] { 9, 7, 7, 8, 7, 10 };
				table.setWidths(a);

				// 查询单项目
				ResultClassArrangeInfo project = intermediaryAuditService.findbyid(arrangeId);
				document.add(new Paragraph(""));
				document.add(new Paragraph("         送审中介机构：" + project.getIntermediaryId() + "         主审："
						+ project.getMainAuditId()));
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审核项目名称：" + project.getProjectName()));
				// 添加表头的元素

				Cell cell = new Cell("建设规模");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("批准直接费用概算或批复预算金额");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("发包方式");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("中标价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell(project.getZbMoney());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("合同价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell(project.getHtmoney());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("主要建筑特征");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				// 查询政府雇员审核审定金额信息
				ResultInterAuidt audit = intermediaryAuditService.findengaudit(project.getDatapreId());
				cell = new Cell("送审时间");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(audit.getStartaudittime()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审结时间");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(audit.getAudittime()));
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("单位工程名称");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审定金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审减金额(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(audit.getProjectName());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().toString()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				
				//总的审定金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getAuditmoney().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				//总的审减金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().subtract(audit.getAuditmoney()).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("合计");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().toString()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				
				//审定金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getAuditmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
 
				
				//审减金额
				if (null != audit.getAuditmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getSentAmount().subtract(audit.getAuditmoney()).toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("其中复核审减金额");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				if (null != audit.getCutmoney()) {
					cell = new Cell(AuditStringUtils.parseMoney(audit.getCutmoney().toString()));
				} else {
					cell = new Cell("");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审计采用的方法(检查,观察,询问,外部调查,重新计算,重新操作,分析)");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("一,合同约定内容是否违背招,投标文件的实质型内容");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("二,合同约定的决算方式:(措施费,新增单价,新增材料价)");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("三,工程量抽查情况");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("四,清单内项目综合单价是否与投标文件一致;新增项目及新增材料单价是否按合同约定方式计价");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("五,施工企业有无资质,是否按有关部门核定的规费标准计取费用");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("六,安全文明施工的计取,是否按《四川省建设工程安全文明施工费计价管理方法》等相关文件要求执行");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("七,变更签证是否经建设单位,建管单位,监理单位,跟踪审计单位签章认可;变更签证是否按规定出具了区评审招标中心评审函或会审会签表");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("八,工期情况及索赔情况说明");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("其他需要说明的地方");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("复核工程师签字");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("一,工程概算执行情况");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("二,工程审定金额是否超合同金额及其原因");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("三,复核审定定案金额与征求建,施双方定案表金额是否一致");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("投资审计科科长签字");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("一,报告内容是否完整");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("二,报告内容是否真实");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("三,审定定案金额与复核意见单是否一致");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("法制科科长签字");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管法制科领导复核内容");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				if (null != resultLegalLeaderView) {
					cell = new Cell(resultLegalLeaderView.getRemark());
				} else {
					cell = new Cell("");
				}

				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管法制科领导签字");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管投资审计工作领导复核内容");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管投资审计工作领导签字");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				document.add(table);
				document.close();

			}
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
	 * 打包项目创建生成四种格式意见单word
	 * 
	 * @param request
	 */
	@RequestMapping("projectpackauditlistword")
	public void projectpackauditlistword(HttpServletRequest request, HttpServletResponse response) {
		// 创建word文档,并设置纸张的大小
		Document document = new Document(PageSize.A4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filepath = "";
		try {
			String url = request.getSession().getServletContext().getRealPath("/upload/template/file");
			// 删除该目录下面的所有文件
			File newfile = new File(url);
			File[] files = newfile.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
			// 获取项目安排id
			String arrangeId = request.getParameter("arrangeId");

			String method = "";
			ResultClassArrangeInfo proarrange = null;
			// 查询单项目
			proarrange = intermediaryAuditService.findbyid(arrangeId);
			// 如果单项目没有查询到查询打包项目
			if (null == proarrange) {
				// 查询打包项目
				proarrange = intermediaryAuditService.findpackbyid(arrangeId);
			}
			// 判断送审金额
			if (Double.parseDouble(proarrange.getSentAmount()) < 200000) {
				method = "1";
			}
			if (Double.parseDouble(proarrange.getSentAmount()) == 200000) {
				method = "2";
			}
			if (Double.parseDouble(proarrange.getSentAmount()) > 200000
					&& Double.parseDouble(proarrange.getSentAmount()) < 2000000) {
				method = "3";
			}
			if (Double.parseDouble(proarrange.getSentAmount()) >= 2000000) {
				method = "4";
			}

			if ("1".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目单项工程竣工决算审计复核意见单" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else if ("2".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目单项工程竣工决算(20万)审计复核意见单" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else if ("3".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目单项工程竣工决算(20-200万)审计复核意见单" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			} else if ("4".equals(method)) {
				filepath = url + "/金牛区审计局国家建设项目单项工程竣工决算(200万)以上审计复核意见单" + sdf.format(new Date()) + ".doc";
				RtfWriter2.getInstance(document, new FileOutputStream(filepath));
			}
			document.open();
			// 设置合同头
			Paragraph ph = new Paragraph();
			Font f = new Font();
			if ("1".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目竣工审计复核意见单", new Font(Font.NORMAL, 20, Font.BOLD, new Color(
						0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("2".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目竣工审计复核意见单20万", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("3".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目竣工审计复核意见单20-200万", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			if ("4".equals(method)) {
				Paragraph p = new Paragraph("金牛区审计局\n国家建设项目竣工审计复核意见单200万以上", new Font(Font.NORMAL, 20, Font.BOLD,
						new Color(0, 0, 0)));
				p.setAlignment(1);
				document.add(p);
			}
			ph.setFont(f);

			if ("1".equals(method) || "2".equals(method)) {
				// 创建有六列的表格
				Table table = new Table(7);
				table.setAlignment(Element.ALIGN_CENTER);// 居中显示
				table.setBorderWidth(1);
				table.setBorderColor(Color.BLACK);
				table.setPadding(5);
				table.setSpacing(0);
				int[] a = new int[] { 10, 7, 7, 7, 7, 9, 9 };
				table.setWidths(a);

				// 查询打包项目
				ResultClassArrangeInfo project = intermediaryAuditService.findpackbyid(arrangeId);
				// 根据打包项目查询项目相应的资料
				List<ResultInterAuidt> list = intermediaryAuditService.findinterpackaudit(arrangeId);
				document.add(new Paragraph(""));
				String zhongjie = "";
				if (AuditStringUtils.isNotEmpty(project.getIntermediaryId())) {
					zhongjie = project.getIntermediaryId();
				}
				document.add(new Paragraph("         送审中介机构：" + zhongjie + "   主审：" + project.getMainAuditId()));
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审核项目名称：" + project.getProjectName() + "等" + list.size() + "个项目"));
				// 添加表头的元素

				Cell cell = new Cell("发包方式");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("合同价(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("批复概算");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构接资料时间");
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd((project.getIntermediarySendTime())));
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构审结时间");
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediaryAuditTime()));
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("单位工程名称");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审金额(元)");
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构审核结果(元)");
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("中介机构审减金额(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审减率(%)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				BigDecimal totalsentmoney = new BigDecimal(0.00);
				BigDecimal totalauditmoney = new BigDecimal(0.00);
				BigDecimal totalcutmoney = new BigDecimal(0.00);
				for (ResultInterAuidt audit : list) {
					cell = new Cell(audit.getProjectName());
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的送审金额
					if (null != audit.getSentAmount()) {
						totalsentmoney = totalsentmoney.add(audit.getSentAmount());
					}
					cell = new Cell(audit.getSentAmount().toString());
					cell.setColspan(2);
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的中介结构审定金额
					if (null != audit.getAuditmoney()) {
						totalauditmoney = totalauditmoney.add(audit.getAuditmoney());
					}
					cell = new Cell(audit.getAuditmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					cell.setColspan(2);
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的审减金额
					if (null != audit.getCutmoney()) {
						totalcutmoney = totalcutmoney.add(audit.getCutmoney());
					}
					cell = new Cell(audit.getCutmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 算审计率
					cell = new Cell("");
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}

				cell = new Cell("合计");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalsentmoney.toString());
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalauditmoney.toString());
				cell.setColspan(2);
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell(totalcutmoney.toString());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				BigDecimal cut = totalcutmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal auditquotiety = cut.divide(totalsentmoney, 4, BigDecimal.ROUND_HALF_UP);// 四舍五入

				auditquotiety = auditquotiety.multiply(new BigDecimal(100));
				// 保留两位小数
				auditquotiety = auditquotiety.setScale(2, BigDecimal.ROUND_HALF_UP);
				if ("0.0000".equals(auditquotiety.toString())) {
					cell = new Cell("0");
				} else {
					cell = new Cell(auditquotiety.toString() + "%");
				}
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("业务科室负责人意见");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(6);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管领导意见");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(6);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				document.add(table);
				document.close();
			}
			if ("3".equals(method)) {
				// 创建有六列的表格
				Table table = new Table(6);
				table.setAlignment(Element.ALIGN_CENTER);// 居中显示
				table.setBorderWidth(1);
				table.setBorderColor(Color.BLACK);
				table.setPadding(5);
				table.setSpacing(0);
				int[] a = new int[] { 9, 7, 7, 8, 7, 10 };
				table.setWidths(a);

				// 查询打包项目
				ResultClassArrangeInfo project = intermediaryAuditService.findpackbyid(arrangeId);

				// 根据打包项目查询项目相应的资料
				List<ResultInterAuidt> list = intermediaryAuditService.findengpackaudit(arrangeId);
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审计人员：" + project.getEmpusers() + "   主审："
						+ project.getMainAuditId()));
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审核项目名称：" + project.getProjectName() + "等" + list.size() + "个项目"));
				// 添加表头的元素

				Cell cell = new Cell("建设规模");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("批准概算或批复预算金额");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("发包方式");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("中标价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("合同价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("主要建筑特征");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审时间");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediarySendTime()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审结时间");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediarySendTime()));
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("单位工程名称");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审定金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审减金额(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				BigDecimal totalsentmoney = new BigDecimal(0.00);
				BigDecimal totalauditmoney = new BigDecimal(0.00);
				BigDecimal totalcutmoney = new BigDecimal(0.00);
				// 循环子项目
				for (ResultInterAuidt pro : list) {
					cell = new Cell(pro.getProjectName());
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的送审金额
					if (null != pro.getSentAmount()) {
						totalsentmoney = totalsentmoney.add(pro.getSentAmount());
					}

					cell = new Cell(pro.getSentAmount().toString());
					cell.setHeader(true);
					cell.setColspan(2);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					// 计算总的中介结构审定金额
					if (null != pro.getAuditmoney()) {
						totalauditmoney = totalauditmoney.add(pro.getAuditmoney());
					}
					if (null != pro.getAuditmoney()) {
						cell = new Cell(pro.getAuditmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						cell = new Cell("");
					}
					cell.setHeader(true);
					cell.setColspan(2);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的审减金额
					if (null != pro.getCutmoney()) {
						totalcutmoney = totalcutmoney.add(pro.getCutmoney());
					}
					if (null != pro.getCutmoney()) {
						cell = new Cell(pro.getCutmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						cell = new Cell("");
					}
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}

				cell = new Cell("合计");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				// 保留两位小数
				cell = new Cell(totalsentmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalauditmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalcutmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("其中复核审减金额");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("复核人员意见业务科室专职");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("业务科室负责人意见");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("法制科意见");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管领导意见");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				document.add(table);
				document.close();

			}
			if ("4".equals(method)) {
				// 创建有六列的表格
				Table table = new Table(6);
				table.setAlignment(Element.ALIGN_CENTER);// 居中显示
				table.setBorderWidth(1);
				table.setBorderColor(Color.BLACK);
				table.setPadding(5);
				table.setSpacing(0);
				int[] a = new int[] { 9, 7, 7, 8, 7, 10 };
				table.setWidths(a);

				// 查询打包项目
				ResultClassArrangeInfo project = intermediaryAuditService.findpackbyid(arrangeId);
				// 根据打包项目查询项目相应的资料
				List<ResultInterAuidt> list = intermediaryAuditService.findengpackaudit(arrangeId);
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审计人员：" + project.getEmpusers() + "   主审："
						+ project.getMainAuditId()));
				document.add(new Paragraph(""));
				document.add(new Paragraph("         审核项目名称：" + project.getProjectName() + "等" + list.size() + "个项目"));
				// 添加表头的元素

				Cell cell = new Cell("建设规模");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("批准概算或批复预算金额");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("发包方式");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("中标价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("合同价");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("主要建筑特征");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审时间");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediarySendTime()));
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审结时间");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediarySendTime()));
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("单位工程名称");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("送审金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审定金额(元)");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审减金额(元)");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				BigDecimal totalsentmoney = new BigDecimal(0.00);
				BigDecimal totalauditmoney = new BigDecimal(0.00);
				BigDecimal totalcutmoney = new BigDecimal(0.00);
				// 循环子项目
				for (ResultInterAuidt pro : list) {
					cell = new Cell(pro.getProjectName());
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的送审金额
					if (null != pro.getSentAmount()) {
						totalsentmoney = totalsentmoney.add(pro.getSentAmount());
					}
					cell = new Cell(pro.getSentAmount().toString());
					cell.setHeader(true);
					cell.setColspan(2);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的中介结构审定金额
					if (null != pro.getAuditmoney()) {
						totalauditmoney = totalauditmoney.add(pro.getAuditmoney());
					}
					if (null != pro.getAuditmoney()) {
						cell = new Cell(pro.getAuditmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						cell = new Cell("");
					}
					cell.setHeader(true);
					cell.setColspan(2);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);

					// 计算总的审减金额
					if (null != pro.getCutmoney()) {
						totalcutmoney = totalcutmoney.add(pro.getCutmoney());
					}
					if (null != pro.getCutmoney()) {
						cell = new Cell(pro.getCutmoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					} else {
						cell = new Cell("");
					}
					cell.setHeader(true);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}

				cell = new Cell("合计");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalsentmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalauditmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell(totalcutmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("其中复核审减金额");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setColspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("审计采用的方法(检查,观察,询问,外部调查,重新计算,重新操作,分析)");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(6);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("一,合同约定内容是否违背招,投标文件的实质型内容");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("二,合同约定的决算方式:(措施费,新增单价,新增材料价)");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("三,工程量抽查情况");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("四,清单内项目综合单价是否与投标文件一致;新增项目及新增材料单价是否按合同约定方式计价");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("五,施工企业有无资质,是否按有关部门核定的规费标准计取费用");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("六,安全文明施工的计取,是否按《四川省建设工程安全文明施工费计价管理方法》等相关文件要求执行");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("七,变更签证是否经建设单位,建管单位,监理单位,跟踪审计单位签章认可;变更签证是否按规定出具了区评审招标中心评审函或会审会签表");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(5);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("八,工期情况及索赔情况说明");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("其他需要说明的地方");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("复核工程师签字");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("一,工程概算执行情况");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("二,工程审定金额是否超合同金额及其原因");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("三,复核审定定案金额与征求建,施双方定案表金额是否一致");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("投资审计科科长签字");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("一,报告内容是否完整");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("二,报告内容是否真实");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("三,审定定案金额与复核意见单是否一致");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("法制科科长签字");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(2);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管法制科领导复核内容");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管法制科领导签字");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管投资审计工作领导复核内容");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(4);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("分管投资审计工作领导签字");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new Cell("");
				cell.setHeader(true);
				cell.setRowspan(3);
				cell.setColspan(5);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				document.add(table);
				document.close();

			}
		} catch (Exception e) {

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

	
}
