package com.audit.service.staff.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Dictionary;
import com.audit.entity.staff.Link;
import com.audit.entity.staff.ProjectOwner;
import com.audit.service.staff.IProjectOwnerService;

public class ProjectOwnerServiceImpl implements IProjectOwnerService {

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
	 * 通过ID获取项目业主信息
	 */
	@Override
	public ProjectOwner getProjectOwner(String id) {

		return ibatisCommonDAO.executeForObject("getProjectOwnerById", id, ProjectOwner.class);
	}

	@Override
	public GridDataModel<ProjectOwner> find(Integer page, Integer pagesize, String name, String order,
			String projectOwnerName, String state) {

		ProjectOwner projectOwner = new ProjectOwner();
		projectOwner.setFiled(name);
		projectOwner.setOwnerName(projectOwnerName);
		projectOwner.setPageno(page - 1);
		projectOwner.setPagesize(pagesize);
		projectOwner.setSort(order);
		if (AuditStringUtils.isNotEmpty(state)) {
			projectOwner.setState(Integer.valueOf(state));
		}

		// 获取总数
		Integer count = ibatisCommonDAO.executeForObject("getProjectOwnerCount", projectOwner, Integer.class);

		// 获取项目业主信息
		List<ProjectOwner> list = ibatisCommonDAO.executeForObjectList("getProjectOwnerByIndexCount", projectOwner);

		GridDataModel<ProjectOwner> projectOwners = new GridDataModel<ProjectOwner>();

		projectOwners.setRows(list);

		projectOwners.setTotal(count);

		return projectOwners;
	}

	@Override
	public Map<String, Object> deleteProjectOwner(String ownerId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 更新该项目的状态为删除
		ProjectOwner projectOwner = new ProjectOwner();

		projectOwner.setState(Integer.parseInt(CommonConstant.PROJECTOWNER_STATE_DELETE));

		projectOwner.setId(ownerId);

		Integer count = ibatisCommonDAO.executeUpdate("updateProjectOwnerState", projectOwner);

		if (count == 0) {
			map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.update.fail"));
			return map;
		}

		// 删除该项目的联系人
		ibatisCommonDAO.executeDelete("deltelink", ownerId);

		map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.update.success"));
		map.put("isSuccess", "true");
		return map;
	}

	@Override
	public Map<String, Object> destroyProjectOwner(String ownerId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 更新该项目的状态为删除
		Integer count = ibatisCommonDAO.executeDelete("deleteProjectOwner", ownerId);

		if (count == 0) {
			map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.destroy.fail"));
			return map;
		}

		// 删除该项目的联系人
		ibatisCommonDAO.executeDelete("deltelink", ownerId);

		map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.destroy.success"));
		map.put("isSuccess", "true");
		return map;
	}

	@Override
	public List<Dictionary> getUnitType() {
		return ibatisCommonDAO.executeForObjectList("getUnitTypeById",
				PropertiesGetValue.getContextProperty("dictionary.type.unitType"));
	}

	@Override
	public Map<String, Object> addProjectOwner(String ownerName, String ownerReferred, String unitType,
			String isMainUnit, String state, String remark, String[] linknames, String[] linktellphones)
			throws Exception {

		// 获取项目业主信息ID
		Integer id = ibatisCommonDAO.executeForObject("getProjectOwnerId", null, Integer.class);

		ProjectOwner projectOwner = new ProjectOwner();

		projectOwner.setId(AuditStringUtils.getID(CommonConstant.STR_PROJECTOWNERRIMARYKEY, id, 3));

		projectOwner.setOwnerName(ownerName);

		projectOwner.setOwnerReferred(ownerReferred);

		projectOwner.setIsMainUnit(Integer.parseInt(isMainUnit));

		projectOwner.setState(Integer.parseInt(state));

		projectOwner.setUnitType(unitType);

		projectOwner.setRemark(remark);

		// 执行插入语句
		ibatisCommonDAO.executeInsert("addProjectOwner", projectOwner);

		// 添加联系人
		for (int i = 0; i < linknames.length; i++) {
			Link link = new Link();
			link.setReferid(projectOwner.getId());
			link.setLinkname(linknames[i]);
			link.setLinktellphone(linktellphones[i]);
			if (AuditStringUtils.isNotEmpty(link.getLinkname()) || AuditStringUtils.isNotEmpty(link.getLinktellphone())) {
				// 执行插入语句
				ibatisCommonDAO.executeInsert("addLinkByProjectOwnerId", link);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.add.success"));
		map.put("isSuccess", "true");
		return map;
	}

	@Override
	public List<Link> getLinks(String ownerId) {
		List<Link> links = ibatisCommonDAO.executeForObjectList("getLinkList", ownerId);
		List<Link> linkFormats = new ArrayList<Link>();
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			link.setNumber(AuditStringUtils.converyNumToDaXie(AuditStringUtils.toString(i + 1)));
			linkFormats.add(link);
		}
		return linkFormats;
	}

	@Override
	public Map<String, Object> updateProjectOwner(String ownerId, String ownerName, String ownerReferred,
			String unitType, String isMainUnit, String state, String remark, String[] linknames, String[] linktellphones)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		ProjectOwner projectOwner = new ProjectOwner();

		projectOwner.setId(ownerId);

		projectOwner.setOwnerName(ownerName);

		projectOwner.setOwnerReferred(ownerReferred);

		projectOwner.setIsMainUnit(Integer.parseInt(isMainUnit));

		projectOwner.setState(Integer.parseInt(state));

		projectOwner.setUnitType(unitType);

		projectOwner.setRemark(remark);

		// 执行插入语句
		Integer count = ibatisCommonDAO.executeUpdate("updateProjectOwner", projectOwner);

		if (count == 0) {
			map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.update.fail"));
			return map;
		}

		// 删除旧联系人
		ibatisCommonDAO.executeDelete("deltelink", ownerId);

		// 更新联系人
		for (int i = 0; i < linknames.length; i++) {
			Link link = new Link();
			link.setReferid(projectOwner.getId());
			link.setLinkname(linknames[i]);
			link.setLinktellphone(linktellphones[i]);
			if (AuditStringUtils.isNotEmpty(link.getLinkname()) || AuditStringUtils.isNotEmpty(link.getLinktellphone())) {
				// 执行插入语句
				ibatisCommonDAO.executeInsert("addLinkByProjectOwnerId", link);
			}
		}
		map.put("msg", PropertiesGetValue.getContextProperty("projectOwner.update.success"));
		map.put("isSuccess", "true");
		return map;
	}
}
