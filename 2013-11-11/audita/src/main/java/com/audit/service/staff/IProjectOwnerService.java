package com.audit.service.staff;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Dictionary;
import com.audit.entity.staff.Link;
import com.audit.entity.staff.ProjectOwner;

public interface IProjectOwnerService {

	/**
	 * 通过ID 获取项目业主信息
	 * 
	 * @param id
	 * @return
	 */
	public ProjectOwner getProjectOwner(String id);

	/**
	 * 分页查询顶层项目业主信息
	 * 
	 * @param page
	 *            当前页数
	 * @param pagesize
	 *            每页条数
	 * @param name
	 *            排序字段
	 * @param order
	 *            排序方式
	 * @return
	 */
	public GridDataModel<ProjectOwner> find(Integer page, Integer pagesize, String name, String order,
			String projectOwnerName, String state);

	/**
	 * 添加项目业主信息
	 * @param ownerName
	 * @param ownerReferred
	 * @param unitType
	 * @param isMainUnit
	 * @param state
	 * @param remark
	 * @return
	 */
	public Map<String, Object> addProjectOwner(String ownerName, String ownerReferred, String unitType,
			String isMainUnit, String state, String remark, String[] linknames, String [] linktellphones) throws Exception;
	
	/**
	 * 更新项目业主信息
	 * @param ownerName
	 * @param ownerReferred
	 * @param unitType
	 * @param isMainUnit
	 * @param state
	 * @param remark
	 * @return
	 */
	public Map<String, Object> updateProjectOwner(String ownerId ,String ownerName, String ownerReferred, String unitType,
			String isMainUnit, String state, String remark, String[] linknames, String [] linktellphones) throws Exception;

	/**
	 * 删除项目业主信息
	 * @param ownerId
	 * @return
	 */
	public Map<String, Object> deleteProjectOwner(String ownerId)throws Exception;
	
	/**
	 * 注销项目业主信息
	 * @param ownerId
	 * @param state
	 * @return
	 */
	public Map<String, Object> destroyProjectOwner(String ownerId)throws Exception;
	
	/**
	 * 获取单位类型下拉内容
	 */
	public List<Dictionary> getUnitType();
	
	/**
	 * 获取项目联系人
	 */
	public List<Link> getLinks(String ownerId);
}
