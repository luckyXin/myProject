/**
 * 
 */
package com.audit.service.search.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.component.IWorkFlowComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.Employee;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.project.TractAuditProjectDetail;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.entity.project.TractProjectQingBiao;
import com.audit.entity.search.NoCompleteProjectParam;
import com.audit.entity.search.NoCompleteProjectResult;
import com.audit.entity.search.SearchProjectInfoParam;
import com.audit.entity.search.SearchProjectInfoResult;
import com.audit.entity.work.AuditState;
import com.audit.service.search.IAllProjectSearchService;

/**
 * @author User
 * 
 */
public class AllProjectSearchServiceImpl implements IAllProjectSearchService {

	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#findPackSubProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<SearchProjectInfoResult> findPackSubProject(Integer page, Integer pagesize, String filed,
			String order, String auditStartTime, String auditEndTime, String employeeName, String auditIdentity,
			String intermediary, String construction, String buildYear, String projectOwner) {

		SearchProjectInfoParam param = new SearchProjectInfoParam();
		param.setAuditEndTime(auditEndTime);
		param.setAuditIdentity(auditIdentity);
		param.setAuditStartTime(auditStartTime);
		param.setBuildYear(buildYear);
		param.setConstruction(construction);
		param.setEmployeeName(employeeName);
		param.setFiled(filed);
		param.setIntermediary(intermediary);
		param.setPageno(page);
		param.setPagesize(pagesize);
		param.setSort(order);
		param.setProjectOwner(projectOwner);

		Integer count = ibatisCommonDAO.executeForObject("selectCompleteProjectCount", param, Integer.class);

		List<SearchProjectInfoResult> list = ibatisCommonDAO.executeForObjectList("selectCompleteProject", param);

		// 政府雇员检索
		for (SearchProjectInfoResult searchProjectInfoResultStemp : list) {
			List<String> employees = ibatisCommonDAO.executeForObjectList("getProjectEmployeeByProjectId",
					searchProjectInfoResultStemp.getProjectId());
			searchProjectInfoResultStemp.setEmployeeAudits(AuditStringUtils.addString(employees));
		}
		GridDataModel<SearchProjectInfoResult> searchProjectInfoResult = new GridDataModel<SearchProjectInfoResult>();

		searchProjectInfoResult.setTotal(count);
		searchProjectInfoResult.setRows(list);

		return searchProjectInfoResult;
	}

	/**
	 * (non-Javadoc) 2013-7-12
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#findNoCompleteProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<NoCompleteProjectResult> findNoCompleteProject(Integer page, Integer pagesize, String filed,
			String order, String auditStartTime, String projectName, String projectOwner, String processPeople,
			String projectState, String isStop) {

		NoCompleteProjectParam param = new NoCompleteProjectParam();
		param.setAudtiStarteTime(auditStartTime);
		param.setFiled(filed);
		param.setProjectName(projectName);
		param.setProjectOwner(projectOwner);
		param.setSort(order);
		param.setPagesize(pagesize);
		param.setPageno(page);
		param.setUserAccount(processPeople);
		param.setProjectState(projectState);
		param.setIsStop(isStop);

		Integer count = ibatisCommonDAO.executeForObject("selectNoCompleteProjectCount", param, Integer.class);

		List<NoCompleteProjectResult> list = ibatisCommonDAO.executeForObjectList("selectNoCompleteProject", param);

		// 查询处理人员
		for (NoCompleteProjectResult noCompleteProjectResult : list) {
			List<String> users = ibatisCommonDAO.executeForObjectList("getNoCompleteProjectOfProcessPeople",
					noCompleteProjectResult.getProjectId());
			noCompleteProjectResult.setProcessPeople(AuditStringUtils.addString(users));
			
			// 复核审计人员
			List<String> employees = ibatisCommonDAO.executeForObjectList("getProjectEmployeeByProjectId",
					noCompleteProjectResult.getProjectId());
			noCompleteProjectResult.setEmployeeAuditName(AuditStringUtils.addString(employees));
		}
		
		

		GridDataModel<NoCompleteProjectResult> searchProjectInfoResult = new GridDataModel<NoCompleteProjectResult>();

		searchProjectInfoResult.setTotal(count);
		searchProjectInfoResult.setRows(list);

		return searchProjectInfoResult;
	}

	/**
	 * 获取所有项目的状态名称以及状态值 2013-8-19
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#getAuditState()
	 */
	@Override
	public List<AuditState> getAuditState() {
		List<AuditState> list = ibatisCommonDAO.executeForObjectList("getProjectAllAuditState", null);
		return list;
	}

	/**
	 * 查询预审资料基础信息 2013-8-19
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#getDatapreinfo(java.lang.String)
	 */
	@Override
	public Datapreinfo getDatapreinfo(String projectId) {
		Datapreinfo data = new Datapreinfo();

		if (!AuditStringUtils.isNotEmpty(projectId)) {
			return data;
		}

		// 查询预审资料基础信息
		data = ibatisCommonDAO.executeForObject("selectdataprebyid", projectId, Datapreinfo.class);

		if (data == null) {
			return new Datapreinfo();
		}
		return data;
	}

	/**
	 * 个人未完成项目查询 2013-8-19
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#findMySelfNoCompleteProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<NoCompleteProjectResult> findMySelfNoCompleteProject(Integer page, Integer pagesize,
			String filed, String order, String acceptStartTime, String acceptEndTime, String projectName,
			String projectOwner, String projectState, String userAccount) {

		NoCompleteProjectParam param = new NoCompleteProjectParam();
		param.setFiled(filed);
		param.setProjectName(projectName);
		param.setProjectOwner(projectOwner);
		param.setSort(order);
		param.setPagesize(pagesize);
		param.setPageno(page);
		param.setProjectState(projectState);
		param.setAcceptStartTime(acceptStartTime);
		param.setAcceptEndTime(acceptEndTime);
		param.setUserAccount(userAccount);

		// 查询个人未完成项目总数
		Integer count = ibatisCommonDAO.executeForObject("selectNoProcessProjectCount", param, Integer.class);

		// 分页查询个人未完成项目信息
		List<NoCompleteProjectResult> list = ibatisCommonDAO.executeForObjectList("selectNoProcessProject", param);

		// 查询处理人员
		for (NoCompleteProjectResult noCompleteProjectResult : list) {
			List<String> users = ibatisCommonDAO.executeForObjectList("getNoCompleteProjectOfProcessPeople",
					noCompleteProjectResult.getProjectId());
			noCompleteProjectResult.setProcessPeople(AuditStringUtils.addString(users));
		}

		GridDataModel<NoCompleteProjectResult> searchProjectInfoResult = new GridDataModel<NoCompleteProjectResult>();

		searchProjectInfoResult.setTotal(count);
		searchProjectInfoResult.setRows(list);

		return searchProjectInfoResult;
	}

	/**
	 * 个人已完成项目查询 2013-8-19
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#findMySelfCompleteProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<NoCompleteProjectResult> findMySelfCompleteProject(Integer page, Integer pagesize,
			String filed, String order, String acceptStartTime, String acceptEndTime, String projectName,
			String projectOwner, String projectState, String userAccount) {
		NoCompleteProjectParam param = new NoCompleteProjectParam();
		param.setFiled(filed);
		param.setProjectName(projectName);
		param.setProjectOwner(projectOwner);
		param.setSort(order);
		param.setPagesize(pagesize);
		param.setPageno(page);
		param.setProjectState(projectState);
		param.setAcceptStartTime(acceptStartTime);
		param.setAcceptEndTime(acceptEndTime);
		param.setUserAccount(userAccount);

		// 查询个人未完成项目总数
		Integer count = ibatisCommonDAO.executeForObject("selectAlreadyProcessProjectCount", param, Integer.class);

		// 分页查询个人未完成项目信息
		List<NoCompleteProjectResult> list = ibatisCommonDAO.executeForObjectList("selectAlreadyProcessProject", param);

		GridDataModel<NoCompleteProjectResult> searchProjectInfoResult = new GridDataModel<NoCompleteProjectResult>();

		searchProjectInfoResult.setTotal(count);
		searchProjectInfoResult.setRows(list);

		return searchProjectInfoResult;
	}

	/**
	 * 开启暂停项目 2013-8-20
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#openStopProject(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> openStopProject(String projectId, String userAccount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 开启暂停项目
		iWorkFlowComponent.onWorkFlow(projectId);

		map.put("msg", "开启项目成功");

		return map;
	}

	/**
	 * 获取跟踪项目标段信息 2013-8-27
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#getTractAuditProjectDetail(java.lang.String)
	 */
	@Override
	public TractAuditProjectDetail getTractAuditProjectDetail(String biaoDuanId) {
		
		String totalCompleteValue = "0";
		String changeTotalMoney = "0";
		String totalIndemnityMoney = "0";
		try {
			totalCompleteValue = ibatisCommonDAO.executeForObject("getTotalCompleteValue", biaoDuanId, String.class);
			changeTotalMoney = ibatisCommonDAO.executeForObject("getChangeTotalMoney", biaoDuanId, String.class);
			totalIndemnityMoney = ibatisCommonDAO.executeForObject("getTotalIndemnityMoney", biaoDuanId, String.class);
		} catch (Exception e) {
			totalCompleteValue = "0";
			changeTotalMoney = "0";
			totalIndemnityMoney = "0";
		}

		// 获取项目基本信息标段基本信息
		TractAuditProjectDetail tractAuditProjectDetail = ibatisCommonDAO.executeForObject(
				"getTractAuditProjectDetailInfo", biaoDuanId, TractAuditProjectDetail.class);

		// 获取清标后金额
		TractProjectQingBiao qb = ibatisCommonDAO.executeForObject("selectbybdid", biaoDuanId,
				TractProjectQingBiao.class);
		tractAuditProjectDetail.setTractProjectQingBiao(qb);

		// 累计已完成产值
		
		tractAuditProjectDetail.setTotalCompleteValue(totalCompleteValue);

		// 变更总金额
		
		tractAuditProjectDetail.setTotalChangeMoney(changeTotalMoney);

		// 索赔总金额
		tractAuditProjectDetail.setTotalIndemnityMoney(totalIndemnityMoney);

		// 获取标段安排信息
		TractArrangeProjectInfo tractArrangeProjectInfo = ibatisCommonDAO.executeForObject(
				"selectTractArrangeProjectInfo", biaoDuanId, TractArrangeProjectInfo.class);
		if (tractArrangeProjectInfo != null) {
			// 获取单项目安排关联的政府雇员
			List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList("getAllEmployeeNameByArrangeId",
					tractArrangeProjectInfo.getId());
			tractArrangeProjectInfo.setGovernmentEmployeeName(AuditStringUtils.addString(govenmentEmployees));

			// 政府工程师获取
			List<Employee> govenmentEmployeeList = ibatisCommonDAO.executeForObjectList(
					"getGovenmentEmployeeByProejctArrangeId", tractArrangeProjectInfo.getId());
			List<String> govenmentEmployeeId = new ArrayList<String>();
			for (Employee employee : govenmentEmployeeList) {
				govenmentEmployeeId.add(employee.getId());
			}
			tractArrangeProjectInfo.setGovernmentEmployee(govenmentEmployeeId);
		}
		tractAuditProjectDetail.setTractArrangeProjectInfo(tractArrangeProjectInfo);

		return tractAuditProjectDetail;
	}

	/**
	 * 获取指定类型的变更总金额 2013-8-27
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#getChangeTotalMoneyByCadeType(com.audit.entity.project.TractProjectChangeCardInfo)
	 */
	@Override
	public String getChangeTotalMoneyByCadeType(TractProjectChangeCardInfo tractProjectChangeCardInfo) {
		// 变更总金额
		String changeTotalMoney = ibatisCommonDAO.executeForObject("getChangeTotalMoneyByCadeType", tractProjectChangeCardInfo, String.class);
		if (changeTotalMoney == null) {
			return "0";
		}
		return changeTotalMoney;
	}

	/**
	 * 获取指定类型的索赔总金额 2013-8-27
	 * 
	 * @see com.audit.service.search.IAllProjectSearchService#getTotalIndemnityMoney(com.audit.entity.project.TractProjectChangeCardInfo)
	 */
	@Override
	public String getTotalIndemnityMoney(TractProjectChangeCardInfo tractProjectChangeCardInfo) {
		// 索赔总金额
		String changeTotalMoney = ibatisCommonDAO.executeForObject("getTotalIndemnityMoneyByIndemnityType",
				tractProjectChangeCardInfo, String.class);
		if (changeTotalMoney == null) {
			return "0";
		}
		return changeTotalMoney;
	}
}
