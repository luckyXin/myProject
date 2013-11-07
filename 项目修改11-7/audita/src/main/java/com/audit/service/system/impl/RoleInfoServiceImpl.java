package com.audit.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Function;
import com.audit.entity.Menu;
import com.audit.entity.ModuleFun;
import com.audit.entity.Role;
import com.audit.entity.system.CheckFunctionImpowerByRoleSqlParam;
import com.audit.entity.system.ModuleFunctionRoleParam;
import com.audit.entity.system.ModuleParam;
import com.audit.entity.system.ModuleResult;
import com.audit.entity.system.RoleModuleFun;
import com.audit.entity.system.TreeMenuResult;
import com.audit.service.system.IRoleInfoService;

public class RoleInfoServiceImpl implements IRoleInfoService {

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

	@Override
	public GridDataModel<Role> find(Integer page, Integer pagesize, String name, String order) {

		// 查询角色的总数
		Integer count = ibatisCommonDAO.executeForObject("getRoleCount", null, Integer.class);

		Role role = new Role();

		role.setPageno(page - 1);

		role.setPagesize(pagesize);

		role.setFiled(name);

		role.setSort(order);

		List<Role> list = ibatisCommonDAO.executeForObjectList("getAllTopRoleByIndexCount", role);

		GridDataModel<Role> gm = new GridDataModel<Role>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public Map<String, Object> add(String roleName, String state, String remark) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Role role = new Role();
		role.setRolename(roleName);
		role.setState(state);
		role.setRemark(remark);
		// 获取ID
		Integer id = ibatisCommonDAO.executeForObject("getMaxRoleId", null, Integer.class);
		role.setId(AuditStringUtils.getID(CommonConstant.STR_ROLERIMARYKEY, id, 3));

		ibatisCommonDAO.executeInsert("addRole", role);
		map.put("isSuccess", role.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("role.add.success"));
		return map;
	}

	@Override
	public Role getRoleInfoById(String id) {

		return ibatisCommonDAO.executeForObject("getRoleById", id, Role.class);
	}

