package com.audit.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.JsonMenu;
import com.audit.entity.Menu;
import com.audit.entity.MenuUserInfoInput;
import com.audit.entity.Module;
import com.audit.entity.ParameterMenu;
import com.audit.entity.User;
import com.audit.service.common.ILoginService;

/**
 * 登录业务层
 * 
 * @author DengXin
 */
public class LoginServiceImpl implements ILoginService {

	/**
	 * sqlMap操作DAO
	 */
	private IbatisCommonDAO ibatisCommonDAO = null;

	/**
	 * @param ibatisCommonDAO the ibatisCommonDAO to set
	 */
	public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO) {
		this.ibatisCommonDAO = ibatisCommonDAO;
	}

	/**
	 * 用户登录
	 */
	@Override
	public User login(User user) {
		user = ibatisCommonDAO.executeForObject("getlogin", user, User.class);

		if (user != null) {

			return user;
		}
		return null;
	}

	/**
	 * 顶层菜单获取
	 */
	@Override
	public List<Menu> getTopMenu(User user) {

		List<Menu> menus = ibatisCommonDAO.executeForObjectList("getTopMenu", user);

		return menus;
	}

	/**
	 * 获取子级菜单,model菜单
	 */
	@Override
	public List<JsonMenu> getSubMenu(String topMenuId, User user) {

		// SQL入力对象
		MenuUserInfoInput input = new MenuUserInfoInput();
		input.setTopMenuID(topMenuId);
		input.setUserAccount(user.getUserAccount());
		input.setPassword(user.getPassword());
		List<Menu> allMenus = new ArrayList<Menu>();

		// 获取子级菜单
		List<Menu> subMenus = ibatisCommonDAO.executeForObjectList("getMenuByTopMenuID", input);

		if (subMenus != null && subMenus.size() > 0) {
			allMenus.addAll(subMenus);
		}

		// 获取model菜单
		List<Menu> modelMenu = ibatisCommonDAO.executeForObjectList("getModuleByTopMenuID", input);

		if (modelMenu != null && modelMenu.size() > 0) {
			allMenus.addAll(modelMenu);
		}

		// json格式转换
		List<JsonMenu> jsonMenus = new ArrayList<JsonMenu>();

		for (Menu menu : allMenus) {
			if (menu.getPid() == null) {
				JsonMenu jsonMenu = new JsonMenu();
				jsonMenu.setId(menu.getId());
				jsonMenu.setText(menu.getMenuName());
				List<JsonMenu> jsonSubMenus = new ArrayList<JsonMenu>();
				for (Menu subMenu : modelMenu) {
					if (StringUtils.equals(subMenu.getPid(), menu.getId())) {
						JsonMenu jsonSubMenu = new JsonMenu();
						jsonSubMenu.setId(subMenu.getId());
						jsonSubMenu.setText(subMenu.getMenuName());
						jsonSubMenu.setUrl(subMenu.getUrl());
						jsonSubMenus.add(jsonSubMenu);
					}
				}
				jsonMenu.setChildren(jsonSubMenus);
				jsonMenus.add(jsonMenu);
			}
		}
		return jsonMenus;
	}

	/**
	 * 查询菜单
	 * 
	 * @param user 用户
	 * @param pstrParentValue 菜单id
	 * @return
	 */
	@Override
	public List<Menu> getMenu(User user, String pstrParentValue) {
		// 定义对象
		ParameterMenu pm = null;
		if (null != user) {
			pm = new ParameterMenu();
			pm.setMenuid(pstrParentValue);
			pm.setUserid(user.getId());
			List<Menu> list = ibatisCommonDAO.executeForObjectList("selectusermenu", pm);
			return list;
		} else {
			return null;
		}

	}

	/**
	 * 查询菜单下面对应的功能
	 * 
	 * @param user 用户
	 * @param pstrParentValue 菜单id
	 * @return
	 */
	@Override
	public List<Module> getModule(User user, String pstrParentValue) {
		// 定义对象
		ParameterMenu pm = null;
		if (null != user) {
			pm = new ParameterMenu();
			pm.setMenuid(pstrParentValue);
			pm.setUserid(user.getId());
			List<Module> list = ibatisCommonDAO.executeForObjectList("selectusermodule", pm);
			// user.setRemark(PropertiesGetValue.getContextProperty("sectionChiefAudit.roleId"));
			// // 判断是不是科长
			// Integer count =
			// ibatisCommonDAO.executeForObject("checkIsKeZhang",user,
			// Integer.class);
			// if (count != 0) {
			// for (Module module : list) {
			// if (PropertiesGetValue.getContextProperty(
			// "employeeAudit.ModuleId").equals(module.getId())) {
			// module.setModulename("科长审核");
			// }
			// }
			// }
			return list;
		} else {
			return null;
		}
	}

	@Override
	public List<Module> getPermission(User user) {
		return ibatisCommonDAO.executeForObjectList("getPermission", user);
	}
}
