package com.audit.service.staff.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Dictionary;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.Link;
import com.audit.service.staff.IIntermediaryagencyService;

public class IntermediaryagencyServiceImpl implements IIntermediaryagencyService {

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
	 * 通过ID获取项目业主信息
	 */
	@Override
	public Intermediaryagency getIntermediaryagency(String id) {

		return ibatisCommonDAO.executeForObject("getIntermediaryagencyById", id, Intermediaryagency.class);
	}

	@Override
	public GridDataModel<Intermediaryagency> find(Integer page, Integer pagesize, String name, String order,
			String intermediaryName,String bidyear) {

		Intermediaryagency inter = new Intermediaryagency();
		inter.setIntermediaryname(intermediaryName);
		inter.setFiled(name);
		inter.setPageno(page - 1);
		inter.setPagesize(pagesize);
		inter.setSort(order);
		inter.setBidyear(bidyear);
		
		// 获取总数
		Integer count = ibatisCommonDAO.executeForObject("getIntermediaryagencyCount", inter, Integer.class);

		// 获取中介机构信息
		List<Intermediaryagency> list = ibatisCommonDAO.executeForObjectList("getIntermediaryagencyByIndexCount", inter);

		GridDataModel<Intermediaryagency> intergdm = new GridDataModel<Intermediaryagency>();

		intergdm.setRows(list);

		intergdm.setTotal(count);

		return intergdm;
	}

	@Override
	public Map<String, Object> updateIntermediaryagency(Intermediaryagency inter,String[] linknames, String [] linktellphones) throws Exception{

		// 执行更新语句
		ibatisCommonDAO.executeUpdate("updateIntermediaryagency", inter);
		
		// 删除旧联系人
		ibatisCommonDAO.executeDelete("deltelink", inter.getId());

		// 更新联系人
		for (int i = 0; i < linknames.length; i++) {
			Link link = new Link();
			link.setReferid(inter.getId());
			link.setLinkname(linknames[i]);
			link.setLinktellphone(linktellphones[i]);
			if (AuditStringUtils.isNotEmpty(link.getLinkname()) || AuditStringUtils.isNotEmpty(link.getLinktellphone())) {
				// 执行插入语句
				ibatisCommonDAO.executeInsert("addLink", link);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", PropertiesGetValue.getContextProperty("intermediaryagency.update.success"));
		map.put("isSuccess", "true");
		return map;
	}
	@Override
	public Map<String, Object> deleteIntermediaryagency(String id) throws Exception {
		
		return null;
	}

	@Override
	public Map<String, Object> addIntermediaryagency(Intermediaryagency inter,String[] linknames, String [] linktellphones) throws Exception{
		
		// 获取项目业主信息ID
		Integer id = ibatisCommonDAO.executeForObject("getIntermediaryagencyID", null, Integer.class);
		
		if(id == null) {id = 0;}
		id = id +1;
		inter.setId(id.toString());

		// 执行插入语句
		ibatisCommonDAO.executeInsert("addIntermediaryagency", inter);
		
		// 添加联系人
		for (int i = 0; i < linknames.length; i++) {
			Link link = new Link();
			link.setReferid(inter.getId());
			link.setLinkname(linknames[i]);
			link.setLinktellphone(linktellphones[i]);
			if (AuditStringUtils.isNotEmpty(link.getLinkname()) || AuditStringUtils.isNotEmpty(link.getLinktellphone())) {
				// 执行插入语句
				ibatisCommonDAO.executeInsert("addLink", link);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", PropertiesGetValue.getContextProperty("intermediaryagency.add.success"));
		map.put("isSuccess", "true");
		return map;
	}

	

	@Override
	public List<Link> getLinks(String id) {
		List<Link> links = ibatisCommonDAO.executeForObjectList("getLinkList", id);
		List<Link> linkFormats = new ArrayList<Link>();
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i);
			link.setNumber(AuditStringUtils.converyNumToDaXie(AuditStringUtils.toString(i + 1)));
			linkFormats.add(link);
		}
		return linkFormats;
	}

	@Override
	public List<Dictionary> getDictionary(String id) {
		return ibatisCommonDAO.executeForObjectList("selectdicbyid",id);
	}

	@Override
	public Map<String, Object> destroyIntermediaryagency(String id)
			throws Exception {
		
		ibatisCommonDAO.executeDelete("deleteIntermediaryagency", id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", PropertiesGetValue.getContextProperty("intermediaryagency.destroy.success"));
		map.put("isSuccess", "true");
		
		return map;
		
	}
}
