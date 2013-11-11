/**
 * 单项目安排业务层管理实现
 */
package com.audit.service.project.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.component.IWorkFlowComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.DifferenceListString;
import com.audit.entity.EditUser;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.Employee;
import com.audit.entity.project.EmployeeArrangeRelate;
import com.audit.entity.project.PackProjectArrange;
import com.audit.entity.project.PackProjectRelate;
import com.audit.entity.project.ProjectInfo;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.project.SingleProjectInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.ISingleProjectArrangeService;

/**
 * @author xinDeng
 */
public class SingleProjectArrangeServiceImpl implements ISingleProjectArrangeService {
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * 查询已安排项目信息 2013-6-13
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findSingleProject(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String projectName, String userAccount) {

		ArrangeProject singleProjectInfo = new ArrangeProject();
		singleProjectInfo.setFiled(filed);
		singleProjectInfo.setPageno(page);
		singleProjectInfo.setPagesize(pagesize);
		singleProjectInfo.setSort(order);
		singleProjectInfo.setOwnerName(ownerName);
		singleProjectInfo.setProjectName(projectName);
		singleProjectInfo.setUserAccount(userAccount);

		// 获取单项目总数
		Integer count = ibatisCommonDAO.executeForObject("getAllArrangeProjectCount", singleProjectInfo, Integer.class);

		// 获取单项目安排信息
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList("getAllArrangeProject", singleProjectInfo);

		List<ArrangeProject> subs = new ArrayList<ArrangeProject>();

		// 整合打包项目安排和单项目安排
		for (ArrangeProject packProjectArrange1 : list) {
			// 判断是不是打包项目
			if (packProjectArrange1.getId().contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				// 查询打包项目下面的子项目
				List<ArrangeProject> subProject = ibatisCommonDAO.executeForObjectList("getPackProjectAllSubProject",
						packProjectArrange1.getId());
				if (subProject.size() != 0) {
					for (ArrangeProject arrangeProjectSub : subProject) {
						arrangeProjectSub.set_parentId(packProjectArrange1.getId());
						arrangeProjectSub.setId(packProjectArrange1.getId());
						subs.add(arrangeProjectSub);
					}
				}
			}
		}

		// 添加子项目
		list.addAll(subs);

		// 送审金额转换万元形式
		/*
		 * for (int i = 0; i < list.size(); i++) {
		 * 
		 * list.get(i).setSentAmount(AuditStringUtils.convertMillion(list.get(i).
		 * getSentAmount())); }
		 */

		GridDataModel<ArrangeProject> model = new GridDataModel<ArrangeProject>();

		model.setTotal(count);

		model.setRows(list);

		return model;
	}

	/**
	 * 项目信息检索 2013-6-14
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> find(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount, String ownerId,String moduleid) {

		ArrangeProject arrangeProject = new ArrangeProject();
		arrangeProject.setFiled(filed);
		arrangeProject.setSort(order);
		arrangeProject.setPageno(page);
		arrangeProject.setPagesize(pagesize);
		arrangeProject.setUserAccount(userAccount);
		arrangeProject.setOwnerName(ownerName);
		arrangeProject.setProjectName(proejctName);
		arrangeProject.setOwnerId(ownerId);
		arrangeProject.setModuleid(moduleid);
		// 获取总数
		Integer count = ibatisCommonDAO.executeForObject("findArrangeProjectAllCount", arrangeProject, Integer.class);

		// 获取单项目安排信息
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList("findArrangeProject", arrangeProject);

		// 送审金额转换万元形式
		/*
		 * for (ArrangeProject arrangeProjecta : list) {
		 * 
		 * arrangeProjecta.setSentAmount(AuditStringUtils.convertMillion(
		 * arrangeProjecta.getSentAmount())); }
		 */

		GridDataModel<ArrangeProject> arrangeProjects = new GridDataModel<ArrangeProject>();

		arrangeProjects.setTotal(count);

		arrangeProjects.setRows(list);

