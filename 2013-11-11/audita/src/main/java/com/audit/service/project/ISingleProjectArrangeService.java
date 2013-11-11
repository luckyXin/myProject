/**
 * 单项目安排业务层管理
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.EditUser;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.Employee;
import com.audit.entity.project.PackProjectArrange;
import com.audit.entity.project.ProjectInfo;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.project.SingleProjectInfo;

/**
 * @author dengXin
 */
public interface ISingleProjectArrangeService {

	/**
	 * 查询打包项目的子项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param packProjectId
	 * @return
	 */
	public GridDataModel<ArrangeProject> findPackSubProject(Integer page, Integer pagesize, String filed, String order,
			String packProjectId);

	/**
	 * 分页已安排项目信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param ownerName 业主名称
	 * @param proejctName 项目名称
	 * @return
	 */
	public GridDataModel<ArrangeProject> findSingleProject(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount);
	
	/**
	 * 分页查询项目信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param ownerName
	 * @param proejctName
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<ArrangeProject> findgov(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount,String moduleid);

	/**
	 * 分页查询项目信息
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param ownerName
	 * @param proejctName
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<ArrangeProject> find(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount,String ownerId,String moduleid);

	/**
	 * 分页查询所有项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param ownerName
	 * @param proejctName
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<ArrangeProject> findAllProject(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount);

	/**
	 * 政府雇员查询
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<Employee> find(Integer page, Integer pagesize, String filed, String order, String userAccount);

	/**
	 * 增加项目安排信息
	 * 
	 * @param projectId
	 * @param projectArrangeTime
	 * @param projectArrangeRemark
	 * @param mainAuditId
	 * @param intermediaryId
	 * @param governmentEmployees
	 * @param intermediarySendTime
	 * @param intermediaryAuditTime
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> add(String projectId, String projectArrangeTime,String intermediaryId, String projectArrangeRemark,
			String mainAuditId, String[] governmentEmployees, String intermediarySendTime,
			String intermediaryAuditTime, String state, String userAccount,String isMySelfState,String dataTransferTime) throws Exception;

	/**
	 * 获取项目安排基本信息
	 * 
	 * @param id
	 * @return
	 */
	public SingleProjectArrange getSingleProjectInfoById(String id);

	/**
	 * 单项目安排基本信息更新
	 * 
	 * @param singleProject
	 * @param projectId
	 * @param projectArrangeTime
	 * @param projectArrangeRemark
	 * @param mainAuditId
	 * @param intermediaryId
	 * @param governmentEmployees
	 * @param intermediarySendTime
	 * @param intermediaryAuditTime
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> update(String singleProject, String projectId, String projectArrangeTime,String intermediaryId,
			String projectArrangeRemark, String mainAuditId, String[] governmentEmployees,
			String intermediarySendTime, String intermediaryAuditTime, String state, String dataTransferTime, String userAccount) throws Exception;

	/**
	 * 单项目安排删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> delete(String id) throws Exception;

	/**
	 * 单项目安排销毁
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> destroy(String id, String userAccount) throws Exception;

	/**
	 * 获取项目信息
	 * 
	 * @param id
	 * @return
	 */
	public ProjectInfo getProject(String id);

	/**
	 * 根据资料id获取项目安排信息
	 * 
	 * @param datapreId
	 * @return
	 */
	public SingleProjectInfo getDataPreId(String datapreId);

	/**
	 * 根据项目安排id查询政府雇员
	 * 
	 * @param projectArrangeId
	 * @return
	 */
	public List<EditUser> findGovernmentEmpUserName(String projectArrangeId);

	/**
	 * 添加打包项目
	 * 
	 * @param projectPackName
	 * @param projectArrangeTime
	 * @param projectArrangeRemark
	 * @param mainAuditId
	 * @param intermediaryId
	 * @param governmentEmployees
	 * @param intermediarySendTime
	 * @param intermediaryAuditTime
	 * @param state
	 * @param userAccount
	 * @return
	 */
	public Map<String, Object> addPackProject(String projectPackName, String projectArrangeTime,String intermediaryId,
			String projectArrangeRemark, String mainAuditId, String[] governmentEmployees,
			String intermediarySendTime, String intermediaryAuditTime, String state, String userAccount, String ownerId,String isMySelfState,String dataTransferTime)
			throws Exception;

	/**
	 * 打包项目更新
	 * 
	 * @param packProjectId
	 * @param projectPackName
	 * @param projectArrangeTime
	 * @param projectArrangeRemark
	 * @param mainAuditId
	 * @param intermediaryId
	 * @param governmentEmployees
	 * @param intermediarySendTime
	 * @param intermediaryAuditTime
	 * @param state
	 * @param userAccount
	 * @param ownerId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updatePackProject(String packProjectId, String projectPackName,
			String projectArrangeTime,String intermediaryId, String projectArrangeRemark, String mainAuditId,
			String[] governmentEmployees, String intermediarySendTime, String intermediaryAuditTime, String state,
			String ownerId, String sentAmount,String dataTransferTime) throws Exception;

	/**
	 * 添加打包项目的子项目
	 * 
	 * @param packProjectId
	 * @param subProjectId
	 * @return
	 */
	public Map<String, Object> addSubProject(String packProjectId, String subProjectId, String userAccount)
			throws Exception;

	/**
	 * 删除打包项目的子项目
	 * 
	 * @param packProjectId
	 * @param subProjectId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> deleteSubProject(String packProjectId, String subProjectId, String userAccount)
			throws Exception;

	/**
	 * 获取打包项目信息
	 * 
	 * @param id
	 * @return
	 */
	public PackProjectArrange getPackProjectArrange(String id);

	/**
	 * 删除打包项目
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> deletePackProjectArrange(String id, String userAccount) throws Exception;

	/**
	 * 打包项目销毁
	 * 
	 * @param id
	 * @param userAccount
	 * @return
	 */
	public Map<String, Object> destroyPackProjectArrange(String id, String userAccount) throws Exception;
}
