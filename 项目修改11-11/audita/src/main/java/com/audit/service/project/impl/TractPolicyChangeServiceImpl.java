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
import com.audit.entity.project.ProTractAdjustment;
import com.audit.entity.project.TractPolicyChange;
import com.audit.service.project.ITractPolicyChangeService;

/**
 * @author 政策性调
 * 
 */
public class TractPolicyChangeServiceImpl implements ITractPolicyChangeService {
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 分页查询政策性调整信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name 排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractPolicyChange> findbybd(Integer page, Integer pagesize, String name, String order,
			String biaoduanid) {
		TractPolicyChange policychange = new TractPolicyChange();
		GridDataModel<TractPolicyChange> gm = null;
		try {
			policychange.setFiled(name);
			policychange.setSort(order);
			policychange.setBiaoDuanId(biaoduanid);
			List<TractPolicyChange> list = ibatisCommonDAO.executeForObjectList("selectallzhengcepage", policychange,
					pagesize * (page - 1), pagesize);
			//判断是否为空
			if(null!=list && list.size()!=0){
				for(TractPolicyChange ploicy:list){
					//查询总的人工调整费用
					ProTractAdjustment ht = ibatisCommonDAO.executeForObject("selecttotalrengongmoney", ploicy.getId(), ProTractAdjustment.class);
					if(null!=ht && !"".equals(ht.getMantzmoney()) && null!=ht.getMantzmoney())
					{
					    ploicy.setArtificialfile(ht.getMantzmoney());
					}else{
						ploicy.setArtificialfile("0");
					}
				}
			}
			Integer count = ibatisCommonDAO.executeForObject("selectallzhengcecount", policychange, Integer.class);
			gm = new GridDataModel<TractPolicyChange>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}
	
	
	/**
	 * 分页人工费用调整信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<ProTractAdjustment> findrengongbypolicy(Integer page,Integer pagesize,String name,String order,String id){
		ProTractAdjustment adjustment = new ProTractAdjustment();
		GridDataModel<ProTractAdjustment> gm = null;
		try {
			adjustment.setFiled(name);
			adjustment.setSort(order);
			adjustment.setPolicyChangeId(id);
			List<ProTractAdjustment> list = ibatisCommonDAO.executeForObjectList("selectrengongtzinfo", adjustment,
					pagesize * (page - 1), pagesize);
			Integer count = ibatisCommonDAO.executeForObject("selectrengongtzinfocount", adjustment, Integer.class);
			gm = new GridDataModel<ProTractAdjustment>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 根据标段id查询政策性对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public List<TractPolicyChange> findbybdid(String biaoduanId) {

		List<TractPolicyChange> list = ibatisCommonDAO.executeForObjectList("selectzhengcebybdid", biaoduanId);
		return list;
	}

	/**
	 * 根据id查询政策性对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractPolicyChange findbyid(String id) {
		TractPolicyChange ht = ibatisCommonDAO.executeForObject("selectzhengcebyid", id, TractPolicyChange.class);
		return ht;
	}
	
	/**
	 * 根据id查询人工调整费用对象
	 * @param biaoduanId
	 * @return
	 */
    public ProTractAdjustment findbyrengongid(String id){
    	ProTractAdjustment ht = ibatisCommonDAO.executeForObject("selectrengongbyid", id, ProTractAdjustment.class);
		return ht;
    }
    
    
    
    /**
	 * 添加人工调整费用
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer addrengong(ProTractAdjustment zhengce)throws Exception{
		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("addrengongmoney", zhengce);
		return row;
	}
	
	/**
	 * 修改人工调整费用
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer updaterengong(ProTractAdjustment zhengce)throws Exception{
		Integer row = 0;
		row = ibatisCommonDAO.executeUpdate("updaterengongmoney", zhengce);
		return row;
	}
	
	
	/**
	 *删除人工调整费用
	 * @return
	 */
	public Map<String, Object>  deleterengong(String id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Integer row = ibatisCommonDAO.executeDelete("delrengongmoney", id);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", "删除成功");
		} else {
			map.put("success", "fail");
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	/**
	 *查询总的人工调整费用
	 * @return
	 */
	public Map<String, Object>  findtotalrengong(String id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		//查询总的人工调整费用
		ProTractAdjustment ht = ibatisCommonDAO.executeForObject("selecttotalrengongmoney", id, ProTractAdjustment.class);
	
		if(null!=ht && !"".equals(ht.getMantzmoney()) && null!=ht.getMantzmoney())
		{
			map.put("msg", ht.getMantzmoney());
		} else {
			map.put("msg", "0");
		}
		return map;
	}

	/**
	 * 添加政策性
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractPolicyChange zhengce) throws Exception {
		Integer row = 0;
		// 执行增加清标
		row = ibatisCommonDAO.executeInsert("addzhengce", zhengce);
		return row;
	}

	/**
	 * 修改政策性
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractPolicyChange zhengce) throws Exception {
		Integer row = 0;
		// 执行修改清标
		row = ibatisCommonDAO.executeUpdate("updatezhengce", zhengce);
		return row;
	}

	/**
	 * 删除政策性
	 * 
	 * @return
	 */
	public Map<String, Object> delete(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer row = ibatisCommonDAO.executeDelete("delzhengce", id);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", "删除成功");
		} else {
			map.put("success", "fail");
			map.put("msg", "删除失败");
		}
		return map;
	}
}
