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
import com.audit.entity.ComboboxJson;
import com.audit.entity.Menu;
import com.audit.entity.ModuleFun;
import com.audit.entity.system.ModuleParam;
import com.audit.entity.system.ModuleResult;
import com.audit.service.system.IModuleInfoService;

public class ModuleInfoServiceImpl implements IModuleInfoService {
	/**
	 * sqlMap操作DAO
	 */
	private IbatisCommonDAO ibatisCommonDAO = null;

	/**
	 * @param ibatisCommonDAO
	 *            the ibatisCommonDAO to set
	 */
	public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO) {
		this.ibatisCommonDAO = ibatisCommonDAO;
	}

	/**
	 * 分页查询模块
	 */
	@Override
	public GridDataModel<ModuleResult> findModule(Integer page, Integer pagesize, String name, String order,
			String conditionModuleName) {
		ModuleParam moduleParam = new ModuleParam();

		moduleParam.setPageno(page - 1);

		moduleParam.setPagesize(pagesize);

		moduleParam.setFiled(name);

		moduleParam.setSort(order);

		moduleParam.setModuleName(conditionModuleName);

		Integer count = ibatisCommonDAO.executeForObject("getModuleCount", moduleParam, Integer.class);

		List<ModuleResult> list = ibatisCommonDAO.executeForObjectList("getModuleByIndexCount", moduleParam);

		GridDataModel<ModuleResult> gm = new GridDataModel<ModuleResult>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	/**
	 * 查询所有子菜单
	 */
	@Override
	public List<ComboboxJson> getModelMenus(String topMenuId) {
		List<Menu> meuns = ibatisCommonDAO.executeForObjectList("getModuleMenus", topMenuId);

		List<ComboboxJson> ComboboxJsons = new ArrayList<ComboboxJson>();
		if (meuns != null && meuns.size() > 0) {
			for (Menu menu : meuns) {
				ComboboxJson comboboxJson = new ComboboxJson();
				comboboxJson.setId(menu.getId());
				comboboxJson.setText(menu.getMenuName());
				if (ComboboxJsons.size() == 0) {
					comboboxJson.setSelected(true);
				}
				ComboboxJsons.add(comboboxJson);
			}
		}
		return ComboboxJsons;
	}

	@Override
	public List<ComboboxJson> getModules(String subMenu) {
		List<ComboboxJson> ComboboxJsons = ibatisCommonDAO.executeForObjectList("getModuleBySubMenuId", subMenu);
		if (ComboboxJsons.size() != 0) {
			ComboboxJsons.get(0).setSelected(true);
		}
		return ComboboxJsons;
	}

	@Override
	public Map<String, Object> add(String moduleName, String state, String menuId, String url, String remark)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ModuleParam moduleParam = new ModuleParam();
		Integer id = ibatisCommonDAO.executeForObject("moduleMaxID", null, Integer.class);
		if (id != null) {
			moduleParam.setModuleId(AuditStringUtils.getID(CommonConstant.STR_MENUPRIMARYKEY, id, 3));
		}
		moduleParam.setMenuId(menuId);
		moduleParam.setModuleName(moduleName);
		moduleParam.setUrl(url);
		moduleParam.setRemark(remark);
		moduleParam.setState(state);
		ibatisCommonDAO.executeInsert("addModule", moduleParam);
		map.put("menuId", moduleParam.getModuleId());
		map.put("msg", PropertiesGetValue.getContextProperty("module.add.success"));
		return map;
	}

	@Override
	public ModuleResult getModulById(String id) {
		return ibatisCommonDAO.executeForObject("getModuleInfoById", id, ModuleResult.class);
	}

	@Override
	public Map<String, Object> update(String moduleName, String state, String remark, String subMenu, String url,
			String moduleId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ModuleParam input = new ModuleParam();
		input.setMenuId(subMenu);
		input.setModuleName(moduleName);
		input.setModuleId(moduleId);
		input.setRemark(remark);
		input.setUrl(url);
		input.setState(state);
		Integer count = 0;
		count = ibatisCommonDAO.executeUpdate("updateModule", input);
		if (count != 0) {
			map.put("menuId", input.getModuleId());
			map.put("msg", PropertiesGetValue.getContextProperty("module.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("module.update.fail"));
		}
		return map;
	}

	@Override
	public Map<String, Object> delete(String moduleId) throws Exception {

		// 删除方法模块关联信息
		ibatisCommonDAO.executeDelete("deleteModulefunByModuleid", moduleId);

		Map<String, Object> map = new HashMap<String, Object>();

		// 删除该模块
		ibatisCommonDAO.executeDelete("deleteModule", moduleId);
		map.put("menuId", moduleId);
		map.put("msg", PropertiesGetValue.getContextProperty("module.delete.success"));
		return map;
	}

	@Override
	public Map<String, Object> destroy(String moduleId, String state) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ModuleParam input = new ModuleParam();
		input.setModuleId(moduleId);
		input.setState(state);
		Integer count = 0;
		count = ibatisCommonDAO.executeUpdate("destroyModule", input);
		if (count != 0) {
			map.put("menuId", input.getModuleId());
			map.put("msg", PropertiesGetValue.getContextProperty("module.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("module.update.fail"));
		}
		return map;
	}

	@Override
	public Map<String, Object> impower(String moduleId, String functionId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		ModuleFun modulFunParam = new ModuleFun();
		modulFunParam.setFunctionId(functionId);
		modulFunParam.setModuleId(moduleId);
		Integer count = ibatisCommonDAO.executeForObject("checkModuleImpower", modulFunParam, Integer.class);
		// 存在该条权限的情况
		if (count != 0) {
			// 删除该条权限
			ibatisCommonDAO.executeDelete("delectModuleFunctionImpower", modulFunParam);
		} else {

			Integer id = ibatisCommonDAO.executeForObject("getMaxModuleFunctionId", null, Integer.class);

			// 获取ID
			modulFunParam.setId(AuditStringUtils.getID(CommonConstant.STR_MODULEFUNCTIONRIMARYKEY, id, 3));
			ibatisCommonDAO.executeInsert("addModuleFunctionImpower", modulFunParam);
		}

		map.put("msg", PropertiesGetValue.getContextProperty("impower.update.success"));
		return map;
	}

	@Override
	public GridDataModel<ModuleResult> findModuleBySubMenu(Integer page, Integer pagesize, String name, String order,
			String subMenuId) {
		// TODO Auto-generated method stub
		return null;
	}
}
