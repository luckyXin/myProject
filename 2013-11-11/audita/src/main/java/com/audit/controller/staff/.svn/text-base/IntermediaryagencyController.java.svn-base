/**
 * 中介机构管理
 */
package com.audit.controller.staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.Dictionary;
import com.audit.entity.User;
import com.audit.service.common.ICompetenceService;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.Link;
import com.audit.service.staff.IIntermediaryagencyService;

/**
 * @author sheng
 */
@Controller
@RequestMapping("staff/intermediaryagency")
public class IntermediaryagencyController implements IControllerBase {

	@Autowired
	private ICompetenceService iCompetenceService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	/**
	 * 页面跳转控制
	 */
	@RequestMapping("input")
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String id = request.getParameter("id");
		if (AuditStringUtils.isNotEmpty(add)) {

			List<Dictionary> DicDep = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.qiye"));
			List<Dictionary> DicBns = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.businessType"));
			List<Dictionary> DicYear = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.bidYear"));

			request.setAttribute("DicDep", DicDep);
			request.setAttribute("DicBns", DicBns);
			request.setAttribute("DicYear", DicYear);

			// 处理类别
			request.setAttribute("processType", "add");

			// 菜单 ID
			request.setAttribute("id", id);

			// 处理地址
			request.setAttribute("url", "/staff/intermediaryagency/add.do");

			return "/staff/intermediaryagencyEdit";
		} else if (AuditStringUtils.isNotEmpty(update)) {

			// 获取数据实体
			String key = request.getParameter("key");
			request.setAttribute("inter", iIntermediaryagencyService.getIntermediaryagency(key));

			List<Dictionary> DicDep = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.qiye"));
			List<Dictionary> DicBns = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.businessType"));
			List<Dictionary> DicYear = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.bidYear"));

			request.setAttribute("DicDep", DicDep);
			request.setAttribute("DicBns", DicBns);
			request.setAttribute("DicYear", DicYear);

			// 处理类别
			request.setAttribute("processType", "update");

			// 更新菜单旧内容
			request.setAttribute("id", id);

			// 获取联系人
			List<Link> links = iIntermediaryagencyService.getLinks(key);

			// 第一联系人设定
			if (links.size() != 0) {
				request.setAttribute("firstLink", links.get(0));
				links.remove(0);
			} else {
				request.setAttribute("firstLink", new Link());
			}

			// 获取联系人
			request.setAttribute("links", links);

			// 处理地址
			request.setAttribute("url", "/staff/intermediaryagency/update.do");

			return "/staff/intermediaryagencyEdit";
		} else {
			List<Dictionary> DicYear = iIntermediaryagencyService.getDictionary(PropertiesGetValue
					.getContextProperty("dictionary.type.bidYear"));

			User user = (User) request.getSession().getAttribute("user");

			String userAccount = user.getUserAccount();

			List<String> commonFunction = iCompetenceService.find(id, userAccount);

			request.setAttribute("conmap", commonFunction);

			request.setAttribute("id", id);

			request.setAttribute("DicYear", DicYear);

			return "/staff/intermediaryagencyIndex";
		}
	}

	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String intermediaryName = request.getParameter("intermediaryName");
		String bidyear = request.getParameter("bidyear");
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<Intermediaryagency> inter = iIntermediaryagencyService.find(page, pagesize, name, order,
				intermediaryName, bidyear);
		map.put("rows", inter.getRows());
		map.put("total", inter.getTotal());
		return map;
	}

	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {

		Intermediaryagency inter = new Intermediaryagency();

		inter.setIntermediaryname(request.getParameter("intermediaryname"));
		inter.setReferred(request.getParameter("referred"));
		inter.setBusinesstype(request.getParameter("businesstype"));
		inter.setDeptqualifica(request.getParameter("deptqualifica"));
		inter.setLegal(request.getParameter("legal"));
		inter.setRegaddress(request.getParameter("regaddress"));
		inter.setAddress(request.getParameter("regaddress"));
		inter.setIstenderunit(Integer.valueOf(request.getParameter("istenderunit")));
		inter.setBidyear(request.getParameter("bidyear"));
		inter.setManageragency(request.getParameter("manageragency"));
		inter.setRemark(request.getParameter("remark"));

		String[] linktellphones = request.getParameterValues("linktellphone");

		String[] linknames = request.getParameterValues("linkname");

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map = iIntermediaryagencyService.addIntermediaryagency(inter, linknames, linktellphones);

		} catch (Exception e) {

			map.put("msg", PropertiesGetValue.getContextProperty("intermediaryagency.add.fail"));
		}
		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {

		Intermediaryagency inter = new Intermediaryagency();

		inter.setId(request.getParameter("id"));
		inter.setIntermediaryname(request.getParameter("intermediaryname"));
		inter.setReferred(request.getParameter("referred"));
		inter.setBusinesstype(request.getParameter("businesstype"));
		inter.setDeptqualifica(request.getParameter("deptqualifica"));
		inter.setLegal(request.getParameter("legal"));
		inter.setRegaddress(request.getParameter("regaddress"));
		inter.setAddress(request.getParameter("regaddress"));
		inter.setIstenderunit(Integer.valueOf(request.getParameter("istenderunit")));
		inter.setBidyear(request.getParameter("bidyear"));
		inter.setManageragency(request.getParameter("manageragency"));
		inter.setRemark(request.getParameter("remark"));

		String[] linktellphones = request.getParameterValues("linktellphone");

		String[] linknames = request.getParameterValues("linkname");

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			map = iIntermediaryagencyService.updateIntermediaryagency(inter, linknames, linktellphones);

		} catch (Exception e) {

			map.put("msg", PropertiesGetValue.getContextProperty("intermediaryagency.update.fail"));
		}
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		// 获取业主ID 执行删除操作
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			map = iIntermediaryagencyService.destroyIntermediaryagency(id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", PropertiesGetValue.getContextProperty("intermediaryagency.destroy.fail"));
		}
		return map;
	}

}
