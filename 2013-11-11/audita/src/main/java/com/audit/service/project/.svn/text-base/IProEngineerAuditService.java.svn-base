/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.User;
import com.audit.entity.project.AuditInfo;
import com.audit.entity.project.GovermentEmployeeAudit;
import com.audit.entity.project.ResultAuditInfo;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.WorkloadInfo;

/**
 * @author Administrator
 * 
 */
public interface IProEngineerAuditService {
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
	public GridDataModel<AuditInfo> find(Integer page, Integer pagesize, String filed, String order,
			String projectName, String ownerName, String arrangeType, String auditType, String userAccount,
			String moduleId,String beginTime,String endTime,String timeLimitStart,String timeLimitEnd,String isUrgent,String greaterThan,
			String lessThan);

	/**
	 * 获取政府雇员审批信息
	 * 
	 * @param projectId
	 * @param userAccount
	 * @return
	 */
	public GovermentEmployeeAudit getGovermentEmployeeAudit(String projectId, String userAccount);
	
	
	
	/**
	 * 根据资料id获取政府雇员审批信息
	 * 
	 * @param projectId
	 * @param userAccount
	 * @return
	 */
	public GovermentEmployeeAudit getGovermentEmployeeAuditbydatapreId(String datapreId);

	/**
	 * 创建审批信息
	 */
	public Map<String, Object> addGovermentEmployeeAudit(String datapreId,String projecttype,String projectId, String auditMoney, String reduceMoney,
			String auditStartTime, String auditEndTime, String auditDayCount, String auditRemark, String submitState,
			String userAccount, String sectionChiefAuditId,String auditlv,String deskAuditRemark) throws Exception;

	/**
	 * 更新审批信息
	 */
	public Map<String, Object> updateGovermentEmployeeAudit(String datapreId,String projecttype,String auditId, String projectId, String auditMoney,
			String reduceMoney, String auditStartTime, String auditEndTime, String auditDayCount, String auditRemark,
			String submitState, String userAccount, String sectionChiefAuditId,String auditlv,String deskAuditRemark) throws Exception;

	/**
	 * 科长审批信息获取
	 * 
	 * @param projcetId
	 * @return
	 */
	public SectionChiefAuditBaseInfo getCectionChiefAuditInfo(String projcetId);
	
	
	
	public List<GovermentEmployeeAudit> findauditcutmoney(String id);
	
	
	
	
	/**
	 * 查询政府雇员查看的相应的资料信息
	 * @param datapreId
	 * @return
	 */
	public List<com.audit.entity.project.DataPreBaseInfo> finddatapreBydateId(String datapreId);
	
	
	/**
	 * 查询最新核对工作量时间
	 * @param datapreId
	 * @return
	 */
	public WorkloadInfo  findworkTime(String datapreId);
	
	/**
	 * 提交政府雇员审核
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addsubmit(String arrangeId,String auditStartTime,String auditEndTime,String auditDayCount,User user,String state)throws Exception;
	
	/**
	 * 根据安排查询领导审核意见
	 * @param arrangeId
	 * @param method
	 * @return
	 */
	public ResultAuditInfo findAuditView(String arrangeId,String method);
	
	/**
	 * 分页查询用户信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<GovermentEmployeeAudit> find(Integer page,Integer pagesize,String name,String order,String datapreId);
}