	@Override
	public GridDataModel<Menu> findTopMenu(Integer page, Integer pagesize, String name, String order, String roleId) {

		CheckFunctionImpowerByRoleSqlParam param = new CheckFunctionImpowerByRoleSqlParam();

		param.setRoleId(roleId);

		Menu menu = new Menu();

		menu.setPageno(page - 1);

		menu.setPagesize(pagesize);

		menu.setFiled(name);

		menu.setSort(order);
		// 导航总记录查询
		Integer count = ibatisCommonDAO.executeForObject("getAllTopMenuCount", null, Integer.class);

		// 查询导航
		List<Menu> list = ibatisCommonDAO.executeForObjectList("getAllTopMenuByIndexCount", menu);

		for (Menu topMenu : list) {

			topMenu.setImpower("1");

			// 获取模块信息
			List<String> modules = ibatisCommonDAO.executeForObjectList("getRoleModuleByTopMenuId", topMenu.getId());

			if (modules.size() == 0) {
				// 该顶层菜单没有权限
				topMenu.setImpower("0");
				continue;
			}

			// 获取模块所拥有的方法
			for (String moduleId : modules) {
				param.setModuleId(moduleId);
				// 获取function 的总数量
				Integer function = ibatisCommonDAO.executeForObject("getFunctionCountByModuleid", moduleId,
						Integer.class);
				Integer functions = ibatisCommonDAO
						.executeForObject("checkFunctionImpowerByRole", param, Integer.class);
				if (functions.intValue() != function.intValue()) {

					// 该顶层菜单没有完全授权
					topMenu.setImpower("0");
					break;
				}

			}

		}

		GridDataModel<Menu> gm = new GridDataModel<Menu>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public GridDataModel<Menu> findSubMenu(Integer page, Integer pagesize, String name, String order, String topMenuId,
			String roleId) {

		CheckFunctionImpowerByRoleSqlParam param = new CheckFunctionImpowerByRoleSqlParam();

		param.setRoleId(roleId);

		Menu menu = new Menu();

		menu.setPageno(page - 1);

		menu.setPagesize(pagesize);

		menu.setFiled(name);

		menu.setSort(order);

		menu.setPid(topMenuId);

		// 子级菜单总记录查询
		Integer count = ibatisCommonDAO.executeForObject("getAllSubMenuCountByTopMenuId", topMenuId, Integer.class);

		// 查询导航
		List<Menu> list = ibatisCommonDAO.executeForObjectList("getRoleAllSubMenuByIndexCount", menu);

		for (Menu subMenu : list) {

			// 获取模块信息
			List<String> modules = ibatisCommonDAO.executeForObjectList("getRoleModuleBySubMenuId", subMenu.getId());

			subMenu.setImpower("1");

			if (modules.size() == 0) {
				// 该顶层菜单没有权限
				subMenu.setImpower("0");
				continue;
			}

			// 获取模块所拥有的方法
			for (String moduleId : modules) {
				// 获取function 的总数量
				Integer function = ibatisCommonDAO.executeForObject("getFunctionCountByModuleid", moduleId,
						Integer.class);
				param.setModuleId(moduleId);
				Integer functions = ibatisCommonDAO
						.executeForObject("checkFunctionImpowerByRole", param, Integer.class);
				if (functions.intValue() != function.intValue()) {

					// 该顶层菜单没有完全授权
					subMenu.setImpower("0");
					break;
				}
			}
		}

		GridDataModel<Menu> gm = new GridDataModel<Menu>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public GridDataModel<ModuleResult> findModule(Integer page, Integer pagesize, String name, String order,
			String subMenuId, String roleId) {

		CheckFunctionImpowerByRoleSqlParam param = new CheckFunctionImpowerByRoleSqlParam();

		param.setRoleId(roleId);

		ModuleParam module = new ModuleParam();

		module.setPageno(page - 1);

		module.setPagesize(pagesize);

		module.setFiled(name);

		module.setSort(order);

		module.setMenuId(subMenuId);

		// 模块总记录查询 getModuleByIndexCount
		Integer count = ibatisCommonDAO.executeForObject("getModuleCountBySubMenu", module, Integer.class);

		// 查询导航
		List<ModuleResult> list = ibatisCommonDAO.executeForObjectList("getModuleByIndexCountBySubMenuId", module);

		for (ModuleResult moduleResult : list) {

			// 获取function 的总数量
			Integer function = ibatisCommonDAO.executeForObject("getFunctionCountByModuleid",
					moduleResult.getModuleId(), Integer.class);

			if (function.intValue() == 0) {
				// 该模块没有完全授权
				moduleResult.setImpower("0");
				continue;
			}

			param.setModuleId(moduleResult.getModuleId());

			Integer functions = ibatisCommonDAO.executeForObject("checkFunctionImpowerByRole", param, Integer.class);
			if (function.intValue() != functions.intValue()) {

				// 该模块没有完全授权
				moduleResult.setImpower("0");

			} else {

				// 该模块完全授权
				moduleResult.setImpower("1");
			}
		}

		GridDataModel<ModuleResult> gm = new GridDataModel<ModuleResult>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public GridDataModel<Function> findFunction(Integer page, Integer pagesize, String name, String order,
			String moduleMenuId, String roleId) {

		CheckFunctionImpowerByRoleSqlParam param = new CheckFunctionImpowerByRoleSqlParam();

		param.setRoleId(roleId);

		param.setModuleId(moduleMenuId);

		Function function = new Function();

		function.setPageno(page - 1);

		function.setPagesize(pagesize);

		function.setFiled(name);

		function.setSort(order);

		function.setModuleid(moduleMenuId);

		// 获取function 的总数量
		Integer count = ibatisCommonDAO.executeForObject("getFunctionCountByModuleid", moduleMenuId, Integer.class);

		// 查询导航
		List<Function> list = ibatisCommonDAO.executeForObjectList("getFunctionByIndexCountByMenuId", function);

		// 授权确认
		List<String> impowers = ibatisCommonDAO.executeForObjectList("checkFunctionImpowerByRoleInfo", param);

		for (Function f : list) {
			if (impowers == null || impowers.size() == 0) {
				f.setImpower("0");
			} else {
				if (impowers.contains(f.getId())) {
					f.setImpower("1");
				} else {
					f.setImpower("0");
				}
			}
		}

		GridDataModel<Function> gm = new GridDataModel<Function>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public Map<String, Object> updateImpowerByTopMenuId(String topMenuId, String roleId, String impower)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取顶层菜单下面所有的模块菜单
		List<String> modules = ibatisCommonDAO.executeForObjectList("getRoleModuleByTopMenuId", topMenuId);

		if (modules.size() == 0) {
			map.put("msg", PropertiesGetValue.getContextProperty("impower.update.fail") + "不存在子菜单");
			return map;
		}
		for (String moduleId : modules) {
			if ("0".equals(impower)) {

				ModuleFunctionRoleParam moduleFunctionRoleParam = new ModuleFunctionRoleParam();
				moduleFunctionRoleParam.setModuleId(moduleId);
				moduleFunctionRoleParam.setRoleId(roleId);
				// 获取模块与方法之间关系
				List<String> moduleFunctions = ibatisCommonDAO.executeForObjectList("getNoexistMFS",
						moduleFunctionRoleParam);
				for (String moduleFunctionId : moduleFunctions) {
					RoleModuleFun roleModuleFun = new RoleModuleFun();
					roleModuleFun.setRoleId(roleId);
					roleModuleFun.setModulefunid(moduleFunctionId);
					ibatisCommonDAO.executeInsert("addRoleModulefun", roleModuleFun);
				}
			} else if ("1".equals(impower)) {

				// 获取模块与方法之间关系
				List<String> moduleFunctions = ibatisCommonDAO.executeForObjectList("getExistModulefunInfo", moduleId);
				for (String moduleFunctionId : moduleFunctions) {
					RoleModuleFun roleModuleFun = new RoleModuleFun();
					roleModuleFun.setRoleId(roleId);
					roleModuleFun.setModulefunid(moduleFunctionId);
					ibatisCommonDAO.executeDelete("deleteRoleModulefun", roleModuleFun);
				}
			}
		}
		map.put("msg", PropertiesGetValue.getContextProperty("impower.update.success"));
		return map;
	}

	@Override
	public Map<String, Object> updateImpowerBySubMenuId(String subMenuId, String roleId, String impower)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		// 获取模块信息
		List<String> modules = ibatisCommonDAO.executeForObjectList("getRoleModuleBySubMenuId", subMenuId);

		if (modules.size() == 0) {
			map.put("msg", PropertiesGetValue.getContextProperty("impower.update.fail") + "不存在模块菜单");
			return map;
		}

		for (String moduleId : modules) {
			if ("0".equals(impower)) {
				ModuleFunctionRoleParam moduleFunctionRoleParam = new ModuleFunctionRoleParam();
				moduleFunctionRoleParam.setModuleId(moduleId);
				moduleFunctionRoleParam.setRoleId(roleId);
				// 获取模块与方法之间关系
				List<String> moduleFunctions = ibatisCommonDAO.executeForObjectList("getNoexistMFS",
						moduleFunctionRoleParam);
				for (String moduleFunctionId : moduleFunctions) {
					RoleModuleFun roleModuleFun = new RoleModuleFun();
					roleModuleFun.setRoleId(roleId);
					roleModuleFun.setModulefunid(moduleFunctionId);
					ibatisCommonDAO.executeInsert("addRoleModulefun", roleModuleFun);
				}
			} else if ("1".equals(impower)) {

				// 获取模块与方法之间关系
				List<String> moduleFunctions = ibatisCommonDAO.executeForObjectList("getExistModulefunInfo", moduleId);
				for (String moduleFunctionId : moduleFunctions) {
					RoleModuleFun roleModuleFun = new RoleModuleFun();
					roleModuleFun.setRoleId(roleId);
					roleModuleFun.setModulefunid(moduleFunctionId);
					ibatisCommonDAO.executeDelete("deleteRoleModulefun", roleModuleFun);
				}
			}
		}
		map.put("msg", PropertiesGetValue.getContextProperty("impower.update.success"));
		return map;
	}

	@Override
	public Map<String, Object> updateImpowerByModuleId(String moduleId, String roleId, String impower) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(impower)) {
			ModuleFunctionRoleParam moduleFunctionRoleParam = new ModuleFunctionRoleParam();
			moduleFunctionRoleParam.setModuleId(moduleId);
			moduleFunctionRoleParam.setRoleId(roleId);
			// 获取模块与方法之间关系
			List<String> moduleFunctions = ibatisCommonDAO.executeForObjectList("getNoexistMFS",
					moduleFunctionRoleParam);
			for (String moduleFunctionId : moduleFunctions) {
				RoleModuleFun roleModuleFun = new RoleModuleFun();
				roleModuleFun.setRoleId(roleId);
				roleModuleFun.setModulefunid(moduleFunctionId);
				ibatisCommonDAO.executeInsert("addRoleModulefun", roleModuleFun);
			}
		} else if ("1".equals(impower)) {

			// 获取模块与方法之间关系
			List<String> moduleFunctions = ibatisCommonDAO.executeForObjectList("getExistModulefunInfo", moduleId);
			for (String moduleFunctionId : moduleFunctions) {
				RoleModuleFun roleModuleFun = new RoleModuleFun();
				roleModuleFun.setRoleId(roleId);
				roleModuleFun.setModulefunid(moduleFunctionId);
				ibatisCommonDAO.executeDelete("deleteRoleModulefun", roleModuleFun);
			}
		}
		map.put("msg", PropertiesGetValue.getContextProperty("impower.update.success"));
		return map;
	}

