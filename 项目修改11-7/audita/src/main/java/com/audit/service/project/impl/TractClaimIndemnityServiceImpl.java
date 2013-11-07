/**
 * 
 */
package com.audit.service.project.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.GridDataModel;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Dictionary;
import com.audit.entity.project.TractClaimIndemnityContext;
import com.audit.service.project.ITractClaimIndemnityService;

/**
 * @author dengyong 索赔
 * 
 */
public class TractClaimIndemnityServiceImpl implements ITractClaimIndemnityService {
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 根据标段id查询索赔对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractClaimIndemnityContext findbybdid(String biaoduanId) {
		TractClaimIndemnityContext ht = ibatisCommonDAO.executeForObject("selectsuopeibybdid", biaoduanId,
				TractClaimIndemnityContext.class);
		return ht;
	}

	/**
	 * 根据id查询索赔对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractClaimIndemnityContext findbyid(String id) {
		TractClaimIndemnityContext ht = ibatisCommonDAO.executeForObject("selectsuopeibyid", id,
				TractClaimIndemnityContext.class);
		return ht;
	}

	/**
	 * 添加索赔
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractClaimIndemnityContext suopei) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("addsuopei", suopei);
		return row;
	}

	/**
	 * 修改索赔
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractClaimIndemnityContext suopei) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeUpdate("updatesuopei", suopei);

		return row;
	}

	/**
	 * 分页查询索赔信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name 排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractClaimIndemnityContext> findbybd(Integer page, Integer pagesize, String name,
			String order, String biaoduanid, String type) {
		TractClaimIndemnityContext suopei = new TractClaimIndemnityContext();
		GridDataModel<TractClaimIndemnityContext> gm = null;
		try {
			suopei.setFiled(name);
			suopei.setSort(order);
			suopei.setBiaoDuanId(biaoduanid);
			suopei.setClaimIndemnityType(type);
			List<TractClaimIndemnityContext> list = ibatisCommonDAO.executeForObjectList("selectallsuopeipage", suopei,
					pagesize * (page - 1), pagesize);

			for (TractClaimIndemnityContext s : list) {
				Dictionary d = ibatisCommonDAO.executeForObject("selectdictionarybyid", s.getClaimIndemnityType(),
						Dictionary.class);
				s.setClaimIndemnityType(d.getDictionaryname());
			}

			Integer count = ibatisCommonDAO.executeForObject("selectallsuopeicount", suopei, Integer.class);
			gm = new GridDataModel<TractClaimIndemnityContext>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 删除索赔
	 * 
	 * @return
	 */
	public Map<String, Object> delete(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer row = ibatisCommonDAO.executeDelete("delsuopei", id);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", "删除索赔成功");
		} else {
			map.put("success", "fail");
			map.put("msg", "删除索赔失败");
		}
		return map;
	}
}
