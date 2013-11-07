/**
 * 
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.TractClaimIndemnityContext;

/**
 * @author 索赔接口
 *
 */
public interface ITractClaimIndemnityService {

	
	/**
	 * 分页查询索赔信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractClaimIndemnityContext> findbybd(Integer page,Integer pagesize,String name,String order,String biaoduanid,String type);
	/**
	 * 根据标段id查询索赔对象
	 * @param biaoduanId
	 * @return
	 */
	public TractClaimIndemnityContext findbybdid(String biaoduanId);
	
	
	/**
	 * 根据id查询索赔对象
	 * @param biaoduanId
	 * @return
	 */
    public TractClaimIndemnityContext findbyid(String id);
	
	
	/**
	 * 添加索赔
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractClaimIndemnityContext suopei)throws Exception;
	
	/**
	 * 修改索赔
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractClaimIndemnityContext suopei)throws Exception;
	
	
	/**
	 *删除索赔
	 * @return
	 */
	public Map<String, Object>  delete(String id) throws Exception;

}