	@Override
	public Map<String, Object> updateImpowerByFunctionId(String functionId, String roleId, String impower,
			String moduleMenuId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ModuleFun mf = new ModuleFun();
		mf.setModuleId(moduleMenuId);
		mf.setFunctionId(functionId);
		String moduleFunction = ibatisCommonDAO.executeForObject("checkFunctionExist", mf, String.class);
		RoleModuleFun roleModuleFun = new RoleModuleFun();
		roleModuleFun.setRoleId(roleId);
		roleModuleFun.setModulefunid(moduleFunction);
		if ("0".equals(impower)) {
			ibatisCommonDAO.executeInsert("addRoleModulefun", roleModuleFun);
		} else if ("1".equals(impower)) {
			ibatisCommonDAO.executeDelete("deleteRoleModulefun", roleModuleFun);
		}
		map.put("msg", PropertiesGetValue.getContextProperty("impower.update.success"));
		return map;
	}

	@Override
	public Map<String, Object> updateRoleInfo(String roleId, String roleName, String state, String remark)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Role role = new Role();
		role.setId(roleId);
		role.setRolename(roleName);
		role.setState(state);
		role.setRemark(remark);
		Integer count = 0;
		count = ibatisCommonDAO.executeUpdate("updateRoleInfo", role);
		if (count != 0) {
			map.put("isSuccess", role.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("role.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("role.update.fail"));
		}
		return map;
	}

	@Override
	public Map<String, Object> delete(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 删除角色关联的菜单
		ibatisCommonDAO.executeDelete("deleteRMF", id);

		// 删除角色关联的菜单
		ibatisCommonDAO.executeDelete("deleteRU", id);

		// 删除用户关联
		ibatisCommonDAO.executeDelete("deleteRole", id);

		map.put("msg", PropertiesGetValue.getContextProperty("role.delete.success"));
		return map;
	}

	@Override
	public Map<String, Object> destroy(String id, String state) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Role role = new Role();
		role.setState(state);
		role.setId(id);
		Integer count = 0;
		count = ibatisCommonDAO.executeUpdate("destroyRole", role);
		if (count != 0) {
			map.put("isSuccess", role.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("role.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("role.update.fail"));
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-13
	 * 
	 * @see com.audit.service.system.IRoleInfoService#getTreeMenu(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<TreeMenuResult> getTreeMenu(Integer page, Integer pagesize, String name, String order,
			String roleId) {

		Role role = new Role();
		role.setPageno(page);
		role.setPagesize(pagesize);
		role.setFiled(name);
		role.setSort(order);
		role.setId(roleId);
		// 获取function 的总数量
		Integer count = ibatisCommonDAO.executeForObject("getAreadyHaveSubMenuImpowerCount", role, Integer.class);

		// 查询导航
		List<TreeMenuResult> list = ibatisCommonDAO.executeForObjectList("getAreadyHaveSubMenuImpower", role);

		List<TreeMenuResult> listAll = new ArrayList<TreeMenuResult>();
		listAll.addAll(list);
		// 获取模块菜单
		for (TreeMenuResult subMenu : list) {
			role.setMenuId(subMenu.getMenuId());
			List<TreeMenuResult> listModule = ibatisCommonDAO.executeForObjectList("getAreadyHaveModuleImpower", role);
			listAll.addAll(listModule);
			// 获取方法权限
			for (TreeMenuResult module : listModule) {
				role.setMenuId(module.getMenuId());
				List<TreeMenuResult> listFunction = ibatisCommonDAO.executeForObjectList(
						"getAreadyHaveFunctionImpower", role);
				listAll.addAll(listFunction);
			}
		}

		GridDataModel<TreeMenuResult> treeMenuResult = new GridDataModel<TreeMenuResult>();
		treeMenuResult.setRows(listAll);
		treeMenuResult.setTotal(count);
		return treeMenuResult;
	}
}
