/**
 * 我的工作信息
 */
package com.audit.service.work;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.work.MyCompleteWorkInfo;
import com.audit.entity.work.MyNoCompleteWorkInfo;
import com.audit.entity.work.WorkInfo;

/**
 * @author User
 */
public interface IMyWorkService {

	/**
	 * 获取未完成的工作
	 * 
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<MyNoCompleteWorkInfo> getMyNoCompleteWork(Integer page, Integer pagesize, String filed,
			String order, String userAccount);

	/**
	 * 获取完成的工作
	 * 
	 * @param userAccount
	 * @return
	 */
	public GridDataModel<MyCompleteWorkInfo> getMyCompleteWork(Integer page, Integer pagesize, String filed,
			String order, String userAccount);

	/**
	 * 获取未完成工作简略
	 * 
	 * @param userAccount
	 * @return
	 */
	public List<WorkInfo> getMyNoCompleteWork(String userAccount);

	/**
	 * 获取已完成工作简略
	 * 
	 * @param userAccount
	 * @return
	 */
	public List<WorkInfo> getMyCompleteWork(String userAccount);

	/**
	 * 获取三日都未处理的项目
	 */
	public Map<String, Object> getThreeDayNoProccess(String userAccount) throws Exception;
} 
