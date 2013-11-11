/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.TractDataTurnList;
import com.audit.entity.project.TractProjectDataTransferInfo;

/**
 * @author Administrator
 * 
 */
public interface ITractProjectDataTransferService {

	/**
	 * 资料交接添加
	 * 
	 * @param file
	 * @param createTime
	 * @param biaoDuanId
	 * @return
	 */
	public Map<String, Object> dataTransfer(FileBelongRelate file, String createTime, String biaoDuanId, String userAccount, String id, List<FileBelongRelate> files) throws Exception;

	/**
	 * 资料移交信息获取
	 * 
	 * @param id
	 * @return
	 */
	public TractProjectDataTransferInfo getTractProjectDataTransferInfo(String id);
	
	
	/**
	 * 增加资料移交信息
	 * @param list
	 * @return
	 */
	public Integer addDataTurn(List<TractDataTurnList> list) throws Exception;
	
	
	/**
	 * 删除资料移交信息
	 * @param list
	 * @return
	 */
	public Integer delDataTurn(String id) throws Exception;
	
	
	/**
	 * 根据标段id查询资料移交信息
	 * @param id
	 * @return
	 */
	public List<TractDataTurnList> findDataTurn(String id);
}
