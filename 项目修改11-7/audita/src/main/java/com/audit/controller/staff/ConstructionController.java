/**
 * 施工企业管理
 */
package com.audit.controller.staff;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.Dictionary;
import com.audit.entity.User;
import com.audit.entity.staff.Construction;
import com.audit.entity.staff.Link;
import com.audit.service.common.ICompetenceService;
import com.audit.service.staff.IConstructionService;
import com.audit.service.system.IDictionaryService;

/**
 * @author dengyong
 */
@Controller
@RequestMapping("staff/practitioners/construction")
public class ConstructionController implements IControllerBase {
	@Autowired
	private IConstructionService constructionService;

	@Autowired
	private IDictionaryService dictionaryService;

	@Autowired
	private ICompetenceService competenceService;

	/**
	 * 添加施工企业
	 */
	@Override
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {

			Integer beginrow = Integer.parseInt(request.getParameter("beginrow"));
			Integer totalrow = Integer.parseInt(request.getParameter("totalrow"));

			// 定义联系人
			List<Link> listlink = new ArrayList<Link>();
			for (int i = beginrow; i <= totalrow; i++) {
				Link link = new Link();
				String linkname = request.getParameter("linkname" + i);
				String linktellphone = request.getParameter("linktellphone" + i);
				if (AuditStringUtils.isNotEmpty(linkname) || AuditStringUtils.isNotEmpty(linktellphone)) {
					link.setLinkname(linkname);
					link.setLinktellphone(linktellphone);
					listlink.add(link);
				}
			}
			String constructname = request.getParameter("constructname");
			String deptreferred = request.getParameter("deptreferred");
			String enterprisetype = request.getParameter("enterprisetype");
			String deptcode = request.getParameter("deptcode");
			String regaddress = request.getParameter("regaddress");
			String regfunds = request.getParameter("regfunds");
			String legal = request.getParameter("legal");
			String deptqualifica = request.getParameter("deptqualifica");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String ischose = request.getParameter("ischose");
			String state = request.getParameter("state");
			String remark = request.getParameter("remark");
			// 调用业务层方法
			Construction con = new Construction();
			con.setConstructname(constructname);
			con.setDeptreferred(deptreferred);
			con.setEnterprisetype(enterprisetype);
			con.setDeptcode(deptcode);
			con.setRegaddress(regaddress);
			if (!AuditStringUtils.isNotEmpty(regfunds)) {
				regfunds = "0";
			}
			con.setRegfunds(Float.parseFloat(regfunds));
			con.setLegal(legal);
			con.setDeptqualifica(deptqualifica);
			con.setEmail(email);
			con.setAddress(address);
			con.setIschose(Integer.parseInt(ischose));
			con.setState(Integer.parseInt(state));
			con.setRemark(remark);
			map = constructionService.add(con, listlink);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 删除施工企业
	 */
	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			String id = request.getParameter("id");
			// 调用业务层删除
			map = constructionService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 销毁施工企业
	 */
	@Override
	@RequestMapping("/destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {

			String id = request.getParameter("id");
			Construction con = new Construction();
			con.setId(id);
			con.setState(CommonConstant.COMM_STATE_DISABLE);
			// 调用业务层修改
			map = constructionService.destory(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 分页查询施工企业信息
	 * 
	 * @throws UnsupportedEncodingException
	 * 
	 */
	@RequestMapping("/find")
	@ResponseBody
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
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
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<Construction> gridmodel = constructionService.find(intPage, pagesize, sort, order, name, state);
		map.put("rows", gridmodel.getRows());
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 * 施工企业管理入口方法
	 */
	@Override
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		// 获取模块id
		String id = request.getParameter("id");
		request.getSession().setAttribute("moduelid", id);
		if (add != null && !"".equals(add)) {
			// 查询企业资质
			List<Dictionary> listdeptqualifica = dictionaryService.findbyid(PropertiesGetValue
					.getContextProperty("dictionary.type.qiye"));
			request.setAttribute("listdeptqualifica", listdeptqualifica);
			return "/staff/constructadd";
		} else if (update != null && !"".equals(update)) {
			// 查询企业资质
			List<Dictionary> listdeptqualifica = dictionaryService.findbyid(PropertiesGetValue
					.getContextProperty("dictionary.type.qiye"));
			request.setAttribute("listdeptqualifica", listdeptqualifica);
			// id
			String key = request.getParameter("key");
			// 查询施工企业对象
			Construction construct = constructionService.findbyid(key);
			request.setAttribute("construct", construct);
			// 查询施工企业的联系人信息
			List<Link> listlink = constructionService.findLink(key);
			List<Link> list = new ArrayList<Link>();
			if (null != listlink && listlink.size() != 0) {
				for (int i = 0; i < listlink.size(); i++) {
					Link link = new Link();
					link.setLinkname(listlink.get(i).getLinkname());
					link.setLinktellphone(listlink.get(i).getLinktellphone());
					Integer count = i + 1;
					link.setNumber(AuditStringUtils.converyNumToDaXie(count.toString()));
					list.add(link);
				}
			}
			request.setAttribute("listlink", list);
			return "/staff/constructedit";
		} else {

			String useraccount = "";
			if (null != user) {
				useraccount = user.getUserAccount();
			}
			List<String> cf = competenceService.find(id, useraccount);
			request.getSession().setAttribute("conmap", cf);
			return "/staff/constructIndex";
		}
	}

	/**
	 * 修改施工企业信息
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			Integer beginrow = Integer.parseInt(request.getParameter("beginrow"));
			Integer totalrow = Integer.parseInt(request.getParameter("totalrow"));

			// 定义联系人
			List<Link> listlink = new ArrayList<Link>();
			for (int i = beginrow; i <= totalrow; i++) {
				Link link = new Link();
				String linkname = request.getParameter("linkname" + i);
				String linktellphone = request.getParameter("linktellphone" + i);
				if (AuditStringUtils.isNotEmpty(linkname) || AuditStringUtils.isNotEmpty(linktellphone)) {
					link.setLinkname(linkname);
					link.setLinktellphone(linktellphone);
					listlink.add(link);
				}
			}
			String constructname = request.getParameter("constructname");
			String id = request.getParameter("id");
			String deptreferred = request.getParameter("deptreferred");
			String enterprisetype = request.getParameter("enterprisetype");
			String deptcode = request.getParameter("deptcode");
			String regaddress = request.getParameter("regaddress");
			String regfunds = request.getParameter("regfunds");
			String legal = request.getParameter("legal");
			String deptqualifica = request.getParameter("deptqualifica");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String ischose = request.getParameter("ischose");
			String state = request.getParameter("state");
			String remark = request.getParameter("remark");
			// 调用业务层方法查询施工企业对象
			Construction con = constructionService.findbyid(id);
			con.setConstructname(constructname);
			con.setDeptreferred(deptreferred);
			con.setEnterprisetype(enterprisetype);
			con.setDeptcode(deptcode);
			con.setRegaddress(regaddress);
			if (!AuditStringUtils.isNotEmpty(regfunds)) {
				regfunds = "0";
			}
			con.setRegfunds(Float.parseFloat(regfunds));
			con.setLegal(legal);
			con.setDeptqualifica(deptqualifica);
			con.setEmail(email);
			con.setAddress(address);
			con.setIschose(Integer.parseInt(ischose));
			con.setState(Integer.parseInt(state));
			con.setRemark(remark);
			map = constructionService.update(con, listlink);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
