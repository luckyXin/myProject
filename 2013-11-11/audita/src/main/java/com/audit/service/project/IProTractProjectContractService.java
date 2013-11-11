/**
 * 
 */
package com.audit.service.project;


import java.util.List;

import com.audit.common.GridDataModel;

import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractProjectContract;


/**
 * @author 合同审核接口
 *
 */
public interface IProTractProjectContractService {
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
	 * 根据标段id查询合同审核对象
	 * @param biaoduanId
	 * @return
	 */
	public List<TractProjectContract> findbybdid(String biaoduanId);
	
	
	/**
	 * 根据id查询合同审核对象
	 * @param biaoduanId
	 * @return
	 */
    public TractProjectContract findbyid(String id);
	
	
	/**
	 * 添加合同
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer add(List<TractProjectContract> htlist)throws Exception;
	
	/**
	 * 修改合同
	 * @param qb
	 * @return
	 * @throws Exception
	 */
	public Integer update(List<TractProjectContract> htlist)throws Exception;
	
	
	/**
	 * 删除合同
	 * @param biaoduanId
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	public Integer delete(String biaoduanId,String sort)throws Exception;

	
}
