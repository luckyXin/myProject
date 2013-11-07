/**
 * 
 */
package com.audit.service.project.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractDataTurnList;
import com.audit.entity.project.TractProjectDataTransferInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.project.ITractProjectDataTransferService;

/**
 * @author Administrator
 * 
 */
public class TractProjectDataTransferServiceImpl implements ITractProjectDataTransferService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IProjectDatePreService projectDatePreService;

	/**
	 * 资料交接 2013-7-28
	 * 
	 * @see com.audit.service.project.ITractProjectDataTransferService#dataTransfer(com.audit.entity.project.FileBelongRelate,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> dataTransfer(FileBelongRelate file, String createTime, String biaoDuanId,
			String userAccount, String id, List<FileBelongRelate> files) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TractProjectDataTransferInfo tractProjectDataTransferInfo = new TractProjectDataTransferInfo();
		tractProjectDataTransferInfo.setComprehensiveReportFile(file.getUrl());
		tractProjectDataTransferInfo.setCreateTime(createTime);
		tractProjectDataTransferInfo.setTractProjectId(biaoDuanId);
		tractProjectDataTransferInfo.setUpdateUserAccount(userAccount);
		tractProjectDataTransferInfo.setCreateUserAccount(userAccount);
		if (AuditStringUtils.isNotEmpty(id)) {
			tractProjectDataTransferInfo.setId(id);
			Integer count = ibatisCommonDAO.executeUpdate("updateDataTransferInfo", tractProjectDataTransferInfo);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.update.fail"));
			}
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));

		} else {
			tractProjectDataTransferInfo.setId(AuditStringUtils.getUUID());
			Integer count = ibatisCommonDAO.executeInsert("insertDataTransferInfo", tractProjectDataTransferInfo);
			if (count == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
			}
			map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
			// 修改标段移交状态
			Integer countTransfer = ibatisCommonDAO.executeUpdate("updateBiaoDuanTransferState", biaoDuanId);
			if (countTransfer == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
			}
			// 跟踪审计标段信息补录入资料预审信息中
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = ibatisCommonDAO.executeForObject("getProejctBiaoDuanInfo",
					biaoDuanId, ProejctBiaoDuanInfo.class);
			// 实例化资料预审对象
			Datapreinfo data = new Datapreinfo();
			data.setProjectName(proejctBiaoDuanInfo.getBiaoDuanName());
			ProejctBiaoDuanInfo pro = ibatisCommonDAO.executeForObject("selectProjectAuditbyId",
					proejctBiaoDuanInfo.getProjectId(), ProejctBiaoDuanInfo.class);
			data.setProownerid(pro.getOwnerId());
			data.setConstructId(proejctBiaoDuanInfo.getConstructUtil());
			data.setIsconfirmSubmit(0);
			// 设置审计类型为跟踪审计
			data.setAudittype(PropertiesGetValue.getContextProperty("gengaudittype"));
			// 设置是否安排
			data.setIsArrangement(0);
			data.setState(0);
			data.setPid("0");
			projectDatePreService.add(data, null, null);
		}

		for (FileBelongRelate otherFile : files) {
			otherFile.setId(AuditStringUtils.getUUID());
			otherFile.setBelongToId(tractProjectDataTransferInfo.getId());
			Integer row = ibatisCommonDAO.executeInsert("addfile", otherFile);
			if (row == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
			}
		}
		map.put("isSuccess", 1);
		return map;
	}

	/**
	 * 查询资料移交信息 2013-7-28
	 * 
	 * @see com.audit.service.project.ITractProjectDataTransferService#getTractProjectDataTransferInfo(java.lang.String)
	 */
	@Override
	public TractProjectDataTransferInfo getTractProjectDataTransferInfo(String id) {
		if (AuditStringUtils.isNotEmpty(id)) {
			TractProjectDataTransferInfo tractProjectDataTransferInfo = ibatisCommonDAO.executeForObject(
					"selectDataTransferInfo", id, TractProjectDataTransferInfo.class);
			if (tractProjectDataTransferInfo == null) {
				return new TractProjectDataTransferInfo();
			}
			return tractProjectDataTransferInfo;
		} else {
			return new TractProjectDataTransferInfo();
		}
	}

	/**
	 * 增加资料移交信息
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer addDataTurn(List<TractDataTurnList> list) throws Exception {
		Integer row = 0;
		if (null != list && list.size() != 0) {
			// 删除所有
			ibatisCommonDAO.executeInsert("delDataTurn", list.get(0).getBiaoDuanId());
			for (TractDataTurnList data : list) {
				row = ibatisCommonDAO.executeInsert("addDataTurn", data);
			}
		}
		return row;
	}

	/**
	 * 删除资料移交信息
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public Integer delDataTurn(String id) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("delDataTurn", id);
		return row;
	}

	/**
	 * 根据标段id查询资料移交信息
	 * 
	 * @param id
	 * @return
	 */
	public List<TractDataTurnList> findDataTurn(String id) {

		List<TractDataTurnList> list = ibatisCommonDAO.executeForObjectList("selectDataTurn", id);
		return list;
	}

}
