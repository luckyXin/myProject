/**
 * 
 */
package com.audit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.service.system.IUserInfoService;

/**
 * @author User
 * 
 */
@Controller
@RequestMapping("/common/system/updateuser/")
public class SysCommonController {

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 用户管理入口方法
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		return "/system/updatepwd";
	}

	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String oldpwd = request.getParameter("oldpwd");
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		EditUser edituser = userInfoService.findbyid(user.getId());
		// 判断和当前用户密码是否相同
		if (oldpwd.equals(edituser.getPassword())) {
			map.put("yes", "yes");
		} else {
			map.put("yes", "no");
		}
		return map;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String newpwd = request.getParameter("newpwd");
		// 获取登录用户
		User user = (User) request.getSession().getAttribute("user");
		EditUser edituser = new EditUser();
		edituser.setId(user.getId());
		edituser.setPassword(newpwd);
		try {
			map = userInfoService.updatepwd(edituser);
		} catch (Exception e) {
			map.put("msg", "修改失败");
		}
		map.put("msg", "修改成功");
		return map;
	}
}
