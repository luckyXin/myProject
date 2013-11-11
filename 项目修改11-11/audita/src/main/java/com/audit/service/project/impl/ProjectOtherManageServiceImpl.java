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
import com.audit.entity.Dictionary;
import com.audit.entity.project.ProTractMonthFileInfo;
import com.audit.entity.project.TractClaimIndemnityContext;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.entity.project.TractOtherManage;
import com.audit.entity.project.TractPolicyChange;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.exception.AuditException;
import com.audit.service.project.IProjectOtherManageService;
import com.audit.service.system.IDictionaryService;

/**
 * @author dengyong 其它管理实现层
 * 
 */
public class ProjectOtherManageServiceImpl implements IProjectOtherManageService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IDictionaryService dictionaryService;

	/**
	 * 分页查询其它管理信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name 排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<TractOtherManage> findbybd(Integer page, Integer pagesize, String name, String order,
			String biaoduanid) {
		// 定义其它管理对象
		TractOtherManage data = new TractOtherManage();
		// 定义其它管理返回
		GridDataModel<TractOtherManage> gm = null;
		try {
			// 设置参数
			data.setFiled(name);
			data.setSort(order);
			data.setBiaoDuanId(biaoduanid);
			// 分页查询其它管理数据
			List<TractOtherManage> list = ibatisCommonDAO.executeForObjectList("selectallothermanagepage", data,
					pagesize * (page - 1), pagesize);
			// 查询其它管理总条数
			Integer count = ibatisCommonDAO.executeForObject("selectallothermanagecount", data, Integer.class);
			gm = new GridDataModel<TractOtherManage>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 增加其它管理信息
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer add(TractOtherManage data) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("addothermanage", data);
		return row;
	}

	/**
	 * 修改其它管理信息
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer update(TractOtherManage data) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeInsert("updateothermanage", data);
		return row;
	}

	/**
	 * 根据标段id查询其它管理对象
	 * 
	 * @param biaoduanId
	 * @return
	 */
	@Override
	public TractOtherManage findbybdid(String biaoduanId) {
		TractOtherManage data = null;

		data = ibatisCommonDAO.executeForObject("selecteothermanagebybdid", biaoduanId, TractOtherManage.class);
		return data;
	}

	/**
	 * 根据id查询其它管理
	 * 
	 * @param biaoduanId
	 * @return
	 */
	@Override
	public TractOtherManage findbyid(String id) {
		TractOtherManage data = null;
		data = ibatisCommonDAO.executeForObject("selecteothermanagebyid", id, TractOtherManage.class);
		return data;
	}

	/**
	 * 删除其它管理
	 * 
	 * @param biaoduanId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer delete(String id) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeDelete("deleothermanage", id);
		return row;
	}

	/**
	 * 导入月报基础数据
	 * 
	 * @param userAccount
	 * @param createTime
	 * @param monthReportFile
	 * @return
	 */
	public Map<String, Object> toLoadExcel(String biaoDuanId, String userAccount, String createTime,
			ProTractMonthFileInfo month, List<MultipartFile> monthReportFile) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TractProjectChangeCardInfo> tractProjectChangeCardInfo = new ArrayList<TractProjectChangeCardInfo>();
		List<TractMonthReportInfo> tractMonthReportInfos = new ArrayList<TractMonthReportInfo>();
		// 定义索赔集合
		List<TractClaimIndemnityContext> tractClaimIndemnityContextlist = new ArrayList<TractClaimIndemnityContext>();
		// 定义政策性调整集合
		List<TractPolicyChange> tractPolicyChangelist = new ArrayList<TractPolicyChange>();
		InputStream fs = monthReportFile.get(0).getInputStream();
		Workbook book = Workbook.getWorkbook(fs);

		// 添加模板文件对象
		Integer row = ibatisCommonDAO.executeInsert("addTractMonthFile", month);
		if (row > 0) {

			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到单元格
			for (int j = 3; j < sheet.getRows(); j++) {
				TractMonthReportInfo tractMonthReportInfo = new TractMonthReportInfo();
				for (int i = 1; i < 8; i++) {
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

					case 5:
						tractMonthReportInfo.setAddPayProjectMoney(cell.getContents());
						break;

					case 6:
						tractMonthReportInfo.setProjectImagePlan(cell.getContents());
						break;

					case 7:
						tractMonthReportInfo.setExistProblem(cell.getContents());
						break;
					}
				}
				tractMonthReportInfos.add(tractMonthReportInfo);
			}
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

			// 获得第二个工作表对象
			sheet = book.getSheet(1);
			// 得到单元格
			for (int j = 3; j < sheet.getRows(); j++) {
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

			// 获得第三个工作表对象
			sheet = book.getSheet(2);
			// 得到单元格
			for (int j = 3; j < sheet.getRows(); j++) {
				TractClaimIndemnityContext claimIndemnityContext = new TractClaimIndemnityContext();
				for (int i = 1; i < 10; i++) {
					Cell cell = sheet.getCell(i, j);
					switch (i) {
					case 1:
						claimIndemnityContext.setConstructSentMoney(cell.getContents());
						break;
					case 2:
						claimIndemnityContext.setIndemnityMoney(cell.getContents());
						break;
					case 3:
						claimIndemnityContext.setAuditMoney(cell.getContents());
						break;
					case 4:
						claimIndemnityContext.setOwnerReadyMoney(cell.getContents());
						break;
					case 5:
						claimIndemnityContext.setClaimitem(cell.getContents());
						break;
					case 6:
						claimIndemnityContext.setCaseexplain(cell.getContents());
						break;
					case 7:
						claimIndemnityContext.setAuditdetail(cell.getContents());
						break;
					case 8:
						claimIndemnityContext.setCreateTime(cell.getContents());
						break;
					case 9:
						claimIndemnityContext.setClaimIndemnityType(getClaimIndemnityType(cell.getContents()));
						break;
					}
				}
				// 添加到索赔集合中
				tractClaimIndemnityContextlist.add(claimIndemnityContext);
			}
			for (TractClaimIndemnityContext claim : tractClaimIndemnityContextlist) {
				claim.setId(AuditStringUtils.getUUID());
				claim.setCreateUserAccount(userAccount);
				claim.setBiaoDuanId(biaoDuanId);
				Integer count = ibatisCommonDAO.executeInsert("addsuopei", claim);
				if (count == 0) {
					throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
				}

			}

			// 获得第四个工作表对象
			sheet = book.getSheet(3);
			// 得到单元格
			for (int j = 3; j < sheet.getRows(); j++) {
				TractPolicyChange policyChange = new TractPolicyChange();
				for (int i = 1; i < 10; i++) {
					Cell cell = sheet.getCell(i, j);
					switch (i) {
					case 1:
						policyChange.setArtificialfile(cell.getContents());
						break;
					case 2:
						policyChange.setDatamoney(cell.getContents());
						break;
					case 3:
						policyChange.setMachinemoney(cell.getContents());
						break;
					case 4:
						policyChange.setOtherContext(cell.getContents());
						break;
					case 5:
						policyChange.setRemark(cell.getContents());
						break;
					case 6:
						policyChange.setCreateTime(cell.getContents());
						break;
					}
				}
				// 添加到政策性调整集合中
				tractPolicyChangelist.add(policyChange);
			}

			for (TractPolicyChange policy : tractPolicyChangelist) {
				policy.setId(AuditStringUtils.getUUID());
				policy.setCreateUserAccount(userAccount);
				policy.setBiaoDuanId(biaoDuanId);
				Integer count = ibatisCommonDAO.executeInsert("addzhengce", policy);
				if (count == 0) {
					throw new AuditException(PropertiesGetValue.getContextProperty("daoHang.add.fail"));
				}

			}
			// 关闭
			book.close();
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

	/**
	 * 获取索赔类型对应的键值
	 * 
	 * @param changeTypeContext
	 * @return
	 */
	private String getClaimIndemnityType(String claimIndemnityType) {
		if (AuditStringUtils.isNotEmpty(claimIndemnityType)) {
			Dictionary dic = dictionaryService.findbyName(claimIndemnityType);
			if (null != dic) {
				return dic.getId();
			} else {
				return "";
			}

		} else {
			return "";
		}
	}

	/**
	 * 分页查询导入文件信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name 排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<ProTractMonthFileInfo> findTemple(Integer page, Integer pagesize, String name, String order,
			String biaoDuanId) {
		ProTractMonthFileInfo monthfile = new ProTractMonthFileInfo();
		GridDataModel<ProTractMonthFileInfo> gm = null;
		try {
			monthfile.setFiled(name);
			monthfile.setSort(order);
			monthfile.setBiaoDuanId(biaoDuanId);
			List<ProTractMonthFileInfo> list = ibatisCommonDAO.executeForObjectList("findMonthFileInfopage", monthfile,
					pagesize * (page - 1), pagesize);
			Integer count = ibatisCommonDAO.executeForObject("findMonthFileInfocount", monthfile, Integer.class);
			gm = new GridDataModel<ProTractMonthFileInfo>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

}
