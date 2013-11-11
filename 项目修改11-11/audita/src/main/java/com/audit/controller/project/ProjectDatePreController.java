/**
 * 资料预审管理控制器
 */
package com.audit.controller.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
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
import com.audit.common.PropertiesGetValue;
import com.audit.component.IAuditDocumentComponent;
import com.audit.entity.Dictionary;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.AuditProjectFinish;
import com.audit.entity.project.DataJoinList;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.PackProjectArrange;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.project.SingleProjectInfo;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.Link;
import com.audit.entity.staff.ProjectOwner;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IPackProjectArrangeService;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.project.ISingleProjectArrangeService;
import com.audit.service.staff.IConstructionService;
import com.audit.service.staff.IIntermediaryagencyService;
import com.audit.service.staff.IProjectOwnerService;
import com.audit.service.system.IDictionaryService;
import com.audit.service.system.IUserInfoService;

/**
 * @author DengXin
 */
@Controller
@RequestMapping("/project/DataPre")
public class ProjectDatePreController {

	@Autowired
	private ICompetenceService competenceService;
	@Autowired
	private IProjectDatePreService projectDatePreService;
	@Autowired
	private IDictionaryService dictionaryService;
	@Autowired
	private IConstructionService constructionService;
	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	@Autowired
	private ISingleProjectArrangeService iSingleProjectArrangeService;

	@Autowired
	private IPackProjectArrangeService iPackProjectArrangeService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IAuditDocumentComponent auditDocumentComponent;

