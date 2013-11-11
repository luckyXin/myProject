package com.audit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.CommonConstant;
import com.audit.common.PropertiesGetValue;
import com.audit.entity.JsonMenu;
import com.audit.entity.Menu;
import com.audit.entity.Module;
import com.audit.entity.User;
import com.audit.service.common.ILoginService;

@Controller
public class LoginAction {

	@Autowired
	private ILoginService loginService = null;

	@RequestMapping("/login")
	public String login(HttpServletRequest request, String userAccount,
			String password, User user) {

		user = new User();
		user.setPassword(password);
		user.setUserAccount(userAccount);
		user = loginService.login(user);
		// 登录验证
		if (null!=user) {
			List<Module> mlist = loginService.getPermission(user);
			
			StringBuffer sbf = new StringBuffer();
			for(Iterator<Module> i = mlist.iterator();i.hasNext();){
				if(!StringUtils.isEmpty(sbf.toString())){
					sbf.append(",");
				}
				sbf.append(request.getContextPath()+i.next().getUrl());
			}
			user.setPermission(sbf.toString());

			request.getSession().setAttribute("user", user);

			// 获取顶层菜单
			List<Menu> menus = loginService.getTopMenu(user);
			request.setAttribute("topMenus", menus);
			return CommonConstant.STR_RESULT_LOGIN_SUCCESS;
		} else {

			// 登录失败消息设定
			String msg = PropertiesGetValue.getContextProperty("login.fail");
			request.setAttribute("msg", msg);
			return CommonConstant.STR_RESULT_LOGIN;
		}
	}

	@RequestMapping("/inputLogin")
	public String inputLogin() {
		return CommonConstant.STR_RESULT_LOGIN;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

	@RequestMapping("/getTopMenu")
	public @ResponseBody List<JsonMenu> getTopMenu(HttpServletRequest request,
			String topMenuId) {
		User user = new User();
		if (request.getSession().getAttribute("user") != null
				&& request.getSession().getAttribute("user") instanceof 

User) {
			user = (User) request.getSession().getAttribute("user");
		}

		List<JsonMenu> menu = loginService.getSubMenu(topMenuId, user);

		return menu;
	}
	/**
	 * 获取登录用户的操作菜单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/getusermenu")
	@ResponseBody
	public List<Map<String, String>> listStudentCustomerInfo(ModelMap 

model,HttpServletRequest request) {
		List<Map<String, String>> m_listmap = new ArrayList<Map<String, String>>();
		    
		    //获取参数
			String  pstrParentValue=request.getParameter("pstrParentValue");
			String  menuid=request.getParameter("menuid");
			String  piParentID=request.getParameter("piParentID");
			if (piParentID==null) {
				piParentID = "-1";
			}
			if(null!=menuid && !"".equals(menuid)){
				pstrParentValue=menuid;
			}
			if(null==pstrParentValue || "".equals(pstrParentValue)){
				pstrParentValue="0";
			}
			//取出session中的用户
		   User	user = (User) request.getSession().getAttribute("user");
			//查询菜单
		   List<Menu> list=loginService.getMenu(user, pstrParentValue);
		   
		   if(null!=list && list.size()!=0)
		   {   
			 for(int i=0;i<list.size();i++){
					Map<String,String> map=new HashMap<String,String>();
					map.put("Ico", "Folder");
					map.put("OnClick", "");
					map.put("Caption", list.get(i).getMenuName());
					map.put("Alt", list.get(i).getMenuName());
					map.put("checkBox", "false");
					map.put("PostValue", list.get(i).getId().toString());
					map.put("PostPage", request.getContextPath()+"/getusermenu.do");
					map.put("ParentID", piParentID.toString());
					m_listmap.add(map);
			}
		   }
			//查询功能权限
			 List<Module> listm=loginService.getModule(user, pstrParentValue);
				if(null!=listm && listm.size()!=0){
					for(int i=0;i<listm.size();i++){
						Map<String,String> map=new 
                        HashMap<String,String>();
						map.put("Ico", "table");
						map.put("OnClick", "openModule('"+listm.get(i).getId()+"','"+listm.get(i).getModulename()+"','"+listm.get(i).getUrl()+"')");
						map.put("Caption", listm.get(i).getModulename());
						map.put("Alt", listm.get(i).getModulename());
						map.put("ParentID", piParentID.toString());
						m_listmap.add(map);
				    }
			}
	        return m_listmap;
	}
}