		return arrangeProjects;
	}

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
			String ownerName, String proejctName, String userAccount,String moduleid) {
		ArrangeProject arrangeProject = new ArrangeProject();
		arrangeProject.setFiled(filed);
		arrangeProject.setSort(order);
		arrangeProject.setPageno(page);
		arrangeProject.setPagesize(pagesize);
		arrangeProject.setUserAccount(userAccount);
		arrangeProject.setOwnerName(ownerName);
		arrangeProject.setProjectName(proejctName);
		arrangeProject.setModuleid(moduleid);
		// 获取总数
		Integer count = ibatisCommonDAO.executeForObject("findArrangeProjectAllCount", arrangeProject, Integer.class);

		// 获取单项目安排信息
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList("findArrangeProject", arrangeProject);

		// 送审金额转换万元形式
		/*
		 * for (ArrangeProject arrangeProjecta : list) {
		 * 
		 * arrangeProjecta.setSentAmount(AuditStringUtils.convertMillion(
		 * arrangeProjecta.getSentAmount())); }
		 */

		GridDataModel<ArrangeProject> arrangeProjects = new GridDataModel<ArrangeProject>();

		arrangeProjects.setTotal(count);

		arrangeProjects.setRows(list);

		return arrangeProjects;
	}

	/**
	 * 人员查询 2013-6-17
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#find(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<Employee> find(Integer page, Integer pagesize, String filed, String order, String userName) {

		Employee user = new Employee();
		user.setPageno(page);
		user.setPagesize(pagesize);
		user.setFiled(filed);
		user.setSort(order);
		user.setUserName(userName);

		// 获取人员总数量
		Integer count = ibatisCommonDAO.executeForObject("findEmployeeCount", user, Integer.class);

		// 获取人员信息
		List<Employee> list = ibatisCommonDAO.executeForObjectList("findEmployee", user);

		GridDataModel<Employee> Employees = new GridDataModel<Employee>();

		Employees.setTotal(count);
		Employees.setRows(list);

		return Employees;
	}

	/**
	 * 项目安排信息追加 2013-6-17
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#add(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	@Override
	public Map<String, Object> add(String projectId, String projectArrangeTime, String intermediaryId,
			String projectArrangeRemark, String mainAuditId, String[] governmentEmployees, String intermediarySendTime,
			String intermediaryAuditTime, String state, String userAccount, String isMySelfState,
			String dataTransferTime) throws Exception {

		// 基本信息设定
		SingleProjectArrange singleProjectArrange = new SingleProjectArrange();

		// 获取ID
		Integer id = ibatisCommonDAO.executeForObject("getSingleProjectMaxId", null, Integer.class);
		if (id == null) {
			id = 0;
		}
		singleProjectArrange.setId(AuditStringUtils.getID(CommonConstant.STR_SINGLEPROJECTARRANGEPRIMARYKEY, id, 3));
		singleProjectArrange.setDatapreId(projectId);
		singleProjectArrange.setProjectTime(projectArrangeTime);
		singleProjectArrange.setProjectRemark(projectArrangeRemark);
		singleProjectArrange.setMainAuditId(mainAuditId);
		singleProjectArrange.setIntermediaryAuditTime(intermediaryAuditTime);
		singleProjectArrange.setIntermediarySendTime(intermediarySendTime);
		singleProjectArrange.setState(state);
		singleProjectArrange.setUserAccount(userAccount);
		singleProjectArrange.setDataTransferTime(dataTransferTime);
		if (AuditStringUtils.isNotEmpty(isMySelfState)) {
			singleProjectArrange.setIsMySelfState(isMySelfState);
		}
		singleProjectArrange.setIntermediaryId(intermediaryId);
		// 加入单项目基本信息
		ibatisCommonDAO.executeInsert("addSingleProjectArrange", singleProjectArrange);

		if (governmentEmployees != null) {
			// 加入政府工程师审批关联
			for (String governmentEmployee : governmentEmployees) {

				EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
				employeeArrangeRelate.setId(AuditStringUtils.getUUID());
				employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
				employeeArrangeRelate.setProjectArrangeId(singleProjectArrange.getId());
				ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
			}
		}

		// 修改项目安排状态 为已经安排
		ibatisCommonDAO.executeUpdate("updateProjectArrangeState", projectId);

		// 判断该项目是否已经交办
		Integer isAssign = ibatisCommonDAO.executeForObject("checkProjectIsAssign", projectId, Integer.class);
		if (isAssign == null || isAssign.intValue() == 0) {
			// 跳转下一个流程，政府交办
			iWorkFlowComponent.changeWorkFlow(projectId,
					PropertiesGetValue.getContextProperty("Flow.AreaLeaderAffirm"), userAccount);
		} else {

			// 跳转下一个流程，政府交办
			iWorkFlowComponent.changeWorkFlow(projectId,
					PropertiesGetValue.getContextProperty("Flow.SingleProjectStateTwo"), userAccount);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", "0");
		map.put("msg", PropertiesGetValue.getContextProperty("singleProjectArrange.add.success"));
		return map;
	}

	/**
	 * 获取单项目安排基本信息 2013-6-18
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#getSingleProjectInfoById(java.lang.String)
	 */
	@Override
	public SingleProjectArrange getSingleProjectInfoById(String id) {

		String singleProjectArrangeId = "";
		// 判断id是否是预审资料id
		int index = id.indexOf("YS");
		if (index >= 0) {
			// 获取单项目安排基本信息
			SingleProjectArrange single = ibatisCommonDAO.executeForObject("selectsingelBydatapreId", id,
					SingleProjectArrange.class);
			if (single != null) {
				singleProjectArrangeId = single.getId();
			}
		} else {
			singleProjectArrangeId = id;
		}

		// 获取单项目安排基本信息
		SingleProjectArrange singleProjectArrange = ibatisCommonDAO.executeForObject("getSingleProejctArrangeById",
				singleProjectArrangeId, SingleProjectArrange.class);

		if (singleProjectArrange != null) {
			// 获取单项目安排关联的政府雇员
			List<Employee> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
					"getGovenmentEmployeeByProejctArrangeId", singleProjectArrange.getId());

			// 雇员信息设定
			StringBuilder govenmentEmployeeName = new StringBuilder();
			List<String> govenmentEmployeeId = new ArrayList<String>();
			if (null != govenmentEmployees && govenmentEmployees.size() != 0) {
				for (Employee employee : govenmentEmployees) {
					if (govenmentEmployeeName.length() == 0) {
						govenmentEmployeeName.append(employee.getUserName());
					} else {
						govenmentEmployeeName.append(",");
						govenmentEmployeeName.append(employee.getUserName());
					}
					govenmentEmployeeId.add(employee.getId());
				}
			}
			singleProjectArrange.setGovernmentEmployeeName(govenmentEmployeeName.toString());
			singleProjectArrange.setGovernmentEmployee(govenmentEmployeeId);
		}

		return singleProjectArrange;
	}

	/**
	 * 单项目安排信息更新 2013-6-18
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#update(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String[],
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> update(String singleProjectId, String projectId, String projectArrangeTime,
			String intermediaryId, String projectArrangeRemark, String mainAuditId, String[] governmentEmployees,
			String intermediarySendTime, String intermediaryAuditTime, String state, String dataTransferTime,
			String userAccount) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 政府工程师获取
		List<Employee> govenmentEmployeeList = ibatisCommonDAO.executeForObjectList(
				"getGovenmentEmployeeByProejctArrangeId", singleProjectId);
		List<String> govenmentEmployeeId = new ArrayList<String>();
		for (Employee employee : govenmentEmployeeList) {
			govenmentEmployeeId.add(employee.getId());
		}

		// 更新基本信息
		SingleProjectArrange singleProjectArrange = new SingleProjectArrange();
		singleProjectArrange.setId(singleProjectId);
		singleProjectArrange.setProjectTime(projectArrangeTime);
		singleProjectArrange.setProjectRemark(projectArrangeRemark);
		singleProjectArrange.setMainAuditId(mainAuditId);
		singleProjectArrange.setIntermediaryAuditTime(intermediaryAuditTime);
		singleProjectArrange.setIntermediarySendTime(intermediarySendTime);
		singleProjectArrange.setState(state);
		singleProjectArrange.setDataTransferTime(dataTransferTime);
		singleProjectArrange.setIntermediaryId(intermediaryId);
		singleProjectArrange.setUserAccount(userAccount);
		// 更新单项目安排信息
		Integer count = ibatisCommonDAO.executeUpdate("updateSingleProjectArrange", singleProjectArrange);

		if (0 == count.intValue()) {
			throw new AuditException(PropertiesGetValue.getContextProperty("singleProjectArrange.update.fail"));
		}

		if (governmentEmployees != null) {
			List<String> newGovenmentEmployeeId = Arrays.asList(governmentEmployees);
			// 获取差分后的政府工程师ID
			DifferenceListString DifGovenmentEmployeeId = AuditStringUtils.compareListString(govenmentEmployeeId,
					newGovenmentEmployeeId);

			// 变更后，追加的政府工程师审批关联
			for (String governmentEmployee : DifGovenmentEmployeeId.getAddString()) {

				EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
				employeeArrangeRelate.setId(AuditStringUtils.getUUID());
				employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
				employeeArrangeRelate.setProjectArrangeId(singleProjectArrange.getId());
				ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
			}

			// 变更后，删除的政府工程师审批关联
			for (String governmentEmployee : DifGovenmentEmployeeId.getDeleteString()) {
				EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
				employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
				employeeArrangeRelate.setProjectArrangeId(singleProjectArrange.getId());
				ibatisCommonDAO.executeDelete("deleteEmployeeArrangeRelete", employeeArrangeRelate);
			}
		}

		// 根据安排状态确定流程的状态
		if ("0".equals(state)) {
			iWorkFlowComponent.onWorkFlow(singleProjectArrange.getDatapreId());
		} else {
			iWorkFlowComponent.offWorkFlow(singleProjectArrange.getDatapreId());
		}

		// 判断是否该项目是否已经安排过了
		Datapreinfo datapreinfo = ibatisCommonDAO.executeForObject("selectdataprebyid", projectId, Datapreinfo.class);
		if (datapreinfo != null && datapreinfo.getIsArrangement() != null) {
			if (!AuditStringUtils.equals("1", datapreinfo.getIsArrangement().toString())) {
				// 修改项目安排状态 为已经安排
				Integer countOne = ibatisCommonDAO.executeUpdate("updateProjectArrangeState", projectId);
				if (countOne == 0) {
					throw new AuditException(PropertiesGetValue.getContextProperty("singleProjectArrange.update.fail"));
				}

				// 判断该项目是否已经交办
				Integer isAssign = ibatisCommonDAO.executeForObject("checkProjectIsAssign", projectId, Integer.class);
				if (isAssign == null || isAssign.intValue() == 0) {
					// 跳转下一个流程，政府交办
					iWorkFlowComponent.changeWorkFlow(projectId,
							PropertiesGetValue.getContextProperty("Flow.AreaLeaderAffirm"), userAccount);
				} else {

					// 跳转下一个流程，政府交办
					iWorkFlowComponent.changeWorkFlow(projectId,
							PropertiesGetValue.getContextProperty("Flow.SingleProjectStateTwo"), userAccount);
				}
			} else {
				// 更新任务流程
				iWorkFlowComponent.backChangeWorKFlowUserAccount(projectId);
			}
		}
		map.put("isSuccess", "0");
		map.put("msg", PropertiesGetValue.getContextProperty("singleProjectArrange.update.success"));
		return map;
	}

	/**
	 * 单项目安排删除 2013-6-19
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#delete(java.lang.String)
	 */
	@Override
	public Map<String, Object> delete(String id) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Integer count = ibatisCommonDAO.executeUpdate("deleteStateSingleProjectArrange", id);

		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("singleProjectArrange.destroty.fail"));
		}

		SingleProjectArrange singleProjectArrange = ibatisCommonDAO.executeForObject("getSingleProejctArrangeById", id,
				SingleProjectArrange.class);

		iWorkFlowComponent.offWorkFlow(singleProjectArrange.getDatapreId());

		map.put("msg", PropertiesGetValue.getContextProperty("singleProjectArrange.delete.success"));
		return map;
	}

	/**
	 * 单项目安排销毁 2013-6-19
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#destroty(java.lang.String,java.lang.String)
	 */
	@Override
	public Map<String, Object> destroy(String id, String userAccount) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 获取单项目安排基本信息
		SingleProjectArrange singleProjectArrangeSingle = ibatisCommonDAO.executeForObject(
				"getSingleProejctArrangeById", id, SingleProjectArrange.class);

		// 判断政府工程师已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(singleProjectArrangeSingle.getGovernmentEmployeeAuditState())) {
			map.put("msg", PropertiesGetValue.getContextProperty("error.massage.alreadyGovenmentEmployeeAudit"));
			return map;
		}

		// 中介机构已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(singleProjectArrangeSingle.getIntermediaryAuditState())) {

			map.put("msg", PropertiesGetValue.getContextProperty("error.massage.alreadyIntermediaryagencyAudit"));
			return map;
		}

		// 主审已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(singleProjectArrangeSingle.getMainAuditState())) {

			map.put("msg", PropertiesGetValue.getContextProperty("error.message.alreadyMainAudit"));
			return map;
		}

		// 获取项目ID
		String projectId = ibatisCommonDAO.executeForObject("getProjectIdBySingleProjectArrangeId", id, String.class);

		// 销毁单项目安排信息
		Integer count = ibatisCommonDAO.executeDelete("deleteSingleProjectArrange", id);

		// 删除该项目安排的政府工程师
		Integer deleteCount = ibatisCommonDAO.executeDelete("deleteEmployeeArrangeReleteByProjectArrangeId", id);

		if (count == 0 || deleteCount == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("singleProjectArrange.destroty.fail"));
		}

		// 修改项目安排状态 为未安排
		ibatisCommonDAO.executeUpdate("updateProjectNoArrangeState", projectId);

		// 修正改项目流向
		iWorkFlowComponent.changeWorkFlowNoRecord(projectId,
				PropertiesGetValue.getContextProperty("Flow.SingleProjectDeleteState"), userAccount);

		map.put("msg", PropertiesGetValue.getContextProperty("singleProjectArrange.destroty.success"));
		return map;
	}

	/**
	 * 获取项目信息 2013-6-24
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#getProject(java.lang.String)
	 */
	@Override
	public ProjectInfo getProject(String id) {

		ProjectInfo projectInfo = ibatisCommonDAO.executeForObject("getProjectInfoByProjectId", id, ProjectInfo.class);

		projectInfo.setSentAmount(projectInfo.getSentAmount());
		return projectInfo;
	}

	/**
	 * 根据资料id获取项目安排信息
	 * 
	 * @param datapreId
	 * @return
	 */
	@Override
	public SingleProjectInfo getDataPreId(String datapreId) {
		SingleProjectInfo single = ibatisCommonDAO.executeForObject("selectsinglebydatapreId", datapreId,
				SingleProjectInfo.class);
		return single;
	}

	/**
	 * 根据项目安排id查询政府雇员
	 * 
	 * @param projectArrangeId
	 * @return
	 */
	@Override
	public List<EditUser> findGovernmentEmpUserName(String projectArrangeId) {
		List<EditUser> list = ibatisCommonDAO.executeForObjectList("selectgovernmentEmpUserName", projectArrangeId);
		return list;
	}

	/**
	 * 查询所有项目 2013-6-21
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#findAllProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findAllProject(Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount) {

		ArrangeProject singleProjectInfo = new ArrangeProject();
		singleProjectInfo.setFiled(filed);
		singleProjectInfo.setPageno(page);
		singleProjectInfo.setPagesize(pagesize);
		singleProjectInfo.setSort(order);
		singleProjectInfo.setOwnerName(ownerName);
		singleProjectInfo.setProjectName(proejctName);
		singleProjectInfo.setUserAccount(userAccount);
		// 获取总数
		Integer count = ibatisCommonDAO.executeForObject("selectAllProjectCount", singleProjectInfo, Integer.class);

		// 获取单项目安排信息
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList("selectAllProject", singleProjectInfo);

		List<ArrangeProject> subs = new ArrayList<ArrangeProject>();

		// 整合打包项目安排和单项目安排
		for (ArrangeProject packProjectArrange1 : list) {
			// 判断是不是打包项目
			if (packProjectArrange1.getId().contains(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY)) {
				// 查询打包项目下面的子项目
				List<ArrangeProject> subProject = ibatisCommonDAO.executeForObjectList("getPackProjectAllSubProject",
						packProjectArrange1.getId());
				if (subProject.size() != 0) {
					for (ArrangeProject arrangeProjectSub : subProject) {
						arrangeProjectSub.set_parentId(packProjectArrange1.getId());
						arrangeProjectSub.setId(packProjectArrange1.getId());
						subs.add(arrangeProjectSub);
					}
				}
			}
		}
		// 添加子项目
		list.addAll(subs);

		GridDataModel<ArrangeProject> arrangeProjects = new GridDataModel<ArrangeProject>();
		arrangeProjects.setRows(list);
		arrangeProjects.setTotal(count);
		return arrangeProjects;
	}

	/**
	 * 查询打包项目的子项目 2013-6-22
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#findPackSubProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findPackSubProject(Integer page, Integer pagesize, String filed, String order,
			String packProjectId) {

		PackProjectArrange packProjectArrange = new PackProjectArrange();
		packProjectArrange.setPageno(page);
		packProjectArrange.setPagesize(pagesize);
		packProjectArrange.setFiled(filed);
		packProjectArrange.setId(packProjectId);
		packProjectArrange.setSort(order);

		// 查询总数
		Integer count = ibatisCommonDAO.executeForObject("getPackSubProjectAllCount", packProjectId, Integer.class);

		// 查询子项目
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList("getPackSubProject", packProjectArrange);

		GridDataModel<ArrangeProject> ArrangeProjects = new GridDataModel<ArrangeProject>();
		ArrangeProjects.setRows(list);
		ArrangeProjects.setTotal(count);

		return ArrangeProjects;
	}

	/**
	 * 添加打包项目 2013-6-22
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#addPackProject(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String[], java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> addPackProject(String projectPackName, String projectArrangeTime, String intermediaryId,
			String projectArrangeRemark, String mainAuditId, String[] governmentEmployees, String intermediarySendTime,
			String intermediaryAuditTime, String state, String userAccount, String ownerId, String isMySelfState,
			String dataTransferTime) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 打包项目基本信息
		PackProjectArrange packProjectArrange = new PackProjectArrange();
		Integer id = ibatisCommonDAO.executeForObject("getPackProjectMaxId", null, Integer.class);
		packProjectArrange.setId(AuditStringUtils.getID(CommonConstant.STR_PACKPROJECTARRANGEPRIMARYKEY, id, 3));
		packProjectArrange.setProjectPackName(projectPackName);
		packProjectArrange.setProjectTime(projectArrangeTime);
		packProjectArrange.setProjectRemark(projectArrangeRemark);
		packProjectArrange.setMainAuditId(mainAuditId);
		packProjectArrange.setIntermediarySendTime(intermediarySendTime);
		packProjectArrange.setIntermediaryAuditTime(intermediaryAuditTime);
		packProjectArrange.setUserAccount(userAccount);
		packProjectArrange.setState(state);
		packProjectArrange.setOwnerId(ownerId);
		packProjectArrange.setSentAmount("0");
		packProjectArrange.setDataTransferTime(dataTransferTime);
		packProjectArrange.setIntermediaryId(intermediaryId);
		if (AuditStringUtils.isNotEmpty(isMySelfState)) {
			packProjectArrange.setIsMySelfState(isMySelfState);
		}
		Integer countPackProjectArrange = ibatisCommonDAO.executeInsert("addPackProjectArrange", packProjectArrange);
		// 插入失败的场合
		if (countPackProjectArrange == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("packProject.add.fail"));
		}

		// 加入政府工程师审批关联
		for (String governmentEmployee : governmentEmployees) {

			EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
			employeeArrangeRelate.setId(AuditStringUtils.getUUID());
			employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
			employeeArrangeRelate.setProjectArrangeId(packProjectArrange.getId());
			ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
		}
		map.put("isAddPackProjectState", 1);
		map.put("packProjectId", packProjectArrange.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("packProject.add.success"));
		return map;
	}

	/**
	 * 添加打包项目的子项目 2013-6-22
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#addSubProject(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> addSubProject(String packProjectId, String subProjectId, String userAccount)
			throws Exception {
		String[] subProjectIds = subProjectId.split(",");
		Integer countPackProjectArrange = 0;
		for (int i = 0; i < subProjectIds.length; i++) {
			PackProjectRelate packProjectRelate = new PackProjectRelate();
			packProjectRelate.setId(AuditStringUtils.getUUID());
			packProjectRelate.setProjectId(subProjectIds[i]);
			packProjectRelate.setProjectPackId(packProjectId);
			countPackProjectArrange = ibatisCommonDAO.executeInsert("addPackProjectOfSubProject", packProjectRelate);
		}

		// 插入失败的场合
		if (countPackProjectArrange == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("packProject.add.fail"));
		}

		String allSentAmount = ibatisCommonDAO.executeForObject("getPackOfAllSubProjectAmount", packProjectId,
				String.class);

		if (!AuditStringUtils.isNotEmpty(allSentAmount)) {
			allSentAmount = "0";
		}
		PackProjectArrange packProjectArrange = new PackProjectArrange();
		packProjectArrange.setId(packProjectId);
		packProjectArrange.setSentAmount(allSentAmount);

		// 更新打包项目送审金额
		ibatisCommonDAO.executeUpdate("updatePackProjectAmount", packProjectArrange);

		for (int i = 0; i < subProjectIds.length; i++) {
			// 修改项目安排状态 为已经安排
			ibatisCommonDAO.executeUpdate("updateProjectArrangeState", subProjectIds[i]);

			// 判断该项目是否已经交办
			Integer isAssign = ibatisCommonDAO
					.executeForObject("checkProjectIsAssign", subProjectIds[i], Integer.class);
			if (isAssign == null || isAssign.intValue() == 0) {

				// 跳转下一个流程，政府交办
				iWorkFlowComponent.changeWorkFlow(subProjectIds[i],
						PropertiesGetValue.getContextProperty("Flow.AreaLeaderAffirm"), userAccount);
			} else {

				// 跳转下一个流程，政府交办
				iWorkFlowComponent.changeWorkFlow(subProjectIds[i],
						PropertiesGetValue.getContextProperty("Flow.SingleProjectStateTwo"), userAccount);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sentAmount", allSentAmount);
		map.put("msg", PropertiesGetValue.getContextProperty("packProject.add.success"));
		return map;
	}

	/**
	 * 删除打包项目的子项目 2013-6-22
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#deleteSubProject(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> deleteSubProject(String packProjectId, String subProjectId, String userAccount)
			throws Exception {

		PackProjectRelate packProjectRelate = new PackProjectRelate();
		packProjectRelate.setProjectId(subProjectId);
		packProjectRelate.setProjectPackId(packProjectId);

		Integer countPackProjectArrange = ibatisCommonDAO.executeDelete("deletePackProjectOfSubProject",
				packProjectRelate);
		// 插入失败的场合
		if (countPackProjectArrange == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("packProject.delete.fail"));
		}

		String allSentAmount = ibatisCommonDAO.executeForObject("getPackOfAllSubProjectAmount", packProjectId,
				String.class);

		if (!AuditStringUtils.isNotEmpty(allSentAmount)) {
			allSentAmount = "0";
		}
		PackProjectArrange packProjectArrange = new PackProjectArrange();
		packProjectArrange.setId(packProjectId);
		packProjectArrange.setSentAmount(allSentAmount);

		// 更新打包项目送审金额
		ibatisCommonDAO.executeUpdate("updatePackProjectAmount", packProjectArrange);

		// 修改项目安排状态 为未安排
		ibatisCommonDAO.executeUpdate("updateProjectNoArrangeState", subProjectId);

		// 修正改项目流向
		iWorkFlowComponent.changeWorkFlow(subProjectId,
				PropertiesGetValue.getContextProperty("Flow.SingleProjectDeleteState"), userAccount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sentAmount", allSentAmount);
		map.put("msg", PropertiesGetValue.getContextProperty("packProject.delete.success"));
		return map;
	}

	/**
	 * 打包项目基本信息更新 2013-6-23
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#updatePackProject(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String[],
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> updatePackProject(String packProjectId, String projectPackName,
			String projectArrangeTime, String intermediaryId, String projectArrangeRemark, String mainAuditId,
			String[] governmentEmployees, String intermediarySendTime, String intermediaryAuditTime, String state,
			String ownerId, String sentAmount, String dataTransferTime) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 判定审批状态
		PackProjectArrange packProjectArrangeAuditState = ibatisCommonDAO.executeForObject("getPackProjectAuditState",
				packProjectId, PackProjectArrange.class);

		// 政府工程师获取
		List<Employee> govenmentEmployeeList = ibatisCommonDAO.executeForObjectList(
				"getGovenmentEmployeeByProejctArrangeId", packProjectId);
		List<String> govenmentEmployeeId = new ArrayList<String>();
		for (Employee employee : govenmentEmployeeList) {
			govenmentEmployeeId.add(employee.getId());
		}

		List<String> newGovenmentEmployeeId = Arrays.asList(governmentEmployees);

		// 获取差分后的政府工程师ID
		DifferenceListString DifGovenmentEmployeeId = AuditStringUtils.compareListString(govenmentEmployeeId,
				newGovenmentEmployeeId);

		// 判断政府工程师已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED
				.equals(packProjectArrangeAuditState.getGovernmentEmployeeAuditState())) {
			// 判定政府工程师有改动的情况
			if (DifGovenmentEmployeeId.getAddString().size() != 0
					|| DifGovenmentEmployeeId.getDeleteString().size() != 0) {

				map.put("msg", PropertiesGetValue.getContextProperty("error.massage.alreadyGovenmentEmployeeAudit"));
				return map;
			}
		}

		// 主审已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(packProjectArrangeAuditState.getMainAuditState())) {
			// 判定主审ID是否有改动
			if (!AuditStringUtils.equals(mainAuditId, packProjectArrangeAuditState.getMainAuditId())) {
				map.put("msg", PropertiesGetValue.getContextProperty("error.message.alreadyMainAudit"));
				return map;
			}
		}

		// 打包项目基本信息
		PackProjectArrange packProjectArrange = new PackProjectArrange();
		packProjectArrange.setId(packProjectId);
		packProjectArrange.setProjectPackName(projectPackName);
		packProjectArrange.setProjectTime(projectArrangeTime);
		packProjectArrange.setProjectRemark(projectArrangeRemark);
		packProjectArrange.setMainAuditId(mainAuditId);
		packProjectArrange.setIntermediarySendTime(intermediarySendTime);
		packProjectArrange.setIntermediaryAuditTime(intermediaryAuditTime);
		packProjectArrange.setState(state);
		packProjectArrange.setOwnerId(ownerId);
		packProjectArrange.setSentAmount(sentAmount);
		packProjectArrange.setDataTransferTime(dataTransferTime);
		packProjectArrange.setIntermediaryId(intermediaryId);
		Integer count = ibatisCommonDAO.executeUpdate("updatePackProjectInfo", packProjectArrange);
		if (count == 0) {
			map.put("msg", PropertiesGetValue.getContextProperty("packProject.update.fail"));
			return map;
		}
		// 打包项目安排下面的子项目变为未安排状态
		List<ArrangeProject> subProject = ibatisCommonDAO.executeForObjectList("getPackProjectAllSubProject",
				packProjectArrange.getId());
		for (ArrangeProject ArrangeProject : subProject) {
			// 根据安排状态确定流程的状态
			if ("0".equals(state)) {
				iWorkFlowComponent.onWorkFlow(ArrangeProject.getId());
			} else {
				iWorkFlowComponent.offWorkFlow(ArrangeProject.getId());
			}
			// 更新任务流程
			iWorkFlowComponent.backChangeWorKFlowUserAccount(ArrangeProject.getId());
		}
		map.put("isUpdatePackProjectState", "0");
		map.put("msg", PropertiesGetValue.getContextProperty("packProject.update.success"));
		return map;
	}

	/**
	 * 获取打包项目信息 2013-6-24
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#getPackProjectArrange(java.lang.String)
	 */
	@Override
	public PackProjectArrange getPackProjectArrange(String id) {

		// 获取打包项目基本信息
		PackProjectArrange packProjectArrange = ibatisCommonDAO.executeForObject("selectPackProjectInfo", id,
				PackProjectArrange.class);

		// 获取单项目安排关联的政府雇员
		List<Employee> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
				"getGovenmentEmployeeByProejctArrangeId", packProjectArrange.getId());

		// 雇员信息设定
		StringBuilder govenmentEmployeeName = new StringBuilder();
		List<String> govenmentEmployeeId = new ArrayList<String>();
		for (Employee employee : govenmentEmployees) {
			if (govenmentEmployeeName.length() == 0) {
				govenmentEmployeeName.append(employee.getUserName());
			} else {
				govenmentEmployeeName.append(",");
				govenmentEmployeeName.append(employee.getUserName());
			}
			govenmentEmployeeId.add(employee.getId());
		}
		packProjectArrange.setGovernmentEmployeeName(govenmentEmployeeName.toString());
		packProjectArrange.setGovernmentEmployee(govenmentEmployeeId);

		return packProjectArrange;
	}

	/**
	 * 打包项目删除 2013-6-25
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#deletePackProjectArrange(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> deletePackProjectArrange(String id, String userAccount) throws Exception {
		PackProjectArrange PackProjectArrange = new PackProjectArrange();
		PackProjectArrange.setId(id);
		PackProjectArrange.setState("1");
		// 打包项目状态改为 "1"
		ibatisCommonDAO.executeUpdate("updatePackProjectState", PackProjectArrange);

		// 打包项目下面子项目改为禁用
		// 查询打包项目下面的子项目
		List<ArrangeProject> subProject = ibatisCommonDAO.executeForObjectList("getPackProjectAllSubProject", id);

		for (ArrangeProject ArrangeProject : subProject) {
			iWorkFlowComponent.offWorkFlow(ArrangeProject.getId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", PropertiesGetValue.getContextProperty("packProject.update.success"));
		return map;
	}

	/**
	 * 打包项目销毁 2013-6-25
	 * 
	 * @see com.audit.service.project.ISingleProjectArrangeService#destroyPackProjectArrange(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> destroyPackProjectArrange(String id, String userAccount) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 获取单项目安排基本信息
		PackProjectArrange packProjectArrange = ibatisCommonDAO.executeForObject("selectPackProjectInfo", id,
				PackProjectArrange.class);

		// 判断政府工程师已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(packProjectArrange.getGovernmentEmployeeAuditState())) {
			map.put("msg", PropertiesGetValue.getContextProperty("error.massage.alreadyGovenmentEmployeeAudit"));
			return map;
		}

		// 中介机构已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(packProjectArrange.getIntermediaryAuditState())) {

			map.put("msg", PropertiesGetValue.getContextProperty("error.massage.alreadyIntermediaryagencyAudit"));
			return map;
		}

		// 主审已经审核的情况
		if (!CommonConstant.AUDIT_STATE_UNTREATED.equals(packProjectArrange.getMainAuditState())) {

			map.put("msg", PropertiesGetValue.getContextProperty("error.message.alreadyMainAudit"));
			return map;
		}

		// 打包项目安排下面的子项目变为未安排状态
		List<ArrangeProject> subProject = ibatisCommonDAO.executeForObjectList("getPackProjectAllSubProject", id);
		for (ArrangeProject ArrangeProject : subProject) {
			// 修改项目安排状态 为未安排
			ibatisCommonDAO.executeUpdate("updateProjectNoArrangeState", ArrangeProject.getId());

			// 修正改项目流向
			iWorkFlowComponent.changeWorkFlowNoRecord(ArrangeProject.getId(),
					PropertiesGetValue.getContextProperty("Flow.SingleProjectDeleteState"), userAccount);

		}

		// 删除该打包项目下面的子项目
		ibatisCommonDAO.executeDelete("deleteSubPackProject", id);

		// 销毁打包项目安排
		Integer count = ibatisCommonDAO.executeDelete("deletePackProject", id);

		// 删除该项目安排的政府工程师
		ibatisCommonDAO.executeDelete("deleteEmployeeArrangeReleteByProjectArrangeId", id);

		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("packProject.delete.fail"));
		}

		// 切换子项目流程状态
		map.put("msg", PropertiesGetValue.getContextProperty("singleProjectArrange.destroty.success"));
		return map;
	}
}
