package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Function;
import com.audit.service.system.IFunctionInfoService;

public class FunctionServiceImpl implements IFunctionInfoService {

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
	 * 分页查询顶层菜单
	 */
	@Override
	public GridDataModel<Function> find(Integer page, Integer pagesize, String name, String order) {

		Function function = new Function();
		function.setPageno(page - 1);

		function.setPagesize(pagesize);

		function.setFiled(name);

		function.setSort(order);

		// 导航总记录查询
		Integer count = ibatisCommonDAO.executeForObject("getFunctionCount", null, Integer.class);

		// 查询导航
		List<Function> list = ibatisCommonDAO.executeForObjectList("getFunctionByIndexCount", function);

		GridDataModel<Function> gm = new GridDataModel<Function>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	/**
	 * 添加方法
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> add(String name, String state, String method, String remark, String icon)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Function function = new Function();
		function.setName(name);
		function.setState(state);
		function.setMethod(method);
		function.setRemark(remark);
		function.setIcon(icon);
		// 生成ID
		Integer id = ibatisCommonDAO.executeForObject("functionMaxID", null, Integer.class);
		function.setId(AuditStringUtils.getID(CommonConstant.STR_FUNCTIONRIMARYKEY, id, 3));

		Integer count = ibatisCommonDAO.executeInsert("addFunction", function);

		if (count != 0) {
			map.put("isSuccess", function.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("function.add.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("function.add.fail"));
		}
		return map;
	}

	/**
	 * 更新方法
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> update(String name, String state, String method, String remark, String icon, String id)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Function function = new Function();
		function.setId(id);
		function.setIcon(icon);
		function.setName(name);
		function.setMethod(method);
		function.setState(state);
		function.setRemark(remark);
		Integer count = 0;
		count = ibatisCommonDAO.executeUpdate("updateFunction", function);
		if (count != 0) {
			map.put("isSuccess", function.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("function.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("function.update.fail"));
		}
		return map;
	}

	/**
	 * 删除方法
	 */
	@Override
	public Map<String, Object> delete(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ibatisCommonDAO.executeDelete("deleteFunction", id);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("function.delete.fail"));
			return map;
		}
		map.put("msg", PropertiesGetValue.getContextProperty("function.delete.success"));
		return map;
	}

	/**
	 * 注销方法
	 */
	@Override
	public Map<String, Object> destory(String id, String state) {
		Map<String, Object> map = new HashMap<String, Object>();
		Function function = new Function();
		function.setId(id);
		function.setState(state);
		Integer count = 0;
		try {
			count = ibatisCommonDAO.executeUpdate("destoryFunction", function);
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
			return map;
		}
		if (count != 0) {
			map.put("menuId", function.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("function.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("function.update.fail"));
		}
		return map;
	}

	@Override
	public Function getFunctionById(String id) {
		return ibatisCommonDAO.executeForObject("getFunctionById", id, Function.class);
	}

	@Override
	public GridDataModel<Function> find(Integer page, Integer pagesize, String name, String order, String moduleId) {

		Function function = new Function();

		function.setPageno(page - 1);

		function.setPagesize(pagesize);

		function.setFiled(name);

		function.setSort(order);

		// 导航总记录查询
		Integer count = ibatisCommonDAO.executeForObject("getFunctionCount", null, Integer.class);

		// 查询导航
		List<Function> list = ibatisCommonDAO.executeForObjectList("getFunctionByIndexCount", function);

		// 授权确认
		List<String> impowers = ibatisCommonDAO.executeForObjectList("checkFunctionImpower", moduleId);

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
}
