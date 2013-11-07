package com.audit.service.system;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Dictionary;

/**
 * 定义字典接口
 * @author dengyong
 *
 */
public interface IDictionaryService {

	
	/**
	 * 根据id查询对应字典
	 * @param id
	 * @return
	 */
	public List<Dictionary> findbyid(String id);
	
	
	/**
	 * 根据字典名称查询字典信息
	 * @param name
	 * @return
	 */
	public Dictionary findbyName(String name);
	
	/**
	 * 分页查询字典信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @param id 字典id
	 * @return
	 */
	public GridDataModel<Dictionary> find(Integer page,Integer pagesize,String name,String order,String id);
	
	/**
	 * 添加字典
	 * @param dic
	 * @return
	 */
	public Map<String, Object> add(Dictionary dic) throws Exception;
	
	/**
	 * 修改字典
	 * @param dic
	 * @return
	 */
	public Map<String, Object> update(Dictionary dic) throws Exception;
	/**
	 * 查询字典对象
	 * @return
	 */
	public Dictionary  finddicbyid(String id);
	
	/**
	 * 删除字典
	 * @param id 字典id
	 * @return
	 */
	public Map<String, Object> delete(String id) throws Exception;
	
	
	
}
