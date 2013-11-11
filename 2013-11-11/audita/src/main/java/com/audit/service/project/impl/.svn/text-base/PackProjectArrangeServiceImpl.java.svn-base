/**
 * 打包项目安排业务处理
 */
package com.audit.service.project.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.EmployeeArrangeRelate;
import com.audit.entity.project.PackProjectArrange;
import com.audit.service.project.IPackProjectArrangeService;

/**
 * @author DengXin
 */
public class PackProjectArrangeServiceImpl implements IPackProjectArrangeService {
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 打包项目信息查询 2013-6-23
	 */
	@Override
	public GridDataModel<PackProjectArrange> findPackProject(Integer page, Integer pagesize, String filed,
			String order, String ownerName, String proejctName, String userAccount) {

		PackProjectArrange packProjectArrange = new PackProjectArrange();
		packProjectArrange.setFiled(filed);
		packProjectArrange.setPageno(page);
		packProjectArrange.setPagesize(pagesize);
		packProjectArrange.setSort(order);
		packProjectArrange.setOwnerName(ownerName);
		packProjectArrange.setProjectPackName(proejctName);
		packProjectArrange.setUserAccount(userAccount);

		// 获取总数
		Integer count = ibatisCommonDAO.executeForObject("findPackProjectArrangeAllCount", packProjectArrange,
				Integer.class);

		// 检索包名信息
		List<PackProjectArrange> list = ibatisCommonDAO.executeForObjectList("findPackProjectArrang",
				packProjectArrange);

		GridDataModel<PackProjectArrange> packProjectArranges = new GridDataModel<PackProjectArrange>();
		packProjectArranges.setRows(list);
		packProjectArranges.setTotal(count);
		return packProjectArranges;
	}

	/**
	 * 根据资料id获取项目安排信息
	 * 
	 * @param datapreId
	 * @return
	 */
	@Override
	public PackProjectArrange getDataPreId(String datapreId) {
		PackProjectArrange pack = ibatisCommonDAO.executeForObject("selectpackProjectdatapreId", datapreId,
				PackProjectArrange.class);
		return pack;
	}

	/**
	 * 创建项目包 2013-6-21
	 * 
	 * @see com.audit.service.project.IPackProjectArrangeService#add(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String[], java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> add(String projectPackName, String ownerId, String sentAmount,
			String projectArrangeTime, String projectArrangeRemark, String mainAuditId, String intermediaryId,
			String[] governmentEmployees, String intermediarySendTime, String intermediaryAuditTime, String state,
			String userAccount) throws Exception {

		PackProjectArrange pack = new PackProjectArrange();

		// 创建包，插入基本信息
		ibatisCommonDAO.executeInsert("addProjectPack", pack);

		// 添加政府雇员关联
		for (String governmentEmployee : governmentEmployees) {

			EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
			employeeArrangeRelate.setId(AuditStringUtils.getUUID());
			employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
			employeeArrangeRelate.setProjectArrangeId(pack.getId());
			ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
		}

		return null;
	}

}
