/**
 * 工作信息获取
 */
package com.audit.service.work.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.work.MyCompleteWorkInfo;
import com.audit.entity.work.MyNoCompleteWorkInfo;
import com.audit.entity.work.WorkInfo;
import com.audit.service.work.IMyWorkService;

/**
 * @author DengXin
 */
public class MyWorkServiceImpl implements IMyWorkService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO = null;

	private final static String strs = "M014, M015, M016";

	/**
	 * 获取未完成的工作 2013-6-20
	 * 
	 * @see com.audit.service.work.IMyWorkService#getMyNoCompleteWork(java.lang.String)
	 */
	@Override
	public GridDataModel<MyNoCompleteWorkInfo> getMyNoCompleteWork(Integer page, Integer pagesize, String filed,
			String order, String userAccount) {

		MyNoCompleteWorkInfo myNoCompleteWorkInfo = new MyNoCompleteWorkInfo();
		myNoCompleteWorkInfo.setFiled(filed);
		myNoCompleteWorkInfo.setPageno(page);
		myNoCompleteWorkInfo.setPagesize(pagesize);
		myNoCompleteWorkInfo.setSort(order);
		myNoCompleteWorkInfo.setUserAccount(userAccount);
		Integer count = ibatisCommonDAO.executeForObject("getNoCompeleteWorkAllCount", userAccount, Integer.class);

		List<MyNoCompleteWorkInfo> list = ibatisCommonDAO.executeForObjectList("getNoCompeleteWork",
				myNoCompleteWorkInfo);

		for (MyNoCompleteWorkInfo myNoCompleteWorkInfos : list) {
			String projectName = AuditStringUtils.EMPTY;

			// 业务数据ID是预审资料ID的场合
			projectName = ibatisCommonDAO.executeForObject("selectDatapreinfoName",
					myNoCompleteWorkInfos.getProjectId(), String.class);
			if (AuditStringUtils.isNotEmpty(projectName)) {

				myNoCompleteWorkInfos.setProjectName(projectName);
				continue;
			}
			// 业务数据ID是单项目信息ID的场合
			projectName = ibatisCommonDAO.executeForObject("selectSingleProjectName",
					myNoCompleteWorkInfos.getProjectId(), String.class);
			if (AuditStringUtils.isNotEmpty(projectName)) {

				myNoCompleteWorkInfos.setProjectName(projectName);
				continue;
			}

			// 业务数据ID是打包项目信息ID的场合
			projectName = ibatisCommonDAO.executeForObject("selectPackProjectArrangeName",
					myNoCompleteWorkInfos.getProjectId(), String.class);
			if (AuditStringUtils.isNotEmpty(projectName)) {

				myNoCompleteWorkInfos.setProjectName(projectName);
				continue;
			}
		}

		GridDataModel<MyNoCompleteWorkInfo> myNoCompleteWorkInfos = new GridDataModel<MyNoCompleteWorkInfo>();
		myNoCompleteWorkInfos.setRows(list);
		myNoCompleteWorkInfos.setTotal(count);

		return myNoCompleteWorkInfos;
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.service.work.IMyWorkService#getMyCompleteWork(java.lang.String)
	 */
	@Override
	public GridDataModel<MyCompleteWorkInfo> getMyCompleteWork(Integer page, Integer pagesize, String filed,
			String order, String userAccount) {
		return null;
	}

	/**
	 * 获取未完成的工作 2013-6-20
	 * 
	 * @see com.audit.service.work.IMyWorkService#getMyNoCompleteWork(java.lang.String)
	 */
	@Override
	public List<WorkInfo> getMyNoCompleteWork(String userAccount) {
		List<WorkInfo> allWorkInfo = new ArrayList<WorkInfo>();

		List<WorkInfo> workInfos = ibatisCommonDAO.executeForObjectList("selectWorkNoCompleteSimple", userAccount);

		// 预审资料，项目安排，政府交办的场合
		for (WorkInfo workInfo : workInfos) {
			if (strs.contains(workInfo.getModuleId())) {
				allWorkInfo.add(workInfo);
			}
		}

		// 获取安排以外的工作任务
		List<WorkInfo> workInfosOther = ibatisCommonDAO.executeForObjectList("selectWorkNoCompleteSimpleByArrange",
				userAccount);

		// 预审资料，项目安排，政府交办的场合
		for (WorkInfo workInfo : workInfosOther) {
			if (!strs.contains(workInfo.getModuleId())) {
				allWorkInfo.add(workInfo);
			}
		}

		return allWorkInfo;
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.service.work.IMyWorkService#getMyCompleteWork(java.lang.String)
	 */
	@Override
	public List<WorkInfo> getMyCompleteWork(String userAccount) {

		List<WorkInfo> allWorkInfo = new ArrayList<WorkInfo>();

		List<WorkInfo> workInfos = ibatisCommonDAO.executeForObjectList("selectWorkCompleteSimple", userAccount);

		// 预审资料，项目安排，政府交办的场合
		for (WorkInfo workInfo : workInfos) {
			if (strs.contains(workInfo.getModuleId())) {
				allWorkInfo.add(workInfo);
			}
		}

		// 获取安排以外的工作任务
		List<WorkInfo> workInfosOther = ibatisCommonDAO.executeForObjectList("selectWorkCompleteSimpleByArrange",
				userAccount);

		// 预审资料，项目安排，政府交办的场合
		for (WorkInfo workInfo : workInfosOther) {
			if (!strs.contains(workInfo.getModuleId())) {
				allWorkInfo.add(workInfo);
			}
		}

		return allWorkInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-16
	 * @throws ParseException 
	 * 
	 * @see com.audit.service.work.IMyWorkService#getThreeDayNoProccess(java.lang.String)
	 */
	@Override
	public Map<String, Object> getThreeDayNoProccess(String userAccount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		int count = 0;
		// 获取当前时间
		String date = AuditStringUtils.getSystem(AuditStringUtils.DATE_YYYYMMMDD);

		// 获取所有未完成的项目
		List<MyNoCompleteWorkInfo> list = ibatisCommonDAO.executeForObjectList("getAllNoCompleteWork", userAccount);

		// 获取超过三天的项目总数
		for (MyNoCompleteWorkInfo myNoCompleteWorkInfo : list) {
			if (myNoCompleteWorkInfo.getStartTime() != null) {
				long day = AuditStringUtils.twoDateSubtract(date, myNoCompleteWorkInfo.getStartTime());
				if (day > 3) {
					count++;
				}
			}
		}
		// 如果有一个以上的项目三天未处理，发出提示
		if (count != 0) {
			map.put("msg", count);
		}
		return map;
	}
}
