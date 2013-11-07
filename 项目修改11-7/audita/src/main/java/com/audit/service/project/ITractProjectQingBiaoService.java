/**
 * 
 */
package com.audit.service.project;

import java.util.List;

import com.audit.common.GridDataModel;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractProjectQingBiao;

/**
 * @author dengyong
 * 清标接口
 * 
 *
 */
public interface ITractProjectQingBiaoService {
	
	
	/**
	 * 查询所有项目信息标段信息
	 * @param proejctBiaoDuanInfo
	 * @return
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanBaseInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 查询检索条件的项目信息标段信息
	 * @param proejctBiaoDuanInfo
	 * @return
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	
	
	/**
	 * 根据标段id查询清标对象
	 * @param biaoduanId
	 * @return
	 */
	public TractProjectQingBiao findbybdid(String biaoduanId);
	
	
	/**
	 * 根据id查询清标对象
	 * @param biaoduanId
	 * @return
	 */
    public TractProjectQingBiao findbyid(String id);
	
	
	/**
	 * 添加清标
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(TractProjectQingBiao qb,List<FileBelongRelate>  listfile)throws Exception;
	
	/**
	 * 修改清标
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(TractProjectQingBiao qb,List<FileBelongRelate>  listfile)throws Exception;

}
