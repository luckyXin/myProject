/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;

/**
 * @author Administrator
 *
 */
public interface ITractProjectCreateService {

	/**
	 * 分页查询跟踪项目信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param ownerName 业主名称
	 * @param proejctName 项目名称
	 * @return
	 */
	public GridDataModel<TractAuditProjectInfo> findTractProject(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount, String submitState);
	
	/**
	 * 查询指定项目下所分的标段
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 通过ID查询单条跟踪项目信息
	 * 
	 * @param id
	 * @return
	 */
	public TractAuditProjectInfo getTractAuditProjectInfoById(String id);
	
	/**
	 * 通过标段ID获取标段信息
	 * @param id
	 * @return
	 */
	public ProejctBiaoDuanInfo getProejctBiaoDuanInfo(String id);
	
	/**
	 * 添加跟踪审计项目
	 * @param tractAuditProjectInfo
	 * @param listfile
	 * @return
	 */
	public Map<String, Object> addTractAuditProject(TractAuditProjectInfo tractAuditProjectInfo, List<FileBelongRelate> listfile) throws Exception ;
	
	/**
	 * 更新跟踪审计项目
	 * @param tractAuditProjectInfo
	 * @param listfile
	 * @return
	 */
	public Map<String, Object> updateTractAuditProject(TractAuditProjectInfo tractAuditProjectInfo, List<FileBelongRelate> listfile) throws Exception;
	
	/**
	 * 增加标段信息
	 */
	public Map<String, Object> addBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) throws Exception;
	
	/**
	 * 更新标段信息
	 */
	public Map<String, Object> updateBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) throws Exception;
	
	/**
	 * 删除标段信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> deleteBiaoDuanInfo(String id) throws Exception;
	
	/**
	 * 删除跟踪审计项目
	 * @param id
	 * @return
	 */
	public Map<String, Object> deleteTractAuditProject(String id) throws Exception;
	
	/**
	 * 判断项目名称是否存在
	 * @param projectName
	 * @return
	 */
	public Map<String, Object> checkProjectNameIsExist(String projectName);
	
	/**
	 * 判断标段名称是否存在
	 */
	public Map<String, Object> checkBiaoDuanNameIsExist(String biaoDuanName);
}
