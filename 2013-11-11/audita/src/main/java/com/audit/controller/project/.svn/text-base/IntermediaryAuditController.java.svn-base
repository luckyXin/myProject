/**
 * 中介机构审核
 */
package com.audit.controller.project;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.Auith;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.DataPreBaseInfo;
import com.audit.entity.project.IntermediaryAudit;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.WorkloadInfo;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IProEngineerAuditService;
import com.audit.service.system.IUserInfoService;

/**
 * @author dengyong
 * 
 */
@Controller
@RequestMapping("/project/intermediaryAudit")
public class IntermediaryAuditController {

	@Autowired
	private ICompetenceService competenceService;
	@Autowired
	private IIntermediaryAuditService intermediaryAuditService;

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IProEngineerAuditService proEngineerAuditService;

	/**
	 * 录入中介审核
	 * 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {

		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			// 获取登录用户
			User user = (User) request.getSession().getAttribute("user");
			String method = request.getParameter("method");
			String auditlv = request.getParameter("auditlv");
			if (AuditStringUtils.isNotEmpty(method)) {
				String arrangeId = request.getParameter("arrangeId");
				String auditoktime = request.getParameter("auditoktime");
				String deferday = request.getParameter("deferday");
				String overday = request.getParameter("overday");
				String state = request.getParameter("state");
				ProIntermediaryAudit pro = new ProIntermediaryAudit();
				pro.setArrangeId(arrangeId);
				if (AuditStringUtils.isNotEmpty(auditoktime)) {
					pro.setAuditoktime(AuditStringUtils.strToDate(auditoktime, "yyyy-MM-dd"));
				}
				pro.setDeferday(deferday);
				pro.setOverday(overday);
				if ("1".equals(state)) {
					pro.setIsconfirm(Integer.parseInt(CommonConstant.INTEROKAUDITSTATE));
				} else {
					pro.setIsconfirm(Integer.parseInt(CommonConstant.INTERNOAUDITSTATE));
				}
				map = intermediaryAuditService.addsubmit(pro, user);

			} else {
				String arrangeId = request.getParameter("arrangeId");
				String datapreId = request.getParameter("datapreId");
				String auditmoney = request.getParameter("auditmoney");
				String cutmoney = request.getParameter("cutmoney");
				String auditoktime = request.getParameter("auditoktime");
				String deferday = request.getParameter("deferday");
				String overday = request.getParameter("overday");
				String remark = request.getParameter("remark");
				String isconfirm = request.getParameter("isconfirm");
				String projecttype = request.getParameter("projecttype");
				ProIntermediaryAudit prointer = intermediaryAuditService.findobject(null, datapreId);
				if (null == prointer) {
					ProIntermediaryAudit interadd = new ProIntermediaryAudit();
					interadd.setAuditlv(auditlv);
					interadd.setAuditmoney(Auith.toConversion(auditmoney));
					interadd.setCutmoney(Auith.toConversion(cutmoney));
					if (AuditStringUtils.isNotEmpty(auditoktime)) {
						interadd.setAuditoktime(AuditStringUtils.strToDate(auditoktime, "yyyy-MM-dd"));
					}
					interadd.setDeferday(deferday);
					interadd.setOverday(overday);
					interadd.setRemark(remark);
					interadd.setArrangeId(arrangeId);
					interadd.setDatapreId(datapreId);
					if (AuditStringUtils.isNotEmpty(isconfirm)) {

						if ("0".equals(projecttype)) {
							interadd.setIsconfirm(Integer.parseInt(isconfirm));
						}
					}
					if ("1".equals(projecttype)) {
						interadd.setAuditstate(CommonConstant.INTEROKAUDITSTATE);
					}
					map = intermediaryAuditService.add(interadd, user);
				} else {
					prointer.setAuditlv(auditlv);
					prointer.setAuditmoney(Auith.toConversion(auditmoney));
					prointer.setCutmoney(Auith.toConversion(cutmoney));
					if (AuditStringUtils.isNotEmpty(auditoktime)) {
						prointer.setAuditoktime(AuditStringUtils.strToDate(auditoktime, "yyyy-MM-dd"));
					}
					prointer.setDeferday(deferday);
					prointer.setOverday(overday);
					prointer.setRemark(remark);
					if (AuditStringUtils.isNotEmpty(isconfirm)) {
						if ("0".equals(projecttype)) {
							prointer.setIsconfirm(Integer.parseInt(isconfirm));
						}
					}
					if ("1".equals(projecttype)) {
						prointer.setAuditstate(CommonConstant.INTEROKAUDITSTATE);
					}
					map = intermediaryAuditService.update(prointer, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("IntermediaryAudit.add.fail"));
		}
		return map;
	}

	/**
	 * 查询中介审核
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String module = request.getParameter("module");
		String projectName = request.getParameter("projectName");
		String proownerid = request.getParameter("proownerid");
		String intermeidiaryId = request.getParameter("intermeidiaryId");
		String isconfirm = request.getParameter("isconfirmss");
		String arrangerpro = request.getParameter("arrangerpro");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String timeLimitStart = request.getParameter("timeLimitStart");
		String timeLimitEnd = request.getParameter("timeLimitEnd");
		String isUrgent = request.getParameter("isUrgent");

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		IntermediaryAudit inter = new IntermediaryAudit();
		inter.setFiled(sort);
		inter.setSort(order);
		inter.setModule(module);
		inter.setProjectName(projectName);
		inter.setProownerid(proownerid);
		inter.setIntermediaryId(intermeidiaryId);
		inter.setIsconfirm(isconfirm);
		inter.setBeginTime(beginTime);
		inter.setEndTime(endTime);
		inter.setIsUrgent(isUrgent);
		inter.setTimeLimitStart(timeLimitStart);
		inter.setTimeLimitEnd(timeLimitEnd);
		if (null != user) {
			inter.setUserAccount(user.getUserAccount());
		} else {
			inter.setUserAccount("");
		}
		GridDataModel<IntermediaryAudit> gridmodel = intermediaryAuditService.find(intPage, pagesize, sort, order,
				inter, arrangerpro);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 查询中介审核
	 */
	@RequestMapping("/findauditmoney")
	@ResponseBody
	public Map<String, Object> findauditmoney(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String arrangeId = request.getParameter("arrangeId");
		BigDecimal auditmoney = new BigDecimal(0.00);
		BigDecimal cutmoney = new BigDecimal(0.00);
		List<ProIntermediaryAudit> listpro = intermediaryAuditService.findauditcutmoney(arrangeId);
		if (null != listpro && listpro.size() != 0) {
			for (ProIntermediaryAudit pro : listpro) {
				auditmoney = auditmoney.add(pro.getAuditmoney());
				cutmoney = cutmoney.add(pro.getCutmoney());
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
	 * 
	 * 跳转入口
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");

		String adds = request.getParameter("adds");
		// 获取模块id
		String id = request.getParameter("id");
		request.setAttribute("cdmoduelId", id);
		if (AuditStringUtils.isNotEmpty(add)) {
			String module = request.getParameter("id");
			request.setAttribute("auditsubpromoduelId", module);
			// 中介审核录入
			// 获取id
			String key = request.getParameter("key");
			ResultClassArrangeInfo inter = null;
			Integer protype = 0;
			// 查询单项目
			inter = intermediaryAuditService.findbyid(key);
			// 如果单项目没有查询到查询打包项目
			if (null == inter) {
				// 查询打包项目
				inter = intermediaryAuditService.findpackbyid(key);
				protype = 1;
			}
			// 查询是否有科长意见
			SectionChiefAuditBaseInfo section = intermediaryAuditService.findbysection(key);
			if (null != section) {
				EditUser editUser = userInfoService.findbyuserAccountobject(section.getUserAccount());
				section.setUserAccount(editUser.getUsername());
			}

			// 查询项目安排的总金额

			BigDecimal auditmoney = new BigDecimal(0.00);
			BigDecimal cutmoney = new BigDecimal(0.00);
			List<ProIntermediaryAudit> listpro = intermediaryAuditService.findauditcutmoney(key);
			if (null != listpro && listpro.size() != 0) {
				for (ProIntermediaryAudit pro : listpro) {
					auditmoney = auditmoney.add(pro.getAuditmoney());
					cutmoney = cutmoney.add(pro.getCutmoney());
				}
				request.setAttribute("auditoktime", listpro.get(0).getAuditoktime());
				request.setAttribute("deferday", listpro.get(0).getDeferday());
				request.setAttribute("overday", listpro.get(0).getOverday());
			}
			// 保留两位小数
			auditmoney = auditmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
			cutmoney = cutmoney.setScale(2, BigDecimal.ROUND_HALF_UP);
			request.setAttribute("zongauditmoney", auditmoney);
			request.setAttribute("zongcutmoney", cutmoney);
			request.setAttribute("section", section);
			// 判断是否打包项目
			if (protype == 0) {
				// 查询单个项目的资料信息
				String datapreId = inter.getDatapreId();
				List<DataPreBaseInfo> datagov = proEngineerAuditService.finddatapreBydateId(datapreId);
				// 保存中介机构看的资料信息
				request.setAttribute("datagov", datagov.get(0));
				// 查询核对工作量时间
				WorkloadInfo wt = proEngineerAuditService.findworkTime(datapreId);
				// 保存工作量时间
				request.setAttribute("wt", wt);
				ProIntermediaryAudit prointer = intermediaryAuditService.findobject(key, null);
				request.setAttribute("prointer", prointer);
			} else {
				// 查询打包项目是否提交
				Integer count = intermediaryAuditService.findissubmitcount(key);
				request.setAttribute("ispacksubmit", count);
			}
			// 项目安排信息
			request.setAttribute("intermediaryAudit", inter);
			// 保存项目类型
			request.setAttribute("protype", protype);
			return "/project/IntermediaryAuditadd";

		} else if (AuditStringUtils.isNotEmpty(adds)) {

			String module = request.getParameter("id");
			request.setAttribute("auditsubpromoduelId", module);

			// 获取项目基本信息
			String key = request.getParameter("key");
			ResultClassArrangeInfo inter = null;
			// 查询单项目
			inter = intermediaryAuditService.findsingleauditbyid(key);
			// 如果单项目没有查询到查询打包项目
			if (null == inter) {
				// 查询打包项目
				inter = intermediaryAuditService.findpackauditbyid(key);
			}
			// 项目安排信息
			request.setAttribute("intermediaryAudit", inter);
			// 保存资料id
			request.setAttribute("datapreId", key);
			// 获取中介审核信息
			ProIntermediaryAudit prointer = intermediaryAuditService.findobject(null, key);
			request.setAttribute("prointer", prointer);
			// 查询单个项目的资料信息
			List<DataPreBaseInfo> datagov = proEngineerAuditService.finddatapreBydateId(key);
			// 保存中介机构看的资料信息
			request.setAttribute("datagov", datagov.get(0));
			return "/project/IntermediaryAuditadds";
		} else {
			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.setAttribute("intermap", cf);
			return "/project/IntermediaryAuditIndex";
		}
	}

}
