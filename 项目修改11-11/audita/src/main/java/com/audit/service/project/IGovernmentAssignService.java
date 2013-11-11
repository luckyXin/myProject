/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProGovernmentAssign;
import com.audit.entity.project.ResultClassProConference;

/**
 * @author dengyong 政府交办接口
 * 
 */
public interface IGovernmentAssignService {

	/**
	 * 分页查询政府交办
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	public GridDataModel<ProGovernmentAssign> find(Integer page, Integer pagesize, String filed, String order,
			String assignCode, String reportBatch);

	/**
	 * 获取批次交办下的
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @return
	 */
	public GridDataModel<ResultClassProConference> findAssignProject(Integer page, Integer pagesize, String filed,
			String order, String assignId);

	/**
	 * 查询交办下面的所有项目信息
	 * 
	 * @param assignId
	 * @return
	 */
	public List<ResultClassProConference> findAllAssignProject(String assignId);
	
	
	/**
	 * 查询没有交办批次的项目信息
	 * @return
	 */
	public List<ResultClassProConference> findAllNoAssignProject();

	/**
	 * 已经交办的项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param assignId
	 * @return
	 */
	public GridDataModel<ArrangeProject> findAssignCompleteProject(Integer page, Integer pagesize, String filed,
			String order, String assignCode, String reportBatch);

	/**
	 * 查询交办与未交办的所有信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param assignCode
	 * @param reportBatch
	 * @return
	 */
	public GridDataModel<ArrangeProject> findAllAssignCompleteProject(Integer page, Integer pagesize, String filed,
			String order, String ownerName, String proejctName, String userAccount);

	/**
	 * 添加政府交办信息
	 */
	public Map<String, Object> add(String reportBatch, String reportTime) throws Exception;

	/**
	 * 添加交办项目
	 * 
	 * @param assignProjectId
	 * @param assignId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addAssignProject(String assignProjectId, String assignId, String userAccount)
			throws Exception;

	/**
	 * 删除交办项目
	 * 
	 * @param assignProjectId
	 * @param assignId
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> deleteAssignProject(String assignProjectId, String assignId, String userAccount)
			throws Exception;

	/**
	 * 获取交办批次信息
	 * 
	 * @param assignId
	 * @return
	 */
	public ProGovernmentAssign getAssignInfo(String assignId);
	
	/**
	 * 查询所有的交办信息
	 * @return
	 */
	public List<ProGovernmentAssign> findAllAssignInfo();

	/**
	 * 政府交办内容录入
	 */
	public Map<String, Object> updateAssignCode(List<FileBelongRelate> listfile, String reportBatch, String reportTime,
			String assignCode, String assignTime, String id, String userAccount) throws Exception;

	/**
	 * 销毁交办信息
	 * 
	 * @param assignId
	 * @param userAccount
	 * @return
	 */
	public Map<String, Object> destroy(String assignId, String userAccount) throws Exception;
}
