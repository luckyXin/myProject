/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ProTractMonthFileInfo;
import com.audit.entity.project.TractOtherManage;

/**
 * @author dengyong
 * 其他管理接口
 *
 */
public interface IProjectOtherManageService {
    
	/**
	 * 分页查询其他管理信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractOtherManage> findbybd(Integer page,Integer pagesize,String name,String order,String biaoduanid);
	
	
	/**
	 * 增加其他管理信息
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractOtherManage data)throws Exception;
	
	
	/**
	 * 修改其他管理信息
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractOtherManage data)throws Exception;
	
	
	/**
	 * 根据标段id查询其他管理对象
	 * @param biaoduanId
	 * @return
	 */
	public TractOtherManage findbybdid(String biaoduanId);
	
	
	/**
	 * 根据id查其他管理对象
	 * @param biaoduanId
	 * @return
	 */
    public TractOtherManage findbyid(String id);
    
    
    /**
	 * 删除其他管理
	 * @param biaoduanId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer delete(String biaoduanId)throws Exception;
	
	/**
	 * 导入月报基础数据
	 * 
	 * @param userAccount
	 * @param createTime
	 * @param monthReportFile
	 * @return
	 */
	public Map<String, Object> toLoadExcel(String biaoDuanId,String userAccount,String createTime,ProTractMonthFileInfo month, List<MultipartFile> file) throws Exception;
	
	
	/**
	 * 分页查询导入文件信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<ProTractMonthFileInfo> findTemple(Integer page,Integer pagesize,String name,String order,String biaoDuanId);
	
	
}
