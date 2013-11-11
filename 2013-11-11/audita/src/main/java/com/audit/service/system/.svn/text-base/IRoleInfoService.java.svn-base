package com.audit.service.system;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Function;
import com.audit.entity.Menu;
import com.audit.entity.Role;
import com.audit.entity.system.ModuleResult;
import com.audit.entity.system.TreeMenuResult;

public interface IRoleInfoService {
	/**
	 * 查询角色信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<Role> find(Integer page, Integer pagesize, String name, String order);

	/**
	 * 查询顶层菜单信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<Menu> findTopMenu(Integer page, Integer pagesize, String name, String order, String roleId);

	/**
	 * 查询顶层菜单信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<Menu> findSubMenu(Integer page, Integer pagesize, String name, String order, String topMenuId,
			String roleId);

	/**
	 * 查询模块菜单信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<ModuleResult> findModule(Integer page, Integer pagesize, String name, String order,
			String subMenuId, String roleId);

	/**
	 * 查询模块菜单信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param name
	 * @param order
	 * @return
	 */
	public GridDataModel<Function> findFunction(Integer page, Integer pagesize, String name, String order,
			String subMenuId, String roleId);

	/**
	 * 添加角色
	 * 
	 * @param roleName
	 * @param state
	 * @param remark
	 * @return
	 */
	public Map<String, Object> add(String roleName, String state, String remark) throws Exception;

	/**
	 * 获取角色信息
	 * 
	 * @param id
	 * @return
	 */
	public Role getRoleInfoById(String id);

	/**
	 * 通过顶层菜单更新角色权限
	 * 
	 * @param topMenuId
	 * @param roleId
	 * @return
	 */
	public Map<String, Object> updateImpowerByTopMenuId(String topMenuId, String roleId, String impower)
			throws Exception;

	/**
	 * 通过子级菜单更新角色权限
	 * 
	 * @param topMenuId
	 * @param roleId
	 * @return
	 */
	public Map<String, Object> updateImpowerBySubMenuId(String subMenuId, String roleId, String impower)
			throws Exception;

	/**
	 * 通过子级菜单更新角色权限
	 * 
	 * @param topMenuId
	 * @param roleId
	 * @return
	 */
	public Map<String, Object> updateImpowerByModuleId(String subMenuId, String roleId, String impower)
			throws Exception;

	/**
	 * 通过子级菜单更新角色权限
	 * 
	 * @param topMenuId
	 * @param roleId
	 * @return
	 */
	public Map<String, Object> updateImpowerByFunctionId(String functionId, String roleId, String impower,
			String moduleMenuId) throws Exception;

	/**
	 * 更新角色信息
	 */
	public Map<String, Object> updateRoleInfo(String roleId, String roleName, String state, String remark)
			throws Exception;

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> delete(String id) throws Exception;

	/**
	 * 注销角色
	 */
	public Map<String, Object> destroy(String id, String state) throws Exception;

	/**
	 * 角色拥有权限树状图
	 */
	public GridDataModel<TreeMenuResult> getTreeMenu(Integer page, Integer pagesize, String name, String order,
			String roleId);
}
