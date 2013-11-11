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
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractProjectQingBiao;
import com.audit.service.project.ITractProjectQingBiaoService;

/**
 * @author Administrator
 * 
 */
public class TractProjectQingBiaoServiceImpl implements ITractProjectQingBiaoService {

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
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectBiaoDuanqingbiaoAllCount",
				proejctBiaoDuanInfo, Integer.class);

		// 检索跟踪项目信息
		List<ProejctBiaoDuanInfo> list = ibatisCommonDAO.executeForObjectList("findTractProjectBiaoDuanqingbiao",
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
		Integer countTracProject = ibatisCommonDAO.executeForObject("getTractProjectAllqbByBiaoDuanInfoCount",
				proejctBiaoDuanInfo, Integer.class);

		// 获取跟踪审计项目信息
		List<ProejctBiaoDuanInfo> tracProjects = ibatisCommonDAO.executeForObjectList(
				"getTractProjectAllqbByBiaoDuanInfo", proejctBiaoDuanInfo);
		allList.addAll(tracProjects);
		for (ProejctBiaoDuanInfo tractProject : tracProjects) {
			proejctBiaoDuanInfo.setProjectId(tractProject.getId());
			List<ProejctBiaoDuanInfo> biaoDuans = ibatisCommonDAO.executeForObjectList(
					"findTractProjectAllqbBiaoDuanById", proejctBiaoDuanInfo);
			allList.addAll(biaoDuans);
		}
		GridDataModel<ProejctBiaoDuanInfo> proejctBiaoDuanInfoModel = new GridDataModel<ProejctBiaoDuanInfo>();
		proejctBiaoDuanInfoModel.setRows(allList);
		proejctBiaoDuanInfoModel.setTotal(countTracProject);

		return proejctBiaoDuanInfoModel;
	}

	/**
	 * 添加清标
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer add(TractProjectQingBiao qb, List<FileBelongRelate> listfile) throws Exception {
		Integer row = 0;
		// 执行增加清标
		row = ibatisCommonDAO.executeInsert("addqingbiao", qb);
		if (row > 0) {
			for (FileBelongRelate file : listfile) {
				file.setId(AuditStringUtils.getUUID());
				row = ibatisCommonDAO.executeInsert("addfile", file);
			}
			// 修改标段信息为已清标
			ibatisCommonDAO.executeUpdate("updatebiaoduanqb", qb.getBiaoDuanId());
		}
		return row;
	}

	/**
	 * 修改清标
	 * 
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer update(TractProjectQingBiao qb, List<FileBelongRelate> listfile) throws Exception {
		Integer row = 0;
		// 执行修改清标
		row = ibatisCommonDAO.executeUpdate("updateqingbiao", qb);
		if (row > 0) {
			for (FileBelongRelate file : listfile) {
				file.setId(AuditStringUtils.getUUID());
				row = ibatisCommonDAO.executeInsert("addfile", file);
			}
		}
		return row;
	}

	/**
	 * 根据标段id查询清标对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectQingBiao findbybdid(String biaoduanId) {
		TractProjectQingBiao qb = ibatisCommonDAO.executeForObject("selectbybdid", biaoduanId,
				TractProjectQingBiao.class);
		return qb;
	}

	/**
	 * 根据id查询清标对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectQingBiao findbyid(String id) {
		TractProjectQingBiao qb = ibatisCommonDAO.executeForObject("selectbyid", id, TractProjectQingBiao.class);
		return qb;
	}
}
