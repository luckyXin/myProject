/**
 * 
 */
package com.audit.service.project.impl;

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
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.ArrangeProject;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProGoverMentProject;
import com.audit.entity.project.ProGovernmentAssign;
import com.audit.entity.project.ResultClassProConference;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.ProjectOwner;
import com.audit.exception.AuditException;
import com.audit.service.project.IGovernmentAssignService;
import com.audit.service.staff.IIntermediaryagencyService;
import com.audit.service.staff.IProjectOwnerService;
import com.audit.service.system.IUserInfoService;

/**
 * @author dengyong 政府交办实现类
 * 
 */
public class GovernmentAssignServiceImpl implements IGovernmentAssignService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 分页查询政府交办
	 * 
	 * @param page
	 *            当前页数
	 * @param pagesize
	 *            每页条数
	 * @param filed
	 *            字段
	 * @param order
	 *            排序
	 * @return
	 */
	public GridDataModel<ProGovernmentAssign> find(Integer page,
			Integer pagesize, String filed, String order, String assignCode,
			String reportBatch) {
		ProGovernmentAssign gov = new ProGovernmentAssign();
		GridDataModel<ProGovernmentAssign> gm = null;
		try {
			gov.setFiled(filed);
			gov.setSort(order);
			gov.setAssignCode(assignCode);
			gov.setReportBatch(reportBatch);
			List<ProGovernmentAssign> list = ibatisCommonDAO
					.executeForObjectList("selectgovernmentassignpage", gov,
							pagesize * (page - 1), pagesize);
			Integer count = ibatisCommonDAO.executeForObject(
					"selectgovernmentassigncount", gov, Integer.class);
			gm = new GridDataModel<ProGovernmentAssign>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 添加政府交办信息 2013-6-26
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#add(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> add(String reportBatch, String reportTime)
			throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		// 获取ID
		Integer id = ibatisCommonDAO.executeForObject(
				"getGovernmentAssignMaxId", null, Integer.class);
		ProGovernmentAssign gov = new ProGovernmentAssign();
		gov.setReportBatch(reportBatch);
		if (AuditStringUtils.isNotEmpty(reportTime)) {
			gov.setReportTime(reportTime);
		}
		gov.setId(AuditStringUtils.getID(
				CommonConstant.STR_GOVERNMENTASSIGNPRIMARYKEY, id, 3));
		Integer count = ibatisCommonDAO
				.executeInsert("addGovermentAssign", gov);
		if (count == 0) {
			map.put("msg", PropertiesGetValue
					.getContextProperty("govermentAssign.add.fail"));
		} else {
			map.put("id", gov.getId());
			map.put("msg", PropertiesGetValue
					.getContextProperty("govermentAssign.add.success"));
		}
		return map;
	}

	/**
	 * 获取交办批次下的所有项目 2013-6-26
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#findAssignProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<ResultClassProConference> findAssignProject(
			Integer page, Integer pagesize, String filed, String order,
			String assignId) {
		/*
		 * ProGovernmentAssign gov = new ProGovernmentAssign();
		 * gov.setFiled(filed); gov.setSort(order); gov.setPageno(page);
		 * gov.setPagesize(pagesize); gov.setId(assignId);
		 * 
		 * // 获取该交办批次下的所有项目总数 Integer count =
		 * ibatisCommonDAO.executeForObject("getAssignProjectCount", assignId,
		 * Integer.class);
		 * 
		 * // 获取交办批次下面的所有项目信息 List<ArrangeProject> list =
		 * ibatisCommonDAO.executeForObjectList("getAssignProject", gov);
		 * 
		 * GridDataModel<ArrangeProject> arrangeProjects = new
		 * GridDataModel<ArrangeProject>();
		 * 
		 * arrangeProjects.setRows(list); arrangeProjects.setTotal(count);
		 * 
		 * return arrangeProjects;
		 */
		GridDataModel<ResultClassProConference> gm = null;
		try {
			ResultClassProConference conference = new ResultClassProConference();
			conference.setFiled(filed);
			conference.setSort(order);
			conference.setGovernmentAssignId(assignId);
			// 查询
			List<ResultClassProConference> list = ibatisCommonDAO
					.executeForObjectList("selectgovernmentAssignproinfo",
							conference, pagesize * (page - 1), pagesize);
			if (null != list && list.size() != 0) {
				for (ResultClassProConference pro : list) {

					if (AuditStringUtils.isNotEmpty(pro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService
								.getProjectOwner(pro.getProownerid());
						pro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(pro.getArrangeId())) {
						if (AuditStringUtils
								.isNotEmpty(pro.getIntermediaryId())) {
							Intermediaryagency in = iIntermediaryagencyService
									.getIntermediaryagency(pro
											.getIntermediaryId());
							if (null != in) {
								pro.setIntermediaryId(in.getReferred());
							}

						}
						if (AuditStringUtils.isNotEmpty(pro.getMainAuditId())) {
							EditUser edituser = userInfoService.findbyid(pro
									.getMainAuditId());
							pro.setMainAuditId(edituser.getUsername());
						}
						String arrangeId = pro.getArrangeId();
						// 查询政府雇员
						List<User> listuser = ibatisCommonDAO
								.executeForObjectList("selectbyanrrgebyemp",
										arrangeId);
						String governmentEmp = "";
						for (User user : listuser) {
							governmentEmp += user.getUsername() + ",";
						}
						if (AuditStringUtils.isNotEmpty(governmentEmp)) {
							governmentEmp = governmentEmp.substring(0,
									governmentEmp.length() - 1);
						}
						pro.setGovernmentEmp(governmentEmp);

					} else {
						String projectId = pro.getDatapreId();
						// 查询打包项目安排
						ResultClassProConference con = ibatisCommonDAO
								.executeForObject("selectbydatapreIdpackdata",
										projectId,
										ResultClassProConference.class);
						if (null != con) {
							if (AuditStringUtils.isNotEmpty(con
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(con
												.getIntermediaryId());
								pro.setIntermediaryId(in.getIntermediaryname());
							}
							if (AuditStringUtils.isNotEmpty(con
									.getMainAuditId())) {
								EditUser edituser = userInfoService
										.findbyid(con.getMainAuditId());
								pro.setMainAuditId(edituser.getUsername());
							}
							String arrangeId = con.getArrangeId();
							// 查询政府雇员
							List<User> listuser = ibatisCommonDAO
									.executeForObjectList(
											"selectbyanrrgebyemp", arrangeId);
							String governmentEmp = "";
							for (User user : listuser) {
								governmentEmp += user.getUsername() + ",";
							}
							if (AuditStringUtils.isNotEmpty(governmentEmp)) {
								governmentEmp = governmentEmp.substring(0,
										governmentEmp.length() - 1);
							}
							pro.setGovernmentEmp(governmentEmp);
						}

					}

				}
			}
			Integer count = ibatisCommonDAO
					.executeForObject("selectgovernmentAssignprocount",
							conference, Integer.class);
			gm = new GridDataModel<ResultClassProConference>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 查询交办下面的所有项目信息
	 * 
	 * @param assignId
	 * @return
	 */
	public List<ResultClassProConference> findAllAssignProject(String assignId) {
		List<ResultClassProConference> list = null;
		try {
			ResultClassProConference conference = new ResultClassProConference();
			conference.setGovernmentAssignId(assignId);
			// 查询
			list = ibatisCommonDAO.executeForObjectList(
					"selectgovernmentAssignproinfo", conference);
			if (null != list && list.size() != 0) {
				for (ResultClassProConference pro : list) {

					if (AuditStringUtils.isNotEmpty(pro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService
								.getProjectOwner(pro.getProownerid());
						pro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(pro.getArrangeId())) {
						if (AuditStringUtils
								.isNotEmpty(pro.getIntermediaryId())) {
							Intermediaryagency in = iIntermediaryagencyService
									.getIntermediaryagency(pro
											.getIntermediaryId());
							if (null != in) {
								pro.setIntermediaryId(in.getReferred());
							}

						}
						if (AuditStringUtils.isNotEmpty(pro.getMainAuditId())) {
							EditUser edituser = userInfoService.findbyid(pro
									.getMainAuditId());
							pro.setMainAuditId(edituser.getUsername());
						}
						String arrangeId = pro.getArrangeId();
						// 查询政府雇员
						List<User> listuser = ibatisCommonDAO
								.executeForObjectList("selectbyanrrgebyemp",
										arrangeId);
						String governmentEmp = "";
						for (User user : listuser) {
							governmentEmp += user.getUsername() + ",";
						}
						if (AuditStringUtils.isNotEmpty(governmentEmp)) {
							governmentEmp = governmentEmp.substring(0,
									governmentEmp.length() - 1);
						}
						pro.setGovernmentEmp(governmentEmp);

					} else {
						String projectId = pro.getDatapreId();
						// 查询打包项目安排
						ResultClassProConference con = ibatisCommonDAO
								.executeForObject("selectbydatapreIdpackdata",
										projectId,
										ResultClassProConference.class);
						if (null != con) {
							if (AuditStringUtils.isNotEmpty(con
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(con
												.getIntermediaryId());
								pro.setIntermediaryId(in.getIntermediaryname());
							}
							if (AuditStringUtils.isNotEmpty(con
									.getMainAuditId())) {
								EditUser edituser = userInfoService
										.findbyid(con.getMainAuditId());
								pro.setMainAuditId(edituser.getUsername());
							}
							String arrangeId = con.getArrangeId();
							// 查询政府雇员
							List<User> listuser = ibatisCommonDAO
									.executeForObjectList(
											"selectbyanrrgebyemp", arrangeId);
							String governmentEmp = "";
							for (User user : listuser) {
								governmentEmp += user.getUsername() + ",";
							}
							if (AuditStringUtils.isNotEmpty(governmentEmp)) {
								governmentEmp = governmentEmp.substring(0,
										governmentEmp.length() - 1);
							}
							pro.setGovernmentEmp(governmentEmp);
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 查询没有交办批次的项目信息
	 * 
	 * @param assignId
	 * @return
	 */
	public List<ResultClassProConference> findAllNoAssignProject() {
		List<ResultClassProConference> list = null;
		try {
			ResultClassProConference conference = new ResultClassProConference();
			// 查询
			list = ibatisCommonDAO.executeForObjectList("selectnogovernmentAssignproinfo", conference);
			if (null != list && list.size() != 0) {
				for (ResultClassProConference pro : list) {

					if (AuditStringUtils.isNotEmpty(pro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService
								.getProjectOwner(pro.getProownerid());
						pro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(pro.getArrangeId())) {
						if (AuditStringUtils
								.isNotEmpty(pro.getIntermediaryId())) {
							Intermediaryagency in = iIntermediaryagencyService
									.getIntermediaryagency(pro
											.getIntermediaryId());
							if (null != in) {
								pro.setIntermediaryId(in.getReferred());
							}

						}
						if (AuditStringUtils.isNotEmpty(pro.getMainAuditId())) {
							EditUser edituser = userInfoService.findbyid(pro
									.getMainAuditId());
							pro.setMainAuditId(edituser.getUsername());
						}
						String arrangeId = pro.getArrangeId();
						// 查询政府雇员
						List<User> listuser = ibatisCommonDAO
								.executeForObjectList("selectbyanrrgebyemp",
										arrangeId);
						String governmentEmp = "";
						for (User user : listuser) {
							governmentEmp += user.getUsername() + ",";
						}
						if (AuditStringUtils.isNotEmpty(governmentEmp)) {
							governmentEmp = governmentEmp.substring(0,
									governmentEmp.length() - 1);
						}
						pro.setGovernmentEmp(governmentEmp);

					} else {
						String projectId = pro.getDatapreId();
						// 查询打包项目安排
						ResultClassProConference con = ibatisCommonDAO
								.executeForObject("selectbydatapreIdpackdata",
										projectId,
										ResultClassProConference.class);
						if (null != con) {
							if (AuditStringUtils.isNotEmpty(con
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(con
												.getIntermediaryId());
								pro.setIntermediaryId(in.getIntermediaryname());
							}
							if (AuditStringUtils.isNotEmpty(con
									.getMainAuditId())) {
								EditUser edituser = userInfoService
										.findbyid(con.getMainAuditId());
								pro.setMainAuditId(edituser.getUsername());
							}
							String arrangeId = con.getArrangeId();
							// 查询政府雇员
							List<User> listuser = ibatisCommonDAO
									.executeForObjectList(
											"selectbyanrrgebyemp", arrangeId);
							String governmentEmp = "";
							for (User user : listuser) {
								governmentEmp += user.getUsername() + ",";
							}
							if (AuditStringUtils.isNotEmpty(governmentEmp)) {
								governmentEmp = governmentEmp.substring(0,
										governmentEmp.length() - 1);
							}
							pro.setGovernmentEmp(governmentEmp);
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 添加交办项目 2013-6-26
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#addAssignProject(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> addAssignProject(String assignProjectId,
			String assignId, String userAccount) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		String[] assignProjectIds = assignProjectId.split(",");
		Integer count = 0;
		for (int i = 0; i < assignProjectIds.length; i++) {
			// 加入交办项目信息关联
			ProGoverMentProject proGovernmentproject = new ProGoverMentProject();
			proGovernmentproject.setId(AuditStringUtils.getUUID());
			proGovernmentproject.setProjectId(assignProjectIds[i].toString());
			proGovernmentproject.setGovernmentAssignId(assignId);
			count = ibatisCommonDAO.executeInsert("addGovermentAssignProject",
					proGovernmentproject);
		}

		if (count == null || count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("govermentAssignProject.add.fail"));
		}

		map.put("msg", PropertiesGetValue
				.getContextProperty("govermentAssignProject.add.success"));
		return map;
	}

	/**
	 * 删除交办项目 2013-6-26
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#deleteAssignProject(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> deleteAssignProject(String assignProjectId,
			String assignId, String userAccount) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		// 删除交办项目信息关联
		ProGoverMentProject proGovernmentproject = new ProGoverMentProject();
		proGovernmentproject.setProjectId(assignProjectId);
		proGovernmentproject.setGovernmentAssignId(assignId);
		Integer count = ibatisCommonDAO.executeInsert(
				"delGovermentAssignProject", proGovernmentproject);
		if (count == null || count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("govermentAssignProject.del.fail"));
		}

		// 判断是否有交办文号
		ProGovernmentAssign proGovernmentAssign = ibatisCommonDAO
				.executeForObject("getProGovernmentAssignById", assignId,
						ProGovernmentAssign.class);
		if (AuditStringUtils.isNotEmpty(proGovernmentAssign.getAssignCode())) {
			// 返回到上一个流程
			iWorkFlowComponent
					.changeWorkFlow(
							assignProjectId,
							PropertiesGetValue
									.getContextProperty("Flow.govermentAssignProjectStateDel"),
							userAccount);
		}
		map.put("msg", PropertiesGetValue
				.getContextProperty("govermentAssignProject.del.success"));
		return map;
	}

	/**
	 * 交办批次信息 2013-6-26
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#getAssignInfo(java.lang.String)
	 */
	@Override
	public ProGovernmentAssign getAssignInfo(String assignId) {

		ProGovernmentAssign proGovernmentAssign = ibatisCommonDAO
				.executeForObject("getProGovernmentAssignById", assignId,
						ProGovernmentAssign.class);


		return proGovernmentAssign;
	}
	
	/**
	 * 查询所有的交办信息
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#getAssignInfo(java.lang.String)
	 */
	@Override
	public List<ProGovernmentAssign> findAllAssignInfo() {
		List<ProGovernmentAssign> list=null;
		list=ibatisCommonDAO.executeForObjectList("selectProGovernmentAllAssign","");
		return list;
	}

	/**
	 * 政府交办文号录入 2013-6-26
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#updateAssignCode(java.util.List,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> updateAssignCode(
			List<FileBelongRelate> listfile, String reportBatch,
			String reportTime, String assignCode, String assignTime, String id,
			String userAccount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 清空政府交办文件
		ibatisCommonDAO.executeDelete("deleteFile", id);

		// 加入文件
		if (listfile != null) {
			for (FileBelongRelate FileBelongRelate : listfile) {
				ibatisCommonDAO.executeInsert("addfile", FileBelongRelate);
			}
		}
		// 加入政府交办文号
		ProGovernmentAssign proGovernmentAssign = new ProGovernmentAssign();
		proGovernmentAssign.setId(id);
		proGovernmentAssign.setAssignCode(assignCode);
		proGovernmentAssign.setAssignTime(assignTime);
		proGovernmentAssign.setReportBatch(reportBatch);
	
		if (AuditStringUtils.isNotEmpty(reportTime)) {
			proGovernmentAssign.setReportTime(reportTime);
		}
		Integer count = ibatisCommonDAO.executeUpdate("updateAssignInfo",
				proGovernmentAssign);

		if (count == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("govermentAssign.add.fail"));
		}
		if (AuditStringUtils.isNotEmpty(assignCode)) {
			// 检索政府交办项目
			List<String> proejctIds = ibatisCommonDAO.executeForObjectList(
					"getAllAssignProject", id);

			// 跳转下一个流程
			for (String projectId : proejctIds) {

				Integer countCheck = ibatisCommonDAO.executeForObject(
						"checkIsMySelfAuditProject", projectId, Integer.class);
				if (countCheck != 0) {
					// 自审
					// 该条项目切换下一个流程
					iWorkFlowComponent
							.changeWorkFlow(
									projectId,
									PropertiesGetValue
											.getContextProperty("workFlow.state.governmentEmployeeAudit"),
									userAccount);
				} else {
					// 该条项目切换下一个流程
					iWorkFlowComponent
							.changeWorkFlow(
									projectId,
									PropertiesGetValue
											.getContextProperty("Flow.govermentAssignProjectStateAdd"),
									userAccount);
				}
			}
		}
		map.put("assignId", id);
		map.put("msg", PropertiesGetValue
				.getContextProperty("govermentAssign.add.success"));
		return map;
	}

	/**
	 * 检索已完成的交办项目 2013-6-27
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#findAssignCompleteProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findAssignCompleteProject(
			Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName) {

		ArrangeProject arrangeProject = new ArrangeProject();
		arrangeProject.setFiled(filed);
		arrangeProject.setSort(order);
		arrangeProject.setPageno(page);
		arrangeProject.setPagesize(pagesize);
		arrangeProject.setOwnerName(ownerName);
		arrangeProject.setProjectName(proejctName);

		// 已完成的交办项目总数
		Integer count = ibatisCommonDAO.executeForObject(
				"getCompleteProjectInfoCount", arrangeProject, Integer.class);

		// 已完成交办项目
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList(
				"getCompleteProjectInfo", arrangeProject);

		GridDataModel<ArrangeProject> ArrangeProjects = new GridDataModel<ArrangeProject>();

		ArrangeProjects.setRows(list);
		ArrangeProjects.setTotal(count);

		return ArrangeProjects;
	}

	/**
	 * 检索全部项目 2013-6-27
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#findAllAssignCompleteProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<ArrangeProject> findAllAssignCompleteProject(
			Integer page, Integer pagesize, String filed, String order,
			String ownerName, String proejctName, String userAccount) {

		ArrangeProject arrangeProject = new ArrangeProject();
		arrangeProject.setFiled(filed);
		arrangeProject.setSort(order);
		arrangeProject.setPageno(page);
		arrangeProject.setPagesize(pagesize);
		arrangeProject.setUserAccount(userAccount);
		arrangeProject.setOwnerName(ownerName);
		arrangeProject.setProjectName(proejctName);
		// 已完成的交办项目总数
		Integer count = ibatisCommonDAO.executeForObject(
				"getAllAssignProjectInfoCount", arrangeProject, Integer.class);

		// 已完成交办项目
		List<ArrangeProject> list = ibatisCommonDAO.executeForObjectList(
				"getAllAssignProjectInfo", arrangeProject);

		GridDataModel<ArrangeProject> ArrangeProjects = new GridDataModel<ArrangeProject>();

		ArrangeProjects.setRows(list);
		ArrangeProjects.setTotal(count);

		return ArrangeProjects;
	}

	/**
	 * 销毁交办信息 2013-6-27
	 * 
	 * @see com.audit.service.project.IGovernmentAssignService#destroy(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> destroy(String assignId, String userAccount)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断是否有交办文号
		ProGovernmentAssign proGovernmentAssign = ibatisCommonDAO
				.executeForObject("getProGovernmentAssignById", assignId,
						ProGovernmentAssign.class);

		// 删除交办信息
		Integer deleteCount = ibatisCommonDAO.executeDelete(
				"deleteGovermentAssignInfo", assignId);

		if (deleteCount == 0) {
			throw new AuditException(PropertiesGetValue
					.getContextProperty("govermentAssign.delete.fail"));
		}

		if (AuditStringUtils.isNotEmpty(proGovernmentAssign.getAssignCode())) {
			// 检索政府交办项目
			List<String> proejctIds = ibatisCommonDAO.executeForObjectList(
					"getAllAssignProject", assignId);

			// 跳转上一个流程
			for (String arrangeProject : proejctIds) {
				// 该条项目切换上一个流程
				iWorkFlowComponent
						.changeWorkFlow(
								arrangeProject,
								PropertiesGetValue
										.getContextProperty("Flow.govermentAssignProjectStateDel"),
								userAccount);
			}
		}

		// 删除交办项目
		ProGoverMentProject proGovernmentproject = new ProGoverMentProject();
		proGovernmentproject.setGovernmentAssignId(assignId);
		ibatisCommonDAO.executeInsert("delGovermentAssignProject",
				proGovernmentproject);

		map.put("msg", PropertiesGetValue
				.getContextProperty("govermentAssign.delete.success"));
		return map;
	}
}
