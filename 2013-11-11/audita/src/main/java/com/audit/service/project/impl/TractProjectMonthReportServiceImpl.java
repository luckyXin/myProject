/**
 * 
 */
package com.audit.service.project.impl;

import java.io.InputStream;
import java.math.BigDecimal;
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
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.ITractProjectMonthReportService;

/**
 * @author Administrator
 * 
 */
public class TractProjectMonthReportServiceImpl implements ITractProjectMonthReportService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 导入数据 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#toLoadExcel(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	@Override
	public Map<String, Object> toLoadExcel(String userAccount, String createTime, List<MultipartFile> monthReportFile,
			String biaoDuanId, List<FileBelongRelate> listfile) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<TractMonthReportInfo> tractMonthReportInfos = new ArrayList<TractMonthReportInfo>();
		InputStream fs = null;
		// 能够获取导入文件的情况
		if (monthReportFile.size() != 0) {
			fs = monthReportFile.get(0).getInputStream();
			Workbook book = Workbook.getWorkbook(fs);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);

			// 得到单元格
			for (int j = 2; j < sheet.getRows(); j++) {
				TractMonthReportInfo tractMonthReportInfo = new TractMonthReportInfo();
				for (int i = 1; i < 5; i++) {
					Cell cell = sheet.getCell(i, j);
					switch (i) {
					case 1:
						tractMonthReportInfo.setCreateTime(cell.getContents());
						break;
					case 2:
						tractMonthReportInfo.setNowMonthCompleteValue(cell.getContents());
						break;
					case 3:
						tractMonthReportInfo.setTotalCompleteValue(cell.getContents());
						break;
					case 4:
						tractMonthReportInfo.setNowMonthPayValue(cell.getContents());
						break;
					}
				}
				tractMonthReportInfos.add(tractMonthReportInfo);
			}
			book.close();

			// 添加月报数据
			for (TractMonthReportInfo tractMonthReport : tractMonthReportInfos) {
				tractMonthReport.setId(AuditStringUtils.getUUID());
				tractMonthReport.setCreateUserAccount(userAccount);
				tractMonthReport.setUpdateTime(createTime);
				tractMonthReport.setBiaoDuanId(biaoDuanId);
				if (AuditStringUtils.isNotEmpty(tractMonthReport.getCreateTime())) {
					Integer count = ibatisCommonDAO.executeInsert("insertTractMonthReport", tractMonthReport);
					if (count == 0) {
						throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
					}
				}
			}
		}

		// 文件插入数据库
		for (FileBelongRelate file : listfile) {
			file.setId(AuditStringUtils.getUUID());
			file.setBelongToId(biaoDuanId);
			Integer fileCount = ibatisCommonDAO.executeInsert("addMonthfile", file);
			if (fileCount == 0) {
				throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.update.fail"));
			}
		}

		map.put("isSuccess", 1);
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		return map;
	}

	/**
	 * 月报信息查询 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#finProejctBiaoDuanInfo(com.audit.entity.project.TractMonthReportInfo)
	 */
	@Override
	public GridDataModel<TractMonthReportInfo> findProejctMonthReportInfo(TractMonthReportInfo tractMonthReportInfo) {

		// 检索月报信息总数
		Integer count = ibatisCommonDAO.executeForObject("findProejctMonthReportInfoCount", tractMonthReportInfo,
				Integer.class);

		// 检索月报信息
		List<TractMonthReportInfo> list = ibatisCommonDAO.executeForObjectList("findProejctMonthReportInfo",
				tractMonthReportInfo);

		GridDataModel<TractMonthReportInfo> tractMonthReportInfos = new GridDataModel<TractMonthReportInfo>();
		tractMonthReportInfos.setRows(list);
		tractMonthReportInfos.setTotal(count);
		return tractMonthReportInfos;
	}

	/**
	 * 月报附件查询 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#findMonthReportFile(java.lang.String)
	 */
	@Override
	public GridDataModel<FileBelongRelate> findMonthReportFile(FileBelongRelate fileBelongRelate) {

		// 检索月报信息总数
		Integer count = ibatisCommonDAO.executeForObject("findMonthReportFileCount", fileBelongRelate, Integer.class);

		List<FileBelongRelate> list = ibatisCommonDAO.executeForObjectList("findMonthReportFile", fileBelongRelate,
				fileBelongRelate.getPagesize() * (fileBelongRelate.getPageno() - 1), fileBelongRelate.getPagesize());

		GridDataModel<FileBelongRelate> tractMonthReportInfos = new GridDataModel<FileBelongRelate>();
		tractMonthReportInfos.setRows(list);
		tractMonthReportInfos.setTotal(count);
		return tractMonthReportInfos;
	}

	/**
	 * 获取月报信息 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#getTractMonthReportInfo(java.lang.String)
	 */
	@Override
	public TractMonthReportInfo getTractMonthReportInfo(String id) {
		if (!AuditStringUtils.isNotEmpty(id)) {
			return new TractMonthReportInfo();
		}
		TractMonthReportInfo tractMonthReportInfo = ibatisCommonDAO.executeForObject("getTractMonthReportInfoById", id,
				TractMonthReportInfo.class);
		if (tractMonthReportInfo == null) {
			return new TractMonthReportInfo();
		}
		return tractMonthReportInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @throws Exception
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#addTractMonthReportInfo(com.audit.entity.project.TractMonthReportInfo)
	 */
	@Override
	public Map<String, Object> addTractMonthReportInfo(TractMonthReportInfo tractMonthReportInfo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = 0;

		if (!AuditStringUtils.isNotEmpty(tractMonthReportInfo.getId())) {
			tractMonthReportInfo.setId(AuditStringUtils.getUUID());
			// 添加月报信息
			count = ibatisCommonDAO.executeInsert("insertTractMonthReport", tractMonthReportInfo);
		} else {
			// 更新月报信息
			count = ibatisCommonDAO.executeInsert("updateTractMonthReport", tractMonthReportInfo);
		}
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
		}
		map.put("isSuccess", tractMonthReportInfo.getId());
		map.put("id", tractMonthReportInfo.getId());
		map.put("msg", PropertiesGetValue.getContextProperty("daoHang.add.success"));
		return map;
	}

	/**
	 * 删除月报信息 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#delTractMonthReportInfo(java.lang.String)
	 */
	@Override
	public Map<String, Object> delTractMonthReportInfo(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = ibatisCommonDAO.executeDelete("deleteTractMonthReport", id);
		if (count == 0) {
			throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.delete.fail"));
		}
		map.put("msg", "删除成功");
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#finProejctBiaoDuanInfo(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {

		// 检索跟踪项目总数
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectCanInputMonthReportBiaoDuanAllCount",
				proejctBiaoDuanInfo, Integer.class);

		// 检索跟踪项目信息
		List<ProejctBiaoDuanInfo> list = ibatisCommonDAO.executeForObjectList(
				"findTractProjectCanInputMonthReportBiaoDuanAll", proejctBiaoDuanInfo);
		GridDataModel<ProejctBiaoDuanInfo> baseInfo = new GridDataModel<ProejctBiaoDuanInfo>();
		baseInfo.setTotal(count);
		baseInfo.setRows(list);
		return baseInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-26
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#finProejctBiaoDuanInfo(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo1(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {

		// 检索跟踪项目总数
		Integer count = ibatisCommonDAO.executeForObject("findTractProjectCanInputMonthReportBiaoDuanAllCounts",
				proejctBiaoDuanInfo, Integer.class);

		// 检索跟踪项目信息
		List<ProejctBiaoDuanInfo> list = ibatisCommonDAO.executeForObjectList(
				"findTractProjectCanInputMonthReportBiaoDuanAlls", proejctBiaoDuanInfo);
		GridDataModel<ProejctBiaoDuanInfo> baseInfo = new GridDataModel<ProejctBiaoDuanInfo>();
		baseInfo.setTotal(count);
		baseInfo.setRows(list);
		return baseInfo;
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#finProejctBiaoDuanInfoAll(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfoAll(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {
		// 所有跟踪审计项目集合
		List<ProejctBiaoDuanInfo> allList = new ArrayList<ProejctBiaoDuanInfo>();

		// 获取跟踪审计项目总数
		Integer countTracProject = ibatisCommonDAO.executeForObject("getProjectCanInputMonthReportCount",
				proejctBiaoDuanInfo, Integer.class);

		// 获取跟踪审计项目信息
		List<ProejctBiaoDuanInfo> tracProjects = ibatisCommonDAO.executeForObjectList("getProjectCanInputMonthReport",
				proejctBiaoDuanInfo);
		allList.addAll(tracProjects);
		for (ProejctBiaoDuanInfo tractProject : tracProjects) {
			proejctBiaoDuanInfo.setProjectId(tractProject.getId());
			List<ProejctBiaoDuanInfo> biaoDuans = ibatisCommonDAO.executeForObjectList(
					"findCanInputMonthReportAllBiaoDuanById", proejctBiaoDuanInfo);
			allList.addAll(biaoDuans);
		}

		GridDataModel<ProejctBiaoDuanInfo> proejctBiaoDuanInfoModel = new GridDataModel<ProejctBiaoDuanInfo>();
		proejctBiaoDuanInfoModel.setRows(allList);
		proejctBiaoDuanInfoModel.setTotal(countTracProject);

		return proejctBiaoDuanInfoModel;
	}

	/**
	 * (non-Javadoc) 2013-7-27
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#finProejctBiaoDuanInfoAll(com.audit.entity.project.ProejctBiaoDuanInfo)
	 */
	@Override
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfoAll1(ProejctBiaoDuanInfo proejctBiaoDuanInfo) {
		// 所有跟踪审计项目集合
		List<ProejctBiaoDuanInfo> allList = new ArrayList<ProejctBiaoDuanInfo>();

		// 获取跟踪审计项目总数
		Integer countTracProject = ibatisCommonDAO.executeForObject("getProjectCanInputMonthReportCounts",
				proejctBiaoDuanInfo, Integer.class);

		// 获取跟踪审计项目信息
		List<ProejctBiaoDuanInfo> tracProjects = ibatisCommonDAO.executeForObjectList("getProjectCanInputMonthReports",
				proejctBiaoDuanInfo);
		allList.addAll(tracProjects);
		for (ProejctBiaoDuanInfo tractProject : tracProjects) {
			proejctBiaoDuanInfo.setProjectId(tractProject.getId());
			List<ProejctBiaoDuanInfo> biaoDuans = ibatisCommonDAO.executeForObjectList(
					"findCanInputMonthReportAllBiaoDuanByIds", proejctBiaoDuanInfo);
			allList.addAll(biaoDuans);
		}

		GridDataModel<ProejctBiaoDuanInfo> proejctBiaoDuanInfoModel = new GridDataModel<ProejctBiaoDuanInfo>();
		proejctBiaoDuanInfoModel.setRows(allList);
		proejctBiaoDuanInfoModel.setTotal(countTracProject);

		return proejctBiaoDuanInfoModel;
	}

	/**
	 * 获取累计完成产值 2013-9-9
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#getTotalCompleteValue(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> getTotalCompleteValue(String biaoDuanId, String nowMonthCompleteValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前累计产值
		String totalCompleteValue = ibatisCommonDAO.executeForObject("getTotalCompleteValue", biaoDuanId, String.class);
		if (!AuditStringUtils.isNotEmpty(totalCompleteValue)) {
			totalCompleteValue = "0";
		}
		if (!AuditStringUtils.isNotEmpty(nowMonthCompleteValue)) {
			nowMonthCompleteValue = "0";
		}
		BigDecimal b1 = new BigDecimal(totalCompleteValue);
		BigDecimal b2 = new BigDecimal(nowMonthCompleteValue);
		totalCompleteValue = b1.add(b2).toString();
		map.put("totalCompleteValue", totalCompleteValue);
		return map;
	}

	/**
	 * 获取累计支付产值 2013-9-9
	 * 
	 * @see com.audit.service.project.ITractProjectMonthReportService#getAddPayProjectMoney(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Map<String, Object> getAddPayProjectMoney(String biaoDuanId, String nowMonthPayValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		TractMonthReportInfo tractMonthReportInfo = new TractMonthReportInfo();
		tractMonthReportInfo.setBiaoDuanId(biaoDuanId);
		tractMonthReportInfo.setNowMonthCompleteValue(nowMonthPayValue);
		// 获取当前累计产值
		String addPayProjectMoney = ibatisCommonDAO.executeForObject("getAddPayProjectMoney", tractMonthReportInfo,
				String.class);
		if (!AuditStringUtils.isNotEmpty(addPayProjectMoney)) {
			addPayProjectMoney = "0";
		}
		if (!AuditStringUtils.isNotEmpty(nowMonthPayValue)) {
			nowMonthPayValue = "0";
		}
		BigDecimal b1 = new BigDecimal(addPayProjectMoney);
		BigDecimal b2 = new BigDecimal(nowMonthPayValue);
		addPayProjectMoney = b1.add(b2).toString();
		map.put("addPayProjectMoney", addPayProjectMoney);
		return map;
	}
}
