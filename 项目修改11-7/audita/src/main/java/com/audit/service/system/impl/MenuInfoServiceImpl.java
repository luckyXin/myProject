package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Menu;
import com.audit.service.system.IMenuInfoService;

public class MenuInfoServiceImpl implements IMenuInfoService {

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

	@Override
	public GridDataModel<Menu> find(Integer page, Integer pagesize, String name, String order) {
		Menu menu = new Menu();

		menu.setPageno(page - 1);

		menu.setPagesize(pagesize);

		menu.setFiled(name);

		menu.setSort(order);
		// 导航总记录查询
		Integer count = ibatisCommonDAO.executeForObject("getAllTopMenuCount", null, Integer.class);

		// 查询导航
		List<Menu> list = ibatisCommonDAO.executeForObjectList("getAllTopMenuByIndexCount", menu);

		GridDataModel<Menu> gm = new GridDataModel<Menu>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public Map<String, Object> add(String menuName, String state, String sort, String remark) throws Exception {
		// 获取ID
		Integer id = ibatisCommonDAO.executeForObject("daoHangMaxID", null, Integer.class);
		Map<String, Object> map = new HashMap<String, Object>();
		Menu menu = new Menu();
		menu.setId(AuditStringUtils.getID(CommonConstant.STR_MENUTOPPRIMARYKEY, id, 2));
		menu.setMenuName(menuName);
		menu.setState(state);
		menu.setSort(sort);
		menu.setRemark(remark);
		menu.setPid(CommonConstant.MENU_TOPPID);

		Integer count = ibatisCommonDAO.executeInsert("addMenu", menu);
		if (count != 0) {
			map.put("menuId", menu.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}
		return map;
	}

	@Override
	public boolean update(Menu menu) throws Exception {

		Integer count = ibatisCommonDAO.executeUpdate("updateMenu", menu);

		if (count != null && count.intValue() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) throws Exception {
		Integer count = 0;
		count = ibatisCommonDAO.executeDelete("deleteMenu", id);
		if (count != null && count.intValue() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Menu getMenu(String id) {
		Menu menu = ibatisCommonDAO.executeForObject("getMenuById", id, Menu.class);

		return menu;
	}

	@Override
	public GridDataModel<Menu> findSubMenu(Integer page, Integer pagesize, String name, String order) {
		Menu menu = new Menu();

		menu.setPageno(page - 1);

		menu.setPagesize(pagesize);

		menu.setFiled(name);

		menu.setSort(order);
		// 导航总记录查询
		Integer count = ibatisCommonDAO.executeForObject("getAllSubMenuCount", null, Integer.class);

		// 查询导航
		List<Menu> list = ibatisCommonDAO.executeForObjectList("getAllSubMenuByIndexCount", menu);

		GridDataModel<Menu> gm = new GridDataModel<Menu>();

		gm.setTotal(count);

		gm.setRows(list);

		return gm;
	}

	@Override
	public Map<String, Object> add(String menuName, String state, String sort, String remark, String pid)
			throws Exception {

		// 获取ID
		Integer id = ibatisCommonDAO.executeForObject("subMenuMaxID", null, Integer.class);
		Map<String, Object> map = new HashMap<String, Object>();
		Menu menu = new Menu();
		menu.setId(AuditStringUtils.getID(CommonConstant.STR_MENUPRIMARYKEY, id, 2));
		menu.setMenuName(menuName);
		menu.setState(state);
		menu.setSort(sort);
		menu.setRemark(remark);
		menu.setPid(pid);

		Integer count = ibatisCommonDAO.executeInsert("addMenu", menu);

		if (count != 0) {
			map.put("menuId", menu.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}
		return map;
	}

	@Override
	public List<Menu> getTopMenu() {
		return ibatisCommonDAO.executeForObjectList("getAllTopMenu", null);
	}

	@Override
	public Map<String, Object> update(String menuName, String state, String sort, String remark, String pid,
			String menuId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Menu menu = new Menu();
		menu.setMenuName(menuName);
		menu.setState(state);
		menu.setSort(sort);
		menu.setRemark(remark);
		menu.setPid(pid);
		menu.setId(menuId);
		
		Integer count = ibatisCommonDAO.executeUpdate("updateMenu", menu);
	
		if (count != 0) {
			map.put("menuId", menu.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.fail"));
		}
		return map;
	}

	@Override
	public Map<String, Object> checkIsExist(String name) {
		Integer count = ibatisCommonDAO.executeForObject("checkNameIsExist", name, Integer.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if (count != 0) {
			map.put("isExist", count);
		}
		return map;
	}

	@Override
	public Map<String, Object> destroy(Menu menu) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		Integer count = ibatisCommonDAO.executeUpdate("destroyMenu", menu);

		if (count != 0) {
			map.put("menuId", menu.getId());
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.fail"));
		}
		return map;
	}
}
