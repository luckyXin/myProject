/**
 * 打包项目安排
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.PackProjectArrange;

/**
 * @author DengXin
 */
public interface IPackProjectArrangeService {

	/**
	 * 分页查询施工企业信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param ownerName 业主名称
	 * @param proejctName 项目名称
	 * @return
	 */
	public GridDataModel<PackProjectArrange> findPackProject(Integer page, Integer pagesize, String filed,
			String order, String ownerName, String proejctName, String userAccount);

	/**
	 * 根据资料id获取项目安排信息
	 * 
	 * @param datapreId
	 * @return
	 */
	public PackProjectArrange getDataPreId(String datapreId);

	/**
	 * 创建项目包
	 * 
	 * @param projectPackName
	 * @param ownerId
	 * @param sentAmount
	 * @param projectArrangeTime
	 * @param projectArrangeRemark
	 * @param mainAuditId
	 * @param intermediaryId
	 * @param governmentEmployees
	 * @param intermediarySendTime
	 * @param intermediaryAuditTime
	 * @param state
	 * @return
	 */
	public Map<String, Object> add(String projectPackName, String ownerId, String sentAmount,
			String projectArrangeTime, String projectArrangeRemark, String mainAuditId, String intermediaryId,
			String[] governmentEmployees, String intermediarySendTime, String intermediaryAuditTime, String state,
			String userAccount) throws Exception;
}
