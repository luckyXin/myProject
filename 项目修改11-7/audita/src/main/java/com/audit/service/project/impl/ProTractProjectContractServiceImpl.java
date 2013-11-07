/**
 * 
 */
package com.audit.service.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractProjectContract;
import com.audit.service.project.IProTractProjectContractService;

/**
 * @author 合同审核
 * 
 */
public class ProTractProjectContractServiceImpl implements IProTractProjectContractService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 查询所有项目信息标段信息
	 * 
	 * @param proejctBiaoDuanInfo
	 * @return
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanBaseInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {
		// 检索跟踪项目总数
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectBiaoDuanhtauditAllCount",
				proejctBiaoDuanInfo, Integer.class);

		// 检索跟踪项目信息
		List<ProejctBiaoDuanInfo> list = ibatisCommonDAO.executeForObjectList("findTractProjectBiaoDuanhtaudit",
				proejctBiaoDuanInfo);
		GridDataModel<ProejctBiaoDuanInfo> baseInfo = new GridDataModel<ProejctBiaoDuanInfo>();
		baseInfo.setTotal(count);
		baseInfo.setRows(list);
		return baseInfo;
	}

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
		Integer countTracProject = ibatisCommonDAO.executeForObject("getTractProjectAllhtByBiaoDuanInfoCount",
				proejctBiaoDuanInfo, Integer.class);

		// 获取跟踪审计项目信息
		List<ProejctBiaoDuanInfo> tracProjects = ibatisCommonDAO.executeForObjectList(
				"getTractProjectAllhtByBiaoDuanInfo", proejctBiaoDuanInfo);
		allList.addAll(tracProjects);
		for (ProejctBiaoDuanInfo tractProject : tracProjects) {
			proejctBiaoDuanInfo.setProjectId(tractProject.getId());
			List<ProejctBiaoDuanInfo> biaoDuans = ibatisCommonDAO.executeForObjectList(
					"findTractProjectAllhtBiaoDuanById", proejctBiaoDuanInfo);
			allList.addAll(biaoDuans);
		}

		GridDataModel<ProejctBiaoDuanInfo> proejctBiaoDuanInfoModel = new GridDataModel<ProejctBiaoDuanInfo>();
		proejctBiaoDuanInfoModel.setRows(allList);
		proejctBiaoDuanInfoModel.setTotal(countTracProject);

		return proejctBiaoDuanInfoModel;
	}

	/**
	 * 根据标段id查询合同审核对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public List<TractProjectContract> findbybdid(String biaoduanId) {
		List<TractProjectContract> ht = ibatisCommonDAO.executeForObjectList("selecthtbybdid", biaoduanId);
		if (null != ht && ht.size() != 0) {
			return ht;
		} else {
			return null;
		}
	}

	/**
	 * 根据id查询合同审核对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectContract findbyid(String id) {
		TractProjectContract ht = ibatisCommonDAO.executeForObject("selecthtbyid", id, TractProjectContract.class);
		return ht;
	}

	/**
	 * 添加合同
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(List<TractProjectContract> htlist) throws Exception {
		Integer row = 0;
		if (null != htlist && htlist.size() > 0) {
			for (TractProjectContract ht : htlist) {
				// 执行增加合同
				row = ibatisCommonDAO.executeInsert("addhtaudit", ht);
			}
		}
		return row;
	}

	/**
	 * 修改合同
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<TractProjectContract> htlist) throws Exception {
		Integer row = 0;
		if (null != htlist && htlist.size() > 0) {
			for (TractProjectContract ht : htlist) {
				if (null == ht.getId() || !AuditStringUtils.isNotEmpty(ht.getId())) {
					ht.setId(AuditStringUtils.getUUID());
					// 执行增加合同
					row = ibatisCommonDAO.executeInsert("addhtaudit", ht);
				} else {
					// 执行修改合同
					row = ibatisCommonDAO.executeUpdate("updatehtaudit", ht);
				}
			}
		}

		return row;
	}

	/**
	 * 删除合同
	 * 
	 * @param biaoduanId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer delete(String biaoduanId, String sort) throws Exception {
		// 实例化合同审核对象
		TractProjectContract t = new TractProjectContract();
		// 设置参数
		t.setSort(sort);
		t.setBiaoDuanId(biaoduanId);
		// 执行删除
		Integer row = ibatisCommonDAO.executeDelete("deletehtaudit", t);
		return row;
	}
}
