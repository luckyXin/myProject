/**
 * 
 */
package com.audit.service.project.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.GridDataModel;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.TractProjectDataEnquiry;
import com.audit.service.project.IProjectDataEnquiryService;

/**
 * @author dengyong 材料询价业务层
 * 
 */
public class ProjectDataEnquiryServiceImpl implements IProjectDataEnquiryService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 分页查询材料询价信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name 排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractProjectDataEnquiry> findbybd(Integer page, Integer pagesize, String name, String order,
			String biaoduanid) {
		// 定义材料询价对象
		TractProjectDataEnquiry data = new TractProjectDataEnquiry();
		// 定义材料询价返回
		GridDataModel<TractProjectDataEnquiry> gm = null;
		try {
			// 设置参数
			data.setFiled(name);
			data.setSort(order);
			data.setBiaoDuanId(biaoduanid);
			// 分页查询材料询价数据
			List<TractProjectDataEnquiry> list = ibatisCommonDAO.executeForObjectList("selectalldataenquirypage", data,
					pagesize * (page - 1), pagesize);
			// 查询材料询价总条数
			Integer count = ibatisCommonDAO.executeForObject("selectalldataenquirycount", data, Integer.class);
			gm = new GridDataModel<TractProjectDataEnquiry>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 增加材料询价信息
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractProjectDataEnquiry data) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("adddataenquiry", data);
		return row;
	}

	/**
	 * 修改材料询价信息
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractProjectDataEnquiry data) throws Exception {

		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("updatedataenquiry", data);
		return row;

	}

	/**
	 * 根据标段id查询材料询价对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectDataEnquiry findbybdid(String biaoduanId) {
		TractProjectDataEnquiry data = null;
		data = ibatisCommonDAO.executeForObject("selectdataenquirybybdid", biaoduanId, TractProjectDataEnquiry.class);
		return data;
	}

	/**
	 * 根据id查询材料询价对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectDataEnquiry findbyid(String id) {
		TractProjectDataEnquiry data = null;
		data = ibatisCommonDAO.executeForObject("selectdataenquirybyid", id, TractProjectDataEnquiry.class);
		return data;
	}

	/**
	 * 删除材料询价
	 * 
	 * @param biaoduanId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer delete(String id) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeDelete("deldataenquiry", id);
		return row;
	}
}
