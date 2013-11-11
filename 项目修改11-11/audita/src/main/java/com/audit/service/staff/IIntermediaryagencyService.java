package com.audit.service.staff;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Dictionary;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.Link;


public interface IIntermediaryagencyService {

	/**
	 * 通过ID 获取中介机构信息
	 * 
	 * @param id
	 * @return
	 */
	public Intermediaryagency getIntermediaryagency(String id);

	/**
	 * 分页查询顶层中介机构信息
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
	public GridDataModel<Intermediaryagency> find(Integer page, Integer pagesize, String name, String order,
			String projectOwnerName, String bidyear);

	/**
	 * 添加中介机构信息
	 * @param ownerName
	 * @param ownerReferred
	 * @param unitType
	 * @param isMainUnit
	 * @param state
	 * @param remark
	 * @return
	 */
	public Map<String, Object> addIntermediaryagency(Intermediaryagency inter,String[] linknames, String [] linktellphones) throws Exception;
	
	/**
	 * 更新中介机构信息
	 * @param ownerName
	 * @param ownerReferred
	 * @param unitType
	 * @param isMainUnit
	 * @param state
	 * @param remark
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> updateIntermediaryagency(Intermediaryagency inter,String[] linknames, String [] linktellphones) throws Exception;

	/**
	 * 删除中介机构信息
	 * @param ownerId
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> deleteIntermediaryagency(String id) throws Exception;
	
	/**
	 * 注销中介机构信息
	 * @param ownerId
	 * @param state
	 * @return
	 */
	public Map<String, Object> destroyIntermediaryagency(String id) throws Exception;
	
	/**
	 * 获取下拉内容
	 */
	public List<Dictionary> getDictionary(String id);
	
	/**
	 * 获取项目联系人
	 */
	public List<Link> getLinks(String id);
}
