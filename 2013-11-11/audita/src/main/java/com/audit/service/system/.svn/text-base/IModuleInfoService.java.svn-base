package com.audit.service.system;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.ComboboxJson;
import com.audit.entity.system.ModuleResult;

public interface IModuleInfoService {

	/**
	 * 查询模块信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<ModuleResult> findModule(Integer page, Integer pagesize, String name, String order,
			String conditionModuleName);
	
	/**
	 * 查询模块信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<ModuleResult> findModuleBySubMenu(Integer page, Integer pagesize, String name, String order,
			String subMenuId);

	/**
	 * 获取所有子级菜单
	 * 
	 * @return
	 */
	public List<ComboboxJson> getModelMenus(String topMenu);
	
    /**
     * 获取所有模块菜单
     * 
     * @return
     */
    public List<ComboboxJson> getModules(String subMenu);

	/**
	 * 添加模块
	 * 
	 * @param moduleName
	 * @param state
	 * @param menuId
	 * @param url
	 * @param remark
	 * @return
	 */
	public Map<String, Object> add(String moduleName, String state,
			String menuId, String url, String remark)throws Exception;

	/**
	 * 获取模块信息
	 * 
	 * @param id
	 * @return
	 */
	public ModuleResult getModulById(String id);

	/**
	 * 更新模块信息
	 * 
	 * @param moduleName
	 * @param state
	 * @param remark
	 * @param subMenu
	 * @param url
	 * @return
	 */
	public Map<String, Object> update(String moduleName, String state,
			String remark, String subMenu, String url, String moduleId)throws Exception;

	/**
	 * 删除模块信息
	 * 
	 * @param moduleId
	 * @return
	 */
	public Map<String, Object> delete(String moduleId)throws Exception;
	
	/**
	 * 注销模块
	 * @param moduleId
	 * @param state
	 * @return
	 */
	public Map<String, Object> destroy(String moduleId, String state)throws Exception;
	
	/**
	 * 模块赋权限
	 * @param moduleId
	 * @param functionId
	 * @return
	 */
	public Map<String, Object> impower(String moduleId, String functionId)throws Exception;
}
