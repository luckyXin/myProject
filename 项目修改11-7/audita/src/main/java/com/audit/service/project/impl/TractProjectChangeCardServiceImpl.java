/**
 * 
 */
package com.audit.service.project.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.ITractProjectChangeCardService;

/**
 * @author Administrator
 * 
 */
public class TractProjectChangeCardServiceImpl implements ITractProjectChangeCardService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectChangeCardService#findfile(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      com.audit.entity.project.FileBelongRelate)
	 */
	@Override
	public GridDataModel<FileBelongRelate> findfile(FileBelongRelate fileBelongRelate) {
		// 检索月报信息总数
		Integer count = ibatisCommonDAO.executeForObject("findChangeCardFileCount", fileBelongRelate, Integer.class);

		List<FileBelongRelate> list = ibatisCommonDAO.executeForObjectList("findChangeCardFile", fileBelongRelate,
				fileBelongRelate.getPagesize() * (fileBelongRelate.getPageno() - 1), fileBelongRelate.getPagesize());

		GridDataModel<FileBelongRelate> tractMonthReportInfos = new GridDataModel<FileBelongRelate>();
		tractMonthReportInfos.setRows(list);
		tractMonthReportInfos.setTotal(count);
		return tractMonthReportInfos;
	}

	/**
	 * 查询变更签证信息 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectChangeCardService#getTractProjectChangeCardInfo(java.lang.String)
	 */
	@Override
	public TractProjectChangeCardInfo getTractProjectChangeCardInfo(String id) {
		TractProjectChangeCardInfo tractProjectChangeCardInfo = new TractProjectChangeCardInfo();
		if (AuditStringUtils.isNotEmpty(id)) {
			tractProjectChangeCardInfo = ibatisCommonDAO.executeForObject("getTractProjectChangeCardInfo", id,
					TractProjectChangeCardInfo.class);
			if (tractProjectChangeCardInfo == null) {
				return new TractProjectChangeCardInfo();
			}
		}
		return tractProjectChangeCardInfo;
	}

	/**
	 * 变更签证编辑 2013-7-27
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.ITractProjectChangeCardService#editTractProjectChangeCardInfo(com.audit.entity.project.TractProjectChangeCardInfo)
	 */
	@Override
	public Map<String, Object> editTractProjectChangeCardInfo(TractProjectChangeCardInfo tractProjectChangeCardInfo)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = 0;
		if (!AuditStringUtils.isNotEmpty(tractProjectChangeCardInfo.getId())) {
			tractProjectChangeCardInfo.setId(AuditStringUtils.getUUID());
			// 添加月报信息
			count = ibatisCommonDAO.executeInsert("insertTractProjectChangeCode", tractProjectChangeCardInfo);
		} else {
			// 更新月报信息
			count = ibatisCommonDAO.executeInsert("updateTractProjectChangeCode", tractProjectChangeCardInfo);
		}
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}
		map.put("isSuccess", tractProjectChangeCardInfo.getId());
		map.put("id", tractProjectChangeCardInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		return map;
	}

	/**
	 * 变更签证查询 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectChangeCardService#findTractProjectChangeCardInfo(com.audit.entity.project.TractProjectChangeCardInfo)
	 */
	@Override
	public GridDataModel<TractProjectChangeCardInfo> findTractProjectChangeCardInfo(
			TractProjectChangeCardInfo tractProjectChangeCardInfo) {

		// 查询变更签证总数
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectChangeCardCount", tractProjectChangeCardInfo,
				Integer.class);

		// 查询变更签证信息
		List<TractProjectChangeCardInfo> list = ibatisCommonDAO.executeForObjectList("findTractProjectChangeCard",
				tractProjectChangeCardInfo);
		GridDataModel<TractProjectChangeCardInfo> baseInfo = new GridDataModel<TractProjectChangeCardInfo>();
		baseInfo.setTotal(count);
		baseInfo.setRows(list);
		return baseInfo;
	}

	/**
	 * 删除变更签证 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectChangeCardService#deleteTractProjectChangeCard(java.lang.String)
	 */
	@Override
	public Map<String, Object> deleteTractProjectChangeCard(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = ibatisCommonDAO.executeDelete("deleteTractChangeCard", id);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.delete.fail"));
		}
		map.put("msg", "删除成功");
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectChangeCardService#toLoadExcel(java.lang.String,
	 *      java.lang.String, java.util.List, java.lang.String, java.util.List)
	 */
	@Override
	public Map<String, Object> toLoadExcel(String userAccount, String createTime, List<MultipartFile> monthReportFile,
			String biaoDuanId, List<FileBelongRelate> listfile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TractProjectChangeCardInfo> tractProjectChangeCardInfo = new ArrayList<TractProjectChangeCardInfo>();
		InputStream fs = monthReportFile.get(0).getInputStream();
		Workbook book = Workbook.getWorkbook(fs);
		// 获得第一个工作表对象
		Sheet sheet = book.getSheet(0);
		// 得到单元格
		for (int j = 2; j < sheet.getRows(); j++) {
			TractProjectChangeCardInfo tractMonthReportInfo = new TractProjectChangeCardInfo();
			for (int i = 1; i < 10; i++) {
				Cell cell = sheet.getCell(i, j);
				switch (i) {
				case 1:
					tractMonthReportInfo.setChangeCode(cell.getContents());
					break;
				case 2:
					tractMonthReportInfo.setChangeTime(cell.getContents());
					break;
				case 3:
					tractMonthReportInfo.setChangeContext(cell.getContents());
					break;
				case 4:
					tractMonthReportInfo.setConstructSentMoney(cell.getContents());
					break;
				case 5:
					tractMonthReportInfo.setTractAuditAdviceMoney(cell.getContents());
					break;
				case 6:
					tractMonthReportInfo.setAffirmChangeMoney(cell.getContents());
					break;
				case 7:
					tractMonthReportInfo.setChangeProccessCondition(cell.getContents());
					break;
				case 8:
					tractMonthReportInfo.setChangeAndNowSiteInfo(cell.getContents());
					break;
				case 9:
					tractMonthReportInfo.setChangeType(getChangeType(cell.getContents()));
					break;
				}
			}
			tractProjectChangeCardInfo.add(tractMonthReportInfo);
		}
		book.close();

		for (TractProjectChangeCardInfo tractMonthReportInfo : tractProjectChangeCardInfo) {
			tractMonthReportInfo.setId(AuditStringUtils.getUUID());
			tractMonthReportInfo.setCreateUserAccount(userAccount);
			tractMonthReportInfo.setUpdateTime(createTime);
			tractMonthReportInfo.setBiaoDuanId(biaoDuanId);
			if (AuditStringUtils.isNotEmpty(tractMonthReportInfo.getChangeCode())) {
				Integer count = ibatisCommonDAO.executeInsert("insertTractProjectChangeCode", tractMonthReportInfo);
				if (count == 0) {
					throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
				}
			}
		}

		// 文件插入数据库
		for (FileBelongRelate file : listfile) {
			file.setId(AuditStringUtils.getUUID());
			file.setBelongToId(biaoDuanId);
			Integer fileCount = ibatisCommonDAO.executeInsert("addChangeCardfile", file);
			if (fileCount == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.update.fail"));
			}
		}

		map.put("isSuccess", 1);
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		return map;
	}

	/**
	 * 获取审计类型对应的键值
	 * 
	 * @param changeTypeContext
	 * @return
	 */
	private String getChangeType(String changeTypeContext) {
		if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE0)) {
			return CommonConstant.CHANGECARDTYPE_KEY0;
		} else if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE1)) {
			return CommonConstant.CHANGECARDTYPE_KEY1;
		} else if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE2)) {
			return CommonConstant.CHANGECARDTYPE_KEY2;
		} else if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE3)) {
			return CommonConstant.CHANGECARDTYPE_KEY3;
		} else if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE4)) {
			return CommonConstant.CHANGECARDTYPE_KEY4;
		} else if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE5)) {
			return CommonConstant.CHANGECARDTYPE_KEY5;
		} else if (AuditStringUtils.equals(changeTypeContext, CommonConstant.CHANGECARDTYPE_VALUE6)) {
			return CommonConstant.CHANGECARDTYPE_KEY6;
		} else {
			return AuditStringUtils.EMPTY;
		}
	}

}
