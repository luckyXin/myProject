/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.audit.common.GridDataModel;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractMonthReportInfo;

/**
 * @author Administrator
 * 
 */
public interface ITractProjectMonthReportService {

	/**
	 * 导入月报基础数据
	 * 
	 * @param userAccount
	 * @param createTime
	 * @param monthReportFile
	 * @return
	 */
	public Map<String, Object> toLoadExcel(String userAccount, String createTime, List<MultipartFile> monthReportFile, String biaoDuanId, List<FileBelongRelate> listfile) throws Exception;

	/**
	 * 月报信息查询
	 * 
	 * @param tractMonthReportInfo
	 * @return
	 */
	public GridDataModel<TractMonthReportInfo> findProejctMonthReportInfo(TractMonthReportInfo tractMonthReportInfo);

	/**
	 * 月报附件
	 * 
	 * @param biaoDuanId
	 * @return
	 */
	public GridDataModel<FileBelongRelate> findMonthReportFile(FileBelongRelate fileBelongRelate);

	/**
	 * 获取月报信息
	 */
	public TractMonthReportInfo getTractMonthReportInfo(String id);

	/**
	 * 添加月报信息
	 */
	public Map<String, Object> addTractMonthReportInfo(TractMonthReportInfo tractMonthReportInfo) throws Exception;

	/**
	 * 删除月报信息
	 */
	public Map<String, Object> delTractMonthReportInfo(String id) throws Exception;
	
	/**
	 * 指定项目下的标段
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 指定项目下的标段
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo1(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 查询所有项目以及下面的子项目
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfoAll(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 查询所有项目以及下面的子项目
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfoAll1(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 获取累计完成产值
	 * @param biaoDuanId
	 * @param nowMonthCompleteValue
	 * @return
	 */
	public Map<String, Object> getTotalCompleteValue(String biaoDuanId, String nowMonthCompleteValue);
	
	/**
	 * 获取累计支付产值
	 * @param biaoDuanId
	 * @param nowMonthCompleteValue
	 * @return
	 */
	public Map<String, Object> getAddPayProjectMoney(String biaoDuanId, String nowMonthPayValue);
}
