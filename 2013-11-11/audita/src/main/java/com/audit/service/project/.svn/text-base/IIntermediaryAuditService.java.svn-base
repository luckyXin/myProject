/**
 * 
 */
package com.audit.service.project;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.User;
import com.audit.entity.project.IntermediaryAudit;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.ResultInterAuidt;
import com.audit.entity.project.SectionChiefAuditBaseInfo;


/**
 * @author 中介审核接口
 *
 */
public interface IIntermediaryAuditService {

	
	/**
	 * 分页查询中介审核
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	public GridDataModel<IntermediaryAudit> find(Integer page, Integer pagesize, String filed, String order,IntermediaryAudit inter,String arrangerpro);
	
	
	
	/**
	 * 查询安排项目信息
	 * @param arrangeId 安排id
	 * @return
	 */
	public ResultClassArrangeInfo findbyid(String arrangeId);
	
	
	/**
	 * 查询单项目安排信息
	 * @param arrangeId 资料id
	 * @return
	 */
	public ResultClassArrangeInfo findsingleauditbyid(String datapreId);
	
	
	/**
	 * 查询打包项目 
	 * @param datapreId 资料id
	 * @return
	 */
	public   ResultClassArrangeInfo  findpackauditbyid(String datapreId);
	/**
	 * 查询打包项目
	 * @param arrangeId
	 * @return
	 */
	public   ResultClassArrangeInfo  findpackbyid(String arrangeId);
	
	/**
	 * 查询中介审核对象
	 * @param arrangeId
	 * @return
	 */
	public ProIntermediaryAudit findobject(String arrangeId,String datapreId);
	
	
	/**
	 * 录入中介审核
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> add(ProIntermediaryAudit prointer,User user)throws Exception;
	
   /**
    * 修改录入中介审核
    * @param prointer
    * @return
    * @throws Exception
    */
	public Map<String, Object> update(ProIntermediaryAudit prointer,User user)throws Exception;
	
	
	
	/**
	 * 提交中介审核
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addsubmit(ProIntermediaryAudit pro,User user)throws Exception;
	/**
	 * 查询科长未同意信息
	 * @param arrangeId
	 * @return
	 */
	public SectionChiefAuditBaseInfo findbysection(String arrangeId);
	
	
	public List<ProIntermediaryAudit> findauditcutmoney(String id);
	
	
	public Integer findissubmitcount(String arrangeId);
	
	/**
	 * 查询资料中介审核信息
	 * @param id
	 * @return
	 */
	public ResultInterAuidt findinteraudit(String id);
	
	/**
	 * 查询资料政府雇员审核
	 * @param id
	 * @return
	 */
	public ResultInterAuidt findengaudit(String id);
	
	
	/**
	 * 查询资料中介审核信息
	 * @param id
	 * @return
	 */
	public List<ResultInterAuidt> findinterpackaudit(String id);
	
	/**
	 * 查询资料政府雇员审核
	 * @param id
	 * @return
	 */
	public List<ResultInterAuidt> findengpackaudit(String id);
}
