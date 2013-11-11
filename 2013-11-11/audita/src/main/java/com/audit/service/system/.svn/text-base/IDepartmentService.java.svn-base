package com.audit.service.system;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Department;



/**
 * 机构接口
 * @author dengyong
 *
 */
public interface IDepartmentService {
    
	/**
	 * 查询机构下拉树形结构
	 * @param id
	 * @return
	 */
	public String findbypid(String id);
	
	
	/**
	 * 根据id查询部门对象
	 * @param id
	 * @return
	 */
	public  Department  findbyid(String id);
	
	
	/**
	 * 修改机构
	 * @param dic
	 * @return
	 */
	public Map<String, Object> update(Department dept)throws Exception;
	
	/**
	 * 按id查询子级机构
	 * @param id
	 * @return
	 */
	public List<Department> findbychildid(String id);
	
	/**
	 * 添加机构
	 * @param dept
	 * @return
	 */
	public Map<String, Object> add(Department dept)throws Exception;
	
	/**
	 * 分页查询机构信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @param id 机构id
	 * @return
	 */
	public GridDataModel<Department> find(Integer page,Integer pagesize,String name,String order,String id);
	
	
	
	/**
	 * 删除机构
	 * @param id 机构id
	 * @return
	 */
	public Map<String, Object> delete(String id)throws Exception;
}
