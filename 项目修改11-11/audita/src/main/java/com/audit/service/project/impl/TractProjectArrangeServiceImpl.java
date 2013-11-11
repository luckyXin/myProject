/**
 * 
 */
package com.audit.service.project.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.DifferenceListString;
import com.audit.entity.project.Employee;
import com.audit.entity.project.EmployeeArrangeRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.entity.project.TractPolicyChange;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.entity.project.TractProjectQingBiao;
import com.audit.exception.AuditException;
import com.audit.service.project.ITractProjectArrangeService;

/**
 * @author Administrator
 * 
 */
public class TractProjectArrangeServiceImpl implements ITractProjectArrangeService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 查询跟踪审计项目以及以下的标段 2013-7-25
	 * 
	 * @see com.audit.service.project.ITractProjectArrangeService#finProejctBiaoDuanInfo(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {

		// 所有跟踪审计项目集合
		List<ProejctBiaoDuanInfo> allList = new ArrayList<ProejctBiaoDuanInfo>();

		// 获取跟踪审计项目总数
		Integer countTracProject = ibatisCommonDAO.executeForObject("getTractProjectAllByBiaoDuanInfoCount",
				proejctBiaoDuanInfo, Integer.class);

		// 获取跟踪审计项目信息
		List<ProejctBiaoDuanInfo> tracProjects = ibatisCommonDAO.executeForObjectList(
				"getTractProjectAllByBiaoDuanInfo", proejctBiaoDuanInfo);
		allList.addAll(tracProjects);
		for (ProejctBiaoDuanInfo tractProject : tracProjects) {
			proejctBiaoDuanInfo.setProjectId(tractProject.getId());
			List<ProejctBiaoDuanInfo> biaoDuans = ibatisCommonDAO.executeForObjectList(
					"findTractProjectAllBiaoDuanById", proejctBiaoDuanInfo);
			for (ProejctBiaoDuanInfo biaoDuanInfo : biaoDuans) {
				// 获取单项目安排关联的政府雇员
				List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList("getAllEmployeeNameByArrangeId",
						biaoDuanInfo.getArrangeId());
				biaoDuanInfo.setReexamineNames(AuditStringUtils.addString(govenmentEmployees));
			}
			allList.addAll(biaoDuans);
		}

		GridDataModel<ProejctBiaoDuanInfo> proejctBiaoDuanInfoModel = new GridDataModel<ProejctBiaoDuanInfo>();
		proejctBiaoDuanInfoModel.setRows(allList);
		proejctBiaoDuanInfoModel.setTotal(countTracProject);

		return proejctBiaoDuanInfoModel;
	}

	/**
	 * 标段安排信息获取 2013-7-25
	 * 
	 * @see com.audit.service.project.ITractProjectArrangeService#getTractArrangeProjectInfo(java.lang.String)
	 */
	@Override
	public TractArrangeProjectInfo getTractArrangeProjectInfo(String biaoDuanId) {
		// 获取标段安排信息
		TractArrangeProjectInfo tractArrangeProjectInfo = ibatisCommonDAO.executeForObject(
				"selectTractArrangeProjectInfo", biaoDuanId, TractArrangeProjectInfo.class);
		if (tractArrangeProjectInfo == null) {
			return new TractArrangeProjectInfo();
		} else {
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
		return tractArrangeProjectInfo;
	}

	/**
	 * 跟踪项目安排信息添加 2013-7-25
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.ITractProjectArrangeService#addTractArrangeProjectInfo(com.audit.entity.project.TractArrangeProjectInfo)
	 */
	@Override
	public Map<String, Object> addTractArrangeProjectInfo(TractArrangeProjectInfo tractArrangeProjectInfo)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 安排ID
		Integer id = ibatisCommonDAO.executeForObject("getTractProjectArrangeMaxId", null, Integer.class);
		if (id == null) {
			id = 0;
		}
		tractArrangeProjectInfo.setId(AuditStringUtils.getID(CommonConstant.STR_TRACTPROJECTARRANGEPRIMARYKEY, id, 3));
		// 添加跟踪审计项目
		Integer count = ibatisCommonDAO.executeInsert("insertTractProjectArrange", tractArrangeProjectInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}

		// 添加复审人员关联信息
		for (String governmentEmployee : tractArrangeProjectInfo.getGovernmentEmployeeIds()) {

			EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
			employeeArrangeRelate.setId(AuditStringUtils.getUUID());
			employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
			employeeArrangeRelate.setProjectArrangeId(tractArrangeProjectInfo.getId());
			ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
		}

		// 改变标段安排状态
		count = ibatisCommonDAO.executeUpdate("updateBiaoDuanArrangeState", tractArrangeProjectInfo.getBiaoDuanId());
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}

		map.put("isSuccess", tractArrangeProjectInfo.getId());
		map.put("id", tractArrangeProjectInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.service.project.ITractProjectArrangeService#updateTractArrangeProjectInfo(com.audit.entity.project.TractArrangeProjectInfo)
	 */
	@Override
	public Map<String, Object> updateTractArrangeProjectInfo(TractArrangeProjectInfo tractArrangeProjectInfo)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 添加跟踪审计项目
		Integer count = ibatisCommonDAO.executeInsert("updateTractProjectArrange", tractArrangeProjectInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}

		// 政府工程师获取
		List<Employee> govenmentEmployeeList = ibatisCommonDAO.executeForObjectList(
				"getGovenmentEmployeeByProejctArrangeId", tractArrangeProjectInfo.getId());
		List<String> govenmentEmployeeId = new ArrayList<String>();
		for (Employee employee : govenmentEmployeeList) {
			govenmentEmployeeId.add(employee.getId());
		}

		// 获取差分后的政府工程师ID
		DifferenceListString DifGovenmentEmployeeId = AuditStringUtils.compareListString(govenmentEmployeeId,
				tractArrangeProjectInfo.getGovernmentEmployeeIds());

		// 变更后，追加的政府工程师审批关联
		for (String governmentEmployee : DifGovenmentEmployeeId.getAddString()) {

			EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
			employeeArrangeRelate.setId(AuditStringUtils.getUUID());
			employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
			employeeArrangeRelate.setProjectArrangeId(tractArrangeProjectInfo.getId());
			ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
		}

		// 变更后，删除的政府工程师审批关联
		for (String governmentEmployee : DifGovenmentEmployeeId.getDeleteString()) {
			EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
			employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
			employeeArrangeRelate.setProjectArrangeId(tractArrangeProjectInfo.getId());
			ibatisCommonDAO.executeDelete("deleteEmployeeArrangeRelete", employeeArrangeRelate);
		}
		map.put("isSuccess", tractArrangeProjectInfo.getId());
		map.put("id", tractArrangeProjectInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));
		return map;
	}

	/**
	 * 根据用户id查询拥有的标段信息
	 * 
	 * @param id
	 * @return
	 */
	public List<TractArrangeProjectInfo> findBdByUserid(String id) {
		List<TractArrangeProjectInfo> list = null;
		list = ibatisCommonDAO.executeForObjectList("selectbiaoduanbyuserid", id);
		return list;
	}

	/**
	 * 根据标段id查询所有的月份
	 * 
	 * @param list
	 * @return
	 */
	public List<String> findMonthByBdid(List<TractArrangeProjectInfo> list) {
		List<String> liststr = null;
		if (null != list && list.size() != 0) {
			liststr = new ArrayList<String>();
			for (TractArrangeProjectInfo t : list) {
				List<TractMonthReportInfo> listmonth = ibatisCommonDAO.executeForObjectList("selectmonthbybdid",
						t.getBiaoDuanId());
				if (null != listmonth && listmonth.size() != 0) {
					for (TractMonthReportInfo tm : listmonth) {
						liststr.add(tm.getCreateTime());
					}
				}
			}
		}
		return liststr;
	}

	/**
	 * 根据年月信息查询对应标段信息
	 * 
	 * @param id
	 * @return
	 */
	public List<TractMonthReportInfo> findBiaoDuanByTime(String id) {
		List<TractMonthReportInfo> list = null;
		list = ibatisCommonDAO.executeForObjectList("selectbiaoduanbytime", id);
		return list;
	}

	/**
	 * 根据项目id查询项目业主
	 * 
	 * @param id
	 * @return
	 */
	public ProejctBiaoDuanInfo findOwnerbyBdid(String id) {
		ProejctBiaoDuanInfo pro = ibatisCommonDAO.executeForObject("selectProjectAuditbyId", id,
				ProejctBiaoDuanInfo.class);

		return pro;
	}

	/**
	 * 根据标段id和时间查询当月最新标段月报信息
	 * 
	 * @param id
	 * @param time
	 * @return
	 */
	public TractMonthReportInfo findMonthReportInfoByBdtime(String id, String time) {
		TractMonthReportInfo month = null;
		TractMonthReportInfo parametermonth = new TractMonthReportInfo();
		parametermonth.setBiaoDuanId(id);
		parametermonth.setCreateTime(time);
		month = ibatisCommonDAO.executeForObject("selectmonthinfobybdtime", parametermonth, TractMonthReportInfo.class);

		return month;
	}

	/**
	 * 查询变更总金额
	 * 
	 * @param id
	 * @param time
	 * @param type
	 * @return
	 */
	public TractProjectChangeCardInfo findProjectChangeCardTotalMoney(String id, String time, String type) {
		TractProjectChangeCardInfo parme = new TractProjectChangeCardInfo();
		// 设置参数
		parme.setBiaoDuanId(id);
		parme.setChangeTime(time);
		parme.setChangeType(type);
		TractProjectChangeCardInfo tractProjectChange = null;
		tractProjectChange = ibatisCommonDAO.executeForObject("selectTractChangeCardInfoById", parme,
				TractProjectChangeCardInfo.class);
		return tractProjectChange;

	}

	/**
	 * 查询政策性调整的人工价和材料价
	 * 
	 * @param id
	 * @param time
	 * @return
	 */
	public TractPolicyChange findTractPolicyChange(String id, String time) {
		TractPolicyChange tractPolicy = null;
		TractPolicyChange t = new TractPolicyChange();
		t.setBiaoDuanId(id);
		t.setCreateTime(time);
		tractPolicy = ibatisCommonDAO.executeForObject("selectTractPolicyChangeInfo", t, TractPolicyChange.class);
		return tractPolicy;
	}

	/**
	 * 根据标段查询清标后的金额
	 * 
	 * @param id
	 * @return
	 */
	public TractProjectQingBiao findQingBiaoMoney(String id) {
		TractProjectQingBiao qb = null;
		qb = ibatisCommonDAO.executeForObject("selectqingbiaomoneybybdid", id, TractProjectQingBiao.class);
		return qb;

	}

}
