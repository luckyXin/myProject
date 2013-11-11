/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ProTractAdjustment;
import com.audit.entity.project.TractPolicyChange;

/**
 * @author 政策性调整接口
 *
 */
public interface ITractPolicyChangeService {
	
	/**
	 * 分页查询政策性调整信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractPolicyChange> findbybd(Integer page,Integer pagesize,String name,String order,String biaoduanid);
	
	
	/**
	 * 分页人工费用调整信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<ProTractAdjustment> findrengongbypolicy(Integer page,Integer pagesize,String name,String order,String id);
	
	/**
	 * 根据标段id查询政策性对象
	 * @param biaoduanId
	 * @return
	 */
	public List<TractPolicyChange> findbybdid(String biaoduanId);
	
	
	/**
	 * 根据id查询政策性对象
	 * @param biaoduanId
	 * @return
	 */
    public TractPolicyChange findbyid(String id);
    
    
    /**
	 * 根据id查询人工调整费用对象
	 * @param biaoduanId
	 * @return
	 */
    public ProTractAdjustment findbyrengongid(String id);
    
    
    
    /**
	 * 添加人工调整费用
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer addrengong(ProTractAdjustment zhengce)throws Exception;
	
	/**
	 * 修改人工调整费用
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer updaterengong(ProTractAdjustment zhengce)throws Exception;
	
	
	/**
	 *删除人工调整费用
	 * @return
	 */
	public Map<String, Object>  deleterengong(String id) throws Exception;

	
	/**
	 *查询总的人工调整费用
	 * @return
	 */
	public Map<String, Object>  findtotalrengong(String id) throws Exception;
	/**
	 * 添加政策性
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractPolicyChange zhengce)throws Exception;
	
	/**
	 * 修改政策性
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractPolicyChange zhengce)throws Exception;
	
	
	/**
	 *删除政策性
	 * @return
	 */
	public Map<String, Object>  delete(String id) throws Exception;


}
