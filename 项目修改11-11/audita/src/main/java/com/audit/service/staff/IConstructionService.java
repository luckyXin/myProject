package com.audit.service.staff;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.staff.Construction;
import com.audit.entity.staff.Link;


/**
 * 施工企业业务层接口
 * @author dengyong
 *
 */
public interface IConstructionService {

	
	/**
	 * 分页查询施工企业信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param name 企业名称
	 * @return
	 */
	public GridDataModel<Construction> find(Integer page,Integer pagesize,String filed,String order,String name,String state);
	
	
	/**
	 * 增加施工企业
	 * @param con 施工企业对象
	 * @param linklist 联系人集合
	 * @return
	 */
	public Map<String, Object> add(Construction con,List<Link> linklist)throws Exception;
	
	/**
	 * 修改施工企业
	 * @param con 施工企业对象
	 * @param linklist 联系人集合
	 * @return
	 */
	public Map<String, Object> update(Construction con,List<Link> linklist)throws Exception;
	
	/**
	 * 删除施工企业
	 * @param id 施工企业id
	 * @return
	 */
	public Map<String, Object> delete(String id)throws Exception;
	
	/**
	 * 销毁施工企业
	 * @param con 施工企业对象
	 * @return
	 */
	public Map<String, Object> destory(Construction con)throws Exception;
	
	
	
	/**
	 * 查询联系人集合信息
	 * @param id
	 * @return
	 */
	public List<Link> findLink(String id);
	
	/**
	 * 查询联系人对象信息
	 * @param id
	 * @return
	 */
	public Link findLinkObject(String id);
	
	/**
	 * 查询施工企业对象
	 * @param id
	 * @return
	 */
	public Construction findbyid(String id);
}
