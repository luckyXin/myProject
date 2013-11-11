/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.audit.common.GridDataModel;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.TractProjectChangeCardInfo;

/**
 * @author Administrator
 * 
 */
public interface ITractProjectChangeCardService {

	/**
	 * 分页查询变更签证对应的资料文件
	 * 
	 * @param page
	 *            当前页数
	 * @param pagesize
	 *            每页条数
	 * @param filed
	 *            排序字段
	 * @param order
	 *            排序方式
	 * @param file
	 *            资料对象
	 * @return
	 */
	public GridDataModel<FileBelongRelate> findfile(FileBelongRelate fileBelongRelate);

	/**
	 * 分页查询变更签证信息
	 * 
	 * @param tractProjectChangeCardInfo
	 * @return
	 */
	public GridDataModel<TractProjectChangeCardInfo> findTractProjectChangeCardInfo(TractProjectChangeCardInfo tractProjectChangeCardInfo);

	/**
	 * 获取跟踪项目变更签证信息
	 * 
	 * @param id
	 * @return
	 */
	public TractProjectChangeCardInfo getTractProjectChangeCardInfo(String id);

	/**
	 * 变更签证编辑
	 */
	public Map<String, Object> editTractProjectChangeCardInfo(TractProjectChangeCardInfo tractProjectChangeCardInfo) throws Exception;

	/**
	 * 删除变更签证
	 */
	public Map<String, Object> deleteTractProjectChangeCard(String id) throws Exception;
	
	/**
	 * 导入月报基础数据
	 * 
	 * @param userAccount
	 * @param createTime
	 * @param monthReportFile
	 * @return
	 */
	public Map<String, Object> toLoadExcel(String userAccount, String createTime, List<MultipartFile> monthReportFile, String biaoDuanId, List<FileBelongRelate> listfile) throws Exception;

}
