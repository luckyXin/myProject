/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.project.TractMonthReportInfo;
import com.audit.entity.project.TractPolicyChange;
import com.audit.entity.project.TractProjectChangeCardInfo;
import com.audit.entity.project.TractProjectQingBiao;

/**
 * @author Administrator
 *
 */
public interface ITractProjectArrangeService {

	/**
	 * 查询跟踪审计项目以及以下的标段
	 * @param proejctBiaoDuanInfo
	 * @return
	 */
	public GridDataModel<ProejctBiaoDuanInfo> finProejctBiaoDuanInfo(ProejctBiaoDuanInfo proejctBiaoDuanInfo);
	
	/**
	 * 标段安排信息获取
	 * @param biaoDuanId
	 * @return
	 */
	public TractArrangeProjectInfo getTractArrangeProjectInfo(String biaoDuanId);
	
	/**
	 * 跟踪项目安排信息添加
	 * @param tractArrangeProjectInfo
	 * @return
	 */
	public Map<String, Object> addTractArrangeProjectInfo(TractArrangeProjectInfo tractArrangeProjectInfo) throws Exception;
	
	/**
	 * 跟踪项目信息更新
	 * @param tractArrangeProjectInfo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateTractArrangeProjectInfo(TractArrangeProjectInfo tractArrangeProjectInfo) throws Exception;
	
	
	/**
	 * 根据用户id查询拥有的标段信息
	 * @param id
	 * @return
	 */
	public List<TractArrangeProjectInfo> findBdByUserid(String id);
	
	
	/**
	 * 根据标段id查询所有的月份
	 * @param list
	 * @return
	 */
	public List<String> findMonthByBdid(List<TractArrangeProjectInfo> list);
	
	
	
	/**
	 * 根据标段id和时间查询当月最新标段月报信息
	 * @param id
	 * @param time
	 * @return
	 */
	public TractMonthReportInfo findMonthReportInfoByBdtime(String id,String time);
	
	
	/**
	 * 根据年月信息查询对应标段信息
	 * @param id
	 * @return
	 */
	public List<TractMonthReportInfo> findBiaoDuanByTime(String id);
	
	
	/**
	 * 根据项目id查询项目业主
	 * @param id
	 * @return
	 */
	public ProejctBiaoDuanInfo findOwnerbyBdid(String id);
	
	
	
	/**
	 * 查询变更总金额
	 * @param id
	 * @param time
	 * @param type
	 * @return
	 */
	public TractProjectChangeCardInfo findProjectChangeCardTotalMoney(String id,String time,String type);
	
	
	/**
	 * 查询政策性调整的人工价和材料价
	 * @param id
	 * @param time
	 * @return
	 */
	public TractPolicyChange findTractPolicyChange(String id,String time);
	
	
	/**
	 * 根据标段查询清标后的金额
	 * @param id
	 * @return
	 */
	public TractProjectQingBiao findQingBiaoMoney(String id);
}
