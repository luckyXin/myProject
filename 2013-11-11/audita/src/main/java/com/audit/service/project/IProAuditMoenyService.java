/**
 * 
 */
package com.audit.service.project;

import java.math.BigDecimal;

import com.audit.common.GridDataModel;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.ProAuditMoeny;
import com.audit.entity.project.ResultClassProAuditMoney;

/**
 *dengyong 结算审计费计算
 */
public interface IProAuditMoenyService {


	
	/**
	 * 分页查询审计费用
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	public GridDataModel<ResultClassProAuditMoney> find(Integer page, Integer pagesize, String filed, String order,String auditProjectName);
	
	/**
	 * 查询对象
	 * @param datapreId
	 * @return
	 */
	public ProAuditMoeny findbyid(String datapreId);

	
	
	/**
	 * 计算效益费用
	 * @param data
	 * @throws Exception
	 */
	public void addauditxiaoyimoney(String datapreId,BigDecimal cutmoney) throws Exception;
	
	/**
	 * 增加结算审核费用
	 * @throws Exception 
	 */
	public void addauditmoeny(Datapreinfo data) throws Exception;
}
