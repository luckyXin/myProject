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
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.ITractProjectCreateService;

/**
 * @author Administrator
 * 
 */
public class TractProjectCreateServiceImpl implements ITractProjectCreateService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 查询内容 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#findSingleProject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public GridDataModel<TractAuditProjectInfo> findTractProject(Integer page, Integer pagesize, String filed,
			String order, String ownerName, String projectName, String userAccount, String submitState) {
		TractAuditProjectInfo tractAuditProjectInfo = new TractAuditProjectInfo();
		tractAuditProjectInfo.setCreateUserAccount(userAccount);
		tractAuditProjectInfo.setOwnerId(ownerName);
		tractAuditProjectInfo.setFiled(filed);
		tractAuditProjectInfo.setPagesize(pagesize);
		tractAuditProjectInfo.setPageno(page);
		tractAuditProjectInfo.setSort(order);
		tractAuditProjectInfo.setSubmitState(submitState);
		tractAuditProjectInfo.setProjectName(projectName);

		// 检索跟踪项目总数
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectAllCount", tractAuditProjectInfo,
				Integer.class);

		// 检索跟踪项目信息
		List<TractAuditProjectInfo> list = ibatisCommonDAO.executeForObjectList("findTractProject",
				tractAuditProjectInfo);
		GridDataModel<TractAuditProjectInfo> baseInfo = new GridDataModel<TractAuditProjectInfo>();
		baseInfo.setTotal(count);
		baseInfo.setRows(list);
		return baseInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#getTractAuditProjectInfoById(java.lang.String)
	 */
	@Override
	public TractAuditProjectInfo getTractAuditProjectInfoById(String id) {
		// 跟蹤審計項目獲取
		TractAuditProjectInfo tractAuditProjectInfo = ibatisCommonDAO.executeForObject("getTractProjectInfoById", id,
				TractAuditProjectInfo.class);
		return tractAuditProjectInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#addTractAuditProject(com.audit.entity.project.TractAuditProjectInfo,
	 *      java.util.List)
	 */
	@Override
	public Map<String, Object> addTractAuditProject(TractAuditProjectInfo tractAuditProjectInfo,
			List<FileBelongRelate> listfile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询跟踪项目最高ID STR_TRACTPROJECTPRIMARYKEY
		Integer id = ibatisCommonDAO.executeForObject("getTractProjectMaxId", null, Integer.class);
		if (id == null) {
			id = 0;
		}
		tractAuditProjectInfo.setId(AuditStringUtils.getID(CommonConstant.STR_TRACTPROJECTPRIMARYKEY, id, 3));

		// 添加跟踪审计项目
		Integer count = ibatisCommonDAO.executeInsert("insertTractProject", tractAuditProjectInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}

		// 文件插入数据库
		for (FileBelongRelate file : listfile) {
			file.setId(AuditStringUtils.getUUID());
			file.setBelongToId(tractAuditProjectInfo.getId());
			Integer fileCount = ibatisCommonDAO.executeInsert("addfile", file);
			if (fileCount == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
			}
		}
		map.put("isSuccess", tractAuditProjectInfo.getId());
		map.put("id", tractAuditProjectInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#updateTractAuditProject(com.audit.entity.project.TractAuditProjectInfo,
	 *      java.util.List)
	 */
	@Override
	public Map<String, Object> updateTractAuditProject(TractAuditProjectInfo tractAuditProjectInfo,
			List<FileBelongRelate> listfile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 添加跟踪审计项目
		Integer count = ibatisCommonDAO.executeInsert("updateTractProject", tractAuditProjectInfo);
		if (count == 0) {
			throw new AuditException();
		}

		// 文件插入数据库
		for (FileBelongRelate file : listfile) {
			file.setId(AuditStringUtils.getUUID());
			file.setBelongToId(tractAuditProjectInfo.getId());
			Integer fileCount = ibatisCommonDAO.executeInsert("addfile", file);
			if (fileCount == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.update.fail"));
			}
		}
		map.put("isSuccess", tractAuditProjectInfo.getId());
		map.put("id", tractAuditProjectInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));
		return map;
	}

	/**
	 * 跟踪项目检索 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#finProejctBiaoDuanInfo(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {

		// 检索跟踪项目总数
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectBiaoDuanAllCount", proejctBiaoDuanInfo,
				Integer.class);

		// 检索跟踪项目信息
		List<ProejctBiaoDuanInfo> list = ibatisCommonDAO.executeForObjectList("findTractProjectBiaoDuan",
				proejctBiaoDuanInfo);
		GridDataModel<ProejctBiaoDuanInfo> baseInfo = new GridDataModel<ProejctBiaoDuanInfo>();
		baseInfo.setTotal(count);
		baseInfo.setRows(list);
		return baseInfo;
	}

	/**
	 * 添加标段信息 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#addBiaoDuanInfo(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public Map<String, Object> addBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建ID
		proejctBiaoDuanInfo.setId(AuditStringUtils.getUUID());
		// 添加跟踪审计项目
		Integer count = ibatisCommonDAO.executeInsert("insertTractProjectBiaoDuan", proejctBiaoDuanInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}
		map.put("isSuccess", proejctBiaoDuanInfo.getId());
		map.put("id", proejctBiaoDuanInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		return map;
	}

	/**
	 * 更新标段信息 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#updateBiaoDuanInfo(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public Map<String, Object> updateBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 添加跟踪审计项目
		Integer count = ibatisCommonDAO.executeInsert("updateTractProjectBiaoDuan", proejctBiaoDuanInfo);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.update.fail"));
		}
		map.put("isSuccess", proejctBiaoDuanInfo.getId());
		map.put("id", proejctBiaoDuanInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.update.success"));
		return map;
	}

	/**
	 * 通过标段ID获取标段信息 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#getProejctBiaoDuanInfo(java.lang.String)
	 */
	@Override
	public ProejctBiaoDuanInfo getProejctBiaoDuanInfo(String id) {
		ProejctBiaoDuanInfo proejctBiaoDuanInfo = ibatisCommonDAO.executeForObject("getProejctBiaoDuanInfo", id,
				ProejctBiaoDuanInfo.class);
		if (proejctBiaoDuanInfo == null) {
			return new ProejctBiaoDuanInfo();
		}
		return proejctBiaoDuanInfo;
	}

	/**
	 * 删除标段信息 2013-7-24
	 * 
	 * @throws Exception
	 * @see com.audit.service.project.ITractProjectCreateService#deleteBiaoDuanInfo(java.lang.String)
	 */
	@Override
	public Map<String, Object> deleteBiaoDuanInfo(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断是否被安排
		ProejctBiaoDuanInfo proejctBiaoDuanInfo = ibatisCommonDAO.executeForObject("getProejctBiaoDuanInfo", id,
				ProejctBiaoDuanInfo.class);
		if (CommonConstant.PROJECT_ARRANGER_ON.equals(proejctBiaoDuanInfo.getIsArrange())) {
			throw new AuditException(PropertiesGetValue.getContextProperty("biaoDuan.already.arrange"));
		}
		// 删除标段
		Integer count = ibatisCommonDAO.executeDelete("deleteBiaoDuanInfoById", id);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.delete.fail"));
		}
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.delete.success"));
		return map;
	}

	/**
	 * 删除跟踪项目信息 2013-7-24
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#deleteTractAuditProject(java.lang.String)
	 */
	@Override
	public Map<String, Object> deleteTractAuditProject(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 判断所有的标段是否都未被安排
		List<String> biaoDuanIds = ibatisCommonDAO.executeForObjectList("getBiaoDuanIdsByProjectId", id);
		for (String biaoDuanId : biaoDuanIds) {
			// 判断是否被安排
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = ibatisCommonDAO.executeForObject("getProejctBiaoDuanInfo",
					biaoDuanId, ProejctBiaoDuanInfo.class);
			if (CommonConstant.PROJECT_ARRANGER_ON.equals(proejctBiaoDuanInfo.getIsArrange())) {
				throw new AuditException(PropertiesGetValue.getContextProperty("biaoDuan.already.arrange"));
			}
		}
		// 删除标段信息
		ibatisCommonDAO.executeDelete("deleteBiaoDuanByProjectId", id);

		// 清楚文件
		ibatisCommonDAO.executeDelete("deleteBelongToFile", id);

		// 删除跟踪项目信息
		Integer count = ibatisCommonDAO.executeDelete("deleteTractProjectById", id);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.delete.fail"));
		}
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.delete.success"));
		return map;
	}

	/**
	 * 判断项目名称是否存在 2013-7-25
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#checkProjectNameIsExist(java.lang.String)
	 */
	@Override
	public Map<String, Object> checkProjectNameIsExist(String projectName) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = ibatisCommonDAO.executeForObject("checkProjectNameIsExist", projectName, Integer.class);
		if (count == 0) {
			map.put("success", "1");
		}
		return map;
	}

	/**
	 * 判断标段名称是否存在 2013-8-29
	 * 
	 * @see com.audit.service.project.ITractProjectCreateService#checkBiaoDuanNameIsExist(java.lang.String)
	 */
	@Override
	public Map<String, Object> checkBiaoDuanNameIsExist(String biaoDuanName) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = ibatisCommonDAO.executeForObject("checkBiaoDuanNameIsExist", biaoDuanName, Integer.class);
		if (count == 0) {
			map.put("success", "1");
		}
		return map;
	}
}
