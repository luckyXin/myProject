/**
 * 
 */
package com.audit.service.system;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.system.NoticeInfo;

/**
 * 公告管理接口
 * @author Administrator
 *
 */
public interface INoticeInfoService {

	/**
	 * 分页查询公告信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<NoticeInfo> find(Integer page,Integer pagesize,String name,String order);
	
	
	/**
	 * 增加公告信息
	 * @return
	 */
	public Map<String, Object>  add(NoticeInfo notice) throws Exception;
	
	
	/**
	 * 修改公告信息
	 * @return
	 */
	public Map<String, Object>  update(NoticeInfo notice) throws Exception;
	
	
	/**
	 *删除公告信息
	 * @return
	 */
	public Map<String, Object>  delete(String id) throws Exception;
	
	/**
	 * 查询公告对象
	 * @return
	 */
	public NoticeInfo  findbyid(String id);
	
	
	/**
	 * 查询前5条公告信息
	 * @return
	 */
	public List<NoticeInfo> findlist();
}
