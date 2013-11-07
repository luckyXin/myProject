/**
 * 
 */
package com.audit.service.search;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.TractAuditProjectDetail;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.entity.search.NoCompleteProjectResult;
import com.audit.entity.search.SearchProjectInfoResult;
import com.audit.entity.work.AuditState;

/**
 * @author User
 * 
 */
public interface IAllProjectSearchService {

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
	public GridDataModel<SearchProjectInfoResult> findPackSubProject(Integer page, Integer pagesize, String filed,
			String order, String auditStartTime, String auditEndTime, String employeeName, String auditIdentity,
			String intermediary, String construction, String buildYear, String projectOwner);

	/**
	 * 查询未完成项目
	 * 
	 * @param page
	 * @param pagesize
	 * @param filed
	 * @param order
	 * @param auditStartTime
	 * @param projectName
	 * @param projectOwner
	 * @param processPeople
	 * @return
	 */
	public GridDataModel<NoCompleteProjectResult> findNoCompleteProject(Integer page, Integer pagesize, String filed,
			String order, String auditStartTime, String projectName, String projectOwner, String processPeople,
			String projectState, String isStop);

	/**
	 * 获取项目状态
	 */
	public List<AuditState> getAuditState();

	/**
	 * 获取预审资料详细信息
	 * 
	 * @param projectId
	 * @return
	 */
	public Datapreinfo getDatapreinfo(String projectId);

	/**
	 * 个人未完成项目查询
	 */
	public GridDataModel<NoCompleteProjectResult> findMySelfNoCompleteProject(Integer page, Integer pagesize,
			String filed, String order, String acceptStartTime, String acceptEndTime, String projectName,
			String projectOwner, String projectState, String userAccount);

	/**
	 * 个人已完成项目查询
	 */
	public GridDataModel<NoCompleteProjectResult> findMySelfCompleteProject(Integer page, Integer pagesize,
			String filed, String order, String acceptStartTime, String acceptEndTime, String projectName,
			String projectOwner, String projectState, String userAccount);

	/**
	 * 开启暂停项目
	 */
	public Map<String, Object> openStopProject(String projectId, String userAccount) throws Exception;

	/**
	 * 获取跟踪项目标段信息
	 */
	public TractAuditProjectDetail getTractAuditProjectDetail(String biaoDuanId);

	/**
	 * 获取指定类型的变更总金额
	 */
	public String getChangeTotalMoneyByCadeType(TractProjectChangeCardInfo tractProjectChangeCardInfo);

	/**
	 * 获取指定类型的索赔总金额
	 */
	public String getTotalIndemnityMoney(TractProjectChangeCardInfo tractProjectChangeCardInfo);
}
