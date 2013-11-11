/**
 * 
 */
package com.audit.service.project;

import com.audit.common.GridDataModel;
import com.audit.entity.project.TractProjectDataEnquiry;

/**
 * @author 材料询价接口
 *
 */
public interface IProjectDataEnquiryService {

	
	/**
	 * 分页查询材料询价信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractProjectDataEnquiry> findbybd(Integer page,Integer pagesize,String name,String order,String biaoduanid);
	
	
	/**
	 * 增加材料询价信息
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractProjectDataEnquiry data)throws Exception;
	
	
	/**
	 * 修改材料询价信息
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractProjectDataEnquiry data)throws Exception;
	
	
	/**
	 * 根据标段id查询材料询价对象
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectDataEnquiry findbybdid(String biaoduanId);
	
	
	/**
	 * 根据id查询材料询价对象
	 * @param biaoduanId
	 * @return
	 */
    public TractProjectDataEnquiry findbyid(String id);
    
    
    /**
	 * 删除材料询价
	 * @param biaoduanId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer delete(String biaoduanId)throws Exception;
}