	/**
	 * 
	 * 2013-6-13 资料预审入口
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String addinterdept = request.getParameter("addinterdept");
		String addchild = request.getParameter("addchild");
		String id = request.getParameter("id");
		String projectfinish = request.getParameter("projectfinish");
		String projoinlist = request.getParameter("projoinlist");
		// 获取模块id
		request.setAttribute("moduelid", id);
		if (AuditStringUtils.isNotEmpty(add)) {
			// 查询审计类型
			List<Dictionary> audittypelist = dictionaryService
					.findbyid(PropertiesGetValue
							.getContextProperty("dictionary.type.audittype"));
			request.setAttribute("audittypelist", audittypelist);
			return "/project/projectDataPreAdd";
		} else if (AuditStringUtils.isNotEmpty(addchild)) {
			String key = request.getParameter("key");
			Datapreinfo datapre = projectDatePreService.findtoid(key);
			request.setAttribute("datapre", datapre);
			if (AuditStringUtils.isNotEmpty(datapre.getProownerid())) {
				ProjectOwner p = iProjectOwnerService.getProjectOwner(datapre
						.getProownerid());
				request.setAttribute("ownerName", p.getOwnerName());
			} else {
				request.setAttribute("ownerName", "");
			}
			// 查询审计类型
			List<Dictionary> audittypelist = dictionaryService
					.findbyid(PropertiesGetValue
							.getContextProperty("dictionary.type.audittype"));
			request.setAttribute("audittypelist", audittypelist);
			return "/project/projectDataPreAddChild";
		} else if (AuditStringUtils.isNotEmpty(projectfinish)) {
			String key = request.getParameter("key");
			Datapreinfo datapre = projectDatePreService.findtoid(key);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			AuditProjectFinish audit = new AuditProjectFinish();
			audit.setNo(sdf.format(new Date()) + "-" + datapre.getProjectNo());
			audit.setAuditName(datapre.getProjectName());
			audit.setSendmoeny(datapre.getSentAmount().toString());
			if (AuditStringUtils.isNotEmpty(datapre.getProownerid())) {
				ProjectOwner p = iProjectOwnerService.getProjectOwner(datapre
						.getProownerid());
				audit.setOwnnerName(p.getOwnerName());
			}
			if (AuditStringUtils.isNotEmpty(datapre.getConstructId())) {

				audit.setConsturctName(datapre.getConstructId());

			}
			audit.setOwnnerLink(datapre.getProownerlink());
			audit.setOwnnerTelphone(datapre.getProownertelphone());
			audit.setConsturctLink(datapre.getConstructlink());
			audit.setConsturctTelPhone(datapre.getConstructtelphone());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			if (null != datapre.getProjectTime()) {
				String time = sf.format(datapre.getProjectTime());
				if (AuditStringUtils.isNotEmpty(time)) {
					String dates[] = time.split("-");
					audit.setYear(dates[0]);
					audit.setMonth(dates[1]);
					audit.setDay(dates[2]);
				}
			}
			// 查询该资料是单项目安排了还是打包项目安排
			SingleProjectInfo single = iSingleProjectArrangeService
					.getDataPreId(key);
			String governmentUser = "";
			if (null != single) {
				// 查询主审人员
				EditUser user = userInfoService.findbyid(single
						.getMainAuditId());
				audit.setPrincipalUser(user.getUsername());
				// 查询中介机构名称
				Intermediaryagency interme = iIntermediaryagencyService
						.getIntermediaryagency(single.getIntermediaryId());
				if(null!=interme)
				{	
				audit.setIntermediaryName(interme.getIntermediaryname());
				}
				// 查询复核工程师
				List<EditUser> list = iSingleProjectArrangeService
						.findGovernmentEmpUserName(single.getId());
				for (EditUser edituser : list) {
					governmentUser += edituser.getUsername() + ",";
				}

			}
			// 查询打包项目安排
			PackProjectArrange pack = iPackProjectArrangeService
					.getDataPreId(key);
			if (null != pack) {
				// 查询主审人员
				EditUser user = userInfoService.findbyid(pack.getMainAuditId());
				audit.setPrincipalUser(user.getUsername());
				// 查询中介机构名称
				Intermediaryagency interme = iIntermediaryagencyService
						.getIntermediaryagency(pack.getIntermediaryId());
				if (null != interme) {
					audit.setIntermediaryName(interme.getIntermediaryname());
				}
				// 查询复核工程师
				// 查询复核工程师
				List<EditUser> list = iSingleProjectArrangeService
						.findGovernmentEmpUserName(pack.getId());
				for (EditUser edituser : list) {
					governmentUser += edituser.getUsername() + ",";
				}
			}
			if (AuditStringUtils.isNotEmpty(governmentUser)) {
				governmentUser = governmentUser.substring(0, governmentUser
						.length() - 1);
			}
			audit.setDepartUser(governmentUser);
			request.setAttribute("audit", audit);
			request.setAttribute("key", key);
			return "/project/projectDataPreFinish";
		} else if (AuditStringUtils.isNotEmpty(projoinlist)) {
			String key = request.getParameter("key");
			Datapreinfo datapre = projectDatePreService.findtoid(key);
			DataJoinList datajoin = projectDatePreService
					.finddatajoinlist(datapre.getId());
			request.setAttribute("datapre", datapre);
			request.setAttribute("datajoin", datajoin);
			return "/project/projoinlist";

		}

		else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			Datapreinfo datapre = projectDatePreService.findtoid(key);
			if (null != datapre) {
				if (AuditStringUtils.isNotEmpty(datapre.getBudgetUpdateTime())) {
					datapre.setBudgetUpdateTime(AuditStringUtils
							.getDatetoyyyyMMdd(datapre.getBudgetUpdateTime()));
				}
			}
			// 查询审计类型
			List<Dictionary> audittypelist = dictionaryService
					.findbyid(PropertiesGetValue
							.getContextProperty("dictionary.type.audittype"));
			request.setAttribute("audittypelist", audittypelist);
			request.setAttribute("datapre", datapre);
			if (AuditStringUtils.isNotEmpty(datapre.getProownerid())) {
				ProjectOwner p = iProjectOwnerService.getProjectOwner(datapre
						.getProownerid());
				request.setAttribute("ownerName", p.getOwnerName());
			} else {
				request.setAttribute("ownerName", "");
			}
			if ("0".equals(datapre.getPid())) {
				return "/project/projectDataPreEdit";
			} else {
				return "/project/projectDataPreEditChild";
			}
		} else if (AuditStringUtils.isNotEmpty(addinterdept)) {
			String key = request.getParameter("key");
			// 查询项目安排对应的事务所
			SingleProjectArrange singlePro = projectDatePreService
					.findZJByArrangeId(key);

			ResultClassArrangeInfo inter = intermediaryAuditService.findbyid(key);
			// 项目安排信息
			request.setAttribute("intermediaryAudit", inter);
			request.setAttribute("singlePro", singlePro);
			request.setAttribute("arrageId", key);
			return "/project/legalsectionChiefAuditAddIntermediary";
		} else {
			User user = (User) request.getSession().getAttribute("user");
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			// 查询审计类型
			List<Dictionary> audittypelist = dictionaryService
					.findbyid(PropertiesGetValue
							.getContextProperty("dictionary.type.audittype"));
			request.setAttribute("audittypelist", audittypelist);
			List<String> commonFunction = competenceService.find(id,
					useraccount);
			request.setAttribute("mapFunction", commonFunction);
			return "/project/projectDataAuditIndex";
		}

	}

	/**
	 * 增加交接清单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addprojoin")
	@ResponseBody
	public Map<String, Object> addprojoin(HttpServletRequest request) {
		String key = request.getParameter("key");
		Map<String, Object> map = null;
		DataJoinList datajoin = null;
		try {
			map = new HashMap<String, Object>();
			// 获取参数
			String auditapply = request.getParameter("auditapply");
			String projectlixiangfile = request
					.getParameter("projectlixiangfile");
			String projectgaisuanfile = request
					.getParameter("projectgaisuanfile");
			String projectconstructfile = request
					.getParameter("projectconstructfile");
			String projianlifile = request.getParameter("projianlifile");
			String proconstructtuzi = request.getParameter("proconstructtuzi");
			String profinishtuzi = request.getParameter("profinishtuzi");
			String proinvitefile = request.getParameter("proinvitefile");
			String procomparisionfile = request
					.getParameter("procomparisionfile");
			String designchangetuzi = request.getParameter("designchangetuzi");
			String constructgroupdesign = request
					.getParameter("constructgroupdesign");
			String hideprolog = request.getParameter("hideprolog");
			String visadata = request.getParameter("visadata");
			String dizilook = request.getParameter("dizilook");
			String profinishsettlement = request
					.getParameter("profinishsettlement");
			String ratemeasurementtable = request
					.getParameter("ratemeasurementtable");
			String stuffzmfile = request.getParameter("stuffzmfile");
			String feescertificate = request.getParameter("feescertificate");
			String probiangenvisaconfirm = request
					.getParameter("probiangenvisaconfirm");
			String advanceforpro = request.getParameter("advanceforpro");
			String proquality = request.getParameter("proquality");
			String prostartend = request.getParameter("prostartend");

			datajoin = projectDatePreService.finddatajoinlist(key);
			if (null == datajoin) {
				datajoin = new DataJoinList();
				// 增加
				datajoin.setDatapreId(key);
				datajoin.setAuditapply(auditapply);
				datajoin.setProjectlixiangfile(projectlixiangfile);
				datajoin.setProjectgaisuanfile(projectgaisuanfile);
				datajoin.setProjectconstructfile(projectconstructfile);
				datajoin.setProjianlifile(projianlifile);
				datajoin.setProstartend(prostartend);
				datajoin.setProquality(proquality);
				datajoin.setAdvanceforpro(advanceforpro);
				datajoin.setProbiangenvisaconfirm(probiangenvisaconfirm);
				datajoin.setFeescertificate(feescertificate);
				datajoin.setStuffzmfile(stuffzmfile);
				datajoin.setRatemeasurementtable(ratemeasurementtable);
				datajoin.setProfinishsettlement(profinishsettlement);
				datajoin.setDizilook(dizilook);
				datajoin.setVisadata(visadata);
				datajoin.setHideprolog(hideprolog);
				datajoin.setConstructgroupdesign(constructgroupdesign);
				datajoin.setDesignchangetuzi(designchangetuzi);
				datajoin.setProcomparisionfile(procomparisionfile);
				datajoin.setProinvitefile(proinvitefile);
				datajoin.setProfinishtuzi(profinishtuzi);
				datajoin.setProconstructtuzi(proconstructtuzi);

				map = projectDatePreService.addprojoin(datajoin);
			}
			if (null != datajoin) {
				// 修改
				datajoin.setAuditapply(auditapply);
				datajoin.setProjectlixiangfile(projectlixiangfile);
				datajoin.setProjectgaisuanfile(projectgaisuanfile);
				datajoin.setProjectconstructfile(projectconstructfile);
				datajoin.setProjianlifile(projianlifile);
				datajoin.setProstartend(prostartend);
				datajoin.setProquality(proquality);
				datajoin.setAdvanceforpro(advanceforpro);
				datajoin.setProbiangenvisaconfirm(probiangenvisaconfirm);
				datajoin.setFeescertificate(feescertificate);
				datajoin.setStuffzmfile(stuffzmfile);
				datajoin.setRatemeasurementtable(ratemeasurementtable);
				datajoin.setProfinishsettlement(profinishsettlement);
				datajoin.setDizilook(dizilook);
				datajoin.setVisadata(visadata);
				datajoin.setHideprolog(hideprolog);
				datajoin.setConstructgroupdesign(constructgroupdesign);
				datajoin.setDesignchangetuzi(designchangetuzi);
				datajoin.setProcomparisionfile(procomparisionfile);
				datajoin.setProinvitefile(proinvitefile);
				datajoin.setProfinishtuzi(profinishtuzi);
				datajoin.setProconstructtuzi(proconstructtuzi);
				map = projectDatePreService.updateprojoin(datajoin);
			}

		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("system.exception"));
		}
		return map;

	}

	/**
	 * 分页查询资料预审信息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/find")
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String projectName = request.getParameter("projectName");
		String proownerid = request.getParameter("proownerid");
		String audittype = request.getParameter("audittype");
		String issubmit = request.getParameter("issubmit");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");

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
		datapre.setAudittype(audittype);
		datapre.setBeginTime(beginTime);
		datapre.setEndTime(endTime);
		if (AuditStringUtils.isNotEmpty(issubmit)) {
			datapre.setIsconfirmSubmit(Integer.parseInt(issubmit));
		}
		if (AuditStringUtils.isNotEmpty(method)) {
			FileBelongRelate file = new FileBelongRelate();
			file.setBelongToId(method);
			GridDataModel<FileBelongRelate> gridmodel = projectDatePreService
					.findfile(intPage, pagesize, sort, order, file);
			map.put("rows", gridmodel.getRows());
			map.put("total", gridmodel.getTotal());
		} else {
			GridDataModel<Datapreinfo> gridmodel = projectDatePreService.find(
					intPage, pagesize, sort, order, datapre);
			map.put("rows", gridmodel.getRows());
			map.put("total", gridmodel.getTotal());
		}
		return map;
	}

	/**
	 * 查询预审资料立项文号是否存在
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String, Object> findbyid(HttpServletRequest request) {

		String method = request.getParameter("method");
		if (AuditStringUtils.isNotEmpty(method)) {
			String projectName = request.getParameter("projectName");
			String id = request.getParameter("id");
			Map<String, Object> map = projectDatePreService.findbyname(
					projectName, id);
			return map;
		} else {
			String projectNo = request.getParameter("projectNo");
			String id = request.getParameter("id");
			Map<String, Object> map = projectDatePreService.findbyid(projectNo,
					id);
			return map;
		}

	}

	/**
	 * 查询对应的联系人
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
	 * 查询对应的联系人电话
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
	 * 增加预审资料 2013-6-17
	 * 
	 * @throws IOException
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request,
			MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) throws IOException {
		String msg = "";
		String falg = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 获取值
			// 工程编号
			String datapreno = request.getParameter("datapreno");
			// 报告文号
			String reportNo = request.getParameter("reportNo");
			String projectNo = request.getParameter("projectNo");
			String projectTime = request.getParameter("projectTime");
			String auditMoney = request.getParameter("auditMoney");
			String projectName = request.getParameter("projectName");
			String proownerid = request.getParameter("proownerid");
			String proownerlink = request.getParameter("proownerlink");
			String proownertelphone = request.getParameter("proownertelphone");
			// 获取施工企业名称
			String constructId = request.getParameter("constructName");
			String constructlink = request.getParameter("constructlink");
			String constructtelphone = request
					.getParameter("constructtelphone");

			String constructlinktext = request
					.getParameter("constructlinktext");
			String constructtelphonetext = request
					.getParameter("constructtelphonetext");
			// 获取直接费用
			String zjMoney = request.getParameter("zjMoney");
			// 获取中标价
			String zbMoney = request.getParameter("zbMoney");
			String htmoney = request.getParameter("htmoney");
			String zhanliemoney = request.getParameter("zhanliemoney");
			String audittype = request.getParameter("audittype");
			String datapretime = request.getParameter("datapretime");
			String isExpedited = request.getParameter("isExpedited");
			String state = request.getParameter("state");
			String datapreopinion = request.getParameter("datapreopinion");
			String datapreOperate = request.getParameter("datapreOperate");
			String sentAmount = request.getParameter("sentAmount");
			String isconfirmSubmit = request.getParameter("isconfirmSubmit");
			String pid = request.getParameter("pid");
			// 获取概算文号
			String budgetInfo = request.getParameter("budgetInfo");
			// 获取概算总金额
			String budgetTotalMoney = request.getParameter("budgetTotalMoney");
			// 获取概算工程直接费用
			String budgetDirectMoney = request
					.getParameter("budgetDirectMoney");
			// 获取概算批改时间
			String budgetUpdateTime = request.getParameter("budgetUpdateTime");
			Datapreinfo datapre = new Datapreinfo();
			datapre.setDatapreno(datapreno);
			datapre.setReportNo(reportNo);
			datapre.setProjectNo(projectNo);
			datapre.setBudgetInfo(budgetInfo);
			datapre.setBudgetTotalMoney(budgetTotalMoney);
			datapre.setBudgetDirectMoney(budgetDirectMoney);
			if (AuditStringUtils.isNotEmpty(budgetUpdateTime)) {
				datapre.setBudgetUpdateTime(budgetUpdateTime);
			}

			if (AuditStringUtils.isNotEmpty(projectTime)) {
				datapre.setProjectTime(sdf.parse(projectTime));
			}
			// 判断
			if (AuditStringUtils.isNotEmpty(constructlink)) {
				Link link2 = constructionService.findLinkObject(constructlink);
				datapre.setConstructlink(link2.getLinkname());

			}
			if (AuditStringUtils.isNotEmpty(constructtelphone)) {
				datapre.setConstructtelphone(constructtelphone);
			}
			if (AuditStringUtils.isNotEmpty(constructlinktext)) {
				datapre.setConstructlink(constructlinktext);

			}
			if (AuditStringUtils.isNotEmpty(constructtelphonetext)) {
				datapre.setConstructtelphone(constructtelphonetext);
			}
			datapre.setProjectName(projectName);
			datapre.setProownerid(proownerid);
			datapre.setProownerlink(proownerlink);
			datapre.setProownertelphone(proownertelphone);
			datapre.setConstructId(constructId);
			datapre.setAudittype(audittype);
			if (AuditStringUtils.isNotEmpty(datapretime)) {
				datapre.setDatapretime(sdf.parse(datapretime));
			}
			if (AuditStringUtils.isNotEmpty(auditMoney)) {
				datapre.setAuditMoney(new BigDecimal(auditMoney));
			}
			if (AuditStringUtils.isNotEmpty(zjMoney)) {
				datapre.setZjMoney(new BigDecimal(zjMoney));
			}
			if (AuditStringUtils.isNotEmpty(zbMoney)) {
				datapre.setZbMoney(new BigDecimal(zbMoney));
			}
			if (AuditStringUtils.isNotEmpty(isExpedited)) {
				datapre.setIsExpedited(Integer.parseInt(isExpedited));
			}
			if (AuditStringUtils.isNotEmpty(isconfirmSubmit)) {
				datapre.setIsconfirmSubmit(Integer.parseInt(isconfirmSubmit));
			}
			if (AuditStringUtils.isNotEmpty(state)) {
				datapre.setState(Integer.parseInt(state));
			}
			if (AuditStringUtils.isNotEmpty(sentAmount)) {
				datapre.setSentAmount(new BigDecimal(sentAmount));
			}
			datapre.setDatapreOperate(datapreOperate);
			datapre.setDatapreopinion(datapreopinion);
			datapre.setHtmoney(htmoney);
			datapre.setZhanliemoney(zhanliemoney);
			datapre.setPid(pid);
			// 默认添加未安排
			datapre.setIsArrangement(CommonConstant.ISNOTARRANGEMENT);

			// 获取当前用户
			User user = (User) request.getSession().getAttribute("user");
			List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
			List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
			// 获取概算附件
			List<MultipartFile> file1 = requestMultipart
					.getFiles("budgetInfoFile");
			// 上传文件
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(
					file, "datapre", request);
			// 上传概算附件
			List<Map<String, String>> listmap1 = AuditStringUtils.uploadfile(
					file1, "datapre", request);
			if (null != listmap1 && listmap1.size() != 0) {
				datapre.setBudgetInfoFile(listmap1.get(0).get("url"));
			}
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
			boolean falgstate = projectDatePreService.add(datapre, listfile,
					user);
			if (falgstate) {
				msg = "保存成功";
				falg = "success";
			} else {
				msg = "保存失败";
				falg = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "保存失败";
			falg = "fail";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(
				"{'success':'" + falg + "','msg':'" + msg + "'}");

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
				map.put("msg", PropertiesGetValue
						.getContextProperty("delete.dataprefile.success"));
			} else {
				map.put("success", "fail");
				map.put("msg", PropertiesGetValue
						.getContextProperty("delete.dataprefile.fail"));
			}
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 修改资料预审
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request,
			MultipartHttpServletRequest requestMultipart,
			HttpServletResponse response) {
		String msg = "";
		String falg = "";
		try {
			String id = request.getParameter("id");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 获取值
			// 获取工程编号
			String datapreno = request.getParameter("datapreno");
			// 报告文号
			String reportNo = request.getParameter("reportNo");
			String projectNo = request.getParameter("projectNo");
			String projectTime = request.getParameter("projectTime");
			String auditMoney = request.getParameter("auditMoney");
			String projectName = request.getParameter("projectName");
			String proownerid = request.getParameter("proownerid");
		   
			String proownerlinkname = request.getParameter("proownerlink");
			String proownertelphone = request.getParameter("proownertelphone");
			// 获取直接费用
			String zjMoney = request.getParameter("zjMoney");
			// 获取中标价
			String zbMoney = request.getParameter("zbMoney");
			String htmoney = request.getParameter("htmoney");
			String zhanliemoney = request.getParameter("zhanliemoney");
			String constructId = request.getParameter("constructName");
			String constructlink = request.getParameter("constructlink");
			String constructtelphone = request
					.getParameter("constructtelphone");
			String constructlinktext = request
					.getParameter("constructlinktext");
			String audittype = request.getParameter("audittype");
			String datapretime = request.getParameter("datapretime");
			String isExpedited = request.getParameter("isExpedited");
			String state = request.getParameter("state");
			String datapreopinion = request.getParameter("datapreopinion");
			String datapreOperate = request.getParameter("datapreOperate");
			String sentAmount = request.getParameter("sentAmount");
			String isconfirmSubmit = request.getParameter("isconfirmSubmit1");
			Datapreinfo datapre = projectDatePreService.findtoid(id);
			String updatestate = datapre.getIsconfirmSubmit().toString();
			// 获取概算文号
			String budgetInfo = request.getParameter("budgetInfo");
			// 获取概算总金额
			String budgetTotalMoney = request.getParameter("budgetTotalMoney");
			// 获取概算工程直接费用
			String budgetDirectMoney = request
					.getParameter("budgetDirectMoney");
			// 获取概算批改时间
			String budgetUpdateTime = request.getParameter("budgetUpdateTime");
			datapre.setDatapreno(datapreno);
			datapre.setReportNo(reportNo);
			datapre.setProjectNo(projectNo);
			datapre.setBudgetInfo(budgetInfo);
			datapre.setBudgetTotalMoney(budgetTotalMoney);
			datapre.setBudgetDirectMoney(budgetDirectMoney);
			if (AuditStringUtils.isNotEmpty(budgetUpdateTime)) {
				datapre.setBudgetUpdateTime(budgetUpdateTime);
			}
			if (AuditStringUtils.isNotEmpty(projectTime)) {
				datapre.setProjectTime(sdf.parse(projectTime));
			}
			datapre.setProjectName(projectName);
			datapre.setProownerid(proownerid);
			if (AuditStringUtils.isNotEmpty(zjMoney)) {
				datapre.setZjMoney(new BigDecimal(zjMoney));
			}
			if (AuditStringUtils.isNotEmpty(zbMoney)) {
				datapre.setZbMoney(new BigDecimal(zbMoney));
			}
			datapre.setProownertelphone(proownertelphone);
			datapre.setConstructId(constructId);
			datapre.setConstructtelphone(constructtelphone);
			datapre.setAudittype(audittype);
			if (AuditStringUtils.isNotEmpty(datapretime)) {
				datapre.setDatapretime(sdf.parse(datapretime));
			}
			// 判断
			if (AuditStringUtils.isNotEmpty(constructlink)) {
				Link link2 = constructionService.findLinkObject(constructlink);
				datapre.setConstructlink(link2.getLinkname());

			}
			if (AuditStringUtils.isNotEmpty(constructlinktext)) {
				datapre.setConstructlink(constructlinktext);

			}
			if (AuditStringUtils.isNotEmpty(auditMoney)) {
				datapre.setAuditMoney(new BigDecimal(auditMoney));
			}
			if (AuditStringUtils.isNotEmpty(isExpedited)) {
				datapre.setIsExpedited(Integer.parseInt(isExpedited));
			}
			if (AuditStringUtils.isNotEmpty(isconfirmSubmit)) {
				datapre.setIsconfirmSubmit(Integer.parseInt(isconfirmSubmit));
			}
			if (AuditStringUtils.isNotEmpty(state)) {
				datapre.setState(Integer.parseInt(state));
			}
			if (AuditStringUtils.isNotEmpty(sentAmount)) {
				datapre.setSentAmount(new BigDecimal(sentAmount));
			}
			datapre.setDatapreOperate(datapreOperate);
			datapre.setDatapreopinion(datapreopinion);
			datapre.setHtmoney(htmoney);
			datapre.setZhanliemoney(zhanliemoney);
			datapre.setProownerlink(proownerlinkname);
			datapre.setProownerlink(datapre.getProownerlink());
			// 获取当前用户
			User user = (User) request.getSession().getAttribute("user");
			List<FileBelongRelate> listfile = new ArrayList<FileBelongRelate>();
			List<MultipartFile> file = requestMultipart.getFiles("uploadFile");
			// 获取概算附件
			List<MultipartFile> file1 = requestMultipart
					.getFiles("budgetInfoFile");
			// 上传概算附件
			List<Map<String, String>> listmap1 = AuditStringUtils.uploadfile(
					file1, "datapre", request);
			if (null != listmap1 && listmap1.size() != 0) {
				datapre.setBudgetInfoFile(listmap1.get(0).get("url"));
			}
			// 上传文件
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(
					file, "datapre", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					FileBelongRelate fbr = new FileBelongRelate();
					fbr.setFileName(listmap.get(i).get("fileName"));
					fbr.setUrl(listmap.get(i).get("url"));
					fbr.setUploadTime(new Date());
					fbr.setState("");
					listfile.add(fbr);
				}
			}
			/*
			 * for (int i = 0; i < file.size(); i++) { //判断是否有文件 if
			 * (!file.get(i).isEmpty()) { String fileName =
			 * file.get(i).getOriginalFilename(); // 获取文件后缀 int
			 * index=fileName.lastIndexOf("."); String suffix=
			 * fileName.substring(index,fileName.length()); String name=
			 * fileName.substring(0,index); SimpleDateFormat sf=new
			 * SimpleDateFormat("yyyyMMddHHMM"); String
			 * newFileName=name+sf.format(new Date())+suffix; byte[] bytes =
			 * file.get(i).getBytes(); //Integer fileSize =
			 * (int)file.get(i).getSize()/1024; String uploadPath =
			 * "/upload/"+newFileName; File filePath = new
			 * File(request.getSession
			 * ().getServletContext().getRealPath(uploadPath));
			 *//**
			 * 文件开始上传到服务器上
			 */
			/*
			 * FileCopyUtils.copy(bytes, filePath); //定义文件对象 FileBelongRelate
			 * fbr=new FileBelongRelate(); fbr.setFileName(fileName);
			 * fbr.setUrl(newFileName); fbr.setState(""); listfile.add(fbr); } }
			 */
			boolean falgstate = projectDatePreService.update(datapre, listfile,
					user, updatestate);
			if (falgstate) {
				msg = "修改成功";
				falg = "success";
			} else {
				msg = "修改失败";
				falg = "fail";
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(
					"{'success':'" + falg + "','msg':'" + msg + "'}");
		} catch (Exception e) {

		}
	}

	/**
	 * 删除资料预审
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			String id = request.getParameter("id");
			map = new HashMap<String, Object>();
			// 获取当前用户
			User user = (User) request.getSession().getAttribute("user");
			map = projectDatePreService.delete(id, user);
		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("system.exception"));
		}
		return map;

	}

	/**
	 * (non-Javadoc) 2013-6-13
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */

	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
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
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
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
	 * 导出word
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/outprojectfinish")
	public void outprojectfinish(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String key = request.getParameter("key");
		Datapreinfo datapre = projectDatePreService.findtoid(key);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		AuditProjectFinish audit = new AuditProjectFinish();
		audit.setNo(sdf.format(new Date()) + "-" + datapre.getProjectNo());
		audit.setAuditName(datapre.getProjectName());
		audit.setSendmoeny(datapre.getSentAmount().toString());
		if (AuditStringUtils.isNotEmpty(datapre.getProownerid())) {
			ProjectOwner p = iProjectOwnerService.getProjectOwner(datapre
					.getProownerid());
			audit.setOwnnerName(p.getOwnerName());
		}
		if (AuditStringUtils.isNotEmpty(datapre.getConstructId())) {
			audit.setConsturctName(datapre.getConstructId());
		}
		audit.setOwnnerLink(datapre.getProownerlink());
		audit.setOwnnerTelphone(datapre.getProownertelphone());
		audit.setConsturctLink(datapre.getConstructlink());
		audit.setConsturctTelPhone(datapre.getConstructtelphone());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (datapre.getProjectTime() != null) {
			String time = sf.format(datapre.getProjectTime());
			String dates[] = time.split("-");
			audit.setYear(dates[0]);
			audit.setMonth(dates[1]);
			audit.setDay(dates[2]);
		}
		// 查询该资料是单项目安排了还是打包项目安排
		SingleProjectInfo single = iSingleProjectArrangeService
				.getDataPreId(key);
		String governmentUser = "";
		if (null != single) {
			// 查询主审人员
			EditUser user = userInfoService.findbyid(single.getMainAuditId());
			if (null != user) {
				if (AuditStringUtils.isNotEmpty(user.getUsername())) {
					audit.setPrincipalUser(user.getUsername());
				} else {
					audit.setPrincipalUser("");
				}

			}
			// 查询中介机构名称
			Intermediaryagency interme = iIntermediaryagencyService
					.getIntermediaryagency(single.getIntermediaryId());

			if (null != interme) {
				if (AuditStringUtils.isNotEmpty(interme.getIntermediaryname())) {
					audit.setIntermediaryName(interme.getIntermediaryname());
				} else {
					audit.setIntermediaryName("");
				}

			}

			// 查询复核工程师
			List<EditUser> list = iSingleProjectArrangeService
					.findGovernmentEmpUserName(single.getId());
			if (null != list && list.size() != 0) {
				for (EditUser edituser : list) {
					governmentUser += edituser.getUsername() + ",";
				}
			}
		}
		// 查询打包项目安排
		PackProjectArrange pack = iPackProjectArrangeService.getDataPreId(key);
		if (null != pack) {
			// 查询主审人员
			EditUser user = userInfoService.findbyid(pack.getMainAuditId());
			if (null != user) {
				if (AuditStringUtils.isNotEmpty(user.getUsername())) {
					audit.setPrincipalUser(user.getUsername());
				} else {
					audit.setPrincipalUser("");
				}

			}
			// 查询中介机构名称
			Intermediaryagency interme = iIntermediaryagencyService
					.getIntermediaryagency(pack.getIntermediaryId());

			if (null != interme) {
				if (AuditStringUtils.isNotEmpty(interme.getIntermediaryname())) {
					audit.setIntermediaryName(interme.getIntermediaryname());
				} else {
					audit.setIntermediaryName("");
				}

			}

			// 查询复核工程师
			List<EditUser> list = iSingleProjectArrangeService
					.findGovernmentEmpUserName(pack.getId());
			if (null != list && list.size() != 0) {
				for (EditUser edituser : list) {
					governmentUser += edituser.getUsername() + ",";
				}
			}

		}
		if (AuditStringUtils.isNotEmpty(governmentUser)) {
			governmentUser = governmentUser.substring(0, governmentUser
					.length() - 1);
		}
		audit.setDepartUser(governmentUser);
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template");
		Map<String, String> map = new HashMap<String, String>();
		map.put("no", audit.getNo());
		map.put("auditName", audit.getAuditName());
		map.put("principalUser", audit.getPrincipalUser());
		map.put("intermediaryName", audit.getIntermediaryName());
		map.put("departUser", audit.getDepartUser());
		map.put("ownnerName", audit.getOwnnerName());
		map.put("ownnerLink", audit.getOwnnerLink());
		map.put("ownnerTelphone", audit.getOwnnerTelphone());
		map.put("consturctName", audit.getConsturctName());
		map.put("consturctLink", audit.getConsturctLink());
		map.put("consturctTelPhone", audit.getConsturctTelPhone());
		map.put("sendmoeny", audit.getSendmoeny());
		map.put("month", audit.getMonth());
		map.put("day", audit.getDay());
		map.put("year", audit.getYear());
		String filePath = auditDocumentComponent.writeWord(url,
				"国家建设项目竣工结算资料审核意见单.doc", map);
		String fileName = ""; // 文件名，输出到用户的下载对话框
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
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
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
	 * 导出word
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/outprojoin")
	public void outprojoin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String key = request.getParameter("key");
		Datapreinfo datapre = projectDatePreService.findtoid(key);
		DataJoinList datajoin = projectDatePreService.finddatajoinlist(key);
		String url = request.getSession().getServletContext().getRealPath(
				"/upload/template");
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectName", datapre.getProjectName());
		if (!AuditStringUtils.isNotEmpty(datajoin.getAuditapply())) {
			map.put("Auditapply", "");
		}
		if ("1".equals(datajoin.getAuditapply())) {
			map.put("Auditapply", "√");
		}
		if ("0".equals(datajoin.getAuditapply())) {
			map.put("Auditapply", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProjectlixiangfile())) {
			map.put("projectlixiangfile", "");
		}
		if ("1".equals(datajoin.getProjectlixiangfile())) {
			map.put("projectlixiangfile", "√");
		}
		if ("0".equals(datajoin.getProjectlixiangfile())) {
			map.put("projectlixiangfile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProjectgaisuanfile())) {
			map.put("projectgaisuanfile", "");
		}
		if ("1".equals(datajoin.getProjectgaisuanfile())) {
			map.put("projectgaisuanfile", "√");
		}
		if ("0".equals(datajoin.getProjectgaisuanfile())) {
			map.put("projectgaisuanfile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProjectconstructfile())) {
			map.put("projectconstructfile", "");
		}
		if ("1".equals(datajoin.getProjectconstructfile())) {
			map.put("projectconstructfile", "√");
		}
		if ("0".equals(datajoin.getProjectconstructfile())) {
			map.put("projectconstructfile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProjianlifile())) {
			map.put("projianlifile", "");
		}
		if ("1".equals(datajoin.getProjianlifile())) {
			map.put("projianlifile", "√");
		}
		if ("0".equals(datajoin.getProjianlifile())) {
			map.put("projianlifile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProconstructtuzi())) {
			map.put("proconstructtuzi", "");
		}
		if ("1".equals(datajoin.getProconstructtuzi())) {
			map.put("proconstructtuzi", "√");
		}
		if ("0".equals(datajoin.getProconstructtuzi())) {
			map.put("proconstructtuzi", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProfinishtuzi())) {
			map.put("profinishtuzi", "");
		}
		if ("1".equals(datajoin.getProfinishtuzi())) {
			map.put("profinishtuzi", "√");
		}
		if ("0".equals(datajoin.getProfinishtuzi())) {
			map.put("profinishtuzi", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProinvitefile())) {
			map.put("proinvitefile", "");
		}
		if ("1".equals(datajoin.getProinvitefile())) {
			map.put("proinvitefile", "√");
		}
		if ("0".equals(datajoin.getProinvitefile())) {
			map.put("proinvitefile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProcomparisionfile())) {
			map.put("procomparisionfile", "");
		}
		if ("1".equals(datajoin.getProcomparisionfile())) {
			map.put("procomparisionfile", "√");
		}
		if ("0".equals(datajoin.getProcomparisionfile())) {
			map.put("procomparisionfile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getDesignchangetuzi())) {
			map.put("designchangetuzi", "");
		}
		if ("1".equals(datajoin.getDesignchangetuzi())) {
			map.put("designchangetuzi", "√");
		}
		if ("0".equals(datajoin.getDesignchangetuzi())) {
			map.put("designchangetuzi", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getConstructgroupdesign())) {
			map.put("constructgroupdesign", "");
		}
		if ("1".equals(datajoin.getConstructgroupdesign())) {
			map.put("constructgroupdesign", "√");
		}
		if ("0".equals(datajoin.getConstructgroupdesign())) {
			map.put("constructgroupdesign", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getHideprolog())) {
			map.put("hideprolog", "");
		}
		if ("1".equals(datajoin.getHideprolog())) {
			map.put("hideprolog", "√");
		}
		if ("0".equals(datajoin.getHideprolog())) {
			map.put("hideprolog", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getVisadata())) {
			map.put("visadata", "");
		}
		if ("1".equals(datajoin.getVisadata())) {
			map.put("visadata", "√");
		}
		if ("0".equals(datajoin.getVisadata())) {
			map.put("visadata", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getDizilook())) {
			map.put("dizilook", "");
		}
		if ("1".equals(datajoin.getDizilook())) {
			map.put("dizilook", "√");
		}
		if ("0".equals(datajoin.getDizilook())) {
			map.put("dizilook", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProfinishsettlement())) {
			map.put("profinishsettlement", "");
		}
		if ("1".equals(datajoin.getProfinishsettlement())) {
			map.put("profinishsettlement", "√");
		}
		if ("0".equals(datajoin.getProfinishsettlement())) {
			map.put("profinishsettlement", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getRatemeasurementtable())) {
			map.put("ratemeasurementtable", "");
		}
		if ("1".equals(datajoin.getRatemeasurementtable())) {
			map.put("ratemeasurementtable", "√");
		}
		if ("0".equals(datajoin.getRatemeasurementtable())) {
			map.put("ratemeasurementtable", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getStuffzmfile())) {
			map.put("stuffzmfile", "");
		}
		if ("1".equals(datajoin.getStuffzmfile())) {
			map.put("stuffzmfile", "√");
		}
		if ("0".equals(datajoin.getStuffzmfile())) {
			map.put("stuffzmfile", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getFeescertificate())) {
			map.put("feescertificate", "");
		}
		if ("1".equals(datajoin.getFeescertificate())) {
			map.put("feescertificate", "√");
		}
		if ("0".equals(datajoin.getFeescertificate())) {
			map.put("feescertificate", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProbiangenvisaconfirm())) {
			map.put("probiangenvisaconfirm", "");
		}
		if ("1".equals(datajoin.getProbiangenvisaconfirm())) {
			map.put("probiangenvisaconfirm", "√");
		}
		if ("0".equals(datajoin.getProbiangenvisaconfirm())) {
			map.put("probiangenvisaconfirm", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getAdvanceforpro())) {
			map.put("advanceforpro", "");
		}
		if ("1".equals(datajoin.getAdvanceforpro())) {
			map.put("advanceforpro", "√");
		}
		if ("0".equals(datajoin.getAdvanceforpro())) {
			map.put("advanceforpro", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProquality())) {
			map.put("proquality", "");
		}
		if ("1".equals(datajoin.getProquality())) {
			map.put("proquality", "√");
		}
		if ("0".equals(datajoin.getProquality())) {
			map.put("proquality", "X");
		}
		if (!AuditStringUtils.isNotEmpty(datajoin.getProstartend())) {
			map.put("prostartend", "");
		}
		if ("1".equals(datajoin.getProstartend())) {
			map.put("prostartend", "√");
		}
		if ("0".equals(datajoin.getProstartend())) {
			map.put("prostartend", "X");
		}

		String filePath = auditDocumentComponent.writeWord(url, "资料接交清单.doc",
				map);
		String fileName = ""; // 文件名，输出到用户的下载对话框
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("/") + 1, filePath.length()).getBytes(
					"GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(
					filePath.lastIndexOf("\\") + 1, filePath.length())
					.getBytes("GB2312"), "ISO8859_1");
		}
		fileName = new String(fileName.getBytes("ISO8859_1"), "GBK");
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
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
