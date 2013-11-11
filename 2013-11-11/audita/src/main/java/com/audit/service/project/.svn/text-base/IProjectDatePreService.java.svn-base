package com.audit.service.project;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.audit.common.GridDataModel;

import com.audit.entity.User;
import com.audit.entity.project.DataJoinList;
import com.audit.entity.project.DataPreBaseWordInfo;
import com.audit.entity.project.DataWord;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ResultClassAuditlookproject;
import com.audit.entity.project.SingleProjectArrange;

/**
 * @author dengyong
 * 资料预审接口
 *
 */
public interface IProjectDatePreService {

	

	/**
	 * 分页查询资料预审信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param datapre 资料预审对象
	 * @return
	 */
	public GridDataModel<Datapreinfo> find(Integer page,Integer pagesize,String filed,String order,Datapreinfo datapre);
	
	
	/**
	 * 分页查询资料预审对应的资料文件
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param file 资料对象
	 * @return
	 */
	public GridDataModel<FileBelongRelate> findfile(Integer page,Integer pagesize,String filed,String order,FileBelongRelate file);
	
	
	/**
	 * 跟踪资料预审id查询文件数目
	 * @param datapreId
	 * @return
	 */
	public Integer findFileBelongRelate(String datapreId);
	
	/**
	 * 查询立项文号是否存在
	 * @return
	 */
	public Map<String, Object>  findbyid(String projectNo,String id);
	
	/**
	 * 查询项目名称是否存在
	 * @return
	 */
	public Map<String, Object>  findbyname(String projectName,String id);
	
	
	/**
	 * 根据id查询资料预审对象
	 * @param id
	 * @return
	 */
	public Datapreinfo  findtoid(String id);
	
	
	/**
	 * 查询项目信息对象
	 * @param id
	 * @return
	 */
	public  DataPreBaseWordInfo findObject(String id);
	
	
	/**
	 * 增加预审资料
	 * @return
	 */
	public boolean add(Datapreinfo data,List<FileBelongRelate>  listfile,User user) throws Exception;
	
	
	/**
	 * 修改预审资料
	 * @return
	 */
	public boolean update(Datapreinfo data,List<FileBelongRelate>  listfile,User user,String updatestate) throws Exception;
	
	
	
	/**
	 * 删除资料预审文件删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean  deletefile(String id,HttpServletRequest request)throws Exception;
	
	
	/**
	 * 删除模板文件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean  deletetempfile(String id,HttpServletRequest request)throws Exception;
	
	
	/**
	 *删除资料预审信息
	 * @return
	 */
	public Map<String, Object>  delete(String id,User user) throws Exception;
	
	/**
	 * 按资料预审id查询对象
	 */
	public DataJoinList finddatajoinlist(String datapreId);
	
	/**
	 * 增加资料交接单
	 * @return
	 */
	public Map<String, Object>  addprojoin(DataJoinList datajoin) throws Exception;
	
	
	
	/**
	 * 修改资料交接单
	 * @return
	 */
	public Map<String, Object>  updateprojoin(DataJoinList datajoin) throws Exception;
	
	
	/**
	 * 按政府交办查询资料预审中的施工企业
	 * @param id
	 * @return
	 */
	public List<Datapreinfo>   findConstructId(String id);
	
	
	/**
	 * 查询政府交办项目审核类型
	 * @param id
	 * @return
	 */
	public List<Datapreinfo>   findproaudittype(String id);
	/**
	 * 按政府交办查询资料预审中工程名称
	 * @param id
	 * @return
	 */
	public List<Datapreinfo>   findProjectName(DataWord dw);
	
	
	/**
	 * 分页查询子项目信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> findsubproject(Integer page,Integer pagesize,String filed,String order,String arrangeId);
	
	/**
	 * 分页查询子项目信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> findMainAuditSubproject(Integer page,Integer pagesize,String filed,String order,String arrangeId);
	
	/**
	 * 分页查询中介子项目信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> selectintersubproject(Integer page,Integer pagesize,String filed,String order,String arrangeId);
	
	/**
	 * 分页查询政府雇员子项目信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> selectgoversubproject(Integer page,Integer pagesize,String filed,String order,String arrangeId);
	
	
	/**
	 * 通过安排id查询单项目安排信息
	 * @param arrageId
	 * @return
	 */
	public SingleProjectArrange findZJByArrangeId(String arrageId);
	
	
	/**
	 * 录入中介机构
	 * @param single
	 * @return
	 * @throws Exception
	 */
	public Integer updateZJByArrangeId(SingleProjectArrange single)throws Exception;
}
